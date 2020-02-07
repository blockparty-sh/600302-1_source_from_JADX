package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.TxNote;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.stub.StreamObserver;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J?\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H¦@ø\u0001\u0000¢\u0006\u0002\u0010$J1\u0010%\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010'J1\u0010(\u001a\u00020)2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+H¦@ø\u0001\u0000¢\u0006\u0002\u0010,\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/TxService;", "", "broadcastBip70Tx", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "url", "", "depositAddress", "txRaw", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "broadcastTx", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBip70PaymentInfo", "Lcom/bitcoin/mwallet/core/services/tx/GetBip70PaymentResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionHistory", "", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "observer", "Lio/grpc/stub/StreamObserver;", "Lcom/bitcoin/mwallet/TxHistory;", "startTime", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lio/grpc/stub/StreamObserver;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUtxos", "Lcom/bitcoin/mwallet/core/services/tx/GetUtxosResponse;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migrateOldNotes", "oldEncryptionKey", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNotes", "Lcom/bitcoin/mwallet/core/services/tx/UpdateNotesResponse;", "note", "Lcom/bitcoin/mwallet/TxNote;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lcom/bitcoin/mwallet/TxNote;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxService */
/* compiled from: TxService.kt */
public interface TxService {
    @Nullable
    Object broadcastBip70Tx(@NotNull WalletId walletId, @NotNull String str, @NotNull String str2, @NotNull byte[] bArr, @NotNull Continuation<? super Bip70BroadcastTxResponse> continuation);

    @Nullable
    Object broadcastTx(@NotNull Coin coin, @NotNull byte[] bArr, @NotNull Continuation<? super BroadcastTxResponse> continuation);

    @Nullable
    Object getBip70PaymentInfo(@NotNull String str, @NotNull Continuation<? super GetBip70PaymentResponse> continuation);

    @Nullable
    Object getTransactionHistory(@NotNull WalletId walletId, @NotNull CopayerId copayerId, @NotNull SigningKey signingKey, @NotNull StreamObserver<TxHistory> streamObserver, long j, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object getUtxos(@NotNull C1261Wallet wallet, @NotNull Continuation<? super GetUtxosResponse> continuation);

    @Nullable
    Object migrateOldNotes(@NotNull WalletId walletId, @NotNull CopayerId copayerId, @NotNull SigningKey signingKey, @NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object updateNotes(@NotNull WalletId walletId, @NotNull CopayerId copayerId, @NotNull SigningKey signingKey, @NotNull TxNote txNote, @NotNull Continuation<? super UpdateNotesResponse> continuation);
}
