package com.bitcoin.mwallet.app.flows.walletdetails.txdetails;

import com.bitcoin.mwallet.TxNote;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.NotesRepository;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.google.protobuf.GeneratedMessageLite;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter$setNoteDetails$1", mo38000f = "TxDetailsPresenter.kt", mo38001i = {0}, mo38002l = {88}, mo38003m = "invokeSuspend", mo38004n = {"txNote"}, mo38005s = {"L$0"})
/* compiled from: TxDetailsPresenter.kt */
final class TxDetailsPresenter$setNoteDetails$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $note;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f337p$;
    final /* synthetic */ TxDetailsPresenter this$0;

    TxDetailsPresenter$setNoteDetails$1(TxDetailsPresenter txDetailsPresenter, String str, Continuation continuation) {
        this.this$0 = txDetailsPresenter;
        this.$note = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TxDetailsPresenter$setNoteDetails$1 txDetailsPresenter$setNoteDetails$1 = new TxDetailsPresenter$setNoteDetails$1(this.this$0, this.$note, continuation);
        txDetailsPresenter$setNoteDetails$1.f337p$ = (CoroutineScope) obj;
        return txDetailsPresenter$setNoteDetails$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TxDetailsPresenter$setNoteDetails$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f337p$;
            GeneratedMessageLite build = TxNote.newBuilder().setNote(this.$note).setTxId(this.this$0.getTxId()).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "TxNote.newBuilder()\n    …\n                .build()");
            TxNote txNote = (TxNote) build;
            NotesRepository notesRepository = this.this$0.getNotesRepository();
            WalletId id = this.this$0.getWallet().getId();
            CopayerId walletCopayerId = this.this$0.getWallet().getCopayers().getWalletCopayerId();
            SigningKey signingKey = this.this$0.getWallet().getSigningKey();
            this.L$0 = txNote;
            this.label = 1;
            if (notesRepository.setTxNote(id, walletCopayerId, signingKey, txNote, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            TxNote txNote2 = (TxNote) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
