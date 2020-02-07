package kotlin.p016io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"Lkotlin/io/TerminateException;", "Lkotlin/io/FileSystemException;", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: kotlin.io.TerminateException */
/* compiled from: Utils.kt */
final class TerminateException extends FileSystemException {
    public TerminateException(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        super(file, null, null, 6, null);
    }
}
