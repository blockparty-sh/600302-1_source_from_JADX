package androidx.work;

import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016j\u0002\b\n¨\u0006\u000b"}, mo37405d2 = {"Landroidx/work/DirectExecutor;", "", "Ljava/util/concurrent/Executor;", "(Ljava/lang/String;I)V", "execute", "", "command", "Ljava/lang/Runnable;", "toString", "", "INSTANCE", "work-runtime-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: DirectExecutor.kt */
public enum DirectExecutor implements Executor {
    INSTANCE;

    @NotNull
    public String toString() {
        return "DirectExecutor";
    }

    public void execute(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "command");
        runnable.run();
    }
}
