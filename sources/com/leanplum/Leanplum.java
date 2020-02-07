package com.leanplum;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.leanplum.ActionContext.ContextualValues;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.MessageDisplayedCallback;
import com.leanplum.callbacks.RegisterDeviceCallback;
import com.leanplum.callbacks.RegisterDeviceFinishedCallback;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.internal.ActionManager;
import com.leanplum.internal.Constants;
import com.leanplum.internal.Constants.Defaults;
import com.leanplum.internal.Constants.Keys;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.Constants.Values;
import com.leanplum.internal.CountAggregator;
import com.leanplum.internal.FeatureFlagManager;
import com.leanplum.internal.FileManager;
import com.leanplum.internal.JsonConverter;
import com.leanplum.internal.LeanplumEventDataManager;
import com.leanplum.internal.LeanplumInternal;
import com.leanplum.internal.LeanplumInternal.locationAttributeRequestsCallback;
import com.leanplum.internal.LeanplumUIEditorWrapper;
import com.leanplum.internal.Log;
import com.leanplum.internal.OsHandler;
import com.leanplum.internal.RequestOld;
import com.leanplum.internal.RequestOld.ApiResponseCallback;
import com.leanplum.internal.RequestOld.ErrorCallback;
import com.leanplum.internal.RequestOld.ResponseCallback;
import com.leanplum.internal.Util;
import com.leanplum.internal.Util.DeviceIdInfo;
import com.leanplum.internal.VarCache;
import com.leanplum.messagetemplates.MessageTemplates;
import com.leanplum.models.GeofenceEventType;
import com.leanplum.models.MessageArchiveData;
import com.leanplum.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class Leanplum {
    public static final int ACTION_KIND_ACTION = 2;
    public static final int ACTION_KIND_MESSAGE = 1;
    private static final String LEANPLUM_NOTIFICATION_CHANNEL = "com.leanplum.LeanplumNotificationChannel";
    private static final String LEANPLUM_PUSH_SERVICE = "com.leanplum.LeanplumPushService";
    public static final String PURCHASE_EVENT_NAME = "Purchase";
    /* access modifiers changed from: private */
    public static Context context;
    /* access modifiers changed from: private */
    public static CountAggregator countAggregator = new CountAggregator();
    private static String customDeviceId;
    private static LeanplumDeviceIdMode deviceIdMode = LeanplumDeviceIdMode.MD5_MAC_ADDRESS;
    private static FeatureFlagManager featureFlagManager = FeatureFlagManager.INSTANCE;
    private static ScheduledExecutorService heartbeatExecutor = null;
    private static final Object heartbeatLock = new Object();
    private static boolean initializedMessageTemplates = false;
    private static boolean locationCollectionEnabled = true;
    private static final ArrayList<MessageDisplayedCallback> messageDisplayedHandlers = new ArrayList<>();
    private static final ArrayList<VariablesChangedCallback> noDownloadsHandlers = new ArrayList<>();
    private static final ArrayList<VariablesChangedCallback> onceNoDownloadsHandlers = new ArrayList<>();
    private static Runnable pushStartCallback;
    /* access modifiers changed from: private */
    public static RegisterDeviceFinishedCallback registerDeviceFinishedHandler;
    /* access modifiers changed from: private */
    public static RegisterDeviceCallback registerDeviceHandler;
    private static final ArrayList<StartCallback> startHandlers = new ArrayList<>();
    private static boolean userSpecifiedDeviceId;
    private static final ArrayList<VariablesChangedCallback> variablesChangedHandlers = new ArrayList<>();

    private Leanplum() {
    }

    public static void setApiConnectionSettings(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("setApiConnectionSettings - Empty hostname parameter provided.");
        } else if (TextUtils.isEmpty(str2)) {
            Log.m280e("setApiConnectionSettings - Empty servletName parameter provided.");
        } else {
            Constants.API_HOST_NAME = str;
            Constants.API_SERVLET = str2;
            Constants.API_SSL = z;
        }
    }

    public static void setSocketConnectionSettings(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("setSocketConnectionSettings - Empty hostName parameter provided.");
        } else if (i < 1 || i > 65535) {
            Log.m280e("setSocketConnectionSettings - Invalid port parameter provided.");
        } else {
            Constants.SOCKET_HOST = str;
            Constants.SOCKET_PORT = i;
        }
    }

    public static void setFileHashingEnabledInDevelopmentMode(boolean z) {
        Constants.hashFilesToDetermineModifications = z;
    }

    public static void setFileUploadingEnabledInDevelopmentMode(boolean z) {
        Constants.enableFileUploadingInDevelopmentMode = z;
    }

    public static void enableVerboseLoggingInDevelopmentMode() {
        Constants.enableVerboseLoggingInDevelopmentMode = true;
    }

    public static void setNetworkTimeout(int i, int i2) {
        if (i < 0) {
            Log.m280e("setNetworkTimeout - Invalid seconds parameter provided.");
        } else if (i2 < 0) {
            Log.m280e("setNetworkTimeout - Invalid downloadSeconds parameter provided.");
        } else {
            Constants.NETWORK_TIMEOUT_SECONDS = i;
            Constants.NETWORK_TIMEOUT_SECONDS_FOR_DOWNLOADS = i2;
        }
    }

    public static void setCanDownloadContentMidSessionInProductionMode(boolean z) {
        Constants.canDownloadContentMidSessionInProduction = z;
    }

    public static void setAppIdForDevelopmentMode(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("setAppIdForDevelopmentMode - Empty appId parameter provided.");
        } else if (TextUtils.isEmpty(str2)) {
            Log.m280e("setAppIdForDevelopmentMode - Empty accessKey parameter provided.");
        } else {
            Constants.isDevelopmentModeEnabled = true;
            RequestOld.setAppId(str, str2);
        }
    }

    public static void setAppIdForProductionMode(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("setAppIdForProductionMode - Empty appId parameter provided.");
        } else if (TextUtils.isEmpty(str2)) {
            Log.m280e("setAppIdForProductionMode - Empty accessKey parameter provided.");
        } else {
            Constants.isDevelopmentModeEnabled = false;
            RequestOld.setAppId(str, str2);
        }
    }

    public static void trackAllAppScreens() {
        LeanplumInternal.enableAutomaticScreenTracking();
    }

    public static void setVariantDebugInfoEnabled(boolean z) {
        LeanplumInternal.setIsVariantDebugInfoEnabled(z);
        countAggregator.incrementCount("set_variant_debug_info_enabled");
    }

    public static boolean isScreenTrackingEnabled() {
        return LeanplumInternal.getIsScreenTrackingEnabled();
    }

    public static boolean isInterfaceEditingEnabled() {
        return LeanplumUIEditorWrapper.isUIEditorAvailable();
    }

    public static void setDeviceIdMode(LeanplumDeviceIdMode leanplumDeviceIdMode) {
        if (leanplumDeviceIdMode == null) {
            Log.m280e("setDeviceIdMode - Invalid mode parameter provided.");
            return;
        }
        deviceIdMode = leanplumDeviceIdMode;
        userSpecifiedDeviceId = true;
    }

    public static void setDeviceId(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.m284w("setDeviceId - Empty deviceId parameter provided.");
        }
        customDeviceId = str;
        userSpecifiedDeviceId = true;
    }

    public static String getDeviceId() {
        if (LeanplumInternal.hasCalledStart()) {
            return RequestOld.deviceId();
        }
        Log.m280e("Leanplum.start() must be called before calling getDeviceId.");
        return null;
    }

    public static void setApplicationContext(Context context2) {
        if (context2 == null) {
            Log.m284w("setApplicationContext - Null context parameter provided.");
        }
        context = context2;
    }

    public static Context getContext() {
        if (context == null) {
            Log.m280e("Your application context is not set. You should call Leanplum.setApplicationContext(this) or LeanplumActivityHelper.enableLifecycleCallbacks(this) in your application's onCreate method, or have your application extend LeanplumApplication.");
        }
        return context;
    }

    public static void syncResources() {
        if (!Constants.isNoop()) {
            try {
                FileManager.enableResourceSyncing(null, null, false);
            } catch (Throwable th) {
                Util.handleException(th);
            }
            countAggregator.incrementCount("sync_resources");
        }
    }

    public static void syncResourcesAsync() {
        if (!Constants.isNoop()) {
            try {
                FileManager.enableResourceSyncing(null, null, true);
            } catch (Throwable th) {
                Util.handleException(th);
            }
            countAggregator.incrementCount("sync_resources");
        }
    }

    public static void syncResources(List<String> list, List<String> list2) {
        try {
            FileManager.enableResourceSyncing(list, list2, false);
        } catch (Throwable th) {
            Util.handleException(th);
        }
        countAggregator.incrementCount("sync_resource_paths");
    }

    public static void syncResourcesAsync(List<String> list, List<String> list2) {
        try {
            FileManager.enableResourceSyncing(list, list2, true);
        } catch (Throwable th) {
            Util.handleException(th);
        }
        countAggregator.incrementCount("sync_resource_paths");
    }

    public static boolean isResourceSyncingEnabled() {
        return FileManager.isResourceSyncingEnabled();
    }

    public static void start(Context context2) {
        start(context2, null, null, null, null);
    }

    public static void start(Context context2, StartCallback startCallback) {
        start(context2, null, null, startCallback, null);
    }

    public static void start(Context context2, Map<String, ?> map) {
        start(context2, null, map, null, null);
    }

    public static void start(Context context2, String str) {
        start(context2, str, null, null, null);
    }

    public static void start(Context context2, String str, StartCallback startCallback) {
        start(context2, str, null, startCallback, null);
    }

    public static void start(Context context2, String str, Map<String, ?> map) {
        start(context2, str, map, null, null);
    }

    public static synchronized void start(Context context2, String str, Map<String, ?> map, StartCallback startCallback) {
        synchronized (Leanplum.class) {
            start(context2, str, map, startCallback, null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized void start(android.content.Context r11, final java.lang.String r12, java.util.Map<java.lang.String, ?> r13, com.leanplum.callbacks.StartCallback r14, java.lang.Boolean r15) {
        /*
            java.lang.Class<com.leanplum.Leanplum> r0 = com.leanplum.Leanplum.class
            monitor-enter(r0)
            com.leanplum.internal.OsHandler.getInstance()     // Catch:{ Throwable -> 0x00ec }
            boolean r1 = r11 instanceof android.app.Activity     // Catch:{ Throwable -> 0x00ec }
            if (r1 == 0) goto L_0x000f
            r1 = r11
            android.app.Activity r1 = (android.app.Activity) r1     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.LeanplumActivityHelper.currentActivity = r1     // Catch:{ Throwable -> 0x00ec }
        L_0x000f:
            r1 = 0
            r2 = 1
            if (r15 != 0) goto L_0x0022
            android.app.Activity r15 = com.leanplum.LeanplumActivityHelper.currentActivity     // Catch:{ Throwable -> 0x00ec }
            if (r15 == 0) goto L_0x0020
            boolean r15 = com.leanplum.LeanplumActivityHelper.isActivityPaused()     // Catch:{ Throwable -> 0x00ec }
            if (r15 == 0) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            r15 = 0
            goto L_0x0026
        L_0x0020:
            r15 = 1
            goto L_0x0026
        L_0x0022:
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x00ec }
        L_0x0026:
            boolean r3 = com.leanplum.internal.Constants.isNoop()     // Catch:{ Throwable -> 0x00ec }
            if (r3 == 0) goto L_0x006d
            com.leanplum.internal.LeanplumInternal.setHasStarted(r2)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.LeanplumInternal.setStartSuccessful(r2)     // Catch:{ Throwable -> 0x00ec }
            triggerStartResponse(r2)     // Catch:{ Throwable -> 0x00ec }
            triggerVariablesChanged()     // Catch:{ Throwable -> 0x00ec }
            triggerVariablesChangedAndNoDownloadsPending()     // Catch:{ Throwable -> 0x00ec }
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Throwable -> 0x00ec }
            r4.<init>()     // Catch:{ Throwable -> 0x00ec }
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Throwable -> 0x00ec }
            r5.<init>()     // Catch:{ Throwable -> 0x00ec }
            java.util.List r6 = com.leanplum.internal.VarCache.getUpdateRuleDiffs()     // Catch:{ Throwable -> 0x00ec }
            java.util.List r7 = com.leanplum.internal.VarCache.getEventRuleDiffs()     // Catch:{ Throwable -> 0x00ec }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Throwable -> 0x00ec }
            r8.<init>()     // Catch:{ Throwable -> 0x00ec }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00ec }
            r9.<init>()     // Catch:{ Throwable -> 0x00ec }
            java.util.HashMap r10 = new java.util.HashMap     // Catch:{ Throwable -> 0x00ec }
            r10.<init>()     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.VarCache.applyVariableDiffs(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.LeanplumInbox r11 = com.leanplum.LeanplumInbox.getInstance()     // Catch:{ Throwable -> 0x00ec }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ Throwable -> 0x00ec }
            r12.<init>()     // Catch:{ Throwable -> 0x00ec }
            r11.update(r12, r1, r1)     // Catch:{ Throwable -> 0x00ec }
            monitor-exit(r0)
            return
        L_0x006d:
            if (r14 == 0) goto L_0x0072
            addStartResponseHandler(r14)     // Catch:{ Throwable -> 0x00ec }
        L_0x0072:
            if (r11 == 0) goto L_0x007b
            android.content.Context r14 = r11.getApplicationContext()     // Catch:{ Throwable -> 0x00ec }
            setApplicationContext(r14)     // Catch:{ Throwable -> 0x00ec }
        L_0x007b:
            boolean r14 = com.leanplum.internal.LeanplumInternal.hasCalledStart()     // Catch:{ Throwable -> 0x00ec }
            if (r14 == 0) goto L_0x009b
            if (r15 != 0) goto L_0x0090
            boolean r11 = com.leanplum.internal.LeanplumInternal.hasStartedInBackground()     // Catch:{ Throwable -> 0x00ec }
            if (r11 == 0) goto L_0x0090
            com.leanplum.internal.LeanplumInternal.setStartedInBackground(r1)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.LeanplumInternal.moveToForeground()     // Catch:{ Throwable -> 0x00ec }
            goto L_0x0099
        L_0x0090:
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r12 = "Already called start"
            r11[r1] = r12     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.Log.m281i(r11)     // Catch:{ Throwable -> 0x00ec }
        L_0x0099:
            monitor-exit(r0)
            return
        L_0x009b:
            initializedMessageTemplates = r2     // Catch:{ Throwable -> 0x00ec }
            android.content.Context r14 = getContext()     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.messagetemplates.MessageTemplates.register(r14)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.LeanplumInternal.setStartedInBackground(r15)     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r14 = "userAttributes"
            java.util.Map r13 = com.leanplum.internal.LeanplumInternal.validateAttributes(r13, r14, r2)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.LeanplumInternal.setCalledStart(r2)     // Catch:{ Throwable -> 0x00ec }
            if (r13 == 0) goto L_0x00b9
            java.util.Queue r14 = com.leanplum.internal.LeanplumInternal.getUserAttributeChanges()     // Catch:{ Throwable -> 0x00ec }
            r14.add(r13)     // Catch:{ Throwable -> 0x00ec }
        L_0x00b9:
            com.leanplum.internal.RequestOld.loadToken()     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.VarCache.setSilent(r2)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.VarCache.loadDiffs()     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.VarCache.setSilent(r1)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.LeanplumInbox r14 = com.leanplum.LeanplumInbox.getInstance()     // Catch:{ Throwable -> 0x00ec }
            r14.load()     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.Leanplum$1 r14 = new com.leanplum.Leanplum$1     // Catch:{ Throwable -> 0x00ec }
            r14.<init>()     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.VarCache.onUpdate(r14)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.Leanplum$2 r14 = new com.leanplum.Leanplum$2     // Catch:{ Throwable -> 0x00ec }
            r14.<init>()     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.RequestOld.onNoPendingDownloads(r14)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.Leanplum$3 r14 = new com.leanplum.Leanplum$3     // Catch:{ Throwable -> 0x00ec }
            r14.<init>(r12, r13, r15)     // Catch:{ Throwable -> 0x00ec }
            java.lang.Void[] r12 = new java.lang.Void[r1]     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.Util.executeAsyncTask(r2, r14, r12)     // Catch:{ Throwable -> 0x00ec }
            com.leanplum.internal.Util.initExceptionHandling(r11)     // Catch:{ Throwable -> 0x00ec }
            goto L_0x00f0
        L_0x00ea:
            r11 = move-exception
            goto L_0x00f9
        L_0x00ec:
            r11 = move-exception
            com.leanplum.internal.Util.handleException(r11)     // Catch:{ all -> 0x00ea }
        L_0x00f0:
            com.leanplum.internal.CountAggregator r11 = countAggregator     // Catch:{ all -> 0x00ea }
            java.lang.String r12 = "start_with_user_id"
            r11.incrementCount(r12)     // Catch:{ all -> 0x00ea }
            monitor-exit(r0)
            return
        L_0x00f9:
            monitor-exit(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.Leanplum.start(android.content.Context, java.lang.String, java.util.Map, com.leanplum.callbacks.StartCallback, java.lang.Boolean):void");
    }

    private static void checkAndStartNotificationsModules() {
        if (Util.hasPlayServices()) {
            try {
                Class.forName(LEANPLUM_PUSH_SERVICE).getDeclaredMethod("onStart", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable unused) {
            }
        } else {
            Log.m281i("No valid Google Play Services APK found.");
        }
    }

    /* access modifiers changed from: private */
    public static void startHelper(String str, Map<String, ?> map, boolean z) {
        String str2;
        LeanplumEventDataManager.init(context);
        checkAndStartNotificationsModules();
        Boolean bool = null;
        if (RequestOld.deviceId() == null) {
            if (userSpecifiedDeviceId || Constants.defaultDeviceId == null) {
                str2 = customDeviceId;
                if (str2 == null) {
                    DeviceIdInfo deviceId = Util.getDeviceId(deviceIdMode);
                    String str3 = deviceId.f646id;
                    bool = Boolean.valueOf(deviceId.limitAdTracking);
                    str2 = str3;
                }
            } else {
                str2 = Constants.defaultDeviceId;
            }
            RequestOld.setDeviceId(str2);
        }
        if (str == null) {
            str = RequestOld.userId();
            if (str == null) {
                str = RequestOld.deviceId();
            }
        }
        RequestOld.setUserId(str);
        String versionName = Util.getVersionName();
        if (versionName == null) {
            versionName = "";
        }
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(new Date().getTime()) / 1000;
        String string = SharedPreferencesUtil.getString(context, Defaults.LEANPLUM_PUSH, Defaults.PROPERTY_REGISTRATION_ID);
        HashMap hashMap = new HashMap();
        hashMap.put(Params.INCLUDE_DEFAULTS, Boolean.toString(false));
        if (z) {
            hashMap.put(Params.BACKGROUND, Boolean.toString(true));
        }
        hashMap.put(Params.VERSION_NAME, versionName);
        hashMap.put(Params.DEVICE_NAME, Util.getDeviceName());
        hashMap.put(Params.DEVICE_MODEL, Util.getDeviceModel());
        hashMap.put(Params.DEVICE_SYSTEM_NAME, Util.getSystemName());
        hashMap.put(Params.DEVICE_SYSTEM_VERSION, Util.getSystemVersion());
        if (!TextUtils.isEmpty(string)) {
            hashMap.put(Params.DEVICE_PUSH_TOKEN, string);
        }
        hashMap.put(Keys.TIMEZONE, timeZone.getID());
        hashMap.put(Keys.TIMEZONE_OFFSET_SECONDS, Integer.toString(offset));
        hashMap.put(Keys.LOCALE, Util.getLocale());
        String str4 = Values.DETECT;
        hashMap.put(Keys.COUNTRY, str4);
        hashMap.put(Keys.REGION, str4);
        hashMap.put(Keys.CITY, str4);
        hashMap.put("location", str4);
        if (Boolean.TRUE.equals(bool)) {
            hashMap.put(Params.LIMIT_TRACKING, bool.toString());
        }
        if (map != null) {
            hashMap.put(Params.USER_ATTRIBUTES, JsonConverter.toJson(map));
        }
        if (Constants.isDevelopmentModeEnabled) {
            hashMap.put(Params.DEV_MODE, Boolean.TRUE.toString());
        }
        hashMap.put("newsfeedMessages", LeanplumInbox.getInstance().messagesIds());
        hashMap.put(Params.INCLUDE_VARIANT_DEBUG_INFO, Boolean.valueOf(LeanplumInternal.getIsVariantDebugInfoEnabled()));
        Util.initializePreLeanplumInstall(hashMap);
        final RequestOld post = RequestOld.post(Methods.START, hashMap);
        post.onApiResponse(new ApiResponseCallback() {
            public void response(List<Map<String, Object>> list, JSONObject jSONObject, int i) {
                Leanplum.handleApiResponse(jSONObject, list, post, i);
            }
        });
        if (z) {
            post.sendEventually();
        } else {
            post.sendIfConnected();
        }
        LeanplumInternal.triggerStartIssued();
    }

    /* access modifiers changed from: private */
    public static void handleApiResponse(JSONObject jSONObject, List<Map<String, Object>> list, RequestOld requestOld, int i) {
        JSONObject jSONObject2;
        try {
            long j = (long) i;
            if (requestOld.getDataBaseIndex() >= j) {
                requestOld.setDataBaseIndex(requestOld.getDataBaseIndex() - j);
                return;
            }
            jSONObject2 = parseLastStartResponse(jSONObject, list);
            if (jSONObject2 != null && !LeanplumInternal.hasStarted()) {
                requestOld.onApiResponse(null);
                handleStartResponse(jSONObject2);
            }
        } catch (Throwable th) {
            Util.handleException(th);
            jSONObject2 = null;
        }
    }

    @VisibleForTesting
    public static JSONObject parseLastStartResponse(JSONObject jSONObject, List<Map<String, Object>> list) {
        int numResponses = RequestOld.numResponses(jSONObject);
        for (int size = list.size() - 1; size >= 0; size--) {
            Map map = (Map) list.get(size);
            if (Methods.START.equals(map.get("action"))) {
                String str = RequestOld.REQUEST_ID_KEY;
                if (map.containsKey(str)) {
                    for (int numResponses2 = RequestOld.numResponses(jSONObject) - 1; numResponses2 >= 0; numResponses2--) {
                        JSONObject responseAt = RequestOld.getResponseAt(jSONObject, numResponses2);
                        if (map.get(str).equals(responseAt.optString(str))) {
                            return responseAt;
                        }
                    }
                }
                if (size < numResponses) {
                    return RequestOld.getResponseAt(jSONObject, size);
                }
            }
        }
        return null;
    }

    private static void handleStartResponse(final JSONObject jSONObject) {
        Util.executeAsyncTask(false, new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Can't wrap try/catch for region: R(19:6|7|(1:9)|10|(1:12)|13|(1:15)|16|(1:18)|19|(4:21|22|23|24)|25|26|(1:28)|29|(1:31)|32|(16:34|(1:39)(1:38)|40|(1:44)|45|(1:47)|48|(1:51)|52|(1:54)|55|(1:57)|58|(1:60)|61|(1:63))|64) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x00d5 */
            /* JADX WARNING: Removed duplicated region for block: B:28:0x00f8 A[Catch:{ Throwable -> 0x01f1 }] */
            /* JADX WARNING: Removed duplicated region for block: B:31:0x0109 A[Catch:{ Throwable -> 0x01f1 }] */
            /* JADX WARNING: Removed duplicated region for block: B:34:0x012c A[Catch:{ Throwable -> 0x01f1 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void doInBackground(java.lang.Void... r14) {
                /*
                    r13 = this;
                    java.lang.String r14 = "isRegistered"
                    org.json.JSONObject r0 = r2
                    boolean r0 = com.leanplum.internal.RequestOld.isResponseSuccess(r0)
                    com.leanplum.internal.CountAggregator r1 = com.leanplum.Leanplum.countAggregator()
                    java.lang.String r2 = "on_start_response"
                    r1.incrementCount(r2)
                    r1 = 0
                    r2 = 0
                    r3 = 1
                    if (r0 != 0) goto L_0x002a
                    com.leanplum.internal.LeanplumInternal.setHasStarted(r3)     // Catch:{ Throwable -> 0x0024 }
                    com.leanplum.internal.LeanplumInternal.setStartSuccessful(r2)     // Catch:{ Throwable -> 0x0024 }
                    com.leanplum.internal.VarCache.loadDiffs()     // Catch:{ Throwable -> 0x0024 }
                    com.leanplum.Leanplum.triggerStartResponse(r2)     // Catch:{ Throwable -> 0x0024 }
                    goto L_0x01f5
                L_0x0024:
                    r14 = move-exception
                    com.leanplum.internal.Util.handleException(r14)
                    goto L_0x01f5
                L_0x002a:
                    com.leanplum.internal.LeanplumInternal.setHasStarted(r3)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.LeanplumInternal.setStartSuccessful(r3)     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "vars"
                    org.json.JSONObject r0 = r0.optJSONObject(r4)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 != 0) goto L_0x0043
                    java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "No variable values were received from the server. Please contact us to investigate."
                    r0[r2] = r4     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.Log.m280e(r0)     // Catch:{ Throwable -> 0x01f1 }
                L_0x0043:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "messages"
                    org.json.JSONObject r0 = r0.optJSONObject(r4)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 != 0) goto L_0x0056
                    java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "No messages received from the server."
                    r0[r2] = r4     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.Log.m279d(r0)     // Catch:{ Throwable -> 0x01f1 }
                L_0x0056:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "regions"
                    org.json.JSONObject r0 = r0.optJSONObject(r4)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 != 0) goto L_0x0069
                    java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "No regions received from the server."
                    r0[r2] = r4     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.Log.m279d(r0)     // Catch:{ Throwable -> 0x01f1 }
                L_0x0069:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "variants"
                    org.json.JSONArray r0 = r0.optJSONArray(r4)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 != 0) goto L_0x007c
                    java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "No variants received from the server."
                    r0[r2] = r4     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.Log.m279d(r0)     // Catch:{ Throwable -> 0x01f1 }
                L_0x007c:
                    android.content.Context r0 = com.leanplum.Leanplum.context     // Catch:{ Throwable -> 0x01f1 }
                    boolean r0 = com.leanplum.utils.BuildUtil.isNotificationChannelSupported(r0)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 == 0) goto L_0x00d5
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "notificationChannels"
                    org.json.JSONArray r0 = r0.optJSONArray(r4)     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r4 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r5 = "notificationChannelGroups"
                    org.json.JSONArray r4 = r4.optJSONArray(r5)     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r5 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r6 = "defaultNotificationChannel"
                    java.lang.String r5 = r5.optString(r6)     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r6 = "com.leanplum.LeanplumNotificationChannel"
                    java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.String r7 = "configureChannels"
                    r8 = 4
                    java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.Class<android.content.Context> r10 = android.content.Context.class
                    r9[r2] = r10     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.Class<org.json.JSONArray> r10 = org.json.JSONArray.class
                    r9[r3] = r10     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.Class<org.json.JSONArray> r10 = org.json.JSONArray.class
                    r11 = 2
                    r9[r11] = r10     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.Class<java.lang.String> r10 = java.lang.String.class
                    r12 = 3
                    r9[r12] = r10     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.reflect.Method r6 = r6.getDeclaredMethod(r7, r9)     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.Object r7 = new java.lang.Object     // Catch:{ Throwable -> 0x00d5 }
                    r7.<init>()     // Catch:{ Throwable -> 0x00d5 }
                    java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00d5 }
                    android.content.Context r9 = com.leanplum.Leanplum.context     // Catch:{ Throwable -> 0x00d5 }
                    r8[r2] = r9     // Catch:{ Throwable -> 0x00d5 }
                    r8[r3] = r4     // Catch:{ Throwable -> 0x00d5 }
                    r8[r11] = r0     // Catch:{ Throwable -> 0x00d5 }
                    r8[r12] = r5     // Catch:{ Throwable -> 0x00d5 }
                    r6.invoke(r7, r8)     // Catch:{ Throwable -> 0x00d5 }
                L_0x00d5:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "token"
                    java.lang.String r0 = r0.optString(r4, r1)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.RequestOld.setToken(r0)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.RequestOld.saveToken()     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.Leanplum.applyContentInResponse(r0, r3)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.VarCache.saveUserAttributes()     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.Leanplum.triggerStartResponse(r3)     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "syncNewsfeed"
                    boolean r0 = r0.optBoolean(r4, r2)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 == 0) goto L_0x00ff
                    com.leanplum.LeanplumInbox r0 = com.leanplum.LeanplumInbox.getInstance()     // Catch:{ Throwable -> 0x01f1 }
                    r0.downloadMessages()     // Catch:{ Throwable -> 0x01f1 }
                L_0x00ff:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "loggingEnabled"
                    boolean r0 = r0.optBoolean(r4, r2)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 == 0) goto L_0x010b
                    com.leanplum.internal.Constants.loggingEnabled = r3     // Catch:{ Throwable -> 0x01f1 }
                L_0x010b:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.util.Set r0 = com.leanplum.Leanplum.parseSdkCounters(r0)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.CountAggregator r4 = com.leanplum.Leanplum.countAggregator     // Catch:{ Throwable -> 0x01f1 }
                    r4.setEnabledCounters(r0)     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.util.Set r0 = com.leanplum.Leanplum.parseFeatureFlags(r0)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.FeatureFlagManager r4 = com.leanplum.internal.FeatureFlagManager.INSTANCE     // Catch:{ Throwable -> 0x01f1 }
                    r4.setEnabledFeatureFlags(r0)     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.Leanplum.parseVariantDebugInfo(r0)     // Catch:{ Throwable -> 0x01f1 }
                    boolean r0 = com.leanplum.internal.Constants.isDevelopmentModeEnabled     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 == 0) goto L_0x01ea
                    android.app.Activity r0 = com.leanplum.LeanplumActivityHelper.currentActivity     // Catch:{ Throwable -> 0x01f1 }
                    android.content.Context r4 = com.leanplum.Leanplum.context     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 == r4) goto L_0x013b
                    android.app.Activity r0 = com.leanplum.LeanplumActivityHelper.currentActivity     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 == 0) goto L_0x013b
                    android.app.Activity r0 = com.leanplum.LeanplumActivityHelper.currentActivity     // Catch:{ Throwable -> 0x01f1 }
                    goto L_0x013f
                L_0x013b:
                    android.content.Context r0 = com.leanplum.Leanplum.context     // Catch:{ Throwable -> 0x01f1 }
                L_0x013f:
                    org.json.JSONObject r4 = r2     // Catch:{ Throwable -> 0x01f1 }
                    boolean r4 = r4.optBoolean(r14)     // Catch:{ Throwable -> 0x01f1 }
                    if (r4 != 0) goto L_0x0164
                    com.leanplum.callbacks.RegisterDeviceCallback r4 = com.leanplum.Leanplum.registerDeviceHandler     // Catch:{ Throwable -> 0x01f1 }
                    if (r4 == 0) goto L_0x0164
                    com.leanplum.callbacks.RegisterDeviceCallback r4 = com.leanplum.Leanplum.registerDeviceHandler     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.Leanplum$5$1 r5 = new com.leanplum.Leanplum$5$1     // Catch:{ Throwable -> 0x01f1 }
                    r5.<init>()     // Catch:{ Throwable -> 0x01f1 }
                    r4.setResponseHandler(r5)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.OsHandler r4 = com.leanplum.internal.OsHandler.getInstance()     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.callbacks.RegisterDeviceCallback r5 = com.leanplum.Leanplum.registerDeviceHandler     // Catch:{ Throwable -> 0x01f1 }
                    r4.post(r5)     // Catch:{ Throwable -> 0x01f1 }
                L_0x0164:
                    org.json.JSONObject r4 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r5 = "isRegisteredFromOtherApp"
                    boolean r4 = r4.optBoolean(r5)     // Catch:{ Throwable -> 0x01f1 }
                    if (r4 == 0) goto L_0x017a
                    com.leanplum.internal.OsHandler r4 = com.leanplum.internal.OsHandler.getInstance()     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.Leanplum$5$2 r5 = new com.leanplum.Leanplum$5$2     // Catch:{ Throwable -> 0x01f1 }
                    r5.<init>(r0)     // Catch:{ Throwable -> 0x01f1 }
                    r4.post(r5)     // Catch:{ Throwable -> 0x01f1 }
                L_0x017a:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    boolean r14 = r0.optBoolean(r14)     // Catch:{ Throwable -> 0x01f1 }
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "latestVersion"
                    java.lang.String r0 = r0.optString(r4, r1)     // Catch:{ Throwable -> 0x01f1 }
                    if (r14 == 0) goto L_0x01a9
                    if (r0 == 0) goto L_0x01a9
                    java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01f1 }
                    r4.<init>()     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r5 = "An update to Leanplum Android SDK, "
                    r4.append(r5)     // Catch:{ Throwable -> 0x01f1 }
                    r4.append(r0)     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r0 = ", is available. Go to leanplum.com to download it."
                    r4.append(r0)     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r0 = r4.toString()     // Catch:{ Throwable -> 0x01f1 }
                    r3[r2] = r0     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.Log.m281i(r3)     // Catch:{ Throwable -> 0x01f1 }
                L_0x01a9:
                    org.json.JSONObject r0 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r2 = "varsFromCode"
                    org.json.JSONObject r0 = r0.optJSONObject(r2)     // Catch:{ Throwable -> 0x01f1 }
                    if (r0 != 0) goto L_0x01b8
                    org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01f1 }
                    r0.<init>()     // Catch:{ Throwable -> 0x01f1 }
                L_0x01b8:
                    org.json.JSONObject r2 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r3 = "actionDefinitions"
                    org.json.JSONObject r2 = r2.optJSONObject(r3)     // Catch:{ Throwable -> 0x01f1 }
                    if (r2 != 0) goto L_0x01c7
                    org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01f1 }
                    r2.<init>()     // Catch:{ Throwable -> 0x01f1 }
                L_0x01c7:
                    org.json.JSONObject r3 = r2     // Catch:{ Throwable -> 0x01f1 }
                    java.lang.String r4 = "fileAttributes"
                    org.json.JSONObject r3 = r3.optJSONObject(r4)     // Catch:{ Throwable -> 0x01f1 }
                    if (r3 != 0) goto L_0x01d6
                    org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01f1 }
                    r3.<init>()     // Catch:{ Throwable -> 0x01f1 }
                L_0x01d6:
                    java.util.Map r0 = com.leanplum.internal.JsonConverter.mapFromJson(r0)     // Catch:{ Throwable -> 0x01f1 }
                    java.util.Map r3 = com.leanplum.internal.JsonConverter.mapFromJson(r3)     // Catch:{ Throwable -> 0x01f1 }
                    java.util.Map r2 = com.leanplum.internal.JsonConverter.mapFromJson(r2)     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.internal.VarCache.setDevModeValuesFromServer(r0, r3, r2)     // Catch:{ Throwable -> 0x01f1 }
                    if (r14 == 0) goto L_0x01ea
                    com.leanplum.internal.LeanplumInternal.onHasStartedAndRegisteredAsDeveloper()     // Catch:{ Throwable -> 0x01f1 }
                L_0x01ea:
                    com.leanplum.internal.LeanplumInternal.moveToForeground()     // Catch:{ Throwable -> 0x01f1 }
                    com.leanplum.Leanplum.startHeartbeat()     // Catch:{ Throwable -> 0x01f1 }
                    goto L_0x01f5
                L_0x01f1:
                    r14 = move-exception
                    com.leanplum.internal.Util.handleException(r14)
                L_0x01f5:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.leanplum.Leanplum.C23295.doInBackground(java.lang.Void[]):java.lang.Void");
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public static void applyContentInResponse(JSONObject jSONObject, boolean z) {
        Map mapFromJsonOrDefault = JsonConverter.mapFromJsonOrDefault(jSONObject.optJSONObject("vars"));
        Map mapFromJsonOrDefault2 = JsonConverter.mapFromJsonOrDefault(jSONObject.optJSONObject(Keys.MESSAGES));
        List listFromJsonOrDefault = JsonConverter.listFromJsonOrDefault(jSONObject.optJSONArray(Keys.UPDATE_RULES));
        List listFromJsonOrDefault2 = JsonConverter.listFromJsonOrDefault(jSONObject.optJSONArray(Keys.EVENT_RULES));
        Map mapFromJsonOrDefault3 = JsonConverter.mapFromJsonOrDefault(jSONObject.optJSONObject("regions"));
        List listFromJsonOrDefault3 = JsonConverter.listFromJsonOrDefault(jSONObject.optJSONArray(Keys.VARIANTS));
        Map mapFromJsonOrDefault4 = JsonConverter.mapFromJsonOrDefault(jSONObject.optJSONObject(Keys.VARIANT_DEBUG_INFO));
        if (z || !mapFromJsonOrDefault.equals(VarCache.getDiffs()) || !mapFromJsonOrDefault2.equals(VarCache.getMessageDiffs()) || !listFromJsonOrDefault.equals(VarCache.getUpdateRuleDiffs()) || !listFromJsonOrDefault2.equals(VarCache.getEventRuleDiffs()) || !listFromJsonOrDefault3.equals(VarCache.variants()) || !mapFromJsonOrDefault3.equals(VarCache.regions())) {
            VarCache.applyVariableDiffs(mapFromJsonOrDefault, mapFromJsonOrDefault2, listFromJsonOrDefault, listFromJsonOrDefault2, mapFromJsonOrDefault3, listFromJsonOrDefault3, mapFromJsonOrDefault4);
        }
    }

    static void setClient(String str, String str2, String str3) {
        Constants.CLIENT = str;
        Constants.LEANPLUM_VERSION = str2;
        Constants.defaultDeviceId = str3;
    }

    static void pause() {
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call pause before calling start");
                return;
            }
            if (LeanplumInternal.issuedStart()) {
                pauseInternal();
            } else {
                LeanplumInternal.addStartIssuedHandler(new Runnable() {
                    public void run() {
                        try {
                            Leanplum.pauseInternal();
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void pauseInternal() {
        RequestOld.post(Methods.PAUSE_SESSION, null).sendIfConnected();
        pauseHeartbeat();
        LeanplumInternal.setIsPaused(true);
    }

    static void resume() {
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call resume before calling start");
                return;
            }
            if (LeanplumInternal.issuedStart()) {
                resumeInternal();
            } else {
                LeanplumInternal.addStartIssuedHandler(new Runnable() {
                    public void run() {
                        try {
                            Leanplum.resumeInternal();
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void resumeInternal() {
        RequestOld post = RequestOld.post(Methods.RESUME_SESSION, null);
        if (LeanplumInternal.hasStartedInBackground()) {
            LeanplumInternal.setStartedInBackground(false);
            post.sendIfConnected();
        } else {
            post.sendIfDelayed();
            LeanplumInternal.maybePerformActions("resume", (String) null, 3, (String) null, (ContextualValues) null);
        }
        resumeHeartbeat();
        LeanplumInternal.setIsPaused(false);
    }

    /* access modifiers changed from: private */
    public static void startHeartbeat() {
        synchronized (heartbeatLock) {
            if (heartbeatExecutor == null) {
                createHeartbeatExecutor();
            }
        }
    }

    private static void pauseHeartbeat() {
        synchronized (heartbeatLock) {
            if (heartbeatExecutor != null) {
                heartbeatExecutor.shutdown();
                heartbeatExecutor = null;
            }
        }
    }

    private static void resumeHeartbeat() {
        startHeartbeat();
    }

    private static void createHeartbeatExecutor() {
        heartbeatExecutor = Executors.newSingleThreadScheduledExecutor();
        heartbeatExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    RequestOld.post(Methods.HEARTBEAT, null).sendIfDelayed();
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }
        }, 15, 15, TimeUnit.MINUTES);
    }

    static void stop() {
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call stop before calling start");
                return;
            }
            if (LeanplumInternal.issuedStart()) {
                stopInternal();
            } else {
                LeanplumInternal.addStartIssuedHandler(new Runnable() {
                    public void run() {
                        try {
                            Leanplum.stopInternal();
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void stopInternal() {
        RequestOld.post(Methods.STOP, null).sendIfConnected();
    }

    public static boolean hasStarted() {
        return LeanplumInternal.hasStarted();
    }

    public static String getUserId() {
        if (hasStarted()) {
            return RequestOld.userId();
        }
        Log.m280e("Leanplum.start() must be called before calling getUserId()");
        return null;
    }

    public static LeanplumInbox getInbox() {
        return LeanplumInbox.getInstance();
    }

    public static boolean hasStartedAndRegisteredAsDeveloper() {
        return LeanplumInternal.hasStartedAndRegisteredAsDeveloper();
    }

    public static void addStartResponseHandler(StartCallback startCallback) {
        if (startCallback == null) {
            Log.m280e("addStartResponseHandler - Invalid handler parameter provided.");
            return;
        }
        if (LeanplumInternal.hasStarted()) {
            if (LeanplumInternal.isStartSuccessful()) {
                startCallback.setSuccess(true);
            }
            startCallback.run();
        } else {
            synchronized (startHandlers) {
                if (startHandlers.indexOf(startCallback) == -1) {
                    startHandlers.add(startCallback);
                }
            }
        }
    }

    public static void removeStartResponseHandler(StartCallback startCallback) {
        if (startCallback == null) {
            Log.m280e("removeStartResponseHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (startHandlers) {
            startHandlers.remove(startCallback);
        }
    }

    /* access modifiers changed from: private */
    public static void triggerStartResponse(boolean z) {
        synchronized (startHandlers) {
            Iterator it = startHandlers.iterator();
            while (it.hasNext()) {
                StartCallback startCallback = (StartCallback) it.next();
                startCallback.setSuccess(z);
                OsHandler.getInstance().post(startCallback);
            }
            startHandlers.clear();
        }
    }

    public static void addVariablesChangedHandler(VariablesChangedCallback variablesChangedCallback) {
        if (variablesChangedCallback == null) {
            Log.m280e("addVariablesChangedHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (variablesChangedHandlers) {
            variablesChangedHandlers.add(variablesChangedCallback);
        }
        if (VarCache.hasReceivedDiffs()) {
            variablesChangedCallback.variablesChanged();
        }
    }

    public static void removeVariablesChangedHandler(VariablesChangedCallback variablesChangedCallback) {
        if (variablesChangedCallback == null) {
            Log.m280e("removeVariablesChangedHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (variablesChangedHandlers) {
            variablesChangedHandlers.remove(variablesChangedCallback);
        }
    }

    /* access modifiers changed from: private */
    public static void triggerVariablesChanged() {
        synchronized (variablesChangedHandlers) {
            Iterator it = variablesChangedHandlers.iterator();
            while (it.hasNext()) {
                OsHandler.getInstance().post((VariablesChangedCallback) it.next());
            }
        }
    }

    public static void addVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        if (variablesChangedCallback == null) {
            Log.m280e("addVariablesChangedAndNoDownloadsPendingHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (noDownloadsHandlers) {
            noDownloadsHandlers.add(variablesChangedCallback);
        }
        if (VarCache.hasReceivedDiffs() && RequestOld.numPendingDownloads() == 0) {
            variablesChangedCallback.variablesChanged();
        }
    }

    public static void removeVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        if (variablesChangedCallback == null) {
            Log.m280e("removeVariablesChangedAndNoDownloadsPendingHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (noDownloadsHandlers) {
            noDownloadsHandlers.remove(variablesChangedCallback);
        }
    }

    public static void addMessageDisplayedHandler(MessageDisplayedCallback messageDisplayedCallback) {
        if (messageDisplayedCallback == null) {
            Log.m280e("addMessageDisplayedHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (messageDisplayedHandlers) {
            messageDisplayedHandlers.add(messageDisplayedCallback);
        }
    }

    public static void removeMessageDisplayedHandler(MessageDisplayedCallback messageDisplayedCallback) {
        if (messageDisplayedCallback == null) {
            Log.m280e("removeMessageDisplayedHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (messageDisplayedHandlers) {
            messageDisplayedHandlers.remove(messageDisplayedCallback);
        }
    }

    public static void triggerMessageDisplayed(ActionContext actionContext) {
        ActionManager.getInstance().recordMessageImpression(actionContext.getMessageId());
        synchronized (messageDisplayedHandlers) {
            Iterator it = messageDisplayedHandlers.iterator();
            while (it.hasNext()) {
                MessageDisplayedCallback messageDisplayedCallback = (MessageDisplayedCallback) it.next();
                messageDisplayedCallback.setMessageArchiveData(messageArchiveDataFromContext(actionContext));
                OsHandler.getInstance().post(messageDisplayedCallback);
            }
        }
    }

    private static MessageArchiveData messageArchiveDataFromContext(ActionContext actionContext) {
        String str;
        String messageId = actionContext.getMessageId();
        try {
            str = messageBodyFromContext(actionContext);
        } catch (Throwable th) {
            Util.handleException(th);
            str = "";
        }
        return new MessageArchiveData(messageId, str, getUserId(), new Date());
    }

    @VisibleForTesting
    public static String messageBodyFromContext(ActionContext actionContext) {
        Object obj = actionContext.getArgs().get("Message");
        if (obj instanceof String) {
            return (String) obj;
        }
        HashMap hashMap = (HashMap) obj;
        String str = "Text";
        if (hashMap.get(str) != null && (hashMap.get(str) instanceof String)) {
            return (String) hashMap.get(str);
        }
        String str2 = "Text value";
        if (hashMap.get(str2) == null || !(hashMap.get(str2) instanceof String)) {
            return null;
        }
        return (String) hashMap.get(str2);
    }

    public static void addOnceVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        if (variablesChangedCallback == null) {
            Log.m280e("addOnceVariablesChangedAndNoDownloadsPendingHandler - Invalid handler parameter provided.");
            return;
        }
        if (!VarCache.hasReceivedDiffs() || RequestOld.numPendingDownloads() != 0) {
            synchronized (onceNoDownloadsHandlers) {
                onceNoDownloadsHandlers.add(variablesChangedCallback);
            }
        } else {
            variablesChangedCallback.variablesChanged();
        }
    }

    public static void removeOnceVariablesChangedAndNoDownloadsPendingHandler(VariablesChangedCallback variablesChangedCallback) {
        if (variablesChangedCallback == null) {
            Log.m280e("removeOnceVariablesChangedAndNoDownloadsPendingHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (onceNoDownloadsHandlers) {
            onceNoDownloadsHandlers.remove(variablesChangedCallback);
        }
    }

    static void triggerVariablesChangedAndNoDownloadsPending() {
        synchronized (noDownloadsHandlers) {
            Iterator it = noDownloadsHandlers.iterator();
            while (it.hasNext()) {
                OsHandler.getInstance().post((VariablesChangedCallback) it.next());
            }
        }
        synchronized (onceNoDownloadsHandlers) {
            Iterator it2 = onceNoDownloadsHandlers.iterator();
            while (it2.hasNext()) {
                OsHandler.getInstance().post((VariablesChangedCallback) it2.next());
            }
            onceNoDownloadsHandlers.clear();
        }
    }

    public static void defineAction(String str, int i, ActionArgs actionArgs) {
        defineAction(str, i, actionArgs, null, null);
    }

    public static void defineAction(String str, int i, ActionArgs actionArgs, ActionCallback actionCallback) {
        defineAction(str, i, actionArgs, null, actionCallback);
    }

    private static void defineAction(String str, int i, ActionArgs actionArgs, Map<String, Object> map, ActionCallback actionCallback) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("defineAction - Empty name parameter provided.");
        } else if (actionArgs == null) {
            Log.m280e("defineAction - Invalid args parameter provided.");
        } else {
            try {
                Context context2 = getContext();
                if (!initializedMessageTemplates) {
                    initializedMessageTemplates = true;
                    MessageTemplates.register(context2);
                }
                if (map == null) {
                    map = new HashMap<>();
                }
                LeanplumInternal.getActionHandlers().remove(str);
                VarCache.registerActionDefinition(str, i, actionArgs.getValue(), map);
                if (actionCallback != null) {
                    onAction(str, actionCallback);
                }
            } catch (Throwable th) {
                Util.handleException(th);
            }
            countAggregator().incrementCount("define_action");
        }
    }

    public static void onAction(String str, ActionCallback actionCallback) {
        if (str == null) {
            Log.m280e("onAction - Invalid actionName parameter provided.");
        } else if (actionCallback == null) {
            Log.m280e("onAction - Invalid handler parameter provided.");
        } else {
            List list = (List) LeanplumInternal.getActionHandlers().get(str);
            if (list == null) {
                list = new ArrayList();
                LeanplumInternal.getActionHandlers().put(str, list);
            }
            list.add(actionCallback);
        }
    }

    public static void setUserAttributes(final String str, Map<String, ?> map) {
        String str2 = Params.USER_ATTRIBUTES;
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call setUserAttributes before calling start");
                return;
            }
            try {
                final HashMap hashMap = new HashMap();
                if (str != null) {
                    hashMap.put(Params.NEW_USER_ID, str);
                }
                if (map != null) {
                    Map validateAttributes = LeanplumInternal.validateAttributes(map, str2, true);
                    hashMap.put(str2, JsonConverter.toJson(validateAttributes));
                    LeanplumInternal.getUserAttributeChanges().add(validateAttributes);
                }
                if (LeanplumInternal.issuedStart()) {
                    setUserAttributesInternal(str, hashMap);
                } else {
                    LeanplumInternal.addStartIssuedHandler(new Runnable() {
                        public void run() {
                            try {
                                Leanplum.setUserAttributesInternal(str, hashMap);
                            } catch (Throwable th) {
                                Util.handleException(th);
                            }
                        }
                    });
                }
            } catch (Throwable th) {
                Util.handleException(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setUserAttributesInternal(String str, HashMap<String, Object> hashMap) {
        RequestOld.post(Methods.SET_USER_ATTRIBUTES, hashMap).send();
        if (str != null && str.length() > 0) {
            RequestOld.setUserId(str);
            if (LeanplumInternal.hasStarted()) {
                VarCache.saveDiffs();
            }
        }
        LeanplumInternal.recordAttributeChanges();
    }

    public static void setUserId(String str) {
        if (str == null) {
            Log.m280e("setUserId - Invalid userId parameter provided.");
            return;
        }
        setUserAttributes(str, null);
    }

    public static void setUserAttributes(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            Log.m280e("setUserAttributes - Invalid userAttributes parameter provided (null or empty).");
            return;
        }
        setUserAttributes(null, map);
    }

    static void setRegistrationId(final String str) {
        if (!Constants.isNoop()) {
            pushStartCallback = new Runnable() {
                public void run() {
                    if (!Constants.isNoop()) {
                        try {
                            HashMap hashMap = new HashMap();
                            hashMap.put(Params.DEVICE_PUSH_TOKEN, str);
                            RequestOld.post(Methods.SET_DEVICE_ATTRIBUTES, hashMap).send();
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                    }
                }
            };
            LeanplumInternal.addStartIssuedHandler(pushStartCallback);
        }
    }

    public static void setTrafficSourceInfo(Map<String, String> map) {
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call setTrafficSourceInfo before calling start");
            } else if (map == null || map.isEmpty()) {
                Log.m280e("setTrafficSourceInfo - Invalid info parameter provided (null or empty).");
            } else {
                try {
                    final HashMap hashMap = new HashMap();
                    hashMap.put(Params.TRAFFIC_SOURCE, JsonConverter.toJson(LeanplumInternal.validateAttributes(map, Params.INFO, false)));
                    if (LeanplumInternal.issuedStart()) {
                        setTrafficSourceInfoInternal(hashMap);
                    } else {
                        LeanplumInternal.addStartIssuedHandler(new Runnable() {
                            public void run() {
                                try {
                                    Leanplum.setTrafficSourceInfoInternal(hashMap);
                                } catch (Throwable th) {
                                    Util.handleException(th);
                                }
                            }
                        });
                    }
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setTrafficSourceInfoInternal(HashMap<String, Object> hashMap) {
        RequestOld.post(Methods.SET_TRAFFIC_SOURCE_INFO, hashMap).send();
    }

    public static void track(String str, double d, String str2, Map<String, ?> map) {
        LeanplumInternal.track(str, d, str2, map, null);
        countAggregator.incrementCount(Methods.TRACK);
    }

    public static void trackPurchase(String str, double d, String str2, Map<String, ?> map) {
        try {
            if (TextUtils.isEmpty(str)) {
                Log.m284w("trackPurchase - Empty event parameter provided.");
            }
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put(Params.IAP_CURRENCY_CODE, str2);
            }
            LeanplumInternal.track(str, d, null, map, hashMap);
        } catch (Throwable th) {
            Log.m280e("trackPurchase - Failed to track purchase event.");
            Util.handleException(th);
        }
    }

    public static void trackGooglePlayPurchase(String str, long j, String str2, String str3, String str4) {
        trackGooglePlayPurchase(PURCHASE_EVENT_NAME, str, j, str2, str3, str4, null);
    }

    public static void trackGooglePlayPurchase(String str, long j, String str2, String str3, String str4, Map<String, ?> map) {
        trackGooglePlayPurchase(PURCHASE_EVENT_NAME, str, j, str2, str3, str4, map);
    }

    public static void trackGooglePlayPurchase(String str, String str2, long j, String str3, String str4, String str5, Map<String, ?> map) {
        HashMap hashMap;
        if (TextUtils.isEmpty(str)) {
            Log.m284w("trackGooglePlayPurchase - Empty eventName parameter provided.");
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Params.GOOGLE_PLAY_PURCHASE_DATA, str4);
        hashMap2.put(Params.GOOGLE_PLAY_PURCHASE_DATA_SIGNATURE, str5);
        hashMap2.put(Params.IAP_CURRENCY_CODE, str3);
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put(Params.IAP_ITEM, str2);
        LeanplumInternal.track(str, ((double) j) / 1000000.0d, null, hashMap, hashMap2);
    }

    public static void track(String str) {
        track(str, 0.0d, "", null);
    }

    public static void track(String str, double d) {
        track(str, d, "", null);
    }

    public static void track(String str, String str2) {
        track(str, 0.0d, str2, null);
    }

    public static void track(String str, Map<String, ?> map) {
        track(str, 0.0d, "", map);
    }

    public static void track(String str, double d, Map<String, ?> map) {
        track(str, d, "", map);
    }

    public static void track(String str, double d, String str2) {
        track(str, d, str2, null);
    }

    public static void trackGeofence(GeofenceEventType geofenceEventType, String str) {
        String str2 = "track_geofence";
        if (featureFlagManager().isFeatureFlagEnabled(str2).booleanValue()) {
            LeanplumInternal.trackGeofence(geofenceEventType, 0.0d, str, null, null);
            countAggregator().incrementCount(str2);
            return;
        }
        countAggregator().incrementCount("track_geofence_disabled");
    }

    public static void advanceTo(final String str, String str2, Map<String, ?> map) {
        final Map map2;
        String str3 = Params.PARAMS;
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call advanceTo before calling start");
                return;
            }
            try {
                final HashMap hashMap = new HashMap();
                hashMap.put(Params.INFO, str2);
                hashMap.put(Params.STATE, str);
                if (map != null) {
                    map2 = LeanplumInternal.validateAttributes(map, str3, false);
                    hashMap.put(str3, JsonConverter.toJson(map2));
                } else {
                    map2 = null;
                }
                if (LeanplumInternal.issuedStart()) {
                    advanceToInternal(str, map2, hashMap);
                } else {
                    LeanplumInternal.addStartIssuedHandler(new Runnable() {
                        public void run() {
                            try {
                                Leanplum.advanceToInternal(str, map2, hashMap);
                            } catch (Throwable th) {
                                Util.handleException(th);
                            }
                        }
                    });
                }
            } catch (Throwable th) {
                Util.handleException(th);
            }
            countAggregator.incrementCount("advance_to");
        }
    }

    /* access modifiers changed from: private */
    public static void advanceToInternal(String str, Map<String, ?> map, Map<String, Object> map2) {
        RequestOld.post(Methods.ADVANCE, map2).send();
        ContextualValues contextualValues = new ContextualValues();
        contextualValues.parameters = map;
        LeanplumInternal.maybePerformActions(Params.STATE, str, 3, (String) null, contextualValues);
    }

    public static void advanceTo(String str) {
        advanceTo(str, "", null);
    }

    public static void advanceTo(String str, String str2) {
        advanceTo(str, str2, null);
    }

    public static void advanceTo(String str, Map<String, ?> map) {
        advanceTo(str, "", map);
    }

    public static void pauseState() {
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call pauseState before calling start");
                return;
            }
            try {
                if (LeanplumInternal.issuedStart()) {
                    pauseStateInternal();
                } else {
                    LeanplumInternal.addStartIssuedHandler(new Runnable() {
                        public void run() {
                            try {
                                Leanplum.pauseStateInternal();
                            } catch (Throwable th) {
                                Util.handleException(th);
                            }
                        }
                    });
                }
            } catch (Throwable th) {
                Util.handleException(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void pauseStateInternal() {
        RequestOld.post(Methods.PAUSE_STATE, new HashMap()).send();
    }

    public static void resumeState() {
        if (!Constants.isNoop()) {
            if (!LeanplumInternal.hasCalledStart()) {
                Log.m280e("You cannot call resumeState before calling start");
                return;
            }
            try {
                if (LeanplumInternal.issuedStart()) {
                    resumeStateInternal();
                } else {
                    LeanplumInternal.addStartIssuedHandler(new Runnable() {
                        public void run() {
                            try {
                                Leanplum.resumeStateInternal();
                            } catch (Throwable th) {
                                Util.handleException(th);
                            }
                        }
                    });
                }
            } catch (Throwable th) {
                Util.handleException(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void resumeStateInternal() {
        RequestOld.post(Methods.RESUME_STATE, new HashMap()).send();
    }

    public static void forceContentUpdate() {
        forceContentUpdate(null);
    }

    public static void forceContentUpdate(final VariablesChangedCallback variablesChangedCallback) {
        if (Constants.isNoop()) {
            if (variablesChangedCallback != null) {
                OsHandler.getInstance().post(variablesChangedCallback);
            }
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(Params.INCLUDE_DEFAULTS, Boolean.toString(false));
            hashMap.put("newsfeedMessages", LeanplumInbox.getInstance().messagesIds());
            hashMap.put(Params.INCLUDE_VARIANT_DEBUG_INFO, Boolean.valueOf(LeanplumInternal.getIsVariantDebugInfoEnabled()));
            RequestOld post = RequestOld.post(Methods.GET_VARS, hashMap);
            post.onResponse(new ResponseCallback() {
                public void response(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        try {
                            Log.m280e("No response received from the server. Please contact us to investigate.");
                        } catch (Throwable th) {
                            Util.handleException(th);
                            return;
                        }
                    } else {
                        Leanplum.applyContentInResponse(jSONObject, false);
                        if (jSONObject.optBoolean(Keys.SYNC_INBOX, false)) {
                            LeanplumInbox.getInstance().downloadMessages();
                        } else {
                            LeanplumInbox.getInstance().triggerInboxSyncedWithStatus(true);
                        }
                        if (jSONObject.optBoolean(Keys.LOGGING_ENABLED, false)) {
                            Constants.loggingEnabled = true;
                        }
                        Leanplum.parseVariantDebugInfo(jSONObject);
                    }
                    if (variablesChangedCallback != null) {
                        OsHandler.getInstance().post(variablesChangedCallback);
                    }
                }
            });
            post.onError(new ErrorCallback() {
                public void error(Exception exc) {
                    if (variablesChangedCallback != null) {
                        OsHandler.getInstance().post(variablesChangedCallback);
                    }
                    LeanplumInbox.getInstance().triggerInboxSyncedWithStatus(false);
                }
            });
            post.sendIfConnected();
        } catch (Throwable th) {
            Util.handleException(th);
        }
        countAggregator.incrementCount("force_content_update");
    }

    public static void enableTestMode() {
        Constants.isTestMode = true;
    }

    public static boolean isTestModeEnabled() {
        return Constants.isTestMode;
    }

    public static void setIsTestModeEnabled(boolean z) {
        Constants.isTestMode = z;
    }

    public static String pathForResource(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            Log.m280e("pathForResource - Empty filename parameter provided.");
            return null;
        }
        Var defineFile = Var.defineFile(str, str);
        if (defineFile != null) {
            str2 = defineFile.fileValue();
        }
        return str2;
    }

    public static Object objectForKeyPath(Object... objArr) {
        return objectForKeyPathComponents(objArr);
    }

    public static Object objectForKeyPathComponents(Object[] objArr) {
        try {
            return VarCache.getMergedValueFromComponentArray(objArr);
        } catch (Throwable th) {
            Util.handleException(th);
            return null;
        }
    }

    public static List<Map<String, Object>> variants() {
        List<Map<String, Object>> variants = VarCache.variants();
        return variants == null ? new ArrayList() : variants;
    }

    public static Map<String, Object> messageMetadata() {
        Map<String, Object> messages = VarCache.messages();
        return messages == null ? new HashMap() : messages;
    }

    public static Map<String, Object> getVariantDebugInfo() {
        return VarCache.getVariantDebugInfo();
    }

    public static void setDeviceLocation(Location location) {
        setDeviceLocation(location, LeanplumLocationAccuracyType.CELL);
    }

    public static void setDeviceLocation(Location location, LeanplumLocationAccuracyType leanplumLocationAccuracyType) {
        if (locationCollectionEnabled) {
            Log.m284w("Leanplum is automatically collecting device location, so there is no need to call setDeviceLocation. If you prefer to always set location manually, then call disableLocationCollection.");
        }
        LeanplumInternal.setUserLocationAttribute(location, leanplumLocationAccuracyType, new locationAttributeRequestsCallback() {
            public void response(boolean z) {
                if (z) {
                    Log.m279d("setUserAttributes with location is successfully called");
                }
            }
        });
    }

    public static void disableLocationCollection() {
        locationCollectionEnabled = false;
    }

    public static boolean isLocationCollectionEnabled() {
        return locationCollectionEnabled;
    }

    /* access modifiers changed from: private */
    public static void parseVariantDebugInfo(JSONObject jSONObject) {
        Map mapFromJsonOrDefault = JsonConverter.mapFromJsonOrDefault(jSONObject.optJSONObject(Keys.VARIANT_DEBUG_INFO));
        if (mapFromJsonOrDefault.size() > 0) {
            VarCache.setVariantDebugInfo(mapFromJsonOrDefault);
        }
    }

    public static void clearUserContent() {
        VarCache.clearUserContent();
        countAggregator.incrementCount("clear_user_content");
    }

    @VisibleForTesting
    public static Set<String> parseSdkCounters(JSONObject jSONObject) {
        return toSet(jSONObject.optJSONArray(Keys.ENABLED_COUNTERS));
    }

    @VisibleForTesting
    public static Set<String> parseFeatureFlags(JSONObject jSONObject) {
        return toSet(jSONObject.optJSONArray(Keys.ENABLED_FEATURE_FLAGS));
    }

    private static Set<String> toSet(JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.optString(i));
            }
        }
        return hashSet;
    }

    public static CountAggregator countAggregator() {
        return countAggregator;
    }

    public static FeatureFlagManager featureFlagManager() {
        return featureFlagManager;
    }
}
