package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class SlpDao_Impl implements SlpDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfSlp;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDelete;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public SlpDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSlp = new EntityInsertionAdapter<Slp>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `slp`(`token_id`,`ticker`,`name`,`wallet_id`,`decimals`) VALUES (?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Slp slp) {
                String writeSlpId = SlpDao_Impl.this.__roomConverters.writeSlpId(slp.getTokenId());
                if (writeSlpId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeSlpId);
                }
                if (slp.getTicker() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, slp.getTicker());
                }
                if (slp.getName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, slp.getName());
                }
                String writeWalletId = SlpDao_Impl.this.__roomConverters.writeWalletId(slp.getWalletId());
                if (writeWalletId == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, writeWalletId);
                }
                supportSQLiteStatement.bindLong(5, (long) slp.getDecimals());
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM slp WHERE wallet_id = ? AND token_id = ?";
            }
        };
    }

    public Object upsert(final Slp[] slpArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SlpDao_Impl.this.__db.beginTransaction();
                try {
                    SlpDao_Impl.this.__insertionAdapterOfSlp.insert((T[]) slpArr);
                    SlpDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SlpDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object delete(final WalletId walletId, final SlpId slpId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = SlpDao_Impl.this.__preparedStmtOfDelete.acquire();
                String writeWalletId = SlpDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeWalletId);
                }
                String writeSlpId = SlpDao_Impl.this.__roomConverters.writeSlpId(slpId);
                if (writeSlpId == null) {
                    acquire.bindNull(2);
                } else {
                    acquire.bindString(2, writeSlpId);
                }
                SlpDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    SlpDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SlpDao_Impl.this.__db.endTransaction();
                    SlpDao_Impl.this.__preparedStmtOfDelete.release(acquire);
                }
            }
        }, continuation);
    }

    public LiveData<List<Slp>> selectAllFromWalletId(WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM slp WHERE wallet_id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"slp"}, false, new Callable<List<Slp>>() {
            public List<Slp> call() throws Exception {
                Cursor query = DBUtil.query(SlpDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "decimals");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        Slp slp = new Slp(SlpDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), SlpDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5));
                        arrayList.add(slp);
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

    public Object selectAllFromWalletIdNonLiveData(WalletId walletId, Continuation<? super List<Slp>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM slp WHERE wallet_id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<Slp>>() {
            public List<Slp> call() throws Exception {
                Cursor query = DBUtil.query(SlpDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "decimals");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        Slp slp = new Slp(SlpDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), SlpDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5));
                        arrayList.add(slp);
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectFromWalletIdAndTokenId(WalletId walletId, SlpId slpId, Continuation<? super Slp> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM slp WHERE wallet_id = ? AND token_id = ?", 2);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        String writeSlpId = this.__roomConverters.writeSlpId(slpId);
        if (writeSlpId == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, writeSlpId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<Slp>() {
            public Slp call() throws Exception {
                Slp slp;
                Cursor query = DBUtil.query(SlpDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "decimals");
                    if (query.moveToFirst()) {
                        slp = new Slp(SlpDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), SlpDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5));
                    } else {
                        slp = null;
                    }
                    return slp;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectAllDistinctToken(Continuation<? super List<Slp>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From slp GROUP BY token_id", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<Slp>>() {
            public List<Slp> call() throws Exception {
                Cursor query = DBUtil.query(SlpDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "decimals");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        Slp slp = new Slp(SlpDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), SlpDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5));
                        arrayList.add(slp);
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getTokenInfo(SlpId slpId, Continuation<? super Slp> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from slp where token_id = ? LIMIT 1", 1);
        String writeSlpId = this.__roomConverters.writeSlpId(slpId);
        if (writeSlpId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeSlpId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<Slp>() {
            public Slp call() throws Exception {
                Slp slp;
                Cursor query = DBUtil.query(SlpDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "ticker");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "decimals");
                    if (query.moveToFirst()) {
                        slp = new Slp(SlpDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), SlpDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5));
                    } else {
                        slp = null;
                    }
                    return slp;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectAllWalletIdForToken(SlpId slpId, Continuation<? super List<WalletId>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT wallet_id FROM slp WHERE token_id = ?", 1);
        String writeSlpId = this.__roomConverters.writeSlpId(slpId);
        if (writeSlpId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeSlpId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<WalletId>>() {
            public List<WalletId> call() throws Exception {
                Cursor query = DBUtil.query(SlpDao_Impl.this.__db, acquire, false);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(SlpDao_Impl.this.__roomConverters.readWalletId(query.getString(0)));
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
