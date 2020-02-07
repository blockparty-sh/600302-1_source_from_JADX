package p015io.opencensus.contrib.grpc.metrics;

import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p015io.opencensus.common.Duration;
import p015io.opencensus.stats.Aggregation;
import p015io.opencensus.stats.Aggregation.Count;
import p015io.opencensus.stats.Aggregation.Distribution;
import p015io.opencensus.stats.Aggregation.Mean;
import p015io.opencensus.stats.Aggregation.Sum;
import p015io.opencensus.stats.BucketBoundaries;
import p015io.opencensus.stats.View;
import p015io.opencensus.stats.View.AggregationWindow;
import p015io.opencensus.stats.View.AggregationWindow.Cumulative;
import p015io.opencensus.stats.View.AggregationWindow.Interval;
import p015io.opencensus.stats.View.Name;
import p015io.opencensus.tags.TagKey;

/* renamed from: io.opencensus.contrib.grpc.metrics.RpcViewConstants */
public final class RpcViewConstants {
    @VisibleForTesting
    static final Aggregation AGGREGATION_WITH_BYTES_HISTOGRAM = Distribution.create(BucketBoundaries.create(RPC_BYTES_BUCKET_BOUNDARIES));
    @VisibleForTesting
    static final Aggregation AGGREGATION_WITH_COUNT_HISTOGRAM = Distribution.create(BucketBoundaries.create(RPC_COUNT_BUCKET_BOUNDARIES));
    @VisibleForTesting
    static final Aggregation AGGREGATION_WITH_MILLIS_HISTOGRAM = Distribution.create(BucketBoundaries.create(RPC_MILLIS_BUCKET_BOUNDARIES));
    static final Aggregation AGGREGATION_WITH_MILLIS_HISTOGRAM_DEPRECATED = Distribution.create(BucketBoundaries.create(RPC_MILLIS_BUCKET_BOUNDARIES_DEPRECATED));
    @VisibleForTesting
    static final Aggregation COUNT = Count.create();
    @VisibleForTesting
    static final AggregationWindow CUMULATIVE = Cumulative.create();
    public static final View GRPC_CLIENT_COMPLETED_RPC_VIEW;
    public static final View GRPC_CLIENT_RECEIVED_BYTES_PER_METHOD_VIEW;
    public static final View GRPC_CLIENT_RECEIVED_BYTES_PER_RPC_VIEW;
    public static final View GRPC_CLIENT_RECEIVED_MESSAGES_PER_METHOD_VIEW;
    public static final View GRPC_CLIENT_RECEIVED_MESSAGES_PER_RPC_VIEW;
    public static final View GRPC_CLIENT_ROUNDTRIP_LATENCY_VIEW;
    public static final View GRPC_CLIENT_SENT_BYTES_PER_METHOD_VIEW;
    public static final View GRPC_CLIENT_SENT_BYTES_PER_RPC_VIEW;
    public static final View GRPC_CLIENT_SENT_MESSAGES_PER_METHOD_VIEW;
    public static final View GRPC_CLIENT_SENT_MESSAGES_PER_RPC_VIEW;
    public static final View GRPC_CLIENT_SERVER_LATENCY_VIEW;
    public static final View GRPC_CLIENT_STARTED_RPC_VIEW;
    public static final View GRPC_SERVER_COMPLETED_RPC_VIEW;
    public static final View GRPC_SERVER_RECEIVED_BYTES_PER_METHOD_VIEW;
    public static final View GRPC_SERVER_RECEIVED_BYTES_PER_RPC_VIEW;
    public static final View GRPC_SERVER_RECEIVED_MESSAGES_PER_METHOD_VIEW;
    public static final View GRPC_SERVER_RECEIVED_MESSAGES_PER_RPC_VIEW;
    public static final View GRPC_SERVER_SENT_BYTES_PER_METHOD_VIEW;
    public static final View GRPC_SERVER_SENT_BYTES_PER_RPC_VIEW;
    public static final View GRPC_SERVER_SENT_MESSAGES_PER_METHOD_VIEW;
    public static final View GRPC_SERVER_SENT_MESSAGES_PER_RPC_VIEW;
    public static final View GRPC_SERVER_SERVER_LATENCY_VIEW;
    public static final View GRPC_SERVER_STARTED_RPC_VIEW;
    @VisibleForTesting
    static final Duration HOUR = Duration.create(3600, 0);
    @VisibleForTesting
    static final AggregationWindow INTERVAL_HOUR = Interval.create(HOUR);
    @VisibleForTesting
    static final AggregationWindow INTERVAL_MINUTE = Interval.create(MINUTE);
    @VisibleForTesting
    static final Aggregation MEAN = Mean.create();
    @VisibleForTesting
    static final Duration MINUTE = Duration.create(60, 0);
    @VisibleForTesting
    static final List<Double> RPC_BYTES_BUCKET_BOUNDARIES;
    public static final View RPC_CLIENT_ERROR_COUNT_HOUR_VIEW;
    public static final View RPC_CLIENT_ERROR_COUNT_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_ERROR_COUNT_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_FINISHED_COUNT_CUMULATIVE_VIEW;
    public static final View RPC_CLIENT_FINISHED_COUNT_HOUR_VIEW;
    public static final View RPC_CLIENT_FINISHED_COUNT_MINUTE_VIEW;
    public static final View RPC_CLIENT_REQUEST_BYTES_HOUR_VIEW;
    public static final View RPC_CLIENT_REQUEST_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_REQUEST_BYTES_VIEW;
    public static final View RPC_CLIENT_REQUEST_COUNT_HOUR_VIEW;
    public static final View RPC_CLIENT_REQUEST_COUNT_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_REQUEST_COUNT_VIEW;
    public static final View RPC_CLIENT_RESPONSE_BYTES_HOUR_VIEW;
    public static final View RPC_CLIENT_RESPONSE_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_RESPONSE_BYTES_VIEW;
    public static final View RPC_CLIENT_RESPONSE_COUNT_HOUR_VIEW;
    public static final View RPC_CLIENT_RESPONSE_COUNT_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_RESPONSE_COUNT_VIEW;
    public static final View RPC_CLIENT_ROUNDTRIP_LATENCY_HOUR_VIEW;
    public static final View RPC_CLIENT_ROUNDTRIP_LATENCY_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_ROUNDTRIP_LATENCY_VIEW;
    public static final View RPC_CLIENT_SERVER_ELAPSED_TIME_HOUR_VIEW;
    public static final View RPC_CLIENT_SERVER_ELAPSED_TIME_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_SERVER_ELAPSED_TIME_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_STARTED_COUNT_CUMULATIVE_VIEW;
    public static final View RPC_CLIENT_STARTED_COUNT_HOUR_VIEW;
    public static final View RPC_CLIENT_STARTED_COUNT_MINUTE_VIEW;
    public static final View RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW;
    public static final View RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_VIEW;
    public static final View RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW;
    public static final View RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_VIEW;
    @VisibleForTesting
    static final List<Double> RPC_COUNT_BUCKET_BOUNDARIES;
    @VisibleForTesting
    static final List<Double> RPC_MILLIS_BUCKET_BOUNDARIES;
    static final List<Double> RPC_MILLIS_BUCKET_BOUNDARIES_DEPRECATED;
    public static final View RPC_SERVER_ERROR_COUNT_HOUR_VIEW;
    public static final View RPC_SERVER_ERROR_COUNT_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_ERROR_COUNT_VIEW;
    @Deprecated
    public static final View RPC_SERVER_FINISHED_COUNT_CUMULATIVE_VIEW;
    public static final View RPC_SERVER_FINISHED_COUNT_HOUR_VIEW;
    public static final View RPC_SERVER_FINISHED_COUNT_MINUTE_VIEW;
    public static final View RPC_SERVER_REQUEST_BYTES_HOUR_VIEW;
    public static final View RPC_SERVER_REQUEST_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_REQUEST_BYTES_VIEW;
    public static final View RPC_SERVER_REQUEST_COUNT_HOUR_VIEW;
    public static final View RPC_SERVER_REQUEST_COUNT_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_REQUEST_COUNT_VIEW;
    public static final View RPC_SERVER_RESPONSE_BYTES_HOUR_VIEW;
    public static final View RPC_SERVER_RESPONSE_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_RESPONSE_BYTES_VIEW;
    public static final View RPC_SERVER_RESPONSE_COUNT_HOUR_VIEW;
    public static final View RPC_SERVER_RESPONSE_COUNT_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_RESPONSE_COUNT_VIEW;
    public static final View RPC_SERVER_SERVER_ELAPSED_TIME_HOUR_VIEW;
    public static final View RPC_SERVER_SERVER_ELAPSED_TIME_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_SERVER_ELAPSED_TIME_VIEW;
    public static final View RPC_SERVER_SERVER_LATENCY_HOUR_VIEW;
    public static final View RPC_SERVER_SERVER_LATENCY_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_SERVER_LATENCY_VIEW;
    @Deprecated
    public static final View RPC_SERVER_STARTED_COUNT_CUMULATIVE_VIEW;
    public static final View RPC_SERVER_STARTED_COUNT_HOUR_VIEW;
    public static final View RPC_SERVER_STARTED_COUNT_MINUTE_VIEW;
    public static final View RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW;
    public static final View RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_VIEW;
    public static final View RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW;
    public static final View RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW;
    @Deprecated
    public static final View RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_VIEW;
    @VisibleForTesting
    static final Aggregation SUM = Sum.create();

    static {
        Double valueOf = Double.valueOf(0.0d);
        RPC_BYTES_BUCKET_BOUNDARIES = Collections.unmodifiableList(Arrays.asList(new Double[]{valueOf, Double.valueOf(1024.0d), Double.valueOf(2048.0d), Double.valueOf(4096.0d), Double.valueOf(16384.0d), Double.valueOf(65536.0d), Double.valueOf(262144.0d), Double.valueOf(1048576.0d), Double.valueOf(4194304.0d), Double.valueOf(1.6777216E7d), Double.valueOf(6.7108864E7d), Double.valueOf(2.68435456E8d), Double.valueOf(1.073741824E9d), Double.valueOf(4.294967296E9d)}));
        RPC_MILLIS_BUCKET_BOUNDARIES = Collections.unmodifiableList(Arrays.asList(new Double[]{valueOf, Double.valueOf(0.01d), Double.valueOf(0.05d), Double.valueOf(0.1d), Double.valueOf(0.3d), Double.valueOf(0.6d), Double.valueOf(0.8d), Double.valueOf(1.0d), Double.valueOf(2.0d), Double.valueOf(3.0d), Double.valueOf(4.0d), Double.valueOf(5.0d), Double.valueOf(6.0d), Double.valueOf(8.0d), Double.valueOf(10.0d), Double.valueOf(13.0d), Double.valueOf(16.0d), Double.valueOf(20.0d), Double.valueOf(25.0d), Double.valueOf(30.0d), Double.valueOf(40.0d), Double.valueOf(50.0d), Double.valueOf(65.0d), Double.valueOf(80.0d), Double.valueOf(100.0d), Double.valueOf(130.0d), Double.valueOf(160.0d), Double.valueOf(200.0d), Double.valueOf(250.0d), Double.valueOf(300.0d), Double.valueOf(400.0d), Double.valueOf(500.0d), Double.valueOf(650.0d), Double.valueOf(800.0d), Double.valueOf(1000.0d), Double.valueOf(2000.0d), Double.valueOf(5000.0d), Double.valueOf(10000.0d), Double.valueOf(20000.0d), Double.valueOf(50000.0d), Double.valueOf(100000.0d)}));
        RPC_MILLIS_BUCKET_BOUNDARIES_DEPRECATED = Collections.unmodifiableList(Arrays.asList(new Double[]{valueOf, Double.valueOf(1.0d), Double.valueOf(2.0d), Double.valueOf(3.0d), Double.valueOf(4.0d), Double.valueOf(5.0d), Double.valueOf(6.0d), Double.valueOf(8.0d), Double.valueOf(10.0d), Double.valueOf(13.0d), Double.valueOf(16.0d), Double.valueOf(20.0d), Double.valueOf(25.0d), Double.valueOf(30.0d), Double.valueOf(40.0d), Double.valueOf(50.0d), Double.valueOf(65.0d), Double.valueOf(80.0d), Double.valueOf(100.0d), Double.valueOf(130.0d), Double.valueOf(160.0d), Double.valueOf(200.0d), Double.valueOf(250.0d), Double.valueOf(300.0d), Double.valueOf(400.0d), Double.valueOf(500.0d), Double.valueOf(650.0d), Double.valueOf(800.0d), Double.valueOf(1000.0d), Double.valueOf(2000.0d), Double.valueOf(5000.0d), Double.valueOf(10000.0d), Double.valueOf(20000.0d), Double.valueOf(50000.0d), Double.valueOf(100000.0d)}));
        RPC_COUNT_BUCKET_BOUNDARIES = Collections.unmodifiableList(Arrays.asList(new Double[]{valueOf, Double.valueOf(1.0d), Double.valueOf(2.0d), Double.valueOf(4.0d), Double.valueOf(8.0d), Double.valueOf(16.0d), Double.valueOf(32.0d), Double.valueOf(64.0d), Double.valueOf(128.0d), Double.valueOf(256.0d), Double.valueOf(512.0d), Double.valueOf(1024.0d), Double.valueOf(2048.0d), Double.valueOf(4096.0d), Double.valueOf(8192.0d), Double.valueOf(16384.0d), Double.valueOf(32768.0d), Double.valueOf(65536.0d)}));
        String str = "RPC Errors";
        RPC_CLIENT_ERROR_COUNT_VIEW = View.create(Name.create("grpc.io/client/error_count/cumulative"), str, RpcMeasureConstants.RPC_CLIENT_ERROR_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_STATUS, RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str2 = "Latency in msecs";
        RPC_CLIENT_ROUNDTRIP_LATENCY_VIEW = View.create(Name.create("grpc.io/client/roundtrip_latency/cumulative"), str2, RpcMeasureConstants.RPC_CLIENT_ROUNDTRIP_LATENCY, AGGREGATION_WITH_MILLIS_HISTOGRAM_DEPRECATED, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str3 = "Server elapsed time in msecs";
        RPC_CLIENT_SERVER_ELAPSED_TIME_VIEW = View.create(Name.create("grpc.io/client/server_elapsed_time/cumulative"), str3, RpcMeasureConstants.RPC_CLIENT_SERVER_ELAPSED_TIME, AGGREGATION_WITH_MILLIS_HISTOGRAM_DEPRECATED, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str4 = "Request bytes";
        RPC_CLIENT_REQUEST_BYTES_VIEW = View.create(Name.create("grpc.io/client/request_bytes/cumulative"), str4, RpcMeasureConstants.RPC_CLIENT_REQUEST_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str5 = "Response bytes";
        RPC_CLIENT_RESPONSE_BYTES_VIEW = View.create(Name.create("grpc.io/client/response_bytes/cumulative"), str5, RpcMeasureConstants.RPC_CLIENT_RESPONSE_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str6 = "Uncompressed Request bytes";
        RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_VIEW = View.create(Name.create("grpc.io/client/uncompressed_request_bytes/cumulative"), str6, RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str7 = "Uncompressed Response bytes";
        RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_VIEW = View.create(Name.create("grpc.io/client/uncompressed_response_bytes/cumulative"), str7, RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str8 = "Count of request messages per client RPC";
        RPC_CLIENT_REQUEST_COUNT_VIEW = View.create(Name.create("grpc.io/client/request_count/cumulative"), str8, RpcMeasureConstants.RPC_CLIENT_REQUEST_COUNT, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str9 = "Count of response messages per client RPC";
        RPC_CLIENT_RESPONSE_COUNT_VIEW = View.create(Name.create("grpc.io/client/response_count/cumulative"), str9, RpcMeasureConstants.RPC_CLIENT_RESPONSE_COUNT, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str10 = "Number of started client RPCs";
        RPC_CLIENT_STARTED_COUNT_CUMULATIVE_VIEW = View.create(Name.create("grpc.io/client/started_count/cumulative"), str10, RpcMeasureConstants.RPC_CLIENT_STARTED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str11 = "Number of finished client RPCs";
        RPC_CLIENT_FINISHED_COUNT_CUMULATIVE_VIEW = View.create(Name.create("grpc.io/client/finished_count/cumulative"), str11, RpcMeasureConstants.RPC_CLIENT_FINISHED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str12 = "Latency in msecs";
        GRPC_CLIENT_ROUNDTRIP_LATENCY_VIEW = View.create(Name.create("grpc.io/client/roundtrip_latency"), str12, RpcMeasureConstants.GRPC_CLIENT_ROUNDTRIP_LATENCY, AGGREGATION_WITH_MILLIS_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str13 = "Server latency in msecs";
        GRPC_CLIENT_SERVER_LATENCY_VIEW = View.create(Name.create("grpc.io/client/server_latency"), str13, RpcMeasureConstants.GRPC_CLIENT_SERVER_LATENCY, AGGREGATION_WITH_MILLIS_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str14 = "Sent bytes per RPC";
        GRPC_CLIENT_SENT_BYTES_PER_RPC_VIEW = View.create(Name.create("grpc.io/client/sent_bytes_per_rpc"), str14, RpcMeasureConstants.GRPC_CLIENT_SENT_BYTES_PER_RPC, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str15 = "Received bytes per RPC";
        GRPC_CLIENT_RECEIVED_BYTES_PER_RPC_VIEW = View.create(Name.create("grpc.io/client/received_bytes_per_rpc"), str15, RpcMeasureConstants.GRPC_CLIENT_RECEIVED_BYTES_PER_RPC, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str16 = "Number of messages sent in the RPC";
        GRPC_CLIENT_SENT_MESSAGES_PER_RPC_VIEW = View.create(Name.create("grpc.io/client/sent_messages_per_rpc"), str16, RpcMeasureConstants.GRPC_CLIENT_SENT_MESSAGES_PER_RPC, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str17 = "Number of response messages received per RPC";
        GRPC_CLIENT_RECEIVED_MESSAGES_PER_RPC_VIEW = View.create(Name.create("grpc.io/client/received_messages_per_rpc"), str17, RpcMeasureConstants.GRPC_CLIENT_RECEIVED_MESSAGES_PER_RPC, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str18 = "Sent bytes per method";
        GRPC_CLIENT_SENT_BYTES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/client/sent_bytes_per_method"), str18, RpcMeasureConstants.GRPC_CLIENT_SENT_BYTES_PER_METHOD, SUM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str19 = "Received bytes per method";
        GRPC_CLIENT_RECEIVED_BYTES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/client/received_bytes_per_method"), str19, RpcMeasureConstants.GRPC_CLIENT_RECEIVED_BYTES_PER_METHOD, SUM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str20 = "Number of messages sent";
        GRPC_CLIENT_SENT_MESSAGES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/client/sent_messages_per_method"), str20, RpcMeasureConstants.GRPC_CLIENT_SENT_MESSAGES_PER_METHOD, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str21 = "Number of messages received";
        GRPC_CLIENT_RECEIVED_MESSAGES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/client/received_messages_per_method"), str21, RpcMeasureConstants.GRPC_CLIENT_RECEIVED_MESSAGES_PER_METHOD, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str22 = "Number of completed client RPCs";
        GRPC_CLIENT_COMPLETED_RPC_VIEW = View.create(Name.create("grpc.io/client/completed_rpcs"), str22, RpcMeasureConstants.GRPC_CLIENT_ROUNDTRIP_LATENCY, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD, RpcMeasureConstants.GRPC_CLIENT_STATUS}));
        String str23 = "Number of started client RPCs";
        GRPC_CLIENT_STARTED_RPC_VIEW = View.create(Name.create("grpc.io/client/started_rpcs"), str23, RpcMeasureConstants.GRPC_CLIENT_STARTED_RPCS, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_CLIENT_METHOD}));
        String str24 = "RPC Errors";
        RPC_SERVER_ERROR_COUNT_VIEW = View.create(Name.create("grpc.io/server/error_count/cumulative"), str24, RpcMeasureConstants.RPC_SERVER_ERROR_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_STATUS, RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str25 = "Latency in msecs";
        RPC_SERVER_SERVER_LATENCY_VIEW = View.create(Name.create("grpc.io/server/server_latency/cumulative"), str25, RpcMeasureConstants.RPC_SERVER_SERVER_LATENCY, AGGREGATION_WITH_MILLIS_HISTOGRAM_DEPRECATED, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str26 = "Server elapsed time in msecs";
        RPC_SERVER_SERVER_ELAPSED_TIME_VIEW = View.create(Name.create("grpc.io/server/elapsed_time/cumulative"), str26, RpcMeasureConstants.RPC_SERVER_SERVER_ELAPSED_TIME, AGGREGATION_WITH_MILLIS_HISTOGRAM_DEPRECATED, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str27 = "Request bytes";
        RPC_SERVER_REQUEST_BYTES_VIEW = View.create(Name.create("grpc.io/server/request_bytes/cumulative"), str27, RpcMeasureConstants.RPC_SERVER_REQUEST_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str28 = "Response bytes";
        RPC_SERVER_RESPONSE_BYTES_VIEW = View.create(Name.create("grpc.io/server/response_bytes/cumulative"), str28, RpcMeasureConstants.RPC_SERVER_RESPONSE_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str29 = "Uncompressed Request bytes";
        RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_VIEW = View.create(Name.create("grpc.io/server/uncompressed_request_bytes/cumulative"), str29, RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str30 = "Uncompressed Response bytes";
        RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_VIEW = View.create(Name.create("grpc.io/server/uncompressed_response_bytes/cumulative"), str30, RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str31 = "Count of request messages per server RPC";
        RPC_SERVER_REQUEST_COUNT_VIEW = View.create(Name.create("grpc.io/server/request_count/cumulative"), str31, RpcMeasureConstants.RPC_SERVER_REQUEST_COUNT, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str32 = "Count of response messages per server RPC";
        RPC_SERVER_RESPONSE_COUNT_VIEW = View.create(Name.create("grpc.io/server/response_count/cumulative"), str32, RpcMeasureConstants.RPC_SERVER_RESPONSE_COUNT, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str33 = "Number of started server RPCs";
        RPC_SERVER_STARTED_COUNT_CUMULATIVE_VIEW = View.create(Name.create("grpc.io/server/started_count/cumulative"), str33, RpcMeasureConstants.RPC_SERVER_STARTED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str34 = "Number of finished server RPCs";
        RPC_SERVER_FINISHED_COUNT_CUMULATIVE_VIEW = View.create(Name.create("grpc.io/server/finished_count/cumulative"), str34, RpcMeasureConstants.RPC_SERVER_FINISHED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), CUMULATIVE);
        String str35 = "Server latency in msecs";
        GRPC_SERVER_SERVER_LATENCY_VIEW = View.create(Name.create("grpc.io/server/server_latency"), str35, RpcMeasureConstants.GRPC_SERVER_SERVER_LATENCY, AGGREGATION_WITH_MILLIS_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str36 = "Sent bytes per RPC";
        GRPC_SERVER_SENT_BYTES_PER_RPC_VIEW = View.create(Name.create("grpc.io/server/sent_bytes_per_rpc"), str36, RpcMeasureConstants.GRPC_SERVER_SENT_BYTES_PER_RPC, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str37 = "Received bytes per RPC";
        GRPC_SERVER_RECEIVED_BYTES_PER_RPC_VIEW = View.create(Name.create("grpc.io/server/received_bytes_per_rpc"), str37, RpcMeasureConstants.GRPC_SERVER_RECEIVED_BYTES_PER_RPC, AGGREGATION_WITH_BYTES_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str38 = "Number of messages sent in each RPC";
        GRPC_SERVER_SENT_MESSAGES_PER_RPC_VIEW = View.create(Name.create("grpc.io/server/sent_messages_per_rpc"), str38, RpcMeasureConstants.GRPC_SERVER_SENT_MESSAGES_PER_RPC, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str39 = "Number of response messages received in each RPC";
        GRPC_SERVER_RECEIVED_MESSAGES_PER_RPC_VIEW = View.create(Name.create("grpc.io/server/received_messages_per_rpc"), str39, RpcMeasureConstants.GRPC_SERVER_RECEIVED_MESSAGES_PER_RPC, AGGREGATION_WITH_COUNT_HISTOGRAM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str40 = "Sent bytes per method";
        GRPC_SERVER_SENT_BYTES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/server/sent_bytes_per_method"), str40, RpcMeasureConstants.GRPC_SERVER_SENT_BYTES_PER_METHOD, SUM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str41 = "Received bytes per method";
        GRPC_SERVER_RECEIVED_BYTES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/server/received_bytes_per_method"), str41, RpcMeasureConstants.GRPC_SERVER_RECEIVED_BYTES_PER_METHOD, SUM, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str42 = "Number of messages sent";
        GRPC_SERVER_SENT_MESSAGES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/server/sent_messages_per_method"), str42, RpcMeasureConstants.GRPC_SERVER_SENT_MESSAGES_PER_METHOD, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str43 = "Number of messages received";
        GRPC_SERVER_RECEIVED_MESSAGES_PER_METHOD_VIEW = View.create(Name.create("grpc.io/server/received_messages_per_method"), str43, RpcMeasureConstants.GRPC_SERVER_RECEIVED_MESSAGES_PER_METHOD, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str44 = "Number of completed server RPCs";
        GRPC_SERVER_COMPLETED_RPC_VIEW = View.create(Name.create("grpc.io/server/completed_rpcs"), str44, RpcMeasureConstants.GRPC_SERVER_SERVER_LATENCY, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD, RpcMeasureConstants.GRPC_SERVER_STATUS}));
        String str45 = "Number of started server RPCs";
        GRPC_SERVER_STARTED_RPC_VIEW = View.create(Name.create("grpc.io/server/started_rpcs"), str45, RpcMeasureConstants.GRPC_SERVER_STARTED_RPCS, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.GRPC_SERVER_METHOD}));
        String str46 = "Minute stats for latency in msecs";
        RPC_CLIENT_ROUNDTRIP_LATENCY_MINUTE_VIEW = View.create(Name.create("grpc.io/client/roundtrip_latency/minute"), str46, RpcMeasureConstants.RPC_CLIENT_ROUNDTRIP_LATENCY, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str47 = "Minute stats for request size in bytes";
        RPC_CLIENT_REQUEST_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/client/request_bytes/minute"), str47, RpcMeasureConstants.RPC_CLIENT_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str48 = "Minute stats for response size in bytes";
        RPC_CLIENT_RESPONSE_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/client/response_bytes/minute"), str48, RpcMeasureConstants.RPC_CLIENT_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str49 = "Minute stats for rpc errors";
        RPC_CLIENT_ERROR_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/client/error_count/minute"), str49, RpcMeasureConstants.RPC_CLIENT_ERROR_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str50 = "Minute stats for uncompressed request size in bytes";
        RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/client/uncompressed_request_bytes/minute"), str50, RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str51 = "Minute stats for uncompressed response size in bytes";
        RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/client/uncompressed_response_bytes/minute"), str51, RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str52 = "Minute stats for server elapsed time in msecs";
        RPC_CLIENT_SERVER_ELAPSED_TIME_MINUTE_VIEW = View.create(Name.create("grpc.io/client/server_elapsed_time/minute"), str52, RpcMeasureConstants.RPC_CLIENT_SERVER_ELAPSED_TIME, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str53 = "Minute stats on the number of client RPCs started";
        RPC_CLIENT_STARTED_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/client/started_count/minute"), str53, RpcMeasureConstants.RPC_CLIENT_STARTED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str54 = "Minute stats on the number of client RPCs finished";
        RPC_CLIENT_FINISHED_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/client/finished_count/minute"), str54, RpcMeasureConstants.RPC_CLIENT_FINISHED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str55 = "Minute stats on the count of request messages per client RPC";
        RPC_CLIENT_REQUEST_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/client/request_count/minute"), str55, RpcMeasureConstants.RPC_CLIENT_REQUEST_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str56 = "Minute stats on the count of response messages per client RPC";
        RPC_CLIENT_RESPONSE_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/client/response_count/minute"), str56, RpcMeasureConstants.RPC_CLIENT_RESPONSE_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str57 = "Hour stats for latency in msecs";
        RPC_CLIENT_ROUNDTRIP_LATENCY_HOUR_VIEW = View.create(Name.create("grpc.io/client/roundtrip_latency/hour"), str57, RpcMeasureConstants.RPC_CLIENT_ROUNDTRIP_LATENCY, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str58 = "Hour stats for request size in bytes";
        RPC_CLIENT_REQUEST_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/client/request_bytes/hour"), str58, RpcMeasureConstants.RPC_CLIENT_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str59 = "Hour stats for response size in bytes";
        RPC_CLIENT_RESPONSE_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/client/response_bytes/hour"), str59, RpcMeasureConstants.RPC_CLIENT_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str60 = "Hour stats for rpc errors";
        RPC_CLIENT_ERROR_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/client/error_count/hour"), str60, RpcMeasureConstants.RPC_CLIENT_ERROR_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str61 = "Hour stats for uncompressed request size in bytes";
        RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/client/uncompressed_request_bytes/hour"), str61, RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str62 = "Hour stats for uncompressed response size in bytes";
        RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/client/uncompressed_response_bytes/hour"), str62, RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str63 = "Hour stats for server elapsed time in msecs";
        RPC_CLIENT_SERVER_ELAPSED_TIME_HOUR_VIEW = View.create(Name.create("grpc.io/client/server_elapsed_time/hour"), str63, RpcMeasureConstants.RPC_CLIENT_SERVER_ELAPSED_TIME, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str64 = "Hour stats on the number of client RPCs started";
        RPC_CLIENT_STARTED_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/client/started_count/hour"), str64, RpcMeasureConstants.RPC_CLIENT_STARTED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str65 = "Hour stats on the number of client RPCs finished";
        RPC_CLIENT_FINISHED_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/client/finished_count/hour"), str65, RpcMeasureConstants.RPC_CLIENT_FINISHED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str66 = "Hour stats on the count of request messages per client RPC";
        RPC_CLIENT_REQUEST_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/client/request_count/hour"), str66, RpcMeasureConstants.RPC_CLIENT_REQUEST_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str67 = "Hour stats on the count of response messages per client RPC";
        RPC_CLIENT_RESPONSE_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/client/response_count/hour"), str67, RpcMeasureConstants.RPC_CLIENT_RESPONSE_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str68 = "Minute stats for server latency in msecs";
        RPC_SERVER_SERVER_LATENCY_MINUTE_VIEW = View.create(Name.create("grpc.io/server/server_latency/minute"), str68, RpcMeasureConstants.RPC_SERVER_SERVER_LATENCY, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str69 = "Minute stats for request size in bytes";
        RPC_SERVER_REQUEST_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/server/request_bytes/minute"), str69, RpcMeasureConstants.RPC_SERVER_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str70 = "Minute stats for response size in bytes";
        RPC_SERVER_RESPONSE_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/server/response_bytes/minute"), str70, RpcMeasureConstants.RPC_SERVER_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str71 = "Minute stats for rpc errors";
        RPC_SERVER_ERROR_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/server/error_count/minute"), str71, RpcMeasureConstants.RPC_SERVER_ERROR_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str72 = "Minute stats for uncompressed request size in bytes";
        RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/server/uncompressed_request_bytes/minute"), str72, RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str73 = "Minute stats for uncompressed response size in bytes";
        RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW = View.create(Name.create("grpc.io/server/uncompressed_response_bytes/minute"), str73, RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str74 = "Minute stats for server elapsed time in msecs";
        RPC_SERVER_SERVER_ELAPSED_TIME_MINUTE_VIEW = View.create(Name.create("grpc.io/server/server_elapsed_time/minute"), str74, RpcMeasureConstants.RPC_SERVER_SERVER_ELAPSED_TIME, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str75 = "Minute stats on the number of server RPCs started";
        RPC_SERVER_STARTED_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/server/started_count/minute"), str75, RpcMeasureConstants.RPC_SERVER_STARTED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str76 = "Minute stats on the number of server RPCs finished";
        RPC_SERVER_FINISHED_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/server/finished_count/minute"), str76, RpcMeasureConstants.RPC_SERVER_FINISHED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str77 = "Minute stats on the count of request messages per server RPC";
        RPC_SERVER_REQUEST_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/server/request_count/minute"), str77, RpcMeasureConstants.RPC_SERVER_REQUEST_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str78 = "Minute stats on the count of response messages per server RPC";
        RPC_SERVER_RESPONSE_COUNT_MINUTE_VIEW = View.create(Name.create("grpc.io/server/response_count/minute"), str78, RpcMeasureConstants.RPC_SERVER_RESPONSE_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_MINUTE);
        String str79 = "Hour stats for server latency in msecs";
        RPC_SERVER_SERVER_LATENCY_HOUR_VIEW = View.create(Name.create("grpc.io/server/server_latency/hour"), str79, RpcMeasureConstants.RPC_SERVER_SERVER_LATENCY, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str80 = "Hour stats for request size in bytes";
        RPC_SERVER_REQUEST_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/server/request_bytes/hour"), str80, RpcMeasureConstants.RPC_SERVER_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str81 = "Hour stats for response size in bytes";
        RPC_SERVER_RESPONSE_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/server/response_bytes/hour"), str81, RpcMeasureConstants.RPC_SERVER_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str82 = "Hour stats for rpc errors";
        RPC_SERVER_ERROR_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/server/error_count/hour"), str82, RpcMeasureConstants.RPC_SERVER_ERROR_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str83 = "Hour stats for uncompressed request size in bytes";
        RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/server/uncompressed_request_bytes/hour"), str83, RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str84 = "Hour stats for uncompressed response size in bytes";
        RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW = View.create(Name.create("grpc.io/server/uncompressed_response_bytes/hour"), str84, RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str85 = "Hour stats for server elapsed time in msecs";
        RPC_SERVER_SERVER_ELAPSED_TIME_HOUR_VIEW = View.create(Name.create("grpc.io/server/server_elapsed_time/hour"), str85, RpcMeasureConstants.RPC_SERVER_SERVER_ELAPSED_TIME, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str86 = "Hour stats on the number of server RPCs started";
        RPC_SERVER_STARTED_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/server/started_count/hour"), str86, RpcMeasureConstants.RPC_SERVER_STARTED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str87 = "Hour stats on the number of server RPCs finished";
        RPC_SERVER_FINISHED_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/server/finished_count/hour"), str87, RpcMeasureConstants.RPC_SERVER_FINISHED_COUNT, COUNT, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str88 = "Hour stats on the count of request messages per server RPC";
        RPC_SERVER_REQUEST_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/server/request_count/hour"), str88, RpcMeasureConstants.RPC_SERVER_REQUEST_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
        String str89 = "Hour stats on the count of response messages per server RPC";
        RPC_SERVER_RESPONSE_COUNT_HOUR_VIEW = View.create(Name.create("grpc.io/server/response_count/hour"), str89, RpcMeasureConstants.RPC_SERVER_RESPONSE_COUNT, MEAN, Arrays.asList(new TagKey[]{RpcMeasureConstants.RPC_METHOD}), INTERVAL_HOUR);
    }

    private RpcViewConstants() {
    }
}
