package com.bitcoin.mwallet.core.dao.room;

import androidx.room.migration.Migration;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003¨\u0006\b"}, mo37405d2 = {"MIGRATION_29_30", "Landroidx/room/migration/Migration;", "getMIGRATION_29_30", "()Landroidx/room/migration/Migration;", "MIGRATION_30_31", "getMIGRATION_30_31", "MIGRATION_31_32", "getMIGRATION_31_32", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: DatabaseMigration.kt */
public final class DatabaseMigrationKt {
    @NotNull
    private static final Migration MIGRATION_29_30 = new DatabaseMigrationKt$MIGRATION_29_30$1(29, 30);
    @NotNull
    private static final Migration MIGRATION_30_31 = new DatabaseMigrationKt$MIGRATION_30_31$1(30, 31);
    @NotNull
    private static final Migration MIGRATION_31_32 = new DatabaseMigrationKt$MIGRATION_31_32$1(31, 32);

    @NotNull
    public static final Migration getMIGRATION_29_30() {
        return MIGRATION_29_30;
    }

    @NotNull
    public static final Migration getMIGRATION_30_31() {
        return MIGRATION_30_31;
    }

    @NotNull
    public static final Migration getMIGRATION_31_32() {
        return MIGRATION_31_32;
    }
}
