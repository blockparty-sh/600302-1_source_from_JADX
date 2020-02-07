package kotlinx.coroutines;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.Task;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000e\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00060\u0002j\u0002`\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0002\b\u0013J\u001f\u0010\u0014\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0010¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u001aJ\u0006\u0010\u001b\u001a\u00020\fJ\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u000eH ¢\u0006\u0002\b\u001dR\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX \u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, mo37405d2 = {"Lkotlinx/coroutines/DispatchedTask;", "T", "Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/SchedulerTask;", "resumeMode", "", "(I)V", "delegate", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "cancelResult", "", "state", "", "cause", "", "cancelResult$kotlinx_coroutines_core", "getExceptionalResult", "getExceptionalResult$kotlinx_coroutines_core", "getSuccessfulResult", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "handleFatalException", "exception", "finallyException", "handleFatalException$kotlinx_coroutines_core", "run", "takeState", "takeState$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Dispatched.kt */
public abstract class DispatchedTask<T> extends Task {
    @JvmField
    public int resumeMode;

    public void cancelResult$kotlinx_coroutines_core(@Nullable Object obj, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "cause");
    }

    @NotNull
    public abstract Continuation<T> getDelegate$kotlinx_coroutines_core();

    public <T> T getSuccessfulResult$kotlinx_coroutines_core(@Nullable Object obj) {
        return obj;
    }

    @Nullable
    public abstract Object takeState$kotlinx_coroutines_core();

    public DispatchedTask(int i) {
        this.resumeMode = i;
    }

    @Nullable
    public final Throwable getExceptionalResult$kotlinx_coroutines_core(@Nullable Object obj) {
        if (!(obj instanceof CompletedExceptionally)) {
            obj = null;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c2, code lost:
        r3 = kotlin.Result.Companion;
        r0 = kotlin.Result.m479constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e5, code lost:
        r2 = kotlin.Result.Companion;
        r0 = kotlin.Result.m479constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:32:0x00b2, B:39:0x00d5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r9 = this;
            kotlinx.coroutines.scheduling.TaskContext r0 = r9.taskContext
            r1 = 0
            r2 = r1
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlin.coroutines.Continuation r3 = r9.getDelegate$kotlinx_coroutines_core()     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            if (r3 == 0) goto L_0x00a9
            kotlinx.coroutines.DispatchedContinuation r3 = (kotlinx.coroutines.DispatchedContinuation) r3     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            kotlin.coroutines.Continuation<T> r4 = r3.continuation     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            kotlin.coroutines.CoroutineContext r5 = r4.getContext()     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            java.lang.Object r6 = r9.takeState$kotlinx_coroutines_core()     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            java.lang.Object r3 = r3.countOrElement     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            java.lang.Object r3 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r5, r3)     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            java.lang.Throwable r7 = r9.getExceptionalResult$kotlinx_coroutines_core(r6)     // Catch:{ all -> 0x00a4 }
            int r8 = r9.resumeMode     // Catch:{ all -> 0x00a4 }
            boolean r8 = kotlinx.coroutines.ResumeModeKt.isCancellableMode(r8)     // Catch:{ all -> 0x00a4 }
            if (r8 == 0) goto L_0x0034
            kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.Key     // Catch:{ all -> 0x00a4 }
            kotlin.coroutines.CoroutineContext$Key r1 = (kotlin.coroutines.CoroutineContext.C2916Key) r1     // Catch:{ all -> 0x00a4 }
            kotlin.coroutines.CoroutineContext$Element r1 = r5.get(r1)     // Catch:{ all -> 0x00a4 }
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1     // Catch:{ all -> 0x00a4 }
        L_0x0034:
            if (r7 != 0) goto L_0x005c
            if (r1 == 0) goto L_0x005c
            boolean r8 = r1.isActive()     // Catch:{ all -> 0x00a4 }
            if (r8 != 0) goto L_0x005c
            java.util.concurrent.CancellationException r1 = r1.getCancellationException()     // Catch:{ all -> 0x00a4 }
            r7 = r1
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch:{ all -> 0x00a4 }
            r9.cancelResult$kotlinx_coroutines_core(r6, r7)     // Catch:{ all -> 0x00a4 }
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x00a4 }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x00a4 }
            java.lang.Throwable r1 = kotlinx.coroutines.internal.StackTraceRecoveryKt.recoverStackTrace(r1, r4)     // Catch:{ all -> 0x00a4 }
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)     // Catch:{ all -> 0x00a4 }
            java.lang.Object r1 = kotlin.Result.m479constructorimpl(r1)     // Catch:{ all -> 0x00a4 }
            r4.resumeWith(r1)     // Catch:{ all -> 0x00a4 }
            goto L_0x007d
        L_0x005c:
            if (r7 == 0) goto L_0x0070
            kotlin.Result$Companion r1 = kotlin.Result.Companion     // Catch:{ all -> 0x00a4 }
            java.lang.Throwable r1 = kotlinx.coroutines.internal.StackTraceRecoveryKt.recoverStackTrace(r7, r4)     // Catch:{ all -> 0x00a4 }
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)     // Catch:{ all -> 0x00a4 }
            java.lang.Object r1 = kotlin.Result.m479constructorimpl(r1)     // Catch:{ all -> 0x00a4 }
            r4.resumeWith(r1)     // Catch:{ all -> 0x00a4 }
            goto L_0x007d
        L_0x0070:
            java.lang.Object r1 = r9.getSuccessfulResult$kotlinx_coroutines_core(r6)     // Catch:{ all -> 0x00a4 }
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x00a4 }
            java.lang.Object r1 = kotlin.Result.m479constructorimpl(r1)     // Catch:{ all -> 0x00a4 }
            r4.resumeWith(r1)     // Catch:{ all -> 0x00a4 }
        L_0x007d:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00a4 }
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r5, r3)     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            kotlin.Result$Companion r1 = kotlin.Result.Companion     // Catch:{ Throwable -> 0x0091 }
            r1 = r9
            kotlinx.coroutines.DispatchedTask r1 = (kotlinx.coroutines.DispatchedTask) r1     // Catch:{ Throwable -> 0x0091 }
            r0.afterTask()     // Catch:{ Throwable -> 0x0091 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0091 }
            java.lang.Object r0 = kotlin.Result.m479constructorimpl(r0)     // Catch:{ Throwable -> 0x0091 }
            goto L_0x009c
        L_0x0091:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m479constructorimpl(r0)
        L_0x009c:
            java.lang.Throwable r0 = kotlin.Result.m482exceptionOrNullimpl(r0)
            r9.handleFatalException$kotlinx_coroutines_core(r2, r0)
            goto L_0x00f6
        L_0x00a4:
            r1 = move-exception
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r5, r3)     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            throw r1     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
        L_0x00a9:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.DispatchedContinuation<T>"
            r1.<init>(r3)     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
            throw r1     // Catch:{ Throwable -> 0x00d4, all -> 0x00b1 }
        L_0x00b1:
            r1 = move-exception
            kotlin.Result$Companion r3 = kotlin.Result.Companion     // Catch:{ Throwable -> 0x00c1 }
            r3 = r9
            kotlinx.coroutines.DispatchedTask r3 = (kotlinx.coroutines.DispatchedTask) r3     // Catch:{ Throwable -> 0x00c1 }
            r0.afterTask()     // Catch:{ Throwable -> 0x00c1 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00c1 }
            java.lang.Object r0 = kotlin.Result.m479constructorimpl(r0)     // Catch:{ Throwable -> 0x00c1 }
            goto L_0x00cc
        L_0x00c1:
            r0 = move-exception
            kotlin.Result$Companion r3 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m479constructorimpl(r0)
        L_0x00cc:
            java.lang.Throwable r0 = kotlin.Result.m482exceptionOrNullimpl(r0)
            r9.handleFatalException$kotlinx_coroutines_core(r2, r0)
            throw r1
        L_0x00d4:
            r1 = move-exception
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ Throwable -> 0x00e4 }
            r2 = r9
            kotlinx.coroutines.DispatchedTask r2 = (kotlinx.coroutines.DispatchedTask) r2     // Catch:{ Throwable -> 0x00e4 }
            r0.afterTask()     // Catch:{ Throwable -> 0x00e4 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00e4 }
            java.lang.Object r0 = kotlin.Result.m479constructorimpl(r0)     // Catch:{ Throwable -> 0x00e4 }
            goto L_0x00ef
        L_0x00e4:
            r0 = move-exception
            kotlin.Result$Companion r2 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m479constructorimpl(r0)
        L_0x00ef:
            java.lang.Throwable r0 = kotlin.Result.m482exceptionOrNullimpl(r0)
            r9.handleFatalException$kotlinx_coroutines_core(r1, r0)
        L_0x00f6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DispatchedTask.run():void");
    }

    public final void handleFatalException$kotlinx_coroutines_core(@Nullable Throwable th, @Nullable Throwable th2) {
        if (th != null || th2 != null) {
            if (!(th == null || th2 == null)) {
                ExceptionsKt.addSuppressed(th, th2);
            }
            if (th == null) {
                th = th2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Fatal exception in coroutines machinery for ");
            sb.append(this);
            sb.append(". ");
            sb.append("Please read KDoc to 'handleFatalException' method and report this incident to maintainers");
            String sb2 = sb.toString();
            if (th == null) {
                Intrinsics.throwNpe();
            }
            CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), new CoroutinesInternalError(sb2, th));
        }
    }
}
