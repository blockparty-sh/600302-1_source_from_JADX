package androidx.room;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo37405d2 = {"<anonymous>", "", "run", "androidx/room/RoomDatabaseKt$acquireTransactionThread$2$2"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: androidx.room.RoomDatabaseKt$acquireTransactionThread$$inlined$suspendCancellableCoroutine$lambda$2 */
/* compiled from: RoomDatabase.kt */
final class C0627xd529b4c1 implements Runnable {
    final /* synthetic */ CancellableContinuation $continuation;
    final /* synthetic */ Job $controlJob$inlined;
    final /* synthetic */ Executor $this_acquireTransactionThread$inlined;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/room/RoomDatabaseKt$acquireTransactionThread$2$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* renamed from: androidx.room.RoomDatabaseKt$acquireTransactionThread$$inlined$suspendCancellableCoroutine$lambda$2$1 */
    /* compiled from: RoomDatabase.kt */
    static final class C06281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f81p$;
        final /* synthetic */ C0627xd529b4c1 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C06281 r0 = new C06281(this.this$0, continuation);
            r0.f81p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C06281) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f81p$;
                Continuation continuation = this.this$0.$continuation;
                Element element = coroutineScope.getCoroutineContext().get(ContinuationInterceptor.Key);
                if (element == null) {
                    Intrinsics.throwNpe();
                }
                Companion companion = Result.Companion;
                continuation.resumeWith(Result.m479constructorimpl(element));
                Job job = this.this$0.$controlJob$inlined;
                this.label = 1;
                if (job.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    C0627xd529b4c1(CancellableContinuation cancellableContinuation, Executor executor, Job job) {
        this.$continuation = cancellableContinuation;
        this.$this_acquireTransactionThread$inlined = executor;
        this.$controlJob$inlined = job;
    }

    public final void run() {
        BuildersKt__BuildersKt.runBlocking$default(null, new C06281(this, null), 1, null);
    }
}
