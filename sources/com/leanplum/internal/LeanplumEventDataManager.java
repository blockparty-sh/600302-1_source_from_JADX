package com.leanplum.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.leanplum.Leanplum;
import com.leanplum.internal.Constants.Defaults;
import com.leanplum.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class LeanplumEventDataManager {
    private static final String COLUMN_DATA = "data";
    private static final String DATABASE_NAME = "__leanplum.db";
    private static final int DATABASE_VERSION = 1;
    private static final String EVENT_TABLE_NAME = "event";
    private static final String KEY_ROWID = "rowid";
    /* access modifiers changed from: private */
    public static ContentValues contentValues = new ContentValues();
    private static SQLiteDatabase database;
    private static LeanplumDataBaseManager databaseManager;
    static boolean willSendErrorLog = false;

    private static class LeanplumDataBaseManager extends SQLiteOpenHelper {
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        LeanplumDataBaseManager(Context context) {
            super(context, LeanplumEventDataManager.DATABASE_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS event(data TEXT)");
            try {
                migrateFromSharedPreferences(sQLiteDatabase);
            } catch (Throwable th) {
                Log.m280e("Cannot move old data from shared preferences to SQLite table.", th);
                Util.handleException(th);
            }
        }

        private static void migrateFromSharedPreferences(SQLiteDatabase sQLiteDatabase) {
            synchronized (RequestOld.class) {
                SharedPreferences sharedPreferences = Leanplum.getContext().getSharedPreferences(Defaults.LEANPLUM, 0);
                Editor edit = sharedPreferences.edit();
                int i = sharedPreferences.getInt(Defaults.COUNT_KEY, 0);
                if (i != 0) {
                    ArrayList<Map> arrayList = new ArrayList<>();
                    for (int i2 = 0; i2 < i; i2++) {
                        String format = String.format(Locale.US, Defaults.ITEM_KEY, new Object[]{Integer.valueOf(i2)});
                        try {
                            arrayList.add(JsonConverter.mapFromJson(new JSONObject(sharedPreferences.getString(format, "{}"))));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        edit.remove(format);
                    }
                    edit.remove(Defaults.COUNT_KEY);
                    try {
                        String string = sharedPreferences.getString(Defaults.UUID_KEY, null);
                        if (string == null || i % RequestOld.MAX_EVENTS_PER_API_CALL == 0) {
                            string = UUID.randomUUID().toString();
                            edit.putString(Defaults.UUID_KEY, string);
                        }
                        for (Map map : arrayList) {
                            map.put("uuid", string);
                            LeanplumEventDataManager.contentValues.put("data", JsonConverter.toJson(map));
                            sQLiteDatabase.insert("event", null, LeanplumEventDataManager.contentValues);
                            LeanplumEventDataManager.contentValues.clear();
                        }
                        SharedPreferencesUtil.commitChanges(edit);
                    } catch (Throwable th) {
                        Log.m280e("Failed on migration data from shared preferences.", th);
                        Util.handleException(th);
                    }
                }
            }
        }
    }

    public static void init(Context context) {
        if (database != null) {
            Log.m280e("Database is already initialized.");
            return;
        }
        try {
            if (databaseManager == null) {
                databaseManager = new LeanplumDataBaseManager(context);
            }
            database = databaseManager.getWritableDatabase();
        } catch (Throwable th) {
            handleSQLiteError("Cannot create database.", th);
        }
    }

    static void insertEvent(String str) {
        if (database != null) {
            contentValues.put("data", str);
            try {
                database.insert("event", null, contentValues);
                willSendErrorLog = false;
            } catch (Throwable th) {
                handleSQLiteError("Unable to insert event to database.", th);
            }
            contentValues.clear();
            Leanplum.countAggregator().incrementCount("add_event");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        if (r11 == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
        com.leanplum.Leanplum.countAggregator().incrementCount("events_with_limit");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0065, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004c, code lost:
        if (r11 != null) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getEvents(int r13) {
        /*
            java.lang.String r0 = "data"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r2 = database
            if (r2 != 0) goto L_0x000c
            return r1
        L_0x000c:
            r11 = 0
            java.lang.String r3 = "event"
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch:{ Throwable -> 0x0051 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid ASC"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0051 }
            r10.<init>()     // Catch:{ Throwable -> 0x0051 }
            java.lang.String r12 = ""
            r10.append(r12)     // Catch:{ Throwable -> 0x0051 }
            r10.append(r13)     // Catch:{ Throwable -> 0x0051 }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x0051 }
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x0051 }
            r13 = 0
            willSendErrorLog = r13     // Catch:{ Throwable -> 0x0051 }
        L_0x0031:
            boolean r13 = r11.moveToNext()     // Catch:{ Throwable -> 0x0051 }
            if (r13 == 0) goto L_0x004c
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0051 }
            int r2 = r11.getColumnIndex(r0)     // Catch:{ Throwable -> 0x0051 }
            java.lang.String r2 = r11.getString(r2)     // Catch:{ Throwable -> 0x0051 }
            r13.<init>(r2)     // Catch:{ Throwable -> 0x0051 }
            java.util.Map r13 = com.leanplum.internal.JsonConverter.mapFromJson(r13)     // Catch:{ Throwable -> 0x0051 }
            r1.add(r13)     // Catch:{ Throwable -> 0x0051 }
            goto L_0x0031
        L_0x004c:
            if (r11 == 0) goto L_0x005c
            goto L_0x0059
        L_0x004f:
            r13 = move-exception
            goto L_0x0066
        L_0x0051:
            r13 = move-exception
            java.lang.String r0 = "Unable to get events from the table."
            handleSQLiteError(r0, r13)     // Catch:{ all -> 0x004f }
            if (r11 == 0) goto L_0x005c
        L_0x0059:
            r11.close()
        L_0x005c:
            com.leanplum.internal.CountAggregator r13 = com.leanplum.Leanplum.countAggregator()
            java.lang.String r0 = "events_with_limit"
            r13.incrementCount(r0)
            return r1
        L_0x0066:
            if (r11 == 0) goto L_0x006b
            r11.close()
        L_0x006b:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.LeanplumEventDataManager.getEvents(int):java.util.List");
    }

    static void deleteEvents(int i) {
        SQLiteDatabase sQLiteDatabase = database;
        if (sQLiteDatabase != null) {
            String str = "event";
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("rowid in (select rowid from event ORDER BY rowid ASC LIMIT ");
                sb.append(i);
                sb.append(")");
                sQLiteDatabase.delete(str, sb.toString(), null);
                willSendErrorLog = false;
            } catch (Throwable th) {
                handleSQLiteError("Unable to delete events from the table.", th);
            }
            Leanplum.countAggregator().incrementCount("delete_events_with_limit");
        }
    }

    static long getEventsCount() {
        SQLiteDatabase sQLiteDatabase = database;
        long j = 0;
        if (sQLiteDatabase == null) {
            return 0;
        }
        try {
            j = DatabaseUtils.queryNumEntries(sQLiteDatabase, "event");
            willSendErrorLog = false;
        } catch (Throwable th) {
            handleSQLiteError("Unable to get a number of rows in the table.", th);
        }
        return j;
    }

    private static void handleSQLiteError(String str, Throwable th) {
        Log.m280e(str, th);
        if (!willSendErrorLog) {
            willSendErrorLog = true;
            Util.handleException(th);
        }
    }
}
