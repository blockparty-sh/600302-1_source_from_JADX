package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"<anonymous>", "R", "T", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineLatest$6"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$combineLatest$2", mo38000f = "Zip.kt", mo38001i = {0, 0}, mo38002l = {259}, mo38003m = "invokeSuspend", mo38004n = {"continuation", "args"}, mo38005s = {"L$0", "L$1"})
/* compiled from: Zip.kt */
public final class FlowKt__ZipKt$combineLatest$$inlined$combineLatest$2 extends SuspendLambda implements Function2<Object[], Continuation<? super R>, Object> {
    final /* synthetic */ Function4 $transform$inlined;
    Object L$0;
    Object L$1;
    int label;
    private Object[] p$0;

    public FlowKt__ZipKt$combineLatest$$inlined$combineLatest$2(Continuation continuation, Function4 function4) {
        this.$transform$inlined = function4;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__ZipKt$combineLatest$$inlined$combineLatest$2 flowKt__ZipKt$combineLatest$$inlined$combineLatest$2 = new FlowKt__ZipKt$combineLatest$$inlined$combineLatest$2(continuation, this.$transform$inlined);
        flowKt__ZipKt$combineLatest$$inlined$combineLatest$2.p$0 = (Object[]) obj;
        return flowKt__ZipKt$combineLatest$$inlined$combineLatest$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ZipKt$combineLatest$$inlined$combineLatest$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = this.p$0;
            Continuation continuation = this;
            Function4 function4 = this.$transform$inlined;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            Object obj4 = objArr[2];
            this.L$0 = continuation;
            this.L$1 = objArr;
            this.label = 1;
            obj = function4.invoke(obj2, obj3, obj4, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            Object[] objArr2 = (Object[]) this.L$1;
            Continuation continuation2 = (Continuation) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }

    @Nullable
    public final Object invokeSuspend$$forInline(@NotNull Object obj) {
        Object[] objArr = this.p$0;
        Continuation continuation = this;
        return this.$transform$inlined.invoke(objArr[0], objArr[1], objArr[2], this);
    }
}
