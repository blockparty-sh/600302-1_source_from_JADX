package com.bitcoin.bitcoink;

import com.bitcoin.bitcoink.DerivationPath.Path;
import com.bitcoin.bitcoink.address.Address;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\"\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0006\u0010!\u001a\u00020\"J\t\u0010#\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006%"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/PrivateKey;", "", "key", "Lorg/bitcoinj/crypto/DeterministicKey;", "(Lorg/bitcoinj/crypto/DeterministicKey;)V", "chainCode", "", "getChainCode", "()[B", "hex", "Lcom/bitcoin/bitcoink/Hex;", "getHex", "()Lcom/bitcoin/bitcoink/Hex;", "hex$delegate", "Lkotlin/Lazy;", "getKey", "()Lorg/bitcoinj/crypto/DeterministicKey;", "base58", "", "network", "Lcom/bitcoin/bitcoink/Network;", "component1", "copy", "deriveChild", "Lkotlin/Pair;", "Lcom/bitcoin/bitcoink/address/Address;", "path", "Lcom/bitcoin/bitcoink/DerivationPath;", "equals", "", "other", "hashCode", "", "toPubKey", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "toString", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PrivateKey.kt */
public final class PrivateKey {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PrivateKey.class), "hex", "getHex()Lcom/bitcoin/bitcoink/Hex;"))};
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final byte[] chainCode;
    @NotNull
    private final Lazy hex$delegate;
    @NotNull
    private final DeterministicKey key;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/PrivateKey$Companion;", "", "()V", "dummyKey", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "masterFromPrivateKeyBytesAndChainCode", "Lcom/bitcoin/bitcoink/PrivateKey;", "privateKey", "", "chainCode", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: PrivateKey.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PrivateKey masterFromPrivateKeyBytesAndChainCode(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
            Intrinsics.checkParameterIsNotNull(bArr, "privateKey");
            Intrinsics.checkParameterIsNotNull(bArr2, "chainCode");
            DeterministicKey createMasterPrivKeyFromBytes = HDKeyDerivation.createMasterPrivKeyFromBytes(bArr, bArr2);
            Intrinsics.checkExpressionValueIsNotNull(createMasterPrivKeyFromBytes, "HDKeyDerivation.createMa…es(privateKey, chainCode)");
            return new PrivateKey(createMasterPrivKeyFromBytes);
        }

        @NotNull
        public final ExtendedPublicKey dummyKey() {
            return ExtendedPublicKey.Companion.fromBase58("xpub6CoMyxJRa42jUoKLkfj3EXCyr8CNwFHoB1dYhUhsDycuJxZKvtyHM7suRbC1om7HE59MNFdgUt7ebpXk4MMGEdy5wty67cME2WSFJ5Krxbv", Network.MAIN);
        }
    }

    @NotNull
    public static /* synthetic */ PrivateKey copy$default(PrivateKey privateKey, DeterministicKey deterministicKey, int i, Object obj) {
        if ((i & 1) != 0) {
            deterministicKey = privateKey.key;
        }
        return privateKey.copy(deterministicKey);
    }

    @NotNull
    public final DeterministicKey component1() {
        return this.key;
    }

    @NotNull
    public final PrivateKey copy(@NotNull DeterministicKey deterministicKey) {
        Intrinsics.checkParameterIsNotNull(deterministicKey, "key");
        return new PrivateKey(deterministicKey);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.key, (java.lang.Object) ((com.bitcoin.bitcoink.PrivateKey) r2).key) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.bitcoink.PrivateKey
            if (r0 == 0) goto L_0x0013
            com.bitcoin.bitcoink.PrivateKey r2 = (com.bitcoin.bitcoink.PrivateKey) r2
            org.bitcoinj.crypto.DeterministicKey r0 = r1.key
            org.bitcoinj.crypto.DeterministicKey r2 = r2.key
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.bitcoink.PrivateKey.equals(java.lang.Object):boolean");
    }

    @NotNull
    public final Hex getHex() {
        Lazy lazy = this.hex$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Hex) lazy.getValue();
    }

    public int hashCode() {
        DeterministicKey deterministicKey = this.key;
        if (deterministicKey != null) {
            return deterministicKey.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PrivateKey(key=");
        sb.append(this.key);
        sb.append(")");
        return sb.toString();
    }

    public PrivateKey(@NotNull DeterministicKey deterministicKey) {
        Intrinsics.checkParameterIsNotNull(deterministicKey, "key");
        this.key = deterministicKey;
        if (this.key.hasPrivKey()) {
            byte[] chainCode2 = this.key.getChainCode();
            Intrinsics.checkExpressionValueIsNotNull(chainCode2, "key.chainCode");
            this.chainCode = chainCode2;
            this.hex$delegate = LazyKt.lazy(new PrivateKey$hex$2(this));
            return;
        }
        throw new IllegalArgumentException("Is pub key only");
    }

    @NotNull
    public final DeterministicKey getKey() {
        return this.key;
    }

    @NotNull
    public final byte[] getChainCode() {
        return this.chainCode;
    }

    @NotNull
    public final String base58(@NotNull Network network) {
        Intrinsics.checkParameterIsNotNull(network, "network");
        String serializePrivB58 = this.key.serializePrivB58(network.getParams$bitcoink());
        Intrinsics.checkExpressionValueIsNotNull(serializePrivB58, "key.serializePrivB58(network.params)");
        return serializePrivB58;
    }

    @NotNull
    public final Pair<PrivateKey, Address> deriveChild(@NotNull Network network, @NotNull DerivationPath derivationPath) {
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
            return new Pair<>(new PrivateKey(deterministicKey), tryParse);
        }
        throw new IllegalStateException();
    }

    @NotNull
    public final ExtendedPublicKey toPubKey() {
        DeterministicKey dropPrivateBytes = this.key.dropPrivateBytes();
        DeterministicKey parent = this.key.getParent();
        if (parent == null) {
            Intrinsics.throwNpe();
        }
        return new ExtendedPublicKey(new DeterministicKey(dropPrivateBytes, parent.dropPrivateBytes()));
    }
}
