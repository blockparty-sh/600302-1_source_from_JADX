package com.bitcoin.mwallet.core.models.networkfee;

import androidx.annotation.StringRes;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "", "presentableNameResId", "", "(Ljava/lang/String;II)V", "getPresentableNameResId", "()I", "PRIORITY", "NORMAL", "ECONOMY", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NetworkFeeLevel.kt */
public enum NetworkFeeLevel {
    PRIORITY(C1018R.string.settings_network_fee_priority),
    NORMAL(C1018R.string.settings_network_fee_normal),
    ECONOMY(C1018R.string.settings_network_fee_economy);
    
    private final int presentableNameResId;

    private NetworkFeeLevel(@StringRes int i) {
        this.presentableNameResId = i;
    }

    public final int getPresentableNameResId() {
        return this.presentableNameResId;
    }
}
