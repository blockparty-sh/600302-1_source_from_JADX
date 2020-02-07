package com.htc.htcwalletsdk.Native;

public interface IJniCallbackListener {
    void CallbackStatus(String str);

    int UICreatePin(int i, String str);

    int UIResetConfirm(int i);

    int UIRestoreWords(String str, int i);

    int UIShowAndVerifyWords(String str, int i);

    int UIShowWords(String str, int i);

    int UITransaction(long j, String str, String str2, String str3, String str4, String str5, String str6);

    int UIVerifyPin(String str);
}
