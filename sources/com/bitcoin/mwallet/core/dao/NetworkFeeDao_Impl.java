package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFee;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class NetworkFeeDao_Impl implements NetworkFeeDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfNetworkFee;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public NetworkFeeDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfNetworkFee = new EntityInsertionAdapter<NetworkFee>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `network_fee`(`coin`,`fee_level`,`satoshis_per_byte`) VALUES (?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, NetworkFee networkFee) {
                String writeCoin = NetworkFeeDao_Impl.this.__roomConverters.writeCoin(networkFee.getCoin());
                if (writeCoin == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeCoin);
                }
                String writeNetworkFeeLevel = NetworkFeeDao_Impl.this.__roomConverters.writeNetworkFeeLevel(networkFee.getFeeLevel());
                if (writeNetworkFeeLevel == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, writeNetworkFeeLevel);
                }
                String writeBigDecimal = NetworkFeeDao_Impl.this.__roomConverters.writeBigDecimal(networkFee.getSatoshisPerByte());
                if (writeBigDecimal == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, writeBigDecimal);
                }
            }
        };
    }

    public Object upsert(final NetworkFee[] networkFeeArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                NetworkFeeDao_Impl.this.__db.beginTransaction();
                try {
                    NetworkFeeDao_Impl.this.__insertionAdapterOfNetworkFee.insert((T[]) networkFeeArr);
                    NetworkFeeDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    NetworkFeeDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object selectNetworkFees(Coin coin, Continuation<? super List<NetworkFee>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM network_fee WHERE coin= ?", 1);
        String writeCoin = this.__roomConverters.writeCoin(coin);
        if (writeCoin == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeCoin);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<NetworkFee>>() {
            public List<NetworkFee> call() throws Exception {
                Cursor query = DBUtil.query(NetworkFeeDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "fee_level");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "satoshis_per_byte");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new NetworkFee(NetworkFeeDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow)), NetworkFeeDao_Impl.this.__roomConverters.readNetworkFeeLevel(query.getString(columnIndexOrThrow2)), NetworkFeeDao_Impl.this.__roomConverters.readBigDecimal(query.getString(columnIndexOrThrow3))));
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
