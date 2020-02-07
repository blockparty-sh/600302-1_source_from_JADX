package p015io.opencensus.metrics;

import java.util.List;
import p015io.opencensus.internal.C2865Utils;

/* renamed from: io.opencensus.metrics.MetricRegistry */
public abstract class MetricRegistry {

    /* renamed from: io.opencensus.metrics.MetricRegistry$NoopMetricRegistry */
    private static final class NoopMetricRegistry extends MetricRegistry {
        private NoopMetricRegistry() {
        }

        public LongGauge addLongGauge(String str, MetricOptions metricOptions) {
            return LongGauge.newNoopLongGauge((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        public DoubleGauge addDoubleGauge(String str, MetricOptions metricOptions) {
            return DoubleGauge.newNoopDoubleGauge((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        public DerivedLongGauge addDerivedLongGauge(String str, MetricOptions metricOptions) {
            return DerivedLongGauge.newNoopDerivedLongGauge((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        public DerivedDoubleGauge addDerivedDoubleGauge(String str, MetricOptions metricOptions) {
            return DerivedDoubleGauge.newNoopDerivedDoubleGauge((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        public LongCumulative addLongCumulative(String str, MetricOptions metricOptions) {
            return LongCumulative.newNoopLongCumulative((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        public DoubleCumulative addDoubleCumulative(String str, MetricOptions metricOptions) {
            return DoubleCumulative.newNoopDoubleCumulative((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        public DerivedLongCumulative addDerivedLongCumulative(String str, MetricOptions metricOptions) {
            return DerivedLongCumulative.newNoopDerivedLongCumulative((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        public DerivedDoubleCumulative addDerivedDoubleCumulative(String str, MetricOptions metricOptions) {
            return DerivedDoubleCumulative.newNoopDerivedDoubleCumulative((String) C2865Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }
    }

    public abstract DerivedDoubleCumulative addDerivedDoubleCumulative(String str, MetricOptions metricOptions);

    public abstract DerivedDoubleGauge addDerivedDoubleGauge(String str, MetricOptions metricOptions);

    public abstract DerivedLongCumulative addDerivedLongCumulative(String str, MetricOptions metricOptions);

    public abstract DerivedLongGauge addDerivedLongGauge(String str, MetricOptions metricOptions);

    public abstract DoubleCumulative addDoubleCumulative(String str, MetricOptions metricOptions);

    public abstract DoubleGauge addDoubleGauge(String str, MetricOptions metricOptions);

    public abstract LongCumulative addLongCumulative(String str, MetricOptions metricOptions);

    public abstract LongGauge addLongGauge(String str, MetricOptions metricOptions);

    @Deprecated
    public LongGauge addLongGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addLongGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    @Deprecated
    public DoubleGauge addDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDoubleGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    @Deprecated
    public DerivedLongGauge addDerivedLongGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDerivedLongGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    @Deprecated
    public DerivedDoubleGauge addDerivedDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDerivedDoubleGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    static MetricRegistry newNoopMetricRegistry() {
        return new NoopMetricRegistry();
    }
}
