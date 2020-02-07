package p015io.grpc.perfmark;

/* renamed from: io.grpc.perfmark.InternalPerfMark */
public final class InternalPerfMark {
    private static final long NULL_NUMERIC_TAG = 0;
    private static final String NULL_STRING_TAG = null;

    /* renamed from: io.grpc.perfmark.InternalPerfMark$InternalPerfMarkTask */
    public static abstract class InternalPerfMarkTask extends PerfMarkTask {
    }

    private InternalPerfMark() {
    }

    public static PerfTag createPerfTag(long j, String str) {
        return TagFactory.create(j, str);
    }

    public static PerfTag createPerfTag(String str) {
        return TagFactory.create(0, str);
    }

    public static PerfTag createPerfTag(long j) {
        return TagFactory.create(j, NULL_STRING_TAG);
    }
}
