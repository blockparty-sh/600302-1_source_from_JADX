package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.PrivateKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/bitcoink/PrivateKey;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: PrivateKeyRepository.kt */
final class PrivateKeyRepository$getPrivateKey$3 extends Lambda implements Function0<PrivateKey> {
    final /* synthetic */ DerivationPath $addressPath;
    final /* synthetic */ Network $walletNetwork;
    final /* synthetic */ ObjectRef $walletPrivateKey;

    PrivateKeyRepository$getPrivateKey$3(ObjectRef objectRef, Network network, DerivationPath derivationPath) {
        this.$walletPrivateKey = objectRef;
        this.$walletNetwork = network;
        this.$addressPath = derivationPath;
        super(0);
    }

    @NotNull
    public final PrivateKey invoke() {
        return (PrivateKey) ((PrivateKey) this.$walletPrivateKey.element).deriveChild(this.$walletNetwork, this.$addressPath).getFirst();
    }
}
