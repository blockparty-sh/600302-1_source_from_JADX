package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class OsExtension implements Model {
    private static final String NAME = "name";
    private static final String VER = "ver";
    private String name;
    private String ver;

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

    public void read(JSONObject jSONObject) {
        setName(jSONObject.optString("name", null));
        setVer(jSONObject.optString(VER, null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, "name", getName());
        JSONUtils.write(jSONStringer, VER, getVer());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OsExtension osExtension = (OsExtension) obj;
        String str = this.name;
        if (str == null ? osExtension.name != null : !str.equals(osExtension.name)) {
            return false;
        }
        String str2 = this.ver;
        if (str2 != null) {
            z = str2.equals(osExtension.ver);
        } else if (osExtension.ver != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.ver;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
