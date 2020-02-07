package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class UserExtension implements Model {
    private static final String LOCALE = "locale";
    private static final String LOCAL_ID = "localId";
    private String localId;
    private String locale;

    public String getLocalId() {
        return this.localId;
    }

    public void setLocalId(String str) {
        this.localId = str;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void read(JSONObject jSONObject) {
        setLocalId(jSONObject.optString(LOCAL_ID, null));
        setLocale(jSONObject.optString("locale", null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, LOCAL_ID, getLocalId());
        JSONUtils.write(jSONStringer, "locale", getLocale());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserExtension userExtension = (UserExtension) obj;
        String str = this.localId;
        if (str == null ? userExtension.localId != null : !str.equals(userExtension.localId)) {
            return false;
        }
        String str2 = this.locale;
        if (str2 != null) {
            z = str2.equals(userExtension.locale);
        } else if (userExtension.locale != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.localId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.locale;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
