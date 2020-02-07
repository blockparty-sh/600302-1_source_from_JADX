package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class SdkExtension implements Model {
    private static final String EPOCH = "epoch";
    private static final String INSTALL_ID = "installId";
    private static final String LIB_VER = "libVer";
    private static final String SEQ = "seq";
    private String epoch;
    private UUID installId;
    private String libVer;
    private Long seq;

    public String getLibVer() {
        return this.libVer;
    }

    public void setLibVer(String str) {
        this.libVer = str;
    }

    public String getEpoch() {
        return this.epoch;
    }

    public void setEpoch(String str) {
        this.epoch = str;
    }

    public Long getSeq() {
        return this.seq;
    }

    public void setSeq(Long l) {
        this.seq = l;
    }

    public UUID getInstallId() {
        return this.installId;
    }

    public void setInstallId(UUID uuid) {
        this.installId = uuid;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        setLibVer(jSONObject.optString(LIB_VER, null));
        setEpoch(jSONObject.optString(EPOCH, null));
        setSeq(JSONUtils.readLong(jSONObject, SEQ));
        String str = "installId";
        if (jSONObject.has(str)) {
            setInstallId(UUID.fromString(jSONObject.getString(str)));
        }
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, LIB_VER, getLibVer());
        JSONUtils.write(jSONStringer, EPOCH, getEpoch());
        JSONUtils.write(jSONStringer, SEQ, getSeq());
        JSONUtils.write(jSONStringer, "installId", getInstallId());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SdkExtension sdkExtension = (SdkExtension) obj;
        String str = this.libVer;
        if (str == null ? sdkExtension.libVer != null : !str.equals(sdkExtension.libVer)) {
            return false;
        }
        String str2 = this.epoch;
        if (str2 == null ? sdkExtension.epoch != null : !str2.equals(sdkExtension.epoch)) {
            return false;
        }
        Long l = this.seq;
        if (l == null ? sdkExtension.seq != null : !l.equals(sdkExtension.seq)) {
            return false;
        }
        UUID uuid = this.installId;
        if (uuid != null) {
            z = uuid.equals(sdkExtension.installId);
        } else if (sdkExtension.installId != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.libVer;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.epoch;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Long l = this.seq;
        int hashCode3 = (hashCode2 + (l != null ? l.hashCode() : 0)) * 31;
        UUID uuid = this.installId;
        if (uuid != null) {
            i = uuid.hashCode();
        }
        return hashCode3 + i;
    }
}
