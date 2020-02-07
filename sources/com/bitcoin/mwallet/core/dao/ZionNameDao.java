package com.bitcoin.mwallet.core.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.bitcoin.mwallet.core.entity.ZionNameEntity;
import com.bitcoin.mwallet.zion.ZionId;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u00020\t2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0014\"\u00020\u0002H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/ZionNameDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/entity/ZionNameEntity;", "()V", "maxNames", "", "getMaxNames", "()I", "deleteByIndex", "", "index", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByZionId", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextAvailableSuffix", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertZionId", "zionName", "", "([Lcom/bitcoin/mwallet/core/entity/ZionNameEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectAll", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionNameDao.kt */
public abstract class ZionNameDao implements DaoBase<ZionNameEntity> {
    private final int maxNames = 10;

    @Nullable
    @Query("DELETE FROM zion_name WHERE `index` = :index")
    public abstract Object deleteByIndex(int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("DELETE FROM zion_name WHERE `zion_id` = :zionId")
    public abstract Object deleteByZionId(@NotNull ZionId zionId, @NotNull Continuation<? super Unit> continuation);

    @Transaction
    @Nullable
    public Object getNextAvailableSuffix(@NotNull Continuation<? super Integer> continuation) {
        return getNextAvailableSuffix$suspendImpl(this, continuation);
    }

    @Nullable
    @Insert(onConflict = 1)
    public abstract Object insertZionId(@NotNull ZionNameEntity[] zionNameEntityArr, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("SELECT * FROM zion_name")
    public abstract Object selectAll(@NotNull Continuation<? super List<ZionNameEntity>> continuation);

    public final int getMaxNames() {
        return this.maxNames;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0076 A[LOOP:0: B:18:0x0070->B:20:0x0076, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object getNextAvailableSuffix$suspendImpl(com.bitcoin.mwallet.core.dao.ZionNameDao r11, kotlin.coroutines.Continuation r12) {
        /*
            boolean r0 = r12 instanceof com.bitcoin.mwallet.core.dao.ZionNameDao$getNextAvailableSuffix$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.bitcoin.mwallet.core.dao.ZionNameDao$getNextAvailableSuffix$1 r0 = (com.bitcoin.mwallet.core.dao.ZionNameDao$getNextAvailableSuffix$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.dao.ZionNameDao$getNextAvailableSuffix$1 r0 = new com.bitcoin.mwallet.core.dao.ZionNameDao$getNextAvailableSuffix$1
            r0.<init>(r11, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004f
            if (r2 == r4) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r11 = r0.L$3
            com.bitcoin.mwallet.core.entity.ZionNameEntity r11 = (com.bitcoin.mwallet.core.entity.ZionNameEntity) r11
            java.lang.Object r11 = r0.L$2
            java.lang.Integer r11 = (java.lang.Integer) r11
            java.lang.Object r1 = r0.L$1
            java.util.Set r1 = (java.util.Set) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.dao.ZionNameDao r0 = (com.bitcoin.mwallet.core.dao.ZionNameDao) r0
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r11
            goto L_0x00d4
        L_0x003f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0047:
            java.lang.Object r11 = r0.L$0
            com.bitcoin.mwallet.core.dao.ZionNameDao r11 = (com.bitcoin.mwallet.core.dao.ZionNameDao) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x005d
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r11
            r0.label = r4
            java.lang.Object r12 = r11.selectAll(r0)
            if (r12 != r1) goto L_0x005d
            return r1
        L_0x005d:
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r2 = new java.util.ArrayList
            r5 = 10
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r12, r5)
            r2.<init>(r5)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r12 = r12.iterator()
        L_0x0070:
            boolean r5 = r12.hasNext()
            if (r5 == 0) goto L_0x0088
            java.lang.Object r5 = r12.next()
            com.bitcoin.mwallet.core.entity.ZionNameEntity r5 = (com.bitcoin.mwallet.core.entity.ZionNameEntity) r5
            int r5 = r5.getIndex()
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            r2.add(r5)
            goto L_0x0070
        L_0x0088:
            java.util.List r2 = (java.util.List) r2
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Set r12 = kotlin.collections.CollectionsKt.toSet(r2)
            r2 = 0
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r5 = r11.maxNames
            r6 = 0
            if (r5 < 0) goto L_0x00ad
            r7 = 0
        L_0x0099:
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            boolean r8 = r12.contains(r8)
            if (r8 != 0) goto L_0x00a8
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            goto L_0x00ad
        L_0x00a8:
            if (r7 == r5) goto L_0x00ad
            int r7 = r7 + 1
            goto L_0x0099
        L_0x00ad:
            if (r2 == 0) goto L_0x00d4
            com.bitcoin.mwallet.core.entity.ZionNameEntity r5 = new com.bitcoin.mwallet.core.entity.ZionNameEntity
            int r7 = r2.intValue()
            com.bitcoin.mwallet.zion.ZionId r8 = new com.bitcoin.mwallet.zion.ZionId
            r9 = 0
            r8.<init>(r9)
            r5.<init>(r7, r8)
            com.bitcoin.mwallet.core.entity.ZionNameEntity[] r4 = new com.bitcoin.mwallet.core.entity.ZionNameEntity[r4]
            r4[r6] = r5
            r0.L$0 = r11
            r0.L$1 = r12
            r0.L$2 = r2
            r0.L$3 = r5
            r0.label = r3
            java.lang.Object r11 = r11.upsert(r4, r0)
            if (r11 != r1) goto L_0x00d4
            return r1
        L_0x00d4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.ZionNameDao.getNextAvailableSuffix$suspendImpl(com.bitcoin.mwallet.core.dao.ZionNameDao, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
