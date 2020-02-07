package org.bitcoinj.store;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.StoredUndoableBlock;
import org.bitcoinj.core.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresFullPrunedBlockStore extends DatabaseFullPrunedBlockStore {
    private static final String CREATE_HEADERS_TABLE = "CREATE TABLE headers (\n    hash bytea NOT NULL,\n    chainwork bytea NOT NULL,\n    height integer NOT NULL,\n    header bytea NOT NULL,\n    wasundoable boolean NOT NULL,\n    CONSTRAINT headers_pk PRIMARY KEY (hash)\n)\n";
    private static final String CREATE_OPEN_OUTPUT_TABLE = "CREATE TABLE openoutputs (\n    hash bytea NOT NULL,\n    index integer NOT NULL,\n    height integer NOT NULL,\n    value bigint NOT NULL,\n    scriptbytes bytea NOT NULL,\n    toaddress character varying(35),\n    addresstargetable smallint,\n    coinbase boolean,\n    CONSTRAINT openoutputs_pk PRIMARY KEY (hash,index)\n)\n";
    private static final String CREATE_OUTPUTS_ADDRESSTARGETABLE_INDEX = "CREATE INDEX openoutputs_addresstargetable_idx ON openoutputs USING btree (addresstargetable)";
    private static final String CREATE_OUTPUTS_ADDRESS_MULTI_INDEX = "CREATE INDEX openoutputs_hash_index_num_height_toaddress_idx ON openoutputs USING btree (hash, index, height, toaddress)";
    private static final String CREATE_OUTPUTS_HASH_INDEX = "CREATE INDEX openoutputs_hash_idx ON openoutputs USING btree (hash)";
    private static final String CREATE_OUTPUTS_TOADDRESS_INDEX = "CREATE INDEX openoutputs_toaddress_idx ON openoutputs USING btree (toaddress)";
    private static final String CREATE_SETTINGS_TABLE = "CREATE TABLE settings (\n    name character varying(32) NOT NULL,\n    value bytea,\n    CONSTRAINT setting_pk PRIMARY KEY (name)\n)\n";
    private static final String CREATE_UNDOABLE_TABLE = "CREATE TABLE undoableblocks (\n    hash bytea NOT NULL,\n    height integer NOT NULL,\n    txoutchanges bytea,\n    transactions bytea,\n    CONSTRAINT undoableblocks_pk PRIMARY KEY (hash)\n)\n";
    private static final String CREATE_UNDOABLE_TABLE_INDEX = "CREATE INDEX undoableblocks_height_idx ON undoableBlocks USING btree (height)";
    private static final String DATABASE_CONNECTION_URL_PREFIX = "jdbc:postgresql://";
    private static final String DATABASE_DRIVER_CLASS = "org.postgresql.Driver";
    private static final String POSTGRES_DUPLICATE_KEY_ERROR_CODE = "23505";
    private static final String SELECT_UNDOABLEBLOCKS_EXISTS_SQL = "select 1 from undoableblocks where hash = ?";
    private static final Logger log = LoggerFactory.getLogger(PostgresFullPrunedBlockStore.class);

    /* access modifiers changed from: protected */
    public String getDatabaseDriverClass() {
        return DATABASE_DRIVER_CLASS;
    }

    /* access modifiers changed from: protected */
    public String getDuplicateKeyErrorCode() {
        return POSTGRES_DUPLICATE_KEY_ERROR_CODE;
    }

    public PostgresFullPrunedBlockStore(NetworkParameters networkParameters, int i, String str, String str2, String str3, String str4) throws BlockStoreException {
        StringBuilder sb = new StringBuilder();
        sb.append(DATABASE_CONNECTION_URL_PREFIX);
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        super(networkParameters, sb.toString(), i, str3, str4, null);
    }

    public PostgresFullPrunedBlockStore(NetworkParameters networkParameters, int i, String str, String str2, String str3, String str4, @Nullable String str5) throws BlockStoreException {
        StringBuilder sb = new StringBuilder();
        sb.append(DATABASE_CONNECTION_URL_PREFIX);
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        super(networkParameters, sb.toString(), i, str3, str4, str5);
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
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE SCHEMA IF NOT EXISTS ");
        sb.append(this.schemaName);
        arrayList.add(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("set search_path to '");
        sb2.append(this.schemaName);
        sb2.append("'");
        arrayList.add(sb2.toString());
        return arrayList;
    }

    public void put(StoredBlock storedBlock, StoredUndoableBlock storedUndoableBlock) throws BlockStoreException {
        byte[] bArr;
        maybeConnect();
        byte[] bArr2 = new byte[28];
        System.arraycopy(storedBlock.getHeader().getHash().getBytes(), 4, bArr2, 0, 28);
        int height = storedBlock.getHeight();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr3 = null;
            if (storedUndoableBlock.getTxOutChanges() != null) {
                storedUndoableBlock.getTxOutChanges().serializeToStream(byteArrayOutputStream);
                bArr3 = byteArrayOutputStream.toByteArray();
                bArr = null;
            } else {
                int size = storedUndoableBlock.getTransactions().size();
                byteArrayOutputStream.write(size & 255);
                byteArrayOutputStream.write((size >> 8) & 255);
                byteArrayOutputStream.write((size >> 16) & 255);
                byteArrayOutputStream.write((size >> 24) & 255);
                for (Transaction bitcoinSerialize : storedUndoableBlock.getTransactions()) {
                    bitcoinSerialize.bitcoinSerialize(byteArrayOutputStream);
                }
                bArr = byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.close();
            try {
                if (log.isDebugEnabled()) {
                    Logger logger = log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Looking for undoable block with hash: ");
                    sb.append(C3447Utils.HEX.encode(bArr2));
                    logger.debug(sb.toString());
                }
                PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(SELECT_UNDOABLEBLOCKS_EXISTS_SQL);
                prepareStatement.setBytes(1, bArr2);
                if (prepareStatement.executeQuery().next()) {
                    prepareStatement.close();
                    PreparedStatement prepareStatement2 = ((Connection) this.conn.get()).prepareStatement(getUpdateUndoableBlocksSQL());
                    prepareStatement2.setBytes(3, bArr2);
                    if (log.isDebugEnabled()) {
                        Logger logger2 = log;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Updating undoable block with hash: ");
                        sb2.append(C3447Utils.HEX.encode(bArr2));
                        logger2.debug(sb2.toString());
                    }
                    if (bArr == null) {
                        prepareStatement2.setBytes(1, bArr3);
                        prepareStatement2.setNull(2, -2);
                    } else {
                        prepareStatement2.setNull(1, -2);
                        prepareStatement2.setBytes(2, bArr);
                    }
                    prepareStatement2.executeUpdate();
                    prepareStatement2.close();
                    return;
                }
                PreparedStatement prepareStatement3 = ((Connection) this.conn.get()).prepareStatement(getInsertUndoableBlocksSQL());
                prepareStatement3.setBytes(1, bArr2);
                prepareStatement3.setInt(2, height);
                if (log.isDebugEnabled()) {
                    Logger logger3 = log;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Inserting undoable block with hash: ");
                    sb3.append(C3447Utils.HEX.encode(bArr2));
                    sb3.append(" at height ");
                    sb3.append(height);
                    logger3.debug(sb3.toString());
                }
                if (bArr == null) {
                    prepareStatement3.setBytes(3, bArr3);
                    prepareStatement3.setNull(4, -2);
                } else {
                    prepareStatement3.setNull(3, -2);
                    prepareStatement3.setBytes(4, bArr);
                }
                prepareStatement3.executeUpdate();
                prepareStatement3.close();
                putUpdateStoredBlock(storedBlock, true);
            } catch (SQLException e) {
                throw new BlockStoreException((Throwable) e);
            } catch (SQLException e2) {
                if (!e2.getSQLState().equals(POSTGRES_DUPLICATE_KEY_ERROR_CODE)) {
                    throw new BlockStoreException((Throwable) e2);
                }
            }
        } catch (IOException e3) {
            throw new BlockStoreException((Throwable) e3);
        }
    }
}
