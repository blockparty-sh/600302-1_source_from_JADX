package com.htc.htcwalletsdk.Security.Core;

import android.graphics.Color;
import com.htc.htcwalletsdk.Export.IJavaCallbackListener;
import com.htc.htcwalletsdk.Native.IJniCallbackListener;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Protect.ISdkProtector;
import com.htc.htcwalletsdk.Security.Key.PublicKeyHolder;

public interface IKeySecurity {
    int ChangePIN(long j);

    int ChangePIN_v2(long j, int i);

    int ClearSeed(long j);

    int ClearTzData();

    int CombineSeeds(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3);

    int CombineSeeds_v2(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3);

    int ConfirmPIN(long j, int i);

    int CreateSeed(long j);

    int EnterVerificationCode(long j, byte b, ByteArrayHolder byteArrayHolder);

    String GetApiVersion();

    int GetEncAddr(long j, String str, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3);

    PublicKeyHolder GetExtPublicKey(long j, String str, PublicKeyHolder publicKeyHolder);

    int GetPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2);

    byte[] GetPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder);

    int GetPartialSeed_v2(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3);

    PublicKeyHolder GetPublicKey(long j, String str, PublicKeyHolder publicKeyHolder);

    int GetTZIDHash(ByteArrayHolder byteArrayHolder);

    int IsRooted();

    int IsSeedExists(long j);

    int ReadTzDataSet(int i, ByteArrayHolder byteArrayHolder);

    long Register(String str, String str2);

    int RestoreSeed(long j);

    void SetCallBackListener(IJavaCallbackListener iJavaCallbackListener);

    void SetCallBackListener(IJniCallbackListener iJniCallbackListener);

    int SetERC20BGColor(Color[] colorArr);

    int SetEnvironment(int i);

    int SetKeyboardType(long j, int i);

    void SetSdkProtectorListener(ISdkProtector iSdkProtector);

    int ShowSeed(long j);

    int ShowVerificationCode(long j, byte b, String str, String str2, String str3);

    int SignTransaction(long j, int i, float f, String str, ByteArrayHolder byteArrayHolder);

    int Unregister(String str, String str2, long j);

    int WriteTzDataSet(int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2);

    int signMultipleTransaction(long j, int i, float f, String str, ByteArrayHolder[] byteArrayHolderArr);
}
