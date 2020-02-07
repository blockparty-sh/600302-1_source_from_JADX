package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.merchant.Merchant;
import com.bitcoin.mwallet.core.models.slp.Slp;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class MerchantDao_Impl implements MerchantDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfSlp;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public MerchantDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSlp = new EntityInsertionAdapter<Slp>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `slp`(`token_id`,`ticker`,`name`,`wallet_id`,`decimals`) VALUES (?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Slp slp) {
                String writeSlpId = MerchantDao_Impl.this.__roomConverters.writeSlpId(slp.getTokenId());
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
                String writeWalletId = MerchantDao_Impl.this.__roomConverters.writeWalletId(slp.getWalletId());
                if (writeWalletId == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, writeWalletId);
                }
                supportSQLiteStatement.bindLong(5, (long) slp.getDecimals());
            }
        };
    }

    public Object upsert(final Slp[] slpArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                MerchantDao_Impl.this.__db.beginTransaction();
                try {
                    MerchantDao_Impl.this.__insertionAdapterOfSlp.insert((T[]) slpArr);
                    MerchantDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    MerchantDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object selectFromTxId(TxId txId, Continuation<? super Merchant> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM merchant WHERE tx_id = ?", 1);
        String writeTxId = this.__roomConverters.writeTxId(txId);
        if (writeTxId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeTxId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<Merchant>() {
            public Merchant call() throws Exception {
                Merchant merchant;
                Cursor query = DBUtil.query(MerchantDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "tx_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "merchant_name");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "description");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "email");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "phone");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "website");
                    if (query.moveToFirst()) {
                        merchant = new Merchant(MerchantDao_Impl.this.__roomConverters.readTxId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6));
                    } else {
                        merchant = null;
                    }
                    return merchant;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }
}
