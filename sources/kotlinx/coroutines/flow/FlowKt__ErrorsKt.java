package kotlinx.coroutines.flow;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\u001a]\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\b23\u0010\n\u001a/\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000b¢\u0006\u0002\b\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0013\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0002\b\u0016\u001aF\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\t0\b2\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u0002`\u0004H\u0007\u001aE\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\b2\u0006\u0010\u0018\u001a\u0002H\t2\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u0002`\u0004H\u0007¢\u0006\u0002\u0010\u001b\u001aB\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\b2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u0002`\u0004H\u0007\"$\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u0002`\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006*\"\u0010\u001f\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, mo37405d2 = {"ALWAYS_TRUE", "Lkotlin/Function1;", "", "", "Lkotlinx/coroutines/flow/ExceptionPredicate;", "getALWAYS_TRUE$FlowKt__ErrorsKt", "()Lkotlin/jvm/functions/Function1;", "collectSafely", "Lkotlinx/coroutines/flow/Flow;", "T", "onException", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "collectSafely$FlowKt__ErrorsKt", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "isCancellationCause", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "isCancellationCause$FlowKt__ErrorsKt", "onErrorCollect", "fallback", "predicate", "onErrorReturn", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "retry", "retries", "", "ExceptionPredicate", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Errors.kt */
final /* synthetic */ class FlowKt__ErrorsKt {
    private static final Function1<Throwable, Boolean> ALWAYS_TRUE = FlowKt__ErrorsKt$ALWAYS_TRUE$1.INSTANCE;

    private static final Function1<Throwable, Boolean> getALWAYS_TRUE$FlowKt__ErrorsKt() {
        return ALWAYS_TRUE;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.functions.Function1, code=kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean>, for r2v0, types: [kotlin.jvm.functions.Function1] */
    @org.jetbrains.annotations.NotNull
    @kotlinx.coroutines.FlowPreview
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ kotlinx.coroutines.flow.Flow onErrorCollect$default(kotlinx.coroutines.flow.Flow r0, kotlinx.coroutines.flow.Flow r1, kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean> r2, int r3, java.lang.Object r4) {
        /*
            r3 = r3 & 2
            if (r3 == 0) goto L_0x0006
            kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean> r2 = ALWAYS_TRUE
        L_0x0006:
            kotlinx.coroutines.flow.Flow r0 = kotlinx.coroutines.flow.FlowKt.onErrorCollect(r0, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.onErrorCollect$default(kotlinx.coroutines.flow.Flow, kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function1, int, java.lang.Object):kotlinx.coroutines.flow.Flow");
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> onErrorCollect(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2, @NotNull Function1<? super Throwable, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$onErrorCollect");
        Intrinsics.checkParameterIsNotNull(flow2, "fallback");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        return collectSafely$FlowKt__ErrorsKt(flow, new FlowKt__ErrorsKt$onErrorCollect$1(function1, flow2, null));
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.functions.Function1, code=kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean>, for r2v0, types: [kotlin.jvm.functions.Function1] */
    @org.jetbrains.annotations.NotNull
    @kotlinx.coroutines.FlowPreview
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ kotlinx.coroutines.flow.Flow onErrorReturn$default(kotlinx.coroutines.flow.Flow r0, java.lang.Object r1, kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean> r2, int r3, java.lang.Object r4) {
        /*
            r3 = r3 & 2
            if (r3 == 0) goto L_0x0006
            kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean> r2 = ALWAYS_TRUE
        L_0x0006:
            kotlinx.coroutines.flow.Flow r0 = kotlinx.coroutines.flow.FlowKt.onErrorReturn(r0, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.onErrorReturn$default(kotlinx.coroutines.flow.Flow, java.lang.Object, kotlin.jvm.functions.Function1, int, java.lang.Object):kotlinx.coroutines.flow.Flow");
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> onErrorReturn(@NotNull Flow<? extends T> flow, T t, @NotNull Function1<? super Throwable, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$onErrorReturn");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        return collectSafely$FlowKt__ErrorsKt(flow, new FlowKt__ErrorsKt$onErrorReturn$1(function1, t, null));
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.functions.Function1, code=kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean>, for r2v0, types: [kotlin.jvm.functions.Function1] */
    @org.jetbrains.annotations.NotNull
    @kotlinx.coroutines.FlowPreview
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ kotlinx.coroutines.flow.Flow retry$default(kotlinx.coroutines.flow.Flow r0, int r1, kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0007
            r1 = 2147483647(0x7fffffff, float:NaN)
        L_0x0007:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000d
            kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Boolean> r2 = ALWAYS_TRUE
        L_0x000d:
            kotlinx.coroutines.flow.Flow r0 = kotlinx.coroutines.flow.FlowKt.retry(r0, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry$default(kotlinx.coroutines.flow.Flow, int, kotlin.jvm.functions.Function1, int, java.lang.Object):kotlinx.coroutines.flow.Flow");
    }

    /* access modifiers changed from: private */
    public static final boolean isCancellationCause$FlowKt__ErrorsKt(@NotNull Throwable th, CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job == null || !job.isCancelled()) {
            return false;
        }
        return Intrinsics.areEqual((Object) (CancellationException) StackTraceRecoveryKt.unwrap(job.getCancellationException()), (Object) StackTraceRecoveryKt.unwrap(th));
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> retry(@NotNull Flow<? extends T> flow, int i, @NotNull Function1<? super Throwable, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$retry");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        if (i > 0) {
            return new FlowKt__ErrorsKt$retry$$inlined$unsafeFlow$1<>(flow, i, function1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected positive amount of retries, but had ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    private static final <T> Flow<T> collectSafely$FlowKt__ErrorsKt(@NotNull Flow<? extends T> flow, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return new FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1<>(flow, function3);
    }
}
