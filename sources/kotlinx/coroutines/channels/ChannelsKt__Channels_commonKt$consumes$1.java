package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "cause", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$consumes$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ReceiveChannel $this_consumes;

    ChannelsKt__Channels_commonKt$consumes$1(ReceiveChannel receiveChannel) {
        this.$this_consumes = receiveChannel;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@Nullable Throwable th) {
        ChannelsKt.cancelConsumed(this.$this_consumes, th);
    }
}
