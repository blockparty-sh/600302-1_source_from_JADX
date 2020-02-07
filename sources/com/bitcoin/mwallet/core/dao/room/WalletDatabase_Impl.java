package com.bitcoin.mwallet.core.dao.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase.Callback;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.p006db.SupportSQLiteDatabase;
import androidx.sqlite.p006db.SupportSQLiteOpenHelper;
import androidx.sqlite.p006db.SupportSQLiteOpenHelper.Configuration;
import com.bitcoin.mwallet.core.dao.ContactDao;
import com.bitcoin.mwallet.core.dao.ContactDao_Impl;
import com.bitcoin.mwallet.core.dao.CredentialDao;
import com.bitcoin.mwallet.core.dao.CredentialDao_Impl;
import com.bitcoin.mwallet.core.dao.ExchangeRateDao;
import com.bitcoin.mwallet.core.dao.ExchangeRateDao_Impl;
import com.bitcoin.mwallet.core.dao.MerchantDao;
import com.bitcoin.mwallet.core.dao.MerchantDao_Impl;
import com.bitcoin.mwallet.core.dao.NetworkFeeDao;
import com.bitcoin.mwallet.core.dao.NetworkFeeDao_Impl;
import com.bitcoin.mwallet.core.dao.SlpDao;
import com.bitcoin.mwallet.core.dao.SlpDao_Impl;
import com.bitcoin.mwallet.core.dao.SlpUtxoDao;
import com.bitcoin.mwallet.core.dao.SlpUtxoDao_Impl;
import com.bitcoin.mwallet.core.dao.SpentUtxoDao;
import com.bitcoin.mwallet.core.dao.SpentUtxoDao_Impl;
import com.bitcoin.mwallet.core.dao.TransactionHistoryDao;
import com.bitcoin.mwallet.core.dao.TransactionHistoryDao_Impl;
import com.bitcoin.mwallet.core.dao.UserDao;
import com.bitcoin.mwallet.core.dao.UserDao_Impl;
import com.bitcoin.mwallet.core.dao.UtxoDao;
import com.bitcoin.mwallet.core.dao.UtxoDao_Impl;
import com.bitcoin.mwallet.core.dao.VerifiedTokenDao;
import com.bitcoin.mwallet.core.dao.VerifiedTokenDao_Impl;
import com.bitcoin.mwallet.core.dao.WalletAddressInfoDao;
import com.bitcoin.mwallet.core.dao.WalletAddressInfoDao_Impl;
import com.bitcoin.mwallet.core.dao.WalletDao;
import com.bitcoin.mwallet.core.dao.WalletDao_Impl;
import com.bitcoin.mwallet.core.dao.ZionNameDao;
import com.bitcoin.mwallet.core.dao.ZionNameDao_Impl;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public final class WalletDatabase_Impl extends WalletDatabase {
    private volatile ContactDao _contactDao;
    private volatile CredentialDao _credentialDao;
    private volatile ExchangeRateDao _exchangeRateDao;
    private volatile MerchantDao _merchantDao;
    private volatile NetworkFeeDao _networkFeeDao;
    private volatile SlpDao _slpDao;
    private volatile SlpUtxoDao _slpUtxoDao;
    private volatile SpentUtxoDao _spentUtxoDao;
    private volatile TransactionHistoryDao _transactionHistoryDao;
    private volatile UserDao _userDao;
    private volatile UtxoDao _utxoDao;
    private volatile VerifiedTokenDao _verifiedTokenDao;
    private volatile WalletAddressInfoDao _walletAddressInfoDao;
    private volatile WalletDao _walletDao;
    private volatile ZionNameDao _zionNameDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new Delegate(32) {
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `credential_mnemonic` (`mnemonic` TEXT NOT NULL, `master_priv_key` TEXT NOT NULL, `id_id` TEXT NOT NULL, `id_type` TEXT NOT NULL, PRIMARY KEY(`id_id`, `id_type`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `credential_encrypted` (`encrypted_mnmeonic` TEXT NOT NULL, `id_id` TEXT NOT NULL, `id_type` TEXT NOT NULL, PRIMARY KEY(`id_id`, `id_type`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `credential_zion` (`zion_id` TEXT NOT NULL, `id_id` TEXT NOT NULL, `id_type` TEXT NOT NULL, PRIMARY KEY(`id_id`, `id_type`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `user` (`id` TEXT NOT NULL, `credential_id` TEXT NOT NULL, `credential_type` TEXT NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `exchange_rate` (`from_ticker` TEXT NOT NULL, `to_ticker` TEXT NOT NULL, `rate` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`from_ticker`, `to_ticker`))");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_exchange_rate_from_ticker` ON `exchange_rate` (`from_ticker`)");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_exchange_rate_to_ticker` ON `exchange_rate` (`to_ticker`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `utxo` (`wallet_id` TEXT NOT NULL, `tx_id` TEXT NOT NULL, `output_index` INTEGER NOT NULL, `address` TEXT NOT NULL, `path` TEXT NOT NULL, `script` BLOB NOT NULL, `satoshis` INTEGER NOT NULL, `coin` TEXT NOT NULL, PRIMARY KEY(`wallet_id`, `tx_id`, `output_index`))");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_utxo_wallet_id` ON `utxo` (`wallet_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `network_fee` (`coin` TEXT NOT NULL, `fee_level` TEXT NOT NULL, `satoshis_per_byte` TEXT NOT NULL, PRIMARY KEY(`coin`, `fee_level`))");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_network_fee_coin` ON `network_fee` (`coin`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `wallet` (`pri_key` TEXT NOT NULL, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `coin` TEXT NOT NULL, `network` TEXT NOT NULL, `path` TEXT NOT NULL, `public_key` TEXT NOT NULL, `signing_key` TEXT NOT NULL, `credential_id` TEXT NOT NULL, `credential_type` TEXT NOT NULL, `copayers_wallet_copayer_id` TEXT NOT NULL, `copayers_wallet_copayer_names` TEXT NOT NULL, `copayers_num_copayers` INTEGER NOT NULL, `copayers_required_signatures` INTEGER NOT NULL, `copayers_copayer_names` TEXT NOT NULL, PRIMARY KEY(`pri_key`))");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_wallet_coin` ON `wallet` (`coin`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `merchant` (`tx_id` TEXT NOT NULL, `merchant_name` TEXT NOT NULL, `description` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `website` TEXT NOT NULL, PRIMARY KEY(`tx_id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `slp` (`token_id` TEXT NOT NULL, `ticker` TEXT NOT NULL, `name` TEXT NOT NULL, `wallet_id` TEXT NOT NULL, `decimals` INTEGER NOT NULL, PRIMARY KEY(`token_id`, `wallet_id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `wallet_address_info` (`wallet_id` TEXT NOT NULL, `path_x` INTEGER NOT NULL, `path_y_next` INTEGER NOT NULL, `path_y_max` INTEGER NOT NULL, PRIMARY KEY(`wallet_id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `tx_history` (`tx_id` TEXT NOT NULL, `wallet_id` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `confirmations` INTEGER NOT NULL, `satoshis` INTEGER NOT NULL, `action` TEXT NOT NULL, `coin` TEXT NOT NULL, `to_address` TEXT NOT NULL, `note` TEXT, `fees` INTEGER NOT NULL, `token_id` TEXT NOT NULL, `token_amount` INTEGER NOT NULL, PRIMARY KEY(`tx_id`, `wallet_id`))");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_tx_history_wallet_id` ON `tx_history` (`wallet_id`)");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_tx_history_timestamp` ON `tx_history` (`timestamp`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `contact` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `description` TEXT NOT NULL, `coin` TEXT NOT NULL, `wallet_type` TEXT NOT NULL, `address` TEXT NOT NULL, `website` TEXT NOT NULL, `editable` INTEGER NOT NULL, `known_address` INTEGER NOT NULL, `always_shown` INTEGER NOT NULL, `always_hidden` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `zion_name` (`index` INTEGER NOT NULL, `zion_id` TEXT, PRIMARY KEY(`index`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `slp_utxo` (`token_id` TEXT NOT NULL, `token_amount` INTEGER NOT NULL, `token_type` TEXT NOT NULL, `transaction_type` TEXT NOT NULL, `wallet_id` TEXT NOT NULL, `tx_id` TEXT NOT NULL, `output_index` INTEGER NOT NULL, `address` TEXT NOT NULL, `path` TEXT NOT NULL, `script` BLOB NOT NULL, `satoshis` INTEGER NOT NULL, `coin` TEXT NOT NULL, PRIMARY KEY(`wallet_id`, `token_id`, `tx_id`, `output_index`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `verified_token` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `symbol` TEXT NOT NULL, `decimals` INTEGER NOT NULL, `iconImageUrl` TEXT NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_verified_token_id` ON `verified_token` (`id`)");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_verified_token_name` ON `verified_token` (`name`)");
                supportSQLiteDatabase.execSQL("CREATE  INDEX `index_verified_token_symbol` ON `verified_token` (`symbol`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `spent_utxo` (`tx_id` TEXT NOT NULL, `output_index` INTEGER NOT NULL, PRIMARY KEY(`tx_id`, `output_index`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"55d03872bb04c2393865ae6106d1388a\")");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `credential_mnemonic`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `credential_encrypted`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `credential_zion`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `user`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `exchange_rate`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `utxo`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `network_fee`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `wallet`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `merchant`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `slp`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `wallet_address_info`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `tx_history`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `contact`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `zion_name`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `slp_utxo`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `verified_token`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `spent_utxo`");
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (WalletDatabase_Impl.this.mCallbacks != null) {
                    int size = WalletDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((Callback) WalletDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                WalletDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                WalletDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (WalletDatabase_Impl.this.mCallbacks != null) {
                    int size = WalletDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((Callback) WalletDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            /* access modifiers changed from: protected */
            public void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(4);
                String str = "TEXT";
                hashMap.put("mnemonic", new Column("mnemonic", str, true, 0));
                hashMap.put("master_priv_key", new Column("master_priv_key", str, true, 0));
                String str2 = "id_id";
                hashMap.put(str2, new Column(str2, str, true, 1));
                String str3 = "id_type";
                hashMap.put(str3, new Column(str3, str, true, 2));
                TableInfo tableInfo = new TableInfo("credential_mnemonic", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "credential_mnemonic");
                String str4 = "\n Found:\n";
                if (tableInfo.equals(read)) {
                    HashMap hashMap2 = new HashMap(3);
                    hashMap2.put("encrypted_mnmeonic", new Column("encrypted_mnmeonic", str, true, 0));
                    hashMap2.put(str2, new Column(str2, str, true, 1));
                    hashMap2.put(str3, new Column(str3, str, true, 2));
                    TableInfo tableInfo2 = new TableInfo("credential_encrypted", hashMap2, new HashSet(0), new HashSet(0));
                    TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "credential_encrypted");
                    if (tableInfo2.equals(read2)) {
                        HashMap hashMap3 = new HashMap(3);
                        hashMap3.put("zion_id", new Column("zion_id", str, true, 0));
                        hashMap3.put(str2, new Column(str2, str, true, 1));
                        hashMap3.put(str3, new Column(str3, str, true, 2));
                        TableInfo tableInfo3 = new TableInfo("credential_zion", hashMap3, new HashSet(0), new HashSet(0));
                        TableInfo read3 = TableInfo.read(supportSQLiteDatabase2, "credential_zion");
                        if (tableInfo3.equals(read3)) {
                            HashMap hashMap4 = new HashMap(3);
                            String str5 = CommonProperties.f657ID;
                            hashMap4.put(str5, new Column(str5, str, true, 1));
                            hashMap4.put("credential_id", new Column("credential_id", str, true, 0));
                            hashMap4.put("credential_type", new Column("credential_type", str, true, 0));
                            TableInfo tableInfo4 = new TableInfo("user", hashMap4, new HashSet(0), new HashSet(0));
                            TableInfo read4 = TableInfo.read(supportSQLiteDatabase2, "user");
                            if (tableInfo4.equals(read4)) {
                                HashMap hashMap5 = new HashMap(4);
                                hashMap5.put("from_ticker", new Column("from_ticker", str, true, 1));
                                hashMap5.put("to_ticker", new Column("to_ticker", str, true, 2));
                                hashMap5.put("rate", new Column("rate", str, true, 0));
                                String str6 = "INTEGER";
                                hashMap5.put("timestamp", new Column("timestamp", str6, true, 0));
                                HashSet hashSet = new HashSet(0);
                                HashSet hashSet2 = new HashSet(2);
                                hashSet2.add(new Index("index_exchange_rate_from_ticker", false, Arrays.asList(new String[]{"from_ticker"})));
                                hashSet2.add(new Index("index_exchange_rate_to_ticker", false, Arrays.asList(new String[]{"to_ticker"})));
                                TableInfo tableInfo5 = new TableInfo("exchange_rate", hashMap5, hashSet, hashSet2);
                                TableInfo read5 = TableInfo.read(supportSQLiteDatabase2, "exchange_rate");
                                if (tableInfo5.equals(read5)) {
                                    HashMap hashMap6 = new HashMap(8);
                                    String str7 = "wallet_id";
                                    hashMap6.put(str7, new Column(str7, str, true, 1));
                                    String str8 = "tx_id";
                                    hashMap6.put(str8, new Column(str8, str, true, 2));
                                    String str9 = "output_index";
                                    hashMap6.put(str9, new Column(str9, str6, true, 3));
                                    hashMap6.put("address", new Column("address", str, true, 0));
                                    hashMap6.put(JsonDataKey_signMessage.path, new Column(JsonDataKey_signMessage.path, str, true, 0));
                                    hashMap6.put("script", new Column("script", "BLOB", true, 0));
                                    hashMap6.put("satoshis", new Column("satoshis", str6, true, 0));
                                    String str10 = "coin";
                                    hashMap6.put(str10, new Column(str10, str, true, 0));
                                    HashSet hashSet3 = new HashSet(0);
                                    HashSet hashSet4 = new HashSet(1);
                                    hashSet4.add(new Index("index_utxo_wallet_id", false, Arrays.asList(new String[]{str7})));
                                    TableInfo tableInfo6 = new TableInfo("utxo", hashMap6, hashSet3, hashSet4);
                                    TableInfo read6 = TableInfo.read(supportSQLiteDatabase2, "utxo");
                                    if (tableInfo6.equals(read6)) {
                                        HashMap hashMap7 = new HashMap(3);
                                        hashMap7.put(str10, new Column(str10, str, true, 1));
                                        hashMap7.put("fee_level", new Column("fee_level", str, true, 2));
                                        hashMap7.put("satoshis_per_byte", new Column("satoshis_per_byte", str, true, 0));
                                        HashSet hashSet5 = new HashSet(0);
                                        HashSet hashSet6 = new HashSet(1);
                                        hashSet6.add(new Index("index_network_fee_coin", false, Arrays.asList(new String[]{str10})));
                                        TableInfo tableInfo7 = new TableInfo("network_fee", hashMap7, hashSet5, hashSet6);
                                        TableInfo read7 = TableInfo.read(supportSQLiteDatabase2, "network_fee");
                                        if (tableInfo7.equals(read7)) {
                                            HashMap hashMap8 = new HashMap(15);
                                            hashMap8.put("pri_key", new Column("pri_key", str, true, 1));
                                            hashMap8.put(str5, new Column(str5, str, true, 0));
                                            String str11 = "name";
                                            hashMap8.put(str11, new Column(str11, str, true, 0));
                                            hashMap8.put(str10, new Column(str10, str, true, 0));
                                            hashMap8.put("network", new Column("network", str, true, 0));
                                            hashMap8.put(JsonDataKey_signMessage.path, new Column(JsonDataKey_signMessage.path, str, true, 0));
                                            hashMap8.put("public_key", new Column("public_key", str, true, 0));
                                            hashMap8.put("signing_key", new Column("signing_key", str, true, 0));
                                            hashMap8.put("credential_id", new Column("credential_id", str, true, 0));
                                            hashMap8.put("credential_type", new Column("credential_type", str, true, 0));
                                            hashMap8.put("copayers_wallet_copayer_id", new Column("copayers_wallet_copayer_id", str, true, 0));
                                            hashMap8.put("copayers_wallet_copayer_names", new Column("copayers_wallet_copayer_names", str, true, 0));
                                            hashMap8.put("copayers_num_copayers", new Column("copayers_num_copayers", str6, true, 0));
                                            hashMap8.put("copayers_required_signatures", new Column("copayers_required_signatures", str6, true, 0));
                                            hashMap8.put("copayers_copayer_names", new Column("copayers_copayer_names", str, true, 0));
                                            HashSet hashSet7 = new HashSet(0);
                                            HashSet hashSet8 = new HashSet(1);
                                            String str12 = str4;
                                            hashSet8.add(new Index("index_wallet_coin", false, Arrays.asList(new String[]{str10})));
                                            TableInfo tableInfo8 = new TableInfo("wallet", hashMap8, hashSet7, hashSet8);
                                            TableInfo read8 = TableInfo.read(supportSQLiteDatabase2, "wallet");
                                            if (tableInfo8.equals(read8)) {
                                                HashMap hashMap9 = new HashMap(6);
                                                hashMap9.put(str8, new Column(str8, str, true, 1));
                                                hashMap9.put("merchant_name", new Column("merchant_name", str, true, 0));
                                                hashMap9.put("description", new Column("description", str, true, 0));
                                                hashMap9.put("email", new Column("email", str, true, 0));
                                                hashMap9.put("phone", new Column("phone", str, true, 0));
                                                hashMap9.put("website", new Column("website", str, true, 0));
                                                TableInfo tableInfo9 = new TableInfo("merchant", hashMap9, new HashSet(0), new HashSet(0));
                                                TableInfo read9 = TableInfo.read(supportSQLiteDatabase2, "merchant");
                                                if (tableInfo9.equals(read9)) {
                                                    HashMap hashMap10 = new HashMap(5);
                                                    hashMap10.put("token_id", new Column("token_id", str, true, 1));
                                                    hashMap10.put("ticker", new Column("ticker", str, true, 0));
                                                    hashMap10.put(str11, new Column(str11, str, true, 0));
                                                    hashMap10.put(str7, new Column(str7, str, true, 2));
                                                    hashMap10.put("decimals", new Column("decimals", str6, true, 0));
                                                    TableInfo tableInfo10 = new TableInfo("slp", hashMap10, new HashSet(0), new HashSet(0));
                                                    TableInfo read10 = TableInfo.read(supportSQLiteDatabase2, "slp");
                                                    if (tableInfo10.equals(read10)) {
                                                        HashMap hashMap11 = new HashMap(4);
                                                        hashMap11.put(str7, new Column(str7, str, true, 1));
                                                        hashMap11.put("path_x", new Column("path_x", str6, true, 0));
                                                        hashMap11.put("path_y_next", new Column("path_y_next", str6, true, 0));
                                                        hashMap11.put("path_y_max", new Column("path_y_max", str6, true, 0));
                                                        TableInfo tableInfo11 = new TableInfo("wallet_address_info", hashMap11, new HashSet(0), new HashSet(0));
                                                        TableInfo read11 = TableInfo.read(supportSQLiteDatabase2, "wallet_address_info");
                                                        if (tableInfo11.equals(read11)) {
                                                            HashMap hashMap12 = new HashMap(12);
                                                            hashMap12.put(str8, new Column(str8, str, true, 1));
                                                            hashMap12.put(str7, new Column(str7, str, true, 2));
                                                            hashMap12.put("timestamp", new Column("timestamp", str6, true, 0));
                                                            hashMap12.put("confirmations", new Column("confirmations", str6, true, 0));
                                                            hashMap12.put("satoshis", new Column("satoshis", str6, true, 0));
                                                            hashMap12.put("action", new Column("action", str, true, 0));
                                                            hashMap12.put(str10, new Column(str10, str, true, 0));
                                                            hashMap12.put("to_address", new Column("to_address", str, true, 0));
                                                            hashMap12.put("note", new Column("note", str, false, 0));
                                                            hashMap12.put("fees", new Column("fees", str6, true, 0));
                                                            hashMap12.put("token_id", new Column("token_id", str, true, 0));
                                                            hashMap12.put("token_amount", new Column("token_amount", str6, true, 0));
                                                            HashSet hashSet9 = new HashSet(0);
                                                            HashSet hashSet10 = new HashSet(2);
                                                            hashSet10.add(new Index("index_tx_history_wallet_id", false, Arrays.asList(new String[]{str7})));
                                                            hashSet10.add(new Index("index_tx_history_timestamp", false, Arrays.asList(new String[]{"timestamp"})));
                                                            TableInfo tableInfo12 = new TableInfo("tx_history", hashMap12, hashSet9, hashSet10);
                                                            TableInfo read12 = TableInfo.read(supportSQLiteDatabase2, "tx_history");
                                                            if (tableInfo12.equals(read12)) {
                                                                HashMap hashMap13 = new HashMap(12);
                                                                hashMap13.put(str5, new Column(str5, str, true, 1));
                                                                hashMap13.put(str11, new Column(str11, str, true, 0));
                                                                hashMap13.put("email", new Column("email", str, true, 0));
                                                                hashMap13.put("description", new Column("description", str, true, 0));
                                                                hashMap13.put(str10, new Column(str10, str, true, 0));
                                                                hashMap13.put("wallet_type", new Column("wallet_type", str, true, 0));
                                                                hashMap13.put("address", new Column("address", str, true, 0));
                                                                hashMap13.put("website", new Column("website", str, true, 0));
                                                                hashMap13.put("editable", new Column("editable", str6, true, 0));
                                                                hashMap13.put("known_address", new Column("known_address", str6, true, 0));
                                                                hashMap13.put("always_shown", new Column("always_shown", str6, true, 0));
                                                                hashMap13.put("always_hidden", new Column("always_hidden", str6, true, 0));
                                                                TableInfo tableInfo13 = new TableInfo("contact", hashMap13, new HashSet(0), new HashSet(0));
                                                                TableInfo read13 = TableInfo.read(supportSQLiteDatabase2, "contact");
                                                                if (tableInfo13.equals(read13)) {
                                                                    HashMap hashMap14 = new HashMap(2);
                                                                    hashMap14.put(Param.INDEX, new Column(Param.INDEX, str6, true, 1));
                                                                    hashMap14.put("zion_id", new Column("zion_id", str, false, 0));
                                                                    TableInfo tableInfo14 = new TableInfo("zion_name", hashMap14, new HashSet(0), new HashSet(0));
                                                                    TableInfo read14 = TableInfo.read(supportSQLiteDatabase2, "zion_name");
                                                                    if (tableInfo14.equals(read14)) {
                                                                        HashMap hashMap15 = new HashMap(12);
                                                                        hashMap15.put("token_id", new Column("token_id", str, true, 2));
                                                                        hashMap15.put("token_amount", new Column("token_amount", str6, true, 0));
                                                                        hashMap15.put("token_type", new Column("token_type", str, true, 0));
                                                                        hashMap15.put("transaction_type", new Column("transaction_type", str, true, 0));
                                                                        hashMap15.put(str7, new Column(str7, str, true, 1));
                                                                        hashMap15.put(str8, new Column(str8, str, true, 3));
                                                                        hashMap15.put(str9, new Column(str9, str6, true, 4));
                                                                        hashMap15.put("address", new Column("address", str, true, 0));
                                                                        hashMap15.put(JsonDataKey_signMessage.path, new Column(JsonDataKey_signMessage.path, str, true, 0));
                                                                        hashMap15.put("script", new Column("script", "BLOB", true, 0));
                                                                        hashMap15.put("satoshis", new Column("satoshis", str6, true, 0));
                                                                        hashMap15.put(str10, new Column(str10, str, true, 0));
                                                                        TableInfo tableInfo15 = new TableInfo("slp_utxo", hashMap15, new HashSet(0), new HashSet(0));
                                                                        TableInfo read15 = TableInfo.read(supportSQLiteDatabase2, "slp_utxo");
                                                                        if (tableInfo15.equals(read15)) {
                                                                            HashMap hashMap16 = new HashMap(5);
                                                                            hashMap16.put(str5, new Column(str5, str, true, 1));
                                                                            hashMap16.put(str11, new Column(str11, str, true, 0));
                                                                            hashMap16.put("symbol", new Column("symbol", str, true, 0));
                                                                            hashMap16.put("decimals", new Column("decimals", str6, true, 0));
                                                                            hashMap16.put("iconImageUrl", new Column("iconImageUrl", str, true, 0));
                                                                            HashSet hashSet11 = new HashSet(0);
                                                                            HashSet hashSet12 = new HashSet(3);
                                                                            hashSet12.add(new Index("index_verified_token_id", false, Arrays.asList(new String[]{str5})));
                                                                            hashSet12.add(new Index("index_verified_token_name", false, Arrays.asList(new String[]{str11})));
                                                                            hashSet12.add(new Index("index_verified_token_symbol", false, Arrays.asList(new String[]{"symbol"})));
                                                                            TableInfo tableInfo16 = new TableInfo("verified_token", hashMap16, hashSet11, hashSet12);
                                                                            TableInfo read16 = TableInfo.read(supportSQLiteDatabase2, "verified_token");
                                                                            if (tableInfo16.equals(read16)) {
                                                                                HashMap hashMap17 = new HashMap(2);
                                                                                hashMap17.put(str8, new Column(str8, str, true, 1));
                                                                                hashMap17.put(str9, new Column(str9, str6, true, 2));
                                                                                TableInfo tableInfo17 = new TableInfo("spent_utxo", hashMap17, new HashSet(0), new HashSet(0));
                                                                                TableInfo read17 = TableInfo.read(supportSQLiteDatabase2, "spent_utxo");
                                                                                if (!tableInfo17.equals(read17)) {
                                                                                    StringBuilder sb = new StringBuilder();
                                                                                    sb.append("Migration didn't properly handle spent_utxo(com.bitcoin.mwallet.core.models.tx.SpentUtxo).\n Expected:\n");
                                                                                    sb.append(tableInfo17);
                                                                                    sb.append(str12);
                                                                                    sb.append(read17);
                                                                                    throw new IllegalStateException(sb.toString());
                                                                                }
                                                                                return;
                                                                            }
                                                                            String str13 = str12;
                                                                            StringBuilder sb2 = new StringBuilder();
                                                                            sb2.append("Migration didn't properly handle verified_token(com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken).\n Expected:\n");
                                                                            sb2.append(tableInfo16);
                                                                            sb2.append(str13);
                                                                            sb2.append(read16);
                                                                            throw new IllegalStateException(sb2.toString());
                                                                        }
                                                                        String str14 = str12;
                                                                        StringBuilder sb3 = new StringBuilder();
                                                                        sb3.append("Migration didn't properly handle slp_utxo(com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo).\n Expected:\n");
                                                                        sb3.append(tableInfo15);
                                                                        sb3.append(str14);
                                                                        sb3.append(read15);
                                                                        throw new IllegalStateException(sb3.toString());
                                                                    }
                                                                    String str15 = str12;
                                                                    StringBuilder sb4 = new StringBuilder();
                                                                    sb4.append("Migration didn't properly handle zion_name(com.bitcoin.mwallet.core.entity.ZionNameEntity).\n Expected:\n");
                                                                    sb4.append(tableInfo14);
                                                                    sb4.append(str15);
                                                                    sb4.append(read14);
                                                                    throw new IllegalStateException(sb4.toString());
                                                                }
                                                                String str16 = str12;
                                                                StringBuilder sb5 = new StringBuilder();
                                                                sb5.append("Migration didn't properly handle contact(com.bitcoin.mwallet.core.models.contact.Contact).\n Expected:\n");
                                                                sb5.append(tableInfo13);
                                                                sb5.append(str16);
                                                                sb5.append(read13);
                                                                throw new IllegalStateException(sb5.toString());
                                                            }
                                                            String str17 = str12;
                                                            StringBuilder sb6 = new StringBuilder();
                                                            sb6.append("Migration didn't properly handle tx_history(com.bitcoin.mwallet.core.models.tx.history.HistoricTransaction).\n Expected:\n");
                                                            sb6.append(tableInfo12);
                                                            sb6.append(str17);
                                                            sb6.append(read12);
                                                            throw new IllegalStateException(sb6.toString());
                                                        }
                                                        String str18 = str12;
                                                        StringBuilder sb7 = new StringBuilder();
                                                        sb7.append("Migration didn't properly handle wallet_address_info(com.bitcoin.mwallet.core.models.address.WalletAddressInfo).\n Expected:\n");
                                                        sb7.append(tableInfo11);
                                                        sb7.append(str18);
                                                        sb7.append(read11);
                                                        throw new IllegalStateException(sb7.toString());
                                                    }
                                                    String str19 = str12;
                                                    StringBuilder sb8 = new StringBuilder();
                                                    sb8.append("Migration didn't properly handle slp(com.bitcoin.mwallet.core.models.slp.Slp).\n Expected:\n");
                                                    sb8.append(tableInfo10);
                                                    sb8.append(str19);
                                                    sb8.append(read10);
                                                    throw new IllegalStateException(sb8.toString());
                                                }
                                                String str20 = str12;
                                                StringBuilder sb9 = new StringBuilder();
                                                sb9.append("Migration didn't properly handle merchant(com.bitcoin.mwallet.core.models.merchant.Merchant).\n Expected:\n");
                                                sb9.append(tableInfo9);
                                                sb9.append(str20);
                                                sb9.append(read9);
                                                throw new IllegalStateException(sb9.toString());
                                            }
                                            String str21 = str12;
                                            StringBuilder sb10 = new StringBuilder();
                                            sb10.append("Migration didn't properly handle wallet(com.bitcoin.mwallet.core.models.wallet.Wallet).\n Expected:\n");
                                            sb10.append(tableInfo8);
                                            sb10.append(str21);
                                            sb10.append(read8);
                                            throw new IllegalStateException(sb10.toString());
                                        }
                                        String str22 = str4;
                                        StringBuilder sb11 = new StringBuilder();
                                        sb11.append("Migration didn't properly handle network_fee(com.bitcoin.mwallet.core.models.networkfee.NetworkFee).\n Expected:\n");
                                        sb11.append(tableInfo7);
                                        sb11.append(str22);
                                        sb11.append(read7);
                                        throw new IllegalStateException(sb11.toString());
                                    }
                                    String str23 = str4;
                                    StringBuilder sb12 = new StringBuilder();
                                    sb12.append("Migration didn't properly handle utxo(com.bitcoin.mwallet.core.models.tx.utxo.Utxo).\n Expected:\n");
                                    sb12.append(tableInfo6);
                                    sb12.append(str23);
                                    sb12.append(read6);
                                    throw new IllegalStateException(sb12.toString());
                                }
                                String str24 = str4;
                                StringBuilder sb13 = new StringBuilder();
                                sb13.append("Migration didn't properly handle exchange_rate(com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate).\n Expected:\n");
                                sb13.append(tableInfo5);
                                sb13.append(str24);
                                sb13.append(read5);
                                throw new IllegalStateException(sb13.toString());
                            }
                            String str25 = str4;
                            StringBuilder sb14 = new StringBuilder();
                            sb14.append("Migration didn't properly handle user(com.bitcoin.mwallet.core.models.user.User).\n Expected:\n");
                            sb14.append(tableInfo4);
                            sb14.append(str25);
                            sb14.append(read4);
                            throw new IllegalStateException(sb14.toString());
                        }
                        String str26 = str4;
                        StringBuilder sb15 = new StringBuilder();
                        sb15.append("Migration didn't properly handle credential_zion(com.bitcoin.mwallet.core.models.credential.CredentialZion).\n Expected:\n");
                        sb15.append(tableInfo3);
                        sb15.append(str26);
                        sb15.append(read3);
                        throw new IllegalStateException(sb15.toString());
                    }
                    String str27 = str4;
                    StringBuilder sb16 = new StringBuilder();
                    sb16.append("Migration didn't properly handle credential_encrypted(com.bitcoin.mwallet.core.models.credential.CredentialEncrypted).\n Expected:\n");
                    sb16.append(tableInfo2);
                    sb16.append(str27);
                    sb16.append(read2);
                    throw new IllegalStateException(sb16.toString());
                }
                String str28 = str4;
                StringBuilder sb17 = new StringBuilder();
                sb17.append("Migration didn't properly handle credential_mnemonic(com.bitcoin.mwallet.core.models.credential.CredentialMnemonic).\n Expected:\n");
                sb17.append(tableInfo);
                sb17.append(str28);
                sb17.append(read);
                throw new IllegalStateException(sb17.toString());
            }
        }, "55d03872bb04c2393865ae6106d1388a", "49b3d583b290c6718238eb5538bbfbee")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "credential_mnemonic", "credential_encrypted", "credential_zion", "user", "exchange_rate", "utxo", "network_fee", "wallet", "merchant", "slp", "wallet_address_info", "tx_history", "contact", "zion_name", "slp_utxo", "verified_token", "spent_utxo");
    }

    public void clearAllTables() {
        String str = "VACUUM";
        String str2 = "PRAGMA wal_checkpoint(FULL)";
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `credential_mnemonic`");
            writableDatabase.execSQL("DELETE FROM `credential_encrypted`");
            writableDatabase.execSQL("DELETE FROM `credential_zion`");
            writableDatabase.execSQL("DELETE FROM `user`");
            writableDatabase.execSQL("DELETE FROM `exchange_rate`");
            writableDatabase.execSQL("DELETE FROM `utxo`");
            writableDatabase.execSQL("DELETE FROM `network_fee`");
            writableDatabase.execSQL("DELETE FROM `wallet`");
            writableDatabase.execSQL("DELETE FROM `merchant`");
            writableDatabase.execSQL("DELETE FROM `slp`");
            writableDatabase.execSQL("DELETE FROM `wallet_address_info`");
            writableDatabase.execSQL("DELETE FROM `tx_history`");
            writableDatabase.execSQL("DELETE FROM `contact`");
            writableDatabase.execSQL("DELETE FROM `zion_name`");
            writableDatabase.execSQL("DELETE FROM `slp_utxo`");
            writableDatabase.execSQL("DELETE FROM `verified_token`");
            writableDatabase.execSQL("DELETE FROM `spent_utxo`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query(str2).close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL(str);
            }
        }
    }

    public CredentialDao credentialDao() {
        CredentialDao credentialDao;
        if (this._credentialDao != null) {
            return this._credentialDao;
        }
        synchronized (this) {
            if (this._credentialDao == null) {
                this._credentialDao = new CredentialDao_Impl(this);
            }
            credentialDao = this._credentialDao;
        }
        return credentialDao;
    }

    public ExchangeRateDao rateDao() {
        ExchangeRateDao exchangeRateDao;
        if (this._exchangeRateDao != null) {
            return this._exchangeRateDao;
        }
        synchronized (this) {
            if (this._exchangeRateDao == null) {
                this._exchangeRateDao = new ExchangeRateDao_Impl(this);
            }
            exchangeRateDao = this._exchangeRateDao;
        }
        return exchangeRateDao;
    }

    public UserDao userDao() {
        UserDao userDao;
        if (this._userDao != null) {
            return this._userDao;
        }
        synchronized (this) {
            if (this._userDao == null) {
                this._userDao = new UserDao_Impl(this);
            }
            userDao = this._userDao;
        }
        return userDao;
    }

    public UtxoDao utxoDao() {
        UtxoDao utxoDao;
        if (this._utxoDao != null) {
            return this._utxoDao;
        }
        synchronized (this) {
            if (this._utxoDao == null) {
                this._utxoDao = new UtxoDao_Impl(this);
            }
            utxoDao = this._utxoDao;
        }
        return utxoDao;
    }

    public WalletDao walletDao() {
        WalletDao walletDao;
        if (this._walletDao != null) {
            return this._walletDao;
        }
        synchronized (this) {
            if (this._walletDao == null) {
                this._walletDao = new WalletDao_Impl(this);
            }
            walletDao = this._walletDao;
        }
        return walletDao;
    }

    public NetworkFeeDao networkFeeDao() {
        NetworkFeeDao networkFeeDao;
        if (this._networkFeeDao != null) {
            return this._networkFeeDao;
        }
        synchronized (this) {
            if (this._networkFeeDao == null) {
                this._networkFeeDao = new NetworkFeeDao_Impl(this);
            }
            networkFeeDao = this._networkFeeDao;
        }
        return networkFeeDao;
    }

    public WalletAddressInfoDao walletAddressInfoDao() {
        WalletAddressInfoDao walletAddressInfoDao;
        if (this._walletAddressInfoDao != null) {
            return this._walletAddressInfoDao;
        }
        synchronized (this) {
            if (this._walletAddressInfoDao == null) {
                this._walletAddressInfoDao = new WalletAddressInfoDao_Impl(this);
            }
            walletAddressInfoDao = this._walletAddressInfoDao;
        }
        return walletAddressInfoDao;
    }

    public TransactionHistoryDao transactionHistoryDao() {
        TransactionHistoryDao transactionHistoryDao;
        if (this._transactionHistoryDao != null) {
            return this._transactionHistoryDao;
        }
        synchronized (this) {
            if (this._transactionHistoryDao == null) {
                this._transactionHistoryDao = new TransactionHistoryDao_Impl(this);
            }
            transactionHistoryDao = this._transactionHistoryDao;
        }
        return transactionHistoryDao;
    }

    public ContactDao contactDao() {
        ContactDao contactDao;
        if (this._contactDao != null) {
            return this._contactDao;
        }
        synchronized (this) {
            if (this._contactDao == null) {
                this._contactDao = new ContactDao_Impl(this);
            }
            contactDao = this._contactDao;
        }
        return contactDao;
    }

    public ZionNameDao zionNameDao() {
        ZionNameDao zionNameDao;
        if (this._zionNameDao != null) {
            return this._zionNameDao;
        }
        synchronized (this) {
            if (this._zionNameDao == null) {
                this._zionNameDao = new ZionNameDao_Impl(this);
            }
            zionNameDao = this._zionNameDao;
        }
        return zionNameDao;
    }

    public SlpDao slpDao() {
        SlpDao slpDao;
        if (this._slpDao != null) {
            return this._slpDao;
        }
        synchronized (this) {
            if (this._slpDao == null) {
                this._slpDao = new SlpDao_Impl(this);
            }
            slpDao = this._slpDao;
        }
        return slpDao;
    }

    public SlpUtxoDao slpUtxoDao() {
        SlpUtxoDao slpUtxoDao;
        if (this._slpUtxoDao != null) {
            return this._slpUtxoDao;
        }
        synchronized (this) {
            if (this._slpUtxoDao == null) {
                this._slpUtxoDao = new SlpUtxoDao_Impl(this);
            }
            slpUtxoDao = this._slpUtxoDao;
        }
        return slpUtxoDao;
    }

    public MerchantDao merchantDao() {
        MerchantDao merchantDao;
        if (this._merchantDao != null) {
            return this._merchantDao;
        }
        synchronized (this) {
            if (this._merchantDao == null) {
                this._merchantDao = new MerchantDao_Impl(this);
            }
            merchantDao = this._merchantDao;
        }
        return merchantDao;
    }

    public VerifiedTokenDao verifiedTokenDao() {
        VerifiedTokenDao verifiedTokenDao;
        if (this._verifiedTokenDao != null) {
            return this._verifiedTokenDao;
        }
        synchronized (this) {
            if (this._verifiedTokenDao == null) {
                this._verifiedTokenDao = new VerifiedTokenDao_Impl(this);
            }
            verifiedTokenDao = this._verifiedTokenDao;
        }
        return verifiedTokenDao;
    }

    public SpentUtxoDao spentUtxoDao() {
        SpentUtxoDao spentUtxoDao;
        if (this._spentUtxoDao != null) {
            return this._spentUtxoDao;
        }
        synchronized (this) {
            if (this._spentUtxoDao == null) {
                this._spentUtxoDao = new SpentUtxoDao_Impl(this);
            }
            spentUtxoDao = this._spentUtxoDao;
        }
        return spentUtxoDao;
    }
}
