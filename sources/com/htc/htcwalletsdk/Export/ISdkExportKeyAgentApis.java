package com.htc.htcwalletsdk.Export;

import android.content.Context;
import android.graphics.Color;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Security.Key.PublicKeyHolder;

public interface ISdkExportKeyAgentApis {
    int changePIN(long j);

    int clearSeed(long j);

    int clearTzData();

    int combineSeeds(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3);

    int confirmPIN(long j, int i);

    int createSeed(long j);

    int deinit();

    int enterVerificationCode(long j, int i, ByteArrayHolder byteArrayHolder);

    PublicKeyHolder getAccountExtPublicKey(long j, int i, int i2, int i3);

    String getApiVersion();

    PublicKeyHolder getBipExtPublicKey(long j, int i, int i2, int i3, int i4, int i5);

    int getEncAddr(long j, String str, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3);

    int getLastError();

    String getModuleVersion();

    int getPartialSeed(long j, int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2);

    byte[] getPartialSeed(long j, int i, ByteArrayHolder byteArrayHolder);

    int getPartialSeed_v2(long j, int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3);

    PublicKeyHolder getReceivePublicKey(long j, int i);

    PublicKeyHolder getReceivePublicKey(long j, int i, int i2);

    PublicKeyHolder getSendPublicKey(long j, int i);

    PublicKeyHolder getSendPublicKey(long j, int i, int i2);

    int getTZIDHash(ByteArrayHolder byteArrayHolder);

    int init(Context context);

    int isSeedExists(long j);

    long register(String str, String str2);

    int restoreSeed(long j);

    int setERC20BGColor(Color[] colorArr);

    int setEnvironment(int i);

    int showSeed(long j);

    int showVerificationCode(long j, int i, String str, String str2, String str3);

    int signMessage(long j, int i, String str, ByteArrayHolder byteArrayHolder);

    int signMultipleTransaction(long j, int i, float f, String str, ByteArrayHolder[] byteArrayHolderArr);

    int signTransaction(long j, int i, float f, String str, ByteArrayHolder byteArrayHolder);
}
