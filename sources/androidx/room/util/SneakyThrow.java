package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
public class SneakyThrow {
    public static void reThrow(@NonNull Exception exc) {
        sneakyThrow(exc);
    }

    private static <E extends Throwable> void sneakyThrow(@NonNull Throwable th) throws Throwable {
        throw th;
    }

    private SneakyThrow() {
    }
}
