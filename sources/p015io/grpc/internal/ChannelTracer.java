package p015io.grpc.internal;

import com.google.common.base.Preconditions;
import com.leanplum.internal.Constants.Methods;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import p015io.grpc.ChannelLogger;
import p015io.grpc.InternalChannelz.ChannelStats;
import p015io.grpc.InternalChannelz.ChannelTrace;
import p015io.grpc.InternalChannelz.ChannelTrace.Event;
import p015io.grpc.InternalChannelz.ChannelTrace.Event.Builder;
import p015io.grpc.InternalChannelz.ChannelTrace.Event.Severity;
import p015io.grpc.InternalLogId;

/* renamed from: io.grpc.internal.ChannelTracer */
final class ChannelTracer {
    static final Logger logger = Logger.getLogger(ChannelLogger.class.getName());
    private final long channelCreationTimeNanos;
    @GuardedBy("lock")
    @Nullable
    private final Collection<Event> events;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public int eventsLogged;
    private final Object lock = new Object();
    private final InternalLogId logId;

    /* renamed from: io.grpc.internal.ChannelTracer$2 */
    static /* synthetic */ class C27412 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity = new int[Severity.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                io.grpc.InternalChannelz$ChannelTrace$Event$Severity[] r0 = p015io.grpc.InternalChannelz.ChannelTrace.Event.Severity.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity = r0
                int[] r0 = $SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity     // Catch:{ NoSuchFieldError -> 0x0014 }
                io.grpc.InternalChannelz$ChannelTrace$Event$Severity r1 = p015io.grpc.InternalChannelz.ChannelTrace.Event.Severity.CT_ERROR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity     // Catch:{ NoSuchFieldError -> 0x001f }
                io.grpc.InternalChannelz$ChannelTrace$Event$Severity r1 = p015io.grpc.InternalChannelz.ChannelTrace.Event.Severity.CT_WARNING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p015io.grpc.internal.ChannelTracer.C27412.<clinit>():void");
        }
    }

    ChannelTracer(InternalLogId internalLogId, final int i, long j, String str) {
        Preconditions.checkNotNull(str, "description");
        this.logId = (InternalLogId) Preconditions.checkNotNull(internalLogId, "logId");
        if (i > 0) {
            this.events = new ArrayDeque<Event>() {
                @GuardedBy("lock")
                public boolean add(Event event) {
                    if (size() == i) {
                        removeFirst();
                    }
                    ChannelTracer.this.eventsLogged = ChannelTracer.this.eventsLogged + 1;
                    return super.add(event);
                }
            };
        } else {
            this.events = null;
        }
        this.channelCreationTimeNanos = j;
        Builder builder = new Builder();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" created");
        reportEvent(builder.setDescription(sb.toString()).setSeverity(Severity.CT_INFO).setTimestampNanos(j).build());
    }

    /* access modifiers changed from: 0000 */
    public void reportEvent(Event event) {
        Level level;
        int i = C27412.$SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity[event.severity.ordinal()];
        if (i == 1) {
            level = Level.FINE;
        } else if (i != 2) {
            level = Level.FINEST;
        } else {
            level = Level.FINER;
        }
        traceOnly(event);
        logOnly(this.logId, level, event.description);
    }

    /* access modifiers changed from: 0000 */
    public boolean isTraceEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.events != null;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void traceOnly(Event event) {
        synchronized (this.lock) {
            if (this.events != null) {
                this.events.add(event);
            }
        }
    }

    static void logOnly(InternalLogId internalLogId, Level level, String str) {
        if (logger.isLoggable(level)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(internalLogId);
            sb.append("] ");
            sb.append(str);
            LogRecord logRecord = new LogRecord(level, sb.toString());
            logRecord.setLoggerName(logger.getName());
            logRecord.setSourceClassName(logger.getName());
            logRecord.setSourceMethodName(Methods.LOG);
            logger.log(logRecord);
        }
    }

    /* access modifiers changed from: 0000 */
    public InternalLogId getLogId() {
        return this.logId;
    }

    /* access modifiers changed from: 0000 */
    public void updateBuilder(ChannelStats.Builder builder) {
        synchronized (this.lock) {
            if (this.events != null) {
                int i = this.eventsLogged;
                ArrayList arrayList = new ArrayList(this.events);
                builder.setChannelTrace(new ChannelTrace.Builder().setNumEventsLogged((long) i).setCreationTimeNanos(this.channelCreationTimeNanos).setEvents(arrayList).build());
            }
        }
    }
}
