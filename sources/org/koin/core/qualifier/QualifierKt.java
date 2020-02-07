package org.koin.core.qualifier;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\b\u001a\u000e\u0010\u0000\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, mo37405d2 = {"named", "Lorg/koin/core/qualifier/TypeQualifier;", "T", "Lorg/koin/core/qualifier/StringQualifier;", "name", "", "koin-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Qualifier.kt */
public final class QualifierKt {
    @NotNull
    public static final StringQualifier named(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        return new StringQualifier(str);
    }

    private static final <T> TypeQualifier named() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
    }
}
