package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact;

import android.widget.Filter.FilterListener;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.contact.Contact;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "it", "", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectContactView.kt */
final class SelectContactView$onViewCreated$1<T> implements Observer<List<? extends Contact>> {
    final /* synthetic */ SelectContactView this$0;

    SelectContactView$onViewCreated$1(SelectContactView selectContactView) {
        this.this$0 = selectContactView;
    }

    public final void onChanged(List<Contact> list) {
        Collection collection = list;
        if (!(collection == null || collection.isEmpty())) {
            this.this$0.adapter.submitList(list);
        }
        this.this$0.adapter.getFilter().filter("", new FilterListener(this) {
            final /* synthetic */ SelectContactView$onViewCreated$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onFilterComplete(int i) {
                this.this$0.this$0.checkEmptyView(this.this$0.this$0.adapter.getFilteredList().isEmpty());
            }
        });
    }
}
