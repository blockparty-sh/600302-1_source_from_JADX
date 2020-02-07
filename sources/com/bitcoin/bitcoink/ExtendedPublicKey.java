package com.bitcoin.bitcoink;

import com.bitcoin.bitcoink.DerivationPath.Path;
import com.bitcoin.bitcoink.address.Address;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "", "key", "Lorg/bitcoinj/crypto/DeterministicKey;", "(Lorg/bitcoinj/crypto/DeterministicKey;)V", "getKey", "()Lorg/bitcoinj/crypto/DeterministicKey;", "base58", "", "network", "Lcom/bitcoin/bitcoink/Network;", "deriveChild", "Lcom/bitcoin/bitcoink/address/Address;", "path", "Lcom/bitcoin/bitcoink/DerivationPath;", "toString", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ExtendedPublicKey.kt */
public final class ExtendedPublicKey {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final DeterministicKey key;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/ExtendedPublicKey$Companion;", "", "()V", "fromBase58", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "base58", "", "network", "Lcom/bitcoin/bitcoink/Network;", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ExtendedPublicKey.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ExtendedPublicKey fromBase58(@NotNull String str, @NotNull Network network) {
            Intrinsics.checkParameterIsNotNull(str, "base58");
            Intrinsics.checkParameterIsNotNull(network, "network");
            DeterministicKey deserializeB58 = DeterministicKey.deserializeB58(str, network.getParams$bitcoink());
            Intrinsics.checkExpressionValueIsNotNull(deserializeB58, "DeterministicKey.deseria…8(base58, network.params)");
            return new ExtendedPublicKey(deserializeB58);
        }
    }

    public ExtendedPublicKey(@NotNull DeterministicKey deterministicKey) {
        Intrinsics.checkParameterIsNotNull(deterministicKey, "key");
        this.key = deterministicKey;
        this.key.isPubKeyOnly();
    }

    @NotNull
    public final DeterministicKey getKey() {
        return this.key;
    }

    @NotNull
    public final String base58(@NotNull Network network) {
        Intrinsics.checkParameterIsNotNull(network, "network");
        String serializePubB58 = this.key.serializePubB58(network.getParams$bitcoink());
        Intrinsics.checkExpressionValueIsNotNull(serializePubB58, "key.serializePubB58(network.params)");
        return serializePubB58;
    }

    @NotNull
    public final Address deriveChild(@NotNull Network network, @NotNull DerivationPath derivationPath) {
        Intrinsics.checkParameterIsNotNull(network, "network");
        Intrinsics.checkParameterIsNotNull(derivationPath, JsonDataKey_signMessage.path);
        DeterministicKey deterministicKey = this.key;
        for (Path path : derivationPath.getPath()) {
            int component1 = path.component1();
            if (path.component2()) {
                deterministicKey = deterministicKey.derive(component1);
                Intrinsics.checkExpressionValueIsNotNull(deterministicKey, "key.derive(number)");
            } else {
                deterministicKey = HDKeyDerivation.deriveChildKey(deterministicKey, new ChildNumber(component1, false));
                Intrinsics.checkExpressionValueIsNotNull(deterministicKey, "HDKeyDerivation.deriveCh…ildNumber(number, false))");
            }
        }
        com.bitcoin.bitcoink.address.Address.Companion companion = Address.Companion;
        String base58 = deterministicKey.toAddress(network.getParams$bitcoink()).toBase58();
        Intrinsics.checkExpressionValueIsNotNull(base58, "key.toAddress(network.params).toBase58()");
        Address tryParse = companion.tryParse(network, base58);
        if (tryParse != null) {
            return tryParse;
        }
        throw new IllegalStateException();
    }

    @NotNull
    public String toString() {
        String deterministicKey = this.key.toString();
        Intrinsics.checkExpressionValueIsNotNull(deterministicKey, "key.toString()");
        return deterministicKey;
    }
}
