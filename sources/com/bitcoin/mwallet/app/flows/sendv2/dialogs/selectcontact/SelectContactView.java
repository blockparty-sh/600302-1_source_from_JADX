package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.contactlist.builder.ContactListBuilder;
import com.bitcoin.mwallet.app.components.contactlist.presenter.ContactListPresenter;
import com.bitcoin.mwallet.app.components.contactlist.view.ContactClickedListener;
import com.bitcoin.mwallet.app.components.contactlist.view.ContactListAdapter;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.views.FullScreenRoundedBottomSheetDialogFragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006$"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactView;", "Lcom/bitcoin/mwallet/core/views/FullScreenRoundedBottomSheetDialogFragment;", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactClickedListener;", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactListener;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactListener;)V", "adapter", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter;", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactListener;", "presenter", "Lcom/bitcoin/mwallet/app/components/contactlist/presenter/ContactListPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/contactlist/presenter/ContactListPresenter;", "setPresenter", "(Lcom/bitcoin/mwallet/app/components/contactlist/presenter/ContactListPresenter;)V", "checkEmptyView", "", "isEmpty", "", "onContactClicked", "contect", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "onWalletTypeClicked", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectContactView.kt */
public final class SelectContactView extends FullScreenRoundedBottomSheetDialogFragment implements ContactClickedListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ContactListAdapter adapter = new ContactListAdapter();
    @NotNull
    private final SelectContactListener listener;
    @Nullable
    private ContactListPresenter presenter;

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

    public void onWalletTypeClicked(@NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
    }

    @NotNull
    public final SelectContactListener getListener() {
        return this.listener;
    }

    public SelectContactView(@NotNull SelectContactListener selectContactListener) {
        Intrinsics.checkParameterIsNotNull(selectContactListener, CastExtraArgs.LISTENER);
        this.listener = selectContactListener;
    }

    @Nullable
    public final ContactListPresenter getPresenter() {
        return this.presenter;
    }

    public final void setPresenter(@Nullable ContactListPresenter contactListPresenter) {
        this.presenter = contactListPresenter;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return getLayoutInflater().inflate(C1018R.layout.fragment_screen_sendv2_selectcontact, null);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(ContactListBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…tListBuilder::class.java)");
        this.presenter = ((ContactListBuilder) viewModel).getPresenter();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C1018R.C1021id.contactList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "contactList");
        recyclerView.setAdapter(this.adapter);
        this.adapter.setOnClickListener(this);
        ContactListPresenter contactListPresenter = this.presenter;
        if (contactListPresenter != null) {
            LiveData contacts = contactListPresenter.getContacts();
            if (contacts != null) {
                contacts.observe(this, new SelectContactView$onViewCreated$1(this));
            }
        }
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = (SearchView) view.findViewById(C1018R.C1021id.keywordSearchView);
        SearchView searchView = (SearchView) objectRef.element;
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SelectContactView$onViewCreated$2(this));
        }
        RadioButton radioButton = (RadioButton) view.findViewById(C1018R.C1021id.bitcoinCashFilterButton);
        RadioButton radioButton2 = (RadioButton) view.findViewById(C1018R.C1021id.bitcoinFilterButton);
        RadioButton radioButton3 = (RadioButton) view.findViewById(C1018R.C1021id.slpFilterButton);
        TextView textView = (TextView) view.findViewById(C1018R.C1021id.addContactButton);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
        if (radioButton != null) {
            radioButton.setOnClickListener(new SelectContactView$onViewCreated$3(this, objectRef));
        }
        if (radioButton2 != null) {
            radioButton2.setOnClickListener(new SelectContactView$onViewCreated$4(this, objectRef));
        }
        if (radioButton3 != null) {
            radioButton3.setOnClickListener(new SelectContactView$onViewCreated$5(this, objectRef));
        }
        if (textView != null) {
            textView.setOnClickListener(new SelectContactView$onViewCreated$6(this));
        }
    }

    /* access modifiers changed from: private */
    public final void checkEmptyView(boolean z) {
        if (z) {
            View view = getView();
            if (view != null) {
                RecyclerView recyclerView = (RecyclerView) view.findViewById(C1018R.C1021id.contactList);
                if (recyclerView != null) {
                    recyclerView.setVisibility(8);
                }
            }
            View view2 = getView();
            if (view2 != null) {
                LinearLayout linearLayout = (LinearLayout) view2.findViewById(C1018R.C1021id.contactEmptyView);
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                    return;
                }
                return;
            }
            return;
        }
        View view3 = getView();
        if (view3 != null) {
            RecyclerView recyclerView2 = (RecyclerView) view3.findViewById(C1018R.C1021id.contactList);
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(0);
            }
        }
        View view4 = getView();
        if (view4 != null) {
            LinearLayout linearLayout2 = (LinearLayout) view4.findViewById(C1018R.C1021id.contactEmptyView);
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
        }
    }

    public void onContactClicked(@NotNull Contact contact) {
        Intrinsics.checkParameterIsNotNull(contact, "contect");
        this.listener.onContactSelected(contact);
        dismiss();
    }
}
