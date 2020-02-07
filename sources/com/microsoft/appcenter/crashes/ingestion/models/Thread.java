package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.crashes.ingestion.models.json.StackFrameFactory;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Thread implements Model {
    private List<StackFrame> frames;

    /* renamed from: id */
    private long f655id;
    private String name;

    public long getId() {
        return this.f655id;
    }

    public void setId(long j) {
        this.f655id = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<StackFrame> getFrames() {
        return this.frames;
    }

    public void setFrames(List<StackFrame> list) {
        this.frames = list;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        setId(jSONObject.getLong(CommonProperties.f657ID));
        setName(jSONObject.optString("name", null));
        setFrames(JSONUtils.readArray(jSONObject, CommonProperties.FRAMES, StackFrameFactory.getInstance()));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, CommonProperties.f657ID, Long.valueOf(getId()));
        JSONUtils.write(jSONStringer, "name", getName());
        JSONUtils.writeArray(jSONStringer, CommonProperties.FRAMES, getFrames());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Thread thread = (Thread) obj;
        if (this.f655id != thread.f655id) {
            return false;
        }
        String str = this.name;
        if (str == null ? thread.name != null : !str.equals(thread.name)) {
            return false;
        }
        List<StackFrame> list = this.frames;
        if (list != null) {
            z = list.equals(thread.frames);
        } else if (thread.frames != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long j = this.f655id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.name;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        List<StackFrame> list = this.frames;
        if (list != null) {
            i2 = list.hashCode();
        }
        return hashCode + i2;
    }
}
