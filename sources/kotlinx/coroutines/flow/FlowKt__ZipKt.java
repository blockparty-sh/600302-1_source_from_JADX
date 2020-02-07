package kotlinx.coroutines.flow;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.selects.SelectBuilder;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002¢\u0006\u0002\b\u0006\u001a#\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002¢\u0006\u0002\b\b\u001a»\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f\"\u0004\b\u0002\u0010\r\"\u0004\b\u0003\u0010\u000e\"\u0004\b\u0004\u0010\u000f\"\u0004\b\u0005\u0010\n*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00052<\b\u0004\u0010\u0014\u001a6\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0015H\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a¡\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f\"\u0004\b\u0002\u0010\r\"\u0004\b\u0003\u0010\u000e\"\u0004\b\u0004\u0010\n*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u000526\b\u0004\u0010\u0014\u001a0\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0018H\bø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f\"\u0004\b\u0002\u0010\r\"\u0004\b\u0003\u0010\n*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u000520\b\u0004\u0010\u0014\u001a*\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001aH\bø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001aj\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f\"\u0004\b\u0002\u0010\n*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u00052(\u0010\u0014\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001cH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0004\b\u0000\u0010\u001e\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u001e0\u00052\u001e\u0010\u001f\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u001e0\u00050 \"\b\u0012\u0004\u0012\u0002H\u001e0\u00052\u0014\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u001e0 0\"2(\u0010\u0014\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001e0 \u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020#H\u0001ø\u0001\u0000¢\u0006\u0002\u0010$\u001a{\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0006\b\u0000\u0010\u001e\u0018\u0001\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u001e0\u00052\u001e\u0010\u001f\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u001e0\u00050 \"\b\u0012\u0004\u0012\u0002H\u001e0\u00052*\b\u0004\u0010\u0014\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001e0 \u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020#H\bø\u0001\u0000¢\u0006\u0002\u0010%\u001ax\u0010&\u001a\u00020'*\b\u0012\u0004\u0012\u00020'0(2\u0006\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\b\u0004\u0010,\u001a\b\u0012\u0004\u0012\u00020'0\"23\b\b\u0010&\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020#H\bø\u0001\u0000¢\u0006\u0004\b0\u00101\u001aj\u00102\u001a\b\u0012\u0004\u0012\u0002H\n0\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f\"\u0004\b\u0002\u0010\n*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u00052(\u0010\u0014\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001cH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u0002\u0004\n\u0002\b\u0019¨\u00063"}, mo37405d2 = {"asChannel", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "flow", "Lkotlinx/coroutines/flow/Flow;", "asChannel$FlowKt__ZipKt", "asFairChannel", "asFairChannel$FlowKt__ZipKt", "combineLatest", "R", "T1", "T2", "T3", "T4", "T5", "other", "other2", "other3", "other4", "transform", "Lkotlin/Function6;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function5;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function4;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "T", "others", "", "arrayFactory", "Lkotlin/Function0;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "onReceive", "", "Lkotlinx/coroutines/selects/SelectBuilder;", "isClosed", "", "channel", "onClosed", "Lkotlin/ParameterName;", "name", "value", "onReceive$FlowKt__ZipKt", "(Lkotlinx/coroutines/selects/SelectBuilder;ZLkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)V", "zip", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Zip.kt */
final /* synthetic */ class FlowKt__ZipKt {
    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, T3, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Function4<? super T1, ? super T2, ? super T3, ? super Continuation<? super R>, ? extends Object> function4) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$combineLatest");
        Intrinsics.checkParameterIsNotNull(flow2, "other");
        Intrinsics.checkParameterIsNotNull(flow3, "other2");
        Intrinsics.checkParameterIsNotNull(function4, "transform");
        Flow[] flowArr = {flow2, flow3};
        return FlowKt.combineLatest(flow, (Flow<? extends T>[]) (Flow[]) Arrays.copyOf(flowArr, flowArr.length), new FlowKt__ZipKt$combineLatest$$inlined$combineLatest$1<>(flowArr), new FlowKt__ZipKt$combineLatest$$inlined$combineLatest$2<>(null, function4));
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, T3, T4, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super Continuation<? super R>, ? extends Object> function5) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$combineLatest");
        Intrinsics.checkParameterIsNotNull(flow2, "other");
        Intrinsics.checkParameterIsNotNull(flow3, "other2");
        Intrinsics.checkParameterIsNotNull(flow4, "other3");
        Intrinsics.checkParameterIsNotNull(function5, "transform");
        Flow[] flowArr = {flow2, flow3, flow4};
        return FlowKt.combineLatest(flow, (Flow<? extends T>[]) (Flow[]) Arrays.copyOf(flowArr, flowArr.length), new FlowKt__ZipKt$combineLatest$$inlined$combineLatest$3<>(flowArr), new FlowKt__ZipKt$combineLatest$$inlined$combineLatest$4<>(null, function5));
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Flow<? extends T5> flow5, @NotNull Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super Continuation<? super R>, ? extends Object> function6) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$combineLatest");
        Intrinsics.checkParameterIsNotNull(flow2, "other");
        Intrinsics.checkParameterIsNotNull(flow3, "other2");
        Intrinsics.checkParameterIsNotNull(flow4, "other3");
        Intrinsics.checkParameterIsNotNull(flow5, "other4");
        Intrinsics.checkParameterIsNotNull(function6, "transform");
        Flow[] flowArr = {flow2, flow3, flow4, flow5};
        return FlowKt.combineLatest(flow, (Flow<? extends T>[]) (Flow[]) Arrays.copyOf(flowArr, flowArr.length), new FlowKt__ZipKt$combineLatest$$inlined$combineLatest$5<>(flowArr), new FlowKt__ZipKt$combineLatest$$inlined$combineLatest$6<>(null, function6));
    }

    @ExperimentalCoroutinesApi
    private static final <T, R> Flow<R> combineLatest(@NotNull Flow<? extends T> flow, Flow<? extends T>[] flowArr, Function2<? super T[], ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.needClassReification();
        return FlowKt.combineLatest(flow, (Flow<? extends T>[]) (Flow[]) Arrays.copyOf(flowArr, flowArr.length), new FlowKt__ZipKt$combineLatest$5<>(flowArr), new FlowKt__ZipKt$combineLatest$6<>(function2, null));
    }

    /* access modifiers changed from: private */
    public static final void onReceive$FlowKt__ZipKt(@NotNull SelectBuilder<? super Unit> selectBuilder, boolean z, ReceiveChannel<? extends Object> receiveChannel, Function0<Unit> function0, Function2<Object, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (!z) {
            selectBuilder.invoke(receiveChannel.getOnReceiveOrNull(), (Function2<? super Q, ? super Continuation<? super R>, ? extends Object>) new FlowKt__ZipKt$onReceive$1<Object,Object,Object>(function0, function2, null));
        }
    }

    /* access modifiers changed from: private */
    public static final ReceiveChannel<Object> asFairChannel$FlowKt__ZipKt(@NotNull CoroutineScope coroutineScope, Flow<?> flow) {
        return ProduceKt.produce$default(coroutineScope, null, 0, new FlowKt__ZipKt$asFairChannel$1(flow, null), 3, null);
    }

    /* access modifiers changed from: private */
    public static final ReceiveChannel<Object> asChannel$FlowKt__ZipKt(@NotNull CoroutineScope coroutineScope, Flow<?> flow) {
        return ProduceKt.produce$default(coroutineScope, null, 0, new FlowKt__ZipKt$asChannel$1(flow, null), 3, null);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$combineLatest");
        Intrinsics.checkParameterIsNotNull(flow2, "other");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return new FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1<>(flow, flow2, function3);
    }

    @NotNull
    @PublishedApi
    public static final <T, R> Flow<R> combineLatest(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T>[] flowArr, @NotNull Function0<T[]> function0, @NotNull Function2<? super T[], ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$combineLatest");
        Intrinsics.checkParameterIsNotNull(flowArr, "others");
        Intrinsics.checkParameterIsNotNull(function0, "arrayFactory");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return new FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2<>(flow, flowArr, function0, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T1, T2, R> Flow<R> zip(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$zip");
        Intrinsics.checkParameterIsNotNull(flow2, "other");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return new FlowKt__ZipKt$zip$$inlined$unsafeFlow$1<>(flow, flow2, function3);
    }
}
