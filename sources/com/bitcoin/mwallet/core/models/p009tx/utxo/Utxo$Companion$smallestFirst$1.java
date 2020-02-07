package com.bitcoin.mwallet.core.models.p009tx.utxo;

import java.util.Comparator;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "o1", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "kotlin.jvm.PlatformType", "o2", "compare"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.models.tx.utxo.Utxo$Companion$smallestFirst$1 */
/* compiled from: Utxo.kt */
final class Utxo$Companion$smallestFirst$1<T> implements Comparator<Utxo> {
    public static final Utxo$Companion$smallestFirst$1 INSTANCE = new Utxo$Companion$smallestFirst$1();

    Utxo$Companion$smallestFirst$1() {
    }

    public final int compare(Utxo utxo, Utxo utxo2) {
        return (utxo.getSatoshis().getSatoshis() > utxo2.getSatoshis().getSatoshis() ? 1 : (utxo.getSatoshis().getSatoshis() == utxo2.getSatoshis().getSatoshis() ? 0 : -1));
    }
}
