package com.htc.htcwalletsdk.Security.Core;

import android.content.Context;
import android.graphics.Color;
import com.htc.htcwalletsdk.Export.IJavaCallbackListener;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Native.IJniCallbackListener;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Protect.ISdkProtector;
import com.htc.htcwalletsdk.Security.Key.PublicKeyHolder;
import com.htc.htcwalletsdk.Utils.ZKMALog;

public class KeySecuritySW implements IKeySecurity {
    static final String TAG = "KeySecuritySW";
    private Context mContext;
    private ISdkProtector mSdkProtectorListener;
    public long mUnique_id;

    public int ChangePIN_v2(long j, int i) {
        return RESULT.UNKNOWN;
    }

    public int CombineSeeds_v2(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        return RESULT.UNKNOWN;
    }

    public int EnterVerificationCode(long j, byte b, ByteArrayHolder byteArrayHolder) {
        return RESULT.UNKNOWN;
    }

    public String GetApiVersion() {
        return "0000.0000000";
    }

    public int GetPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        return RESULT.UNKNOWN;
    }

    public byte[] GetPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder) {
        return null;
    }

    public int GetPartialSeed_v2(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        return RESULT.UNKNOWN;
    }

    public int GetTZIDHash(ByteArrayHolder byteArrayHolder) {
        return RESULT.E_TEEKM_FAILURE;
    }

    public int ReadTzDataSet(int i, ByteArrayHolder byteArrayHolder) {
        return RESULT.UNKNOWN;
    }

    public void SetCallBackListener(IJavaCallbackListener iJavaCallbackListener) {
    }

    public void SetCallBackListener(IJniCallbackListener iJniCallbackListener) {
    }

    public int SetERC20BGColor(Color[] colorArr) {
        return RESULT.UNKNOWN;
    }

    public int SetEnvironment(int i) {
        return 0;
    }

    public int SetKeyboardType(long j, int i) {
        return RESULT.UNKNOWN;
    }

    public int ShowVerificationCode(long j, byte b, String str, String str2, String str3) {
        return RESULT.UNKNOWN;
    }

    public int WriteTzDataSet(int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        return RESULT.UNKNOWN;
    }

    public int signMultipleTransaction(long j, int i, float f, String str, ByteArrayHolder[] byteArrayHolderArr) {
        return RESULT.UNKNOWN;
    }

    public KeySecuritySW(Context context) {
        String str = TAG;
        ZKMALog.m272d(str, str);
        this.mContext = context;
    }

    public void SetSdkProtectorListener(ISdkProtector iSdkProtector) {
        this.mSdkProtectorListener = iSdkProtector;
    }

    public long Register(String str, String str2) {
        ZKMALog.m272d(TAG, "Register");
        return 19088743;
    }

    public int Unregister(String str, String str2, long j) {
        ZKMALog.m272d(TAG, "Unregister");
        return RESULT.UNKNOWN;
    }

    public int IsSeedExists(long j) {
        ZKMALog.m272d(TAG, "IsSeedExists");
        return RESULT.UNKNOWN;
    }

    public int CreateSeed(long j) {
        ZKMALog.m272d(TAG, "CreateSeed");
        return RESULT.UNKNOWN;
    }

    public int RestoreSeed(long j) {
        ZKMALog.m272d(TAG, "RestoreSeed");
        return RESULT.UNKNOWN;
    }

    public int ShowSeed(long j) {
        ZKMALog.m272d(TAG, "getModuleVersion");
        return RESULT.UNKNOWN;
    }

    public int ClearSeed(long j) {
        ZKMALog.m272d(TAG, "ClearSeed");
        return RESULT.UNKNOWN;
    }

    public int ChangePIN(long j) {
        ZKMALog.m272d(TAG, "ChangePIN");
        return RESULT.UNKNOWN;
    }

    public int ConfirmPIN(long j, int i) {
        ZKMALog.m272d(TAG, "ConfirmPIN");
        return RESULT.UNKNOWN;
    }

    public PublicKeyHolder GetPublicKey(long j, String str, PublicKeyHolder publicKeyHolder) {
        ZKMALog.m272d(TAG, "GetPublicKey");
        return publicKeyHolder;
    }

    public PublicKeyHolder GetExtPublicKey(long j, String str, PublicKeyHolder publicKeyHolder) {
        ZKMALog.m272d(TAG, "GetExtPublicKey");
        return publicKeyHolder;
    }

    public int SignTransaction(long j, int i, float f, String str, ByteArrayHolder byteArrayHolder) {
        ZKMALog.m272d(TAG, "SignTransaction");
        return RESULT.UNKNOWN;
    }

    public int CombineSeeds(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        ZKMALog.m272d(TAG, "CombineSeeds");
        return RESULT.UNKNOWN;
    }

    public int IsRooted() {
        ZKMALog.m272d(TAG, "IsRooted");
        return 0;
    }

    public int ClearTzData() {
        ZKMALog.m272d(TAG, "ClearTzData");
        return RESULT.UNKNOWN;
    }

    public int GetEncAddr(long j, String str, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        ZKMALog.m272d(TAG, "GetEncAddr");
        return RESULT.UNKNOWN;
    }
}
