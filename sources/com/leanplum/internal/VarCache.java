package com.leanplum.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.leanplum.ActionContext;
import com.leanplum.CacheUpdateBlock;
import com.leanplum.Leanplum;
import com.leanplum.LocationManager;
import com.leanplum.Var;
import com.leanplum.internal.Constants.Defaults;
import com.leanplum.internal.Constants.Keys;
import com.leanplum.internal.Constants.Kinds;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.utils.SharedPreferencesUtil;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VarCache {
    private static final String LEANPLUM = "__leanplum__";
    private static final Pattern NAME_COMPONENT_PATTERN = Pattern.compile(NAME_COMPONENT_REGEX);
    private static final String NAME_COMPONENT_REGEX = "(?:[^\\.\\[.(\\\\]+|\\\\.)+";
    private static final Map<String, Object> actionDefinitions = new HashMap();
    private static int contentVersion;
    private static final Map<String, String> defaultKinds = new HashMap();
    private static Map<String, Object> devModeActionDefinitionsFromServer;
    private static Map<String, Object> devModeFileAttributesFromServer;
    private static Map<String, Object> devModeValuesFromServer;
    private static Map<String, Object> diffs = new HashMap();
    private static List<Map<String, Object>> eventRuleDiffs;
    private static CacheUpdateBlock eventsUpdateBlock;
    private static final Map<String, Object> fileAttributes = new HashMap();
    private static final Map<String, InputStream> fileStreams = new HashMap();
    private static boolean hasReceivedDiffs = false;
    private static CacheUpdateBlock interfaceUpdateBlock;
    private static Object merged;
    private static Map<String, Object> messageDiffs = new HashMap();
    private static Map<String, Object> messages = new HashMap();
    private static Map<String, Object> regions = new HashMap();
    private static boolean silent;
    private static CacheUpdateBlock updateBlock;
    private static List<Map<String, Object>> updateRuleDiffs;
    private static Map<String, Object> userAttributes;
    public static final Map<String, Object> valuesFromClient = new HashMap();
    private static Map<String, Object> variantDebugInfo = new HashMap();
    private static List<Map<String, Object>> variants = new ArrayList();
    private static final Map<String, Var<?>> vars = new ConcurrentHashMap();

    public static String[] getNameComponents(String str) {
        Matcher matcher = NAME_COMPONENT_PATTERN.matcher(str);
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            arrayList.add(str.substring(matcher.start(), matcher.end()));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static Object traverse(Object obj, Object obj2, boolean z) {
        Object obj3 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) CollectionUtil.uncheckedCast(obj);
            Object obj4 = map.get(obj2);
            if (z && obj4 == null && (obj2 instanceof String)) {
                obj4 = new HashMap();
                map.put(obj2, obj4);
            }
            return obj4;
        }
        if (obj instanceof List) {
            List list = (List) CollectionUtil.uncheckedCast(obj);
            Integer num = (Integer) obj2;
            obj3 = list.get(num.intValue());
            if (z && obj3 == null) {
                obj3 = new HashMap();
                list.set(num.intValue(), obj3);
            }
        }
        return obj3;
    }

    public static boolean registerFile(String str, String str2, InputStream inputStream, boolean z, String str3, int i) {
        if (!Constants.isDevelopmentModeEnabled) {
            return false;
        }
        if (!Constants.isNoop()) {
            if (inputStream == null) {
                return false;
            }
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            String str4 = Keys.HASH;
            String str5 = Keys.SIZE;
            if (z) {
                hashMap2.put(str4, str3);
                hashMap2.put(str5, Integer.valueOf(i));
            } else if (!Constants.hashFilesToDetermineModifications || !Util.isSimulator()) {
                hashMap2.put(str5, Integer.valueOf(FileManager.getFileSize(FileManager.fileValue(str, str2, null))));
            } else {
                HashResults fileMD5HashCreateWithPath = FileManager.fileMD5HashCreateWithPath(inputStream);
                if (fileMD5HashCreateWithPath != null) {
                    hashMap2.put(str4, fileMD5HashCreateWithPath.hash);
                    hashMap2.put(str5, Integer.valueOf(fileMD5HashCreateWithPath.size));
                }
            }
            hashMap.put("", hashMap2);
            fileAttributes.put(str, hashMap);
            fileStreams.put(str, inputStream);
            maybeUploadNewFiles();
        }
        return true;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Map<java.lang.String, java.lang.Object>, code=java.lang.Object, for r7v0, types: [java.util.Map<java.lang.String, java.lang.Object>, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void updateValues(java.lang.String r3, java.lang.String[] r4, java.lang.Object r5, java.lang.String r6, java.lang.Object r7, java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            if (r4 == 0) goto L_0x0025
            int r0 = r4.length
            if (r0 <= 0) goto L_0x0025
            r0 = 0
        L_0x0006:
            int r1 = r4.length
            r2 = 1
            int r1 = r1 - r2
            if (r0 >= r1) goto L_0x0014
            r1 = r4[r0]
            java.lang.Object r7 = traverse(r7, r1, r2)
            int r0 = r0 + 1
            goto L_0x0006
        L_0x0014:
            boolean r0 = r7 instanceof java.util.Map
            if (r0 == 0) goto L_0x0025
            java.lang.Object r7 = com.leanplum.internal.CollectionUtil.uncheckedCast(r7)
            java.util.Map r7 = (java.util.Map) r7
            int r0 = r4.length
            int r0 = r0 - r2
            r4 = r4[r0]
            r7.put(r4, r5)
        L_0x0025:
            if (r8 == 0) goto L_0x002a
            r8.put(r3, r6)
        L_0x002a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.VarCache.updateValues(java.lang.String, java.lang.String[], java.lang.Object, java.lang.String, java.util.Map, java.util.Map):void");
    }

    public static void registerVariable(Var<?> var) {
        vars.put(var.name(), var);
        synchronized (valuesFromClient) {
            updateValues(var.name(), var.nameComponents(), var.defaultValue(), var.kind(), valuesFromClient, defaultKinds);
        }
    }

    public static <T> Var<T> getVariable(String str) {
        return (Var) vars.get(str);
    }

    private static void computeMergedDictionary() {
        synchronized (valuesFromClient) {
            merged = mergeHelper(valuesFromClient, diffs);
        }
    }

    public static Object mergeHelper(Object obj, Object obj2) {
        if (obj2 == null) {
            return obj;
        }
        if ((obj2 instanceof Number) || (obj2 instanceof Boolean) || (obj2 instanceof String) || (obj2 instanceof Character) || (obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String) || (obj instanceof Character)) {
            return obj2;
        }
        boolean z = obj2 instanceof Map;
        Iterable<String> keySet = z ? ((Map) obj2).keySet() : (Iterable) obj2;
        boolean z2 = obj instanceof Map;
        Iterable<Object> keySet2 = z2 ? ((Map) obj).keySet() : (Iterable) obj;
        Map map = z ? (Map) obj2 : null;
        Map map2 = z2 ? (Map) obj : null;
        boolean z3 = false;
        if (obj == null && z && ((Map) obj2).size() > 0) {
            Iterator it = keySet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z3 = true;
                    break;
                }
                Object next = it.next();
                if (next instanceof String) {
                    String str = (String) next;
                    if (str.length() < 3 || str.charAt(0) != '[' || str.charAt(str.length() - 1) != ']') {
                        break;
                    }
                    String substring = str.substring(1, str.length() - 1);
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(Integer.getInteger(substring));
                    if (!sb.toString().equals(substring)) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if ((obj instanceof List) || z3) {
            ArrayList arrayList = new ArrayList();
            for (Object add : keySet2) {
                arrayList.add(add);
            }
            for (String str2 : keySet) {
                int parseInt = Integer.parseInt(str2.substring(1, str2.length() - 1));
                Object obj3 = map != null ? map.get(str2) : null;
                while (parseInt >= arrayList.size()) {
                    arrayList.add(null);
                }
                arrayList.set(parseInt, mergeHelper(arrayList.get(parseInt), obj3));
            }
            return arrayList;
        } else if (!z2 && !z) {
            return null;
        } else {
            HashMap hashMap = new HashMap();
            if (keySet2 != null) {
                for (Object next2 : keySet2) {
                    if (!(map == null || map2 == null)) {
                        Object obj4 = map.get(next2);
                        Object obj5 = map2.get(next2);
                        if (obj4 == null && obj5 != null) {
                            hashMap.put(next2, obj5);
                        }
                    }
                }
            }
            for (Object next3 : keySet) {
                hashMap.put(next3, mergeHelper(map2 != null ? map2.get(next3) : null, map != null ? map.get(next3) : null));
            }
            return hashMap;
        }
    }

    public static <T> T getMergedValueFromComponentArray(Object[] objArr, Object obj) {
        Object obj2 = obj;
        for (Object traverse : objArr) {
            obj2 = traverse(obj2, traverse, false);
        }
        return obj2;
    }

    public static <T> T getMergedValueFromComponentArray(Object[] objArr) {
        Object obj = merged;
        if (obj == null) {
            obj = valuesFromClient;
        }
        return getMergedValueFromComponentArray(objArr, obj);
    }

    public static Map<String, Object> getDiffs() {
        return diffs;
    }

    public static Map<String, Object> getMessageDiffs() {
        return messageDiffs;
    }

    public static List<Map<String, Object>> getUpdateRuleDiffs() {
        return updateRuleDiffs;
    }

    public static List<Map<String, Object>> getEventRuleDiffs() {
        return eventRuleDiffs;
    }

    public static Map<String, Object> regions() {
        return regions;
    }

    public static boolean hasReceivedDiffs() {
        return hasReceivedDiffs;
    }

    public static Map<String, Object> getVariantDebugInfo() {
        return variantDebugInfo;
    }

    public static void setVariantDebugInfo(Map<String, Object> map) {
        if (map != null) {
            variantDebugInfo = map;
        } else {
            variantDebugInfo = new HashMap();
        }
    }

    public static void loadDiffs() {
        String str = "[]";
        String str2 = "{}";
        if (!Constants.isNoop()) {
            SharedPreferences sharedPreferences = Leanplum.getContext().getSharedPreferences("__leanplum__", 0);
            if (RequestOld.token() == null) {
                applyVariableDiffs(new HashMap(), new HashMap(), new ArrayList(), new ArrayList(), new HashMap(), new ArrayList(), new HashMap());
                return;
            }
            try {
                AESCrypt aESCrypt = new AESCrypt(RequestOld.appId(), RequestOld.token());
                applyVariableDiffs(JsonConverter.fromJson(aESCrypt.decodePreference(sharedPreferences, Defaults.VARIABLES_KEY, str2)), JsonConverter.fromJson(aESCrypt.decodePreference(sharedPreferences, Defaults.MESSAGES_KEY, str2)), JsonConverter.listFromJson(new JSONArray(aESCrypt.decodePreference(sharedPreferences, Defaults.UPDATE_RULES_KEY, str))), JsonConverter.listFromJson(new JSONArray(aESCrypt.decodePreference(sharedPreferences, Defaults.EVENT_RULES_KEY, str))), JsonConverter.fromJson(aESCrypt.decodePreference(sharedPreferences, "regions", str2)), JsonConverter.listFromJson(new JSONArray(aESCrypt.decodePreference(sharedPreferences, Keys.VARIANTS, str))), JsonConverter.fromJson(aESCrypt.decodePreference(sharedPreferences, Keys.VARIANT_DEBUG_INFO, str2)));
                String decodePreference = aESCrypt.decodePreference(sharedPreferences, Params.DEVICE_ID, null);
                String str3 = "\"; discarding.";
                if (decodePreference != null) {
                    if (Util.isValidDeviceId(decodePreference)) {
                        RequestOld.setDeviceId(decodePreference);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid stored device id found: \"");
                        sb.append(decodePreference);
                        sb.append(str3);
                        Log.m284w(sb.toString());
                    }
                }
                String decodePreference2 = aESCrypt.decodePreference(sharedPreferences, Params.USER_ID, null);
                if (decodePreference2 != null) {
                    if (Util.isValidUserId(decodePreference2)) {
                        RequestOld.setUserId(decodePreference2);
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Invalid stored user id found: \"");
                        sb2.append(decodePreference2);
                        sb2.append(str3);
                        Log.m284w(sb2.toString());
                    }
                }
                if (Boolean.parseBoolean(aESCrypt.decodePreference(sharedPreferences, Keys.LOGGING_ENABLED, "false"))) {
                    Constants.loggingEnabled = true;
                }
            } catch (Exception e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Could not load variable diffs.\n");
                sb3.append(Log.getStackTraceString(e));
                Log.m280e(sb3.toString());
            }
            userAttributes();
            Leanplum.countAggregator().incrementCount("load_diffs");
        }
    }

    public static void saveDiffs() {
        if (!Constants.isNoop() && RequestOld.token() != null) {
            Editor edit = Leanplum.getContext().getSharedPreferences("__leanplum__", 0).edit();
            AESCrypt aESCrypt = new AESCrypt(RequestOld.appId(), RequestOld.token());
            edit.putString(Defaults.VARIABLES_KEY, aESCrypt.encrypt(JsonConverter.toJson(diffs)));
            edit.putString(Defaults.MESSAGES_KEY, aESCrypt.encrypt(JsonConverter.toJson(messages)));
            try {
                if (updateRuleDiffs != null && !updateRuleDiffs.isEmpty()) {
                    edit.putString(Defaults.UPDATE_RULES_KEY, aESCrypt.encrypt(JsonConverter.listToJsonArray(updateRuleDiffs).toString()));
                }
            } catch (JSONException e) {
                Log.m280e("Error converting updateRuleDiffs to JSON", e);
            }
            try {
                if (eventRuleDiffs != null && !eventRuleDiffs.isEmpty()) {
                    edit.putString(Defaults.EVENT_RULES_KEY, aESCrypt.encrypt(JsonConverter.listToJsonArray(eventRuleDiffs).toString()));
                }
            } catch (JSONException e2) {
                Log.m280e("Error converting eventRuleDiffs to JSON", e2);
            }
            edit.putString("regions", aESCrypt.encrypt(JsonConverter.toJson(regions)));
            try {
                if (variants != null && !variants.isEmpty()) {
                    edit.putString(Keys.VARIANTS, aESCrypt.encrypt(JsonConverter.listToJsonArray(variants).toString()));
                }
            } catch (JSONException e3) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error converting ");
                sb.append(variants);
                sb.append(" to JSON.\n");
                sb.append(Log.getStackTraceString(e3));
                Log.m280e(sb.toString());
            }
            Map<String, Object> map = variantDebugInfo;
            if (map != null) {
                edit.putString(Keys.VARIANT_DEBUG_INFO, aESCrypt.encrypt(JsonConverter.toJson(map)));
            }
            edit.putString(Params.DEVICE_ID, aESCrypt.encrypt(RequestOld.deviceId()));
            edit.putString(Params.USER_ID, aESCrypt.encrypt(RequestOld.userId()));
            edit.putString(Keys.LOGGING_ENABLED, aESCrypt.encrypt(String.valueOf(Constants.loggingEnabled)));
            SharedPreferencesUtil.commitChanges(edit);
            Leanplum.countAggregator().incrementCount("send_diffs");
        }
    }

    static int getResIdFromPath(String str) {
        try {
            String replace = str.replace("res/", "");
            String substring = replace.substring(0, replace.lastIndexOf(46));
            String substring2 = substring.substring(substring.lastIndexOf(47) + 1);
            String replace2 = substring.substring(0, substring.lastIndexOf(47)).replace('/', '.');
            Context context = Leanplum.getContext();
            return context.getResources().getIdentifier(substring2, replace2, context.getPackageName());
        } catch (Exception unused) {
            return 0;
        }
    }

    private static void fileVariableFinish() {
        for (String str : new HashMap(vars).keySet()) {
            Var var = (Var) vars.get(str);
            if (var != null) {
                String str2 = var.stringValue;
                if (var.isResource) {
                    if ("file".equals(var.kind()) && str2 != null && !str2.equals(var.defaultValue())) {
                        Map map = (Map) CollectionUtil.uncheckedCast(fileAttributes.get(str2));
                        InputStream inputStream = (InputStream) fileStreams.get(str2);
                        if (!(map == null || inputStream == null)) {
                            var.setOverrideResId(getResIdFromPath(var.stringValue()));
                        }
                    }
                }
            }
        }
    }

    public static void applyVariableDiffs(Map<String, Object> map, Map<String, Object> map2, List<Map<String, Object>> list, List<Map<String, Object>> list2, Map<String, Object> map3, List<Map<String, Object>> list3, Map<String, Object> map4) {
        boolean z;
        String str;
        String str2;
        List<Map<String, Object>> list4 = list;
        List<Map<String, Object>> list5 = list2;
        Map<String, Object> map5 = map3;
        if (map != null) {
            diffs = map;
            computeMergedDictionary();
            for (String str3 : new HashMap(vars).keySet()) {
                ((Var) vars.get(str3)).update();
            }
            fileVariableFinish();
        }
        boolean z2 = false;
        if (map2 != null) {
            messageDiffs = map2;
            HashMap hashMap = new HashMap();
            Iterator it = map2.entrySet().iterator();
            while (true) {
                str = "action";
                str2 = "vars";
                if (!it.hasNext()) {
                    break;
                }
                Entry entry = (Entry) it.next();
                Map map6 = (Map) CollectionUtil.uncheckedCast(entry.getValue());
                HashMap hashMap2 = new HashMap(map6);
                Map map7 = (Map) CollectionUtil.uncheckedCast(mergeHelper((Map) Util.multiIndex(actionDefinitions, hashMap2.get(str), "values"), (Map) CollectionUtil.uncheckedCast(map6.get(str2))));
                hashMap.put(entry.getKey(), hashMap2);
                hashMap2.put(str2, map7);
            }
            messages = hashMap;
            for (Entry key : messages.entrySet()) {
                String str4 = (String) key.getKey();
                Map map8 = (Map) CollectionUtil.uncheckedCast(messages.get(str4));
                if (!(map8 == null || map8.get(str) == null)) {
                    new ActionContext(map8.get(str).toString(), (Map) CollectionUtil.uncheckedCast(map8.get(str2)), str4).update();
                }
            }
        }
        if (map5 != null) {
            regions = map5;
        }
        if (!(map2 == null && map5 == null)) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            ActionManager.getForegroundandBackgroundRegionNames(hashSet, hashSet2);
            LocationManager locationManager = ActionManager.getLocationManager();
            if (locationManager != null) {
                locationManager.setRegionsData(map5, hashSet, hashSet2);
            }
        }
        if (list4 != null) {
            z = !list4.equals(updateRuleDiffs);
            updateRuleDiffs = new ArrayList(list4);
            downloadUpdateRulesImages();
        } else {
            z = false;
        }
        if (list5 != null) {
            z2 = !list5.equals(eventRuleDiffs);
            eventRuleDiffs = new ArrayList(list5);
        }
        if (list3 != null) {
            variants = list3;
        }
        if (map4 != null) {
            setVariantDebugInfo(map4);
        }
        contentVersion++;
        if (!silent) {
            saveDiffs();
            triggerHasReceivedDiffs();
            if (z) {
                CacheUpdateBlock cacheUpdateBlock = interfaceUpdateBlock;
                if (cacheUpdateBlock != null) {
                    cacheUpdateBlock.updateCache();
                }
            }
            if (z2) {
                CacheUpdateBlock cacheUpdateBlock2 = eventsUpdateBlock;
                if (cacheUpdateBlock2 != null) {
                    cacheUpdateBlock2.updateCache();
                }
            }
        }
        Leanplum.countAggregator().incrementCount("apply_variable_diffs");
    }

    static void applyUpdateRuleDiffs(List<Map<String, Object>> list) {
        updateRuleDiffs = list;
        downloadUpdateRulesImages();
        CacheUpdateBlock cacheUpdateBlock = interfaceUpdateBlock;
        if (cacheUpdateBlock != null) {
            cacheUpdateBlock.updateCache();
        }
        saveDiffs();
    }

    private static void downloadUpdateRulesImages() {
        for (Map map : updateRuleDiffs) {
            for (Object uncheckedCast : (List) map.get("changes")) {
                Map map2 = (Map) CollectionUtil.uncheckedCast(uncheckedCast);
                String str = (String) map2.get("key");
                if (str != null && str.contains("image")) {
                    FileManager.maybeDownloadFile(true, (String) map2.get("value"), null, null, null);
                }
            }
        }
    }

    public static int contentVersion() {
        return contentVersion;
    }

    private static boolean areActionDefinitionsEqual(Map<String, Object> map, Map<String, Object> map2) {
        if (map == null || map2 == null || map.size() != map2.size()) {
            return false;
        }
        Iterator it = map.entrySet().iterator();
        while (true) {
            boolean z = true;
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                Map map3 = (Map) CollectionUtil.uncheckedCast(entry.getValue());
                Map map4 = (Map) CollectionUtil.uncheckedCast(map2.get(entry.getKey()));
                if (map4 != null && map3 != null) {
                    String str = "kind";
                    Object obj = map3.get(str);
                    String str2 = "values";
                    Object obj2 = map3.get(str2);
                    String str3 = Params.KINDS;
                    Object obj3 = map3.get(str3);
                    String str4 = "options";
                    Object obj4 = map3.get(str4);
                    if ((obj != null && !obj.equals(map4.get(str))) || ((obj2 != null && !obj2.equals(map4.get(str2))) || (obj3 != null && !obj3.equals(map4.get(str3))))) {
                        break;
                    }
                    boolean z2 = obj4 == null;
                    if (map4.get(str4) != null) {
                        z = false;
                    }
                    if (z2 != z || (obj4 != null && obj4.equals(map4.get(str4)))) {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private static void triggerHasReceivedDiffs() {
        hasReceivedDiffs = true;
        CacheUpdateBlock cacheUpdateBlock = updateBlock;
        if (cacheUpdateBlock != null) {
            cacheUpdateBlock.updateCache();
        }
    }

    static boolean sendVariablesIfChanged() {
        return sendContentIfChanged(true, false);
    }

    static boolean sendActionsIfChanged() {
        return sendContentIfChanged(false, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean sendContentIfChanged(boolean r4, boolean r5) {
        /*
            r0 = 1
            if (r4 == 0) goto L_0x0011
            java.util.Map<java.lang.String, java.lang.Object> r1 = devModeValuesFromServer
            if (r1 == 0) goto L_0x0011
            java.util.Map<java.lang.String, java.lang.Object> r2 = valuesFromClient
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0011
            r1 = 1
            goto L_0x0012
        L_0x0011:
            r1 = 0
        L_0x0012:
            if (r5 == 0) goto L_0x001f
            java.util.Map<java.lang.String, java.lang.Object> r2 = actionDefinitions
            java.util.Map<java.lang.String, java.lang.Object> r3 = devModeActionDefinitionsFromServer
            boolean r2 = areActionDefinitionsEqual(r2, r3)
            if (r2 != 0) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r0 = r1
        L_0x0020:
            if (r0 == 0) goto L_0x0060
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            if (r4 == 0) goto L_0x003f
            java.util.Map<java.lang.String, java.lang.Object> r4 = valuesFromClient
            java.lang.String r4 = com.leanplum.internal.JsonConverter.toJson(r4)
            java.lang.String r2 = "vars"
            r1.put(r2, r4)
            java.util.Map<java.lang.String, java.lang.String> r4 = defaultKinds
            java.lang.String r4 = com.leanplum.internal.JsonConverter.toJson(r4)
            java.lang.String r2 = "kinds"
            r1.put(r2, r4)
        L_0x003f:
            if (r5 == 0) goto L_0x004c
            java.util.Map<java.lang.String, java.lang.Object> r4 = actionDefinitions
            java.lang.String r4 = com.leanplum.internal.JsonConverter.toJson(r4)
            java.lang.String r5 = "actionDefinitions"
            r1.put(r5, r4)
        L_0x004c:
            java.util.Map<java.lang.String, java.lang.Object> r4 = fileAttributes
            java.lang.String r4 = com.leanplum.internal.JsonConverter.toJson(r4)
            java.lang.String r5 = "fileAttributes"
            r1.put(r5, r4)
            java.lang.String r4 = "setVars"
            com.leanplum.internal.RequestOld r4 = com.leanplum.internal.RequestOld.post(r4, r1)
            r4.sendIfConnected()
        L_0x0060:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.VarCache.sendContentIfChanged(boolean, boolean):boolean");
    }

    static void maybeUploadNewFiles() {
        String str;
        String str2;
        ArrayList arrayList;
        if (!Constants.isNoop() && devModeFileAttributesFromServer != null && Leanplum.hasStartedAndRegisteredAsDeveloper() && Constants.enableFileUploadingInDevelopmentMode) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            Iterator it = fileAttributes.entrySet().iterator();
            char c = 0;
            int i = 0;
            while (true) {
                boolean hasNext = it.hasNext();
                str = Methods.UPLOAD_FILE;
                str2 = "data";
                if (!hasNext) {
                    break;
                }
                Entry entry = (Entry) it.next();
                String str3 = (String) entry.getKey();
                Map map = (Map) CollectionUtil.uncheckedCast(devModeFileAttributesFromServer.get(str3));
                String str4 = "";
                Map map2 = (Map) CollectionUtil.uncheckedCast(((Map) CollectionUtil.uncheckedCast(entry.getValue())).get(str4));
                Map map3 = (Map) CollectionUtil.uncheckedCast(map != null ? map.get(str4) : null);
                if (FileManager.isNewerLocally(map2, map3)) {
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("Will upload file ");
                    sb.append(str3);
                    sb.append(". Local attributes: ");
                    sb.append(map2);
                    sb.append("; server attributes: ");
                    sb.append(map3);
                    objArr[c] = sb.toString();
                    Log.m283v(objArr);
                    String str5 = Keys.HASH;
                    String str6 = (String) map2.get(str5);
                    if (str6 == null) {
                        str6 = str4;
                    }
                    String fileRelativeToAppBundle = FileManager.fileRelativeToAppBundle(str3);
                    if ((i <= 26214400 || arrayList2.size() <= 0) && arrayList2.size() < 16) {
                        arrayList = arrayList4;
                    } else {
                        HashMap hashMap = new HashMap();
                        hashMap.put(str2, arrayList3.toString());
                        RequestOld.post(str, hashMap).sendFilesNow(arrayList2, arrayList4);
                        arrayList2 = new ArrayList();
                        arrayList3 = new ArrayList();
                        arrayList = new ArrayList();
                        i = 0;
                    }
                    ArrayList arrayList5 = arrayList3;
                    ArrayList arrayList6 = arrayList2;
                    String str7 = Keys.SIZE;
                    i += ((Integer) map2.get(str7)).intValue();
                    arrayList6.add(fileRelativeToAppBundle);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(str5, str6);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(map2.get(str7));
                        sb2.append(str4);
                        jSONObject.put(str7, sb2.toString());
                        jSONObject.put(Keys.FILENAME, str3);
                        arrayList5.add(jSONObject);
                    } catch (JSONException e) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Unable to upload files.\n");
                        sb3.append(Log.getStackTraceString(e));
                        Log.m280e(sb3.toString());
                        arrayList5.add(new JSONObject());
                    }
                    arrayList.add(fileStreams.get(str3));
                    arrayList2 = arrayList6;
                    arrayList3 = arrayList5;
                    arrayList4 = arrayList;
                }
                c = 0;
            }
            if (arrayList2.size() > 0) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put(str2, arrayList3.toString());
                RequestOld.post(str, hashMap2).sendFilesNow(arrayList2, arrayList4);
            }
        }
    }

    public static void setSilent(boolean z) {
        silent = z;
    }

    public static boolean silent() {
        return silent;
    }

    public static void setDevModeValuesFromServer(Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        devModeValuesFromServer = map;
        devModeActionDefinitionsFromServer = map3;
        devModeFileAttributesFromServer = map2;
    }

    public static void onUpdate(CacheUpdateBlock cacheUpdateBlock) {
        updateBlock = cacheUpdateBlock;
        Leanplum.countAggregator().incrementCount("on_update_varcache");
    }

    public static void onInterfaceUpdate(CacheUpdateBlock cacheUpdateBlock) {
        interfaceUpdateBlock = cacheUpdateBlock;
        Leanplum.countAggregator().incrementCount("on_interface_update");
    }

    public static void onEventsUpdate(CacheUpdateBlock cacheUpdateBlock) {
        eventsUpdateBlock = cacheUpdateBlock;
        Leanplum.countAggregator().incrementCount("on_events_update");
    }

    public static List<Map<String, Object>> variants() {
        return variants;
    }

    public static Map<String, Object> actionDefinitions() {
        return actionDefinitions;
    }

    public static Map<String, Object> messages() {
        return messages;
    }

    public static void registerActionDefinition(String str, int i, List<ActionArg<?>> list, Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (ActionArg actionArg : list) {
            updateValues(actionArg.name(), getNameComponents(actionArg.name()), actionArg.defaultValue(), actionArg.kind(), hashMap, hashMap2);
            arrayList.add(actionArg.name());
        }
        HashMap hashMap3 = new HashMap();
        hashMap3.put("kind", Integer.valueOf(i));
        hashMap3.put("values", hashMap);
        hashMap3.put(Params.KINDS, hashMap2);
        hashMap3.put("order", arrayList);
        hashMap3.put("options", map);
        actionDefinitions.put(str, hashMap3);
    }

    public static <T> String kindFromValue(T t) {
        if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Character) || (t instanceof Byte) || (t instanceof BigInteger)) {
            return Kinds.INT;
        }
        if ((t instanceof Float) || (t instanceof Double) || (t instanceof BigDecimal)) {
            return Kinds.FLOAT;
        }
        if (t instanceof String) {
            return "string";
        }
        if ((t instanceof List) || (t instanceof Array)) {
            return Kinds.ARRAY;
        }
        if (t instanceof Map) {
            return Kinds.DICTIONARY;
        }
        if (t instanceof Boolean) {
            return Kinds.BOOLEAN;
        }
        return null;
    }

    static Map<String, Object> userAttributes() {
        if (userAttributes == null) {
            try {
                userAttributes = JsonConverter.fromJson(new AESCrypt(RequestOld.appId(), RequestOld.token()).decodePreference(Leanplum.getContext().getSharedPreferences("__leanplum__", 0), Defaults.ATTRIBUTES_KEY, "{}"));
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not load user attributes.\n");
                sb.append(Log.getStackTraceString(e));
                Log.m280e(sb.toString());
                userAttributes = new HashMap();
            }
        }
        return userAttributes;
    }

    public static void saveUserAttributes() {
        if (!Constants.isNoop() && RequestOld.appId() != null && userAttributes != null) {
            Editor edit = Leanplum.getContext().getSharedPreferences("__leanplum__", 0).edit();
            edit.putString(Defaults.ATTRIBUTES_KEY, new AESCrypt(RequestOld.appId(), RequestOld.token()).encrypt(JsonConverter.toJson(userAttributes)));
            SharedPreferencesUtil.commitChanges(edit);
            Leanplum.countAggregator().incrementCount("save_user_attributes");
        }
    }

    public static void clearUserContent() {
        vars.clear();
        variants.clear();
        variantDebugInfo.clear();
        diffs.clear();
        messageDiffs.clear();
        messages = null;
        userAttributes = null;
        merged = null;
        devModeValuesFromServer = null;
        devModeFileAttributesFromServer = null;
        devModeActionDefinitionsFromServer = null;
    }

    public static void reset() {
        vars.clear();
        variantDebugInfo.clear();
        fileAttributes.clear();
        fileStreams.clear();
        valuesFromClient.clear();
        defaultKinds.clear();
        actionDefinitions.clear();
        diffs.clear();
        messageDiffs.clear();
        regions.clear();
        devModeValuesFromServer = null;
        devModeFileAttributesFromServer = null;
        devModeActionDefinitionsFromServer = null;
        variants.clear();
        updateBlock = null;
        hasReceivedDiffs = false;
        messages = null;
        merged = null;
        silent = false;
        contentVersion = 0;
        userAttributes = null;
    }
}
