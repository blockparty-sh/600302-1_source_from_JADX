package p015io.grpc.perfmark;

import com.google.errorprone.annotations.CompileTimeConstant;

/* renamed from: io.grpc.perfmark.PerfMark */
public final class PerfMark {
    private static final PerfTag NULL_PERF_TAG = TagFactory.create();

    /* renamed from: io.grpc.perfmark.PerfMark$NoopTask */
    private static final class NoopTask extends PerfMarkTask {
        /* access modifiers changed from: private */
        public static final PerfMarkTask INSTANCE = new NoopTask();

        public void close() {
        }

        NoopTask() {
        }
    }

    public static void event(PerfTag perfTag, @CompileTimeConstant String str) {
    }

    public static void event(@CompileTimeConstant String str) {
    }

    public static void taskEnd(PerfTag perfTag, @CompileTimeConstant String str) {
    }

    public static void taskEnd(@CompileTimeConstant String str) {
    }

    public static void taskStart(PerfTag perfTag, @CompileTimeConstant String str) {
    }

    public static void taskStart(@CompileTimeConstant String str) {
    }

    private PerfMark() {
        throw new AssertionError("nope");
    }

    public static PerfMarkTask task(PerfTag perfTag, @CompileTimeConstant String str) {
        return NoopTask.INSTANCE;
    }

    public static PerfMarkTask task(@CompileTimeConstant String str) {
        return NoopTask.INSTANCE;
    }

    public static PerfTag createTag(long j, String str) {
        return NULL_PERF_TAG;
    }

    public static PerfTag createTag(String str) {
        return NULL_PERF_TAG;
    }

    public static PerfTag createTag(long j) {
        return NULL_PERF_TAG;
    }
}
