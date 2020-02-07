package com.bitcoin.mwallet.app.components.contactlist.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Filter;
import android.widget.Filter.FilterListener;
import androidx.appcompat.widget.SearchView;
import com.bitcoin.mwallet.core.entity.WalletType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "radio", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ContactListView.kt */
final class ContactListView$onViewCreated$4 implements OnClickListener {
    final /* synthetic */ ObjectRef $searchView;
    final /* synthetic */ ContactListView this$0;

    ContactListView$onViewCreated$4(ContactListView contactListView, ObjectRef objectRef) {
        this.this$0 = contactListView;
        this.$searchView = objectRef;
    }

    public final void onClick(View view) {
        this.this$0.adapter.setWalletType(WalletType.BTC);
        Filter filter = this.this$0.adapter.getFilter();
        SearchView searchView = (SearchView) this.$searchView.element;
        Intrinsics.checkExpressionValueIsNotNull(searchView, "searchView");
        filter.filter(searchView.getQuery(), new FilterListener(this) {
            final /* synthetic */ ContactListView$onViewCreated$4 this$0;

            {
                this.this$0 = r1;
            }

            public final void onFilterComplete(int i) {
                this.this$0.this$0.checkEmptyView(this.this$0.this$0.adapter.getFilteredList().isEmpty());
            }
        });
        ContactClickedListener access$getListener$p = this.this$0.listener;
        if (access$getListener$p != null) {
            access$getListener$p.onWalletTypeClicked(WalletType.BTC);
        }
    }
}
