package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.entity.ZionNameEntity;
import com.bitcoin.mwallet.zion.ZionId;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

public final class ZionNameDao_Impl extends ZionNameDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfZionNameEntity;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteByIndex;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteByZionId;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public ZionNameDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfZionNameEntity = new EntityInsertionAdapter<ZionNameEntity>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `zion_name`(`index`,`zion_id`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ZionNameEntity zionNameEntity) {
                supportSQLiteStatement.bindLong(1, (long) zionNameEntity.getIndex());
                String writeZionId = ZionNameDao_Impl.this.__roomConverters.writeZionId(zionNameEntity.getZionID());
                if (writeZionId == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, writeZionId);
                }
            }
        };
        this.__preparedStmtOfDeleteByZionId = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM zion_name WHERE `zion_id` = ?";
            }
        };
        this.__preparedStmtOfDeleteByIndex = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM zion_name WHERE `index` = ?";
            }
        };
    }

    public Object upsert(final ZionNameEntity[] zionNameEntityArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                ZionNameDao_Impl.this.__db.beginTransaction();
                try {
                    ZionNameDao_Impl.this.__insertionAdapterOfZionNameEntity.insert((T[]) zionNameEntityArr);
                    ZionNameDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    ZionNameDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object insertZionId(final ZionNameEntity[] zionNameEntityArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                ZionNameDao_Impl.this.__db.beginTransaction();
                try {
                    ZionNameDao_Impl.this.__insertionAdapterOfZionNameEntity.insert((T[]) zionNameEntityArr);
                    ZionNameDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    ZionNameDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object getNextAvailableSuffix(Continuation<? super Integer> continuation) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1<Continuation<? super Integer>, Object>() {
            public Object invoke(Continuation<? super Integer> continuation) {
                return ZionNameDao_Impl.super.getNextAvailableSuffix(continuation);
            }
        }, continuation);
    }

    public Object deleteByZionId(final ZionId zionId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = ZionNameDao_Impl.this.__preparedStmtOfDeleteByZionId.acquire();
                String writeZionId = ZionNameDao_Impl.this.__roomConverters.writeZionId(zionId);
                if (writeZionId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeZionId);
                }
                ZionNameDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    ZionNameDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    ZionNameDao_Impl.this.__db.endTransaction();
                    ZionNameDao_Impl.this.__preparedStmtOfDeleteByZionId.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteByIndex(final int i, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = ZionNameDao_Impl.this.__preparedStmtOfDeleteByIndex.acquire();
                acquire.bindLong(1, (long) i);
                ZionNameDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    ZionNameDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    ZionNameDao_Impl.this.__db.endTransaction();
                    ZionNameDao_Impl.this.__preparedStmtOfDeleteByIndex.release(acquire);
                }
            }
        }, continuation);
    }

    public Object selectAll(Continuation<? super List<ZionNameEntity>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM zion_name", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<ZionNameEntity>>() {
            public List<ZionNameEntity> call() throws Exception {
                Cursor query = DBUtil.query(ZionNameDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, Param.INDEX);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "zion_id");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new ZionNameEntity(query.getInt(columnIndexOrThrow), ZionNameDao_Impl.this.__roomConverters.readZionId(query.getString(columnIndexOrThrow2))));
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
