package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00042'\u0010\u0005\u001a#\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000bHHø\u0001\u0000"}, mo37405d2 = {"reduce", "", "S", "E", "Lkotlinx/coroutines/channels/ReceiveChannel;", "operation", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "acc", "continuation", "Lkotlin/coroutines/Continuation;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo38000f = "Channels.common.kt", mo38001i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, mo38002l = {1991, 1993}, mo38003m = "reduce", mo38004n = {"$this$reduce", "operation", "$this$consume$iv", "cause$iv", "$this$consume", "iterator", "$this$reduce", "operation", "$this$consume$iv", "cause$iv", "$this$consume", "iterator", "accumulator"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* compiled from: Channels.common.kt */
public final class ChannelsKt__Channels_commonKt$reduce$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    public ChannelsKt__Channels_commonKt$reduce$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.reduce(null, null, this);
    }
}
