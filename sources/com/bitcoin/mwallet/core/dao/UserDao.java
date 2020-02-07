package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.bitcoin.mwallet.core.models.user.User;
import com.bitcoin.mwallet.core.models.user.UserId;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H'J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0002H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/UserDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/user/User;", "getAllUsers", "Landroidx/lifecycle/LiveData;", "getUser", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeUser", "", "id", "Lcom/bitcoin/mwallet/core/models/user/UserId;", "(Lcom/bitcoin/mwallet/core/models/user/UserId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UserDao.kt */
public interface UserDao extends DaoBase<User> {
    @NotNull
    @Query("SELECT * FROM user LIMIT 1")
    LiveData<User> getAllUsers();

    @Nullable
    @Query("SELECT * FROM user LIMIT 1")
    Object getUser(@NotNull Continuation<? super User> continuation);

    @Nullable
    @Query("Delete FROM user WHERE id = :id")
    Object removeUser(@NotNull UserId userId, @NotNull Continuation<? super Unit> continuation);
}
