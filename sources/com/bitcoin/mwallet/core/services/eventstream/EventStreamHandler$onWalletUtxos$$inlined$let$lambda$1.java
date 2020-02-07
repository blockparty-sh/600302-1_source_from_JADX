package com.bitcoin.mwallet.core.services.eventstream;

import com.bitcoin.mwallet.WalletUtxos;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/bitcoin/mwallet/core/services/eventstream/EventStreamHandler$onWalletUtxos$1$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: EventStreamHandler.kt */
final class EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $slp;
    final /* synthetic */ List $slpUtxos;
    final /* synthetic */ List $utxos;
    final /* synthetic */ WalletId $walletId$inlined;
    final /* synthetic */ WalletUtxos $walletUtxos$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f435p$;
    final /* synthetic */ EventStreamHandler this$0;

    EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1(List list, List list2, List list3, Continuation continuation, EventStreamHandler eventStreamHandler, WalletUtxos walletUtxos, WalletId walletId) {
        this.$utxos = list;
        this.$slpUtxos = list2;
        this.$slp = list3;
        this.this$0 = eventStreamHandler;
        this.$walletUtxos$inlined = walletUtxos;
        this.$walletId$inlined = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1 eventStreamHandler$onWalletUtxos$$inlined$let$lambda$1 = new EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1(this.$utxos, this.$slpUtxos, this.$slp, continuation, this.this$0, this.$walletUtxos$inlined, this.$walletId$inlined);
        eventStreamHandler$onWalletUtxos$$inlined$let$lambda$1.f435p$ = (CoroutineScope) obj;
        return eventStreamHandler$onWalletUtxos$$inlined$let$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0025
            if (r1 == r4) goto L_0x0021
            if (r1 == r3) goto L_0x001d
            if (r1 != r2) goto L_0x0015
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0063
        L_0x0015:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x001d:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0050
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x003d
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineScope r6 = r5.f435p$
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r6 = r5.this$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r6 = r6.utxoRepository
            java.util.List r1 = r5.$utxos
            java.util.Collection r1 = (java.util.Collection) r1
            r5.label = r4
            java.lang.Object r6 = r6.upsert(r1, r5)
            if (r6 != r0) goto L_0x003d
            return r0
        L_0x003d:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r6 = r5.this$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r6 = r6.utxoRepository
            java.util.List r1 = r5.$slpUtxos
            java.util.Collection r1 = (java.util.Collection) r1
            r5.label = r3
            java.lang.Object r6 = r6.slpUtxoUpsert(r1, r5)
            if (r6 != r0) goto L_0x0050
            return r0
        L_0x0050:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r6 = r5.this$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r6 = r6.slpRepository
            java.util.List r1 = r5.$slp
            java.util.Collection r1 = (java.util.Collection) r1
            r5.label = r2
            java.lang.Object r6 = r6.upsert(r1, r5)
            if (r6 != r0) goto L_0x0063
            return r0
        L_0x0063:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
