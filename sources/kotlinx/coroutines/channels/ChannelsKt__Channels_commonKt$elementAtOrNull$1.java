package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0007H@ø\u0001\u0000"}, mo37405d2 = {"elementAtOrNull", "", "E", "Lkotlinx/coroutines/channels/ReceiveChannel;", "index", "", "continuation", "Lkotlin/coroutines/Continuation;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo38000f = "Channels.common.kt", mo38001i = {0, 0, 0, 0, 0, 0}, mo38002l = {218}, mo38003m = "elementAtOrNull", mo38004n = {"$this$elementAtOrNull", "index", "$this$consume$iv", "cause$iv", "$this$consume", "count"}, mo38005s = {"L$0", "I$0", "L$1", "L$2", "L$3", "I$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$elementAtOrNull$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__Channels_commonKt$elementAtOrNull$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.elementAtOrNull(null, 0, this);
    }
}
