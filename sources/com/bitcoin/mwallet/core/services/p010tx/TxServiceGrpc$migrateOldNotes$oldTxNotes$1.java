package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.GetOldTxNotesRequest;
import com.bitcoin.mwallet.TxNote;
import com.bitcoin.mwallet.TxServiceGrpc.TxServiceBlockingStub;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.SignedStub;
import com.bitcoin.mwallet.core.utils.SignedStub.Companion;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.stub.AbstractStub;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00040\u0001*\u00020\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/TxNote;", "kotlin.jvm.PlatformType", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$oldTxNotes$1", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$migrateOldNotes$oldTxNotes$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$migrateOldNotes$oldTxNotes$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Iterator<? extends TxNote>>, Object> {
    final /* synthetic */ CopayerId $copayerId;
    final /* synthetic */ SigningKey $signingKey;
    final /* synthetic */ WalletId $walletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f448p$;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$migrateOldNotes$oldTxNotes$1(TxServiceGrpc txServiceGrpc, CopayerId copayerId, WalletId walletId, SigningKey signingKey, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        this.$copayerId = copayerId;
        this.$walletId = walletId;
        this.$signingKey = signingKey;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TxServiceGrpc$migrateOldNotes$oldTxNotes$1 txServiceGrpc$migrateOldNotes$oldTxNotes$1 = new TxServiceGrpc$migrateOldNotes$oldTxNotes$1(this.this$0, this.$copayerId, this.$walletId, this.$signingKey, continuation);
        txServiceGrpc$migrateOldNotes$oldTxNotes$1.f448p$ = (CoroutineScope) obj;
        return txServiceGrpc$migrateOldNotes$oldTxNotes$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TxServiceGrpc$migrateOldNotes$oldTxNotes$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f448p$;
            GetOldTxNotesRequest getOldTxNotesRequest = (GetOldTxNotesRequest) GetOldTxNotesRequest.newBuilder().setCopayerId(this.$copayerId.getId().getHex()).setWalletId(this.$walletId.getId()).build();
            Companion companion = SignedStub.Companion;
            AbstractStub access$getBlockingStub$p = this.this$0.blockingStub;
            byte[] byteArray = getOldTxNotesRequest.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "oldNotesRequest.toByteArray()");
            return ((TxServiceBlockingStub) companion.signed(access$getBlockingStub$p, byteArray, this.$walletId, this.$copayerId, this.$signingKey)).getOldTxNotes(getOldTxNotesRequest);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
