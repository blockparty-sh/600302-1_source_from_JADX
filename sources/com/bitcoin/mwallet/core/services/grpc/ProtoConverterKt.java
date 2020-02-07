package com.bitcoin.mwallet.core.services.grpc;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.address.Address.Companion;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.ExchangeRates;
import com.bitcoin.mwallet.ResponseError;
import com.bitcoin.mwallet.ResponseErrorType;
import com.bitcoin.mwallet.Token;
import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.TxHistory.TxType;
import com.bitcoin.mwallet.Utxo;
import com.bitcoin.mwallet.Utxo.UtxoAddress;
import com.bitcoin.mwallet.core.extensions.StringKt;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse;
import com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse.ErrorType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0000\u001a\u00020\u0005*\u00020\u0006\u001a\f\u0010\u0000\u001a\u00020\u0007*\u00020\bH\u0002\u001a\u0012\u0010\u0000\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001\u001a\n\u0010\u0000\u001a\u00020\f*\u00020\r\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0014\u001a\n\u0010\u0012\u001a\u00020\u0002*\u00020\u0001\u001a\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0017"}, mo37405d2 = {"toDomain", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/mwallet/Coin;", "Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateResponse;", "Lcom/bitcoin/mwallet/ExchangeRates;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "Lcom/bitcoin/mwallet/ResponseError;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse$ErrorType;", "Lcom/bitcoin/mwallet/ResponseErrorType;", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "Lcom/bitcoin/mwallet/TxHistory;", "coin", "Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "Lcom/bitcoin/mwallet/TxHistory$TxType;", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "Lcom/bitcoin/mwallet/Utxo;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "toProto", "Lcom/bitcoin/mwallet/Network;", "Lcom/bitcoin/bitcoink/Network;", "toSlpDomain", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxo;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: ProtoConverter.kt */
public final class ProtoConverterKt {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Network.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[com.bitcoin.mwallet.Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$3 = new int[TxType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$4 = new int[ResponseErrorType.values().length];

        static {
            $EnumSwitchMapping$0[Network.MAIN.ordinal()] = 1;
            $EnumSwitchMapping$0[Network.TEST.ordinal()] = 2;
            $EnumSwitchMapping$1[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$1[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$2[com.bitcoin.mwallet.Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$2[com.bitcoin.mwallet.Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$3[TxType.TX_TYPE_SENT.ordinal()] = 1;
            $EnumSwitchMapping$3[TxType.TX_TYPE_RECEIVED.ordinal()] = 2;
            $EnumSwitchMapping$3[TxType.TX_TYPE_MOVED.ordinal()] = 3;
            $EnumSwitchMapping$4[ResponseErrorType.UNKNOWN.ordinal()] = 1;
        }
    }

    @NotNull
    public static final com.bitcoin.mwallet.Network toProto(@NotNull Network network) {
        Intrinsics.checkParameterIsNotNull(network, "$this$toProto");
        int i = WhenMappings.$EnumSwitchMapping$0[network.ordinal()];
        if (i == 1) {
            return com.bitcoin.mwallet.Network.LIVE;
        }
        if (i == 2) {
            return com.bitcoin.mwallet.Network.TEST;
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public static final com.bitcoin.mwallet.Coin toProto(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "$this$toProto");
        int i = WhenMappings.$EnumSwitchMapping$1[coin.ordinal()];
        if (i == 1) {
            return com.bitcoin.mwallet.Coin.BCH;
        }
        if (i == 2) {
            return com.bitcoin.mwallet.Coin.BTC;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Nullable
    public static final Coin toDomain(@NotNull com.bitcoin.mwallet.Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "$this$toDomain");
        int i = WhenMappings.$EnumSwitchMapping$2[coin.ordinal()];
        if (i == 1) {
            return Coin.BCH;
        }
        if (i != 2) {
            return null;
        }
        return Coin.BTC;
    }

    @Nullable
    public static final SlpUtxo toSlpDomain(@NotNull Utxo utxo, @NotNull C1261Wallet wallet) {
        Intrinsics.checkParameterIsNotNull(utxo, "$this$toSlpDomain");
        Intrinsics.checkParameterIsNotNull(wallet, "wallet");
        Companion companion = Address.Companion;
        Network network = wallet.getNetwork();
        UtxoAddress address = utxo.getAddress();
        String str = "this.address";
        Intrinsics.checkExpressionValueIsNotNull(address, str);
        String legacyAddress = address.getLegacyAddress();
        Intrinsics.checkExpressionValueIsNotNull(legacyAddress, "this.address.legacyAddress");
        Address tryParse = companion.tryParse(network, legacyAddress);
        if (tryParse == null) {
            return null;
        }
        Token token = utxo.getToken();
        String str2 = "this.token";
        Intrinsics.checkExpressionValueIsNotNull(token, str2);
        String tokenId = token.getTokenId();
        Intrinsics.checkExpressionValueIsNotNull(tokenId, "this.token.tokenId");
        SlpId slpId = new SlpId(tokenId);
        Token token2 = utxo.getToken();
        Intrinsics.checkExpressionValueIsNotNull(token2, str2);
        long tokenValue = token2.getTokenValue();
        Token token3 = utxo.getToken();
        Intrinsics.checkExpressionValueIsNotNull(token3, str2);
        String tokenType = token3.getTokenType();
        Intrinsics.checkExpressionValueIsNotNull(tokenType, "this.token.tokenType");
        Token token4 = utxo.getToken();
        Intrinsics.checkExpressionValueIsNotNull(token4, str2);
        String transactionType = token4.getTransactionType();
        Intrinsics.checkExpressionValueIsNotNull(transactionType, "this.token.transactionType");
        WalletId id = wallet.getId();
        String txId = utxo.getTxId();
        Intrinsics.checkExpressionValueIsNotNull(txId, "this.txId");
        TxId txId2 = new TxId(txId);
        int outputIndex = utxo.getOutputIndex();
        DerivationPath.Companion companion2 = DerivationPath.Companion;
        StringBuilder sb = new StringBuilder();
        UtxoAddress address2 = utxo.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address2, str);
        sb.append(address2.getPathX());
        sb.append('/');
        UtxoAddress address3 = utxo.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address3, str);
        sb.append(address3.getPathY());
        DerivationPath parse = companion2.parse(sb.toString());
        byte[] byteArray = utxo.getScript().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "this.script.toByteArray()");
        com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo utxo2 = new com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo(id, txId2, outputIndex, tryParse, parse, byteArray, new Satoshis(utxo.getSatoshis()), wallet.getCoin());
        SlpUtxo slpUtxo = new SlpUtxo(slpId, tokenValue, tokenType, transactionType, utxo2);
        return slpUtxo;
    }

    @Nullable
    public static final com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo toDomain(@NotNull Utxo utxo, @NotNull C1261Wallet wallet) {
        Intrinsics.checkParameterIsNotNull(utxo, "$this$toDomain");
        Intrinsics.checkParameterIsNotNull(wallet, "wallet");
        Companion companion = Address.Companion;
        Network network = wallet.getNetwork();
        UtxoAddress address = utxo.getAddress();
        String str = "this.address";
        Intrinsics.checkExpressionValueIsNotNull(address, str);
        String legacyAddress = address.getLegacyAddress();
        Intrinsics.checkExpressionValueIsNotNull(legacyAddress, "this.address.legacyAddress");
        Address tryParse = companion.tryParse(network, legacyAddress);
        if (tryParse == null) {
            return null;
        }
        WalletId id = wallet.getId();
        String txId = utxo.getTxId();
        Intrinsics.checkExpressionValueIsNotNull(txId, "this.txId");
        TxId txId2 = new TxId(txId);
        int outputIndex = utxo.getOutputIndex();
        DerivationPath.Companion companion2 = DerivationPath.Companion;
        StringBuilder sb = new StringBuilder();
        UtxoAddress address2 = utxo.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address2, str);
        sb.append(address2.getPathX());
        sb.append('/');
        UtxoAddress address3 = utxo.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address3, str);
        sb.append(address3.getPathY());
        DerivationPath parse = companion2.parse(sb.toString());
        byte[] byteArray = utxo.getScript().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "this.script.toByteArray()");
        com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo utxo2 = new com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo(id, txId2, outputIndex, tryParse, parse, byteArray, new Satoshis(utxo.getSatoshis()), wallet.getCoin());
        return utxo2;
    }

    @NotNull
    public static final HistoricTransaction toDomain(@NotNull TxHistory txHistory, @NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(txHistory, "$this$toDomain");
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        String txId = txHistory.getTxId();
        Intrinsics.checkExpressionValueIsNotNull(txId, "this.txId");
        TxId txId2 = new TxId(txId);
        String walletId = txHistory.getWalletId();
        Intrinsics.checkExpressionValueIsNotNull(walletId, "this.walletId");
        WalletId walletId2 = new WalletId(walletId);
        TxType txType = txHistory.getTxType();
        Intrinsics.checkExpressionValueIsNotNull(txType, "this.txType");
        TxAction domain = toDomain(txType);
        Satoshis satoshis = new Satoshis(txHistory.getSatoshis());
        long timestamp = txHistory.getTimestamp();
        String note = txHistory.getNote();
        String legacyAddress = txHistory.getLegacyAddress();
        Intrinsics.checkExpressionValueIsNotNull(legacyAddress, "this.legacyAddress");
        boolean confirmed = txHistory.getConfirmed();
        Satoshis satoshis2 = new Satoshis(txHistory.getFee());
        Token token = txHistory.getToken();
        String str = "this.token";
        Intrinsics.checkExpressionValueIsNotNull(token, str);
        String tokenId = token.getTokenId();
        Intrinsics.checkExpressionValueIsNotNull(tokenId, "this.token.tokenId");
        SlpId slpId = new SlpId(tokenId);
        Token token2 = txHistory.getToken();
        Intrinsics.checkExpressionValueIsNotNull(token2, str);
        Coin coin2 = coin;
        HistoricTransaction historicTransaction = new HistoricTransaction(txId2, walletId2, timestamp, confirmed ? 1 : 0, satoshis, domain, coin2, legacyAddress, note, satoshis2, slpId, token2.getTokenValue());
        return historicTransaction;
    }

    @NotNull
    public static final TxAction toDomain(@NotNull TxType txType) {
        Intrinsics.checkParameterIsNotNull(txType, "$this$toDomain");
        int i = WhenMappings.$EnumSwitchMapping$3[txType.ordinal()];
        if (i == 1) {
            return TxAction.SENT;
        }
        if (i == 2) {
            return TxAction.RECEIVED;
        }
        if (i != 3) {
            return TxAction.UNKNOWN;
        }
        return TxAction.MOVED;
    }

    @NotNull
    public static final GrpcErrorResponse toDomain(@NotNull ResponseError responseError) {
        Intrinsics.checkParameterIsNotNull(responseError, "$this$toDomain");
        String errorMessage = responseError.getErrorMessage();
        Intrinsics.checkExpressionValueIsNotNull(errorMessage, "this.errorMessage");
        ResponseErrorType type = responseError.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "this.type");
        return new GrpcErrorResponse(errorMessage, toDomain(type));
    }

    @NotNull
    public static final ExchangeRateResponse toDomain(@NotNull ExchangeRates exchangeRates) {
        ExchangeRate exchangeRate;
        Intrinsics.checkParameterIsNotNull(exchangeRates, "$this$toDomain");
        List ratesList = exchangeRates.getRatesList();
        Intrinsics.checkExpressionValueIsNotNull(ratesList, "this.ratesList");
        Iterable<com.bitcoin.mwallet.ExchangeRate> iterable = ratesList;
        Collection arrayList = new ArrayList();
        for (com.bitcoin.mwallet.ExchangeRate exchangeRate2 : iterable) {
            String rate = exchangeRate2.getRate();
            Intrinsics.checkExpressionValueIsNotNull(rate, "exchangeRate.rate");
            BigDecimal tryParseBigDecimal = StringKt.tryParseBigDecimal(rate);
            if (tryParseBigDecimal != null) {
                String ticker = exchangeRate2.getTicker();
                Intrinsics.checkExpressionValueIsNotNull(ticker, "exchangeRate.ticker");
                String name = exchangeRate2.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "exchangeRate.name");
                exchangeRate = new ExchangeRate(ticker, name, tryParseBigDecimal);
            } else {
                exchangeRate = null;
            }
            if (exchangeRate != null) {
                arrayList.add(exchangeRate);
            }
        }
        return new ExchangeRateResponse(new Date(exchangeRates.getUpdatedEpochMillis()), CollectionsKt.toSet((List) arrayList));
    }

    private static final ErrorType toDomain(@NotNull ResponseErrorType responseErrorType) {
        if (WhenMappings.$EnumSwitchMapping$4[responseErrorType.ordinal()] != 1) {
            return ErrorType.UNKNOWN;
        }
        return ErrorType.BAD_SIGNATURE;
    }
}
