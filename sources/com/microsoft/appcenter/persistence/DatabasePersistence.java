package com.microsoft.appcenter.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.crypto.CryptoUtils;
import com.microsoft.appcenter.utils.storage.DatabaseManager;
import com.microsoft.appcenter.utils.storage.DatabaseManager.Listener;
import com.microsoft.appcenter.utils.storage.FileManager;
import com.microsoft.appcenter.utils.storage.SQLiteUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;

public class DatabasePersistence extends Persistence {
    @VisibleForTesting
    static final String COLUMN_DATA_TYPE = "type";
    @VisibleForTesting
    static final String COLUMN_GROUP = "persistence_group";
    @VisibleForTesting
    static final String COLUMN_LOG = "log";
    @VisibleForTesting
    static final String COLUMN_PRIORITY = "priority";
    @VisibleForTesting
    static final String COLUMN_TARGET_KEY = "target_key";
    @VisibleForTesting
    static final String COLUMN_TARGET_TOKEN = "target_token";
    @VisibleForTesting
    static final String COLUMN_TIMESTAMP = "timestamp";
    @VisibleForTesting
    static final String DATABASE = "com.microsoft.appcenter.persistence";
    private static final String GET_SORT_ORDER = "priority DESC, oid";
    private static final String INDEX_PRIORITY = "ix_logs_priority";
    private static final String PAYLOAD_FILE_EXTENSION = ".json";
    private static final String PAYLOAD_LARGE_DIRECTORY = "/appcenter/database_large_payloads";
    private static final int PAYLOAD_MAX_SIZE = 1992294;
    @VisibleForTesting
    static final ContentValues SCHEMA = getContentValues("", "", "", "", "", 0, Long.valueOf(0));
    @VisibleForTesting
    static final String TABLE = "logs";
    private static final int VERSION = 5;
    @VisibleForTesting
    static final int VERSION_PRIORITY_KEY = 4;
    @VisibleForTesting
    static final int VERSION_TARGET_KEY = 3;
    @VisibleForTesting
    static final int VERSION_TYPE_API_KEY = 2;
    private final Context mContext;
    @VisibleForTesting
    final DatabaseManager mDatabaseManager;
    private final File mLargePayloadDirectory;
    @VisibleForTesting
    final Set<Long> mPendingDbIdentifiers;
    @VisibleForTesting
    final Map<String, List<Long>> mPendingDbIdentifiersGroups;

    public DatabasePersistence(Context context) {
        this(context, 5, SCHEMA);
    }

    DatabasePersistence(Context context, int i, ContentValues contentValues) {
        this.mContext = context;
        this.mPendingDbIdentifiersGroups = new HashMap();
        this.mPendingDbIdentifiers = new HashSet();
        Context context2 = context;
        DatabaseManager databaseManager = new DatabaseManager(context2, DATABASE, TABLE, i, contentValues, new Listener() {
            private void createPriorityIndex(SQLiteDatabase sQLiteDatabase) {
                sQLiteDatabase.execSQL("CREATE INDEX `ix_logs_priority` ON logs (`priority`)");
            }

            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                createPriorityIndex(sQLiteDatabase);
            }

            public boolean onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                if (i < 2) {
                    sQLiteDatabase.execSQL("ALTER TABLE logs ADD COLUMN `target_token` TEXT");
                    sQLiteDatabase.execSQL("ALTER TABLE logs ADD COLUMN `type` TEXT");
                }
                if (i < 3) {
                    sQLiteDatabase.execSQL("ALTER TABLE logs ADD COLUMN `target_key` TEXT");
                }
                if (i < 4) {
                    sQLiteDatabase.execSQL("ALTER TABLE logs ADD COLUMN `priority` INTEGER DEFAULT 1");
                }
                sQLiteDatabase.execSQL("ALTER TABLE logs ADD COLUMN `timestamp` INTEGER DEFAULT 0");
                createPriorityIndex(sQLiteDatabase);
                return true;
            }
        });
        this.mDatabaseManager = databaseManager;
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.FILES_PATH);
        sb.append(PAYLOAD_LARGE_DIRECTORY);
        this.mLargePayloadDirectory = new File(sb.toString());
        this.mLargePayloadDirectory.mkdirs();
    }

    private static ContentValues getContentValues(@Nullable String str, @Nullable String str2, String str3, String str4, String str5, int i, Long l) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GROUP, str);
        contentValues.put("log", str2);
        contentValues.put(COLUMN_TARGET_TOKEN, str3);
        contentValues.put("type", str4);
        contentValues.put(COLUMN_TARGET_KEY, str5);
        contentValues.put(COLUMN_PRIORITY, Integer.valueOf(i));
        contentValues.put("timestamp", l);
        return contentValues;
    }

    public boolean setMaxStorageSize(long j) {
        return this.mDatabaseManager.setMaxSize(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0156, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0167, code lost:
        throw new com.microsoft.appcenter.persistence.Persistence.PersistenceException("Cannot convert to JSON string.", r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0156 A[ExcHandler: JSONException (r0v1 'e' org.json.JSONException A[CUSTOM_DECLARE]), Splitter:B:1:0x0008] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long putLog(@androidx.annotation.NonNull com.microsoft.appcenter.ingestion.models.Log r17, @androidx.annotation.NonNull java.lang.String r18, @androidx.annotation.IntRange(from = 1, mo644to = 2) int r19) throws com.microsoft.appcenter.persistence.Persistence.PersistenceException {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r19
            java.lang.String r3 = "AppCenter"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r4.<init>()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r5 = "Storing a log to the Persistence database for log type "
            r4.append(r5)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r5 = r17.getType()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r4.append(r5)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r5 = " with flags="
            r4.append(r5)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r4.append(r2)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r4)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            com.microsoft.appcenter.ingestion.models.json.LogSerializer r4 = r16.getLogSerializer()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r4 = r4.serializeLog(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r5 = "UTF-8"
            byte[] r5 = r4.getBytes(r5)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            int r5 = r5.length     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r6 = 1992294(0x1e6666, float:2.791799E-39)
            r7 = 0
            if (r5 < r6) goto L_0x003f
            r6 = 1
            goto L_0x0040
        L_0x003f:
            r6 = 0
        L_0x0040:
            boolean r8 = r0 instanceof com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r9 = 0
            if (r8 == 0) goto L_0x006e
            if (r6 != 0) goto L_0x0066
            java.util.Set r8 = r17.getTransmissionTargetTokens()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.Object r8 = r8.next()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r10 = com.microsoft.appcenter.ingestion.models.one.PartAUtils.getTargetKey(r8)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            android.content.Context r11 = r1.mContext     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            com.microsoft.appcenter.utils.crypto.CryptoUtils r11 = com.microsoft.appcenter.utils.crypto.CryptoUtils.getInstance(r11)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r8 = r11.encrypt(r8)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r11 = r10
            r10 = r8
            goto L_0x0070
        L_0x0066:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r2 = "Log is larger than 1992294 bytes, cannot send to OneCollector."
            r0.<init>(r2)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            throw r0     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
        L_0x006e:
            r10 = r9
            r11 = r10
        L_0x0070:
            com.microsoft.appcenter.utils.storage.DatabaseManager r8 = r1.mDatabaseManager     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            long r12 = r8.getMaxSize()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r14 = -1
            int r8 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r8 == 0) goto L_0x014c
            if (r6 != 0) goto L_0x00a8
            long r14 = (long) r5     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            int r8 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r8 <= 0) goto L_0x0084
            goto L_0x00a8
        L_0x0084:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.<init>()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r3 = "Log is too large ("
            r2.append(r3)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.append(r5)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r3 = " bytes) to store in database. Current maximum database size is "
            r2.append(r3)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.append(r12)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r3 = " bytes."
            r2.append(r3)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            throw r0     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
        L_0x00a8:
            if (r6 == 0) goto L_0x00ac
            r8 = r9
            goto L_0x00ad
        L_0x00ac:
            r8 = r4
        L_0x00ad:
            java.lang.String r5 = r17.getType()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            int r12 = com.microsoft.appcenter.Flags.getPersistenceFlag(r2, r7)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.util.Date r2 = r17.getTimestamp()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            long r13 = r2.getTime()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r7 = r18
            r9 = r10
            r10 = r5
            android.content.ContentValues r2 = getContentValues(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            com.microsoft.appcenter.utils.storage.DatabaseManager r5 = r1.mDatabaseManager     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r7 = "priority"
            long r7 = r5.put(r2, r7)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r9 = -1
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 == 0) goto L_0x012c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.<init>()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r5 = "Stored a log to the Persistence database for log type "
            r2.append(r5)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r0 = r17.getType()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.append(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r0 = " with databaseId="
            r2.append(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.append(r7)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r0 = r2.toString()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            if (r6 == 0) goto L_0x012b
            java.lang.String r0 = "Payload is larger than what SQLite supports, storing payload in a separate file."
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r0 = r18
            java.io.File r0 = r1.getLargePayloadGroupDirectory(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r0.mkdir()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.io.File r0 = r1.getLargePayloadFile(r0, r7)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            com.microsoft.appcenter.utils.storage.FileManager.write(r0, r4)     // Catch:{ IOException -> 0x0123, JSONException -> 0x0156 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.<init>()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r4 = "Payload written to "
            r2.append(r4)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.append(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r0 = r2.toString()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            goto L_0x012b
        L_0x0123:
            r0 = move-exception
            r2 = r0
            com.microsoft.appcenter.utils.storage.DatabaseManager r0 = r1.mDatabaseManager     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r0.delete(r7)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            throw r2     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
        L_0x012b:
            return r7
        L_0x012c:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r2 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r3.<init>()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r4 = "Failed to store a log to the Persistence database for log type "
            r3.append(r4)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r0 = r17.getType()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r3.append(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r0 = "."
            r3.append(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            r2.<init>(r0)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            throw r2     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
        L_0x014c:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            java.lang.String r2 = "Failed to store a log to the Persistence database."
            r0.<init>(r2)     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
            throw r0     // Catch:{ JSONException -> 0x0156, IOException -> 0x0154 }
        L_0x0154:
            r0 = move-exception
            goto L_0x0158
        L_0x0156:
            r0 = move-exception
            goto L_0x0160
        L_0x0158:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r2 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException
            java.lang.String r3 = "Cannot save large payload in a file."
            r2.<init>(r3, r0)
            throw r2
        L_0x0160:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r2 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException
            java.lang.String r3 = "Cannot convert to JSON string."
            r2.<init>(r3, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.persistence.DatabasePersistence.putLog(com.microsoft.appcenter.ingestion.models.Log, java.lang.String, int):long");
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    @NonNull
    public File getLargePayloadGroupDirectory(String str) {
        return new File(this.mLargePayloadDirectory, str);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    @NonNull
    public File getLargePayloadFile(File file, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append(".json");
        return new File(file, sb.toString());
    }

    private void deleteLog(File file, long j) {
        getLargePayloadFile(file, j).delete();
        this.mDatabaseManager.delete(j);
    }

    public void deleteLogs(@NonNull String str, @NonNull String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleting logs from the Persistence database for ");
        sb.append(str);
        sb.append(" with ");
        sb.append(str2);
        String str3 = "AppCenter";
        AppCenterLog.debug(str3, sb.toString());
        AppCenterLog.debug(str3, "The IDs for deleting log(s) is/are:");
        Map<String, List<Long>> map = this.mPendingDbIdentifiersGroups;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(str2);
        List<Long> list = (List) map.remove(sb2.toString());
        File largePayloadGroupDirectory = getLargePayloadGroupDirectory(str);
        if (list != null) {
            for (Long l : list) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("\t");
                sb3.append(l);
                AppCenterLog.debug(str3, sb3.toString());
                deleteLog(largePayloadGroupDirectory, l.longValue());
                this.mPendingDbIdentifiers.remove(l);
            }
        }
    }

    public void deleteLogs(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleting all logs from the Persistence database for ");
        sb.append(str);
        String str2 = "AppCenter";
        AppCenterLog.debug(str2, sb.toString());
        File largePayloadGroupDirectory = getLargePayloadGroupDirectory(str);
        File[] listFiles = largePayloadGroupDirectory.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
        largePayloadGroupDirectory.delete();
        int delete2 = this.mDatabaseManager.delete(COLUMN_GROUP, (Object) str);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Deleted ");
        sb2.append(delete2);
        sb2.append(" logs.");
        AppCenterLog.debug(str2, sb2.toString());
        Iterator it = this.mPendingDbIdentifiersGroups.keySet().iterator();
        while (it.hasNext()) {
            if (((String) it.next()).startsWith(str)) {
                it.remove();
            }
        }
    }

    public int countLogs(@NonNull String str) {
        return countLogs("persistence_group = ?", str);
    }

    public int countLogs(@NonNull Date date) {
        return countLogs("timestamp < ?", String.valueOf(date.getTime()));
    }

    private int countLogs(String str, String... strArr) {
        Cursor cursor;
        SQLiteQueryBuilder newSQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        newSQLiteQueryBuilder.appendWhere(str);
        int i = 0;
        try {
            cursor = this.mDatabaseManager.getCursor(newSQLiteQueryBuilder, new String[]{"COUNT(*)"}, strArr, null);
            cursor.moveToNext();
            i = cursor.getInt(0);
            cursor.close();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get logs count: ", e);
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
        return i;
    }

    @Nullable
    public String getLogs(@NonNull String str, @NonNull Collection<String> collection, @IntRange(from = 0) int i, @NonNull List<Log> list, @Nullable Date date, @Nullable Date date2) {
        Cursor cursor;
        Cursor cursor2;
        String str2 = str;
        int i2 = i;
        StringBuilder sb = new StringBuilder();
        sb.append("Trying to get ");
        sb.append(i2);
        sb.append(" logs from the Persistence database for ");
        sb.append(str2);
        String str3 = "AppCenter";
        AppCenterLog.debug(str3, sb.toString());
        SQLiteQueryBuilder newSQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        newSQLiteQueryBuilder.appendWhere("persistence_group = ?");
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        String str4 = " AND ";
        if (!collection.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            for (int i3 = 0; i3 < collection.size(); i3++) {
                sb2.append("?,");
            }
            sb2.deleteCharAt(sb2.length() - 1);
            newSQLiteQueryBuilder.appendWhere(str4);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("target_key NOT IN (");
            sb3.append(sb2.toString());
            sb3.append(")");
            newSQLiteQueryBuilder.appendWhere(sb3.toString());
            arrayList.addAll(collection);
        }
        if (date != null) {
            newSQLiteQueryBuilder.appendWhere(str4);
            newSQLiteQueryBuilder.appendWhere("timestamp >= ?");
            arrayList.add(String.valueOf(date.getTime()));
        }
        if (date2 != null) {
            newSQLiteQueryBuilder.appendWhere(str4);
            newSQLiteQueryBuilder.appendWhere("timestamp < ?");
            arrayList.add(String.valueOf(date2.getTime()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<Long> arrayList2 = new ArrayList<>();
        File largePayloadGroupDirectory = getLargePayloadGroupDirectory(str);
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        try {
            cursor = this.mDatabaseManager.getCursor(newSQLiteQueryBuilder, null, strArr, GET_SORT_ORDER);
        } catch (RuntimeException e) {
            AppCenterLog.error(str3, "Failed to get logs: ", e);
            cursor = null;
        }
        int i4 = 0;
        while (cursor != null) {
            ContentValues nextValues = this.mDatabaseManager.nextValues(cursor);
            if (nextValues == null || i4 >= i2) {
                break;
            }
            Long asLong = nextValues.getAsLong(DatabaseManager.PRIMARY_KEY);
            if (asLong == null) {
                AppCenterLog.error(str3, "Empty database record, probably content was larger than 2MB, need to delete as it's now corrupted.");
                Iterator it = getLogsIds(newSQLiteQueryBuilder, strArr).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        cursor2 = cursor;
                        break;
                    }
                    Long l = (Long) it.next();
                    if (!this.mPendingDbIdentifiers.contains(l) && !linkedHashMap.containsKey(l)) {
                        cursor2 = cursor;
                        deleteLog(largePayloadGroupDirectory, l.longValue());
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Empty database corrupted empty record deleted, id=");
                        sb4.append(l);
                        AppCenterLog.error(str3, sb4.toString());
                        break;
                    }
                    cursor = cursor;
                }
            } else {
                cursor2 = cursor;
                if (!this.mPendingDbIdentifiers.contains(asLong)) {
                    try {
                        String asString = nextValues.getAsString("log");
                        if (asString == null) {
                            File largePayloadFile = getLargePayloadFile(largePayloadGroupDirectory, asLong.longValue());
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Read payload file ");
                            sb5.append(largePayloadFile);
                            AppCenterLog.debug(str3, sb5.toString());
                            asString = FileManager.read(largePayloadFile);
                            if (asString == null) {
                                throw new JSONException("Log payload is null and not stored as a file.");
                            }
                        }
                        Log deserializeLog = getLogSerializer().deserializeLog(asString, nextValues.getAsString("type"));
                        String asString2 = nextValues.getAsString(COLUMN_TARGET_TOKEN);
                        if (asString2 != null) {
                            deserializeLog.addTransmissionTarget(CryptoUtils.getInstance(this.mContext).decrypt(asString2, false).getDecryptedData());
                        }
                        linkedHashMap.put(asLong, deserializeLog);
                        i4++;
                    } catch (JSONException e2) {
                        AppCenterLog.error(str3, "Cannot deserialize a log in the database", e2);
                        arrayList2.add(asLong);
                    }
                } else {
                    continue;
                }
            }
            cursor = cursor2;
        }
        Cursor cursor3 = cursor;
        if (cursor3 != null) {
            try {
                cursor3.close();
            } catch (RuntimeException unused) {
            }
        }
        if (arrayList2.size() > 0) {
            for (Long longValue : arrayList2) {
                deleteLog(largePayloadGroupDirectory, longValue.longValue());
            }
            AppCenterLog.warn(str3, "Deleted logs that cannot be deserialized");
        }
        if (linkedHashMap.size() <= 0) {
            AppCenterLog.debug(str3, "No logs found in the Persistence database at the moment");
            return null;
        }
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb6 = new StringBuilder();
        sb6.append("Returning ");
        sb6.append(linkedHashMap.size());
        sb6.append(" log(s) with an ID, ");
        sb6.append(uuid);
        AppCenterLog.debug(str3, sb6.toString());
        AppCenterLog.debug(str3, "The SID/ID pairs for returning log(s) is/are:");
        ArrayList arrayList3 = new ArrayList();
        for (Entry entry : linkedHashMap.entrySet()) {
            Long l2 = (Long) entry.getKey();
            this.mPendingDbIdentifiers.add(l2);
            arrayList3.add(l2);
            list.add(entry.getValue());
            StringBuilder sb7 = new StringBuilder();
            sb7.append("\t");
            sb7.append(((Log) entry.getValue()).getSid());
            sb7.append(" / ");
            sb7.append(l2);
            AppCenterLog.debug(str3, sb7.toString());
        }
        Map<String, List<Long>> map = this.mPendingDbIdentifiersGroups;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(str2);
        sb8.append(uuid);
        map.put(sb8.toString(), arrayList3);
        return uuid;
    }

    public void clearPendingLogState() {
        this.mPendingDbIdentifiers.clear();
        this.mPendingDbIdentifiersGroups.clear();
        AppCenterLog.debug("AppCenter", "Cleared pending log states");
    }

    public void close() {
        this.mDatabaseManager.close();
    }

    private List<Long> getLogsIds(SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr) {
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        try {
            cursor = this.mDatabaseManager.getCursor(sQLiteQueryBuilder, DatabaseManager.SELECT_PRIMARY_KEY, strArr, null);
            while (cursor.moveToNext()) {
                arrayList.add(this.mDatabaseManager.buildValues(cursor).getAsLong(DatabaseManager.PRIMARY_KEY));
            }
            cursor.close();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get corrupted ids: ", e);
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
        return arrayList;
    }
}
