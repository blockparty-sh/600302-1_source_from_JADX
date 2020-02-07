package com.bitcoin.mwallet.app.flows.receive.receive;

import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindActions$3 implements OnClickListener {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindActions$3(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r7) {
        /*
            r6 = this;
            java.io.File r7 = new java.io.File
            com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView r0 = r6.this$0
            android.content.Context r0 = r0.getContext()
            r1 = 0
            if (r0 == 0) goto L_0x0016
            java.io.File r0 = r0.getFilesDir()
            if (r0 == 0) goto L_0x0016
            java.lang.String r0 = r0.getAbsolutePath()
            goto L_0x0017
        L_0x0016:
            r0 = r1
        L_0x0017:
            java.lang.String r2 = "/tmp"
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r2)
            r7.<init>(r0)
            r7.mkdirs()
            java.io.File r0 = new java.io.File
            java.lang.String r2 = "myaddr.png"
            r0.<init>(r7, r2)
            java.io.FileOutputStream r7 = new java.io.FileOutputStream
            r7.<init>(r0)
            com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView r2 = r6.this$0
            android.view.View r2 = r2.getView()
            if (r2 == 0) goto L_0x00bb
            r3 = 2131362400(0x7f0a0260, float:1.834458E38)
            android.view.View r2 = r2.findViewById(r3)
            java.lang.String r3 = "qrContainer"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r3 = 1
            android.graphics.Bitmap r2 = androidx.core.view.ViewKt.drawToBitmap$default(r2, r1, r3, r1)
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.PNG
            r5 = 85
            java.io.OutputStream r7 = (java.io.OutputStream) r7
            r2.compress(r4, r5, r7)
            com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView r7 = r6.this$0
            androidx.fragment.app.FragmentActivity r7 = r7.getActivity()
            if (r7 != 0) goto L_0x005c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x005c:
            java.lang.String r2 = "activity!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r2)
            android.content.Context r7 = r7.getApplicationContext()
            com.bitcoin.mwallet.ApplicationClass$Companion r2 = com.bitcoin.mwallet.ApplicationClass.Companion
            android.content.Context r2 = r2.getCurrentApplicationContext()
            if (r2 == 0) goto L_0x0071
            java.lang.String r1 = r2.getPackageName()
        L_0x0071:
            java.lang.String r2 = ".fileprovider"
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r2)
            android.net.Uri r7 = androidx.core.content.FileProvider.getUriForFile(r7, r1, r0)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.intent.action.SEND"
            r0.<init>(r1)
            java.lang.String r1 = "image/png"
            r0.setType(r1)
            com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView r1 = r6.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r1 = r1.getPresenter()
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r1 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r1
            androidx.lifecycle.MutableLiveData r1 = r1.getDisplayedAddress()
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x009c
            goto L_0x009e
        L_0x009c:
            java.lang.String r1 = ""
        L_0x009e:
            java.lang.String r2 = "android.intent.extra.TEXT"
            r0.putExtra(r2, r1)
            android.os.Parcelable r7 = (android.os.Parcelable) r7
            java.lang.String r1 = "android.intent.extra.STREAM"
            r0.putExtra(r1, r7)
            r0.addFlags(r3)
            com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView r7 = r6.this$0
            java.lang.String r1 = "Share"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            android.content.Intent r0 = android.content.Intent.createChooser(r0, r1)
            r7.startActivity(r0)
            return
        L_0x00bb:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.view.View"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView$bindActions$3.onClick(android.view.View):void");
    }
}
