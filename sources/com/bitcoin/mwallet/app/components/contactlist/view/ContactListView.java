package com.bitcoin.mwallet.app.components.contactlist.view;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.contactlist.builder.ContactListBuilder;
import com.bitcoin.mwallet.app.components.contactlist.presenter.ContactListPresenter;
import com.bitcoin.mwallet.app.viper.ComponentView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/contactlist/builder/ContactListBuilder;", "Lcom/bitcoin/mwallet/app/components/contactlist/presenter/ContactListPresenter;", "()V", "adapter", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter;", "listener", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactClickedListener;", "checkEmptyView", "", "isEmpty", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "setOnContactClickedListener", "newListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactListView.kt */
public final class ContactListView extends ComponentView<ContactListBuilder, ContactListPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ContactListAdapter adapter = new ContactListAdapter();
    /* access modifiers changed from: private */
    public ContactClickedListener listener;

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

    public ContactListView() {
        super(C1018R.layout.fragment_component_contactlist, Reflection.getOrCreateKotlinClass(ContactListBuilder.class));
    }

    public final void setOnContactClickedListener(@NotNull ContactClickedListener contactClickedListener) {
        Intrinsics.checkParameterIsNotNull(contactClickedListener, "newListener");
        this.listener = contactClickedListener;
        this.adapter.setOnClickListener(contactClickedListener);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C1018R.C1021id.contactList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "contactList");
        recyclerView.setAdapter(this.adapter);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ContactListPresenter contactListPresenter = (ContactListPresenter) getPresenter();
        if (contactListPresenter != null) {
            LiveData contacts = contactListPresenter.getContacts();
            if (contacts != null) {
                contacts.observe(this, new ContactListView$onViewCreated$1(this));
            }
        }
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = (SearchView) view.findViewById(C1018R.C1021id.keywordSearchView);
        SearchView searchView = (SearchView) objectRef.element;
        if (searchView != null) {
            searchView.setOnQueryTextListener(new ContactListView$onViewCreated$2(this));
        }
        RadioButton radioButton = (RadioButton) view.findViewById(C1018R.C1021id.bitcoinCashFilterButton);
        RadioButton radioButton2 = (RadioButton) view.findViewById(C1018R.C1021id.bitcoinFilterButton);
        RadioButton radioButton3 = (RadioButton) view.findViewById(C1018R.C1021id.slpFilterButton);
        TextView textView = (TextView) view.findViewById(C1018R.C1021id.addContactButton);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
        if (radioButton != null) {
            radioButton.setOnClickListener(new ContactListView$onViewCreated$3(this, objectRef));
        }
        if (radioButton2 != null) {
            radioButton2.setOnClickListener(new ContactListView$onViewCreated$4(this, objectRef));
        }
        if (radioButton3 != null) {
            radioButton3.setOnClickListener(new ContactListView$onViewCreated$5(this, objectRef));
        }
        if (textView != null) {
            textView.setOnClickListener(new ContactListView$onViewCreated$6(this));
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
}
