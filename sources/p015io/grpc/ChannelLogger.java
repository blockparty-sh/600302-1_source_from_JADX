package p015io.grpc;

import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/5029")
@ThreadSafe
/* renamed from: io.grpc.ChannelLogger */
public abstract class ChannelLogger {

    /* renamed from: io.grpc.ChannelLogger$ChannelLogLevel */
    public enum ChannelLogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public abstract void log(ChannelLogLevel channelLogLevel, String str);

    public abstract void log(ChannelLogLevel channelLogLevel, String str, Object... objArr);
}
