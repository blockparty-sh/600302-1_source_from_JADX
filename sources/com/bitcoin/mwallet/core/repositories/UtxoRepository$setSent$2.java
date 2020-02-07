package com.bitcoin.mwallet.core.repositories;

import java.util.List;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.UtxoRepository$setSent$2", mo38000f = "UtxoRepository.kt", mo38001i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, mo38002l = {101, 102, 103}, mo38003m = "invokeSuspend", mo38004n = {"$this$forEach$iv", "element$iv", "it", "$this$forEach$iv", "element$iv", "it", "$this$forEach$iv", "element$iv", "it"}, mo38005s = {"L$0", "L$2", "L$3", "L$0", "L$2", "L$3", "L$0", "L$2", "L$3"})
/* compiled from: UtxoRepository.kt */
final class UtxoRepository$setSent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $spentUtxos;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f397p$;
    final /* synthetic */ UtxoRepository this$0;

    UtxoRepository$setSent$2(UtxoRepository utxoRepository, List list, Continuation continuation) {
        this.this$0 = utxoRepository;
        this.$spentUtxos = list;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        UtxoRepository$setSent$2 utxoRepository$setSent$2 = new UtxoRepository$setSent$2(this.this$0, this.$spentUtxos, continuation);
        utxoRepository$setSent$2.f397p$ = (CoroutineScope) obj;
        return utxoRepository$setSent$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((UtxoRepository$setSent$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0106 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x005e
            if (r2 == r5) goto L_0x0049
            if (r2 == r4) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            java.lang.Object r2 = r0.L$3
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r2 = r0.L$2
            java.lang.Object r2 = r0.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r6 = r0.L$0
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            kotlin.ResultKt.throwOnFailure(r18)
            r7 = r2
            r8 = r6
            goto L_0x006d
        L_0x0027:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x002f:
            java.lang.Object r2 = r0.L$3
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r6 = r0.L$2
            java.lang.Object r7 = r0.L$1
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r0.L$0
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            kotlin.ResultKt.throwOnFailure(r18)
            r9 = r1
            r1 = r0
        L_0x0042:
            r16 = r8
            r8 = r6
            r6 = r16
            goto L_0x0107
        L_0x0049:
            java.lang.Object r2 = r0.L$3
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r6 = r0.L$2
            java.lang.Object r7 = r0.L$1
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r0.L$0
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            kotlin.ResultKt.throwOnFailure(r18)
            r9 = r1
            r1 = r0
            goto L_0x00e0
        L_0x005e:
            kotlin.ResultKt.throwOnFailure(r18)
            kotlinx.coroutines.CoroutineScope r2 = r0.f397p$
            java.util.List r2 = r0.$spentUtxos
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r6 = r2.iterator()
            r8 = r2
            r7 = r6
        L_0x006d:
            r2 = r1
            r1 = r0
        L_0x006f:
            boolean r6 = r7.hasNext()
            if (r6 == 0) goto L_0x0132
            java.lang.Object r6 = r7.next()
            r9 = r6
            kotlin.Pair r9 = (kotlin.Pair) r9
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Deleting: "
            r10.append(r11)
            java.lang.Object r11 = r9.getFirst()
            com.bitcoin.bitcoink.tx.TxId r11 = (com.bitcoin.bitcoink.p008tx.TxId) r11
            r10.append(r11)
            r11 = 32
            r10.append(r11)
            java.lang.Object r11 = r9.getSecond()
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r11 = 0
            java.lang.Object[] r12 = new java.lang.Object[r11]
            timber.log.Timber.m426d(r10, r12)
            com.bitcoin.mwallet.core.repositories.UtxoRepository r10 = r1.this$0
            com.bitcoin.mwallet.core.dao.SpentUtxoDao r10 = r10.spentUtxoDao
            com.bitcoin.mwallet.core.models.tx.SpentUtxo[] r12 = new com.bitcoin.mwallet.core.models.p009tx.SpentUtxo[r5]
            com.bitcoin.mwallet.core.models.tx.SpentUtxo r13 = new com.bitcoin.mwallet.core.models.tx.SpentUtxo
            java.lang.Object r14 = r9.getFirst()
            com.bitcoin.bitcoink.tx.TxId r14 = (com.bitcoin.bitcoink.p008tx.TxId) r14
            java.lang.Object r15 = r9.getSecond()
            java.lang.Number r15 = (java.lang.Number) r15
            int r15 = r15.intValue()
            r13.<init>(r14, r15)
            r12[r11] = r13
            r1.L$0 = r8
            r1.L$1 = r7
            r1.L$2 = r6
            r1.L$3 = r9
            r1.label = r5
            java.lang.Object r10 = r10.upsert(r12, r1)
            if (r10 != r2) goto L_0x00db
            return r2
        L_0x00db:
            r16 = r9
            r9 = r2
            r2 = r16
        L_0x00e0:
            com.bitcoin.mwallet.core.repositories.UtxoRepository r10 = r1.this$0
            com.bitcoin.mwallet.core.dao.UtxoDao r10 = r10.utxoDao
            java.lang.Object r11 = r2.getFirst()
            com.bitcoin.bitcoink.tx.TxId r11 = (com.bitcoin.bitcoink.p008tx.TxId) r11
            java.lang.Object r12 = r2.getSecond()
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
            r1.L$0 = r8
            r1.L$1 = r7
            r1.L$2 = r6
            r1.L$3 = r2
            r1.label = r4
            java.lang.Object r10 = r10.delete(r11, r12, r1)
            if (r10 != r9) goto L_0x0042
            return r9
        L_0x0107:
            com.bitcoin.mwallet.core.repositories.UtxoRepository r10 = r1.this$0
            com.bitcoin.mwallet.core.dao.SlpUtxoDao r10 = r10.slpUtxoDao
            java.lang.Object r11 = r2.getFirst()
            com.bitcoin.bitcoink.tx.TxId r11 = (com.bitcoin.bitcoink.p008tx.TxId) r11
            java.lang.Object r12 = r2.getSecond()
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
            r1.L$0 = r6
            r1.L$1 = r7
            r1.L$2 = r8
            r1.L$3 = r2
            r1.label = r3
            java.lang.Object r2 = r10.delete(r11, r12, r1)
            if (r2 != r9) goto L_0x012e
            return r9
        L_0x012e:
            r8 = r6
            r2 = r9
            goto L_0x006f
        L_0x0132:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.UtxoRepository$setSent$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
