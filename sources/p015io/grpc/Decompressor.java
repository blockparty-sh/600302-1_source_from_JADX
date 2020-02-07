package p015io.grpc;

import java.io.IOException;
import java.io.InputStream;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1704")
/* renamed from: io.grpc.Decompressor */
public interface Decompressor {
    InputStream decompress(InputStream inputStream) throws IOException;

    String getMessageEncoding();
}
