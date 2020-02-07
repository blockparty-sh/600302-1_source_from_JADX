package kotlinx.coroutines.scheduling;

import androidx.work.WorkRequest;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.TimeSourceKt;
import kotlinx.coroutines.internal.Symbol;
import org.bitcoinj.uri.BitcoinURI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\b\u0000\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0003UVWB+\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0006H\b¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u001c\u001a\u00020\u00192\n\u0010\u0016\u001a\u00060\u0014j\u0002`\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0006H\b¢\u0006\u0004\b\u001d\u0010\u000eJ\u0015\u0010\u001f\u001a\b\u0018\u00010\u001eR\u00020\u0000H\u0002¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u000fH\b¢\u0006\u0004\b!\u0010\u0011J\u0010\u0010\"\u001a\u00020\u0003H\b¢\u0006\u0004\b\"\u0010\u0013J-\u0010%\u001a\u00020\u000f2\n\u0010\u0016\u001a\u00060\u0014j\u0002`\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010$\u001a\u00020#¢\u0006\u0004\b%\u0010&J\u001b\u0010(\u001a\u00020\u000f2\n\u0010'\u001a\u00060\u0014j\u0002`\u0015H\u0016¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u000fH\b¢\u0006\u0004\b*\u0010\u0011J\u0010\u0010+\u001a\u00020\u0003H\b¢\u0006\u0004\b+\u0010\u0013J\u001b\u0010-\u001a\u00020\u00032\n\u0010,\u001a\u00060\u001eR\u00020\u0000H\u0002¢\u0006\u0004\b-\u0010.J\u0015\u0010/\u001a\b\u0018\u00010\u001eR\u00020\u0000H\u0002¢\u0006\u0004\b/\u0010 J\u001b\u00100\u001a\u00020\u000f2\n\u0010,\u001a\u00060\u001eR\u00020\u0000H\u0002¢\u0006\u0004\b0\u00101J+\u00104\u001a\u00020\u000f2\n\u0010,\u001a\u00060\u001eR\u00020\u00002\u0006\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u0003H\u0002¢\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\u000fH\u0002¢\u0006\u0004\b6\u0010\u0011J\u0017\u00108\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u0019H\u0002¢\u0006\u0004\b8\u00109J\u0015\u0010;\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u0006¢\u0006\u0004\b;\u0010<J\u001f\u0010=\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00192\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\b=\u0010>J\u000f\u0010?\u001a\u00020\bH\u0016¢\u0006\u0004\b?\u0010@J\u000f\u0010A\u001a\u00020#H\u0002¢\u0006\u0004\bA\u0010BR\u0017\u0010\r\u001a\u00020\u00038Â\u0002@\u0002X\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00038\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010DR\u0016\u0010F\u001a\u00020E8\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0017\u0010\u001d\u001a\u00020\u00038Â\u0002@\u0002X\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\u0013R\u0016\u0010J\u001a\u00020I8\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010LR\u0016\u0010M\u001a\u00020#8B@\u0002X\u0004¢\u0006\u0006\u001a\u0004\bM\u0010BR\u0016\u0010\u0005\u001a\u00020\u00038\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010DR\u0016\u0010O\u001a\u00020N8\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010QR\"\u0010S\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u001eR\u00020\u00000R8\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\bS\u0010T¨\u0006X"}, mo37405d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "Ljava/util/concurrent/Executor;", "Ljava/io/Closeable;", "", "corePoolSize", "maxPoolSize", "", "idleWorkerKeepAliveNs", "", "schedulerName", "<init>", "(IIJLjava/lang/String;)V", "state", "blockingWorkers", "(J)I", "", "close", "()V", "createNewWorker", "()I", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "Lkotlinx/coroutines/scheduling/TaskContext;", "taskContext", "Lkotlinx/coroutines/scheduling/Task;", "createTask$kotlinx_coroutines_core", "(Ljava/lang/Runnable;Lkotlinx/coroutines/scheduling/TaskContext;)Lkotlinx/coroutines/scheduling/Task;", "createTask", "createdWorkers", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "currentWorker", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "decrementBlockingWorkers", "decrementCreatedWorkers", "", "fair", "dispatch", "(Ljava/lang/Runnable;Lkotlinx/coroutines/scheduling/TaskContext;Z)V", "command", "execute", "(Ljava/lang/Runnable;)V", "incrementBlockingWorkers", "incrementCreatedWorkers", "worker", "parkedWorkersStackNextIndex", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)I", "parkedWorkersStackPop", "parkedWorkersStackPush", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)V", "oldIndex", "newIndex", "parkedWorkersStackTopUpdate", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;II)V", "requestCpuWorker", "task", "runSafely", "(Lkotlinx/coroutines/scheduling/Task;)V", "timeout", "shutdown", "(J)V", "submitToLocalQueue", "(Lkotlinx/coroutines/scheduling/Task;Z)I", "toString", "()Ljava/lang/String;", "tryUnpark", "()Z", "getBlockingWorkers", "I", "Ljava/util/concurrent/Semaphore;", "cpuPermits", "Ljava/util/concurrent/Semaphore;", "getCreatedWorkers", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "J", "isTerminated", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Ljava/lang/String;", "", "workers", "[Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "Companion", "Worker", "WorkerState", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CoroutineScheduler.kt */
public final class CoroutineScheduler implements Executor, Closeable {
    private static final int ADDED = -1;
    private static final int ADDED_REQUIRES_HELP = 0;
    private static final int ALLOWED = 0;
    private static final long BLOCKING_MASK = 4398044413952L;
    private static final int BLOCKING_SHIFT = 21;
    private static final long CREATED_MASK = 2097151;
    public static final Companion Companion = new Companion(null);
    private static final int FORBIDDEN = -1;
    /* access modifiers changed from: private */
    public static final int MAX_PARK_TIME_NS = ((int) TimeUnit.SECONDS.toNanos(1));
    /* access modifiers changed from: private */
    public static final int MAX_SPINS = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.spins", 1000, 1, 0, 8, (Object) null);
    public static final int MAX_SUPPORTED_POOL_SIZE = 2097150;
    /* access modifiers changed from: private */
    public static final int MAX_YIELDS = (MAX_SPINS + SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.yields", 0, 0, 0, 8, (Object) null));
    /* access modifiers changed from: private */
    public static final int MIN_PARK_TIME_NS = ((int) RangesKt.coerceAtMost(RangesKt.coerceAtLeast(TasksKt.WORK_STEALING_TIME_RESOLUTION_NS / ((long) 4), 10), (long) MAX_PARK_TIME_NS));
    public static final int MIN_SUPPORTED_POOL_SIZE = 1;
    private static final int NOT_ADDED = 1;
    /* access modifiers changed from: private */
    public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    private static final long PARKED_INDEX_MASK = 2097151;
    private static final long PARKED_VERSION_INC = 2097152;
    private static final long PARKED_VERSION_MASK = -2097152;
    private static final int TERMINATED = 1;
    private static final AtomicIntegerFieldUpdater _isTerminated$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");
    static final AtomicLongFieldUpdater controlState$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
    private static final AtomicLongFieldUpdater parkedWorkersStack$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");
    private volatile int _isTerminated;
    volatile long controlState;
    /* access modifiers changed from: private */
    public final int corePoolSize;
    /* access modifiers changed from: private */
    public final Semaphore cpuPermits;
    /* access modifiers changed from: private */
    public final GlobalQueue globalQueue;
    /* access modifiers changed from: private */
    public final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;
    private volatile long parkedWorkersStack;
    /* access modifiers changed from: private */
    public final Random random;
    /* access modifiers changed from: private */
    public final String schedulerName;
    /* access modifiers changed from: private */
    public final Worker[] workers;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\u00048\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u000e\u0010\u000e\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u00020\u00048\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0002R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001b"}, mo37405d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Companion;", "", "()V", "ADDED", "", "ADDED_REQUIRES_HELP", "ALLOWED", "BLOCKING_MASK", "", "BLOCKING_SHIFT", "CREATED_MASK", "FORBIDDEN", "MAX_PARK_TIME_NS", "MAX_PARK_TIME_NS$annotations", "MAX_SPINS", "MAX_SUPPORTED_POOL_SIZE", "MAX_YIELDS", "MIN_PARK_TIME_NS", "MIN_PARK_TIME_NS$annotations", "MIN_SUPPORTED_POOL_SIZE", "NOT_ADDED", "NOT_IN_STACK", "Lkotlinx/coroutines/internal/Symbol;", "PARKED_INDEX_MASK", "PARKED_VERSION_INC", "PARKED_VERSION_MASK", "TERMINATED", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CoroutineScheduler.kt */
    public static final class Companion {
        @JvmStatic
        private static /* synthetic */ void MAX_PARK_TIME_NS$annotations() {
        }

        @JvmStatic
        private static /* synthetic */ void MIN_PARK_TIME_NS$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WorkerState.values().length];

        static {
            $EnumSwitchMapping$0[WorkerState.PARKING.ordinal()] = 1;
            $EnumSwitchMapping$0[WorkerState.BLOCKING.ordinal()] = 2;
            $EnumSwitchMapping$0[WorkerState.CPU_ACQUIRED.ordinal()] = 3;
            $EnumSwitchMapping$0[WorkerState.RETIRING.ordinal()] = 4;
            $EnumSwitchMapping$0[WorkerState.TERMINATED.ordinal()] = 5;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0004\b\u001d\u0010\u001bJ\u0017\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001f\u0010\u000bJ\r\u0010 \u001a\u00020\t¢\u0006\u0004\b \u0010\u0014J\u0017\u0010$\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020\tH\u0016¢\u0006\u0004\b%\u0010\u0014J\r\u0010&\u001a\u00020\u0010¢\u0006\u0004\b&\u0010\u0012J\r\u0010'\u001a\u00020\u0010¢\u0006\u0004\b'\u0010\u0012J\u0017\u0010,\u001a\u00020\u00102\u0006\u0010)\u001a\u00020(H\u0000¢\u0006\u0004\b*\u0010+J\u0011\u0010-\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0004\b-\u0010\u001bJ\u000f\u0010.\u001a\u00020\tH\u0002¢\u0006\u0004\b.\u0010\u0014R*\u0010/\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00028\u0006@FX\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0013\u00105\u001a\u00020\u00108F@\u0006¢\u0006\u0006\u001a\u0004\b5\u0010\u0012R\u0013\u00106\u001a\u00020\u00108F@\u0006¢\u0006\u0006\u001a\u0004\b6\u0010\u0012R\u0016\u00107\u001a\u00020\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b9\u00100R\u0019\u0010;\u001a\u00020:8\u0006@\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R$\u0010@\u001a\u0004\u0018\u00010?8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bF\u00100R\u0016\u0010G\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u00100R\u0013\u0010K\u001a\u00020H8F@\u0006¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0016\u0010L\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bL\u00100R\"\u0010M\u001a\u00020(8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bS\u00108¨\u0006T"}, mo37405d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "Ljava/lang/Thread;", "", "index", "<init>", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;I)V", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;)V", "Lkotlinx/coroutines/scheduling/TaskMode;", "taskMode", "", "afterTask", "(Lkotlinx/coroutines/scheduling/TaskMode;)V", "", "taskSubmissionTime", "beforeTask", "(Lkotlinx/coroutines/scheduling/TaskMode;J)V", "", "blockingQuiescence", "()Z", "blockingWorkerIdle", "()V", "cpuWorkerIdle", "nanos", "doPark", "(J)Z", "Lkotlinx/coroutines/scheduling/Task;", "findTask$kotlinx_coroutines_core", "()Lkotlinx/coroutines/scheduling/Task;", "findTask", "findTaskWithCpuPermit", "mode", "idleReset", "idleResetBeforeUnpark", "upperBound", "nextInt$kotlinx_coroutines_core", "(I)I", "nextInt", "run", "tryAcquireCpuPermit", "tryForbidTermination", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "newState", "tryReleaseCpu$kotlinx_coroutines_core", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;)Z", "tryReleaseCpu", "trySteal", "tryTerminateWorker", "indexInArray", "I", "getIndexInArray", "()I", "setIndexInArray", "(I)V", "isBlocking", "isParking", "lastExhaustionTime", "J", "lastStealIndex", "Lkotlinx/coroutines/scheduling/WorkQueue;", "localQueue", "Lkotlinx/coroutines/scheduling/WorkQueue;", "getLocalQueue", "()Lkotlinx/coroutines/scheduling/WorkQueue;", "", "nextParkedWorker", "Ljava/lang/Object;", "getNextParkedWorker", "()Ljava/lang/Object;", "setNextParkedWorker", "(Ljava/lang/Object;)V", "parkTimeNs", "rngState", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "getScheduler", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "scheduler", "spins", "state", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "getState", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "setState", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;)V", "terminationDeadline", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CoroutineScheduler.kt */
    public final class Worker extends Thread {
        private static final AtomicIntegerFieldUpdater terminationState$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "terminationState");
        private volatile int indexInArray;
        private long lastExhaustionTime;
        private int lastStealIndex;
        @NotNull
        private final WorkQueue localQueue;
        @Nullable
        private volatile Object nextParkedWorker;
        private int parkTimeNs;
        private int rngState;
        private volatile int spins;
        @NotNull
        private volatile WorkerState state;
        private long terminationDeadline;
        private volatile int terminationState;

        private Worker() {
            setDaemon(true);
            this.localQueue = new WorkQueue();
            this.state = WorkerState.RETIRING;
            this.terminationState = 0;
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            this.parkTimeNs = CoroutineScheduler.MIN_PARK_TIME_NS;
            this.rngState = CoroutineScheduler.this.random.nextInt();
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final void setIndexInArray(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(CoroutineScheduler.this.schedulerName);
            sb.append("-worker-");
            sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
            setName(sb.toString());
            this.indexInArray = i;
        }

        public Worker(CoroutineScheduler coroutineScheduler, int i) {
            this();
            setIndexInArray(i);
        }

        @NotNull
        public final CoroutineScheduler getScheduler() {
            return CoroutineScheduler.this;
        }

        @NotNull
        public final WorkQueue getLocalQueue() {
            return this.localQueue;
        }

        @NotNull
        public final WorkerState getState() {
            return this.state;
        }

        public final void setState(@NotNull WorkerState workerState) {
            Intrinsics.checkParameterIsNotNull(workerState, "<set-?>");
            this.state = workerState;
        }

        public final boolean isParking() {
            return this.state == WorkerState.PARKING;
        }

        public final boolean isBlocking() {
            return this.state == WorkerState.BLOCKING;
        }

        @Nullable
        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final void setNextParkedWorker(@Nullable Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean tryForbidTermination() {
            int i = this.terminationState;
            if (i == -1) {
                return false;
            }
            if (i == 0) {
                return terminationState$FU.compareAndSet(this, 0, -1);
            }
            if (i == 1) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid terminationState = ");
            sb.append(i);
            throw new IllegalStateException(sb.toString().toString());
        }

        public final boolean tryAcquireCpuPermit() {
            if (this.state == WorkerState.CPU_ACQUIRED) {
                return true;
            }
            if (!CoroutineScheduler.this.cpuPermits.tryAcquire()) {
                return false;
            }
            this.state = WorkerState.CPU_ACQUIRED;
            return true;
        }

        public final boolean tryReleaseCpu$kotlinx_coroutines_core(@NotNull WorkerState workerState) {
            Intrinsics.checkParameterIsNotNull(workerState, "newState");
            WorkerState workerState2 = this.state;
            boolean z = workerState2 == WorkerState.CPU_ACQUIRED;
            if (z) {
                CoroutineScheduler.this.cpuPermits.release();
            }
            if (workerState2 != workerState) {
                this.state = workerState;
            }
            return z;
        }

        public void run() {
            boolean z = false;
            while (!CoroutineScheduler.this.isTerminated() && this.state != WorkerState.TERMINATED) {
                Task findTask$kotlinx_coroutines_core = findTask$kotlinx_coroutines_core();
                if (findTask$kotlinx_coroutines_core == null) {
                    if (this.state == WorkerState.CPU_ACQUIRED) {
                        cpuWorkerIdle();
                    } else {
                        blockingWorkerIdle();
                    }
                    z = true;
                } else {
                    TaskMode mode = findTask$kotlinx_coroutines_core.getMode();
                    if (z) {
                        idleReset(mode);
                        z = false;
                    }
                    beforeTask(mode, findTask$kotlinx_coroutines_core.submissionTime);
                    CoroutineScheduler.this.runSafely(findTask$kotlinx_coroutines_core);
                    afterTask(mode);
                }
            }
            tryReleaseCpu$kotlinx_coroutines_core(WorkerState.TERMINATED);
        }

        private final void beforeTask(TaskMode taskMode, long j) {
            if (taskMode != TaskMode.NON_BLOCKING) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, 2097152);
                if (tryReleaseCpu$kotlinx_coroutines_core(WorkerState.BLOCKING)) {
                    CoroutineScheduler.this.requestCpuWorker();
                }
            } else if (CoroutineScheduler.this.cpuPermits.availablePermits() != 0) {
                long nanoTime = TasksKt.schedulerTimeSource.nanoTime();
                if (nanoTime - j >= TasksKt.WORK_STEALING_TIME_RESOLUTION_NS && nanoTime - this.lastExhaustionTime >= TasksKt.WORK_STEALING_TIME_RESOLUTION_NS * ((long) 5)) {
                    this.lastExhaustionTime = nanoTime;
                    CoroutineScheduler.this.requestCpuWorker();
                }
            }
        }

        private final void afterTask(TaskMode taskMode) {
            if (taskMode != TaskMode.NON_BLOCKING) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, CoroutineScheduler.PARKED_VERSION_MASK);
                WorkerState workerState = this.state;
                if (workerState != WorkerState.TERMINATED) {
                    boolean z = workerState == WorkerState.BLOCKING;
                    if (!_Assertions.ENABLED || z) {
                        this.state = WorkerState.RETIRING;
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Expected BLOCKING state, but has ");
                    sb.append(workerState);
                    throw new AssertionError(sb.toString());
                }
            }
        }

        public final int nextInt$kotlinx_coroutines_core(int i) {
            int i2 = this.rngState;
            this.rngState = i2 ^ (i2 << 13);
            int i3 = this.rngState;
            this.rngState = i3 ^ (i3 >> 17);
            int i4 = this.rngState;
            this.rngState = i4 ^ (i4 << 5);
            int i5 = i - 1;
            if ((i5 & i) == 0) {
                return this.rngState & i5;
            }
            return (this.rngState & Integer.MAX_VALUE) % i;
        }

        private final void cpuWorkerIdle() {
            int i = this.spins;
            if (i <= CoroutineScheduler.MAX_YIELDS) {
                this.spins = i + 1;
                if (i >= CoroutineScheduler.MAX_SPINS) {
                    Thread.yield();
                    return;
                }
                return;
            }
            if (this.parkTimeNs < CoroutineScheduler.MAX_PARK_TIME_NS) {
                this.parkTimeNs = RangesKt.coerceAtMost((this.parkTimeNs * 3) >>> 1, CoroutineScheduler.MAX_PARK_TIME_NS);
            }
            tryReleaseCpu$kotlinx_coroutines_core(WorkerState.PARKING);
            doPark((long) this.parkTimeNs);
        }

        private final void blockingWorkerIdle() {
            tryReleaseCpu$kotlinx_coroutines_core(WorkerState.PARKING);
            if (blockingQuiescence()) {
                this.terminationState = 0;
                if (this.terminationDeadline == 0) {
                    this.terminationDeadline = System.nanoTime() + CoroutineScheduler.this.idleWorkerKeepAliveNs;
                }
                if (doPark(CoroutineScheduler.this.idleWorkerKeepAliveNs) && System.nanoTime() - this.terminationDeadline >= 0) {
                    this.terminationDeadline = 0;
                    tryTerminateWorker();
                }
            }
        }

        private final boolean doPark(long j) {
            CoroutineScheduler.this.parkedWorkersStackPush(this);
            if (!blockingQuiescence()) {
                return false;
            }
            LockSupport.parkNanos(j);
            return true;
        }

        private final void tryTerminateWorker() {
            synchronized (CoroutineScheduler.this.workers) {
                if (!CoroutineScheduler.this.isTerminated()) {
                    if (CoroutineScheduler.this.getCreatedWorkers() > CoroutineScheduler.this.corePoolSize) {
                        if (blockingQuiescence()) {
                            if (terminationState$FU.compareAndSet(this, 0, 1)) {
                                int i = this.indexInArray;
                                setIndexInArray(0);
                                CoroutineScheduler.this.parkedWorkersStackTopUpdate(this, i, 0);
                                int andDecrement = (int) (CoroutineScheduler.controlState$FU.getAndDecrement(CoroutineScheduler.this) & 2097151);
                                if (andDecrement != i) {
                                    Worker worker = CoroutineScheduler.this.workers[andDecrement];
                                    if (worker == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    CoroutineScheduler.this.workers[i] = worker;
                                    worker.setIndexInArray(i);
                                    CoroutineScheduler.this.parkedWorkersStackTopUpdate(worker, andDecrement, i);
                                }
                                CoroutineScheduler.this.workers[andDecrement] = null;
                                Unit unit = Unit.INSTANCE;
                                this.state = WorkerState.TERMINATED;
                            }
                        }
                    }
                }
            }
        }

        private final boolean blockingQuiescence() {
            Task removeFirstWithModeOrNull = CoroutineScheduler.this.globalQueue.removeFirstWithModeOrNull(TaskMode.PROBABLY_BLOCKING);
            if (removeFirstWithModeOrNull == null) {
                return true;
            }
            this.localQueue.add(removeFirstWithModeOrNull, CoroutineScheduler.this.globalQueue);
            return false;
        }

        private final void idleReset(TaskMode taskMode) {
            this.terminationDeadline = 0;
            this.lastStealIndex = 0;
            if (this.state == WorkerState.PARKING) {
                boolean z = taskMode == TaskMode.PROBABLY_BLOCKING;
                if (!_Assertions.ENABLED || z) {
                    this.state = WorkerState.BLOCKING;
                    this.parkTimeNs = CoroutineScheduler.MIN_PARK_TIME_NS;
                } else {
                    throw new AssertionError("Assertion failed");
                }
            }
            this.spins = 0;
        }

        public final void idleResetBeforeUnpark() {
            this.parkTimeNs = CoroutineScheduler.MIN_PARK_TIME_NS;
            this.spins = 0;
        }

        @Nullable
        public final Task findTask$kotlinx_coroutines_core() {
            if (tryAcquireCpuPermit()) {
                return findTaskWithCpuPermit();
            }
            Task poll = this.localQueue.poll();
            if (poll == null) {
                poll = CoroutineScheduler.this.globalQueue.removeFirstWithModeOrNull(TaskMode.PROBABLY_BLOCKING);
            }
            return poll;
        }

        private final Task findTaskWithCpuPermit() {
            boolean z = nextInt$kotlinx_coroutines_core(CoroutineScheduler.this.corePoolSize * 2) == 0;
            if (z) {
                Task removeFirstWithModeOrNull = CoroutineScheduler.this.globalQueue.removeFirstWithModeOrNull(TaskMode.NON_BLOCKING);
                if (removeFirstWithModeOrNull != null) {
                    return removeFirstWithModeOrNull;
                }
            }
            Task poll = this.localQueue.poll();
            if (poll != null) {
                return poll;
            }
            if (!z) {
                Task task = (Task) CoroutineScheduler.this.globalQueue.removeFirstOrNull();
                if (task != null) {
                    return task;
                }
            }
            return trySteal();
        }

        private final Task trySteal() {
            int access$getCreatedWorkers$p = CoroutineScheduler.this.getCreatedWorkers();
            if (access$getCreatedWorkers$p < 2) {
                return null;
            }
            int i = this.lastStealIndex;
            if (i == 0) {
                i = nextInt$kotlinx_coroutines_core(access$getCreatedWorkers$p);
            }
            int i2 = i + 1;
            if (i2 > access$getCreatedWorkers$p) {
                i2 = 1;
            }
            this.lastStealIndex = i2;
            Worker worker = CoroutineScheduler.this.workers[i2];
            if (worker == null || worker == this || !this.localQueue.trySteal(worker.localQueue, CoroutineScheduler.this.globalQueue)) {
                return null;
            }
            return this.localQueue.poll();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, mo37405d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "", "(Ljava/lang/String;I)V", "CPU_ACQUIRED", "BLOCKING", "PARKING", "RETIRING", "TERMINATED", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CoroutineScheduler.kt */
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        RETIRING,
        TERMINATED
    }

    private final int blockingWorkers(long j) {
        return (int) ((j & BLOCKING_MASK) >> 21);
    }

    /* access modifiers changed from: private */
    public final int createdWorkers(long j) {
        return (int) (j & 2097151);
    }

    public CoroutineScheduler(int i, int i2, long j, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "schedulerName");
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        if (this.corePoolSize >= 1) {
            String str2 = "Max pool size ";
            if (this.maxPoolSize >= this.corePoolSize) {
                if (this.maxPoolSize <= 2097150) {
                    if (this.idleWorkerKeepAliveNs > 0) {
                        this.globalQueue = new GlobalQueue();
                        this.cpuPermits = new Semaphore(this.corePoolSize, false);
                        this.parkedWorkersStack = 0;
                        this.workers = new Worker[(this.maxPoolSize + 1)];
                        this.controlState = 0;
                        this.random = new Random();
                        this._isTerminated = 0;
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Idle worker keep alive time ");
                    sb.append(this.idleWorkerKeepAliveNs);
                    sb.append(" must be positive");
                    throw new IllegalArgumentException(sb.toString().toString());
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(this.maxPoolSize);
                sb2.append(" should not exceed maximal supported number of threads 2097150");
                throw new IllegalArgumentException(sb2.toString().toString());
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append(this.maxPoolSize);
            sb3.append(" should be greater than or equals to core pool size ");
            sb3.append(this.corePoolSize);
            throw new IllegalArgumentException(sb3.toString().toString());
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Core pool size ");
        sb4.append(this.corePoolSize);
        sb4.append(" should be at least 1");
        throw new IllegalArgumentException(sb4.toString().toString());
    }

    public /* synthetic */ CoroutineScheduler(int i, int i2, long j, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i3 & 4) != 0) {
            j = TasksKt.IDLE_WORKER_KEEP_ALIVE_NS;
        }
        long j2 = j;
        if ((i3 & 8) != 0) {
            str = TasksKt.DEFAULT_SCHEDULER_NAME;
        }
        this(i, i2, j2, str);
    }

    /* access modifiers changed from: private */
    public final void parkedWorkersStackPush(Worker worker) {
        long j;
        long j2;
        int indexInArray;
        if (worker.getNextParkedWorker() == NOT_IN_STACK) {
            do {
                j = this.parkedWorkersStack;
                int i = (int) (2097151 & j);
                j2 = (2097152 + j) & PARKED_VERSION_MASK;
                indexInArray = worker.getIndexInArray();
                boolean z = indexInArray != 0;
                if (!_Assertions.ENABLED || z) {
                    worker.setNextParkedWorker(this.workers[i]);
                } else {
                    throw new AssertionError("Assertion failed");
                }
            } while (!parkedWorkersStack$FU.compareAndSet(this, j, ((long) indexInArray) | j2));
        }
    }

    private final int parkedWorkersStackNextIndex(Worker worker) {
        Object nextParkedWorker = worker.getNextParkedWorker();
        while (nextParkedWorker != NOT_IN_STACK) {
            if (nextParkedWorker == null) {
                return 0;
            }
            Worker worker2 = (Worker) nextParkedWorker;
            int indexInArray = worker2.getIndexInArray();
            if (indexInArray != 0) {
                return indexInArray;
            }
            nextParkedWorker = worker2.getNextParkedWorker();
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public final int getCreatedWorkers() {
        return (int) (this.controlState & 2097151);
    }

    private final int getBlockingWorkers() {
        return (int) ((this.controlState & BLOCKING_MASK) >> 21);
    }

    private final int incrementCreatedWorkers() {
        return (int) (controlState$FU.incrementAndGet(this) & 2097151);
    }

    /* access modifiers changed from: private */
    public final int decrementCreatedWorkers() {
        return (int) (controlState$FU.getAndDecrement(this) & 2097151);
    }

    /* access modifiers changed from: private */
    public final void incrementBlockingWorkers() {
        controlState$FU.addAndGet(this, 2097152);
    }

    /* access modifiers changed from: private */
    public final void decrementBlockingWorkers() {
        controlState$FU.addAndGet(this, PARKED_VERSION_MASK);
    }

    /* access modifiers changed from: private */
    public final boolean isTerminated() {
        return this._isTerminated != 0;
    }

    public void execute(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "command");
        dispatch$default(this, runnable, null, false, 6, null);
    }

    public void close() {
        shutdown(WorkRequest.MIN_BACKOFF_MILLIS);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007c, code lost:
        if (r9 != null) goto L_0x0087;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void shutdown(long r9) {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = _isTerminated$FU
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r0 = r8.currentWorker()
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker[] r3 = r8.workers
            monitor-enter(r3)
            long r4 = r8.controlState     // Catch:{ all -> 0x00b7 }
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r5 = (int) r4
            monitor-exit(r3)
            if (r2 > r5) goto L_0x0071
            r3 = 1
        L_0x001d:
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker[] r4 = r8.workers
            r4 = r4[r3]
            if (r4 != 0) goto L_0x0026
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0026:
            if (r4 == r0) goto L_0x006c
        L_0x0028:
            boolean r6 = r4.isAlive()
            if (r6 == 0) goto L_0x0038
            r6 = r4
            java.lang.Thread r6 = (java.lang.Thread) r6
            java.util.concurrent.locks.LockSupport.unpark(r6)
            r4.join(r9)
            goto L_0x0028
        L_0x0038:
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r6 = r4.getState()
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r7 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.TERMINATED
            if (r6 != r7) goto L_0x0042
            r7 = 1
            goto L_0x0043
        L_0x0042:
            r7 = 0
        L_0x0043:
            if (r7 == 0) goto L_0x004f
            kotlinx.coroutines.scheduling.WorkQueue r4 = r4.getLocalQueue()
            kotlinx.coroutines.scheduling.GlobalQueue r6 = r8.globalQueue
            r4.offloadAllWork$kotlinx_coroutines_core(r6)
            goto L_0x006c
        L_0x004f:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Expected TERMINATED state, but found "
            r9.append(r10)
            r9.append(r6)
            java.lang.String r9 = r9.toString()
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r9 = r9.toString()
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        L_0x006c:
            if (r3 == r5) goto L_0x0071
            int r3 = r3 + 1
            goto L_0x001d
        L_0x0071:
            kotlinx.coroutines.scheduling.GlobalQueue r9 = r8.globalQueue
            r9.close()
        L_0x0076:
            if (r0 == 0) goto L_0x007f
            kotlinx.coroutines.scheduling.Task r9 = r0.findTask$kotlinx_coroutines_core()
            if (r9 == 0) goto L_0x007f
            goto L_0x0087
        L_0x007f:
            kotlinx.coroutines.scheduling.GlobalQueue r9 = r8.globalQueue
            java.lang.Object r9 = r9.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r9 = (kotlinx.coroutines.scheduling.Task) r9
        L_0x0087:
            if (r9 == 0) goto L_0x008d
            r8.runSafely(r9)
            goto L_0x0076
        L_0x008d:
            if (r0 == 0) goto L_0x0094
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r9 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.TERMINATED
            r0.tryReleaseCpu$kotlinx_coroutines_core(r9)
        L_0x0094:
            java.util.concurrent.Semaphore r9 = r8.cpuPermits
            int r9 = r9.availablePermits()
            int r10 = r8.corePoolSize
            if (r9 != r10) goto L_0x009f
            r1 = 1
        L_0x009f:
            boolean r9 = kotlin._Assertions.ENABLED
            if (r9 == 0) goto L_0x00b0
            if (r1 == 0) goto L_0x00a6
            goto L_0x00b0
        L_0x00a6:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            java.lang.String r10 = "Assertion failed"
            r9.<init>(r10)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        L_0x00b0:
            r9 = 0
            r8.parkedWorkersStack = r9
            r8.controlState = r9
            return
        L_0x00b7:
            r9 = move-exception
            monitor-exit(r3)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.shutdown(long):void");
    }

    public static /* synthetic */ void dispatch$default(CoroutineScheduler coroutineScheduler, Runnable runnable, TaskContext taskContext, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            taskContext = NonBlockingContext.INSTANCE;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        coroutineScheduler.dispatch(runnable, taskContext, z);
    }

    public final void dispatch(@NotNull Runnable runnable, @NotNull TaskContext taskContext, boolean z) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        Intrinsics.checkParameterIsNotNull(taskContext, "taskContext");
        TimeSourceKt.getTimeSource().trackTask();
        Task createTask$kotlinx_coroutines_core = createTask$kotlinx_coroutines_core(runnable, taskContext);
        int submitToLocalQueue = submitToLocalQueue(createTask$kotlinx_coroutines_core, z);
        if (submitToLocalQueue != -1) {
            if (submitToLocalQueue != 1) {
                requestCpuWorker();
            } else if (this.globalQueue.addLast(createTask$kotlinx_coroutines_core)) {
                requestCpuWorker();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(this.schedulerName);
                sb.append(" was terminated");
                throw new RejectedExecutionException(sb.toString());
            }
        }
    }

    @NotNull
    public final Task createTask$kotlinx_coroutines_core(@NotNull Runnable runnable, @NotNull TaskContext taskContext) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        Intrinsics.checkParameterIsNotNull(taskContext, "taskContext");
        long nanoTime = TasksKt.schedulerTimeSource.nanoTime();
        if (!(runnable instanceof Task)) {
            return new TaskImpl(runnable, nanoTime, taskContext);
        }
        Task task = (Task) runnable;
        task.submissionTime = nanoTime;
        task.taskContext = taskContext;
        return task;
    }

    /* access modifiers changed from: private */
    public final void requestCpuWorker() {
        if (this.cpuPermits.availablePermits() == 0) {
            tryUnpark();
        } else if (!tryUnpark()) {
            long j = this.controlState;
            if (((int) (2097151 & j)) - ((int) ((j & BLOCKING_MASK) >> 21)) < this.corePoolSize) {
                int createNewWorker = createNewWorker();
                if (createNewWorker == 1 && this.corePoolSize > 1) {
                    createNewWorker();
                }
                if (createNewWorker > 0) {
                    return;
                }
            }
            tryUnpark();
        }
    }

    private final boolean tryUnpark() {
        while (true) {
            Worker parkedWorkersStackPop = parkedWorkersStackPop();
            if (parkedWorkersStackPop == null) {
                return false;
            }
            parkedWorkersStackPop.idleResetBeforeUnpark();
            boolean isParking = parkedWorkersStackPop.isParking();
            LockSupport.unpark(parkedWorkersStackPop);
            if (isParking && parkedWorkersStackPop.tryForbidTermination()) {
                return true;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0080, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int createNewWorker() {
        /*
            r10 = this;
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker[] r0 = r10.workers
            monitor-enter(r0)
            boolean r1 = r10.isTerminated()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x000c
            r1 = -1
            monitor-exit(r0)
            return r1
        L_0x000c:
            long r1 = r10.controlState     // Catch:{ all -> 0x0081 }
            r3 = 2097151(0x1fffff, double:1.0361303E-317)
            long r5 = r1 & r3
            int r6 = (int) r5     // Catch:{ all -> 0x0081 }
            r7 = 4398044413952(0x3ffffe00000, double:2.1729226538177E-311)
            long r1 = r1 & r7
            r5 = 21
            long r1 = r1 >> r5
            int r2 = (int) r1     // Catch:{ all -> 0x0081 }
            int r1 = r6 - r2
            int r2 = r10.corePoolSize     // Catch:{ all -> 0x0081 }
            r5 = 0
            if (r1 < r2) goto L_0x0027
            monitor-exit(r0)
            return r5
        L_0x0027:
            int r2 = r10.maxPoolSize     // Catch:{ all -> 0x0081 }
            if (r6 >= r2) goto L_0x007f
            java.util.concurrent.Semaphore r2 = r10.cpuPermits     // Catch:{ all -> 0x0081 }
            int r2 = r2.availablePermits()     // Catch:{ all -> 0x0081 }
            if (r2 != 0) goto L_0x0034
            goto L_0x007f
        L_0x0034:
            long r6 = r10.controlState     // Catch:{ all -> 0x0081 }
            long r6 = r6 & r3
            int r2 = (int) r6     // Catch:{ all -> 0x0081 }
            r6 = 1
            int r2 = r2 + r6
            if (r2 <= 0) goto L_0x0044
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker[] r7 = r10.workers     // Catch:{ all -> 0x0081 }
            r7 = r7[r2]     // Catch:{ all -> 0x0081 }
            if (r7 != 0) goto L_0x0044
            r7 = 1
            goto L_0x0045
        L_0x0044:
            r7 = 0
        L_0x0045:
            if (r7 == 0) goto L_0x0071
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r7 = new kotlinx.coroutines.scheduling.CoroutineScheduler$Worker     // Catch:{ all -> 0x0081 }
            r7.<init>(r10, r2)     // Catch:{ all -> 0x0081 }
            r7.start()     // Catch:{ all -> 0x0081 }
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = controlState$FU     // Catch:{ all -> 0x0081 }
            long r8 = r8.incrementAndGet(r10)     // Catch:{ all -> 0x0081 }
            long r3 = r3 & r8
            int r4 = (int) r3     // Catch:{ all -> 0x0081 }
            if (r2 != r4) goto L_0x005a
            r5 = 1
        L_0x005a:
            if (r5 == 0) goto L_0x0063
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker[] r3 = r10.workers     // Catch:{ all -> 0x0081 }
            r3[r2] = r7     // Catch:{ all -> 0x0081 }
            int r1 = r1 + r6
            monitor-exit(r0)
            return r1
        L_0x0063:
            java.lang.String r1 = "Failed requirement."
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0081 }
            r2.<init>(r1)     // Catch:{ all -> 0x0081 }
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x0081 }
            throw r2     // Catch:{ all -> 0x0081 }
        L_0x0071:
            java.lang.String r1 = "Failed requirement."
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0081 }
            r2.<init>(r1)     // Catch:{ all -> 0x0081 }
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x0081 }
            throw r2     // Catch:{ all -> 0x0081 }
        L_0x007f:
            monitor-exit(r0)
            return r5
        L_0x0081:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.createNewWorker():int");
    }

    private final int submitToLocalQueue(Task task, boolean z) {
        boolean z2;
        Worker currentWorker = currentWorker();
        if (currentWorker == null || currentWorker.getState() == WorkerState.TERMINATED) {
            return 1;
        }
        int i = -1;
        if (task.getMode() == TaskMode.NON_BLOCKING) {
            if (currentWorker.isBlocking()) {
                i = 0;
            } else if (!currentWorker.tryAcquireCpuPermit()) {
                return 1;
            }
        }
        if (z) {
            z2 = currentWorker.getLocalQueue().addLast(task, this.globalQueue);
        } else {
            z2 = currentWorker.getLocalQueue().add(task, this.globalQueue);
        }
        if (!z2 || currentWorker.getLocalQueue().getBufferSize$kotlinx_coroutines_core() > TasksKt.QUEUE_SIZE_OFFLOAD_THRESHOLD) {
            return 0;
        }
        return i;
    }

    private final Worker currentWorker() {
        Thread currentThread = Thread.currentThread();
        if (!(currentThread instanceof Worker)) {
            currentThread = null;
        }
        Worker worker = (Worker) currentThread;
        if (worker == null || !Intrinsics.areEqual((Object) worker.getScheduler(), (Object) this)) {
            return null;
        }
        return worker;
    }

    @NotNull
    public String toString() {
        Worker[] workerArr;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (Worker worker : this.workers) {
            if (worker != null) {
                int size$kotlinx_coroutines_core = worker.getLocalQueue().size$kotlinx_coroutines_core();
                int i6 = WhenMappings.$EnumSwitchMapping$0[worker.getState().ordinal()];
                if (i6 == 1) {
                    i3++;
                } else if (i6 == 2) {
                    i2++;
                    Collection collection = arrayList;
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.valueOf(size$kotlinx_coroutines_core));
                    sb.append("b");
                    collection.add(sb.toString());
                } else if (i6 == 3) {
                    i++;
                    Collection collection2 = arrayList;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(String.valueOf(size$kotlinx_coroutines_core));
                    sb2.append("c");
                    collection2.add(sb2.toString());
                } else if (i6 == 4) {
                    i4++;
                    if (size$kotlinx_coroutines_core > 0) {
                        Collection collection3 = arrayList;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(String.valueOf(size$kotlinx_coroutines_core));
                        sb3.append(BitcoinURI.FIELD_PAYMENT_REQUEST_URL);
                        collection3.add(sb3.toString());
                    }
                } else if (i6 == 5) {
                    i5++;
                }
            }
        }
        long j = this.controlState;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.schedulerName);
        sb4.append('@');
        sb4.append(DebugKt.getHexAddress(this));
        sb4.append('[');
        sb4.append("Pool Size {");
        sb4.append("core = ");
        sb4.append(this.corePoolSize);
        String str = ", ";
        sb4.append(str);
        sb4.append("max = ");
        sb4.append(this.maxPoolSize);
        String str2 = "}, ";
        sb4.append(str2);
        sb4.append("Worker States {");
        sb4.append("CPU = ");
        sb4.append(i);
        sb4.append(str);
        String str3 = "blocking = ";
        sb4.append(str3);
        sb4.append(i2);
        sb4.append(str);
        sb4.append("parked = ");
        sb4.append(i3);
        sb4.append(str);
        sb4.append("retired = ");
        sb4.append(i4);
        sb4.append(str);
        sb4.append("terminated = ");
        sb4.append(i5);
        sb4.append(str2);
        sb4.append("running workers queues = ");
        sb4.append(arrayList);
        sb4.append(str);
        sb4.append("global queue size = ");
        sb4.append(this.globalQueue.getSize());
        sb4.append(str);
        sb4.append("Control State Workers {");
        sb4.append("created = ");
        sb4.append((int) (2097151 & j));
        sb4.append(str);
        sb4.append(str3);
        sb4.append((int) ((j & BLOCKING_MASK) >> 21));
        sb4.append('}');
        sb4.append("]");
        return sb4.toString();
    }

    /* access modifiers changed from: private */
    public final void runSafely(Task task) {
        try {
            task.run();
        } catch (Throwable th) {
            TimeSourceKt.getTimeSource().unTrackTask();
            throw th;
        }
        TimeSourceKt.getTimeSource().unTrackTask();
    }

    /* access modifiers changed from: private */
    public final void parkedWorkersStackTopUpdate(Worker worker, int i, int i2) {
        while (true) {
            long j = this.parkedWorkersStack;
            int i3 = (int) (2097151 & j);
            long j2 = (2097152 + j) & PARKED_VERSION_MASK;
            int i4 = i3 == i ? i2 == 0 ? parkedWorkersStackNextIndex(worker) : i2 : i3;
            if (i4 >= 0) {
                if (parkedWorkersStack$FU.compareAndSet(this, j, j2 | ((long) i4))) {
                    return;
                }
            }
        }
    }

    private final Worker parkedWorkersStackPop() {
        while (true) {
            long j = this.parkedWorkersStack;
            Worker worker = this.workers[(int) (2097151 & j)];
            if (worker == null) {
                return null;
            }
            long j2 = (2097152 + j) & PARKED_VERSION_MASK;
            int parkedWorkersStackNextIndex = parkedWorkersStackNextIndex(worker);
            if (parkedWorkersStackNextIndex >= 0) {
                if (parkedWorkersStack$FU.compareAndSet(this, j, ((long) parkedWorkersStackNextIndex) | j2)) {
                    worker.setNextParkedWorker(NOT_IN_STACK);
                    return worker;
                }
            }
        }
    }
}
