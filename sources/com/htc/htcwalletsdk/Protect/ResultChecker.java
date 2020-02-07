package com.htc.htcwalletsdk.Protect;

public class ResultChecker extends SdkProtector {
    private static final String TAG = "ResultChecker";
    public static ISdkProtector mSdkProtectorListener;

    public static void setSdkProtectorListener(ISdkProtector iSdkProtector) {
        mSdkProtectorListener = iSdkProtector;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006c, code lost:
        r8 = r8 + 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006d, code lost:
        r8 = r8 + 15;
        r1.putString(r5, com.htc.htcwalletsdk.CONSTANT.FLAG_UI_TRY_OFTEN.TRY_LATER);
        r1.putString(com.htc.htcwalletsdk.CONSTANT.UI_IN_KEY.MINS, java.lang.String.valueOf(r8));
        com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowUIActivity(com.htc.htcwalletsdk.Act.UITryOftenAct.class, r9, r2);
        r9 = new java.lang.StringBuilder();
        r9.append(r4);
        r9.append(r2.getOut().get(r3));
        com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r0, r9.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00c6, code lost:
        r9 = mSdkProtectorListener;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00c8, code lost:
        if (r9 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00ca, code lost:
        r9.onErrorFeedback(r10, 0, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void Diagnostic(android.content.Context r9, int r10) {
        /*
            java.lang.String r0 = "ResultChecker"
            if (r9 == 0) goto L_0x00cf
            if (r10 == 0) goto L_0x00cf
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            com.htc.htcwalletsdk.Utils.ParamHolder r2 = new com.htc.htcwalletsdk.Utils.ParamHolder
            r2.<init>()
            r2.setIn(r1)
            java.lang.String r3 = "test"
            java.lang.String r4 = "input data ready"
            r1.putString(r3, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Diagnostic    errcode="
            r4.append(r5)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            com.htc.htcwalletsdk.Utils.ZKMALog.m275i(r0, r4)
            com.htc.htcwalletsdk.GlobalVariable.SetErrorCode(r10)
            java.lang.String r4 = "Verify output data : "
            java.lang.String r5 = "flag"
            r6 = 0
            r7 = 15
            switch(r10) {
                case -205: goto L_0x009c;
                case -204: goto L_0x006a;
                case -203: goto L_0x0068;
                case -202: goto L_0x0066;
                default: goto L_0x003b;
            }
        L_0x003b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "bShowErrorDialog="
            r1.append(r2)
            boolean r2 = com.htc.htcwalletsdk.Export.ExportFields.bShowErrorDialog
            r1.append(r2)
            java.lang.String r2 = ",  Unhandle error code="
            r1.append(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r0, r1)
            boolean r1 = com.htc.htcwalletsdk.Export.ExportFields.bTZ_support
            r2 = 1
            if (r1 != r2) goto L_0x00c1
            boolean r1 = com.htc.htcwalletsdk.Export.ExportFields.bShowErrorDialog
            if (r1 != r2) goto L_0x00c1
            com.htc.htcwalletsdk.Protect.SdkProtector.GenericErrorChecker(r9, r10)
            goto L_0x00c6
        L_0x0066:
            r8 = 0
            goto L_0x006d
        L_0x0068:
            r8 = 0
            goto L_0x006c
        L_0x006a:
            r8 = 15
        L_0x006c:
            int r8 = r8 + r7
        L_0x006d:
            int r8 = r8 + r7
            java.lang.String r7 = "try_later"
            r1.putString(r5, r7)
            java.lang.String r5 = java.lang.String.valueOf(r8)
            java.lang.String r7 = "LOCK_MINS"
            r1.putString(r7, r5)
            java.lang.Class<com.htc.htcwalletsdk.Act.UITryOftenAct> r1 = com.htc.htcwalletsdk.Act.UITryOftenAct.class
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowUIActivity(r1, r9, r2)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            android.os.Bundle r1 = r2.getOut()
            java.lang.Object r1 = r1.get(r3)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r0, r9)
            goto L_0x00c6
        L_0x009c:
            java.lang.String r7 = "try_too_often"
            r1.putString(r5, r7)
            java.lang.Class<com.htc.htcwalletsdk.Act.UITryOftenAct> r1 = com.htc.htcwalletsdk.Act.UITryOftenAct.class
            com.htc.htcwalletsdk.Security.Core.KeyAgent.doShowUIActivity(r1, r9, r2)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            android.os.Bundle r1 = r2.getOut()
            java.lang.Object r1 = r1.get(r3)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r0, r9)
            goto L_0x00c6
        L_0x00c1:
            java.lang.String r9 = "SW wallet do no any prompt."
            com.htc.htcwalletsdk.Utils.ZKMALog.m272d(r0, r9)
        L_0x00c6:
            com.htc.htcwalletsdk.Protect.ISdkProtector r9 = mSdkProtectorListener
            if (r9 == 0) goto L_0x00d6
            r0 = 0
            r9.onErrorFeedback(r10, r6, r0)
            goto L_0x00d6
        L_0x00cf:
            if (r9 != 0) goto L_0x00d6
            java.lang.String r9 = "Unhandled ERROR   since  context= null"
            com.htc.htcwalletsdk.Utils.ZKMALog.m273e(r0, r9)
        L_0x00d6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htc.htcwalletsdk.Protect.ResultChecker.Diagnostic(android.content.Context, int):void");
    }
}
