package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.Bip70Output;
import com.bitcoin.mwallet.Bip70PaymentErrorStatus;
import com.bitcoin.mwallet.Bip70PaymentInfo;
import com.bitcoin.mwallet.Bip70PaymentResponse;
import com.bitcoin.mwallet.Bip70PaymentResponse.ResponseCase;
import com.bitcoin.mwallet.BroadcastTxError;
import com.bitcoin.mwallet.BroadcastTxResponse;
import com.bitcoin.mwallet.GetUtxosResponse;
import com.bitcoin.mwallet.GetUtxosResponse.WalletUtxosList;
import com.bitcoin.mwallet.ResponseError;
import com.bitcoin.mwallet.Token;
import com.bitcoin.mwallet.TxServiceGrpc.TxServiceBlockingStub;
import com.bitcoin.mwallet.TxServiceGrpc.TxServiceStub;
import com.bitcoin.mwallet.UpdateNotesResponse;
import com.bitcoin.mwallet.Utxo;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.BitcoinUriContent.CREATOR;
import com.bitcoin.mwallet.core.models.address.AddressAndOriginalText;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse.ErrorType;
import com.leanplum.internal.Constants.Params;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import p015io.grpc.Channel;
import p015io.grpc.ManagedChannel;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 92\u00020\u00012\u00020\u0002:\u00019B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ1\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ?\u0010 \u001a\u00020!2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010)\u001a\u00020*H@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0019\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H@ø\u0001\u0000¢\u0006\u0002\u00100J1\u00101\u001a\u00020!2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00102\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u00103J1\u00104\u001a\u0002052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00106\u001a\u000207H@ø\u0001\u0000¢\u0006\u0002\u00108R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006:"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/TxServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "network", "Lcom/bitcoin/bitcoink/Network;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;Lcom/bitcoin/bitcoink/Network;)V", "blockingStub", "Lcom/bitcoin/mwallet/TxServiceGrpc$TxServiceBlockingStub;", "stub", "Lcom/bitcoin/mwallet/TxServiceGrpc$TxServiceStub;", "broadcastBip70Tx", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "url", "", "depositAddress", "txRaw", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "broadcastTx", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBip70PaymentInfo", "Lcom/bitcoin/mwallet/core/services/tx/GetBip70PaymentResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionHistory", "", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "observer", "Lio/grpc/stub/StreamObserver;", "Lcom/bitcoin/mwallet/TxHistory;", "startTime", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lio/grpc/stub/StreamObserver;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUtxos", "Lcom/bitcoin/mwallet/core/services/tx/GetUtxosResponse;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migrateOldNotes", "oldEncryptionKey", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNotes", "Lcom/bitcoin/mwallet/core/services/tx/UpdateNotesResponse;", "txNote", "Lcom/bitcoin/mwallet/TxNote;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lcom/bitcoin/mwallet/TxNote;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc */
/* compiled from: TxServiceGrpc.kt */
public final class TxServiceGrpc extends GrpcServiceBase implements TxService {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public final TxServiceBlockingStub blockingStub;
    /* access modifiers changed from: private */
    public final Network network;
    /* access modifiers changed from: private */
    public final TxServiceStub stub;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0002J\f\u0010\u0003\u001a\u00020\u0006*\u00020\u0007H\u0002J\f\u0010\u0003\u001a\u00020\u0006*\u00020\bH\u0002J\u0014\u0010\u0003\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0014\u0010\u0003\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\f\u0010\u0003\u001a\u00020\u000f*\u00020\u0010H\u0002J\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0014\u0010\u0014\u001a\u00020\u0015*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/TxServiceGrpc$Companion;", "", "()V", "toDomain", "Lcom/bitcoin/mwallet/core/services/tx/GetBip70PaymentResponse;", "Lcom/bitcoin/mwallet/Bip70PaymentResponse;", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;", "Lcom/bitcoin/mwallet/BroadcastTxError;", "Lcom/bitcoin/mwallet/BroadcastTxResponse;", "Lcom/bitcoin/mwallet/core/services/tx/GetUtxosResponse;", "Lcom/bitcoin/mwallet/GetUtxosResponse;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "Lcom/bitcoin/mwallet/GetUtxosResponse$WalletUtxosList;", "Lcom/bitcoin/mwallet/core/services/tx/UpdateNotesResponse;", "Lcom/bitcoin/mwallet/UpdateNotesResponse;", "toSlp", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "toSlpDomain", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$Companion */
    /* compiled from: TxServiceGrpc.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        /* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$Companion$WhenMappings */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ResponseCase.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[GetUtxosResponse.ResponseCase.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[BroadcastTxResponse.ResponseCase.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$3 = new int[BroadcastTxError.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$4 = new int[UpdateNotesResponse.ResponseCase.values().length];

            static {
                $EnumSwitchMapping$0[ResponseCase.INFO.ordinal()] = 1;
                $EnumSwitchMapping$0[ResponseCase.ERROR.ordinal()] = 2;
                $EnumSwitchMapping$0[ResponseCase.ERROR_STATUS.ordinal()] = 3;
                $EnumSwitchMapping$1[GetUtxosResponse.ResponseCase.UTXOS.ordinal()] = 1;
                $EnumSwitchMapping$1[GetUtxosResponse.ResponseCase.ERROR.ordinal()] = 2;
                $EnumSwitchMapping$2[BroadcastTxResponse.ResponseCase.TX_ID.ordinal()] = 1;
                $EnumSwitchMapping$2[BroadcastTxResponse.ResponseCase.BROADCAST_ERROR.ordinal()] = 2;
                $EnumSwitchMapping$2[BroadcastTxResponse.ResponseCase.ERROR.ordinal()] = 3;
                $EnumSwitchMapping$3[BroadcastTxError.TOO_LONG_MEMPOOL_CHAIN.ordinal()] = 1;
                $EnumSwitchMapping$4[UpdateNotesResponse.ResponseCase.ERROR.ordinal()] = 1;
                $EnumSwitchMapping$4[UpdateNotesResponse.ResponseCase.RESPONSE_NOT_SET.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final GetBip70PaymentResponse toDomain(@NotNull Bip70PaymentResponse bip70PaymentResponse) {
            ResponseCase responseCase = bip70PaymentResponse.getResponseCase();
            if (responseCase != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[responseCase.ordinal()];
                if (i == 1) {
                    Bip70PaymentInfo info = bip70PaymentResponse.getInfo();
                    String str = Params.INFO;
                    Intrinsics.checkExpressionValueIsNotNull(info, str);
                    String merchantProcessor = info.getMerchantProcessor();
                    String str2 = merchantProcessor;
                    Intrinsics.checkExpressionValueIsNotNull(merchantProcessor, "info.merchantProcessor");
                    Bip70PaymentInfo info2 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info2, str);
                    String merchantName = info2.getMerchantName();
                    String str3 = merchantName;
                    Intrinsics.checkExpressionValueIsNotNull(merchantName, "info.merchantName");
                    Bip70PaymentInfo info3 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info3, str);
                    String merchantWebsite = info3.getMerchantWebsite();
                    String str4 = merchantWebsite;
                    Intrinsics.checkExpressionValueIsNotNull(merchantWebsite, "info.merchantWebsite");
                    Bip70PaymentInfo info4 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info4, str);
                    String email = info4.getEmail();
                    String str5 = email;
                    Intrinsics.checkExpressionValueIsNotNull(email, "info.email");
                    Bip70PaymentInfo info5 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info5, str);
                    String invoiceAmount = info5.getInvoiceAmount();
                    String str6 = invoiceAmount;
                    Intrinsics.checkExpressionValueIsNotNull(invoiceAmount, "info.invoiceAmount");
                    Bip70PaymentInfo info6 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info6, str);
                    String invoiceCurrency = info6.getInvoiceCurrency();
                    String str7 = invoiceCurrency;
                    Intrinsics.checkExpressionValueIsNotNull(invoiceCurrency, "info.invoiceCurrency");
                    Bip70PaymentInfo info7 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info7, str);
                    String paymentAmount = info7.getPaymentAmount();
                    String str8 = paymentAmount;
                    Intrinsics.checkExpressionValueIsNotNull(paymentAmount, "info.paymentAmount");
                    Bip70PaymentInfo info8 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info8, str);
                    String paymentCurrency = info8.getPaymentCurrency();
                    String str9 = paymentCurrency;
                    Intrinsics.checkExpressionValueIsNotNull(paymentCurrency, "info.paymentCurrency");
                    Bip70PaymentInfo info9 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info9, str);
                    String itemDesc = info9.getItemDesc();
                    String str10 = itemDesc;
                    Intrinsics.checkExpressionValueIsNotNull(itemDesc, "info.itemDesc");
                    Bip70PaymentInfo info10 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info10, str);
                    String phone = info10.getPhone();
                    String str11 = phone;
                    Intrinsics.checkExpressionValueIsNotNull(phone, "info.phone");
                    Bip70PaymentInfo info11 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info11, str);
                    long createTime = info11.getCreateTime();
                    Bip70PaymentInfo info12 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info12, str);
                    long expiryTime = info12.getExpiryTime();
                    Bip70PaymentInfo info13 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info13, str);
                    String conversionAssets = info13.getConversionAssets();
                    String str12 = conversionAssets;
                    Intrinsics.checkExpressionValueIsNotNull(conversionAssets, "info.conversionAssets");
                    Bip70PaymentInfo info14 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info14, str);
                    String conversionRate = info14.getConversionRate();
                    String str13 = conversionRate;
                    com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo bip70PaymentInfo = r2;
                    Intrinsics.checkExpressionValueIsNotNull(conversionRate, "info.conversionRate");
                    Bip70PaymentInfo info15 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info15, str);
                    String str14 = str;
                    com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo bip70PaymentInfo2 = new com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo(str2, str3, info15.getVerification(), str7, str6, str9, str8, str13, str12, str10, str5, str4, str11, createTime, expiryTime);
                    Bip70PaymentInfo info16 = bip70PaymentResponse.getInfo();
                    Intrinsics.checkExpressionValueIsNotNull(info16, str14);
                    List outputsList = info16.getOutputsList();
                    Intrinsics.checkExpressionValueIsNotNull(outputsList, "info.outputsList");
                    Iterable<Bip70Output> iterable = outputsList;
                    Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                    for (Bip70Output bip70Output : iterable) {
                        AddressAndOriginalText addressAndOriginalText = null;
                        CREATOR creator = BitcoinUriContent.CREATOR;
                        Intrinsics.checkExpressionValueIsNotNull(bip70Output, "it");
                        String address = bip70Output.getAddress();
                        Intrinsics.checkExpressionValueIsNotNull(address, "it.address");
                        BitcoinUriContent parse = creator.parse(address);
                        if (parse.isValid()) {
                            addressAndOriginalText = parse.getAddress();
                        }
                        String scriptHex = bip70Output.getScriptHex();
                        Intrinsics.checkExpressionValueIsNotNull(scriptHex, "it.scriptHex");
                        arrayList.add(new Bip70PaymentOutput(scriptHex, bip70Output.getAmount(), addressAndOriginalText));
                    }
                    return new GetBip70PaymentResponse(bip70PaymentInfo, (List) arrayList);
                } else if (i == 2) {
                    ResponseError error = bip70PaymentResponse.getError();
                    Intrinsics.checkExpressionValueIsNotNull(error, "error");
                    return new GetBip70PaymentResponse(ProtoConverterKt.toDomain(error));
                } else if (i == 3) {
                    Bip70PaymentErrorStatus errorStatus = bip70PaymentResponse.getErrorStatus();
                    Intrinsics.checkExpressionValueIsNotNull(errorStatus, "errorStatus");
                    return new GetBip70PaymentResponse(errorStatus);
                }
            }
            return new GetBip70PaymentResponse(GrpcErrorResponse.Companion.unknown());
        }

        /* access modifiers changed from: private */
        public final GetUtxosResponse toDomain(@NotNull GetUtxosResponse getUtxosResponse, C1261Wallet wallet) {
            GetUtxosResponse.ResponseCase responseCase = getUtxosResponse.getResponseCase();
            if (responseCase != null) {
                int i = WhenMappings.$EnumSwitchMapping$1[responseCase.ordinal()];
                if (i == 1) {
                    Companion companion = this;
                    WalletUtxosList utxos = getUtxosResponse.getUtxos();
                    String str = "utxos";
                    Intrinsics.checkExpressionValueIsNotNull(utxos, str);
                    WalletUtxos domain = companion.toDomain(utxos, wallet);
                    WalletUtxosList utxos2 = getUtxosResponse.getUtxos();
                    Intrinsics.checkExpressionValueIsNotNull(utxos2, str);
                    WalletSlpUtxos slpDomain = companion.toSlpDomain(utxos2, wallet);
                    WalletUtxosList utxos3 = getUtxosResponse.getUtxos();
                    Intrinsics.checkExpressionValueIsNotNull(utxos3, str);
                    return new GetUtxosResponse(domain, slpDomain, companion.toSlp(utxos3, wallet));
                } else if (i == 2) {
                    ResponseError error = getUtxosResponse.getError();
                    Intrinsics.checkExpressionValueIsNotNull(error, "error");
                    return new GetUtxosResponse(ProtoConverterKt.toDomain(error));
                }
            }
            return new GetUtxosResponse(GrpcErrorResponse.Companion.unknown());
        }

        private final List<Slp> toSlp(@NotNull WalletUtxosList walletUtxosList, C1261Wallet wallet) {
            String str;
            String str2;
            String str3;
            List utxosList = walletUtxosList.getUtxosList();
            Intrinsics.checkExpressionValueIsNotNull(utxosList, "this.utxosList");
            Iterable iterable = utxosList;
            Collection arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (true) {
                str = "it";
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                com.bitcoin.mwallet.WalletUtxos walletUtxos = (com.bitcoin.mwallet.WalletUtxos) next;
                Intrinsics.checkExpressionValueIsNotNull(walletUtxos, str);
                if (Intrinsics.areEqual((Object) walletUtxos.getWalletId(), (Object) wallet.getId().getId())) {
                    arrayList.add(next);
                }
            }
            Iterable<com.bitcoin.mwallet.WalletUtxos> iterable2 = (List) arrayList;
            Collection arrayList2 = new ArrayList();
            for (com.bitcoin.mwallet.WalletUtxos walletUtxos2 : iterable2) {
                Intrinsics.checkExpressionValueIsNotNull(walletUtxos2, str);
                List utxoList = walletUtxos2.getUtxoList();
                Intrinsics.checkExpressionValueIsNotNull(utxoList, "it.utxoList");
                Iterable iterable3 = utxoList;
                Collection arrayList3 = new ArrayList();
                Iterator it2 = iterable3.iterator();
                while (true) {
                    str2 = "it.token.tokenId";
                    str3 = "it.token";
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next2 = it2.next();
                    Utxo utxo = (Utxo) next2;
                    Intrinsics.checkExpressionValueIsNotNull(utxo, str);
                    Token token = utxo.getToken();
                    Intrinsics.checkExpressionValueIsNotNull(token, str3);
                    String tokenId = token.getTokenId();
                    Intrinsics.checkExpressionValueIsNotNull(tokenId, str2);
                    if (tokenId.length() > 0) {
                        arrayList3.add(next2);
                    }
                }
                Iterable<Utxo> iterable4 = (List) arrayList3;
                Collection arrayList4 = new ArrayList();
                for (Utxo utxo2 : iterable4) {
                    Intrinsics.checkExpressionValueIsNotNull(utxo2, str);
                    Token token2 = utxo2.getToken();
                    Intrinsics.checkExpressionValueIsNotNull(token2, str3);
                    String tokenId2 = token2.getTokenId();
                    Intrinsics.checkExpressionValueIsNotNull(tokenId2, str2);
                    SlpId slpId = new SlpId(tokenId2);
                    Token token3 = utxo2.getToken();
                    Intrinsics.checkExpressionValueIsNotNull(token3, str3);
                    String tokenTicker = token3.getTokenTicker();
                    Intrinsics.checkExpressionValueIsNotNull(tokenTicker, "it.token.tokenTicker");
                    Token token4 = utxo2.getToken();
                    Intrinsics.checkExpressionValueIsNotNull(token4, str3);
                    String tokenName = token4.getTokenName();
                    Intrinsics.checkExpressionValueIsNotNull(tokenName, "it.token.tokenName");
                    WalletId id = wallet.getId();
                    Token token5 = utxo2.getToken();
                    Intrinsics.checkExpressionValueIsNotNull(token5, str3);
                    Slp slp = new Slp(slpId, tokenTicker, tokenName, id, token5.getTokenDecimals());
                    arrayList4.add(slp);
                }
                CollectionsKt.addAll(arrayList2, (Iterable) (List) arrayList4);
            }
            return (List) arrayList2;
        }

        private final WalletSlpUtxos toSlpDomain(@NotNull WalletUtxosList walletUtxosList, C1261Wallet wallet) {
            String str;
            List utxosList = walletUtxosList.getUtxosList();
            Intrinsics.checkExpressionValueIsNotNull(utxosList, "this.utxosList");
            Iterable iterable = utxosList;
            Collection arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (true) {
                str = "it";
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                com.bitcoin.mwallet.WalletUtxos walletUtxos = (com.bitcoin.mwallet.WalletUtxos) next;
                Intrinsics.checkExpressionValueIsNotNull(walletUtxos, str);
                if (Intrinsics.areEqual((Object) walletUtxos.getWalletId(), (Object) wallet.getId().getId())) {
                    arrayList.add(next);
                }
            }
            Iterable<com.bitcoin.mwallet.WalletUtxos> iterable2 = (List) arrayList;
            Collection arrayList2 = new ArrayList();
            for (com.bitcoin.mwallet.WalletUtxos walletUtxos2 : iterable2) {
                Intrinsics.checkExpressionValueIsNotNull(walletUtxos2, str);
                List utxoList = walletUtxos2.getUtxoList();
                Intrinsics.checkExpressionValueIsNotNull(utxoList, "it.utxoList");
                Iterable iterable3 = utxoList;
                Collection arrayList3 = new ArrayList();
                for (Object next2 : iterable3) {
                    Utxo utxo = (Utxo) next2;
                    Intrinsics.checkExpressionValueIsNotNull(utxo, str);
                    Token token = utxo.getToken();
                    Intrinsics.checkExpressionValueIsNotNull(token, "it.token");
                    String tokenId = token.getTokenId();
                    Intrinsics.checkExpressionValueIsNotNull(tokenId, "it.token.tokenId");
                    if (tokenId.length() > 0) {
                        arrayList3.add(next2);
                    }
                }
                Iterable<Utxo> iterable4 = (List) arrayList3;
                Collection arrayList4 = new ArrayList();
                for (Utxo utxo2 : iterable4) {
                    Intrinsics.checkExpressionValueIsNotNull(utxo2, str);
                    SlpUtxo slpDomain = ProtoConverterKt.toSlpDomain(utxo2, wallet);
                    if (slpDomain != null) {
                        arrayList4.add(slpDomain);
                    }
                }
                CollectionsKt.addAll(arrayList2, (Iterable) (List) arrayList4);
            }
            List list = (List) arrayList2;
            Iterable<SlpUtxo> iterable5 = list;
            Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable5, 10));
            for (SlpUtxo slpUtxo : iterable5) {
                StringBuilder sb = new StringBuilder();
                sb.append("SLP UTXO ");
                sb.append(slpUtxo.getTokenId());
                sb.append(' ');
                sb.append(slpUtxo.getTokenAmount());
                sb.append(' ');
                sb.append(slpUtxo.getUtxo());
                Timber.m426d(sb.toString(), new Object[0]);
                arrayList5.add(Unit.INSTANCE);
            }
            List list2 = (List) arrayList5;
            return new WalletSlpUtxos(wallet.getId(), list);
        }

        private final WalletUtxos toDomain(@NotNull WalletUtxosList walletUtxosList, C1261Wallet wallet) {
            String str;
            List utxosList = walletUtxosList.getUtxosList();
            Intrinsics.checkExpressionValueIsNotNull(utxosList, "this.utxosList");
            Iterable iterable = utxosList;
            Collection arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (true) {
                str = "it";
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                com.bitcoin.mwallet.WalletUtxos walletUtxos = (com.bitcoin.mwallet.WalletUtxos) next;
                Intrinsics.checkExpressionValueIsNotNull(walletUtxos, str);
                if (Intrinsics.areEqual((Object) walletUtxos.getWalletId(), (Object) wallet.getId().getId())) {
                    arrayList.add(next);
                }
            }
            Iterable<com.bitcoin.mwallet.WalletUtxos> iterable2 = (List) arrayList;
            Collection arrayList2 = new ArrayList();
            for (com.bitcoin.mwallet.WalletUtxos walletUtxos2 : iterable2) {
                Intrinsics.checkExpressionValueIsNotNull(walletUtxos2, "walletUtxos");
                List utxoList = walletUtxos2.getUtxoList();
                Intrinsics.checkExpressionValueIsNotNull(utxoList, "walletUtxos.utxoList");
                Iterable iterable3 = utxoList;
                Collection arrayList3 = new ArrayList();
                for (Object next2 : iterable3) {
                    Utxo utxo = (Utxo) next2;
                    Intrinsics.checkExpressionValueIsNotNull(utxo, str);
                    Token token = utxo.getToken();
                    Intrinsics.checkExpressionValueIsNotNull(token, "it.token");
                    String tokenId = token.getTokenId();
                    Intrinsics.checkExpressionValueIsNotNull(tokenId, "it.token.tokenId");
                    if (tokenId.length() == 0) {
                        arrayList3.add(next2);
                    }
                }
                Iterable<Utxo> iterable4 = (List) arrayList3;
                Collection arrayList4 = new ArrayList();
                for (Utxo utxo2 : iterable4) {
                    Intrinsics.checkExpressionValueIsNotNull(utxo2, str);
                    com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo domain = ProtoConverterKt.toDomain(utxo2, wallet);
                    if (domain != null) {
                        arrayList4.add(domain);
                    }
                }
                CollectionsKt.addAll(arrayList2, (Iterable) (List) arrayList4);
            }
            List list = (List) arrayList2;
            StringBuilder sb = new StringBuilder();
            sb.append("Wallet Utxos: ");
            sb.append(list);
            Timber.m426d(sb.toString(), new Object[0]);
            return new WalletUtxos(wallet.getId(), list);
        }

        /* access modifiers changed from: private */
        public final BroadcastTxResponse toDomain(@NotNull BroadcastTxResponse broadcastTxResponse) {
            BroadcastTxResponse broadcastTxResponse2;
            BroadcastTxResponse.ResponseCase responseCase = broadcastTxResponse.getResponseCase();
            if (responseCase != null) {
                int i = WhenMappings.$EnumSwitchMapping$2[responseCase.ordinal()];
                if (i == 1) {
                    String txId = broadcastTxResponse.getTxId();
                    Intrinsics.checkExpressionValueIsNotNull(txId, "this.txId");
                    broadcastTxResponse2 = new BroadcastTxResponse(new TxId(txId));
                } else if (i == 2) {
                    Companion companion = this;
                    BroadcastTxError broadcastError = broadcastTxResponse.getBroadcastError();
                    Intrinsics.checkExpressionValueIsNotNull(broadcastError, "this.broadcastError");
                    return companion.toDomain(broadcastError);
                } else if (i == 3) {
                    ResponseError error = broadcastTxResponse.getError();
                    Intrinsics.checkExpressionValueIsNotNull(error, "this.error");
                    broadcastTxResponse2 = new BroadcastTxResponse(ProtoConverterKt.toDomain(error));
                }
                return broadcastTxResponse2;
            }
            return new BroadcastTxResponse(GrpcErrorResponse.Companion.unknown());
        }

        private final BroadcastTxResponse toDomain(@NotNull BroadcastTxError broadcastTxError) {
            if (WhenMappings.$EnumSwitchMapping$3[broadcastTxError.ordinal()] != 1) {
                return new BroadcastTxResponse(ErrorType.UNKNOWN);
            }
            return new BroadcastTxResponse(ErrorType.TOO_LONG_MEMCHAIN);
        }

        /* access modifiers changed from: private */
        public final UpdateNotesResponse toDomain(@NotNull UpdateNotesResponse updateNotesResponse) {
            UpdateNotesResponse.ResponseCase responseCase = updateNotesResponse.getResponseCase();
            if (responseCase != null) {
                int i = WhenMappings.$EnumSwitchMapping$4[responseCase.ordinal()];
                if (i == 1) {
                    ResponseError error = updateNotesResponse.getError();
                    Intrinsics.checkExpressionValueIsNotNull(error, "this.error");
                    return new UpdateNotesResponse(ProtoConverterKt.toDomain(error));
                } else if (i == 2) {
                    return new UpdateNotesResponse();
                }
            }
            return new UpdateNotesResponse(GrpcErrorResponse.Companion.unknown());
        }
    }

    public /* synthetic */ TxServiceGrpc(CoroutineDispatcher coroutineDispatcher, ManagedChannel managedChannel, Network network2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            network2 = Network.MAIN;
        }
        this(coroutineDispatcher, managedChannel, network2);
    }

    public TxServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel, @NotNull Network network2) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        Intrinsics.checkParameterIsNotNull(network2, "network");
        super(coroutineDispatcher);
        this.network = network2;
        Channel channel = managedChannel;
        TxServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.TxServiceGrpc.newBlockingStub(channel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "TxServiceGrpc.newBlockingStub(channel)");
        this.blockingStub = newBlockingStub;
        TxServiceStub newStub = com.bitcoin.mwallet.TxServiceGrpc.newStub(channel);
        Intrinsics.checkExpressionValueIsNotNull(newStub, "TxServiceGrpc.newStub(channel)");
        this.stub = newStub;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object broadcastBip70Tx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r14, @org.jetbrains.annotations.NotNull java.lang.String r15, @org.jetbrains.annotations.NotNull java.lang.String r16, @org.jetbrains.annotations.NotNull byte[] r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.Bip70BroadcastTxResponse> r18) {
        /*
            r13 = this;
            r8 = r13
            r0 = r18
            boolean r1 = r0 instanceof com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$broadcastBip70Tx$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$1 r1 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$broadcastBip70Tx$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0017
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001c
        L_0x0017:
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$1 r1 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$1
            r1.<init>(r13, r0)
        L_0x001c:
            r0 = r1
            java.lang.Object r1 = r0.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r10 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 != r10) goto L_0x0042
            java.lang.Object r2 = r0.L$4
            byte[] r2 = (byte[]) r2
            java.lang.Object r2 = r0.L$3
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r0.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r0 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r0
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ RuntimeException -> 0x0089 }
            goto L_0x007d
        L_0x0042:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.CoroutineDispatcher r1 = r13.getGrpcDispatcher()     // Catch:{ RuntimeException -> 0x0089 }
            r11 = r1
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11     // Catch:{ RuntimeException -> 0x0089 }
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$response$1 r12 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$response$1     // Catch:{ RuntimeException -> 0x0089 }
            r7 = 0
            r1 = r12
            r2 = r13
            r3 = r15
            r4 = r17
            r5 = r16
            r6 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ RuntimeException -> 0x0089 }
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12     // Catch:{ RuntimeException -> 0x0089 }
            r0.L$0 = r8     // Catch:{ RuntimeException -> 0x0089 }
            r1 = r14
            r0.L$1 = r1     // Catch:{ RuntimeException -> 0x0089 }
            r1 = r15
            r0.L$2 = r1     // Catch:{ RuntimeException -> 0x0089 }
            r1 = r16
            r0.L$3 = r1     // Catch:{ RuntimeException -> 0x0089 }
            r1 = r17
            r0.L$4 = r1     // Catch:{ RuntimeException -> 0x0089 }
            r0.label = r10     // Catch:{ RuntimeException -> 0x0089 }
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r11, r12, r0)     // Catch:{ RuntimeException -> 0x0089 }
            if (r1 != r9) goto L_0x007d
            return r9
        L_0x007d:
            com.bitcoin.mwallet.Bip70BroadcastTxResponse r1 = (com.bitcoin.mwallet.Bip70BroadcastTxResponse) r1     // Catch:{ RuntimeException -> 0x0089 }
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r0)     // Catch:{ RuntimeException -> 0x0089 }
            com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse r0 = com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpcKt.toDomain(r1)     // Catch:{ RuntimeException -> 0x0089 }
            goto L_0x0090
        L_0x0089:
            r0 = move-exception
            com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse r1 = new com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse
            r1.<init>(r0)
            r0 = r1
        L_0x0090:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc.broadcastBip70Tx(com.bitcoin.mwallet.core.models.wallet.WalletId, java.lang.String, java.lang.String, byte[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getBip70PaymentInfo(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.GetBip70PaymentResponse> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$getBip70PaymentInfo$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$1 r0 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$getBip70PaymentInfo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$1 r0 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r6 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ RuntimeException -> 0x0066 }
            goto L_0x0058
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CoroutineDispatcher r7 = r5.getGrpcDispatcher()     // Catch:{ RuntimeException -> 0x0066 }
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7     // Catch:{ RuntimeException -> 0x0066 }
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$response$1 r2 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$response$1     // Catch:{ RuntimeException -> 0x0066 }
            r4 = 0
            r2.<init>(r5, r6, r4)     // Catch:{ RuntimeException -> 0x0066 }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ RuntimeException -> 0x0066 }
            r0.L$0 = r5     // Catch:{ RuntimeException -> 0x0066 }
            r0.L$1 = r6     // Catch:{ RuntimeException -> 0x0066 }
            r0.label = r3     // Catch:{ RuntimeException -> 0x0066 }
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)     // Catch:{ RuntimeException -> 0x0066 }
            if (r7 != r1) goto L_0x0058
            return r1
        L_0x0058:
            java.lang.String r6 = "withContext(grpcDispatch…nt(request)\n            }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r6)     // Catch:{ RuntimeException -> 0x0066 }
            com.bitcoin.mwallet.Bip70PaymentResponse r7 = (com.bitcoin.mwallet.Bip70PaymentResponse) r7     // Catch:{ RuntimeException -> 0x0066 }
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$Companion r6 = Companion     // Catch:{ RuntimeException -> 0x0066 }
            com.bitcoin.mwallet.core.services.tx.GetBip70PaymentResponse r6 = r6.toDomain(r7)     // Catch:{ RuntimeException -> 0x0066 }
            goto L_0x006d
        L_0x0066:
            r6 = move-exception
            com.bitcoin.mwallet.core.services.tx.GetBip70PaymentResponse r7 = new com.bitcoin.mwallet.core.services.tx.GetBip70PaymentResponse
            r7.<init>(r6)
            r6 = r7
        L_0x006d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc.getBip70PaymentInfo(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object broadcastTx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r6, @org.jetbrains.annotations.NotNull byte[] r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$broadcastTx$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastTx$1 r0 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$broadcastTx$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastTx$1 r0 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastTx$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r6 = r0.L$2
            byte[] r6 = (byte[]) r6
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r6 = (com.bitcoin.mwallet.core.models.Coin) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r6 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r6
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ RuntimeException -> 0x005d }
            goto L_0x005a
        L_0x0036:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.String r8 = "broadcastTx"
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastTx$2 r2 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastTx$2     // Catch:{ RuntimeException -> 0x005d }
            r4 = 0
            r2.<init>(r5, r6, r7, r4)     // Catch:{ RuntimeException -> 0x005d }
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch:{ RuntimeException -> 0x005d }
            r0.L$0 = r5     // Catch:{ RuntimeException -> 0x005d }
            r0.L$1 = r6     // Catch:{ RuntimeException -> 0x005d }
            r0.L$2 = r7     // Catch:{ RuntimeException -> 0x005d }
            r0.label = r3     // Catch:{ RuntimeException -> 0x005d }
            java.lang.Object r8 = r5.logDuration(r8, r2, r0)     // Catch:{ RuntimeException -> 0x005d }
            if (r8 != r1) goto L_0x005a
            return r1
        L_0x005a:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r8 = (com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse) r8     // Catch:{ RuntimeException -> 0x005d }
            goto L_0x0063
        L_0x005d:
            r6 = move-exception
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r8 = new com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse
            r8.<init>(r6)
        L_0x0063:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc.broadcastTx(com.bitcoin.mwallet.core.models.Coin, byte[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getUtxos(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.C1261Wallet r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$getUtxos$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getUtxos$1 r0 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$getUtxos$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getUtxos$1 r0 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getUtxos$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r6 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r6 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ RuntimeException -> 0x0057 }
            goto L_0x0054
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = "getUtxos"
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getUtxos$2 r2 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getUtxos$2     // Catch:{ RuntimeException -> 0x0057 }
            r4 = 0
            r2.<init>(r5, r6, r4)     // Catch:{ RuntimeException -> 0x0057 }
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch:{ RuntimeException -> 0x0057 }
            r0.L$0 = r5     // Catch:{ RuntimeException -> 0x0057 }
            r0.L$1 = r6     // Catch:{ RuntimeException -> 0x0057 }
            r0.label = r3     // Catch:{ RuntimeException -> 0x0057 }
            java.lang.Object r7 = r5.logDuration(r7, r2, r0)     // Catch:{ RuntimeException -> 0x0057 }
            if (r7 != r1) goto L_0x0054
            return r1
        L_0x0054:
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r7 = (com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse) r7     // Catch:{ RuntimeException -> 0x0057 }
            goto L_0x005d
        L_0x0057:
            r6 = move-exception
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r7 = new com.bitcoin.mwallet.core.services.tx.GetUtxosResponse
            r7.<init>(r6)
        L_0x005d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc.getUtxos(com.bitcoin.mwallet.core.models.wallet.Wallet, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getTransactionHistory(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r16, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.CopayerId r17, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.utils.signature.SigningKey r18, @org.jetbrains.annotations.NotNull p015io.grpc.stub.StreamObserver<com.bitcoin.mwallet.TxHistory> r19, long r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r22) {
        /*
            r15 = this;
            r10 = r15
            r0 = r22
            boolean r1 = r0 instanceof com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$getTransactionHistory$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$1 r1 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$getTransactionHistory$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0017
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001c
        L_0x0017:
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$1 r1 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$1
            r1.<init>(r15, r0)
        L_0x001c:
            r0 = r1
            java.lang.Object r1 = r0.result
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r12 = 1
            if (r2 == 0) goto L_0x004e
            if (r2 != r12) goto L_0x0046
            long r2 = r0.J$0
            java.lang.Object r2 = r0.L$4
            io.grpc.stub.StreamObserver r2 = (p015io.grpc.stub.StreamObserver) r2
            java.lang.Object r3 = r0.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r3 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r3
            java.lang.Object r3 = r0.L$2
            com.bitcoin.mwallet.core.models.CopayerId r3 = (com.bitcoin.mwallet.core.models.CopayerId) r3
            java.lang.Object r3 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r3
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r0 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r0
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ RuntimeException -> 0x0044 }
            goto L_0x008e
        L_0x0044:
            r0 = move-exception
            goto L_0x0089
        L_0x0046:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004e:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.String r13 = "getTransactionHistory"
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$2 r14 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$2     // Catch:{ RuntimeException -> 0x0086 }
            r9 = 0
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r20
            r7 = r18
            r8 = r19
            r1.<init>(r2, r3, r4, r5, r7, r8, r9)     // Catch:{ RuntimeException -> 0x0086 }
            kotlin.jvm.functions.Function1 r14 = (kotlin.jvm.functions.Function1) r14     // Catch:{ RuntimeException -> 0x0086 }
            r0.L$0 = r10     // Catch:{ RuntimeException -> 0x0086 }
            r1 = r16
            r0.L$1 = r1     // Catch:{ RuntimeException -> 0x0086 }
            r1 = r17
            r0.L$2 = r1     // Catch:{ RuntimeException -> 0x0086 }
            r1 = r18
            r0.L$3 = r1     // Catch:{ RuntimeException -> 0x0086 }
            r2 = r19
            r0.L$4 = r2     // Catch:{ RuntimeException -> 0x0044 }
            r3 = r20
            r0.J$0 = r3     // Catch:{ RuntimeException -> 0x0044 }
            r0.label = r12     // Catch:{ RuntimeException -> 0x0044 }
            java.lang.Object r0 = r15.logDuration(r13, r14, r0)     // Catch:{ RuntimeException -> 0x0044 }
            if (r0 != r11) goto L_0x008e
            return r11
        L_0x0086:
            r0 = move-exception
            r2 = r19
        L_0x0089:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r2.onError(r0)
        L_0x008e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc.getTransactionHistory(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.CopayerId, com.bitcoin.mwallet.core.utils.signature.SigningKey, io.grpc.stub.StreamObserver, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateNotes(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r14, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.CopayerId r15, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.utils.signature.SigningKey r16, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.TxNote r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.UpdateNotesResponse> r18) {
        /*
            r13 = this;
            r8 = r13
            r0 = r18
            boolean r1 = r0 instanceof com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$updateNotes$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$1 r1 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$updateNotes$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0017
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001c
        L_0x0017:
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$1 r1 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$1
            r1.<init>(r13, r0)
        L_0x001c:
            r0 = r1
            java.lang.Object r1 = r0.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r10 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 != r10) goto L_0x0042
            java.lang.Object r2 = r0.L$4
            com.bitcoin.mwallet.TxNote r2 = (com.bitcoin.mwallet.TxNote) r2
            java.lang.Object r2 = r0.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r2 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r2
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.CopayerId r2 = (com.bitcoin.mwallet.core.models.CopayerId) r2
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r0 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r0
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ RuntimeException -> 0x007b }
            goto L_0x0078
        L_0x0042:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.String r11 = "updateNotes"
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$2 r12 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$2     // Catch:{ RuntimeException -> 0x007b }
            r7 = 0
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r17
            r6 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ RuntimeException -> 0x007b }
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12     // Catch:{ RuntimeException -> 0x007b }
            r0.L$0 = r8     // Catch:{ RuntimeException -> 0x007b }
            r1 = r14
            r0.L$1 = r1     // Catch:{ RuntimeException -> 0x007b }
            r1 = r15
            r0.L$2 = r1     // Catch:{ RuntimeException -> 0x007b }
            r1 = r16
            r0.L$3 = r1     // Catch:{ RuntimeException -> 0x007b }
            r1 = r17
            r0.L$4 = r1     // Catch:{ RuntimeException -> 0x007b }
            r0.label = r10     // Catch:{ RuntimeException -> 0x007b }
            java.lang.Object r1 = r13.logDuration(r11, r12, r0)     // Catch:{ RuntimeException -> 0x007b }
            if (r1 != r9) goto L_0x0078
            return r9
        L_0x0078:
            com.bitcoin.mwallet.core.services.tx.UpdateNotesResponse r1 = (com.bitcoin.mwallet.core.services.p010tx.UpdateNotesResponse) r1     // Catch:{ RuntimeException -> 0x007b }
            goto L_0x0081
        L_0x007b:
            r0 = move-exception
            com.bitcoin.mwallet.core.services.tx.UpdateNotesResponse r1 = new com.bitcoin.mwallet.core.services.tx.UpdateNotesResponse
            r1.<init>(r0)
        L_0x0081:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc.updateNotes(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.CopayerId, com.bitcoin.mwallet.core.utils.signature.SigningKey, com.bitcoin.mwallet.TxNote, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0116 A[LOOP:1: B:26:0x0110->B:28:0x0116, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01a6 A[LOOP:3: B:36:0x01a0->B:38:0x01a6, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0218 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00fb A[EDGE_INSN: B:44:0x00fb->B:25:0x00fb ?: BREAK  
    EDGE_INSN: B:44:0x00fb->B:25:0x00fb ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object migrateOldNotes(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r21, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.CopayerId r22, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.utils.signature.SigningKey r23, @org.jetbrains.annotations.NotNull java.lang.String r24, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            r20 = this;
            r6 = r20
            r0 = r25
            boolean r1 = r0 instanceof com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$migrateOldNotes$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$1 r1 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc$migrateOldNotes$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$1 r1 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$1
            r1.<init>(r6, r0)
        L_0x001d:
            r7 = r1
            java.lang.Object r0 = r7.result
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r9 = 2
            r10 = 1
            if (r1 == 0) goto L_0x0077
            if (r1 == r10) goto L_0x005f
            if (r1 != r9) goto L_0x0057
            java.lang.Object r1 = r7.L$8
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r1 = r7.L$7
            com.bitcoin.mwallet.UpdateNotesRequest$Builder r1 = (com.bitcoin.mwallet.UpdateNotesRequest.Builder) r1
            java.lang.Object r1 = r7.L$6
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r1 = r7.L$5
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r1 = r7.L$4
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r7.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r1 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r1
            java.lang.Object r1 = r7.L$2
            com.bitcoin.mwallet.core.models.CopayerId r1 = (com.bitcoin.mwallet.core.models.CopayerId) r1
            java.lang.Object r1 = r7.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            java.lang.Object r1 = r7.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r1 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0219
        L_0x0057:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x005f:
            java.lang.Object r1 = r7.L$4
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r7.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r2 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r2
            java.lang.Object r3 = r7.L$2
            com.bitcoin.mwallet.core.models.CopayerId r3 = (com.bitcoin.mwallet.core.models.CopayerId) r3
            java.lang.Object r4 = r7.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r4
            java.lang.Object r5 = r7.L$0
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc r5 = (com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc) r5
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00ae
        L_0x0077:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.CoroutineDispatcher r0 = r20.getGrpcDispatcher()
            r11 = r0
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$oldTxNotes$1 r12 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$oldTxNotes$1
            r5 = 0
            r0 = r12
            r1 = r20
            r2 = r22
            r3 = r21
            r4 = r23
            r0.<init>(r1, r2, r3, r4, r5)
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r7.L$0 = r6
            r4 = r21
            r7.L$1 = r4
            r3 = r22
            r7.L$2 = r3
            r2 = r23
            r7.L$3 = r2
            r1 = r24
            r7.L$4 = r1
            r7.label = r10
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r11, r12, r7)
            if (r0 != r8) goto L_0x00ad
            return r8
        L_0x00ad:
            r5 = r6
        L_0x00ae:
            java.util.Iterator r0 = (java.util.Iterator) r0
            java.lang.String r11 = "oldTxNotes"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r11)
            kotlin.sequences.Sequence r11 = kotlin.sequences.SequencesKt.asSequence(r0)
            java.util.List r11 = kotlin.sequences.SequencesKt.toList(r11)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.Collection r12 = (java.util.Collection) r12
            java.util.Iterator r11 = r11.iterator()
        L_0x00ca:
            boolean r13 = r11.hasNext()
            java.lang.String r14 = "it.note"
            java.lang.String r15 = "it"
            if (r13 == 0) goto L_0x00fb
            java.lang.Object r13 = r11.next()
            r9 = r13
            com.bitcoin.mwallet.TxNote r9 = (com.bitcoin.mwallet.TxNote) r9
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r15)
            java.lang.String r9 = r9.getNote()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r14)
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            boolean r9 = kotlin.text.StringsKt.isBlank(r9)
            r9 = r9 ^ r10
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00f9
            r12.add(r13)
        L_0x00f9:
            r9 = 2
            goto L_0x00ca
        L_0x00fb:
            java.util.List r12 = (java.util.List) r12
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r9 = new java.util.ArrayList
            r11 = 10
            int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r12, r11)
            r9.<init>(r13)
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r12 = r12.iterator()
        L_0x0110:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x013c
            java.lang.Object r13 = r12.next()
            com.bitcoin.mwallet.TxNote r13 = (com.bitcoin.mwallet.TxNote) r13
            com.bitcoin.mwallet.core.utils.NoteSecurity$Companion r11 = com.bitcoin.mwallet.core.utils.NoteSecurity.Companion
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r15)
            java.lang.String r10 = r13.getNote()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r14)
            java.lang.String r10 = r11.decryptOldFormat(r10, r1)
            kotlin.Pair r11 = new kotlin.Pair
            java.lang.String r13 = r13.getTxId()
            r11.<init>(r13, r10)
            r9.add(r11)
            r10 = 1
            r11 = 10
            goto L_0x0110
        L_0x013c:
            java.util.List r9 = (java.util.List) r9
            com.bitcoin.mwallet.UpdateNotesRequest$Builder r10 = com.bitcoin.mwallet.UpdateNotesRequest.newBuilder()
            com.bitcoin.bitcoink.Hex r11 = r3.getId()
            java.lang.String r11 = r11.getHex()
            com.bitcoin.mwallet.UpdateNotesRequest$Builder r10 = r10.setCopayerId(r11)
            java.lang.String r11 = r4.getId()
            com.bitcoin.mwallet.UpdateNotesRequest$Builder r10 = r10.setWalletId(r11)
            r11 = r9
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.Collection r12 = (java.util.Collection) r12
            java.util.Iterator r11 = r11.iterator()
        L_0x0164:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x018b
            java.lang.Object r13 = r11.next()
            r14 = r13
            kotlin.Pair r14 = (kotlin.Pair) r14
            java.lang.Object r14 = r14.getFirst()
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            boolean r14 = kotlin.text.StringsKt.isBlank(r14)
            r15 = 1
            r14 = r14 ^ r15
            java.lang.Boolean r14 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r14)
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x0164
            r12.add(r13)
            goto L_0x0164
        L_0x018b:
            java.util.List r12 = (java.util.List) r12
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r11 = new java.util.ArrayList
            r13 = 10
            int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r12, r13)
            r11.<init>(r13)
            java.util.Collection r11 = (java.util.Collection) r11
            java.util.Iterator r12 = r12.iterator()
        L_0x01a0:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x01d4
            java.lang.Object r13 = r12.next()
            kotlin.Pair r13 = (kotlin.Pair) r13
            com.bitcoin.mwallet.core.utils.NoteSecurity$Companion r14 = com.bitcoin.mwallet.core.utils.NoteSecurity.Companion
            java.lang.Object r15 = r13.getSecond()
            java.lang.String r15 = (java.lang.String) r15
            java.lang.String r14 = r14.encryptNewFormat(r15, r2)
            com.bitcoin.mwallet.TxNote$Builder r15 = com.bitcoin.mwallet.TxNote.newBuilder()
            com.bitcoin.mwallet.TxNote$Builder r14 = r15.setNote(r14)
            java.lang.Object r13 = r13.getFirst()
            java.lang.String r13 = (java.lang.String) r13
            com.bitcoin.mwallet.TxNote$Builder r13 = r14.setTxId(r13)
            com.google.protobuf.GeneratedMessageLite r13 = r13.build()
            com.bitcoin.mwallet.TxNote r13 = (com.bitcoin.mwallet.TxNote) r13
            r11.add(r13)
            goto L_0x01a0
        L_0x01d4:
            r15 = r11
            java.util.List r15 = (java.util.List) r15
            r11 = r15
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            r10.addAllTxNote(r11)
            kotlinx.coroutines.CoroutineDispatcher r11 = r5.getGrpcDispatcher()
            r14 = r11
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14
            com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$2 r18 = new com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$2
            r17 = 0
            r11 = r18
            r12 = r5
            r13 = r10
            r6 = r14
            r14 = r4
            r19 = r8
            r8 = r15
            r15 = r3
            r16 = r2
            r11.<init>(r12, r13, r14, r15, r16, r17)
            r11 = r18
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r7.L$0 = r5
            r7.L$1 = r4
            r7.L$2 = r3
            r7.L$3 = r2
            r7.L$4 = r1
            r7.L$5 = r0
            r7.L$6 = r9
            r7.L$7 = r10
            r7.L$8 = r8
            r0 = 2
            r7.label = r0
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r6, r11, r7)
            r1 = r19
            if (r0 != r1) goto L_0x0219
            return r1
        L_0x0219:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.p010tx.TxServiceGrpc.migrateOldNotes(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.CopayerId, com.bitcoin.mwallet.core.utils.signature.SigningKey, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
