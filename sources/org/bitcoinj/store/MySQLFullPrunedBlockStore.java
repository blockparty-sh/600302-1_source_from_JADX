package org.bitcoinj.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bitcoinj.core.NetworkParameters;

public class MySQLFullPrunedBlockStore extends DatabaseFullPrunedBlockStore {
    private static final String CREATE_HEADERS_TABLE = "CREATE TABLE headers (\n    hash varbinary(28) NOT NULL,\n    chainwork varbinary(12) NOT NULL,\n    height integer NOT NULL,\n    header varbinary(80) NOT NULL,\n    wasundoable tinyint(1) NOT NULL,\n    CONSTRAINT headers_pk PRIMARY KEY (hash) USING BTREE \n)";
    private static final String CREATE_OPEN_OUTPUT_TABLE = "CREATE TABLE openoutputs (\n    hash varbinary(32) NOT NULL,\n    `index` integer NOT NULL,\n    height integer NOT NULL,\n    value bigint NOT NULL,\n    scriptbytes mediumblob NOT NULL,\n    toaddress varchar(35),\n    addresstargetable tinyint(1),\n    coinbase boolean,\n    CONSTRAINT openoutputs_pk PRIMARY KEY (hash, `index`) USING BTREE \n)\n";
    private static final String CREATE_OUTPUTS_ADDRESSTARGETABLE_INDEX = "CREATE INDEX openoutputs_addresstargetable_idx ON openoutputs (addresstargetable) USING btree";
    private static final String CREATE_OUTPUTS_ADDRESS_MULTI_INDEX = "CREATE INDEX openoutputs_hash_index_height_toaddress_idx ON openoutputs (hash, `index`, height, toaddress) USING btree";
    private static final String CREATE_OUTPUTS_HASH_INDEX = "CREATE INDEX openoutputs_hash_idx ON openoutputs (hash) USING btree";
    private static final String CREATE_OUTPUTS_TOADDRESS_INDEX = "CREATE INDEX openoutputs_toaddress_idx ON openoutputs (toaddress) USING btree";
    private static final String CREATE_SETTINGS_TABLE = "CREATE TABLE settings (\n    name varchar(32) NOT NULL,\n    value blob,\n    CONSTRAINT setting_pk PRIMARY KEY (name)  \n)\n";
    private static final String CREATE_UNDOABLE_TABLE = "CREATE TABLE undoableblocks (\n    hash varbinary(28) NOT NULL,\n    height integer NOT NULL,\n    txoutchanges mediumblob,\n    transactions mediumblob,\n    CONSTRAINT undoableblocks_pk PRIMARY KEY (hash) USING BTREE \n)\n";
    private static final String CREATE_UNDOABLE_TABLE_INDEX = "CREATE INDEX undoableblocks_height_idx ON undoableblocks (height) USING btree";
    private static final String DATABASE_CONNECTION_URL_PREFIX = "jdbc:mysql://";
    private static final String DATABASE_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DELETE_OPENOUTPUTS_SQL = "DELETE FROM openoutputs WHERE hash = ? AND `index`= ?";
    private static final String INSERT_OPENOUTPUTS_SQL = "INSERT INTO openoutputs (hash, `index`, height, value, scriptbytes, toaddress, addresstargetable, coinbase) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String MYSQL_DUPLICATE_KEY_ERROR_CODE = "23000";
    private static final String SELECT_OPENOUTPUTS_SQL = "SELECT height, value, scriptbytes, coinbase, toaddress, addresstargetable FROM openoutputs WHERE hash = ? AND `index` = ?";
    private static final String SELECT_TRANSACTION_OUTPUTS_SQL = "SELECT hash, value, scriptbytes, height, `index`, coinbase, toaddress, addresstargetable FROM openoutputs where toaddress = ?";

    /* access modifiers changed from: protected */
    public String getDatabaseDriverClass() {
        return DATABASE_DRIVER_CLASS;
    }

    /* access modifiers changed from: protected */
    public String getDeleteOpenoutputsSQL() {
        return DELETE_OPENOUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getDuplicateKeyErrorCode() {
        return MYSQL_DUPLICATE_KEY_ERROR_CODE;
    }

    /* access modifiers changed from: protected */
    public String getInsertOpenoutputsSQL() {
        return INSERT_OPENOUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectOpenoutputsSQL() {
        return SELECT_OPENOUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getTrasactionOutputSelectSQL() {
        return SELECT_TRANSACTION_OUTPUTS_SQL;
    }

    public MySQLFullPrunedBlockStore(NetworkParameters networkParameters, int i, String str, String str2, String str3, String str4) throws BlockStoreException {
        StringBuilder sb = new StringBuilder();
        sb.append(DATABASE_CONNECTION_URL_PREFIX);
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        super(networkParameters, sb.toString(), i, str3, str4, null);
    }

    /* access modifiers changed from: protected */
    public List<String> getCreateTablesSQL() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CREATE_SETTINGS_TABLE);
        arrayList.add(CREATE_HEADERS_TABLE);
        arrayList.add(CREATE_UNDOABLE_TABLE);
        arrayList.add(CREATE_OPEN_OUTPUT_TABLE);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<String> getCreateIndexesSQL() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CREATE_UNDOABLE_TABLE_INDEX);
        arrayList.add(CREATE_OUTPUTS_ADDRESS_MULTI_INDEX);
        arrayList.add(CREATE_OUTPUTS_ADDRESSTARGETABLE_INDEX);
        arrayList.add(CREATE_OUTPUTS_HASH_INDEX);
        arrayList.add(CREATE_OUTPUTS_TOADDRESS_INDEX);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<String> getCreateSchemeSQL() {
        return Collections.emptyList();
    }
}
