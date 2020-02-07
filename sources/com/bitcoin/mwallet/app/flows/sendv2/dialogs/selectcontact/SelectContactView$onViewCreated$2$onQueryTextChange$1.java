package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact;

import android.widget.Filter.FilterListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "it", "", "onFilterComplete"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectContactView.kt */
final class SelectContactView$onViewCreated$2$onQueryTextChange$1 implements FilterListener {
    final /* synthetic */ SelectContactView$onViewCreated$2 this$0;

    SelectContactView$onViewCreated$2$onQueryTextChange$1(SelectContactView$onViewCreated$2 selectContactView$onViewCreated$2) {
        this.this$0 = selectContactView$onViewCreated$2;
    }

    public final void onFilterComplete(int i) {
        this.this$0.this$0.checkEmptyView(this.this$0.this$0.adapter.getFilteredList().isEmpty());
    }
}
