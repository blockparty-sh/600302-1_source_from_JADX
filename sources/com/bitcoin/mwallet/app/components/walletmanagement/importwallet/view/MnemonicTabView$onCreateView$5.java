package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "mnemonicWords", "", "", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicTabView.kt */
final class MnemonicTabView$onCreateView$5<T> implements Observer<List<? extends String>> {
    final /* synthetic */ View $layoutView;
    final /* synthetic */ MnemonicTabView this$0;

    MnemonicTabView$onCreateView$5(MnemonicTabView mnemonicTabView, View view) {
        this.this$0 = mnemonicTabView;
        this.$layoutView = view;
    }

    public final void onChanged(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "mnemonicWords");
        if (this.this$0.getPresenter().getHasZion()) {
            TextView textView = (TextView) this.$layoutView.findViewById(C1018R.C1021id.importWalletByMnemonicButton);
            if (textView != null) {
                textView.setEnabled(true);
            }
        } else {
            TextView textView2 = (TextView) this.$layoutView.findViewById(C1018R.C1021id.importWalletByMnemonicButton);
            if (textView2 != null) {
                textView2.setEnabled(list.size() >= 12);
            }
        }
        TextView textView3 = (TextView) this.$layoutView.findViewById(C1018R.C1021id.recoveryPhraseEditText);
        Collection collection = list;
        if (collection.size() >= 12) {
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            this.this$0.dismissKeyboard();
        } else {
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
            MnemonicTabView mnemonicTabView = this.this$0;
            Intrinsics.checkExpressionValueIsNotNull(textView3, "textInputMnemonics");
            mnemonicTabView.showKeyboard(textView3);
        }
        if (textView3 != null) {
            textView3.setHint(MessageFormat.format(this.this$0.getResources().getString(C1018R.string.addwallet_enter_recovery), new Object[]{Integer.valueOf(collection.size() + 1)}));
        }
    }
}
