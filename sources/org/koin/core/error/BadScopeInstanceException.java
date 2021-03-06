package org.koin.core.error;

import com.leanplum.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, mo37405d2 = {"Lorg/koin/core/error/BadScopeInstanceException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "s", "", "(Ljava/lang/String;)V", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: BadScopeInstanceException.kt */
public final class BadScopeInstanceException extends Exception {
    public BadScopeInstanceException(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER);
        super(str);
    }
}
