package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.UtxoDao;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.UtxoRepository$upsert$2", mo38000f = "UtxoRepository.kt", mo38001i = {}, mo38002l = {130}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: UtxoRepository.kt */
final class UtxoRepository$upsert$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Collection $utxos;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f399p$;
    final /* synthetic */ UtxoRepository this$0;

    UtxoRepository$upsert$2(UtxoRepository utxoRepository, Collection collection, Continuation continuation) {
        this.this$0 = utxoRepository;
        this.$utxos = collection;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        UtxoRepository$upsert$2 utxoRepository$upsert$2 = new UtxoRepository$upsert$2(this.this$0, this.$utxos, continuation);
        utxoRepository$upsert$2.f399p$ = (CoroutineScope) obj;
        return utxoRepository$upsert$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((UtxoRepository$upsert$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f399p$;
            UtxoDao access$getUtxoDao$p = this.this$0.utxoDao;
            Collection collection = this.$utxos;
            if (collection != null) {
                Object[] array = collection.toArray(new Utxo[0]);
                if (array != null) {
                    Utxo[] utxoArr = (Utxo[]) array;
                    Utxo[] utxoArr2 = (Utxo[]) Arrays.copyOf(utxoArr, utxoArr.length);
                    this.label = 1;
                    if (access$getUtxoDao$p.upsert(utxoArr2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
