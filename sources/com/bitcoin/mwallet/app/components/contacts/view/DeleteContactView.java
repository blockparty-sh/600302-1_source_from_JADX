package com.bitcoin.mwallet.app.components.contacts.view;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.contacts.presenter.ContactsPresenter;
import com.bitcoin.mwallet.core.models.contact.Contact;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/view/DeleteContactView;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "contact", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "presenter", "Lcom/bitcoin/mwallet/app/components/contacts/presenter/ContactsPresenter;", "(Lcom/bitcoin/mwallet/core/models/contact/Contact;Lcom/bitcoin/mwallet/app/components/contacts/presenter/ContactsPresenter;)V", "getContact", "()Lcom/bitcoin/mwallet/core/models/contact/Contact;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/contacts/presenter/ContactsPresenter;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "", "view", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DeleteContactView.kt */
public final class DeleteContactView extends AppCompatDialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    private final Contact contact;
    @NotNull
    private final ContactsPresenter presenter;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public final Contact getContact() {
        return this.contact;
    }

    @NotNull
    public final ContactsPresenter getPresenter() {
        return this.presenter;
    }

    public DeleteContactView(@NotNull Contact contact2, @NotNull ContactsPresenter contactsPresenter) {
        Intrinsics.checkParameterIsNotNull(contact2, "contact");
        Intrinsics.checkParameterIsNotNull(contactsPresenter, "presenter");
        this.contact = contact2;
        this.presenter = contactsPresenter;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(1);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            Window window = dialog2.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(0));
            }
        }
        setStyle(2, 16973829);
        super.onViewCreated(view, bundle);
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(C1018R.layout.fragment_delete_contact_view, viewGroup);
        TextView textView = (TextView) inflate.findViewById(C1018R.C1021id.cancelButton);
        if (textView != null) {
            textView.setOnClickListener(new DeleteContactView$onCreateView$1(this));
        }
        TextView textView2 = (TextView) inflate.findViewById(C1018R.C1021id.deleteButton);
        if (textView2 != null) {
            textView2.setOnClickListener(new DeleteContactView$onCreateView$2(this));
        }
        TextView textView3 = (TextView) inflate.findViewById(C1018R.C1021id.textDeleteInfo);
        if (textView3 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.contact.getName());
            sb.append(" (");
            String address = this.contact.getAddress();
            if (address != null) {
                String substring = address.substring(0, 20);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                sb.append(substring);
                sb.append(')');
                textView3.append(sb.toString());
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        inflate.setBackgroundColor(0);
        return inflate;
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(null);
            }
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            Window window2 = dialog2.getWindow();
            if (window2 != null) {
                window2.setBackgroundDrawableResource(17170445);
            }
        }
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }
}
