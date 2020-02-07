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
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.Copayers;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class WalletDao_Impl implements WalletDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfWallet;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteById;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteByIdAndCredentialId;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfRenameWallet;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfRenameWallet_1;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public WalletDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWallet = new EntityInsertionAdapter<C1261Wallet>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `wallet`(`pri_key`,`id`,`name`,`coin`,`network`,`path`,`public_key`,`signing_key`,`credential_id`,`credential_type`,`copayers_wallet_copayer_id`,`copayers_wallet_copayer_names`,`copayers_num_copayers`,`copayers_required_signatures`,`copayers_copayer_names`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, C1261Wallet wallet) {
                String writeWalletId = WalletDao_Impl.this.__roomConverters.writeWalletId(wallet.getPri_key());
                if (writeWalletId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeWalletId);
                }
                String writeWalletId2 = WalletDao_Impl.this.__roomConverters.writeWalletId(wallet.getId());
                if (writeWalletId2 == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, writeWalletId2);
                }
                if (wallet.getName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, wallet.getName());
                }
                String writeCoin = WalletDao_Impl.this.__roomConverters.writeCoin(wallet.getCoin());
                if (writeCoin == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, writeCoin);
                }
                String writeNetwork = WalletDao_Impl.this.__roomConverters.writeNetwork(wallet.getNetwork());
                if (writeNetwork == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, writeNetwork);
                }
                String writeDerivationPath = WalletDao_Impl.this.__roomConverters.writeDerivationPath(wallet.getPath());
                if (writeDerivationPath == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, writeDerivationPath);
                }
                String writePublicKey = WalletDao_Impl.this.__roomConverters.writePublicKey(wallet.getPublicKey());
                if (writePublicKey == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, writePublicKey);
                }
                String writeSigningKey = WalletDao_Impl.this.__roomConverters.writeSigningKey(wallet.getSigningKey());
                if (writeSigningKey == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, writeSigningKey);
                }
                CredentialId credentialId = wallet.getCredentialId();
                if (credentialId != null) {
                    if (credentialId.getId() == null) {
                        supportSQLiteStatement.bindNull(9);
                    } else {
                        supportSQLiteStatement.bindString(9, credentialId.getId());
                    }
                    String writeCredentialType = WalletDao_Impl.this.__roomConverters.writeCredentialType(credentialId.getType());
                    if (writeCredentialType == null) {
                        supportSQLiteStatement.bindNull(10);
                    } else {
                        supportSQLiteStatement.bindString(10, writeCredentialType);
                    }
                } else {
                    supportSQLiteStatement.bindNull(9);
                    supportSQLiteStatement.bindNull(10);
                }
                Copayers copayers = wallet.getCopayers();
                if (copayers != null) {
                    String writeCopayerId = WalletDao_Impl.this.__roomConverters.writeCopayerId(copayers.getWalletCopayerId());
                    if (writeCopayerId == null) {
                        supportSQLiteStatement.bindNull(11);
                    } else {
                        supportSQLiteStatement.bindString(11, writeCopayerId);
                    }
                    if (copayers.getWalletCopayerName() == null) {
                        supportSQLiteStatement.bindNull(12);
                    } else {
                        supportSQLiteStatement.bindString(12, copayers.getWalletCopayerName());
                    }
                    supportSQLiteStatement.bindLong(13, (long) copayers.getNumCopayers());
                    supportSQLiteStatement.bindLong(14, (long) copayers.getRequiredSignatures());
                    String writeListOfStrings = WalletDao_Impl.this.__roomConverters.writeListOfStrings(copayers.getCopayerNames());
                    if (writeListOfStrings == null) {
                        supportSQLiteStatement.bindNull(15);
                    } else {
                        supportSQLiteStatement.bindString(15, writeListOfStrings);
                    }
                } else {
                    supportSQLiteStatement.bindNull(11);
                    supportSQLiteStatement.bindNull(12);
                    supportSQLiteStatement.bindNull(13);
                    supportSQLiteStatement.bindNull(14);
                    supportSQLiteStatement.bindNull(15);
                }
            }
        };
        this.__preparedStmtOfRenameWallet = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE wallet SET name = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfRenameWallet_1 = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE wallet SET name = ? WHERE id = ? AND name =?";
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM wallet WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteByIdAndCredentialId = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM wallet WHERE id =? AND credential_id =?";
            }
        };
    }

    public Object upsert(final C1261Wallet[] walletArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                WalletDao_Impl.this.__db.beginTransaction();
                try {
                    WalletDao_Impl.this.__insertionAdapterOfWallet.insert((T[]) walletArr);
                    WalletDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object renameWallet(final WalletId walletId, final String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = WalletDao_Impl.this.__preparedStmtOfRenameWallet.acquire();
                String str = str;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                String writeWalletId = WalletDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(2);
                } else {
                    acquire.bindString(2, writeWalletId);
                }
                WalletDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    WalletDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletDao_Impl.this.__db.endTransaction();
                    WalletDao_Impl.this.__preparedStmtOfRenameWallet.release(acquire);
                }
            }
        }, continuation);
    }

    public Object renameWallet(final WalletId walletId, final String str, final String str2, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = WalletDao_Impl.this.__preparedStmtOfRenameWallet_1.acquire();
                String str = str2;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                String writeWalletId = WalletDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(2);
                } else {
                    acquire.bindString(2, writeWalletId);
                }
                String str2 = str;
                if (str2 == null) {
                    acquire.bindNull(3);
                } else {
                    acquire.bindString(3, str2);
                }
                WalletDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    WalletDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletDao_Impl.this.__db.endTransaction();
                    WalletDao_Impl.this.__preparedStmtOfRenameWallet_1.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteById(final WalletId walletId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = WalletDao_Impl.this.__preparedStmtOfDeleteById.acquire();
                String writeWalletId = WalletDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeWalletId);
                }
                WalletDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    WalletDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletDao_Impl.this.__db.endTransaction();
                    WalletDao_Impl.this.__preparedStmtOfDeleteById.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteByIdAndCredentialId(final WalletId walletId, final String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = WalletDao_Impl.this.__preparedStmtOfDeleteByIdAndCredentialId.acquire();
                String writeWalletId = WalletDao_Impl.this.__roomConverters.writeWalletId(walletId);
                if (writeWalletId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeWalletId);
                }
                String str = str;
                if (str == null) {
                    acquire.bindNull(2);
                } else {
                    acquire.bindString(2, str);
                }
                WalletDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    WalletDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WalletDao_Impl.this.__db.endTransaction();
                    WalletDao_Impl.this.__preparedStmtOfDeleteByIdAndCredentialId.release(acquire);
                }
            }
        }, continuation);
    }

    public LiveData<List<C1261Wallet>> allWallets() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM wallet", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"wallet"}, false, new Callable<List<C1261Wallet>>() {
            public List<C1261Wallet> call() throws Exception {
                int i;
                CredentialId credentialId;
                Copayers copayers;
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pri_key");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "network");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "public_key");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "signing_key");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_names");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "copayers_num_copayers");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "copayers_required_signatures");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "copayers_copayer_names");
                    int i7 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i8 = columnIndexOrThrow;
                        WalletId readWalletId = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow));
                        WalletId readWalletId2 = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2));
                        String string = query.getString(columnIndexOrThrow3);
                        Coin readCoin = WalletDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4));
                        Network readNetwork = WalletDao_Impl.this.__roomConverters.readNetwork(query.getString(columnIndexOrThrow5));
                        DerivationPath readDerivationPath = WalletDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow6));
                        ExtendedPublicKey readPublicKey = WalletDao_Impl.this.__roomConverters.readPublicKey(query.getString(columnIndexOrThrow7));
                        SigningKey readSigningKey = WalletDao_Impl.this.__roomConverters.readSigningKey(query.getString(columnIndexOrThrow8));
                        if (query.isNull(columnIndexOrThrow9)) {
                            if (query.isNull(columnIndexOrThrow10)) {
                                i = columnIndexOrThrow2;
                                credentialId = null;
                                if (query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12)) {
                                    i5 = columnIndexOrThrow14;
                                    i4 = columnIndexOrThrow15;
                                    i6 = i7;
                                } else {
                                    i6 = i7;
                                    if (query.isNull(i6)) {
                                        i5 = columnIndexOrThrow14;
                                        if (query.isNull(i5)) {
                                            i4 = columnIndexOrThrow15;
                                            if (query.isNull(i4)) {
                                                i3 = columnIndexOrThrow3;
                                                i2 = columnIndexOrThrow4;
                                                copayers = null;
                                                C1261Wallet wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                                arrayList.add(wallet);
                                                i7 = i6;
                                                columnIndexOrThrow3 = i3;
                                                columnIndexOrThrow4 = i2;
                                                columnIndexOrThrow = i8;
                                                columnIndexOrThrow14 = i5;
                                                columnIndexOrThrow15 = i4;
                                                columnIndexOrThrow2 = i;
                                            }
                                        }
                                    } else {
                                        i5 = columnIndexOrThrow14;
                                    }
                                    i4 = columnIndexOrThrow15;
                                }
                                i3 = columnIndexOrThrow3;
                                i2 = columnIndexOrThrow4;
                                Copayers copayers2 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(i6), query.getInt(i5), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i4)));
                                copayers = copayers2;
                                C1261Wallet wallet2 = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                arrayList.add(wallet2);
                                i7 = i6;
                                columnIndexOrThrow3 = i3;
                                columnIndexOrThrow4 = i2;
                                columnIndexOrThrow = i8;
                                columnIndexOrThrow14 = i5;
                                columnIndexOrThrow15 = i4;
                                columnIndexOrThrow2 = i;
                            }
                        }
                        i = columnIndexOrThrow2;
                        credentialId = new CredentialId(query.getString(columnIndexOrThrow9), WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow10)));
                        if (query.isNull(columnIndexOrThrow11)) {
                        }
                        i5 = columnIndexOrThrow14;
                        i4 = columnIndexOrThrow15;
                        i6 = i7;
                        i3 = columnIndexOrThrow3;
                        i2 = columnIndexOrThrow4;
                        Copayers copayers22 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(i6), query.getInt(i5), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i4)));
                        copayers = copayers22;
                        C1261Wallet wallet22 = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                        arrayList.add(wallet22);
                        i7 = i6;
                        columnIndexOrThrow3 = i3;
                        columnIndexOrThrow4 = i2;
                        columnIndexOrThrow = i8;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow15 = i4;
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

    public Object getWallets(Continuation<? super List<C1261Wallet>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM wallet", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<C1261Wallet>>() {
            public List<C1261Wallet> call() throws Exception {
                int i;
                CredentialId credentialId;
                Copayers copayers;
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pri_key");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "network");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "public_key");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "signing_key");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_names");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "copayers_num_copayers");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "copayers_required_signatures");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "copayers_copayer_names");
                    int i7 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i8 = columnIndexOrThrow;
                        WalletId readWalletId = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow));
                        WalletId readWalletId2 = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2));
                        String string = query.getString(columnIndexOrThrow3);
                        Coin readCoin = WalletDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4));
                        Network readNetwork = WalletDao_Impl.this.__roomConverters.readNetwork(query.getString(columnIndexOrThrow5));
                        DerivationPath readDerivationPath = WalletDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow6));
                        ExtendedPublicKey readPublicKey = WalletDao_Impl.this.__roomConverters.readPublicKey(query.getString(columnIndexOrThrow7));
                        SigningKey readSigningKey = WalletDao_Impl.this.__roomConverters.readSigningKey(query.getString(columnIndexOrThrow8));
                        if (query.isNull(columnIndexOrThrow9)) {
                            if (query.isNull(columnIndexOrThrow10)) {
                                i = columnIndexOrThrow2;
                                credentialId = null;
                                if (query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12)) {
                                    i5 = columnIndexOrThrow14;
                                    i4 = columnIndexOrThrow15;
                                    i6 = i7;
                                } else {
                                    i6 = i7;
                                    if (query.isNull(i6)) {
                                        i5 = columnIndexOrThrow14;
                                        if (query.isNull(i5)) {
                                            i4 = columnIndexOrThrow15;
                                            if (query.isNull(i4)) {
                                                i3 = columnIndexOrThrow3;
                                                i2 = columnIndexOrThrow4;
                                                copayers = null;
                                                C1261Wallet wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                                arrayList.add(wallet);
                                                i7 = i6;
                                                columnIndexOrThrow3 = i3;
                                                columnIndexOrThrow4 = i2;
                                                columnIndexOrThrow = i8;
                                                columnIndexOrThrow14 = i5;
                                                columnIndexOrThrow15 = i4;
                                                columnIndexOrThrow2 = i;
                                            }
                                        }
                                    } else {
                                        i5 = columnIndexOrThrow14;
                                    }
                                    i4 = columnIndexOrThrow15;
                                }
                                i3 = columnIndexOrThrow3;
                                i2 = columnIndexOrThrow4;
                                Copayers copayers2 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(i6), query.getInt(i5), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i4)));
                                copayers = copayers2;
                                C1261Wallet wallet2 = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                arrayList.add(wallet2);
                                i7 = i6;
                                columnIndexOrThrow3 = i3;
                                columnIndexOrThrow4 = i2;
                                columnIndexOrThrow = i8;
                                columnIndexOrThrow14 = i5;
                                columnIndexOrThrow15 = i4;
                                columnIndexOrThrow2 = i;
                            }
                        }
                        i = columnIndexOrThrow2;
                        credentialId = new CredentialId(query.getString(columnIndexOrThrow9), WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow10)));
                        if (query.isNull(columnIndexOrThrow11)) {
                        }
                        i5 = columnIndexOrThrow14;
                        i4 = columnIndexOrThrow15;
                        i6 = i7;
                        i3 = columnIndexOrThrow3;
                        i2 = columnIndexOrThrow4;
                        Copayers copayers22 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(i6), query.getInt(i5), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i4)));
                        copayers = copayers22;
                        C1261Wallet wallet22 = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                        arrayList.add(wallet22);
                        i7 = i6;
                        columnIndexOrThrow3 = i3;
                        columnIndexOrThrow4 = i2;
                        columnIndexOrThrow = i8;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow15 = i4;
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

    public LiveData<C1261Wallet> walletById(WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM wallet WHERE id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"wallet"}, false, new Callable<C1261Wallet>() {
            public C1261Wallet call() throws Exception {
                C1261Wallet wallet;
                CredentialId credentialId;
                Copayers copayers;
                int i;
                int i2;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pri_key");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "network");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "public_key");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "signing_key");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_names");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "copayers_num_copayers");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "copayers_required_signatures");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "copayers_copayer_names");
                    if (query.moveToFirst()) {
                        int i3 = columnIndexOrThrow15;
                        WalletId readWalletId = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow));
                        WalletId readWalletId2 = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2));
                        String string = query.getString(columnIndexOrThrow3);
                        Coin readCoin = WalletDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4));
                        Network readNetwork = WalletDao_Impl.this.__roomConverters.readNetwork(query.getString(columnIndexOrThrow5));
                        DerivationPath readDerivationPath = WalletDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow6));
                        ExtendedPublicKey readPublicKey = WalletDao_Impl.this.__roomConverters.readPublicKey(query.getString(columnIndexOrThrow7));
                        SigningKey readSigningKey = WalletDao_Impl.this.__roomConverters.readSigningKey(query.getString(columnIndexOrThrow8));
                        if (query.isNull(columnIndexOrThrow9)) {
                            if (query.isNull(columnIndexOrThrow10)) {
                                credentialId = null;
                                if (query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12) || !query.isNull(columnIndexOrThrow13)) {
                                    i2 = columnIndexOrThrow14;
                                } else {
                                    i2 = columnIndexOrThrow14;
                                    if (query.isNull(i2)) {
                                        i = i3;
                                        if (!query.isNull(i)) {
                                            Copayers copayers2 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i)));
                                            copayers = copayers2;
                                            wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                        } else {
                                            copayers = null;
                                            wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                        }
                                    }
                                }
                                i = i3;
                                Copayers copayers22 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i)));
                                copayers = copayers22;
                                wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                            }
                        }
                        credentialId = new CredentialId(query.getString(columnIndexOrThrow9), WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow10)));
                        if (query.isNull(columnIndexOrThrow11)) {
                        }
                        i2 = columnIndexOrThrow14;
                        i = i3;
                        Copayers copayers222 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i)));
                        copayers = copayers222;
                        wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                    } else {
                        wallet = null;
                    }
                    return wallet;
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

    public LiveData<C1261Wallet> walletByPk(WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM wallet where pri_key = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"wallet"}, false, new Callable<C1261Wallet>() {
            public C1261Wallet call() throws Exception {
                C1261Wallet wallet;
                CredentialId credentialId;
                Copayers copayers;
                int i;
                int i2;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pri_key");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "network");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "public_key");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "signing_key");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_id");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_names");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "copayers_num_copayers");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "copayers_required_signatures");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "copayers_copayer_names");
                    if (query.moveToFirst()) {
                        int i3 = columnIndexOrThrow15;
                        WalletId readWalletId = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow));
                        WalletId readWalletId2 = WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2));
                        String string = query.getString(columnIndexOrThrow3);
                        Coin readCoin = WalletDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4));
                        Network readNetwork = WalletDao_Impl.this.__roomConverters.readNetwork(query.getString(columnIndexOrThrow5));
                        DerivationPath readDerivationPath = WalletDao_Impl.this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow6));
                        ExtendedPublicKey readPublicKey = WalletDao_Impl.this.__roomConverters.readPublicKey(query.getString(columnIndexOrThrow7));
                        SigningKey readSigningKey = WalletDao_Impl.this.__roomConverters.readSigningKey(query.getString(columnIndexOrThrow8));
                        if (query.isNull(columnIndexOrThrow9)) {
                            if (query.isNull(columnIndexOrThrow10)) {
                                credentialId = null;
                                if (query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12) || !query.isNull(columnIndexOrThrow13)) {
                                    i2 = columnIndexOrThrow14;
                                } else {
                                    i2 = columnIndexOrThrow14;
                                    if (query.isNull(i2)) {
                                        i = i3;
                                        if (!query.isNull(i)) {
                                            Copayers copayers2 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i)));
                                            copayers = copayers2;
                                            wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                        } else {
                                            copayers = null;
                                            wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                        }
                                    }
                                }
                                i = i3;
                                Copayers copayers22 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i)));
                                copayers = copayers22;
                                wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                            }
                        }
                        credentialId = new CredentialId(query.getString(columnIndexOrThrow9), WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow10)));
                        if (query.isNull(columnIndexOrThrow11)) {
                        }
                        i2 = columnIndexOrThrow14;
                        i = i3;
                        Copayers copayers222 = new Copayers(WalletDao_Impl.this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), WalletDao_Impl.this.__roomConverters.readListOfStrings(query.getString(i)));
                        copayers = copayers222;
                        wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                    } else {
                        wallet = null;
                    }
                    return wallet;
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

    public C1261Wallet getWalletById(WalletId walletId) {
        RoomSQLiteQuery roomSQLiteQuery;
        C1261Wallet wallet;
        CredentialId credentialId;
        Copayers copayers;
        int i;
        int i2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM wallet WHERE id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pri_key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "network");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "public_key");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "signing_key");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_id");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_names");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "copayers_num_copayers");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "copayers_required_signatures");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "copayers_copayer_names");
                if (query.moveToFirst()) {
                    int i3 = columnIndexOrThrow15;
                    WalletId readWalletId = this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow));
                    WalletId readWalletId2 = this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2));
                    String string = query.getString(columnIndexOrThrow3);
                    Coin readCoin = this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4));
                    Network readNetwork = this.__roomConverters.readNetwork(query.getString(columnIndexOrThrow5));
                    DerivationPath readDerivationPath = this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow6));
                    ExtendedPublicKey readPublicKey = this.__roomConverters.readPublicKey(query.getString(columnIndexOrThrow7));
                    SigningKey readSigningKey = this.__roomConverters.readSigningKey(query.getString(columnIndexOrThrow8));
                    if (query.isNull(columnIndexOrThrow9)) {
                        if (query.isNull(columnIndexOrThrow10)) {
                            credentialId = null;
                            if (query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12) || !query.isNull(columnIndexOrThrow13)) {
                                i2 = columnIndexOrThrow14;
                            } else {
                                i2 = columnIndexOrThrow14;
                                if (query.isNull(i2)) {
                                    i = i3;
                                    if (!query.isNull(i)) {
                                        Copayers copayers2 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), this.__roomConverters.readListOfStrings(query.getString(i)));
                                        copayers = copayers2;
                                        wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                    } else {
                                        copayers = null;
                                        wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                    }
                                }
                            }
                            i = i3;
                            Copayers copayers22 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), this.__roomConverters.readListOfStrings(query.getString(i)));
                            copayers = copayers22;
                            wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                        }
                    }
                    credentialId = new CredentialId(query.getString(columnIndexOrThrow9), this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow10)));
                    if (query.isNull(columnIndexOrThrow11)) {
                    }
                    i2 = columnIndexOrThrow14;
                    i = i3;
                    Copayers copayers222 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), this.__roomConverters.readListOfStrings(query.getString(i)));
                    copayers = copayers222;
                    wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                } else {
                    wallet = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return wallet;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<C1261Wallet> getWalletsById(WalletId walletId) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        CredentialId credentialId;
        int i2;
        Copayers copayers;
        int i3;
        int i4;
        int i5;
        int i6;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM wallet WHERE id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pri_key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "network");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "public_key");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "signing_key");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_id");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_names");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "copayers_num_copayers");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "copayers_required_signatures");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "copayers_copayer_names");
                int i7 = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    int i8 = columnIndexOrThrow;
                    WalletId readWalletId = this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow));
                    WalletId readWalletId2 = this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2));
                    String string = query.getString(columnIndexOrThrow3);
                    Coin readCoin = this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4));
                    Network readNetwork = this.__roomConverters.readNetwork(query.getString(columnIndexOrThrow5));
                    DerivationPath readDerivationPath = this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow6));
                    ExtendedPublicKey readPublicKey = this.__roomConverters.readPublicKey(query.getString(columnIndexOrThrow7));
                    SigningKey readSigningKey = this.__roomConverters.readSigningKey(query.getString(columnIndexOrThrow8));
                    if (query.isNull(columnIndexOrThrow9)) {
                        if (query.isNull(columnIndexOrThrow10)) {
                            i = columnIndexOrThrow2;
                            credentialId = null;
                            if (query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12)) {
                                i5 = columnIndexOrThrow14;
                                i4 = columnIndexOrThrow15;
                                i6 = i7;
                            } else {
                                i6 = i7;
                                if (query.isNull(i6)) {
                                    i5 = columnIndexOrThrow14;
                                    if (query.isNull(i5)) {
                                        i4 = columnIndexOrThrow15;
                                        if (query.isNull(i4)) {
                                            i2 = columnIndexOrThrow3;
                                            i3 = columnIndexOrThrow4;
                                            copayers = null;
                                            C1261Wallet wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                            arrayList.add(wallet);
                                            columnIndexOrThrow3 = i2;
                                            i7 = i6;
                                            columnIndexOrThrow14 = i5;
                                            columnIndexOrThrow4 = i3;
                                            columnIndexOrThrow = i8;
                                            columnIndexOrThrow2 = i;
                                            columnIndexOrThrow15 = i4;
                                        }
                                    }
                                } else {
                                    i5 = columnIndexOrThrow14;
                                }
                                i4 = columnIndexOrThrow15;
                            }
                            i2 = columnIndexOrThrow3;
                            i3 = columnIndexOrThrow4;
                            Copayers copayers2 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(i6), query.getInt(i5), this.__roomConverters.readListOfStrings(query.getString(i4)));
                            copayers = copayers2;
                            C1261Wallet wallet2 = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                            arrayList.add(wallet2);
                            columnIndexOrThrow3 = i2;
                            i7 = i6;
                            columnIndexOrThrow14 = i5;
                            columnIndexOrThrow4 = i3;
                            columnIndexOrThrow = i8;
                            columnIndexOrThrow2 = i;
                            columnIndexOrThrow15 = i4;
                        }
                    }
                    i = columnIndexOrThrow2;
                    credentialId = new CredentialId(query.getString(columnIndexOrThrow9), this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow10)));
                    if (query.isNull(columnIndexOrThrow11)) {
                    }
                    i5 = columnIndexOrThrow14;
                    i4 = columnIndexOrThrow15;
                    i6 = i7;
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    Copayers copayers22 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(i6), query.getInt(i5), this.__roomConverters.readListOfStrings(query.getString(i4)));
                    copayers = copayers22;
                    C1261Wallet wallet22 = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                    arrayList.add(wallet22);
                    columnIndexOrThrow3 = i2;
                    i7 = i6;
                    columnIndexOrThrow14 = i5;
                    columnIndexOrThrow4 = i3;
                    columnIndexOrThrow = i8;
                    columnIndexOrThrow2 = i;
                    columnIndexOrThrow15 = i4;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public Object getPublicKey(WalletId walletId, Continuation<? super ExtendedPublicKey> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT public_key FROM wallet WHERE id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<ExtendedPublicKey>() {
            public ExtendedPublicKey call() throws Exception {
                ExtendedPublicKey extendedPublicKey;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    if (query.moveToFirst()) {
                        extendedPublicKey = WalletDao_Impl.this.__roomConverters.readPublicKey(query.getString(0));
                    } else {
                        extendedPublicKey = null;
                    }
                    return extendedPublicKey;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getWalletIds(Continuation<? super List<WalletId>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM wallet", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<WalletId>>() {
            public List<WalletId> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new WalletId(query.getString(columnIndexOrThrow)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getWalletPks(Continuation<? super List<WalletId>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT pri_key FROM wallet", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<WalletId>>() {
            public List<WalletId> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(0)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getWalletIds(Coin coin, Continuation<? super List<WalletId>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM wallet where coin = ?", 1);
        String writeCoin = this.__roomConverters.writeCoin(coin);
        if (writeCoin == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeCoin);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<WalletId>>() {
            public List<WalletId> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new WalletId(query.getString(columnIndexOrThrow)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object getWalletPks(Coin coin, Continuation<? super List<WalletId>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT pri_key from wallet where coin =?", 1);
        String writeCoin = this.__roomConverters.writeCoin(coin);
        if (writeCoin == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeCoin);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<WalletId>>() {
            public List<WalletId> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(0)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectWalletIdsByCoin(Coin coin, Continuation<? super List<WalletId>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM wallet WHERE coin = ?", 1);
        String writeCoin = this.__roomConverters.writeCoin(coin);
        if (writeCoin == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeCoin);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<WalletId>>() {
            public List<WalletId> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new WalletId(query.getString(columnIndexOrThrow)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectWalletPkByCoin(Coin coin, Continuation<? super List<WalletId>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT pri_key from wallet WHERE coin =?", 1);
        String writeCoin = this.__roomConverters.writeCoin(coin);
        if (writeCoin == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeCoin);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<WalletId>>() {
            public List<WalletId> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(0)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public LiveData<List<WalletInfoSatoshis>> selectWalletInfoSatoshis() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT wallet.credential_type as credentialType, wallet.id as walletId, wallet.name as name, wallet.coin as coin, wallet.copayers_num_copayers as numCopayers, SUM(utxo.satoshis) as satoshis FROM wallet LEFT JOIN utxo ON wallet.id = utxo.wallet_id GROUP BY wallet.pri_key", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"wallet", "utxo"}, false, new Callable<List<WalletInfoSatoshis>>() {
            public List<WalletInfoSatoshis> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "credentialType");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "walletId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "numCopayers");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        WalletInfoSatoshis walletInfoSatoshis = new WalletInfoSatoshis(WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow)), WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getString(columnIndexOrThrow3), WalletDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5), WalletDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow6)));
                        arrayList.add(walletInfoSatoshis);
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

    public LiveData<WalletInfoSatoshis> selectWalletInfoSatoshis(WalletId walletId) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT wallet.credential_type as credentialType, wallet.id as walletId, wallet.name as name, wallet.coin as coin, wallet.copayers_num_copayers as numCopayers, SUM(utxo.satoshis) as satoshis FROM wallet LEFT JOIN utxo ON wallet.id = utxo.wallet_id WHERE wallet.id = ? GROUP by wallet.pri_key", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"wallet", "utxo"}, false, new Callable<WalletInfoSatoshis>() {
            public WalletInfoSatoshis call() throws Exception {
                WalletInfoSatoshis walletInfoSatoshis;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "credentialType");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "walletId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "numCopayers");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    if (query.moveToFirst()) {
                        walletInfoSatoshis = new WalletInfoSatoshis(WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow)), WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getString(columnIndexOrThrow3), WalletDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5), WalletDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow6)));
                    } else {
                        walletInfoSatoshis = null;
                    }
                    return walletInfoSatoshis;
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

    public LiveData<List<WalletInfoSatoshis>> selectWalletInfoSatoshis(Coin coin) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT wallet.credential_type as credentialType, wallet.id as walletId, wallet.name as name, wallet.coin as coin, wallet.copayers_num_copayers as numCopayers, SUM(utxo.satoshis) as satoshis FROM wallet LEFT JOIN utxo ON wallet.id = utxo.wallet_id WHERE wallet.coin = ? GROUP BY wallet.pri_key", 1);
        String writeCoin = this.__roomConverters.writeCoin(coin);
        if (writeCoin == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeCoin);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"wallet", "utxo"}, false, new Callable<List<WalletInfoSatoshis>>() {
            public List<WalletInfoSatoshis> call() throws Exception {
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "credentialType");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "walletId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "numCopayers");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "satoshis");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        WalletInfoSatoshis walletInfoSatoshis = new WalletInfoSatoshis(WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow)), WalletDao_Impl.this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2)), query.getString(columnIndexOrThrow3), WalletDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4)), query.getInt(columnIndexOrThrow5), WalletDao_Impl.this.__roomConverters.readSatoshis(query.getLong(columnIndexOrThrow6)));
                        arrayList.add(walletInfoSatoshis);
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

    public Object getCoin(WalletId walletId, Continuation<? super Coin> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT coin FROM wallet WHERE id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<Coin>() {
            public Coin call() throws Exception {
                Coin coin;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    if (query.moveToFirst()) {
                        coin = WalletDao_Impl.this.__roomConverters.readCoin(query.getString(0));
                    } else {
                        coin = null;
                    }
                    return coin;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectCredentialId(WalletId walletId, Continuation<? super CredentialId> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT credential_id as id, credential_type as type FROM wallet WHERE id = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<CredentialId>() {
            public CredentialId call() throws Exception {
                CredentialId credentialId;
                Cursor query = DBUtil.query(WalletDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
                    if (query.moveToFirst()) {
                        credentialId = new CredentialId(query.getString(columnIndexOrThrow), WalletDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow2)));
                    } else {
                        credentialId = null;
                    }
                    return credentialId;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public C1261Wallet getWalletByPk(WalletId walletId) {
        RoomSQLiteQuery roomSQLiteQuery;
        C1261Wallet wallet;
        CredentialId credentialId;
        Copayers copayers;
        int i;
        int i2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM wallet WHERE pri_key = ?", 1);
        String writeWalletId = this.__roomConverters.writeWalletId(walletId);
        if (writeWalletId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeWalletId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pri_key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "coin");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "network");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, JsonDataKey_signMessage.path);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "public_key");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "signing_key");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_id");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "copayers_wallet_copayer_names");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "copayers_num_copayers");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "copayers_required_signatures");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "copayers_copayer_names");
                if (query.moveToFirst()) {
                    int i3 = columnIndexOrThrow15;
                    WalletId readWalletId = this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow));
                    WalletId readWalletId2 = this.__roomConverters.readWalletId(query.getString(columnIndexOrThrow2));
                    String string = query.getString(columnIndexOrThrow3);
                    Coin readCoin = this.__roomConverters.readCoin(query.getString(columnIndexOrThrow4));
                    Network readNetwork = this.__roomConverters.readNetwork(query.getString(columnIndexOrThrow5));
                    DerivationPath readDerivationPath = this.__roomConverters.readDerivationPath(query.getString(columnIndexOrThrow6));
                    ExtendedPublicKey readPublicKey = this.__roomConverters.readPublicKey(query.getString(columnIndexOrThrow7));
                    SigningKey readSigningKey = this.__roomConverters.readSigningKey(query.getString(columnIndexOrThrow8));
                    if (query.isNull(columnIndexOrThrow9)) {
                        if (query.isNull(columnIndexOrThrow10)) {
                            credentialId = null;
                            if (query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12) || !query.isNull(columnIndexOrThrow13)) {
                                i2 = columnIndexOrThrow14;
                            } else {
                                i2 = columnIndexOrThrow14;
                                if (query.isNull(i2)) {
                                    i = i3;
                                    if (!query.isNull(i)) {
                                        Copayers copayers2 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), this.__roomConverters.readListOfStrings(query.getString(i)));
                                        copayers = copayers2;
                                        wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                    } else {
                                        copayers = null;
                                        wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                                    }
                                }
                            }
                            i = i3;
                            Copayers copayers22 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), this.__roomConverters.readListOfStrings(query.getString(i)));
                            copayers = copayers22;
                            wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                        }
                    }
                    credentialId = new CredentialId(query.getString(columnIndexOrThrow9), this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow10)));
                    if (query.isNull(columnIndexOrThrow11)) {
                    }
                    i2 = columnIndexOrThrow14;
                    i = i3;
                    Copayers copayers222 = new Copayers(this.__roomConverters.readCopayerId(query.getString(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12), query.getInt(columnIndexOrThrow13), query.getInt(i2), this.__roomConverters.readListOfStrings(query.getString(i)));
                    copayers = copayers222;
                    wallet = new C1261Wallet(readWalletId, readWalletId2, string, readCoin, readNetwork, credentialId, readDerivationPath, readPublicKey, copayers, readSigningKey);
                } else {
                    wallet = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return wallet;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }
}
