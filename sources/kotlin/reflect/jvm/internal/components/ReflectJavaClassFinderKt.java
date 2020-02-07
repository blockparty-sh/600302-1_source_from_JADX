package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"tryLoadClass", "Ljava/lang/Class;", "Ljava/lang/ClassLoader;", "fqName", "", "descriptors.runtime"}, mo37406k = 2, mo37407mv = {1, 1, 11})
/* compiled from: ReflectJavaClassFinder.kt */
public final class ReflectJavaClassFinderKt {
    @Nullable
    public static final Class<?> tryLoadClass(@NotNull ClassLoader classLoader, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(classLoader, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "fqName");
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}