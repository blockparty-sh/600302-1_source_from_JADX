package p015io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* renamed from: io.grpc.internal.ServiceConfigUtil */
public final class ServiceConfigUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long DURATION_SECONDS_MAX = 315576000000L;
    private static final long DURATION_SECONDS_MIN = -315576000000L;
    private static final String HEDGING_POLICY_HEDGING_DELAY_KEY = "hedgingDelay";
    private static final String HEDGING_POLICY_MAX_ATTEMPTS_KEY = "maxAttempts";
    private static final String HEDGING_POLICY_NON_FATAL_STATUS_CODES_KEY = "nonFatalStatusCodes";
    private static final String METHOD_CONFIG_HEDGING_POLICY_KEY = "hedgingPolicy";
    private static final String METHOD_CONFIG_MAX_REQUEST_MESSAGE_BYTES_KEY = "maxRequestMessageBytes";
    private static final String METHOD_CONFIG_MAX_RESPONSE_MESSAGE_BYTES_KEY = "maxResponseMessageBytes";
    private static final String METHOD_CONFIG_NAME_KEY = "name";
    private static final String METHOD_CONFIG_RETRY_POLICY_KEY = "retryPolicy";
    private static final String METHOD_CONFIG_TIMEOUT_KEY = "timeout";
    private static final String METHOD_CONFIG_WAIT_FOR_READY_KEY = "waitForReady";
    private static final String NAME_METHOD_KEY = "method";
    private static final String NAME_SERVICE_KEY = "service";
    private static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    private static final String RETRY_POLICY_BACKOFF_MULTIPLIER_KEY = "backoffMultiplier";
    private static final String RETRY_POLICY_INITIAL_BACKOFF_KEY = "initialBackoff";
    private static final String RETRY_POLICY_MAX_ATTEMPTS_KEY = "maxAttempts";
    private static final String RETRY_POLICY_MAX_BACKOFF_KEY = "maxBackoff";
    private static final String RETRY_POLICY_RETRYABLE_STATUS_CODES_KEY = "retryableStatusCodes";
    private static final String SERVICE_CONFIG_LOAD_BALANCING_CONFIG_KEY = "loadBalancingConfig";
    private static final String SERVICE_CONFIG_LOAD_BALANCING_POLICY_KEY = "loadBalancingPolicy";
    private static final String SERVICE_CONFIG_METHOD_CONFIG_KEY = "methodConfig";
    private static final String SERVICE_CONFIG_STICKINESS_METADATA_KEY = "stickinessMetadataKey";
    private static final String XDS_CONFIG_BALANCER_NAME_KEY = "balancerName";
    private static final String XDS_CONFIG_CHILD_POLICY_KEY = "childPolicy";
    private static final String XDS_CONFIG_FALLBACK_POLICY_KEY = "fallbackPolicy";

    /* renamed from: io.grpc.internal.ServiceConfigUtil$LbConfig */
    public static final class LbConfig {
        private final String policyName;
        private final Map<String, ?> rawConfigValue;

        public LbConfig(String str, Map<String, ?> map) {
            this.policyName = (String) Preconditions.checkNotNull(str, "policyName");
            this.rawConfigValue = (Map) Preconditions.checkNotNull(map, "rawConfigValue");
        }

        public String getPolicyName() {
            return this.policyName;
        }

        public Map<String, ?> getRawConfigValue() {
            return this.rawConfigValue;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LbConfig)) {
                return false;
            }
            LbConfig lbConfig = (LbConfig) obj;
            if (!this.policyName.equals(lbConfig.policyName) || !this.rawConfigValue.equals(lbConfig.rawConfigValue)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.policyName, this.rawConfigValue);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("policyName", (Object) this.policyName).add("rawConfigValue", (Object) this.rawConfigValue).toString();
        }
    }

    private static long saturatedAdd(long j, long j2) {
        long j3 = j + j2;
        boolean z = true;
        boolean z2 = (j2 ^ j) < 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        return z2 | z ? j3 : ((j3 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    private ServiceConfigUtil() {
    }

    @Nullable
    public static String getHealthCheckedServiceName(@Nullable Map<String, ?> map) {
        if (map != null) {
            String str = "healthCheckConfig";
            if (map.containsKey(str)) {
                Map object = getObject(map, str);
                String str2 = "serviceName";
                if (!object.containsKey(str2)) {
                    return null;
                }
                return getString(object, str2);
            }
        }
        return null;
    }

    @Nullable
    static Throttle getThrottlePolicy(@Nullable Map<String, ?> map) {
        if (map != null) {
            String str = "retryThrottling";
            if (map.containsKey(str)) {
                Map object = getObject(map, str);
                float floatValue = getDouble(object, "maxTokens").floatValue();
                float floatValue2 = getDouble(object, "tokenRatio").floatValue();
                boolean z = true;
                Preconditions.checkState(floatValue > 0.0f, "maxToken should be greater than zero");
                if (floatValue2 <= 0.0f) {
                    z = false;
                }
                Preconditions.checkState(z, "tokenRatio should be greater than zero");
                return new Throttle(floatValue, floatValue2);
            }
        }
        return null;
    }

    @Nullable
    static Integer getMaxAttemptsFromRetryPolicy(Map<String, ?> map) {
        String str = "maxAttempts";
        if (!map.containsKey(str)) {
            return null;
        }
        return Integer.valueOf(getDouble(map, str).intValue());
    }

    @Nullable
    static Long getInitialBackoffNanosFromRetryPolicy(Map<String, ?> map) {
        String str = RETRY_POLICY_INITIAL_BACKOFF_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, str)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Long getMaxBackoffNanosFromRetryPolicy(Map<String, ?> map) {
        String str = RETRY_POLICY_MAX_BACKOFF_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, str)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Double getBackoffMultiplierFromRetryPolicy(Map<String, ?> map) {
        String str = RETRY_POLICY_BACKOFF_MULTIPLIER_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return getDouble(map, str);
    }

    @Nullable
    static List<String> getRetryableStatusCodesFromRetryPolicy(Map<String, ?> map) {
        String str = RETRY_POLICY_RETRYABLE_STATUS_CODES_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return checkStringList(getList(map, str));
    }

    @Nullable
    static Integer getMaxAttemptsFromHedgingPolicy(Map<String, ?> map) {
        String str = "maxAttempts";
        if (!map.containsKey(str)) {
            return null;
        }
        return Integer.valueOf(getDouble(map, str).intValue());
    }

    @Nullable
    static Long getHedgingDelayNanosFromHedgingPolicy(Map<String, ?> map) {
        String str = HEDGING_POLICY_HEDGING_DELAY_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, str)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static List<String> getNonFatalStatusCodesFromHedgingPolicy(Map<String, ?> map) {
        String str = HEDGING_POLICY_NON_FATAL_STATUS_CODES_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return checkStringList(getList(map, str));
    }

    @Nullable
    static String getServiceFromName(Map<String, ?> map) {
        String str = "service";
        if (!map.containsKey(str)) {
            return null;
        }
        return getString(map, str);
    }

    @Nullable
    static String getMethodFromName(Map<String, ?> map) {
        String str = "method";
        if (!map.containsKey(str)) {
            return null;
        }
        return getString(map, str);
    }

    @Nullable
    static Map<String, ?> getRetryPolicyFromMethodConfig(Map<String, ?> map) {
        String str = METHOD_CONFIG_RETRY_POLICY_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return getObject(map, str);
    }

    @Nullable
    static Map<String, ?> getHedgingPolicyFromMethodConfig(Map<String, ?> map) {
        String str = METHOD_CONFIG_HEDGING_POLICY_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return getObject(map, str);
    }

    @Nullable
    static List<Map<String, ?>> getNameListFromMethodConfig(Map<String, ?> map) {
        String str = "name";
        if (!map.containsKey(str)) {
            return null;
        }
        return checkObjectList(getList(map, str));
    }

    @Nullable
    static Long getTimeoutFromMethodConfig(Map<String, ?> map) {
        String str = METHOD_CONFIG_TIMEOUT_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, str)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Boolean getWaitForReadyFromMethodConfig(Map<String, ?> map) {
        String str = METHOD_CONFIG_WAIT_FOR_READY_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return getBoolean(map, str);
    }

    @Nullable
    static Integer getMaxRequestMessageBytesFromMethodConfig(Map<String, ?> map) {
        String str = METHOD_CONFIG_MAX_REQUEST_MESSAGE_BYTES_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return Integer.valueOf(getDouble(map, str).intValue());
    }

    @Nullable
    static Integer getMaxResponseMessageBytesFromMethodConfig(Map<String, ?> map) {
        String str = METHOD_CONFIG_MAX_RESPONSE_MESSAGE_BYTES_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return Integer.valueOf(getDouble(map, str).intValue());
    }

    @Nullable
    static List<Map<String, ?>> getMethodConfigFromServiceConfig(Map<String, ?> map) {
        String str = SERVICE_CONFIG_METHOD_CONFIG_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return checkObjectList(getList(map, str));
    }

    @VisibleForTesting
    public static List<Map<String, ?>> getLoadBalancingConfigsFromServiceConfig(Map<String, ?> map) {
        ArrayList arrayList = new ArrayList();
        String str = SERVICE_CONFIG_LOAD_BALANCING_CONFIG_KEY;
        if (map.containsKey(str)) {
            for (Map add : checkObjectList(getList(map, str))) {
                arrayList.add(add);
            }
        }
        if (arrayList.isEmpty()) {
            String str2 = SERVICE_CONFIG_LOAD_BALANCING_POLICY_KEY;
            if (map.containsKey(str2)) {
                arrayList.add(Collections.singletonMap(getString(map, str2).toLowerCase(Locale.ROOT), Collections.emptyMap()));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static LbConfig unwrapLoadBalancingConfig(Map<String, ?> map) {
        if (map.size() == 1) {
            String str = (String) ((Entry) map.entrySet().iterator().next()).getKey();
            return new LbConfig(str, getObject(map, str));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("There are ");
        sb.append(map.size());
        sb.append(" fields in a LoadBalancingConfig object. Exactly one is expected. Config=");
        sb.append(map);
        throw new RuntimeException(sb.toString());
    }

    public static List<LbConfig> unwrapLoadBalancingConfigList(List<Map<String, ?>> list) {
        ArrayList arrayList = new ArrayList();
        for (Map unwrapLoadBalancingConfig : list) {
            arrayList.add(unwrapLoadBalancingConfig(unwrapLoadBalancingConfig));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static String getBalancerNameFromXdsConfig(LbConfig lbConfig) {
        return getString(lbConfig.getRawConfigValue(), XDS_CONFIG_BALANCER_NAME_KEY);
    }

    @Nullable
    public static List<LbConfig> getChildPolicyFromXdsConfig(LbConfig lbConfig) {
        List list = getList(lbConfig.getRawConfigValue(), XDS_CONFIG_CHILD_POLICY_KEY);
        if (list != null) {
            return unwrapLoadBalancingConfigList(checkObjectList(list));
        }
        return null;
    }

    @Nullable
    public static List<LbConfig> getFallbackPolicyFromXdsConfig(LbConfig lbConfig) {
        List list = getList(lbConfig.getRawConfigValue(), XDS_CONFIG_FALLBACK_POLICY_KEY);
        if (list != null) {
            return unwrapLoadBalancingConfigList(checkObjectList(list));
        }
        return null;
    }

    @Nullable
    public static String getStickinessMetadataKeyFromServiceConfig(Map<String, ?> map) {
        String str = SERVICE_CONFIG_STICKINESS_METADATA_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return getString(map, str);
    }

    @Nullable
    static List<?> getList(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof List) {
            return (List) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not List", new Object[]{obj, str, map}));
    }

    @Nullable
    static Map<String, ?> getObject(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Map) {
            return (Map) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not object", new Object[]{obj, str, map}));
    }

    @Nullable
    static Double getDouble(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Double) {
            return (Double) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Double", new Object[]{obj, str, map}));
    }

    @Nullable
    static String getString(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not String", new Object[]{obj, str, map}));
    }

    @Nullable
    static Boolean getBoolean(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Boolean", new Object[]{obj, str, map}));
    }

    static List<Map<String, ?>> checkObjectList(List<?> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) instanceof Map) {
                i++;
            } else {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not object", new Object[]{list.get(i), Integer.valueOf(i), list}));
            }
        }
        return list;
    }

    static List<String> checkStringList(List<?> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) instanceof String) {
                i++;
            } else {
                throw new ClassCastException(String.format("value '%s' for idx %d in '%s' is not string", new Object[]{list.get(i), Integer.valueOf(i), list}));
            }
        }
        return list;
    }

    private static long parseDuration(String str) throws ParseException {
        boolean z;
        String str2;
        String str3 = "Invalid duration string: ";
        if (str.isEmpty() || str.charAt(str.length() - 1) != 's') {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(str);
            throw new ParseException(sb.toString(), 0);
        }
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        String substring = str.substring(0, str.length() - 1);
        int indexOf = substring.indexOf(46);
        if (indexOf != -1) {
            str2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        } else {
            str2 = "";
        }
        long parseLong = Long.parseLong(substring);
        int parseNanos = str2.isEmpty() ? 0 : parseNanos(str2);
        if (parseLong >= 0) {
            if (z) {
                parseLong = -parseLong;
                parseNanos = -parseNanos;
            }
            try {
                return normalizedDuration(parseLong, parseNanos);
            } catch (IllegalArgumentException unused) {
                throw new ParseException("Duration value is out of range.", 0);
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(str);
            throw new ParseException(sb2.toString(), 0);
        }
    }

    private static int parseNanos(String str) throws ParseException {
        int i = 0;
        for (int i2 = 0; i2 < 9; i2++) {
            i *= 10;
            if (i2 < str.length()) {
                if (str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                    throw new ParseException("Invalid nanoseconds.", 0);
                }
                i += str.charAt(i2) - '0';
            }
        }
        return i;
    }

    private static long normalizedDuration(long j, int i) {
        long j2 = (long) i;
        long j3 = NANOS_PER_SECOND;
        if (j2 <= (-j3) || j2 >= j3) {
            j = LongMath.checkedAdd(j, j2 / NANOS_PER_SECOND);
            i = (int) (j2 % NANOS_PER_SECOND);
        }
        if (j > 0 && i < 0) {
            i = (int) (((long) i) + NANOS_PER_SECOND);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (((long) i) - NANOS_PER_SECOND);
            j++;
        }
        if (durationIsValid(j, i)) {
            return saturatedAdd(TimeUnit.SECONDS.toNanos(j), (long) i);
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", new Object[]{Long.valueOf(j), Integer.valueOf(i)}));
    }

    private static boolean durationIsValid(long j, int i) {
        if (j >= DURATION_SECONDS_MIN && j <= DURATION_SECONDS_MAX) {
            long j2 = (long) i;
            if (j2 >= -999999999 && j2 < NANOS_PER_SECOND) {
                int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if ((i2 >= 0 && i >= 0) || (i2 <= 0 && i <= 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}
