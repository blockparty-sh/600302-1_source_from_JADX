package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.Arrays;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmMetadataVersion.kt */
public final class JvmMetadataVersion extends BinaryVersion {
    public static final Companion Companion = new Companion(null);
    @NotNull
    @JvmField
    public static final JvmMetadataVersion INSTANCE = new JvmMetadataVersion(1, 1, 11);
    @NotNull
    @JvmField
    public static final JvmMetadataVersion INVALID_VERSION = new JvmMetadataVersion(new int[0]);

    /* compiled from: JvmMetadataVersion.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public JvmMetadataVersion(@NotNull int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "numbers");
        super(Arrays.copyOf(iArr, iArr.length));
    }

    public boolean isCompatible() {
        return getMajor() == 1 && getMinor() == 1;
    }
}
