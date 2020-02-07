package com.htc.htcwalletsdk.Security.Core;

import android.content.Context;
import android.graphics.Color;
import com.htc.htcwalletsdk.Export.IJavaCallbackListener;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.GlobalVariable;
import com.htc.htcwalletsdk.Native.IJniCallbackListener;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Protect.ISdkProtector;
import com.htc.htcwalletsdk.Protect.ResultChecker;
import com.htc.htcwalletsdk.Security.Key.PublicKeyHolder;
import com.htc.htcwalletsdk.Utils.GenericUtils;
import com.htc.htcwalletsdk.Utils.ZKMALog;
import com.htc.wallettzservice.EncAddr;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class KeySecurityHW implements IKeySecurity {
    private static final String TAG = "KeySecurityHW";
    private Context mContext;
    private TEEKHelper mTEEKHelper;
    public long mUnique_id;

    public KeySecurityHW(Context context) {
        this.mContext = context;
        this.mTEEKHelper = TEEKHelper.getInstance(context);
    }

    public boolean isLoadSuccess() {
        boolean isLoadSuccess = this.mTEEKHelper.isLoadSuccess();
        StringBuilder sb = new StringBuilder();
        sb.append("isLoadSuccess ret=");
        sb.append(isLoadSuccess);
        ZKMALog.m272d(TAG, sb.toString());
        return isLoadSuccess;
    }

    public String GetApiVersion() {
        String str = TAG;
        ZKMALog.m272d(str, "GetApiVersion");
        String hexString = Integer.toHexString(this.mTEEKHelper.TzApiVersion());
        StringBuilder sb = new StringBuilder();
        sb.append("GetTzApiVersion = ");
        sb.append(hexString);
        ZKMALog.m272d(str, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mTEEKHelper.getServiceVersion());
        sb2.append(".");
        sb2.append(hexString);
        return sb2.toString();
    }

    public void SetCallBackListener(IJniCallbackListener iJniCallbackListener) {
        ZKMALog.m272d(TAG, "SetCallBackListener IJniCallbackListener");
    }

    public void SetCallBackListener(IJavaCallbackListener iJavaCallbackListener) {
        ZKMALog.m272d(TAG, "SetCallBackListener IJavaCallbackListener");
    }

    public void SetSdkProtectorListener(ISdkProtector iSdkProtector) {
        ZKMALog.m272d(TAG, "SetCallBackListener IJavaCallbackListener");
        ResultChecker.setSdkProtectorListener(iSdkProtector);
    }

    public int SetEnvironment(int i) {
        return this.mTEEKHelper.setEnvironment((byte) i);
    }

    public long Register(String str, String str2) {
        byte[] bytes = str2.getBytes();
        this.mUnique_id = this.mTEEKHelper.register(str, bytes, bytes.length);
        return this.mUnique_id;
    }

    public int Unregister(String str, String str2, long j) {
        byte[] bytes = str2.getBytes();
        this.mTEEKHelper.unRegister(str, bytes, bytes.length, j);
        return 0;
    }

    public int IsSeedExists(long j) {
        return this.mTEEKHelper.isSeedExists(j);
    }

    public int CreateSeed(long j) {
        return this.mTEEKHelper.createSeed(j);
    }

    public int RestoreSeed(long j) {
        return this.mTEEKHelper.restoreSeed(j);
    }

    public int ShowSeed(long j) {
        return this.mTEEKHelper.showSeed(j);
    }

    public int ClearSeed(long j) {
        return this.mTEEKHelper.clearSeed(j);
    }

    public int ChangePIN(long j) {
        return this.mTEEKHelper.changePIN(j);
    }

    public int ConfirmPIN(long j, int i) {
        return this.mTEEKHelper.confirmPIN(j, i);
    }

    public PublicKeyHolder GetPublicKey(long j, String str, PublicKeyHolder publicKeyHolder) {
        publicKeyHolder.setKey(this.mTEEKHelper.getPublicKey(j, str));
        return publicKeyHolder;
    }

    public PublicKeyHolder GetExtPublicKey(long j, String str, PublicKeyHolder publicKeyHolder) {
        publicKeyHolder.setKey(this.mTEEKHelper.getExtPublicKey(j, str));
        return publicKeyHolder;
    }

    public int SignTransaction(long j, int i, float f, String str, ByteArrayHolder byteArrayHolder) {
        String str2 = TAG;
        ZKMALog.m272d(str2, "SignTransaction");
        byte[] signTransaction = this.mTEEKHelper.signTransaction(j, i, f, str);
        if (signTransaction == null || signTransaction.length == 0) {
            return RESULT.E_TEEKM_FAILURE;
        }
        if (signTransaction.length == 4) {
            int i2 = ByteBuffer.wrap(signTransaction).getInt();
            StringBuilder sb = new StringBuilder();
            sb.append("ret = ");
            sb.append(i2);
            ZKMALog.m275i(str2, sb.toString());
            return i2;
        } else if (byteArrayHolder.byteArray.length >= signTransaction.length) {
            byteArrayHolder.byteArray = Arrays.copyOf(signTransaction, signTransaction.length);
            byteArrayHolder.receivedLength = signTransaction.length;
            return 0;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("byteArrayHolder.byteArray.length = ");
            sb2.append(byteArrayHolder.byteArray.length);
            sb2.append(" < tz_processed.length=");
            sb2.append(signTransaction.length);
            sb2.append(" extend size!");
            ZKMALog.m277w(str2, sb2.toString());
            byteArrayHolder.extendSize(signTransaction.length);
            byteArrayHolder.byteArray = Arrays.copyOf(signTransaction, signTransaction.length);
            byteArrayHolder.receivedLength = signTransaction.length;
            return 0;
        }
    }

    public int signMultipleTransaction(long j, int i, float f, String str, ByteArrayHolder[] byteArrayHolderArr) {
        String str2 = TAG;
        ZKMALog.m272d(str2, "signMultipleTransaction");
        byte[] signTransaction = this.mTEEKHelper.signTransaction(j, i, f, str);
        if (signTransaction == null || signTransaction.length == 0) {
            return RESULT.E_TEEKM_FAILURE;
        }
        if (signTransaction.length == 4) {
            int i2 = ByteBuffer.wrap(signTransaction).getInt();
            StringBuilder sb = new StringBuilder();
            sb.append("ret = ");
            sb.append(i2);
            ZKMALog.m275i(str2, sb.toString());
            return i2;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("tz_processed.length=");
        sb2.append(signTransaction.length);
        ZKMALog.m272d(str2, sb2.toString());
        byte[] bArr = new byte[4];
        System.arraycopy(signTransaction, 0, bArr, 0, 4);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("intArray0=");
        sb3.append(GenericUtils.bytesToHex(bArr));
        ZKMALog.m271c(str2, sb3.toString());
        int bytesToInt = GenericUtils.bytesToInt(bArr);
        StringBuilder sb4 = new StringBuilder();
        sb4.append("len0=");
        sb4.append(bytesToInt);
        ZKMALog.m272d(str2, sb4.toString());
        byte[] bArr2 = new byte[bytesToInt];
        System.arraycopy(signTransaction, 4, bArr2, 0, bytesToInt);
        byteArrayHolderArr[0].byteArray = Arrays.copyOf(bArr2, bytesToInt);
        byteArrayHolderArr[0].receivedLength = bytesToInt;
        int i3 = bytesToInt + 4;
        if (signTransaction.length - i3 > 4) {
            byte[] bArr3 = new byte[4];
            System.arraycopy(signTransaction, i3, bArr3, 0, 4);
            StringBuilder sb5 = new StringBuilder();
            sb5.append("intArray1=");
            sb5.append(GenericUtils.bytesToHex(bArr3));
            ZKMALog.m271c(str2, sb5.toString());
            int bytesToInt2 = GenericUtils.bytesToInt(bArr3);
            StringBuilder sb6 = new StringBuilder();
            sb6.append("len1=");
            sb6.append(bytesToInt2);
            ZKMALog.m272d(str2, sb6.toString());
            byte[] bArr4 = new byte[bytesToInt2];
            System.arraycopy(signTransaction, bytesToInt + 8, bArr4, 0, bytesToInt2);
            byteArrayHolderArr[1].byteArray = Arrays.copyOf(bArr4, bytesToInt2);
            byteArrayHolderArr[1].receivedLength = bytesToInt2;
        }
        return 0;
    }

    public byte[] GetPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder) {
        byte[] partialSeed = this.mTEEKHelper.getPartialSeed(j, b, byteArrayHolder.byteArray, (long) byteArrayHolder.byteArray.length);
        if (partialSeed == null || partialSeed.length == 0) {
            GlobalVariable.SetErrorCode(RESULT.E_TEEKM_FAILURE);
        } else if (partialSeed.length == 4) {
            ResultChecker.Diagnostic(this.mContext, ByteBuffer.wrap(partialSeed).getInt());
        }
        return partialSeed;
    }

    public int GetPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        int i;
        byte[] partialSeed = this.mTEEKHelper.getPartialSeed(j, b, byteArrayHolder.byteArray, (long) byteArrayHolder.byteArray.length);
        String str = TAG;
        if (partialSeed == null || partialSeed.length == 0) {
            i = RESULT.E_TEEKM_FAILURE;
        } else if (partialSeed.length == 4) {
            i = ByteBuffer.wrap(partialSeed).getInt();
        } else if (byteArrayHolder2.byteArray.length >= partialSeed.length) {
            byteArrayHolder2.byteArray = Arrays.copyOf(partialSeed, partialSeed.length);
            byteArrayHolder2.receivedLength = partialSeed.length;
            i = 0;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("byteArrayHolder.byteArray.length = ");
            sb.append(byteArrayHolder2.byteArray.length);
            sb.append(" ,  tz_processed.length=");
            sb.append(partialSeed.length);
            ZKMALog.m273e(str, sb.toString());
            throw new IllegalArgumentException();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ret = ");
        sb2.append(i);
        ZKMALog.m275i(str, sb2.toString());
        return i;
    }

    public int GetPartialSeed_v2(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        int i;
        ByteArrayHolder byteArrayHolder4 = byteArrayHolder;
        ByteArrayHolder byteArrayHolder5 = byteArrayHolder2;
        ByteArrayHolder byteArrayHolder6 = byteArrayHolder3;
        byte[] partialSeed_v2 = this.mTEEKHelper.getPartialSeed_v2(j, b, byteArrayHolder4.byteArray, (long) byteArrayHolder4.byteArray.length, byteArrayHolder5.byteArray, (long) byteArrayHolder5.byteArray.length);
        String str = TAG;
        if (partialSeed_v2 == null || partialSeed_v2.length == 0) {
            i = RESULT.E_TEEKM_FAILURE;
        } else if (partialSeed_v2.length == 4) {
            i = ByteBuffer.wrap(partialSeed_v2).getInt();
        } else if (byteArrayHolder6.byteArray.length >= partialSeed_v2.length) {
            byteArrayHolder6.byteArray = Arrays.copyOf(partialSeed_v2, partialSeed_v2.length);
            byteArrayHolder6.receivedLength = partialSeed_v2.length;
            i = 0;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("out_seed.byteArray.length = ");
            sb.append(byteArrayHolder6.byteArray.length);
            sb.append(" ,  tz_processed.length=");
            sb.append(partialSeed_v2.length);
            ZKMALog.m273e(str, sb.toString());
            throw new IllegalArgumentException();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ret = ");
        sb2.append(i);
        ZKMALog.m275i(str, sb2.toString());
        return i;
    }

    public int ShowVerificationCode(long j, byte b, String str, String str2, String str3) {
        return this.mTEEKHelper.showVerificationCode(j, b, str, str2, str3);
    }

    public int EnterVerificationCode(long j, byte b, ByteArrayHolder byteArrayHolder) {
        int i;
        byte[] enterVerificationCode = this.mTEEKHelper.enterVerificationCode(j, b);
        String str = TAG;
        if (enterVerificationCode == null || enterVerificationCode.length == 0) {
            i = RESULT.E_TEEKM_FAILURE;
        } else if (enterVerificationCode.length == 4) {
            i = ByteBuffer.wrap(enterVerificationCode).getInt();
        } else if (byteArrayHolder.byteArray.length >= enterVerificationCode.length) {
            byteArrayHolder.byteArray = Arrays.copyOf(enterVerificationCode, enterVerificationCode.length);
            byteArrayHolder.receivedLength = enterVerificationCode.length;
            i = 0;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("enc_verify_code.byteArray.length = ");
            sb.append(byteArrayHolder.byteArray.length);
            sb.append(" ,  tz_processed.length=");
            sb.append(enterVerificationCode.length);
            ZKMALog.m273e(str, sb.toString());
            throw new IllegalArgumentException();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ret = ");
        sb2.append(i);
        ZKMALog.m275i(str, sb2.toString());
        return i;
    }

    public int CombineSeeds(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        ByteArrayHolder byteArrayHolder4 = byteArrayHolder;
        ByteArrayHolder byteArrayHolder5 = byteArrayHolder2;
        ByteArrayHolder byteArrayHolder6 = byteArrayHolder3;
        return this.mTEEKHelper.combineSeeds(j, byteArrayHolder4.byteArray, (long) byteArrayHolder4.byteArray.length, byteArrayHolder5.byteArray, (long) byteArrayHolder5.byteArray.length, byteArrayHolder6.byteArray, (long) byteArrayHolder6.byteArray.length);
    }

    public int CombineSeeds_v2(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        ByteArrayHolder byteArrayHolder4 = byteArrayHolder;
        ByteArrayHolder byteArrayHolder5 = byteArrayHolder2;
        ByteArrayHolder byteArrayHolder6 = byteArrayHolder3;
        return this.mTEEKHelper.combineSeeds_v2(j, byteArrayHolder4.byteArray, (long) byteArrayHolder4.byteArray.length, byteArrayHolder5.byteArray, (long) byteArrayHolder5.byteArray.length, byteArrayHolder6.byteArray, (long) byteArrayHolder6.byteArray.length);
    }

    public int GetTZIDHash(ByteArrayHolder byteArrayHolder) {
        byte[] tZIDHash = this.mTEEKHelper.getTZIDHash();
        if (tZIDHash == null || tZIDHash.length == 0) {
            return RESULT.E_TEEKM_FAILURE;
        }
        if (tZIDHash.length == 4) {
            return ByteBuffer.wrap(tZIDHash).getInt();
        }
        if (byteArrayHolder.byteArray.length >= tZIDHash.length) {
            byteArrayHolder.byteArray = Arrays.copyOf(tZIDHash, tZIDHash.length);
            byteArrayHolder.receivedLength = tZIDHash.length;
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ext_idhash.byteArray.length = ");
        sb.append(byteArrayHolder.byteArray.length);
        sb.append(" ,  tz_processed.length=");
        sb.append(tZIDHash.length);
        ZKMALog.m273e(TAG, sb.toString());
        throw new IllegalArgumentException();
    }

    public int ClearTzData() {
        return this.mTEEKHelper.clearData();
    }

    public int IsRooted() {
        return this.mTEEKHelper.isRooted();
    }

    public int GetEncAddr(long j, String str, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        EncAddr encAddr = this.mTEEKHelper.getEncAddr(j, str);
        byte[] extAddr = encAddr.getExtAddr();
        byte[] extEncaddr = encAddr.getExtEncaddr();
        byte[] extEncaddrSignature = encAddr.getExtEncaddrSignature();
        if (extAddr == null || extAddr.length == 0) {
            return RESULT.E_TEEKM_FAILURE;
        }
        if (extAddr.length == 4) {
            return ByteBuffer.wrap(extAddr).getInt();
        }
        int length = byteArrayHolder.byteArray.length;
        int length2 = extAddr.length;
        String str2 = " ,  src_ext_addr.length=";
        String str3 = "ext_addr.byteArray.length = ";
        String str4 = TAG;
        if (length >= length2) {
            byteArrayHolder.byteArray = Arrays.copyOf(extAddr, extAddr.length);
            byteArrayHolder.receivedLength = extAddr.length;
            if (byteArrayHolder2.byteArray.length >= extEncaddr.length) {
                byteArrayHolder2.byteArray = Arrays.copyOf(extEncaddr, extEncaddr.length);
                byteArrayHolder2.receivedLength = extEncaddr.length;
                if (byteArrayHolder3.byteArray.length >= extEncaddrSignature.length) {
                    byteArrayHolder3.byteArray = Arrays.copyOf(extEncaddrSignature, extEncaddrSignature.length);
                    byteArrayHolder3.receivedLength = extEncaddrSignature.length;
                    return 0;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(byteArrayHolder.byteArray.length);
                sb.append(str2);
                sb.append(extAddr.length);
                ZKMALog.m273e(str4, sb.toString());
                throw new IllegalArgumentException();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(byteArrayHolder.byteArray.length);
            sb2.append(str2);
            sb2.append(extAddr.length);
            ZKMALog.m273e(str4, sb2.toString());
            throw new IllegalArgumentException();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str3);
        sb3.append(byteArrayHolder.byteArray.length);
        sb3.append(str2);
        sb3.append(extAddr.length);
        ZKMALog.m273e(str4, sb3.toString());
        throw new IllegalArgumentException();
    }

    public int SetERC20BGColor(Color[] colorArr) {
        return this.mTEEKHelper.setERC20BGColor(colorArr);
    }

    public int ReadTzDataSet(int i, ByteArrayHolder byteArrayHolder) {
        byte[] readTzDataSet = this.mTEEKHelper.readTzDataSet(i);
        int i2 = 0;
        String str = TAG;
        if (readTzDataSet == null || readTzDataSet.length == 0) {
            i2 = RESULT.E_TEEKM_FAILURE;
        } else if (readTzDataSet.length == 4) {
            ZKMALog.m273e(str, "tz_data.length == 4   an ERROR occurred!");
            i2 = ByteBuffer.wrap(readTzDataSet).order(ByteOrder.LITTLE_ENDIAN).getInt();
        } else if (readTzDataSet.length == 8) {
            ZKMALog.m273e(str, "tz_data.length == 8  ");
            byteArrayHolder.byteArray = Arrays.copyOf(readTzDataSet, readTzDataSet.length);
            byteArrayHolder.receivedLength = readTzDataSet.length;
        } else if (byteArrayHolder.byteArray.length >= readTzDataSet.length) {
            byteArrayHolder.byteArray = Arrays.copyOf(readTzDataSet, readTzDataSet.length);
            byteArrayHolder.receivedLength = readTzDataSet.length;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("data.byteArray.length = ");
            sb.append(byteArrayHolder.byteArray.length);
            sb.append(" ,  tz_data.length=");
            sb.append(readTzDataSet.length);
            ZKMALog.m273e(str, sb.toString());
            throw new IllegalArgumentException();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ret = ");
        sb2.append(i2);
        ZKMALog.m275i(str, sb2.toString());
        return i2;
    }

    public int WriteTzDataSet(int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        int writeTzDataSet = this.mTEEKHelper.writeTzDataSet(i, byteArrayHolder.byteArray, byteArrayHolder2.byteArray);
        String str = TAG;
        if (byteArrayHolder == null || byteArrayHolder.byteArray.length == 0 || byteArrayHolder2.byteArray.length == 0) {
            writeTzDataSet = RESULT.E_TEEKM_FAILURE;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("data.byteArray.length = ");
            sb.append(byteArrayHolder.byteArray.length);
            sb.append("   signature.byteArray.length = ");
            sb.append(byteArrayHolder2.byteArray.length);
            ZKMALog.m273e(str, sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ret = ");
        sb2.append(writeTzDataSet);
        ZKMALog.m275i(str, sb2.toString());
        return writeTzDataSet;
    }

    public int SetKeyboardType(long j, int i) {
        return this.mTEEKHelper.setKeyboardType(j, i);
    }

    public int ChangePIN_v2(long j, int i) {
        return this.mTEEKHelper.changePIN_v2(j, i);
    }
}
