package com.htc.htcwalletsdk.Protect;

import android.os.Looper;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Utils.ZKMALog;

public class SdkProtector {
    private static final String TAG = "SdkProtector";

    public static void throwIfOnMainThread(boolean z) {
        if (z && Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Must not be invoked from the main thread.");
        }
    }

    public static void throwIfApiNotSupport() {
        ZKMALog.m273e(TAG, "throwIfApiNotSupport  BuildConfig.PARTNER_ID=1");
        throw new UnsupportedOperationException();
    }

    public static void throwIfSdkNotInitial(boolean z) {
        String str = TAG;
        ZKMALog.m272d(str, "enableApiByFlavor  BuildConfig.PARTNER_ID=1");
        if (!z) {
            StringBuilder sb = new StringBuilder();
            sb.append("ERROR! throwIfSdkNotInitial bInitSdk=");
            sb.append(z);
            ZKMALog.m273e(str, sb.toString());
            Thread.dumpStack();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0074, code lost:
        r4 = new java.lang.StringBuilder();
        r4.append("GenericErrorChecker BYPASS errorcode = ");
        r4.append(r5);
        com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r3, r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void GenericErrorChecker(android.content.Context r4, int r5) {
        /*
            if (r4 == 0) goto L_0x016e
            if (r5 == 0) goto L_0x016e
            r0 = -1000(0xfffffffffffffc18, float:NaN)
            r1 = 0
            r2 = 1
            if (r5 > r0) goto L_0x0025
            r0 = -1100(0xfffffffffffffbb4, float:NaN)
            if (r5 < r0) goto L_0x0025
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type7_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x0025:
            r0 = -1
            if (r5 == r0) goto L_0x0159
            if (r5 == r2) goto L_0x016e
            r0 = -1421(0xfffffffffffffa73, float:NaN)
            if (r5 == r0) goto L_0x016e
            r0 = -1420(0xfffffffffffffa74, float:NaN)
            if (r5 == r0) goto L_0x016e
            r0 = -1120(0xfffffffffffffba0, float:NaN)
            if (r5 == r0) goto L_0x0143
            r0 = -1119(0xfffffffffffffba1, float:NaN)
            if (r5 == r0) goto L_0x0143
            r0 = -806(0xfffffffffffffcda, float:NaN)
            if (r5 == r0) goto L_0x016e
            r0 = -805(0xfffffffffffffcdb, float:NaN)
            if (r5 == r0) goto L_0x016e
            r0 = -304(0xfffffffffffffed0, float:NaN)
            java.lang.String r3 = "SdkProtector"
            if (r5 == r0) goto L_0x012e
            r0 = -303(0xfffffffffffffed1, float:NaN)
            if (r5 == r0) goto L_0x0118
            switch(r5) {
                case -50005: goto L_0x0112;
                case -50004: goto L_0x0112;
                case -50003: goto L_0x0112;
                case -50002: goto L_0x0112;
                default: goto L_0x004f;
            }
        L_0x004f:
            switch(r5) {
                case -11007: goto L_0x0118;
                case -11006: goto L_0x00fc;
                case -11005: goto L_0x00e6;
                case -11004: goto L_0x00cf;
                case -11003: goto L_0x00b8;
                case -11002: goto L_0x00a1;
                case -11001: goto L_0x008a;
                case -11000: goto L_0x0159;
                default: goto L_0x0052;
            }
        L_0x0052:
            switch(r5) {
                case -10100: goto L_0x0143;
                case -9001: goto L_0x016e;
                case -1214: goto L_0x0118;
                case -927: goto L_0x016e;
                case -907: goto L_0x016e;
                case -712: goto L_0x016e;
                case -703: goto L_0x016e;
                case -512: goto L_0x016e;
                case -200: goto L_0x0074;
                case 1: goto L_0x016e;
                default: goto L_0x0055;
            }
        L_0x0055:
            switch(r5) {
                case -503: goto L_0x016e;
                case -502: goto L_0x016e;
                case -501: goto L_0x016e;
                default: goto L_0x0058;
            }
        L_0x0058:
            switch(r5) {
                case -105: goto L_0x016e;
                case -104: goto L_0x0074;
                case -103: goto L_0x0074;
                case -102: goto L_0x0074;
                case -101: goto L_0x0074;
                case -100: goto L_0x0074;
                default: goto L_0x005b;
            }
        L_0x005b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "GenericErrorChecker Sdk is Handling errorcode="
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.htc.htcwalletsdk.Utils.ZKMALog.m273e(r3, r0)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x0074:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "GenericErrorChecker BYPASS errorcode = "
            r4.append(r0)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r3, r4)
            goto L_0x016e
        L_0x008a:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type1_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x00a1:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type2_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x00b8:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type3_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x00cf:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type4_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x00e6:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type5_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x00fc:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type6_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x0112:
            java.lang.String r4 = "TZAPI/Service/ZKMA/ZKMS is too old."
            com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r3, r4)
            goto L_0x016e
        L_0x0118:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_type7_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x012e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "GenericErrorChecker App need to handle this errorcode = "
            r4.append(r0)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r3, r4)
            goto L_0x016e
        L_0x0143:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_sync_tztable_error
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
            goto L_0x016e
        L_0x0159:
            int r0 = com.htc.htcwalletsdk.C2271R.string.text_generic_error_description
            java.lang.String r0 = r4.getString(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2[r1] = r5
            java.lang.String r5 = java.lang.String.format(r0, r2)
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowAlertDialog(r4, r5)
        L_0x016e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htc.htcwalletsdk.Protect.SdkProtector.GenericErrorChecker(android.content.Context, int):void");
    }

    public static int setXXXXListener(Object obj) {
        if (obj != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("listener=");
        sb.append(obj);
        ZKMALog.m277w(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static long register(String str, String str2) {
        if (str != null && str2 != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("wallet_name=");
        sb.append(str);
        sb.append("  sha256=");
        sb.append(str2);
        ZKMALog.m273e(TAG, sb.toString());
        return -10303;
    }

    public static int unregister(String str, String str2, long j) {
        if (j != 0 && str != null && str2 != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("wallet_name=");
        sb.append(str);
        sb.append("  sha256=");
        sb.append(str2);
        sb.append("  unique_id=");
        sb.append(j);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int getPartialSeed(long j, int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2) {
        if (j != 0 && byteArrayHolder != null && byteArrayHolder2 != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  public_key=");
        sb.append(byteArrayHolder);
        sb.append("  byteArrayHolder=");
        sb.append(byteArrayHolder2);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int combineSeeds(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        if (j != 0 && byteArrayHolder != null && byteArrayHolder2 != null && byteArrayHolder3 != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  seed_1=");
        sb.append(byteArrayHolder);
        sb.append("  seed_2=");
        sb.append(byteArrayHolder2);
        sb.append("  seed_3=");
        sb.append(byteArrayHolder3);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int signMessage(long j, int i, String str, ByteArrayHolder byteArrayHolder) {
        if (j != 0 && str != null && byteArrayHolder != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  strJson=");
        sb.append(str);
        sb.append("  byteArrayHolder=");
        sb.append(byteArrayHolder);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int signTransaction(long j, int i, float f, String str, ByteArrayHolder byteArrayHolder) {
        if (j != 0 && str != null && byteArrayHolder != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  strJson=");
        sb.append(str);
        sb.append("  byteArrayHolder=");
        sb.append(byteArrayHolder);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int getTZIDHash(ByteArrayHolder byteArrayHolder) {
        if (byteArrayHolder != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ext_idhash=");
        sb.append(byteArrayHolder);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int showVerificationCode(long j, int i, String str, String str2, String str3) {
        if (j != 0 && str != null && str2 != null && str3 != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  friend_name=");
        sb.append(str);
        sb.append("  friend_phone_num=");
        sb.append(str2);
        sb.append("  friend_phone_model=");
        sb.append(str3);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int enterVerificationCode(long j, int i, ByteArrayHolder byteArrayHolder) {
        if (j != 0 && byteArrayHolder != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  enc_verify_code_with_sig=");
        sb.append(byteArrayHolder);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int getPartialSeed_v2(long j, int i, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        if (j != 0 && byteArrayHolder != null && byteArrayHolder2 != null && byteArrayHolder3 != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  enc_verify_code_pubkey_with_sig=");
        sb.append(byteArrayHolder);
        sb.append("  enc_aes_key_with_sig=");
        sb.append(byteArrayHolder2);
        sb.append("  out_encryptedSeed=");
        sb.append(byteArrayHolder3);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }

    public static int combineSeeds_v2(long j, ByteArrayHolder byteArrayHolder, ByteArrayHolder byteArrayHolder2, ByteArrayHolder byteArrayHolder3) {
        if (j != 0 && byteArrayHolder != null && byteArrayHolder2 != null && byteArrayHolder3 != null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unique_id=");
        sb.append(j);
        sb.append("  enc_seed_1_with_sig=");
        sb.append(byteArrayHolder);
        sb.append("  enc_seed_2_with_sig=");
        sb.append(byteArrayHolder2);
        sb.append("  enc_seed_3_with_sig=");
        sb.append(byteArrayHolder3);
        ZKMALog.m273e(TAG, sb.toString());
        return RESULT.E_SDK_INVALID_ARG;
    }
}
