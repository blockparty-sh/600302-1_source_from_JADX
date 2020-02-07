package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.LogWithProperties;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class HandledErrorLog extends LogWithProperties {
    private static final String EXCEPTION = "exception";
    public static final String TYPE = "handledError";
    private Exception exception;

    /* renamed from: id */
    private UUID f654id;

    public String getType() {
        return TYPE;
    }

    public UUID getId() {
        return this.f654id;
    }

    public void setId(UUID uuid) {
        this.f654id = uuid;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception2) {
        this.exception = exception2;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setId(UUID.fromString(jSONObject.getString(CommonProperties.f657ID)));
        String str = EXCEPTION;
        if (jSONObject.has(str)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            Exception exception2 = new Exception();
            exception2.read(jSONObject2);
            setException(exception2);
        }
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        jSONStringer.key(CommonProperties.f657ID).value(getId());
        if (getException() != null) {
            jSONStringer.key(EXCEPTION).object();
            this.exception.write(jSONStringer);
            jSONStringer.endObject();
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        HandledErrorLog handledErrorLog = (HandledErrorLog) obj;
        UUID uuid = this.f654id;
        if (uuid == null ? handledErrorLog.f654id != null : !uuid.equals(handledErrorLog.f654id)) {
            return false;
        }
        Exception exception2 = this.exception;
        if (exception2 != null) {
            z = exception2.equals(handledErrorLog.exception);
        } else if (handledErrorLog.exception != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        UUID uuid = this.f654id;
        int i = 0;
        int hashCode2 = (hashCode + (uuid != null ? uuid.hashCode() : 0)) * 31;
        Exception exception2 = this.exception;
        if (exception2 != null) {
            i = exception2.hashCode();
        }
        return hashCode2 + i;
    }
}
