package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFees;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ9\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ&\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "", "getNetworkFeeInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "utxoSelector", "Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "(Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;)V", "getGetNetworkFeeInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "getUtxoRepository", "()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "getUtxoSelector", "()Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;", "isInsufficientCoinFee", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "cryptoInputAmount", "Ljava/math/BigDecimal;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "numOutputs", "", "extraFee", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/math/BigDecimal;Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/Integer;J)Z", "isInsufficientTokenFee", "tokenInputAmount", "tokenInfo", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: FeeValidatorInteractor.kt */
public final class FeeValidatorInteractor {
    @NotNull
    private final GetNetworkFeeInteractor getNetworkFeeInteractor;
    @NotNull
    private final UtxoRepository utxoRepository;
    @NotNull
    private final UtxoSelector utxoSelector;

    public FeeValidatorInteractor(@NotNull GetNetworkFeeInteractor getNetworkFeeInteractor2, @NotNull UtxoSelector utxoSelector2, @NotNull UtxoRepository utxoRepository2) {
        Intrinsics.checkParameterIsNotNull(getNetworkFeeInteractor2, "getNetworkFeeInteractor");
        Intrinsics.checkParameterIsNotNull(utxoSelector2, "utxoSelector");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        this.getNetworkFeeInteractor = getNetworkFeeInteractor2;
        this.utxoSelector = utxoSelector2;
        this.utxoRepository = utxoRepository2;
    }

    @NotNull
    public final GetNetworkFeeInteractor getGetNetworkFeeInteractor() {
        return this.getNetworkFeeInteractor;
    }

    @NotNull
    public final UtxoSelector getUtxoSelector() {
        return this.utxoSelector;
    }

    @NotNull
    public final UtxoRepository getUtxoRepository() {
        return this.utxoRepository;
    }

    public final boolean isInsufficientTokenFee(@NotNull WalletId walletId, @NotNull BigDecimal bigDecimal, @NotNull Slp slp, @NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "tokenInputAmount");
        Intrinsics.checkParameterIsNotNull(slp, "tokenInfo");
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Pair pair = (Pair) BuildersKt__BuildersKt.runBlocking$default(null, new FeeValidatorInteractor$isInsufficientTokenFee$networkFee$1(this, coin, null), 1, null);
        Satoshis fee = this.utxoSelector.select(((WalletUtxos) BuildersKt__BuildersKt.runBlocking$default(null, new FeeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1(this, walletId, null), 1, null)).getUtxos(), ((WalletSlpUtxos) BuildersKt__BuildersKt.runBlocking$default(null, new FeeValidatorInteractor$isInsufficientTokenFee$slpUtxos$1(this, walletId, slp, null), 1, null)).getUtxos(), slp, bigDecimal, ((NetworkFees) pair.getFirst()).getFee((NetworkFeeLevel) pair.getSecond())).getFee();
        if ((!Intrinsics.areEqual((Object) bigDecimal, (Object) BigDecimal.ZERO) || fee.getSatoshis() != 0) && fee.getSatoshis() == 0) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean isInsufficientCoinFee$default(FeeValidatorInteractor feeValidatorInteractor, WalletId walletId, BigDecimal bigDecimal, Coin coin, Integer num, long j, int i, Object obj) {
        if ((i & 8) != 0) {
            num = null;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            j = 0;
        }
        return feeValidatorInteractor.isInsufficientCoinFee(walletId, bigDecimal, coin, num2, j);
    }

    public final boolean isInsufficientCoinFee(@NotNull WalletId walletId, @NotNull BigDecimal bigDecimal, @NotNull Coin coin, @Nullable Integer num, long j) {
        WalletId walletId2 = walletId;
        BigDecimal bigDecimal2 = bigDecimal;
        Coin coin2 = coin;
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "cryptoInputAmount");
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Pair pair = (Pair) BuildersKt__BuildersKt.runBlocking$default(null, new FeeValidatorInteractor$isInsufficientCoinFee$networkFee$1(this, coin, null), 1, null);
        BigDecimal fee = ((NetworkFees) pair.getFirst()).getFee((NetworkFeeLevel) pair.getSecond());
        WalletUtxos walletUtxos = (WalletUtxos) BuildersKt__BuildersKt.runBlocking$default(null, new FeeValidatorInteractor$isInsufficientCoinFee$walletUtxos$1(this, walletId, null), 1, null);
        Satoshis fromCoins = Satoshis.Companion.fromCoins(bigDecimal);
        Satoshis fee2 = this.utxoSelector.select(coin, walletUtxos.getUtxos(), fromCoins, false, fee, num, j).getFee();
        StringBuilder sb = new StringBuilder();
        sb.append("fees: ");
        sb.append(fee2.getSatoshis());
        Timber.m426d(sb.toString(), new Object[0]);
        if (fee2.getSatoshis() == 0) {
            return true;
        }
        return false;
    }
}
