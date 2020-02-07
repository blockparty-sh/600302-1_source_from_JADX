package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0011\u0010\u0003\u001a\u00020\u0004H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\b0\u0007H'J\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u0005J%\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\f\"\u00020\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/VerifiedTokenDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVerifiedTokens", "Landroidx/lifecycle/LiveData;", "", "getVerifiedTokensNonStream", "replace", "values", "", "([Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: VerifiedTokenDao.kt */
public interface VerifiedTokenDao extends DaoBase<VerifiedToken> {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: VerifiedTokenDao.kt */
    public static final class DefaultImpls {
        /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x006d A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x006e A[PHI: r7 
          PHI: (r7v2 java.lang.Object) = (r7v6 java.lang.Object), (r7v1 java.lang.Object) binds: [B:18:0x006b, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        @androidx.room.Transaction
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.Object replace(com.bitcoin.mwallet.core.dao.VerifiedTokenDao r5, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                boolean r0 = r7 instanceof com.bitcoin.mwallet.core.dao.VerifiedTokenDao$replace$1
                if (r0 == 0) goto L_0x0014
                r0 = r7
                com.bitcoin.mwallet.core.dao.VerifiedTokenDao$replace$1 r0 = (com.bitcoin.mwallet.core.dao.VerifiedTokenDao$replace$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L_0x0019
            L_0x0014:
                com.bitcoin.mwallet.core.dao.VerifiedTokenDao$replace$1 r0 = new com.bitcoin.mwallet.core.dao.VerifiedTokenDao$replace$1
                r0.<init>(r5, r7)
            L_0x0019:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L_0x004a
                if (r2 == r4) goto L_0x003d
                if (r2 != r3) goto L_0x0035
                java.lang.Object r5 = r0.L$1
                com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r5 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[]) r5
                java.lang.Object r5 = r0.L$0
                com.bitcoin.mwallet.core.dao.VerifiedTokenDao r5 = (com.bitcoin.mwallet.core.dao.VerifiedTokenDao) r5
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x006e
            L_0x0035:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L_0x003d:
                java.lang.Object r5 = r0.L$1
                r6 = r5
                com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r6 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[]) r6
                java.lang.Object r5 = r0.L$0
                com.bitcoin.mwallet.core.dao.VerifiedTokenDao r5 = (com.bitcoin.mwallet.core.dao.VerifiedTokenDao) r5
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x005a
            L_0x004a:
                kotlin.ResultKt.throwOnFailure(r7)
                r0.L$0 = r5
                r0.L$1 = r6
                r0.label = r4
                java.lang.Object r7 = r5.deleteAll(r0)
                if (r7 != r1) goto L_0x005a
                return r1
            L_0x005a:
                int r7 = r6.length
                java.lang.Object[] r7 = java.util.Arrays.copyOf(r6, r7)
                com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r7 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[]) r7
                r0.L$0 = r5
                r0.L$1 = r6
                r0.label = r3
                java.lang.Object r7 = r5.upsert(r7, r0)
                if (r7 != r1) goto L_0x006e
                return r1
            L_0x006e:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.VerifiedTokenDao.DefaultImpls.replace(com.bitcoin.mwallet.core.dao.VerifiedTokenDao, com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[], kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Nullable
    @Query("DELETE FROM verified_token")
    Object deleteAll(@NotNull Continuation<? super Unit> continuation);

    @NotNull
    @Query("SELECT * From verified_token")
    LiveData<List<VerifiedToken>> getVerifiedTokens();

    @Nullable
    @Query("SELECT * From verified_token")
    Object getVerifiedTokensNonStream(@NotNull Continuation<? super List<VerifiedToken>> continuation);

    @Transaction
    @Nullable
    Object replace(@NotNull VerifiedToken[] verifiedTokenArr, @NotNull Continuation<? super Unit> continuation);
}
