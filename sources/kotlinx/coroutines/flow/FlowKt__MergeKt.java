package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u001ae\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\f0\n27\u0010\r\u001a3\b\u0001\u0012\u0013\u0012\u0011H\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001ao\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\f0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u000127\u0010\r\u001a3\b\u0001\u0012\u0013\u0012\u0011H\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a$\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\f0\n\"\u0004\b\u0000\u0010\f*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\n0\nH\u0007\u001a.\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\f0\n\"\u0004\b\u0000\u0010\f*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\n0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0001H\u0007\u001ae\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\f0\n27\u0010\r\u001a3\b\u0001\u0012\u0013\u0012\u0011H\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\"\u001c\u0010\u0000\u001a\u00020\u00018\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u00078\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, mo37405d2 = {"DEFAULT_CONCURRENCY", "", "DEFAULT_CONCURRENCY$annotations", "()V", "getDEFAULT_CONCURRENCY", "()I", "DEFAULT_CONCURRENCY_PROPERTY_NAME", "", "DEFAULT_CONCURRENCY_PROPERTY_NAME$annotations", "flatMapConcat", "Lkotlinx/coroutines/flow/Flow;", "R", "T", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flatMapMerge", "concurrency", "(Lkotlinx/coroutines/flow/Flow;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flattenConcat", "flattenMerge", "switchMap", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Merge.kt */
final /* synthetic */ class FlowKt__MergeKt {
    private static final int DEFAULT_CONCURRENCY = SystemPropsKt.systemProp(FlowKt.DEFAULT_CONCURRENCY_PROPERTY_NAME, 16, 1, Integer.MAX_VALUE);

    @FlowPreview
    public static /* synthetic */ void DEFAULT_CONCURRENCY$annotations() {
    }

    public static final int getDEFAULT_CONCURRENCY() {
        return DEFAULT_CONCURRENCY;
    }

    @NotNull
    @FlowPreview
    public static /* synthetic */ Flow flatMapMerge$default(Flow flow, int i, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = DEFAULT_CONCURRENCY;
        }
        return FlowKt.flatMapMerge(flow, i, function2);
    }

    @NotNull
    @FlowPreview
    public static /* synthetic */ Flow flattenMerge$default(Flow flow, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = DEFAULT_CONCURRENCY;
        }
        return FlowKt.flattenMerge(flow, i);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> flattenMerge(@NotNull Flow<? extends Flow<? extends T>> flow, int i) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$flattenMerge");
        if (!(i > 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected positive concurrency level, but had ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (i == 1) {
            return FlowKt.flattenConcat(flow);
        } else {
            ChannelFlowMerge channelFlowMerge = new ChannelFlowMerge(flow, i, null, 0, 12, null);
            return channelFlowMerge;
        }
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T, R> Flow<R> switchMap(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$switchMap");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return FlowCoroutineKt.scopedFlow(new FlowKt__MergeKt$switchMap$1(flow, function2, null));
    }

    @NotNull
    @FlowPreview
    public static final <T, R> Flow<R> flatMapConcat(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$flatMapConcat");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return FlowKt.flattenConcat(new FlowKt__MergeKt$flatMapConcat$$inlined$map$1(flow, function2));
    }

    @NotNull
    @FlowPreview
    public static final <T, R> Flow<R> flatMapMerge(@NotNull Flow<? extends T> flow, int i, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$flatMapMerge");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return FlowKt.flattenMerge(new FlowKt__MergeKt$flatMapMerge$$inlined$map$1(flow, function2), i);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> flattenConcat(@NotNull Flow<? extends Flow<? extends T>> flow) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$flattenConcat");
        return new FlowKt__MergeKt$flattenConcat$$inlined$unsafeFlow$1<>(flow);
    }
}
