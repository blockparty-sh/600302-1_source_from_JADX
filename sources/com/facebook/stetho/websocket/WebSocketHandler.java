package com.facebook.stetho.websocket;

import android.util.Base64;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.server.SocketLike;
import com.facebook.stetho.server.http.HttpHandler;
import com.facebook.stetho.server.http.HttpStatus;
import com.facebook.stetho.server.http.LightHttpBody;
import com.facebook.stetho.server.http.LightHttpMessage;
import com.facebook.stetho.server.http.LightHttpRequest;
import com.facebook.stetho.server.http.LightHttpResponse;
import com.facebook.stetho.server.http.LightHttpServer;
import com.facebook.stetho.server.http.LightHttpServer.HttpMessageWriter;
import com.google.common.net.HttpHeaders;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;

public class WebSocketHandler implements HttpHandler {
    private static final String HEADER_CONNECTION = "Connection";
    private static final String HEADER_CONNECTION_UPGRADE = "Upgrade";
    private static final String HEADER_SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";
    private static final String HEADER_SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
    private static final String HEADER_SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    private static final String HEADER_SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
    private static final String HEADER_SEC_WEBSOCKET_VERSION_13 = "13";
    private static final String HEADER_UPGRADE = "Upgrade";
    private static final String HEADER_UPGRADE_WEBSOCKET = "websocket";
    private static final String SERVER_KEY_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private final SimpleEndpoint mEndpoint;

    public WebSocketHandler(SimpleEndpoint simpleEndpoint) {
        this.mEndpoint = simpleEndpoint;
    }

    public boolean handleRequest(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) throws IOException {
        if (!isSupportableUpgradeRequest(lightHttpRequest)) {
            lightHttpResponse.code = HttpStatus.HTTP_NOT_IMPLEMENTED;
            lightHttpResponse.reasonPhrase = "Not Implemented";
            lightHttpResponse.body = LightHttpBody.create("Not a supported WebSocket upgrade request\n", ErrorAttachmentLog.CONTENT_TYPE_TEXT_PLAIN);
            return true;
        }
        doUpgrade(socketLike, lightHttpRequest, lightHttpResponse);
        return false;
    }

    private static boolean isSupportableUpgradeRequest(LightHttpRequest lightHttpRequest) {
        String str = HttpHeaders.UPGRADE;
        if (HEADER_UPGRADE_WEBSOCKET.equalsIgnoreCase(getFirstHeaderValue(lightHttpRequest, str)) && str.equals(getFirstHeaderValue(lightHttpRequest, "Connection"))) {
            if (HEADER_SEC_WEBSOCKET_VERSION_13.equals(getFirstHeaderValue(lightHttpRequest, HEADER_SEC_WEBSOCKET_VERSION))) {
                return true;
            }
        }
        return false;
    }

    private void doUpgrade(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) throws IOException {
        lightHttpResponse.code = 101;
        lightHttpResponse.reasonPhrase = "Switching Protocols";
        String str = HttpHeaders.UPGRADE;
        lightHttpResponse.addHeader(str, HEADER_UPGRADE_WEBSOCKET);
        lightHttpResponse.addHeader("Connection", str);
        lightHttpResponse.body = null;
        String firstHeaderValue = getFirstHeaderValue(lightHttpRequest, HEADER_SEC_WEBSOCKET_KEY);
        if (firstHeaderValue != null) {
            lightHttpResponse.addHeader(HEADER_SEC_WEBSOCKET_ACCEPT, generateServerKey(firstHeaderValue));
        }
        InputStream input = socketLike.getInput();
        OutputStream output = socketLike.getOutput();
        LightHttpServer.writeResponseMessage(lightHttpResponse, new HttpMessageWriter(new BufferedOutputStream(output)));
        new WebSocketSession(input, output, this.mEndpoint).handle();
    }

    private static String generateServerKey(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(SERVER_KEY_GUID);
            String sb2 = sb.toString();
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(Utf8Charset.encodeUTF8(sb2));
            return Base64.encodeToString(instance.digest(), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    private static String getFirstHeaderValue(LightHttpMessage lightHttpMessage, String str) {
        return lightHttpMessage.getFirstHeaderValue(str);
    }
}
