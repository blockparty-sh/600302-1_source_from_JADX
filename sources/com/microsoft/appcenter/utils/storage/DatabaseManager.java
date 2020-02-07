package com.microsoft.appcenter.utils.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

public class DatabaseManager implements Closeable {
    public static final String PRIMARY_KEY = "oid";
    public static final String[] SELECT_PRIMARY_KEY = {PRIMARY_KEY};
    private final Context mContext;
    private final String mDatabase;
    /* access modifiers changed from: private */
    public final String mDefaultTable;
    /* access modifiers changed from: private */
    public final Listener mListener;
    private SQLiteOpenHelper mSQLiteOpenHelper;
    /* access modifiers changed from: private */
    public final ContentValues mSchema;

    public static class DefaultListener implements Listener {
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        public boolean onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            return false;
        }
    }

    public interface Listener {
        void onCreate(SQLiteDatabase sQLiteDatabase);

        boolean onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
    }

    public DatabaseManager(Context context, String str, String str2, int i, ContentValues contentValues, Listener listener) {
        this(context, str, str2, i, contentValues, listener, null);
    }

    DatabaseManager(Context context, String str, String str2, int i, ContentValues contentValues, Listener listener, String[] strArr) {
        this.mContext = context;
        this.mDatabase = str;
        this.mDefaultTable = str2;
        this.mSchema = contentValues;
        this.mListener = listener;
        final String[] strArr2 = strArr;
        C25361 r0 = new SQLiteOpenHelper(context, str, null, i) {
            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                DatabaseManager databaseManager = DatabaseManager.this;
                databaseManager.createTable(sQLiteDatabase, databaseManager.mDefaultTable, DatabaseManager.this.mSchema, strArr2);
                DatabaseManager.this.mListener.onCreate(sQLiteDatabase);
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                if (!DatabaseManager.this.mListener.onUpgrade(sQLiteDatabase, i, i2)) {
                    SQLiteUtils.dropTable(sQLiteDatabase, DatabaseManager.this.mDefaultTable);
                    onCreate(sQLiteDatabase);
                }
            }
        };
        this.mSQLiteOpenHelper = r0;
    }

    private static ContentValues buildValues(Cursor cursor, ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (!cursor.isNull(i)) {
                String columnName = cursor.getColumnName(i);
                if (columnName.equals(PRIMARY_KEY)) {
                    contentValues2.put(columnName, Long.valueOf(cursor.getLong(i)));
                } else {
                    Object obj = contentValues.get(columnName);
                    if (obj instanceof byte[]) {
                        contentValues2.put(columnName, cursor.getBlob(i));
                    } else if (obj instanceof Double) {
                        contentValues2.put(columnName, Double.valueOf(cursor.getDouble(i)));
                    } else if (obj instanceof Float) {
                        contentValues2.put(columnName, Float.valueOf(cursor.getFloat(i)));
                    } else if (obj instanceof Integer) {
                        contentValues2.put(columnName, Integer.valueOf(cursor.getInt(i)));
                    } else if (obj instanceof Long) {
                        contentValues2.put(columnName, Long.valueOf(cursor.getLong(i)));
                    } else if (obj instanceof Short) {
                        contentValues2.put(columnName, Short.valueOf(cursor.getShort(i)));
                    } else if (obj instanceof Boolean) {
                        boolean z = true;
                        if (cursor.getInt(i) != 1) {
                            z = false;
                        }
                        contentValues2.put(columnName, Boolean.valueOf(z));
                    } else {
                        contentValues2.put(columnName, cursor.getString(i));
                    }
                }
            }
        }
        return contentValues2;
    }

    public void resetDatabase() {
        close();
        this.mContext.deleteDatabase(this.mDatabase);
        getDatabase();
    }

    public void createTable(@NonNull String str, @NonNull ContentValues contentValues, String[] strArr) {
        createTable(getDatabase(), str, contentValues, strArr);
    }

    public ContentValues buildValues(Cursor cursor) {
        return buildValues(cursor, this.mSchema);
    }

    @Nullable
    public ContentValues nextValues(Cursor cursor) {
        try {
            if (cursor.moveToNext()) {
                return buildValues(cursor);
            }
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get next cursor value: ", e);
        }
        return null;
    }

    public long replace(@NonNull String str, @NonNull ContentValues contentValues, String... strArr) {
        Cursor cursor;
        String str2 = PRIMARY_KEY;
        SQLiteQueryBuilder newSQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        ArrayList arrayList = new ArrayList();
        try {
            ArrayList arrayList2 = new ArrayList();
            for (String str3 : strArr) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(" = ?");
                arrayList2.add(sb.toString());
                arrayList.add(contentValues.getAsString(str3));
            }
            newSQLiteQueryBuilder.appendWhere(TextUtils.join(" AND ", arrayList2));
            if (arrayList.size() > 0) {
                cursor = getCursor(str, newSQLiteQueryBuilder, null, (String[]) arrayList.toArray(new String[0]), null);
                ContentValues nextValues = nextValues(cursor);
                if (nextValues != null && !cursor.moveToNext()) {
                    contentValues.put(str2, nextValues.getAsInteger(str2));
                }
                cursor.close();
            }
            return getDatabase().replace(str, null, contentValues);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", String.format("Failed to replace values (%s) from database %s.", new Object[]{contentValues.toString(), this.mDatabase}), e);
            return -1;
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }

    public long put(@NonNull ContentValues contentValues, @NonNull String str) {
        String str2 = "AppCenter";
        Long l = null;
        Cursor cursor = null;
        while (l == null) {
            try {
                l = Long.valueOf(getDatabase().insertOrThrow(this.mDefaultTable, null, contentValues));
            } catch (SQLiteFullException e) {
                AppCenterLog.debug(str2, "Storage is full, trying to delete the oldest log that has the lowest priority which is lower or equal priority than the new log");
                if (cursor == null) {
                    String asString = contentValues.getAsString(str);
                    SQLiteQueryBuilder newSQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(" <= ?");
                    newSQLiteQueryBuilder.appendWhere(sb.toString());
                    String[] strArr = SELECT_PRIMARY_KEY;
                    String[] strArr2 = {asString};
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(" , ");
                    sb2.append(PRIMARY_KEY);
                    cursor = getCursor(newSQLiteQueryBuilder, strArr, strArr2, sb2.toString());
                }
                if (cursor.moveToNext()) {
                    long j = cursor.getLong(0);
                    delete(j);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Deleted log id=");
                    sb3.append(j);
                    AppCenterLog.debug(str2, sb3.toString());
                } else {
                    throw e;
                }
            } catch (RuntimeException e2) {
                l = Long.valueOf(-1);
                AppCenterLog.error(str2, String.format("Failed to insert values (%s) to database %s.", new Object[]{contentValues.toString(), this.mDatabase}), e2);
            }
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (RuntimeException unused) {
            }
        }
        return l.longValue();
    }

    public void delete(@IntRange(from = 0) long j) {
        delete(this.mDefaultTable, j);
    }

    private void delete(@NonNull String str, @IntRange(from = 0) long j) {
        delete(str, PRIMARY_KEY, (Object) Long.valueOf(j));
    }

    public int delete(@NonNull String str, String str2, String[] strArr) {
        try {
            return getDatabase().delete(str, str2, strArr);
        } catch (RuntimeException e) {
            Object[] objArr = {str2, Arrays.toString(strArr), this.mDatabase};
            AppCenterLog.error("AppCenter", String.format("Failed to delete values that match condition=\"%s\" and values=\"%s\" from database %s.", objArr), e);
            return 0;
        }
    }

    public int delete(@Nullable String str, @Nullable Object obj) {
        return delete(this.mDefaultTable, str, obj);
    }

    public int delete(@NonNull String str, @Nullable String str2, @Nullable Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(" = ?");
        return delete(str, sb.toString(), new String[]{String.valueOf(obj)});
    }

    public void clear() {
        try {
            getDatabase().delete(this.mDefaultTable, null, null);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to clear the table.", e);
        }
    }

    public void close() {
        try {
            this.mSQLiteOpenHelper.close();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to close the database.", e);
        }
    }

    public final long getRowCount() {
        try {
            return DatabaseUtils.queryNumEntries(getDatabase(), this.mDefaultTable);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get row count of database.", e);
            return -1;
        }
    }

    public Cursor getCursor(@Nullable SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, @Nullable String[] strArr2, @Nullable String str) throws RuntimeException {
        return getCursor(this.mDefaultTable, sQLiteQueryBuilder, strArr, strArr2, str);
    }

    public Cursor getCursor(@NonNull String str, @Nullable SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, @Nullable String[] strArr2, @Nullable String str2) throws RuntimeException {
        if (sQLiteQueryBuilder == null) {
            sQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        }
        SQLiteQueryBuilder sQLiteQueryBuilder2 = sQLiteQueryBuilder;
        sQLiteQueryBuilder2.setTables(str);
        return sQLiteQueryBuilder2.query(getDatabase(), strArr, null, strArr2, null, null, str2);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public SQLiteDatabase getDatabase() {
        try {
            return this.mSQLiteOpenHelper.getWritableDatabase();
        } catch (RuntimeException e) {
            String str = "AppCenter";
            AppCenterLog.warn(str, "Failed to open database. Trying to delete database (may be corrupted).", e);
            if (this.mContext.deleteDatabase(this.mDatabase)) {
                AppCenterLog.info(str, "The database was successfully deleted.");
            } else {
                AppCenterLog.warn(str, "Failed to delete database.");
            }
            return this.mSQLiteOpenHelper.getWritableDatabase();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void setSQLiteOpenHelper(@NonNull SQLiteOpenHelper sQLiteOpenHelper) {
        this.mSQLiteOpenHelper.close();
        this.mSQLiteOpenHelper = sQLiteOpenHelper;
    }

    /* access modifiers changed from: private */
    public void createTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String[] strArr) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS `");
        sb.append(str);
        sb.append("` (oid INTEGER PRIMARY KEY AUTOINCREMENT");
        for (Entry entry : contentValues.valueSet()) {
            sb.append(", `");
            sb.append((String) entry.getKey());
            sb.append("` ");
            Object value = entry.getValue();
            if ((value instanceof Double) || (value instanceof Float)) {
                sb.append("REAL");
            } else if ((value instanceof Number) || (value instanceof Boolean)) {
                sb.append("INTEGER");
            } else if (value instanceof byte[]) {
                sb.append("BLOB");
            } else {
                sb.append("TEXT");
            }
        }
        if (strArr != null && strArr.length > 0) {
            sb.append(", UNIQUE(`");
            sb.append(TextUtils.join("`, `", strArr));
            sb.append("`)");
        }
        sb.append(");");
        sQLiteDatabase.execSQL(sb.toString());
    }

    public boolean setMaxSize(long j) {
        String str = "AppCenter";
        try {
            SQLiteDatabase database = getDatabase();
            long maximumSize = database.setMaximumSize(j);
            long pageSize = database.getPageSize();
            long j2 = j / pageSize;
            if (j % pageSize != 0) {
                j2++;
            }
            String str2 = " bytes.";
            if (maximumSize != j2 * pageSize) {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not change maximum database size to ");
                sb.append(j);
                sb.append(" bytes, current maximum size is ");
                sb.append(maximumSize);
                sb.append(str2);
                AppCenterLog.error(str, sb.toString());
                return false;
            }
            String str3 = "Changed maximum database size to ";
            if (j == maximumSize) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str3);
                sb2.append(maximumSize);
                sb2.append(str2);
                AppCenterLog.info(str, sb2.toString());
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str3);
                sb3.append(maximumSize);
                sb3.append(" bytes (next multiple of page size).");
                AppCenterLog.info(str, sb3.toString());
            }
            return true;
        } catch (RuntimeException e) {
            AppCenterLog.error(str, "Could not change maximum database size.", e);
            return false;
        }
    }

    public long getMaxSize() {
        try {
            return getDatabase().getMaximumSize();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Could not get maximum database size.", e);
            return -1;
        }
    }
}
