package com.bitcoin.mwallet.core.models.slp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bitcoinj.uri.BitcoinURI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\b\u0018\u0000 '2\u00020\u0001:\u0001'B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\nHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\nHÖ\u0001J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"ø\u0001\u0000¢\u0006\u0002\u0010#J\u000e\u0010$\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"J\t\u0010%\u001a\u00020\u0005HÖ\u0001J\u000e\u0010&\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006("}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/slp/Slp;", "", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "ticker", "", "name", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "decimals", "", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;Ljava/lang/String;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;I)V", "getDecimals", "()I", "getName", "()Ljava/lang/String;", "getTicker", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toRawAmount", "Lkotlin/ULong;", "amount", "Ljava/math/BigDecimal;", "(Ljava/math/BigDecimal;)J", "toReadableAmount", "toString", "toTickerAmount", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"token_id", "wallet_id"}, tableName = "slp")
/* compiled from: Slp.kt */
public final class Slp {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final BigDecimal maxRawAmount = new BigDecimal(ULong.m670toStringimpl(-1));
    @ColumnInfo(name = "decimals")
    private final int decimals;
    @ColumnInfo(name = "name")
    @NotNull
    private final String name;
    @ColumnInfo(name = "ticker")
    @NotNull
    private final String ticker;
    @ColumnInfo(name = "token_id")
    @NotNull
    private final SlpId tokenId;
    @ColumnInfo(name = "wallet_id")
    @NotNull
    private final WalletId walletId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/slp/Slp$Companion;", "", "()V", "maxRawAmount", "Ljava/math/BigDecimal;", "getMaxRawAmount", "()Ljava/math/BigDecimal;", "createNew", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "ticker", "", "tokenName", "wallet", "decimals", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Slp.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BigDecimal getMaxRawAmount() {
            return Slp.maxRawAmount;
        }

        @NotNull
        public final Pair<Slp, C1261Wallet> createNew(@NotNull SlpId slpId, @NotNull String str, @NotNull String str2, @NotNull C1261Wallet wallet, int i) {
            Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
            Intrinsics.checkParameterIsNotNull(str, "ticker");
            Intrinsics.checkParameterIsNotNull(str2, "tokenName");
            Intrinsics.checkParameterIsNotNull(wallet, "wallet");
            Slp slp = new Slp(slpId, str, str2, wallet.getId(), i);
            return new Pair<>(slp, wallet);
        }
    }

    @NotNull
    public static /* synthetic */ Slp copy$default(Slp slp, SlpId slpId, String str, String str2, WalletId walletId2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            slpId = slp.tokenId;
        }
        if ((i2 & 2) != 0) {
            str = slp.ticker;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            str2 = slp.name;
        }
        String str4 = str2;
        if ((i2 & 8) != 0) {
            walletId2 = slp.walletId;
        }
        WalletId walletId3 = walletId2;
        if ((i2 & 16) != 0) {
            i = slp.decimals;
        }
        return slp.copy(slpId, str3, str4, walletId3, i);
    }

    @NotNull
    public final SlpId component1() {
        return this.tokenId;
    }

    @NotNull
    public final String component2() {
        return this.ticker;
    }

    @NotNull
    public final String component3() {
        return this.name;
    }

    @NotNull
    public final WalletId component4() {
        return this.walletId;
    }

    public final int component5() {
        return this.decimals;
    }

    @NotNull
    public final Slp copy(@NotNull SlpId slpId, @NotNull String str, @NotNull String str2, @NotNull WalletId walletId2, int i) {
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        Intrinsics.checkParameterIsNotNull(str, "ticker");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Slp slp = new Slp(slpId, str, str2, walletId2, i);
        return slp;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Slp) {
                Slp slp = (Slp) obj;
                if (Intrinsics.areEqual((Object) this.tokenId, (Object) slp.tokenId) && Intrinsics.areEqual((Object) this.ticker, (Object) slp.ticker) && Intrinsics.areEqual((Object) this.name, (Object) slp.name) && Intrinsics.areEqual((Object) this.walletId, (Object) slp.walletId)) {
                    if (this.decimals == slp.decimals) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        SlpId slpId = this.tokenId;
        int i = 0;
        int hashCode = (slpId != null ? slpId.hashCode() : 0) * 31;
        String str = this.ticker;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        WalletId walletId2 = this.walletId;
        if (walletId2 != null) {
            i = walletId2.hashCode();
        }
        return ((hashCode3 + i) * 31) + this.decimals;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Slp(tokenId=");
        sb.append(this.tokenId);
        sb.append(", ticker=");
        sb.append(this.ticker);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", decimals=");
        sb.append(this.decimals);
        sb.append(")");
        return sb.toString();
    }

    public Slp(@NotNull SlpId slpId, @NotNull String str, @NotNull String str2, @NotNull WalletId walletId2, int i) {
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        Intrinsics.checkParameterIsNotNull(str, "ticker");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this.tokenId = slpId;
        this.ticker = str;
        this.name = str2;
        this.walletId = walletId2;
        this.decimals = i;
    }

    @NotNull
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    @NotNull
    public final String getTicker() {
        return this.ticker;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    public final int getDecimals() {
        return this.decimals;
    }

    public final long toRawAmount(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, BitcoinURI.FIELD_AMOUNT);
        if (bigDecimal.compareTo(maxRawAmount) <= 0) {
            int scale = bigDecimal.scale();
            int i = this.decimals;
            if (scale <= i) {
                return ULong.m633constructorimpl(bigDecimal.scaleByPowerOfTen(i).longValue());
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.ticker);
            sb.append(" sippprts maximum ");
            sb.append(this.decimals);
            sb.append(" decimals but amount is ");
            sb.append(bigDecimal);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException("Amount is larger than 8 unsigned bytes");
    }

    @NotNull
    public final BigDecimal toReadableAmount(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, BitcoinURI.FIELD_AMOUNT);
        BigDecimal stripTrailingZeros = bigDecimal.scaleByPowerOfTen(-this.decimals).stripTrailingZeros();
        Intrinsics.checkExpressionValueIsNotNull(stripTrailingZeros, "amount.scaleByPowerOfTen…als).stripTrailingZeros()");
        return stripTrailingZeros;
    }

    @NotNull
    public final String toTickerAmount(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, BitcoinURI.FIELD_AMOUNT);
        String replace$default = StringsKt.replace$default(this.ticker, "\n", "", false, 4, (Object) null);
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        sb.append(BigDecimalKt.toPlainGroupedString(toReadableAmount(bigDecimal)));
        sb.append(' ');
        sb.append(replace$default);
        return sb.toString();
    }
}
