package com.bitcoin.mwallet.app.components.contactlist.view;

import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import com.bitcoin.mwallet.core.models.contact.Contact;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, mo37405d2 = {"com/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter$Companion$DIFF_CALLBACK$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactListAdapter.kt */
public final class ContactListAdapter$Companion$DIFF_CALLBACK$1 extends ItemCallback<Contact> {
    ContactListAdapter$Companion$DIFF_CALLBACK$1() {
    }

    public boolean areItemsTheSame(@NotNull Contact contact, @NotNull Contact contact2) {
        Intrinsics.checkParameterIsNotNull(contact, "oldItem");
        Intrinsics.checkParameterIsNotNull(contact2, "newItem");
        return Intrinsics.areEqual((Object) contact.getId(), (Object) contact2.getId());
    }

    public boolean areContentsTheSame(@NotNull Contact contact, @NotNull Contact contact2) {
        Intrinsics.checkParameterIsNotNull(contact, "oldItem");
        Intrinsics.checkParameterIsNotNull(contact2, "newItem");
        return Intrinsics.areEqual((Object) contact.getName(), (Object) contact2.getName()) && contact.getCoin() == contact2.getCoin() && Intrinsics.areEqual((Object) contact.getWalletType(), (Object) contact2.getWalletType()) && Intrinsics.areEqual((Object) contact.getEmail(), (Object) contact2.getEmail()) && Intrinsics.areEqual((Object) contact.getWebsite(), (Object) contact2.getWebsite()) && Intrinsics.areEqual((Object) contact.getContactDescription(), (Object) contact2.getContactDescription()) && contact.isAlwaysHidden() == contact2.isAlwaysHidden() && contact.isEditable() == contact2.isEditable() && contact.isKnownAddress() == contact2.isKnownAddress();
    }
}
