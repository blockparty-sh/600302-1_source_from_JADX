package com.bitcoin.mwallet.app.flows.sendv2.review;

import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setWalletId$2", mo38000f = "ReviewPresenter.kt", mo38001i = {1, 1, 1, 2, 2, 2}, mo38002l = {136, 140, 152}, mo38003m = "invokeSuspend", mo38004n = {"walletTemp", "it", "slpUtxos", "walletTemp", "it", "slpUtxos"}, mo38005s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: ReviewPresenter.kt */
final class ReviewPresenter$setWalletId$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WalletId $id;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f288p$;
    final /* synthetic */ ReviewPresenter this$0;

    ReviewPresenter$setWalletId$2(ReviewPresenter reviewPresenter, WalletId walletId, Continuation continuation) {
        this.this$0 = reviewPresenter;
        this.$id = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ReviewPresenter$setWalletId$2 reviewPresenter$setWalletId$2 = new ReviewPresenter$setWalletId$2(this.this$0, this.$id, continuation);
        reviewPresenter$setWalletId$2.f288p$ = (CoroutineScope) obj;
        return reviewPresenter$setWalletId$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ReviewPresenter$setWalletId$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0122 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0123  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0074
            if (r2 == r5) goto L_0x006e
            if (r2 == r4) goto L_0x005a
            if (r2 != r3) goto L_0x0052
            java.lang.Object r1 = r0.L$10
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r1
            java.lang.Object r2 = r0.L$9
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r3 = r0.L$8
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r3
            java.lang.Object r4 = r0.L$7
            com.bitcoin.bitcoink.Network r4 = (com.bitcoin.bitcoink.Network) r4
            java.lang.Object r5 = r0.L$6
            com.bitcoin.bitcoink.DerivationPath r5 = (com.bitcoin.bitcoink.DerivationPath) r5
            java.lang.Object r6 = r0.L$5
            com.bitcoin.mwallet.core.models.credential.CredentialId r6 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r6
            java.lang.Object r7 = r0.L$4
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$3
            com.bitcoin.mwallet.core.models.Coin r8 = (com.bitcoin.mwallet.core.models.Coin) r8
            boolean r9 = r0.Z$0
            java.lang.Object r10 = r0.L$2
            com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos r10 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos) r10
            java.lang.Object r11 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r11 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r11
            java.lang.Object r11 = r0.L$0
            com.bitcoin.mwallet.core.models.wallet.Wallet r11 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r11
            kotlin.ResultKt.throwOnFailure(r19)
            r13 = r10
            r14 = r11
            r10 = r8
            r11 = r9
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r2 = r19
            goto L_0x0134
        L_0x0052:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x005a:
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos r2 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos) r2
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r2 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r2
            java.lang.Object r4 = r0.L$0
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            kotlin.ResultKt.throwOnFailure(r19)
            r5 = r2
            r2 = r4
            r4 = r19
            goto L_0x00cb
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r19)
            r2 = r19
            goto L_0x0095
        L_0x0074:
            kotlin.ResultKt.throwOnFailure(r19)
            kotlinx.coroutines.CoroutineScope r2 = r0.f288p$
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = r0.this$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = r0.$id
            r2.setWalletId(r6)
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = r0.this$0
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor r2 = r2.getWalletInteractor
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r6 = r0.this$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = r6.getWalletId()
            r0.label = r5
            java.lang.Object r2 = r2.getWallet(r6, r0)
            if (r2 != r1) goto L_0x0095
            return r1
        L_0x0095:
            com.bitcoin.mwallet.core.models.wallet.Wallet r2 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r2
            if (r2 == 0) goto L_0x0141
            r5 = 0
            com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos r5 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos) r5
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r6 = r0.this$0
            com.bitcoin.mwallet.core.models.slp.SlpId r6 = r6.getTokenId()
            if (r6 == 0) goto L_0x00d1
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r6 = r0.this$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r6 = r6.utxoRepository
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r7 = r0.this$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = r7.getWalletId()
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r8 = r0.this$0
            com.bitcoin.mwallet.core.models.slp.SlpId r8 = r8.getTokenId()
            if (r8 != 0) goto L_0x00bb
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00bb:
            r0.L$0 = r2
            r0.L$1 = r2
            r0.L$2 = r5
            r0.label = r4
            java.lang.Object r4 = r6.walletTokenUtxosNonLive(r7, r8, r0)
            if (r4 != r1) goto L_0x00ca
            return r1
        L_0x00ca:
            r5 = r2
        L_0x00cb:
            com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos r4 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos) r4
            r11 = r2
            r10 = r4
            r2 = r5
            goto L_0x00d3
        L_0x00d1:
            r11 = r2
            r10 = r5
        L_0x00d3:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r4 = r0.this$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = r11.getPri_key()
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = r11.getId()
            com.bitcoin.bitcoink.Network r7 = r11.getNetwork()
            com.bitcoin.bitcoink.DerivationPath r8 = r11.getPath()
            com.bitcoin.mwallet.core.models.credential.CredentialId r9 = r11.getCredentialId()
            java.lang.String r12 = r11.getName()
            com.bitcoin.mwallet.core.models.Coin r13 = r11.getCoin()
            boolean r14 = r11.isMultiSig()
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r15 = r0.this$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r15 = r15.utxoRepository
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r3 = r0.this$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = r3.getWalletId()
            r0.L$0 = r11
            r0.L$1 = r2
            r0.L$2 = r10
            r0.Z$0 = r14
            r0.L$3 = r13
            r0.L$4 = r12
            r0.L$5 = r9
            r0.L$6 = r8
            r0.L$7 = r7
            r0.L$8 = r6
            r0.L$9 = r5
            r0.L$10 = r4
            r2 = 3
            r0.label = r2
            java.lang.Object r2 = r15.getWalletUtxos(r3, r0)
            if (r2 != r1) goto L_0x0123
            return r1
        L_0x0123:
            r1 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r12
            r16 = r13
            r13 = r10
            r10 = r16
            r17 = r14
            r14 = r11
            r11 = r17
        L_0x0134:
            r12 = r2
            com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos r12 = (com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos) r12
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r2 = new com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData
            r3 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r1.setWalletData(r2)
            goto L_0x0142
        L_0x0141:
            r14 = r2
        L_0x0142:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r0.this$0
            androidx.lifecycle.MutableLiveData r1 = r1.getWallet()
            r1.setValue(r14)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setWalletId$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
