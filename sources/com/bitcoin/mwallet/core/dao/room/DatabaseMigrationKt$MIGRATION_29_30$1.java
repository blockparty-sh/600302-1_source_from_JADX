package com.bitcoin.mwallet.core.dao.room;

import androidx.room.migration.Migration;
import androidx.sqlite.p006db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, mo37405d2 = {"com/bitcoin/mwallet/core/dao/room/DatabaseMigrationKt$MIGRATION_29_30$1", "Landroidx/room/migration/Migration;", "migrate", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DatabaseMigration.kt */
public final class DatabaseMigrationKt$MIGRATION_29_30$1 extends Migration {
    DatabaseMigrationKt$MIGRATION_29_30$1(int i, int i2) {
        super(i, i2);
    }

    public void migrate(@NotNull SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkParameterIsNotNull(supportSQLiteDatabase, "database");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `slp` (`token_id` TEXT NOT NULL, `ticker` TEXT NOT NULL, `name` TEXT NOT NULL, `wallet_id` TEXT NOT NULL, `decimals` INTEGER NOT NULL, PRIMARY KEY(`token_id`, `wallet_id`))");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS 'slp_utxo' ('token_id' TEXT NOT NULL, 'token_amount' INTEGER NOT NULL, 'token_type' TEXT NOT NULL, 'transaction_type' TEXT NOT NULL, 'wallet_id' TEXT NOT NULL, 'tx_id' TEXT NOT NULL, 'output_index' INTEGER NOT NULL, 'address' TEXT NOT NULL, 'path' TEXT NOT NULL, 'script' BLOB NOT NULL, 'satoshis' INTEGER NOT NULL, 'coin' TEXT NOT NULL, PRIMARY KEY('wallet_id', 'token_id', 'tx_id', 'output_index'))");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `verified_token` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `symbol` TEXT NOT NULL, `decimals` INTEGER NOT NULL, `iconImageUrl` TEXT NOT NULL, PRIMARY KEY(`id`))");
        supportSQLiteDatabase.execSQL("CREATE  INDEX `index_verified_token_id` ON `verified_token` (`id`)");
        supportSQLiteDatabase.execSQL("CREATE  INDEX `index_verified_token_name` ON `verified_token` (`name`)");
        supportSQLiteDatabase.execSQL("CREATE  INDEX `index_verified_token_symbol` ON `verified_token` (`symbol`)");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS 'merchant' ('tx_id' TEXT NOT NULL, 'merchant_name' TEXT NOT NULL, 'description' TEXT NOT NULL, 'email' TEXT NOT NULL, 'phone' TEXT NOT NULL, 'website' TEXT NOT NULL, PRIMARY KEY('tx_id'))");
        supportSQLiteDatabase.execSQL("ALTER TABLE 'tx_history' ADD COLUMN 'token_id' TEXT DEFAULT '' NOT NULL");
        supportSQLiteDatabase.execSQL("ALTER TABLE 'tx_history' ADD COLUMN 'token_amount' INTEGER DEFAULT 0 NOT NULL");
        supportSQLiteDatabase.execSQL("ALTER TABLE 'wallet' ADD COLUMN 'signing_key' TEXT DEFAULT '' NOT NULL");
        supportSQLiteDatabase.execSQL("ALTER TABLE 'contact' ADD COLUMN 'wallet_type' TEXT DEFAULT '' NOT NULL");
    }
}
