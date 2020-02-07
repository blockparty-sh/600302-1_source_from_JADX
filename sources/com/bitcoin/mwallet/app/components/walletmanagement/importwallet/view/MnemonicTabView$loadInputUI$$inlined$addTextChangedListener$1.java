package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J*\u0010\r\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016¨\u0006\u000f¸\u0006\u0000"}, mo37405d2 = {"androidx/core/widget/TextViewKt$addTextChangedListener$textWatcher$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "text", "", "start", "", "count", "after", "onTextChanged", "before", "core-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TextView.kt */
public final class MnemonicTabView$loadInputUI$$inlined$addTextChangedListener$1 implements TextWatcher {
    final /* synthetic */ MnemonicTabView$loadInputUI$1 $addTag$1$inlined;
    final /* synthetic */ EditText $recoveryPhraseEditText$inlined;

    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
    }

    public MnemonicTabView$loadInputUI$$inlined$addTextChangedListener$1(MnemonicTabView$loadInputUI$1 mnemonicTabView$loadInputUI$1, EditText editText) {
        this.$addTag$1$inlined = mnemonicTabView$loadInputUI$1;
        this.$recoveryPhraseEditText$inlined = editText;
    }

    public void afterTextChanged(@Nullable Editable editable) {
        if (editable != null) {
            if (editable.length() == 0) {
                return;
            }
        }
        if ((editable != null && StringsKt.last(editable) == ',') || (editable != null && StringsKt.last(editable) == ' ')) {
            this.$addTag$1$inlined.invoke(editable.subSequence(0, editable.length() - 1).toString());
            this.$recoveryPhraseEditText$inlined.setText(null);
        }
    }
}
