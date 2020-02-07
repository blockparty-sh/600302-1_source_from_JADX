package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class AppExtension implements Model {

    /* renamed from: ID */
    private static final String f658ID = "id";
    private static final String LOCALE = "locale";
    private static final String NAME = "name";
    private static final String USER_ID = "userId";
    private static final String VER = "ver";

    /* renamed from: id */
    private String f659id;
    private String locale;
    private String name;
    private String userId;
    private String ver;

    public String getId() {
        return this.f659id;
    }

    public void setId(String str) {
        this.f659id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getVer() {
        return this.ver;
    }

    public void setVer(String str) {
        this.ver = str;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void read(JSONObject jSONObject) {
        setId(jSONObject.optString("id", null));
        setVer(jSONObject.optString(VER, null));
        setName(jSONObject.optString("name", null));
        setLocale(jSONObject.optString("locale", null));
        setUserId(jSONObject.optString("userId", null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, "id", getId());
        JSONUtils.write(jSONStringer, VER, getVer());
        JSONUtils.write(jSONStringer, "name", getName());
        JSONUtils.write(jSONStringer, "locale", getLocale());
        JSONUtils.write(jSONStringer, "userId", getUserId());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppExtension appExtension = (AppExtension) obj;
        String str = this.f659id;
        if (str == null ? appExtension.f659id != null : !str.equals(appExtension.f659id)) {
            return false;
        }
        String str2 = this.ver;
        if (str2 == null ? appExtension.ver != null : !str2.equals(appExtension.ver)) {
            return false;
        }
        String str3 = this.name;
        if (str3 == null ? appExtension.name != null : !str3.equals(appExtension.name)) {
            return false;
        }
        String str4 = this.locale;
        if (str4 == null ? appExtension.locale != null : !str4.equals(appExtension.locale)) {
            return false;
        }
        String str5 = this.userId;
        if (str5 != null) {
            z = str5.equals(appExtension.userId);
        } else if (appExtension.userId != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.f659id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.ver;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.locale;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.userId;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }
}
