package p015io.opencensus.metrics;

import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
import p015io.opencensus.common.ToLongFunction;
import p015io.opencensus.internal.C2865Utils;

@ThreadSafe
/* renamed from: io.opencensus.metrics.DerivedLongCumulative */
public abstract class DerivedLongCumulative {

    /* renamed from: io.opencensus.metrics.DerivedLongCumulative$NoopDerivedLongCumulative */
    private static final class NoopDerivedLongCumulative extends DerivedLongCumulative {
        private final int labelKeysSize;

        public void clear() {
        }

        static NoopDerivedLongCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDerivedLongCumulative(str, str2, str3, list);
        }

        NoopDerivedLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
            C2865Utils.checkNotNull(str, "name");
            C2865Utils.checkNotNull(str2, "description");
            C2865Utils.checkNotNull(str3, "unit");
            C2865Utils.checkListElementNotNull((List) C2865Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        public <T> void createTimeSeries(List<LabelValue> list, T t, ToLongFunction<T> toLongFunction) {
            C2865Utils.checkListElementNotNull((List) C2865Utils.checkNotNull(list, "labelValues"), "labelValue");
            C2865Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            C2865Utils.checkNotNull(toLongFunction, "function");
        }

        public void removeTimeSeries(List<LabelValue> list) {
            C2865Utils.checkNotNull(list, "labelValues");
        }
    }

    public abstract void clear();

    public abstract <T> void createTimeSeries(List<LabelValue> list, T t, ToLongFunction<T> toLongFunction);

    public abstract void removeTimeSeries(List<LabelValue> list);

    static DerivedLongCumulative newNoopDerivedLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDerivedLongCumulative.create(str, str2, str3, list);
    }
}
