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
import com.bitcoin.mwallet.core.models.address.AddressPurpose;
import com.bitcoin.mwallet.core.models.address.WalletAddressInfo;
import com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

public final class WalletAddressInfoDao_Impl extends WalletAddressInfoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfWalletAddressInfo;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDelete;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfUpdatePathY;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public WalletAddressInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWalletAddressInfo = new EntityInsertionAdapter<WalletAddressInfo>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `wallet_address_info`(`wallet_id`,`path_x`,`path_y_next`,`path_y_max`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WalletAddressInfo walletAddressInfo) {
                String writeWalletId = WalletAddressInfoDao_Impl.this.__roomConverters.writeWalletId(walletAddressInfo.getWalletId());
                if (writeWalletId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeWalletId);
                }
                supportSQLiteStatement.bindLong(2, (long) walletAddressInfo.getPathX());
                PathY pathY = walletAddressInfo.getPathY();
                if (pathY != null) {
                    supportSQLiteStatement.bindLong(3, (long) pathY.getNext().intValue());
                    supportSQLiteStatement.bindLong(4, (long) pathY.getMax());
                    return;
                }
                supportSQLiteStatement.bindNull(3);
                supportSQLiteStatement.bindNull(4);
            }
        };
        this.__preparedStmtOfUpdatePathY = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE wallet_address_info SET path_y_next = ?, path_y_max = ? WHERE wallet_id = ? AND path_x = ?";
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM wallet_address_info WHERE wallet_id = ?";
            }
        };
    }

    public Object upsert(final WalletAddressInfo[] walletAddressInfoArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                WalletAddressInfoDao_Impl.this.__db.beginTransaction();
                try {
                    WalletAddressInfoDao_Impl.this.__insertionAdapterOfWalletAddressInfo.insert((T[]) walletAddressInfoArr);
                    WalletAddressInfoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletAddressInfoDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object getOrCreatePathY(final WalletId walletId, final AddressPurpose addressPurpose, Continuation<? super PathY> continuation) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1<Continuation<? super PathY>, Object>() {
            public Object invoke(Continuation<? super PathY> continuation) {
                return WalletAddressInfoDao_Impl.super.getOrCreatePathY(walletId, addressPurpose, continuation);
            }
        }, continuation);
    }

    public Object incrementAndGetPathY(final WalletId walletId, final AddressPurpose addressPurpose, Continuation<? super PathY> continuation) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1<Continuation<? super PathY>, Object>() {
            public Object invoke(Continuation<? super PathY> continuation) {
                return WalletAddressInfoDao_Impl.super.incrementAndGetPathY(walletId, addressPurpose, continuation);
            }
        }, continuation);
    }

    public Object updatePathY(WalletId walletId, int i, int i2, int i3, Continuation<? super Unit> continuation) {
        RoomDatabase roomDatabase = this.__db;
        final int i4 = i2;
        final int i5 = i3;
        final WalletId walletId2 = walletId;
        final int i6 = i;
        C12187 r1 = new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = WalletAddressInfoDao_Impl.this.__preparedStmtOfUpdatePathY.acquire();
                acquire.bindLong(1, (long) i4);
                acquire.bindLong(2, (long) i5);
                String writeWalletId = WalletAddressInfoDao_Impl.this.__roomConverters.writeWalletId(walletId2);
                if (writeWalletId == null) {
                    acquire.bindNull(3);
                } else {
                    acquire.bindString(3, writeWalletId);
                }
                acquire.bindLong(4, (long) i6);
                WalletAddressInfoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    WalletAddressInfoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletAddressInfoDao_Impl.this.__db.endTransaction();
                    WalletAddressInfoDao_Impl.this.__preparedStmtOfUpdatePathY.release(acquire);
                }
            }
        };
        return CoroutinesRoom.execute(roomDatabase, true, r1, continuation);
    }

    public Object delete(final WalletId walletId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = WalletAddressInfoDao_Impl.this.__preparedStmtOfDelete.acquire();
                String writeWalletId = WalletAddressInfoDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeWalletId);
                }
                WalletAddressInfoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    WalletAddressInfoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletAddressInfoDao_Impl.this.__db.endTransaction();
                    WalletAddressInfoDao_Impl.this.__preparedStmtOfDelete.release(acquire);
                }
            }
        }, continuation);
    }

    public Object getPathY(WalletId walletId, int i, Continuation<? super PathY> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT path_y_next as next, path_y_max as max FROM wallet_address_info WHERE wallet_id = ? AND path_x = ?", 2);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        acquire.bindLong(2, (long) i);
        return CoroutinesRoom.execute(this.__db, false, new Callable<PathY>() {
            public PathY call() throws Exception {
                PathY pathY;
                Cursor query = DBUtil.query(WalletAddressInfoDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "next");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "max");
                    if (query.moveToFirst()) {
                        Integer valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow));
                        pathY = new PathY(valueOf.intValue(), query.getInt(columnIndexOrThrow2));
                    } else {
                        pathY = null;
                    }
                    return pathY;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }
}
