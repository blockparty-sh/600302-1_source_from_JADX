package p015io.grpc.okhttp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;
import org.bitcoinj.core.TransactionInput;
import p015io.grpc.okhttp.internal.framed.ErrorCode;
import p015io.grpc.okhttp.internal.framed.FrameWriter;
import p015io.grpc.okhttp.internal.framed.Header;
import p015io.grpc.okhttp.internal.framed.Settings;

/* renamed from: io.grpc.okhttp.ExceptionHandlingFrameWriter */
final class ExceptionHandlingFrameWriter implements FrameWriter {
    private static final Set<String> QUIET_ERRORS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"Socket closed"})));
    private static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private final OkHttpFrameLogger frameLogger;
    private final FrameWriter frameWriter;
    private final TransportExceptionHandler transportExceptionHandler;

    /* renamed from: io.grpc.okhttp.ExceptionHandlingFrameWriter$TransportExceptionHandler */
    interface TransportExceptionHandler {
        void onException(Throwable th);
    }

    ExceptionHandlingFrameWriter(TransportExceptionHandler transportExceptionHandler2, FrameWriter frameWriter2) {
        this(transportExceptionHandler2, frameWriter2, new OkHttpFrameLogger(Level.FINE, OkHttpClientTransport.class));
    }

    @VisibleForTesting
    ExceptionHandlingFrameWriter(TransportExceptionHandler transportExceptionHandler2, FrameWriter frameWriter2, OkHttpFrameLogger okHttpFrameLogger) {
        this.transportExceptionHandler = (TransportExceptionHandler) Preconditions.checkNotNull(transportExceptionHandler2, "transportExceptionHandler");
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter2, "frameWriter");
        this.frameLogger = (OkHttpFrameLogger) Preconditions.checkNotNull(okHttpFrameLogger, "frameLogger");
    }

    public void connectionPreface() {
        try {
            this.frameWriter.connectionPreface();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void ackSettings(Settings settings) {
        this.frameLogger.logSettingsAck(Direction.OUTBOUND);
        try {
            this.frameWriter.ackSettings(settings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void pushPromise(int i, int i2, List<Header> list) {
        this.frameLogger.logPushPromise(Direction.OUTBOUND, i, i2, list);
        try {
            this.frameWriter.pushPromise(i, i2, list);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void synStream(boolean z, boolean z2, int i, int i2, List<Header> list) {
        try {
            this.frameWriter.synStream(z, z2, i, i2, list);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void synReply(boolean z, int i, List<Header> list) {
        try {
            this.frameWriter.synReply(z, i, list);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void headers(int i, List<Header> list) {
        this.frameLogger.logHeaders(Direction.OUTBOUND, i, list, false);
        try {
            this.frameWriter.headers(i, list);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void rstStream(int i, ErrorCode errorCode) {
        this.frameLogger.logRstStream(Direction.OUTBOUND, i, errorCode);
        try {
            this.frameWriter.rstStream(i, errorCode);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public int maxDataLength() {
        return this.frameWriter.maxDataLength();
    }

    public void data(boolean z, int i, Buffer buffer, int i2) {
        this.frameLogger.logData(Direction.OUTBOUND, i, buffer.buffer(), i2, z);
        try {
            this.frameWriter.data(z, i, buffer, i2);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void settings(Settings settings) {
        this.frameLogger.logSettings(Direction.OUTBOUND, settings);
        try {
            this.frameWriter.settings(settings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void ping(boolean z, int i, int i2) {
        if (z) {
            this.frameLogger.logPingAck(Direction.OUTBOUND, (TransactionInput.NO_SEQUENCE & ((long) i2)) | (((long) i) << 32));
        } else {
            this.frameLogger.logPing(Direction.OUTBOUND, (TransactionInput.NO_SEQUENCE & ((long) i2)) | (((long) i) << 32));
        }
        try {
            this.frameWriter.ping(z, i, i2);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void goAway(int i, ErrorCode errorCode, byte[] bArr) {
        this.frameLogger.logGoAway(Direction.OUTBOUND, i, errorCode, ByteString.m329of(bArr));
        try {
            this.frameWriter.goAway(i, errorCode, bArr);
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void windowUpdate(int i, long j) {
        this.frameLogger.logWindowsUpdate(Direction.OUTBOUND, i, j);
        try {
            this.frameWriter.windowUpdate(i, j);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void close() {
        try {
            this.frameWriter.close();
        } catch (IOException e) {
            log.log(getLogLevel(e), "Failed closing connection", e);
        }
    }

    @VisibleForTesting
    static Level getLogLevel(Throwable th) {
        if (!(th instanceof IOException) || th.getMessage() == null || !QUIET_ERRORS.contains(th.getMessage())) {
            return Level.INFO;
        }
        return Level.FINE;
    }
}
