package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, mo37405d2 = {"Lkotlin/reflect/full/IllegalCallableAccessException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cause", "Ljava/lang/IllegalAccessException;", "(Ljava/lang/IllegalAccessException;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
@SinceKotlin(version = "1.1")
/* compiled from: exceptions.kt */
public final class IllegalCallableAccessException extends Exception {
    public IllegalCallableAccessException(@NotNull IllegalAccessException illegalAccessException) {
        Intrinsics.checkParameterIsNotNull(illegalAccessException, "cause");
        super(illegalAccessException);
    }
}
