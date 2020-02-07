package com.leanplum.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Base64;
import androidx.core.p003os.EnvironmentCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.BasicNameValuePair;
import p015io.grpc.internal.GrpcUtil;

class WebSocketClient {
    private static final String TAG = "WebSocketClient";
    private static TrustManager[] sTrustManagers;
    /* access modifiers changed from: private */
    public List<BasicNameValuePair> mExtraHeaders;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    /* access modifiers changed from: private */
    public Listener mListener;
    /* access modifiers changed from: private */
    public HybiParser mParser;
    /* access modifiers changed from: private */
    public final Object mSendLock = new Object();
    /* access modifiers changed from: private */
    public Socket mSocket;
    private Thread mThread;
    /* access modifiers changed from: private */
    public URI mURI;

    interface Listener {
        void onConnect();

        void onDisconnect(int i, String str);

        void onError(Exception exc);

        void onMessage(String str);

        void onMessage(byte[] bArr);
    }

    public static void setTrustManagers(TrustManager[] trustManagerArr) {
        sTrustManagers = trustManagerArr;
    }

    public WebSocketClient(URI uri, Listener listener, List<BasicNameValuePair> list) {
        this.mURI = uri;
        this.mListener = listener;
        this.mExtraHeaders = list;
        this.mParser = new HybiParser(this);
        this.mHandlerThread = new HandlerThread("websocket-thread");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    public Listener getListener() {
        return this.mListener;
    }

    public void connect() {
        Thread thread = this.mThread;
        if (thread == null || !thread.isAlive()) {
            this.mThread = new Thread(new Runnable() {
                public void run() {
                    String str = "\r\n";
                    try {
                        String str2 = "wss";
                        int i = WebSocketClient.this.mURI.getPort() != -1 ? WebSocketClient.this.mURI.getPort() : WebSocketClient.this.mURI.getScheme().equals(str2) ? GrpcUtil.DEFAULT_PORT_SSL : 80;
                        String path = TextUtils.isEmpty(WebSocketClient.this.mURI.getPath()) ? "/" : WebSocketClient.this.mURI.getPath();
                        if (!TextUtils.isEmpty(WebSocketClient.this.mURI.getQuery())) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(path);
                            sb.append("?");
                            sb.append(WebSocketClient.this.mURI.getQuery());
                            path = sb.toString();
                        }
                        String str3 = WebSocketClient.this.mURI.getScheme().equals(str2) ? "https" : "http";
                        URI uri = null;
                        try {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("//");
                            sb2.append(WebSocketClient.this.mURI.getHost());
                            uri = new URI(str3, sb2.toString(), null);
                        } catch (URISyntaxException e) {
                            Util.handleException(e);
                        }
                        try {
                            try {
                                WebSocketClient.this.mSocket = (WebSocketClient.this.mURI.getScheme().equals(str2) ? WebSocketClient.this.getSSLSocketFactory() : SocketFactory.getDefault()).createSocket(WebSocketClient.this.mURI.getHost(), i);
                            } catch (IOException e2) {
                                Util.handleException(e2);
                            }
                            PrintWriter printWriter = new PrintWriter(WebSocketClient.this.mSocket.getOutputStream());
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("GET ");
                            sb3.append(path);
                            sb3.append(" HTTP/1.1\r\n");
                            printWriter.print(sb3.toString());
                            printWriter.print("Upgrade: websocket\r\n");
                            printWriter.print("Connection: Upgrade\r\n");
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("Host: ");
                            sb4.append(WebSocketClient.this.mURI.getHost());
                            sb4.append(str);
                            printWriter.print(sb4.toString());
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Origin: ");
                            sb5.append(uri != null ? uri.toString() : EnvironmentCompat.MEDIA_UNKNOWN);
                            sb5.append(str);
                            printWriter.print(sb5.toString());
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append("Sec-WebSocket-Key: ");
                            sb6.append(WebSocketClient.this.createSecret());
                            sb6.append(str);
                            printWriter.print(sb6.toString());
                            printWriter.print("Sec-WebSocket-Version: 13\r\n");
                            if (WebSocketClient.this.mExtraHeaders != null) {
                                for (NameValuePair nameValuePair : WebSocketClient.this.mExtraHeaders) {
                                    printWriter.print(String.format("%s: %s\r\n", new Object[]{nameValuePair.getName(), nameValuePair.getValue()}));
                                }
                            }
                            printWriter.print(str);
                            printWriter.flush();
                            HappyDataInputStream happyDataInputStream = new HappyDataInputStream(WebSocketClient.this.mSocket.getInputStream());
                            StatusLine access$600 = WebSocketClient.this.parseStatusLine(WebSocketClient.this.readLine(happyDataInputStream));
                            if (access$600 == null) {
                                throw new HttpException("Received no reply from server.");
                            } else if (access$600.getStatusCode() == 101) {
                                while (true) {
                                    String access$500 = WebSocketClient.this.readLine(happyDataInputStream);
                                    if (TextUtils.isEmpty(access$500)) {
                                        break;
                                    }
                                    WebSocketClient.this.parseHeader(access$500).getName().equals("Sec-WebSocket-Accept");
                                }
                                WebSocketClient.this.mListener.onConnect();
                                WebSocketClient.this.mParser.start(happyDataInputStream);
                            } else {
                                throw new HttpResponseException(access$600.getStatusCode(), access$600.getReasonPhrase());
                            }
                        } catch (GeneralSecurityException e3) {
                            Util.handleException(e3);
                        }
                    } catch (EOFException e4) {
                        Log.m279d("WebSocket EOF!", e4);
                        WebSocketClient.this.mListener.onDisconnect(0, "EOF");
                    } catch (SSLException e5) {
                        Log.m279d("Websocket SSL error!", e5);
                        WebSocketClient.this.mListener.onDisconnect(0, "SSL");
                    } catch (Exception e6) {
                        WebSocketClient.this.mListener.onError(e6);
                    }
                }
            });
            this.mThread.start();
        }
    }

    public void disconnect() {
        if (this.mSocket != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    try {
                        if (WebSocketClient.this.mSocket != null) {
                            WebSocketClient.this.mSocket.close();
                            WebSocketClient.this.mSocket = null;
                        }
                    } catch (IOException e) {
                        Log.m279d("Error while disconnecting", e);
                        WebSocketClient.this.mListener.onError(e);
                    }
                }
            });
        }
    }

    public void send(String str) {
        sendFrame(this.mParser.frame(str));
    }

    public void send(byte[] bArr) {
        sendFrame(this.mParser.frame(bArr));
    }

    /* access modifiers changed from: private */
    public StatusLine parseStatusLine(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return BasicLineParser.parseStatusLine(str, new BasicLineParser());
    }

    /* access modifiers changed from: private */
    public Header parseHeader(String str) {
        return BasicLineParser.parseHeader(str, new BasicLineParser());
    }

    /* access modifiers changed from: private */
    public String readLine(HappyDataInputStream happyDataInputStream) throws IOException {
        int read = happyDataInputStream.read();
        if (read == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        while (read != 10) {
            if (read != 13) {
                sb.append((char) read);
            }
            read = happyDataInputStream.read();
            if (read == -1) {
                return null;
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public String createSecret() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) ((int) (Math.random() * 256.0d));
        }
        return Base64.encodeToString(bArr, 0).trim();
    }

    /* access modifiers changed from: 0000 */
    public void sendFrame(final byte[] bArr) {
        this.mHandler.post(new Runnable() {
            public void run() {
                try {
                    synchronized (WebSocketClient.this.mSendLock) {
                        if (WebSocketClient.this.mSocket != null) {
                            OutputStream outputStream = WebSocketClient.this.mSocket.getOutputStream();
                            outputStream.write(bArr);
                            outputStream.flush();
                        }
                    }
                } catch (IOException e) {
                    WebSocketClient.this.mListener.onError(e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, sTrustManagers, null);
        return instance.getSocketFactory();
    }
}
