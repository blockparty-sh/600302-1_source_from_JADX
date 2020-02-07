package com.htc.htcwalletsdk.Security.Core;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import com.htc.htcwalletsdk.Act.BaseResultCallbackAct;
import com.htc.htcwalletsdk.Act.UIErrorDialogAct;
import com.htc.htcwalletsdk.BuildConfig;
import com.htc.htcwalletsdk.Export.ExportFields;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import com.htc.htcwalletsdk.Export.IJavaCallbackListener;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Native.IJniCallbackListener;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Protect.ISdkProtector;
import com.htc.htcwalletsdk.Protect.ResultChecker;
import com.htc.htcwalletsdk.Security.Key.PublicKeyHolder;
import com.htc.htcwalletsdk.Utils.GenericUtils;
import com.htc.htcwalletsdk.Utils.JsonParser;
import com.htc.htcwalletsdk.Utils.ParamHolder;
import com.htc.htcwalletsdk.Utils.Result;
import com.htc.htcwalletsdk.Utils.ResultCallback;
import com.htc.htcwalletsdk.Utils.ZKMALog;
import org.bitcoinj.core.TransactionInput;

public class KeyAgent {
    static final String BIT_DNUM_INDEX = "BitDigitalNum_Index";
    static int BitCoin_idx = 0;
    static final String ETH_DNUM_INDEX = "EthDigitalNum_Index";
    static int Ethereum_idx = 0;
    static final String LIT_DNUM_INDEX = "LitDigitalNum_Index";
    static int LiteCoin_idx = 0;
    static final String TAG = "KeyAgent";
    private static HtcWalletSdkManager mHtcWalletSdkManager;
    private boolean bUseHW = false;
    private Context mContext;
    ExportFields mExportFields;
    public IJavaCallbackListener mJavaCallbackListener;
    IKeySecurity mKeySecurity;
    String mSha256;
    long mUnique_id;
    String mWalletName;

    public String getModuleVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public KeyAgent(HtcWalletSdkManager htcWalletSdkManager) {
        String str = TAG;
        ZKMALog.m272d(str, str);
    }

    public void setCallBackListener(IJniCallbackListener iJniCallbackListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("setCallBackListener IJniCallbackListener=");
        sb.append(iJniCallbackListener);
        ZKMALog.m272d(TAG, sb.toString());
        this.mKeySecurity.SetCallBackListener(iJniCallbackListener);
    }

    public void setCallBackListener(IJavaCallbackListener iJavaCallbackListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("setCallBackListener IJavaCallbackListener=");
        sb.append(iJavaCallbackListener);
        ZKMALog.m272d(TAG, sb.toString());
        this.mJavaCallbackListener = iJavaCallbackListener;
    }

    public IJavaCallbackListener getJavaCallbackListener() {
        return this.mJavaCallbackListener;
    }

    public void setSdkProtectorListener(ISdkProtector iSdkProtector) {
        ResultChecker.setSdkProtectorListener(iSdkProtector);
    }

    public String getApiVersion() {
        ZKMALog.m272d(TAG, "getApiVersion");
        ExportFields exportFields = this.mExportFields;
        String str = ExportFields.bTZ_support ? "0." : "1.";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(this.mKeySecurity.GetApiVersion());
        return sb.toString();
    }

    private int checkFwVersions() {
        int IsRooted = this.mKeySecurity.IsRooted();
        String str = TAG;
        if (IsRooted == 0) {
            ZKMALog.m273e(str, "NOT_ROOTED");
            String GetApiVersion = this.mKeySecurity.GetApiVersion();
            if (GetApiVersion != null) {
                try {
                    String[] split = GetApiVersion.split("\\.");
                    if (split.length != 0) {
                        int parseInt = Integer.parseInt(split[0], 16);
                        int parseInt2 = Integer.parseInt(split[1], 16);
                        StringBuilder sb = new StringBuilder();
                        sb.append("serviceVer=");
                        sb.append(parseInt);
                        sb.append("  tzapiVer=");
                        sb.append(parseInt2);
                        sb.append("   minTzApiVer=");
                        sb.append(ExportFields.minTzApiVer);
                        ZKMALog.m275i(str, sb.toString());
                        if (parseInt < ExportFields.minServiceVer) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("ERROR! serviceVer=");
                            sb2.append(parseInt);
                            sb2.append(" <  minServiceVer=");
                            sb2.append(ExportFields.minServiceVer);
                            ZKMALog.m273e(str, sb2.toString());
                            return RESULT.E_SDK_ROM_SERVICE_TOO_OLD;
                        } else if (parseInt2 >= ExportFields.minTzApiVer) {
                            return 0;
                        } else {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("ERROR! tzapiVer=");
                            sb3.append(parseInt2);
                            sb3.append(" <  minTzApiVer=");
                            sb3.append(ExportFields.minTzApiVer);
                            ZKMALog.m273e(str, sb3.toString());
                            return RESULT.E_SDK_ROM_TZAPI_TOO_OLD;
                        }
                    }
                } catch (Exception e) {
                    ZKMALog.m273e(str, "E_SDK_VERISON_UNKNOWN");
                    e.printStackTrace();
                    return RESULT.E_SDK_VERISON_UNKNOWN;
                }
            }
            return IsRooted;
        } else if (IsRooted == -1) {
            ZKMALog.m273e(str, "E_TEEKM_UNKNOWN_ERROR");
            return -1;
        } else {
            ZKMALog.m273e(str, "E_TEEKM_TAMPERED");
            return RESULT.E_TEEKM_TAMPERED;
        }
    }

    public int init(Context context) {
        int i;
        this.mExportFields = HtcWalletSdkManager.getInstance().getExportFields();
        ExportFields exportFields = this.mExportFields;
        ExportFields.bTZ_support = GenericUtils.isDeviceSupportHardwareWallet(context);
        StringBuilder sb = new StringBuilder();
        sb.append("HtcWalletSDK:");
        sb.append(getModuleVersion());
        sb.append("  bTZ_support = ");
        ExportFields exportFields2 = this.mExportFields;
        sb.append(ExportFields.bTZ_support);
        ZKMALog.m273e(TAG, sb.toString());
        ExportFields exportFields3 = this.mExportFields;
        if (ExportFields.bTZ_support) {
            this.mKeySecurity = new KeySecurityHW(context);
            i = ((KeySecurityHW) this.mKeySecurity).isLoadSuccess() ? checkFwVersions() : RESULT.E_SDK_SERVICE_LOAD_FAILURE;
        } else {
            this.mKeySecurity = new KeySecuritySW(context);
            i = 0;
        }
        this.mContext = context;
        ResultChecker.Diagnostic(context, i);
        return i;
    }

    public long register(String str, String str2) {
        this.mWalletName = str;
        this.mSha256 = str2;
        long Register = this.mKeySecurity.Register(str, str2);
        this.mUnique_id = TransactionInput.NO_SEQUENCE & Register;
        int i = (int) ((Register >> 32) & -1);
        if (i == -1 || i == 0 || i == 1) {
            return this.mUnique_id;
        }
        if (i > 0) {
            i *= -1;
        }
        ResultChecker.Diagnostic(this.mContext, i);
        return (long) i;
    }

    public int isSeedExists(long j) {
        int IsSeedExists = this.mKeySecurity.IsSeedExists(j);
        ResultChecker.Diagnostic(this.mContext, IsSeedExists);
        return IsSeedExists;
    }

    public int createSeed(long j) {
        int CreateSeed = this.mKeySecurity.CreateSeed(j);
        ResultChecker.Diagnostic(this.mContext, CreateSeed);
        return CreateSeed;
    }

    public int restoreSeed(long j) {
        int RestoreSeed = this.mKeySecurity.RestoreSeed(j);
        ResultChecker.Diagnostic(this.mContext, RestoreSeed);
        return RestoreSeed;
    }

    public int showSeed(long j) {
        int ShowSeed = this.mKeySecurity.ShowSeed(j);
        ResultChecker.Diagnostic(this.mContext, ShowSeed);
        return ShowSeed;
    }

    public int clearSeed(long j) {
        int ClearSeed = this.mKeySecurity.ClearSeed(j);
        ResultChecker.Diagnostic(this.mContext, ClearSeed);
        return ClearSeed;
    }

    public int changePIN(long j) {
        int ChangePIN = this.mKeySecurity.ChangePIN(j);
        ResultChecker.Diagnostic(this.mContext, ChangePIN);
        return ChangePIN;
    }

    public int confirmPIN(long j, int i) {
        int ConfirmPIN = this.mKeySecurity.ConfirmPIN(j, i);
        ResultChecker.Diagnostic(this.mContext, ConfirmPIN);
        return ConfirmPIN;
    }

    public PublicKeyHolder getPublicKey(long j, int i, int i2, int i3) {
        String str;
        PublicKeyHolder publicKeyHolder = new PublicKeyHolder();
        String str2 = "/";
        if (i == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("m/44'/0'/0'/");
            sb.append(Integer.toString(i2));
            sb.append(str2);
            sb.append(i3);
            str = sb.toString();
        } else if (i == 2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("m/44'/2'/0'/");
            sb2.append(Integer.toString(i2));
            sb2.append(str2);
            sb2.append(i3);
            str = sb2.toString();
        } else if (i == 60) {
            str = "m/44'/60'/0'/0/0";
        } else if (i != 145) {
            str = i != 148 ? "UNKNOWN path" : "m/44'/148'/0'/0'/0'";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("m/44'/145'/0'/");
            sb3.append(Integer.toString(i2));
            sb3.append(str2);
            sb3.append(i3);
            str = sb3.toString();
        }
        publicKeyHolder.setKeyPath(str);
        return this.mKeySecurity.GetPublicKey(j, str, publicKeyHolder);
    }

    public PublicKeyHolder getExtPublicKey(long j, int i, int i2, int i3) {
        PublicKeyHolder publicKeyHolder = new PublicKeyHolder();
        StringBuilder sb = new StringBuilder();
        sb.append("m/");
        sb.append(Integer.toString(i));
        String str = "'/";
        sb.append(str);
        sb.append(Integer.toString(i2));
        sb.append(str);
        sb.append(Integer.toString(i3));
        sb.append("'");
        String sb2 = sb.toString();
        publicKeyHolder.setKeyPath(sb2);
        return this.mKeySecurity.GetExtPublicKey(j, sb2, publicKeyHolder);
    }

    public PublicKeyHolder getExtPublicKey(long j, int i, int i2, int i3, int i4, int i5) {
        PublicKeyHolder publicKeyHolder = new PublicKeyHolder();
        StringBuilder sb = new StringBuilder();
        sb.append("m/");
        sb.append(Integer.toString(i));
        String str = "'/";
        sb.append(str);
        sb.append(Integer.toString(i2));
        sb.append(str);
        sb.append(Integer.toString(i3));
        sb.append(str);
        sb.append(Integer.toString(i4));
        String sb2 = sb.toString();
        publicKeyHolder.setKeyPath(sb2);
        return this.mKeySecurity.GetExtPublicKey(j, sb2, publicKeyHolder);
    }

    public PublicKeyHolder getPublicKey(long j, int i, int i2) {
        String str;
        int i3;
        String str2;
        PublicKeyHolder publicKeyHolder = new PublicKeyHolder();
        Context context = this.mContext;
        String str3 = TAG;
        SharedPreferences sharedPreferences = context.getSharedPreferences(str3, 0);
        String str4 = "/";
        String str5 = null;
        if (i == 0) {
            str5 = BIT_DNUM_INDEX;
            i3 = sharedPreferences.getInt(str5, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("m/44'/0'/0'/");
            sb.append(Integer.toString(i2));
            sb.append(str4);
            sb.append(i3);
            str = sb.toString();
            BitCoin_idx++;
        } else if (i != 2) {
            if (i == 60) {
                str2 = "m/44'/60'/0'/0/0";
            } else if (i != 145) {
                str2 = i != 148 ? "UNKNOWN path" : "m/44'/148'/0'/0'/0'";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("m/44'/145'/0'/");
                sb2.append(Integer.toString(i2));
                sb2.append(str4);
                sb2.append(0);
                str2 = sb2.toString();
            }
            str = str2;
            i3 = 0;
        } else {
            str5 = LIT_DNUM_INDEX;
            i3 = sharedPreferences.getInt(str5, 0);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("m/44'/2'/0'/");
            sb3.append(Integer.toString(i2));
            sb3.append(str4);
            sb3.append(i3);
            str = sb3.toString();
            LiteCoin_idx++;
        }
        if (str5 != null) {
            if (i3 > 65535) {
                i3 = 0;
            }
            i3++;
            sharedPreferences.edit().putInt(str5, i3).commit();
        }
        ZKMALog.m271c(str3, Integer.toString(i3));
        publicKeyHolder.setKeyPath(str);
        return this.mKeySecurity.GetPublicKey(j, str, publicKeyHolder);
    }

    public int signTransaction(long j, int i, float f, String str, ByteArrayHolder byteArrayHolder) {
        int SignTransaction = this.mKeySecurity.SignTransaction(j, i, f, str, byteArrayHolder);
        ResultChecker.Diagnostic(this.mContext, SignTransaction);
        return SignTransaction;
    }

    public int signMultipleTransaction(long j, int i, float f, String str, ByteArrayHolder[] byteArrayHolderArr) {
        int signMultipleTransaction = this.mKeySecurity.signMultipleTransaction(j, i, f, str, byteArrayHolderArr);
        ResultChecker.Diagnostic(this.mContext, signMultipleTransaction);
        return signMultipleTransaction;
    }

    public int signMessage(long j, int i, String str, ByteArrayHolder byteArrayHolder) {
        String str2 = TAG;
        int i2 = RESULT.UNKNOWN;
        if (i != 60) {
            ZKMALog.m273e(str2, "Not support this coin type!");
        } else {
            int ParserJsonEthSignMessage = new JsonParser().ParserJsonEthSignMessage(str);
            if (ParserJsonEthSignMessage > 2550) {
                StringBuilder sb = new StringBuilder();
                sb.append("data field length=");
                sb.append(ParserJsonEthSignMessage);
                sb.append(" in JSON is too long.");
                ZKMALog.m273e(str2, sb.toString());
                return RESULT.UNKNOWN;
            } else if (ParserJsonEthSignMessage == 0) {
                ZKMALog.m273e(str2, "Can't get data field in JSON");
                return RESULT.UNKNOWN;
            } else {
                i2 = this.mKeySecurity.SignTransaction(j, i, 1.0f, str, byteArrayHolder);
                ResultChecker.Diagnostic(this.mContext, i2);
            }
        }
        return i2;
    }

    public int deinit() {
        this.mKeySecurity = null;
        return 0;
    }

    public int unregister(String str, String str2, long j) {
        int Unregister = this.mKeySecurity.Unregister(str, str2, j);
        ResultChecker.Diagnostic(this.mContext, Unregister);
        return Unregister;
    }

    public int clearTzData() {
        int ClearTzData = this.mKeySecurity.ClearTzData();
        ResultChecker.Diagnostic(this.mContext, ClearTzData);
        return ClearTzData;
    }

    public byte[] getPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder) {
        return this.mKeySecurity.GetPartialSeed(j, b, byteArrayHolder);
    }

    public int getPartialSeed(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        int GetPartialSeed = this.mKeySecurity.GetPartialSeed(j, b, byteArrayHolder, byteArrayHolder2);
        ResultChecker.Diagnostic(this.mContext, GetPartialSeed);
        return GetPartialSeed;
    }

    public int getPartialSeed_v2(long j, byte b, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        int GetPartialSeed_v2 = this.mKeySecurity.GetPartialSeed_v2(j, b, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        ResultChecker.Diagnostic(this.mContext, GetPartialSeed_v2);
        return GetPartialSeed_v2;
    }

    public int combineSeeds(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        int CombineSeeds = this.mKeySecurity.CombineSeeds(j, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        ResultChecker.Diagnostic(this.mContext, CombineSeeds);
        return CombineSeeds;
    }

    public int combineSeeds_v2(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        int CombineSeeds_v2 = this.mKeySecurity.CombineSeeds_v2(j, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        ResultChecker.Diagnostic(this.mContext, CombineSeeds_v2);
        return CombineSeeds_v2;
    }

    public int getTZIDHash(ByteArrayHolder byteArrayHolder) {
        int GetTZIDHash = this.mKeySecurity.GetTZIDHash(byteArrayHolder);
        ResultChecker.Diagnostic(this.mContext, GetTZIDHash);
        return GetTZIDHash;
    }

    public int showVerificationCode(long j, byte b, String str, String str2, String str3) {
        int ShowVerificationCode = this.mKeySecurity.ShowVerificationCode(j, b, str, str2, str3);
        ResultChecker.Diagnostic(this.mContext, ShowVerificationCode);
        return ShowVerificationCode;
    }

    public int enterVerificationCode(long j, byte b, ByteArrayHolder byteArrayHolder) {
        int EnterVerificationCode = this.mKeySecurity.EnterVerificationCode(j, b, byteArrayHolder);
        ResultChecker.Diagnostic(this.mContext, EnterVerificationCode);
        return EnterVerificationCode;
    }

    public int getEncAddr(long j, String str, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        int GetEncAddr = this.mKeySecurity.GetEncAddr(j, str, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        ResultChecker.Diagnostic(this.mContext, GetEncAddr);
        return GetEncAddr;
    }

    public int setERC20BGColor(Color[] colorArr) {
        int SetERC20BGColor = this.mKeySecurity.SetERC20BGColor(colorArr);
        ResultChecker.Diagnostic(this.mContext, SetERC20BGColor);
        return SetERC20BGColor;
    }

    public int setEnvironment(int i) {
        int SetEnvironment = this.mKeySecurity.SetEnvironment(i);
        ResultChecker.Diagnostic(this.mContext, SetEnvironment);
        return SetEnvironment;
    }

    public int isRooted() {
        int IsRooted = this.mKeySecurity.IsRooted();
        ResultChecker.Diagnostic(this.mContext, IsRooted);
        return IsRooted;
    }

    public int readTzDataSet(int i, ByteArrayHolder byteArrayHolder) {
        int ReadTzDataSet = this.mKeySecurity.ReadTzDataSet(i, byteArrayHolder);
        if (ReadTzDataSet < 0) {
            ResultChecker.Diagnostic(this.mContext, ReadTzDataSet);
        }
        return ReadTzDataSet;
    }

    public int writeTzDataSet(int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        int WriteTzDataSet = this.mKeySecurity.WriteTzDataSet(i, byteArrayHolder, byteArrayHolder2);
        ResultChecker.Diagnostic(this.mContext, WriteTzDataSet);
        return WriteTzDataSet;
    }

    public int setKeyboardType(long j, int i) {
        int SetKeyboardType = this.mKeySecurity.SetKeyboardType(j, i);
        ResultChecker.Diagnostic(this.mContext, SetKeyboardType);
        return SetKeyboardType;
    }

    public int changePIN_v2(long j, int i) {
        int ChangePIN_v2 = this.mKeySecurity.ChangePIN_v2(j, i);
        ResultChecker.Diagnostic(this.mContext, ChangePIN_v2);
        return ChangePIN_v2;
    }

    public static synchronized boolean doShowUIActivity(Class<?> cls, Context context, ParamHolder paramHolder) {
        boolean z;
        synchronized (KeyAgent.class) {
            ZKMALog.m275i(TAG, "doShowUIActivity +++");
            final Result result = new Result();
            C22761 r2 = new ResultCallback(Looper.getMainLooper()) {
                /* access modifiers changed from: protected */
                public void onSuccess() {
                    synchronized (result) {
                        result.success = true;
                        ZKMALog.m275i(KeyAgent.TAG, "onSuccess() notifyAll**");
                        result.notifyAll();
                    }
                }

                /* access modifiers changed from: protected */
                public void onFailed(int i) {
                    synchronized (result) {
                        result.errCode = i;
                        ZKMALog.m275i(KeyAgent.TAG, "onFailed() notifyAll**");
                        result.notifyAll();
                    }
                }

                /* access modifiers changed from: protected */
                public void onFailed(String str) {
                    synchronized (result) {
                        result.errMessage = str;
                        ZKMALog.m275i(KeyAgent.TAG, "onFailed() notifyAll**");
                        result.notifyAll();
                    }
                }
            };
            BaseResultCallbackAct.sResultCallback = r2;
            Intent intent = new Intent();
            intent.putExtra("input", paramHolder.getIn());
            intent.setClass(context, cls);
            intent.setFlags(335544320);
            context.getApplicationContext().startActivity(intent);
            ZKMALog.m275i(TAG, "before lock");
            synchronized (result) {
                try {
                    ZKMALog.m275i(TAG, "wait ....");
                    result.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ZKMALog.m275i(TAG, "after lock ");
            paramHolder.setOut(r2.getOutput());
            ZKMALog.m275i(TAG, "doShowUIActivity ---");
            z = result.success;
        }
        return z;
    }

    public static boolean doShowAlertDialog(Context context, int i) {
        Bundle bundle = new Bundle();
        ParamHolder paramHolder = new ParamHolder();
        paramHolder.setIn(bundle);
        bundle.putInt("errorCode", i);
        return doShowUIActivity(UIErrorDialogAct.class, context, paramHolder);
    }

    public static boolean doShowAlertDialog(Context context, String str) {
        Bundle bundle = new Bundle();
        ParamHolder paramHolder = new ParamHolder();
        paramHolder.setIn(bundle);
        bundle.putString("errorMessage", str);
        return doShowUIActivity(UIErrorDialogAct.class, context, paramHolder);
    }
}
