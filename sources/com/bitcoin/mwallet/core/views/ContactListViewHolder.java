package com.bitcoin.mwallet.core.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/ContactListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "contactIcon", "Landroid/widget/ImageView;", "contactName", "Landroid/widget/TextView;", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactListViewHolder.kt */
public final class ContactListViewHolder extends ViewHolder {
    private final ImageView contactIcon;
    private final TextView contactName;
    @NotNull
    private final View view;

    public ContactListViewHolder(@NotNull View view2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        super(view2);
        this.view = view2;
        View findViewById = this.view.findViewById(C1018R.C1021id.contactName);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.contactName)");
        this.contactName = (TextView) findViewById;
        View findViewById2 = this.view.findViewById(C1018R.C1021id.contactIcon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.contactIcon)");
        this.contactIcon = (ImageView) findViewById2;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    public final void bind(@NotNull Contact contact) {
        Intrinsics.checkParameterIsNotNull(contact, Params.IAP_ITEM);
        this.contactName.setText(contact.getName());
        if (Intrinsics.areEqual((Object) contact.getName(), (Object) "SatoshiDice")) {
            this.contactIcon.setImageResource(C1018R.C1020drawable.ic_satoshi_dice_round);
        }
        if (contact.getCoin() == Coin.BCH) {
            this.contactIcon.setImageResource(C1018R.C1020drawable.logo_bch_green);
        } else if (contact.getCoin() == Coin.BTC) {
            this.contactIcon.setImageResource(C1018R.C1020drawable.logo_btc);
        }
    }
}
