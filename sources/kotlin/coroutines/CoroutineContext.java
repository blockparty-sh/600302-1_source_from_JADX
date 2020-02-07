package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001:\u0002\u0011\u0012J5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0002¨\u0006\u0013"}, mo37405d2 = {"Lkotlin/coroutines/CoroutineContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "plus", "context", "Element", "Key", "kotlin-stdlib"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@SinceKotlin(version = "1.3")
/* compiled from: CoroutineContext.kt */
public interface CoroutineContext {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: CoroutineContext.kt */
    public static final class DefaultImpls {
        @NotNull
        public static CoroutineContext plus(CoroutineContext coroutineContext, @NotNull CoroutineContext coroutineContext2) {
            Intrinsics.checkParameterIsNotNull(coroutineContext2, "context");
            return coroutineContext2 == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) coroutineContext2.fold(coroutineContext, CoroutineContext$plus$1.INSTANCE);
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, mo37405d2 = {"Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "kotlin-stdlib"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CoroutineContext.kt */
    public interface Element extends CoroutineContext {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        /* compiled from: CoroutineContext.kt */
        public static final class DefaultImpls {
            @NotNull
            public static CoroutineContext plus(Element element, @NotNull CoroutineContext coroutineContext) {
                Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
                return DefaultImpls.plus(element, coroutineContext);
            }

            @Nullable
            public static <E extends Element> E get(Element element, @NotNull C2916Key<E> key) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                if (!Intrinsics.areEqual((Object) element.getKey(), (Object) key)) {
                    return null;
                }
                if (element != null) {
                    return element;
                }
                throw new TypeCastException("null cannot be cast to non-null type E");
            }

            public static <R> R fold(Element element, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
                Intrinsics.checkParameterIsNotNull(function2, "operation");
                return function2.invoke(r, element);
            }

            /* JADX WARNING: type inference failed for: r1v1 */
            /* JADX WARNING: type inference failed for: r1v3, types: [kotlin.coroutines.EmptyCoroutineContext] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Unknown variable types count: 1 */
            @org.jetbrains.annotations.NotNull
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static kotlin.coroutines.CoroutineContext minusKey(kotlin.coroutines.CoroutineContext.Element r1, @org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext.C2916Key<?> r2) {
                /*
                    java.lang.String r0 = "key"
                    kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
                    kotlin.coroutines.CoroutineContext$Key r0 = r1.getKey()
                    boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
                    if (r2 == 0) goto L_0x0011
                    kotlin.coroutines.EmptyCoroutineContext r1 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
                L_0x0011:
                    kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.coroutines.CoroutineContext.Element.DefaultImpls.minusKey(kotlin.coroutines.CoroutineContext$Element, kotlin.coroutines.CoroutineContext$Key):kotlin.coroutines.CoroutineContext");
            }
        }

        <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

        @Nullable
        <E extends Element> E get(@NotNull C2916Key<E> key);

        @NotNull
        C2916Key<?> getKey();

        @NotNull
        CoroutineContext minusKey(@NotNull C2916Key<?> key);
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, mo37405d2 = {"Lkotlin/coroutines/CoroutineContext$Key;", "E", "Lkotlin/coroutines/CoroutineContext$Element;", "", "kotlin-stdlib"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: kotlin.coroutines.CoroutineContext$Key */
    /* compiled from: CoroutineContext.kt */
    public interface C2916Key<E extends Element> {
    }

    <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

    @Nullable
    <E extends Element> E get(@NotNull C2916Key<E> key);

    @NotNull
    CoroutineContext minusKey(@NotNull C2916Key<?> key);

    @NotNull
    CoroutineContext plus(@NotNull CoroutineContext coroutineContext);
}
