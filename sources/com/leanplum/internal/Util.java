package com.leanplum.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.TypedValue;
import androidx.annotation.RequiresPermission;
import com.facebook.stetho.common.Utf8Charset;
import com.google.common.net.HttpHeaders;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumDeviceIdMode;
import com.leanplum.LeanplumException;
import com.leanplum.core.BuildConfig;
import com.leanplum.internal.Constants.Defaults;
import com.leanplum.internal.Constants.Keys;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.Constants.Values;
import com.leanplum.monitoring.ExceptionHandler;
import com.leanplum.utils.SharedPreferencesUtil;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import p015io.grpc.internal.GrpcUtil;

public class Util {
    private static final String ACCESS_WIFI_STATE_PERMISSION = "android.permission.ACCESS_WIFI_STATE";
    private static String appName = null;
    private static final Executor asyncExecutor = Executors.newCachedThreadPool();
    private static boolean hasPlayServices = false;
    private static boolean hasPlayServicesCalled = false;
    private static final Executor singleThreadExecutor = Executors.newSingleThreadExecutor();
    private static String versionName = null;

    public static class DeviceIdInfo {

        /* renamed from: id */
        public final String f646id;
        public boolean limitAdTracking;

        public DeviceIdInfo(String str) {
            this.f646id = str;
        }

        public DeviceIdInfo(String str, boolean z) {
            this.f646id = str;
            this.limitAdTracking = z;
        }
    }

    public static String getSystemName() {
        return "Android OS";
    }

    private static String md5(String str) throws Exception {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes(Charset.forName(Utf8Charset.NAME)));
        byte[] digest = instance.digest();
        StringBuilder sb = new StringBuilder();
        for (byte valueOf : digest) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
        }
        return sb.toString();
    }

    public static String sha256(String str) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA256");
        instance.update(str.getBytes(Charset.forName(Utf8Charset.NAME)));
        byte[] digest = instance.digest();
        StringBuilder sb = new StringBuilder();
        for (byte valueOf : digest) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
        }
        return sb.toString();
    }

    private static String checkDeviceId(String str, String str2) {
        if (str2 == null || isValidDeviceId(str2)) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid device id generated (");
        sb.append(str);
        sb.append("): ");
        sb.append(str2);
        Log.m280e(sb.toString());
        return null;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    private static String getWifiMacAddressHash(Context context) {
        String str = "Skipping wifi device id; ";
        if (context.checkCallingOrSelfPermission(ACCESS_WIFI_STATE_PERMISSION) != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("no wifi state permissions.");
            Log.m283v(sb.toString());
            return null;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("null WifiInfo.");
                Log.m281i(sb2.toString());
                return null;
            }
            String macAddress = connectionInfo.getMacAddress();
            if (macAddress != null) {
                if (!macAddress.isEmpty()) {
                    if ("02:00:00:00:00:00".equals(macAddress)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str);
                        sb3.append("Marshmallow and later returns a fake MAC address.");
                        Log.m283v(sb3.toString());
                        return null;
                    }
                    String md5 = md5(connectionInfo.getMacAddress());
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Using wifi device id: ");
                    sb4.append(md5);
                    Log.m283v(sb4.toString());
                    return checkDeviceId("mac address", md5);
                }
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append("no mac address returned.");
            Log.m281i(sb5.toString());
            return null;
        } catch (Exception unused) {
            Log.m284w("Error getting wifi MAC address.");
            return null;
        }
    }

    private static DeviceIdInfo getAdvertisingId(Context context) throws Exception {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            String checkDeviceId = checkDeviceId("advertising id", (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]));
            if (checkDeviceId != null) {
                boolean booleanValue = ((Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue();
                StringBuilder sb = new StringBuilder();
                sb.append("Using advertising device id: ");
                sb.append(checkDeviceId);
                Log.m283v(sb.toString());
                return new DeviceIdInfo(checkDeviceId, booleanValue);
            }
        } catch (Throwable th) {
            Log.m280e("Error getting advertising ID. Google Play Services are not available: ", th);
        }
        return null;
    }

    private static String getAndroidId(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (string == null || string.isEmpty()) {
            Log.m281i("Skipping Android device id; no id returned.");
            return null;
        } else if ("9774d56d682e549c".equals(string)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Skipping Android device id; got invalid device id: ");
            sb.append(string);
            Log.m283v(sb.toString());
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Using Android device id: ");
            sb2.append(string);
            Log.m283v(sb2.toString());
            return checkDeviceId("android id", string);
        }
    }

    private static String generateRandomDeviceId() {
        StringBuilder sb = new StringBuilder();
        sb.append(UUID.randomUUID().toString());
        sb.append("-LP");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Using generated device id: ");
        sb3.append(sb2);
        Log.m283v(sb3.toString());
        return sb2;
    }

    private static boolean isValidForCharset(String str, String str2) {
        CharsetEncoder charsetEncoder;
        try {
            charsetEncoder = Charset.forName(str2).newEncoder();
        } catch (UnsupportedCharsetException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported charset: ");
            sb.append(str2);
            Log.m284w(sb.toString());
            charsetEncoder = null;
        }
        if (charsetEncoder == null || charsetEncoder.canEncode(str)) {
            return true;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Invalid id (contains invalid characters): ");
        sb2.append(str);
        Log.m283v(sb2.toString());
        return false;
    }

    public static boolean isValidUserId(String str) {
        String str2 = "Invalid user id ";
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append("(sentinel): ");
            sb.append(str);
            Log.m283v(sb.toString());
            return false;
        } else if (str.length() > 400) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append("(too long): ");
            sb2.append(str);
            Log.m283v(sb2.toString());
            return false;
        } else if (str.contains("\n")) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append("(contains newline): ");
            sb3.append(str);
            Log.m283v(sb3.toString());
            return false;
        } else if (!str.contains("\"") && !str.contains("'")) {
            return isValidForCharset(str, Utf8Charset.NAME);
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append("(contains quotes): ");
            sb4.append(str);
            Log.m283v(sb4.toString());
            return false;
        }
    }

    public static boolean isValidDeviceId(String str) {
        String str2 = "Invalid device id ";
        if (str == null || str.isEmpty() || "9774d56d682e549c".equals(str) || "0f607264fc6318a92b9e13c65db7cd3c".equals(str) || "f607264fc6318a92b9e13c65db7cd3c".equals(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append("(sentinel): ");
            sb.append(str);
            Log.m283v(sb.toString());
            return false;
        } else if (str.length() > 400) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append("(too long): ");
            sb2.append(str);
            Log.m283v(sb2.toString());
            return false;
        } else if (str.contains("[")) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append("(contains brackets): ");
            sb3.append(str);
            Log.m283v(sb3.toString());
            return false;
        } else if (str.contains("\n")) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append("(contains newline): ");
            sb4.append(str);
            Log.m283v(sb4.toString());
            return false;
        } else if (str.contains(",")) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str2);
            sb5.append("(contains comma): ");
            sb5.append(str);
            Log.m283v(sb5.toString());
            return false;
        } else if (!str.contains("\"") && !str.contains("'")) {
            return isValidForCharset(str, "US-ASCII");
        } else {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(str2);
            sb6.append("(contains quotes): ");
            sb6.append(str);
            Log.m283v(sb6.toString());
            return false;
        }
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static DeviceIdInfo getDeviceId(LeanplumDeviceIdMode leanplumDeviceIdMode) {
        Context context = Leanplum.getContext();
        if (leanplumDeviceIdMode.equals(LeanplumDeviceIdMode.ADVERTISING_ID)) {
            try {
                DeviceIdInfo advertisingId = getAdvertisingId(context);
                if (advertisingId != null) {
                    return advertisingId;
                }
            } catch (Exception e) {
                Log.m280e("Error getting advertising ID", e);
            }
        }
        if ((isSimulator() || leanplumDeviceIdMode.equals(LeanplumDeviceIdMode.ANDROID_ID)) && getAndroidId(context) != null) {
            return new DeviceIdInfo(getAndroidId(context));
        }
        String wifiMacAddressHash = getWifiMacAddressHash(context);
        if (wifiMacAddressHash != null) {
            return new DeviceIdInfo(wifiMacAddressHash);
        }
        String androidId = getAndroidId(context);
        if (androidId != null) {
            return new DeviceIdInfo(androidId);
        }
        return new DeviceIdInfo(generateRandomDeviceId());
    }

    public static String getVersionName() {
        String str = versionName;
        if (str != null) {
            return str;
        }
        Context context = Leanplum.getContext();
        try {
            if (TextUtils.isEmpty(versionName)) {
                versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            }
        } catch (Exception unused) {
            Log.m284w("Could not extract versionName from Manifest or PackageInfo.");
        }
        return versionName;
    }

    public static String getDeviceModel() {
        if (isSimulator()) {
            return "Android Emulator";
        }
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return capitalize(str2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(capitalize(str));
        sb.append(" ");
        sb.append(str2);
        return sb.toString();
    }

    public static String getApplicationName(Context context) {
        String str = appName;
        if (str != null) {
            return str;
        }
        int i = context.getApplicationInfo().labelRes;
        if (i == 0) {
            appName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        } else {
            appName = context.getString(i);
        }
        return appName;
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(charAt));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String getSystemVersion() {
        return VERSION.RELEASE;
    }

    public static boolean isSimulator() {
        String lowerCase = Build.MODEL.toLowerCase(Locale.getDefault());
        return lowerCase.contains("google_sdk") || lowerCase.contains("emulator") || lowerCase.contains("sdk");
    }

    public static String getDeviceName() {
        if (isSimulator()) {
            return "Android Emulator";
        }
        return getDeviceModel();
    }

    public static String getLocale() {
        String language = Locale.getDefault().getLanguage();
        String str = "";
        if (str.equals(language)) {
            language = "xx";
        }
        String country = Locale.getDefault().getCountry();
        if (str.equals(country)) {
            country = "XX";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language);
        sb.append("_");
        sb.append(country);
        return sb.toString();
    }

    private static String getQuery(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        Builder builder = new Builder();
        for (Entry entry : map.entrySet()) {
            if (entry.getValue() == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("RequestOld parameter for key: ");
                sb.append((String) entry.getKey());
                sb.append(" is null.");
                Log.m284w(sb.toString());
            } else {
                builder.appendQueryParameter((String) entry.getKey(), entry.getValue().toString());
            }
        }
        return builder.build().getEncodedQuery();
    }

    public static HttpURLConnection operation(String str, String str2, Map<String, Object> map, String str3, boolean z, int i) throws IOException {
        String str4 = DefaultHttpClient.METHOD_GET;
        if (str4.equals(str3)) {
            str2 = attachGetParameters(str2, map);
        }
        HttpURLConnection createHttpUrlConnection = createHttpUrlConnection(str, str2, str3, z, i);
        if (!str4.equals(str3)) {
            attachPostParameters(map, createHttpUrlConnection);
        }
        if (Constants.enableVerboseLoggingInDevelopmentMode && Constants.isDevelopmentModeEnabled) {
            StringBuilder sb = new StringBuilder();
            sb.append("Sending request at path ");
            sb.append(str2);
            sb.append(" with parameters ");
            sb.append(map);
            Log.m279d(sb.toString());
        }
        return createHttpUrlConnection;
    }

    private static String attachGetParameters(String str, Map<String, Object> map) {
        if (map == null) {
            return str;
        }
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (Entry entry : map.entrySet()) {
            if (entry.getValue() != null) {
                buildUpon.appendQueryParameter((String) entry.getKey(), entry.getValue().toString());
            }
        }
        return buildUpon.build().toString();
    }

    private static void attachPostParameters(Map<String, Object> map, HttpURLConnection httpURLConnection) throws IOException {
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, Utf8Charset.NAME));
        bufferedWriter.write(getQuery(map));
        bufferedWriter.close();
        outputStream.close();
    }

    public static HttpURLConnection createHttpUrlConnection(String str, String str2, String str3, boolean z, int i) throws IOException {
        if (!str2.startsWith("http")) {
            StringBuilder sb = new StringBuilder();
            sb.append(z ? "https://" : "http://");
            sb.append(str);
            sb.append("/");
            sb.append(str2);
            str2 = sb.toString();
        }
        return createHttpUrlConnection(str2, str3, z, i);
    }

    static HttpURLConnection createHttpUrlConnection(String str, String str2, boolean z, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        if (z) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
        }
        int i2 = i * 1000;
        httpURLConnection.setReadTimeout(i2);
        httpURLConnection.setConnectTimeout(i2);
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setDoOutput(!DefaultHttpClient.METHOD_GET.equals(str2));
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        Context context = Leanplum.getContext();
        StringBuilder sb = new StringBuilder();
        sb.append(getApplicationName(context));
        String str3 = "/";
        sb.append(str3);
        sb.append(getVersionName());
        sb.append(str3);
        sb.append(RequestOld.appId());
        sb.append(str3);
        sb.append(Constants.CLIENT);
        sb.append(str3);
        sb.append(Constants.LEANPLUM_VERSION);
        sb.append(str3);
        sb.append(getSystemName());
        sb.append(str3);
        sb.append(getSystemVersion());
        sb.append(str3);
        sb.append(Constants.LEANPLUM_SUPPORTED_ENCODING);
        sb.append(str3);
        sb.append(BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER);
        httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, sb.toString());
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, Constants.LEANPLUM_SUPPORTED_ENCODING);
        return httpURLConnection;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:23|24|25|(2:27|28)|31) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00d2, code lost:
        if (r14 == null) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00d8, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d9, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append(r0);
        r2.append(r14);
        com.leanplum.internal.Log.m284w(r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00f6, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r8 = new java.lang.StringBuilder();
        r8.append("Unable to read file while uploading ");
        r8.append(r7.get(r12));
        com.leanplum.internal.Log.m280e(r8.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x011b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x011c, code lost:
        r9 = new java.lang.StringBuilder();
        r9.append(r0);
        r9.append(r7);
        com.leanplum.internal.Log.m284w(r9.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0132, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0133, code lost:
        if (r14 != null) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0139, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x013a, code lost:
        r9 = new java.lang.StringBuilder();
        r9.append(r0);
        r9.append(r7);
        com.leanplum.internal.Log.m284w(r9.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0150, code lost:
        throw r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00f8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.HttpURLConnection uploadFilesOperation(java.lang.String r6, java.util.List<java.io.File> r7, java.util.List<java.io.InputStream> r8, java.lang.String r9, java.lang.String r10, java.util.Map<java.lang.String, java.lang.Object> r11, java.lang.String r12, boolean r13, int r14) throws java.io.IOException {
        /*
            java.lang.String r0 = "Failed to close InputStream: "
            java.net.HttpURLConnection r9 = createHttpUrlConnection(r9, r10, r12, r13, r14)
            java.lang.String r10 = "Content-Type"
            java.lang.String r12 = "multipart/form-data; boundary===================================leanplum"
            r9.setRequestProperty(r10, r12)
            java.lang.String r10 = "Connection"
            java.lang.String r12 = "Keep-Alive"
            r9.setRequestProperty(r10, r12)
            java.io.DataOutputStream r10 = new java.io.DataOutputStream
            java.io.OutputStream r12 = r9.getOutputStream()
            r10.<init>(r12)
            java.util.Set r11 = r11.entrySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x0025:
            boolean r12 = r11.hasNext()
            java.lang.String r13 = "\r\n"
            if (r12 == 0) goto L_0x0063
            java.lang.Object r12 = r11.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r1 = "--==================================leanplum\r\nContent-Disposition: form-data; name=\""
            r14.append(r1)
            java.lang.Object r1 = r12.getKey()
            java.lang.String r1 = (java.lang.String) r1
            r14.append(r1)
            java.lang.String r1 = "\""
            r14.append(r1)
            r14.append(r13)
            r14.append(r13)
            java.lang.Object r12 = r12.getValue()
            r14.append(r12)
            r14.append(r13)
            java.lang.String r12 = r14.toString()
            r10.writeBytes(r12)
            goto L_0x0025
        L_0x0063:
            r11 = 0
            r12 = 0
        L_0x0065:
            int r14 = r7.size()
            if (r12 >= r14) goto L_0x0151
            java.lang.Object r14 = r7.get(r12)
            java.io.File r14 = (java.io.File) r14
            java.util.Locale r1 = java.util.Locale.getDefault()
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r11] = r6
            java.lang.Integer r3 = java.lang.Integer.valueOf(r12)
            r4 = 1
            r2[r4] = r3
            r3 = 2
            java.lang.String r5 = r14.getName()
            r2[r3] = r5
            java.lang.String r3 = "Content-Disposition: form-data; name=\"%s%d\";filename=\"%s\""
            java.lang.String r1 = java.lang.String.format(r1, r3, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "--==================================leanplum\r\n"
            r2.append(r3)
            r2.append(r1)
            r2.append(r13)
            java.lang.String r1 = "Content-Type: application/octet-stream"
            r2.append(r1)
            r2.append(r13)
            r2.append(r13)
            java.lang.String r1 = r2.toString()
            r10.writeBytes(r1)
            int r1 = r8.size()
            if (r12 >= r1) goto L_0x00bd
            java.lang.Object r14 = r8.get(r12)
            java.io.InputStream r14 = (java.io.InputStream) r14
            goto L_0x00c3
        L_0x00bd:
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r14)
            r14 = r1
        L_0x00c3:
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r1]
        L_0x00c7:
            int r2 = r14.read(r1)     // Catch:{ NullPointerException -> 0x00f8 }
            r3 = -1
            if (r2 == r3) goto L_0x00d2
            r10.write(r1, r11, r2)     // Catch:{ NullPointerException -> 0x00f8 }
            goto L_0x00c7
        L_0x00d2:
            if (r14 == 0) goto L_0x00ef
            r14.close()     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00ef
        L_0x00d8:
            r14 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r4]
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            r1[r11] = r14
            com.leanplum.internal.Log.m284w(r1)
        L_0x00ef:
            r10.writeBytes(r13)
            int r12 = r12 + 1
            goto L_0x0065
        L_0x00f6:
            r6 = move-exception
            goto L_0x0133
        L_0x00f8:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x00f6 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f6 }
            r8.<init>()     // Catch:{ all -> 0x00f6 }
            java.lang.String r9 = "Unable to read file while uploading "
            r8.append(r9)     // Catch:{ all -> 0x00f6 }
            java.lang.Object r7 = r7.get(r12)     // Catch:{ all -> 0x00f6 }
            r8.append(r7)     // Catch:{ all -> 0x00f6 }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x00f6 }
            r6[r11] = r7     // Catch:{ all -> 0x00f6 }
            com.leanplum.internal.Log.m280e(r6)     // Catch:{ all -> 0x00f6 }
            r6 = 0
            if (r14 == 0) goto L_0x0132
            r14.close()     // Catch:{ IOException -> 0x011b }
            goto L_0x0132
        L_0x011b:
            r7 = move-exception
            java.lang.Object[] r8 = new java.lang.Object[r4]
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r0)
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r8[r11] = r7
            com.leanplum.internal.Log.m284w(r8)
        L_0x0132:
            return r6
        L_0x0133:
            if (r14 == 0) goto L_0x0150
            r14.close()     // Catch:{ IOException -> 0x0139 }
            goto L_0x0150
        L_0x0139:
            r7 = move-exception
            java.lang.Object[] r8 = new java.lang.Object[r4]
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r0)
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r8[r11] = r7
            com.leanplum.internal.Log.m284w(r8)
        L_0x0150:
            throw r6
        L_0x0151:
            java.lang.String r6 = "--==================================leanplum--\r\n"
            r10.writeBytes(r6)
            r10.flush()
            r10.close()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.Util.uploadFilesOperation(java.lang.String, java.util.List, java.util.List, java.lang.String, java.lang.String, java.util.Map, java.lang.String, boolean, int):java.net.HttpURLConnection");
    }

    public static void saveResponse(URLConnection uRLConnection, OutputStream outputStream) throws IOException {
        InputStream inputStream = uRLConnection.getInputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.close();
                return;
            }
        }
    }

    private static String getResponse(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        if (httpURLConnection.getResponseCode() < 400) {
            String headerField = httpURLConnection.getHeaderField(GrpcUtil.CONTENT_ENCODING);
            if (headerField == null || !headerField.trim().equalsIgnoreCase(Constants.LEANPLUM_SUPPORTED_ENCODING)) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = new GZIPInputStream(httpURLConnection.getInputStream());
            }
        } else {
            inputStream = httpURLConnection.getErrorStream();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Utf8Charset.NAME));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
                sb.append("\n");
            } else {
                try {
                    break;
                } catch (Exception unused) {
                }
            }
        }
        inputStream.close();
        bufferedReader.close();
        return sb.toString();
    }

    public static JSONObject getJsonResponse(HttpURLConnection httpURLConnection) throws JSONException, IOException {
        String response = getResponse(httpURLConnection);
        if (Constants.enableVerboseLoggingInDevelopmentMode && Constants.isDevelopmentModeEnabled) {
            StringBuilder sb = new StringBuilder();
            sb.append("Received response ");
            sb.append(response);
            Log.m279d(sb.toString());
        }
        return new JSONObject(new JSONTokener(response));
    }

    public static boolean isConnected() {
        boolean z = true;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) Leanplum.getContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                z = false;
            }
            return z;
        } catch (Exception e) {
            Log.m280e("Error getting connectivity info", e);
            return false;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Map<?, ?>, code=java.lang.Object, for r5v0, types: [java.util.Map<?, ?>, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T multiIndex(java.lang.Object r5, java.lang.Object... r6) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r6.length
            r2 = 0
        L_0x0006:
            if (r2 >= r1) goto L_0x001a
            r3 = r6[r2]
            java.util.Map r5 = (java.util.Map) r5
            boolean r4 = r5.containsKey(r3)
            if (r4 != 0) goto L_0x0013
            return r0
        L_0x0013:
            java.lang.Object r5 = r5.get(r3)
            int r2 = r2 + 1
            goto L_0x0006
        L_0x001a:
            java.lang.Object r5 = com.leanplum.internal.CollectionUtil.uncheckedCast(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.Util.multiIndex(java.util.Map, java.lang.Object[]):java.lang.Object");
    }

    public static <T> void executeAsyncTask(boolean z, AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        if (z) {
            asyncTask.executeOnExecutor(singleThreadExecutor, tArr);
        } else {
            asyncTask.executeOnExecutor(asyncExecutor, tArr);
        }
    }

    public static boolean hasPlayServices() {
        String str = "com.google.android.gms";
        if (hasPlayServicesCalled) {
            return hasPlayServices;
        }
        PackageManager packageManager = Leanplum.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (packageInfo.versionCode < 4242000) {
                StringBuilder sb = new StringBuilder();
                sb.append("Google Play services version is too old: ");
                sb.append(packageInfo.versionCode);
                Log.m281i(sb.toString());
                hasPlayServicesCalled = true;
                hasPlayServices = false;
                return false;
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                hasPlayServicesCalled = true;
                hasPlayServices = applicationInfo.enabled;
                return applicationInfo.enabled;
            } catch (NameNotFoundException unused) {
                hasPlayServicesCalled = true;
                hasPlayServices = false;
                return false;
            }
        } catch (NameNotFoundException unused2) {
            hasPlayServicesCalled = true;
            hasPlayServices = false;
            return false;
        }
    }

    public static boolean isInBackground() {
        return LeanplumActivityHelper.getCurrentActivity() == null || LeanplumActivityHelper.isActivityPaused();
    }

    public static void initializePreLeanplumInstall(Map<String, Object> map) {
        Context context = Leanplum.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Defaults.LEANPLUM, 0);
        String str = Keys.INSTALL_TIME_INITIALIZED;
        if (!sharedPreferences.getBoolean(str, false)) {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            setInstallTime(map, packageManager, packageName);
            setUpdateTime(map, packageManager, packageName);
            Editor edit = sharedPreferences.edit();
            edit.putBoolean(str, true);
            SharedPreferencesUtil.commitChanges(edit);
        }
    }

    private static void setInstallTime(Map<String, Object> map, PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            String str2 = Params.INSTALL_DATE;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(((double) packageInfo.firstInstallTime) / 1000.0d);
            map.put(str2, sb.toString());
        } catch (NameNotFoundException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to find package info: ");
            sb2.append(e);
            Log.m284w(sb2.toString());
        }
    }

    private static void setUpdateTime(Map<String, Object> map, PackageManager packageManager, String str) {
        try {
            File file = new File(packageManager.getApplicationInfo(str, 0).sourceDir);
            if (file.exists()) {
                String str2 = Params.UPDATE_DATE;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(((double) file.lastModified()) / 1000.0d);
                map.put(str2, sb.toString());
            }
        } catch (Throwable th) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to find package info: ");
            sb2.append(th);
            Log.m284w(sb2.toString());
        }
    }

    public static void initExceptionHandling(Context context) {
        ExceptionHandler.getInstance().setContext(context);
    }

    public static void handleException(Throwable th) {
        String str;
        String str2;
        ExceptionHandler.getInstance().reportException(th);
        if (th instanceof OutOfMemoryError) {
            if (Constants.isDevelopmentModeEnabled) {
                throw ((OutOfMemoryError) th);
            }
        } else if (!(th instanceof LeanplumException)) {
            Log.m280e("INTERNAL ERROR", th);
            try {
                str = getVersionName();
            } catch (Throwable unused) {
                str = "(Unknown)";
            }
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", Values.SDK_ERROR);
                String message = th.getMessage();
                if (message != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(th.toString());
                    sb.append(" (");
                    sb.append(message);
                    sb.append(')');
                    str2 = sb.toString();
                } else {
                    str2 = th.toString();
                }
                hashMap.put("message", str2);
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                hashMap.put("stackTrace", stringWriter.toString());
                hashMap.put(Params.VERSION_NAME, str);
                RequestOld.post(Methods.LOG, hashMap).send();
            } catch (Throwable th2) {
                Log.m280e("Unable to send error report.", th2);
            }
        } else if (Constants.isDevelopmentModeEnabled) {
            throw ((LeanplumException) th);
        }
    }

    public static <K, V> Map<K, V> newMap(K k, V v, Object... objArr) {
        if (objArr.length % 2 != 1) {
            HashMap hashMap = new HashMap();
            hashMap.put(k, v);
            for (int i = 0; i < objArr.length; i += 2) {
                hashMap.put(CollectionUtil.uncheckedCast(objArr[i]), CollectionUtil.uncheckedCast(objArr[i + 1]));
            }
            return hashMap;
        }
        throw new IllegalArgumentException("Must supply an even number of values.");
    }

    public static String generateResourceNameFromId(int i) {
        if (i <= 0) {
            try {
                Log.m284w("Provided resource id is invalid.");
                return null;
            } catch (Exception e) {
                Log.m284w("Failed to generate resource name from provided resource id: ", e);
                handleException(e);
                return null;
            }
        } else {
            Resources resources = Leanplum.getContext().getResources();
            String resourceEntryName = resources.getResourceEntryName(i);
            String resourceTypeName = resources.getResourceTypeName(i);
            TypedValue typedValue = new TypedValue();
            resources.getValue(i, typedValue, true);
            String[] split = typedValue.string.toString().split("\\.(?=[^\\.]+$)");
            String str = "";
            if (split.length == 2) {
                StringBuilder sb = new StringBuilder();
                sb.append(".");
                sb.append(split[1]);
                str = sb.toString();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(resourceTypeName);
            sb2.append("/");
            sb2.append(resourceEntryName);
            sb2.append(str);
            return sb2.toString();
        }
    }

    public static int generateIdFromResourceName(String str) {
        String[] split = str.split("/");
        if (split.length == 2) {
            Resources resources = Leanplum.getContext().getResources();
            String str2 = split[0];
            String str3 = split[1];
            String[] split2 = str3.split("\\.(?=[^\\.]+$)");
            if (split2.length == 2) {
                str3 = split2[0];
            }
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                return resources.getIdentifier(str3, str2, Leanplum.getContext().getPackageName());
            }
        }
        Log.m284w("Could not extract resource id from provided resource name: ", str);
        return 0;
    }
}
