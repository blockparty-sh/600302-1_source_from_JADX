package p015io.grpc.okhttp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;
import p015io.grpc.okhttp.internal.framed.ErrorCode;
import p015io.grpc.okhttp.internal.framed.Header;
import p015io.grpc.okhttp.internal.framed.Settings;

/* renamed from: io.grpc.okhttp.OkHttpFrameLogger */
class OkHttpFrameLogger {
    private static final int BUFFER_LENGTH_THRESHOLD = 64;
    private final Level level;
    private final Logger logger;

    /* renamed from: io.grpc.okhttp.OkHttpFrameLogger$Direction */
    enum Direction {
        INBOUND,
        OUTBOUND
    }

    /* renamed from: io.grpc.okhttp.OkHttpFrameLogger$SettingParams */
    private enum SettingParams {
        HEADER_TABLE_SIZE(1),
        ENABLE_PUSH(2),
        MAX_CONCURRENT_STREAMS(4),
        MAX_FRAME_SIZE(5),
        MAX_HEADER_LIST_SIZE(6),
        INITIAL_WINDOW_SIZE(7);
        
        private final int bit;

        private SettingParams(int i) {
            this.bit = i;
        }

        public int getBit() {
            return this.bit;
        }
    }

    OkHttpFrameLogger(Level level2, Class<?> cls) {
        this(level2, Logger.getLogger(cls.getName()));
    }

    @VisibleForTesting
    OkHttpFrameLogger(Level level2, Logger logger2) {
        this.level = (Level) Preconditions.checkNotNull(level2, Param.LEVEL);
        this.logger = (Logger) Preconditions.checkNotNull(logger2, "logger");
    }

    private static String toString(Settings settings) {
        SettingParams[] values;
        EnumMap enumMap = new EnumMap(SettingParams.class);
        for (SettingParams settingParams : SettingParams.values()) {
            if (settings.isSet(settingParams.getBit())) {
                enumMap.put(settingParams, Integer.valueOf(settings.get(settingParams.getBit())));
            }
        }
        return enumMap.toString();
    }

    private static String toString(Buffer buffer) {
        if (buffer.size() <= 64) {
            return buffer.snapshot().hex();
        }
        int min = (int) Math.min(buffer.size(), 64);
        StringBuilder sb = new StringBuilder();
        sb.append(buffer.snapshot(min).hex());
        sb.append("...");
        return sb.toString();
    }

    private boolean isEnabled() {
        return this.logger.isLoggable(this.level);
    }

    /* access modifiers changed from: 0000 */
    public void logData(Direction direction, int i, Buffer buffer, int i2, boolean z) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" DATA: streamId=");
            sb.append(i);
            sb.append(" endStream=");
            sb.append(z);
            sb.append(" length=");
            sb.append(i2);
            sb.append(" bytes=");
            sb.append(toString(buffer));
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logHeaders(Direction direction, int i, List<Header> list, boolean z) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" HEADERS: streamId=");
            sb.append(i);
            sb.append(" headers=");
            sb.append(list);
            sb.append(" endStream=");
            sb.append(z);
            logger2.log(level2, sb.toString());
        }
    }

    public void logPriority(Direction direction, int i, int i2, int i3, boolean z) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" PRIORITY: streamId=");
            sb.append(i);
            sb.append(" streamDependency=");
            sb.append(i2);
            sb.append(" weight=");
            sb.append(i3);
            sb.append(" exclusive=");
            sb.append(z);
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logRstStream(Direction direction, int i, ErrorCode errorCode) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" RST_STREAM: streamId=");
            sb.append(i);
            sb.append(" errorCode=");
            sb.append(errorCode);
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logSettingsAck(Direction direction) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" SETTINGS: ack=true");
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logSettings(Direction direction, Settings settings) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" SETTINGS: ack=false settings=");
            sb.append(toString(settings));
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logPing(Direction direction, long j) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" PING: ack=false bytes=");
            sb.append(j);
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logPingAck(Direction direction, long j) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" PING: ack=true bytes=");
            sb.append(j);
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logPushPromise(Direction direction, int i, int i2, List<Header> list) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" PUSH_PROMISE: streamId=");
            sb.append(i);
            sb.append(" promisedStreamId=");
            sb.append(i2);
            sb.append(" headers=");
            sb.append(list);
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logGoAway(Direction direction, int i, ErrorCode errorCode, ByteString byteString) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" GO_AWAY: lastStreamId=");
            sb.append(i);
            sb.append(" errorCode=");
            sb.append(errorCode);
            sb.append(" length=");
            sb.append(byteString.size());
            sb.append(" bytes=");
            sb.append(toString(new Buffer().write(byteString)));
            logger2.log(level2, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void logWindowsUpdate(Direction direction, int i, long j) {
        if (isEnabled()) {
            Logger logger2 = this.logger;
            Level level2 = this.level;
            StringBuilder sb = new StringBuilder();
            sb.append(direction);
            sb.append(" WINDOW_UPDATE: streamId=");
            sb.append(i);
            sb.append(" windowSizeIncrement=");
            sb.append(j);
            logger2.log(level2, sb.toString());
        }
    }
}
