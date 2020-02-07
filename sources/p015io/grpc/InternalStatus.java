package p015io.grpc;

import p015io.grpc.Metadata.C2689Key;

@Internal
/* renamed from: io.grpc.InternalStatus */
public final class InternalStatus {
    @Internal
    public static final C2689Key<Status> CODE_KEY = Status.CODE_KEY;
    @Internal
    public static final C2689Key<String> MESSAGE_KEY = Status.MESSAGE_KEY;

    private InternalStatus() {
    }
}
