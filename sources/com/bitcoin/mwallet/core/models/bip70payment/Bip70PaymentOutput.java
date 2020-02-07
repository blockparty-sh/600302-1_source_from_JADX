package com.bitcoin.mwallet.core.models.bip70payment;

import com.bitcoin.mwallet.core.models.address.AddressAndOriginalText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentOutput;", "", "script", "", "amount", "", "address", "Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;", "(Ljava/lang/String;JLcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;)V", "getAddress", "()Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;", "getAmount", "()J", "getScript", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentOutput.kt */
public final class Bip70PaymentOutput {
    @Nullable
    private final AddressAndOriginalText address;
    private final long amount;
    @NotNull
    private final String script;

    @NotNull
    public static /* synthetic */ Bip70PaymentOutput copy$default(Bip70PaymentOutput bip70PaymentOutput, String str, long j, AddressAndOriginalText addressAndOriginalText, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bip70PaymentOutput.script;
        }
        if ((i & 2) != 0) {
            j = bip70PaymentOutput.amount;
        }
        if ((i & 4) != 0) {
            addressAndOriginalText = bip70PaymentOutput.address;
        }
        return bip70PaymentOutput.copy(str, j, addressAndOriginalText);
    }

    @NotNull
    public final String component1() {
        return this.script;
    }

    public final long component2() {
        return this.amount;
    }

    @Nullable
    public final AddressAndOriginalText component3() {
        return this.address;
    }

    @NotNull
    public final Bip70PaymentOutput copy(@NotNull String str, long j, @Nullable AddressAndOriginalText addressAndOriginalText) {
        Intrinsics.checkParameterIsNotNull(str, "script");
        return new Bip70PaymentOutput(str, j, addressAndOriginalText);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Bip70PaymentOutput) {
                Bip70PaymentOutput bip70PaymentOutput = (Bip70PaymentOutput) obj;
                if (Intrinsics.areEqual((Object) this.script, (Object) bip70PaymentOutput.script)) {
                    if (!(this.amount == bip70PaymentOutput.amount) || !Intrinsics.areEqual((Object) this.address, (Object) bip70PaymentOutput.address)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.script;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.amount;
        int i2 = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        AddressAndOriginalText addressAndOriginalText = this.address;
        if (addressAndOriginalText != null) {
            i = addressAndOriginalText.hashCode();
        }
        return i2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bip70PaymentOutput(script=");
        sb.append(this.script);
        sb.append(", amount=");
        sb.append(this.amount);
        sb.append(", address=");
        sb.append(this.address);
        sb.append(")");
        return sb.toString();
    }

    public Bip70PaymentOutput(@NotNull String str, long j, @Nullable AddressAndOriginalText addressAndOriginalText) {
        Intrinsics.checkParameterIsNotNull(str, "script");
        this.script = str;
        this.amount = j;
        this.address = addressAndOriginalText;
    }

    @NotNull
    public final String getScript() {
        return this.script;
    }

    public final long getAmount() {
        return this.amount;
    }

    @Nullable
    public final AddressAndOriginalText getAddress() {
        return this.address;
    }
}
