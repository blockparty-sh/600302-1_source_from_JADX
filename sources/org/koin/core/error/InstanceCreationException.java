package org.koin.core.error;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lorg/koin/core/error/InstanceCreationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "msg", "", "parent", "(Ljava/lang/String;Ljava/lang/Exception;)V", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: InstanceCreationException.kt */
public final class InstanceCreationException extends Exception {
    public InstanceCreationException(@NotNull String str, @NotNull Exception exc) {
        Intrinsics.checkParameterIsNotNull(str, NotificationCompat.CATEGORY_MESSAGE);
        Intrinsics.checkParameterIsNotNull(exc, "parent");
        super(str, exc);
    }
}
