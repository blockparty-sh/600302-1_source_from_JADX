package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BuiltInsResourceLoader.kt */
public final class BuiltInsResourceLoader {
    @Nullable
    public final InputStream loadResource(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, JsonDataKey_signMessage.path);
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader != null) {
            InputStream resourceAsStream = classLoader.getResourceAsStream(str);
            if (resourceAsStream != null) {
                return resourceAsStream;
            }
        }
        return ClassLoader.getSystemResourceAsStream(str);
    }
}
