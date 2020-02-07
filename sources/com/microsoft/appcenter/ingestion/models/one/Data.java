package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Data implements Model {
    static final String BASE_DATA = "baseData";
    static final String BASE_TYPE = "baseType";
    private final JSONObject mProperties = new JSONObject();

    public JSONObject getProperties() {
        return this.mProperties;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        JSONArray names = jSONObject.names();
        if (names != null) {
            for (int i = 0; i < names.length(); i++) {
                String string = names.getString(i);
                this.mProperties.put(string, jSONObject.get(string));
            }
        }
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONObject jSONObject = this.mProperties;
        String str = BASE_TYPE;
        JSONUtils.write(jSONStringer, str, jSONObject.optString(str, null));
        JSONObject jSONObject2 = this.mProperties;
        String str2 = BASE_DATA;
        JSONUtils.write(jSONStringer, str2, jSONObject2.optJSONObject(str2));
        JSONArray names = this.mProperties.names();
        if (names != null) {
            for (int i = 0; i < names.length(); i++) {
                String string = names.getString(i);
                if (!string.equals(str) && !string.equals(str2)) {
                    jSONStringer.key(string).value(this.mProperties.get(string));
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mProperties.toString().equals(((Data) obj).mProperties.toString());
    }

    public int hashCode() {
        return this.mProperties.toString().hashCode();
    }
}
