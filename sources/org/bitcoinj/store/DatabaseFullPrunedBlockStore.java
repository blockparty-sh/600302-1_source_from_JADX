package org.bitcoinj.store;

import com.google.common.collect.Lists;
import java.io.PrintStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.StoredUndoableBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.core.UTXOProviderException;
import org.bitcoinj.core.VerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DatabaseFullPrunedBlockStore implements FullPrunedBlockStore {
    private static final String CHAIN_HEAD_SETTING = "chainhead";
    private static final String DELETE_OPENOUTPUTS_SQL = "DELETE FROM openoutputs WHERE hash = ? AND index = ?";
    private static final String DELETE_UNDOABLEBLOCKS_SQL = "DELETE FROM undoableblocks WHERE height <= ?";
    private static final String DROP_HEADERS_TABLE = "DROP TABLE headers";
    private static final String DROP_OPEN_OUTPUT_TABLE = "DROP TABLE openoutputs";
    private static final String DROP_SETTINGS_TABLE = "DROP TABLE settings";
    private static final String DROP_UNDOABLE_TABLE = "DROP TABLE undoableblocks";
    private static final String INSERT_HEADERS_SQL = "INSERT INTO headers(hash, chainwork, height, header, wasundoable) VALUES(?, ?, ?, ?, ?)";
    private static final String INSERT_OPENOUTPUTS_SQL = "INSERT INTO openoutputs (hash, index, height, value, scriptbytes, toaddress, addresstargetable, coinbase) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SETTINGS_SQL = "INSERT INTO settings(name, value) VALUES(?, ?)";
    private static final String INSERT_UNDOABLEBLOCKS_SQL = "INSERT INTO undoableblocks(hash, height, txoutchanges, transactions) VALUES(?, ?, ?, ?)";
    private static final String SELECT_BALANCE_SQL = "select sum(value) from openoutputs where toaddress = ?";
    private static final String SELECT_CHECK_TABLES_EXIST_SQL = "SELECT * FROM settings WHERE 1 = 2";
    private static final String SELECT_COMPATIBILITY_COINBASE_SQL = "SELECT coinbase FROM openoutputs WHERE 1 = 2";
    private static final String SELECT_DUMP_HEADERS_SQL = "SELECT chainwork, header FROM headers";
    private static final String SELECT_DUMP_OPENOUTPUTS_SQL = "SELECT value, scriptbytes FROM openoutputs";
    private static final String SELECT_DUMP_SETTINGS_SQL = "SELECT name, value FROM settings";
    private static final String SELECT_DUMP_UNDOABLEBLOCKS_SQL = "SELECT txoutchanges, transactions FROM undoableblocks";
    private static final String SELECT_HEADERS_SQL = "SELECT chainwork, height, header, wasundoable FROM headers WHERE hash = ?";
    private static final String SELECT_OPENOUTPUTS_COUNT_SQL = "SELECT COUNT(*) FROM openoutputs WHERE hash = ?";
    private static final String SELECT_OPENOUTPUTS_SQL = "SELECT height, value, scriptbytes, coinbase, toaddress, addresstargetable FROM openoutputs WHERE hash = ? AND index = ?";
    private static final String SELECT_SETTINGS_SQL = "SELECT value FROM settings WHERE name = ?";
    private static final String SELECT_TRANSACTION_OUTPUTS_SQL = "SELECT hash, value, scriptbytes, height, index, coinbase, toaddress, addresstargetable FROM openoutputs where toaddress = ?";
    private static final String SELECT_UNDOABLEBLOCKS_SQL = "SELECT txoutchanges, transactions FROM undoableblocks WHERE hash = ?";
    private static final String UPDATE_HEADERS_SQL = "UPDATE headers SET wasundoable=? WHERE hash=?";
    private static final String UPDATE_SETTINGS_SQL = "UPDATE settings SET value = ? WHERE name = ?";
    private static final String UPDATE_UNDOABLEBLOCKS_SQL = "UPDATE undoableblocks SET txoutchanges=?, transactions=? WHERE hash = ?";
    private static final String VERIFIED_CHAIN_HEAD_SETTING = "verifiedchainhead";
    private static final String VERSION_SETTING = "version";
    private static final Logger log = LoggerFactory.getLogger(DatabaseFullPrunedBlockStore.class);
    protected List<Connection> allConnections = new LinkedList();
    protected StoredBlock chainHeadBlock;
    protected Sha256Hash chainHeadHash;
    protected ThreadLocal<Connection> conn = new ThreadLocal<>();
    protected String connectionURL;
    protected int fullStoreDepth;
    protected NetworkParameters params;
    protected String password;
    protected String schemaName;
    protected String username;
    protected StoredBlock verifiedChainHeadBlock;
    protected Sha256Hash verifiedChainHeadHash;

    /* access modifiers changed from: protected */
    public String getBalanceSelectSQL() {
        return SELECT_BALANCE_SQL;
    }

    /* access modifiers changed from: protected */
    public abstract List<String> getCreateIndexesSQL();

    /* access modifiers changed from: protected */
    public abstract List<String> getCreateSchemeSQL();

    /* access modifiers changed from: protected */
    public abstract List<String> getCreateTablesSQL();

    /* access modifiers changed from: protected */
    public abstract String getDatabaseDriverClass();

    /* access modifiers changed from: protected */
    public String getDeleteOpenoutputsSQL() {
        return DELETE_OPENOUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getDeleteUndoableBlocksSQL() {
        return DELETE_UNDOABLEBLOCKS_SQL;
    }

    /* access modifiers changed from: protected */
    public abstract String getDuplicateKeyErrorCode();

    /* access modifiers changed from: protected */
    public String getInsertHeadersSQL() {
        return INSERT_HEADERS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getInsertOpenoutputsSQL() {
        return INSERT_OPENOUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getInsertSettingsSQL() {
        return INSERT_SETTINGS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getInsertUndoableBlocksSQL() {
        return INSERT_UNDOABLEBLOCKS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectHeadersDumpSQL() {
        return SELECT_DUMP_HEADERS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectHeadersSQL() {
        return SELECT_HEADERS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectOpenoutputsCountSQL() {
        return SELECT_OPENOUTPUTS_COUNT_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectOpenoutputsSQL() {
        return SELECT_OPENOUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectSettingsDumpSQL() {
        return SELECT_DUMP_SETTINGS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectSettingsSQL() {
        return SELECT_SETTINGS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectUndoableBlocksSQL() {
        return SELECT_UNDOABLEBLOCKS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectUndoableblocksDumpSQL() {
        return SELECT_DUMP_UNDOABLEBLOCKS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getSelectopenoutputsDumpSQL() {
        return SELECT_DUMP_OPENOUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getTablesExistSQL() {
        return SELECT_CHECK_TABLES_EXIST_SQL;
    }

    /* access modifiers changed from: protected */
    public String getTrasactionOutputSelectSQL() {
        return SELECT_TRANSACTION_OUTPUTS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getUpdateHeadersSQL() {
        return UPDATE_HEADERS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getUpdateSettingsSLQ() {
        return UPDATE_SETTINGS_SQL;
    }

    /* access modifiers changed from: protected */
    public String getUpdateUndoableBlocksSQL() {
        return UPDATE_UNDOABLEBLOCKS_SQL;
    }

    public DatabaseFullPrunedBlockStore(NetworkParameters networkParameters, String str, int i, @Nullable String str2, @Nullable String str3, @Nullable String str4) throws BlockStoreException {
        this.params = networkParameters;
        this.fullStoreDepth = i;
        this.connectionURL = str;
        this.schemaName = str4;
        this.username = str2;
        this.password = str3;
        try {
            Class.forName(getDatabaseDriverClass());
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append(getDatabaseDriverClass());
            sb.append(" loaded. ");
            logger.info(sb.toString());
        } catch (ClassNotFoundException e) {
            log.error("check CLASSPATH for database driver jar ", (Throwable) e);
        }
        maybeConnect();
        try {
            if (!tablesExists()) {
                createTables();
            } else {
                checkCompatibility();
            }
            initFromDatabase();
        } catch (SQLException e2) {
            throw new BlockStoreException((Throwable) e2);
        }
    }

    /* access modifiers changed from: protected */
    public List<String> getCompatibilitySQL() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SELECT_COMPATIBILITY_COINBASE_SQL);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<String> getDropTablesSQL() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DROP_SETTINGS_TABLE);
        arrayList.add(DROP_HEADERS_TABLE);
        arrayList.add(DROP_UNDOABLE_TABLE);
        arrayList.add(DROP_OPEN_OUTPUT_TABLE);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063 A[Catch:{ SQLException -> 0x009b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void maybeConnect() throws org.bitcoinj.store.BlockStoreException {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.ThreadLocal<java.sql.Connection> r0 = r3.conn     // Catch:{ SQLException -> 0x009b }
            java.lang.Object r0 = r0.get()     // Catch:{ SQLException -> 0x009b }
            if (r0 == 0) goto L_0x0019
            java.lang.ThreadLocal<java.sql.Connection> r0 = r3.conn     // Catch:{ SQLException -> 0x009b }
            java.lang.Object r0 = r0.get()     // Catch:{ SQLException -> 0x009b }
            java.sql.Connection r0 = (java.sql.Connection) r0     // Catch:{ SQLException -> 0x009b }
            boolean r0 = r0.isClosed()     // Catch:{ SQLException -> 0x009b }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r3)
            return
        L_0x0019:
            java.lang.String r0 = r3.username     // Catch:{ SQLException -> 0x009b }
            if (r0 == 0) goto L_0x0041
            java.lang.String r0 = r3.password     // Catch:{ SQLException -> 0x009b }
            if (r0 != 0) goto L_0x0022
            goto L_0x0041
        L_0x0022:
            java.util.Properties r0 = new java.util.Properties     // Catch:{ SQLException -> 0x009b }
            r0.<init>()     // Catch:{ SQLException -> 0x009b }
            java.lang.String r1 = "user"
            java.lang.String r2 = r3.username     // Catch:{ SQLException -> 0x009b }
            r0.setProperty(r1, r2)     // Catch:{ SQLException -> 0x009b }
            java.lang.String r1 = "password"
            java.lang.String r2 = r3.password     // Catch:{ SQLException -> 0x009b }
            r0.setProperty(r1, r2)     // Catch:{ SQLException -> 0x009b }
            java.lang.ThreadLocal<java.sql.Connection> r1 = r3.conn     // Catch:{ SQLException -> 0x009b }
            java.lang.String r2 = r3.connectionURL     // Catch:{ SQLException -> 0x009b }
            java.sql.Connection r0 = java.sql.DriverManager.getConnection(r2, r0)     // Catch:{ SQLException -> 0x009b }
            r1.set(r0)     // Catch:{ SQLException -> 0x009b }
            goto L_0x004c
        L_0x0041:
            java.lang.ThreadLocal<java.sql.Connection> r0 = r3.conn     // Catch:{ SQLException -> 0x009b }
            java.lang.String r1 = r3.connectionURL     // Catch:{ SQLException -> 0x009b }
            java.sql.Connection r1 = java.sql.DriverManager.getConnection(r1)     // Catch:{ SQLException -> 0x009b }
            r0.set(r1)     // Catch:{ SQLException -> 0x009b }
        L_0x004c:
            java.util.List<java.sql.Connection> r0 = r3.allConnections     // Catch:{ SQLException -> 0x009b }
            java.lang.ThreadLocal<java.sql.Connection> r1 = r3.conn     // Catch:{ SQLException -> 0x009b }
            java.lang.Object r1 = r1.get()     // Catch:{ SQLException -> 0x009b }
            r0.add(r1)     // Catch:{ SQLException -> 0x009b }
            java.lang.ThreadLocal<java.sql.Connection> r0 = r3.conn     // Catch:{ SQLException -> 0x009b }
            java.lang.Object r0 = r0.get()     // Catch:{ SQLException -> 0x009b }
            java.sql.Connection r0 = (java.sql.Connection) r0     // Catch:{ SQLException -> 0x009b }
            java.lang.String r1 = r3.schemaName     // Catch:{ SQLException -> 0x009b }
            if (r1 == 0) goto L_0x007f
            java.sql.Statement r0 = r0.createStatement()     // Catch:{ SQLException -> 0x009b }
            java.util.List r1 = r3.getCreateSchemeSQL()     // Catch:{ SQLException -> 0x009b }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ SQLException -> 0x009b }
        L_0x006f:
            boolean r2 = r1.hasNext()     // Catch:{ SQLException -> 0x009b }
            if (r2 == 0) goto L_0x007f
            java.lang.Object r2 = r1.next()     // Catch:{ SQLException -> 0x009b }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ SQLException -> 0x009b }
            r0.execute(r2)     // Catch:{ SQLException -> 0x009b }
            goto L_0x006f
        L_0x007f:
            org.slf4j.Logger r0 = log     // Catch:{ SQLException -> 0x009b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLException -> 0x009b }
            r1.<init>()     // Catch:{ SQLException -> 0x009b }
            java.lang.String r2 = "Made a new connection to database "
            r1.append(r2)     // Catch:{ SQLException -> 0x009b }
            java.lang.String r2 = r3.connectionURL     // Catch:{ SQLException -> 0x009b }
            r1.append(r2)     // Catch:{ SQLException -> 0x009b }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLException -> 0x009b }
            r0.info(r1)     // Catch:{ SQLException -> 0x009b }
            monitor-exit(r3)
            return
        L_0x0099:
            r0 = move-exception
            goto L_0x00a2
        L_0x009b:
            r0 = move-exception
            org.bitcoinj.store.BlockStoreException r1 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x0099 }
            r1.<init>(r0)     // Catch:{ all -> 0x0099 }
            throw r1     // Catch:{ all -> 0x0099 }
        L_0x00a2:
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.DatabaseFullPrunedBlockStore.maybeConnect():void");
    }

    public synchronized void close() {
        for (Connection connection : this.allConnections) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                }
                connection.close();
                if (connection == this.conn.get()) {
                    this.conn.set(null);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        this.allConnections.clear();
    }

    private boolean tablesExists() throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ((Connection) this.conn.get()).prepareStatement(getTablesExistSQL());
            preparedStatement.executeQuery().close();
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            return true;
        } catch (SQLException unused) {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            return false;
        } catch (Throwable th) {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            throw th;
        }
    }

    private void checkCompatibility() throws SQLException, BlockStoreException {
        for (String prepareStatement : getCompatibilitySQL()) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = ((Connection) this.conn.get()).prepareStatement(prepareStatement);
                preparedStatement.executeQuery().close();
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Database block store is not compatible with the current release.  See bitcoinj release notes for further information: ");
                sb.append(e.getMessage());
                throw new BlockStoreException(sb.toString());
            } catch (Throwable th) {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                throw th;
            }
        }
    }

    private void createTables() throws SQLException, BlockStoreException {
        Statement createStatement = ((Connection) this.conn.get()).createStatement();
        for (String str : getCreateTablesSQL()) {
            if (log.isDebugEnabled()) {
                log.debug("DatabaseFullPrunedBlockStore : CREATE table [SQL= {0}]", (Object) str);
            }
            createStatement.executeUpdate(str);
        }
        for (String str2 : getCreateIndexesSQL()) {
            if (log.isDebugEnabled()) {
                log.debug("DatabaseFullPrunedBlockStore : CREATE index [SQL= {0}]", (Object) str2);
            }
            createStatement.executeUpdate(str2);
        }
        createStatement.close();
        PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getInsertSettingsSQL());
        prepareStatement.setString(1, CHAIN_HEAD_SETTING);
        prepareStatement.setNull(2, -2);
        prepareStatement.execute();
        prepareStatement.setString(1, VERIFIED_CHAIN_HEAD_SETTING);
        prepareStatement.setNull(2, -2);
        prepareStatement.execute();
        prepareStatement.setString(1, "version");
        prepareStatement.setBytes(2, "03".getBytes());
        prepareStatement.execute();
        prepareStatement.close();
        createNewStore(this.params);
    }

    private void createNewStore(NetworkParameters networkParameters) throws BlockStoreException {
        try {
            StoredBlock storedBlock = new StoredBlock(networkParameters.getGenesisBlock().cloneAsHeader(), networkParameters.getGenesisBlock().getWork(), 0);
            put(storedBlock, new StoredUndoableBlock(networkParameters.getGenesisBlock().getHash(), (List<Transaction>) Lists.newLinkedList()));
            setChainHead(storedBlock);
            setVerifiedChainHead(storedBlock);
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        }
    }

    private void initFromDatabase() throws SQLException, BlockStoreException {
        PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getSelectSettingsSQL());
        prepareStatement.setString(1, CHAIN_HEAD_SETTING);
        ResultSet executeQuery = prepareStatement.executeQuery();
        if (executeQuery.next()) {
            Sha256Hash wrap = Sha256Hash.wrap(executeQuery.getBytes(1));
            executeQuery.close();
            this.chainHeadBlock = get(wrap);
            this.chainHeadHash = wrap;
            if (this.chainHeadBlock != null) {
                prepareStatement.setString(1, VERIFIED_CHAIN_HEAD_SETTING);
                ResultSet executeQuery2 = prepareStatement.executeQuery();
                if (executeQuery2.next()) {
                    Sha256Hash wrap2 = Sha256Hash.wrap(executeQuery2.getBytes(1));
                    executeQuery2.close();
                    prepareStatement.close();
                    this.verifiedChainHeadBlock = get(wrap2);
                    this.verifiedChainHeadHash = wrap2;
                    if (this.verifiedChainHeadBlock == null) {
                        throw new BlockStoreException("corrupt databse block store - verified head block not found");
                    }
                    return;
                }
                throw new BlockStoreException("corrupt database block store - no verified chain head pointer");
            }
            throw new BlockStoreException("corrupt database block store - head block not found");
        }
        throw new BlockStoreException("corrupt database block store - no chain head pointer");
    }

    /* access modifiers changed from: protected */
    public void putUpdateStoredBlock(StoredBlock storedBlock, boolean z) throws SQLException {
        try {
            PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getInsertHeadersSQL());
            byte[] bArr = new byte[28];
            System.arraycopy(storedBlock.getHeader().getHash().getBytes(), 4, bArr, 0, 28);
            prepareStatement.setBytes(1, bArr);
            prepareStatement.setBytes(2, storedBlock.getChainWork().toByteArray());
            prepareStatement.setInt(3, storedBlock.getHeight());
            prepareStatement.setBytes(4, storedBlock.getHeader().cloneAsHeader().unsafeBitcoinSerialize());
            prepareStatement.setBoolean(5, z);
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals(getDuplicateKeyErrorCode()) || !z) {
                throw e;
            }
            PreparedStatement prepareStatement2 = ((Connection) this.conn.get()).prepareStatement(getUpdateHeadersSQL());
            prepareStatement2.setBoolean(1, true);
            byte[] bArr2 = new byte[28];
            System.arraycopy(storedBlock.getHeader().getHash().getBytes(), 4, bArr2, 0, 28);
            prepareStatement2.setBytes(2, bArr2);
            prepareStatement2.executeUpdate();
            prepareStatement2.close();
        }
    }

    public void put(StoredBlock storedBlock) throws BlockStoreException {
        maybeConnect();
        try {
            putUpdateStoredBlock(storedBlock, false);
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void put(org.bitcoinj.core.StoredBlock r12, org.bitcoinj.core.StoredUndoableBlock r13) throws org.bitcoinj.store.BlockStoreException {
        /*
            r11 = this;
            r11.maybeConnect()
            r0 = 28
            byte[] r1 = new byte[r0]
            org.bitcoinj.core.Block r2 = r12.getHeader()
            org.bitcoinj.core.Sha256Hash r2 = r2.getHash()
            byte[] r2 = r2.getBytes()
            r3 = 4
            r4 = 0
            java.lang.System.arraycopy(r2, r3, r1, r4, r0)
            int r0 = r12.getHeight()
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00f7 }
            r2.<init>()     // Catch:{ IOException -> 0x00f7 }
            org.bitcoinj.core.TransactionOutputChanges r4 = r13.getTxOutChanges()     // Catch:{ IOException -> 0x00f7 }
            r5 = 0
            if (r4 == 0) goto L_0x0037
            org.bitcoinj.core.TransactionOutputChanges r13 = r13.getTxOutChanges()     // Catch:{ IOException -> 0x00f7 }
            r13.serializeToStream(r2)     // Catch:{ IOException -> 0x00f7 }
            byte[] r13 = r2.toByteArray()     // Catch:{ IOException -> 0x00f7 }
            r10 = r5
            r5 = r13
            r13 = r10
            goto L_0x0075
        L_0x0037:
            java.util.List r4 = r13.getTransactions()     // Catch:{ IOException -> 0x00f7 }
            int r4 = r4.size()     // Catch:{ IOException -> 0x00f7 }
            r6 = r4 & 255(0xff, float:3.57E-43)
            r2.write(r6)     // Catch:{ IOException -> 0x00f7 }
            int r6 = r4 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            r2.write(r6)     // Catch:{ IOException -> 0x00f7 }
            int r6 = r4 >> 16
            r6 = r6 & 255(0xff, float:3.57E-43)
            r2.write(r6)     // Catch:{ IOException -> 0x00f7 }
            int r4 = r4 >> 24
            r4 = r4 & 255(0xff, float:3.57E-43)
            r2.write(r4)     // Catch:{ IOException -> 0x00f7 }
            java.util.List r13 = r13.getTransactions()     // Catch:{ IOException -> 0x00f7 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ IOException -> 0x00f7 }
        L_0x0061:
            boolean r4 = r13.hasNext()     // Catch:{ IOException -> 0x00f7 }
            if (r4 == 0) goto L_0x0071
            java.lang.Object r4 = r13.next()     // Catch:{ IOException -> 0x00f7 }
            org.bitcoinj.core.Transaction r4 = (org.bitcoinj.core.Transaction) r4     // Catch:{ IOException -> 0x00f7 }
            r4.bitcoinSerialize(r2)     // Catch:{ IOException -> 0x00f7 }
            goto L_0x0061
        L_0x0071:
            byte[] r13 = r2.toByteArray()     // Catch:{ IOException -> 0x00f7 }
        L_0x0075:
            r2.close()     // Catch:{ IOException -> 0x00f7 }
            r2 = 3
            r4 = 2
            r6 = -2
            r7 = 1
            java.lang.ThreadLocal<java.sql.Connection> r8 = r11.conn     // Catch:{ SQLException -> 0x00b2 }
            java.lang.Object r8 = r8.get()     // Catch:{ SQLException -> 0x00b2 }
            java.sql.Connection r8 = (java.sql.Connection) r8     // Catch:{ SQLException -> 0x00b2 }
            java.lang.String r9 = r11.getInsertUndoableBlocksSQL()     // Catch:{ SQLException -> 0x00b2 }
            java.sql.PreparedStatement r8 = r8.prepareStatement(r9)     // Catch:{ SQLException -> 0x00b2 }
            r8.setBytes(r7, r1)     // Catch:{ SQLException -> 0x00b2 }
            r8.setInt(r4, r0)     // Catch:{ SQLException -> 0x00b2 }
            if (r13 != 0) goto L_0x009b
            r8.setBytes(r2, r5)     // Catch:{ SQLException -> 0x00b2 }
            r8.setNull(r3, r6)     // Catch:{ SQLException -> 0x00b2 }
            goto L_0x00a1
        L_0x009b:
            r8.setNull(r2, r6)     // Catch:{ SQLException -> 0x00b2 }
            r8.setBytes(r3, r13)     // Catch:{ SQLException -> 0x00b2 }
        L_0x00a1:
            r8.executeUpdate()     // Catch:{ SQLException -> 0x00b2 }
            r8.close()     // Catch:{ SQLException -> 0x00b2 }
            r11.putUpdateStoredBlock(r12, r7)     // Catch:{ SQLException -> 0x00ab }
            goto L_0x00e9
        L_0x00ab:
            r12 = move-exception
            org.bitcoinj.store.BlockStoreException r0 = new org.bitcoinj.store.BlockStoreException     // Catch:{ SQLException -> 0x00b2 }
            r0.<init>(r12)     // Catch:{ SQLException -> 0x00b2 }
            throw r0     // Catch:{ SQLException -> 0x00b2 }
        L_0x00b2:
            r12 = move-exception
            java.lang.String r0 = r12.getSQLState()     // Catch:{ SQLException -> 0x00f0 }
            java.lang.String r3 = r11.getDuplicateKeyErrorCode()     // Catch:{ SQLException -> 0x00f0 }
            boolean r0 = r0.equals(r3)     // Catch:{ SQLException -> 0x00f0 }
            if (r0 == 0) goto L_0x00ea
            java.lang.ThreadLocal<java.sql.Connection> r12 = r11.conn     // Catch:{ SQLException -> 0x00f0 }
            java.lang.Object r12 = r12.get()     // Catch:{ SQLException -> 0x00f0 }
            java.sql.Connection r12 = (java.sql.Connection) r12     // Catch:{ SQLException -> 0x00f0 }
            java.lang.String r0 = r11.getUpdateUndoableBlocksSQL()     // Catch:{ SQLException -> 0x00f0 }
            java.sql.PreparedStatement r12 = r12.prepareStatement(r0)     // Catch:{ SQLException -> 0x00f0 }
            r12.setBytes(r2, r1)     // Catch:{ SQLException -> 0x00f0 }
            if (r13 != 0) goto L_0x00dd
            r12.setBytes(r7, r5)     // Catch:{ SQLException -> 0x00f0 }
            r12.setNull(r4, r6)     // Catch:{ SQLException -> 0x00f0 }
            goto L_0x00e3
        L_0x00dd:
            r12.setNull(r7, r6)     // Catch:{ SQLException -> 0x00f0 }
            r12.setBytes(r4, r13)     // Catch:{ SQLException -> 0x00f0 }
        L_0x00e3:
            r12.executeUpdate()     // Catch:{ SQLException -> 0x00f0 }
            r12.close()     // Catch:{ SQLException -> 0x00f0 }
        L_0x00e9:
            return
        L_0x00ea:
            org.bitcoinj.store.BlockStoreException r13 = new org.bitcoinj.store.BlockStoreException     // Catch:{ SQLException -> 0x00f0 }
            r13.<init>(r12)     // Catch:{ SQLException -> 0x00f0 }
            throw r13     // Catch:{ SQLException -> 0x00f0 }
        L_0x00f0:
            r12 = move-exception
            org.bitcoinj.store.BlockStoreException r13 = new org.bitcoinj.store.BlockStoreException
            r13.<init>(r12)
            throw r13
        L_0x00f7:
            r12 = move-exception
            org.bitcoinj.store.BlockStoreException r13 = new org.bitcoinj.store.BlockStoreException
            r13.<init>(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.DatabaseFullPrunedBlockStore.put(org.bitcoinj.core.StoredBlock, org.bitcoinj.core.StoredUndoableBlock):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x00c4 A[SYNTHETIC, Splitter:B:66:0x00c4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bitcoinj.core.StoredBlock get(org.bitcoinj.core.Sha256Hash r8, boolean r9) throws org.bitcoinj.store.BlockStoreException {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to close PreparedStatement"
            org.bitcoinj.core.Sha256Hash r1 = r7.chainHeadHash
            if (r1 == 0) goto L_0x000f
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x000f
            org.bitcoinj.core.StoredBlock r8 = r7.chainHeadBlock
            return r8
        L_0x000f:
            org.bitcoinj.core.Sha256Hash r1 = r7.verifiedChainHeadHash
            if (r1 == 0) goto L_0x001c
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x001c
            org.bitcoinj.core.StoredBlock r8 = r7.verifiedChainHeadBlock
            return r8
        L_0x001c:
            r7.maybeConnect()
            r1 = 0
            java.lang.ThreadLocal<java.sql.Connection> r2 = r7.conn     // Catch:{ SQLException -> 0x00bb, ProtocolException -> 0x00b4, VerificationException -> 0x00ad }
            java.lang.Object r2 = r2.get()     // Catch:{ SQLException -> 0x00bb, ProtocolException -> 0x00b4, VerificationException -> 0x00ad }
            java.sql.Connection r2 = (java.sql.Connection) r2     // Catch:{ SQLException -> 0x00bb, ProtocolException -> 0x00b4, VerificationException -> 0x00ad }
            java.lang.String r3 = r7.getSelectHeadersSQL()     // Catch:{ SQLException -> 0x00bb, ProtocolException -> 0x00b4, VerificationException -> 0x00ad }
            java.sql.PreparedStatement r2 = r2.prepareStatement(r3)     // Catch:{ SQLException -> 0x00bb, ProtocolException -> 0x00b4, VerificationException -> 0x00ad }
            r3 = 28
            byte[] r4 = new byte[r3]     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            byte[] r8 = r8.getBytes()     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            r5 = 0
            r6 = 4
            java.lang.System.arraycopy(r8, r6, r4, r5, r3)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            r8 = 1
            r2.setBytes(r8, r4)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            java.sql.ResultSet r3 = r2.executeQuery()     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            boolean r4 = r3.next()     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            if (r4 != 0) goto L_0x0058
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ SQLException -> 0x0051 }
            goto L_0x0057
        L_0x0051:
            org.bitcoinj.store.BlockStoreException r8 = new org.bitcoinj.store.BlockStoreException
            r8.<init>(r0)
            throw r8
        L_0x0057:
            return r1
        L_0x0058:
            if (r9 == 0) goto L_0x006d
            boolean r9 = r3.getBoolean(r6)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            if (r9 != 0) goto L_0x006d
            if (r2 == 0) goto L_0x006c
            r2.close()     // Catch:{ SQLException -> 0x0066 }
            goto L_0x006c
        L_0x0066:
            org.bitcoinj.store.BlockStoreException r8 = new org.bitcoinj.store.BlockStoreException
            r8.<init>(r0)
            throw r8
        L_0x006c:
            return r1
        L_0x006d:
            java.math.BigInteger r9 = new java.math.BigInteger     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            byte[] r8 = r3.getBytes(r8)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            r9.<init>(r8)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            r8 = 2
            int r8 = r3.getInt(r8)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            org.bitcoinj.core.NetworkParameters r1 = r7.params     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            org.bitcoinj.core.MessageSerializer r1 = r1.getDefaultSerializer()     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            r4 = 3
            byte[] r3 = r3.getBytes(r4)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            org.bitcoinj.core.Block r1 = r1.makeBlock(r3)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            r1.verifyHeader()     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            org.bitcoinj.core.StoredBlock r3 = new org.bitcoinj.core.StoredBlock     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            r3.<init>(r1, r9, r8)     // Catch:{ SQLException -> 0x00a7, ProtocolException -> 0x00a4, VerificationException -> 0x00a1, all -> 0x009f }
            if (r2 == 0) goto L_0x009e
            r2.close()     // Catch:{ SQLException -> 0x0098 }
            goto L_0x009e
        L_0x0098:
            org.bitcoinj.store.BlockStoreException r8 = new org.bitcoinj.store.BlockStoreException
            r8.<init>(r0)
            throw r8
        L_0x009e:
            return r3
        L_0x009f:
            r8 = move-exception
            goto L_0x00c2
        L_0x00a1:
            r8 = move-exception
            r1 = r2
            goto L_0x00ae
        L_0x00a4:
            r8 = move-exception
            r1 = r2
            goto L_0x00b5
        L_0x00a7:
            r8 = move-exception
            r1 = r2
            goto L_0x00bc
        L_0x00aa:
            r8 = move-exception
            r2 = r1
            goto L_0x00c2
        L_0x00ad:
            r8 = move-exception
        L_0x00ae:
            org.bitcoinj.store.BlockStoreException r9 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00aa }
            r9.<init>(r8)     // Catch:{ all -> 0x00aa }
            throw r9     // Catch:{ all -> 0x00aa }
        L_0x00b4:
            r8 = move-exception
        L_0x00b5:
            org.bitcoinj.store.BlockStoreException r9 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00aa }
            r9.<init>(r8)     // Catch:{ all -> 0x00aa }
            throw r9     // Catch:{ all -> 0x00aa }
        L_0x00bb:
            r8 = move-exception
        L_0x00bc:
            org.bitcoinj.store.BlockStoreException r9 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00aa }
            r9.<init>(r8)     // Catch:{ all -> 0x00aa }
            throw r9     // Catch:{ all -> 0x00aa }
        L_0x00c2:
            if (r2 == 0) goto L_0x00ce
            r2.close()     // Catch:{ SQLException -> 0x00c8 }
            goto L_0x00ce
        L_0x00c8:
            org.bitcoinj.store.BlockStoreException r8 = new org.bitcoinj.store.BlockStoreException
            r8.<init>(r0)
            throw r8
        L_0x00ce:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.DatabaseFullPrunedBlockStore.get(org.bitcoinj.core.Sha256Hash, boolean):org.bitcoinj.core.StoredBlock");
    }

    public StoredBlock get(Sha256Hash sha256Hash) throws BlockStoreException {
        return get(sha256Hash, false);
    }

    public StoredBlock getOnceUndoableStoredBlock(Sha256Hash sha256Hash) throws BlockStoreException {
        return get(sha256Hash, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x00da A[SYNTHETIC, Splitter:B:59:0x00da] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bitcoinj.core.StoredUndoableBlock getUndoBlock(org.bitcoinj.core.Sha256Hash r9) throws org.bitcoinj.store.BlockStoreException {
        /*
            r8 = this;
            java.lang.String r0 = "Failed to close PreparedStatement"
            r8.maybeConnect()
            r1 = 0
            java.lang.ThreadLocal<java.sql.Connection> r2 = r8.conn     // Catch:{ SQLException -> 0x00d1, NullPointerException -> 0x00ca, ClassCastException -> 0x00c3, ProtocolException -> 0x00bc, IOException -> 0x00b5 }
            java.lang.Object r2 = r2.get()     // Catch:{ SQLException -> 0x00d1, NullPointerException -> 0x00ca, ClassCastException -> 0x00c3, ProtocolException -> 0x00bc, IOException -> 0x00b5 }
            java.sql.Connection r2 = (java.sql.Connection) r2     // Catch:{ SQLException -> 0x00d1, NullPointerException -> 0x00ca, ClassCastException -> 0x00c3, ProtocolException -> 0x00bc, IOException -> 0x00b5 }
            java.lang.String r3 = r8.getSelectUndoableBlocksSQL()     // Catch:{ SQLException -> 0x00d1, NullPointerException -> 0x00ca, ClassCastException -> 0x00c3, ProtocolException -> 0x00bc, IOException -> 0x00b5 }
            java.sql.PreparedStatement r2 = r2.prepareStatement(r3)     // Catch:{ SQLException -> 0x00d1, NullPointerException -> 0x00ca, ClassCastException -> 0x00c3, ProtocolException -> 0x00bc, IOException -> 0x00b5 }
            r3 = 28
            byte[] r4 = new byte[r3]     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            byte[] r5 = r9.getBytes()     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r6 = 4
            r7 = 0
            java.lang.System.arraycopy(r5, r6, r4, r7, r3)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r3 = 1
            r2.setBytes(r3, r4)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            java.sql.ResultSet r4 = r2.executeQuery()     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            boolean r5 = r4.next()     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            if (r5 != 0) goto L_0x003e
            if (r2 == 0) goto L_0x003d
            r2.close()     // Catch:{ SQLException -> 0x0037 }
            goto L_0x003d
        L_0x0037:
            org.bitcoinj.store.BlockStoreException r9 = new org.bitcoinj.store.BlockStoreException
            r9.<init>(r0)
            throw r9
        L_0x003d:
            return r1
        L_0x003e:
            byte[] r1 = r4.getBytes(r3)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r5 = 2
            byte[] r4 = r4.getBytes(r5)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            if (r1 != 0) goto L_0x0085
            byte r1 = r4[r7]     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r3 = r4[r3]     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 8
            r1 = r1 | r3
            r3 = 3
            byte r5 = r4[r5]     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << 16
            r1 = r1 | r5
            byte r3 = r4[r3]     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 24
            r1 = r1 | r3
            java.util.LinkedList r3 = new java.util.LinkedList     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r3.<init>()     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
        L_0x0068:
            if (r7 >= r1) goto L_0x007f
            org.bitcoinj.core.NetworkParameters r5 = r8.params     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            org.bitcoinj.core.MessageSerializer r5 = r5.getDefaultSerializer()     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            org.bitcoinj.core.Transaction r5 = r5.makeTransaction(r4, r6)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r3.add(r5)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            int r5 = r5.getMessageSize()     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            int r6 = r6 + r5
            int r7 = r7 + 1
            goto L_0x0068
        L_0x007f:
            org.bitcoinj.core.StoredUndoableBlock r1 = new org.bitcoinj.core.StoredUndoableBlock     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r1.<init>(r9, r3)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            goto L_0x0094
        L_0x0085:
            org.bitcoinj.core.TransactionOutputChanges r3 = new org.bitcoinj.core.TransactionOutputChanges     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r4.<init>(r1)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r3.<init>(r4)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            org.bitcoinj.core.StoredUndoableBlock r1 = new org.bitcoinj.core.StoredUndoableBlock     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
            r1.<init>(r9, r3)     // Catch:{ SQLException -> 0x00af, NullPointerException -> 0x00ac, ClassCastException -> 0x00a9, ProtocolException -> 0x00a6, IOException -> 0x00a3, all -> 0x00a1 }
        L_0x0094:
            if (r2 == 0) goto L_0x00a0
            r2.close()     // Catch:{ SQLException -> 0x009a }
            goto L_0x00a0
        L_0x009a:
            org.bitcoinj.store.BlockStoreException r9 = new org.bitcoinj.store.BlockStoreException
            r9.<init>(r0)
            throw r9
        L_0x00a0:
            return r1
        L_0x00a1:
            r9 = move-exception
            goto L_0x00d8
        L_0x00a3:
            r9 = move-exception
            r1 = r2
            goto L_0x00b6
        L_0x00a6:
            r9 = move-exception
            r1 = r2
            goto L_0x00bd
        L_0x00a9:
            r9 = move-exception
            r1 = r2
            goto L_0x00c4
        L_0x00ac:
            r9 = move-exception
            r1 = r2
            goto L_0x00cb
        L_0x00af:
            r9 = move-exception
            r1 = r2
            goto L_0x00d2
        L_0x00b2:
            r9 = move-exception
            r2 = r1
            goto L_0x00d8
        L_0x00b5:
            r9 = move-exception
        L_0x00b6:
            org.bitcoinj.store.BlockStoreException r2 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00b2 }
            r2.<init>(r9)     // Catch:{ all -> 0x00b2 }
            throw r2     // Catch:{ all -> 0x00b2 }
        L_0x00bc:
            r9 = move-exception
        L_0x00bd:
            org.bitcoinj.store.BlockStoreException r2 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00b2 }
            r2.<init>(r9)     // Catch:{ all -> 0x00b2 }
            throw r2     // Catch:{ all -> 0x00b2 }
        L_0x00c3:
            r9 = move-exception
        L_0x00c4:
            org.bitcoinj.store.BlockStoreException r2 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00b2 }
            r2.<init>(r9)     // Catch:{ all -> 0x00b2 }
            throw r2     // Catch:{ all -> 0x00b2 }
        L_0x00ca:
            r9 = move-exception
        L_0x00cb:
            org.bitcoinj.store.BlockStoreException r2 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00b2 }
            r2.<init>(r9)     // Catch:{ all -> 0x00b2 }
            throw r2     // Catch:{ all -> 0x00b2 }
        L_0x00d1:
            r9 = move-exception
        L_0x00d2:
            org.bitcoinj.store.BlockStoreException r2 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x00b2 }
            r2.<init>(r9)     // Catch:{ all -> 0x00b2 }
            throw r2     // Catch:{ all -> 0x00b2 }
        L_0x00d8:
            if (r2 == 0) goto L_0x00e4
            r2.close()     // Catch:{ SQLException -> 0x00de }
            goto L_0x00e4
        L_0x00de:
            org.bitcoinj.store.BlockStoreException r9 = new org.bitcoinj.store.BlockStoreException
            r9.<init>(r0)
            throw r9
        L_0x00e4:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.DatabaseFullPrunedBlockStore.getUndoBlock(org.bitcoinj.core.Sha256Hash):org.bitcoinj.core.StoredUndoableBlock");
    }

    public StoredBlock getChainHead() throws BlockStoreException {
        return this.chainHeadBlock;
    }

    public void setChainHead(StoredBlock storedBlock) throws BlockStoreException {
        Sha256Hash hash = storedBlock.getHeader().getHash();
        this.chainHeadHash = hash;
        this.chainHeadBlock = storedBlock;
        maybeConnect();
        try {
            PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getUpdateSettingsSLQ());
            prepareStatement.setString(2, CHAIN_HEAD_SETTING);
            prepareStatement.setBytes(1, hash.getBytes());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    public StoredBlock getVerifiedChainHead() throws BlockStoreException {
        return this.verifiedChainHeadBlock;
    }

    public void setVerifiedChainHead(StoredBlock storedBlock) throws BlockStoreException {
        Sha256Hash hash = storedBlock.getHeader().getHash();
        this.verifiedChainHeadHash = hash;
        this.verifiedChainHeadBlock = storedBlock;
        maybeConnect();
        try {
            PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getUpdateSettingsSLQ());
            prepareStatement.setString(2, VERIFIED_CHAIN_HEAD_SETTING);
            prepareStatement.setBytes(1, hash.getBytes());
            prepareStatement.executeUpdate();
            prepareStatement.close();
            if (this.chainHeadBlock.getHeight() < storedBlock.getHeight()) {
                setChainHead(storedBlock);
            }
            removeUndoableBlocksWhereHeightIsLessThan(storedBlock.getHeight() - this.fullStoreDepth);
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    private void removeUndoableBlocksWhereHeightIsLessThan(int i) throws BlockStoreException {
        try {
            PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getDeleteUndoableBlocksSQL());
            prepareStatement.setInt(1, i);
            if (log.isDebugEnabled()) {
                Logger logger = log;
                StringBuilder sb = new StringBuilder();
                sb.append("Deleting undoable undoable block with height <= ");
                sb.append(i);
                logger.debug(sb.toString());
            }
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0085 A[SYNTHETIC, Splitter:B:32:0x0085] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bitcoinj.core.UTXO getTransactionOutput(org.bitcoinj.core.Sha256Hash r16, long r17) throws org.bitcoinj.store.BlockStoreException {
        /*
            r15 = this;
            java.lang.String r1 = "Failed to close PreparedStatement"
            r15.maybeConnect()
            r2 = 0
            r3 = r15
            java.lang.ThreadLocal<java.sql.Connection> r0 = r3.conn     // Catch:{ SQLException -> 0x007c }
            java.lang.Object r0 = r0.get()     // Catch:{ SQLException -> 0x007c }
            java.sql.Connection r0 = (java.sql.Connection) r0     // Catch:{ SQLException -> 0x007c }
            java.lang.String r4 = r15.getSelectOpenoutputsSQL()     // Catch:{ SQLException -> 0x007c }
            java.sql.PreparedStatement r4 = r0.prepareStatement(r4)     // Catch:{ SQLException -> 0x007c }
            byte[] r0 = r16.getBytes()     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r5 = 1
            r4.setBytes(r5, r0)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r8 = r17
            int r0 = (int) r8     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r6 = 2
            r4.setInt(r6, r0)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            java.sql.ResultSet r0 = r4.executeQuery()     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            boolean r7 = r0.next()     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            if (r7 != 0) goto L_0x003d
            if (r4 == 0) goto L_0x003c
            r4.close()     // Catch:{ SQLException -> 0x0036 }
            goto L_0x003c
        L_0x0036:
            org.bitcoinj.store.BlockStoreException r0 = new org.bitcoinj.store.BlockStoreException
            r0.<init>(r1)
            throw r0
        L_0x003c:
            return r2
        L_0x003d:
            int r11 = r0.getInt(r5)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            long r5 = r0.getLong(r6)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            org.bitcoinj.core.Coin r10 = org.bitcoinj.core.Coin.valueOf(r5)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r2 = 3
            byte[] r2 = r0.getBytes(r2)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r5 = 4
            boolean r12 = r0.getBoolean(r5)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r5 = 5
            java.lang.String r14 = r0.getString(r5)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            org.bitcoinj.core.UTXO r0 = new org.bitcoinj.core.UTXO     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            org.bitcoinj.script.Script r13 = new org.bitcoinj.script.Script     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r13.<init>(r2)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            r6 = r0
            r7 = r16
            r8 = r17
            r6.<init>(r7, r8, r10, r11, r12, r13, r14)     // Catch:{ SQLException -> 0x0076, all -> 0x0074 }
            if (r4 == 0) goto L_0x0073
            r4.close()     // Catch:{ SQLException -> 0x006d }
            goto L_0x0073
        L_0x006d:
            org.bitcoinj.store.BlockStoreException r0 = new org.bitcoinj.store.BlockStoreException
            r0.<init>(r1)
            throw r0
        L_0x0073:
            return r0
        L_0x0074:
            r0 = move-exception
            goto L_0x0083
        L_0x0076:
            r0 = move-exception
            r2 = r4
            goto L_0x007d
        L_0x0079:
            r0 = move-exception
            r4 = r2
            goto L_0x0083
        L_0x007c:
            r0 = move-exception
        L_0x007d:
            org.bitcoinj.store.BlockStoreException r4 = new org.bitcoinj.store.BlockStoreException     // Catch:{ all -> 0x0079 }
            r4.<init>(r0)     // Catch:{ all -> 0x0079 }
            throw r4     // Catch:{ all -> 0x0079 }
        L_0x0083:
            if (r4 == 0) goto L_0x008f
            r4.close()     // Catch:{ SQLException -> 0x0089 }
            goto L_0x008f
        L_0x0089:
            org.bitcoinj.store.BlockStoreException r0 = new org.bitcoinj.store.BlockStoreException
            r0.<init>(r1)
            throw r0
        L_0x008f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.DatabaseFullPrunedBlockStore.getTransactionOutput(org.bitcoinj.core.Sha256Hash, long):org.bitcoinj.core.UTXO");
    }

    public void addUnspentTransactionOutput(UTXO utxo) throws BlockStoreException {
        maybeConnect();
        boolean z = null;
        try {
            z = ((Connection) this.conn.get()).prepareStatement(getInsertOpenoutputsSQL());
            z.setBytes(1, utxo.getHash().getBytes());
            z.setInt(2, (int) utxo.getIndex());
            z.setInt(3, utxo.getHeight());
            z.setLong(4, utxo.getValue().value);
            z.setBytes(5, utxo.getScript().getProgram());
            z.setString(6, utxo.getAddress());
            z.setInt(7, utxo.getScript().getScriptType().ordinal());
            z.setBoolean(8, utxo.isCoinbase());
            z.executeUpdate();
            z.close();
            if (z != null) {
                try {
                    z.close();
                } catch (SQLException e) {
                    throw new BlockStoreException((Throwable) e);
                }
            }
        } catch (SQLException e2) {
            z = e2.getSQLState().equals(getDuplicateKeyErrorCode());
            if (!z) {
                throw new BlockStoreException((Throwable) e2);
            } else if (z != null) {
                try {
                    z.close();
                } catch (SQLException e3) {
                    throw new BlockStoreException((Throwable) e3);
                }
            }
        } finally {
            if (z != null) {
                try {
                    z.close();
                } catch (SQLException e4) {
                    throw new BlockStoreException((Throwable) e4);
                }
            }
        }
    }

    public void removeUnspentTransactionOutput(UTXO utxo) throws BlockStoreException {
        maybeConnect();
        if (getTransactionOutput(utxo.getHash(), utxo.getIndex()) != null) {
            try {
                PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getDeleteOpenoutputsSQL());
                prepareStatement.setBytes(1, utxo.getHash().getBytes());
                prepareStatement.setInt(2, (int) utxo.getIndex());
                prepareStatement.executeUpdate();
                prepareStatement.close();
            } catch (SQLException e) {
                throw new BlockStoreException((Throwable) e);
            }
        } else {
            throw new BlockStoreException("Tried to remove a UTXO from DatabaseFullPrunedBlockStore that it didn't have!");
        }
    }

    public void beginDatabaseBatchWrite() throws BlockStoreException {
        maybeConnect();
        if (log.isDebugEnabled()) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("Starting database batch write with connection: ");
            sb.append(((Connection) this.conn.get()).toString());
            logger.debug(sb.toString());
        }
        try {
            ((Connection) this.conn.get()).setAutoCommit(false);
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    public void commitDatabaseBatchWrite() throws BlockStoreException {
        maybeConnect();
        if (log.isDebugEnabled()) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("Committing database batch write with connection: ");
            sb.append(((Connection) this.conn.get()).toString());
            logger.debug(sb.toString());
        }
        try {
            ((Connection) this.conn.get()).commit();
            ((Connection) this.conn.get()).setAutoCommit(true);
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    public void abortDatabaseBatchWrite() throws BlockStoreException {
        maybeConnect();
        if (log.isDebugEnabled()) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("Rollback database batch write with connection: ");
            sb.append(((Connection) this.conn.get()).toString());
            logger.debug(sb.toString());
        }
        try {
            if (!((Connection) this.conn.get()).getAutoCommit()) {
                ((Connection) this.conn.get()).rollback();
                ((Connection) this.conn.get()).setAutoCommit(true);
                return;
            }
            log.warn("Warning: Rollback attempt without transaction");
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    public boolean hasUnspentOutputs(Sha256Hash sha256Hash, int i) throws BlockStoreException {
        String str = "Failed to close PreparedStatement";
        maybeConnect();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ((Connection) this.conn.get()).prepareStatement(getSelectOpenoutputsCountSQL());
            boolean z = true;
            preparedStatement.setBytes(1, sha256Hash.getBytes());
            ResultSet executeQuery = preparedStatement.executeQuery();
            if (executeQuery.next()) {
                if (executeQuery.getInt(1) == 0) {
                    z = false;
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException unused) {
                        throw new BlockStoreException(str);
                    }
                }
                return z;
            }
            throw new BlockStoreException("Got no results from a COUNT(*) query");
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        } catch (Throwable th) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException unused2) {
                    throw new BlockStoreException(str);
                }
            }
            throw th;
        }
    }

    public NetworkParameters getParams() {
        return this.params;
    }

    public int getChainHeadHeight() throws UTXOProviderException {
        try {
            return getVerifiedChainHead().getHeight();
        } catch (BlockStoreException e) {
            throw new UTXOProviderException((Throwable) e);
        }
    }

    public void resetStore() throws BlockStoreException {
        maybeConnect();
        try {
            deleteStore();
            createTables();
            initFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStore() throws BlockStoreException {
        maybeConnect();
        try {
            Statement createStatement = ((Connection) this.conn.get()).createStatement();
            for (String execute : getDropTablesSQL()) {
                createStatement.execute(execute);
            }
            createStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BigInteger calculateBalanceForAddress(Address address) throws BlockStoreException {
        String str = "Could not close statement";
        maybeConnect();
        PreparedStatement preparedStatement = null;
        try {
            PreparedStatement prepareStatement = ((Connection) this.conn.get()).prepareStatement(getBalanceSelectSQL());
            prepareStatement.setString(1, address.toString());
            ResultSet executeQuery = prepareStatement.executeQuery();
            BigInteger bigInteger = BigInteger.ZERO;
            if (executeQuery.next()) {
                BigInteger valueOf = BigInteger.valueOf(executeQuery.getLong(1));
                if (prepareStatement != null) {
                    try {
                        prepareStatement.close();
                    } catch (SQLException unused) {
                        throw new BlockStoreException(str);
                    }
                }
                return valueOf;
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException unused2) {
                    throw new BlockStoreException(str);
                }
            }
            return bigInteger;
        } catch (SQLException e) {
            throw new BlockStoreException((Throwable) e);
        } catch (Throwable th) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException unused3) {
                    throw new BlockStoreException(str);
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00aa A[SYNTHETIC, Splitter:B:35:0x00aa] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<org.bitcoinj.core.UTXO> getOpenTransactionOutputs(java.util.List<org.bitcoinj.core.Address> r18) throws org.bitcoinj.core.UTXOProviderException {
        /*
            r17 = this;
            java.lang.String r1 = "Could not close statement"
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = 0
            r17.maybeConnect()     // Catch:{ SQLException -> 0x009f, BlockStoreException -> 0x0096, all -> 0x0092 }
            r3 = r17
            java.lang.ThreadLocal<java.sql.Connection> r4 = r3.conn     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            java.lang.Object r4 = r4.get()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            java.sql.Connection r4 = (java.sql.Connection) r4     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            java.lang.String r5 = r17.getTrasactionOutputSelectSQL()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            java.sql.PreparedStatement r2 = r4.prepareStatement(r5)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            java.util.Iterator r4 = r18.iterator()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
        L_0x0021:
            boolean r5 = r4.hasNext()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            if (r5 == 0) goto L_0x007d
            java.lang.Object r5 = r4.next()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            org.bitcoinj.core.Address r5 = (org.bitcoinj.core.Address) r5     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r6 = 1
            r2.setString(r6, r5)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            java.sql.ResultSet r5 = r2.executeQuery()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
        L_0x0039:
            boolean r7 = r5.next()     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            if (r7 == 0) goto L_0x0021
            byte[] r7 = r5.getBytes(r6)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            org.bitcoinj.core.Sha256Hash r9 = org.bitcoinj.core.Sha256Hash.wrap(r7)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r7 = 2
            long r7 = r5.getLong(r7)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            org.bitcoinj.core.Coin r12 = org.bitcoinj.core.Coin.valueOf(r7)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r7 = 3
            byte[] r7 = r5.getBytes(r7)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r8 = 4
            int r13 = r5.getInt(r8)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r8 = 5
            int r8 = r5.getInt(r8)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r10 = 6
            boolean r14 = r5.getBoolean(r10)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r10 = 7
            java.lang.String r16 = r5.getString(r10)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            org.bitcoinj.core.UTXO r15 = new org.bitcoinj.core.UTXO     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            long r10 = (long) r8     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            org.bitcoinj.script.Script r8 = new org.bitcoinj.script.Script     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r8.<init>(r7)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r7 = r8
            r8 = r15
            r6 = r15
            r15 = r7
            r8.<init>(r9, r10, r12, r13, r14, r15, r16)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r0.add(r6)     // Catch:{ SQLException -> 0x0090, BlockStoreException -> 0x008e }
            r6 = 1
            goto L_0x0039
        L_0x007d:
            if (r2 == 0) goto L_0x008b
            r2.close()     // Catch:{ SQLException -> 0x0083 }
            goto L_0x008b
        L_0x0083:
            r0 = move-exception
            r2 = r0
            org.bitcoinj.core.UTXOProviderException r0 = new org.bitcoinj.core.UTXOProviderException
            r0.<init>(r1, r2)
            throw r0
        L_0x008b:
            return r0
        L_0x008c:
            r0 = move-exception
            goto L_0x00a8
        L_0x008e:
            r0 = move-exception
            goto L_0x0099
        L_0x0090:
            r0 = move-exception
            goto L_0x00a2
        L_0x0092:
            r0 = move-exception
            r3 = r17
            goto L_0x00a8
        L_0x0096:
            r0 = move-exception
            r3 = r17
        L_0x0099:
            org.bitcoinj.core.UTXOProviderException r4 = new org.bitcoinj.core.UTXOProviderException     // Catch:{ all -> 0x008c }
            r4.<init>(r0)     // Catch:{ all -> 0x008c }
            throw r4     // Catch:{ all -> 0x008c }
        L_0x009f:
            r0 = move-exception
            r3 = r17
        L_0x00a2:
            org.bitcoinj.core.UTXOProviderException r4 = new org.bitcoinj.core.UTXOProviderException     // Catch:{ all -> 0x008c }
            r4.<init>(r0)     // Catch:{ all -> 0x008c }
            throw r4     // Catch:{ all -> 0x008c }
        L_0x00a8:
            if (r2 == 0) goto L_0x00b6
            r2.close()     // Catch:{ SQLException -> 0x00ae }
            goto L_0x00b6
        L_0x00ae:
            r0 = move-exception
            r2 = r0
            org.bitcoinj.core.UTXOProviderException r0 = new org.bitcoinj.core.UTXOProviderException
            r0.<init>(r1, r2)
            throw r0
        L_0x00b6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.DatabaseFullPrunedBlockStore.getOpenTransactionOutputs(java.util.List):java.util.List");
    }

    public void dumpSizes() throws SQLException, BlockStoreException {
        int i;
        maybeConnect();
        Statement createStatement = ((Connection) this.conn.get()).createStatement();
        ResultSet executeQuery = createStatement.executeQuery(getSelectSettingsDumpSQL());
        long j = 0;
        int i2 = 0;
        while (executeQuery.next()) {
            j = j + ((long) executeQuery.getString(1).length()) + ((long) executeQuery.getBytes(2).length);
            i2++;
        }
        executeQuery.close();
        System.out.printf(Locale.US, "Settings size: %d, count: %d, average size: %f%n", new Object[]{Long.valueOf(j), Integer.valueOf(i2), Double.valueOf(((double) j) / ((double) i2))});
        long j2 = j + 0;
        ResultSet executeQuery2 = createStatement.executeQuery(getSelectHeadersDumpSQL());
        long j3 = 0;
        int i3 = 0;
        while (executeQuery2.next()) {
            j3 = j3 + 28 + ((long) executeQuery2.getBytes(1).length) + 4 + ((long) executeQuery2.getBytes(2).length);
            i3++;
        }
        executeQuery2.close();
        long j4 = j2;
        System.out.printf(Locale.US, "Headers size: %d, count: %d, average size: %f%n", new Object[]{Long.valueOf(j3), Integer.valueOf(i3), Double.valueOf(((double) j3) / ((double) i3))});
        long j5 = j4 + j3;
        ResultSet executeQuery3 = createStatement.executeQuery(getSelectUndoableblocksDumpSQL());
        int i4 = 0;
        long j6 = 0;
        while (executeQuery3.next()) {
            long j7 = j6 + 28 + 4;
            byte[] bytes = executeQuery3.getBytes(1);
            byte[] bytes2 = executeQuery3.getBytes(2);
            if (bytes == null) {
                i = bytes2.length;
            } else {
                i = bytes.length;
            }
            j6 = j7 + ((long) i);
            i4++;
        }
        executeQuery3.close();
        System.out.printf(Locale.US, "Undoable Blocks size: %d, count: %d, average size: %f%n", new Object[]{Long.valueOf(j6), Integer.valueOf(i4), Double.valueOf(((double) j6) / ((double) i4))});
        long j8 = j5 + j6;
        ResultSet executeQuery4 = createStatement.executeQuery(getSelectopenoutputsDumpSQL());
        long j9 = 0;
        int i5 = 0;
        long j10 = 0;
        while (executeQuery4.next()) {
            j9 = j9 + 32 + 4 + 4 + ((long) executeQuery4.getBytes(1).length) + ((long) executeQuery4.getBytes(2).length);
            j10 += (long) executeQuery4.getBytes(2).length;
            i5++;
        }
        executeQuery4.close();
        double d = (double) i5;
        Object[] objArr = {Long.valueOf(j9), Integer.valueOf(i5), Double.valueOf(((double) j9) / d), Double.valueOf(((double) j10) / d), Integer.valueOf(i5 * 8)};
        System.out.printf(Locale.US, "Open Outputs size: %d, count: %d, average size: %f, average script size: %f (%d in id indexes)%n", objArr);
        long j11 = j8 + j9;
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("Total Size: ");
        sb.append(j11);
        printStream.println(sb.toString());
        createStatement.close();
    }
}
