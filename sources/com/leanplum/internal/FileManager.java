package com.leanplum.internal;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.internal.Constants.Keys;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileManager {
    private static boolean initializing = false;
    static final Object initializingLock = new Object();
    private static boolean isInitialized = false;
    public static Var<HashMap<String, Object>> resources = null;
    private static ResourceUpdateCallback updateCallback;

    public enum DownloadFileResult {
        NONE,
        EXISTS_IN_ASSETS,
        DOWNLOADING
    }

    static class HashResults {
        final String hash;
        final int size;

        public HashResults(String str, int i) {
            this.hash = str;
            this.size = i;
        }
    }

    interface ResourceUpdateCallback {
        void onResourceSyncFinish();
    }

    private static String appBundlePath() {
        return "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x005d A[SYNTHETIC, Splitter:B:29:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0073 A[SYNTHETIC, Splitter:B:37:0x0073] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.leanplum.internal.FileManager.DownloadFileResult maybeDownloadFile(boolean r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, final java.lang.Runnable r8) {
        /*
            java.lang.String r0 = "Failed to close InputStream."
            if (r5 == 0) goto L_0x00b5
            boolean r6 = r5.equals(r6)
            if (r6 != 0) goto L_0x00b5
            if (r4 == 0) goto L_0x0012
            int r4 = com.leanplum.internal.VarCache.getResIdFromPath(r5)
            if (r4 != 0) goto L_0x00b5
        L_0x0012:
            r4 = 0
            r6 = 1
            r1 = 0
            r2 = 2
            android.content.Context r3 = com.leanplum.Leanplum.getContext()     // Catch:{ IOException -> 0x0070, all -> 0x005a }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ IOException -> 0x0070, all -> 0x005a }
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ IOException -> 0x0070, all -> 0x005a }
            java.io.InputStream r3 = r3.open(r5)     // Catch:{ IOException -> 0x0070, all -> 0x005a }
            if (r3 == 0) goto L_0x0045
            com.leanplum.internal.FileManager$DownloadFileResult r4 = com.leanplum.internal.FileManager.DownloadFileResult.EXISTS_IN_ASSETS     // Catch:{ IOException -> 0x0043, all -> 0x003f }
            if (r3 == 0) goto L_0x003e
            r3.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x003e
        L_0x0030:
            r5 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r2]
            r7[r1] = r0
            java.lang.String r5 = r5.getMessage()
            r7[r6] = r5
            com.leanplum.internal.Log.m284w(r7)
        L_0x003e:
            return r4
        L_0x003f:
            r4 = move-exception
            r5 = r4
            r4 = r3
            goto L_0x005b
        L_0x0043:
            goto L_0x0071
        L_0x0045:
            if (r3 == 0) goto L_0x0085
            r3.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x0085
        L_0x004b:
            r3 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r0
            java.lang.String r0 = r3.getMessage()
            r2[r6] = r0
            com.leanplum.internal.Log.m284w(r2)
            goto L_0x0085
        L_0x005a:
            r5 = move-exception
        L_0x005b:
            if (r4 == 0) goto L_0x006f
            r4.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x006f
        L_0x0061:
            r4 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r2]
            r7[r1] = r0
            java.lang.String r4 = r4.getMessage()
            r7[r6] = r4
            com.leanplum.internal.Log.m284w(r7)
        L_0x006f:
            throw r5
        L_0x0070:
            r3 = r4
        L_0x0071:
            if (r3 == 0) goto L_0x0085
            r3.close()     // Catch:{ IOException -> 0x0077 }
            goto L_0x0085
        L_0x0077:
            r3 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r0
            java.lang.String r0 = r3.getMessage()
            r2[r6] = r0
            com.leanplum.internal.Log.m284w(r2)
        L_0x0085:
            java.lang.String r6 = fileRelativeToAppBundle(r5)
            boolean r6 = fileExistsAtPath(r6)
            if (r6 != 0) goto L_0x00b5
            java.lang.String r6 = fileRelativeToDocuments(r5)
            boolean r6 = fileExistsAtPath(r6)
            if (r6 != 0) goto L_0x00b5
            java.lang.String r6 = "downloadFile"
            com.leanplum.internal.RequestOld r4 = com.leanplum.internal.RequestOld.get(r6, r4)
            com.leanplum.internal.FileManager$1 r6 = new com.leanplum.internal.FileManager$1
            r6.<init>(r8)
            r4.onResponse(r6)
            com.leanplum.internal.FileManager$2 r6 = new com.leanplum.internal.FileManager$2
            r6.<init>(r8)
            r4.onError(r6)
            r4.downloadFile(r5, r7)
            com.leanplum.internal.FileManager$DownloadFileResult r4 = com.leanplum.internal.FileManager.DownloadFileResult.DOWNLOADING
            return r4
        L_0x00b5:
            com.leanplum.internal.CountAggregator r4 = com.leanplum.Leanplum.countAggregator()
            java.lang.String r5 = "maybe_download_file"
            r4.incrementCount(r5)
            com.leanplum.internal.FileManager$DownloadFileResult r4 = com.leanplum.internal.FileManager.DownloadFileResult.NONE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.FileManager.maybeDownloadFile(boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Runnable):com.leanplum.internal.FileManager$DownloadFileResult");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        com.leanplum.internal.Log.m284w(r0, r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x007a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007b, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007f, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0080, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0083, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[SYNTHETIC, Splitter:B:30:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007f A[ExcHandler: NoSuchAlgorithmException (r10v1 'e' java.security.NoSuchAlgorithmException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.leanplum.internal.FileManager.HashResults fileMD5HashCreateWithPath(java.io.InputStream r10) {
        /*
            java.lang.String r0 = "Failed to close InputStream."
            r1 = 0
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r3 = 2
            r4 = 1
            r5 = 0
            java.security.DigestInputStream r6 = new java.security.DigestInputStream     // Catch:{ all -> 0x0062 }
            r6.<init>(r10, r2)     // Catch:{ all -> 0x0062 }
            r10 = 8192(0x2000, float:1.14794E-41)
            byte[] r10 = new byte[r10]     // Catch:{ all -> 0x0060 }
            r7 = 0
        L_0x0016:
            int r8 = r6.read(r10)     // Catch:{ all -> 0x0060 }
            r9 = -1
            if (r8 == r9) goto L_0x001f
            int r7 = r7 + r8
            goto L_0x0016
        L_0x001f:
            r6.close()     // Catch:{ IOException -> 0x0023, NoSuchAlgorithmException -> 0x007f }
            goto L_0x0031
        L_0x0023:
            r10 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r3[r5] = r0     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            java.lang.String r10 = r10.getMessage()     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r3[r4] = r10     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            com.leanplum.internal.Log.m284w(r3)     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
        L_0x0031:
            byte[] r10 = r2.digest()     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r0.<init>()     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            int r2 = r10.length     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
        L_0x003b:
            if (r5 >= r2) goto L_0x0056
            byte r3 = r10[r5]     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r3 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            int r6 = r3.length()     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            if (r6 != r4) goto L_0x0050
            r6 = 48
            r0.append(r6)     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
        L_0x0050:
            r0.append(r3)     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            int r5 = r5 + 1
            goto L_0x003b
        L_0x0056:
            com.leanplum.internal.FileManager$HashResults r10 = new com.leanplum.internal.FileManager$HashResults     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            java.lang.String r0 = r0.toString()     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r10.<init>(r0, r7)     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            return r10
        L_0x0060:
            r10 = move-exception
            goto L_0x0065
        L_0x0062:
            r2 = move-exception
            r6 = r10
            r10 = r2
        L_0x0065:
            if (r6 == 0) goto L_0x0079
            r6.close()     // Catch:{ IOException -> 0x006b, NoSuchAlgorithmException -> 0x007f }
            goto L_0x0079
        L_0x006b:
            r2 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r3[r5] = r0     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            java.lang.String r0 = r2.getMessage()     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            r3[r4] = r0     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
            com.leanplum.internal.Log.m284w(r3)     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
        L_0x0079:
            throw r10     // Catch:{ NoSuchAlgorithmException -> 0x007f, IOException -> 0x007a }
        L_0x007a:
            r10 = move-exception
            r10.printStackTrace()
            return r1
        L_0x007f:
            r10 = move-exception
            r10.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.FileManager.fileMD5HashCreateWithPath(java.io.InputStream):com.leanplum.internal.FileManager$HashResults");
    }

    static int getFileSize(String str) {
        if (str == null) {
            return -1;
        }
        return (int) new File(str).length();
    }

    public static boolean fileExistsAtPath(String str) {
        return str != null && new File(str).exists();
    }

    private static String documentsPath() {
        Context context = Leanplum.getContext();
        if (context != null) {
            return context.getDir("Leanplum_Documents", 0).getAbsolutePath();
        }
        return null;
    }

    private static String bundlePath() {
        return Leanplum.getContext().getDir("Leanplum_Bundle", 0).getAbsolutePath();
    }

    private static String fileRelativeTo(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    static String fileRelativeToAppBundle(String str) {
        if (str == null) {
            return null;
        }
        return fileRelativeTo(appBundlePath(), str);
    }

    static String fileRelativeToLPBundle(String str) {
        if (str == null) {
            return null;
        }
        return fileRelativeTo(bundlePath(), str);
    }

    public static String fileRelativeToDocuments(String str) {
        if (str == null) {
            return null;
        }
        return fileRelativeTo(documentsPath(), str);
    }

    static boolean isNewerLocally(Map<String, Object> map, Map<String, Object> map2) {
        boolean z = true;
        if (map2 == null) {
            return true;
        }
        String str = Keys.HASH;
        String str2 = (String) map.get(str);
        String str3 = (String) map2.get(str);
        String str4 = Keys.SIZE;
        Integer num = (Integer) map.get(str4);
        Integer num2 = (Integer) map2.get(str4);
        if (num2 != null && ((num == null || num.equals(num2)) && (str2 == null || (str3 != null && str2.equals(str3))))) {
            z = false;
        }
        return z;
    }

    static void setResourceSyncFinishBlock(ResourceUpdateCallback resourceUpdateCallback) {
        updateCallback = resourceUpdateCallback;
    }

    static boolean initializing() {
        return initializing;
    }

    public static boolean isResourceSyncingEnabled() {
        return initializing || isInitialized;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0128 A[SYNTHETIC, Splitter:B:51:0x0128] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0141 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0153 A[SYNTHETIC, Splitter:B:67:0x0153] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void enableResourceSyncing(java.util.List<java.util.regex.Pattern> r14, java.util.List<java.util.regex.Pattern> r15) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "__Android Resources"
            com.leanplum.Var r0 = com.leanplum.Var.define(r1, r0)
            resources = r0
            java.lang.String r0 = "res/drawable"
            java.lang.String r1 = "res/layout"
            android.content.Context r2 = com.leanplum.Leanplum.getContext()
            r3 = 2
            r4 = 1
            r5 = 0
            r6 = 0
            java.util.zip.ZipInputStream r7 = new java.util.zip.ZipInputStream     // Catch:{ IOException -> 0x0109 }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0109 }
            java.lang.String r2 = r2.getPackageResourcePath()     // Catch:{ IOException -> 0x0109 }
            r8.<init>(r2)     // Catch:{ IOException -> 0x0109 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0109 }
        L_0x0027:
            java.util.zip.ZipEntry r2 = r7.getNextEntry()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r2 == 0) goto L_0x00ec
            java.lang.String r6 = r2.getName()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            boolean r8 = r6.startsWith(r0)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r8 != 0) goto L_0x003d
            boolean r8 = r6.startsWith(r1)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r8 == 0) goto L_0x0027
        L_0x003d:
            r8 = 4
            java.lang.String r8 = r6.substring(r8)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r14 == 0) goto L_0x006a
            int r9 = r14.size()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r9 <= 0) goto L_0x006a
            java.util.Iterator r9 = r14.iterator()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
        L_0x004e:
            boolean r10 = r9.hasNext()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r10 == 0) goto L_0x0066
            java.lang.Object r10 = r9.next()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.util.regex.Pattern r10 = (java.util.regex.Pattern) r10     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.util.regex.Matcher r10 = r10.matcher(r8)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            boolean r10 = r10.matches()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r10 == 0) goto L_0x004e
            r9 = 1
            goto L_0x0067
        L_0x0066:
            r9 = 0
        L_0x0067:
            if (r9 != 0) goto L_0x006a
            goto L_0x0027
        L_0x006a:
            if (r15 == 0) goto L_0x008c
            java.util.Iterator r9 = r15.iterator()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
        L_0x0070:
            boolean r10 = r9.hasNext()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r10 == 0) goto L_0x0088
            java.lang.Object r10 = r9.next()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.util.regex.Pattern r10 = (java.util.regex.Pattern) r10     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.util.regex.Matcher r10 = r10.matcher(r8)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            boolean r10 = r10.matches()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            if (r10 == 0) goto L_0x0070
            r9 = 1
            goto L_0x0089
        L_0x0088:
            r9 = 0
        L_0x0089:
            if (r9 == 0) goto L_0x008c
            goto L_0x0027
        L_0x008c:
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r9.<init>()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r10 = 8192(0x2000, float:1.14794E-41)
            byte[] r10 = new byte[r10]     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r11 = 0
        L_0x0096:
            int r12 = r7.read(r10)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r13 = -1
            if (r12 == r13) goto L_0x00a2
            r9.write(r10, r5, r12)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            int r11 = r11 + r12
            goto L_0x0096
        L_0x00a2:
            r7.closeEntry()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r10.<init>()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.String r12 = ""
            r10.append(r12)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            long r12 = r2.getTime()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r10.append(r12)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.String r2 = ""
            r10.append(r2)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r10.append(r11)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.String r2 = r10.toString()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r10.<init>()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.String r12 = "__Android Resources."
            r10.append(r12)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.String r12 = "."
            java.lang.String r13 = "\\."
            java.lang.String r8 = r8.replace(r12, r13)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r12 = 47
            r13 = 46
            java.lang.String r8 = r8.replace(r12, r13)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            r10.append(r8)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            java.lang.String r8 = r10.toString()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            byte[] r9 = r9.toByteArray()     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            com.leanplum.Var.defineResource(r8, r6, r11, r2, r9)     // Catch:{ IOException -> 0x0103, all -> 0x0101 }
            goto L_0x0027
        L_0x00ec:
            r7.close()     // Catch:{ IOException -> 0x00f0 }
            goto L_0x013c
        L_0x00f0:
            r14 = move-exception
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r0 = "Failed to close ZipInputStream."
            r15[r5] = r0
            java.lang.String r14 = r14.getMessage()
            r15[r4] = r14
            com.leanplum.internal.Log.m284w(r15)
            goto L_0x013c
        L_0x0101:
            r14 = move-exception
            goto L_0x0151
        L_0x0103:
            r14 = move-exception
            r6 = r7
            goto L_0x010a
        L_0x0106:
            r14 = move-exception
            r7 = r6
            goto L_0x0151
        L_0x0109:
            r14 = move-exception
        L_0x010a:
            java.lang.Object[] r15 = new java.lang.Object[r4]     // Catch:{ all -> 0x0106 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0106 }
            r0.<init>()     // Catch:{ all -> 0x0106 }
            java.lang.String r1 = "Error occurred when trying to enable resource syncing."
            r0.append(r1)     // Catch:{ all -> 0x0106 }
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0106 }
            r0.append(r14)     // Catch:{ all -> 0x0106 }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x0106 }
            r15[r5] = r14     // Catch:{ all -> 0x0106 }
            com.leanplum.internal.Log.m284w(r15)     // Catch:{ all -> 0x0106 }
            if (r6 == 0) goto L_0x013c
            r6.close()     // Catch:{ IOException -> 0x012c }
            goto L_0x013c
        L_0x012c:
            r14 = move-exception
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r0 = "Failed to close ZipInputStream."
            r15[r5] = r0
            java.lang.String r14 = r14.getMessage()
            r15[r4] = r14
            com.leanplum.internal.Log.m284w(r15)
        L_0x013c:
            isInitialized = r4
            java.lang.Object r14 = initializingLock
            monitor-enter(r14)
            initializing = r5     // Catch:{ all -> 0x014e }
            com.leanplum.internal.FileManager$ResourceUpdateCallback r15 = updateCallback     // Catch:{ all -> 0x014e }
            if (r15 == 0) goto L_0x014c
            com.leanplum.internal.FileManager$ResourceUpdateCallback r15 = updateCallback     // Catch:{ all -> 0x014e }
            r15.onResourceSyncFinish()     // Catch:{ all -> 0x014e }
        L_0x014c:
            monitor-exit(r14)     // Catch:{ all -> 0x014e }
            return
        L_0x014e:
            r15 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x014e }
            throw r15
        L_0x0151:
            if (r7 == 0) goto L_0x0167
            r7.close()     // Catch:{ IOException -> 0x0157 }
            goto L_0x0167
        L_0x0157:
            r15 = move-exception
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r1 = "Failed to close ZipInputStream."
            r0[r5] = r1
            java.lang.String r15 = r15.getMessage()
            r0[r4] = r15
            com.leanplum.internal.Log.m284w(r0)
        L_0x0167:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.FileManager.enableResourceSyncing(java.util.List, java.util.List):void");
    }

    private static List<Pattern> compilePatterns(List<String> list) {
        if (list == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            try {
                arrayList.add(Pattern.compile(str));
            } catch (PatternSyntaxException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Ignoring malformed resource syncing pattern: \"");
                sb.append(str);
                sb.append("\". Patterns must be regular expressions.");
                Log.m280e(sb.toString());
            }
        }
        return arrayList;
    }

    public static void enableResourceSyncing(List<String> list, List<String> list2, boolean z) {
        initializing = true;
        if (!isInitialized) {
            try {
                final List compilePatterns = compilePatterns(list);
                final List compilePatterns2 = compilePatterns(list2);
                if (z) {
                    Util.executeAsyncTask(false, new AsyncTask<Void, Void, Void>() {
                        /* access modifiers changed from: protected */
                        public Void doInBackground(Void... voidArr) {
                            try {
                                FileManager.enableResourceSyncing(compilePatterns, compilePatterns2);
                            } catch (Throwable th) {
                                Util.handleException(th);
                            }
                            return null;
                        }
                    }, new Void[0]);
                } else {
                    enableResourceSyncing(compilePatterns, compilePatterns2);
                }
            } catch (Throwable th) {
                Util.handleException(th);
            }
        }
    }

    public static String fileValue(String str) {
        return fileValue(str, str, null);
    }

    public static String fileValue(String str, String str2, Boolean bool) {
        if (str == null) {
            return null;
        }
        if (str.equals(str2)) {
            String fileRelativeToAppBundle = fileRelativeToAppBundle(str2);
            if (fileExistsAtPath(fileRelativeToAppBundle)) {
                return fileRelativeToAppBundle;
            }
        }
        if (bool == null) {
            try {
                InputStream open = Leanplum.getContext().getAssets().open(str);
                if (open != null) {
                    try {
                        open.close();
                    } catch (Exception e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to close InputStream: ");
                        sb.append(e);
                        Log.m284w(sb.toString());
                    }
                }
                return str;
            } catch (Exception unused) {
            }
        } else {
            if (bool.booleanValue()) {
                return str;
            }
            String fileRelativeToLPBundle = fileRelativeToLPBundle(str);
            if (!fileExistsAtPath(fileRelativeToLPBundle)) {
                fileRelativeToLPBundle = fileRelativeToDocuments(str);
                if (!fileExistsAtPath(fileRelativeToLPBundle)) {
                    fileRelativeToLPBundle = fileRelativeToAppBundle(str);
                    if (!fileExistsAtPath(fileRelativeToLPBundle)) {
                        fileRelativeToLPBundle = fileRelativeToLPBundle(str2);
                        if (!fileExistsAtPath(fileRelativeToLPBundle)) {
                            fileRelativeToLPBundle = fileRelativeToAppBundle(str2);
                            if (!fileExistsAtPath(fileRelativeToLPBundle)) {
                                return str2;
                            }
                        }
                    }
                }
            }
            return fileRelativeToLPBundle;
        }
    }

    public static InputStream stream(boolean z, Boolean bool, Boolean bool2, String str, String str2, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Context context = Leanplum.getContext();
            if (z && str.equals(str2) && bArr != null) {
                return new ByteArrayInputStream(bArr);
            }
            if (z && str.equals(str2) && bArr == null) {
                int generateIdFromResourceName = Util.generateIdFromResourceName(str);
                if (generateIdFromResourceName == 0) {
                    return null;
                }
                Resources resources2 = context.getResources();
                StringBuilder sb = new StringBuilder();
                sb.append("android.resource://");
                sb.append(resources2.getResourcePackageName(generateIdFromResourceName));
                sb.append('/');
                sb.append(resources2.getResourceTypeName(generateIdFromResourceName));
                sb.append('/');
                sb.append(resources2.getResourceEntryName(generateIdFromResourceName));
                return context.getContentResolver().openInputStream(Uri.parse(sb.toString()));
            } else if (bool2 == null) {
                try {
                    return context.getAssets().open(str);
                } catch (IOException unused) {
                }
            } else {
                if (!bool2.booleanValue()) {
                    if (bool.booleanValue() && str.equals(str2)) {
                    }
                    return new FileInputStream(new File(str));
                }
                return context.getAssets().open(str);
            }
        } catch (IOException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to load a stream.");
            sb2.append(e.getMessage());
            Log.m284w(sb2.toString());
            return null;
        }
    }
}
