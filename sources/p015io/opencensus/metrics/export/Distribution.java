package p015io.opencensus.metrics.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.common.Function;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.metrics.data.Exemplar;

@Immutable
/* renamed from: io.opencensus.metrics.export.Distribution */
public abstract class Distribution {

    @Immutable
    /* renamed from: io.opencensus.metrics.export.Distribution$Bucket */
    public static abstract class Bucket {
        public abstract long getCount();

        @Nullable
        public abstract Exemplar getExemplar();

        Bucket() {
        }

        public static Bucket create(long j) {
            C2865Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            return new AutoValue_Distribution_Bucket(j, null);
        }

        public static Bucket create(long j, Exemplar exemplar) {
            C2865Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            C2865Utils.checkNotNull(exemplar, "exemplar");
            return new AutoValue_Distribution_Bucket(j, exemplar);
        }
    }

    @Immutable
    /* renamed from: io.opencensus.metrics.export.Distribution$BucketOptions */
    public static abstract class BucketOptions {

        @Immutable
        /* renamed from: io.opencensus.metrics.export.Distribution$BucketOptions$ExplicitOptions */
        public static abstract class ExplicitOptions extends BucketOptions {
            public abstract List<Double> getBucketBoundaries();

            ExplicitOptions() {
                super();
            }

            public final <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2) {
                return function.apply(this);
            }

            /* access modifiers changed from: private */
            public static ExplicitOptions create(List<Double> list) {
                C2865Utils.checkNotNull(list, "bucketBoundaries");
                List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
                checkBucketBoundsAreSorted(unmodifiableList);
                return new AutoValue_Distribution_BucketOptions_ExplicitOptions(unmodifiableList);
            }

            private static void checkBucketBoundsAreSorted(List<Double> list) {
                if (list.size() >= 1) {
                    String str = "bucketBoundary";
                    double doubleValue = ((Double) C2865Utils.checkNotNull(list.get(0), str)).doubleValue();
                    C2865Utils.checkArgument(doubleValue > 0.0d, "bucket boundary should be > 0");
                    int i = 1;
                    while (i < list.size()) {
                        double doubleValue2 = ((Double) C2865Utils.checkNotNull(list.get(i), str)).doubleValue();
                        C2865Utils.checkArgument(doubleValue < doubleValue2, "bucket boundaries not sorted.");
                        i++;
                        doubleValue = doubleValue2;
                    }
                }
            }
        }

        public abstract <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2);

        private BucketOptions() {
        }

        public static BucketOptions explicitOptions(List<Double> list) {
            return ExplicitOptions.create(list);
        }
    }

    @Nullable
    public abstract BucketOptions getBucketOptions();

    public abstract List<Bucket> getBuckets();

    public abstract long getCount();

    public abstract double getSum();

    public abstract double getSumOfSquaredDeviations();

    Distribution() {
    }

    public static Distribution create(long j, double d, double d2, BucketOptions bucketOptions, List<Bucket> list) {
        boolean z = true;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        C2865Utils.checkArgument(i >= 0, "count should be non-negative.");
        int i2 = (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1));
        C2865Utils.checkArgument(i2 >= 0, "sum of squared deviations should be non-negative.");
        if (i == 0) {
            C2865Utils.checkArgument(d == 0.0d, "sum should be 0 if count is 0.");
            if (i2 != 0) {
                z = false;
            }
            C2865Utils.checkArgument(z, "sum of squared deviations should be 0 if count is 0.");
        }
        C2865Utils.checkNotNull(bucketOptions, "bucketOptions");
        List unmodifiableList = Collections.unmodifiableList(new ArrayList((Collection) C2865Utils.checkNotNull(list, "buckets")));
        C2865Utils.checkListElementNotNull(unmodifiableList, "bucket");
        AutoValue_Distribution autoValue_Distribution = new AutoValue_Distribution(j, d, d2, bucketOptions, unmodifiableList);
        return autoValue_Distribution;
    }
}
