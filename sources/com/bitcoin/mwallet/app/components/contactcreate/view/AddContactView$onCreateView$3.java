package com.bitcoin.mwallet.app.components.contactcreate.view;

import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor$ContactResult;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AddContactView.kt */
final class AddContactView$onCreateView$3<T> implements Observer<ContactResult> {
    final /* synthetic */ AddContactView this$0;

    AddContactView$onCreateView$3(AddContactView addContactView) {
        this.this$0 = addContactView;
    }

    public final void onChanged(ContactResult contactResult) {
        if (contactResult != null) {
            Toast.makeText(this.this$0.getContext(), contactResult.toString(), 0).show();
            AddContactPresenter presenter = this.this$0.getPresenter();
            if (presenter != null) {
                MutableLiveData daoResult = presenter.getDaoResult();
                if (daoResult != null) {
                    daoResult.postValue(null);
                    return;
                }
                return;
            }
            return;
        }
        this.this$0.dismiss();
    }
}
