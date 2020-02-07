package kotlinx.coroutines.flow;

import kotlin.BuilderInference;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000`\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\b\u0010\u0003\u001a\u00020\u0001H\u0007\u001aI\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00052#\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\t¢\u0006\u0002\b\nH\u0007\u001a>\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\tH\u0007\u001aV\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00052(\u0010\f\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a$\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00050\u0005H\u0007\u001aS\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u000521\u0010\u0014\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0007¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a$\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00050\u0005H\u0007\u001a&\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0007\u001a,\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005H\u0007\u001a&\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0007\u001a~\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010!\u001a\u0002H\u00062H\b\u0001\u0010\"\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0006¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H\u0007¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010#H\u0007ø\u0001\u0000¢\u0006\u0002\u0010%\u001a&\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010'\u001a\u00020(H\u0007\u001a\u0018\u0010)\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0005H\u0007\u001a,\u0010)\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00130\tH\u0007\u001a@\u0010)\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00130\t2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00130\tH\u0007\u001a&\u0010-\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0007\u001aL\u0010.\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070/2\u0006\u0010\u001b\u001a\u00020\u001c2\u001c\u00100\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH\u0007ø\u0001\u0000¢\u0006\u0002\u00101\u0002\u0004\n\u0002\b\u0019¨\u00062"}, mo37405d2 = {"BehaviourSubject", "", "PublishSubject", "ReplaySubject", "compose", "Lkotlinx/coroutines/flow/Flow;", "R", "T", "transformer", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "concatMap", "mapper", "flatMap", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flatten", "forEach", "", "action", "Lkotlin/ParameterName;", "name", "value", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)V", "merge", "observeOn", "context", "Lkotlin/coroutines/CoroutineContext;", "onErrorResume", "fallback", "publishOn", "scanFold", "initial", "operation", "Lkotlin/Function3;", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "skip", "count", "", "subscribe", "onEach", "onError", "", "subscribeOn", "withContext", "Lkotlinx/coroutines/flow/FlowCollector;", "block", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Migration.kt */
public final class MigrationKt {
    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    public static final <T> Flow<T> observeOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$observeOn");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    public static final <T> Flow<T> publishOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$publishOn");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use flowOn instead")
    public static final <T> Flow<T> subscribeOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$subscribeOn");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use BroadcastChannel.asFlow()")
    public static final Object BehaviourSubject() {
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "ReplaySubject is not supported. The closest analogue is buffered broadcast channel")
    public static final Object ReplaySubject() {
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "PublishSubject is not supported")
    public static final Object PublishSubject() {
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is named onErrorCollect", replaceWith = @ReplaceWith(expression = "onErrorCollect(fallback)", imports = {}))
    public static final <T> Flow<T> onErrorResume(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$onErrorResume");
        Intrinsics.checkParameterIsNotNull(flow2, "fallback");
        throw new IllegalStateException("Should not be called".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "withContext in flow body is deprecated, use flowOn instead")
    public static final <T, R> void withContext(@NotNull FlowCollector<? super T> flowCollector, @NotNull CoroutineContext coroutineContext, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(flowCollector, "$this$withContext");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        throw new IllegalStateException("Should not be called".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use launch + collect instead")
    public static final <T> void subscribe(@NotNull Flow<? extends T> flow) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$subscribe");
        throw new IllegalStateException("Should not be called".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use launch + collect instead")
    public static final <T> void subscribe(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$subscribe");
        Intrinsics.checkParameterIsNotNull(function1, "onEach");
        throw new IllegalStateException("Should not be called".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use launch + collect instead")
    public static final <T> void subscribe(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$subscribe");
        Intrinsics.checkParameterIsNotNull(function1, "onEach");
        Intrinsics.checkParameterIsNotNull(function12, "onError");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is named flatMapConcat", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    public static final <T, R> Flow<R> flatMap(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$flatMap");
        Intrinsics.checkParameterIsNotNull(function2, "mapper");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is named flatMapConcat", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    public static final <T, R> Flow<R> concatMap(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, ? extends Flow<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$concatMap");
        Intrinsics.checkParameterIsNotNull(function1, "mapper");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is named flattenConcat", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    public static final <T> Flow<T> merge(@NotNull Flow<? extends Flow<? extends T>> flow) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$merge");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is named flattenConcat", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    public static final <T> Flow<T> flatten(@NotNull Flow<? extends Flow<? extends T>> flow) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$flatten");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Kotlin analogue of compose is 'let'", replaceWith = @ReplaceWith(expression = "let(transformer)", imports = {}))
    public static final <T, R> Flow<R> compose(@NotNull Flow<? extends T> flow, @NotNull Function1<? super Flow<? extends T>, ? extends Flow<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$compose");
        Intrinsics.checkParameterIsNotNull(function1, "transformer");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Kotlin analogue of 'skip' is 'drop'", replaceWith = @ReplaceWith(expression = "drop(count)", imports = {}))
    public static final <T> Flow<T> skip(@NotNull Flow<? extends T> flow, int i) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$skip");
        throw new IllegalStateException("Should not be called".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'forEach' is 'collect'", replaceWith = @ReplaceWith(expression = "collect(block)", imports = {}))
    public static final <T> void forEach(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$forEach");
        Intrinsics.checkParameterIsNotNull(function2, "action");
        throw new IllegalStateException("Should not be called".toString());
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow has less verbose 'scan' shortcut", replaceWith = @ReplaceWith(expression = "scan(initial, operation)", imports = {}))
    public static final <T, R> Flow<R> scanFold(@NotNull Flow<? extends T> flow, R r, @NotNull @BuilderInference Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$scanFold");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        throw new IllegalStateException("Should not be called".toString());
    }
}
