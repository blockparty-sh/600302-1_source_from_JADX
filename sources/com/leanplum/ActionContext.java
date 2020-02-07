package com.leanplum;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.internal.ActionManager;
import com.leanplum.internal.BaseActionContext;
import com.leanplum.internal.CollectionUtil;
import com.leanplum.internal.Constants;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.Constants.Values;
import com.leanplum.internal.FileManager;
import com.leanplum.internal.JsonConverter;
import com.leanplum.internal.LeanplumInternal;
import com.leanplum.internal.Log;
import com.leanplum.internal.Util;
import com.leanplum.internal.VarCache;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlinx.coroutines.DebugKt;
import org.json.JSONException;
import org.json.JSONObject;

public class ActionContext extends BaseActionContext implements Comparable<ActionContext> {
    private final int contentVersion;
    private ContextualValues contextualValues;
    private String key;
    private final String name;
    private ActionContext parentContext;
    private boolean preventRealtimeUpdating;

    public static class ContextualValues {
        public Map<String, Object> arguments;
        public Object attributeValue;
        public Map<String, ?> parameters;
        public Object previousAttributeValue;
    }

    public ActionContext(String str, Map<String, Object> map, String str2) {
        this(str, map, str2, null, 1000);
    }

    public ActionContext(String str, Map<String, Object> map, String str2, String str3, int i) {
        super(str2, str3);
        this.preventRealtimeUpdating = false;
        this.name = str;
        this.args = map;
        this.contentVersion = VarCache.contentVersion();
        this.priority = i;
    }

    public void preventRealtimeUpdating() {
        this.preventRealtimeUpdating = true;
    }

    public void setContextualValues(ContextualValues contextualValues2) {
        this.contextualValues = contextualValues2;
    }

    public ContextualValues getContextualValues() {
        return this.contextualValues;
    }

    private static Map<String, Object> getDefinition(String str) {
        Map<String, Object> map = (Map) CollectionUtil.uncheckedCast(VarCache.actionDefinitions().get(str));
        return map == null ? new HashMap() : map;
    }

    private Map<String, Object> getDefinition() {
        return getDefinition(this.name);
    }

    private Map<String, Object> defaultValues() {
        Map<String, Object> map = (Map) CollectionUtil.uncheckedCast(getDefinition().get("values"));
        return map == null ? new HashMap() : map;
    }

    private Map<String, String> kinds() {
        Map<String, String> map = (Map) CollectionUtil.uncheckedCast(getDefinition().get(Params.KINDS));
        return map == null ? new HashMap() : map;
    }

    public void update() {
        updateArgs(this.args, "", defaultValues());
    }

    private void updateArgs(Map<String, Object> map, String str, Map<String, Object> map2) {
        Map kinds = kinds();
        for (Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            Object value = entry.getValue();
            Map map3 = null;
            Object obj = map2 != null ? map2.get(str2) : null;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            String str3 = (String) kinds.get(sb.toString());
            String str4 = Values.ACTION_ARG;
            String str5 = "action";
            if ((str3 == null || !str3.equals(str5)) && (value instanceof Map)) {
                Map map4 = (Map) value;
                if (!map4.containsKey(str4)) {
                    if (obj instanceof Map) {
                        map3 = (Map) obj;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(str2);
                    sb2.append(".");
                    updateArgs(map4, sb2.toString(), map3);
                }
            }
            if ((str3 != null && str3.equals("file")) || str2.contains("__file__")) {
                FileManager.maybeDownloadFile(false, value.toString(), obj != null ? obj.toString() : null, null, null);
            } else if (str3 == null || str3.equals(str5)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(str2);
                Object objectNamed = objectNamed(sb3.toString());
                if (objectNamed instanceof Map) {
                    Map map5 = (Map) objectNamed;
                    new ActionContext((String) map5.get(str4), map5, this.messageId).update();
                }
            }
        }
    }

    public String actionName() {
        return this.name;
    }

    public <T> T objectNamed(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("objectNamed - Invalid name parameter provided.");
            return null;
        }
        try {
            if (!this.preventRealtimeUpdating && VarCache.contentVersion() > this.contentVersion) {
                ActionContext actionContext = this.parentContext;
                if (actionContext != null) {
                    this.args = actionContext.getChildArgs(this.key);
                } else if (this.messageId != null) {
                    Map map = (Map) CollectionUtil.uncheckedCast(VarCache.messages().get(this.messageId));
                    if (map != null) {
                        this.args = (Map) CollectionUtil.uncheckedCast(map.get("vars"));
                    }
                }
            }
            return VarCache.getMergedValueFromComponentArray(VarCache.getNameComponents(str), this.args);
        } catch (Throwable th) {
            Util.handleException(th);
            return null;
        }
    }

    public String stringNamed(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("stringNamed - Invalid name parameter provided.");
            return null;
        }
        Object objectNamed = objectNamed(str);
        if (objectNamed == null) {
            return null;
        }
        try {
            return fillTemplate(objectNamed.toString());
        } catch (Throwable th) {
            Util.handleException(th);
            return objectNamed.toString();
        }
    }

    public String fillTemplate(String str) {
        if (this.contextualValues == null || str == null) {
            return str;
        }
        String str2 = "##";
        if (!str.contains(str2)) {
            return str;
        }
        if (this.contextualValues.parameters != null) {
            for (Entry entry : this.contextualValues.parameters.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append("##Parameter ");
                sb.append((String) entry.getKey());
                sb.append(str2);
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("");
                sb3.append(entry.getValue());
                str = str.replace(sb2, sb3.toString());
            }
        }
        if (this.contextualValues.previousAttributeValue != null) {
            str = str.replace("##Previous Value##", this.contextualValues.previousAttributeValue.toString());
        }
        if (this.contextualValues.attributeValue == null) {
            return str;
        }
        return str.replace("##Value##", this.contextualValues.attributeValue.toString());
    }

    private String getDefaultValue(String str) {
        String[] split = str.split("\\.");
        Map defaultValues = defaultValues();
        int i = 0;
        while (true) {
            String str2 = null;
            if (i >= split.length || defaultValues == null) {
                return null;
            }
            if (i == split.length - 1) {
                Object obj = defaultValues.get(split[i]);
                if (obj != null) {
                    str2 = obj.toString();
                }
                return str2;
            }
            defaultValues = (Map) CollectionUtil.uncheckedCast(defaultValues.get(split[i]));
            i++;
        }
    }

    public InputStream streamNamed(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                Log.m280e("streamNamed - Invalid name parameter provided.");
                return null;
            }
            String stringNamed = stringNamed(str);
            String defaultValue = getDefaultValue(str);
            if (stringNamed == null || stringNamed.length() == 0) {
                if (defaultValue != null) {
                    if (defaultValue.length() == 0) {
                    }
                }
                return null;
            }
            InputStream stream = FileManager.stream(false, null, null, FileManager.fileValue(stringNamed, defaultValue, null), defaultValue, null);
            if (stream == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not open stream named ");
                sb.append(str);
                Log.m280e(sb.toString());
            }
            return stream;
        } catch (Throwable th) {
            Util.handleException(th);
            return null;
        }
    }

    public boolean booleanNamed(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("booleanNamed - Invalid name parameter provided.");
            return false;
        }
        Object objectNamed = objectNamed(str);
        if (objectNamed == null) {
            return false;
        }
        try {
            if (objectNamed instanceof Boolean) {
                return ((Boolean) objectNamed).booleanValue();
            }
            return convertToBoolean(objectNamed.toString());
        } catch (Throwable th) {
            Util.handleException(th);
            return false;
        }
    }

    private static boolean convertToBoolean(String str) {
        return "1".equalsIgnoreCase(str) || "yes".equalsIgnoreCase(str) || "true".equalsIgnoreCase(str) || DebugKt.DEBUG_PROPERTY_VALUE_ON.equalsIgnoreCase(str);
    }

    public Number numberNamed(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("numberNamed - Invalid name parameter provided.");
            return null;
        }
        Object objectNamed = objectNamed(str);
        if (objectNamed != null) {
            try {
                if (!TextUtils.isEmpty(objectNamed.toString())) {
                    if (objectNamed instanceof Number) {
                        return (Number) objectNamed;
                    }
                    return Double.valueOf(objectNamed.toString());
                }
            } catch (Throwable th) {
                Util.handleException(th);
                return Double.valueOf(0.0d);
            }
        }
        return Double.valueOf(0.0d);
    }

    private Map<String, Object> getChildArgs(String str) {
        Object objectNamed = objectNamed(str);
        if (!(objectNamed instanceof Map)) {
            return null;
        }
        Map map = (Map) CollectionUtil.uncheckedCast(objectNamed);
        return (Map) CollectionUtil.uncheckedCast(VarCache.mergeHelper((Map) CollectionUtil.uncheckedCast(getDefinition((String) map.get(Values.ACTION_ARG)).get("values")), map));
    }

    public void runActionNamed(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.m280e("runActionNamed - Invalid name parameter provided.");
            return;
        }
        Map childArgs = getChildArgs(str);
        if (childArgs != null) {
            if (!isChainToExistingMessageStarted(childArgs, str)) {
                Object obj = childArgs.get(Values.ACTION_ARG);
                if (obj != null) {
                    createActionContextForMessageId(obj.toString(), childArgs, this.messageId, str, Boolean.valueOf(false));
                }
            }
            Leanplum.countAggregator().incrementCount("run_action_named");
        }
    }

    private boolean createActionContextForMessageId(String str, Map<String, Object> map, String str2, String str3, Boolean bool) {
        try {
            final ActionContext actionContext = new ActionContext(str, map, str2);
            actionContext.contextualValues = this.contextualValues;
            actionContext.preventRealtimeUpdating = this.preventRealtimeUpdating;
            actionContext.isRooted = this.isRooted;
            actionContext.key = str3;
            if (bool.booleanValue()) {
                LeanplumInternal.triggerAction(actionContext, new VariablesChangedCallback() {
                    public void variablesChanged() {
                        try {
                            Leanplum.triggerMessageDisplayed(actionContext);
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                    }
                });
            } else {
                actionContext.parentContext = this;
                LeanplumInternal.triggerAction(actionContext);
            }
            return true;
        } catch (Throwable th) {
            Util.handleException(th);
            return false;
        }
    }

    private boolean isChainToExistingMessageStarted(Map<String, Object> map, final String str) {
        if (map == null) {
            return false;
        }
        final String chainedMessageId = getChainedMessageId(map);
        if (!shouldForceContentUpdateForChainedMessage(map)) {
            return executeChainedMessage(chainedMessageId, VarCache.messages(), str);
        }
        Leanplum.forceContentUpdate(new VariablesChangedCallback() {
            public void variablesChanged() {
                ActionContext.this.executeChainedMessage(chainedMessageId, VarCache.messages(), str);
            }
        });
        return false;
    }

    static boolean shouldForceContentUpdateForChainedMessage(Map<String, Object> map) {
        if (map == null) {
            return false;
        }
        String chainedMessageId = getChainedMessageId(map);
        if (chainedMessageId == null || (VarCache.messages() != null && VarCache.messages().containsKey(chainedMessageId))) {
            return false;
        }
        return true;
    }

    static String getChainedMessageId(Map<String, Object> map) {
        if (map != null) {
            if (Values.CHAIN_MESSAGE_ACTION_NAME.equals(map.get(Values.ACTION_ARG))) {
                return (String) map.get(Values.CHAIN_MESSAGE_ARG);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean executeChainedMessage(String str, Map<String, Object> map, String str2) {
        boolean z = false;
        if (map == null) {
            return false;
        }
        Map map2 = (Map) CollectionUtil.uncheckedCast(map.get(str));
        if (map2 != null) {
            Map map3 = (Map) CollectionUtil.uncheckedCast(map2.get("vars"));
            Object obj = map2.get("action");
            if (obj != null) {
                if (createActionContextForMessageId(obj.toString(), map3, str, str2, Boolean.valueOf(true))) {
                    z = true;
                }
            }
        }
        return z;
    }

    private String eventWithParentEventNames(String str) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (ActionContext actionContext = this; actionContext.parentContext != null; actionContext = actionContext.parentContext) {
            arrayList.add(actionContext);
        }
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < -1) {
                break;
            }
            if (sb.length() > 0) {
                sb.append(' ');
            }
            String str2 = size > -1 ? ((ActionContext) arrayList.get(size)).key : str;
            String str3 = "";
            if (str2 == null) {
                sb = new StringBuilder(str3);
                break;
            }
            sb.append(str2.replace(" action", str3));
        }
        return sb.toString();
    }

    public void runTrackedActionNamed(String str) {
        try {
            if (!Constants.isNoop() && this.messageId != null && this.isRooted) {
                if (TextUtils.isEmpty(str)) {
                    Log.m280e("runTrackedActionNamed - Invalid name parameter provided.");
                    return;
                }
                trackMessageEvent(str, 0.0d, null, null);
            }
            runActionNamed(str);
        } catch (Throwable th) {
            Util.handleException(th);
        }
        Leanplum.countAggregator().incrementCount("run_tracked_action_named");
    }

    public void trackMessageEvent(String str, double d, String str2, Map<String, Object> map) {
        try {
            if (!Constants.isNoop() && this.messageId != null) {
                if (TextUtils.isEmpty(str)) {
                    Log.m280e("trackMessageEvent - Invalid event parameter provided.");
                    return;
                }
                String eventWithParentEventNames = eventWithParentEventNames(str);
                if (TextUtils.isEmpty(eventWithParentEventNames)) {
                    Log.m280e("trackMessageEvent - Failed to generate parent action names.");
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put(Params.MESSAGE_ID, this.messageId);
                LeanplumInternal.track(eventWithParentEventNames, d, str2, map, hashMap);
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    public void track(String str, double d, Map<String, Object> map) {
        try {
            if (!Constants.isNoop() && this.messageId != null) {
                if (TextUtils.isEmpty(str)) {
                    Log.m280e("track - Invalid event parameter provided.");
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put(Params.MESSAGE_ID, this.messageId);
                LeanplumInternal.track(str, d, null, map, hashMap);
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    public void muteFutureMessagesOfSameKind() {
        try {
            ActionManager.getInstance().muteFutureMessagesOfKind(this.messageId);
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    public int compareTo(@NonNull ActionContext actionContext) {
        return this.priority - actionContext.getPriority();
    }

    public static String filePath(String str) {
        return FileManager.fileValue(str);
    }

    public static JSONObject mapToJsonObject(Map<String, ?> map) throws JSONException {
        return JsonConverter.mapToJsonObject(map);
    }

    public static <T> Map<String, T> mapFromJson(JSONObject jSONObject) throws JSONException {
        return JsonConverter.mapFromJson(jSONObject);
    }
}
