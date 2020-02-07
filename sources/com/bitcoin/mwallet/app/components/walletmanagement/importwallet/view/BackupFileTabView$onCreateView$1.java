package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: BackupFileTabView.kt */
final class BackupFileTabView$onCreateView$1<T> implements Observer<String> {
    final /* synthetic */ ObjectRef $layoutView;

    BackupFileTabView$onCreateView$1(ObjectRef objectRef) {
        this.$layoutView = objectRef;
    }

    public final void onChanged(String str) {
        TextView textView = (TextView) ((View) this.$layoutView.element).findViewById(C1018R.C1021id.importWalletByFileButton);
        if (textView != null) {
            CharSequence charSequence = str;
            textView.setEnabled(!(charSequence == null || charSequence.length() == 0));
        }
    }
}
