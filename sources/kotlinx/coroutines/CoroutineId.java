package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0018B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, mo37405d2 = {"Lkotlinx/coroutines/CoroutineId;", "Lkotlinx/coroutines/ThreadContextElement;", "", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "restoreThreadContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "oldState", "toString", "updateThreadContext", "Key", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CoroutineContext.kt */
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement<String> {
    public static final C3158Key Key = new C3158Key(null);

    /* renamed from: id */
    private final long f733id;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, mo37405d2 = {"Lkotlinx/coroutines/CoroutineId$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineId;", "()V", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: kotlinx.coroutines.CoroutineId$Key */
    /* compiled from: CoroutineContext.kt */
    public static final class C3158Key implements C2916Key<CoroutineId> {
        private C3158Key() {
        }

        public /* synthetic */ C3158Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public static /* synthetic */ CoroutineId copy$default(CoroutineId coroutineId, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = coroutineId.f733id;
        }
        return coroutineId.copy(j);
    }

    public final long component1() {
        return this.f733id;
    }

    @NotNull
    public final CoroutineId copy(long j) {
        return new CoroutineId(j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof CoroutineId) {
                if (this.f733id == ((CoroutineId) obj).f733id) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.f733id;
        return (int) (j ^ (j >>> 32));
    }

    public <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        return DefaultImpls.fold(this, r, function2);
    }

    @Nullable
    public <E extends Element> E get(@NotNull C2916Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.get(this, key);
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull C2916Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.minusKey(this, key);
    }

    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return DefaultImpls.plus(this, coroutineContext);
    }

    public final long getId() {
        return this.f733id;
    }

    public CoroutineId(long j) {
        super(Key);
        this.f733id = j;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CoroutineId(");
        sb.append(this.f733id);
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0015, code lost:
        if (r10 != null) goto L_0x001a;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String updateThreadContext(@org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext r10) {
        /*
            r9 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            kotlinx.coroutines.CoroutineName$Key r0 = kotlinx.coroutines.CoroutineName.Key
            kotlin.coroutines.CoroutineContext$Key r0 = (kotlin.coroutines.CoroutineContext.C2916Key) r0
            kotlin.coroutines.CoroutineContext$Element r10 = r10.get(r0)
            kotlinx.coroutines.CoroutineName r10 = (kotlinx.coroutines.CoroutineName) r10
            if (r10 == 0) goto L_0x0018
            java.lang.String r10 = r10.getName()
            if (r10 == 0) goto L_0x0018
            goto L_0x001a
        L_0x0018:
            java.lang.String r10 = "coroutine"
        L_0x001a:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.String r1 = "currentThread"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r1 = r0.getName()
            java.lang.String r2 = "oldName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r3 = r1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.lang.String r4 = " @"
            int r2 = kotlin.text.StringsKt.lastIndexOf$default(r3, r4, r5, r6, r7, r8)
            if (r2 >= 0) goto L_0x003f
            int r2 = r1.length()
        L_0x003f:
            int r3 = r10.length()
            int r3 = r3 + r2
            int r3 = r3 + 10
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            r3 = 0
            java.lang.String r2 = r1.substring(r3, r2)
            java.lang.String r3 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r4.append(r2)
            java.lang.String r2 = " @"
            r4.append(r2)
            r4.append(r10)
            r10 = 35
            r4.append(r10)
            long r2 = r9.f733id
            r4.append(r2)
            java.lang.String r10 = r4.toString()
            java.lang.String r2 = "StringBuilder(capacity).…builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r2)
            r0.setName(r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineId.updateThreadContext(kotlin.coroutines.CoroutineContext):java.lang.String");
    }

    public void restoreThreadContext(@NotNull CoroutineContext coroutineContext, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(str, "oldState");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        currentThread.setName(str);
    }
}
