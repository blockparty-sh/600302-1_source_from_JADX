package com.microsoft.appcenter.utils.storage;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import androidx.annotation.NonNull;

public class SQLiteUtils {
    @NonNull
    public static SQLiteQueryBuilder newSQLiteQueryBuilder() {
        return new SQLiteQueryBuilder();
    }

    public static void dropTable(@NonNull SQLiteDatabase sQLiteDatabase, @NonNull String str) {
        sQLiteDatabase.execSQL(formatDropTableQuery(str));
    }

    @NonNull
    public static String formatDropTableQuery(@NonNull String str) {
        return String.format("DROP TABLE `%s`", new Object[]{str});
    }
}
