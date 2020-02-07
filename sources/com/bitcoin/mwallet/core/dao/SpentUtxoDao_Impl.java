package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.p009tx.SpentUtxo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class SpentUtxoDao_Impl implements SpentUtxoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfSpentUtxo;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDelete;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public SpentUtxoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSpentUtxo = new EntityInsertionAdapter<SpentUtxo>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `spent_utxo`(`tx_id`,`output_index`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, SpentUtxo spentUtxo) {
                String writeTxId = SpentUtxoDao_Impl.this.__roomConverters.writeTxId(spentUtxo.getTxId());
                if (writeTxId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeTxId);
                }
                supportSQLiteStatement.bindLong(2, (long) spentUtxo.getOutputIndex());
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM spent_utxo WHERE tx_id = ? AND output_index = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM spent_utxo";
            }
        };
    }

    public Object upsert(final SpentUtxo[] spentUtxoArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SpentUtxoDao_Impl.this.__db.beginTransaction();
                try {
                    SpentUtxoDao_Impl.this.__insertionAdapterOfSpentUtxo.insert((T[]) spentUtxoArr);
                    SpentUtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SpentUtxoDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object delete(final TxId txId, final int i, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = SpentUtxoDao_Impl.this.__preparedStmtOfDelete.acquire();
                String writeTxId = SpentUtxoDao_Impl.this.__roomConverters.writeTxId(txId);
                if (writeTxId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeTxId);
                }
                acquire.bindLong(2, (long) i);
                SpentUtxoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    SpentUtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SpentUtxoDao_Impl.this.__db.endTransaction();
                    SpentUtxoDao_Impl.this.__preparedStmtOfDelete.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteAll(Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = SpentUtxoDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                SpentUtxoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    SpentUtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SpentUtxoDao_Impl.this.__db.endTransaction();
                    SpentUtxoDao_Impl.this.__preparedStmtOfDeleteAll.release(acquire);
                }
            }
        }, continuation);
    }

    public Object getSpentUtxos(Continuation<? super List<SpentUtxo>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM spent_utxo", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<SpentUtxo>>() {
            public List<SpentUtxo> call() throws Exception {
                Cursor query = DBUtil.query(SpentUtxoDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "output_index");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new SpentUtxo(SpentUtxoDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow)), query.getInt(columnIndexOrThrow2)));
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
