package com.leanplum.internal;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumEditorMode;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.Constants.Values;
import com.microsoft.appcenter.Constants;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Socket {
    private static final String EVENT_APPLY_VARS = "applyVars";
    private static final String EVENT_CONTENT_RESPONSE = "getContentResponse";
    private static final String EVENT_GET_ACTIONS = "getActions";
    private static final String EVENT_GET_VARIABLES = "getVariables";
    private static final String EVENT_GET_VIEW_HIERARCHY = "getViewHierarchy";
    private static final String EVENT_PREVIEW_UPDATE_RULES = "previewUpdateRules";
    private static final String EVENT_REGISTER_DEVICE = "registerDevice";
    private static final String EVENT_TRIGGER = "trigger";
    private static final String EVENT_UPDATE_VARS = "updateVars";
    private static final String TAG = "Leanplum";
    private static Socket instance = new Socket();
    /* access modifiers changed from: private */
    public boolean authSent;
    /* access modifiers changed from: private */
    public boolean connected = false;
    /* access modifiers changed from: private */
    public boolean connecting = false;
    /* access modifiers changed from: private */
    public SocketIOClient sio;

    public Socket() {
        createSocketClient();
    }

    public static Socket getInstance() {
        return instance;
    }

    private void createSocketClient() {
        C24061 r0 = new Handler() {
            public void onError(Exception exc) {
                Log.m280e("Development socket error", exc);
            }

            public void onDisconnect(int i, String str) {
                Log.m281i("Disconnected from development server");
                Socket.this.connected = false;
                Socket.this.connecting = false;
                Socket.this.authSent = false;
            }

            public void onConnect() {
                if (!Socket.this.authSent) {
                    Log.m281i("Connected to development server");
                    String str = Params.APP_ID;
                    try {
                        Socket.this.sio.emit("auth", new JSONArray(Collections.singletonList(new JSONObject(Util.newMap(str, RequestOld.appId(), Params.DEVICE_ID, RequestOld.deviceId())))));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        Util.handleException(th);
                    }
                    Socket.this.authSent = true;
                    Socket.this.connected = true;
                    Socket.this.connecting = false;
                }
                Leanplum.countAggregator().incrementCount("connect_to_app_id");
            }

            /* renamed from: on */
            public void mo32798on(String str, JSONArray jSONArray) {
                char c = 65535;
                try {
                    switch (str.hashCode()) {
                        case -2075611462:
                            if (str.equals(Socket.EVENT_APPLY_VARS)) {
                                c = 7;
                                break;
                            }
                            break;
                        case -1059891784:
                            if (str.equals(Socket.EVENT_TRIGGER)) {
                                c = 1;
                                break;
                            }
                            break;
                        case -924224807:
                            if (str.equals("registerDevice")) {
                                c = 6;
                                break;
                            }
                            break;
                        case -420918074:
                            if (str.equals(Socket.EVENT_PREVIEW_UPDATE_RULES)) {
                                c = 3;
                                break;
                            }
                            break;
                        case -295879019:
                            if (str.equals(Socket.EVENT_UPDATE_VARS)) {
                                c = 0;
                                break;
                            }
                            break;
                        case -124943161:
                            if (str.equals(Socket.EVENT_GET_ACTIONS)) {
                                c = 5;
                                break;
                            }
                            break;
                        case -92082495:
                            if (str.equals(Socket.EVENT_GET_VARIABLES)) {
                                c = 4;
                                break;
                            }
                            break;
                        case 394550618:
                            if (str.equals(Socket.EVENT_GET_VIEW_HIERARCHY)) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            Leanplum.forceContentUpdate();
                            return;
                        case 1:
                            Socket.this.handleTriggerEvent(jSONArray);
                            return;
                        case 2:
                            LeanplumUIEditorWrapper.getInstance().startUpdating();
                            LeanplumUIEditorWrapper.getInstance().sendUpdate();
                            return;
                        case 3:
                            Socket.this.previewUpdateRules(jSONArray);
                            return;
                        case 4:
                            Socket.this.handleGetVariablesEvent();
                            return;
                        case 5:
                            Socket.this.handleGetActionsEvent();
                            return;
                        case 6:
                            Socket.this.handleRegisterDeviceEvent(jSONArray);
                            return;
                        case 7:
                            Socket.handleApplyVarsEvent(jSONArray);
                            return;
                        default:
                            return;
                    }
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }
        };
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://");
            sb.append(Constants.SOCKET_HOST);
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(Constants.SOCKET_PORT);
            this.sio = new SocketIOClient(new URI(sb.toString()), r0);
        } catch (URISyntaxException e) {
            Log.m280e(e.getMessage());
        }
        connect();
        new Timer().schedule(new TimerTask() {
            public void run() {
                try {
                    Socket.this.reconnect();
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }
        }, 0, 5000);
    }

    private void connect() {
        this.connecting = true;
        this.sio.connect();
    }

    /* access modifiers changed from: private */
    public void reconnect() {
        if (!this.connected && !this.connecting) {
            connect();
        }
    }

    public <T> void sendEvent(String str, Map<String, T> map) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Sending event: ");
            sb.append(str);
            sb.append(" & data over socket:\n");
            sb.append(map);
            Log.m282p(sb.toString());
            this.sio.emit(str, new JSONArray(Collections.singletonList(JsonConverter.mapToJsonObject(map))));
        } catch (JSONException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to create JSON data object: ");
            sb2.append(e.getMessage());
            Log.m280e(sb2.toString());
        }
        Leanplum.countAggregator().incrementCount("send_event_socket");
    }

    /* access modifiers changed from: 0000 */
    public void handleTriggerEvent(JSONArray jSONArray) {
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            JSONObject jSONObject2 = jSONObject.getJSONObject("action");
            if (jSONObject2 != null) {
                String string = jSONObject.getString(Params.MESSAGE_ID);
                boolean z = jSONObject.getBoolean("isRooted");
                String string2 = jSONObject2.getString(Values.ACTION_ARG);
                Map map = (Map) CollectionUtil.uncheckedCast(VarCache.actionDefinitions().get(string2));
                Map map2 = null;
                if (map != null) {
                    map2 = (Map) CollectionUtil.uncheckedCast(map.get("values"));
                }
                ActionContext actionContext = new ActionContext(string2, (Map) CollectionUtil.uncheckedCast(VarCache.mergeHelper(map2, JsonConverter.mapFromJson(jSONObject2))), string);
                actionContext.preventRealtimeUpdating();
                actionContext.setIsRooted(z);
                actionContext.setIsPreview(true);
                actionContext.update();
                LeanplumInternal.triggerAction(actionContext);
                Leanplum.triggerMessageDisplayed(actionContext);
            }
        } catch (JSONException e) {
            Log.m280e("Error getting action info", e);
        }
    }

    public void handleGetVariablesEvent() {
        boolean sendVariablesIfChanged = VarCache.sendVariablesIfChanged();
        VarCache.maybeUploadNewFiles();
        sendEvent(EVENT_CONTENT_RESPONSE, Util.newMap("updated", Boolean.valueOf(sendVariablesIfChanged), new Object[0]));
    }

    /* access modifiers changed from: 0000 */
    public void handleGetActionsEvent() {
        boolean sendActionsIfChanged = VarCache.sendActionsIfChanged();
        VarCache.maybeUploadNewFiles();
        sendEvent(EVENT_CONTENT_RESPONSE, Util.newMap("updated", Boolean.valueOf(sendActionsIfChanged), new Object[0]));
    }

    /* access modifiers changed from: 0000 */
    public void handleRegisterDeviceEvent(JSONArray jSONArray) {
        final String str;
        LeanplumInternal.onHasStartedAndRegisteredAsDeveloper();
        try {
            str = jSONArray.getJSONObject(0).getString("email");
        } catch (JSONException unused) {
            Log.m283v("Socket - No developer e-mail provided.");
            str = null;
        }
        if (str == null) {
            str = "a Leanplum account";
        }
        OsHandler.getInstance().post(new Runnable() {
            public void run() {
                LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                    public void variablesChanged() {
                        Builder builder = new Builder(LeanplumActivityHelper.getCurrentActivity());
                        builder.setTitle(Socket.TAG);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Your device is registered to ");
                        sb.append(str);
                        sb.append(".");
                        builder.setMessage(sb.toString());
                        builder.setPositiveButton("OK", new OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.show();
                    }
                });
            }
        });
    }

    static void handleApplyVarsEvent(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(0);
                if (jSONObject != null) {
                    VarCache.applyVariableDiffs(JsonConverter.mapFromJson(jSONObject), null, null, null, null, null, null);
                }
            } catch (JSONException e) {
                Log.m280e("Couldn't applyVars for preview.", e);
            } catch (Throwable th) {
                Util.handleException(th);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void previewUpdateRules(JSONArray jSONArray) {
        LeanplumEditorMode leanplumEditorMode;
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            if (!jSONObject.optBoolean("closed")) {
                LeanplumUIEditorWrapper.getInstance().startUpdating();
            } else {
                LeanplumUIEditorWrapper.getInstance().stopUpdating();
            }
            int optInt = jSONObject.optInt("mode");
            if (optInt >= LeanplumEditorMode.values().length) {
                Log.m282p("Invalid editor mode in packet");
                leanplumEditorMode = LeanplumEditorMode.LP_EDITOR_MODE_INTERFACE;
            } else {
                leanplumEditorMode = LeanplumEditorMode.values()[optInt];
            }
            LeanplumUIEditorWrapper.getInstance().setMode(leanplumEditorMode);
            JSONArray optJSONArray = jSONObject.optJSONArray("rules");
            if (optJSONArray != null) {
                VarCache.applyUpdateRuleDiffs(JsonConverter.listFromJson(optJSONArray));
            }
            LeanplumUIEditorWrapper.getInstance().sendUpdateDelayedDefault();
        } catch (Exception unused) {
            Log.m280e("Error parsing data");
        }
    }

    public boolean isConnected() {
        return this.connected;
    }
}
