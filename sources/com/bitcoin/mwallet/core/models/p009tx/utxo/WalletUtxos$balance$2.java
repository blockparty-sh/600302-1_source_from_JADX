package com.bitcoin.mwallet.core.models.p009tx.utxo;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos$balance$2 */
/* compiled from: WalletUtxos.kt */
final class WalletUtxos$balance$2 extends Lambda implements Function0<Satoshis> {
    final /* synthetic */ WalletUtxos this$0;

    WalletUtxos$balance$2(WalletUtxos walletUtxos) {
        this.this$0 = walletUtxos;
        super(0);
    }

    @NotNull
    public final Satoshis invoke() {
        long j = 0;
        for (Utxo satoshis : this.this$0.getUtxos()) {
            j += satoshis.getSatoshis().getSatoshis();
        }
        return new Satoshis(j);
    }
}
