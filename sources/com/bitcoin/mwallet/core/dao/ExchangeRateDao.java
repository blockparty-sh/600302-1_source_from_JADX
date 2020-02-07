package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J#\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0006\u001a\u00020\u0005H'J \u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\t0\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H'\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/ExchangeRateDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/exchangerate/ExchangeRate;", "get", "toTicker", "", "fromTicker", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getToTickerByFromTicker", "", "select", "Landroidx/lifecycle/LiveData;", "selectByToTicker", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ExchangeRateDao.kt */
public interface ExchangeRateDao extends DaoBase<ExchangeRate> {
    @Nullable
    @Query("SELECT * FROM exchange_rate WHERE to_ticker = :toTicker AND from_ticker = :fromTicker")
    Object get(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ExchangeRate> continuation);

    @NotNull
    @Query("SELECT to_ticker FROM exchange_rate WHERE from_ticker = :fromTicker")
    List<String> getToTickerByFromTicker(@NotNull String str);

    @NotNull
    @Query("SELECT * FROM exchange_rate WHERE to_ticker = :toTicker AND from_ticker = :fromTicker")
    LiveData<ExchangeRate> select(@NotNull String str, @NotNull String str2);

    @NotNull
    @Query("SELECT * FROM exchange_rate WHERE to_ticker = :toTicker")
    LiveData<List<ExchangeRate>> selectByToTicker(@NotNull String str);
}
