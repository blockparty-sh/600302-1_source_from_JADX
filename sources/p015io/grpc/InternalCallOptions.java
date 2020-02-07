package p015io.grpc;

@Internal
/* renamed from: io.grpc.InternalCallOptions */
public final class InternalCallOptions {
    private InternalCallOptions() {
    }

    public static Boolean getWaitForReady(CallOptions callOptions) {
        return callOptions.getWaitForReady();
    }
}
