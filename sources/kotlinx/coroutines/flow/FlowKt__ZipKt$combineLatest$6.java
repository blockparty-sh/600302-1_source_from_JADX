package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "R", "T", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$6", mo38000f = "Zip.kt", mo38001i = {}, mo38002l = {142}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: Zip.kt */
public final class FlowKt__ZipKt$combineLatest$6 extends SuspendLambda implements Function2<T[], Continuation<? super R>, Object> {
    final /* synthetic */ Function2 $transform;
    int label;
    private Object[] p$0;

    public FlowKt__ZipKt$combineLatest$6(Function2 function2, Continuation continuation) {
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__ZipKt$combineLatest$6 flowKt__ZipKt$combineLatest$6 = new FlowKt__ZipKt$combineLatest$6(this.$transform, continuation);
        flowKt__ZipKt$combineLatest$6.p$0 = (Object[]) obj;
        return flowKt__ZipKt$combineLatest$6;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ZipKt$combineLatest$6) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = this.p$0;
            Function2 function2 = this.$transform;
            this.label = 1;
            obj = function2.invoke(objArr, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }

    @Nullable
    public final Object invokeSuspend$$forInline(@NotNull Object obj) {
        return this.$transform.invoke(this.p$0, this);
    }
}
