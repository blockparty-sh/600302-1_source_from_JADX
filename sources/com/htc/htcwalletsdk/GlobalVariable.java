package com.htc.htcwalletsdk;

import com.htc.htcwalletsdk.Utils.ZKMALog;

public class GlobalVariable {
    static int ErrorCode = 0;
    static final String TAG = "GlobalVariable";

    public static int GetErrorCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetErrorCode=");
        sb.append(ErrorCode);
        ZKMALog.m276v(TAG, sb.toString());
        return ErrorCode;
    }

    public static void SetErrorCode(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SetErrorCode=");
        sb.append(i);
        ZKMALog.m276v(TAG, sb.toString());
        ErrorCode = i;
    }
}
