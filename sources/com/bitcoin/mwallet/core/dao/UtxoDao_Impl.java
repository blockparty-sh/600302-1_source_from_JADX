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
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.dao.UtxoDao.DefaultImpls;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

public final class UtxoDao_Impl implements UtxoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfUtxo;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDelete;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public UtxoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfUtxo = new EntityInsertionAdapter<Utxo>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `utxo`(`wallet_id`,`tx_id`,`output_index`,`address`,`path`,`script`,`satoshis`,`coin`) VALUES (?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Utxo utxo) {
                String writeWalletId = UtxoDao_Impl.this.__roomConverters.writeWalletId(utxo.getWalletId());
                if (writeWalletId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeWalletId);
                }
                String writeTxId = UtxoDao_Impl.this.__roomConverters.writeTxId(utxo.getTxId());
                if (writeTxId == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, writeTxId);
                }
                supportSQLiteStatement.bindLong(3, (long) utxo.getOutputIndex());
                String writeAddress = UtxoDao_Impl.this.__roomConverters.writeAddress(utxo.getAddress());
                if (writeAddress == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, writeAddress);
                }
                String writeDerivationPath = UtxoDao_Impl.this.__roomConverters.writeDerivationPath(utxo.getAddressPath());
                if (writeDerivationPath == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, writeDerivationPath);
                }
                if (utxo.getScript() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindBlob(6, utxo.getScript());
                }
                supportSQLiteStatement.bindLong(7, UtxoDao_Impl.this.__roomConverters.writeSatoshis(utxo.getSatoshis()));
                String writeCoin = UtxoDao_Impl.this.__roomConverters.writeCoin(utxo.getCoin());
                if (writeCoin == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, writeCoin);
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM utxo WHERE tx_id = ? AND output_index = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM utxo where wallet_id = ?";
            }
        };
    }

    public Object upsert(final Utxo[] utxoArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                UtxoDao_Impl.this.__db.beginTransaction();
                try {
                    UtxoDao_Impl.this.__insertionAdapterOfUtxo.insert((T[]) utxoArr);
                    UtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    UtxoDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object replace(final WalletId walletId, final Utxo[] utxoArr, Continuation<? super Unit> continuation) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1<Continuation<? super Unit>, Object>() {
            public Object invoke(Continuation<? super Unit> continuation) {
                return DefaultImpls.replace(UtxoDao_Impl.this, walletId, utxoArr, continuation);
            }
        }, continuation);
    }

    public Object delete(final TxId txId, final int i, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = UtxoDao_Impl.this.__preparedStmtOfDelete.acquire();
                String writeTxId = UtxoDao_Impl.this.__roomConverters.writeTxId(txId);
                if (writeTxId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeTxId);
                }
                acquire.bindLong(2, (long) i);
                UtxoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    UtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    UtxoDao_Impl.this.__db.endTransaction();
                    UtxoDao_Impl.this.__preparedStmtOfDelete.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteAll(final WalletId walletId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = UtxoDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                String writeWalletId = UtxoDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeWalletId);
                }
                UtxoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    UtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    UtxoDao_Impl.this.__db.endTransaction();
                    UtxoDao_Impl.this.__preparedStmtOfDeleteAll.release(acquire);
                }
            }
        }, continuation);
    }

    public Object refresh(final WalletId walletId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = UtxoDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                String writeWalletId = UtxoDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeWalletId);
                }
                UtxoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    UtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    UtxoDao_Impl.this.__db.endTransaction();
                    UtxoDao_Impl.this.__preparedStmtOfDeleteAll.release(acquire);
                }
            }
        }, continuation);
    }

    public LiveData<List<Utxo>> utxos(WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM utxo WHERE wallet_id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"utxo"}, false, new Callable<List<Utxo>>() {
            public List<Utxo> call() throws Exception {
                Cursor query = DBUtil.query(UtxoDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output_index");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "address");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "script");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        Utxo utxo = new Utxo(UtxoDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow)), UtxoDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow2)), query.getInt(columnIndexOrThrow3), UtxoDao_Impl.this.__roomConverters.readAddress(query.getString(columnIndexOrThrow4)), UtxoDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow5)), query.getBlob(columnIndexOrThrow6), UtxoDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow7)), UtxoDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow8)));
                        arrayList.add(utxo);
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

    public Object getUtxos(WalletId walletId, Continuation<? super List<Utxo>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM utxo WHERE wallet_id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<Utxo>>() {
            public List<Utxo> call() throws Exception {
                Cursor query = DBUtil.query(UtxoDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output_index");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "address");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "script");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        Utxo utxo = new Utxo(UtxoDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow)), UtxoDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow2)), query.getInt(columnIndexOrThrow3), UtxoDao_Impl.this.__roomConverters.readAddress(query.getString(columnIndexOrThrow4)), UtxoDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow5)), query.getBlob(columnIndexOrThrow6), UtxoDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow7)), UtxoDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow8)));
                        arrayList.add(utxo);
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
