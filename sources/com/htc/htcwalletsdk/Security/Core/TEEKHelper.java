package com.htc.htcwalletsdk.Security.Core;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Utils.ZKMALog;
import com.htc.wallettzservice.EncAddr;
import java.lang.reflect.Method;

public class TEEKHelper {
    private static final String TAG = "ZKMALog";
    private static TEEKHelper sInst;
    private Method METHOD_APIVERSION;
    private Method METHOD_CHANGEPIN;
    private Method METHOD_CHANGEPINv2;
    private Method METHOD_CLEARDATA;
    private Method METHOD_CLEARSEED;
    private Method METHOD_COMBINESEEDS;
    private Method METHOD_COMBINESEEDSv2;
    private Method METHOD_CONFIRMPIN;
    private Method METHOD_CREATESEED;
    private Method METHOD_ENTERVERIFICATIONCODE;
    private Method METHOD_GETENCADDR;
    private Method METHOD_GETEXTPUBLICKEY;
    private Method METHOD_GETPARTIALSEED;
    private Method METHOD_GETPARTIALSEEDv2;
    private Method METHOD_GETPUBLICKEY;
    private Method METHOD_GETSERVICEVERSION;
    private Method METHOD_GETTZIDHASH;
    private Method METHOD_ISSEEDEXISTS;
    private Method METHOD_ISTAMPERED;
    private Method METHOD_READTZDATASET;
    private Method METHOD_REGISTER;
    private Method METHOD_RESTORESEED;
    private Method METHOD_SETENVIRONMENT;
    private Method METHOD_SETERC20BGCOLOR;
    private Method METHOD_SETKEYBOARDTYPE;
    private Method METHOD_SHOWSEED;
    private Method METHOD_SHOWVERIFICATIONCODE;
    private Method METHOD_SIGNTRANSACTION;
    private Method METHOD_UNREGISTER;
    private Method METHOD_WRITETZDATASET;
    private Class mClass;
    private final Context mContext;
    private boolean mLoadSuccess = false;
    private Object mManager;

    public static synchronized TEEKHelper getInstance(Context context) {
        TEEKHelper tEEKHelper;
        synchronized (TEEKHelper.class) {
            if (sInst == null) {
                sInst = new TEEKHelper(context);
            }
            tEEKHelper = sInst;
        }
        return tEEKHelper;
    }

    private TEEKHelper(Context context) {
        this.mContext = context.getApplicationContext();
        init();
    }

    private void init() {
        Method[] declaredMethods;
        String str = "ZKMALog";
        try {
            this.mClass = Class.forName("com.htc.wallettzservice.HtcWalletTZManager");
            this.mManager = this.mClass.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.mContext});
            StringBuilder sb = new StringBuilder();
            sb.append("new instance:");
            sb.append(this.mManager);
            ZKMALog.m272d(str, sb.toString());
        } catch (Exception e) {
            Log.e(str, "load class failed!!", e);
        }
        ZKMALog.m272d(str, "enumerate all methods ++");
        for (Method method : this.mClass.getDeclaredMethods()) {
            if (method != null) {
                String method2 = method.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("method:");
                sb2.append(method);
                ZKMALog.m272d(str, sb2.toString());
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.apiVersion()")) {
                        this.METHOD_APIVERSION = this.mClass.getMethod("apiVersion", new Class[0]);
                    }
                } catch (NoSuchMethodException e2) {
                    Log.e(str, "apiVersion API check failed!", e2);
                }
                try {
                    if (method2.equals("public long com.htc.wallettzservice.HtcWalletTZManager.register(java.lang.String,byte[],int)")) {
                        this.METHOD_REGISTER = this.mClass.getMethod("register", new Class[]{String.class, byte[].class, Integer.TYPE});
                    }
                } catch (NoSuchMethodException e3) {
                    Log.e(str, "register API check failed!", e3);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.unRegister(java.lang.String,byte[],int,long)")) {
                        this.METHOD_UNREGISTER = this.mClass.getMethod("unRegister", new Class[]{String.class, byte[].class, Integer.TYPE, Long.TYPE});
                    }
                } catch (NoSuchMethodException e4) {
                    Log.e(str, "unRegister API check failed!", e4);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.createSeed(long)")) {
                        this.METHOD_CREATESEED = this.mClass.getMethod("createSeed", new Class[]{Long.TYPE});
                    }
                } catch (NoSuchMethodException e5) {
                    Log.e(str, "createSeed API check failed!", e5);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.restoreSeed(long)")) {
                        this.METHOD_RESTORESEED = this.mClass.getMethod("restoreSeed", new Class[]{Long.TYPE});
                    }
                } catch (NoSuchMethodException e6) {
                    Log.e(str, "restoreSeed API check failed!", e6);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.showSeed(long)")) {
                        this.METHOD_SHOWSEED = this.mClass.getMethod("showSeed", new Class[]{Long.TYPE});
                    }
                } catch (NoSuchMethodException e7) {
                    Log.e(str, "showSeed API check failed!", e7);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.clearSeed(long)")) {
                        this.METHOD_CLEARSEED = this.mClass.getMethod("clearSeed", new Class[]{Long.TYPE});
                    }
                } catch (NoSuchMethodException e8) {
                    Log.e(str, "clearSeed API check failed!", e8);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.isSeedExists(long)")) {
                        this.METHOD_ISSEEDEXISTS = this.mClass.getMethod("isSeedExists", new Class[]{Long.TYPE});
                    }
                } catch (NoSuchMethodException e9) {
                    Log.e(str, "isSeedExists API check failed!", e9);
                }
                try {
                    if (method2.equals("public java.lang.String com.htc.wallettzservice.HtcWalletTZManager.getPublicKey(long,java.lang.String)")) {
                        this.METHOD_GETPUBLICKEY = this.mClass.getMethod("getPublicKey", new Class[]{Long.TYPE, String.class});
                    }
                } catch (NoSuchMethodException e10) {
                    Log.e(str, "getPublicKey API check failed!", e10);
                }
                try {
                    if (method2.equals("public java.lang.String com.htc.wallettzservice.HtcWalletTZManager.getExtPublicKey(long,java.lang.String)")) {
                        this.METHOD_GETEXTPUBLICKEY = this.mClass.getMethod("getExtPublicKey", new Class[]{Long.TYPE, String.class});
                    }
                } catch (NoSuchMethodException e11) {
                    Log.e(str, "getExtPublicKey API check failed!", e11);
                }
                try {
                    if (method2.equals("public byte[] com.htc.wallettzservice.HtcWalletTZManager.signTransaction(long,int,float,java.lang.String)")) {
                        this.METHOD_SIGNTRANSACTION = this.mClass.getMethod("signTransaction", new Class[]{Long.TYPE, Integer.TYPE, Float.TYPE, String.class});
                    }
                } catch (NoSuchMethodException e12) {
                    Log.e(str, "signTransaction API check failed!", e12);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.clearData()")) {
                        this.METHOD_CLEARDATA = this.mClass.getMethod("clearData", new Class[0]);
                    }
                } catch (NoSuchMethodException e13) {
                    Log.e(str, "clearData API check failed!", e13);
                }
                try {
                    if (method2.equals("public byte[] com.htc.wallettzservice.HtcWalletTZManager.getPartialSeed(long,byte,byte[],long)")) {
                        this.METHOD_GETPARTIALSEED = this.mClass.getMethod("getPartialSeed", new Class[]{Long.TYPE, Byte.TYPE, byte[].class, Long.TYPE});
                    }
                } catch (NoSuchMethodException e14) {
                    Log.e(str, "getPartialSeed API check failed!", e14);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.combineSeeds(long,byte[],long,byte[],long,byte[],long)")) {
                        this.METHOD_COMBINESEEDS = this.mClass.getMethod("combineSeeds", new Class[]{Long.TYPE, byte[].class, Long.TYPE, byte[].class, Long.TYPE, byte[].class, Long.TYPE});
                    }
                } catch (NoSuchMethodException e15) {
                    Log.e(str, "combineSeeds API check failed!", e15);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.changePin(long)")) {
                        this.METHOD_CHANGEPIN = this.mClass.getMethod("changePin", new Class[]{Long.TYPE});
                    }
                } catch (NoSuchMethodException e16) {
                    Log.e(str, "changePin API check failed!", e16);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.confirmPin(long,int)")) {
                        this.METHOD_CONFIRMPIN = this.mClass.getMethod("confirmPin", new Class[]{Long.TYPE, Integer.TYPE});
                    }
                } catch (NoSuchMethodException e17) {
                    Log.e(str, "confirmPin API check failed!", e17);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.isTampered()")) {
                        this.METHOD_ISTAMPERED = this.mClass.getMethod("isTampered", new Class[0]);
                    }
                } catch (NoSuchMethodException e18) {
                    Log.e(str, "isTampered API check failed!", e18);
                }
                try {
                    if (method2.equals("public java.lang.String com.htc.wallettzservice.HtcWalletTZManager.getServiceVersion()")) {
                        this.METHOD_GETSERVICEVERSION = this.mClass.getMethod("getServiceVersion", new Class[0]);
                    }
                } catch (NoSuchMethodException e19) {
                    Log.e(str, "getServiceVersion API check failed!", e19);
                }
                try {
                    if (method2.equals("public byte[] com.htc.wallettzservice.HtcWalletTZManager.getTZIDHash()")) {
                        this.METHOD_GETTZIDHASH = this.mClass.getMethod("getTZIDHash", new Class[0]);
                    }
                } catch (NoSuchMethodException e20) {
                    Log.e(str, "getTZIDHash API check failed!", e20);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.showVerificationCode(long,byte,java.lang.String,java.lang.String,java.lang.String)")) {
                        this.METHOD_SHOWVERIFICATIONCODE = this.mClass.getMethod("showVerificationCode", new Class[]{Long.TYPE, Byte.TYPE, String.class, String.class, String.class});
                    }
                } catch (NoSuchMethodException e21) {
                    Log.e(str, "showVerificationCode API check failed!", e21);
                }
                try {
                    if (method2.equals("public byte[] com.htc.wallettzservice.HtcWalletTZManager.getPartialSeedv2(long,byte,byte[],long,byte[],long)")) {
                        this.METHOD_GETPARTIALSEEDv2 = this.mClass.getMethod("getPartialSeedv2", new Class[]{Long.TYPE, Byte.TYPE, byte[].class, Long.TYPE, byte[].class, Long.TYPE});
                    }
                } catch (NoSuchMethodException e22) {
                    Log.e(str, "getPartialSeedv2 API check failed!", e22);
                }
                try {
                    if (method2.equals("public byte[] com.htc.wallettzservice.HtcWalletTZManager.enterVerificationCode(long,byte)")) {
                        this.METHOD_ENTERVERIFICATIONCODE = this.mClass.getMethod("enterVerificationCode", new Class[]{Long.TYPE, Byte.TYPE});
                    }
                } catch (NoSuchMethodException e23) {
                    Log.e(str, "enterVerificationCode API check failed!", e23);
                }
                try {
                    if (method2.equals("public com.htc.wallettzservice.EncAddr com.htc.wallettzservice.HtcWalletTZManager.getEncAddr(long,java.lang.String)")) {
                        this.METHOD_GETENCADDR = this.mClass.getMethod("getEncAddr", new Class[]{Long.TYPE, String.class});
                    }
                } catch (NoSuchMethodException e24) {
                    Log.e(str, "getEncAddr API check failed!", e24);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.combineSeedsv2(long,byte[],long,byte[],long,byte[],long)")) {
                        this.METHOD_COMBINESEEDSv2 = this.mClass.getMethod("combineSeedsv2", new Class[]{Long.TYPE, byte[].class, Long.TYPE, byte[].class, Long.TYPE, byte[].class, Long.TYPE});
                    }
                } catch (NoSuchMethodException e25) {
                    Log.e(str, "combineSeedsv2 API check failed!", e25);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.setERC20BGColor(android.graphics.Color[])")) {
                        this.METHOD_SETERC20BGCOLOR = this.mClass.getMethod("setERC20BGColor", new Class[]{Color[].class});
                    }
                } catch (NoSuchMethodException e26) {
                    Log.e(str, "setERC20BGColor API check failed!", e26);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.setEnvironment(byte)")) {
                        this.METHOD_SETENVIRONMENT = this.mClass.getMethod("setEnvironment", new Class[]{Byte.TYPE});
                    }
                } catch (NoSuchMethodException e27) {
                    Log.e(str, "setEnvironment API check failed!", e27);
                }
                try {
                    if (method2.equals("public byte[] com.htc.wallettzservice.HtcWalletTZManager.readTzDataSet(int)")) {
                        this.METHOD_READTZDATASET = this.mClass.getMethod("readTzDataSet", new Class[]{Integer.TYPE});
                    }
                } catch (NoSuchMethodException e28) {
                    Log.e(str, "readTzDataSet API check failed!", e28);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.writeTzDataSet(int,byte[],int,byte[],int)")) {
                        this.METHOD_WRITETZDATASET = this.mClass.getMethod("writeTzDataSet", new Class[]{Integer.TYPE, byte[].class, Integer.TYPE, byte[].class, Integer.TYPE});
                    }
                } catch (NoSuchMethodException e29) {
                    Log.e(str, "writeTzDataSet API check failed!", e29);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.setKeyboardType(long,int)")) {
                        this.METHOD_SETKEYBOARDTYPE = this.mClass.getMethod("setKeyboardType", new Class[]{Long.TYPE, Integer.TYPE});
                    }
                } catch (NoSuchMethodException e30) {
                    Log.e(str, "setKeyboardType API check failed!", e30);
                }
                try {
                    if (method2.equals("public int com.htc.wallettzservice.HtcWalletTZManager.changePinv2(long,int)")) {
                        this.METHOD_CHANGEPINv2 = this.mClass.getMethod("changePinv2", new Class[]{Long.TYPE, Integer.TYPE});
                    }
                } catch (NoSuchMethodException e31) {
                    Log.e(str, "changePinv2 API check failed!", e31);
                }
            } else {
                ZKMALog.m273e(str, "get some method failed!!");
            }
        }
        ZKMALog.m272d(str, "enumerate all methods --");
        this.mLoadSuccess = true;
    }

    public boolean isLoadSuccess() {
        return this.mLoadSuccess;
    }

    public int TzApiVersion() {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "TzApiVersion +");
            Object invoke = this.METHOD_APIVERSION.invoke(this.mManager, new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("TzApiVersion - ");
            sb.append(String.format("0x%08X", new Object[]{invoke}));
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    /* access modifiers changed from: 0000 */
    public long register(String str, byte[] bArr, int i) {
        String str2 = "ZKMALog";
        Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str2, "register +");
            Object invoke = this.METHOD_REGISTER.invoke(this.mManager, new Object[]{str, bArr, Integer.valueOf(i)});
            ZKMALog.m275i(str2, "register -");
            return ((Long) invoke).longValue();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str2, sb.toString(), e);
            return -99999;
        }
    }

    public int unRegister(String str, byte[] bArr, int i, long j) {
        String str2 = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str2, "unRegister +");
            this.METHOD_UNREGISTER.invoke(this.mManager, new Object[]{str, bArr, Integer.valueOf(i), Long.valueOf(j)});
            ZKMALog.m275i(str2, "unRegister -");
            return valueOf.intValue();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str2, sb.toString(), e);
            return RESULT.UNKNOWN;
        }
    }

    public int isSeedExists(long j) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "isSeedExists +");
            Object invoke = this.METHOD_ISSEEDEXISTS.invoke(this.mManager, new Object[]{Long.valueOf(j)});
            StringBuilder sb = new StringBuilder();
            sb.append("isSeedExists - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int createSeed(long j) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "createSeed +");
            Object invoke = this.METHOD_CREATESEED.invoke(this.mManager, new Object[]{Long.valueOf(j)});
            StringBuilder sb = new StringBuilder();
            sb.append("createSeed - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int restoreSeed(long j) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "restoreSeed +");
            Object invoke = this.METHOD_RESTORESEED.invoke(this.mManager, new Object[]{Long.valueOf(j)});
            StringBuilder sb = new StringBuilder();
            sb.append("restoreSeed - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int showSeed(long j) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "showSeed +");
            Object invoke = this.METHOD_SHOWSEED.invoke(this.mManager, new Object[]{Long.valueOf(j)});
            StringBuilder sb = new StringBuilder();
            sb.append("showSeed - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int clearSeed(long j) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "clearSeed +");
            Object invoke = this.METHOD_CLEARSEED.invoke(this.mManager, new Object[]{Long.valueOf(j)});
            StringBuilder sb = new StringBuilder();
            sb.append("clearSeed - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public String getPublicKey(long j, String str) {
        String str2 = "ZKMALog";
        Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str2, "getPublicKey +");
            Object invoke = this.METHOD_GETPUBLICKEY.invoke(this.mManager, new Object[]{Long.valueOf(j), str});
            ZKMALog.m275i(str2, "getPublicKey -");
            return (String) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str2, sb.toString(), e);
            return null;
        }
    }

    public String getExtPublicKey(long j, String str) {
        String str2 = "ZKMALog";
        Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str2, "getExtPublicKey +");
            Object invoke = this.METHOD_GETEXTPUBLICKEY.invoke(this.mManager, new Object[]{Long.valueOf(j), str});
            ZKMALog.m275i(str2, "getExtPublicKey -");
            return (String) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str2, sb.toString(), e);
            return null;
        }
    }

    public byte[] signTransaction(long j, int i, float f, String str) {
        String str2 = "ZKMALog";
        try {
            ZKMALog.m275i(str2, "signTransaction +");
            Object invoke = this.METHOD_SIGNTRANSACTION.invoke(this.mManager, new Object[]{Long.valueOf(j), Integer.valueOf(i), Float.valueOf(f), str});
            ZKMALog.byteArray(str2, (byte[]) invoke);
            ZKMALog.m275i(str2, "signTransaction -");
            return (byte[]) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str2, sb.toString(), e);
            return null;
        }
    }

    public byte[] signMultipleTransaction(long j, int i, float f, String str) {
        String str2 = "ZKMALog";
        try {
            ZKMALog.m275i(str2, "signMultipleTransaction +");
            Object invoke = this.METHOD_SIGNTRANSACTION.invoke(this.mManager, new Object[]{Long.valueOf(j), Integer.valueOf(i), Float.valueOf(f), str});
            ZKMALog.byteArray(str2, (byte[]) invoke);
            ZKMALog.m275i(str2, "signMultipleTransaction -");
            return (byte[]) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str2, sb.toString(), e);
            return null;
        }
    }

    public int clearData() {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "clearData +");
            Object invoke = this.METHOD_CLEARDATA.invoke(this.mManager, new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("clearData  - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public byte[] getPartialSeed(long j, byte b, byte[] bArr, long j2) {
        String str = "ZKMALog";
        try {
            ZKMALog.m275i(str, "getPartialSeed +");
            Object invoke = this.METHOD_GETPARTIALSEED.invoke(this.mManager, new Object[]{Long.valueOf(j), Byte.valueOf(b), bArr, Long.valueOf(j2)});
            ZKMALog.byteArray(str, (byte[]) invoke);
            ZKMALog.m275i(str, "getPartialSeed -");
            return (byte[]) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str, sb.toString(), e);
            return null;
        }
    }

    public int combineSeeds(long j, byte[] bArr, long j2, byte[] bArr2, long j3, byte[] bArr3, long j4) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "combineSeeds +");
            Object invoke = this.METHOD_COMBINESEEDS.invoke(this.mManager, new Object[]{Long.valueOf(j), bArr, Long.valueOf(j2), bArr2, Long.valueOf(j3), bArr3, Long.valueOf(j4)});
            StringBuilder sb = new StringBuilder();
            sb.append("combineSeeds - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public byte[] getTZIDHash() {
        String str = "ZKMALog";
        try {
            ZKMALog.m275i(str, "getTZIDHash +");
            Object invoke = this.METHOD_GETTZIDHASH.invoke(this.mManager, new Object[0]);
            ZKMALog.byteArray(str, (byte[]) invoke);
            ZKMALog.m275i(str, "getTZIDHash -");
            return (byte[]) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str, sb.toString(), e);
            return null;
        }
    }

    public EncAddr getEncAddr(long j, String str) {
        String str2 = "ZKMALog";
        try {
            ZKMALog.m275i(str2, "getEncAddr +");
            Object invoke = this.METHOD_GETENCADDR.invoke(this.mManager, new Object[]{Long.valueOf(j), str});
            ZKMALog.m275i(str2, "getEncAddr -");
            return (EncAddr) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str2, sb.toString(), e);
            return null;
        }
    }

    public int isRooted() {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "isTempered +");
            Object invoke = this.METHOD_ISTAMPERED.invoke(this.mManager, new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("isTempered - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int changePIN(long j) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "changePin +");
            Object invoke = this.METHOD_CHANGEPIN.invoke(this.mManager, new Object[]{Long.valueOf(j)});
            StringBuilder sb = new StringBuilder();
            sb.append("changePin - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int confirmPIN(long j, int i) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("confirmPin + resId=");
            sb.append(i);
            ZKMALog.m275i(str, sb.toString());
            Object invoke = this.METHOD_CONFIRMPIN.invoke(this.mManager, new Object[]{Long.valueOf(j), Integer.valueOf(i)});
            StringBuilder sb2 = new StringBuilder();
            sb2.append("confirmPin - ");
            sb2.append(invoke);
            sb2.append(" resId=");
            sb2.append(i);
            sb2.append(" ");
            sb2.append(invoke);
            ZKMALog.m275i(str, sb2.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append(e);
            Log.e(str, sb3.toString(), e);
            return valueOf.intValue();
        }
    }

    public String getServiceVersion() {
        String str = "ZKMALog";
        try {
            ZKMALog.m275i(str, "getServiceVersion +");
            Object invoke = this.METHOD_GETSERVICEVERSION.invoke(this.mManager, new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("getServiceVersion - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return (String) invoke;
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return null;
        }
    }

    public int showVerificationCode(long j, byte b, String str, String str2, String str3) {
        String str4 = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str4, "showVerificationCode +");
            Object invoke = this.METHOD_SHOWVERIFICATIONCODE.invoke(this.mManager, new Object[]{Long.valueOf(j), Byte.valueOf(b), str, str2, str3});
            StringBuilder sb = new StringBuilder();
            sb.append("showVerificationCode - ");
            sb.append(invoke);
            ZKMALog.m275i(str4, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str4, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public byte[] enterVerificationCode(long j, byte b) {
        String str = "ZKMALog";
        try {
            ZKMALog.m275i(str, "enterVerificationCode +");
            Object invoke = this.METHOD_ENTERVERIFICATIONCODE.invoke(this.mManager, new Object[]{Long.valueOf(j), Byte.valueOf(b)});
            ZKMALog.byteArray(str, (byte[]) invoke);
            ZKMALog.m275i(str, "enterVerificationCode -");
            return (byte[]) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str, sb.toString(), e);
            return null;
        }
    }

    public byte[] getPartialSeed_v2(long j, byte b, byte[] bArr, long j2, byte[] bArr2, long j3) {
        String str = "ZKMALog";
        try {
            ZKMALog.m275i(str, "getPartialSeedv2 +");
            Object invoke = this.METHOD_GETPARTIALSEEDv2.invoke(this.mManager, new Object[]{Long.valueOf(j), Byte.valueOf(b), bArr, Long.valueOf(j2), bArr2, Long.valueOf(j3)});
            ZKMALog.byteArray(str, (byte[]) invoke);
            ZKMALog.m275i(str, "getPartialSeedv2 - ");
            return (byte[]) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str, sb.toString(), e);
            return null;
        }
    }

    public int combineSeeds_v2(long j, byte[] bArr, long j2, byte[] bArr2, long j3, byte[] bArr3, long j4) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "combineSeeds_v2 +");
            Object invoke = this.METHOD_COMBINESEEDSv2.invoke(this.mManager, new Object[]{Long.valueOf(j), bArr, Long.valueOf(j2), bArr2, Long.valueOf(j3), bArr3, Long.valueOf(j4)});
            StringBuilder sb = new StringBuilder();
            sb.append("combineSeeds_v2 - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int setERC20BGColor(Color[] colorArr) {
        String str = "ZKMALog";
        try {
            ZKMALog.m275i(str, "setERC20BGColor +");
            Object invoke = this.METHOD_SETERC20BGCOLOR.invoke(this.mManager, new Object[]{colorArr});
            StringBuilder sb = new StringBuilder();
            sb.append("setERC20BGColor - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return RESULT.UNKNOWN;
        }
    }

    public int setEnvironment(byte b) {
        String str = "ZKMALog";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("setEnvironment + env=");
            sb.append(b);
            ZKMALog.m275i(str, sb.toString());
            Object invoke = this.METHOD_SETENVIRONMENT.invoke(this.mManager, new Object[]{Byte.valueOf(b)});
            StringBuilder sb2 = new StringBuilder();
            sb2.append("setEnvironment - ");
            sb2.append(invoke);
            ZKMALog.m275i(str, sb2.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append(e);
            Log.e(str, sb3.toString(), e);
            return RESULT.UNKNOWN;
        }
    }

    public byte[] readTzDataSet(int i) {
        String str = "ZKMALog";
        try {
            ZKMALog.m275i(str, "readTzDataSet +");
            Object invoke = this.METHOD_READTZDATASET.invoke(this.mManager, new Object[]{Integer.valueOf(i)});
            ZKMALog.byteArray(str, (byte[]) invoke);
            ZKMALog.m275i(str, "readTzDataSet -");
            return (byte[]) invoke;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            Log.e(str, sb.toString(), e);
            return null;
        }
    }

    public int writeTzDataSet(int i, byte[] bArr, byte[] bArr2) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            ZKMALog.m275i(str, "writeTzDataSet +");
            Object invoke = this.METHOD_WRITETZDATASET.invoke(this.mManager, new Object[]{Integer.valueOf(i), bArr, Integer.valueOf(bArr.length), bArr2, Integer.valueOf(bArr2.length)});
            StringBuilder sb = new StringBuilder();
            sb.append("writeTzDataSet - ");
            sb.append(invoke);
            ZKMALog.m275i(str, sb.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(e);
            Log.e(str, sb2.toString(), e);
            return valueOf.intValue();
        }
    }

    public int setKeyboardType(long j, int i) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("setKeyboardType + nType=");
            sb.append(i);
            ZKMALog.m275i(str, sb.toString());
            Object invoke = this.METHOD_SETKEYBOARDTYPE.invoke(this.mManager, new Object[]{Long.valueOf(j), Integer.valueOf(i)});
            StringBuilder sb2 = new StringBuilder();
            sb2.append("setKeyboardType - ");
            sb2.append(invoke);
            ZKMALog.m275i(str, sb2.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append(e);
            Log.e(str, sb3.toString(), e);
            return valueOf.intValue();
        }
    }

    public int changePIN_v2(long j, int i) {
        String str = "ZKMALog";
        Integer valueOf = Integer.valueOf(RESULT.UNKNOWN);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("changePIN_v2 + nType=");
            sb.append(i);
            ZKMALog.m275i(str, sb.toString());
            Object invoke = this.METHOD_CHANGEPINv2.invoke(this.mManager, new Object[]{Long.valueOf(j), Integer.valueOf(i)});
            StringBuilder sb2 = new StringBuilder();
            sb2.append("changePIN_v2 - ");
            sb2.append(invoke);
            ZKMALog.m275i(str, sb2.toString());
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append(e);
            Log.e(str, sb3.toString(), e);
            return valueOf.intValue();
        }
    }
}
