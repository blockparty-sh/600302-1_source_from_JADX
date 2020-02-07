package kotlinx.coroutines.flow;

import java.util.Iterator;
import kotlin.BuilderInference;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\u001c\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\t\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001aM\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0007\u001aM\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a!\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0010\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0011\u001a-\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0013\"\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0014\u001aT\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u001724\b\u0001\u0010\u0003\u001a.\u0012\u0004\u0012\u00020\u0018\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00020\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0002\b\tH\u0007\u001aN\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0005\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001fH\u0007\u001a#\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u0001*\u00020 H\u0007\u001a\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020!0\u0001*\u00020\"H\u0007\u001a\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020#H\u0007\u001a\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020$H\u0007\u001a\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u0001*\u00020%H\u0007\u001a\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020!0\u0001*\u00020&H\u0007\u001a\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020'H\u0007\u001a6\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0(H\u0007ø\u0001\u0000¢\u0006\u0002\u0010)\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, mo37405d2 = {"callbackFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "channelFlow", "emptyFlow", "flow", "Lkotlinx/coroutines/flow/FlowCollector;", "flowOf", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "elements", "", "([Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "flowViaChannel", "bufferSize", "", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/ParameterName;", "name", "channel", "unsafeFlow", "asFlow", "Lkotlin/Function0;", "", "", "", "", "", "Lkotlin/ranges/IntRange;", "Lkotlin/ranges/LongRange;", "Lkotlin/sequences/Sequence;", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Builders.kt */
final /* synthetic */ class FlowKt__BuildersKt {
    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> flow(@NotNull @BuilderInference Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "block");
        return new FlowKt__BuildersKt$flow$1<>(function2);
    }

    @NotNull
    @PublishedApi
    public static final <T> Flow<T> unsafeFlow(@NotNull @BuilderInference Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "block");
        return new FlowKt__BuildersKt$unsafeFlow$1<>(function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> emptyFlow() {
        return EmptyFlow.INSTANCE;
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Long> asFlow(@NotNull LongRange longRange) {
        Intrinsics.checkParameterIsNotNull(longRange, "$this$asFlow");
        return FlowKt.flow(new FlowKt__BuildersKt$asFlow$10(longRange, null));
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use channelFlow instead", replaceWith = @ReplaceWith(expression = "channelFlow(block)", imports = {}))
    @FlowPreview
    public static /* synthetic */ Flow flowViaChannel$default(int i, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -2;
        }
        return FlowKt.flowViaChannel(i, function2);
    }

    @FlowPreview
    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use channelFlow instead", replaceWith = @ReplaceWith(expression = "channelFlow(block)", imports = {}))
    public static final <T> Flow<T> flowViaChannel(int i, @NotNull @BuilderInference Function2<? super CoroutineScope, ? super SendChannel<? super T>, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "block");
        return FlowKt.buffer(FlowKt.channelFlow(new FlowKt__BuildersKt$flowViaChannel$1(function2, null)), i);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> channelFlow(@NotNull @BuilderInference Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "block");
        ChannelFlowBuilder channelFlowBuilder = new ChannelFlowBuilder(function2, null, 0, 6, null);
        return channelFlowBuilder;
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> callbackFlow(@NotNull @BuilderInference Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "block");
        return FlowKt.channelFlow(function2);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> asFlow(@NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$1<>(function0);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> asFlow(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2<>(function1);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3<>(iterable);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull Iterator<? extends T> it) {
        Intrinsics.checkParameterIsNotNull(it, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4<>(it);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5<>(sequence);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> flowOf(@NotNull T... tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "elements");
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1<>(tArr);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> flowOf(T t) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2<>(t);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6<>(tArr);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Integer> asFlow(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7<>(iArr);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Long> asFlow(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8<>(jArr);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Integer> asFlow(@NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(intRange, "$this$asFlow");
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9<>(intRange);
    }
}
