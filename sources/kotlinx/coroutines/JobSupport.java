package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.leanplum.internal.Constants.Kinds;
import com.leanplum.internal.Constants.Params;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006Ð\u0001Ñ\u0001Ò\u0001B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ+\u0010\u000f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u001b\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0002¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010#\u001a\u0004\u0018\u00010\tH@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\u0004\u0018\u00010\tH@ø\u0001\u0000¢\u0006\u0004\b$\u0010\"J\u0019\u0010&\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0004\b&\u0010'J\u001f\u0010&\u001a\u00020\u00152\u000e\u0010%\u001a\n\u0018\u00010(j\u0004\u0018\u0001`)H\u0016¢\u0006\u0004\b&\u0010*J\u0017\u0010+\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b+\u0010'J\u0019\u0010.\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\b,\u0010-J\u0019\u0010/\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b/\u0010'J\u0019\u00100\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b0\u0010-J\u0017\u00101\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0011H\u0002¢\u0006\u0004\b1\u0010'J\u0017\u00102\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0011H\u0016¢\u0006\u0004\b2\u0010'J)\u00105\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b5\u00106J)\u0010;\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u0002072\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b;\u0010<J\u0019\u0010=\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b=\u0010>J\u000f\u0010@\u001a\u00020?H\u0002¢\u0006\u0004\b@\u0010AJ\u0019\u0010B\u001a\u0004\u0018\u0001082\u0006\u0010\u0018\u001a\u000203H\u0002¢\u0006\u0004\bB\u0010CJ\u0011\u0010D\u001a\u00060(j\u0002`)¢\u0006\u0004\bD\u0010EJ\u0013\u0010F\u001a\u00060(j\u0002`)H\u0016¢\u0006\u0004\bF\u0010EJ\u0011\u0010I\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\bG\u0010HJ\u000f\u0010J\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\bJ\u0010KJ'\u0010L\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0018\u001a\u0002072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002¢\u0006\u0004\bL\u0010MJ\u0019\u0010N\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u000203H\u0002¢\u0006\u0004\bN\u0010OJ\u0017\u0010Q\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0011H\u0014¢\u0006\u0004\bQ\u0010'J\u0017\u0010T\u001a\u00020\u00152\u0006\u0010P\u001a\u00020\u0011H\u0010¢\u0006\u0004\bR\u0010SJ\u0019\u0010X\u001a\u00020\u00152\b\u0010U\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0004\bV\u0010WJF\u0010a\u001a\u00020`2\u0006\u0010Y\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\u00052'\u0010_\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u00150[j\u0002`^¢\u0006\u0004\ba\u0010bJ6\u0010a\u001a\u00020`2'\u0010_\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u00150[j\u0002`^¢\u0006\u0004\ba\u0010cJ\u0013\u0010d\u001a\u00020\u0015H@ø\u0001\u0000¢\u0006\u0004\bd\u0010\"J\u000f\u0010e\u001a\u00020\u0005H\u0002¢\u0006\u0004\be\u0010fJ\u0013\u0010g\u001a\u00020\u0015H@ø\u0001\u0000¢\u0006\u0004\bg\u0010\"J&\u0010j\u001a\u00020i2\u0014\u0010h\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00150[H\b¢\u0006\u0004\bj\u0010kJ\u0019\u0010l\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\bl\u0010-J\u0019\u0010n\u001a\u00020\u00052\b\u0010:\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\bm\u0010-J!\u0010q\u001a\u00020\u00052\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0000¢\u0006\u0004\bo\u0010pJD\u0010r\u001a\u0006\u0012\u0002\b\u00030\r2'\u0010_\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u00150[j\u0002`^2\u0006\u0010Y\u001a\u00020\u0005H\u0002¢\u0006\u0004\br\u0010sJ\u000f\u0010w\u001a\u00020tH\u0010¢\u0006\u0004\bu\u0010vJ\u001f\u0010x\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u0011H\u0002¢\u0006\u0004\bx\u0010yJ2\u0010{\u001a\u00020\u0015\"\u000e\b\u0000\u0010z\u0018\u0001*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010\u0011H\b¢\u0006\u0004\b{\u0010yJ\u0019\u0010Y\u001a\u00020\u00152\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0014¢\u0006\u0004\bY\u0010SJ\u0019\u0010|\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0014¢\u0006\u0004\b|\u0010}J\u0010\u0010\u0001\u001a\u00020\u0015H\u0010¢\u0006\u0004\b~\u0010J\u0019\u0010\u0001\u001a\u00020\u00152\u0007\u0010\u0001\u001a\u00020\u0003¢\u0006\u0006\b\u0001\u0010\u0001J\u001b\u0010\u0001\u001a\u00020\u00152\u0007\u0010\u0018\u001a\u00030\u0001H\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u001e\u0010\u0001\u001a\u00020\u00152\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0006\b\u0001\u0010\u0001JI\u0010\u0001\u001a\u00020\u0015\"\u0005\b\u0000\u0010\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u00012\u001d\u0010h\u001a\u0019\b\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t0[ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001JX\u0010\u0001\u001a\u00020\u0015\"\u0004\b\u0000\u0010z\"\u0005\b\u0001\u0010\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u00012$\u0010h\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u001e\u0010\u0001\u001a\u00020\u00152\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0000¢\u0006\u0006\b\u0001\u0010\u0001JX\u0010\u0001\u001a\u00020\u0015\"\u0004\b\u0000\u0010z\"\u0005\b\u0001\u0010\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u00012$\u0010h\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u000f\u0010\u0001\u001a\u00020\u0005¢\u0006\u0005\b\u0001\u0010fJ\u001c\u0010\u0001\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u001c\u0010\u0001\u001a\u00020t2\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u0011\u0010\u0001\u001a\u00020tH\u0007¢\u0006\u0005\b\u0001\u0010vJ\u0011\u0010\u0001\u001a\u00020tH\u0016¢\u0006\u0005\b\u0001\u0010vJ,\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b\u0001\u0010\u0001J,\u0010 \u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b \u0001\u0010¡\u0001J\"\u0010¢\u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002032\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J.\u0010¤\u0001\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\t2\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J,\u0010¦\u0001\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u0002032\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b¦\u0001\u0010§\u0001J-\u0010¨\u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002072\u0006\u0010\u001d\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\tH\u0010¢\u0006\u0006\b¨\u0001\u0010©\u0001J\u0019\u0010«\u0001\u001a\u0004\u0018\u000108*\u00030ª\u0001H\u0002¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u001f\u0010­\u0001\u001a\u00020\u0015*\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0005\b­\u0001\u0010yJ'\u0010¯\u0001\u001a\u00060(j\u0002`)*\u00020\u00112\u000b\b\u0002\u0010®\u0001\u001a\u0004\u0018\u00010tH\u0004¢\u0006\u0006\b¯\u0001\u0010°\u0001R\u001d\u0010´\u0001\u001a\t\u0012\u0004\u0012\u00020\u00010±\u00018F@\u0006¢\u0006\b\u001a\u0006\b²\u0001\u0010³\u0001R\u001a\u0010¶\u0001\u001a\u0004\u0018\u00010\u00118D@\u0004X\u0004¢\u0006\u0007\u001a\u0005\bµ\u0001\u0010KR\u0018\u0010¸\u0001\u001a\u00020\u00058D@\u0004X\u0004¢\u0006\u0007\u001a\u0005\b·\u0001\u0010fR\u0018\u0010º\u0001\u001a\u00020\u00058P@\u0010X\u0004¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010fR\u0018\u0010»\u0001\u001a\u00020\u00058V@\u0016X\u0004¢\u0006\u0007\u001a\u0005\b»\u0001\u0010fR\u0015\u0010¼\u0001\u001a\u00020\u00058F@\u0006¢\u0006\u0007\u001a\u0005\b¼\u0001\u0010fR\u0015\u0010½\u0001\u001a\u00020\u00058F@\u0006¢\u0006\u0007\u001a\u0005\b½\u0001\u0010fR\u0015\u0010¾\u0001\u001a\u00020\u00058F@\u0006¢\u0006\u0007\u001a\u0005\b¾\u0001\u0010fR\u0018\u0010¿\u0001\u001a\u00020\u00058T@\u0014X\u0004¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010fR\u001b\u0010Ã\u0001\u001a\u0007\u0012\u0002\b\u00030À\u00018F@\u0006¢\u0006\b\u001a\u0006\bÁ\u0001\u0010Â\u0001R\u0018\u0010Å\u0001\u001a\u00020\u00058P@\u0010X\u0004¢\u0006\u0007\u001a\u0005\bÄ\u0001\u0010fR\u0016\u0010È\u0001\u001a\u00020\u00048F@\u0006¢\u0006\b\u001a\u0006\bÆ\u0001\u0010Ç\u0001R\u001b\u0010É\u0001\u001a\u0004\u0018\u00010\u001e8\u0000@\u0000X\u000e¢\u0006\b\n\u0006\bÉ\u0001\u0010Ê\u0001R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\t8@@\u0000X\u0004¢\u0006\u0007\u001a\u0005\bË\u0001\u0010HR \u0010Í\u0001\u001a\u0004\u0018\u00010\u0011*\u0004\u0018\u00010\t8B@\u0002X\u0004¢\u0006\u0007\u001a\u0005\bÌ\u0001\u0010>R\u001d\u0010Î\u0001\u001a\u00020\u0005*\u0002038B@\u0002X\u0004¢\u0006\b\u001a\u0006\bÎ\u0001\u0010Ï\u0001\u0002\u0004\n\u0002\b\u0019¨\u0006Ó\u0001"}, mo37405d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/selects/SelectClause0;", "", "active", "<init>", "(Z)V", "", "expect", "Lkotlinx/coroutines/NodeList;", "list", "Lkotlinx/coroutines/JobNode;", "node", "addLastAtomic", "(Ljava/lang/Object;Lkotlinx/coroutines/NodeList;Lkotlinx/coroutines/JobNode;)Z", "", "rootCause", "", "exceptions", "", "addSuppressedExceptions", "(Ljava/lang/Throwable;Ljava/util/List;)V", "state", "", "mode", "afterCompletionInternal", "(Ljava/lang/Object;I)V", "child", "Lkotlinx/coroutines/ChildHandle;", "attachChild", "(Lkotlinx/coroutines/ChildJob;)Lkotlinx/coroutines/ChildHandle;", "awaitInternal$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitInternal", "awaitSuspend", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelCoroutine", "cancelImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;)Z", "cancelImpl", "cancelInternal", "cancelMakeCompleting", "cancelParent", "childCancelled", "Lkotlinx/coroutines/Incomplete;", "update", "completeStateFinalization", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;I)V", "Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/ChildHandleNode;", "lastChild", "proposedUpdate", "continueCompleting", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "createCauseException", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "Lkotlinx/coroutines/JobCancellationException;", "createJobCancellationException", "()Lkotlinx/coroutines/JobCancellationException;", "firstChild", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/ChildHandleNode;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "getChildJobCancellationCause", "getCompletedInternal$kotlinx_coroutines_core", "()Ljava/lang/Object;", "getCompletedInternal", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "getFinalRootCause", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/util/List;)Ljava/lang/Throwable;", "getOrPromoteCancellingList", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/NodeList;", "exception", "handleJobException", "handleOnCompletionException$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "handleOnCompletionException", "parent", "initParentJobInternal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/Job;)V", "initParentJobInternal", "onCancelling", "invokeImmediately", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "join", "joinInternal", "()Z", "joinSuspend", "block", "", "loopOnState", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "makeCancelling", "makeCompleting$kotlinx_coroutines_core", "makeCompleting", "makeCompletingOnce$kotlinx_coroutines_core", "(Ljava/lang/Object;I)Z", "makeCompletingOnce", "makeNode", "(Lkotlin/jvm/functions/Function1;Z)Lkotlinx/coroutines/JobNode;", "", "nameString$kotlinx_coroutines_core", "()Ljava/lang/String;", "nameString", "notifyCancelling", "(Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V", "T", "notifyHandlers", "onCompletionInternal", "(Ljava/lang/Object;)V", "onStartInternal$kotlinx_coroutines_core", "()V", "onStartInternal", "parentJob", "parentCancelled", "(Lkotlinx/coroutines/ParentJob;)V", "Lkotlinx/coroutines/Empty;", "promoteEmptyToNodeList", "(Lkotlinx/coroutines/Empty;)V", "promoteSingleToNodeList", "(Lkotlinx/coroutines/JobNode;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/coroutines/Continuation;", "registerSelectClause0", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/Function2;", "registerSelectClause1Internal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectClause1Internal", "removeNode$kotlinx_coroutines_core", "removeNode", "selectAwaitCompletion$kotlinx_coroutines_core", "selectAwaitCompletion", "start", "startInternal", "(Ljava/lang/Object;)I", "stateString", "(Ljava/lang/Object;)Ljava/lang/String;", "toDebugString", "toString", "tryFinalizeFinishingState", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/lang/Object;I)Z", "tryFinalizeSimpleState", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;I)Z", "tryMakeCancelling", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z", "tryMakeCompleting", "(Ljava/lang/Object;Ljava/lang/Object;I)I", "tryMakeCompletingSlowPath", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;I)I", "tryWaitForChild", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "nextChild", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/ChildHandleNode;", "notifyCompletion", "message", "toCancellationException", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "children", "getCompletionCause", "completionCause", "getCompletionCauseHandled", "completionCauseHandled", "getHandlesException$kotlinx_coroutines_core", "handlesException", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "isScopedCoroutine", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "key", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "onJoin", "parentHandle", "Lkotlinx/coroutines/ChildHandle;", "getState$kotlinx_coroutines_core", "getExceptionOrNull", "exceptionOrNull", "isCancelling", "(Lkotlinx/coroutines/Incomplete;)Z", "AwaitContinuation", "ChildCompletion", "Finishing", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
/* compiled from: JobSupport.kt */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
    private volatile Object _state;
    @Nullable
    @JvmField
    public volatile ChildHandle parentHandle;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo37405d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: JobSupport.kt */
    private static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        private final JobSupport job;

        /* access modifiers changed from: protected */
        @NotNull
        public String nameString() {
            return "AwaitContinuation";
        }

        public AwaitContinuation(@NotNull Continuation<? super T> continuation, @NotNull JobSupport jobSupport) {
            Intrinsics.checkParameterIsNotNull(continuation, "delegate");
            Intrinsics.checkParameterIsNotNull(jobSupport, "job");
            super(continuation, 1);
            this.job = jobSupport;
        }

        @NotNull
        public Throwable getContinuationCancellationCause(@NotNull Job job2) {
            Intrinsics.checkParameterIsNotNull(job2, "parent");
            Object state$kotlinx_coroutines_core = this.job.getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Finishing) {
                Throwable th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
                if (th != null) {
                    return th;
                }
            }
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                return ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            }
            return job2.getCancellationException();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo37405d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/Job;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: JobSupport.kt */
    private static final class ChildCompletion extends JobNode<Job> {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        public ChildCompletion(@NotNull JobSupport jobSupport, @NotNull Finishing finishing, @NotNull ChildHandleNode childHandleNode, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(jobSupport, "parent");
            Intrinsics.checkParameterIsNotNull(finishing, Params.STATE);
            Intrinsics.checkParameterIsNotNull(childHandleNode, "child");
            super(childHandleNode.childJob);
            this.parent = jobSupport;
            this.state = finishing;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        public void invoke(@Nullable Throwable th) {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ChildCompletion[");
            sb.append(this.child);
            sb.append(", ");
            sb.append(this.proposedUpdate);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tJ\u0018\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0016j\b\u0012\u0004\u0012\u00020\t`\u0017H\u0002J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\tJ\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo37405d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "isCompleting", "", "rootCause", "", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "_exceptionsHolder", "isActive", "()Z", "isCancelling", "isSealed", "getList", "()Lkotlinx/coroutines/NodeList;", "addExceptionLocked", "", "exception", "allocateList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "sealLocked", "", "proposedException", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: JobSupport.kt */
    private static final class Finishing implements Incomplete {
        private volatile Object _exceptionsHolder;
        @JvmField
        public volatile boolean isCompleting;
        @NotNull
        private final NodeList list;
        @Nullable
        @JvmField
        public volatile Throwable rootCause;

        @NotNull
        public NodeList getList() {
            return this.list;
        }

        public Finishing(@NotNull NodeList nodeList, boolean z, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(nodeList, Kinds.ARRAY);
            this.list = nodeList;
            this.isCompleting = z;
            this.rootCause = th;
        }

        public final boolean isSealed() {
            return this._exceptionsHolder == JobSupportKt.SEALED;
        }

        public final boolean isCancelling() {
            return this.rootCause != null;
        }

        public boolean isActive() {
            return this.rootCause == null;
        }

        @NotNull
        public final List<Throwable> sealLocked(@Nullable Throwable th) {
            ArrayList arrayList;
            Object obj = this._exceptionsHolder;
            if (obj == null) {
                arrayList = allocateList();
            } else if (obj instanceof Throwable) {
                ArrayList allocateList = allocateList();
                allocateList.add(obj);
                arrayList = allocateList;
            } else if (!(obj instanceof ArrayList)) {
                StringBuilder sb = new StringBuilder();
                sb.append("State is ");
                sb.append(obj);
                throw new IllegalStateException(sb.toString().toString());
            } else if (obj != null) {
                arrayList = (ArrayList) obj;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Throwable> /* = java.util.ArrayList<kotlin.Throwable> */");
            }
            Throwable th2 = this.rootCause;
            if (th2 != null) {
                arrayList.add(0, th2);
            }
            if (th != null && (!Intrinsics.areEqual((Object) th, (Object) th2))) {
                arrayList.add(th);
            }
            this._exceptionsHolder = JobSupportKt.SEALED;
            return arrayList;
        }

        public final void addExceptionLocked(@NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(th, "exception");
            Throwable th2 = this.rootCause;
            if (th2 == null) {
                this.rootCause = th;
            } else if (th != th2) {
                Object obj = this._exceptionsHolder;
                if (obj == null) {
                    this._exceptionsHolder = th;
                } else if (obj instanceof Throwable) {
                    if (th != obj) {
                        ArrayList allocateList = allocateList();
                        allocateList.add(obj);
                        allocateList.add(th);
                        this._exceptionsHolder = allocateList;
                    }
                } else if (obj instanceof ArrayList) {
                    ((ArrayList) obj).add(th);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("State is ");
                    sb.append(obj);
                    throw new IllegalStateException(sb.toString().toString());
                }
            }
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Finishing[cancelling=");
            sb.append(isCancelling());
            sb.append(", completing=");
            sb.append(this.isCompleting);
            sb.append(", rootCause=");
            sb.append(this.rootCause);
            sb.append(", exceptions=");
            sb.append(this._exceptionsHolder);
            sb.append(", list=");
            sb.append(getList());
            sb.append(']');
            return sb.toString();
        }
    }

    /* access modifiers changed from: protected */
    public void afterCompletionInternal(@Nullable Object obj, int i) {
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean handleJobException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isScopedCoroutine() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCancelling(@Nullable Throwable th) {
    }

    /* access modifiers changed from: protected */
    public void onCompletionInternal(@Nullable Object obj) {
    }

    public void onStartInternal$kotlinx_coroutines_core() {
    }

    public JobSupport(boolean z) {
        this._state = z ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
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
        return DefaultImpls.plus((Job) this, coroutineContext);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "other");
        return DefaultImpls.plus((Job) this, job);
    }

    @NotNull
    public final C2916Key<?> getKey() {
        return Job.Key;
    }

    public final void initParentJobInternal$kotlinx_coroutines_core(@Nullable Job job) {
        if (!(this.parentHandle == null)) {
            throw new IllegalStateException("Check failed.".toString());
        } else if (job == null) {
            this.parentHandle = NonDisposableHandle.INSTANCE;
        } else {
            job.start();
            ChildHandle attachChild = job.attachChild(this);
            this.parentHandle = attachChild;
            if (isCompleted()) {
                attachChild.dispose();
                this.parentHandle = NonDisposableHandle.INSTANCE;
            }
        }
    }

    private final Void loopOnState(Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(getState$kotlinx_coroutines_core());
        }
    }

    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).isActive();
    }

    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof CompletedExceptionally) || ((state$kotlinx_coroutines_core instanceof Finishing) && ((Finishing) state$kotlinx_coroutines_core).isCancelling());
    }

    private final boolean tryFinalizeFinishingState(Finishing finishing, Object obj, int i) {
        boolean isCancelling;
        Throwable finalRootCause;
        boolean z = false;
        if (!(getState$kotlinx_coroutines_core() == finishing)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (!(!finishing.isSealed())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (finishing.isCompleting) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(obj instanceof CompletedExceptionally) ? null : obj);
            Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
            synchronized (finishing) {
                isCancelling = finishing.isCancelling();
                List sealLocked = finishing.sealLocked(th);
                finalRootCause = getFinalRootCause(finishing, sealLocked);
                if (finalRootCause != null) {
                    addSuppressedExceptions(finalRootCause, sealLocked);
                }
            }
            if (!(finalRootCause == null || finalRootCause == th)) {
                obj = new CompletedExceptionally(finalRootCause, false, 2, null);
            }
            if (finalRootCause != null) {
                if (cancelParent(finalRootCause) || handleJobException(finalRootCause)) {
                    z = true;
                }
                if (z) {
                    if (obj != null) {
                        ((CompletedExceptionally) obj).makeHandled();
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                    }
                }
            }
            if (!isCancelling) {
                onCancelling(finalRootCause);
            }
            onCompletionInternal(obj);
            if (_state$FU.compareAndSet(this, finishing, JobSupportKt.boxIncomplete(obj))) {
                completeStateFinalization(finishing, obj, i);
                return true;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected state: ");
            sb.append(this._state);
            sb.append(", expected: ");
            sb.append(finishing);
            sb.append(", update: ");
            sb.append(obj);
            throw new IllegalArgumentException(sb.toString().toString());
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private final Throwable getFinalRootCause(Finishing finishing, List<? extends Throwable> list) {
        Object obj;
        boolean z;
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (!(((Throwable) obj) instanceof CancellationException)) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
            Throwable th = (Throwable) obj;
            if (th == null) {
                th = (Throwable) list.get(0);
            }
            return th;
        } else if (finishing.isCancelling()) {
            return createJobCancellationException();
        } else {
            return null;
        }
    }

    private final void addSuppressedExceptions(Throwable th, List<? extends Throwable> list) {
        if (list.size() > 1) {
            Set identitySet = ConcurrentKt.identitySet(list.size());
            Throwable unwrap = StackTraceRecoveryKt.unwrap(th);
            for (Throwable unwrap2 : list) {
                Throwable unwrap3 = StackTraceRecoveryKt.unwrap(unwrap2);
                if (unwrap3 != th && unwrap3 != unwrap && !(unwrap3 instanceof CancellationException) && identitySet.add(unwrap3)) {
                    ExceptionsKt.addSuppressed(th, unwrap3);
                }
            }
        }
    }

    private final boolean tryFinalizeSimpleState(Incomplete incomplete, Object obj, int i) {
        String str = "Check failed.";
        if ((incomplete instanceof Empty) || (incomplete instanceof JobNode)) {
            if (!(!(obj instanceof CompletedExceptionally))) {
                throw new IllegalStateException(str.toString());
            } else if (!_state$FU.compareAndSet(this, incomplete, JobSupportKt.boxIncomplete(obj))) {
                return false;
            } else {
                onCancelling(null);
                onCompletionInternal(obj);
                completeStateFinalization(incomplete, obj, i);
                return true;
            }
        } else {
            throw new IllegalStateException(str.toString());
        }
    }

    private final void completeStateFinalization(Incomplete incomplete, Object obj, int i) {
        ChildHandle childHandle = this.parentHandle;
        if (childHandle != null) {
            childHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
        Throwable th = null;
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(obj instanceof CompletedExceptionally) ? null : obj);
        if (completedExceptionally != null) {
            th = completedExceptionally.cause;
        }
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
            } catch (Throwable th2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Exception in completion handler ");
                sb.append(incomplete);
                sb.append(" for ");
                sb.append(this);
                handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException(sb.toString(), th2));
            }
        } else {
            NodeList list = incomplete.getList();
            if (list != null) {
                notifyCompletion(list, th);
            }
        }
        afterCompletionInternal(obj, i);
    }

    private final void notifyCancelling(NodeList nodeList, Throwable th) {
        onCancelling(th);
        Throwable th2 = null;
        Object next = nodeList.getNext();
        if (next != null) {
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (th2 != null) {
                            ExceptionsKt.addSuppressed(th2, th3);
                            if (th2 != null) {
                            }
                        }
                        JobSupport jobSupport = this;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Exception in completion handler ");
                        sb.append(jobNode);
                        sb.append(" for ");
                        sb.append(jobSupport);
                        Throwable completionHandlerException = new CompletionHandlerException(sb.toString(), th3);
                        Unit unit = Unit.INSTANCE;
                        th2 = completionHandlerException;
                    }
                }
            }
            if (th2 != null) {
                handleOnCompletionException$kotlinx_coroutines_core(th2);
            }
            cancelParent(th);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final boolean cancelParent(Throwable th) {
        boolean z = th instanceof CancellationException;
        ChildHandle childHandle = this.parentHandle;
        if (childHandle == null || childHandle == NonDisposableHandle.INSTANCE || isScopedCoroutine()) {
            return z;
        }
        return childHandle.childCancelled(th) || z;
    }

    private final <T extends JobNode<?>> void notifyHandlers(NodeList nodeList, Throwable th) {
        Throwable th2 = null;
        Object next = nodeList.getNext();
        if (next != null) {
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (th2 != null) {
                            ExceptionsKt.addSuppressed(th2, th3);
                            if (th2 != null) {
                            }
                        }
                        JobSupport jobSupport = this;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Exception in completion handler ");
                        sb.append(jobNode);
                        sb.append(" for ");
                        sb.append(jobSupport);
                        Throwable completionHandlerException = new CompletionHandlerException(sb.toString(), th3);
                        Unit unit = Unit.INSTANCE;
                        th2 = completionHandlerException;
                    }
                }
            }
            if (th2 != null) {
                handleOnCompletionException$kotlinx_coroutines_core(th2);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final int startInternal(Object obj) {
        if (obj instanceof Empty) {
            if (((Empty) obj).isActive()) {
                return 0;
            }
            if (!_state$FU.compareAndSet(this, obj, JobSupportKt.EMPTY_ACTIVE)) {
                return -1;
            }
            onStartInternal$kotlinx_coroutines_core();
            return 1;
        } else if (!(obj instanceof InactiveNodeList)) {
            return 0;
        } else {
            if (!_state$FU.compareAndSet(this, obj, ((InactiveNodeList) obj).getList())) {
                return -1;
            }
            onStartInternal$kotlinx_coroutines_core();
            return 1;
        }
    }

    @NotNull
    public final CancellationException getCancellationException() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        String str = "Job is still new or active: ";
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
            if (th != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(DebugKt.getClassSimpleName(this));
                sb.append(" is cancelling");
                CancellationException cancellationException = toCancellationException(th, sb.toString());
                if (cancellationException != null) {
                    return cancellationException;
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(this);
            throw new IllegalStateException(sb2.toString().toString());
        } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(this);
            throw new IllegalStateException(sb3.toString().toString());
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            return toCancellationException$default(this, ((CompletedExceptionally) state$kotlinx_coroutines_core).cause, null, 1, null);
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(DebugKt.getClassSimpleName(this));
            sb4.append(" has completed normally");
            return new JobCancellationException(sb4.toString(), null, this);
        }
    }

    @NotNull
    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = null;
            }
            return jobSupport.toCancellationException(th, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final CancellationException toCancellationException(@NotNull Throwable th, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(th, "$this$toCancellationException");
        CancellationException cancellationException = (CancellationException) (!(th instanceof CancellationException) ? null : th);
        if (cancellationException != null) {
            return cancellationException;
        }
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(DebugKt.getClassSimpleName(th));
            sb.append(" was cancelled");
            str = sb.toString();
        }
        return new JobCancellationException(str, th, this);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Throwable getCompletionCause() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        String str = "Job is still new or active: ";
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
            if (th != null) {
                return th;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(this);
            throw new IllegalStateException(sb.toString().toString());
        } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(this);
            throw new IllegalStateException(sb2.toString().toString());
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            return ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean getCompletionCauseHandled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof CompletedExceptionally) && ((CompletedExceptionally) state$kotlinx_coroutines_core).getHandled();
    }

    @NotNull
    public final DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        return invokeOnCompletion(false, true, function1);
    }

    @NotNull
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, @NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        Throwable th = null;
        JobNode jobNode = null;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Empty) {
                Empty empty = (Empty) state$kotlinx_coroutines_core;
                if (empty.isActive()) {
                    if (jobNode == null) {
                        jobNode = makeNode(function1, z);
                    }
                    if (_state$FU.compareAndSet(this, state$kotlinx_coroutines_core, jobNode)) {
                        return jobNode;
                    }
                } else {
                    promoteEmptyToNodeList(empty);
                }
            } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
                NodeList list = ((Incomplete) state$kotlinx_coroutines_core).getList();
                if (list != null) {
                    Throwable th2 = null;
                    DisposableHandle disposableHandle = NonDisposableHandle.INSTANCE;
                    if (z && (state$kotlinx_coroutines_core instanceof Finishing)) {
                        synchronized (state$kotlinx_coroutines_core) {
                            th2 = ((Finishing) state$kotlinx_coroutines_core).rootCause;
                            if (th2 == null || ((function1 instanceof ChildHandleNode) && !((Finishing) state$kotlinx_coroutines_core).isCompleting)) {
                                if (jobNode == null) {
                                    jobNode = makeNode(function1, z);
                                }
                                if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                                    if (th2 == null) {
                                        DisposableHandle disposableHandle2 = jobNode;
                                        return disposableHandle2;
                                    }
                                    disposableHandle = jobNode;
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                    if (th2 != null) {
                        if (z2) {
                            function1.invoke(th2);
                        }
                        return disposableHandle;
                    }
                    if (jobNode == null) {
                        jobNode = makeNode(function1, z);
                    }
                    if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                        return jobNode;
                    }
                } else if (state$kotlinx_coroutines_core != null) {
                    promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.JobNode<*>");
                }
            } else {
                if (z2) {
                    if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                        state$kotlinx_coroutines_core = null;
                    }
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) state$kotlinx_coroutines_core;
                    if (completedExceptionally != null) {
                        th = completedExceptionally.cause;
                    }
                    function1.invoke(th);
                }
                return NonDisposableHandle.INSTANCE;
            }
        }
    }

    private final JobNode<?> makeNode(Function1<? super Throwable, Unit> function1, boolean z) {
        String str = "Failed requirement.";
        boolean z2 = true;
        JobCancellingNode jobCancellingNode = null;
        if (z) {
            if (function1 instanceof JobCancellingNode) {
                jobCancellingNode = function1;
            }
            JobCancellingNode jobCancellingNode2 = jobCancellingNode;
            if (jobCancellingNode2 != null) {
                if (jobCancellingNode2.job != this) {
                    z2 = false;
                }
                if (!z2) {
                    throw new IllegalArgumentException(str.toString());
                } else if (jobCancellingNode2 != null) {
                    return jobCancellingNode2;
                }
            }
            return new InvokeOnCancelling<>(this, function1);
        }
        if (function1 instanceof JobNode) {
            jobCancellingNode = function1;
        }
        JobNode<?> jobNode = jobCancellingNode;
        if (jobNode != null) {
            if (jobNode.job != this || (jobNode instanceof JobCancellingNode)) {
                z2 = false;
            }
            if (!z2) {
                throw new IllegalArgumentException(str.toString());
            } else if (jobNode != null) {
                return jobNode;
            }
        }
        return new InvokeOnCompletion<>(this, function1);
    }

    private final void promoteEmptyToNodeList(Empty empty) {
        NodeList nodeList = new NodeList();
        _state$FU.compareAndSet(this, empty, empty.isActive() ? nodeList : new InactiveNodeList(nodeList));
    }

    private final void promoteSingleToNodeList(JobNode<?> jobNode) {
        jobNode.addOneIfEmpty(new NodeList());
        _state$FU.compareAndSet(this, jobNode, jobNode.getNextNode());
    }

    @Nullable
    public final Object join(@NotNull Continuation<? super Unit> continuation) {
        if (joinInternal()) {
            return joinSuspend(continuation);
        }
        YieldKt.checkCompletion(continuation.getContext());
        return Unit.INSTANCE;
    }

    @NotNull
    public final SelectClause0 getOnJoin() {
        return this;
    }

    public void cancel(@Nullable CancellationException cancellationException) {
        cancel(cancellationException);
    }

    /* renamed from: cancelInternal */
    public boolean cancel(@Nullable Throwable th) {
        return cancelImpl$kotlinx_coroutines_core(th) && getHandlesException$kotlinx_coroutines_core();
    }

    public final void parentCancelled(@NotNull ParentJob parentJob) {
        Intrinsics.checkParameterIsNotNull(parentJob, "parentJob");
        cancelImpl$kotlinx_coroutines_core(parentJob);
    }

    public boolean childCancelled(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "cause");
        boolean z = true;
        if (th instanceof CancellationException) {
            return true;
        }
        if (!cancelImpl$kotlinx_coroutines_core(th) || !getHandlesException$kotlinx_coroutines_core()) {
            z = false;
        }
        return z;
    }

    public final boolean cancelCoroutine(@Nullable Throwable th) {
        return cancelImpl$kotlinx_coroutines_core(th);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(@Nullable Object obj) {
        if (!getOnCancelComplete$kotlinx_coroutines_core() || !cancelMakeCompleting(obj)) {
            return makeCancelling(obj);
        }
        return true;
    }

    private final JobCancellationException createJobCancellationException() {
        return new JobCancellationException("Job was cancelled", null, this);
    }

    @NotNull
    public CancellationException getChildJobCancellationCause() {
        Throwable th;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        CancellationException cancellationException = null;
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            th = null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot be cancelling child in this state: ");
            sb.append(state$kotlinx_coroutines_core);
            throw new IllegalStateException(sb.toString().toString());
        }
        if (th instanceof CancellationException) {
            cancellationException = th;
        }
        CancellationException cancellationException2 = cancellationException;
        if (cancellationException2 != null) {
            return cancellationException2;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Parent job is ");
        sb2.append(stateString(state$kotlinx_coroutines_core));
        return new JobCancellationException(sb2.toString(), th, this);
    }

    private final Throwable createCauseException(Object obj) {
        if (obj != null ? obj instanceof Throwable : true) {
            if (obj == null) {
                obj = createJobCancellationException();
            }
            return (Throwable) obj;
        } else if (obj != null) {
            return ((ParentJob) obj).getChildJobCancellationCause();
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        if (r8 == null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0040, code lost:
        notifyCancelling(((kotlinx.coroutines.JobSupport.Finishing) r2).getList(), r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean makeCancelling(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
        L_0x0004:
            java.lang.Object r2 = r7.getState$kotlinx_coroutines_core()
            boolean r3 = r2 instanceof kotlinx.coroutines.JobSupport.Finishing
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x004d
            monitor-enter(r2)
            r3 = r2
            kotlinx.coroutines.JobSupport$Finishing r3 = (kotlinx.coroutines.JobSupport.Finishing) r3     // Catch:{ all -> 0x004a }
            boolean r3 = r3.isSealed()     // Catch:{ all -> 0x004a }
            if (r3 == 0) goto L_0x001a
            monitor-exit(r2)
            return r4
        L_0x001a:
            r3 = r2
            kotlinx.coroutines.JobSupport$Finishing r3 = (kotlinx.coroutines.JobSupport.Finishing) r3     // Catch:{ all -> 0x004a }
            boolean r3 = r3.isCancelling()     // Catch:{ all -> 0x004a }
            if (r8 != 0) goto L_0x0025
            if (r3 != 0) goto L_0x0032
        L_0x0025:
            if (r1 == 0) goto L_0x0028
            goto L_0x002c
        L_0x0028:
            java.lang.Throwable r1 = r7.createCauseException(r8)     // Catch:{ all -> 0x004a }
        L_0x002c:
            r8 = r2
            kotlinx.coroutines.JobSupport$Finishing r8 = (kotlinx.coroutines.JobSupport.Finishing) r8     // Catch:{ all -> 0x004a }
            r8.addExceptionLocked(r1)     // Catch:{ all -> 0x004a }
        L_0x0032:
            r8 = r2
            kotlinx.coroutines.JobSupport$Finishing r8 = (kotlinx.coroutines.JobSupport.Finishing) r8     // Catch:{ all -> 0x004a }
            java.lang.Throwable r8 = r8.rootCause     // Catch:{ all -> 0x004a }
            r1 = r3 ^ 1
            if (r1 == 0) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r8 = r0
        L_0x003d:
            monitor-exit(r2)
            if (r8 == 0) goto L_0x0049
            kotlinx.coroutines.JobSupport$Finishing r2 = (kotlinx.coroutines.JobSupport.Finishing) r2
            kotlinx.coroutines.NodeList r0 = r2.getList()
            r7.notifyCancelling(r0, r8)
        L_0x0049:
            return r5
        L_0x004a:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        L_0x004d:
            boolean r3 = r2 instanceof kotlinx.coroutines.Incomplete
            if (r3 == 0) goto L_0x00a8
            if (r1 == 0) goto L_0x0054
            goto L_0x0058
        L_0x0054:
            java.lang.Throwable r1 = r7.createCauseException(r8)
        L_0x0058:
            r3 = r2
            kotlinx.coroutines.Incomplete r3 = (kotlinx.coroutines.Incomplete) r3
            boolean r6 = r3.isActive()
            if (r6 == 0) goto L_0x0068
            boolean r2 = r7.tryMakeCancelling(r3, r1)
            if (r2 == 0) goto L_0x0004
            return r5
        L_0x0068:
            kotlinx.coroutines.CompletedExceptionally r3 = new kotlinx.coroutines.CompletedExceptionally
            r6 = 2
            r3.<init>(r1, r4, r6, r0)
            int r3 = r7.tryMakeCompleting(r2, r3, r4)
            if (r3 == 0) goto L_0x008b
            if (r3 == r5) goto L_0x008a
            if (r3 == r6) goto L_0x008a
            r2 = 3
            if (r3 != r2) goto L_0x007c
            goto L_0x0004
        L_0x007c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "unexpected result"
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        L_0x008a:
            return r5
        L_0x008b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "Cannot happen in "
            r8.append(r0)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r8 = r8.toString()
            r0.<init>(r8)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x00a8:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.makeCancelling(java.lang.Object):boolean");
    }

    private final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list != null) {
            return list;
        }
        if (incomplete instanceof Empty) {
            return new NodeList();
        }
        if (incomplete instanceof JobNode) {
            promoteSingleToNodeList((JobNode) incomplete);
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("State should have list: ");
        sb.append(incomplete);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final boolean tryMakeCancelling(Incomplete incomplete, Throwable th) {
        String str = "Check failed.";
        if (!(!(incomplete instanceof Finishing))) {
            throw new IllegalStateException(str.toString());
        } else if (incomplete.isActive()) {
            NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete);
            if (orPromoteCancellingList == null) {
                return false;
            }
            if (!_state$FU.compareAndSet(this, incomplete, new Finishing(orPromoteCancellingList, false, th))) {
                return false;
            }
            notifyCancelling(orPromoteCancellingList, th);
            return true;
        } else {
            throw new IllegalStateException(str.toString());
        }
    }

    private final int tryMakeCompleting(Object obj, Object obj2, int i) {
        if (!(obj instanceof Incomplete)) {
            return 0;
        }
        if (((obj instanceof Empty) || (obj instanceof JobNode)) && !(obj instanceof ChildHandleNode) && !(obj2 instanceof CompletedExceptionally)) {
            return !tryFinalizeSimpleState((Incomplete) obj, obj2, i) ? 3 : 1;
        }
        return tryMakeCompletingSlowPath((Incomplete) obj, obj2, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0057, code lost:
        if (r3 == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0059, code lost:
        notifyCancelling(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005c, code lost:
        r8 = firstChild(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0060, code lost:
        if (r8 == null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0066, code lost:
        if (tryWaitForChild(r2, r8, r9) == false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0069, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x006e, code lost:
        if (tryFinalizeFinishingState(r2, r9, r10) == false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0070, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0071, code lost:
        return 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int tryMakeCompletingSlowPath(kotlinx.coroutines.Incomplete r8, java.lang.Object r9, int r10) {
        /*
            r7 = this;
            kotlinx.coroutines.NodeList r0 = r7.getOrPromoteCancellingList(r8)
            r1 = 3
            if (r0 == 0) goto L_0x0083
            boolean r2 = r8 instanceof kotlinx.coroutines.JobSupport.Finishing
            r3 = 0
            if (r2 != 0) goto L_0x000e
            r2 = r3
            goto L_0x000f
        L_0x000e:
            r2 = r8
        L_0x000f:
            kotlinx.coroutines.JobSupport$Finishing r2 = (kotlinx.coroutines.JobSupport.Finishing) r2
            r4 = 0
            if (r2 == 0) goto L_0x0015
            goto L_0x001a
        L_0x0015:
            kotlinx.coroutines.JobSupport$Finishing r2 = new kotlinx.coroutines.JobSupport$Finishing
            r2.<init>(r0, r4, r3)
        L_0x001a:
            r5 = r3
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            monitor-enter(r2)
            boolean r5 = r2.isCompleting     // Catch:{ all -> 0x0080 }
            if (r5 == 0) goto L_0x0024
            monitor-exit(r2)
            return r4
        L_0x0024:
            r4 = 1
            r2.isCompleting = r4     // Catch:{ all -> 0x0080 }
            if (r2 == r8) goto L_0x0033
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = _state$FU     // Catch:{ all -> 0x0080 }
            boolean r5 = r5.compareAndSet(r7, r8, r2)     // Catch:{ all -> 0x0080 }
            if (r5 != 0) goto L_0x0033
            monitor-exit(r2)
            return r1
        L_0x0033:
            boolean r5 = r2.isSealed()     // Catch:{ all -> 0x0080 }
            r5 = r5 ^ r4
            if (r5 == 0) goto L_0x0072
            boolean r5 = r2.isCancelling()     // Catch:{ all -> 0x0080 }
            boolean r6 = r9 instanceof kotlinx.coroutines.CompletedExceptionally     // Catch:{ all -> 0x0080 }
            if (r6 != 0) goto L_0x0044
            r6 = r3
            goto L_0x0045
        L_0x0044:
            r6 = r9
        L_0x0045:
            kotlinx.coroutines.CompletedExceptionally r6 = (kotlinx.coroutines.CompletedExceptionally) r6     // Catch:{ all -> 0x0080 }
            if (r6 == 0) goto L_0x004e
            java.lang.Throwable r6 = r6.cause     // Catch:{ all -> 0x0080 }
            r2.addExceptionLocked(r6)     // Catch:{ all -> 0x0080 }
        L_0x004e:
            java.lang.Throwable r6 = r2.rootCause     // Catch:{ all -> 0x0080 }
            r5 = r5 ^ r4
            if (r5 == 0) goto L_0x0054
            r3 = r6
        L_0x0054:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0080 }
            monitor-exit(r2)
            if (r3 == 0) goto L_0x005c
            r7.notifyCancelling(r0, r3)
        L_0x005c:
            kotlinx.coroutines.ChildHandleNode r8 = r7.firstChild(r8)
            if (r8 == 0) goto L_0x006a
            boolean r8 = r7.tryWaitForChild(r2, r8, r9)
            if (r8 == 0) goto L_0x006a
            r8 = 2
            return r8
        L_0x006a:
            boolean r8 = r7.tryFinalizeFinishingState(r2, r9, r10)
            if (r8 == 0) goto L_0x0071
            return r4
        L_0x0071:
            return r1
        L_0x0072:
            java.lang.String r8 = "Failed requirement."
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0080 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0080 }
            r9.<init>(r8)     // Catch:{ all -> 0x0080 }
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ all -> 0x0080 }
            throw r9     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        L_0x0083:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.tryMakeCompletingSlowPath(kotlinx.coroutines.Incomplete, java.lang.Object, int):int");
    }

    private final Throwable getExceptionOrNull(@Nullable Object obj) {
        if (!(obj instanceof CompletedExceptionally)) {
            obj = null;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    private final ChildHandleNode firstChild(Incomplete incomplete) {
        ChildHandleNode childHandleNode = (ChildHandleNode) (!(incomplete instanceof ChildHandleNode) ? null : incomplete);
        if (childHandleNode != null) {
            return childHandleNode;
        }
        NodeList list = incomplete.getList();
        if (list != null) {
            return nextChild(list);
        }
        return null;
    }

    private final boolean tryWaitForChild(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (DefaultImpls.invokeOnCompletion$default(childHandleNode.childJob, false, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1, null) == NonDisposableHandle.INSTANCE) {
            childHandleNode = nextChild(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final void continueCompleting(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        if (getState$kotlinx_coroutines_core() == finishing) {
            ChildHandleNode nextChild = nextChild(childHandleNode);
            if ((nextChild == null || !tryWaitForChild(finishing, nextChild, obj)) && tryFinalizeFinishingState(finishing, obj, 0)) {
            }
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final ChildHandleNode nextChild(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    @NotNull
    public final Sequence<Job> getChildren() {
        return SequencesKt.sequence(new JobSupport$children$1(this, null));
    }

    @NotNull
    public final ChildHandle attachChild(@NotNull ChildJob childJob) {
        Intrinsics.checkParameterIsNotNull(childJob, "child");
        DisposableHandle invokeOnCompletion$default = DefaultImpls.invokeOnCompletion$default(this, true, false, new ChildHandleNode(this, childJob), 2, null);
        if (invokeOnCompletion$default != null) {
            return (ChildHandle) invokeOnCompletion$default;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        throw th;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(toDebugString());
        sb.append('@');
        sb.append(DebugKt.getHexAddress(this));
        return sb.toString();
    }

    @NotNull
    @InternalCoroutinesApi
    public final String toDebugString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nameString$kotlinx_coroutines_core());
        sb.append('{');
        sb.append(stateString(getState$kotlinx_coroutines_core()));
        sb.append('}');
        return sb.toString();
    }

    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        return DebugKt.getClassSimpleName(this);
    }

    private final String stateString(Object obj) {
        String str = "Active";
        if (obj instanceof Finishing) {
            Finishing finishing = (Finishing) obj;
            if (finishing.isCancelling()) {
                return "Cancelling";
            }
            if (finishing.isCompleting) {
                return "Completing";
            }
            return str;
        } else if (!(obj instanceof Incomplete)) {
            return obj instanceof CompletedExceptionally ? "Cancelled" : "Completed";
        } else {
            if (((Incomplete) obj).isActive()) {
                return str;
            }
            return "New";
        }
    }

    private final boolean isCancelling(@NotNull Incomplete incomplete) {
        return (incomplete instanceof Finishing) && ((Finishing) incomplete).isCancelling();
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
    }

    @Nullable
    public final Throwable getCompletionExceptionOrNull() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            return getExceptionOrNull(state$kotlinx_coroutines_core);
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    @Nullable
    public final Object getCompletedInternal$kotlinx_coroutines_core() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        } else if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return JobSupportKt.unboxState(state$kotlinx_coroutines_core);
        } else {
            throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        }
    }

    @Nullable
    public final Object awaitInternal$kotlinx_coroutines_core(@NotNull Continuation<Object> continuation) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                    return JobSupportKt.unboxState(state$kotlinx_coroutines_core);
                }
                Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
                if (!StackTraceRecoveryKt.recoveryDisabled(th)) {
                    InlineMarker.mark(0);
                    if (!(continuation instanceof CoroutineStackFrame)) {
                        throw th;
                    }
                    throw StackTraceRecoveryKt.recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
                }
                throw th;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return awaitSuspend(continuation);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object awaitSuspend(@NotNull Continuation<Object> continuation) {
        AwaitContinuation awaitContinuation = new AwaitContinuation(IntrinsicsKt.intercepted(continuation), this);
        CancellableContinuationKt.disposeOnCancellation(awaitContinuation, invokeOnCompletion(new ResumeAwaitOnCompletion(this, awaitContinuation)));
        Object result = awaitContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <T, R> void selectAwaitCompletion$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            selectInstance.resumeSelectCancellableWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
        } else {
            CancellableKt.startCoroutineCancellable(function2, JobSupportKt.unboxState(state$kotlinx_coroutines_core), selectInstance.getCompletion());
        }
    }

    @Nullable
    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    private final void notifyCompletion(@NotNull NodeList nodeList, Throwable th) {
        Throwable th2 = null;
        Object next = nodeList.getNext();
        if (next != null) {
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof JobNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (th2 != null) {
                            ExceptionsKt.addSuppressed(th2, th3);
                            if (th2 != null) {
                            }
                        }
                        JobSupport jobSupport = this;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Exception in completion handler ");
                        sb.append(jobNode);
                        sb.append(" for ");
                        sb.append(jobSupport);
                        Throwable completionHandlerException = new CompletionHandlerException(sb.toString(), th3);
                        Unit unit = Unit.INSTANCE;
                        th2 = completionHandlerException;
                    }
                }
            }
            if (th2 != null) {
                handleOnCompletionException$kotlinx_coroutines_core(th2);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    public final boolean start() {
        int startInternal;
        do {
            startInternal = startInternal(getState$kotlinx_coroutines_core());
            if (startInternal == 0) {
                return false;
            }
        } while (startInternal != 1);
        return true;
    }

    private final boolean addLastAtomic(Object obj, NodeList nodeList, JobNode<?> jobNode) {
        int tryCondAddNext;
        LockFreeLinkedListNode lockFreeLinkedListNode = jobNode;
        CondAddOp jobSupport$addLastAtomic$$inlined$addLastIf$1 = new JobSupport$addLastAtomic$$inlined$addLastIf$1(lockFreeLinkedListNode, lockFreeLinkedListNode, this, obj);
        do {
            Object prev = nodeList.getPrev();
            if (prev != null) {
                tryCondAddNext = ((LockFreeLinkedListNode) prev).tryCondAddNext(lockFreeLinkedListNode, nodeList, jobSupport$addLastAtomic$$inlined$addLastIf$1);
                if (tryCondAddNext == 1) {
                    return true;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (tryCondAddNext != 2);
        return false;
    }

    private final boolean joinInternal() {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return true;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object joinSuspend(@NotNull Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        CancellableContinuationKt.disposeOnCancellation(cancellableContinuation, invokeOnCompletion(new ResumeOnCompletion(this, cancellableContinuation)));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <R> void registerSelectClause0(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Object state$kotlinx_coroutines_core;
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!selectInstance.isSelected()) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                    if (selectInstance.trySelect(null)) {
                        UndispatchedKt.startCoroutineUnintercepted(function1, selectInstance.getCompletion());
                    }
                    return;
                }
            } else {
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        selectInstance.disposeOnSelect(invokeOnCompletion(new SelectJoinOnCompletion(this, selectInstance, function1)));
    }

    public final void removeNode$kotlinx_coroutines_core(@NotNull JobNode<?> jobNode) {
        Object state$kotlinx_coroutines_core;
        Intrinsics.checkParameterIsNotNull(jobNode, "node");
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof JobNode)) {
                if ((state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).getList() != null) {
                    jobNode.remove();
                }
                return;
            } else if (state$kotlinx_coroutines_core != jobNode) {
                return;
            }
        } while (!_state$FU.compareAndSet(this, state$kotlinx_coroutines_core, JobSupportKt.EMPTY_ACTIVE));
    }

    private final boolean cancelMakeCompleting(Object obj) {
        int tryMakeCompleting;
        do {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if ((state$kotlinx_coroutines_core instanceof Incomplete) && (!(state$kotlinx_coroutines_core instanceof Finishing) || !((Finishing) state$kotlinx_coroutines_core).isCompleting)) {
                tryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new CompletedExceptionally(createCauseException(obj), false, 2, null), 0);
                if (tryMakeCompleting != 0) {
                    if (tryMakeCompleting == 1 || tryMakeCompleting == 2) {
                        return true;
                    }
                }
            }
            return false;
        } while (tryMakeCompleting == 3);
        throw new IllegalStateException("unexpected result".toString());
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(@Nullable Object obj) {
        int tryMakeCompleting;
        do {
            boolean z = false;
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj, 0);
            if (tryMakeCompleting != 0) {
                z = true;
                if (!(tryMakeCompleting == 1 || tryMakeCompleting == 2)) {
                }
            }
            return z;
        } while (tryMakeCompleting == 3);
        throw new IllegalStateException("unexpected result".toString());
    }

    public final boolean makeCompletingOnce$kotlinx_coroutines_core(@Nullable Object obj, int i) {
        int tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj, i);
            if (tryMakeCompleting == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Job ");
                sb.append(this);
                sb.append(" is already complete or completing, ");
                sb.append("but is being completed with ");
                sb.append(obj);
                throw new IllegalStateException(sb.toString(), getExceptionOrNull(obj));
            } else if (tryMakeCompleting == 1) {
                return true;
            } else {
                if (tryMakeCompleting == 2) {
                    return false;
                }
            }
        } while (tryMakeCompleting == 3);
        throw new IllegalStateException("unexpected result".toString());
    }

    public final <T, R> void registerSelectClause1Internal$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Object state$kotlinx_coroutines_core;
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!selectInstance.isSelected()) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                    if (selectInstance.trySelect(null)) {
                        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                            selectInstance.resumeSelectCancellableWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
                        } else {
                            UndispatchedKt.startCoroutineUnintercepted(function2, JobSupportKt.unboxState(state$kotlinx_coroutines_core), selectInstance.getCompletion());
                        }
                    }
                    return;
                }
            } else {
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        selectInstance.disposeOnSelect(invokeOnCompletion(new SelectAwaitOnCompletion(this, selectInstance, function2)));
    }
}
