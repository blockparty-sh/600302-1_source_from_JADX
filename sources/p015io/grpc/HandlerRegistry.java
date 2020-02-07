package p015io.grpc;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: io.grpc.HandlerRegistry */
public abstract class HandlerRegistry {
    @Nullable
    public abstract ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2);

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2222")
    public List<ServerServiceDefinition> getServices() {
        return Collections.emptyList();
    }

    @Nullable
    public final ServerMethodDefinition<?, ?> lookupMethod(String str) {
        return lookupMethod(str, null);
    }
}
