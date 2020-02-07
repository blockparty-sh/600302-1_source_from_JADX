package com.microsoft.appcenter.ingestion;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.http.HttpClient.CallTemplate;
import com.microsoft.appcenter.http.HttpUtils;
import com.microsoft.appcenter.http.ServiceCall;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.TicketCache;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class OneCollectorIngestion implements Ingestion {
    @VisibleForTesting
    static final String API_KEY = "apikey";
    private static final String CLIENT_VERSION_FORMAT = "ACS-Android-Java-no-%s-no";
    @VisibleForTesting
    static final String CLIENT_VERSION_KEY = "Client-Version";
    private static final String CONTENT_TYPE_VALUE = "application/x-json-stream; charset=utf-8";
    private static final String DEFAULT_LOG_URL = "https://mobile.events.data.microsoft.com/OneCollector/1.0";
    @VisibleForTesting
    static final String STRICT = "Strict";
    @VisibleForTesting
    static final String TICKETS = "Tickets";
    @VisibleForTesting
    static final String UPLOAD_TIME_KEY = "Upload-Time";
    private final HttpClient mHttpClient;
    private final LogSerializer mLogSerializer;
    private String mLogUrl = DEFAULT_LOG_URL;

    private static class IngestionCallTemplate implements CallTemplate {
        private final LogContainer mLogContainer;
        private final LogSerializer mLogSerializer;

        IngestionCallTemplate(LogSerializer logSerializer, LogContainer logContainer) {
            this.mLogSerializer = logSerializer;
            this.mLogContainer = logContainer;
        }

        public String buildRequestBody() throws JSONException {
            StringBuilder sb = new StringBuilder();
            for (Log serializeLog : this.mLogContainer.getLogs()) {
                sb.append(this.mLogSerializer.serializeLog(serializeLog));
                sb.append(10);
            }
            return sb.toString();
        }

        public void onBeforeCalling(URL url, Map<String, String> map) {
            if (AppCenterLog.getLogLevel() <= 2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Calling ");
                sb.append(url);
                sb.append("...");
                String sb2 = sb.toString();
                String str = "AppCenter";
                AppCenterLog.verbose(str, sb2);
                HashMap hashMap = new HashMap(map);
                String str2 = OneCollectorIngestion.API_KEY;
                String str3 = (String) hashMap.get(str2);
                if (str3 != null) {
                    hashMap.put(str2, HttpUtils.hideApiKeys(str3));
                }
                String str4 = OneCollectorIngestion.TICKETS;
                String str5 = (String) hashMap.get(str4);
                if (str5 != null) {
                    hashMap.put(str4, HttpUtils.hideTickets(str5));
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Headers: ");
                sb3.append(hashMap);
                AppCenterLog.verbose(str, sb3.toString());
            }
        }
    }

    public OneCollectorIngestion(@NonNull Context context, @NonNull LogSerializer logSerializer) {
        this.mLogSerializer = logSerializer;
        this.mHttpClient = HttpUtils.createHttpClient(context);
    }

    public ServiceCall sendAsync(String str, String str2, UUID uuid, LogContainer logContainer, ServiceCallback serviceCallback) throws IllegalArgumentException {
        HashMap hashMap = new HashMap();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        for (Log transmissionTargetTokens : logContainer.getLogs()) {
            linkedHashSet.addAll(transmissionTargetTokens.getTransmissionTargetTokens());
        }
        StringBuilder sb = new StringBuilder();
        for (String append : linkedHashSet) {
            sb.append(append);
            sb.append(",");
        }
        if (!linkedHashSet.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        hashMap.put(API_KEY, sb.toString());
        JSONObject jSONObject = new JSONObject();
        for (Log log : logContainer.getLogs()) {
            List<String> ticketKeys = ((CommonSchemaLog) log).getExt().getProtocol().getTicketKeys();
            if (ticketKeys != null) {
                for (String str3 : ticketKeys) {
                    String ticket = TicketCache.getTicket(str3);
                    if (ticket != null) {
                        try {
                            jSONObject.put(str3, ticket);
                        } catch (JSONException e) {
                            AppCenterLog.error("AppCenter", "Cannot serialize tickets, sending log anonymously", e);
                        }
                    }
                }
            }
        }
        if (jSONObject.length() > 0) {
            hashMap.put(TICKETS, jSONObject.toString());
            if (Constants.APPLICATION_DEBUGGABLE) {
                hashMap.put(STRICT, Boolean.TRUE.toString());
            }
        }
        hashMap.put("Content-Type", CONTENT_TYPE_VALUE);
        hashMap.put(CLIENT_VERSION_KEY, String.format(CLIENT_VERSION_FORMAT, new Object[]{"2.5.0"}));
        hashMap.put(UPLOAD_TIME_KEY, String.valueOf(System.currentTimeMillis()));
        return this.mHttpClient.callAsync(this.mLogUrl, "POST", hashMap, new IngestionCallTemplate(this.mLogSerializer, logContainer), serviceCallback);
    }

    public void setLogUrl(@NonNull String str) {
        this.mLogUrl = str;
    }

    public void reopen() {
        this.mHttpClient.reopen();
    }

    public void close() throws IOException {
        this.mHttpClient.close();
    }
}
