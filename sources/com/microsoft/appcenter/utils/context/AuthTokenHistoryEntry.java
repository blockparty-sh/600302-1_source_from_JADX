package com.microsoft.appcenter.utils.context;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

final class AuthTokenHistoryEntry implements Model {
    private static final String AUTH_TOKEN = "authToken";
    private static final String EXPIRES_ON = "expiresOn";
    private static final String HOME_ACCOUNT_ID = "homeAccountId";
    private static final String TIME = "time";
    private String mAuthToken;
    private Date mExpiresOn;
    private String mHomeAccountId;
    private Date mTime;

    AuthTokenHistoryEntry() {
    }

    AuthTokenHistoryEntry(String str, String str2, Date date, Date date2) {
        this.mAuthToken = str;
        this.mHomeAccountId = str2;
        this.mTime = date;
        this.mExpiresOn = date2;
    }

    public String getAuthToken() {
        return this.mAuthToken;
    }

    private void setAuthToken(String str) {
        this.mAuthToken = str;
    }

    /* access modifiers changed from: 0000 */
    public String getHomeAccountId() {
        return this.mHomeAccountId;
    }

    private void setHomeAccountId(String str) {
        this.mHomeAccountId = str;
    }

    public Date getTime() {
        return this.mTime;
    }

    private void setTime(Date date) {
        this.mTime = date;
    }

    /* access modifiers changed from: 0000 */
    public Date getExpiresOn() {
        return this.mExpiresOn;
    }

    private void setExpiresOn(Date date) {
        this.mExpiresOn = date;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        Date date = null;
        setAuthToken(jSONObject.optString(AUTH_TOKEN, null));
        setHomeAccountId(jSONObject.optString(HOME_ACCOUNT_ID, null));
        String optString = jSONObject.optString("time", null);
        setTime(optString != null ? JSONDateUtils.toDate(optString) : null);
        String optString2 = jSONObject.optString(EXPIRES_ON, null);
        if (optString2 != null) {
            date = JSONDateUtils.toDate(optString2);
        }
        setExpiresOn(date);
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, AUTH_TOKEN, getAuthToken());
        JSONUtils.write(jSONStringer, HOME_ACCOUNT_ID, getHomeAccountId());
        Date time = getTime();
        String str = null;
        JSONUtils.write(jSONStringer, "time", time != null ? JSONDateUtils.toString(time) : null);
        Date expiresOn = getExpiresOn();
        if (expiresOn != null) {
            str = JSONDateUtils.toString(expiresOn);
        }
        JSONUtils.write(jSONStringer, EXPIRES_ON, str);
    }
}
