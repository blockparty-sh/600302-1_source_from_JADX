package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\u00020\f8FX\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u00020\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\u0007¨\u0006\u0013"}, mo37405d2 = {"Lkotlinx/coroutines/Dispatchers;", "", "()V", "Default", "Lkotlinx/coroutines/CoroutineDispatcher;", "Default$annotations", "getDefault", "()Lkotlinx/coroutines/CoroutineDispatcher;", "IO", "IO$annotations", "getIO", "Main", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Main$annotations", "getMain", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "Unconfined", "Unconfined$annotations", "getUnconfined", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Dispatchers.kt */
public final class Dispatchers {
    @NotNull
    private static final CoroutineDispatcher Default = CoroutineContextKt.createDefaultDispatcher();
    public static final Dispatchers INSTANCE = new Dispatchers();
    @NotNull

    /* renamed from: IO */
    private static final CoroutineDispatcher f734IO = DefaultScheduler.INSTANCE.getIO();
    @NotNull
    private static final CoroutineDispatcher Unconfined = Unconfined.INSTANCE;

    @JvmStatic
    public static /* synthetic */ void Default$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void IO$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void Main$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void Unconfined$annotations() {
    }

    private Dispatchers() {
    }

    @NotNull
    public static final CoroutineDispatcher getDefault() {
        return Default;
    }

    @NotNull
    public static final MainCoroutineDispatcher getMain() {
        return MainDispatcherLoader.dispatcher;
    }

    @NotNull
    public static final CoroutineDispatcher getUnconfined() {
        return Unconfined;
    }

    @NotNull
    public static final CoroutineDispatcher getIO() {
        return f734IO;
    }
}
