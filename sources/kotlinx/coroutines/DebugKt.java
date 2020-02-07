package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0010H\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u0018\u0010\t\u001a\u00020\u0003*\u00020\n8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0018\u0010\r\u001a\u00020\u0003*\u00020\n8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, mo37405d2 = {"DEBUG", "", "DEBUG_PROPERTY_NAME", "", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_OFF", "DEBUG_PROPERTY_VALUE_ON", "RECOVER_STACK_TRACES", "STACKTRACE_RECOVERY_PROPERTY_NAME", "classSimpleName", "", "getClassSimpleName", "(Ljava/lang/Object;)Ljava/lang/String;", "hexAddress", "getHexAddress", "toDebugString", "Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Debug.kt */
public final class DebugKt {
    @JvmField
    public static final boolean DEBUG;
    @NotNull
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";
    @JvmField
    public static final boolean RECOVER_STACK_TRACES;
    @NotNull
    public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        if (r0.equals(DEBUG_PROPERTY_VALUE_AUTO) != false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r0.equals(DEBUG_PROPERTY_VALUE_ON) != false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        if (r0.equals("") != false) goto L_0x0049;
     */
    static {
        /*
            java.lang.String r0 = "kotlinx.coroutines.debug"
            java.lang.String r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x000b
            goto L_0x0027
        L_0x000b:
            int r3 = r0.hashCode()
            if (r3 == 0) goto L_0x0041
            r4 = 3551(0xddf, float:4.976E-42)
            if (r3 == r4) goto L_0x0038
            r4 = 109935(0x1ad6f, float:1.54052E-40)
            if (r3 == r4) goto L_0x002e
            r4 = 3005871(0x2dddaf, float:4.212122E-39)
            if (r3 != r4) goto L_0x005c
            java.lang.String r3 = "auto"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x005c
        L_0x0027:
            java.lang.Class<kotlinx.coroutines.CoroutineId> r0 = kotlinx.coroutines.CoroutineId.class
            boolean r0 = r0.desiredAssertionStatus()
            goto L_0x004a
        L_0x002e:
            java.lang.String r3 = "off"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x005c
            r0 = 0
            goto L_0x004a
        L_0x0038:
            java.lang.String r3 = "on"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x005c
            goto L_0x0049
        L_0x0041:
            java.lang.String r3 = ""
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x005c
        L_0x0049:
            r0 = 1
        L_0x004a:
            DEBUG = r0
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = "kotlinx.coroutines.stacktrace.recovery"
            boolean r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0, r2)
            if (r0 == 0) goto L_0x0059
            r1 = 1
        L_0x0059:
            RECOVER_STACK_TRACES = r1
            return
        L_0x005c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "System property 'kotlinx.coroutines.debug' has unrecognized value '"
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
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DebugKt.<clinit>():void");
    }

    @NotNull
    public static final String getHexAddress(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$hexAddress");
        String hexString = Integer.toHexString(System.identityHashCode(obj));
        Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(System.identityHashCode(this))");
        return hexString;
    }

    @NotNull
    public static final String toDebugString(@NotNull Continuation<?> continuation) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(continuation, "$this$toDebugString");
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            Companion companion = Result.Companion;
            StringBuilder sb = new StringBuilder();
            sb.append(continuation);
            sb.append('@');
            sb.append(getHexAddress(continuation));
            obj = Result.m479constructorimpl(sb.toString());
        } catch (Throwable th) {
            Companion companion2 = Result.Companion;
            obj = Result.m479constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m482exceptionOrNullimpl(obj) != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(continuation.getClass().getName());
            sb2.append('@');
            sb2.append(getHexAddress(continuation));
            obj = sb2.toString();
        }
        return (String) obj;
    }

    @NotNull
    public static final String getClassSimpleName(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$classSimpleName");
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this::class.java.simpleName");
        return simpleName;
    }
}
