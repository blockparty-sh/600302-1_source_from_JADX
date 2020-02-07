package p015io.opencensus.common;

import java.util.TreeMap;
import javax.annotation.Nullable;

/* renamed from: io.opencensus.common.ServerStatsFieldEnums */
public final class ServerStatsFieldEnums {
    private static final int TOTALSIZE = computeTotalSize();

    /* renamed from: io.opencensus.common.ServerStatsFieldEnums$Id */
    public enum C2864Id {
        SERVER_STATS_LB_LATENCY_ID(0),
        SERVER_STATS_SERVICE_LATENCY_ID(1),
        SERVER_STATS_TRACE_OPTION_ID(2);
        
        private static final TreeMap<Integer, C2864Id> map = null;
        private final int value;

        static {
            int i;
            C2864Id[] values;
            map = new TreeMap<>();
            for (C2864Id id : values()) {
                map.put(Integer.valueOf(id.value), id);
            }
        }

        private C2864Id(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        @Nullable
        public static C2864Id valueOf(int i) {
            return (C2864Id) map.get(Integer.valueOf(i));
        }
    }

    /* renamed from: io.opencensus.common.ServerStatsFieldEnums$Size */
    public enum Size {
        SERVER_STATS_LB_LATENCY_SIZE(8),
        SERVER_STATS_SERVICE_LATENCY_SIZE(8),
        SERVER_STATS_TRACE_OPTION_SIZE(1);
        
        private final int value;

        private Size(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    private ServerStatsFieldEnums() {
    }

    private static int computeTotalSize() {
        int i = 0;
        for (Size value : Size.values()) {
            i = i + value.value() + 1;
        }
        return i;
    }

    public static int getTotalSize() {
        return TOTALSIZE;
    }
}
