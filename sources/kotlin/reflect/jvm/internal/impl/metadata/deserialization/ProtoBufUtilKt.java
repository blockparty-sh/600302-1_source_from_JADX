package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProtoBufUtil.kt */
public final class ProtoBufUtilKt {
    @Nullable
    public static final <M extends ExtendableMessage<M>, T> T getExtensionOrNull(@NotNull ExtendableMessage<M> extendableMessage, @NotNull GeneratedExtension<M, T> generatedExtension) {
        Intrinsics.checkParameterIsNotNull(extendableMessage, "$receiver");
        Intrinsics.checkParameterIsNotNull(generatedExtension, "extension");
        if (extendableMessage.hasExtension(generatedExtension)) {
            return extendableMessage.getExtension(generatedExtension);
        }
        return null;
    }

    @Nullable
    public static final <M extends ExtendableMessage<M>, T> T getExtensionOrNull(@NotNull ExtendableMessage<M> extendableMessage, @NotNull GeneratedExtension<M, List<T>> generatedExtension, int i) {
        Intrinsics.checkParameterIsNotNull(extendableMessage, "$receiver");
        Intrinsics.checkParameterIsNotNull(generatedExtension, "extension");
        if (i < extendableMessage.getExtensionCount(generatedExtension)) {
            return extendableMessage.getExtension(generatedExtension, i);
        }
        return null;
    }
}