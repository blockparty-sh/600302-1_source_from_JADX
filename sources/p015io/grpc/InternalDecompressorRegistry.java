package p015io.grpc;

@Internal
/* renamed from: io.grpc.InternalDecompressorRegistry */
public final class InternalDecompressorRegistry {
    private InternalDecompressorRegistry() {
    }

    @Internal
    public static byte[] getRawAdvertisedMessageEncodings(DecompressorRegistry decompressorRegistry) {
        return decompressorRegistry.getRawAdvertisedMessageEncodings();
    }
}
