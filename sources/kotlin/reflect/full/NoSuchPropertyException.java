package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"Lkotlin/reflect/full/NoSuchPropertyException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cause", "(Ljava/lang/Exception;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
@SinceKotlin(version = "1.1")
/* compiled from: exceptions.kt */
public final class NoSuchPropertyException extends Exception {
    public NoSuchPropertyException() {
        this(null, 1, null);
    }

    public NoSuchPropertyException(@Nullable Exception exc) {
        super(exc);
    }

    public /* synthetic */ NoSuchPropertyException(Exception exc, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            exc = null;
        }
        this(exc);
    }
}
