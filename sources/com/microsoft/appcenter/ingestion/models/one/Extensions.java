package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Extensions implements Model {
    private static final String APP = "app";
    private static final String DEVICE = "device";
    private static final String LOC = "loc";
    private static final String METADATA = "metadata";
    private static final String NET = "net";

    /* renamed from: OS */
    private static final String f662OS = "os";
    private static final String PROTOCOL = "protocol";
    private static final String SDK = "sdk";
    private static final String USER = "user";
    private AppExtension app;
    private DeviceExtension device;
    private LocExtension loc;
    private MetadataExtension metadata;
    private NetExtension net;

    /* renamed from: os */
    private OsExtension f663os;
    private ProtocolExtension protocol;
    private SdkExtension sdk;
    private UserExtension user;

    public MetadataExtension getMetadata() {
        return this.metadata;
    }

    public void setMetadata(MetadataExtension metadataExtension) {
        this.metadata = metadataExtension;
    }

    public ProtocolExtension getProtocol() {
        return this.protocol;
    }

    public void setProtocol(ProtocolExtension protocolExtension) {
        this.protocol = protocolExtension;
    }

    public UserExtension getUser() {
        return this.user;
    }

    public void setUser(UserExtension userExtension) {
        this.user = userExtension;
    }

    public DeviceExtension getDevice() {
        return this.device;
    }

    public void setDevice(DeviceExtension deviceExtension) {
        this.device = deviceExtension;
    }

    public OsExtension getOs() {
        return this.f663os;
    }

    public void setOs(OsExtension osExtension) {
        this.f663os = osExtension;
    }

    public AppExtension getApp() {
        return this.app;
    }

    public void setApp(AppExtension appExtension) {
        this.app = appExtension;
    }

    public NetExtension getNet() {
        return this.net;
    }

    public void setNet(NetExtension netExtension) {
        this.net = netExtension;
    }

    public SdkExtension getSdk() {
        return this.sdk;
    }

    public void setSdk(SdkExtension sdkExtension) {
        this.sdk = sdkExtension;
    }

    public LocExtension getLoc() {
        return this.loc;
    }

    public void setLoc(LocExtension locExtension) {
        this.loc = locExtension;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        String str = METADATA;
        if (jSONObject.has(str)) {
            MetadataExtension metadataExtension = new MetadataExtension();
            metadataExtension.read(jSONObject.getJSONObject(str));
            setMetadata(metadataExtension);
        }
        String str2 = PROTOCOL;
        if (jSONObject.has(str2)) {
            ProtocolExtension protocolExtension = new ProtocolExtension();
            protocolExtension.read(jSONObject.getJSONObject(str2));
            setProtocol(protocolExtension);
        }
        String str3 = USER;
        if (jSONObject.has(str3)) {
            UserExtension userExtension = new UserExtension();
            userExtension.read(jSONObject.getJSONObject(str3));
            setUser(userExtension);
        }
        String str4 = DEVICE;
        if (jSONObject.has(str4)) {
            DeviceExtension deviceExtension = new DeviceExtension();
            deviceExtension.read(jSONObject.getJSONObject(str4));
            setDevice(deviceExtension);
        }
        String str5 = f662OS;
        if (jSONObject.has(str5)) {
            OsExtension osExtension = new OsExtension();
            osExtension.read(jSONObject.getJSONObject(str5));
            setOs(osExtension);
        }
        String str6 = APP;
        if (jSONObject.has(str6)) {
            AppExtension appExtension = new AppExtension();
            appExtension.read(jSONObject.getJSONObject(str6));
            setApp(appExtension);
        }
        String str7 = NET;
        if (jSONObject.has(str7)) {
            NetExtension netExtension = new NetExtension();
            netExtension.read(jSONObject.getJSONObject(str7));
            setNet(netExtension);
        }
        String str8 = SDK;
        if (jSONObject.has(str8)) {
            SdkExtension sdkExtension = new SdkExtension();
            sdkExtension.read(jSONObject.getJSONObject(str8));
            setSdk(sdkExtension);
        }
        String str9 = LOC;
        if (jSONObject.has(str9)) {
            LocExtension locExtension = new LocExtension();
            locExtension.read(jSONObject.getJSONObject(str9));
            setLoc(locExtension);
        }
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        if (getMetadata() != null) {
            jSONStringer.key(METADATA).object();
            getMetadata().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getProtocol() != null) {
            jSONStringer.key(PROTOCOL).object();
            getProtocol().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getUser() != null) {
            jSONStringer.key(USER).object();
            getUser().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getDevice() != null) {
            jSONStringer.key(DEVICE).object();
            getDevice().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getOs() != null) {
            jSONStringer.key(f662OS).object();
            getOs().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getApp() != null) {
            jSONStringer.key(APP).object();
            getApp().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getNet() != null) {
            jSONStringer.key(NET).object();
            getNet().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getSdk() != null) {
            jSONStringer.key(SDK).object();
            getSdk().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getLoc() != null) {
            jSONStringer.key(LOC).object();
            getLoc().write(jSONStringer);
            jSONStringer.endObject();
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Extensions extensions = (Extensions) obj;
        MetadataExtension metadataExtension = this.metadata;
        if (metadataExtension == null ? extensions.metadata != null : !metadataExtension.equals(extensions.metadata)) {
            return false;
        }
        ProtocolExtension protocolExtension = this.protocol;
        if (protocolExtension == null ? extensions.protocol != null : !protocolExtension.equals(extensions.protocol)) {
            return false;
        }
        UserExtension userExtension = this.user;
        if (userExtension == null ? extensions.user != null : !userExtension.equals(extensions.user)) {
            return false;
        }
        DeviceExtension deviceExtension = this.device;
        if (deviceExtension == null ? extensions.device != null : !deviceExtension.equals(extensions.device)) {
            return false;
        }
        OsExtension osExtension = this.f663os;
        if (osExtension == null ? extensions.f663os != null : !osExtension.equals(extensions.f663os)) {
            return false;
        }
        AppExtension appExtension = this.app;
        if (appExtension == null ? extensions.app != null : !appExtension.equals(extensions.app)) {
            return false;
        }
        NetExtension netExtension = this.net;
        if (netExtension == null ? extensions.net != null : !netExtension.equals(extensions.net)) {
            return false;
        }
        SdkExtension sdkExtension = this.sdk;
        if (sdkExtension == null ? extensions.sdk != null : !sdkExtension.equals(extensions.sdk)) {
            return false;
        }
        LocExtension locExtension = this.loc;
        if (locExtension != null) {
            z = locExtension.equals(extensions.loc);
        } else if (extensions.loc != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        MetadataExtension metadataExtension = this.metadata;
        int i = 0;
        int hashCode = (metadataExtension != null ? metadataExtension.hashCode() : 0) * 31;
        ProtocolExtension protocolExtension = this.protocol;
        int hashCode2 = (hashCode + (protocolExtension != null ? protocolExtension.hashCode() : 0)) * 31;
        UserExtension userExtension = this.user;
        int hashCode3 = (hashCode2 + (userExtension != null ? userExtension.hashCode() : 0)) * 31;
        DeviceExtension deviceExtension = this.device;
        int hashCode4 = (hashCode3 + (deviceExtension != null ? deviceExtension.hashCode() : 0)) * 31;
        OsExtension osExtension = this.f663os;
        int hashCode5 = (hashCode4 + (osExtension != null ? osExtension.hashCode() : 0)) * 31;
        AppExtension appExtension = this.app;
        int hashCode6 = (hashCode5 + (appExtension != null ? appExtension.hashCode() : 0)) * 31;
        NetExtension netExtension = this.net;
        int hashCode7 = (hashCode6 + (netExtension != null ? netExtension.hashCode() : 0)) * 31;
        SdkExtension sdkExtension = this.sdk;
        int hashCode8 = (hashCode7 + (sdkExtension != null ? sdkExtension.hashCode() : 0)) * 31;
        LocExtension locExtension = this.loc;
        if (locExtension != null) {
            i = locExtension.hashCode();
        }
        return hashCode8 + i;
    }
}
