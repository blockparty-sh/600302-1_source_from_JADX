package com.bitcoin.mwallet.core.models.p009tx;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "", "iconContentDescription", "", "iconResourceId", "(Ljava/lang/String;III)V", "getIconContentDescription", "()I", "getIconResourceId", "SENT", "RECEIVED", "MOVED", "UNKNOWN", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.models.tx.TxAction */
/* compiled from: TxAction.kt */
public enum TxAction {
    SENT(C1018R.string.historictransaction_sent_action_icon_content_description, C1018R.C1020drawable.ic_tx_sent_blue),
    RECEIVED(C1018R.string.historictransaction_received_action_icon_content_description, C1018R.C1020drawable.ic_tx_received),
    MOVED(C1018R.string.general_todo, C1018R.C1020drawable.ic_tx_moved),
    UNKNOWN(C1018R.string.general_todo, C1018R.C1020drawable.ic_tx_moved);
    
    private final int iconContentDescription;
    private final int iconResourceId;

    private TxAction(@StringRes int i, @DrawableRes int i2) {
        this.iconContentDescription = i;
        this.iconResourceId = i2;
    }

    public final int getIconContentDescription() {
        return this.iconContentDescription;
    }

    public final int getIconResourceId() {
        return this.iconResourceId;
    }
}
