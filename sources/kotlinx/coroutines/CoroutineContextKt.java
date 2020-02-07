package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\r\u001a\u00020\u000eH\u0000\u001a\b\u0010\u000f\u001a\u00020\u0010H\u0000\u001a4\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0017H\b¢\u0006\u0002\u0010\u0018\u001a\u0014\u0010\u0019\u001a\u00020\n*\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\nH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u001a\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\n8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, mo37405d2 = {"COROUTINES_SCHEDULER_PROPERTY_NAME", "", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "DEBUG_THREAD_NAME_SEPARATOR", "useCoroutinesScheduler", "", "getUseCoroutinesScheduler", "()Z", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "createDefaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "resetCoroutineId", "", "withCoroutineContext", "T", "context", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "newCoroutineContext", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: CoroutineContext.kt */
public final class CoroutineContextKt {
    @NotNull
    public static final String COROUTINES_SCHEDULER_PROPERTY_NAME = "kotlinx.coroutines.scheduler";
    private static final AtomicLong COROUTINE_ID = new AtomicLong();
    private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";
    private static final boolean useCoroutinesScheduler;

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        if (r0.equals(kotlinx.coroutines.DebugKt.DEBUG_PROPERTY_VALUE_ON) != false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        if (r0.equals("") != false) goto L_0x003a;
     */
    static {
        /*
            java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
            r0.<init>()
            COROUTINE_ID = r0
            java.lang.String r0 = "kotlinx.coroutines.scheduler"
            java.lang.String r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0)
            if (r0 != 0) goto L_0x0010
            goto L_0x003a
        L_0x0010:
            int r1 = r0.hashCode()
            if (r1 == 0) goto L_0x0032
            r2 = 3551(0xddf, float:4.976E-42)
            if (r1 == r2) goto L_0x0029
            r2 = 109935(0x1ad6f, float:1.54052E-40)
            if (r1 != r2) goto L_0x003e
            java.lang.String r1 = "off"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x003e
            r0 = 0
            goto L_0x003b
        L_0x0029:
            java.lang.String r1 = "on"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x003e
            goto L_0x003a
        L_0x0032:
            java.lang.String r1 = ""
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x003e
        L_0x003a:
            r0 = 1
        L_0x003b:
            useCoroutinesScheduler = r0
            return
        L_0x003e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "System property 'kotlinx.coroutines.scheduler' has unrecognized value '"
            r1.append(r2)
            r1.append(r0)
            r0 = 39
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.<clinit>():void");
    }

    public static final void resetCoroutineId() {
        COROUTINE_ID.set(0);
    }

    public static final boolean getUseCoroutinesScheduler() {
        return useCoroutinesScheduler;
    }

    @NotNull
    public static final CoroutineDispatcher createDefaultDispatcher() {
        return useCoroutinesScheduler ? DefaultScheduler.INSTANCE : CommonPool.INSTANCE;
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final CoroutineContext newCoroutineContext(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$newCoroutineContext");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        CoroutineContext plus = coroutineScope.getCoroutineContext().plus(coroutineContext);
        CoroutineContext plus2 = DebugKt.DEBUG ? plus.plus(new CoroutineId(COROUTINE_ID.incrementAndGet())) : plus;
        return (plus == Dispatchers.getDefault() || plus.get(ContinuationInterceptor.Key) != null) ? plus2 : plus2.plus(Dispatchers.getDefault());
    }

    public static final <T> T withCoroutineContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function0, "block");
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        if (r4 != null) goto L_0x002c;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getCoroutineName(@org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext r4) {
        /*
            java.lang.String r0 = "$this$coroutineName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            boolean r0 = kotlinx.coroutines.DebugKt.DEBUG
            r1 = 0
            if (r0 != 0) goto L_0x000b
            return r1
        L_0x000b:
            kotlinx.coroutines.CoroutineId$Key r0 = kotlinx.coroutines.CoroutineId.Key
            kotlin.coroutines.CoroutineContext$Key r0 = (kotlin.coroutines.CoroutineContext.C2916Key) r0
            kotlin.coroutines.CoroutineContext$Element r0 = r4.get(r0)
            kotlinx.coroutines.CoroutineId r0 = (kotlinx.coroutines.CoroutineId) r0
            if (r0 == 0) goto L_0x0045
            kotlinx.coroutines.CoroutineName$Key r1 = kotlinx.coroutines.CoroutineName.Key
            kotlin.coroutines.CoroutineContext$Key r1 = (kotlin.coroutines.CoroutineContext.C2916Key) r1
            kotlin.coroutines.CoroutineContext$Element r4 = r4.get(r1)
            kotlinx.coroutines.CoroutineName r4 = (kotlinx.coroutines.CoroutineName) r4
            if (r4 == 0) goto L_0x002a
            java.lang.String r4 = r4.getName()
            if (r4 == 0) goto L_0x002a
            goto L_0x002c
        L_0x002a:
            java.lang.String r4 = "coroutine"
        L_0x002c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            r4 = 35
            r1.append(r4)
            long r2 = r0.getId()
            r1.append(r2)
            java.lang.String r4 = r1.toString()
            return r4
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.getCoroutineName(kotlin.coroutines.CoroutineContext):java.lang.String");
    }
}
