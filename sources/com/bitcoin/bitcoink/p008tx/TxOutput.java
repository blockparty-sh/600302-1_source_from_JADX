package com.bitcoin.bitcoink.p008tx;

import com.bitcoin.bitcoink.address.Address;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/tx/TxOutput;", "", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "address", "Lcom/bitcoin/bitcoink/address/Address;", "script", "", "(Lcom/bitcoin/bitcoink/tx/Satoshis;Lcom/bitcoin/bitcoink/address/Address;[B)V", "getAddress", "()Lcom/bitcoin/bitcoink/address/Address;", "getSatoshis", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "getScript", "()[B", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.bitcoink.tx.TxOutput */
/* compiled from: TxOutput.kt */
public final class TxOutput {
    @NotNull
    private final Address address;
    @NotNull
    private final Satoshis satoshis;
    @NotNull
    private final byte[] script;

    public TxOutput(@NotNull Satoshis satoshis2, @NotNull Address address2, @NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        Intrinsics.checkParameterIsNotNull(address2, "address");
        Intrinsics.checkParameterIsNotNull(bArr, "script");
        this.satoshis = satoshis2;
        this.address = address2;
        this.script = bArr;
    }

    @NotNull
    public final Address getAddress() {
        return this.address;
    }

    @NotNull
    public final Satoshis getSatoshis() {
        return this.satoshis;
    }

    @NotNull
    public final byte[] getScript() {
        return this.script;
    }
}
