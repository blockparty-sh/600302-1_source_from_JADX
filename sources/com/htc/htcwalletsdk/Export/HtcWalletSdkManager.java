package com.htc.htcwalletsdk.Export;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.WorkerThread;
import com.htc.htcwalletsdk.GlobalVariable;
import com.htc.htcwalletsdk.Native.IJniCallbackListener;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Protect.ISdkProtector;
import com.htc.htcwalletsdk.Protect.SdkProtector;
import com.htc.htcwalletsdk.Security.Core.KeyAgent;
import com.htc.htcwalletsdk.Security.Key.PublicKeyHolder;
import com.htc.htcwalletsdk.Utils.DebugMode;
import com.htc.htcwalletsdk.Utils.ZKMALog;

public class HtcWalletSdkManager implements ISdkExportKeyAgentApis {
    static final String TAG = "HtcWalletSdkManager";
    private boolean bAvoidUiThread;
    private KeyAgent mKeyAgent;

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static boolean bInit = false;
        public static ExportFields mExportFields = new ExportFields();
        /* access modifiers changed from: private */
        public static HtcWalletSdkManager mInstance = new HtcWalletSdkManager();

        private SingletonHolder() {
        }
    }

    public static HtcWalletSdkManager getInstance() {
        return SingletonHolder.mInstance;
    }

    private HtcWalletSdkManager() {
        this.bAvoidUiThread = true;
        String str = TAG;
        ZKMALog.m275i(str, "HtcWalletSdkManager +++");
        DebugMode.CheckDebugMode();
        this.mKeyAgent = new KeyAgent(SingletonHolder.mInstance);
        ZKMALog.m275i(str, "HtcWalletSdkManager ---");
    }

    @WorkerThread
    public int init(Context context) {
        int i;
        String str = TAG;
        ZKMALog.m275i(str, "init +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        if (!SingletonHolder.bInit) {
            i = this.mKeyAgent.init(context);
            if (i == 0) {
                SingletonHolder.bInit = true;
            }
        } else {
            ZKMALog.m275i(str, "init again, so do nothing!");
            i = 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("init ---  ret=");
        sb.append(i);
        ZKMALog.m275i(str, sb.toString());
        return i;
    }

    @WorkerThread
    public int setEnvironment(int i) {
        String str = TAG;
        ZKMALog.m275i(str, "setEnvironment +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int environment = this.mKeyAgent.setEnvironment(i);
        StringBuilder sb = new StringBuilder();
        sb.append("setEnvironment ---  ret=");
        sb.append(environment);
        ZKMALog.m275i(str, sb.toString());
        return environment;
    }

    public void setCallBackListener(IJniCallbackListener iJniCallbackListener) {
        String str = TAG;
        ZKMALog.m275i(str, "setCallBackListener for JNI +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        this.mKeyAgent.setCallBackListener(iJniCallbackListener);
        ZKMALog.m275i(str, "setCallBackListener for JNI ---");
    }

    public void setCallBackListener(IJavaCallbackListener iJavaCallbackListener) {
        String str = TAG;
        ZKMALog.m275i(str, "setCallBackListener for JAVA +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        this.mKeyAgent.setCallBackListener(iJavaCallbackListener);
        ZKMALog.m275i(str, "setCallBackListener for JAVA---");
    }

    public void setSdkProtectorListener(ISdkProtector iSdkProtector) {
        String str = TAG;
        ZKMALog.m275i(str, "setSdkProtectorListener +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        this.mKeyAgent.setSdkProtectorListener(iSdkProtector);
        ZKMALog.m275i(str, "setSdkProtectorListener ---");
    }

    public String getModuleVersion() {
        String str = TAG;
        ZKMALog.m275i(str, "GetModuleVersion +++");
        String moduleVersion = this.mKeyAgent.getModuleVersion();
        StringBuilder sb = new StringBuilder();
        sb.append("GetModuleVersion ---  vret=");
        sb.append(moduleVersion);
        ZKMALog.m275i(str, sb.toString());
        return moduleVersion;
    }

    @WorkerThread
    public String getApiVersion() {
        String str = TAG;
        ZKMALog.m275i(str, "getApiVersion +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        String apiVersion = this.mKeyAgent.getApiVersion();
        StringBuilder sb = new StringBuilder();
        sb.append("getApiVersion ---  vret=");
        sb.append(apiVersion);
        ZKMALog.m275i(str, sb.toString());
        return apiVersion;
    }

    @WorkerThread
    public long register(String str, String str2) {
        String str3 = TAG;
        ZKMALog.m275i(str3, "register +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        long register = this.mKeyAgent.register(str, str2);
        ZKMALog.m275i(str3, "register ---");
        return register;
    }

    @WorkerThread
    public PublicKeyHolder getSendPublicKey(long j, int i, int i2) {
        String str = TAG;
        ZKMALog.m275i(str, "getSendPublicKey idx +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        PublicKeyHolder publicKey = this.mKeyAgent.getPublicKey(j, i, 0, i2);
        ZKMALog.m275i(str, "getSendPublicKey idx ---");
        return publicKey;
    }

    @WorkerThread
    public PublicKeyHolder getReceivePublicKey(long j, int i, int i2) {
        String str = TAG;
        ZKMALog.m275i(str, "getReceivePublicKey idx +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        PublicKeyHolder publicKey = this.mKeyAgent.getPublicKey(j, i, 1, i2);
        ZKMALog.m275i(str, "getReceivePublicKey idx ---");
        return publicKey;
    }

    @WorkerThread
    public PublicKeyHolder getSendPublicKey(long j, int i) {
        String str = TAG;
        ZKMALog.m275i(str, "getSendPublicKey +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        PublicKeyHolder publicKey = this.mKeyAgent.getPublicKey(j, i, 0);
        ZKMALog.m275i(str, "getSendPublicKey ---");
        return publicKey;
    }

    @WorkerThread
    public PublicKeyHolder getReceivePublicKey(long j, int i) {
        String str = TAG;
        ZKMALog.m275i(str, "getReceivePublicKey +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        PublicKeyHolder publicKey = this.mKeyAgent.getPublicKey(j, i, 1);
        ZKMALog.m275i(str, "getReceivePublicKey ---");
        return publicKey;
    }

    @WorkerThread
    public PublicKeyHolder getAccountExtPublicKey(long j, int i, int i2, int i3) {
        String str = TAG;
        ZKMALog.m275i(str, "getAccountExtendedPublicKey +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        PublicKeyHolder extPublicKey = this.mKeyAgent.getExtPublicKey(j, i, i2, i3);
        ZKMALog.m275i(str, "getAccountExtendedPublicKey ---");
        return extPublicKey;
    }

    @WorkerThread
    public PublicKeyHolder getBipExtPublicKey(long j, int i, int i2, int i3, int i4, int i5) {
        String str = TAG;
        ZKMALog.m275i(str, "getBipExtPublicKey +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        PublicKeyHolder extPublicKey = this.mKeyAgent.getExtPublicKey(j, i, i2, i3, i4, i5);
        ZKMALog.m275i(str, "getBipExtPublicKey ---");
        return extPublicKey;
    }

    @WorkerThread
    public int isSeedExists(long j) {
        String str = TAG;
        ZKMALog.m275i(str, "IsSeedExists +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int isSeedExists = this.mKeyAgent.isSeedExists(j);
        StringBuilder sb = new StringBuilder();
        sb.append("IsSeedExists ---  ret=");
        sb.append(isSeedExists);
        ZKMALog.m275i(str, sb.toString());
        return isSeedExists;
    }

    @WorkerThread
    public int createSeed(long j) {
        String str = TAG;
        ZKMALog.m275i(str, "createSeed +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int createSeed = this.mKeyAgent.createSeed(j);
        StringBuilder sb = new StringBuilder();
        sb.append("createSeed ---  ret=");
        sb.append(createSeed);
        ZKMALog.m275i(str, sb.toString());
        return createSeed;
    }

    @WorkerThread
    public int clearSeed(long j) {
        String str = TAG;
        ZKMALog.m275i(str, "clearSeed +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int clearSeed = this.mKeyAgent.clearSeed(j);
        StringBuilder sb = new StringBuilder();
        sb.append("clearSeed ---  ret=");
        sb.append(clearSeed);
        ZKMALog.m275i(str, sb.toString());
        return clearSeed;
    }

    @WorkerThread
    public int showSeed(long j) {
        String str = TAG;
        ZKMALog.m275i(str, "showSeed +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int showSeed = this.mKeyAgent.showSeed(j);
        StringBuilder sb = new StringBuilder();
        sb.append("showSeed ---  ret=");
        sb.append(showSeed);
        ZKMALog.m275i(str, sb.toString());
        return showSeed;
    }

    @WorkerThread
    public int restoreSeed(long j) {
        String str = TAG;
        ZKMALog.m275i(str, "restoreSeed +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int restoreSeed = this.mKeyAgent.restoreSeed(j);
        StringBuilder sb = new StringBuilder();
        sb.append("restoreSeed ---  ret=");
        sb.append(restoreSeed);
        ZKMALog.m275i(str, sb.toString());
        return restoreSeed;
    }

    @WorkerThread
    public int signTransaction(long j, int i, float f, String str, ByteArrayHolder byteArrayHolder) {
        String str2 = TAG;
        ZKMALog.m275i(str2, "signTransaction +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int signTransaction = this.mKeyAgent.signTransaction(j, i, f, str, byteArrayHolder);
        StringBuilder sb = new StringBuilder();
        sb.append("signTransaction ---  ret=");
        sb.append(signTransaction);
        ZKMALog.m275i(str2, sb.toString());
        return signTransaction;
    }

    @WorkerThread
    public int isRooted() {
        String str = TAG;
        ZKMALog.m275i(str, "isRooted +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int isRooted = this.mKeyAgent.isRooted();
        StringBuilder sb = new StringBuilder();
        sb.append("isRooted ---  ret=");
        sb.append(isRooted);
        ZKMALog.m275i(str, sb.toString());
        return isRooted;
    }

    @WorkerThread
    public int clearTzData() {
        String str = TAG;
        ZKMALog.m275i(str, "clearTzData +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        ZKMALog.m275i(str, "API not support!");
        StringBuilder sb = new StringBuilder();
        sb.append("clearTzData ---  ret=");
        sb.append(RESULT.UNKNOWN);
        ZKMALog.m275i(str, sb.toString());
        return RESULT.UNKNOWN;
    }

    @WorkerThread
    public int unregister(String str, String str2, long j) {
        String str3 = TAG;
        ZKMALog.m275i(str3, "unregister +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int unregister = this.mKeyAgent.unregister(str, str2, j);
        ZKMALog.m275i(str3, "unregister ---");
        return unregister;
    }

    @WorkerThread
    public int deinit() {
        String str = TAG;
        ZKMALog.m275i(str, "deinit +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int deinit = this.mKeyAgent.deinit();
        SingletonHolder.bInit = false;
        StringBuilder sb = new StringBuilder();
        sb.append("deinit ---  ret=");
        sb.append(deinit);
        ZKMALog.m275i(str, sb.toString());
        return deinit;
    }

    @WorkerThread
    @Deprecated
    public byte[] getPartialSeed(long j, int i, ByteArrayHolder byteArrayHolder) {
        String str = TAG;
        ZKMALog.m275i(str, "getPartialSeed +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        byte[] partialSeed = this.mKeyAgent.getPartialSeed(j, (byte) i, byteArrayHolder);
        StringBuilder sb = new StringBuilder();
        sb.append("getPartialSeed ---  ret=");
        sb.append(partialSeed);
        ZKMALog.m275i(str, sb.toString());
        return partialSeed;
    }

    @WorkerThread
    @Deprecated
    public int getPartialSeed(long j, int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        String str = TAG;
        ZKMALog.m275i(str, "getPartialSeed +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int partialSeed = this.mKeyAgent.getPartialSeed(j, (byte) i, byteArrayHolder, byteArrayHolder2);
        StringBuilder sb = new StringBuilder();
        sb.append("getPartialSeed ---  ret=");
        sb.append(partialSeed);
        ZKMALog.m275i(str, sb.toString());
        return partialSeed;
    }

    @WorkerThread
    public int combineSeeds(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        String str = TAG;
        ZKMALog.m275i(str, "combineSeeds +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int combineSeeds = this.mKeyAgent.combineSeeds(j, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        StringBuilder sb = new StringBuilder();
        sb.append("combineSeeds ---  ret=");
        sb.append(combineSeeds);
        ZKMALog.m275i(str, sb.toString());
        return combineSeeds;
    }

    @WorkerThread
    public int changePIN(long j) {
        String str = TAG;
        ZKMALog.m275i(str, "changePIN +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int changePIN = this.mKeyAgent.changePIN(j);
        StringBuilder sb = new StringBuilder();
        sb.append("changePIN ---  ret=");
        sb.append(changePIN);
        ZKMALog.m275i(str, sb.toString());
        return changePIN;
    }

    @WorkerThread
    public int confirmPIN(long j, int i) {
        String str = TAG;
        ZKMALog.m275i(str, "confirmPIN +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int confirmPIN = this.mKeyAgent.confirmPIN(j, i);
        StringBuilder sb = new StringBuilder();
        sb.append("confirmPIN ---  ret=");
        sb.append(confirmPIN);
        ZKMALog.m275i(str, sb.toString());
        return confirmPIN;
    }

    public KeyAgent getKeyAgent() {
        return this.mKeyAgent;
    }

    public ExportFields getExportFields() {
        return SingletonHolder.mExportFields;
    }

    public int getLastError() {
        return GlobalVariable.GetErrorCode();
    }

    @WorkerThread
    public int getTZIDHash(ByteArrayHolder byteArrayHolder) {
        String str = TAG;
        ZKMALog.m275i(str, "getTZIDHash +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int tZIDHash = this.mKeyAgent.getTZIDHash(byteArrayHolder);
        StringBuilder sb = new StringBuilder();
        sb.append("getTZIDHash ---  ret=");
        sb.append(tZIDHash);
        ZKMALog.m275i(str, sb.toString());
        return tZIDHash;
    }

    @WorkerThread
    public int getEncAddr(long j, String str, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        String str2 = TAG;
        ZKMALog.m275i(str2, "getEncAddr +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int encAddr = this.mKeyAgent.getEncAddr(j, str, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        StringBuilder sb = new StringBuilder();
        sb.append("getEncAddr ---  ret=");
        sb.append(encAddr);
        ZKMALog.m275i(str2, sb.toString());
        return encAddr;
    }

    @WorkerThread
    public int showVerificationCode(long j, int i, String str, String str2, String str3) {
        String str4 = TAG;
        ZKMALog.m275i(str4, "showVerificationCode +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int showVerificationCode = this.mKeyAgent.showVerificationCode(j, (byte) i, str, str2, str3);
        StringBuilder sb = new StringBuilder();
        sb.append("showVerificationCode ---  ret=");
        sb.append(showVerificationCode);
        ZKMALog.m275i(str4, sb.toString());
        return showVerificationCode;
    }

    @WorkerThread
    public int enterVerificationCode(long j, int i, ByteArrayHolder byteArrayHolder) {
        String str = TAG;
        ZKMALog.m275i(str, "enterVerificationCode +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int enterVerificationCode = this.mKeyAgent.enterVerificationCode(j, (byte) i, byteArrayHolder);
        StringBuilder sb = new StringBuilder();
        sb.append("enterVerificationCode ---  ret=");
        sb.append(enterVerificationCode);
        ZKMALog.m275i(str, sb.toString());
        return enterVerificationCode;
    }

    @WorkerThread
    public int getPartialSeed_v2(long j, int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        String str = TAG;
        ZKMALog.m275i(str, "getPartialSeed_v2 +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int partialSeed_v2 = this.mKeyAgent.getPartialSeed_v2(j, (byte) i, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        StringBuilder sb = new StringBuilder();
        sb.append("getPartialSeed_v2 ---  ret=");
        sb.append(partialSeed_v2);
        ZKMALog.m275i(str, sb.toString());
        return partialSeed_v2;
    }

    @WorkerThread
    public int combineSeeds_v2(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        String str = TAG;
        ZKMALog.m275i(str, "combineSeeds_v2 +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int combineSeeds_v2 = this.mKeyAgent.combineSeeds_v2(j, byteArrayHolder, byteArrayHolder2, byteArrayHolder3);
        StringBuilder sb = new StringBuilder();
        sb.append("combineSeeds_v2 ---  ret=");
        sb.append(combineSeeds_v2);
        ZKMALog.m275i(str, sb.toString());
        return combineSeeds_v2;
    }

    @WorkerThread
    public int setERC20BGColor(Color[] colorArr) {
        String str = TAG;
        ZKMALog.m275i(str, "setERC20BGColor +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int eRC20BGColor = this.mKeyAgent.setERC20BGColor(colorArr);
        StringBuilder sb = new StringBuilder();
        sb.append("setERC20BGColor ---  ret=");
        sb.append(eRC20BGColor);
        ZKMALog.m275i(str, sb.toString());
        return eRC20BGColor;
    }

    @WorkerThread
    public int signMessage(long j, int i, String str, ByteArrayHolder byteArrayHolder) {
        String str2 = TAG;
        ZKMALog.m275i(str2, "signMessage +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int signMessage = this.mKeyAgent.signMessage(j, i, str, byteArrayHolder);
        StringBuilder sb = new StringBuilder();
        sb.append("signMessage ---  ret=");
        sb.append(signMessage);
        ZKMALog.m275i(str2, sb.toString());
        return signMessage;
    }

    @WorkerThread
    public int signMultipleTransaction(long j, int i, float f, String str, ByteArrayHolder[] byteArrayHolderArr) {
        String str2 = TAG;
        ZKMALog.m275i(str2, "signMultipleTransaction +++");
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int signMultipleTransaction = this.mKeyAgent.signMultipleTransaction(j, i, f, str, byteArrayHolderArr);
        StringBuilder sb = new StringBuilder();
        sb.append("signMultipleTransaction ---  ret=");
        sb.append(signMultipleTransaction);
        ZKMALog.m275i(str2, sb.toString());
        return signMultipleTransaction;
    }

    @WorkerThread
    public int readTzDataSet(int i, ByteArrayHolder byteArrayHolder) {
        String str = TAG;
        ZKMALog.m275i(str, "readTzDataSet +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int readTzDataSet = this.mKeyAgent.readTzDataSet(i, byteArrayHolder);
        ZKMALog.m275i(str, "readTzDataSet ---");
        return readTzDataSet;
    }

    @WorkerThread
    public int writeTzDataSet(int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        String str = TAG;
        ZKMALog.m275i(str, "writeTzDataSet +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int writeTzDataSet = this.mKeyAgent.writeTzDataSet(i, byteArrayHolder, byteArrayHolder2);
        ZKMALog.m275i(str, "writeTzDataSet ---");
        return writeTzDataSet;
    }

    @WorkerThread
    public int setKeyboardType(long j, int i) {
        String str = TAG;
        ZKMALog.m275i(str, "setKeyboardType +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int keyboardType = this.mKeyAgent.setKeyboardType(j, i);
        ZKMALog.m275i(str, "setKeyboardType ---");
        return keyboardType;
    }

    @WorkerThread
    public int changePIN_v2(long j, int i) {
        String str = TAG;
        ZKMALog.m275i(str, "changePIN_v2 +++");
        SdkProtector.throwIfApiNotSupport();
        SdkProtector.throwIfOnMainThread(this.bAvoidUiThread);
        SdkProtector.throwIfSdkNotInitial(SingletonHolder.bInit);
        int changePIN_v2 = this.mKeyAgent.changePIN_v2(j, i);
        ZKMALog.m275i(str, "changePIN_v2 ---");
        return changePIN_v2;
    }
}
