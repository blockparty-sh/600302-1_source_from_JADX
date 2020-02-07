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
import com.bitcoin.bitcoink.PrivateKey;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.credential.CredentialEncrypted;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.bitcoin.mwallet.core.models.credential.CredentialZion;
import com.bitcoin.mwallet.zion.ZionId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class CredentialDao_Impl extends CredentialDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfCredentialEncrypted;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfCredentialMnemonic;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfCredentialZion;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteEncrypted;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteMnemonic;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfDeleteZion;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public CredentialDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfCredentialMnemonic = new EntityInsertionAdapter<CredentialMnemonic>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `credential_mnemonic`(`mnemonic`,`master_priv_key`,`id_id`,`id_type`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, CredentialMnemonic credentialMnemonic) {
                String writeEncryptedAtRestList = CredentialDao_Impl.this.__roomConverters.writeEncryptedAtRestList(credentialMnemonic.getMnemonic());
                if (writeEncryptedAtRestList == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeEncryptedAtRestList);
                }
                String writePrivateKey = CredentialDao_Impl.this.__roomConverters.writePrivateKey(credentialMnemonic.getMasterPrivKey());
                if (writePrivateKey == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, writePrivateKey);
                }
                CredentialId id = credentialMnemonic.getId();
                if (id != null) {
                    if (id.getId() == null) {
                        supportSQLiteStatement.bindNull(3);
                    } else {
                        supportSQLiteStatement.bindString(3, id.getId());
                    }
                    String writeCredentialType = CredentialDao_Impl.this.__roomConverters.writeCredentialType(id.getType());
                    if (writeCredentialType == null) {
                        supportSQLiteStatement.bindNull(4);
                    } else {
                        supportSQLiteStatement.bindString(4, writeCredentialType);
                    }
                } else {
                    supportSQLiteStatement.bindNull(3);
                    supportSQLiteStatement.bindNull(4);
                }
            }
        };
        this.__insertionAdapterOfCredentialZion = new EntityInsertionAdapter<CredentialZion>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `credential_zion`(`zion_id`,`id_id`,`id_type`) VALUES (?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, CredentialZion credentialZion) {
                String writeZionId = CredentialDao_Impl.this.__roomConverters.writeZionId(credentialZion.getZionId());
                if (writeZionId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeZionId);
                }
                CredentialId id = credentialZion.getId();
                if (id != null) {
                    if (id.getId() == null) {
                        supportSQLiteStatement.bindNull(2);
                    } else {
                        supportSQLiteStatement.bindString(2, id.getId());
                    }
                    String writeCredentialType = CredentialDao_Impl.this.__roomConverters.writeCredentialType(id.getType());
                    if (writeCredentialType == null) {
                        supportSQLiteStatement.bindNull(3);
                    } else {
                        supportSQLiteStatement.bindString(3, writeCredentialType);
                    }
                } else {
                    supportSQLiteStatement.bindNull(2);
                    supportSQLiteStatement.bindNull(3);
                }
            }
        };
        this.__insertionAdapterOfCredentialEncrypted = new EntityInsertionAdapter<CredentialEncrypted>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `credential_encrypted`(`encrypted_mnmeonic`,`id_id`,`id_type`) VALUES (?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, CredentialEncrypted credentialEncrypted) {
                if (credentialEncrypted.getEncryptedMnemonic() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, credentialEncrypted.getEncryptedMnemonic());
                }
                CredentialId id = credentialEncrypted.getId();
                if (id != null) {
                    if (id.getId() == null) {
                        supportSQLiteStatement.bindNull(2);
                    } else {
                        supportSQLiteStatement.bindString(2, id.getId());
                    }
                    String writeCredentialType = CredentialDao_Impl.this.__roomConverters.writeCredentialType(id.getType());
                    if (writeCredentialType == null) {
                        supportSQLiteStatement.bindNull(3);
                    } else {
                        supportSQLiteStatement.bindString(3, writeCredentialType);
                    }
                } else {
                    supportSQLiteStatement.bindNull(2);
                    supportSQLiteStatement.bindNull(3);
                }
            }
        };
        this.__preparedStmtOfDeleteMnemonic = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM credential_mnemonic WHERE id_id = ?";
            }
        };
        this.__preparedStmtOfDeleteZion = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM credential_zion WHERE id_id = ?";
            }
        };
        this.__preparedStmtOfDeleteEncrypted = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM credential_encrypted WHERE id_id = ?";
            }
        };
    }

    public Object upsert(final CredentialMnemonic[] credentialMnemonicArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                CredentialDao_Impl.this.__db.beginTransaction();
                try {
                    CredentialDao_Impl.this.__insertionAdapterOfCredentialMnemonic.insert((T[]) credentialMnemonicArr);
                    CredentialDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    CredentialDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object upsert(final CredentialZion[] credentialZionArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                CredentialDao_Impl.this.__db.beginTransaction();
                try {
                    CredentialDao_Impl.this.__insertionAdapterOfCredentialZion.insert((T[]) credentialZionArr);
                    CredentialDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    CredentialDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object upsert(final CredentialEncrypted[] credentialEncryptedArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                CredentialDao_Impl.this.__db.beginTransaction();
                try {
                    CredentialDao_Impl.this.__insertionAdapterOfCredentialEncrypted.insert((T[]) credentialEncryptedArr);
                    CredentialDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    CredentialDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object deleteMnemonic(final String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = CredentialDao_Impl.this.__preparedStmtOfDeleteMnemonic.acquire();
                String str = str;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                CredentialDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    CredentialDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    CredentialDao_Impl.this.__db.endTransaction();
                    CredentialDao_Impl.this.__preparedStmtOfDeleteMnemonic.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteZion(final String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = CredentialDao_Impl.this.__preparedStmtOfDeleteZion.acquire();
                String str = str;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                CredentialDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    CredentialDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    CredentialDao_Impl.this.__db.endTransaction();
                    CredentialDao_Impl.this.__preparedStmtOfDeleteZion.release(acquire);
                }
            }
        }, continuation);
    }

    public Object deleteEncrypted(final String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = CredentialDao_Impl.this.__preparedStmtOfDeleteEncrypted.acquire();
                String str = str;
                if (str == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, str);
                }
                CredentialDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    CredentialDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    CredentialDao_Impl.this.__db.endTransaction();
                    CredentialDao_Impl.this.__preparedStmtOfDeleteEncrypted.release(acquire);
                }
            }
        }, continuation);
    }

    public Object selectMnemonic(String str, Continuation<? super CredentialMnemonic> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM credential_mnemonic WHERE id_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<CredentialMnemonic>() {
            public CredentialMnemonic call() throws Exception {
                CredentialMnemonic credentialMnemonic;
                Cursor query = DBUtil.query(CredentialDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mnemonic");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "master_priv_key");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id_id");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "id_type");
                    CredentialId credentialId = null;
                    if (query.moveToFirst()) {
                        EncryptedAtRestList readEncryptedAtRestList = CredentialDao_Impl.this.__roomConverters.readEncryptedAtRestList(query.getString(columnIndexOrThrow));
                        PrivateKey readPrivateKey = CredentialDao_Impl.this.__roomConverters.readPrivateKey(query.getString(columnIndexOrThrow2));
                        if (!query.isNull(columnIndexOrThrow3) || !query.isNull(columnIndexOrThrow4)) {
                            credentialId = new CredentialId(query.getString(columnIndexOrThrow3), CredentialDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow4)));
                        }
                        credentialMnemonic = new CredentialMnemonic(credentialId, readEncryptedAtRestList, readPrivateKey);
                    } else {
                        credentialMnemonic = null;
                    }
                    return credentialMnemonic;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectZion(String str, Continuation<? super CredentialZion> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM credential_zion WHERE id_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<CredentialZion>() {
            public CredentialZion call() throws Exception {
                CredentialZion credentialZion;
                Cursor query = DBUtil.query(CredentialDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "zion_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id_type");
                    CredentialId credentialId = null;
                    if (query.moveToFirst()) {
                        ZionId readZionId = CredentialDao_Impl.this.__roomConverters.readZionId(query.getString(columnIndexOrThrow));
                        if (!query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                            credentialId = new CredentialId(query.getString(columnIndexOrThrow2), CredentialDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow3)));
                        }
                        credentialZion = new CredentialZion(credentialId, readZionId);
                    } else {
                        credentialZion = null;
                    }
                    return credentialZion;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object selectEncrypted(String str, Continuation<? super CredentialEncrypted> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From credential_encrypted WHERE id_id = ? ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<CredentialEncrypted>() {
            public CredentialEncrypted call() throws Exception {
                CredentialEncrypted credentialEncrypted;
                Cursor query = DBUtil.query(CredentialDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "encrypted_mnmeonic");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id_type");
                    CredentialId credentialId = null;
                    if (query.moveToFirst()) {
                        String string = query.getString(columnIndexOrThrow);
                        if (!query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                            credentialId = new CredentialId(query.getString(columnIndexOrThrow2), CredentialDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow3)));
                        }
                        credentialEncrypted = new CredentialEncrypted(credentialId, string);
                    } else {
                        credentialEncrypted = null;
                    }
                    return credentialEncrypted;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public Object findIds(ZionId zionId, Continuation<? super List<String>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id_id FROM credential_zion WHERE zion_id = ?", 1);
        String writeZionId = this.__roomConverters.writeZionId(zionId);
        if (writeZionId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeZionId);
        }
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<String>>() {
            public List<String> call() throws Exception {
                Cursor query = DBUtil.query(CredentialDao_Impl.this.__db, acquire, false);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(query.getString(0));
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
