package com.microsoft.appcenter.ingestion.models.properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class StringTypedProperty extends TypedProperty {
    public static final String TYPE = "string";
    private String value;

    public String getType() {
        return "string";
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setValue(jSONObject.getString("value"));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        jSONStringer.key("value").value(getValue());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        StringTypedProperty stringTypedProperty = (StringTypedProperty) obj;
        String str = this.value;
        if (str != null) {
            z = str.equals(stringTypedProperty.value);
        } else if (stringTypedProperty.value != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.value;
        return hashCode + (str != null ? str.hashCode() : 0);
    }
}
