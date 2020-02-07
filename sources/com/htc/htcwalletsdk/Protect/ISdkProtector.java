package com.htc.htcwalletsdk.Protect;

public interface ISdkProtector {

    public enum NotifyType {
        ALARMEVENT,
        UIEVENT,
        APIEVENT,
        RESERVED1,
        RESERVED2,
        RESERVED3,
        RESERVED4,
        RESERVED5,
        RESERVED6,
        RESERVED7,
        RESERVED8,
        CUSTOMEVENT1,
        CUSTOMEVENT2,
        CUSTOMEVENT3,
        CUSTOMEVENT4
    }

    int onErrorFeedback(int i, int i2, String str);

    int onNotify(NotifyType notifyType, int i, String str, byte[] bArr);
}
