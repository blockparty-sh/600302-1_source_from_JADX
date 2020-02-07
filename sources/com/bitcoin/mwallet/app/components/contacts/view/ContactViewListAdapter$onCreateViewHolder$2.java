package com.bitcoin.mwallet.app.components.contacts.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.core.models.contact.Contact;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "contactView", "Landroid/view/View;", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ContactViewListAdapter.kt */
final class ContactViewListAdapter$onCreateViewHolder$2 implements OnClickListener {
    final /* synthetic */ ContactViewListAdapter this$0;

    ContactViewListAdapter$onCreateViewHolder$2(ContactViewListAdapter contactViewListAdapter) {
        this.this$0 = contactViewListAdapter;
    }

    public final void onClick(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "contactView");
        Object tag = view.getTag();
        if (!(tag instanceof Contact)) {
            tag = null;
        }
        Contact contact = (Contact) tag;
        if (contact != null) {
            this.this$0.clickedListener.onContactClicked(contact);
        }
    }
}
