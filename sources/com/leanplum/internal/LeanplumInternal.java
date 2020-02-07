package com.leanplum.internal;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import com.leanplum.ActionContext;
import com.leanplum.ActionContext.ContextualValues;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumException;
import com.leanplum.LeanplumLocationAccuracyType;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.internal.ActionManager.MessageMatchResult;
import com.leanplum.internal.Constants.Keys;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.RequestOld.ErrorCallback;
import com.leanplum.internal.RequestOld.ResponseCallback;
import com.leanplum.models.GeofenceEventType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class LeanplumInternal {
    private static final String ACTION = "action";
    private static final Map<String, List<ActionCallback>> actionHandlers = new HashMap();
    private static boolean calledStart;
    private static boolean hasStarted;
    private static boolean hasStartedAndRegisteredAsDeveloper;
    private static boolean inForeground;
    private static final Object inForegroundLock = new Object();
    private static boolean isPaused;
    private static boolean isScreenTrackingEnabled = false;
    private static boolean isVariantDebugInfoEnabled = false;
    private static boolean issuedStart;
    private static final ArrayList<Runnable> startIssuedHandlers = new ArrayList<>();
    private static boolean startSuccessful;
    private static boolean startedInBackground;
    private static final Queue<Map<String, ?>> userAttributeChanges = new ConcurrentLinkedQueue();

    public interface locationAttributeRequestsCallback {
        void response(boolean z);
    }

    /* access modifiers changed from: private */
    public static void onHasStartedAndRegisteredAsDeveloperAndFinishedSyncing() {
        if (!hasStartedAndRegisteredAsDeveloper) {
            hasStartedAndRegisteredAsDeveloper = true;
        }
    }

    public static void onHasStartedAndRegisteredAsDeveloper() {
        synchronized (FileManager.initializingLock) {
            if (FileManager.initializing()) {
                FileManager.setResourceSyncFinishBlock(new ResourceUpdateCallback() {
                    public void onResourceSyncFinish() {
                        try {
                            LeanplumInternal.onHasStartedAndRegisteredAsDeveloperAndFinishedSyncing();
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                    }
                });
            } else {
                onHasStartedAndRegisteredAsDeveloperAndFinishedSyncing();
            }
        }
    }

    public static void triggerAction(ActionContext actionContext) {
        triggerAction(actionContext, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r0 = new java.util.concurrent.atomic.AtomicBoolean(false);
        r1 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r1.hasNext() == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        r2 = (com.leanplum.callbacks.ActionCallback) r1.next();
        com.leanplum.internal.OsHandler.getInstance().post(new com.leanplum.internal.LeanplumInternal.C23642());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void triggerAction(final com.leanplum.ActionContext r5, final com.leanplum.callbacks.VariablesChangedCallback r6) {
        /*
            java.util.Map<java.lang.String, java.util.List<com.leanplum.callbacks.ActionCallback>> r0 = actionHandlers
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.util.List<com.leanplum.callbacks.ActionCallback>> r1 = actionHandlers     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = r5.actionName()     // Catch:{ all -> 0x0042 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0042 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x0018
            if (r6 == 0) goto L_0x0016
            r6.variablesChanged()     // Catch:{ all -> 0x0042 }
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            return
        L_0x0018:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0042 }
            r2.<init>(r1)     // Catch:{ all -> 0x0042 }
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            java.util.concurrent.atomic.AtomicBoolean r0 = new java.util.concurrent.atomic.AtomicBoolean
            r1 = 0
            r0.<init>(r1)
            java.util.Iterator r1 = r2.iterator()
        L_0x0028:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0041
            java.lang.Object r2 = r1.next()
            com.leanplum.callbacks.ActionCallback r2 = (com.leanplum.callbacks.ActionCallback) r2
            com.leanplum.internal.OsHandler r3 = com.leanplum.internal.OsHandler.getInstance()
            com.leanplum.internal.LeanplumInternal$2 r4 = new com.leanplum.internal.LeanplumInternal$2
            r4.<init>(r2, r5, r6, r0)
            r3.post(r4)
            goto L_0x0028
        L_0x0041:
            return
        L_0x0042:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.LeanplumInternal.triggerAction(com.leanplum.ActionContext, com.leanplum.callbacks.VariablesChangedCallback):void");
    }

    public static void maybePerformActions(String str, String str2, int i, String str3, ContextualValues contextualValues) {
        maybePerformActions(new String[]{str}, str2, i, str3, contextualValues);
    }

    static void maybePerformActions(String[] strArr, String str, int i, String str2, ContextualValues contextualValues) {
        String str3;
        String str4;
        String[] strArr2 = strArr;
        Map messages = VarCache.messages();
        if (messages != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = messages.keySet().iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                str3 = ActionManager.HELD_BACK_ACTION_NAME;
                if (!hasNext) {
                    break;
                }
                final String str5 = (String) it.next();
                if (str5 == null) {
                    String str6 = str2;
                } else if (str5.equals(str2)) {
                }
                Map map = (Map) CollectionUtil.uncheckedCast(messages.get(str5));
                String str7 = "action";
                String str8 = (String) map.get(str7);
                if (str8 != null) {
                    if (str8.equals(str3)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(Constants.HELD_BACK_MESSAGE_PREFIX);
                        sb.append(str5);
                        str4 = sb.toString();
                    } else {
                        str4 = str5;
                    }
                    if (!(!str8.equals(ActionManager.PUSH_NOTIFICATION_ACTION_NAME)) || (i & 1) != 0) {
                        MessageMatchResult messageMatchResult = new MessageMatchResult();
                        for (String shouldShowMessage : strArr2) {
                            MessageMatchResult shouldShowMessage2 = ActionManager.getInstance().shouldShowMessage(str4, map, shouldShowMessage, str, contextualValues);
                            messageMatchResult.matchedTrigger |= shouldShowMessage2.matchedTrigger;
                            messageMatchResult.matchedUnlessTrigger |= shouldShowMessage2.matchedUnlessTrigger;
                            messageMatchResult.matchedLimit |= shouldShowMessage2.matchedLimit;
                            messageMatchResult.matchedActivePeriod = shouldShowMessage2.matchedActivePeriod | messageMatchResult.matchedActivePeriod;
                        }
                        if (messageMatchResult.matchedUnlessTrigger) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("__Cancel");
                            sb2.append(map.get(str7));
                            triggerAction(new ActionContext(sb2.toString(), new HashMap(), str5), new VariablesChangedCallback() {
                                public void variablesChanged() {
                                    try {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put(Params.MESSAGE_ID, str5);
                                        LeanplumInternal.track("Cancel", 0.0d, null, null, hashMap);
                                    } catch (Throwable th) {
                                        Util.handleException(th);
                                    }
                                }
                            });
                        }
                        if (messageMatchResult.matchedActivePeriod) {
                            if (messageMatchResult.matchedTrigger) {
                                ActionManager.getInstance().recordMessageTrigger(str4);
                                if (messageMatchResult.matchedLimit) {
                                    String str9 = "priority";
                                    ActionContext actionContext = new ActionContext(map.get(str7).toString(), (Map) CollectionUtil.uncheckedCast(map.get("vars")), str4, str5, map.containsKey(str9) ? ((Integer) map.get(str9)).intValue() : 1000);
                                    actionContext.setContextualValues(contextualValues);
                                    arrayList.add(actionContext);
                                }
                            }
                            ContextualValues contextualValues2 = contextualValues;
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                Collections.sort(arrayList, new Comparator<ActionContext>() {
                    public int compare(ActionContext actionContext, ActionContext actionContext2) {
                        return actionContext.getPriority() - actionContext2.getPriority();
                    }
                });
                int priority = ((ActionContext) arrayList.get(0)).getPriority();
                int fetchCountDown = fetchCountDown((ActionContext) arrayList.get(0), messages);
                Boolean valueOf = Boolean.valueOf(false);
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    final ActionContext actionContext2 = (ActionContext) it2.next();
                    if (actionContext2.getPriority() > priority || (valueOf.booleanValue() && fetchCountDown(actionContext2, messages) == fetchCountDown)) {
                        break;
                    }
                    valueOf = Boolean.valueOf(true);
                    if (actionContext2.actionName().equals(str3)) {
                        ActionManager.getInstance().recordHeldBackImpression(actionContext2.getMessageId(), actionContext2.getOriginalMessageId());
                    } else {
                        triggerAction(actionContext2, new VariablesChangedCallback() {
                            public void variablesChanged() {
                                try {
                                    Leanplum.triggerMessageDisplayed(actionContext2);
                                } catch (Throwable th) {
                                    Util.handleException(th);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private static int fetchCountDown(ActionContext actionContext, Map<String, Object> map) {
        Object obj;
        if (actionContext.isPreview()) {
            obj = Double.valueOf(5.0d);
        } else {
            obj = ((Map) CollectionUtil.uncheckedCast(map.get(actionContext.messageId))).get("countdown");
        }
        return ((Number) obj).intValue();
    }

    private static Map<String, Object> makeTrackArgs(String str, double d, String str2, Map<String, ?> map, Map<String, String> map2) {
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        hashMap.put("value", Double.toString(d));
        hashMap.put(Params.INFO, str2);
        if (str != null) {
            hashMap.put("event", str);
        }
        if (map != null) {
            String str3 = Params.PARAMS;
            hashMap.put(str3, JsonConverter.toJson(validateAttributes(map, str3, false)));
        }
        if (!inForeground || LeanplumActivityHelper.isActivityPaused()) {
            hashMap.put("allowOffline", Boolean.TRUE.toString());
        }
        return hashMap;
    }

    public static void track(String str, double d, String str2, Map<String, ?> map, Map<String, String> map2) {
        if (!Constants.isNoop()) {
            try {
                trackInternalWhenStarted(str, map, makeTrackArgs(str, d, str2, map, map2));
            } catch (Throwable th) {
                Util.handleException(th);
            }
        }
    }

    public static void trackGeofence(GeofenceEventType geofenceEventType, double d, String str, Map<String, ?> map, Map<String, String> map2) {
        if (!Constants.isNoop()) {
            try {
                RequestOld.post(Methods.TRACK_GEOFENCE, makeTrackArgs(geofenceEventType.getName(), d, str, map, map2)).sendIfConnected();
            } catch (Throwable th) {
                Util.handleException(th);
            }
        }
    }

    private static void trackInternalWhenStarted(final String str, final Map<String, ?> map, final Map<String, Object> map2) {
        if (issuedStart) {
            trackInternal(str, map, map2);
        } else {
            addStartIssuedHandler(new Runnable() {
                public void run() {
                    try {
                        LeanplumInternal.trackInternal(str, map, map2);
                    } catch (Throwable th) {
                        Util.handleException(th);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void trackInternal(String str, Map<String, ?> map, Map<String, Object> map2) {
        String str2;
        RequestOld.post(Methods.TRACK, map2).send();
        String str3 = Params.MESSAGE_ID;
        if (map2.get(str3) != null) {
            str2 = map2.get(str3).toString();
            StringBuilder sb = new StringBuilder();
            sb.append(".m");
            sb.append(str2);
            String sb2 = sb.toString();
            if (str == null || str.length() <= 0) {
                str = sb2;
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append(" ");
                sb3.append(str);
                str = sb3.toString();
            }
        } else {
            str2 = null;
        }
        ContextualValues contextualValues = new ContextualValues();
        contextualValues.parameters = map;
        contextualValues.arguments = map2;
        Map<String, Object> map3 = contextualValues.arguments;
        String str4 = Params.PARAMS;
        if (map3.containsKey(str4)) {
            try {
                contextualValues.arguments.put(str4, new JSONObject(contextualValues.arguments.get(str4).toString()));
            } catch (JSONException unused) {
            }
        }
        maybePerformActions("event", str, 3, str2, contextualValues);
    }

    public static void performTrackedAction(String str, String str2) {
        Map messages = VarCache.messages();
        if (messages != null) {
            Map map = (Map) CollectionUtil.uncheckedCast(messages.get(str2));
            if (map != null) {
                new ActionContext(map.get("action").toString(), (Map) CollectionUtil.uncheckedCast(map.get("vars")), str2).runTrackedActionNamed(str);
            }
        }
    }

    public static void setUserLocationAttribute(final Location location, final LeanplumLocationAccuracyType leanplumLocationAccuracyType, final locationAttributeRequestsCallback locationattributerequestscallback) {
        Leanplum.addStartResponseHandler(new StartCallback() {
            public void onResponse(final boolean z) {
                Util.executeAsyncTask(false, new AsyncTask<Void, Void, Void>() {
                    /* access modifiers changed from: protected */
                    public Void doInBackground(Void... voidArr) {
                        if (!z) {
                            return null;
                        }
                        if (location == null) {
                            Log.m280e("Location can't be null in setUserLocationAttribute.");
                            return null;
                        }
                        String format = String.format(Locale.US, "%.6f,%.6f", new Object[]{Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())});
                        HashMap hashMap = new HashMap();
                        hashMap.put("location", format);
                        hashMap.put(Keys.LOCATION_ACCURACY_TYPE, leanplumLocationAccuracyType.name().toLowerCase());
                        if (!(Leanplum.getContext() == null || Locale.US == null)) {
                            try {
                                List fromLocation = new Geocoder(Leanplum.getContext(), Locale.US).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                if (fromLocation != null && fromLocation.size() > 0) {
                                    Address address = (Address) fromLocation.get(0);
                                    hashMap.put(Keys.CITY, address.getLocality());
                                    hashMap.put(Keys.REGION, address.getAdminArea());
                                    hashMap.put(Keys.COUNTRY, address.getCountryCode());
                                }
                            } catch (IOException e) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Failed to connect to Geocoder: ");
                                sb.append(e);
                                Log.m280e(sb.toString());
                            } catch (IllegalArgumentException e2) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Invalid latitude or longitude values: ");
                                sb2.append(e2);
                                Log.m280e(sb2.toString());
                            } catch (Throwable unused) {
                            }
                        }
                        RequestOld post = RequestOld.post(Methods.SET_USER_ATTRIBUTES, hashMap);
                        post.onResponse(new ResponseCallback() {
                            public void response(JSONObject jSONObject) {
                                locationattributerequestscallback.response(true);
                            }
                        });
                        post.onError(new ErrorCallback() {
                            public void error(Exception exc) {
                                locationattributerequestscallback.response(false);
                                StringBuilder sb = new StringBuilder();
                                sb.append("setUserAttributes failed when specifying location with error: ");
                                sb.append(exc.getMessage());
                                Log.m280e(sb.toString());
                            }
                        });
                        post.send();
                        return null;
                    }
                }, new Void[0]);
            }
        });
    }

    public static void recordAttributeChanges() {
        boolean z = false;
        for (Map map : userAttributeChanges) {
            Map userAttributes = VarCache.userAttributes();
            if (userAttributes == null) {
                userAttributes = new HashMap();
            }
            for (String str : map.keySet()) {
                Object obj = userAttributes.get(str);
                Object obj2 = map.get(str);
                if ((obj == null && obj2 != null) || (obj != null && !obj.equals(obj2))) {
                    ContextualValues contextualValues = new ContextualValues();
                    contextualValues.previousAttributeValue = obj;
                    contextualValues.attributeValue = obj2;
                    userAttributes.put(str, obj2);
                    maybePerformActions("userAttribute", str, 3, (String) null, contextualValues);
                    z = true;
                }
            }
        }
        userAttributeChanges.clear();
        if (z) {
            VarCache.saveUserAttributes();
        }
    }

    public static void moveToForeground() {
        synchronized (inForegroundLock) {
            if (!inForeground) {
                inForeground = true;
                Leanplum.addStartResponseHandler(new StartCallback() {
                    public void onResponse(boolean z) {
                        if (z) {
                            if (Constants.isDevelopmentModeEnabled && !Constants.isNoop()) {
                                Socket.getInstance();
                            }
                            LeanplumInternal.maybePerformActions(new String[]{Methods.START, "resume"}, (String) null, 3, (String) null, (ContextualValues) null);
                            LeanplumInternal.recordAttributeChanges();
                        }
                    }
                });
            }
        }
    }

    public static void addStartIssuedHandler(Runnable runnable) {
        if (runnable == null) {
            Log.m280e("addStartIssuedHandler - Invalid handler parameter provided.");
            return;
        }
        synchronized (startIssuedHandlers) {
            if (!issuedStart) {
                startIssuedHandlers.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    public static void triggerStartIssued() {
        synchronized (startIssuedHandlers) {
            setIssuedStart(true);
            Iterator it = startIssuedHandlers.iterator();
            while (it.hasNext()) {
                OsHandler.getInstance().post((Runnable) it.next());
            }
            startIssuedHandlers.clear();
        }
    }

    public static <T> Map<String, T> validateAttributes(Map<String, T> map, String str, boolean z) {
        if (map == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        try {
            for (Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (!z || !(value instanceof Iterable)) {
                    if (value instanceof Date) {
                        value = CollectionUtil.uncheckedCast(Long.valueOf(((Date) CollectionUtil.uncheckedCast(value)).getTime()));
                    }
                    if (value != null && !isValidScalarValue(value, str)) {
                    }
                } else {
                    boolean z2 = true;
                    Iterator it = ((Iterable) CollectionUtil.uncheckedCast(value)).iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!isValidScalarValue(it.next(), str)) {
                                z2 = false;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (!z2) {
                    }
                }
                hashMap.put(entry.getKey(), value);
            }
        } catch (ConcurrentModificationException unused) {
            maybeThrowException(new LeanplumException("ConcurrentModificationException: You cannot modify Map<String, ?> attributes/parameters. Will override with an empty map"));
            hashMap = new HashMap();
        }
        return hashMap;
    }

    private static boolean isValidScalarValue(Object obj, String str) {
        if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean)) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" values must be of type String, Number, or Boolean.");
        maybeThrowException(new LeanplumException(sb.toString()));
        return false;
    }

    public static void maybeThrowException(RuntimeException runtimeException) {
        if (!Constants.isDevelopmentModeEnabled) {
            StringBuilder sb = new StringBuilder();
            sb.append(runtimeException.getMessage());
            sb.append(" This error is only thrown in development mode.");
            Log.m280e(sb.toString(), runtimeException);
            return;
        }
        throw runtimeException;
    }

    public static boolean hasStartedAndRegisteredAsDeveloper() {
        return hasStartedAndRegisteredAsDeveloper;
    }

    public static Map<String, List<ActionCallback>> getActionHandlers() {
        return actionHandlers;
    }

    public static boolean issuedStart() {
        return issuedStart;
    }

    private static void setIssuedStart(boolean z) {
        issuedStart = z;
    }

    public static boolean hasStarted() {
        return hasStarted;
    }

    public static void setHasStarted(boolean z) {
        hasStarted = z;
    }

    public static boolean isStartSuccessful() {
        return startSuccessful;
    }

    public static void setStartSuccessful(boolean z) {
        startSuccessful = z;
    }

    public static boolean hasCalledStart() {
        return calledStart;
    }

    public static void setCalledStart(boolean z) {
        calledStart = z;
    }

    public static boolean isPaused() {
        return isPaused;
    }

    public static void setIsPaused(boolean z) {
        isPaused = z;
    }

    public static boolean hasStartedInBackground() {
        return startedInBackground;
    }

    public static void setStartedInBackground(boolean z) {
        startedInBackground = z;
    }

    public static Queue<Map<String, ?>> getUserAttributeChanges() {
        return userAttributeChanges;
    }

    public static boolean getIsScreenTrackingEnabled() {
        return isScreenTrackingEnabled;
    }

    public static boolean getIsVariantDebugInfoEnabled() {
        return isVariantDebugInfoEnabled;
    }

    public static void setIsVariantDebugInfoEnabled(boolean z) {
        isVariantDebugInfoEnabled = z;
    }

    public static void enableAutomaticScreenTracking() {
        isScreenTrackingEnabled = true;
    }
}
