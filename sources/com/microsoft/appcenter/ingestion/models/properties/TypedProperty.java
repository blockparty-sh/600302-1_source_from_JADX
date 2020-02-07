package com.microsoft.appcenter.ingestion.models.properties;

import com.microsoft.appcenter.ingestion.models.Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class TypedProperty implements Model {
    private String name;

    public abstract String getType();

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getString("type").equals(getType())) {
            setName(jSONObject.getString("name"));
            return;
        }
        throw new JSONException("Invalid type");
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        jSONStringer.key("type").value(getType());
        jSONStringer.key("name").value(getName());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypedProperty typedProperty = (TypedProperty) obj;
        String str = this.name;
        if (str != null) {
            z = str.equals(typedProperty.name);
        } else if (typedProperty.name != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.name;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
