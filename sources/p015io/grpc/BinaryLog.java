package p015io.grpc;

import java.io.Closeable;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/4017")
/* renamed from: io.grpc.BinaryLog */
public abstract class BinaryLog implements Closeable {
    public abstract Channel wrapChannel(Channel channel);

    public abstract <ReqT, RespT> ServerMethodDefinition<?, ?> wrapMethodDefinition(ServerMethodDefinition<ReqT, RespT> serverMethodDefinition);
}
