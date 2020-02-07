package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.mwallet.core.dao.VerifiedTokenDao.DefaultImpls;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

public final class VerifiedTokenDao_Impl implements VerifiedTokenDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfVerifiedToken;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public VerifiedTokenDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfVerifiedToken = new EntityInsertionAdapter<VerifiedToken>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `verified_token`(`id`,`name`,`symbol`,`decimals`,`iconImageUrl`) VALUES (?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, VerifiedToken verifiedToken) {
                if (verifiedToken.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, verifiedToken.getId());
                }
                if (verifiedToken.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, verifiedToken.getName());
                }
                if (verifiedToken.getSymbol() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, verifiedToken.getSymbol());
                }
                supportSQLiteStatement.bindLong(4, (long) verifiedToken.getDecimals());
                if (verifiedToken.getIconImageUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, verifiedToken.getIconImageUrl());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM verified_token";
            }
        };
    }

    public Object upsert(final VerifiedToken[] verifiedTokenArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                VerifiedTokenDao_Impl.this.__db.beginTransaction();
                try {
                    VerifiedTokenDao_Impl.this.__insertionAdapterOfVerifiedToken.insert((T[]) verifiedTokenArr);
                    VerifiedTokenDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    VerifiedTokenDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object replace(final VerifiedToken[] verifiedTokenArr, Continuation<? super Unit> continuation) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1<Continuation<? super Unit>, Object>() {
            public Object invoke(Continuation<? super Unit> continuation) {
                return DefaultImpls.replace(VerifiedTokenDao_Impl.this, verifiedTokenArr, continuation);
            }
        }, continuation);
    }

    public Object deleteAll(Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = VerifiedTokenDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                VerifiedTokenDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    VerifiedTokenDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    VerifiedTokenDao_Impl.this.__db.endTransaction();
                    VerifiedTokenDao_Impl.this.__preparedStmtOfDeleteAll.release(acquire);
                }
            }
        }, continuation);
    }

    public LiveData<List<VerifiedToken>> getVerifiedTokens() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From verified_token", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"verified_token"}, false, new Callable<List<VerifiedToken>>() {
            public List<VerifiedToken> call() throws Exception {
                Cursor query = DBUtil.query(VerifiedTokenDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "symbol");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "decimals");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "iconImageUrl");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        VerifiedToken verifiedToken = new VerifiedToken(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getString(columnIndexOrThrow5));
                        arrayList.add(verifiedToken);
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

    public Object getVerifiedTokensNonStream(Continuation<? super List<VerifiedToken>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From verified_token", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<VerifiedToken>>() {
            public List<VerifiedToken> call() throws Exception {
                Cursor query = DBUtil.query(VerifiedTokenDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "symbol");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "decimals");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "iconImageUrl");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        VerifiedToken verifiedToken = new VerifiedToken(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getString(columnIndexOrThrow5));
                        arrayList.add(verifiedToken);
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }
}
