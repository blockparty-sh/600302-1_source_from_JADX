package com.bitcoin.mwallet.app.components.contactlist.view;

import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.contact.Contact;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactClickedListener;", "", "onContactClicked", "", "contact", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "onWalletTypeClicked", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactClickedListener.kt */
public interface ContactClickedListener {
    void onContactClicked(@NotNull Contact contact);

    void onWalletTypeClicked(@NotNull WalletType walletType);
}
