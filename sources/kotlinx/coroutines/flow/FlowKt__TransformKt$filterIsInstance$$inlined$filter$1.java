package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\b"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeFlow$2"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 implements Flow<Object> {
    final /* synthetic */ Flow $this_filter$inlined;

    public FlowKt__TransformKt$filterIsInstance$$inlined$filter$1(Flow flow) {
        this.$this_filter$inlined = flow;
    }

    @Nullable
    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
        Flow flow = this.$this_filter$inlined;
        Intrinsics.needClassReification();
        return flow.collect(new FlowCollector<Object>() {
            @Nullable
            public Object emit(Object obj, @NotNull Continuation continuation) {
                Intrinsics.reifiedOperationMarker(3, "R");
                if (Boxing.boxBoolean(obj instanceof Object).booleanValue()) {
                    return r3.emit(obj, continuation);
                }
                return Unit.INSTANCE;
            }

            @Nullable
            public Object emit$$forInline(Object obj, @NotNull Continuation continuation) {
                InlineMarker.mark(4);
                new ContinuationImpl(this, continuation) {
                    int label;
                    /* synthetic */ Object result;
                    final /* synthetic */ C32332 this$0;

                    {
                        this.this$0 = r1;
                    }

                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return this.this$0.emit(null, this);
                    }
                };
                InlineMarker.mark(5);
                Intrinsics.reifiedOperationMarker(3, "R");
                if (!(obj instanceof Object)) {
                    return Unit.INSTANCE;
                }
                FlowCollector flowCollector = r3;
                InlineMarker.mark(0);
                Object emit = flowCollector.emit(obj, continuation);
                InlineMarker.mark(2);
                InlineMarker.mark(1);
                return emit;
            }
        }, continuation);
    }

    @Nullable
    public Object collect$$forInline(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(this, continuation) {
            Object L$0;
            Object L$1;
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 this$0;

            {
                this.this$0 = r1;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.collect(null, this);
            }
        };
        InlineMarker.mark(5);
        Flow flow = this.$this_filter$inlined;
        Intrinsics.needClassReification();
        FlowCollector r1 = new FlowCollector<Object>() {
            @Nullable
            public Object emit(Object obj, @NotNull Continuation continuation) {
                Intrinsics.reifiedOperationMarker(3, "R");
                if (Boxing.boxBoolean(obj instanceof Object).booleanValue()) {
                    return flowCollector.emit(obj, continuation);
                }
                return Unit.INSTANCE;
            }

            @Nullable
            public Object emit$$forInline(Object obj, @NotNull Continuation continuation) {
                InlineMarker.mark(4);
                new ContinuationImpl(this, continuation) {
                    int label;
                    /* synthetic */ Object result;
                    final /* synthetic */ C32332 this$0;

                    {
                        this.this$0 = r1;
                    }

                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return this.this$0.emit(null, this);
                    }
                };
                InlineMarker.mark(5);
                Intrinsics.reifiedOperationMarker(3, "R");
                if (!(obj instanceof Object)) {
                    return Unit.INSTANCE;
                }
                FlowCollector flowCollector = flowCollector;
                InlineMarker.mark(0);
                Object emit = flowCollector.emit(obj, continuation);
                InlineMarker.mark(2);
                InlineMarker.mark(1);
                return emit;
            }
        };
        InlineMarker.mark(0);
        flow.collect(r1, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
