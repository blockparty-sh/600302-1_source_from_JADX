package com.bitcoin.mwallet.core.dao.room;

import androidx.room.migration.Migration;
import androidx.sqlite.p006db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, mo37405d2 = {"com/bitcoin/mwallet/core/dao/room/DatabaseMigrationKt$MIGRATION_30_31$1", "Landroidx/room/migration/Migration;", "migrate", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DatabaseMigration.kt */
public final class DatabaseMigrationKt$MIGRATION_30_31$1 extends Migration {
    DatabaseMigrationKt$MIGRATION_30_31$1(int i, int i2) {
        super(i, i2);
    }

    public void migrate(@NotNull SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkParameterIsNotNull(supportSQLiteDatabase, "database");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `credential_encrypted` (`encrypted_mnmeonic` TEXT NOT NULL, `id_id` TEXT NOT NULL, `id_type` TEXT NOT NULL, PRIMARY KEY(`id_id`, `id_type`))");
    }
}
