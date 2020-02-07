package p015io.opencensus.contrib.grpc.metrics;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import p015io.opencensus.stats.Measure.MeasureDouble;
import p015io.opencensus.stats.Measure.MeasureLong;
import p015io.opencensus.tags.TagKey;

/* renamed from: io.opencensus.contrib.grpc.metrics.RpcMeasureConstants */
public final class RpcMeasureConstants {
    private static final String BYTE = "By";
    private static final String COUNT = "1";
    public static final TagKey GRPC_CLIENT_METHOD = TagKey.create("grpc_client_method");
    public static final MeasureDouble GRPC_CLIENT_RECEIVED_BYTES_PER_METHOD;
    public static final MeasureDouble GRPC_CLIENT_RECEIVED_BYTES_PER_RPC;
    public static final MeasureLong GRPC_CLIENT_RECEIVED_MESSAGES_PER_METHOD;
    public static final MeasureLong GRPC_CLIENT_RECEIVED_MESSAGES_PER_RPC;
    public static final MeasureDouble GRPC_CLIENT_ROUNDTRIP_LATENCY;
    public static final MeasureDouble GRPC_CLIENT_SENT_BYTES_PER_METHOD;
    public static final MeasureDouble GRPC_CLIENT_SENT_BYTES_PER_RPC;
    public static final MeasureLong GRPC_CLIENT_SENT_MESSAGES_PER_METHOD;
    public static final MeasureLong GRPC_CLIENT_SENT_MESSAGES_PER_RPC;
    public static final MeasureDouble GRPC_CLIENT_SERVER_LATENCY;
    public static final MeasureLong GRPC_CLIENT_STARTED_RPCS;
    public static final TagKey GRPC_CLIENT_STATUS = TagKey.create("grpc_client_status");
    public static final TagKey GRPC_SERVER_METHOD = TagKey.create("grpc_server_method");
    public static final MeasureDouble GRPC_SERVER_RECEIVED_BYTES_PER_METHOD;
    public static final MeasureDouble GRPC_SERVER_RECEIVED_BYTES_PER_RPC;
    public static final MeasureLong GRPC_SERVER_RECEIVED_MESSAGES_PER_METHOD;
    public static final MeasureLong GRPC_SERVER_RECEIVED_MESSAGES_PER_RPC;
    public static final MeasureDouble GRPC_SERVER_SENT_BYTES_PER_METHOD;
    public static final MeasureDouble GRPC_SERVER_SENT_BYTES_PER_RPC;
    public static final MeasureLong GRPC_SERVER_SENT_MESSAGES_PER_METHOD;
    public static final MeasureLong GRPC_SERVER_SENT_MESSAGES_PER_RPC;
    public static final MeasureDouble GRPC_SERVER_SERVER_LATENCY;
    public static final MeasureLong GRPC_SERVER_STARTED_RPCS;
    public static final TagKey GRPC_SERVER_STATUS = TagKey.create("grpc_server_status");
    private static final String MILLISECOND = "ms";
    @Deprecated
    public static final MeasureLong RPC_CLIENT_ERROR_COUNT;
    @Deprecated
    public static final MeasureLong RPC_CLIENT_FINISHED_COUNT;
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_REQUEST_BYTES = GRPC_CLIENT_SENT_BYTES_PER_RPC;
    @Deprecated
    public static final MeasureLong RPC_CLIENT_REQUEST_COUNT = GRPC_CLIENT_SENT_MESSAGES_PER_RPC;
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_RESPONSE_BYTES = GRPC_CLIENT_RECEIVED_BYTES_PER_RPC;
    @Deprecated
    public static final MeasureLong RPC_CLIENT_RESPONSE_COUNT = GRPC_CLIENT_RECEIVED_MESSAGES_PER_RPC;
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_ROUNDTRIP_LATENCY = GRPC_CLIENT_ROUNDTRIP_LATENCY;
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_SERVER_ELAPSED_TIME = GRPC_CLIENT_SERVER_LATENCY;
    @Deprecated
    public static final MeasureLong RPC_CLIENT_STARTED_COUNT = GRPC_CLIENT_STARTED_RPCS;
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES;
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES;
    @Deprecated
    public static final TagKey RPC_METHOD = TagKey.create(Param.METHOD);
    @Deprecated
    public static final MeasureLong RPC_SERVER_ERROR_COUNT;
    @Deprecated
    public static final MeasureLong RPC_SERVER_FINISHED_COUNT;
    @Deprecated
    public static final MeasureDouble RPC_SERVER_REQUEST_BYTES = GRPC_SERVER_RECEIVED_BYTES_PER_RPC;
    @Deprecated
    public static final MeasureLong RPC_SERVER_REQUEST_COUNT = GRPC_SERVER_RECEIVED_MESSAGES_PER_RPC;
    @Deprecated
    public static final MeasureDouble RPC_SERVER_RESPONSE_BYTES = GRPC_SERVER_SENT_BYTES_PER_RPC;
    @Deprecated
    public static final MeasureLong RPC_SERVER_RESPONSE_COUNT = GRPC_SERVER_SENT_MESSAGES_PER_RPC;
    @Deprecated
    public static final MeasureDouble RPC_SERVER_SERVER_ELAPSED_TIME;
    @Deprecated
    public static final MeasureDouble RPC_SERVER_SERVER_LATENCY = GRPC_SERVER_SERVER_LATENCY;
    @Deprecated
    public static final MeasureLong RPC_SERVER_STARTED_COUNT = GRPC_SERVER_STARTED_RPCS;
    @Deprecated
    public static final MeasureDouble RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES;
    @Deprecated
    public static final MeasureDouble RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES;
    @Deprecated
    public static final TagKey RPC_STATUS = TagKey.create("canonical_status");

    static {
        String str = BYTE;
        GRPC_CLIENT_SENT_BYTES_PER_RPC = MeasureDouble.create("grpc.io/client/sent_bytes_per_rpc", "Total bytes sent across all request messages per RPC", str);
        GRPC_CLIENT_RECEIVED_BYTES_PER_RPC = MeasureDouble.create("grpc.io/client/received_bytes_per_rpc", "Total bytes received across all response messages per RPC", str);
        String str2 = "Total bytes sent per method, recorded real-time as bytes are sent.";
        GRPC_CLIENT_SENT_BYTES_PER_METHOD = MeasureDouble.create("grpc.io/client/sent_bytes_per_method", str2, str);
        String str3 = "Total bytes received per method, recorded real-time as bytes are received.";
        GRPC_CLIENT_RECEIVED_BYTES_PER_METHOD = MeasureDouble.create("grpc.io/client/received_bytes_per_method", str3, str);
        String str4 = "Total messages sent per method.";
        String str5 = COUNT;
        GRPC_CLIENT_SENT_MESSAGES_PER_METHOD = MeasureLong.create("grpc.io/client/sent_messages_per_method", str4, str5);
        String str6 = "Total messages received per method.";
        GRPC_CLIENT_RECEIVED_MESSAGES_PER_METHOD = MeasureLong.create("grpc.io/client/received_messages_per_method", str6, str5);
        String str7 = MILLISECOND;
        GRPC_CLIENT_ROUNDTRIP_LATENCY = MeasureDouble.create("grpc.io/client/roundtrip_latency", "Time between first byte of request sent to last byte of response received, or terminal error.", str7);
        GRPC_CLIENT_SENT_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/client/sent_messages_per_rpc", "Number of messages sent in the RPC", str5);
        GRPC_CLIENT_RECEIVED_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/client/received_messages_per_rpc", "Number of response messages received per RPC", str5);
        GRPC_CLIENT_SERVER_LATENCY = MeasureDouble.create("grpc.io/client/server_latency", "Server latency in msecs", str7);
        GRPC_CLIENT_STARTED_RPCS = MeasureLong.create("grpc.io/client/started_rpcs", "Number of started client RPCs.", str5);
        String str8 = "RPC Errors";
        RPC_CLIENT_ERROR_COUNT = MeasureLong.create("grpc.io/client/error_count", str8, str5);
        String str9 = "Uncompressed Request bytes";
        RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES = MeasureDouble.create("grpc.io/client/uncompressed_request_bytes", str9, str);
        String str10 = "Uncompressed Response bytes";
        RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES = MeasureDouble.create("grpc.io/client/uncompressed_response_bytes", str10, str);
        RPC_CLIENT_FINISHED_COUNT = MeasureLong.create("grpc.io/client/finished_count", "Number of client RPCs (streams) finished", str5);
        GRPC_SERVER_SENT_BYTES_PER_RPC = MeasureDouble.create("grpc.io/server/sent_bytes_per_rpc", "Total bytes sent across all response messages per RPC", str);
        GRPC_SERVER_RECEIVED_BYTES_PER_RPC = MeasureDouble.create("grpc.io/server/received_bytes_per_rpc", "Total bytes received across all messages per RPC", str);
        GRPC_SERVER_SENT_BYTES_PER_METHOD = MeasureDouble.create("grpc.io/server/sent_bytes_per_method", str2, str);
        GRPC_SERVER_RECEIVED_BYTES_PER_METHOD = MeasureDouble.create("grpc.io/server/received_bytes_per_method", str3, str);
        GRPC_SERVER_SENT_MESSAGES_PER_METHOD = MeasureLong.create("grpc.io/server/sent_messages_per_method", str4, str5);
        GRPC_SERVER_RECEIVED_MESSAGES_PER_METHOD = MeasureLong.create("grpc.io/server/received_messages_per_method", str6, str5);
        GRPC_SERVER_SENT_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/server/sent_messages_per_rpc", "Number of messages sent in each RPC", str5);
        GRPC_SERVER_RECEIVED_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/server/received_messages_per_rpc", "Number of messages received in each RPC", str5);
        GRPC_SERVER_SERVER_LATENCY = MeasureDouble.create("grpc.io/server/server_latency", "Time between first byte of request received to last byte of response sent, or terminal error.", str7);
        GRPC_SERVER_STARTED_RPCS = MeasureLong.create("grpc.io/server/started_rpcs", "Number of started server RPCs.", str5);
        RPC_SERVER_ERROR_COUNT = MeasureLong.create("grpc.io/server/error_count", str8, str5);
        RPC_SERVER_SERVER_ELAPSED_TIME = MeasureDouble.create("grpc.io/server/server_elapsed_time", "Server elapsed time in msecs", str7);
        RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES = MeasureDouble.create("grpc.io/server/uncompressed_request_bytes", str9, str);
        RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES = MeasureDouble.create("grpc.io/server/uncompressed_response_bytes", str10, str);
        RPC_SERVER_FINISHED_COUNT = MeasureLong.create("grpc.io/server/finished_count", "Number of server RPCs (streams) finished", str5);
    }

    private RpcMeasureConstants() {
    }
}
