package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\u0004*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0003\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0005*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u0007\u001a\u0002H\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00040\u000bHHø\u0001\u0000"}, mo37405d2 = {"associateByTo", "", "E", "K", "M", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "destination", "keySelector", "Lkotlin/Function1;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo38000f = "Channels.common.kt", mo38001i = {0, 0, 0, 0, 0, 0, 0}, mo38002l = {2810}, mo38003m = "associateByTo", mo38004n = {"$this$associateByTo", "destination", "keySelector", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* compiled from: Channels.common.kt */
public final class ChannelsKt__Channels_commonKt$associateByTo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;

    public ChannelsKt__Channels_commonKt$associateByTo$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.associateByTo(null, null, null, this);
    }
}
