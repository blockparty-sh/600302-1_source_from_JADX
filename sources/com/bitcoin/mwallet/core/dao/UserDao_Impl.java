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
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.user.User;
import com.bitcoin.mwallet.core.models.user.UserId;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class UserDao_Impl implements UserDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfUser;
    /* access modifiers changed from: private */
    public final SharedSQLiteStatement __preparedStmtOfRemoveUser;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public UserDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `user`(`id`,`credential_id`,`credential_type`) VALUES (?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, User user) {
                String writeUserId = UserDao_Impl.this.__roomConverters.writeUserId(user.getId());
                if (writeUserId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeUserId);
                }
                CredentialId credentialId = user.getCredentialId();
                if (credentialId != null) {
                    if (credentialId.getId() == null) {
                        supportSQLiteStatement.bindNull(2);
                    } else {
                        supportSQLiteStatement.bindString(2, credentialId.getId());
                    }
                    String writeCredentialType = UserDao_Impl.this.__roomConverters.writeCredentialType(credentialId.getType());
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
        this.__preparedStmtOfRemoveUser = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "Delete FROM user WHERE id = ?";
            }
        };
    }

    public Object upsert(final User[] userArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                UserDao_Impl.this.__db.beginTransaction();
                try {
                    UserDao_Impl.this.__insertionAdapterOfUser.insert((T[]) userArr);
                    UserDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    UserDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public Object removeUser(final UserId userId, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = UserDao_Impl.this.__preparedStmtOfRemoveUser.acquire();
                String writeUserId = UserDao_Impl.this.__roomConverters.writeUserId(userId);
                if (writeUserId == null) {
                    acquire.bindNull(1);
                } else {
                    acquire.bindString(1, writeUserId);
                }
                UserDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    UserDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    UserDao_Impl.this.__db.endTransaction();
                    UserDao_Impl.this.__preparedStmtOfRemoveUser.release(acquire);
                }
            }
        }, continuation);
    }

    public Object getUser(Continuation<? super User> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM user LIMIT 1", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<User>() {
            public User call() throws Exception {
                User user;
                Cursor query = DBUtil.query(UserDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
                    CredentialId credentialId = null;
                    if (query.moveToFirst()) {
                        UserId readUserId = UserDao_Impl.this.__roomConverters.readUserId(query.getString(columnIndexOrThrow));
                        if (!query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                            credentialId = new CredentialId(query.getString(columnIndexOrThrow2), UserDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow3)));
                        }
                        user = new User(readUserId, credentialId);
                    } else {
                        user = null;
                    }
                    return user;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    public LiveData<User> getAllUsers() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM user LIMIT 1", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"user"}, false, new Callable<User>() {
            public User call() throws Exception {
                User user;
                Cursor query = DBUtil.query(UserDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "credential_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "credential_type");
                    CredentialId credentialId = null;
                    if (query.moveToFirst()) {
                        UserId readUserId = UserDao_Impl.this.__roomConverters.readUserId(query.getString(columnIndexOrThrow));
                        if (!query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                            credentialId = new CredentialId(query.getString(columnIndexOrThrow2), UserDao_Impl.this.__roomConverters.readCredentialType(query.getString(columnIndexOrThrow3)));
                        }
                        user = new User(readUserId, credentialId);
                    } else {
                        user = null;
                    }
                    return user;
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
}
