package com.microsoft.appcenter.analytics.ingestion.models;

import com.microsoft.appcenter.ingestion.models.LogWithProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class LogWithNameAndProperties extends LogWithProperties {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setName(jSONObject.getString("name"));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        jSONStringer.key("name").value(getName());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        LogWithNameAndProperties logWithNameAndProperties = (LogWithNameAndProperties) obj;
        String str = this.name;
        if (str != null) {
            z = str.equals(logWithNameAndProperties.name);
        } else if (logWithNameAndProperties.name != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.name;
        return hashCode + (str != null ? str.hashCode() : 0);
    }
}
