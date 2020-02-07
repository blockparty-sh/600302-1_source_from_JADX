package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Ð\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0011\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\u001aJ\u0010\u0002\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t2\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\f0\u000b\"\u0006\u0012\u0002\b\u00030\fH\u0007¢\u0006\u0002\u0010\r\u001a5\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010\u0013\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010\u0013\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aY\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aG\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aa\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a]\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0018\b\u0002\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00100!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001aw\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u0018\b\u0003\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010$\u001ao\u0010%\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u0018\b\u0003\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001a\u001a\u0010&\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0001\u001aC\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100)2\u001d\u0010*\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f\u0012\u0004\u0012\u0002H(0\u0003¢\u0006\u0002\b+H\b¢\u0006\u0002\u0010,\u001aC\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001d\u0010*\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f\u0012\u0004\u0012\u0002H(0\u0003¢\u0006\u0002\b+H\b¢\u0006\u0002\u0010-\u001a5\u0010.\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100)2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u00100\u001a5\u0010.\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a;\u00101\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0018\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001002\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a1\u00103\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t*\u0006\u0012\u0002\b\u00030\fH\u0007\u001a!\u00104\u001a\u000205\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u00104\u001a\u000205\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001e\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH\u0007\u001aZ\u00107\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092\"\u0010:\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001a0\u0010?\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010@\u001a\u0002052\b\b\u0002\u00108\u001a\u000209H\u0007\u001aT\u0010A\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001a)\u0010B\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010C\u001a\u000205H@ø\u0001\u0000¢\u0006\u0002\u0010D\u001a=\u0010E\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010C\u001a\u0002052\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u0002H\u00100\u0003HHø\u0001\u0000¢\u0006\u0002\u0010G\u001a+\u0010H\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010C\u001a\u000205H@ø\u0001\u0000¢\u0006\u0002\u0010D\u001aT\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001ai\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u00020927\u0010\u0011\u001a3\b\u0001\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0<\u0012\u0006\u0012\u0004\u0018\u00010=0KH\u0007ø\u0001\u0000¢\u0006\u0002\u0010L\u001ad\u0010M\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2'\u0010\u0011\u001a#\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0;HHø\u0001\u0000¢\u0006\u0002\u0010P\u001ab\u0010M\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010N*\b\u0012\u0004\u0012\u0002H\u00100Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2'\u0010\u0011\u001a#\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0;HHø\u0001\u0000¢\u0006\u0002\u0010R\u001aT\u0010S\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001a$\u0010T\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\b\b\u0000\u0010\u0010*\u00020=*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\fH\u0007\u001aA\u0010U\u001a\u0002HN\"\b\b\u0000\u0010\u0010*\u00020=\"\u0010\b\u0001\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100O*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\f2\u0006\u0010\"\u001a\u0002HNH@ø\u0001\u0000¢\u0006\u0002\u0010V\u001a?\u0010U\u001a\u0002HN\"\b\b\u0000\u0010\u0010*\u00020=\"\u000e\b\u0001\u0010N*\b\u0012\u0004\u0012\u0002H\u00100Q*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\f2\u0006\u0010\"\u001a\u0002HNH@ø\u0001\u0000¢\u0006\u0002\u0010W\u001aO\u0010X\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aM\u0010X\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010N*\b\u0012\u0004\u0012\u0002H\u00100Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Z\u001aO\u0010[\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aM\u0010[\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010N*\b\u0012\u0004\u0012\u0002H\u00100Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Z\u001a7\u0010\\\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a7\u0010]\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010^\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010^\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a#\u0010_\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010_\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a`\u0010`\u001a\b\u0012\u0004\u0012\u0002H(0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092(\u0010\u0019\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0\f0<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001aX\u0010a\u001a\u0002H(\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010b\u001a\u0002H(2'\u0010c\u001a#\u0012\u0013\u0012\u0011H(¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(d\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0;HHø\u0001\u0000¢\u0006\u0002\u0010e\u001am\u0010f\u001a\u0002H(\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010b\u001a\u0002H(2<\u0010c\u001a8\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011H(¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(d\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0KHHø\u0001\u0000¢\u0006\u0002\u0010g\u001aM\u0010h\u001a\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100i0\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001ag\u0010h\u001a\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180i0\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001aa\u0010j\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u001c\b\u0002\u0010 *\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100k0!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001a{\u0010j\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u001c\b\u0003\u0010 *\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180k0!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010$\u001a)\u0010l\u001a\u000205\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010m\u001a\u0002H\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010n\u001a5\u0010o\u001a\u000205\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a5\u0010p\u001a\u000205\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010q\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010q\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a)\u0010r\u001a\u000205\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010m\u001a\u0002H\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010n\u001a#\u0010s\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010s\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aZ\u0010t\u001a\b\u0012\u0004\u0012\u0002H(0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001ao\u0010u\u001a\b\u0012\u0004\u0012\u0002H(0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u00020927\u0010\u0019\u001a3\b\u0001\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0<\u0012\u0006\u0012\u0004\u0018\u00010=0KH\u0007ø\u0001\u0000¢\u0006\u0002\u0010L\u001au\u0010v\u001a\b\u0012\u0004\u0012\u0002H(0\f\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010(*\u00020=*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u00020929\u0010\u0019\u001a5\b\u0001\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H(0<\u0012\u0006\u0012\u0004\u0018\u00010=0KH\u0007ø\u0001\u0000¢\u0006\u0002\u0010L\u001ap\u0010w\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010(*\u00020=\"\u0010\b\u0002\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H(0O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2)\u0010\u0019\u001a%\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H(0;HHø\u0001\u0000¢\u0006\u0002\u0010P\u001an\u0010w\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010(*\u00020=\"\u000e\b\u0002\u0010N*\b\u0012\u0004\u0012\u0002H(0Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2)\u0010\u0019\u001a%\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H(0;HHø\u0001\u0000¢\u0006\u0002\u0010R\u001aj\u0010x\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(\"\u0010\b\u0002\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H(0O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2'\u0010\u0019\u001a#\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0;HHø\u0001\u0000¢\u0006\u0002\u0010P\u001ah\u0010x\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(\"\u000e\b\u0002\u0010N*\b\u0012\u0004\u0012\u0002H(0Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2'\u0010\u0019\u001a#\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0;HHø\u0001\u0000¢\u0006\u0002\u0010R\u001a`\u0010y\u001a\b\u0012\u0004\u0012\u0002H(0\f\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010(*\u00020=*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092$\u0010\u0019\u001a \b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H(0<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001a[\u0010z\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010(*\u00020=\"\u0010\b\u0002\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H(0O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H(0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aY\u0010z\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010(*\u00020=\"\u000e\b\u0002\u0010N*\b\u0012\u0004\u0012\u0002H(0Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H(0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Z\u001aU\u0010{\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(\"\u0010\b\u0002\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H(0O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aS\u0010{\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(\"\u000e\b\u0002\u0010N*\b\u0012\u0004\u0012\u0002H(0Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HN2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Z\u001aG\u0010|\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H(0}*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aB\u0010~\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001c\u0010\u001a\u0018\u0012\u0006\b\u0000\u0012\u0002H\u00100\u0001j\u000b\u0012\u0006\b\u0000\u0012\u0002H\u0010`\u0001H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001aH\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H(0}*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aC\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001c\u0010\u001a\u0018\u0012\u0006\b\u0000\u0012\u0002H\u00100\u0001j\u000b\u0012\u0006\b\u0000\u0012\u0002H\u0010`\u0001H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a\"\u0010\u0001\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a6\u0010\u0001\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aN\u0010\u0001\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100i\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100i0\u001a\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a[\u0010\u0001\u001a\u0003H\u0001\"\u0005\b\u0000\u0010\u0001\"\t\b\u0001\u0010\u0010*\u0003H\u0001*\b\u0012\u0004\u0012\u0002H\u00100\f2)\u0010c\u001a%\u0012\u0014\u0012\u0012H\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(d\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u0003H\u00010;HHø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001ap\u0010\u0001\u001a\u0003H\u0001\"\u0005\b\u0000\u0010\u0001\"\t\b\u0001\u0010\u0010*\u0003H\u0001*\b\u0012\u0004\u0012\u0002H\u00100\f2>\u0010c\u001a:\u0012\u0013\u0012\u001105¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(C\u0012\u0014\u0012\u0012H\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(d\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u0003H\u00010KHHø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a%\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\b\b\u0000\u0010\u0010*\u00020=*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\fH\u0007\u001a\"\u0010\u0001\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a6\u0010\u0001\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a$\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a8\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a6\u0010\u0001\u001a\u000205\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002050\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a8\u0010\u0001\u001a\u00030\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0013\u0010:\u001a\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u00030\u00010\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a1\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010@\u001a\u0002052\b\b\u0002\u00108\u001a\u000209H\u0007\u001aU\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u0002092\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0<\u0012\u0006\u0012\u0004\u0018\u00010=0;H\u0007ø\u0001\u0000¢\u0006\u0002\u0010>\u001a:\u0010\u0001\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010N*\b\u0012\u0004\u0012\u0002H\u00100Q*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HNH@ø\u0001\u0000¢\u0006\u0002\u0010W\u001a<\u0010\u0001\u001a\u0002HN\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010N*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100O*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HNH@ø\u0001\u0000¢\u0006\u0002\u0010V\u001a(\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100i\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a@\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001aW\u0010\u0001\u001a\u0002H \"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018\"\u0018\b\u0002\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\f2\u0006\u0010\"\u001a\u0002H H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a(\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100k\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0001\u001a\t\u0012\u0004\u0012\u0002H\u00100\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0001\u001a\t\u0012\u0004\u0012\u0002H\u00100\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a/\u0010\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u0010020\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00108\u001a\u000209H\u0007\u001aA\u0010\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H(0\u001a0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(*\b\u0012\u0004\u0012\u0002H\u00100\f2\r\u0010 \u0001\u001a\b\u0012\u0004\u0012\u0002H(0\fH\u0004\u001a~\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00180\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010(\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\r\u0010 \u0001\u001a\b\u0012\u0004\u0012\u0002H(0\f2\b\b\u0002\u00108\u001a\u00020928\u0010\u0019\u001a4\u0012\u0014\u0012\u0012H\u0010¢\u0006\r\b\u0005\u0012\t\b\u0006\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u0012H(¢\u0006\r\b\u0005\u0012\t\b\u0006\u0012\u0005\b\b(¢\u0001\u0012\u0004\u0012\u0002H\u00180;H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006£\u0001"}, mo37405d2 = {"DEFAULT_CLOSE_MESSAGE", "", "consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "all", "", "E", "predicate", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "any", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateByTo", "M", "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateTo", "cancelConsumed", "consume", "R", "Lkotlinx/coroutines/channels/BroadcastChannel;", "block", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "consumeEach", "action", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeEachIndexed", "Lkotlin/collections/IndexedValue;", "consumes", "count", "", "distinct", "distinctBy", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrElse", "defaultValue", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterIndexedTo", "C", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterNot", "filterNotNull", "filterNotNullTo", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterNotTo", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterTo", "find", "findLast", "first", "firstOrNull", "flatMap", "fold", "initial", "operation", "acc", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foldIndexed", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "groupBy", "", "groupByTo", "", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "indexOfFirst", "indexOfLast", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapIndexedNotNullTo", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "maxBy", "", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minBy", "minWith", "none", "partition", "reduce", "S", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reduceIndexed", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requireNoNulls", "single", "singleOrNull", "sumBy", "sumByDouble", "", "take", "takeWhile", "toChannel", "toCollection", "toList", "toMap", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "toMutableSet", "", "toSet", "", "withIndex", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/channels/ChannelsKt")
/* compiled from: Channels.common.kt */
final /* synthetic */ class ChannelsKt__Channels_commonKt {
    @ObsoleteCoroutinesApi
    public static final <E, R> R consume(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(broadcastChannel, "$this$consume");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        ReceiveChannel openSubscription = broadcastChannel.openSubscription();
        try {
            return function1.invoke(openSubscription);
        } finally {
            InlineMarker.finallyStart(1);
            DefaultImpls.cancel$default(openSubscription, (CancellationException) null, 1, (Object) null);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0076 A[Catch:{ all -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0082 A[Catch:{ all -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object consumeEach(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.BroadcastChannel<E> r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, kotlin.Unit> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1
            r0.<init>(r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0050
            if (r2 != r4) goto L_0x0048
            java.lang.Object r9 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.BroadcastChannel r5 = (kotlinx.coroutines.channels.BroadcastChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.BroadcastChannel r7 = (kotlinx.coroutines.channels.BroadcastChannel) r7
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0098 }
            r8 = r5
            r5 = r10
            r10 = r7
            r7 = r1
            r1 = r8
            goto L_0x007a
        L_0x0048:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.channels.ReceiveChannel r2 = r9.openSubscription()
            kotlinx.coroutines.channels.ChannelIterator r11 = r2.iterator()     // Catch:{ all -> 0x0098 }
            r6 = r1
            r5 = r2
            r1 = r9
            r9 = r11
            r11 = r10
            r10 = r1
        L_0x0061:
            r0.L$0 = r10     // Catch:{ all -> 0x0098 }
            r0.L$1 = r11     // Catch:{ all -> 0x0098 }
            r0.L$2 = r1     // Catch:{ all -> 0x0098 }
            r0.L$3 = r2     // Catch:{ all -> 0x0098 }
            r0.L$4 = r5     // Catch:{ all -> 0x0098 }
            r0.L$5 = r9     // Catch:{ all -> 0x0098 }
            r0.label = r4     // Catch:{ all -> 0x0098 }
            java.lang.Object r7 = r9.hasNext(r0)     // Catch:{ all -> 0x0098 }
            if (r7 != r6) goto L_0x0076
            return r6
        L_0x0076:
            r8 = r6
            r6 = r11
            r11 = r7
            r7 = r8
        L_0x007a:
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x0098 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x0098 }
            if (r11 == 0) goto L_0x008c
            java.lang.Object r11 = r9.next()     // Catch:{ all -> 0x0098 }
            r6.invoke(r11)     // Catch:{ all -> 0x0098 }
            r11 = r6
            r6 = r7
            goto L_0x0061
        L_0x008c:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0098 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(r2, r3, r4, r3)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r9
        L_0x0098:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(r2, r3, r4, r3)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach(kotlinx.coroutines.channels.BroadcastChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final Function1<Throwable, Unit> consumes(@NotNull ReceiveChannel<?> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$consumes");
        return new ChannelsKt__Channels_commonKt$consumes$1<>(receiveChannel);
    }

    @PublishedApi
    public static final void cancelConsumed(@NotNull ReceiveChannel<?> receiveChannel, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$cancelConsumed");
        CancellationException cancellationException = null;
        if (th != 0) {
            if (th instanceof CancellationException) {
                cancellationException = th;
            }
            cancellationException = cancellationException;
            if (cancellationException == null) {
                cancellationException = ExceptionsKt.CancellationException("Channel was consumed, consumer had failed", th);
            }
        }
        receiveChannel.cancel(cancellationException);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final Function1<Throwable, Unit> consumesAll(@NotNull ReceiveChannel<?>... receiveChannelArr) {
        Intrinsics.checkParameterIsNotNull(receiveChannelArr, "channels");
        return new ChannelsKt__Channels_commonKt$consumesAll$1<>(receiveChannelArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R> R consume(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlinx.coroutines.channels.ReceiveChannel<? extends E>, ? extends R> r3) {
        /*
            java.lang.String r0 = "$this$consume"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            java.lang.Object r3 = r3.invoke(r2)     // Catch:{ Throwable -> 0x001e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r3
        L_0x001c:
            r3 = move-exception
            goto L_0x0020
        L_0x001e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001c }
        L_0x0020:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consume(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007e A[Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008a A[Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object consumeEach(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, kotlin.Unit> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0058
            if (r2 != r3) goto L_0x0050
            java.lang.Object r8 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Throwable -> 0x004b, all -> 0x0047 }
            r7 = r4
            r4 = r9
            r9 = r6
            r6 = r1
            r1 = r7
            goto L_0x0082
        L_0x0047:
            r8 = move-exception
            r1 = r4
            goto L_0x00ad
        L_0x004b:
            r8 = move-exception
            r2 = r8
            r8 = r4
            goto L_0x00ac
        L_0x0050:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0058:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = 0
            r2 = r10
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r10 = r8.iterator()     // Catch:{ Throwable -> 0x00aa }
            r4 = r8
            r5 = r1
            r1 = r4
            r8 = r10
            r10 = r9
            r9 = r1
        L_0x0069:
            r0.L$0 = r9     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r0.L$1 = r10     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r0.L$5 = r8     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r0.label = r3     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            java.lang.Object r6 = r8.hasNext(r0)     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            if (r6 != r5) goto L_0x007e
            return r5
        L_0x007e:
            r7 = r5
            r5 = r10
            r10 = r6
            r6 = r7
        L_0x0082:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            if (r10 == 0) goto L_0x0094
            java.lang.Object r10 = r8.next()     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r5.invoke(r10)     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            r10 = r5
            r5 = r6
            goto L_0x0069
        L_0x0094:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00a2, all -> 0x00a0 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r8
        L_0x00a0:
            r8 = move-exception
            goto L_0x00ad
        L_0x00a2:
            r8 = move-exception
            r2 = r8
            r8 = r1
            goto L_0x00ac
        L_0x00a6:
            r9 = move-exception
            r1 = r8
            r8 = r9
            goto L_0x00ad
        L_0x00aa:
            r9 = move-exception
            r2 = r9
        L_0x00ac:
            throw r2     // Catch:{ all -> 0x00a6 }
        L_0x00ad:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a0 A[Catch:{ Throwable -> 0x00cf, all -> 0x00cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ab A[Catch:{ Throwable -> 0x00cf, all -> 0x00cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object consumeEachIndexed(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.collections.IndexedValue<? extends E>, kotlin.Unit> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x006a
            if (r3 != r4) goto L_0x0062
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$5
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
            java.lang.Object r10 = r1.L$1
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x005f }
            r15 = r10
            r10 = r2
            r2 = r15
            r16 = r8
            r8 = r5
            r5 = r16
            r17 = r7
            r7 = r6
            r6 = r17
            goto L_0x00a3
        L_0x0059:
            r0 = move-exception
            r15 = r7
            r7 = r6
            r6 = r15
            goto L_0x00dc
        L_0x005f:
            r0 = move-exception
            goto L_0x00da
        L_0x0062:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006a:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d2 }
            r5 = r18
            r8 = r5
            r9 = r0
            r10 = r2
            r7 = r6
            r0 = r8
            r6 = r0
            r2 = r19
        L_0x0087:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.L$2 = r9     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.L$3 = r5     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.L$4 = r6     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.L$7 = r3     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r1.label = r4     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            java.lang.Object r11 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            if (r11 != r10) goto L_0x00a0
            return r10
        L_0x00a0:
            r15 = r11
            r11 = r0
            r0 = r15
        L_0x00a3:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            if (r0 == 0) goto L_0x00bf
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            kotlin.collections.IndexedValue r12 = new kotlin.collections.IndexedValue     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            int r13 = r9.element     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            int r14 = r13 + 1
            r9.element = r14     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r12.<init>(r13, r0)     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r2.invoke(r12)     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0 = r11
            goto L_0x0087
        L_0x00bf:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00cd:
            r0 = move-exception
            goto L_0x00dc
        L_0x00cf:
            r0 = move-exception
            r7 = r6
            goto L_0x00da
        L_0x00d2:
            r0 = move-exception
            r7 = r6
            r6 = r18
            goto L_0x00dc
        L_0x00d7:
            r0 = move-exception
            r7 = r18
        L_0x00da:
            r6 = r0
            throw r6     // Catch:{ all -> 0x0059 }
        L_0x00dc:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEachIndexed(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a2 A[Catch:{ Throwable -> 0x0059 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object elementAt(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, int r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r18) {
        /*
            r0 = r17
            r1 = r18
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1 r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1 r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1
            r2.<init>(r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 46
            java.lang.String r6 = "ReceiveChannel doesn't contain element at index "
            r7 = 1
            if (r4 == 0) goto L_0x0065
            if (r4 != r7) goto L_0x005d
            int r0 = r2.I$1
            java.lang.Object r4 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r8 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r2.L$3
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$1
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            int r12 = r2.I$0
            java.lang.Object r13 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ Throwable -> 0x0059 }
            r15 = r13
            r13 = r1
            r1 = r12
            r12 = r3
            r3 = r11
            r11 = r8
            r8 = r2
            r2 = r15
            goto L_0x009a
        L_0x0056:
            r0 = move-exception
            goto L_0x010b
        L_0x0059:
            r0 = move-exception
        L_0x005a:
            r9 = r0
            goto L_0x010a
        L_0x005d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0065:
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = 0
            r9 = r1
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            if (r0 < 0) goto L_0x00e7
            r1 = 0
            kotlinx.coroutines.channels.ChannelIterator r4 = r16.iterator()     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            r11 = 0
            r8 = r16
            r10 = r8
            r1 = r0
            r12 = r3
            r0 = r10
            r3 = r0
        L_0x007b:
            r2.L$0 = r0     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.I$0 = r1     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.L$1 = r3     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.L$2 = r8     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.L$3 = r9     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.L$4 = r10     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.L$5 = r4     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.I$1 = r11     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r2.label = r7     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            java.lang.Object r13 = r4.hasNext(r2)     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            if (r13 != r12) goto L_0x0094
            return r12
        L_0x0094:
            r15 = r2
            r2 = r0
            r0 = r11
            r11 = r10
            r10 = r8
            r8 = r15
        L_0x009a:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x0059 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x0059 }
            if (r13 == 0) goto L_0x00b4
            java.lang.Object r13 = r4.next()     // Catch:{ Throwable -> 0x0059 }
            int r14 = r0 + 1
            if (r1 != r0) goto L_0x00ae
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r9)
            return r13
        L_0x00ae:
            r0 = r2
            r2 = r8
            r8 = r10
            r10 = r11
            r11 = r14
            goto L_0x007b
        L_0x00b4:
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)     // Catch:{ Throwable -> 0x0059 }
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ Throwable -> 0x0059 }
            r0.intValue()     // Catch:{ Throwable -> 0x0059 }
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ Throwable -> 0x0059 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0059 }
            r2.<init>()     // Catch:{ Throwable -> 0x0059 }
            r2.append(r6)     // Catch:{ Throwable -> 0x0059 }
            r2.append(r1)     // Catch:{ Throwable -> 0x0059 }
            r2.append(r5)     // Catch:{ Throwable -> 0x0059 }
            java.lang.String r1 = r2.toString()     // Catch:{ Throwable -> 0x0059 }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0059 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ Throwable -> 0x0059 }
            throw r0     // Catch:{ Throwable -> 0x0059 }
        L_0x00d7:
            r0 = move-exception
            r10 = r8
            goto L_0x010b
        L_0x00da:
            r0 = move-exception
            r9 = r0
            r10 = r8
            goto L_0x010a
        L_0x00de:
            r0 = move-exception
            r10 = r16
            goto L_0x010b
        L_0x00e2:
            r0 = move-exception
            r10 = r16
            goto L_0x005a
        L_0x00e7:
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r17)     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            r1.intValue()     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            r2.<init>()     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            r2.append(r6)     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            r2.append(r0)     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            r2.append(r5)     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
            throw r1     // Catch:{ Throwable -> 0x00e2, all -> 0x00de }
        L_0x010a:
            throw r9     // Catch:{ all -> 0x0056 }
        L_0x010b:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAt(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009e A[Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ab A[Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object elementAtOrElse(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, int r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x005c
            if (r2 != r3) goto L_0x0054
            java.lang.Object r10 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            int r11 = r0.I$1
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x004f, all -> 0x004b }
            r9 = r4
            r4 = r12
            r12 = r6
            r6 = r1
            r1 = r9
            goto L_0x00a3
        L_0x004b:
            r10 = move-exception
            r1 = r4
            goto L_0x00e2
        L_0x004f:
            r10 = move-exception
            r2 = r10
            r10 = r4
            goto L_0x00e1
        L_0x0054:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x005c:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            r2 = r13
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            if (r11 >= 0) goto L_0x0078
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)     // Catch:{ Throwable -> 0x00df }
            java.lang.Object r11 = r12.invoke(r11)     // Catch:{ Throwable -> 0x00df }
            r12 = 4
            kotlin.jvm.internal.InlineMarker.finallyStart(r12)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r12)
            return r11
        L_0x0078:
            r13 = 0
            kotlinx.coroutines.channels.ChannelIterator r4 = r10.iterator()     // Catch:{ Throwable -> 0x00df }
            r13 = r12
            r6 = r1
            r5 = 0
            r1 = r10
            r12 = r11
            r11 = r1
            r10 = r4
            r4 = r11
        L_0x0085:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.I$0 = r12     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.I$1 = r5     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.L$5 = r10     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.label = r3     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            if (r7 != r6) goto L_0x009e
            return r6
        L_0x009e:
            r9 = r7
            r7 = r11
            r11 = r5
            r5 = r13
            r13 = r9
        L_0x00a3:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            if (r13 == 0) goto L_0x00c2
            java.lang.Object r13 = r10.next()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            int r8 = r11 + 1
            if (r12 != r11) goto L_0x00be
            r10 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r10)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r10)
            return r13
        L_0x00be:
            r13 = r5
            r11 = r7
            r5 = r8
            goto L_0x0085
        L_0x00c2:
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.Object r10 = r5.invoke(r10)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r11 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r11)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r11)
            return r10
        L_0x00d5:
            r10 = move-exception
            goto L_0x00e2
        L_0x00d7:
            r10 = move-exception
            r2 = r10
            r10 = r1
            goto L_0x00e1
        L_0x00db:
            r11 = move-exception
            r1 = r10
            r10 = r11
            goto L_0x00e2
        L_0x00df:
            r11 = move-exception
            r2 = r11
        L_0x00e1:
            throw r2     // Catch:{ all -> 0x00db }
        L_0x00e2:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrElse(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0086 A[Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0094 A[Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object elementAtOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, int r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0057
            if (r2 != r4) goto L_0x004f
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            int r12 = r0.I$1
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x004a, all -> 0x0046 }
            r10 = r6
            r6 = r1
            r1 = r10
            goto L_0x008c
        L_0x0046:
            r11 = move-exception
            r1 = r6
            goto L_0x00b6
        L_0x004a:
            r11 = move-exception
            r5 = r11
            r11 = r6
            goto L_0x00b5
        L_0x004f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r13)
            r5 = r3
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            if (r12 >= 0) goto L_0x0063
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r11, r5)
            return r3
        L_0x0063:
            r13 = 0
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ Throwable -> 0x00b3 }
            r13 = r12
            r7 = r1
            r6 = 0
            r12 = r11
            r1 = r12
            r11 = r2
            r2 = r1
        L_0x006f:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.I$0 = r13     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$1 = r1     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$2 = r5     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.I$1 = r6     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$4 = r11     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.label = r4     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            java.lang.Object r8 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            if (r8 != r7) goto L_0x0086
            return r7
        L_0x0086:
            r10 = r8
            r8 = r12
            r12 = r6
            r6 = r7
            r7 = r13
            r13 = r10
        L_0x008c:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            if (r13 == 0) goto L_0x00a5
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            int r9 = r12 + 1
            if (r7 != r12) goto L_0x00a0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r5)
            return r13
        L_0x00a0:
            r13 = r7
            r12 = r8
            r7 = r6
            r6 = r9
            goto L_0x006f
        L_0x00a5:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r5)
            return r3
        L_0x00a9:
            r11 = move-exception
            goto L_0x00b6
        L_0x00ab:
            r11 = move-exception
            r5 = r11
            r11 = r1
            goto L_0x00b5
        L_0x00af:
            r12 = move-exception
            r1 = r11
            r11 = r12
            goto L_0x00b6
        L_0x00b3:
            r12 = move-exception
            r5 = r12
        L_0x00b5:
            throw r5     // Catch:{ all -> 0x00af }
        L_0x00b6:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r5)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0053, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        r2 = r13;
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00cb, code lost:
        r6 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0056 A[ExcHandler: Throwable (r13v11 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r5 
      PHI: (r5v7 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r5v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:33:0x00b9, B:34:?, B:22:0x0076, B:10:0x0047, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008f A[Catch:{ Throwable -> 0x0056, all -> 0x00c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009b A[Catch:{ Throwable -> 0x0056, all -> 0x00c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9 A[SYNTHETIC, Splitter:B:33:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object find(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0063
            if (r2 != r4) goto L_0x005b
            java.lang.Object r13 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0056, all -> 0x0053 }
            r11 = r7
            r7 = r14
            r14 = r9
            r9 = r1
            r1 = r11
            r12 = r6
            r6 = r2
            r2 = r12
            goto L_0x0093
        L_0x0053:
            r13 = move-exception
            goto L_0x00cb
        L_0x0056:
            r13 = move-exception
            r2 = r13
            r13 = r5
            goto L_0x00cf
        L_0x005b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r15)
            r2 = r3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r15 = r13.iterator()     // Catch:{ Throwable -> 0x00cd }
            r5 = r13
            r7 = r5
            r8 = r1
            r6 = r2
            r1 = r7
            r2 = r1
            r13 = r15
            r15 = r14
            r14 = r2
        L_0x0076:
            r0.L$0 = r14     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.L$1 = r15     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.L$4 = r5     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.L$5 = r6     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.L$6 = r7     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.L$7 = r13     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            r0.label = r4     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            java.lang.Object r9 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            if (r9 != r8) goto L_0x008f
            return r8
        L_0x008f:
            r11 = r8
            r8 = r15
            r15 = r9
            r9 = r11
        L_0x0093:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            if (r15 == 0) goto L_0x00b9
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            if (r10 == 0) goto L_0x00b6
            r13 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r13)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r13)
            goto L_0x00c5
        L_0x00b6:
            r15 = r8
            r8 = r9
            goto L_0x0076
        L_0x00b9:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0056, all -> 0x00c6 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            r15 = r3
        L_0x00c5:
            return r15
        L_0x00c6:
            r13 = move-exception
            goto L_0x00d0
        L_0x00c8:
            r14 = move-exception
            r5 = r13
            r13 = r14
        L_0x00cb:
            r6 = r2
            goto L_0x00d0
        L_0x00cd:
            r14 = move-exception
            r2 = r14
        L_0x00cf:
            throw r2     // Catch:{ all -> 0x00c8 }
        L_0x00d0:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.find(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0056, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0059, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005a, code lost:
        r2 = r13;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d0, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0059 A[ExcHandler: Throwable (r13v11 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r4 
      PHI: (r4v7 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r4v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r4v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r4v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:22:0x0081, B:10:0x004a, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c A[Catch:{ Throwable -> 0x0059, all -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8 A[Catch:{ Throwable -> 0x0059, all -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object findLast(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0066
            if (r2 != r3) goto L_0x005e
            java.lang.Object r13 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x0056 }
            r11 = r7
            r7 = r14
            r14 = r9
            r9 = r1
            r1 = r11
            r12 = r5
            r5 = r2
            r2 = r12
            goto L_0x00a0
        L_0x0056:
            r13 = move-exception
            goto L_0x00d0
        L_0x0059:
            r13 = move-exception
            r2 = r13
            r13 = r4
            goto L_0x00d4
        L_0x005e:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0066:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlin.jvm.internal.Ref$ObjectRef r15 = new kotlin.jvm.internal.Ref$ObjectRef
            r15.<init>()
            r2 = 0
            r15.element = r2
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r13.iterator()     // Catch:{ Throwable -> 0x00d2 }
            r7 = r13
            r6 = r15
            r8 = r1
            r5 = r2
            r1 = r7
            r2 = r1
            r15 = r14
            r13 = r4
            r14 = r2
            r4 = r14
        L_0x0081:
            r0.L$0 = r14     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$1 = r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$7 = r7     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.L$8 = r13     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            r0.label = r3     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            java.lang.Object r9 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            if (r9 != r8) goto L_0x009c
            return r8
        L_0x009c:
            r11 = r8
            r8 = r15
            r15 = r9
            r9 = r11
        L_0x00a0:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            if (r15 == 0) goto L_0x00bd
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            if (r10 == 0) goto L_0x00ba
            r6.element = r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
        L_0x00ba:
            r15 = r8
            r8 = r9
            goto L_0x0081
        L_0x00bd:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0059, all -> 0x00cb }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            T r13 = r6.element
            return r13
        L_0x00cb:
            r13 = move-exception
            goto L_0x00d5
        L_0x00cd:
            r14 = move-exception
            r4 = r13
            r13 = r14
        L_0x00d0:
            r5 = r2
            goto L_0x00d5
        L_0x00d2:
            r14 = move-exception
            r2 = r14
        L_0x00d4:
            throw r2     // Catch:{ all -> 0x00cd }
        L_0x00d5:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.findLast(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[Catch:{ Throwable -> 0x0040, all -> 0x003e }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007e A[SYNTHETIC, Splitter:B:29:0x007e] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object first(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004c
            if (r2 != r3) goto L_0x0044
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r1 = r0.L$2
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
            goto L_0x006e
        L_0x003e:
            r5 = move-exception
            goto L_0x0093
        L_0x0040:
            r5 = move-exception
            r1 = r5
            r5 = r2
            goto L_0x008f
        L_0x0044:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x004c:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r0.L$0 = r5     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r0.L$1 = r5     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r0.label = r3     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            java.lang.Object r0 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            if (r0 != r1) goto L_0x0069
            return r1
        L_0x0069:
            r1 = r6
            r6 = r0
            r4 = r2
            r2 = r5
            r5 = r4
        L_0x006e:
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
            boolean r6 = r6.booleanValue()     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
            if (r6 == 0) goto L_0x007e
            java.lang.Object r5 = r5.next()     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r1)
            return r5
        L_0x007e:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
            java.lang.String r6 = "ReceiveChannel is empty."
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
            throw r5     // Catch:{ Throwable -> 0x0040, all -> 0x003e }
        L_0x0088:
            r0 = move-exception
            r2 = r5
            r1 = r6
            r5 = r0
            goto L_0x0093
        L_0x008d:
            r6 = move-exception
            r1 = r6
        L_0x008f:
            throw r1     // Catch:{ all -> 0x0090 }
        L_0x0090:
            r6 = move-exception
            r2 = r5
            r5 = r6
        L_0x0093:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object first(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0054, all -> 0x004e }
            r9 = r5
            r5 = r12
            r12 = r7
            r7 = r1
            r1 = r9
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x008f
        L_0x004e:
            r11 = move-exception
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x00d9
        L_0x0054:
            r11 = move-exception
            r2 = r11
            r11 = r4
            goto L_0x00d8
        L_0x0059:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            r2 = r13
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r13 = r11.iterator()     // Catch:{ Throwable -> 0x00d6 }
            r5 = r11
            r6 = r1
            r4 = r2
            r1 = r5
            r2 = r1
            r11 = r13
            r13 = r12
            r12 = r2
        L_0x0074:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$6 = r11     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.label = r3     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r7 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r7 != r6) goto L_0x008b
            return r6
        L_0x008b:
            r9 = r6
            r6 = r13
            r13 = r7
            r7 = r9
        L_0x008f:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r13 == 0) goto L_0x00b5
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r8 = r6.invoke(r13)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r8 = r8.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r8 == 0) goto L_0x00b2
            r11 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r11)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r11)
            return r13
        L_0x00b2:
            r13 = r6
            r6 = r7
            goto L_0x0074
        L_0x00b5:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            java.util.NoSuchElementException r11 = new java.util.NoSuchElementException
            java.lang.String r12 = "ReceiveChannel contains no element matching the predicate."
            r11.<init>(r12)
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            throw r11
        L_0x00ca:
            r11 = move-exception
            goto L_0x00d9
        L_0x00cc:
            r11 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00d8
        L_0x00d1:
            r12 = move-exception
            r4 = r2
            r2 = r11
            r11 = r12
            goto L_0x00d9
        L_0x00d6:
            r12 = move-exception
            r2 = r12
        L_0x00d8:
            throw r2     // Catch:{ all -> 0x00d1 }
        L_0x00d9:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007e A[SYNTHETIC, Splitter:B:29:0x007e] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object firstOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0050
            if (r2 != r4) goto L_0x0048
            java.lang.Object r6 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r1 = r0.L$2
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ Throwable -> 0x0044, all -> 0x003f }
            goto L_0x0072
        L_0x003f:
            r6 = move-exception
            r0 = r6
            r7 = r1
            r6 = r2
            goto L_0x008e
        L_0x0044:
            r6 = move-exception
            r1 = r6
            r6 = r2
            goto L_0x008a
        L_0x0048:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r3
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r0.L$0 = r6     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r0.L$1 = r6     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r0.label = r4     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            java.lang.Object r0 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            if (r0 != r1) goto L_0x006d
            return r1
        L_0x006d:
            r1 = r7
            r7 = r0
            r5 = r2
            r2 = r6
            r6 = r5
        L_0x0072:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ Throwable -> 0x0044, all -> 0x003f }
            boolean r7 = r7.booleanValue()     // Catch:{ Throwable -> 0x0044, all -> 0x003f }
            if (r7 != 0) goto L_0x007e
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r1)
            return r3
        L_0x007e:
            java.lang.Object r6 = r6.next()     // Catch:{ Throwable -> 0x0044, all -> 0x003f }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r1)
            return r6
        L_0x0086:
            r0 = move-exception
            goto L_0x008e
        L_0x0088:
            r7 = move-exception
            r1 = r7
        L_0x008a:
            throw r1     // Catch:{ all -> 0x008b }
        L_0x008b:
            r7 = move-exception
            r0 = r7
            r7 = r1
        L_0x008e:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object firstOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 != r4) goto L_0x005a
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0055, all -> 0x004f }
            r10 = r6
            r6 = r13
            r13 = r8
            r8 = r1
            r1 = r10
            r11 = r5
            r5 = r2
            r2 = r11
            goto L_0x008f
        L_0x004f:
            r12 = move-exception
            r10 = r5
            r5 = r2
            r2 = r10
            goto L_0x00d0
        L_0x0055:
            r12 = move-exception
            r2 = r12
            r12 = r5
            goto L_0x00cf
        L_0x005a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r14)
            r2 = r3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r14 = r12.iterator()     // Catch:{ Throwable -> 0x00cd }
            r6 = r12
            r7 = r1
            r5 = r2
            r1 = r6
            r2 = r1
            r12 = r14
            r14 = r13
            r13 = r2
        L_0x0074:
            r0.L$0 = r13     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            r0.L$4 = r5     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            r0.L$5 = r6     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            r0.label = r4     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            java.lang.Object r8 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            if (r8 != r7) goto L_0x008b
            return r7
        L_0x008b:
            r10 = r7
            r7 = r14
            r14 = r8
            r8 = r10
        L_0x008f:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            if (r14 == 0) goto L_0x00b5
            java.lang.Object r14 = r12.next()     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            java.lang.Object r9 = r7.invoke(r14)     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            boolean r9 = r9.booleanValue()     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            if (r9 == 0) goto L_0x00b2
            r12 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r12)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r12)
            return r14
        L_0x00b2:
            r14 = r7
            r7 = r8
            goto L_0x0074
        L_0x00b5:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00c3, all -> 0x00c1 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r3
        L_0x00c1:
            r12 = move-exception
            goto L_0x00d0
        L_0x00c3:
            r12 = move-exception
            r10 = r2
            r2 = r12
            r12 = r10
            goto L_0x00cf
        L_0x00c8:
            r13 = move-exception
            r5 = r2
            r2 = r12
            r12 = r13
            goto L_0x00d0
        L_0x00cd:
            r13 = move-exception
            r2 = r13
        L_0x00cf:
            throw r2     // Catch:{ all -> 0x00c8 }
        L_0x00d0:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097 A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a3 A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bf A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object indexOf(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, E r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0063
            if (r2 != r3) goto L_0x005b
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$1
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0056, all -> 0x0050 }
            r9 = r5
            r5 = r12
            r12 = r8
            r8 = r1
            r1 = r9
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x009b
        L_0x0050:
            r11 = move-exception
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x00d9
        L_0x0056:
            r11 = move-exception
            r2 = r11
            r11 = r4
            goto L_0x00d8
        L_0x005b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlin.jvm.internal.Ref$IntRef r13 = new kotlin.jvm.internal.Ref$IntRef
            r13.<init>()
            r2 = 0
            r13.element = r2
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r11.iterator()     // Catch:{ Throwable -> 0x00d6 }
            r5 = r11
            r6 = r13
            r7 = r1
            r1 = r5
            r13 = r12
            r11 = r4
            r12 = r1
            r4 = r2
            r2 = r12
        L_0x007e:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$7 = r11     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.label = r3     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r8 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r8 != r7) goto L_0x0097
            return r7
        L_0x0097:
            r9 = r7
            r7 = r13
            r13 = r8
            r8 = r9
        L_0x009b:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r13 == 0) goto L_0x00bf
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r13)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r13 == 0) goto L_0x00b7
            int r11 = r6.element     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            return r11
        L_0x00b7:
            int r13 = r6.element     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            int r13 = r13 + r3
            r6.element = r13     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r13 = r7
            r7 = r8
            goto L_0x007e
        L_0x00bf:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            r11 = -1
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)
            return r11
        L_0x00ca:
            r11 = move-exception
            goto L_0x00d9
        L_0x00cc:
            r11 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00d8
        L_0x00d1:
            r12 = move-exception
            r4 = r2
            r2 = r11
            r11 = r12
            goto L_0x00d9
        L_0x00d6:
            r12 = move-exception
            r2 = r12
        L_0x00d8:
            throw r2     // Catch:{ all -> 0x00d1 }
        L_0x00d9:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097 A[Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a5 A[Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d0 A[Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object indexOfFirst(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0063
            if (r2 != r3) goto L_0x005b
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0056, all -> 0x0050 }
            r9 = r8
            r8 = r12
            r12 = r9
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x009d
        L_0x0050:
            r11 = move-exception
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x00f0
        L_0x0056:
            r11 = move-exception
            r2 = r11
            r11 = r4
            goto L_0x00ef
        L_0x005b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlin.jvm.internal.Ref$IntRef r13 = new kotlin.jvm.internal.Ref$IntRef
            r13.<init>()
            r2 = 0
            r13.element = r2
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r11.iterator()     // Catch:{ Throwable -> 0x00ed }
            r5 = r11
            r6 = r13
            r7 = r1
            r1 = r5
            r13 = r12
            r11 = r4
            r12 = r1
            r4 = r2
            r2 = r12
        L_0x007e:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$7 = r11     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.label = r3     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            java.lang.Object r8 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            if (r8 != r7) goto L_0x0097
            return r7
        L_0x0097:
            r9 = r7
            r7 = r13
            r13 = r8
            r8 = r5
            r5 = r1
            r1 = r9
        L_0x009d:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            if (r13 == 0) goto L_0x00d0
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            java.lang.Object r13 = r7.invoke(r13)     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            if (r13 == 0) goto L_0x00c6
            int r11 = r6.element     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r12 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r12)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r12)
            return r11
        L_0x00c6:
            int r13 = r6.element     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            int r13 = r13 + r3
            r6.element = r13     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r13 = r7
            r7 = r1
            r1 = r5
            r5 = r8
            goto L_0x007e
        L_0x00d0:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            r11 = -1
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)
            return r11
        L_0x00e1:
            r11 = move-exception
            goto L_0x00f0
        L_0x00e3:
            r11 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00ef
        L_0x00e8:
            r12 = move-exception
            r4 = r2
            r2 = r11
            r11 = r12
            goto L_0x00f0
        L_0x00ed:
            r12 = move-exception
            r2 = r12
        L_0x00ef:
            throw r2     // Catch:{ all -> 0x00e8 }
        L_0x00f0:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfFirst(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a7 A[Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b3 A[Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00cf A[Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object indexOfLast(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0069
            if (r2 != r3) goto L_0x0061
            java.lang.Object r12 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x005c, all -> 0x0056 }
            r10 = r5
            r5 = r13
            r13 = r9
            r9 = r1
            r1 = r10
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x00ab
        L_0x0056:
            r12 = move-exception
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x00f2
        L_0x005c:
            r12 = move-exception
            r2 = r12
            r12 = r4
            goto L_0x00ee
        L_0x0061:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            r2 = -1
            r14.element = r2
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            r4 = 0
            r2.element = r4
            r4 = 0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlinx.coroutines.channels.ChannelIterator r5 = r12.iterator()     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r7 = r14
            r8 = r1
            r6 = r2
            r1 = r12
            r2 = r1
            r14 = r13
            r13 = r2
            r12 = r5
            r5 = r13
        L_0x008c:
            r0.L$0 = r13     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$7 = r5     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.L$8 = r12     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r0.label = r3     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            java.lang.Object r9 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            if (r9 != r8) goto L_0x00a7
            return r8
        L_0x00a7:
            r10 = r8
            r8 = r14
            r14 = r9
            r9 = r10
        L_0x00ab:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            if (r14 == 0) goto L_0x00cf
            java.lang.Object r14 = r12.next()     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            java.lang.Object r14 = r8.invoke(r14)     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            if (r14 == 0) goto L_0x00c7
            int r14 = r6.element     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r7.element = r14     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
        L_0x00c7:
            int r14 = r6.element     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            int r14 = r14 + r3
            r6.element = r14     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            r14 = r8
            r8 = r9
            goto L_0x008c
        L_0x00cf:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00e3, all -> 0x00e1 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            int r12 = r7.element
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            return r12
        L_0x00e1:
            r12 = move-exception
            goto L_0x00f2
        L_0x00e3:
            r12 = move-exception
            r10 = r2
            r2 = r12
            r12 = r10
            goto L_0x00ee
        L_0x00e8:
            r13 = move-exception
        L_0x00e9:
            r2 = r12
            r12 = r13
            goto L_0x00f2
        L_0x00ec:
            r13 = move-exception
            r2 = r13
        L_0x00ee:
            throw r2     // Catch:{ all -> 0x00ef }
        L_0x00ef:
            r13 = move-exception
            r4 = r2
            goto L_0x00e9
        L_0x00f2:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfLast(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4 A[Catch:{ Throwable -> 0x0074, all -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ce A[Catch:{ Throwable -> 0x0049, all -> 0x0044 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d8 A[SYNTHETIC, Splitter:B:49:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object last(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0079
            if (r2 == r4) goto L_0x0056
            if (r2 != r3) goto L_0x004e
            java.lang.Object r9 = r0.L$5
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Throwable -> 0x0049, all -> 0x0044 }
            goto L_0x00c6
        L_0x0044:
            r9 = move-exception
            r10 = r9
            r9 = r6
            goto L_0x00e7
        L_0x0049:
            r9 = move-exception
            r5 = r9
            r9 = r6
            goto L_0x00e6
        L_0x004e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0056:
            java.lang.Object r9 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$2
            r5 = r4
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
            goto L_0x009c
        L_0x006f:
            r9 = move-exception
            r10 = r9
            r9 = r4
            goto L_0x00e7
        L_0x0074:
            r9 = move-exception
            r5 = r9
            r9 = r4
            goto L_0x00e6
        L_0x0079:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = 0
            r5 = r10
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            kotlinx.coroutines.channels.ChannelIterator r10 = r9.iterator()     // Catch:{ Throwable -> 0x00e4 }
            r0.L$0 = r9     // Catch:{ Throwable -> 0x00e4 }
            r0.L$1 = r9     // Catch:{ Throwable -> 0x00e4 }
            r0.L$2 = r5     // Catch:{ Throwable -> 0x00e4 }
            r0.L$3 = r9     // Catch:{ Throwable -> 0x00e4 }
            r0.L$4 = r10     // Catch:{ Throwable -> 0x00e4 }
            r0.label = r4     // Catch:{ Throwable -> 0x00e4 }
            java.lang.Object r2 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00e4 }
            if (r2 != r1) goto L_0x0097
            return r1
        L_0x0097:
            r4 = r9
            r6 = r4
            r9 = r10
            r10 = r2
            r2 = r6
        L_0x009c:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
            r7 = r6
            r8 = r2
            r2 = r9
            r9 = r4
            r4 = r8
        L_0x00ad:
            r0.L$0 = r7     // Catch:{ Throwable -> 0x00e4 }
            r0.L$1 = r9     // Catch:{ Throwable -> 0x00e4 }
            r0.L$2 = r5     // Catch:{ Throwable -> 0x00e4 }
            r0.L$3 = r4     // Catch:{ Throwable -> 0x00e4 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00e4 }
            r0.L$5 = r10     // Catch:{ Throwable -> 0x00e4 }
            r0.label = r3     // Catch:{ Throwable -> 0x00e4 }
            java.lang.Object r6 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00e4 }
            if (r6 != r1) goto L_0x00c2
            return r1
        L_0x00c2:
            r8 = r6
            r6 = r9
            r9 = r10
            r10 = r8
        L_0x00c6:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x0049, all -> 0x0044 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x0049, all -> 0x0044 }
            if (r10 == 0) goto L_0x00d4
            java.lang.Object r10 = r2.next()     // Catch:{ Throwable -> 0x0049, all -> 0x0044 }
            r9 = r6
            goto L_0x00ad
        L_0x00d4:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            return r9
        L_0x00d8:
            java.util.NoSuchElementException r9 = new java.util.NoSuchElementException     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
            java.lang.String r10 = "ReceiveChannel is empty."
            r9.<init>(r10)     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
            throw r9     // Catch:{ Throwable -> 0x0074, all -> 0x006f }
        L_0x00e2:
            r10 = move-exception
            goto L_0x00e7
        L_0x00e4:
            r10 = move-exception
            r5 = r10
        L_0x00e6:
            throw r5     // Catch:{ all -> 0x00e2 }
        L_0x00e7:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r5)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a8 A[Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b4 A[Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object last(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x006a
            if (r2 != r3) goto L_0x0062
            java.lang.Object r13 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            kotlin.jvm.internal.Ref$BooleanRef r6 = (kotlin.jvm.internal.Ref.BooleanRef) r6
            java.lang.Object r7 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x005d, all -> 0x0057 }
            r11 = r5
            r5 = r14
            r14 = r9
            r9 = r1
            r1 = r11
            r12 = r4
            r4 = r2
            r2 = r12
            goto L_0x00ac
        L_0x0057:
            r13 = move-exception
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x00f6
        L_0x005d:
            r13 = move-exception
            r2 = r13
            r13 = r4
            goto L_0x00f5
        L_0x0062:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x006a:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlin.jvm.internal.Ref$ObjectRef r15 = new kotlin.jvm.internal.Ref$ObjectRef
            r15.<init>()
            r2 = 0
            r15.element = r2
            kotlin.jvm.internal.Ref$BooleanRef r4 = new kotlin.jvm.internal.Ref$BooleanRef
            r4.<init>()
            r5 = 0
            r4.element = r5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r5 = r13.iterator()     // Catch:{ Throwable -> 0x00f3 }
            r7 = r15
            r8 = r1
            r6 = r4
            r1 = r13
            r15 = r14
            r4 = r2
            r14 = r1
            r2 = r14
            r13 = r5
            r5 = r2
        L_0x008d:
            r0.L$0 = r14     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$1 = r15     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$7 = r5     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.L$8 = r13     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r0.label = r3     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            java.lang.Object r9 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            if (r9 != r8) goto L_0x00a8
            return r8
        L_0x00a8:
            r11 = r8
            r8 = r15
            r15 = r9
            r9 = r11
        L_0x00ac:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            if (r15 == 0) goto L_0x00cb
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            if (r10 == 0) goto L_0x00c8
            r7.element = r15     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            r6.element = r3     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
        L_0x00c8:
            r15 = r8
            r8 = r9
            goto L_0x008d
        L_0x00cb:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00e9, all -> 0x00e7 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            boolean r13 = r6.element
            if (r13 == 0) goto L_0x00dd
            T r13 = r7.element
            return r13
        L_0x00dd:
            java.util.NoSuchElementException r13 = new java.util.NoSuchElementException
            java.lang.String r14 = "ReceiveChannel contains no element matching the predicate."
            r13.<init>(r14)
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            throw r13
        L_0x00e7:
            r13 = move-exception
            goto L_0x00f6
        L_0x00e9:
            r13 = move-exception
            r11 = r2
            r2 = r13
            r13 = r11
            goto L_0x00f5
        L_0x00ee:
            r14 = move-exception
            r4 = r2
            r2 = r13
            r13 = r14
            goto L_0x00f6
        L_0x00f3:
            r14 = move-exception
            r2 = r14
        L_0x00f5:
            throw r2     // Catch:{ all -> 0x00ee }
        L_0x00f6:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a5 A[Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b1 A[Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c7 A[Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object lastIndexOf(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, E r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0067
            if (r2 != r3) goto L_0x005f
            java.lang.Object r12 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            java.lang.Object r8 = r0.L$1
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x005a, all -> 0x0054 }
            r10 = r5
            r5 = r13
            r13 = r9
            r9 = r1
            r1 = r10
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x00a9
        L_0x0054:
            r12 = move-exception
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x00e4
        L_0x005a:
            r12 = move-exception
            r2 = r12
            r12 = r4
            goto L_0x00e0
        L_0x005f:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            r2 = -1
            r14.element = r2
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            r4 = 0
            r2.element = r4
            r4 = 0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlinx.coroutines.channels.ChannelIterator r5 = r12.iterator()     // Catch:{ Throwable -> 0x00de, all -> 0x00da }
            r7 = r14
            r8 = r1
            r6 = r2
            r1 = r12
            r2 = r1
            r14 = r13
            r13 = r2
            r12 = r5
            r5 = r13
        L_0x008a:
            r0.L$0 = r13     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$7 = r5     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.L$8 = r12     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r0.label = r3     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            java.lang.Object r9 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            if (r9 != r8) goto L_0x00a5
            return r8
        L_0x00a5:
            r10 = r8
            r8 = r14
            r14 = r9
            r9 = r10
        L_0x00a9:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            if (r14 == 0) goto L_0x00c7
            java.lang.Object r14 = r12.next()     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r14)     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            if (r14 == 0) goto L_0x00bf
            int r14 = r6.element     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r7.element = r14     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
        L_0x00bf:
            int r14 = r6.element     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            int r14 = r14 + r3
            r6.element = r14     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            r14 = r8
            r8 = r9
            goto L_0x008a
        L_0x00c7:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00d5, all -> 0x00d3 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            int r12 = r7.element
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            return r12
        L_0x00d3:
            r12 = move-exception
            goto L_0x00e4
        L_0x00d5:
            r12 = move-exception
            r10 = r2
            r2 = r12
            r12 = r10
            goto L_0x00e0
        L_0x00da:
            r13 = move-exception
        L_0x00db:
            r2 = r12
            r12 = r13
            goto L_0x00e4
        L_0x00de:
            r13 = move-exception
            r2 = r13
        L_0x00e0:
            throw r2     // Catch:{ all -> 0x00e1 }
        L_0x00e1:
            r13 = move-exception
            r4 = r2
            goto L_0x00db
        L_0x00e4:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0065, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0066, code lost:
        r10 = r9;
        r9 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006a, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006b, code lost:
        r5 = r9;
        r9 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d1, code lost:
        r5 = r10;
        r10 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d8, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00db, code lost:
        throw r10;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:11:0x0040, B:28:0x0093] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009f A[SYNTHETIC, Splitter:B:33:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c7 A[Catch:{ Throwable -> 0x006a, all -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object lastOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x006f
            if (r2 == r5) goto L_0x004d
            if (r2 != r4) goto L_0x0045
            java.lang.Object r9 = r0.L$5
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r3 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            goto L_0x00bf
        L_0x0045:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x004d:
            java.lang.Object r9 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            goto L_0x0093
        L_0x0065:
            r9 = move-exception
            r10 = r9
            r9 = r6
            goto L_0x00d8
        L_0x006a:
            r9 = move-exception
            r5 = r9
            r9 = r6
            goto L_0x00d6
        L_0x006f:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r3
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            kotlinx.coroutines.channels.ChannelIterator r2 = r9.iterator()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            r0.L$0 = r9     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            r0.L$1 = r9     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            r0.L$3 = r9     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            r0.label = r5     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            java.lang.Object r5 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d0 }
            if (r5 != r1) goto L_0x008c
            return r1
        L_0x008c:
            r6 = r9
            r7 = r6
            r9 = r2
            r2 = r7
            r8 = r5
            r5 = r10
            r10 = r8
        L_0x0093:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            if (r10 != 0) goto L_0x009f
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            return r3
        L_0x009f:
            java.lang.Object r10 = r9.next()     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            r3 = r2
            r2 = r9
        L_0x00a5:
            r9 = r6
            r0.L$0 = r7     // Catch:{ Throwable -> 0x00d4 }
            r0.L$1 = r9     // Catch:{ Throwable -> 0x00d4 }
            r0.L$2 = r5     // Catch:{ Throwable -> 0x00d4 }
            r0.L$3 = r3     // Catch:{ Throwable -> 0x00d4 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00d4 }
            r0.L$5 = r10     // Catch:{ Throwable -> 0x00d4 }
            r0.label = r4     // Catch:{ Throwable -> 0x00d4 }
            java.lang.Object r6 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00d4 }
            if (r6 != r1) goto L_0x00bb
            return r1
        L_0x00bb:
            r8 = r6
            r6 = r9
            r9 = r10
            r10 = r8
        L_0x00bf:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            if (r10 == 0) goto L_0x00cc
            java.lang.Object r10 = r2.next()     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            goto L_0x00a5
        L_0x00cc:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            return r9
        L_0x00d0:
            r0 = move-exception
            r5 = r10
            r10 = r0
            goto L_0x00d8
        L_0x00d4:
            r10 = move-exception
            r5 = r10
        L_0x00d6:
            throw r5     // Catch:{ all -> 0x00d7 }
        L_0x00d7:
            r10 = move-exception
        L_0x00d8:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r5)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098 A[Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a4 A[Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object lastOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0065
            if (r2 != r3) goto L_0x005d
            java.lang.Object r12 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0058, all -> 0x0052 }
            r10 = r5
            r5 = r13
            r13 = r8
            r8 = r1
            r1 = r10
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x009c
        L_0x0052:
            r12 = move-exception
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x00d6
        L_0x0058:
            r12 = move-exception
            r2 = r12
            r12 = r4
            goto L_0x00d5
        L_0x005d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0065:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$ObjectRef r14 = new kotlin.jvm.internal.Ref$ObjectRef
            r14.<init>()
            r2 = 0
            r14.element = r2
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r12.iterator()     // Catch:{ Throwable -> 0x00d3 }
            r5 = r12
            r6 = r14
            r7 = r1
            r1 = r5
            r14 = r13
            r12 = r4
            r13 = r1
            r4 = r2
            r2 = r13
        L_0x007f:
            r0.L$0 = r13     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.L$7 = r12     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            r0.label = r3     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            java.lang.Object r8 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            if (r8 != r7) goto L_0x0098
            return r7
        L_0x0098:
            r10 = r7
            r7 = r14
            r14 = r8
            r8 = r10
        L_0x009c:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            if (r14 == 0) goto L_0x00b9
            java.lang.Object r14 = r12.next()     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            java.lang.Object r9 = r7.invoke(r14)     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            boolean r9 = r9.booleanValue()     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            if (r9 == 0) goto L_0x00b6
            r6.element = r14     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
        L_0x00b6:
            r14 = r7
            r7 = r8
            goto L_0x007f
        L_0x00b9:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00c9, all -> 0x00c7 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            T r12 = r6.element
            return r12
        L_0x00c7:
            r12 = move-exception
            goto L_0x00d6
        L_0x00c9:
            r12 = move-exception
            r10 = r2
            r2 = r12
            r12 = r10
            goto L_0x00d5
        L_0x00ce:
            r13 = move-exception
            r4 = r2
            r2 = r12
            r12 = r13
            goto L_0x00d6
        L_0x00d3:
            r13 = move-exception
            r2 = r13
        L_0x00d5:
            throw r2     // Catch:{ all -> 0x00ce }
        L_0x00d6:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a5 A[Catch:{ Throwable -> 0x0074, all -> 0x0071 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cc A[SYNTHETIC, Splitter:B:44:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d6 A[SYNTHETIC, Splitter:B:47:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object single(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0079
            if (r2 == r4) goto L_0x0059
            if (r2 != r3) goto L_0x0051
            java.lang.Object r8 = r0.L$5
            java.lang.Object r1 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r1 = r0.L$2
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x00c0
        L_0x0047:
            r8 = move-exception
            r4 = r1
            r5 = r2
            goto L_0x00ec
        L_0x004c:
            r8 = move-exception
            r1 = r8
            r8 = r2
            goto L_0x00e7
        L_0x0051:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0059:
            java.lang.Object r8 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            goto L_0x009d
        L_0x0071:
            r8 = move-exception
            goto L_0x00ec
        L_0x0074:
            r8 = move-exception
            r1 = r8
            r8 = r5
            goto L_0x00e7
        L_0x0079:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = 0
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            r0.L$0 = r8     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            r0.L$1 = r8     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            r0.L$2 = r9     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            r0.L$3 = r8     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            r0.label = r4     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            java.lang.Object r4 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00e5, all -> 0x00e0 }
            if (r4 != r1) goto L_0x0096
            return r1
        L_0x0096:
            r5 = r8
            r6 = r5
            r8 = r2
            r2 = r6
            r7 = r4
            r4 = r9
            r9 = r7
        L_0x009d:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            boolean r9 = r9.booleanValue()     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            if (r9 == 0) goto L_0x00d6
            java.lang.Object r9 = r8.next()     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r0.L$0 = r6     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r0.L$1 = r5     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r0.L$2 = r4     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r0.L$4 = r8     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r0.L$5 = r9     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r0.label = r3     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            java.lang.Object r8 = r8.hasNext(r0)     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            if (r8 != r1) goto L_0x00be
            return r1
        L_0x00be:
            r1 = r4
            r2 = r5
        L_0x00c0:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            boolean r8 = r8.booleanValue()     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            if (r8 != 0) goto L_0x00cc
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r1)
            return r9
        L_0x00cc:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            java.lang.String r9 = "ReceiveChannel has more than one element."
            r8.<init>(r9)     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            java.lang.Throwable r8 = (java.lang.Throwable) r8     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            throw r8     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
        L_0x00d6:
            java.util.NoSuchElementException r8 = new java.util.NoSuchElementException     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            java.lang.String r9 = "ReceiveChannel is empty."
            r8.<init>(r9)     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            java.lang.Throwable r8 = (java.lang.Throwable) r8     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            throw r8     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
        L_0x00e0:
            r0 = move-exception
            r5 = r8
            r4 = r9
            r8 = r0
            goto L_0x00ec
        L_0x00e5:
            r9 = move-exception
            r1 = r9
        L_0x00e7:
            throw r1     // Catch:{ all -> 0x00e8 }
        L_0x00e8:
            r9 = move-exception
            r5 = r8
            r8 = r9
            r4 = r1
        L_0x00ec:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r4)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a6 A[Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b4 A[Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object single(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0068
            if (r2 != r3) goto L_0x0060
            java.lang.Object r13 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            kotlin.jvm.internal.Ref$BooleanRef r6 = (kotlin.jvm.internal.Ref.BooleanRef) r6
            java.lang.Object r7 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x005b, all -> 0x0055 }
            r11 = r9
            r9 = r14
            r14 = r11
            r12 = r4
            r4 = r2
            r2 = r12
            goto L_0x00ac
        L_0x0055:
            r13 = move-exception
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x0107
        L_0x005b:
            r13 = move-exception
            r2 = r13
            r13 = r4
            goto L_0x0106
        L_0x0060:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0068:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlin.jvm.internal.Ref$ObjectRef r15 = new kotlin.jvm.internal.Ref$ObjectRef
            r15.<init>()
            r2 = 0
            r15.element = r2
            kotlin.jvm.internal.Ref$BooleanRef r4 = new kotlin.jvm.internal.Ref$BooleanRef
            r4.<init>()
            r5 = 0
            r4.element = r5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r5 = r13.iterator()     // Catch:{ Throwable -> 0x0104 }
            r7 = r15
            r8 = r1
            r6 = r4
            r1 = r13
            r15 = r14
            r4 = r2
            r14 = r1
            r2 = r14
            r13 = r5
            r5 = r2
        L_0x008b:
            r0.L$0 = r14     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$1 = r15     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$7 = r5     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.L$8 = r13     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r0.label = r3     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            java.lang.Object r9 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            if (r9 != r8) goto L_0x00a6
            return r8
        L_0x00a6:
            r11 = r8
            r8 = r15
            r15 = r9
            r9 = r5
            r5 = r1
            r1 = r11
        L_0x00ac:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            if (r15 == 0) goto L_0x00dc
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            if (r10 == 0) goto L_0x00d7
            boolean r10 = r6.element     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            if (r10 != 0) goto L_0x00cd
            r7.element = r15     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            r6.element = r3     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            goto L_0x00d7
        L_0x00cd:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            java.lang.String r14 = "ReceiveChannel contains more than one matching element."
            r13.<init>(r14)     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            java.lang.Throwable r13 = (java.lang.Throwable) r13     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            throw r13     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
        L_0x00d7:
            r15 = r8
            r8 = r1
            r1 = r5
            r5 = r9
            goto L_0x008b
        L_0x00dc:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00fa, all -> 0x00f8 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            boolean r13 = r6.element
            if (r13 == 0) goto L_0x00ee
            T r13 = r7.element
            return r13
        L_0x00ee:
            java.util.NoSuchElementException r13 = new java.util.NoSuchElementException
            java.lang.String r14 = "ReceiveChannel contains no element matching the predicate."
            r13.<init>(r14)
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            throw r13
        L_0x00f8:
            r13 = move-exception
            goto L_0x0107
        L_0x00fa:
            r13 = move-exception
            r11 = r2
            r2 = r13
            r13 = r11
            goto L_0x0106
        L_0x00ff:
            r14 = move-exception
            r4 = r2
            r2 = r13
            r13 = r14
            goto L_0x0107
        L_0x0104:
            r14 = move-exception
            r2 = r14
        L_0x0106:
            throw r2     // Catch:{ all -> 0x00ff }
        L_0x0107:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00aa A[SYNTHETIC, Splitter:B:37:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object singleOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x007a
            if (r2 == r4) goto L_0x005a
            if (r2 != r3) goto L_0x0052
            java.lang.Object r9 = r0.L$5
            java.lang.Object r1 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r1 = r0.L$2
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            r8 = r10
            r10 = r9
            r9 = r8
            goto L_0x00c5
        L_0x0048:
            r9 = move-exception
            r4 = r1
            r6 = r2
            goto L_0x00e1
        L_0x004d:
            r9 = move-exception
            r1 = r9
            r9 = r2
            goto L_0x00dc
        L_0x0052:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x005a:
            java.lang.Object r9 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            goto L_0x009e
        L_0x0072:
            r9 = move-exception
            goto L_0x00e1
        L_0x0075:
            r9 = move-exception
            r1 = r9
            r9 = r6
            goto L_0x00dc
        L_0x007a:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r5
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            kotlinx.coroutines.channels.ChannelIterator r2 = r9.iterator()     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            r0.L$0 = r9     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            r0.L$1 = r9     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            r0.L$3 = r9     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            r0.label = r4     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            java.lang.Object r4 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            if (r4 != r1) goto L_0x0097
            return r1
        L_0x0097:
            r6 = r9
            r7 = r6
            r9 = r2
            r2 = r7
            r8 = r4
            r4 = r10
            r10 = r8
        L_0x009e:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            if (r10 != 0) goto L_0x00aa
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r4)
            return r5
        L_0x00aa:
            java.lang.Object r10 = r9.next()     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            r0.L$0 = r7     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            r0.L$1 = r6     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            r0.L$2 = r4     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            r0.L$4 = r9     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            r0.L$5 = r10     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            r0.label = r3     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            java.lang.Object r9 = r9.hasNext(r0)     // Catch:{ Throwable -> 0x0075, all -> 0x0072 }
            if (r9 != r1) goto L_0x00c3
            return r1
        L_0x00c3:
            r1 = r4
            r2 = r6
        L_0x00c5:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            boolean r9 = r9.booleanValue()     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            if (r9 == 0) goto L_0x00d1
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r1)
            return r5
        L_0x00d1:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r1)
            return r10
        L_0x00d5:
            r0 = move-exception
            r6 = r9
            r4 = r10
            r9 = r0
            goto L_0x00e1
        L_0x00da:
            r10 = move-exception
            r1 = r10
        L_0x00dc:
            throw r1     // Catch:{ all -> 0x00dd }
        L_0x00dd:
            r10 = move-exception
            r6 = r9
            r9 = r10
            r4 = r1
        L_0x00e1:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r4)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b3 A[Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bc A[Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ef A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object singleOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0074
            if (r3 != r5) goto L_0x006c
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0069 }
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r12
            r12 = r2
            r2 = r16
            r17 = r9
            r9 = r6
            r6 = r17
            r18 = r8
            r8 = r7
            r7 = r18
            goto L_0x00b4
        L_0x0063:
            r0 = move-exception
            r15 = r8
            r8 = r7
            r7 = r15
            goto L_0x0102
        L_0x0069:
            r0 = move-exception
            goto L_0x0100
        L_0x006c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0074:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r0.element = r4
            kotlin.jvm.internal.Ref$BooleanRef r3 = new kotlin.jvm.internal.Ref$BooleanRef
            r3.<init>()
            r6 = 0
            r3.element = r6
            r7 = r4
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r6 = r19.iterator()     // Catch:{ Throwable -> 0x00fd, all -> 0x00f8 }
            r9 = r19
            r11 = r0
            r12 = r2
            r10 = r3
            r3 = r6
            r8 = r7
            r0 = r9
            r6 = r0
            r7 = r6
            r2 = r20
        L_0x0099:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$2 = r11     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$3 = r10     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$4 = r6     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$7 = r9     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.L$8 = r3     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r1.label = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            java.lang.Object r13 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            if (r13 != r12) goto L_0x00b4
            return r12
        L_0x00b4:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            if (r13 == 0) goto L_0x00e0
            java.lang.Object r13 = r3.next()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            java.lang.Object r14 = r2.invoke(r13)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            if (r14 == 0) goto L_0x0099
            boolean r14 = r10.element     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            if (r14 == 0) goto L_0x00db
            r0 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            return r4
        L_0x00db:
            r11.element = r13     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            r10.element = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            goto L_0x0099
        L_0x00e0:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00f5, all -> 0x00f3 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            boolean r0 = r10.element
            if (r0 != 0) goto L_0x00f0
            return r4
        L_0x00f0:
            T r0 = r11.element
            return r0
        L_0x00f3:
            r0 = move-exception
            goto L_0x0102
        L_0x00f5:
            r0 = move-exception
            r8 = r7
            goto L_0x0100
        L_0x00f8:
            r0 = move-exception
            r8 = r7
            r7 = r19
            goto L_0x0102
        L_0x00fd:
            r0 = move-exception
            r8 = r19
        L_0x0100:
            r7 = r0
            throw r7     // Catch:{ all -> 0x0063 }
        L_0x0102:
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.drop(receiveChannel, i, coroutineContext);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> drop(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$drop");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$drop$1(receiveChannel, i, null), 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.dropWhile(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> dropWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$dropWhile");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$dropWhile$1(receiveChannel, function2, null), 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> filter(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$filter");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$filter$1(receiveChannel, function2, null), 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterIndexed(receiveChannel, coroutineContext, function3);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> filterIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$filterIndexed");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$filterIndexed$1(receiveChannel, function3, null), 2, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bb A[Catch:{ Throwable -> 0x00ff, all -> 0x00fd }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c4 A[Catch:{ Throwable -> 0x00ff, all -> 0x00fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r21, @org.jetbrains.annotations.NotNull C r22, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, java.lang.Boolean> r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r24) {
        /*
            r0 = r24
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x007f
            if (r3 != r4) goto L_0x0077
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$7
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            java.util.Collection r12 = (java.util.Collection) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0072, all -> 0x006d }
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r12
            r12 = r2
            r2 = r17
            r18 = r11
            r11 = r5
            r5 = r18
            r19 = r10
            r10 = r6
            r6 = r19
            r20 = r8
            r8 = r7
            r7 = r20
            goto L_0x00bc
        L_0x006d:
            r0 = move-exception
            r10 = r6
            r8 = r7
            goto L_0x0105
        L_0x0072:
            r0 = move-exception
            r6 = r0
            r8 = r7
            goto L_0x010b
        L_0x0077:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007f:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r3 = r21.iterator()     // Catch:{ Throwable -> 0x0107, all -> 0x0101 }
            r7 = r21
            r8 = r7
            r11 = r8
            r5 = r23
            r9 = r0
            r12 = r2
            r10 = r6
            r0 = r11
            r6 = r0
            r2 = r22
        L_0x009f:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$2 = r5     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$3 = r6     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$4 = r9     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$7 = r10     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$8 = r11     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1.label = r4     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            java.lang.Object r13 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            if (r13 != r12) goto L_0x00bc
            return r12
        L_0x00bc:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            if (r13 == 0) goto L_0x00f0
            java.lang.Object r13 = r3.next()     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            kotlin.collections.IndexedValue r14 = new kotlin.collections.IndexedValue     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            int r15 = r9.element     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            int r4 = r15 + 1
            r9.element = r4     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r14.<init>(r15, r13)     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            int r4 = r14.component1()     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            java.lang.Object r13 = r14.component2()     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            java.lang.Object r4 = r5.invoke(r4, r13)     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            boolean r4 = r4.booleanValue()     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            if (r4 == 0) goto L_0x00ee
            r2.add(r13)     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
        L_0x00ee:
            r4 = 1
            goto L_0x009f
        L_0x00f0:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00ff, all -> 0x00fd }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r2
        L_0x00fd:
            r0 = move-exception
            goto L_0x0105
        L_0x00ff:
            r0 = move-exception
            goto L_0x010a
        L_0x0101:
            r0 = move-exception
            r8 = r21
        L_0x0104:
            r10 = r6
        L_0x0105:
            r1 = 1
            goto L_0x010e
        L_0x0107:
            r0 = move-exception
            r8 = r21
        L_0x010a:
            r6 = r0
        L_0x010b:
            throw r6     // Catch:{ all -> 0x010c }
        L_0x010c:
            r0 = move-exception
            goto L_0x0104
        L_0x010e:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00fe A[Catch:{ Throwable -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, @org.jetbrains.annotations.NotNull C r21, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, java.lang.Boolean> r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a5
            if (r3 == r5) goto L_0x006e
            if (r3 != r4) goto L_0x0066
            java.lang.Object r3 = r1.L$13
            int r3 = r1.I$0
            java.lang.Object r3 = r1.L$12
            kotlin.collections.IndexedValue r3 = (kotlin.collections.IndexedValue) r3
            java.lang.Object r3 = r1.L$11
            java.lang.Object r3 = r1.L$10
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x00a2 }
            r5 = r2
            r2 = 2
            goto L_0x0152
        L_0x0066:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006e:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x00a2 }
            r16 = r14
            r14 = r0
            r0 = r16
            goto L_0x00f6
        L_0x009f:
            r0 = move-exception
            goto L_0x0185
        L_0x00a2:
            r0 = move-exception
            goto L_0x018a
        L_0x00a5:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            r7 = r3
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r3 = r20.iterator()     // Catch:{ Throwable -> 0x0187, all -> 0x0182 }
            r9 = r20
            r10 = r9
            r12 = r10
            r6 = r22
            r8 = r0
            r13 = r2
            r11 = r7
            r0 = r12
            r7 = r0
            r2 = r21
        L_0x00c5:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$2 = r6     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$3 = r7     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$4 = r8     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$5 = r9     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$6 = r10     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$7 = r11     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$8 = r12     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            r1.label = r5     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            java.lang.Object r14 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x017e, all -> 0x017a }
            if (r14 != r13) goto L_0x00e2
            return r13
        L_0x00e2:
            r16 = r13
            r13 = r2
            r2 = r16
            r17 = r12
            r12 = r6
            r6 = r17
            r18 = r11
            r11 = r7
            r7 = r18
            r19 = r10
            r10 = r8
            r8 = r19
        L_0x00f6:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00a2 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00a2 }
            if (r14 == 0) goto L_0x016d
            java.lang.Object r14 = r3.next()     // Catch:{ Throwable -> 0x00a2 }
            kotlin.collections.IndexedValue r15 = new kotlin.collections.IndexedValue     // Catch:{ Throwable -> 0x00a2 }
            int r5 = r10.element     // Catch:{ Throwable -> 0x00a2 }
            int r4 = r5 + 1
            r10.element = r4     // Catch:{ Throwable -> 0x00a2 }
            r15.<init>(r5, r14)     // Catch:{ Throwable -> 0x00a2 }
            int r4 = r15.component1()     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r5 = r15.component2()     // Catch:{ Throwable -> 0x00a2 }
            r20 = r2
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r2 = r12.invoke(r2, r5)     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Throwable -> 0x00a2 }
            boolean r2 = r2.booleanValue()     // Catch:{ Throwable -> 0x00a2 }
            if (r2 == 0) goto L_0x0163
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00a2 }
            r1.L$1 = r13     // Catch:{ Throwable -> 0x00a2 }
            r1.L$2 = r12     // Catch:{ Throwable -> 0x00a2 }
            r1.L$3 = r11     // Catch:{ Throwable -> 0x00a2 }
            r1.L$4 = r10     // Catch:{ Throwable -> 0x00a2 }
            r1.L$5 = r9     // Catch:{ Throwable -> 0x00a2 }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00a2 }
            r1.L$7 = r7     // Catch:{ Throwable -> 0x00a2 }
            r1.L$8 = r6     // Catch:{ Throwable -> 0x00a2 }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x00a2 }
            r1.L$10 = r14     // Catch:{ Throwable -> 0x00a2 }
            r1.L$11 = r14     // Catch:{ Throwable -> 0x00a2 }
            r1.L$12 = r15     // Catch:{ Throwable -> 0x00a2 }
            r1.I$0 = r4     // Catch:{ Throwable -> 0x00a2 }
            r1.L$13 = r5     // Catch:{ Throwable -> 0x00a2 }
            r2 = 2
            r1.label = r2     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r4 = r13.send(r5, r1)     // Catch:{ Throwable -> 0x00a2 }
            r5 = r20
            if (r4 != r5) goto L_0x0151
            return r5
        L_0x0151:
            r14 = r0
        L_0x0152:
            r0 = r14
        L_0x0153:
            r16 = r12
            r12 = r6
            r6 = r16
            r17 = r11
            r11 = r7
            r7 = r17
            r18 = r10
            r10 = r8
            r8 = r18
            goto L_0x0167
        L_0x0163:
            r5 = r20
            r2 = 2
            goto L_0x0153
        L_0x0167:
            r2 = r13
            r4 = 2
            r13 = r5
            r5 = 1
            goto L_0x00c5
        L_0x016d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00a2 }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r13
        L_0x017a:
            r0 = move-exception
            r8 = r10
            r7 = r11
            goto L_0x0185
        L_0x017e:
            r0 = move-exception
            r7 = r0
            r8 = r10
            goto L_0x018b
        L_0x0182:
            r0 = move-exception
            r8 = r20
        L_0x0185:
            r1 = 1
            goto L_0x018c
        L_0x0187:
            r0 = move-exception
            r8 = r20
        L_0x018a:
            r7 = r0
        L_0x018b:
            throw r7     // Catch:{ all -> 0x009f }
        L_0x018c:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterNot(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> filterNot(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$filterNot");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ChannelsKt.filter(receiveChannel, coroutineContext, new ChannelsKt__Channels_commonKt$filterNot$1(function2, null));
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> filterNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$filterNotNull");
        ReceiveChannel<E> filter$default = filter$default(receiveChannel, null, new ChannelsKt__Channels_commonKt$filterNotNull$1(null), 1, null);
        if (filter$default != null) {
            return filter$default;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E>");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0054, all -> 0x004e }
            r8 = r5
            r5 = r11
            r11 = r7
            r7 = r1
            r1 = r8
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x008f
        L_0x004e:
            r10 = move-exception
            r8 = r4
            r4 = r2
            r2 = r8
            goto L_0x00b8
        L_0x0054:
            r10 = move-exception
            r2 = r10
            r10 = r4
            goto L_0x00b7
        L_0x0059:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            r2 = r12
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r12 = r10.iterator()     // Catch:{ Throwable -> 0x00b5 }
            r5 = r10
            r6 = r1
            r4 = r2
            r1 = r5
            r2 = r1
            r10 = r12
            r12 = r11
            r11 = r2
        L_0x0074:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.L$6 = r10     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            r0.label = r3     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            if (r7 != r6) goto L_0x008b
            return r6
        L_0x008b:
            r8 = r6
            r6 = r12
            r12 = r7
            r7 = r8
        L_0x008f:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            if (r12 == 0) goto L_0x00a3
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            if (r12 == 0) goto L_0x00a0
            r6.add(r12)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
        L_0x00a0:
            r12 = r6
            r6 = r7
            goto L_0x0074
        L_0x00a3:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00ab, all -> 0x00a9 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            return r6
        L_0x00a9:
            r10 = move-exception
            goto L_0x00b8
        L_0x00ab:
            r10 = move-exception
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x00b7
        L_0x00b0:
            r11 = move-exception
            r4 = r2
            r2 = r10
            r10 = r11
            goto L_0x00b8
        L_0x00b5:
            r11 = move-exception
            r2 = r11
        L_0x00b7:
            throw r2     // Catch:{ all -> 0x00b0 }
        L_0x00b8:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ae A[Catch:{ Throwable -> 0x00f0, all -> 0x00ee }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00bf A[Catch:{ Throwable -> 0x00f0, all -> 0x00ee }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull C r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0087
            if (r2 == r4) goto L_0x0059
            if (r2 != r3) goto L_0x0051
            java.lang.Object r11 = r0.L$8
            java.lang.Object r11 = r0.L$7
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0082, all -> 0x007c }
            r9 = r5
            r5 = r2
            r2 = r9
            goto L_0x00e0
        L_0x0051:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0059:
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0082, all -> 0x007c }
            r9 = r5
            r5 = r2
            r2 = r9
            goto L_0x00b7
        L_0x007c:
            r11 = move-exception
            r9 = r5
            r5 = r2
            r2 = r9
            goto L_0x00fd
        L_0x0082:
            r11 = move-exception
            r2 = r11
            r11 = r5
            goto L_0x00fc
        L_0x0087:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            r2 = r13
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r13 = r11.iterator()     // Catch:{ Throwable -> 0x00fa }
            r6 = r11
            r7 = r1
            r5 = r2
            r1 = r6
            r2 = r1
        L_0x0097:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$4 = r5     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$5 = r6     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$6 = r13     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.label = r4     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            java.lang.Object r8 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            if (r8 != r7) goto L_0x00ae
            return r7
        L_0x00ae:
            r9 = r8
            r8 = r11
            r11 = r13
            r13 = r9
            r10 = r7
            r7 = r12
            r12 = r6
            r6 = r1
            r1 = r10
        L_0x00b7:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            if (r13 == 0) goto L_0x00e8
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            if (r13 == 0) goto L_0x00e0
            r0.L$0 = r8     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$4 = r5     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$5 = r12     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$6 = r11     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$7 = r13     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.L$8 = r13     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            r0.label = r3     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            java.lang.Object r13 = r7.send(r13, r0)     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            if (r13 != r1) goto L_0x00e0
            return r1
        L_0x00e0:
            r13 = r11
            r11 = r8
            r9 = r6
            r6 = r12
            r12 = r7
            r7 = r1
            r1 = r9
            goto L_0x0097
        L_0x00e8:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00f0, all -> 0x00ee }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            return r7
        L_0x00ee:
            r11 = move-exception
            goto L_0x00fd
        L_0x00f0:
            r11 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00fc
        L_0x00f5:
            r12 = move-exception
            r5 = r2
            r2 = r11
            r11 = r12
            goto L_0x00fd
        L_0x00fa:
            r12 = move-exception
            r2 = r12
        L_0x00fc:
            throw r2     // Catch:{ all -> 0x00f5 }
        L_0x00fd:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r12 = r10;
        r10 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0055 A[ExcHandler: Throwable (r10v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0075, B:10:0x0046, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[Catch:{ Throwable -> 0x0055, all -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ Throwable -> 0x0055, all -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterNotTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r10 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r0.L$5
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x0051 }
            r9 = r6
            r6 = r11
            r11 = r7
            r7 = r1
            r1 = r4
            r4 = r12
            r12 = r9
            goto L_0x0093
        L_0x0051:
            r10 = move-exception
        L_0x0052:
            r4 = r12
            goto L_0x00cc
        L_0x0055:
            r10 = move-exception
            r12 = r10
            r10 = r2
            goto L_0x00c7
        L_0x005a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ Throwable -> 0x00c5, all -> 0x00c0 }
            r5 = r10
            r4 = r13
            r6 = r1
            r1 = r5
            r13 = r12
            r10 = r2
            r2 = r1
            r12 = r11
            r11 = r2
        L_0x0075:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$7 = r10     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.label = r3     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            if (r7 != r6) goto L_0x008e
            return r6
        L_0x008e:
            r9 = r5
            r5 = r13
            r13 = r7
            r7 = r6
            r6 = r9
        L_0x0093:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            if (r13 == 0) goto L_0x00b2
            java.lang.Object r13 = r10.next()     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            java.lang.Object r8 = r5.invoke(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            boolean r8 = r8.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            if (r8 != 0) goto L_0x00ae
            r12.add(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
        L_0x00ae:
            r13 = r5
            r5 = r6
            r6 = r7
            goto L_0x0075
        L_0x00b2:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r12
        L_0x00be:
            r10 = move-exception
            goto L_0x00cc
        L_0x00c0:
            r11 = move-exception
            r2 = r10
            r10 = r11
            r4 = r13
            goto L_0x00cc
        L_0x00c5:
            r11 = move-exception
            r12 = r11
        L_0x00c7:
            throw r12     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            r11 = move-exception
            r2 = r10
            r10 = r11
            goto L_0x0052
        L_0x00cc:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x010b, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010c, code lost:
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x010e, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010f, code lost:
        r13 = r11;
        r11 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c7 A[Catch:{ Throwable -> 0x010e, all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010e A[ExcHandler: Throwable (r11v4 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r1 
      PHI: (r1v3 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r1v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r1v6 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:33:0x00bf, B:28:0x009c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:28:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterNotTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull C r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x008d
            if (r2 == r4) goto L_0x005d
            if (r2 != r3) goto L_0x0055
            java.lang.Object r11 = r0.L$9
            java.lang.Object r11 = r0.L$8
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0088, all -> 0x0084 }
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x00f4
        L_0x0055:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x005d:
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0088, all -> 0x0084 }
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x00bf
        L_0x0084:
            r11 = move-exception
            r1 = r2
            goto L_0x011d
        L_0x0088:
            r11 = move-exception
            r13 = r11
            r11 = r2
            goto L_0x0119
        L_0x008d:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = 0
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ Throwable -> 0x0117, all -> 0x0112 }
            r6 = r11
            r5 = r14
            r7 = r1
            r14 = r6
            r1 = r14
        L_0x009c:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$3 = r14     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$6 = r6     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$7 = r2     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.label = r4     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            java.lang.Object r8 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            if (r8 != r7) goto L_0x00b5
            return r7
        L_0x00b5:
            r10 = r8
            r8 = r11
            r11 = r2
            r2 = r7
            r7 = r12
            r12 = r6
            r6 = r13
            r13 = r5
            r5 = r14
            r14 = r10
        L_0x00bf:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            if (r14 == 0) goto L_0x00fd
            java.lang.Object r14 = r11.next()     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            java.lang.Object r9 = r6.invoke(r14)     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            boolean r9 = r9.booleanValue()     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            if (r9 != 0) goto L_0x00f4
            r0.L$0 = r8     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$5 = r13     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$7 = r11     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$8 = r14     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$9 = r14     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.label = r3     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            java.lang.Object r14 = r7.send(r14, r0)     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            if (r14 != r2) goto L_0x00f4
            return r2
        L_0x00f4:
            r14 = r5
            r5 = r13
            r13 = r6
            r6 = r12
            r12 = r7
            r7 = r2
            r2 = r11
            r11 = r8
            goto L_0x009c
        L_0x00fd:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r7
        L_0x0109:
            r11 = move-exception
            goto L_0x011d
        L_0x010b:
            r11 = move-exception
            r13 = r5
            goto L_0x011d
        L_0x010e:
            r11 = move-exception
            r13 = r11
            r11 = r1
            goto L_0x0119
        L_0x0112:
            r12 = move-exception
            r1 = r11
            r11 = r12
            r13 = r14
            goto L_0x011d
        L_0x0117:
            r12 = move-exception
            r13 = r12
        L_0x0119:
            throw r13     // Catch:{ all -> 0x011a }
        L_0x011a:
            r12 = move-exception
            r1 = r11
            r11 = r12
        L_0x011d:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r12 = r10;
        r10 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0055 A[ExcHandler: Throwable (r10v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0075, B:10:0x0046, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[Catch:{ Throwable -> 0x0055, all -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ Throwable -> 0x0055, all -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r10 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r0.L$5
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x0051 }
            r9 = r6
            r6 = r11
            r11 = r7
            r7 = r1
            r1 = r4
            r4 = r12
            r12 = r9
            goto L_0x0093
        L_0x0051:
            r10 = move-exception
        L_0x0052:
            r4 = r12
            goto L_0x00cc
        L_0x0055:
            r10 = move-exception
            r12 = r10
            r10 = r2
            goto L_0x00c7
        L_0x005a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ Throwable -> 0x00c5, all -> 0x00c0 }
            r5 = r10
            r4 = r13
            r6 = r1
            r1 = r5
            r13 = r12
            r10 = r2
            r2 = r1
            r12 = r11
            r11 = r2
        L_0x0075:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.L$7 = r10     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            r0.label = r3     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            if (r7 != r6) goto L_0x008e
            return r6
        L_0x008e:
            r9 = r5
            r5 = r13
            r13 = r7
            r7 = r6
            r6 = r9
        L_0x0093:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            if (r13 == 0) goto L_0x00b2
            java.lang.Object r13 = r10.next()     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            java.lang.Object r8 = r5.invoke(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            boolean r8 = r8.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            if (r8 == 0) goto L_0x00ae
            r12.add(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
        L_0x00ae:
            r13 = r5
            r5 = r6
            r6 = r7
            goto L_0x0075
        L_0x00b2:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0055, all -> 0x00be }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r12
        L_0x00be:
            r10 = move-exception
            goto L_0x00cc
        L_0x00c0:
            r11 = move-exception
            r2 = r10
            r10 = r11
            r4 = r13
            goto L_0x00cc
        L_0x00c5:
            r11 = move-exception
            r12 = r11
        L_0x00c7:
            throw r12     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            r11 = move-exception
            r2 = r10
            r10 = r11
            goto L_0x0052
        L_0x00cc:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x010b, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010c, code lost:
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x010e, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010f, code lost:
        r13 = r11;
        r11 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c7 A[Catch:{ Throwable -> 0x010e, all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010e A[ExcHandler: Throwable (r11v4 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r1 
      PHI: (r1v3 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r1v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r1v6 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:33:0x00bf, B:28:0x009c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:28:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull C r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x008d
            if (r2 == r4) goto L_0x005d
            if (r2 != r3) goto L_0x0055
            java.lang.Object r11 = r0.L$9
            java.lang.Object r11 = r0.L$8
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0088, all -> 0x0084 }
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x00f4
        L_0x0055:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x005d:
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0088, all -> 0x0084 }
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x00bf
        L_0x0084:
            r11 = move-exception
            r1 = r2
            goto L_0x011d
        L_0x0088:
            r11 = move-exception
            r13 = r11
            r11 = r2
            goto L_0x0119
        L_0x008d:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = 0
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ Throwable -> 0x0117, all -> 0x0112 }
            r6 = r11
            r5 = r14
            r7 = r1
            r14 = r6
            r1 = r14
        L_0x009c:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$3 = r14     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$6 = r6     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.L$7 = r2     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            r0.label = r4     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            java.lang.Object r8 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x010e, all -> 0x010b }
            if (r8 != r7) goto L_0x00b5
            return r7
        L_0x00b5:
            r10 = r8
            r8 = r11
            r11 = r2
            r2 = r7
            r7 = r12
            r12 = r6
            r6 = r13
            r13 = r5
            r5 = r14
            r14 = r10
        L_0x00bf:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            if (r14 == 0) goto L_0x00fd
            java.lang.Object r14 = r11.next()     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            java.lang.Object r9 = r6.invoke(r14)     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            boolean r9 = r9.booleanValue()     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            if (r9 == 0) goto L_0x00f4
            r0.L$0 = r8     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$5 = r13     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$7 = r11     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$8 = r14     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.L$9 = r14     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            r0.label = r3     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            java.lang.Object r14 = r7.send(r14, r0)     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            if (r14 != r2) goto L_0x00f4
            return r2
        L_0x00f4:
            r14 = r5
            r5 = r13
            r13 = r6
            r6 = r12
            r12 = r7
            r7 = r2
            r2 = r11
            r11 = r8
            goto L_0x009c
        L_0x00fd:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x010e, all -> 0x0109 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r7
        L_0x0109:
            r11 = move-exception
            goto L_0x011d
        L_0x010b:
            r11 = move-exception
            r13 = r5
            goto L_0x011d
        L_0x010e:
            r11 = move-exception
            r13 = r11
            r11 = r1
            goto L_0x0119
        L_0x0112:
            r12 = move-exception
            r1 = r11
            r11 = r12
            r13 = r14
            goto L_0x011d
        L_0x0117:
            r12 = move-exception
            r13 = r12
        L_0x0119:
            throw r13     // Catch:{ all -> 0x011a }
        L_0x011a:
            r12 = move-exception
            r1 = r11
            r11 = r12
        L_0x011d:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.take(receiveChannel, i, coroutineContext);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> take(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$take");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$take$1(receiveChannel, i, null), 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.takeWhile(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> takeWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$takeWhile");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$takeWhile$1(receiveChannel, function2, null), 2, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0056, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0059, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005a, code lost:
        r2 = r13;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d1, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0059 A[ExcHandler: Throwable (r13v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r4 
      PHI: (r4v7 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r4v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r4v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r4v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:22:0x0081, B:10:0x004a, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c A[Catch:{ Throwable -> 0x0059, all -> 0x00cc }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8 A[Catch:{ Throwable -> 0x0059, all -> 0x00cc }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V> java.lang.Object associate(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends kotlin.Pair<? extends K, ? extends V>> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends V>> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0066
            if (r2 != r3) goto L_0x005e
            java.lang.Object r13 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x0056 }
            r11 = r7
            r7 = r14
            r14 = r9
            r9 = r1
            r1 = r11
            r12 = r5
            r5 = r2
            r2 = r12
            goto L_0x00a0
        L_0x0056:
            r13 = move-exception
            goto L_0x00d1
        L_0x0059:
            r13 = move-exception
            r2 = r13
            r13 = r4
            goto L_0x00d5
        L_0x005e:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0066:
            kotlin.ResultKt.throwOnFailure(r15)
            java.util.LinkedHashMap r15 = new java.util.LinkedHashMap
            r15.<init>()
            java.util.Map r15 = (java.util.Map) r15
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r13.iterator()     // Catch:{ Throwable -> 0x00d3 }
            r7 = r13
            r6 = r15
            r8 = r1
            r5 = r2
            r1 = r7
            r2 = r1
            r15 = r14
            r13 = r4
            r14 = r2
            r4 = r14
        L_0x0081:
            r0.L$0 = r14     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$1 = r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$7 = r7     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.L$8 = r13     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r0.label = r3     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            java.lang.Object r9 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            if (r9 != r8) goto L_0x009c
            return r8
        L_0x009c:
            r11 = r8
            r8 = r15
            r15 = r9
            r9 = r11
        L_0x00a0:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            if (r15 == 0) goto L_0x00c0
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            java.lang.Object r15 = r8.invoke(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            kotlin.Pair r15 = (kotlin.Pair) r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            java.lang.Object r10 = r15.getFirst()     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            java.lang.Object r15 = r15.getSecond()     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r6.put(r10, r15)     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            r15 = r8
            r8 = r9
            goto L_0x0081
        L_0x00c0:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0059, all -> 0x00cc }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x00cc:
            r13 = move-exception
            goto L_0x00d6
        L_0x00ce:
            r14 = move-exception
            r4 = r13
            r13 = r14
        L_0x00d1:
            r5 = r2
            goto L_0x00d6
        L_0x00d3:
            r14 = move-exception
            r2 = r14
        L_0x00d5:
            throw r2     // Catch:{ all -> 0x00ce }
        L_0x00d6:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associate(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object associate$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Pair pair = (Pair) function1.invoke(it.next());
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0056, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0059, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005a, code lost:
        r2 = r13;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c7, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0059 A[ExcHandler: Throwable (r13v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r4 
      PHI: (r4v7 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r4v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r4v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r4v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:22:0x0081, B:10:0x004a, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c A[Catch:{ Throwable -> 0x0059, all -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8 A[Catch:{ Throwable -> 0x0059, all -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K> java.lang.Object associateBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends E>> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0066
            if (r2 != r3) goto L_0x005e
            java.lang.Object r13 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x0056 }
            r11 = r7
            r7 = r14
            r14 = r9
            r9 = r1
            r1 = r11
            r12 = r5
            r5 = r2
            r2 = r12
            goto L_0x00a0
        L_0x0056:
            r13 = move-exception
            goto L_0x00c7
        L_0x0059:
            r13 = move-exception
            r2 = r13
            r13 = r4
            goto L_0x00cb
        L_0x005e:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0066:
            kotlin.ResultKt.throwOnFailure(r15)
            java.util.LinkedHashMap r15 = new java.util.LinkedHashMap
            r15.<init>()
            java.util.Map r15 = (java.util.Map) r15
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r13.iterator()     // Catch:{ Throwable -> 0x00c9 }
            r7 = r13
            r6 = r15
            r8 = r1
            r5 = r2
            r1 = r7
            r2 = r1
            r15 = r14
            r13 = r4
            r14 = r2
            r4 = r14
        L_0x0081:
            r0.L$0 = r14     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$1 = r15     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$7 = r7     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.L$8 = r13     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r0.label = r3     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            java.lang.Object r9 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            if (r9 != r8) goto L_0x009c
            return r8
        L_0x009c:
            r11 = r8
            r8 = r15
            r15 = r9
            r9 = r11
        L_0x00a0:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            if (r15 == 0) goto L_0x00b6
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r6.put(r10, r15)     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            r15 = r8
            r8 = r9
            goto L_0x0081
        L_0x00b6:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0059, all -> 0x00c2 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x00c2:
            r13 = move-exception
            goto L_0x00cc
        L_0x00c4:
            r14 = move-exception
            r4 = r13
            r13 = r14
        L_0x00c7:
            r5 = r2
            goto L_0x00cc
        L_0x00c9:
            r14 = move-exception
            r2 = r14
        L_0x00cb:
            throw r2     // Catch:{ all -> 0x00c4 }
        L_0x00cc:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object associateBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    linkedHashMap.put(function1.invoke(next), next);
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b8 A[Catch:{ Throwable -> 0x00df, all -> 0x00dd }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c1 A[Catch:{ Throwable -> 0x00df, all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V> java.lang.Object associateBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r21, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends V>> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x007d
            if (r3 != r4) goto L_0x0075
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$7
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            java.util.Map r9 = (java.util.Map) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0070, all -> 0x006b }
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r12
            r12 = r2
            r2 = r16
            r17 = r11
            r11 = r5
            r5 = r17
            r18 = r10
            r10 = r6
            r6 = r18
            r19 = r8
            r8 = r7
            r7 = r19
            goto L_0x00b9
        L_0x006b:
            r0 = move-exception
            r10 = r6
            r8 = r7
            goto L_0x00ed
        L_0x0070:
            r0 = move-exception
            r6 = r0
            r8 = r7
            goto L_0x00ea
        L_0x0075:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007d:
            kotlin.ResultKt.throwOnFailure(r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r3 = r20.iterator()     // Catch:{ Throwable -> 0x00e6, all -> 0x00e1 }
            r7 = r20
            r8 = r7
            r11 = r8
            r5 = r22
            r9 = r0
            r12 = r2
            r10 = r6
            r0 = r11
            r6 = r0
            r2 = r21
        L_0x009c:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$2 = r5     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$3 = r6     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$4 = r9     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$7 = r10     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$8 = r11     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r1.label = r4     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            java.lang.Object r13 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            if (r13 != r12) goto L_0x00b9
            return r12
        L_0x00b9:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            if (r13 == 0) goto L_0x00d1
            java.lang.Object r13 = r3.next()     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            java.lang.Object r14 = r2.invoke(r13)     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            java.lang.Object r13 = r5.invoke(r13)     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            r9.put(r14, r13)     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            goto L_0x009c
        L_0x00d1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00df, all -> 0x00dd }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r9
        L_0x00dd:
            r0 = move-exception
            goto L_0x00ed
        L_0x00df:
            r0 = move-exception
            goto L_0x00e9
        L_0x00e1:
            r0 = move-exception
            r8 = r20
        L_0x00e4:
            r10 = r6
            goto L_0x00ed
        L_0x00e6:
            r0 = move-exception
            r8 = r20
        L_0x00e9:
            r6 = r0
        L_0x00ea:
            throw r6     // Catch:{ all -> 0x00eb }
        L_0x00eb:
            r0 = move-exception
            goto L_0x00e4
        L_0x00ed:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object associateBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Throwable th;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    linkedHashMap.put(function1.invoke(next), function12.invoke(next));
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r12 = r10;
        r10 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0055 A[ExcHandler: Throwable (r10v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0075, B:10:0x0046, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[Catch:{ Throwable -> 0x0055, all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ Throwable -> 0x0055, all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, M extends java.util.Map<? super K, ? super E>> java.lang.Object associateByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull M r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r10 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r0.L$5
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x0051 }
            r9 = r6
            r6 = r11
            r11 = r7
            r7 = r1
            r1 = r4
            r4 = r12
            r12 = r9
            goto L_0x0093
        L_0x0051:
            r10 = move-exception
        L_0x0052:
            r4 = r12
            goto L_0x00c4
        L_0x0055:
            r10 = move-exception
            r12 = r10
            r10 = r2
            goto L_0x00bf
        L_0x005a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ Throwable -> 0x00bd, all -> 0x00b8 }
            r5 = r10
            r4 = r13
            r6 = r1
            r1 = r5
            r13 = r12
            r10 = r2
            r2 = r1
            r12 = r11
            r11 = r2
        L_0x0075:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$7 = r10     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.label = r3     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            if (r7 != r6) goto L_0x008e
            return r6
        L_0x008e:
            r9 = r5
            r5 = r13
            r13 = r7
            r7 = r6
            r6 = r9
        L_0x0093:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            if (r13 == 0) goto L_0x00aa
            java.lang.Object r13 = r10.next()     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            java.lang.Object r8 = r5.invoke(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r12.put(r8, r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r13 = r5
            r5 = r6
            r6 = r7
            goto L_0x0075
        L_0x00aa:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r12
        L_0x00b6:
            r10 = move-exception
            goto L_0x00c4
        L_0x00b8:
            r11 = move-exception
            r2 = r10
            r10 = r11
            r4 = r13
            goto L_0x00c4
        L_0x00bd:
            r11 = move-exception
            r12 = r11
        L_0x00bf:
            throw r12     // Catch:{ all -> 0x00c0 }
        L_0x00c0:
            r11 = move-exception
            r2 = r10
            r10 = r11
            goto L_0x0052
        L_0x00c4:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098 A[Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a4 A[Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object associateByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull M r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0069
            if (r2 != r3) goto L_0x0061
            java.lang.Object r10 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r0.L$6
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x005c, all -> 0x0057 }
            r9 = r5
            r5 = r11
            r11 = r4
            r4 = r12
            r12 = r6
            r6 = r1
            r1 = r2
            r2 = r13
            r13 = r9
            goto L_0x009c
        L_0x0057:
            r10 = move-exception
            r4 = r12
            r2 = r13
            goto L_0x00d4
        L_0x005c:
            r10 = move-exception
            r12 = r10
            r10 = r13
            goto L_0x00cf
        L_0x0061:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = 0
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ Throwable -> 0x00cd, all -> 0x00c8 }
            r5 = r10
            r4 = r14
            r6 = r1
            r1 = r5
            r14 = r13
            r10 = r2
            r2 = r1
            r13 = r12
            r12 = r11
            r11 = r2
        L_0x007d:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$3 = r14     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$7 = r5     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.L$8 = r10     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r0.label = r3     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            if (r7 != r6) goto L_0x0098
            return r6
        L_0x0098:
            r9 = r7
            r7 = r11
            r11 = r14
            r14 = r9
        L_0x009c:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            if (r14 == 0) goto L_0x00b6
            java.lang.Object r14 = r10.next()     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            java.lang.Object r8 = r13.invoke(r14)     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            java.lang.Object r14 = r11.invoke(r14)     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r12.put(r8, r14)     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            r14 = r11
            r11 = r7
            goto L_0x007d
        L_0x00b6:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00c4, all -> 0x00c2 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r12
        L_0x00c2:
            r10 = move-exception
            goto L_0x00d4
        L_0x00c4:
            r10 = move-exception
            r12 = r10
            r10 = r2
            goto L_0x00cf
        L_0x00c8:
            r11 = move-exception
            r2 = r10
            r10 = r11
            r4 = r14
            goto L_0x00d4
        L_0x00cd:
            r11 = move-exception
            r12 = r11
        L_0x00cf:
            throw r12     // Catch:{ all -> 0x00d0 }
        L_0x00d0:
            r11 = move-exception
            r2 = r10
            r10 = r11
            r4 = r12
        L_0x00d4:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r12 = r10;
        r10 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c0, code lost:
        r10 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0055 A[ExcHandler: Throwable (r10v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0075, B:10:0x0046, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[Catch:{ Throwable -> 0x0055, all -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ Throwable -> 0x0055, all -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object associateTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull M r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends kotlin.Pair<? extends K, ? extends V>> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r10 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r0.L$5
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x0051 }
            r9 = r6
            r6 = r11
            r11 = r7
            r7 = r1
            r1 = r4
            r4 = r12
            r12 = r9
            goto L_0x0093
        L_0x0051:
            r10 = move-exception
        L_0x0052:
            r4 = r12
            goto L_0x00ce
        L_0x0055:
            r10 = move-exception
            r12 = r10
            r10 = r2
            goto L_0x00c9
        L_0x005a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c2 }
            r5 = r10
            r4 = r13
            r6 = r1
            r1 = r5
            r13 = r12
            r10 = r2
            r2 = r1
            r12 = r11
            r11 = r2
        L_0x0075:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.L$7 = r10     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r0.label = r3     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            if (r7 != r6) goto L_0x008e
            return r6
        L_0x008e:
            r9 = r5
            r5 = r13
            r13 = r7
            r7 = r6
            r6 = r9
        L_0x0093:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            if (r13 == 0) goto L_0x00b4
            java.lang.Object r13 = r10.next()     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            java.lang.Object r13 = r5.invoke(r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            kotlin.Pair r13 = (kotlin.Pair) r13     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            java.lang.Object r8 = r13.getFirst()     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            java.lang.Object r13 = r13.getSecond()     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r12.put(r8, r13)     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            r13 = r5
            r5 = r6
            r6 = r7
            goto L_0x0075
        L_0x00b4:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0055, all -> 0x00c0 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r12
        L_0x00c0:
            r10 = move-exception
            goto L_0x00ce
        L_0x00c2:
            r11 = move-exception
            r2 = r10
            r10 = r11
            r4 = r13
            goto L_0x00ce
        L_0x00c7:
            r11 = move-exception
            r12 = r11
        L_0x00c9:
            throw r12     // Catch:{ all -> 0x00ca }
        L_0x00ca:
            r11 = move-exception
            r2 = r10
            r10 = r11
            goto L_0x0052
        L_0x00ce:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b0 A[Catch:{ Throwable -> 0x007a, all -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object toChannel(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x007e
            if (r2 == r4) goto L_0x0057
            if (r2 != r3) goto L_0x004f
            java.lang.Object r10 = r0.L$8
            java.lang.Object r10 = r0.L$7
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
        L_0x004c:
            r12 = r10
            r10 = r5
            goto L_0x008d
        L_0x004f:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0057:
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            goto L_0x00a8
        L_0x0077:
            r10 = move-exception
            goto L_0x00dc
        L_0x007a:
            r10 = move-exception
            r2 = r10
            r10 = r5
            goto L_0x00db
        L_0x007e:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            r2 = r12
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r12 = r10.iterator()     // Catch:{ Throwable -> 0x00d9 }
            r6 = r10
            r8 = r6
            r7 = r11
            r11 = r8
        L_0x008d:
            r0.L$0 = r8     // Catch:{ Throwable -> 0x00d9 }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x00d9 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00d9 }
            r0.L$3 = r10     // Catch:{ Throwable -> 0x00d9 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00d9 }
            r0.L$5 = r11     // Catch:{ Throwable -> 0x00d9 }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x00d9 }
            r0.label = r4     // Catch:{ Throwable -> 0x00d9 }
            java.lang.Object r5 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x00d9 }
            if (r5 != r1) goto L_0x00a4
            return r1
        L_0x00a4:
            r9 = r5
            r5 = r10
            r10 = r12
            r12 = r9
        L_0x00a8:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            if (r12 == 0) goto L_0x00cf
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$0 = r8     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$5 = r11     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$6 = r10     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$7 = r12     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.L$8 = r12     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            r0.label = r3     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            java.lang.Object r12 = r7.send(r12, r0)     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            if (r12 != r1) goto L_0x004c
            return r1
        L_0x00cf:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x007a, all -> 0x0077 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r2)
            return r7
        L_0x00d5:
            r11 = move-exception
            r5 = r10
            r10 = r11
            goto L_0x00dc
        L_0x00d9:
            r11 = move-exception
            r2 = r11
        L_0x00db:
            throw r2     // Catch:{ all -> 0x00d5 }
        L_0x00dc:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toChannel(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object toCollection(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0054, all -> 0x004e }
            r8 = r5
            r5 = r11
            r11 = r7
            r7 = r1
            r1 = r8
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x008f
        L_0x004e:
            r10 = move-exception
            r8 = r4
            r4 = r2
            r2 = r8
            goto L_0x00b6
        L_0x0054:
            r10 = move-exception
            r2 = r10
            r10 = r4
            goto L_0x00b5
        L_0x0059:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            r2 = r12
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r12 = r10.iterator()     // Catch:{ Throwable -> 0x00b3 }
            r5 = r10
            r6 = r1
            r4 = r2
            r1 = r5
            r2 = r1
            r10 = r12
            r12 = r11
            r11 = r2
        L_0x0074:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r0.L$6 = r10     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r0.label = r3     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            if (r7 != r6) goto L_0x008b
            return r6
        L_0x008b:
            r8 = r6
            r6 = r12
            r12 = r7
            r7 = r8
        L_0x008f:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            if (r12 == 0) goto L_0x00a1
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r6.add(r12)     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            r12 = r6
            r6 = r7
            goto L_0x0074
        L_0x00a1:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00a9, all -> 0x00a7 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            return r6
        L_0x00a7:
            r10 = move-exception
            goto L_0x00b6
        L_0x00a9:
            r10 = move-exception
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x00b5
        L_0x00ae:
            r11 = move-exception
            r4 = r2
            r2 = r10
            r10 = r11
            goto L_0x00b6
        L_0x00b3:
            r11 = move-exception
            r2 = r11
        L_0x00b5:
            throw r2     // Catch:{ all -> 0x00ae }
        L_0x00b6:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toCollection(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final <E> Object toList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<? extends E>> continuation) {
        return ChannelsKt.toMutableList(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <K, V> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt.toMap(receiveChannel, new LinkedHashMap(), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object toMap(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends kotlin.Pair<? extends K, ? extends V>> r11, @org.jetbrains.annotations.NotNull M r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0054, all -> 0x004e }
            r9 = r5
            r5 = r12
            r12 = r7
            r7 = r1
            r1 = r9
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x008f
        L_0x004e:
            r11 = move-exception
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x00c0
        L_0x0054:
            r11 = move-exception
            r2 = r11
            r11 = r4
            goto L_0x00bf
        L_0x0059:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            r2 = r13
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r13 = r11.iterator()     // Catch:{ Throwable -> 0x00bd }
            r5 = r11
            r6 = r1
            r4 = r2
            r1 = r5
            r2 = r1
            r11 = r13
            r13 = r12
            r12 = r2
        L_0x0074:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r0.L$6 = r11     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r0.label = r3     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            java.lang.Object r7 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            if (r7 != r6) goto L_0x008b
            return r6
        L_0x008b:
            r9 = r6
            r6 = r13
            r13 = r7
            r7 = r9
        L_0x008f:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            if (r13 == 0) goto L_0x00ab
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            kotlin.Pair r13 = (kotlin.Pair) r13     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            java.lang.Object r8 = r13.getFirst()     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            java.lang.Object r13 = r13.getSecond()     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r6.put(r8, r13)     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            r13 = r6
            r6 = r7
            goto L_0x0074
        L_0x00ab:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00b3, all -> 0x00b1 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            return r6
        L_0x00b1:
            r11 = move-exception
            goto L_0x00c0
        L_0x00b3:
            r11 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00bf
        L_0x00b8:
            r12 = move-exception
            r4 = r2
            r2 = r11
            r11 = r12
            goto L_0x00c0
        L_0x00bd:
            r12 = move-exception
            r2 = r12
        L_0x00bf:
            throw r2     // Catch:{ all -> 0x00b8 }
        L_0x00c0:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toMap(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object toMutableList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new ArrayList(), continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object toSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<? extends E>> continuation) {
        return ChannelsKt.toMutableSet(receiveChannel, continuation);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.flatMap(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, R> ReceiveChannel<R> flatMap(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super ReceiveChannel<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$flatMap");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$flatMap$1(receiveChannel, function2, null), 2, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0060 A[ExcHandler: Throwable (th java.lang.Throwable), PHI: r7 
      PHI: (r7v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r7v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r7v10 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r7v10 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:21:0x0088, B:10:0x004c, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a3 A[Catch:{ Throwable -> 0x0060, all -> 0x00d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ae A[Catch:{ Throwable -> 0x0060, all -> 0x00d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K> java.lang.Object groupBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends java.util.List<? extends E>>> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x006b
            if (r3 != r4) goto L_0x0063
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$6
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            java.util.Map r9 = (java.util.Map) r9
            java.lang.Object r10 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0060 }
            r15 = r11
            r11 = r2
            r2 = r15
            r16 = r10
            r10 = r5
            r5 = r16
            r17 = r8
            r8 = r6
            r6 = r17
            goto L_0x00a6
        L_0x005d:
            r0 = move-exception
            goto L_0x00dc
        L_0x0060:
            r0 = move-exception
            goto L_0x00e1
        L_0x0063:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006b:
            kotlin.ResultKt.throwOnFailure(r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ Throwable -> 0x00de, all -> 0x00d9 }
            r5 = r18
            r7 = r5
            r10 = r7
            r9 = r0
            r11 = r2
            r8 = r6
            r0 = r10
            r6 = r0
            r2 = r19
        L_0x0088:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$2 = r5     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$3 = r9     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$4 = r6     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$7 = r10     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.L$8 = r3     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r1.label = r4     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            java.lang.Object r12 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            if (r12 != r11) goto L_0x00a3
            return r11
        L_0x00a3:
            r15 = r12
            r12 = r0
            r0 = r15
        L_0x00a6:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            if (r0 == 0) goto L_0x00cb
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            java.lang.Object r13 = r2.invoke(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            java.lang.Object r14 = r9.get(r13)     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            if (r14 != 0) goto L_0x00c4
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r14.<init>()     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r9.put(r13, r14)     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
        L_0x00c4:
            java.util.List r14 = (java.util.List) r14     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r14.add(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            r0 = r12
            goto L_0x0088
        L_0x00cb:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0060, all -> 0x00d7 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r9
        L_0x00d7:
            r0 = move-exception
            goto L_0x00e3
        L_0x00d9:
            r0 = move-exception
            r7 = r18
        L_0x00dc:
            r8 = r6
            goto L_0x00e3
        L_0x00de:
            r0 = move-exception
            r7 = r18
        L_0x00e1:
            r6 = r0
            throw r6     // Catch:{ all -> 0x005d }
        L_0x00e3:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object groupBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    Object invoke = function1.invoke(next);
                    Object obj = linkedHashMap.get(invoke);
                    if (obj == null) {
                        obj = new ArrayList();
                        linkedHashMap.put(invoke, obj);
                    }
                    ((List) obj).add(next);
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ba A[Catch:{ Throwable -> 0x00f1, all -> 0x00ef }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c3 A[Catch:{ Throwable -> 0x00f1, all -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V> java.lang.Object groupBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r21, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r22, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends java.util.List<? extends V>>> r24) {
        /*
            r0 = r24
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x007f
            if (r3 != r4) goto L_0x0077
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$7
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            java.util.Map r9 = (java.util.Map) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0072, all -> 0x006d }
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r12
            r12 = r2
            r2 = r17
            r18 = r11
            r11 = r5
            r5 = r18
            r19 = r10
            r10 = r6
            r6 = r19
            r20 = r8
            r8 = r7
            r7 = r20
            goto L_0x00bb
        L_0x006d:
            r0 = move-exception
            r10 = r6
            r8 = r7
            goto L_0x00ff
        L_0x0072:
            r0 = move-exception
            r6 = r0
            r8 = r7
            goto L_0x00fc
        L_0x0077:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007f:
            kotlin.ResultKt.throwOnFailure(r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r3 = r21.iterator()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f3 }
            r7 = r21
            r8 = r7
            r11 = r8
            r5 = r23
            r9 = r0
            r12 = r2
            r10 = r6
            r0 = r11
            r6 = r0
            r2 = r22
        L_0x009e:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$2 = r5     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$3 = r6     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$4 = r9     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$7 = r10     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$8 = r11     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r1.label = r4     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            java.lang.Object r13 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            if (r13 != r12) goto L_0x00bb
            return r12
        L_0x00bb:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            if (r13 == 0) goto L_0x00e3
            java.lang.Object r13 = r3.next()     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            java.lang.Object r14 = r2.invoke(r13)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            java.lang.Object r15 = r9.get(r14)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            if (r15 != 0) goto L_0x00d9
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r15.<init>()     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r9.put(r14, r15)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
        L_0x00d9:
            java.util.List r15 = (java.util.List) r15     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            java.lang.Object r13 = r5.invoke(r13)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            r15.add(r13)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            goto L_0x009e
        L_0x00e3:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r9
        L_0x00ef:
            r0 = move-exception
            goto L_0x00ff
        L_0x00f1:
            r0 = move-exception
            goto L_0x00fb
        L_0x00f3:
            r0 = move-exception
            r8 = r21
        L_0x00f6:
            r10 = r6
            goto L_0x00ff
        L_0x00f8:
            r0 = move-exception
            r8 = r21
        L_0x00fb:
            r6 = r0
        L_0x00fc:
            throw r6     // Catch:{ all -> 0x00fd }
        L_0x00fd:
            r0 = move-exception
            goto L_0x00f6
        L_0x00ff:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object groupBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Throwable th;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    Object invoke = function1.invoke(next);
                    Object obj = linkedHashMap.get(invoke);
                    if (obj == null) {
                        obj = new ArrayList();
                        linkedHashMap.put(invoke, obj);
                    }
                    ((List) obj).add(function12.invoke(next));
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0052, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0053, code lost:
        r4 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        r14 = r12;
        r12 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0056 A[ExcHandler: Throwable (r12v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0076, B:10:0x0046, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008f A[Catch:{ Throwable -> 0x0056, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ Throwable -> 0x0056, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, M extends java.util.Map<? super K, java.util.List<E>>> java.lang.Object groupByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull M r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0063
            if (r2 != r3) goto L_0x005b
            java.lang.Object r12 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r14 = r0.L$5
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0056, all -> 0x0052 }
            r10 = r7
            r7 = r13
            r13 = r10
            r11 = r4
            r4 = r14
            r14 = r6
            r6 = r1
            r1 = r11
            goto L_0x0093
        L_0x0052:
            r12 = move-exception
        L_0x0053:
            r4 = r14
            goto L_0x00d3
        L_0x0056:
            r12 = move-exception
            r14 = r12
            r12 = r2
            goto L_0x00ce
        L_0x005b:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r15)
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r12.iterator()     // Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
            r5 = r12
            r4 = r15
            r6 = r1
            r1 = r5
            r15 = r14
            r12 = r2
            r2 = r1
            r14 = r13
            r13 = r2
        L_0x0076:
            r0.L$0 = r13     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.L$2 = r15     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.L$7 = r12     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r0.label = r3     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            java.lang.Object r7 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            if (r7 != r6) goto L_0x008f
            return r6
        L_0x008f:
            r10 = r5
            r5 = r15
            r15 = r7
            r7 = r10
        L_0x0093:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            if (r15 == 0) goto L_0x00b9
            java.lang.Object r15 = r12.next()     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            java.lang.Object r8 = r5.invoke(r15)     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            java.lang.Object r9 = r14.get(r8)     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            if (r9 != 0) goto L_0x00b1
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r9.<init>()     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r14.put(r8, r9)     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
        L_0x00b1:
            java.util.List r9 = (java.util.List) r9     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r9.add(r15)     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            r15 = r5
            r5 = r7
            goto L_0x0076
        L_0x00b9:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0056, all -> 0x00c5 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r14
        L_0x00c5:
            r12 = move-exception
            goto L_0x00d3
        L_0x00c7:
            r13 = move-exception
            r2 = r12
            r12 = r13
            r4 = r15
            goto L_0x00d3
        L_0x00cc:
            r13 = move-exception
            r14 = r13
        L_0x00ce:
            throw r14     // Catch:{ all -> 0x00cf }
        L_0x00cf:
            r13 = move-exception
            r2 = r12
            r12 = r13
            goto L_0x0053
        L_0x00d3:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098 A[Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a4 A[Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V, M extends java.util.Map<? super K, java.util.List<V>>> java.lang.Object groupByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull M r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0069
            if (r2 != r3) goto L_0x0061
            java.lang.Object r11 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$6
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x005c, all -> 0x0057 }
            r10 = r5
            r5 = r12
            r12 = r4
            r4 = r13
            r13 = r6
            r6 = r1
            r1 = r2
            r2 = r14
            r14 = r10
            goto L_0x009c
        L_0x0057:
            r11 = move-exception
            r4 = r13
            r2 = r14
            goto L_0x00e4
        L_0x005c:
            r11 = move-exception
            r13 = r11
            r11 = r14
            goto L_0x00df
        L_0x0061:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r15)
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ Throwable -> 0x00dd, all -> 0x00d8 }
            r5 = r11
            r4 = r15
            r6 = r1
            r1 = r5
            r15 = r14
            r11 = r2
            r2 = r1
            r14 = r13
            r13 = r12
            r12 = r2
        L_0x007d:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$2 = r14     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$3 = r15     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$7 = r5     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.L$8 = r11     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r0.label = r3     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.Object r7 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            if (r7 != r6) goto L_0x0098
            return r6
        L_0x0098:
            r10 = r7
            r7 = r12
            r12 = r15
            r15 = r10
        L_0x009c:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            if (r15 == 0) goto L_0x00c6
            java.lang.Object r15 = r11.next()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.Object r8 = r14.invoke(r15)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.Object r9 = r13.get(r8)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            if (r9 != 0) goto L_0x00ba
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r9.<init>()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r13.put(r8, r9)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
        L_0x00ba:
            java.util.List r9 = (java.util.List) r9     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.Object r15 = r12.invoke(r15)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r9.add(r15)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r15 = r12
            r12 = r7
            goto L_0x007d
        L_0x00c6:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r13
        L_0x00d2:
            r11 = move-exception
            goto L_0x00e4
        L_0x00d4:
            r11 = move-exception
            r13 = r11
            r11 = r2
            goto L_0x00df
        L_0x00d8:
            r12 = move-exception
            r2 = r11
            r11 = r12
            r4 = r15
            goto L_0x00e4
        L_0x00dd:
            r12 = move-exception
            r13 = r12
        L_0x00df:
            throw r13     // Catch:{ all -> 0x00e0 }
        L_0x00e0:
            r12 = move-exception
            r2 = r11
            r11 = r12
            r4 = r13
        L_0x00e4:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, R> ReceiveChannel<R> map(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$map");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$map$1(receiveChannel, function2, null), 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, R> ReceiveChannel<R> mapIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$mapIndexed");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$mapIndexed$1(receiveChannel, function3, null), 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, R> ReceiveChannel<R> mapIndexedNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$mapIndexedNotNull");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bb A[Catch:{ Throwable -> 0x00fd, all -> 0x00fb }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c4 A[Catch:{ Throwable -> 0x00fd, all -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapIndexedNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r21, @org.jetbrains.annotations.NotNull C r22, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r24) {
        /*
            r0 = r24
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x007f
            if (r3 != r4) goto L_0x0077
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$7
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            java.util.Collection r12 = (java.util.Collection) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0072, all -> 0x006d }
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r12
            r12 = r2
            r2 = r17
            r18 = r11
            r11 = r5
            r5 = r18
            r19 = r10
            r10 = r6
            r6 = r19
            r20 = r8
            r8 = r7
            r7 = r20
            goto L_0x00bc
        L_0x006d:
            r0 = move-exception
            r10 = r6
            r8 = r7
            goto L_0x0103
        L_0x0072:
            r0 = move-exception
            r6 = r0
            r8 = r7
            goto L_0x0109
        L_0x0077:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007f:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r3 = r21.iterator()     // Catch:{ Throwable -> 0x0105, all -> 0x00ff }
            r7 = r21
            r8 = r7
            r11 = r8
            r5 = r23
            r9 = r0
            r12 = r2
            r10 = r6
            r0 = r11
            r6 = r0
            r2 = r22
        L_0x009f:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$2 = r5     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$3 = r6     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$4 = r9     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$7 = r10     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$8 = r11     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1.label = r4     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            java.lang.Object r13 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            if (r13 != r12) goto L_0x00bc
            return r12
        L_0x00bc:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            if (r13 == 0) goto L_0x00ee
            java.lang.Object r13 = r3.next()     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            kotlin.collections.IndexedValue r14 = new kotlin.collections.IndexedValue     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            int r15 = r9.element     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            int r4 = r15 + 1
            r9.element = r4     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r14.<init>(r15, r13)     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            int r4 = r14.component1()     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            java.lang.Object r13 = r14.component2()     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            java.lang.Object r4 = r5.invoke(r4, r13)     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            if (r4 == 0) goto L_0x00ec
            boolean r4 = r2.add(r4)     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
        L_0x00ec:
            r4 = 1
            goto L_0x009f
        L_0x00ee:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00fd, all -> 0x00fb }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r2
        L_0x00fb:
            r0 = move-exception
            goto L_0x0103
        L_0x00fd:
            r0 = move-exception
            goto L_0x0108
        L_0x00ff:
            r0 = move-exception
            r8 = r21
        L_0x0102:
            r10 = r6
        L_0x0103:
            r1 = 1
            goto L_0x010c
        L_0x0105:
            r0 = move-exception
            r8 = r21
        L_0x0108:
            r6 = r0
        L_0x0109:
            throw r6     // Catch:{ all -> 0x010a }
        L_0x010a:
            r0 = move-exception
            goto L_0x0102
        L_0x010c:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00fe A[Catch:{ Throwable -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapIndexedNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, @org.jetbrains.annotations.NotNull C r21, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a5
            if (r3 == r5) goto L_0x006e
            if (r3 != r4) goto L_0x0066
            java.lang.Object r3 = r1.L$14
            java.lang.Object r3 = r1.L$13
            int r3 = r1.I$0
            java.lang.Object r3 = r1.L$12
            kotlin.collections.IndexedValue r3 = (kotlin.collections.IndexedValue) r3
            java.lang.Object r3 = r1.L$11
            java.lang.Object r3 = r1.L$10
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x00a2 }
            goto L_0x014f
        L_0x0066:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006e:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x00a2 }
            r16 = r14
            r14 = r0
            r0 = r16
            goto L_0x00f6
        L_0x009f:
            r0 = move-exception
            goto L_0x0194
        L_0x00a2:
            r0 = move-exception
            goto L_0x0199
        L_0x00a5:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            r7 = r3
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r3 = r20.iterator()     // Catch:{ Throwable -> 0x0196, all -> 0x0191 }
            r9 = r20
            r10 = r9
            r12 = r10
            r6 = r22
            r8 = r0
            r13 = r2
            r11 = r7
            r0 = r12
            r7 = r0
            r2 = r21
        L_0x00c5:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$2 = r6     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$3 = r7     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$4 = r8     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$5 = r9     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$6 = r10     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$7 = r11     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$8 = r12     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            r1.label = r5     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            java.lang.Object r14 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x018d, all -> 0x0189 }
            if (r14 != r13) goto L_0x00e2
            return r13
        L_0x00e2:
            r16 = r13
            r13 = r2
            r2 = r16
            r17 = r12
            r12 = r6
            r6 = r17
            r18 = r11
            r11 = r7
            r7 = r18
            r19 = r10
            r10 = r8
            r8 = r19
        L_0x00f6:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00a2 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00a2 }
            if (r14 == 0) goto L_0x017c
            java.lang.Object r14 = r3.next()     // Catch:{ Throwable -> 0x00a2 }
            kotlin.collections.IndexedValue r15 = new kotlin.collections.IndexedValue     // Catch:{ Throwable -> 0x00a2 }
            int r5 = r10.element     // Catch:{ Throwable -> 0x00a2 }
            int r4 = r5 + 1
            r10.element = r4     // Catch:{ Throwable -> 0x00a2 }
            r15.<init>(r5, r14)     // Catch:{ Throwable -> 0x00a2 }
            int r4 = r15.component1()     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r5 = r15.component2()     // Catch:{ Throwable -> 0x00a2 }
            r20 = r2
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r2 = r12.invoke(r2, r5)     // Catch:{ Throwable -> 0x00a2 }
            if (r2 == 0) goto L_0x0165
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00a2 }
            r1.L$1 = r13     // Catch:{ Throwable -> 0x00a2 }
            r1.L$2 = r12     // Catch:{ Throwable -> 0x00a2 }
            r1.L$3 = r11     // Catch:{ Throwable -> 0x00a2 }
            r1.L$4 = r10     // Catch:{ Throwable -> 0x00a2 }
            r1.L$5 = r9     // Catch:{ Throwable -> 0x00a2 }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00a2 }
            r1.L$7 = r7     // Catch:{ Throwable -> 0x00a2 }
            r1.L$8 = r6     // Catch:{ Throwable -> 0x00a2 }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x00a2 }
            r1.L$10 = r14     // Catch:{ Throwable -> 0x00a2 }
            r1.L$11 = r14     // Catch:{ Throwable -> 0x00a2 }
            r1.L$12 = r15     // Catch:{ Throwable -> 0x00a2 }
            r1.I$0 = r4     // Catch:{ Throwable -> 0x00a2 }
            r1.L$13 = r5     // Catch:{ Throwable -> 0x00a2 }
            r1.L$14 = r2     // Catch:{ Throwable -> 0x00a2 }
            r4 = 2
            r1.label = r4     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r2 = r13.send(r2, r1)     // Catch:{ Throwable -> 0x00a2 }
            r5 = r20
            if (r2 != r5) goto L_0x014d
            return r5
        L_0x014d:
            r14 = r0
            r2 = r5
        L_0x014f:
            r0 = r14
            r16 = r13
            r13 = r2
            r2 = r16
            r17 = r12
            r12 = r6
            r6 = r17
            r18 = r11
            r11 = r7
            r7 = r18
            r19 = r10
            r10 = r8
            r8 = r19
            goto L_0x0179
        L_0x0165:
            r5 = r20
            r4 = 2
            r2 = r13
            r13 = r5
            r16 = r12
            r12 = r6
            r6 = r16
            r17 = r11
            r11 = r7
            r7 = r17
            r18 = r10
            r10 = r8
            r8 = r18
        L_0x0179:
            r5 = 1
            goto L_0x00c5
        L_0x017c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00a2 }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r13
        L_0x0189:
            r0 = move-exception
            r8 = r10
            r7 = r11
            goto L_0x0194
        L_0x018d:
            r0 = move-exception
            r7 = r0
            r8 = r10
            goto L_0x019a
        L_0x0191:
            r0 = move-exception
            r8 = r20
        L_0x0194:
            r1 = 1
            goto L_0x019b
        L_0x0196:
            r0 = move-exception
            r8 = r20
        L_0x0199:
            r7 = r0
        L_0x019a:
            throw r7     // Catch:{ all -> 0x009f }
        L_0x019b:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
        r4 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0059, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005a, code lost:
        r14 = r12;
        r12 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059 A[ExcHandler: Throwable (r12v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v10 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v13 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v13 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0082, B:10:0x004a, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009d A[Catch:{ Throwable -> 0x0059, all -> 0x00cf }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa A[Catch:{ Throwable -> 0x0059, all -> 0x00cf }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull C r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0066
            if (r2 != r3) goto L_0x005e
            java.lang.Object r12 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r14 = r0.L$6
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$3
            kotlin.jvm.internal.Ref$IntRef r5 = (kotlin.jvm.internal.Ref.IntRef) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r7 = r0.L$1
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x0055 }
            r11 = r7
            r7 = r13
            r13 = r8
            r8 = r1
            r1 = r4
            r4 = r14
            r14 = r11
            goto L_0x00a2
        L_0x0055:
            r12 = move-exception
        L_0x0056:
            r4 = r14
            goto L_0x00de
        L_0x0059:
            r12 = move-exception
            r14 = r12
            r12 = r2
            goto L_0x00d8
        L_0x005e:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0066:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlin.jvm.internal.Ref$IntRef r15 = new kotlin.jvm.internal.Ref$IntRef
            r15.<init>()
            r2 = 0
            r15.element = r2
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r12.iterator()     // Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
            r6 = r12
            r5 = r15
            r7 = r1
            r1 = r6
            r15 = r14
            r12 = r4
            r14 = r13
            r4 = r2
            r13 = r1
            r2 = r13
        L_0x0082:
            r0.L$0 = r13     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$2 = r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$7 = r6     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.L$8 = r12     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r0.label = r3     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            java.lang.Object r8 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            if (r8 != r7) goto L_0x009d
            return r7
        L_0x009d:
            r11 = r6
            r6 = r15
            r15 = r8
            r8 = r7
            r7 = r11
        L_0x00a2:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            if (r15 == 0) goto L_0x00c3
            java.lang.Object r15 = r12.next()     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            int r9 = r5.element     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            int r10 = r9 + 1
            r5.element = r10     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            java.lang.Object r15 = r6.invoke(r9, r15)     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r14.add(r15)     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            r15 = r6
            r6 = r7
            r7 = r8
            goto L_0x0082
        L_0x00c3:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0059, all -> 0x00cf }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r14
        L_0x00cf:
            r12 = move-exception
            goto L_0x00de
        L_0x00d1:
            r13 = move-exception
            r4 = r2
            r2 = r12
            r12 = r13
            goto L_0x00de
        L_0x00d6:
            r13 = move-exception
            r14 = r13
        L_0x00d8:
            throw r14     // Catch:{ all -> 0x00d9 }
        L_0x00d9:
            r13 = move-exception
            r2 = r12
            r12 = r13
            goto L_0x0056
        L_0x00de:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d3 A[Catch:{ Throwable -> 0x011f }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e0 A[Catch:{ Throwable -> 0x011f }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, @org.jetbrains.annotations.NotNull C r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009b
            if (r3 == r5) goto L_0x0065
            if (r3 != r4) goto L_0x005d
            java.lang.Object r3 = r1.L$10
            java.lang.Object r3 = r1.L$9
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0096, all -> 0x0092 }
            r0 = r10
            r16 = r8
            r8 = r2
            r2 = r16
            goto L_0x00b8
        L_0x005d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0065:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0096, all -> 0x0092 }
            r16 = r8
            r8 = r2
            r2 = r16
            goto L_0x00d8
        L_0x0092:
            r0 = move-exception
            r2 = r8
            goto L_0x012b
        L_0x0096:
            r0 = move-exception
            r7 = r0
            r2 = r8
            goto L_0x0129
        L_0x009b:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            r7 = r3
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r3 = r17.iterator()     // Catch:{ Throwable -> 0x0125, all -> 0x0121 }
            r6 = r17
            r9 = r6
            r13 = r9
            r12 = r18
            r11 = r19
            r8 = r2
            r2 = r13
        L_0x00b8:
            r1.L$0 = r13     // Catch:{ Throwable -> 0x011f }
            r1.L$1 = r12     // Catch:{ Throwable -> 0x011f }
            r1.L$2 = r11     // Catch:{ Throwable -> 0x011f }
            r1.L$3 = r0     // Catch:{ Throwable -> 0x011f }
            r1.L$4 = r9     // Catch:{ Throwable -> 0x011f }
            r1.L$5 = r2     // Catch:{ Throwable -> 0x011f }
            r1.L$6 = r7     // Catch:{ Throwable -> 0x011f }
            r1.L$7 = r6     // Catch:{ Throwable -> 0x011f }
            r1.L$8 = r3     // Catch:{ Throwable -> 0x011f }
            r1.label = r5     // Catch:{ Throwable -> 0x011f }
            java.lang.Object r10 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x011f }
            if (r10 != r8) goto L_0x00d3
            return r8
        L_0x00d3:
            r16 = r10
            r10 = r0
            r0 = r16
        L_0x00d8:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x011f }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x011f }
            if (r0 == 0) goto L_0x0113
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x011f }
            int r14 = r10.element     // Catch:{ Throwable -> 0x011f }
            int r15 = r14 + 1
            r10.element = r15     // Catch:{ Throwable -> 0x011f }
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14)     // Catch:{ Throwable -> 0x011f }
            java.lang.Object r14 = r11.invoke(r14, r0)     // Catch:{ Throwable -> 0x011f }
            r1.L$0 = r13     // Catch:{ Throwable -> 0x011f }
            r1.L$1 = r12     // Catch:{ Throwable -> 0x011f }
            r1.L$2 = r11     // Catch:{ Throwable -> 0x011f }
            r1.L$3 = r10     // Catch:{ Throwable -> 0x011f }
            r1.L$4 = r9     // Catch:{ Throwable -> 0x011f }
            r1.L$5 = r2     // Catch:{ Throwable -> 0x011f }
            r1.L$6 = r7     // Catch:{ Throwable -> 0x011f }
            r1.L$7 = r6     // Catch:{ Throwable -> 0x011f }
            r1.L$8 = r3     // Catch:{ Throwable -> 0x011f }
            r1.L$9 = r0     // Catch:{ Throwable -> 0x011f }
            r1.L$10 = r0     // Catch:{ Throwable -> 0x011f }
            r1.label = r4     // Catch:{ Throwable -> 0x011f }
            java.lang.Object r0 = r12.send(r14, r1)     // Catch:{ Throwable -> 0x011f }
            if (r0 != r8) goto L_0x0111
            return r8
        L_0x0111:
            r0 = r10
            goto L_0x00b8
        L_0x0113:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x011f }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r12
        L_0x011f:
            r0 = move-exception
            goto L_0x0128
        L_0x0121:
            r0 = move-exception
            r2 = r17
            goto L_0x012b
        L_0x0125:
            r0 = move-exception
            r2 = r17
        L_0x0128:
            r7 = r0
        L_0x0129:
            throw r7     // Catch:{ all -> 0x012a }
        L_0x012a:
            r0 = move-exception
        L_0x012b:
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, R> ReceiveChannel<R> mapNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$mapNotNull");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r11 = r9;
        r9 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0055 A[ExcHandler: Throwable (r9v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0075, B:10:0x0046, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[Catch:{ Throwable -> 0x0055, all -> 0x00bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ Throwable -> 0x0055, all -> 0x00bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull C r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r9 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r0.L$5
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0055, all -> 0x0051 }
            r8 = r6
            r6 = r10
            r10 = r7
            r7 = r1
            r1 = r4
            r4 = r11
            r11 = r8
            goto L_0x0093
        L_0x0051:
            r9 = move-exception
        L_0x0052:
            r4 = r11
            goto L_0x00ca
        L_0x0055:
            r9 = move-exception
            r11 = r9
            r9 = r2
            goto L_0x00c5
        L_0x005a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            kotlinx.coroutines.channels.ChannelIterator r2 = r9.iterator()     // Catch:{ Throwable -> 0x00c3, all -> 0x00be }
            r5 = r9
            r4 = r12
            r6 = r1
            r1 = r5
            r12 = r11
            r9 = r2
            r2 = r1
            r11 = r10
            r10 = r2
        L_0x0075:
            r0.L$0 = r10     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.L$2 = r12     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.L$7 = r9     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            r0.label = r3     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            java.lang.Object r7 = r9.hasNext(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            if (r7 != r6) goto L_0x008e
            return r6
        L_0x008e:
            r8 = r5
            r5 = r12
            r12 = r7
            r7 = r6
            r6 = r8
        L_0x0093:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            if (r12 == 0) goto L_0x00b0
            java.lang.Object r12 = r9.next()     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            java.lang.Object r12 = r5.invoke(r12)     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            if (r12 == 0) goto L_0x00ac
            boolean r12 = r11.add(r12)     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12)     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
        L_0x00ac:
            r12 = r5
            r5 = r6
            r6 = r7
            goto L_0x0075
        L_0x00b0:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0055, all -> 0x00bc }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r11
        L_0x00bc:
            r9 = move-exception
            goto L_0x00ca
        L_0x00be:
            r10 = move-exception
            r2 = r9
            r9 = r10
            r4 = r12
            goto L_0x00ca
        L_0x00c3:
            r10 = move-exception
            r11 = r10
        L_0x00c5:
            throw r11     // Catch:{ all -> 0x00c6 }
        L_0x00c6:
            r10 = move-exception
            r2 = r9
            r9 = r10
            goto L_0x0052
        L_0x00ca:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x010c, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010d, code lost:
        r13 = r11;
        r11 = r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c9 A[Catch:{ Throwable -> 0x010c, all -> 0x0107 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010c A[ExcHandler: Throwable (r11v4 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r1 
      PHI: (r1v3 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r1v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r1v6 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:33:0x00c1, B:28:0x009e] A[DONT_GENERATE, DONT_INLINE], Splitter:B:33:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull C r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x008f
            if (r2 == r4) goto L_0x005f
            if (r2 != r3) goto L_0x0057
            java.lang.Object r11 = r0.L$10
            java.lang.Object r11 = r0.L$9
            java.lang.Object r11 = r0.L$8
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x008a, all -> 0x0086 }
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x00f2
        L_0x0057:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x005f:
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x008a, all -> 0x0086 }
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x00c1
        L_0x0086:
            r11 = move-exception
            r1 = r2
            goto L_0x011b
        L_0x008a:
            r11 = move-exception
            r13 = r11
            r11 = r2
            goto L_0x0117
        L_0x008f:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = 0
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ Throwable -> 0x0115, all -> 0x0110 }
            r6 = r11
            r5 = r14
            r7 = r1
            r14 = r6
            r1 = r14
        L_0x009e:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.L$3 = r14     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.L$6 = r6     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.L$7 = r2     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            r0.label = r4     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            java.lang.Object r8 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x010c, all -> 0x0109 }
            if (r8 != r7) goto L_0x00b7
            return r7
        L_0x00b7:
            r10 = r8
            r8 = r11
            r11 = r2
            r2 = r7
            r7 = r12
            r12 = r6
            r6 = r13
            r13 = r5
            r5 = r14
            r14 = r10
        L_0x00c1:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            if (r14 == 0) goto L_0x00fb
            java.lang.Object r14 = r11.next()     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            java.lang.Object r9 = r6.invoke(r14)     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            if (r9 == 0) goto L_0x00f2
            r0.L$0 = r8     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$5 = r13     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$7 = r11     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$8 = r14     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$9 = r14     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.L$10 = r9     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            r0.label = r3     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            java.lang.Object r14 = r7.send(r9, r0)     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            if (r14 != r2) goto L_0x00f2
            return r2
        L_0x00f2:
            r14 = r5
            r5 = r13
            r13 = r6
            r6 = r12
            r12 = r7
            r7 = r2
            r2 = r11
            r11 = r8
            goto L_0x009e
        L_0x00fb:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x010c, all -> 0x0107 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r7
        L_0x0107:
            r11 = move-exception
            goto L_0x011b
        L_0x0109:
            r11 = move-exception
            r13 = r5
            goto L_0x011b
        L_0x010c:
            r11 = move-exception
            r13 = r11
            r11 = r1
            goto L_0x0117
        L_0x0110:
            r12 = move-exception
            r1 = r11
            r11 = r12
            r13 = r14
            goto L_0x011b
        L_0x0115:
            r12 = move-exception
            r13 = r12
        L_0x0117:
            throw r13     // Catch:{ all -> 0x0118 }
        L_0x0118:
            r12 = move-exception
            r1 = r11
            r11 = r12
        L_0x011b:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r1, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r11 = r9;
        r9 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0055 A[ExcHandler: Throwable (r9v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x0075, B:10:0x0046, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[Catch:{ Throwable -> 0x0055, all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ Throwable -> 0x0055, all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull C r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r9 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r0.L$5
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0055, all -> 0x0051 }
            r8 = r6
            r6 = r10
            r10 = r7
            r7 = r1
            r1 = r4
            r4 = r11
            r11 = r8
            goto L_0x0093
        L_0x0051:
            r9 = move-exception
        L_0x0052:
            r4 = r11
            goto L_0x00c4
        L_0x0055:
            r9 = move-exception
            r11 = r9
            r9 = r2
            goto L_0x00bf
        L_0x005a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            kotlinx.coroutines.channels.ChannelIterator r2 = r9.iterator()     // Catch:{ Throwable -> 0x00bd, all -> 0x00b8 }
            r5 = r9
            r4 = r12
            r6 = r1
            r1 = r5
            r12 = r11
            r9 = r2
            r2 = r1
            r11 = r10
            r10 = r2
        L_0x0075:
            r0.L$0 = r10     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$2 = r12     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.L$7 = r9     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r0.label = r3     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            java.lang.Object r7 = r9.hasNext(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            if (r7 != r6) goto L_0x008e
            return r6
        L_0x008e:
            r8 = r5
            r5 = r12
            r12 = r7
            r7 = r6
            r6 = r8
        L_0x0093:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            if (r12 == 0) goto L_0x00aa
            java.lang.Object r12 = r9.next()     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            java.lang.Object r12 = r5.invoke(r12)     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r11.add(r12)     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            r12 = r5
            r5 = r6
            r6 = r7
            goto L_0x0075
        L_0x00aa:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0055, all -> 0x00b6 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r11
        L_0x00b6:
            r9 = move-exception
            goto L_0x00c4
        L_0x00b8:
            r10 = move-exception
            r2 = r9
            r9 = r10
            r4 = r12
            goto L_0x00c4
        L_0x00bd:
            r10 = move-exception
            r11 = r10
        L_0x00bf:
            throw r11     // Catch:{ all -> 0x00c0 }
        L_0x00c0:
            r10 = move-exception
            r2 = r9
            r9 = r10
            goto L_0x0052
        L_0x00c4:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ed, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ee, code lost:
        r2 = r11;
        r11 = r12;
        r13 = r14;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bc A[Catch:{ Throwable -> 0x0083, all -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull C r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0088
            if (r2 == r4) goto L_0x005c
            if (r2 != r3) goto L_0x0054
            java.lang.Object r11 = r0.L$9
            java.lang.Object r11 = r0.L$8
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        L_0x0050:
            r10 = r2
            r2 = r11
            r11 = r10
            goto L_0x0098
        L_0x0054:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x005c:
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$5
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            goto L_0x00b4
        L_0x0080:
            r11 = move-exception
            goto L_0x00f8
        L_0x0083:
            r11 = move-exception
            r13 = r11
            r11 = r2
            goto L_0x00f4
        L_0x0088:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = 0
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            r5 = r11
            r8 = r5
            r7 = r12
            r6 = r13
            r13 = r14
            r12 = r8
        L_0x0098:
            r0.L$0 = r8     // Catch:{ Throwable -> 0x00f2 }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x00f2 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00f2 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x00f2 }
            r0.L$4 = r11     // Catch:{ Throwable -> 0x00f2 }
            r0.L$5 = r13     // Catch:{ Throwable -> 0x00f2 }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x00f2 }
            r0.L$7 = r2     // Catch:{ Throwable -> 0x00f2 }
            r0.label = r4     // Catch:{ Throwable -> 0x00f2 }
            java.lang.Object r14 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00f2 }
            if (r14 != r1) goto L_0x00b1
            return r1
        L_0x00b1:
            r10 = r2
            r2 = r11
            r11 = r10
        L_0x00b4:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            if (r14 == 0) goto L_0x00e1
            java.lang.Object r14 = r11.next()     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            java.lang.Object r9 = r6.invoke(r14)     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$0 = r8     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$5 = r13     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$7 = r11     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$8 = r14     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.L$9 = r14     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            r0.label = r3     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            java.lang.Object r14 = r7.send(r9, r0)     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            if (r14 != r1) goto L_0x0050
            return r1
        L_0x00e1:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0083, all -> 0x0080 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r7
        L_0x00ed:
            r12 = move-exception
            r2 = r11
            r11 = r12
            r13 = r14
            goto L_0x00f8
        L_0x00f2:
            r12 = move-exception
            r13 = r12
        L_0x00f4:
            throw r13     // Catch:{ all -> 0x00f5 }
        L_0x00f5:
            r12 = move-exception
            r2 = r11
            r11 = r12
        L_0x00f8:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.withIndex(receiveChannel, coroutineContext);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<IndexedValue<E>> withIndex(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$withIndex");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$withIndex$1(receiveChannel, null), 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> distinct(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$distinct");
        return distinctBy$default(receiveChannel, null, new ChannelsKt__Channels_commonKt$distinct$1(null), 1, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, K> ReceiveChannel<E> distinctBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$distinctBy");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "selector");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$distinctBy$1(receiveChannel, function2, null), 2, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object toMutableSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ba A[SYNTHETIC, Splitter:B:36:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object all(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0054, all -> 0x004e }
            r8 = r5
            r5 = r11
            r11 = r7
            r7 = r1
            r1 = r8
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x008f
        L_0x004e:
            r10 = move-exception
            r8 = r4
            r4 = r2
            r2 = r8
            goto L_0x00d9
        L_0x0054:
            r10 = move-exception
            r2 = r10
            r10 = r4
            goto L_0x00d8
        L_0x0059:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            r2 = r12
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r12 = r10.iterator()     // Catch:{ Throwable -> 0x00d6 }
            r5 = r10
            r6 = r1
            r4 = r2
            r1 = r5
            r2 = r1
            r10 = r12
            r12 = r11
            r11 = r2
        L_0x0074:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$6 = r10     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.label = r3     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r7 != r6) goto L_0x008b
            return r6
        L_0x008b:
            r8 = r6
            r6 = r12
            r12 = r7
            r7 = r8
        L_0x008f:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r12 == 0) goto L_0x00ba
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r12 = r6.invoke(r12)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r12 != 0) goto L_0x00b7
            r10 = 0
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r11 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r11)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r11)
            return r10
        L_0x00b7:
            r12 = r6
            r6 = r7
            goto L_0x0074
        L_0x00ba:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r10
        L_0x00ca:
            r10 = move-exception
            goto L_0x00d9
        L_0x00cc:
            r10 = move-exception
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x00d8
        L_0x00d1:
            r11 = move-exception
            r4 = r2
            r2 = r10
            r10 = r11
            goto L_0x00d9
        L_0x00d6:
            r11 = move-exception
            r2 = r11
        L_0x00d8:
            throw r2     // Catch:{ all -> 0x00d1 }
        L_0x00d9:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.all(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object any(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Throwable -> 0x0041 }
            r0 = r5
            r5 = r1
            goto L_0x0069
        L_0x003c:
            r6 = move-exception
            r0 = r6
            r6 = r5
            r5 = r1
            goto L_0x0073
        L_0x0041:
            r5 = move-exception
            goto L_0x0072
        L_0x0043:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ Throwable -> 0x006f, all -> 0x006d }
            r0.L$0 = r5     // Catch:{ Throwable -> 0x006f, all -> 0x006d }
            r0.L$1 = r5     // Catch:{ Throwable -> 0x006f, all -> 0x006d }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x006f, all -> 0x006d }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x006f, all -> 0x006d }
            r0.label = r3     // Catch:{ Throwable -> 0x006f, all -> 0x006d }
            java.lang.Object r0 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x006f, all -> 0x006d }
            if (r0 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r4 = r0
            r0 = r6
            r6 = r4
        L_0x0069:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r0)
            return r6
        L_0x006d:
            r0 = move-exception
            goto L_0x0073
        L_0x006f:
            r6 = move-exception
            r1 = r5
            r5 = r6
        L_0x0072:
            throw r5     // Catch:{ all -> 0x003c }
        L_0x0073:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b9 A[SYNTHETIC, Splitter:B:36:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object any(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0054, all -> 0x004e }
            r8 = r5
            r5 = r11
            r11 = r7
            r7 = r1
            r1 = r8
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x008f
        L_0x004e:
            r10 = move-exception
            r8 = r4
            r4 = r2
            r2 = r8
            goto L_0x00d9
        L_0x0054:
            r10 = move-exception
            r2 = r10
            r10 = r4
            goto L_0x00d8
        L_0x0059:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            r2 = r12
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r12 = r10.iterator()     // Catch:{ Throwable -> 0x00d6 }
            r5 = r10
            r6 = r1
            r4 = r2
            r1 = r5
            r2 = r1
            r10 = r12
            r12 = r11
            r11 = r2
        L_0x0074:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$6 = r10     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.label = r3     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r7 != r6) goto L_0x008b
            return r6
        L_0x008b:
            r8 = r6
            r6 = r12
            r12 = r7
            r7 = r8
        L_0x008f:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r12 == 0) goto L_0x00b9
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r12 = r6.invoke(r12)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r12 == 0) goto L_0x00b6
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r11 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r11)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r11)
            return r10
        L_0x00b6:
            r12 = r6
            r6 = r7
            goto L_0x0074
        L_0x00b9:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            r10 = 0
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)
            return r10
        L_0x00ca:
            r10 = move-exception
            goto L_0x00d9
        L_0x00cc:
            r10 = move-exception
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x00d8
        L_0x00d1:
            r11 = move-exception
            r4 = r2
            r2 = r10
            r10 = r11
            goto L_0x00d9
        L_0x00d6:
            r11 = move-exception
            r2 = r11
        L_0x00d8:
            throw r2     // Catch:{ all -> 0x00d1 }
        L_0x00d9:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[Catch:{ Throwable -> 0x00b1, all -> 0x00af }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099 A[Catch:{ Throwable -> 0x00b1, all -> 0x00af }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object count(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x005d
            if (r2 != r3) goto L_0x0055
            java.lang.Object r11 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$4
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0050, all -> 0x004c }
            r9 = r6
            r6 = r1
            r1 = r9
            r10 = r5
            r5 = r2
            r2 = r10
            goto L_0x0091
        L_0x004c:
            r11 = move-exception
            r2 = r5
            goto L_0x00bc
        L_0x0050:
            r11 = move-exception
            r4 = r11
            r11 = r5
            goto L_0x00bb
        L_0x0055:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x005d:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.jvm.internal.Ref$IntRef r12 = new kotlin.jvm.internal.Ref$IntRef
            r12.<init>()
            r2 = 0
            r12.element = r2
            r2 = 0
            r4 = r2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ Throwable -> 0x00b9 }
            r5 = r11
            r7 = r12
            r6 = r1
            r12 = r5
            r1 = r12
            r11 = r2
            r2 = r1
        L_0x0077:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.L$6 = r11     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.label = r3     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            java.lang.Object r8 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r8 != r6) goto L_0x008e
            return r6
        L_0x008e:
            r9 = r8
            r8 = r12
            r12 = r9
        L_0x0091:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r12 == 0) goto L_0x00a3
            r11.next()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            int r12 = r7.element     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            int r12 = r12 + r3
            r7.element = r12     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r12 = r8
            goto L_0x0077
        L_0x00a3:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            int r11 = r7.element
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)
            return r11
        L_0x00af:
            r11 = move-exception
            goto L_0x00bc
        L_0x00b1:
            r11 = move-exception
            r4 = r11
            r11 = r2
            goto L_0x00bb
        L_0x00b5:
            r12 = move-exception
            r2 = r11
            r11 = r12
            goto L_0x00bc
        L_0x00b9:
            r12 = move-exception
            r4 = r12
        L_0x00bb:
            throw r4     // Catch:{ all -> 0x00b5 }
        L_0x00bc:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097 A[Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a5 A[Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bf A[Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object count(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0063
            if (r2 != r3) goto L_0x005b
            java.lang.Object r11 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x0056, all -> 0x0050 }
            r9 = r8
            r8 = r12
            r12 = r9
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x009d
        L_0x0050:
            r11 = move-exception
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x00e0
        L_0x0056:
            r11 = move-exception
            r2 = r11
            r11 = r4
            goto L_0x00df
        L_0x005b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlin.jvm.internal.Ref$IntRef r13 = new kotlin.jvm.internal.Ref$IntRef
            r13.<init>()
            r2 = 0
            r13.element = r2
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r11.iterator()     // Catch:{ Throwable -> 0x00dd }
            r5 = r11
            r6 = r13
            r7 = r1
            r1 = r5
            r13 = r12
            r11 = r4
            r12 = r1
            r4 = r2
            r2 = r12
        L_0x007e:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.L$7 = r11     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            r0.label = r3     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            java.lang.Object r8 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            if (r8 != r7) goto L_0x0097
            return r7
        L_0x0097:
            r9 = r7
            r7 = r13
            r13 = r8
            r8 = r5
            r5 = r1
            r1 = r9
        L_0x009d:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            if (r13 == 0) goto L_0x00bf
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            java.lang.Object r13 = r7.invoke(r13)     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            if (r13 == 0) goto L_0x00ba
            int r13 = r6.element     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            int r13 = r13 + r3
            r6.element = r13     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
        L_0x00ba:
            r13 = r7
            r7 = r1
            r1 = r5
            r5 = r8
            goto L_0x007e
        L_0x00bf:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00d3, all -> 0x00d1 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            int r11 = r6.element
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)
            return r11
        L_0x00d1:
            r11 = move-exception
            goto L_0x00e0
        L_0x00d3:
            r11 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00df
        L_0x00d8:
            r12 = move-exception
            r4 = r2
            r2 = r11
            r11 = r12
            goto L_0x00e0
        L_0x00dd:
            r12 = move-exception
            r2 = r12
        L_0x00df:
            throw r2     // Catch:{ all -> 0x00d8 }
        L_0x00e0:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0058, code lost:
        r13 = r11;
        r11 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c5, code lost:
        r11 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Incorrect type for immutable var: ssa=R, code=java.lang.Object, for r12v0, types: [R, T, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0057 A[ExcHandler: Throwable (r11v11 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r2v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v12 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r2v12 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:23:0x007f, B:10:0x0048, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009a A[Catch:{ Throwable -> 0x0057, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a7 A[Catch:{ Throwable -> 0x0057, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R> java.lang.Object fold(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, java.lang.Object r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super R, ? super E, ? extends R> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0064
            if (r2 != r3) goto L_0x005c
            java.lang.Object r11 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$6
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r7 = r0.L$1
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0057, all -> 0x0053 }
            r10 = r7
            r7 = r12
            r12 = r8
            r8 = r1
            r1 = r4
            r4 = r13
            r13 = r10
            goto L_0x009f
        L_0x0053:
            r11 = move-exception
        L_0x0054:
            r4 = r13
            goto L_0x00d3
        L_0x0057:
            r11 = move-exception
            r13 = r11
            r11 = r2
            goto L_0x00ce
        L_0x005c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0064:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$ObjectRef r14 = new kotlin.jvm.internal.Ref$ObjectRef
            r14.<init>()
            r14.element = r12
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r11.iterator()     // Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
            r6 = r11
            r5 = r14
            r7 = r1
            r1 = r6
            r14 = r13
            r11 = r4
            r13 = r12
            r4 = r2
            r12 = r1
            r2 = r12
        L_0x007f:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$2 = r14     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$7 = r6     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.L$8 = r11     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r0.label = r3     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            java.lang.Object r8 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            if (r8 != r7) goto L_0x009a
            return r7
        L_0x009a:
            r10 = r6
            r6 = r14
            r14 = r8
            r8 = r7
            r7 = r10
        L_0x009f:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            if (r14 == 0) goto L_0x00b7
            java.lang.Object r14 = r11.next()     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            T r9 = r5.element     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            java.lang.Object r14 = r6.invoke(r9, r14)     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r5.element = r14     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            r14 = r6
            r6 = r7
            r7 = r8
            goto L_0x007f
        L_0x00b7:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0057, all -> 0x00c5 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            T r11 = r5.element
            return r11
        L_0x00c5:
            r11 = move-exception
            goto L_0x00d3
        L_0x00c7:
            r12 = move-exception
            r4 = r2
            r2 = r11
            r11 = r12
            goto L_0x00d3
        L_0x00cc:
            r12 = move-exception
            r13 = r12
        L_0x00ce:
            throw r13     // Catch:{ all -> 0x00cf }
        L_0x00cf:
            r12 = move-exception
            r2 = r11
            r11 = r12
            goto L_0x0054
        L_0x00d3:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.fold(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0066, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00e2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Incorrect type for immutable var: ssa=R, code=java.lang.Object, for r20v0, types: [R, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0066 A[ExcHandler: Throwable (th java.lang.Throwable), PHI: r7 
      PHI: (r7v9 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r7v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r7v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r7v11 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:21:0x0098, B:10:0x004e, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b4 A[Catch:{ Throwable -> 0x0066, all -> 0x00e2 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bd A[Catch:{ Throwable -> 0x0066, all -> 0x00e2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R> java.lang.Object foldIndexed(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, java.lang.Object r20, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super R, ? super E, ? extends R> r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x0071
            if (r3 != r4) goto L_0x0069
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$7
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function3 r11 = (kotlin.jvm.functions.Function3) r11
            java.lang.Object r12 = r1.L$1
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0066 }
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r12
            r12 = r2
            r2 = r11
            r11 = r5
            r5 = r17
            r18 = r8
            r8 = r6
            r6 = r18
            goto L_0x00b5
        L_0x0063:
            r0 = move-exception
            goto L_0x00e7
        L_0x0066:
            r0 = move-exception
            goto L_0x00ec
        L_0x0069:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0071:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            r5 = r20
            r3.element = r5
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r7 = r19.iterator()     // Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
            r11 = r19
            r10 = r0
            r12 = r2
            r9 = r3
            r8 = r6
            r3 = r7
            r0 = r11
            r6 = r0
            r7 = r6
            r2 = r21
        L_0x0098:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$1 = r5     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$2 = r2     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$3 = r10     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$4 = r9     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$5 = r6     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$6 = r7     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$7 = r8     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$8 = r11     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r1.label = r4     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            java.lang.Object r13 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            if (r13 != r12) goto L_0x00b5
            return r12
        L_0x00b5:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            if (r13 == 0) goto L_0x00d4
            java.lang.Object r13 = r3.next()     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            int r14 = r10.element     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            int r15 = r14 + 1
            r10.element = r15     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14)     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            T r15 = r9.element     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            java.lang.Object r13 = r2.invoke(r14, r15, r13)     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            r9.element = r13     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            goto L_0x0098
        L_0x00d4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0066, all -> 0x00e2 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            T r0 = r9.element
            return r0
        L_0x00e2:
            r0 = move-exception
            goto L_0x00ee
        L_0x00e4:
            r0 = move-exception
            r7 = r19
        L_0x00e7:
            r8 = r6
            goto L_0x00ee
        L_0x00e9:
            r0 = move-exception
            r7 = r19
        L_0x00ec:
            r6 = r0
            throw r6     // Catch:{ all -> 0x0063 }
        L_0x00ee:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.foldIndexed(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c5 A[SYNTHETIC, Splitter:B:40:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f0 A[Catch:{ Throwable -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00fd A[Catch:{ Throwable -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R extends java.lang.Comparable<? super R>> java.lang.Object maxBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x008e
            if (r2 == r5) goto L_0x0064
            if (r2 != r4) goto L_0x005c
            java.lang.Object r13 = r0.L$7
            java.lang.Comparable r13 = (java.lang.Comparable) r13
            java.lang.Object r14 = r0.L$6
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r3 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r6 = r0.L$3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0057, all -> 0x0052 }
            r12 = r0
            r0 = r13
            r13 = r7
        L_0x004e:
            r7 = r1
            r1 = r12
            goto L_0x00f5
        L_0x0052:
            r13 = move-exception
            r14 = r13
            r13 = r7
            goto L_0x0123
        L_0x0057:
            r13 = move-exception
            r6 = r13
            r13 = r7
            goto L_0x0122
        L_0x005c:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0064:
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$3
            r6 = r2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            r12 = r7
            r7 = r14
            r14 = r12
            goto L_0x00b2
        L_0x0084:
            r13 = move-exception
            r14 = r13
            r13 = r2
            goto L_0x0123
        L_0x0089:
            r13 = move-exception
            r6 = r13
            r13 = r2
            goto L_0x0122
        L_0x008e:
            kotlin.ResultKt.throwOnFailure(r15)
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r15 = r13.iterator()     // Catch:{ Throwable -> 0x0120 }
            r0.L$0 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x0120 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0120 }
            r0.L$4 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$5 = r15     // Catch:{ Throwable -> 0x0120 }
            r0.label = r5     // Catch:{ Throwable -> 0x0120 }
            java.lang.Object r2 = r15.hasNext(r0)     // Catch:{ Throwable -> 0x0120 }
            if (r2 != r1) goto L_0x00ad
            return r1
        L_0x00ad:
            r7 = r13
            r8 = r7
            r13 = r15
            r15 = r2
            r2 = r8
        L_0x00b2:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            if (r15 != 0) goto L_0x00c5
            r13 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r13)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r13)
            return r3
        L_0x00c5:
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            java.lang.Object r3 = r14.invoke(r15)     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            java.lang.Comparable r3 = (java.lang.Comparable) r3     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            r9 = r8
            r8 = r14
            r14 = r15
            r15 = r3
            r3 = r7
            r12 = r2
            r2 = r13
            r13 = r12
        L_0x00d7:
            r0.L$0 = r9     // Catch:{ Throwable -> 0x0120 }
            r0.L$1 = r8     // Catch:{ Throwable -> 0x0120 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0120 }
            r0.L$4 = r3     // Catch:{ Throwable -> 0x0120 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x0120 }
            r0.L$6 = r14     // Catch:{ Throwable -> 0x0120 }
            r0.L$7 = r15     // Catch:{ Throwable -> 0x0120 }
            r0.label = r4     // Catch:{ Throwable -> 0x0120 }
            java.lang.Object r7 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x0120 }
            if (r7 != r1) goto L_0x00f0
            return r1
        L_0x00f0:
            r12 = r0
            r0 = r15
            r15 = r7
            goto L_0x004e
        L_0x00f5:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0120 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0120 }
            if (r15 == 0) goto L_0x0114
            java.lang.Object r15 = r2.next()     // Catch:{ Throwable -> 0x0120 }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x0120 }
            java.lang.Comparable r10 = (java.lang.Comparable) r10     // Catch:{ Throwable -> 0x0120 }
            int r11 = r0.compareTo(r10)     // Catch:{ Throwable -> 0x0120 }
            if (r11 >= 0) goto L_0x0110
            r14 = r15
            r15 = r10
            goto L_0x0111
        L_0x0110:
            r15 = r0
        L_0x0111:
            r0 = r1
            r1 = r7
            goto L_0x00d7
        L_0x0114:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r13, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r14
        L_0x011e:
            r14 = move-exception
            goto L_0x0123
        L_0x0120:
            r14 = move-exception
            r6 = r14
        L_0x0122:
            throw r6     // Catch:{ all -> 0x011e }
        L_0x0123:
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r13, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        r11 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0079, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007a, code lost:
        r3 = r10;
        r10 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ec, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ed, code lost:
        r3 = r12;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:11:0x0044, B:32:0x00a3] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079 A[ExcHandler: Throwable (r10v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r5 
      PHI: (r5v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r5v1 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v13 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v13 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v15 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v15 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:46:0x00d3, B:32:0x00a3, B:37:0x00af, B:18:0x006c, B:19:?, B:11:0x0044, B:12:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:32:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00af A[SYNTHETIC, Splitter:B:37:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00db A[Catch:{ Throwable -> 0x0079, all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object maxWith(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull java.util.Comparator<? super E> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x007e
            if (r2 == r5) goto L_0x0054
            if (r2 != r4) goto L_0x004c
            java.lang.Object r10 = r0.L$6
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            java.util.Comparator r6 = (java.util.Comparator) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            goto L_0x00d3
        L_0x0049:
            r10 = move-exception
            r11 = r10
            goto L_0x0076
        L_0x004c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0054:
            java.lang.Object r10 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            java.util.Comparator r6 = (java.util.Comparator) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            r9 = r6
            r6 = r11
            r11 = r9
            goto L_0x00a3
        L_0x0073:
            r10 = move-exception
            r11 = r10
            r3 = r2
        L_0x0076:
            r10 = r5
            goto L_0x00f3
        L_0x0079:
            r10 = move-exception
            r3 = r10
            r10 = r5
            goto L_0x00f1
        L_0x007e:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r3
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$0 = r10     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$3 = r12     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$4 = r10     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.label = r5     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            java.lang.Object r5 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            if (r5 != r1) goto L_0x009d
            return r1
        L_0x009d:
            r6 = r10
            r7 = r6
            r10 = r2
            r2 = r12
            r12 = r5
            r5 = r7
        L_0x00a3:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            if (r12 != 0) goto L_0x00af
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r2)
            return r3
        L_0x00af:
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            r3 = r2
            r2 = r6
            r6 = r11
            r11 = r10
        L_0x00b7:
            r10 = r5
            r0.L$0 = r7     // Catch:{ Throwable -> 0x00ef }
            r0.L$1 = r6     // Catch:{ Throwable -> 0x00ef }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00ef }
            r0.L$3 = r3     // Catch:{ Throwable -> 0x00ef }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00ef }
            r0.L$5 = r11     // Catch:{ Throwable -> 0x00ef }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x00ef }
            r0.label = r4     // Catch:{ Throwable -> 0x00ef }
            java.lang.Object r5 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00ef }
            if (r5 != r1) goto L_0x00cf
            return r1
        L_0x00cf:
            r9 = r5
            r5 = r10
            r10 = r12
            r12 = r9
        L_0x00d3:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            if (r12 == 0) goto L_0x00e8
            java.lang.Object r12 = r11.next()     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            int r8 = r6.compare(r10, r12)     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            if (r8 >= 0) goto L_0x00e6
            goto L_0x00b7
        L_0x00e6:
            r12 = r10
            goto L_0x00b7
        L_0x00e8:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r3)
            return r10
        L_0x00ec:
            r11 = move-exception
            r3 = r12
            goto L_0x00f3
        L_0x00ef:
            r11 = move-exception
            r3 = r11
        L_0x00f1:
            throw r3     // Catch:{ all -> 0x00f2 }
        L_0x00f2:
            r11 = move-exception
        L_0x00f3:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c5 A[SYNTHETIC, Splitter:B:40:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f0 A[Catch:{ Throwable -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00fd A[Catch:{ Throwable -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R extends java.lang.Comparable<? super R>> java.lang.Object minBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x008e
            if (r2 == r5) goto L_0x0064
            if (r2 != r4) goto L_0x005c
            java.lang.Object r13 = r0.L$7
            java.lang.Comparable r13 = (java.lang.Comparable) r13
            java.lang.Object r14 = r0.L$6
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r3 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r6 = r0.L$3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0057, all -> 0x0052 }
            r12 = r0
            r0 = r13
            r13 = r7
        L_0x004e:
            r7 = r1
            r1 = r12
            goto L_0x00f5
        L_0x0052:
            r13 = move-exception
            r14 = r13
            r13 = r7
            goto L_0x0123
        L_0x0057:
            r13 = move-exception
            r6 = r13
            r13 = r7
            goto L_0x0122
        L_0x005c:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0064:
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$3
            r6 = r2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            r12 = r7
            r7 = r14
            r14 = r12
            goto L_0x00b2
        L_0x0084:
            r13 = move-exception
            r14 = r13
            r13 = r2
            goto L_0x0123
        L_0x0089:
            r13 = move-exception
            r6 = r13
            r13 = r2
            goto L_0x0122
        L_0x008e:
            kotlin.ResultKt.throwOnFailure(r15)
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r15 = r13.iterator()     // Catch:{ Throwable -> 0x0120 }
            r0.L$0 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x0120 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0120 }
            r0.L$4 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$5 = r15     // Catch:{ Throwable -> 0x0120 }
            r0.label = r5     // Catch:{ Throwable -> 0x0120 }
            java.lang.Object r2 = r15.hasNext(r0)     // Catch:{ Throwable -> 0x0120 }
            if (r2 != r1) goto L_0x00ad
            return r1
        L_0x00ad:
            r7 = r13
            r8 = r7
            r13 = r15
            r15 = r2
            r2 = r8
        L_0x00b2:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            if (r15 != 0) goto L_0x00c5
            r13 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r13)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r13)
            return r3
        L_0x00c5:
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            java.lang.Object r3 = r14.invoke(r15)     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            java.lang.Comparable r3 = (java.lang.Comparable) r3     // Catch:{ Throwable -> 0x0089, all -> 0x0084 }
            r9 = r8
            r8 = r14
            r14 = r15
            r15 = r3
            r3 = r7
            r12 = r2
            r2 = r13
            r13 = r12
        L_0x00d7:
            r0.L$0 = r9     // Catch:{ Throwable -> 0x0120 }
            r0.L$1 = r8     // Catch:{ Throwable -> 0x0120 }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x0120 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0120 }
            r0.L$4 = r3     // Catch:{ Throwable -> 0x0120 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x0120 }
            r0.L$6 = r14     // Catch:{ Throwable -> 0x0120 }
            r0.L$7 = r15     // Catch:{ Throwable -> 0x0120 }
            r0.label = r4     // Catch:{ Throwable -> 0x0120 }
            java.lang.Object r7 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x0120 }
            if (r7 != r1) goto L_0x00f0
            return r1
        L_0x00f0:
            r12 = r0
            r0 = r15
            r15 = r7
            goto L_0x004e
        L_0x00f5:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x0120 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x0120 }
            if (r15 == 0) goto L_0x0114
            java.lang.Object r15 = r2.next()     // Catch:{ Throwable -> 0x0120 }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x0120 }
            java.lang.Comparable r10 = (java.lang.Comparable) r10     // Catch:{ Throwable -> 0x0120 }
            int r11 = r0.compareTo(r10)     // Catch:{ Throwable -> 0x0120 }
            if (r11 <= 0) goto L_0x0110
            r14 = r15
            r15 = r10
            goto L_0x0111
        L_0x0110:
            r15 = r0
        L_0x0111:
            r0 = r1
            r1 = r7
            goto L_0x00d7
        L_0x0114:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r13, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r14
        L_0x011e:
            r14 = move-exception
            goto L_0x0123
        L_0x0120:
            r14 = move-exception
            r6 = r14
        L_0x0122:
            throw r6     // Catch:{ all -> 0x011e }
        L_0x0123:
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r13, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        r11 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0079, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007a, code lost:
        r3 = r10;
        r10 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ec, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ed, code lost:
        r3 = r12;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:11:0x0044, B:32:0x00a3] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079 A[ExcHandler: Throwable (r10v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r5 
      PHI: (r5v8 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r5v1 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v5 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v13 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v13 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v15 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v15 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:46:0x00d3, B:32:0x00a3, B:37:0x00af, B:18:0x006c, B:19:?, B:11:0x0044, B:12:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:32:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00af A[SYNTHETIC, Splitter:B:37:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00db A[Catch:{ Throwable -> 0x0079, all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object minWith(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull java.util.Comparator<? super E> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x007e
            if (r2 == r5) goto L_0x0054
            if (r2 != r4) goto L_0x004c
            java.lang.Object r10 = r0.L$6
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            java.util.Comparator r6 = (java.util.Comparator) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            goto L_0x00d3
        L_0x0049:
            r10 = move-exception
            r11 = r10
            goto L_0x0076
        L_0x004c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0054:
            java.lang.Object r10 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            java.util.Comparator r6 = (java.util.Comparator) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            r9 = r6
            r6 = r11
            r11 = r9
            goto L_0x00a3
        L_0x0073:
            r10 = move-exception
            r11 = r10
            r3 = r2
        L_0x0076:
            r10 = r5
            goto L_0x00f3
        L_0x0079:
            r10 = move-exception
            r3 = r10
            r10 = r5
            goto L_0x00f1
        L_0x007e:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r3
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$0 = r10     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$3 = r12     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$4 = r10     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            r0.label = r5     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            java.lang.Object r5 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
            if (r5 != r1) goto L_0x009d
            return r1
        L_0x009d:
            r6 = r10
            r7 = r6
            r10 = r2
            r2 = r12
            r12 = r5
            r5 = r7
        L_0x00a3:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            if (r12 != 0) goto L_0x00af
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r2)
            return r3
        L_0x00af:
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x0079, all -> 0x0073 }
            r3 = r2
            r2 = r6
            r6 = r11
            r11 = r10
        L_0x00b7:
            r10 = r5
            r0.L$0 = r7     // Catch:{ Throwable -> 0x00ef }
            r0.L$1 = r6     // Catch:{ Throwable -> 0x00ef }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00ef }
            r0.L$3 = r3     // Catch:{ Throwable -> 0x00ef }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00ef }
            r0.L$5 = r11     // Catch:{ Throwable -> 0x00ef }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x00ef }
            r0.label = r4     // Catch:{ Throwable -> 0x00ef }
            java.lang.Object r5 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00ef }
            if (r5 != r1) goto L_0x00cf
            return r1
        L_0x00cf:
            r9 = r5
            r5 = r10
            r10 = r12
            r12 = r9
        L_0x00d3:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            if (r12 == 0) goto L_0x00e8
            java.lang.Object r12 = r11.next()     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            int r8 = r6.compare(r10, r12)     // Catch:{ Throwable -> 0x0079, all -> 0x0049 }
            if (r8 <= 0) goto L_0x00e6
            goto L_0x00b7
        L_0x00e6:
            r12 = r10
            goto L_0x00b7
        L_0x00e8:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r3)
            return r10
        L_0x00ec:
            r11 = move-exception
            r3 = r12
            goto L_0x00f3
        L_0x00ef:
            r11 = move-exception
            r3 = r11
        L_0x00f1:
            throw r3     // Catch:{ all -> 0x00f2 }
        L_0x00f2:
            r11 = move-exception
        L_0x00f3:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0082, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0083, code lost:
        r1 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071 A[Catch:{ Throwable -> 0x0082, all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072 A[Catch:{ Throwable -> 0x0082, all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0082 A[ExcHandler: Throwable (r6v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r5 
      PHI: (r5v4 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) = (r5v6 kotlinx.coroutines.channels.ReceiveChannel<? extends E>), (r5v0 kotlinx.coroutines.channels.ReceiveChannel<? extends E>) binds: [B:24:0x0069, B:19:0x0051] A[DONT_GENERATE, DONT_INLINE], Splitter:B:19:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object none(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Throwable -> 0x0041 }
            r0 = r5
            r5 = r1
            goto L_0x0069
        L_0x003c:
            r6 = move-exception
            r0 = r6
            r6 = r5
            r5 = r1
            goto L_0x0086
        L_0x0041:
            r5 = move-exception
            goto L_0x0085
        L_0x0043:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r0.L$0 = r5     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r0.L$1 = r5     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r0.label = r3     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.Object r0 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r0 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r4 = r0
            r0 = r6
            r6 = r4
        L_0x0069:
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ Throwable -> 0x0082, all -> 0x007b }
            boolean r6 = r6.booleanValue()     // Catch:{ Throwable -> 0x0082, all -> 0x007b }
            if (r6 != 0) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r3 = 0
        L_0x0073:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ Throwable -> 0x0082, all -> 0x007b }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r0)
            return r6
        L_0x007b:
            r6 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x0086
        L_0x0080:
            r0 = move-exception
            goto L_0x0086
        L_0x0082:
            r6 = move-exception
            r1 = r5
            r5 = r6
        L_0x0085:
            throw r5     // Catch:{ all -> 0x003c }
        L_0x0086:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Throwable -> 0x00cc, all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ba A[SYNTHETIC, Splitter:B:36:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object none(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r10 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x0054, all -> 0x004e }
            r8 = r5
            r5 = r11
            r11 = r7
            r7 = r1
            r1 = r8
            r9 = r4
            r4 = r2
            r2 = r9
            goto L_0x008f
        L_0x004e:
            r10 = move-exception
            r8 = r4
            r4 = r2
            r2 = r8
            goto L_0x00d9
        L_0x0054:
            r10 = move-exception
            r2 = r10
            r10 = r4
            goto L_0x00d8
        L_0x0059:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            r2 = r12
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r12 = r10.iterator()     // Catch:{ Throwable -> 0x00d6 }
            r5 = r10
            r6 = r1
            r4 = r2
            r1 = r5
            r2 = r1
            r10 = r12
            r12 = r11
            r11 = r2
        L_0x0074:
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$2 = r1     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$3 = r2     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$4 = r4     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.L$6 = r10     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r0.label = r3     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r7 = r10.hasNext(r0)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r7 != r6) goto L_0x008b
            return r6
        L_0x008b:
            r8 = r6
            r6 = r12
            r12 = r7
            r7 = r8
        L_0x008f:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r12 == 0) goto L_0x00ba
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Object r12 = r6.invoke(r12)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            if (r12 == 0) goto L_0x00b7
            r10 = 0
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            r11 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r11)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r11)
            return r10
        L_0x00b7:
            r12 = r6
            r6 = r7
            goto L_0x0074
        L_0x00ba:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00cc, all -> 0x00ca }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r10
        L_0x00ca:
            r10 = move-exception
            goto L_0x00d9
        L_0x00cc:
            r10 = move-exception
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x00d8
        L_0x00d1:
            r11 = move-exception
            r4 = r2
            r2 = r10
            r10 = r11
            goto L_0x00d9
        L_0x00d6:
            r11 = move-exception
            r2 = r11
        L_0x00d8:
            throw r2     // Catch:{ all -> 0x00d1 }
        L_0x00d9:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b1 A[Catch:{ Throwable -> 0x007f, all -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00dd A[Catch:{ Throwable -> 0x004d, all -> 0x0048 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f1 A[SYNTHETIC, Splitter:B:50:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <S, E extends S> java.lang.Object reduce(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super S, ? super E, ? extends S> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super S> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0084
            if (r2 == r4) goto L_0x005a
            if (r2 != r3) goto L_0x0052
            java.lang.Object r10 = r0.L$6
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$3
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            goto L_0x00d5
        L_0x0048:
            r10 = move-exception
            r11 = r10
            r10 = r6
            goto L_0x0100
        L_0x004d:
            r10 = move-exception
            r5 = r10
            r10 = r6
            goto L_0x00ff
        L_0x0052:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x005a:
            java.lang.Object r10 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$3
            r5 = r2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
            r9 = r6
            r6 = r11
            r11 = r9
            goto L_0x00a9
        L_0x007a:
            r10 = move-exception
            r11 = r10
            r10 = r2
            goto L_0x0100
        L_0x007f:
            r10 = move-exception
            r5 = r10
            r10 = r2
            goto L_0x00ff
        L_0x0084:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            r5 = r12
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            kotlinx.coroutines.channels.ChannelIterator r12 = r10.iterator()     // Catch:{ Throwable -> 0x00fd }
            r0.L$0 = r10     // Catch:{ Throwable -> 0x00fd }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x00fd }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00fd }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x00fd }
            r0.L$4 = r10     // Catch:{ Throwable -> 0x00fd }
            r0.L$5 = r12     // Catch:{ Throwable -> 0x00fd }
            r0.label = r4     // Catch:{ Throwable -> 0x00fd }
            java.lang.Object r2 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x00fd }
            if (r2 != r1) goto L_0x00a4
            return r1
        L_0x00a4:
            r6 = r10
            r7 = r6
            r10 = r12
            r12 = r2
            r2 = r7
        L_0x00a9:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
            if (r12 == 0) goto L_0x00f1
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
            r8 = r7
            r7 = r11
            r11 = r10
            r10 = r2
            r2 = r6
        L_0x00ba:
            r0.L$0 = r8     // Catch:{ Throwable -> 0x00fd }
            r0.L$1 = r7     // Catch:{ Throwable -> 0x00fd }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x00fd }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x00fd }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00fd }
            r0.L$5 = r11     // Catch:{ Throwable -> 0x00fd }
            r0.L$6 = r12     // Catch:{ Throwable -> 0x00fd }
            r0.label = r3     // Catch:{ Throwable -> 0x00fd }
            java.lang.Object r6 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x00fd }
            if (r6 != r1) goto L_0x00d1
            return r1
        L_0x00d1:
            r9 = r6
            r6 = r10
            r10 = r12
            r12 = r9
        L_0x00d5:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            boolean r12 = r12.booleanValue()     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            if (r12 == 0) goto L_0x00e7
            java.lang.Object r12 = r11.next()     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            java.lang.Object r12 = r7.invoke(r10, r12)     // Catch:{ Throwable -> 0x004d, all -> 0x0048 }
            r10 = r6
            goto L_0x00ba
        L_0x00e7:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r10
        L_0x00f1:
            java.lang.UnsupportedOperationException r10 = new java.lang.UnsupportedOperationException     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
            java.lang.String r11 = "Empty channel can't be reduced."
            r10.<init>(r11)     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
            throw r10     // Catch:{ Throwable -> 0x007f, all -> 0x007a }
        L_0x00fb:
            r11 = move-exception
            goto L_0x0100
        L_0x00fd:
            r11 = move-exception
            r5 = r11
        L_0x00ff:
            throw r5     // Catch:{ all -> 0x00fb }
        L_0x0100:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduce(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b3 A[Catch:{ Throwable -> 0x0081, all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e2 A[Catch:{ Throwable -> 0x004f, all -> 0x004a }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00fc A[SYNTHETIC, Splitter:B:50:0x00fc] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <S, E extends S> java.lang.Object reduceIndexed(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super S, ? super E, ? extends S> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super S> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0086
            if (r2 == r4) goto L_0x005c
            if (r2 != r3) goto L_0x0054
            java.lang.Object r12 = r0.L$6
            int r13 = r0.I$0
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function3 r8 = (kotlin.jvm.functions.Function3) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x004f, all -> 0x004a }
            goto L_0x00da
        L_0x004a:
            r12 = move-exception
            r13 = r12
            r12 = r7
            goto L_0x010b
        L_0x004f:
            r12 = move-exception
            r6 = r12
            r12 = r7
            goto L_0x010a
        L_0x0054:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x005c:
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$3
            r6 = r2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            r11 = r5
            r5 = r13
            r13 = r11
            goto L_0x00ab
        L_0x007c:
            r12 = move-exception
            r13 = r12
            r12 = r2
            goto L_0x010b
        L_0x0081:
            r12 = move-exception
            r6 = r12
            r12 = r2
            goto L_0x010a
        L_0x0086:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = 0
            r6 = r14
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r14 = r12.iterator()     // Catch:{ Throwable -> 0x0108 }
            r0.L$0 = r12     // Catch:{ Throwable -> 0x0108 }
            r0.L$1 = r13     // Catch:{ Throwable -> 0x0108 }
            r0.L$2 = r12     // Catch:{ Throwable -> 0x0108 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0108 }
            r0.L$4 = r12     // Catch:{ Throwable -> 0x0108 }
            r0.L$5 = r14     // Catch:{ Throwable -> 0x0108 }
            r0.label = r4     // Catch:{ Throwable -> 0x0108 }
            java.lang.Object r2 = r14.hasNext(r0)     // Catch:{ Throwable -> 0x0108 }
            if (r2 != r1) goto L_0x00a6
            return r1
        L_0x00a6:
            r5 = r12
            r7 = r5
            r12 = r14
            r14 = r2
            r2 = r7
        L_0x00ab:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            if (r14 == 0) goto L_0x00fc
            java.lang.Object r14 = r12.next()     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            r8 = r13
            r9 = r7
            r13 = 1
            r11 = r2
            r2 = r12
            r12 = r11
        L_0x00bd:
            r0.L$0 = r9     // Catch:{ Throwable -> 0x0108 }
            r0.L$1 = r8     // Catch:{ Throwable -> 0x0108 }
            r0.L$2 = r12     // Catch:{ Throwable -> 0x0108 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x0108 }
            r0.L$4 = r5     // Catch:{ Throwable -> 0x0108 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x0108 }
            r0.I$0 = r13     // Catch:{ Throwable -> 0x0108 }
            r0.L$6 = r14     // Catch:{ Throwable -> 0x0108 }
            r0.label = r3     // Catch:{ Throwable -> 0x0108 }
            java.lang.Object r7 = r2.hasNext(r0)     // Catch:{ Throwable -> 0x0108 }
            if (r7 != r1) goto L_0x00d6
            return r1
        L_0x00d6:
            r11 = r7
            r7 = r12
            r12 = r14
            r14 = r11
        L_0x00da:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x004f, all -> 0x004a }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x004f, all -> 0x004a }
            if (r14 == 0) goto L_0x00f2
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13)     // Catch:{ Throwable -> 0x004f, all -> 0x004a }
            int r13 = r13 + 1
            java.lang.Object r10 = r2.next()     // Catch:{ Throwable -> 0x004f, all -> 0x004a }
            java.lang.Object r14 = r8.invoke(r14, r12, r10)     // Catch:{ Throwable -> 0x004f, all -> 0x004a }
            r12 = r7
            goto L_0x00bd
        L_0x00f2:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r12
        L_0x00fc:
            java.lang.UnsupportedOperationException r12 = new java.lang.UnsupportedOperationException     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            java.lang.String r13 = "Empty channel can't be reduced."
            r12.<init>(r13)     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            java.lang.Throwable r12 = (java.lang.Throwable) r12     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            throw r12     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
        L_0x0106:
            r13 = move-exception
            goto L_0x010b
        L_0x0108:
            r13 = move-exception
            r6 = r13
        L_0x010a:
            throw r6     // Catch:{ all -> 0x0106 }
        L_0x010b:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r12, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduceIndexed(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0099 A[Catch:{ Throwable -> 0x00cf, all -> 0x00cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a5 A[Catch:{ Throwable -> 0x00cf, all -> 0x00cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object sumBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Integer> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0065
            if (r2 != r3) goto L_0x005d
            java.lang.Object r12 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$5
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x0058, all -> 0x0052 }
            r10 = r5
            r5 = r13
            r13 = r8
            r8 = r1
            r1 = r10
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x009d
        L_0x0052:
            r12 = move-exception
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x00dc
        L_0x0058:
            r12 = move-exception
            r2 = r12
            r12 = r4
            goto L_0x00db
        L_0x005d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0065:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            r2 = 0
            r14.element = r2
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r4 = r12.iterator()     // Catch:{ Throwable -> 0x00d9 }
            r5 = r12
            r6 = r14
            r7 = r1
            r1 = r5
            r14 = r13
            r12 = r4
            r13 = r1
            r4 = r2
            r2 = r13
        L_0x0080:
            r0.L$0 = r13     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.L$1 = r14     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.L$3 = r1     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.L$6 = r5     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.L$7 = r12     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r0.label = r3     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            java.lang.Object r8 = r12.hasNext(r0)     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            if (r8 != r7) goto L_0x0099
            return r7
        L_0x0099:
            r10 = r7
            r7 = r14
            r14 = r8
            r8 = r10
        L_0x009d:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            if (r14 == 0) goto L_0x00bb
            java.lang.Object r14 = r12.next()     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            int r9 = r6.element     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            java.lang.Object r14 = r7.invoke(r14)     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            java.lang.Number r14 = (java.lang.Number) r14     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            int r14 = r14.intValue()     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            int r9 = r9 + r14
            r6.element = r9     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            r14 = r7
            r7 = r8
            goto L_0x0080
        L_0x00bb:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00cf, all -> 0x00cd }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            int r12 = r6.element
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            return r12
        L_0x00cd:
            r12 = move-exception
            goto L_0x00dc
        L_0x00cf:
            r12 = move-exception
            r10 = r2
            r2 = r12
            r12 = r10
            goto L_0x00db
        L_0x00d4:
            r13 = move-exception
            r4 = r2
            r2 = r12
            r12 = r13
            goto L_0x00dc
        L_0x00d9:
            r13 = move-exception
            r2 = r13
        L_0x00db:
            throw r2     // Catch:{ all -> 0x00d4 }
        L_0x00dc:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a5 A[Catch:{ Throwable -> 0x00db, all -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b2 A[Catch:{ Throwable -> 0x00db, all -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object sumByDouble(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Double> r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Double> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x006e
            if (r3 != r4) goto L_0x0066
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r5 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$5
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$2
            kotlin.jvm.internal.Ref$DoubleRef r9 = (kotlin.jvm.internal.Ref.DoubleRef) r9
            java.lang.Object r10 = r1.L$1
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Throwable -> 0x0063 }
            r16 = r10
            r10 = r2
            r2 = r16
            r17 = r8
            r8 = r5
            r5 = r17
            r18 = r7
            r7 = r6
            r6 = r18
            goto L_0x00aa
        L_0x005b:
            r0 = move-exception
            r16 = r7
            r7 = r6
            r6 = r16
            goto L_0x00e8
        L_0x0063:
            r0 = move-exception
            goto L_0x00e6
        L_0x0066:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$DoubleRef r0 = new kotlin.jvm.internal.Ref$DoubleRef
            r0.<init>()
            r5 = 0
            r0.element = r5
            r3 = 0
            r6 = r3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r3 = r19.iterator()     // Catch:{ Throwable -> 0x00e3, all -> 0x00de }
            r5 = r19
            r8 = r5
            r9 = r0
            r10 = r2
            r7 = r6
            r0 = r8
            r6 = r0
            r2 = r20
        L_0x008c:
            r1.L$0 = r0     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.L$1 = r2     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.L$2 = r9     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.L$3 = r5     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.L$4 = r6     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.L$5 = r7     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.L$6 = r8     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.L$7 = r3     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r1.label = r4     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            java.lang.Object r11 = r3.hasNext(r1)     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            if (r11 != r10) goto L_0x00a5
            return r10
        L_0x00a5:
            r16 = r11
            r11 = r0
            r0 = r16
        L_0x00aa:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            if (r0 == 0) goto L_0x00c7
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            double r12 = r9.element     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            java.lang.Object r0 = r2.invoke(r0)     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            double r14 = r0.doubleValue()     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            double r12 = r12 + r14
            r9.element = r12     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            r0 = r11
            goto L_0x008c
        L_0x00c7:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00db, all -> 0x00d9 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            double r0 = r9.element
            java.lang.Double r0 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r0)
            return r0
        L_0x00d9:
            r0 = move-exception
            goto L_0x00e8
        L_0x00db:
            r0 = move-exception
            r7 = r6
            goto L_0x00e6
        L_0x00de:
            r0 = move-exception
            r7 = r6
            r6 = r19
            goto L_0x00e8
        L_0x00e3:
            r0 = move-exception
            r7 = r19
        L_0x00e6:
            r6 = r0
            throw r6     // Catch:{ all -> 0x005b }
        L_0x00e8:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumByDouble(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E> ReceiveChannel<E> requireNoNulls(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$requireNoNulls");
        return map$default(receiveChannel, null, new ChannelsKt__Channels_commonKt$requireNoNulls$1(receiveChannel, null), 1, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a1 A[Catch:{ Throwable -> 0x00da, all -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ad A[Catch:{ Throwable -> 0x00da, all -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object partition(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<? extends E>, ? extends java.util.List<? extends E>>> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0069
            if (r2 != r3) goto L_0x0061
            java.lang.Object r13 = r0.L$8
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$7
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r4 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$3
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r7 = r0.L$2
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x005c, all -> 0x0056 }
            r11 = r5
            r5 = r14
            r14 = r9
            r9 = r1
            r1 = r11
            r12 = r4
            r4 = r2
            r2 = r12
            goto L_0x00a5
        L_0x0056:
            r13 = move-exception
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x00e9
        L_0x005c:
            r13 = move-exception
            r2 = r13
            r13 = r4
            goto L_0x00e5
        L_0x0061:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r15)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r4 = 0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlinx.coroutines.channels.ChannelIterator r5 = r13.iterator()     // Catch:{ Throwable -> 0x00e3, all -> 0x00df }
            r7 = r15
            r8 = r1
            r6 = r2
            r1 = r13
            r2 = r1
            r15 = r14
            r14 = r2
            r13 = r5
            r5 = r14
        L_0x0086:
            r0.L$0 = r14     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$1 = r15     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$4 = r1     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$5 = r2     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$7 = r5     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.L$8 = r13     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            r0.label = r3     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            java.lang.Object r9 = r13.hasNext(r0)     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            if (r9 != r8) goto L_0x00a1
            return r8
        L_0x00a1:
            r11 = r8
            r8 = r15
            r15 = r9
            r9 = r11
        L_0x00a5:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            if (r15 == 0) goto L_0x00c7
            java.lang.Object r15 = r13.next()     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            java.lang.Object r10 = r8.invoke(r15)     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            if (r10 == 0) goto L_0x00c1
            r7.add(r15)     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            goto L_0x00c4
        L_0x00c1:
            r6.add(r15)     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
        L_0x00c4:
            r15 = r8
            r8 = r9
            goto L_0x0086
        L_0x00c7:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00da, all -> 0x00d8 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            kotlin.Pair r13 = new kotlin.Pair
            r13.<init>(r7, r6)
            return r13
        L_0x00d8:
            r13 = move-exception
            goto L_0x00e9
        L_0x00da:
            r13 = move-exception
            r11 = r2
            r2 = r13
            r13 = r11
            goto L_0x00e5
        L_0x00df:
            r14 = move-exception
        L_0x00e0:
            r2 = r13
            r13 = r14
            goto L_0x00e9
        L_0x00e3:
            r14 = move-exception
            r2 = r14
        L_0x00e5:
            throw r2     // Catch:{ all -> 0x00e6 }
        L_0x00e6:
            r14 = move-exception
            r4 = r2
            goto L_0x00e0
        L_0x00e9:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.partition(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object partition$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        arrayList.add(next);
                    } else {
                        arrayList2.add(next);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return new Pair(arrayList, arrayList2);
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, R> ReceiveChannel<Pair<E, R>> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$zip");
        Intrinsics.checkParameterIsNotNull(receiveChannel2, "other");
        return zip$default(receiveChannel, receiveChannel2, null, ChannelsKt__Channels_commonKt$zip$1.INSTANCE, 2, null);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    public static final <E, R, V> ReceiveChannel<V> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super R, ? extends V> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "$this$zip");
        Intrinsics.checkParameterIsNotNull(receiveChannel2, "other");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new ChannelsKt__Channels_commonKt$zip$2(receiveChannel, receiveChannel2, function2, null), 2, null);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object consumeEach$$forInline(@NotNull BroadcastChannel broadcastChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        ReceiveChannel openSubscription = broadcastChannel.openSubscription();
        try {
            ChannelIterator it = openSubscription.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    return Unit.INSTANCE;
                }
                function1.invoke(it.next());
            }
        } finally {
            InlineMarker.finallyStart(1);
            DefaultImpls.cancel$default(openSubscription, (CancellationException) null, 1, (Object) null);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ExperimentalCoroutinesApi
    private static final Object consumeEach$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    function1.invoke(it.next());
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return unit;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object consumeEachIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    function1.invoke(new IndexedValue(i, it.next()));
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Unit.INSTANCE;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r8 = r9.invoke(java.lang.Integer.valueOf(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004e, code lost:
        r9 = 2;
        kotlin.jvm.internal.InlineMarker.finallyStart(2);
     */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object elementAtOrElse$$forInline(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel r7, int r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1 r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            if (r8 >= 0) goto L_0x0019
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0055 }
            java.lang.Object r8 = r9.invoke(r8)     // Catch:{ Throwable -> 0x0055 }
            r9 = 4
            kotlin.jvm.internal.InlineMarker.finallyStart(r9)
        L_0x0012:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r9)
            return r8
        L_0x0019:
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ Throwable -> 0x0055 }
            r3 = 0
            r4 = 0
        L_0x001f:
            kotlin.jvm.internal.InlineMarker.mark(r3)     // Catch:{ Throwable -> 0x0055 }
            java.lang.Object r5 = r2.hasNext(r10)     // Catch:{ Throwable -> 0x0055 }
            kotlin.jvm.internal.InlineMarker.mark(r1)     // Catch:{ Throwable -> 0x0055 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ Throwable -> 0x0055 }
            boolean r5 = r5.booleanValue()     // Catch:{ Throwable -> 0x0055 }
            if (r5 == 0) goto L_0x0046
            java.lang.Object r5 = r2.next()     // Catch:{ Throwable -> 0x0055 }
            int r6 = r4 + 1
            if (r8 != r4) goto L_0x0044
            r8 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            return r5
        L_0x0044:
            r4 = r6
            goto L_0x001f
        L_0x0046:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0055 }
            java.lang.Object r8 = r9.invoke(r8)     // Catch:{ Throwable -> 0x0055 }
            r9 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r9)
            goto L_0x0012
        L_0x0053:
            r8 = move-exception
            goto L_0x0058
        L_0x0055:
            r8 = move-exception
            r0 = r8
            throw r0     // Catch:{ all -> 0x0053 }
        L_0x0058:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrElse$$forInline(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object find$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Object next;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    next = it.next();
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return null;
                }
            } while (!((Boolean) function1.invoke(next)).booleanValue());
            InlineMarker.finallyStart(2);
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(2);
            return next;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object findLast$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Object obj = null;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        obj = next;
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return obj;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object first$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Object next;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    next = it.next();
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    throw new NoSuchElementException("ReceiveChannel contains no element matching the predicate.");
                }
            } while (!((Boolean) function1.invoke(next)).booleanValue());
            InlineMarker.finallyStart(2);
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(2);
            return next;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object firstOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Object next;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    next = it.next();
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return null;
                }
            } while (!((Boolean) function1.invoke(next)).booleanValue());
            InlineMarker.finallyStart(2);
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(2);
            return next;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object indexOfFirst$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(-1);
                } else if (((Boolean) function1.invoke(it.next())).booleanValue()) {
                    Integer valueOf = Integer.valueOf(i);
                    InlineMarker.finallyStart(2);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(2);
                    return valueOf;
                } else {
                    i++;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object indexOfLast$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = -1;
            int i2 = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    if (((Boolean) function1.invoke(it.next())).booleanValue()) {
                        i = i2;
                    }
                    i2++;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(i);
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object last$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object obj = null;
            boolean z = false;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    break;
                }
                Object next = it.next();
                if (((Boolean) function1.invoke(next)).booleanValue()) {
                    obj = next;
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(1);
            if (z) {
                return obj;
            }
            throw new NoSuchElementException("ReceiveChannel contains no element matching the predicate.");
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object lastOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Object obj = null;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        obj = next;
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return obj;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object single$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object obj = null;
            boolean z = false;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        if (!z) {
                            obj = next;
                            z = true;
                        } else {
                            throw new IllegalArgumentException("ReceiveChannel contains more than one matching element.");
                        }
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    if (z) {
                        return obj;
                    }
                    throw new NoSuchElementException("ReceiveChannel contains no element matching the predicate.");
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r10 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        if (r5 != false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        return r6;
     */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object singleOrNull$$forInline(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1 r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r9.iterator()     // Catch:{ Throwable -> 0x004f }
            r4 = 0
            r6 = r0
            r5 = 0
        L_0x000c:
            kotlin.jvm.internal.InlineMarker.mark(r4)     // Catch:{ Throwable -> 0x004f }
            java.lang.Object r7 = r3.hasNext(r11)     // Catch:{ Throwable -> 0x004f }
            kotlin.jvm.internal.InlineMarker.mark(r2)     // Catch:{ Throwable -> 0x004f }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ Throwable -> 0x004f }
            boolean r7 = r7.booleanValue()     // Catch:{ Throwable -> 0x004f }
            if (r7 == 0) goto L_0x003e
            java.lang.Object r7 = r3.next()     // Catch:{ Throwable -> 0x004f }
            java.lang.Object r8 = r10.invoke(r7)     // Catch:{ Throwable -> 0x004f }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Throwable -> 0x004f }
            boolean r8 = r8.booleanValue()     // Catch:{ Throwable -> 0x004f }
            if (r8 == 0) goto L_0x000c
            if (r5 == 0) goto L_0x003b
            r10 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r10)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r10)
            return r0
        L_0x003b:
            r6 = r7
            r5 = 1
            goto L_0x000c
        L_0x003e:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x004f }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            if (r5 != 0) goto L_0x004c
            return r0
        L_0x004c:
            return r6
        L_0x004d:
            r10 = move-exception
            goto L_0x0052
        L_0x004f:
            r10 = move-exception
            r1 = r10
            throw r1     // Catch:{ all -> 0x004d }
        L_0x0052:
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object filterIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, it.next());
                    int component1 = indexedValue.component1();
                    Object component2 = indexedValue.component2();
                    if (((Boolean) function2.invoke(Integer.valueOf(component1), component2)).booleanValue()) {
                        collection.add(component2);
                    }
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object filterIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, it.next());
                    int component1 = indexedValue.component1();
                    Object component2 = indexedValue.component2();
                    if (((Boolean) function2.invoke(Integer.valueOf(component1), component2)).booleanValue()) {
                        InlineMarker.mark(0);
                        sendChannel.send(component2, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object filterNotTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (!((Boolean) function1.invoke(next)).booleanValue()) {
                        collection.add(next);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object filterNotTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (!((Boolean) function1.invoke(next)).booleanValue()) {
                        InlineMarker.mark(0);
                        sendChannel.send(next, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object filterTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        collection.add(next);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object filterTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        InlineMarker.mark(0);
                        sendChannel.send(next, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object associateByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    map.put(function1.invoke(next), next);
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object associateByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    map.put(function1.invoke(next), function12.invoke(next));
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object associateTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Pair pair = (Pair) function1.invoke(it.next());
                    map.put(pair.getFirst(), pair.getSecond());
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object groupByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    Object invoke = function1.invoke(next);
                    Object obj = map.get(invoke);
                    if (obj == null) {
                        obj = new ArrayList();
                        map.put(invoke, obj);
                    }
                    ((List) obj).add(next);
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object groupByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object next = it.next();
                    Object invoke = function1.invoke(next);
                    Object obj = map.get(invoke);
                    if (obj == null) {
                        obj = new ArrayList();
                        map.put(invoke, obj);
                    }
                    ((List) obj).add(function12.invoke(next));
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapIndexedNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, it.next());
                    int component1 = indexedValue.component1();
                    Object invoke = function2.invoke(Integer.valueOf(component1), indexedValue.component2());
                    if (invoke != null) {
                        collection.add(invoke);
                    }
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapIndexedNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, it.next());
                    int component1 = indexedValue.component1();
                    Object invoke = function2.invoke(Integer.valueOf(component1), indexedValue.component2());
                    if (invoke != null) {
                        InlineMarker.mark(0);
                        sendChannel.send(invoke, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    collection.add(function2.invoke(Integer.valueOf(i), it.next()));
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    Object invoke = function2.invoke(Integer.valueOf(i), it.next());
                    InlineMarker.mark(0);
                    sendChannel.send(invoke, continuation);
                    InlineMarker.mark(2);
                    InlineMarker.mark(1);
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object invoke = function1.invoke(it.next());
                    if (invoke != null) {
                        collection.add(invoke);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object invoke = function1.invoke(it.next());
                    if (invoke != null) {
                        InlineMarker.mark(0);
                        sendChannel.send(invoke, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    collection.add(function1.invoke(it.next()));
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object mapTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    Object invoke = function1.invoke(it.next());
                    InlineMarker.mark(0);
                    sendChannel.send(invoke, continuation);
                    InlineMarker.mark(2);
                    InlineMarker.mark(1);
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object all$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Boolean.valueOf(true);
                }
            } while (((Boolean) function1.invoke(it.next())).booleanValue());
            Boolean valueOf = Boolean.valueOf(false);
            InlineMarker.finallyStart(2);
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(2);
            return valueOf;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object any$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Boolean.valueOf(false);
                }
            } while (!((Boolean) function1.invoke(it.next())).booleanValue());
            Boolean valueOf = Boolean.valueOf(true);
            InlineMarker.finallyStart(2);
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(2);
            return valueOf;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object count$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(i);
                } else if (((Boolean) function1.invoke(it.next())).booleanValue()) {
                    i++;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object fold$$forInline(@NotNull ReceiveChannel receiveChannel, Object obj, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    obj = function2.invoke(obj, it.next());
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return obj;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object foldIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, Object obj, @NotNull Function3 function3, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object obj2 = obj;
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    int i2 = i + 1;
                    obj2 = function3.invoke(Integer.valueOf(i), obj2, it.next());
                    i = i2;
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return obj2;
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object maxBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        int i;
        Object obj = null;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (!((Boolean) hasNext).booleanValue()) {
                i = 3;
                InlineMarker.finallyStart(3);
            } else {
                obj = it.next();
                Comparable comparable = (Comparable) function1.invoke(obj);
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (!((Boolean) hasNext2).booleanValue()) {
                        break;
                    }
                    Object next = it.next();
                    Comparable comparable2 = (Comparable) function1.invoke(next);
                    if (comparable.compareTo(comparable2) < 0) {
                        obj = next;
                        comparable = comparable2;
                    }
                }
                i = 2;
                InlineMarker.finallyStart(2);
            }
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(i);
            return obj;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object minBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        int i;
        Object obj = null;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (!((Boolean) hasNext).booleanValue()) {
                i = 3;
                InlineMarker.finallyStart(3);
            } else {
                obj = it.next();
                Comparable comparable = (Comparable) function1.invoke(obj);
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (!((Boolean) hasNext2).booleanValue()) {
                        break;
                    }
                    Object next = it.next();
                    Comparable comparable2 = (Comparable) function1.invoke(next);
                    if (comparable.compareTo(comparable2) > 0) {
                        obj = next;
                        comparable = comparable2;
                    }
                }
                i = 2;
                InlineMarker.finallyStart(2);
            }
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(i);
            return obj;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object none$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Boolean.valueOf(true);
                }
            } while (!((Boolean) function1.invoke(it.next())).booleanValue());
            Boolean valueOf = Boolean.valueOf(false);
            InlineMarker.finallyStart(2);
            ChannelsKt.cancelConsumed(receiveChannel, th2);
            InlineMarker.finallyEnd(2);
            return valueOf;
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object reduce$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (((Boolean) hasNext).booleanValue()) {
                Object next = it.next();
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) hasNext2).booleanValue()) {
                        next = function2.invoke(next, it.next());
                    } else {
                        InlineMarker.finallyStart(2);
                        ChannelsKt.cancelConsumed(receiveChannel, th2);
                        InlineMarker.finallyEnd(2);
                        return next;
                    }
                }
            } else {
                throw new UnsupportedOperationException("Empty channel can't be reduced.");
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object reduceIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function3 function3, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (((Boolean) hasNext).booleanValue()) {
                Object next = it.next();
                int i = 1;
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) hasNext2).booleanValue()) {
                        Integer valueOf = Integer.valueOf(i);
                        i++;
                        next = function3.invoke(valueOf, next, it.next());
                    } else {
                        InlineMarker.finallyStart(2);
                        ChannelsKt.cancelConsumed(receiveChannel, th2);
                        InlineMarker.finallyEnd(2);
                        return next;
                    }
                }
            } else {
                throw new UnsupportedOperationException("Empty channel can't be reduced.");
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object sumBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    i += ((Number) function1.invoke(it.next())).intValue();
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(i);
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    private static final Object sumByDouble$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th;
        Throwable th2 = null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            double d = 0.0d;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    d += ((Number) function1.invoke(it.next())).doubleValue();
                } else {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, th2);
                    InlineMarker.finallyEnd(1);
                    return Double.valueOf(d);
                }
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, th);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }
}
