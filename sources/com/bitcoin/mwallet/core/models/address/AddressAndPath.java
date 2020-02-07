package com.bitcoin.mwallet.core.models.address;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.address.Address;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "", "address", "Lcom/bitcoin/bitcoink/address/Address;", "path", "Lcom/bitcoin/bitcoink/DerivationPath;", "(Lcom/bitcoin/bitcoink/address/Address;Lcom/bitcoin/bitcoink/DerivationPath;)V", "getAddress", "()Lcom/bitcoin/bitcoink/address/Address;", "getPath", "()Lcom/bitcoin/bitcoink/DerivationPath;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressAndPath.kt */
public final class AddressAndPath {
    @NotNull
    private final Address address;
    @NotNull
    private final DerivationPath path;

    @NotNull
    public static /* synthetic */ AddressAndPath copy$default(AddressAndPath addressAndPath, Address address2, DerivationPath derivationPath, int i, Object obj) {
        if ((i & 1) != 0) {
            address2 = addressAndPath.address;
        }
        if ((i & 2) != 0) {
            derivationPath = addressAndPath.path;
        }
        return addressAndPath.copy(address2, derivationPath);
    }

    @NotNull
    public final Address component1() {
        return this.address;
    }

    @NotNull
    public final DerivationPath component2() {
        return this.path;
    }

    @NotNull
    public final AddressAndPath copy(@NotNull Address address2, @NotNull DerivationPath derivationPath) {
        Intrinsics.checkParameterIsNotNull(address2, "address");
        Intrinsics.checkParameterIsNotNull(derivationPath, JsonDataKey_signMessage.path);
        return new AddressAndPath(address2, derivationPath);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.path, (java.lang.Object) r3.path) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.address.AddressAndPath
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.models.address.AddressAndPath r3 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r3
            com.bitcoin.bitcoink.address.Address r0 = r2.address
            com.bitcoin.bitcoink.address.Address r1 = r3.address
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.bitcoin.bitcoink.DerivationPath r0 = r2.path
            com.bitcoin.bitcoink.DerivationPath r3 = r3.path
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.address.AddressAndPath.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Address address2 = this.address;
        int i = 0;
        int hashCode = (address2 != null ? address2.hashCode() : 0) * 31;
        DerivationPath derivationPath = this.path;
        if (derivationPath != null) {
            i = derivationPath.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AddressAndPath(address=");
        sb.append(this.address);
        sb.append(", path=");
        sb.append(this.path);
        sb.append(")");
        return sb.toString();
    }

    public AddressAndPath(@NotNull Address address2, @NotNull DerivationPath derivationPath) {
        Intrinsics.checkParameterIsNotNull(address2, "address");
        Intrinsics.checkParameterIsNotNull(derivationPath, JsonDataKey_signMessage.path);
        this.address = address2;
        this.path = derivationPath;
    }

    @NotNull
    public final Address getAddress() {
        return this.address;
    }

    @NotNull
    public final DerivationPath getPath() {
        return this.path;
    }
}
