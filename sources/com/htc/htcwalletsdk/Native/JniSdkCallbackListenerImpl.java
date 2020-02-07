package com.htc.htcwalletsdk.Native;

import com.htc.htcwalletsdk.Utils.ZKMALog;

public class JniSdkCallbackListenerImpl implements IJniCallbackListener {
    public String JNIcallback_status = "not available";
    private final String TAG = getClass().getSimpleName();

    public int UICreatePin(int i, String str) {
        return 0;
    }

    public int UIResetConfirm(int i) {
        return 0;
    }

    public int UIRestoreWords(String str, int i) {
        return 0;
    }

    public int UIShowAndVerifyWords(String str, int i) {
        return 0;
    }

    public int UIShowWords(String str, int i) {
        return 0;
    }

    public int UITransaction(long j, String str, String str2, String str3, String str4, String str5, String str6) {
        return 0;
    }

    public int UIVerifyPin(String str) {
        return 0;
    }

    public void CallbackStatus(String str) {
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append(" JNI Native CallbackStatus!!! ");
        sb.append(str);
        ZKMALog.m272d(str2, sb.toString());
        this.JNIcallback_status = str;
    }
}
