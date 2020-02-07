package p015io.grpc;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/4359")
/* renamed from: io.grpc.ConnectivityState */
public enum ConnectivityState {
    CONNECTING,
    READY,
    TRANSIENT_FAILURE,
    IDLE,
    SHUTDOWN
}
