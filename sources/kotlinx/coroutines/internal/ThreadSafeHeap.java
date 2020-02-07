package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000*\u0012\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J$\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00028\u00002\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016H\b¢\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0010J\u000f\u0010\u0019\u001a\u0004\u0018\u00018\u0000H\u0001¢\u0006\u0002\u0010\u001aJ\r\u0010\u001b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\bH\u0002¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u001fJ\u0015\u0010 \u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u000eH\u0001¢\u0006\u0002\u0010\"J$\u0010#\u001a\u0004\u0018\u00018\u00002\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0%H\b¢\u0006\u0002\u0010&J\r\u0010'\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u001aJ\u0011\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u000eH\u0010J\u0011\u0010*\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u000eH\u0010J\u0018\u0010+\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000eH\u0002R\u001a\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0012\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, mo37405d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", "T", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "()V", "a", "", "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "isEmpty", "", "()Z", "size", "", "addImpl", "", "node", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "addLast", "addLastIf", "cond", "Lkotlin/Function0;", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function0;)Z", "clear", "firstImpl", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "peek", "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "removeAtImpl", "index", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstIf", "predicate", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "siftDownFrom", "i", "siftUpFrom", "swap", "j", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@InternalCoroutinesApi
/* compiled from: ThreadSafeHeap.kt */
public final class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {

    /* renamed from: a */
    private T[] f777a;
    @JvmField
    public volatile int size;

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final synchronized void clear() {
        Arrays.fill(this.f777a, 0, this.size, null);
        this.size = 0;
    }

    @Nullable
    public final synchronized T peek() {
        return firstImpl();
    }

    @Nullable
    public final synchronized T removeFirstOrNull() {
        return this.size > 0 ? removeAtImpl(0) : null;
    }

    public final synchronized void addLast(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        addImpl(t);
    }

    public final synchronized boolean remove(@NotNull T t) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(t, "node");
        z = true;
        boolean z2 = false;
        if (t.getHeap() == null) {
            z = false;
        } else {
            int index = t.getIndex();
            if (index >= 0) {
                z2 = true;
            }
            if (z2) {
                removeAtImpl(index);
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        return z;
    }

    @Nullable
    @PublishedApi
    public final T firstImpl() {
        T[] tArr = this.f777a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    @NotNull
    @PublishedApi
    public final T removeAtImpl(int i) {
        boolean z = false;
        String str = "Check failed.";
        if (this.size > 0) {
            T[] tArr = this.f777a;
            if (tArr == null) {
                Intrinsics.throwNpe();
            }
            this.size--;
            if (i < this.size) {
                swap(i, this.size);
                int i2 = (i - 1) / 2;
                if (i > 0) {
                    T t = tArr[i];
                    if (t == null) {
                        Intrinsics.throwNpe();
                    }
                    Comparable comparable = (Comparable) t;
                    T t2 = tArr[i2];
                    if (t2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (comparable.compareTo(t2) < 0) {
                        swap(i, i2);
                        siftUpFrom(i2);
                    }
                }
                siftDownFrom(i);
            }
            T t3 = tArr[this.size];
            if (t3 == null) {
                Intrinsics.throwNpe();
            }
            if (t3.getHeap() == this) {
                z = true;
            }
            if (z) {
                t3.setHeap(null);
                t3.setIndex(-1);
                tArr[this.size] = (ThreadSafeHeapNode) null;
                return t3;
            }
            throw new IllegalStateException(str.toString());
        }
        throw new IllegalStateException(str.toString());
    }

    @PublishedApi
    public final void addImpl(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        if (t.getHeap() == null) {
            t.setHeap(this);
            ThreadSafeHeapNode[] realloc = realloc();
            int i = this.size;
            this.size = i + 1;
            realloc[i] = t;
            t.setIndex(i);
            siftUpFrom(i);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final void siftUpFrom(int i) {
        while (i > 0) {
            T[] tArr = this.f777a;
            if (tArr == null) {
                Intrinsics.throwNpe();
            }
            int i2 = (i - 1) / 2;
            T t = tArr[i2];
            if (t == null) {
                Intrinsics.throwNpe();
            }
            Comparable comparable = (Comparable) t;
            T t2 = tArr[i];
            if (t2 == null) {
                Intrinsics.throwNpe();
            }
            if (comparable.compareTo(t2) > 0) {
                swap(i, i2);
                i = i2;
            } else {
                return;
            }
        }
    }

    private final void siftDownFrom(int i) {
        while (true) {
            int i2 = (i * 2) + 1;
            if (i2 < this.size) {
                T[] tArr = this.f777a;
                if (tArr == null) {
                    Intrinsics.throwNpe();
                }
                int i3 = i2 + 1;
                if (i3 < this.size) {
                    T t = tArr[i3];
                    if (t == null) {
                        Intrinsics.throwNpe();
                    }
                    Comparable comparable = (Comparable) t;
                    T t2 = tArr[i2];
                    if (t2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (comparable.compareTo(t2) < 0) {
                        i2 = i3;
                    }
                }
                T t3 = tArr[i];
                if (t3 == null) {
                    Intrinsics.throwNpe();
                }
                Comparable comparable2 = (Comparable) t3;
                T t4 = tArr[i2];
                if (t4 == null) {
                    Intrinsics.throwNpe();
                }
                if (comparable2.compareTo(t4) > 0) {
                    swap(i, i2);
                    i = i2;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private final T[] realloc() {
        T[] tArr = this.f777a;
        if (tArr == null) {
            T[] tArr2 = new ThreadSafeHeapNode[4];
            this.f777a = tArr2;
            return tArr2;
        } else if (this.size < tArr.length) {
            return tArr;
        } else {
            T[] copyOf = Arrays.copyOf(tArr, this.size * 2);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            T[] tArr3 = (ThreadSafeHeapNode[]) copyOf;
            this.f777a = tArr3;
            return tArr3;
        }
    }

    private final void swap(int i, int i2) {
        T[] tArr = this.f777a;
        if (tArr == null) {
            Intrinsics.throwNpe();
        }
        T t = tArr[i2];
        if (t == null) {
            Intrinsics.throwNpe();
        }
        T t2 = tArr[i];
        if (t2 == null) {
            Intrinsics.throwNpe();
        }
        tArr[i] = t;
        tArr[i2] = t2;
        t.setIndex(i);
        t2.setIndex(i2);
    }

    @Nullable
    public final T removeFirstIf(@NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        synchronized (this) {
            try {
                ThreadSafeHeapNode firstImpl = firstImpl();
                T t = null;
                if (firstImpl != null) {
                    if (((Boolean) function1.invoke(firstImpl)).booleanValue()) {
                        t = removeAtImpl(0);
                    }
                    InlineMarker.finallyEnd(1);
                    return t;
                }
                InlineMarker.finallyStart(2);
                InlineMarker.finallyEnd(2);
                return null;
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    public final boolean addLastIf(@NotNull T t, @NotNull Function0<Boolean> function0) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(t, "node");
        Intrinsics.checkParameterIsNotNull(function0, "cond");
        synchronized (this) {
            try {
                if (((Boolean) function0.invoke()).booleanValue()) {
                    addImpl(t);
                    z = true;
                } else {
                    z = false;
                }
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
        InlineMarker.finallyEnd(1);
        return z;
    }
}
