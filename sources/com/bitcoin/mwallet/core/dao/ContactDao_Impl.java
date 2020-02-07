package com.bitcoin.mwallet.core.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p006db.SupportSQLiteStatement;
import com.bitcoin.mwallet.core.dao.room.RoomConverters;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.models.contact.ContactId;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

public final class ContactDao_Impl implements ContactDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfContact;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter __insertionAdapterOfContact;
    /* access modifiers changed from: private */
    public final RoomConverters __roomConverters = new RoomConverters();

    public ContactDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfContact = new EntityInsertionAdapter<Contact>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `contact`(`id`,`name`,`email`,`description`,`coin`,`wallet_type`,`address`,`website`,`editable`,`known_address`,`always_shown`,`always_hidden`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Contact contact) {
                String writeContactId = ContactDao_Impl.this.__roomConverters.writeContactId(contact.getId());
                if (writeContactId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeContactId);
                }
                if (contact.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, contact.getName());
                }
                if (contact.getEmail() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, contact.getEmail());
                }
                if (contact.getContactDescription() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, contact.getContactDescription());
                }
                String writeCoin = ContactDao_Impl.this.__roomConverters.writeCoin(contact.getCoin());
                if (writeCoin == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, writeCoin);
                }
                if (contact.getWalletType() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, contact.getWalletType());
                }
                if (contact.getAddress() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, contact.getAddress());
                }
                if (contact.getWebsite() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, contact.getWebsite());
                }
                supportSQLiteStatement.bindLong(9, contact.isEditable() ? 1 : 0);
                supportSQLiteStatement.bindLong(10, contact.isKnownAddress() ? 1 : 0);
                supportSQLiteStatement.bindLong(11, contact.isAlwaysShown() ? 1 : 0);
                supportSQLiteStatement.bindLong(12, contact.isAlwaysHidden() ? 1 : 0);
            }
        };
        this.__deletionAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `contact` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Contact contact) {
                String writeContactId = ContactDao_Impl.this.__roomConverters.writeContactId(contact.getId());
                if (writeContactId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, writeContactId);
                }
            }
        };
    }

    public Object upsert(final Contact[] contactArr, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() {
            public Unit call() throws Exception {
                ContactDao_Impl.this.__db.beginTransaction();
                try {
                    ContactDao_Impl.this.__insertionAdapterOfContact.insert((T[]) contactArr);
                    ContactDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    ContactDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public void insertContact(Contact... contactArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfContact.insert((T[]) contactArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteContact(Contact... contactArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfContact.handleMultiple((T[]) contactArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public LiveData<List<Contact>> selectAll() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM contact", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"contact"}, false, new Callable<List<Contact>>() {
            public List<Contact> call() throws Exception {
                Cursor query = DBUtil.query(ContactDao_Impl.this.__db, acquire, false);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "email");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "description");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "coin");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "wallet_type");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "address");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "website");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "editable");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "known_address");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "always_shown");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "always_hidden");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i = columnIndexOrThrow;
                        Contact contact = new Contact(ContactDao_Impl.this.__roomConverters.readContactId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), ContactDao_Impl.this.__roomConverters.readCoin(query.getString(columnIndexOrThrow5)), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8), query.getInt(columnIndexOrThrow9) != 0, query.getInt(columnIndexOrThrow10) != 0, query.getInt(columnIndexOrThrow11) != 0, query.getInt(columnIndexOrThrow12) != 0);
                        arrayList.add(contact);
                        columnIndexOrThrow = i;
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

    public Contact contactById(ContactId contactId) {
        Contact contact;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM contact WHERE id = ?", 1);
        String writeContactId = this.__roomConverters.writeContactId(contactId);
        if (writeContactId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, writeContactId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "email");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "description");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "coin");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "wallet_type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "address");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "website");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "editable");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "known_address");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "always_shown");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "always_hidden");
            if (query.moveToFirst()) {
                contact = new Contact(this.__roomConverters.readContactId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), this.__roomConverters.readCoin(query.getString(columnIndexOrThrow5)), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8), query.getInt(columnIndexOrThrow9) != 0, query.getInt(columnIndexOrThrow10) != 0, query.getInt(columnIndexOrThrow11) != 0, query.getInt(columnIndexOrThrow12) != 0);
            } else {
                contact = null;
            }
            return contact;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public Contact contactByAddressAndWalletType(String str, String str2) {
        Contact contact;
        String str3 = str;
        String str4 = str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM contact WHERE address = ? AND wallet_type = ?", 2);
        if (str3 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str3);
        }
        if (str4 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str4);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, CommonProperties.f657ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "email");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "description");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "coin");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "wallet_type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "address");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "website");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "editable");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "known_address");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "always_shown");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "always_hidden");
            if (query.moveToFirst()) {
                contact = new Contact(this.__roomConverters.readContactId(query.getString(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), this.__roomConverters.readCoin(query.getString(columnIndexOrThrow5)), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8), query.getInt(columnIndexOrThrow9) != 0, query.getInt(columnIndexOrThrow10) != 0, query.getInt(columnIndexOrThrow11) != 0, query.getInt(columnIndexOrThrow12) != 0);
            } else {
                contact = null;
            }
            return contact;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
