package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class LocExtension implements Model {

    /* renamed from: TZ */
    private static final String f664TZ = "tz";

    /* renamed from: tz */
    private String f665tz;

    public String getTz() {
        return this.f665tz;
    }

    public void setTz(String str) {
        this.f665tz = str;
    }

    public void read(JSONObject jSONObject) {
        setTz(jSONObject.optString(f664TZ, null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, f664TZ, getTz());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocExtension locExtension = (LocExtension) obj;
        String str = this.f665tz;
        if (str != null) {
            z = str.equals(locExtension.f665tz);
        } else if (locExtension.f665tz != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.f665tz;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
