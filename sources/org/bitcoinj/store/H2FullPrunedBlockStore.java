package org.bitcoinj.store;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bitcoinj.core.NetworkParameters;

public class H2FullPrunedBlockStore extends DatabaseFullPrunedBlockStore {
    private static final String CREATE_HEADERS_TABLE = "CREATE TABLE headers ( hash BINARY(28) NOT NULL CONSTRAINT headers_pk PRIMARY KEY,chainwork BLOB NOT NULL,height INT NOT NULL,header BLOB NOT NULL,wasundoable BOOL NOT NULL)";
    private static final String CREATE_OPEN_OUTPUT_TABLE = "CREATE TABLE openoutputs (hash BINARY(32) NOT NULL,index INT NOT NULL,height INT NOT NULL,value BIGINT NOT NULL,scriptbytes BLOB NOT NULL,toaddress VARCHAR(35),addresstargetable TINYINT,coinbase BOOLEAN,PRIMARY KEY (hash, index),)";
    private static final String CREATE_OUTPUTS_ADDRESSTARGETABLE_INDEX = "CREATE INDEX openoutputs_addresstargetable_idx ON openoutputs (addresstargetable)";
    private static final String CREATE_OUTPUTS_ADDRESS_MULTI_INDEX = "CREATE INDEX openoutputs_hash_index_height_toaddress_idx ON openoutputs (hash, index, height, toaddress)";
    private static final String CREATE_OUTPUTS_HASH_INDEX = "CREATE INDEX openoutputs_hash_idx ON openoutputs (hash)";
    private static final String CREATE_OUTPUTS_TOADDRESS_INDEX = "CREATE INDEX openoutputs_toaddress_idx ON openoutputs (toaddress)";
    private static final String CREATE_SETTINGS_TABLE = "CREATE TABLE settings ( name VARCHAR(32) NOT NULL CONSTRAINT settings_pk PRIMARY KEY,value BLOB)";
    private static final String CREATE_UNDOABLE_TABLE = "CREATE TABLE undoableblocks ( hash BINARY(28) NOT NULL CONSTRAINT undoableblocks_pk PRIMARY KEY,height INT NOT NULL,txoutchanges BLOB,transactions BLOB)";
    private static final String CREATE_UNDOABLE_TABLE_INDEX = "CREATE INDEX undoableblocks_height_idx ON undoableblocks (height)";
    private static final String DATABASE_CONNECTION_URL_PREFIX = "jdbc:h2:";
    private static final String DATABASE_DRIVER_CLASS = "org.h2.Driver";
    private static final String H2_DUPLICATE_KEY_ERROR_CODE = "23505";

    /* access modifiers changed from: protected */
    public String getDatabaseDriverClass() {
        return DATABASE_DRIVER_CLASS;
    }

    /* access modifiers changed from: protected */
    public String getDuplicateKeyErrorCode() {
        return H2_DUPLICATE_KEY_ERROR_CODE;
    }

    public H2FullPrunedBlockStore(NetworkParameters networkParameters, String str, String str2, String str3, int i) throws BlockStoreException {
        StringBuilder sb = new StringBuilder();
        sb.append(DATABASE_CONNECTION_URL_PREFIX);
        sb.append(str);
        sb.append(";create=true;LOCK_TIMEOUT=60000;DB_CLOSE_ON_EXIT=FALSE");
        super(networkParameters, sb.toString(), i, str2, str3, null);
    }

    public H2FullPrunedBlockStore(NetworkParameters networkParameters, String str, int i) throws BlockStoreException {
        this(networkParameters, str, null, null, i);
    }

    public H2FullPrunedBlockStore(NetworkParameters networkParameters, String str, int i, int i2) throws BlockStoreException {
        this(networkParameters, str, i);
        try {
            Statement createStatement = ((Connection) this.conn.get()).createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SET CACHE_SIZE ");
            sb.append(i2);
            createStatement.executeUpdate(sb.toString());
            createStatement.close();
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
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
