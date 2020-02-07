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
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class TransactionHistoryDao_Impl implements TransactionHistoryDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfHistoricTransaction;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfUpdateNote;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public TransactionHistoryDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfHistoricTransaction = new EntityInsertionAdapter<HistoricTransaction>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `tx_history`(`tx_id`,`wallet_id`,`timestamp`,`confirmations`,`satoshis`,`action`,`coin`,`to_address`,`note`,`fees`,`token_id`,`token_amount`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, HistoricTransaction historicTransaction) {
                String writeTxId = TransactionHistoryDao_Impl.this.__roomConverters.writeTxId(historicTransaction.getTxId());
                if (writeTxId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeTxId);
                }
                String writeWalletId = TransactionHistoryDao_Impl.this.__roomConverters.writeWalletId(historicTransaction.getWalletId());
                if (writeWalletId == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, writeWalletId);
                }
                supportSQLiteStatement.bindLong(3, historicTransaction.getTimestamp());
                supportSQLiteStatement.bindLong(4, (long) historicTransaction.getConfirmations());
                supportSQLiteStatement.bindLong(5, TransactionHistoryDao_Impl.this.__roomConverters.writeSatoshis(historicTransaction.getSatoshis()));
                String writeTxAction = TransactionHistoryDao_Impl.this.__roomConverters.writeTxAction(historicTransaction.getAction());
                if (writeTxAction == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, writeTxAction);
                }
                String writeCoin = TransactionHistoryDao_Impl.this.__roomConverters.writeCoin(historicTransaction.getCoin());
                if (writeCoin == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, writeCoin);
                }
                if (historicTransaction.getToAddress() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, historicTransaction.getToAddress());
                }
                if (historicTransaction.getNote() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, historicTransaction.getNote());
                }
                supportSQLiteStatement.bindLong(10, TransactionHistoryDao_Impl.this.__roomConverters.writeSatoshis(historicTransaction.getFees()));
                String writeSlpId = TransactionHistoryDao_Impl.this.__roomConverters.writeSlpId(historicTransaction.getTokenId());
                if (writeSlpId == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, writeSlpId);
                }
                supportSQLiteStatement.bindLong(12, historicTransaction.getTokenAmount());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM tx_history WHERE wallet_id = ?";
            }
        };
        this.__preparedStmtOfUpdateNote = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE tx_history SET note = ? WHERE tx_id = ? AND wallet_id = ?";
            }
        };
    }

    public Object upsert(final HistoricTransaction[] historicTransactionArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                TransactionHistoryDao_Impl.this.__db.beginTransaction();
                try {
                    TransactionHistoryDao_Impl.this.__insertionAdapterOfHistoricTransaction.insert((T[]) historicTransactionArr);
                    TransactionHistoryDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TransactionHistoryDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public void deleteAll(WalletId walletId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    public void updateNote(TxId txId, String str, WalletId walletId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateNote.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        String writeTxId = this.__roomConverters.writeTxId(txId);
        if (writeTxId == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, writeTxId);
        }
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, writeWalletId);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateNote.release(acquire);
        }
    }

    public Object selectLastConfirmedTransaction(Continuation<? super HistoricTransaction> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tx_history where confirmations > 0 ORDER BY timestamp DESC LIMIT 1", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<HistoricTransaction>() {
            public HistoricTransaction call() throws Exception {
                HistoricTransaction historicTransaction;
                Cursor query = DBUtil.query(TransactionHistoryDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "confirmations");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "action");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "to_address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "note");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fees");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    if (query.moveToFirst()) {
                        historicTransaction = new HistoricTransaction(TransactionHistoryDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow)), TransactionHistoryDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow5)), TransactionHistoryDao_Impl.this.__roomConverters.sendTxAction(query.getString(columnIndexOrThrow6)), TransactionHistoryDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow7)), query.getString(columnIndexOrThrow8), query.getString(columnIndexOrThrow9), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow10)), TransactionHistoryDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow11)), query.getLong(columnIndexOrThrow12));
                    } else {
                        historicTransaction = null;
                    }
                    return historicTransaction;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public LiveData<List<HistoricTransaction>> selectLatest(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tx_history ORDER BY timestamp DESC LIMIT ?", 1);
        acquire.bindLong(1, (long) i);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"tx_history"}, false, new Callable<List<HistoricTransaction>>() {
            public List<HistoricTransaction> call() throws Exception {
                Cursor query = DBUtil.query(TransactionHistoryDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "confirmations");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "action");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "to_address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "note");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fees");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i = columnIndexOrThrow;
                        int i2 = columnIndexOrThrow2;
                        int i3 = columnIndexOrThrow3;
                        int i4 = i2;
                        HistoricTransaction historicTransaction = new HistoricTransaction(TransactionHistoryDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow)), TransactionHistoryDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow5)), TransactionHistoryDao_Impl.this.__roomConverters.sendTxAction(query.getString(columnIndexOrThrow6)), TransactionHistoryDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow7)), query.getString(columnIndexOrThrow8), query.getString(columnIndexOrThrow9), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow10)), TransactionHistoryDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow11)), query.getLong(columnIndexOrThrow12));
                        arrayList.add(historicTransaction);
                        columnIndexOrThrow3 = i3;
                        columnIndexOrThrow = i;
                        columnIndexOrThrow2 = i4;
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

    public LiveData<List<HistoricTransaction>> selectByWalletId(WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tx_history WHERE wallet_id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"tx_history"}, false, new Callable<List<HistoricTransaction>>() {
            public List<HistoricTransaction> call() throws Exception {
                Cursor query = DBUtil.query(TransactionHistoryDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "confirmations");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "action");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "to_address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "note");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fees");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i = columnIndexOrThrow;
                        int i2 = columnIndexOrThrow2;
                        int i3 = columnIndexOrThrow3;
                        int i4 = i2;
                        HistoricTransaction historicTransaction = new HistoricTransaction(TransactionHistoryDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow)), TransactionHistoryDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow5)), TransactionHistoryDao_Impl.this.__roomConverters.sendTxAction(query.getString(columnIndexOrThrow6)), TransactionHistoryDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow7)), query.getString(columnIndexOrThrow8), query.getString(columnIndexOrThrow9), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow10)), TransactionHistoryDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow11)), query.getLong(columnIndexOrThrow12));
                        arrayList.add(historicTransaction);
                        columnIndexOrThrow3 = i3;
                        columnIndexOrThrow = i;
                        columnIndexOrThrow2 = i4;
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

    public LiveData<HistoricTransaction> selectById(TxId txId, WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tx_history WHERE tx_id = ? and wallet_id = ?", 2);
        String writeTxId = this.__roomConverters.writeTxId(txId);
        if (writeTxId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeTxId);
        }
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"tx_history"}, false, new Callable<HistoricTransaction>() {
            public HistoricTransaction call() throws Exception {
                HistoricTransaction historicTransaction;
                Cursor query = DBUtil.query(TransactionHistoryDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "confirmations");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "action");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "to_address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "note");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fees");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    if (query.moveToFirst()) {
                        historicTransaction = new HistoricTransaction(TransactionHistoryDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow)), TransactionHistoryDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow5)), TransactionHistoryDao_Impl.this.__roomConverters.sendTxAction(query.getString(columnIndexOrThrow6)), TransactionHistoryDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow7)), query.getString(columnIndexOrThrow8), query.getString(columnIndexOrThrow9), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow10)), TransactionHistoryDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow11)), query.getLong(columnIndexOrThrow12));
                    } else {
                        historicTransaction = null;
                    }
                    return historicTransaction;
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

    public Object selectTransactionByIdAndAction(TxId txId, TxAction txAction, Continuation<? super HistoricTransaction> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tx_history WHERE tx_id = ? AND `action` = ?", 2);
        String writeTxId = this.__roomConverters.writeTxId(txId);
        if (writeTxId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeTxId);
        }
        String writeTxAction = this.__roomConverters.writeTxAction(txAction);
        if (writeTxAction == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, writeTxAction);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<HistoricTransaction>() {
            public HistoricTransaction call() throws Exception {
                HistoricTransaction historicTransaction;
                Cursor query = DBUtil.query(TransactionHistoryDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "confirmations");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "action");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "to_address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "note");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fees");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    if (query.moveToFirst()) {
                        historicTransaction = new HistoricTransaction(TransactionHistoryDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow)), TransactionHistoryDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow5)), TransactionHistoryDao_Impl.this.__roomConverters.sendTxAction(query.getString(columnIndexOrThrow6)), TransactionHistoryDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow7)), query.getString(columnIndexOrThrow8), query.getString(columnIndexOrThrow9), TransactionHistoryDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow10)), TransactionHistoryDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow11)), query.getLong(columnIndexOrThrow12));
                    } else {
                        historicTransaction = null;
                    }
                    return historicTransaction;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }
}
