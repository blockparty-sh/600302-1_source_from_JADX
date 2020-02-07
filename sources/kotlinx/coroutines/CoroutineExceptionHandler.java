package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, mo37405d2 = {"Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlin/coroutines/CoroutineContext$Element;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "Key", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CoroutineExceptionHandler.kt */
public interface CoroutineExceptionHandler extends Element {
    public static final C3157Key Key = C3157Key.$$INSTANCE;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: CoroutineExceptionHandler.kt */
    public static final class DefaultImpls {
        public static <R> R fold(CoroutineExceptionHandler coroutineExceptionHandler, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "operation");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.fold(coroutineExceptionHandler, r, function2);
        }

        @Nullable
        public static <E extends Element> E get(CoroutineExceptionHandler coroutineExceptionHandler, @NotNull C2916Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.get(coroutineExceptionHandler, key);
        }

        @NotNull
        public static CoroutineContext minusKey(CoroutineExceptionHandler coroutineExceptionHandler, @NotNull C2916Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.minusKey(coroutineExceptionHandler, key);
        }

        @NotNull
        public static CoroutineContext plus(CoroutineExceptionHandler coroutineExceptionHandler, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.plus(coroutineExceptionHandler, coroutineContext);
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, mo37405d2 = {"Lkotlinx/coroutines/CoroutineExceptionHandler$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "()V", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: kotlinx.coroutines.CoroutineExceptionHandler$Key */
    /* compiled from: CoroutineExceptionHandler.kt */
    public static final class C3157Key implements C2916Key<CoroutineExceptionHandler> {
        static final /* synthetic */ C3157Key $$INSTANCE = new C3157Key();

        private C3157Key() {
        }
    }

    void handleException(@NotNull CoroutineContext coroutineContext, @NotNull Throwable th);
}
