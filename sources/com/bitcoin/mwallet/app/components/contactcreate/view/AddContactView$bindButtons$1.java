package com.bitcoin.mwallet.app.components.contactcreate.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter;
import com.bitcoin.mwallet.core.entity.WalletType;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "radio", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AddContactView.kt */
final class AddContactView$bindButtons$1 implements OnClickListener {
    final /* synthetic */ EditText $address;
    final /* synthetic */ AddContactView this$0;

    AddContactView$bindButtons$1(AddContactView addContactView, EditText editText) {
        this.this$0 = addContactView;
        this.$address = editText;
    }

    public final void onClick(View view) {
        AddContactPresenter presenter = this.this$0.getPresenter();
        if (presenter != null) {
            EditText editText = this.$address;
            presenter.setRawInput(String.valueOf(editText != null ? editText.getText() : null), WalletType.BCH);
        }
    }
}
