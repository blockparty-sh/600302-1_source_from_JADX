package com.bitcoin.mwallet.app.components.contacts.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.contactlist.view.ContactClickedListener;
import com.bitcoin.mwallet.app.components.contactlist.view.ContactListView;
import com.bitcoin.mwallet.app.components.contacts.builder.ContactsBuilder;
import com.bitcoin.mwallet.app.components.contacts.presenter.ContactsPresenter;
import com.bitcoin.mwallet.app.viper.ComponentView;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.app.viper.ScreenView.Companion;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.leanplum.internal.Constants.Params;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/view/ContactsView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/contacts/builder/ContactsBuilder;", "Lcom/bitcoin/mwallet/app/components/contacts/presenter/ContactsPresenter;", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactClickedListener;", "()V", "contactListView", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListView;", "getContactListView", "()Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListView;", "setContactListView", "(Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListView;)V", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onContactClicked", "item", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "onWalletTypeClicked", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactsView.kt */
public final class ContactsView extends ComponentView<ContactsBuilder, ContactsPresenter> implements ContactClickedListener {
    private HashMap _$_findViewCache;
    @Nullable
    private ContactListView contactListView;

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

    public static final /* synthetic */ ContactsPresenter access$getPresenter$p(ContactsView contactsView) {
        return (ContactsPresenter) contactsView.getPresenter();
    }

    public ContactsView() {
        super(C1018R.layout.fragment_component_contacts, Reflection.getOrCreateKotlinClass(ContactsBuilder.class));
    }

    @Nullable
    public final ContactListView getContactListView() {
        return this.contactListView;
    }

    public final void setContactListView(@Nullable ContactListView contactListView2) {
        this.contactListView = contactListView2;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(C1018R.layout.fragment_component_contacts, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ntacts, container, false)");
        return inflate;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ((Button) _$_findCachedViewById(C1018R.C1021id.addContactButton)).setOnClickListener(new ContactsView$onViewCreated$1(this));
        ((ContactsPresenter) getPresenter()).getDaoResult().observe(this, new ContactsView$onViewCreated$2(this));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        Object obj;
        super.onActivityCreated(bundle);
        Companion companion = ScreenView.Companion;
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "fragment.childFragmentManager");
        List fragments = childFragmentManager.getFragments();
        Intrinsics.checkExpressionValueIsNotNull(fragments, "fragment.childFragmentManager.fragments");
        Iterator it = fragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Fragment) obj) instanceof ContactListView) {
                break;
            }
        }
        if (!(obj instanceof ContactListView)) {
            obj = null;
        }
        this.contactListView = (ContactListView) obj;
        ContactListView contactListView2 = this.contactListView;
        if (contactListView2 != null) {
            contactListView2.setOnContactClickedListener(this);
        }
    }

    public void onContactClicked(@NotNull Contact contact) {
        Intrinsics.checkParameterIsNotNull(contact, Params.IAP_ITEM);
        new DeleteContactView(contact, (ContactsPresenter) getPresenter()).show(getChildFragmentManager(), "delete_contact");
    }

    public void onWalletTypeClicked(@NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        ((ContactsPresenter) getPresenter()).selectWalletType(walletType);
    }
}
