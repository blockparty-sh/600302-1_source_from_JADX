package com.leanplum.internal;

import android.os.Looper;
import com.leanplum.Leanplum;
import com.leanplum.core.BuildConfig;
import com.microsoft.appcenter.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SocketIOClient {
    /* access modifiers changed from: private */
    public WebSocketClient mClient;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public int mHeartbeat;
    /* access modifiers changed from: private */
    public android.os.Handler mSendHandler;
    /* access modifiers changed from: private */
    public Looper mSendLooper;
    /* access modifiers changed from: private */
    public String mSession;
    /* access modifiers changed from: private */
    public String mURL;

    interface Handler {
        /* renamed from: on */
        void mo32798on(String str, JSONArray jSONArray);

        void onConnect();

        void onDisconnect(int i, String str);

        void onError(Exception exc);
    }

    public SocketIOClient(URI uri, Handler handler) {
        StringBuilder sb = new StringBuilder();
        sb.append(uri.toString().replaceAll("/$", ""));
        sb.append("/socket.io/1/");
        this.mURL = sb.toString();
        this.mHandler = handler;
    }

    private static String userAgentString() {
        String str;
        String str2 = "/";
        if (Leanplum.getContext() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(Util.getApplicationName(Leanplum.getContext()));
            sb.append(str2);
            sb.append(Util.getVersionName());
            str = sb.toString();
        } else {
            str = "websocket";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("(");
        sb2.append(RequestOld.appId());
        String str3 = "; ";
        sb2.append(str3);
        sb2.append(Constants.CLIENT);
        sb2.append(str3);
        sb2.append(Constants.LEANPLUM_VERSION);
        sb2.append(str2);
        sb2.append(BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER);
        sb2.append(")");
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public static String downloadUriAsString(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }

    private static byte[] readToEndAsArray(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = dataInputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private static String readToEnd(InputStream inputStream) throws IOException {
        return new String(readToEndAsArray(inputStream));
    }

    public void emit(String str, JSONArray jSONArray) throws JSONException {
        final JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        jSONObject.put("args", jSONArray);
        this.mSendHandler.post(new Runnable() {
            public void run() {
                SocketIOClient.this.mClient.send(String.format("5:::%s", new Object[]{jSONObject.toString()}));
            }
        });
    }

    /* access modifiers changed from: private */
    public void connectSession() throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mURL);
        sb.append("websocket/");
        sb.append(this.mSession);
        this.mClient = new WebSocketClient(new URI(sb.toString()), new Listener() {
            public void onMessage(byte[] bArr) {
                SocketIOClient.this.cleanup();
                SocketIOClient.this.mHandler.onError(new Exception("Unexpected binary data"));
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
            /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
                r1 = new org.json.JSONArray();
             */
            /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onMessage(java.lang.String r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = ":"
                    r1 = 4
                    java.lang.String[] r0 = r5.split(r0, r1)     // Catch:{ Exception -> 0x0075 }
                    r1 = 0
                    r1 = r0[r1]     // Catch:{ Exception -> 0x0075 }
                    int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0075 }
                    switch(r1) {
                        case 1: goto L_0x006b;
                        case 2: goto L_0x005f;
                        case 3: goto L_0x0057;
                        case 4: goto L_0x0057;
                        case 5: goto L_0x001a;
                        case 6: goto L_0x007e;
                        case 7: goto L_0x0014;
                        case 8: goto L_0x007e;
                        default: goto L_0x0011;
                    }     // Catch:{ Exception -> 0x0075 }
                L_0x0011:
                    java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Exception -> 0x0075 }
                    goto L_0x006f
                L_0x0014:
                    java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Exception -> 0x0075 }
                    r0.<init>(r5)     // Catch:{ Exception -> 0x0075 }
                    throw r0     // Catch:{ Exception -> 0x0075 }
                L_0x001a:
                    r5 = 1
                    r5 = r0[r5]     // Catch:{ Exception -> 0x0075 }
                    r1 = 3
                    r0 = r0[r1]     // Catch:{ Exception -> 0x0075 }
                    org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0075 }
                    r1.<init>(r0)     // Catch:{ Exception -> 0x0075 }
                    java.lang.String r0 = "name"
                    java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x0075 }
                    java.lang.String r2 = "args"
                    org.json.JSONArray r1 = r1.getJSONArray(r2)     // Catch:{ JSONException -> 0x0032 }
                    goto L_0x0037
                L_0x0032:
                    org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x0075 }
                    r1.<init>()     // Catch:{ Exception -> 0x0075 }
                L_0x0037:
                    java.lang.String r2 = ""
                    boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x0075 }
                    if (r2 != 0) goto L_0x004d
                    com.leanplum.internal.SocketIOClient r2 = com.leanplum.internal.SocketIOClient.this     // Catch:{ Exception -> 0x0075 }
                    android.os.Handler r2 = r2.mSendHandler     // Catch:{ Exception -> 0x0075 }
                    com.leanplum.internal.SocketIOClient$2$1 r3 = new com.leanplum.internal.SocketIOClient$2$1     // Catch:{ Exception -> 0x0075 }
                    r3.<init>(r5)     // Catch:{ Exception -> 0x0075 }
                    r2.post(r3)     // Catch:{ Exception -> 0x0075 }
                L_0x004d:
                    com.leanplum.internal.SocketIOClient r5 = com.leanplum.internal.SocketIOClient.this     // Catch:{ Exception -> 0x0075 }
                    com.leanplum.internal.SocketIOClient$Handler r5 = r5.mHandler     // Catch:{ Exception -> 0x0075 }
                    r5.mo32798on(r0, r1)     // Catch:{ Exception -> 0x0075 }
                    goto L_0x007e
                L_0x0057:
                    java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Exception -> 0x0075 }
                    java.lang.String r0 = "message type not supported"
                    r5.<init>(r0)     // Catch:{ Exception -> 0x0075 }
                    throw r5     // Catch:{ Exception -> 0x0075 }
                L_0x005f:
                    com.leanplum.internal.SocketIOClient r5 = com.leanplum.internal.SocketIOClient.this     // Catch:{ Exception -> 0x0075 }
                    com.leanplum.internal.WebSocketClient r5 = r5.mClient     // Catch:{ Exception -> 0x0075 }
                    java.lang.String r0 = "2::"
                    r5.send(r0)     // Catch:{ Exception -> 0x0075 }
                    goto L_0x007e
                L_0x006b:
                    r4.onConnect()     // Catch:{ Exception -> 0x0075 }
                    goto L_0x007e
                L_0x006f:
                    java.lang.String r0 = "unknown code"
                    r5.<init>(r0)     // Catch:{ Exception -> 0x0075 }
                    throw r5     // Catch:{ Exception -> 0x0075 }
                L_0x0075:
                    r5 = move-exception
                    com.leanplum.internal.SocketIOClient r0 = com.leanplum.internal.SocketIOClient.this
                    r0.cleanup()
                    r4.onError(r5)
                L_0x007e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.SocketIOClient.C24122.onMessage(java.lang.String):void");
            }

            public void onError(Exception exc) {
                SocketIOClient.this.cleanup();
                SocketIOClient.this.mHandler.onError(exc);
            }

            public void onDisconnect(int i, String str) {
                SocketIOClient.this.cleanup();
                SocketIOClient.this.mHandler.onDisconnect(i, str);
            }

            public void onConnect() {
                SocketIOClient.this.mSendHandler.postDelayed(new Runnable() {
                    public void run() {
                        SocketIOClient.this.mSendHandler.postDelayed(this, (long) SocketIOClient.this.mHeartbeat);
                        SocketIOClient.this.mClient.send("2:::");
                    }
                }, (long) SocketIOClient.this.mHeartbeat);
                SocketIOClient.this.mHandler.onConnect();
            }
        }, null);
        this.mClient.connect();
    }

    public void disconnect() throws IOException {
        cleanup();
    }

    /* access modifiers changed from: private */
    public void cleanup() {
        WebSocketClient webSocketClient = this.mClient;
        if (webSocketClient != null) {
            webSocketClient.disconnect();
            this.mClient = null;
        }
        Looper looper = this.mSendLooper;
        if (looper != null) {
            looper.quit();
        }
        this.mSendLooper = null;
        this.mSendHandler = null;
    }

    public void connect() {
        if (this.mClient == null) {
            new Thread() {
                public void run() {
                    try {
                        String[] split = SocketIOClient.downloadUriAsString(SocketIOClient.this.mURL).split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
                        SocketIOClient.this.mSession = split[0];
                        String str = split[1];
                        if (!"".equals(str)) {
                            SocketIOClient.this.mHeartbeat = (Integer.parseInt(str) / 2) * 1000;
                        }
                        if (new HashSet(Arrays.asList(split[3].split(","))).contains("websocket")) {
                            Looper.prepare();
                            SocketIOClient.this.mSendLooper = Looper.myLooper();
                            SocketIOClient.this.mSendHandler = new android.os.Handler();
                            SocketIOClient.this.connectSession();
                            Looper.loop();
                            return;
                        }
                        throw new Exception("websocket not supported");
                    } catch (Exception e) {
                        SocketIOClient.this.mHandler.onError(e);
                    }
                }
            }.start();
        }
    }
}
