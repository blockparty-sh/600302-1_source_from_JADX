package androidx.room;

import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, mo37405d2 = {"Landroidx/room/CoroutinesRoom;", "", "()V", "Companion", "room-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
/* compiled from: CoroutinesRoom.kt */
public final class CoroutinesRoom {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00040\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo37405d2 = {"Landroidx/room/CoroutinesRoom$Companion;", "", "()V", "execute", "R", "db", "Landroidx/room/RoomDatabase;", "inTransaction", "", "callable", "Ljava/util/concurrent/Callable;", "(Landroidx/room/RoomDatabase;ZLjava/util/concurrent/Callable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CoroutinesRoom.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
            if (r0 != null) goto L_0x0036;
         */
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final <R> java.lang.Object execute(@org.jetbrains.annotations.NotNull androidx.room.RoomDatabase r3, boolean r4, @org.jetbrains.annotations.NotNull java.util.concurrent.Callable<R> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r6) {
            /*
                r2 = this;
                boolean r0 = r3.isOpen()
                if (r0 == 0) goto L_0x0011
                boolean r0 = r3.inTransaction()
                if (r0 == 0) goto L_0x0011
                java.lang.Object r3 = r5.call()
                return r3
            L_0x0011:
                kotlin.coroutines.CoroutineContext r0 = r6.getContext()
                androidx.room.TransactionElement$Key r1 = androidx.room.TransactionElement.Key
                kotlin.coroutines.CoroutineContext$Key r1 = (kotlin.coroutines.CoroutineContext.C2916Key) r1
                kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r1)
                androidx.room.TransactionElement r0 = (androidx.room.TransactionElement) r0
                if (r0 == 0) goto L_0x0028
                kotlin.coroutines.ContinuationInterceptor r0 = r0.getTransactionDispatcher$room_ktx_release()
                if (r0 == 0) goto L_0x0028
                goto L_0x0036
            L_0x0028:
                if (r4 == 0) goto L_0x002f
                kotlinx.coroutines.CoroutineDispatcher r3 = androidx.room.CoroutinesRoomKt.getTransactionDispatcher(r3)
                goto L_0x0033
            L_0x002f:
                kotlinx.coroutines.CoroutineDispatcher r3 = androidx.room.CoroutinesRoomKt.getQueryDispatcher(r3)
            L_0x0033:
                r0 = r3
                kotlin.coroutines.ContinuationInterceptor r0 = (kotlin.coroutines.ContinuationInterceptor) r0
            L_0x0036:
                kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
                androidx.room.CoroutinesRoom$Companion$execute$2 r3 = new androidx.room.CoroutinesRoom$Companion$execute$2
                r4 = 0
                r3.<init>(r5, r4)
                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
                java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r0, r3, r6)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.CoroutinesRoom.Companion.execute(androidx.room.RoomDatabase, boolean, java.util.concurrent.Callable, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @JvmStatic
    @Nullable
    public static final <R> Object execute(@NotNull RoomDatabase roomDatabase, boolean z, @NotNull Callable<R> callable, @NotNull Continuation<? super R> continuation) {
        return Companion.execute(roomDatabase, z, callable, continuation);
    }

    private CoroutinesRoom() {
    }
}
