package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, mo37405d2 = {"<anonymous>", "", "textView", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "actionId", "", "keyEvent", "Landroid/view/KeyEvent;", "onEditorAction"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicTabView.kt */
final class MnemonicTabView$loadInputUI$2 implements OnEditorActionListener {
    final /* synthetic */ MnemonicTabView$loadInputUI$1 $addTag$1;

    MnemonicTabView$loadInputUI$2(MnemonicTabView$loadInputUI$1 mnemonicTabView$loadInputUI$1) {
        this.$addTag$1 = mnemonicTabView$loadInputUI$1;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        this.$addTag$1.invoke(textView.getText().toString());
        textView.setText(null);
        return true;
    }
}