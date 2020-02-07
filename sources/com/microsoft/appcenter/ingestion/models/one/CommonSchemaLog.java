package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.AbstractLog;
import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class CommonSchemaLog extends AbstractLog {

    /* renamed from: CV */
    private static final String f660CV = "cV";
    private static final String DATA = "data";
    private static final String EXT = "ext";
    private static final String FLAGS = "flags";
    private static final String IKEY = "iKey";
    private static final String NAME = "name";
    private static final String POP_SAMPLE = "popSample";
    private static final String TIME = "time";
    private static final String VER = "ver";

    /* renamed from: cV */
    private String f661cV;
    private Data data;
    private Extensions ext;
    private Long flags;
    private String iKey;
    private String name;
    private Double popSample;
    private String ver;

    public String getVer() {
        return this.ver;
    }

    public void setVer(String str) {
        this.ver = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Double getPopSample() {
        return this.popSample;
    }

    public void setPopSample(Double d) {
        this.popSample = d;
    }

    public String getIKey() {
        return this.iKey;
    }

    public void setIKey(String str) {
        this.iKey = str;
    }

    public Long getFlags() {
        return this.flags;
    }

    public void setFlags(Long l) {
        this.flags = l;
    }

    public String getCV() {
        return this.f661cV;
    }

    public void setCV(String str) {
        this.f661cV = str;
    }

    public Extensions getExt() {
        return this.ext;
    }

    public void setExt(Extensions extensions) {
        this.ext = extensions;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        setVer(jSONObject.getString(VER));
        setName(jSONObject.getString("name"));
        setTimestamp(JSONDateUtils.toDate(jSONObject.getString("time")));
        String str = POP_SAMPLE;
        if (jSONObject.has(str)) {
            setPopSample(Double.valueOf(jSONObject.getDouble(str)));
        }
        setIKey(jSONObject.optString(IKEY, null));
        setFlags(JSONUtils.readLong(jSONObject, FLAGS));
        setCV(jSONObject.optString(f660CV, null));
        String str2 = EXT;
        if (jSONObject.has(str2)) {
            Extensions extensions = new Extensions();
            extensions.read(jSONObject.getJSONObject(str2));
            setExt(extensions);
        }
        String str3 = "data";
        if (jSONObject.has(str3)) {
            Data data2 = new Data();
            data2.read(jSONObject.getJSONObject(str3));
            setData(data2);
        }
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        jSONStringer.key(VER).value(getVer());
        jSONStringer.key("name").value(getName());
        jSONStringer.key("time").value(JSONDateUtils.toString(getTimestamp()));
        JSONUtils.write(jSONStringer, POP_SAMPLE, getPopSample());
        JSONUtils.write(jSONStringer, IKEY, getIKey());
        JSONUtils.write(jSONStringer, FLAGS, getFlags());
        JSONUtils.write(jSONStringer, f660CV, getCV());
        if (getExt() != null) {
            jSONStringer.key(EXT).object();
            getExt().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getData() != null) {
            jSONStringer.key("data").object();
            getData().write(jSONStringer);
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
        CommonSchemaLog commonSchemaLog = (CommonSchemaLog) obj;
        String str = this.ver;
        if (str == null ? commonSchemaLog.ver != null : !str.equals(commonSchemaLog.ver)) {
            return false;
        }
        String str2 = this.name;
        if (str2 == null ? commonSchemaLog.name != null : !str2.equals(commonSchemaLog.name)) {
            return false;
        }
        Double d = this.popSample;
        if (d == null ? commonSchemaLog.popSample != null : !d.equals(commonSchemaLog.popSample)) {
            return false;
        }
        String str3 = this.iKey;
        if (str3 == null ? commonSchemaLog.iKey != null : !str3.equals(commonSchemaLog.iKey)) {
            return false;
        }
        Long l = this.flags;
        if (l == null ? commonSchemaLog.flags != null : !l.equals(commonSchemaLog.flags)) {
            return false;
        }
        String str4 = this.f661cV;
        if (str4 == null ? commonSchemaLog.f661cV != null : !str4.equals(commonSchemaLog.f661cV)) {
            return false;
        }
        Extensions extensions = this.ext;
        if (extensions == null ? commonSchemaLog.ext != null : !extensions.equals(commonSchemaLog.ext)) {
            return false;
        }
        Data data2 = this.data;
        if (data2 != null) {
            z = data2.equals(commonSchemaLog.data);
        } else if (commonSchemaLog.data != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.ver;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Double d = this.popSample;
        int hashCode4 = (hashCode3 + (d != null ? d.hashCode() : 0)) * 31;
        String str3 = this.iKey;
        int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Long l = this.flags;
        int hashCode6 = (hashCode5 + (l != null ? l.hashCode() : 0)) * 31;
        String str4 = this.f661cV;
        int hashCode7 = (hashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Extensions extensions = this.ext;
        int hashCode8 = (hashCode7 + (extensions != null ? extensions.hashCode() : 0)) * 31;
        Data data2 = this.data;
        if (data2 != null) {
            i = data2.hashCode();
        }
        return hashCode8 + i;
    }
}
