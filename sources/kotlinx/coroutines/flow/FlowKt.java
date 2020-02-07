package kotlinx.coroutines.flow;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt", "kotlinx/coroutines/flow/FlowKt__ChannelsKt", "kotlinx/coroutines/flow/FlowKt__CollectKt", "kotlinx/coroutines/flow/FlowKt__CollectionKt", "kotlinx/coroutines/flow/FlowKt__ContextKt", "kotlinx/coroutines/flow/FlowKt__CountKt", "kotlinx/coroutines/flow/FlowKt__DelayKt", "kotlinx/coroutines/flow/FlowKt__DistinctKt", "kotlinx/coroutines/flow/FlowKt__ErrorsKt", "kotlinx/coroutines/flow/FlowKt__LimitKt", "kotlinx/coroutines/flow/FlowKt__MergeKt", "kotlinx/coroutines/flow/FlowKt__ReduceKt", "kotlinx/coroutines/flow/FlowKt__TransformKt", "kotlinx/coroutines/flow/FlowKt__ZipKt"}, mo37406k = 4, mo37407mv = {1, 1, 15})
public final class FlowKt {
    @NotNull
    public static final String DEFAULT_CONCURRENCY_PROPERTY_NAME = "kotlinx.coroutines.flow.defaultConcurrency";

    @FlowPreview
    public static /* synthetic */ void DEFAULT_CONCURRENCY_PROPERTY_NAME$annotations() {
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull Iterable<? extends T> iterable) {
        return FlowKt__BuildersKt.asFlow(iterable);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull Iterator<? extends T> it) {
        return FlowKt__BuildersKt.asFlow(it);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> asFlow(@NotNull Function0<? extends T> function0) {
        return FlowKt__BuildersKt.asFlow(function0);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> asFlow(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        return FlowKt__BuildersKt.asFlow(function1);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Integer> asFlow(@NotNull IntRange intRange) {
        return FlowKt__BuildersKt.asFlow(intRange);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Long> asFlow(@NotNull LongRange longRange) {
        return FlowKt__BuildersKt.asFlow(longRange);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull Sequence<? extends T> sequence) {
        return FlowKt__BuildersKt.asFlow(sequence);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> asFlow(@NotNull BroadcastChannel<T> broadcastChannel) {
        return FlowKt__ChannelsKt.asFlow(broadcastChannel);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Integer> asFlow(@NotNull int[] iArr) {
        return FlowKt__BuildersKt.asFlow(iArr);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final Flow<Long> asFlow(@NotNull long[] jArr) {
        return FlowKt__BuildersKt.asFlow(jArr);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> asFlow(@NotNull T[] tArr) {
        return FlowKt__BuildersKt.asFlow(tArr);
    }

    @NotNull
    @FlowPreview
    public static final <T> BroadcastChannel<T> broadcastIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull CoroutineStart coroutineStart) {
        return FlowKt__ChannelsKt.broadcastIn(flow, coroutineScope, coroutineStart);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> buffer(@NotNull Flow<? extends T> flow, int i) {
        return FlowKt__ContextKt.buffer(flow, i);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> callbackFlow(@NotNull @BuilderInference Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return FlowKt__BuildersKt.callbackFlow(function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> channelFlow(@NotNull @BuilderInference Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return FlowKt__BuildersKt.channelFlow(function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return FlowKt__ZipKt.combineLatest(flow, flow2, function3);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, T3, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Function4<? super T1, ? super T2, ? super T3, ? super Continuation<? super R>, ? extends Object> function4) {
        return FlowKt__ZipKt.combineLatest(flow, flow2, flow3, function4);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, T3, T4, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super Continuation<? super R>, ? extends Object> function5) {
        return FlowKt__ZipKt.combineLatest(flow, flow2, flow3, flow4, function5);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Flow<? extends T5> flow5, @NotNull Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super Continuation<? super R>, ? extends Object> function6) {
        return FlowKt__ZipKt.combineLatest(flow, flow2, flow3, flow4, flow5, function6);
    }

    @NotNull
    @PublishedApi
    public static final <T, R> Flow<R> combineLatest(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T>[] flowArr, @NotNull Function0<T[]> function0, @NotNull Function2<? super T[], ? super Continuation<? super R>, ? extends Object> function2) {
        return FlowKt__ZipKt.combineLatest(flow, flowArr, function0, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> conflate(@NotNull Flow<? extends T> flow) {
        return FlowKt__ContextKt.conflate(flow);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object count(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super Integer> continuation) {
        return FlowKt__CountKt.count(flow, continuation);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object count(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super Integer> continuation) {
        return FlowKt__CountKt.count(flow, function2, continuation);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.debounce(flow, j);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> delayEach(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.delayEach(flow, j);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> delayFlow(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.delayFlow(flow, j);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> distinctUntilChanged(@NotNull Flow<? extends T> flow) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow);
    }

    @NotNull
    @FlowPreview
    public static final <T, K> Flow<T> distinctUntilChangedBy(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, ? extends K> function1) {
        return FlowKt__DistinctKt.distinctUntilChangedBy(flow, function1);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> drop(@NotNull Flow<? extends T> flow, int i) {
        return FlowKt__LimitKt.drop(flow, i);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> dropWhile(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return FlowKt__LimitKt.dropWhile(flow, function2);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object emitAll(@NotNull FlowCollector<? super T> flowCollector, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation) {
        return FlowKt__CollectKt.emitAll(flowCollector, flow, continuation);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    private static final Object emitAll$$forInline(@NotNull FlowCollector flowCollector, @NotNull Flow flow, @NotNull Continuation continuation) {
        return FlowKt__CollectKt.emitAll(flowCollector, flow, continuation);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> emptyFlow() {
        return FlowKt__BuildersKt.emptyFlow();
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> filter(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return FlowKt__TransformKt.filter(flow, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> filterNot(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return FlowKt__TransformKt.filterNot(flow, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> filterNotNull(@NotNull Flow<? extends T> flow) {
        return FlowKt__TransformKt.filterNotNull(flow);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.first(flow, continuation);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.first(flow, function2, continuation);
    }

    @NotNull
    public static final ReceiveChannel<Unit> fixedPeriodTicker(@NotNull CoroutineScope coroutineScope, long j, long j2) {
        return FlowKt__DelayKt.fixedPeriodTicker(coroutineScope, j, j2);
    }

    @NotNull
    @FlowPreview
    public static final <T, R> Flow<R> flatMapConcat(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        return FlowKt__MergeKt.flatMapConcat(flow, function2);
    }

    @NotNull
    @FlowPreview
    public static final <T, R> Flow<R> flatMapMerge(@NotNull Flow<? extends T> flow, int i, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        return FlowKt__MergeKt.flatMapMerge(flow, i, function2);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> flattenConcat(@NotNull Flow<? extends Flow<? extends T>> flow) {
        return FlowKt__MergeKt.flattenConcat(flow);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> flattenMerge(@NotNull Flow<? extends Flow<? extends T>> flow, int i) {
        return FlowKt__MergeKt.flattenMerge(flow, i);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> flow(@NotNull @BuilderInference Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return FlowKt__BuildersKt.flow(function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> flowOf(T t) {
        return FlowKt__BuildersKt.flowOf(t);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> flowOf(@NotNull T... tArr) {
        return FlowKt__BuildersKt.flowOf(tArr);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> flowOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        return FlowKt__ContextKt.flowOn(flow, coroutineContext);
    }

    @FlowPreview
    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use channelFlow instead", replaceWith = @ReplaceWith(expression = "channelFlow(block)", imports = {}))
    public static final <T> Flow<T> flowViaChannel(int i, @NotNull @BuilderInference Function2<? super CoroutineScope, ? super SendChannel<? super T>, Unit> function2) {
        return FlowKt__BuildersKt.flowViaChannel(i, function2);
    }

    @FlowPreview
    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "flowWith is deprecated without replacement, please refer to its KDoc for an explanation")
    public static final <T, R> Flow<R> flowWith(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext, int i, @NotNull Function1<? super Flow<? extends T>, ? extends Flow<? extends R>> function1) {
        return FlowKt__ContextKt.flowWith(flow, coroutineContext, i, function1);
    }

    public static final int getDEFAULT_CONCURRENCY() {
        return FlowKt__MergeKt.getDEFAULT_CONCURRENCY();
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T, R> Flow<R> map(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return FlowKt__TransformKt.map(flow, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T, R> Flow<R> mapNotNull(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return FlowKt__TransformKt.mapNotNull(flow, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> onEach(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return FlowKt__TransformKt.onEach(flow, function2);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> onErrorCollect(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2, @NotNull Function1<? super Throwable, Boolean> function1) {
        return FlowKt__ErrorsKt.onErrorCollect(flow, flow2, function1);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> onErrorReturn(@NotNull Flow<? extends T> flow, T t, @NotNull Function1<? super Throwable, Boolean> function1) {
        return FlowKt__ErrorsKt.onErrorReturn(flow, t, function1);
    }

    @NotNull
    @FlowPreview
    public static final <T> ReceiveChannel<T> produceIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope) {
        return FlowKt__ChannelsKt.produceIn(flow, coroutineScope);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <S, T extends S> Object reduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super S, ? super T, ? super Continuation<? super S>, ? extends Object> function3, @NotNull Continuation<? super S> continuation) {
        return FlowKt__ReduceKt.reduce(flow, function3, continuation);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> retry(@NotNull Flow<? extends T> flow, int i, @NotNull Function1<? super Throwable, Boolean> function1) {
        return FlowKt__ErrorsKt.retry(flow, i, function1);
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> sample(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.sample(flow, j);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T, R> Flow<R> scan(@NotNull Flow<? extends T> flow, R r, @NotNull @BuilderInference Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        return FlowKt__TransformKt.scan(flow, r, function3);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> scanReduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3) {
        return FlowKt__TransformKt.scanReduce(flow, function3);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object single(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.single(flow, continuation);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object singleOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.singleOrNull(flow, continuation);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T, R> Flow<R> switchMap(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        return FlowKt__MergeKt.switchMap(flow, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> take(@NotNull Flow<? extends T> flow, int i) {
        return FlowKt__LimitKt.take(flow, i);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> takeWhile(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return FlowKt__LimitKt.takeWhile(flow, function2);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T, C extends Collection<? super T>> Object toCollection(@NotNull Flow<? extends T> flow, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return FlowKt__CollectionKt.toCollection(flow, c, continuation);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object toList(@NotNull Flow<? extends T> flow, @NotNull List<T> list, @NotNull Continuation<? super List<? extends T>> continuation) {
        return FlowKt__CollectionKt.toList(flow, list, continuation);
    }

    @Nullable
    @ExperimentalCoroutinesApi
    public static final <T> Object toSet(@NotNull Flow<? extends T> flow, @NotNull Set<T> set, @NotNull Continuation<? super Set<? extends T>> continuation) {
        return FlowKt__CollectionKt.toSet(flow, set, continuation);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T, R> Flow<R> transform(@NotNull Flow<? extends T> flow, @NotNull @BuilderInference Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return FlowKt__TransformKt.transform(flow, function3);
    }

    @NotNull
    @PublishedApi
    public static final <T> Flow<T> unsafeFlow(@NotNull @BuilderInference Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return FlowKt__BuildersKt.unsafeFlow(function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, R> Flow<R> zip(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return FlowKt__ZipKt.zip(flow, flow2, function3);
    }
}
