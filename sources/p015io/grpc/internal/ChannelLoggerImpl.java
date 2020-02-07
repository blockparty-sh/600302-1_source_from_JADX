package p015io.grpc.internal;

import com.google.common.base.Preconditions;
import com.leanplum.internal.Constants.Params;
import java.text.MessageFormat;
import java.util.logging.Level;
import p015io.grpc.ChannelLogger;
import p015io.grpc.ChannelLogger.ChannelLogLevel;
import p015io.grpc.InternalChannelz.ChannelTrace.Event.Builder;
import p015io.grpc.InternalChannelz.ChannelTrace.Event.Severity;
import p015io.grpc.InternalLogId;

/* renamed from: io.grpc.internal.ChannelLoggerImpl */
final class ChannelLoggerImpl extends ChannelLogger {
    private final TimeProvider time;
    private final ChannelTracer tracer;

    /* renamed from: io.grpc.internal.ChannelLoggerImpl$1 */
    static /* synthetic */ class C27391 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel = new int[ChannelLogLevel.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                io.grpc.ChannelLogger$ChannelLogLevel[] r0 = p015io.grpc.ChannelLogger.ChannelLogLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel = r0
                int[] r0 = $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel     // Catch:{ NoSuchFieldError -> 0x0014 }
                io.grpc.ChannelLogger$ChannelLogLevel r1 = p015io.grpc.ChannelLogger.ChannelLogLevel.ERROR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel     // Catch:{ NoSuchFieldError -> 0x001f }
                io.grpc.ChannelLogger$ChannelLogLevel r1 = p015io.grpc.ChannelLogger.ChannelLogLevel.WARNING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p015io.grpc.internal.ChannelLoggerImpl.C27391.<clinit>():void");
        }
    }

    ChannelLoggerImpl(ChannelTracer channelTracer, TimeProvider timeProvider) {
        this.tracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer, "tracer");
        this.time = (TimeProvider) Preconditions.checkNotNull(timeProvider, Params.TIME);
    }

    public void log(ChannelLogLevel channelLogLevel, String str) {
        logOnly(this.tracer.getLogId(), channelLogLevel, str);
        if (isTraceable(channelLogLevel)) {
            trace(channelLogLevel, str);
        }
    }

    public void log(ChannelLogLevel channelLogLevel, String str, Object... objArr) {
        String str2;
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (isTraceable(channelLogLevel) || ChannelTracer.logger.isLoggable(javaLogLevel)) {
            str2 = MessageFormat.format(str, objArr);
        } else {
            str2 = null;
        }
        log(channelLogLevel, str2);
    }

    static void logOnly(InternalLogId internalLogId, ChannelLogLevel channelLogLevel, String str) {
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(internalLogId, javaLogLevel, str);
        }
    }

    static void logOnly(InternalLogId internalLogId, ChannelLogLevel channelLogLevel, String str, Object... objArr) {
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(internalLogId, javaLogLevel, MessageFormat.format(str, objArr));
        }
    }

    private boolean isTraceable(ChannelLogLevel channelLogLevel) {
        return channelLogLevel != ChannelLogLevel.DEBUG && this.tracer.isTraceEnabled();
    }

    private void trace(ChannelLogLevel channelLogLevel, String str) {
        if (channelLogLevel != ChannelLogLevel.DEBUG) {
            this.tracer.traceOnly(new Builder().setDescription(str).setSeverity(toTracerSeverity(channelLogLevel)).setTimestampNanos(this.time.currentTimeNanos()).build());
        }
    }

    private static Severity toTracerSeverity(ChannelLogLevel channelLogLevel) {
        int i = C27391.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[channelLogLevel.ordinal()];
        if (i == 1) {
            return Severity.CT_ERROR;
        }
        if (i != 2) {
            return Severity.CT_INFO;
        }
        return Severity.CT_WARNING;
    }

    private static Level toJavaLogLevel(ChannelLogLevel channelLogLevel) {
        int i = C27391.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[channelLogLevel.ordinal()];
        if (i == 1) {
            return Level.FINE;
        }
        if (i != 2) {
            return Level.FINEST;
        }
        return Level.FINER;
    }
}
