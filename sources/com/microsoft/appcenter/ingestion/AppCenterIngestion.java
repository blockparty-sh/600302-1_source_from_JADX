package com.microsoft.appcenter.ingestion;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.AbstractAppCallTemplate;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.http.HttpUtils;
import com.microsoft.appcenter.http.ServiceCall;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONException;

public class AppCenterIngestion implements Ingestion {
    @VisibleForTesting
    static final String API_PATH = "/logs?api-version=1.0.0";
    public static final String DEFAULT_LOG_URL = "https://in.appcenter.ms";
    @VisibleForTesting
    static final String INSTALL_ID = "Install-ID";
    private final HttpClient mHttpClient;
    private final LogSerializer mLogSerializer;
    private String mLogUrl = DEFAULT_LOG_URL;

    private static class IngestionCallTemplate extends AbstractAppCallTemplate {
        private final LogContainer mLogContainer;
        private final LogSerializer mLogSerializer;

        IngestionCallTemplate(LogSerializer logSerializer, LogContainer logContainer) {
            this.mLogSerializer = logSerializer;
            this.mLogContainer = logContainer;
        }

        public String buildRequestBody() throws JSONException {
            return this.mLogSerializer.serializeContainer(this.mLogContainer);
        }
    }

    public AppCenterIngestion(@NonNull Context context, @NonNull LogSerializer logSerializer) {
        this.mLogSerializer = logSerializer;
        this.mHttpClient = HttpUtils.createHttpClient(context);
    }

    public void setLogUrl(@NonNull String str) {
        this.mLogUrl = str;
    }

    public ServiceCall sendAsync(String str, String str2, UUID uuid, LogContainer logContainer, ServiceCallback serviceCallback) throws IllegalArgumentException {
        HashMap hashMap = new HashMap();
        hashMap.put(INSTALL_ID, uuid.toString());
        hashMap.put(Constants.APP_SECRET, str2);
        if (str != null) {
            hashMap.put("Authorization", String.format(Constants.AUTH_TOKEN_FORMAT, new Object[]{str}));
        }
        IngestionCallTemplate ingestionCallTemplate = new IngestionCallTemplate(this.mLogSerializer, logContainer);
        HttpClient httpClient = this.mHttpClient;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mLogUrl);
        sb.append(API_PATH);
        return httpClient.callAsync(sb.toString(), "POST", hashMap, ingestionCallTemplate, serviceCallback);
    }

    public void close() throws IOException {
        this.mHttpClient.close();
    }

    public void reopen() {
        this.mHttpClient.reopen();
    }
}
