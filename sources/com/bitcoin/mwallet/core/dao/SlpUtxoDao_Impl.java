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
import com.bitcoin.mwallet.core.dao.SlpUtxoDao.DefaultImpls;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

public final class SlpUtxoDao_Impl implements SlpUtxoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfSlpUtxo;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDelete;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public SlpUtxoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSlpUtxo = new EntityInsertionAdapter<SlpUtxo>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `slp_utxo`(`token_id`,`token_amount`,`token_type`,`transaction_type`,`wallet_id`,`tx_id`,`output_index`,`address`,`path`,`script`,`satoshis`,`coin`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, SlpUtxo slpUtxo) {
                String writeSlpId = SlpUtxoDao_Impl.this.__roomConverters.writeSlpId(slpUtxo.getTokenId());
                if (writeSlpId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeSlpId);
                }
                supportSQLiteStatement.bindLong(2, slpUtxo.getTokenAmount());
                if (slpUtxo.getTokenType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, slpUtxo.getTokenType());
                }
                if (slpUtxo.getTransactionType() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, slpUtxo.getTransactionType());
                }
                Utxo utxo = slpUtxo.getUtxo();
                if (utxo != null) {
                    String writeWalletId = SlpUtxoDao_Impl.this.__roomConverters.writeWalletId(utxo.getWalletId());
                    if (writeWalletId == null) {
                        supportSQLiteStatement.bindNull(5);
                    } else {
                        supportSQLiteStatement.bindString(5, writeWalletId);
                    }
                    String writeTxId = SlpUtxoDao_Impl.this.__roomConverters.writeTxId(utxo.getTxId());
                    if (writeTxId == null) {
                        supportSQLiteStatement.bindNull(6);
                    } else {
                        supportSQLiteStatement.bindString(6, writeTxId);
                    }
                    supportSQLiteStatement.bindLong(7, (long) utxo.getOutputIndex());
                    String writeAddress = SlpUtxoDao_Impl.this.__roomConverters.writeAddress(utxo.getAddress());
                    if (writeAddress == null) {
                        supportSQLiteStatement.bindNull(8);
                    } else {
                        supportSQLiteStatement.bindString(8, writeAddress);
                    }
                    String writeDerivationPath = SlpUtxoDao_Impl.this.__roomConverters.writeDerivationPath(utxo.getAddressPath());
                    if (writeDerivationPath == null) {
                        supportSQLiteStatement.bindNull(9);
                    } else {
                        supportSQLiteStatement.bindString(9, writeDerivationPath);
                    }
                    if (utxo.getScript() == null) {
                        supportSQLiteStatement.bindNull(10);
                    } else {
                        supportSQLiteStatement.bindBlob(10, utxo.getScript());
                    }
                    supportSQLiteStatement.bindLong(11, SlpUtxoDao_Impl.this.__roomConverters.writeSatoshis(utxo.getSatoshis()));
                    String writeCoin = SlpUtxoDao_Impl.this.__roomConverters.writeCoin(utxo.getCoin());
                    if (writeCoin == null) {
                        supportSQLiteStatement.bindNull(12);
                    } else {
                        supportSQLiteStatement.bindString(12, writeCoin);
                    }
                } else {
                    supportSQLiteStatement.bindNull(5);
                    supportSQLiteStatement.bindNull(6);
                    supportSQLiteStatement.bindNull(7);
                    supportSQLiteStatement.bindNull(8);
                    supportSQLiteStatement.bindNull(9);
                    supportSQLiteStatement.bindNull(10);
                    supportSQLiteStatement.bindNull(11);
                    supportSQLiteStatement.bindNull(12);
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM slp_utxo WHERE tx_id = ? AND output_index = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM slp_utxo where wallet_id = ?";
            }
        };
    }

    public Object upsert(final SlpUtxo[] slpUtxoArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SlpUtxoDao_Impl.this.__db.beginTransaction();
                try {
                    SlpUtxoDao_Impl.this.__insertionAdapterOfSlpUtxo.insert((T[]) slpUtxoArr);
                    SlpUtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SlpUtxoDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object replace(final WalletId walletId, final SlpUtxo[] slpUtxoArr, Continuation<? super Unit> continuation) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1<Continuation<? super Unit>, Object>() {
            public Object invoke(Continuation<? super Unit> continuation) {
                return DefaultImpls.replace(SlpUtxoDao_Impl.this, walletId, slpUtxoArr, continuation);
            }
        }, continuation);
    }

    public Object delete(final TxId txId, final int i, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = SlpUtxoDao_Impl.this.__preparedStmtOfDelete.acquire();
                String writeTxId = SlpUtxoDao_Impl.this.__roomConverters.writeTxId(txId);
                if (writeTxId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeTxId);
                }
                acquire.bindLong(2, (long) i);
                SlpUtxoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    SlpUtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SlpUtxoDao_Impl.this.__db.endTransaction();
                    SlpUtxoDao_Impl.this.__preparedStmtOfDelete.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteAll(final WalletId walletId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = SlpUtxoDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                String writeWalletId = SlpUtxoDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeWalletId);
                }
                SlpUtxoDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    SlpUtxoDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SlpUtxoDao_Impl.this.__db.endTransaction();
                    SlpUtxoDao_Impl.this.__preparedStmtOfDeleteAll.release(acquire);
                }
            }
        }, continuation);
    }

    public LiveData<List<SlpUtxo>> selectAll(WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM slp_utxo WHERE wallet_id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"slp_utxo"}, false, new Callable<List<SlpUtxo>>() {
            public List<SlpUtxo> call() throws Exception {
                int i;
                Utxo utxo;
                int i2;
                Cursor query = DBUtil.query(SlpUtxoDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "token_type");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "transaction_type");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "output_index");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "script");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i3 = columnIndexOrThrow;
                        SlpId readSlpId = SlpUtxoDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow));
                        long j = query.getLong(columnIndexOrThrow2);
                        String string = query.getString(columnIndexOrThrow3);
                        String string2 = query.getString(columnIndexOrThrow4);
                        if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                            if (query.isNull(columnIndexOrThrow12)) {
                                utxo = null;
                                i = columnIndexOrThrow2;
                                i2 = columnIndexOrThrow3;
                                SlpUtxo slpUtxo = new SlpUtxo(readSlpId, j, string, string2, utxo);
                                arrayList.add(slpUtxo);
                                columnIndexOrThrow3 = i2;
                                columnIndexOrThrow = i3;
                                columnIndexOrThrow2 = i;
                            }
                        }
                        int i4 = columnIndexOrThrow2;
                        i2 = columnIndexOrThrow3;
                        i = i4;
                        Utxo utxo2 = new Utxo(SlpUtxoDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow5)), SlpUtxoDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow6)), query.getInt(columnIndexOrThrow7), SlpUtxoDao_Impl.this.__roomConverters.readAddress(query.getString(columnIndexOrThrow8)), SlpUtxoDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow9)), query.getBlob(columnIndexOrThrow10), SlpUtxoDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow11)), SlpUtxoDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow12)));
                        utxo = utxo2;
                        SlpUtxo slpUtxo2 = new SlpUtxo(readSlpId, j, string, string2, utxo);
                        arrayList.add(slpUtxo2);
                        columnIndexOrThrow3 = i2;
                        columnIndexOrThrow = i3;
                        columnIndexOrThrow2 = i;
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

    public LiveData<List<SlpUtxo>> selectTokenUtxo(WalletId walletId, SlpId slpId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM slp_utxo WHERE wallet_id = ? AND token_id = ?", 2);
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
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"slp_utxo"}, false, new Callable<List<SlpUtxo>>() {
            public List<SlpUtxo> call() throws Exception {
                int i;
                Utxo utxo;
                int i2;
                Cursor query = DBUtil.query(SlpUtxoDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "token_type");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "transaction_type");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "output_index");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "script");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i3 = columnIndexOrThrow;
                        SlpId readSlpId = SlpUtxoDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow));
                        long j = query.getLong(columnIndexOrThrow2);
                        String string = query.getString(columnIndexOrThrow3);
                        String string2 = query.getString(columnIndexOrThrow4);
                        if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                            if (query.isNull(columnIndexOrThrow12)) {
                                utxo = null;
                                i = columnIndexOrThrow2;
                                i2 = columnIndexOrThrow3;
                                SlpUtxo slpUtxo = new SlpUtxo(readSlpId, j, string, string2, utxo);
                                arrayList.add(slpUtxo);
                                columnIndexOrThrow3 = i2;
                                columnIndexOrThrow = i3;
                                columnIndexOrThrow2 = i;
                            }
                        }
                        int i4 = columnIndexOrThrow2;
                        i2 = columnIndexOrThrow3;
                        i = i4;
                        Utxo utxo2 = new Utxo(SlpUtxoDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow5)), SlpUtxoDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow6)), query.getInt(columnIndexOrThrow7), SlpUtxoDao_Impl.this.__roomConverters.readAddress(query.getString(columnIndexOrThrow8)), SlpUtxoDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow9)), query.getBlob(columnIndexOrThrow10), SlpUtxoDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow11)), SlpUtxoDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow12)));
                        utxo = utxo2;
                        SlpUtxo slpUtxo2 = new SlpUtxo(readSlpId, j, string, string2, utxo);
                        arrayList.add(slpUtxo2);
                        columnIndexOrThrow3 = i2;
                        columnIndexOrThrow = i3;
                        columnIndexOrThrow2 = i;
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

    public Object selectTokenUtxoNonLive(WalletId walletId, SlpId slpId, Continuation<? super List<SlpUtxo>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM slp_utxo WHERE wallet_id = ? AND token_id = ?", 2);
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
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<SlpUtxo>>() {
            public List<SlpUtxo> call() throws Exception {
                int i;
                Utxo utxo;
                int i2;
                Cursor query = DBUtil.query(SlpUtxoDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "token_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "token_amount");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "token_type");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "transaction_type");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "wallet_id");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "output_index");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "address");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "script");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i3 = columnIndexOrThrow;
                        SlpId readSlpId = SlpUtxoDao_Impl.this.__roomConverters.readSlpId(query.getString(columnIndexOrThrow));
                        long j = query.getLong(columnIndexOrThrow2);
                        String string = query.getString(columnIndexOrThrow3);
                        String string2 = query.getString(columnIndexOrThrow4);
                        if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                            if (query.isNull(columnIndexOrThrow12)) {
                                utxo = null;
                                i = columnIndexOrThrow2;
                                i2 = columnIndexOrThrow3;
                                SlpUtxo slpUtxo = new SlpUtxo(readSlpId, j, string, string2, utxo);
                                arrayList.add(slpUtxo);
                                columnIndexOrThrow3 = i2;
                                columnIndexOrThrow = i3;
                                columnIndexOrThrow2 = i;
                            }
                        }
                        int i4 = columnIndexOrThrow2;
                        i2 = columnIndexOrThrow3;
                        i = i4;
                        Utxo utxo2 = new Utxo(SlpUtxoDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow5)), SlpUtxoDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow6)), query.getInt(columnIndexOrThrow7), SlpUtxoDao_Impl.this.__roomConverters.readAddress(query.getString(columnIndexOrThrow8)), SlpUtxoDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow9)), query.getBlob(columnIndexOrThrow10), SlpUtxoDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow11)), SlpUtxoDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow12)));
                        utxo = utxo2;
                        SlpUtxo slpUtxo2 = new SlpUtxo(readSlpId, j, string, string2, utxo);
                        arrayList.add(slpUtxo2);
                        columnIndexOrThrow3 = i2;
                        columnIndexOrThrow = i3;
                        columnIndexOrThrow2 = i;
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getWalletTokenAmount(WalletId walletId, SlpId slpId, Continuation<? super Long> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COALESCE(sum(COALESCE(token_amount,0)), 0) From slp_utxo where wallet_id = ? AND token_id = ?", 2);
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
        return CoroutinesRoom.execute(this.__db, false, new Callable<Long>() {
            public Long call() throws Exception {
                Cursor query = DBUtil.query(SlpUtxoDao_Impl.this.__db, acquire, false);
                try {
                    Long l = null;
                    if (query.moveToFirst()) {
                        if (!query.isNull(0)) {
                            l = Long.valueOf(query.getLong(0));
                        }
                    }
                    return l;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getAllAmountForToken(SlpId slpId, Continuation<? super Long> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COALESCE(sum(COALESCE(token_amount,0)), 0) From slp_utxo where token_id = ?", 1);
        String writeSlpId = this.__roomConverters.writeSlpId(slpId);
        if (writeSlpId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeSlpId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<Long>() {
            public Long call() throws Exception {
                Cursor query = DBUtil.query(SlpUtxoDao_Impl.this.__db, acquire, false);
                try {
                    Long l = null;
                    if (query.moveToFirst()) {
                        if (!query.isNull(0)) {
                            l = Long.valueOf(query.getLong(0));
                        }
                    }
                    return l;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }
}
