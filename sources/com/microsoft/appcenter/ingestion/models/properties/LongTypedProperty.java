package com.microsoft.appcenter.ingestion.models.properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class LongTypedProperty extends TypedProperty {
    public static final String TYPE = "long";
    private long value;

    public String getType() {
        return TYPE;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long j) {
        this.value = j;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setValue(jSONObject.getLong("value"));
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
        if (this.value != ((LongTypedProperty) obj).value) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        long j = this.value;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }
}
