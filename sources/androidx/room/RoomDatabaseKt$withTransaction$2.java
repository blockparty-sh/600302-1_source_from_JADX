package androidx.room;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "androidx.room.RoomDatabaseKt$withTransaction$2", mo38000f = "RoomDatabase.kt", mo38001i = {0}, mo38002l = {58}, mo38003m = "invokeSuspend", mo38004n = {"transactionElement"}, mo38005s = {"L$0"})
/* compiled from: RoomDatabase.kt */
final class RoomDatabaseKt$withTransaction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ RoomDatabase $this_withTransaction;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f82p$;

    RoomDatabaseKt$withTransaction$2(RoomDatabase roomDatabase, Function1 function1, Continuation continuation) {
        this.$this_withTransaction = roomDatabase;
        this.$block = function1;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        RoomDatabaseKt$withTransaction$2 roomDatabaseKt$withTransaction$2 = new RoomDatabaseKt$withTransaction$2(this.$this_withTransaction, this.$block, continuation);
        roomDatabaseKt$withTransaction$2.f82p$ = (CoroutineScope) obj;
        return roomDatabaseKt$withTransaction$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RoomDatabaseKt$withTransaction$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        TransactionElement transactionElement;
        TransactionElement transactionElement2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Element element = this.f82p$.getCoroutineContext().get(TransactionElement.Key);
            if (element == null) {
                Intrinsics.throwNpe();
            }
            transactionElement = (TransactionElement) element;
            transactionElement.acquire();
            try {
                this.$this_withTransaction.beginTransaction();
            } catch (Throwable th) {
                th = th;
                transactionElement.release();
                throw th;
            }
            try {
                Function1 function1 = this.$block;
                this.L$0 = transactionElement;
                this.label = 1;
                Object invoke = function1.invoke(this);
                if (invoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                transactionElement2 = transactionElement;
                obj = invoke;
            } catch (Throwable th2) {
                th = th2;
                this.$this_withTransaction.endTransaction();
                throw th;
            }
        } else if (i == 1) {
            transactionElement2 = (TransactionElement) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th3) {
                TransactionElement transactionElement3 = transactionElement2;
                th = th3;
                transactionElement = transactionElement3;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$this_withTransaction.setTransactionSuccessful();
        try {
            this.$this_withTransaction.endTransaction();
            transactionElement2.release();
            return obj;
        } catch (Throwable th4) {
            TransactionElement transactionElement4 = transactionElement2;
            th = th4;
            transactionElement = transactionElement4;
            transactionElement.release();
            throw th;
        }
    }
}
