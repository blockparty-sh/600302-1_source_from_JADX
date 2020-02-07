package org.koin.android.error;

import com.leanplum.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"Lorg/koin/android/error/MissingAndroidContextException;", "", "s", "", "(Ljava/lang/String;)V", "koin-android_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MissingAndroidContextException.kt */
public final class MissingAndroidContextException extends Throwable {
    public MissingAndroidContextException(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER);
        super(str);
    }
}
