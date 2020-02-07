package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class ExchangeRateDao_Impl implements ExchangeRateDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfExchangeRate;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public ExchangeRateDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfExchangeRate = new EntityInsertionAdapter<ExchangeRate>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `exchange_rate`(`from_ticker`,`to_ticker`,`rate`,`timestamp`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExchangeRate exchangeRate) {
                if (exchangeRate.getFromTicker() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, exchangeRate.getFromTicker());
                }
                if (exchangeRate.getToTicker() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, exchangeRate.getToTicker());
                }
                String writeBigDecimal = ExchangeRateDao_Impl.this.__roomConverters.writeBigDecimal(exchangeRate.getRate());
                if (writeBigDecimal == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, writeBigDecimal);
                }
                supportSQLiteStatement.bindLong(4, ExchangeRateDao_Impl.this.__roomConverters.writeDate(exchangeRate.getTimestamp()));
            }
        };
    }

    public Object upsert(final ExchangeRate[] exchangeRateArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                ExchangeRateDao_Impl.this.__db.beginTransaction();
                try {
                    ExchangeRateDao_Impl.this.__insertionAdapterOfExchangeRate.insert((T[]) exchangeRateArr);
                    ExchangeRateDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    ExchangeRateDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public LiveData<List<ExchangeRate>> selectByToTicker(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM exchange_rate WHERE to_ticker = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"exchange_rate"}, false, new Callable<List<ExchangeRate>>() {
            public List<ExchangeRate> call() throws Exception {
                Cursor query = DBUtil.query(ExchangeRateDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "from_ticker");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "to_ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "rate");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new ExchangeRate(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), ExchangeRateDao_Impl.this.__roomConverters.readBigDecimal(query.getString(columnIndexOrThrow3)), ExchangeRateDao_Impl.this.__roomConverters.readDate(query.getLong(columnIndexOrThrow4))));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public LiveData<ExchangeRate> select(String str, String str2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM exchange_rate WHERE to_ticker = ? AND from_ticker = ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"exchange_rate"}, false, new Callable<ExchangeRate>() {
            public ExchangeRate call() throws Exception {
                ExchangeRate exchangeRate;
                Cursor query = DBUtil.query(ExchangeRateDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "from_ticker");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "to_ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "rate");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    if (query.moveToFirst()) {
                        exchangeRate = new ExchangeRate(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), ExchangeRateDao_Impl.this.__roomConverters.readBigDecimal(query.getString(columnIndexOrThrow3)), ExchangeRateDao_Impl.this.__roomConverters.readDate(query.getLong(columnIndexOrThrow4)));
                    } else {
                        exchangeRate = null;
                    }
                    return exchangeRate;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Object get(String str, String str2, Continuation<? super ExchangeRate> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM exchange_rate WHERE to_ticker = ? AND from_ticker = ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<ExchangeRate>() {
            public ExchangeRate call() throws Exception {
                ExchangeRate exchangeRate;
                Cursor query = DBUtil.query(ExchangeRateDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "from_ticker");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "to_ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "rate");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    if (query.moveToFirst()) {
                        exchangeRate = new ExchangeRate(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), ExchangeRateDao_Impl.this.__roomConverters.readBigDecimal(query.getString(columnIndexOrThrow3)), ExchangeRateDao_Impl.this.__roomConverters.readDate(query.getLong(columnIndexOrThrow4)));
                    } else {
                        exchangeRate = null;
                    }
                    return exchangeRate;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public List<String> getToTickerByFromTicker(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT to_ticker FROM exchange_rate WHERE from_ticker = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
