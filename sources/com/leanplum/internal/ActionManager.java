package com.leanplum.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.leanplum.ActionContext;
import com.leanplum.ActionContext.ContextualValues;
import com.leanplum.Leanplum;
import com.leanplum.LocationManager;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.core.BuildConfig;
import com.leanplum.internal.Constants.Defaults;
import com.leanplum.internal.Constants.Params;
import com.leanplum.utils.SharedPreferencesUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActionManager {
    public static final String HELD_BACK_ACTION_NAME = "__held_back";
    private static final String LEANPLUM_LOCAL_PUSH_HELPER = "com.leanplum.internal.LeanplumLocalPushHelper";
    private static final String PREFERENCES_NAME = "__leanplum_messaging__";
    public static final String PUSH_NOTIFICATION_ACTION_NAME = "__Push Notification";
    private static ActionManager instance = null;
    private static LocationManager locationManager = null;
    private static boolean loggedLocationManagerFailure = false;
    private Map<String, Map<String, Number>> messageImpressionOccurrences = new HashMap();
    private Map<String, Number> messageTriggerOccurrences = new HashMap();
    private Map<String, Number> sessionOccurrences = new HashMap();

    public static class MessageMatchResult {
        public boolean matchedActivePeriod;
        public boolean matchedLimit;
        public boolean matchedTrigger;
        public boolean matchedUnlessTrigger;
    }

    public static synchronized ActionManager getInstance() {
        ActionManager actionManager;
        synchronized (ActionManager.class) {
            if (instance == null) {
                instance = new ActionManager();
            }
            actionManager = instance;
        }
        return actionManager;
    }

    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|(1:20)|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        if (loggedLocationManagerFailure == false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        r4 = new java.lang.Object[1];
        r4[r1] = "Geofencing support requires leanplum-location module and Google Play Services v8.1 and higher.\nAdd this to your build.gradle file:\nimplementation 'com.google.android.gms:play-services-location:8.3.0+'\nimplementation 'com.leanplum:leanplum-location:+'";
        com.leanplum.internal.Log.m284w(r4);
        loggedLocationManagerFailure = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0040, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x002f */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.leanplum.LocationManager getLocationManager() {
        /*
            java.lang.Class<com.leanplum.internal.ActionManager> r0 = com.leanplum.internal.ActionManager.class
            monitor-enter(r0)
            com.leanplum.LocationManager r1 = locationManager     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x000b
            com.leanplum.LocationManager r1 = locationManager     // Catch:{ all -> 0x0041 }
            monitor-exit(r0)
            return r1
        L_0x000b:
            boolean r1 = com.leanplum.internal.Util.hasPlayServices()     // Catch:{ all -> 0x0041 }
            r2 = 0
            if (r1 == 0) goto L_0x003f
            r1 = 0
            java.lang.String r3 = "com.leanplum.LocationManagerImplementation"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ Throwable -> 0x002f }
            java.lang.String r4 = "instance"
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ Throwable -> 0x002f }
            java.lang.reflect.Method r3 = r3.getMethod(r4, r5)     // Catch:{ Throwable -> 0x002f }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x002f }
            java.lang.Object r3 = r3.invoke(r2, r4)     // Catch:{ Throwable -> 0x002f }
            com.leanplum.LocationManager r3 = (com.leanplum.LocationManager) r3     // Catch:{ Throwable -> 0x002f }
            locationManager = r3     // Catch:{ Throwable -> 0x002f }
            com.leanplum.LocationManager r1 = locationManager     // Catch:{ Throwable -> 0x002f }
            monitor-exit(r0)
            return r1
        L_0x002f:
            boolean r3 = loggedLocationManagerFailure     // Catch:{ all -> 0x0041 }
            if (r3 != 0) goto L_0x003f
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0041 }
            java.lang.String r5 = "Geofencing support requires leanplum-location module and Google Play Services v8.1 and higher.\nAdd this to your build.gradle file:\nimplementation 'com.google.android.gms:play-services-location:8.3.0+'\nimplementation 'com.leanplum:leanplum-location:+'"
            r4[r1] = r5     // Catch:{ all -> 0x0041 }
            com.leanplum.internal.Log.m284w(r4)     // Catch:{ all -> 0x0041 }
            loggedLocationManagerFailure = r3     // Catch:{ all -> 0x0041 }
        L_0x003f:
            monitor-exit(r0)
            return r2
        L_0x0041:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.ActionManager.getLocationManager():com.leanplum.LocationManager");
    }

    private ActionManager() {
        listenForLocalNotifications();
    }

    private void listenForLocalNotifications() {
        Leanplum.onAction(PUSH_NOTIFICATION_ACTION_NAME, new ActionCallback() {
            public boolean onResponse(ActionContext actionContext) {
                Object obj;
                try {
                    String messageId = actionContext.getMessageId();
                    if (actionContext.isPreview()) {
                        obj = Double.valueOf(5.0d);
                    } else {
                        Map map = (Map) CollectionUtil.uncheckedCast(VarCache.getMessageDiffs().get(messageId));
                        if (map == null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Could not find message options for ID ");
                            sb.append(messageId);
                            Log.m280e(sb.toString());
                            return false;
                        }
                        obj = map.get("countdown");
                    }
                    if (!(obj instanceof Number)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Invalid notification countdown: ");
                        sb2.append(obj);
                        Log.m280e(sb2.toString());
                        return false;
                    }
                    long currentTimeMillis = System.currentTimeMillis() + (((Number) obj).longValue() * 1000);
                    try {
                        return ((Boolean) Class.forName(ActionManager.LEANPLUM_LOCAL_PUSH_HELPER).getDeclaredMethod("scheduleLocalPush", new Class[]{ActionContext.class, String.class, Long.TYPE}).invoke(new Object(), new Object[]{actionContext, messageId, Long.valueOf(currentTimeMillis)})).booleanValue();
                    } catch (Throwable unused) {
                        return false;
                    }
                } catch (Throwable th) {
                    Util.handleException(th);
                    return false;
                }
            }
        });
        Leanplum.onAction("__Cancel__Push Notification", new ActionCallback() {
            public boolean onResponse(ActionContext actionContext) {
                try {
                    String messageId = actionContext.getMessageId();
                    Context context = Leanplum.getContext();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(ActionManager.PREFERENCES_NAME, 0);
                    String format = String.format(Defaults.LOCAL_NOTIFICATION_KEY, new Object[]{messageId});
                    long j = sharedPreferences.getLong(format, 0);
                    Editor edit = sharedPreferences.edit();
                    edit.remove(format);
                    SharedPreferencesUtil.commitChanges(edit);
                    try {
                        Class.forName(ActionManager.LEANPLUM_LOCAL_PUSH_HELPER).getDeclaredMethod("cancelLocalPush", new Class[]{Context.class, String.class}).invoke(new Object(), new Object[]{context, messageId});
                        boolean z = j > System.currentTimeMillis();
                        if (z) {
                            Log.m281i("Cancelled notification");
                        }
                        return z;
                    } catch (Throwable unused) {
                        return false;
                    }
                } catch (Throwable th) {
                    Util.handleException(th);
                    return false;
                }
            }
        });
    }

    public Map<String, Number> getMessageImpressionOccurrences(String str) {
        Map<String, Number> map = (Map) this.messageImpressionOccurrences.get(str);
        if (map != null) {
            return map;
        }
        Map<String, Number> map2 = (Map) CollectionUtil.uncheckedCast(JsonConverter.fromJson(Leanplum.getContext().getSharedPreferences(PREFERENCES_NAME, 0).getString(String.format(Defaults.MESSAGE_IMPRESSION_OCCURRENCES_KEY, new Object[]{str}), "{}")));
        this.messageImpressionOccurrences.put(str, map2);
        return map2;
    }

    public void saveMessageImpressionOccurrences(Map<String, Number> map, String str) {
        Editor edit = Leanplum.getContext().getSharedPreferences(PREFERENCES_NAME, 0).edit();
        edit.putString(String.format(Defaults.MESSAGE_IMPRESSION_OCCURRENCES_KEY, new Object[]{str}), JsonConverter.toJson(map));
        this.messageImpressionOccurrences.put(str, map);
        SharedPreferencesUtil.commitChanges(edit);
    }

    public int getMessageTriggerOccurrences(String str) {
        Number number = (Number) this.messageTriggerOccurrences.get(str);
        if (number != null) {
            return number.intValue();
        }
        int i = Leanplum.getContext().getSharedPreferences(PREFERENCES_NAME, 0).getInt(String.format(Defaults.MESSAGE_TRIGGER_OCCURRENCES_KEY, new Object[]{str}), 0);
        this.messageTriggerOccurrences.put(str, Integer.valueOf(i));
        return i;
    }

    public void saveMessageTriggerOccurrences(int i, String str) {
        Editor edit = Leanplum.getContext().getSharedPreferences(PREFERENCES_NAME, 0).edit();
        edit.putInt(String.format(Defaults.MESSAGE_TRIGGER_OCCURRENCES_KEY, new Object[]{str}), i);
        this.messageTriggerOccurrences.put(str, Integer.valueOf(i));
        SharedPreferencesUtil.commitChanges(edit);
    }

    public MessageMatchResult shouldShowMessage(String str, Map<String, Object> map, String str2, String str3, ContextualValues contextualValues) {
        MessageMatchResult messageMatchResult = new MessageMatchResult();
        boolean z = false;
        if (Leanplum.getContext().getSharedPreferences(PREFERENCES_NAME, 0).getBoolean(String.format(Defaults.MESSAGE_MUTED_KEY, new Object[]{str}), false)) {
            return messageMatchResult;
        }
        messageMatchResult.matchedTrigger = matchedTriggers(map.get("whenTriggers"), str2, str3, contextualValues);
        messageMatchResult.matchedUnlessTrigger = matchedTriggers(map.get("unlessTriggers"), str2, str3, contextualValues);
        if (!messageMatchResult.matchedTrigger && !messageMatchResult.matchedUnlessTrigger) {
            return messageMatchResult;
        }
        Object obj = map.get("whenLimits");
        Map map2 = null;
        if (obj instanceof Map) {
            map2 = (Map) CollectionUtil.uncheckedCast(obj);
        }
        messageMatchResult.matchedLimit = matchesLimits(str, map2);
        Object obj2 = map.get("startTime");
        Object obj3 = map.get("endTime");
        if (obj2 == null || obj3 == null) {
            messageMatchResult.matchedActivePeriod = true;
        } else {
            long time = new Date().getTime();
            if (time >= ((Long) obj2).longValue() && time <= ((Long) obj3).longValue()) {
                z = true;
            }
            messageMatchResult.matchedActivePeriod = z;
        }
        return messageMatchResult;
    }

    private boolean matchesLimits(String str, Map<String, Object> map) {
        if (map == null) {
            return true;
        }
        List<Object> list = (List) CollectionUtil.uncheckedCast(map.get("children"));
        if (list.isEmpty()) {
            return true;
        }
        Map messageImpressionOccurrences2 = getMessageImpressionOccurrences(str);
        int messageTriggerOccurrences2 = getMessageTriggerOccurrences(str) + 1;
        for (Object uncheckedCast : list) {
            Map map2 = (Map) CollectionUtil.uncheckedCast(uncheckedCast);
            String obj = map2.get("subject").toString();
            String obj2 = map2.get("noun").toString();
            String obj3 = map2.get("verb").toString();
            if (obj.equals("times")) {
                List list2 = (List) CollectionUtil.uncheckedCast(map2.get("objects"));
                if (!matchesLimitTimes(Integer.parseInt(obj2), list2.size() > 0 ? Integer.parseInt(list2.get(0).toString()) : 0, obj3, messageImpressionOccurrences2, str)) {
                    return false;
                }
            } else if (obj.equals("onNthOccurrence")) {
                if (messageTriggerOccurrences2 != Integer.parseInt(obj2)) {
                    return false;
                }
            } else if (obj.equals("everyNthOccurrence")) {
                int parseInt = Integer.parseInt(obj2);
                if (parseInt == 0 || messageTriggerOccurrences2 % parseInt != 0) {
                    return false;
                }
            } else {
                continue;
            }
        }
        return true;
    }

    private boolean matchesLimitTimes(int i, int i2, String str, Map<String, Number> map, String str2) {
        int i3;
        int i4 = i;
        int i5 = i2;
        String str3 = str;
        Map<String, Number> map2 = map;
        Number valueOf = Long.valueOf(0);
        if (str3.equals("limitSession")) {
            Number number = (Number) this.sessionOccurrences.get(str2);
            if (number != null) {
                valueOf = number;
            }
        } else {
            if (map2 == null || map.isEmpty()) {
                return true;
            }
            Number number2 = (Number) map2.get("min");
            Number number3 = (Number) map2.get("max");
            if (number2 == null) {
                number2 = valueOf;
            }
            if (number3 == null) {
                number3 = valueOf;
            }
            long j = 1;
            if (str3.equals("limitUser")) {
                valueOf = Long.valueOf((number3.longValue() - number2.longValue()) + 1);
            } else {
                if (str3.equals("limitMinute")) {
                    i5 *= 60;
                } else if (str3.equals("limitHour")) {
                    i5 *= 3600;
                } else {
                    if (str3.equals("limitDay")) {
                        i3 = 86400;
                    } else if (str3.equals("limitWeek")) {
                        i3 = 604800;
                    } else if (str3.equals("limitMonth")) {
                        i3 = 2592000;
                    }
                    i5 *= i3;
                }
                long currentTimeMillis = System.currentTimeMillis();
                int i6 = 0;
                for (long longValue = number3.longValue(); longValue >= number2.longValue(); longValue -= j) {
                    StringBuilder sb = new StringBuilder();
                    String str4 = "";
                    sb.append(str4);
                    sb.append(longValue);
                    if (map2.containsKey(sb.toString())) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str4);
                        sb2.append(longValue);
                        if ((currentTimeMillis - ((Number) map2.get(sb2.toString())).longValue()) / 1000 > ((long) i5)) {
                            break;
                        }
                        i6++;
                        if (i6 >= i4) {
                            return false;
                        }
                        j = 1;
                    }
                }
            }
        }
        return valueOf.longValue() < ((long) i4);
    }

    private boolean matchedTriggers(Object obj, String str, String str2, ContextualValues contextualValues) {
        if (obj instanceof Map) {
            for (Object uncheckedCast : (List) CollectionUtil.uncheckedCast(((Map) CollectionUtil.uncheckedCast(obj)).get("children"))) {
                if (matchedTrigger((Map) CollectionUtil.uncheckedCast(uncheckedCast), str, str2, contextualValues)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean matchedTrigger(Map<String, Object> map, String str, String str2, ContextualValues contextualValues) {
        boolean z = false;
        if (((String) map.get("subject")).equals(str)) {
            String str3 = (String) map.get("noun");
            if ((str3 == null && str2 == null) || (str3 != null && str3.equals(str2))) {
                String str4 = (String) map.get("verb");
                List list = (List) CollectionUtil.uncheckedCast(map.get("objects"));
                if ("changesTo".equals(str4)) {
                    if (!(contextualValues == null || list == null)) {
                        for (Object next : list) {
                            if ((next == null && contextualValues.attributeValue == null) || (next != null && next.toString().equalsIgnoreCase(contextualValues.attributeValue.toString()))) {
                                return true;
                            }
                        }
                    }
                    return false;
                } else if ("changesFromTo".equals(str4)) {
                    if (!(contextualValues == null || list.size() != 2 || list.get(0) == null || list.get(1) == null || contextualValues.previousAttributeValue == null || contextualValues.attributeValue == null || !list.get(0).toString().equalsIgnoreCase(contextualValues.previousAttributeValue.toString()) || !list.get(1).toString().equalsIgnoreCase(contextualValues.attributeValue.toString()))) {
                        z = true;
                    }
                    return z;
                } else if (!"triggersWithParameter".equals(str4)) {
                    return true;
                } else {
                    if (!(contextualValues == null || list.size() != 2 || list.get(0) == null || list.get(1) == null || contextualValues.parameters == null)) {
                        Object obj = contextualValues.parameters.get(list.get(0));
                        if (obj != null && obj.toString().equalsIgnoreCase(list.get(1).toString())) {
                            z = true;
                        }
                    }
                    return z;
                }
            }
        }
        return false;
    }

    public void recordMessageTrigger(String str) {
        saveMessageTriggerOccurrences(getMessageTriggerOccurrences(str) + 1, str);
        Leanplum.countAggregator().incrementCount("record_message_trigger");
    }

    public void recordHeldBackImpression(String str, String str2) {
        recordImpression(str, str2);
    }

    public void recordMessageImpression(String str) {
        recordImpression(str, null);
    }

    private void recordImpression(String str, String str2) {
        HashMap hashMap = new HashMap();
        String str3 = Params.MESSAGE_ID;
        if (str2 != null) {
            hashMap.put(str3, str2);
            LeanplumInternal.track(Constants.HELD_BACK_EVENT_NAME, 0.0d, null, null, hashMap);
        } else {
            hashMap.put(str3, str);
            LeanplumInternal.track(null, 0.0d, null, null, hashMap);
        }
        Number number = (Number) this.sessionOccurrences.get(str);
        if (number == null) {
            number = Long.valueOf(0);
        }
        this.sessionOccurrences.put(str, Long.valueOf(number.longValue() + 1));
        Map messageImpressionOccurrences2 = getMessageImpressionOccurrences(str);
        String str4 = "max";
        String str5 = "min";
        if (messageImpressionOccurrences2 == null || messageImpressionOccurrences2.isEmpty()) {
            messageImpressionOccurrences2 = new HashMap();
            messageImpressionOccurrences2.put(str5, Long.valueOf(0));
            messageImpressionOccurrences2.put(str4, Long.valueOf(0));
            messageImpressionOccurrences2.put(BuildConfig.BUILD_NUMBER, Long.valueOf(System.currentTimeMillis()));
        } else {
            Number number2 = (Number) messageImpressionOccurrences2.get(str5);
            Number number3 = (Number) messageImpressionOccurrences2.get(str4);
            if (number2 == null) {
                number2 = Long.valueOf(0);
            }
            if (number3 == null) {
                number3 = Long.valueOf(0);
            }
            Long valueOf = Long.valueOf(number3.longValue() + 1);
            StringBuilder sb = new StringBuilder();
            String str6 = "";
            sb.append(str6);
            sb.append(valueOf);
            messageImpressionOccurrences2.put(sb.toString(), Long.valueOf(System.currentTimeMillis()));
            if ((valueOf.longValue() - number2.longValue()) + 1 > 100) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str6);
                sb2.append(number2);
                messageImpressionOccurrences2.remove(sb2.toString());
                messageImpressionOccurrences2.put(str5, Long.valueOf(number2.longValue() + 1));
            }
            messageImpressionOccurrences2.put(str4, valueOf);
        }
        saveMessageImpressionOccurrences(messageImpressionOccurrences2, str);
    }

    public void muteFutureMessagesOfKind(String str) {
        if (str != null) {
            Editor edit = Leanplum.getContext().getSharedPreferences(PREFERENCES_NAME, 0).edit();
            edit.putBoolean(String.format(Defaults.MESSAGE_MUTED_KEY, new Object[]{str}), true);
            SharedPreferencesUtil.commitChanges(edit);
        }
    }

    public static void getForegroundandBackgroundRegionNames(Set<String> set, Set<String> set2) {
        Map messages = VarCache.messages();
        for (String str : messages.keySet()) {
            Map map = (Map) CollectionUtil.uncheckedCast(messages.get(str));
            Object obj = map.get("action");
            if (obj instanceof String) {
                Set<String> set3 = obj.equals(PUSH_NOTIFICATION_ACTION_NAME) ? set2 : set;
                Map map2 = (Map) CollectionUtil.uncheckedCast(map.get("whenTriggers"));
                Map map3 = (Map) CollectionUtil.uncheckedCast(map.get("unlessTriggers"));
                addRegionNamesFromTriggersToSet(map2, set3);
                addRegionNamesFromTriggersToSet(map3, set3);
            }
        }
    }

    public static void addRegionNamesFromTriggersToSet(Map<String, Object> map, Set<String> set) {
        if (map != null) {
            for (Map map2 : (List) CollectionUtil.uncheckedCast(map.get("children"))) {
                String str = (String) map2.get("subject");
                if (str.equals("enterRegion") || str.equals("exitRegion")) {
                    set.add((String) map2.get("noun"));
                }
            }
        }
    }
}
