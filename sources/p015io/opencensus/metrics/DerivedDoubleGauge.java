package p015io.opencensus.metrics;

import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
import p015io.opencensus.common.ToDoubleFunction;
import p015io.opencensus.internal.C2865Utils;

@ThreadSafe
/* renamed from: io.opencensus.metrics.DerivedDoubleGauge */
public abstract class DerivedDoubleGauge {

    /* renamed from: io.opencensus.metrics.DerivedDoubleGauge$NoopDerivedDoubleGauge */
    private static final class NoopDerivedDoubleGauge extends DerivedDoubleGauge {
        private final int labelKeysSize;

        public void clear() {
        }

        static NoopDerivedDoubleGauge create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDerivedDoubleGauge(str, str2, str3, list);
        }

        NoopDerivedDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
            C2865Utils.checkNotNull(str, "name");
            C2865Utils.checkNotNull(str2, "description");
            C2865Utils.checkNotNull(str3, "unit");
            C2865Utils.checkListElementNotNull((List) C2865Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        public <T> void createTimeSeries(List<LabelValue> list, T t, ToDoubleFunction<T> toDoubleFunction) {
            C2865Utils.checkListElementNotNull((List) C2865Utils.checkNotNull(list, "labelValues"), "labelValue");
            C2865Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            C2865Utils.checkNotNull(toDoubleFunction, "function");
        }

        public void removeTimeSeries(List<LabelValue> list) {
            C2865Utils.checkNotNull(list, "labelValues");
        }
    }

    public abstract void clear();

    public abstract <T> void createTimeSeries(List<LabelValue> list, T t, ToDoubleFunction<T> toDoubleFunction);

    public abstract void removeTimeSeries(List<LabelValue> list);

    static DerivedDoubleGauge newNoopDerivedDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDerivedDoubleGauge.create(str, str2, str3, list);
    }
}
