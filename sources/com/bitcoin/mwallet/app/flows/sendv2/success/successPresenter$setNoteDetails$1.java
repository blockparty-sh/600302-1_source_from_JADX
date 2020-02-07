package com.bitcoin.mwallet.app.flows.sendv2.success;

import com.bitcoin.mwallet.TxNote;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.NotesRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.success.successPresenter$setNoteDetails$1", mo38000f = "successPresenter.kt", mo38001i = {1, 1}, mo38002l = {104, 109}, mo38003m = "invokeSuspend", mo38004n = {"wallet", "txNote"}, mo38005s = {"L$0", "L$1"})
/* compiled from: successPresenter.kt */
final class successPresenter$setNoteDetails$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $note;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f306p$;
    final /* synthetic */ successPresenter this$0;

    successPresenter$setNoteDetails$1(successPresenter successpresenter, String str, Continuation continuation) {
        this.this$0 = successpresenter;
        this.$note = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        successPresenter$setNoteDetails$1 successpresenter_setnotedetails_1 = new successPresenter$setNoteDetails$1(this.this$0, this.$note, continuation);
        successpresenter_setnotedetails_1.f306p$ = (CoroutineScope) obj;
        return successpresenter_setnotedetails_1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((successPresenter$setNoteDetails$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f306p$;
            WalletRepository walletRepository = this.this$0.getWalletRepository();
            WalletId walletId = this.this$0.getWalletId();
            this.label = 1;
            obj = walletRepository.getWalletById(walletId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            TxNote txNote = (TxNote) this.L$1;
            C1261Wallet wallet = (C1261Wallet) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        C1261Wallet wallet2 = (C1261Wallet) obj;
        if (wallet2 != null) {
            GeneratedMessageLite build = TxNote.newBuilder().setNote(this.$note).setTxId(this.this$0.getTxId().getId()).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "TxNote.newBuilder()\n    …                 .build()");
            TxNote txNote2 = (TxNote) build;
            NotesRepository notesRepository = this.this$0.getNotesRepository();
            WalletId walletId2 = this.this$0.getWalletId();
            CopayerId walletCopayerId = wallet2.getCopayers().getWalletCopayerId();
            SigningKey signingKey = wallet2.getSigningKey();
            this.L$0 = wallet2;
            this.L$1 = txNote2;
            this.label = 2;
            if (notesRepository.setTxNote(walletId2, walletCopayerId, signingKey, txNote2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
