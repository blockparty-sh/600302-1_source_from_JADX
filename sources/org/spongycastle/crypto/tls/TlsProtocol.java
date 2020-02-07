package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.RandomGenerator;
import org.spongycastle.crypto.tls.SessionParameters.Builder;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public abstract class TlsProtocol {
    protected static final short CS_CERTIFICATE_REQUEST = 7;
    protected static final short CS_CERTIFICATE_STATUS = 5;
    protected static final short CS_CERTIFICATE_VERIFY = 12;
    protected static final short CS_CLIENT_CERTIFICATE = 10;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 11;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    protected static final short CS_END = 16;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_SERVER_FINISHED = 15;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_HELLO_DONE = 8;
    protected static final short CS_SERVER_KEY_EXCHANGE = 6;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_START = 0;
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    private static final String TLS_ERROR_MESSAGE = "Internal TLS error, this could be an attack";
    private ByteQueue alertQueue = new ByteQueue(2);
    protected boolean allowCertificateStatus = false;
    private volatile boolean appDataReady = false;
    private ByteQueue applicationDataQueue = new ByteQueue();
    protected Hashtable clientExtensions = null;
    private volatile boolean closed = false;
    protected short connection_state = 0;
    protected boolean expectSessionTicket = false;
    private byte[] expected_verify_data = null;
    private volatile boolean failedWithError = false;
    private ByteQueue handshakeQueue = new ByteQueue();
    protected int[] offeredCipherSuites = null;
    protected short[] offeredCompressionMethods = null;
    protected Certificate peerCertificate = null;
    protected boolean receivedChangeCipherSpec = false;
    protected RecordStream recordStream;
    protected boolean resumedSession = false;
    protected SecureRandom secureRandom;
    protected boolean secure_renegotiation = false;
    protected SecurityParameters securityParameters = null;
    protected Hashtable serverExtensions = null;
    protected SessionParameters sessionParameters = null;
    private volatile boolean splitApplicationDataRecords = true;
    private TlsInputStream tlsInputStream = null;
    private TlsOutputStream tlsOutputStream = null;
    protected TlsSession tlsSession = null;

    class HandshakeMessage extends ByteArrayOutputStream {
        HandshakeMessage(TlsProtocol tlsProtocol, short s) throws IOException {
            this(s, 60);
        }

        HandshakeMessage(short s, int i) throws IOException {
            super(i + 4);
            TlsUtils.writeUint8(s, (OutputStream) this);
            this.count += 3;
        }

        /* access modifiers changed from: 0000 */
        public void writeToRecordStream() throws IOException {
            int i = this.count - 4;
            TlsUtils.checkUint24(i);
            TlsUtils.writeUint24(i, this.buf, 1);
            TlsProtocol.this.writeHandshakeMessage(this.buf, 0, this.count);
            this.buf = null;
        }
    }

    private void processApplicationData() {
    }

    /* access modifiers changed from: protected */
    public abstract AbstractTlsContext getContext();

    /* access modifiers changed from: protected */
    public abstract TlsPeer getPeer();

    /* access modifiers changed from: protected */
    public void handleChangeCipherSpecMessage() throws IOException {
    }

    /* access modifiers changed from: protected */
    public abstract void handleHandshakeMessage(short s, byte[] bArr) throws IOException;

    /* access modifiers changed from: protected */
    public void handleWarningMessage(short s) throws IOException {
    }

    public TlsProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom2) {
        this.recordStream = new RecordStream(this, inputStream, outputStream);
        this.secureRandom = secureRandom2;
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        byte[] bArr = this.expected_verify_data;
        if (bArr != null) {
            Arrays.fill(bArr, 0);
            this.expected_verify_data = null;
        }
        this.securityParameters.clear();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    /* access modifiers changed from: protected */
    public void completeHandshake() throws IOException {
        while (this.connection_state != 16) {
            try {
                boolean z = this.closed;
                safeReadRecord();
            } finally {
                cleanupHandshake();
            }
        }
        this.recordStream.finaliseHandshake();
        this.splitApplicationDataRecords = !TlsUtils.isTLSv11(getContext());
        if (!this.appDataReady) {
            this.appDataReady = true;
            this.tlsInputStream = new TlsInputStream(this);
            this.tlsOutputStream = new TlsOutputStream(this);
        }
        if (this.tlsSession != null) {
            if (this.sessionParameters == null) {
                this.sessionParameters = new Builder().setCipherSuite(this.securityParameters.cipherSuite).setCompressionAlgorithm(this.securityParameters.compressionAlgorithm).setMasterSecret(this.securityParameters.masterSecret).setPeerCertificate(this.peerCertificate).setServerExtensions(this.serverExtensions).build();
                this.tlsSession = new TlsSessionImpl(this.tlsSession.getSessionID(), this.sessionParameters);
            }
            getContext().setResumableSession(this.tlsSession);
        }
        getPeer().notifyHandshakeComplete();
    }

    /* access modifiers changed from: protected */
    public void processRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        switch (s) {
            case 20:
                processChangeCipherSpec(bArr, i, i2);
                return;
            case 21:
                this.alertQueue.addData(bArr, i, i2);
                processAlert();
                return;
            case 22:
                this.handshakeQueue.addData(bArr, i, i2);
                processHandshake();
                return;
            case 23:
                if (this.appDataReady) {
                    this.applicationDataQueue.addData(bArr, i, i2);
                    processApplicationData();
                    return;
                }
                throw new TlsFatalAlert(10);
            case 24:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert(10);
                }
                return;
            default:
                return;
        }
    }

    private void processHandshake() throws IOException {
        boolean z;
        do {
            z = true;
            if (this.handshakeQueue.size() >= 4) {
                byte[] bArr = new byte[4];
                this.handshakeQueue.read(bArr, 0, 4, 0);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
                int readUint24 = TlsUtils.readUint24(byteArrayInputStream);
                if (this.handshakeQueue.size() >= readUint24 + 4) {
                    byte[] removeData = this.handshakeQueue.removeData(readUint24, 4);
                    if (readUint8 != 0) {
                        if (readUint8 == 20 && this.expected_verify_data == null) {
                            this.expected_verify_data = createVerifyData(!getContext().isServer());
                        }
                        this.recordStream.updateHandshakeData(bArr, 0, 4);
                        this.recordStream.updateHandshakeData(removeData, 0, readUint24);
                    }
                    handleHandshakeMessage(readUint8, removeData);
                    continue;
                }
            }
            z = false;
            continue;
        } while (z);
    }

    private void processAlert() throws IOException {
        while (this.alertQueue.size() >= 2) {
            byte[] removeData = this.alertQueue.removeData(2, 0);
            short s = (short) removeData[0];
            short s2 = (short) removeData[1];
            getPeer().notifyAlertReceived(s, s2);
            if (s != 2) {
                if (s2 == 0) {
                    handleClose(false);
                }
                handleWarningMessage(s2);
            } else {
                invalidateSession();
                this.failedWithError = true;
                this.closed = true;
                this.recordStream.safeClose();
                throw new IOException(TLS_ERROR_MESSAGE);
            }
        }
    }

    private void processChangeCipherSpec(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            if (TlsUtils.readUint8(bArr, i + i3) != 1) {
                throw new TlsFatalAlert(50);
            } else if (this.receivedChangeCipherSpec || this.alertQueue.size() > 0 || this.handshakeQueue.size() > 0) {
                throw new TlsFatalAlert(10);
            } else {
                this.recordStream.receivedReadCipherSpec();
                this.receivedChangeCipherSpec = true;
                handleChangeCipherSpecMessage();
                i3++;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int applicationDataAvailable() throws IOException {
        return this.applicationDataQueue.size();
    }

    /* access modifiers changed from: protected */
    public int readApplicationData(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.size() == 0) {
            if (!this.closed) {
                safeReadRecord();
            } else if (!this.failedWithError) {
                return -1;
            } else {
                throw new IOException(TLS_ERROR_MESSAGE);
            }
        }
        int min = Math.min(i2, this.applicationDataQueue.size());
        this.applicationDataQueue.removeData(bArr, i, min, 0);
        return min;
    }

    /* access modifiers changed from: protected */
    public void safeReadRecord() throws IOException {
        String str = "Failed to read record";
        try {
            if (!this.recordStream.readRecord()) {
                throw new EOFException();
            }
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), str, e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, str, e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, str, e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void safeWriteRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        String str = "Failed to write record";
        try {
            this.recordStream.writeRecord(s, bArr, i, i2);
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), str, e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, str, e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, str, e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void writeData(byte[] bArr, int i, int i2) throws IOException {
        if (!this.closed) {
            while (i2 > 0) {
                if (this.splitApplicationDataRecords) {
                    safeWriteRecord(23, bArr, i, 1);
                    i++;
                    i2--;
                }
                if (i2 > 0) {
                    int min = Math.min(i2, this.recordStream.getPlaintextLimit());
                    safeWriteRecord(23, bArr, i, min);
                    i += min;
                    i2 -= min;
                }
            }
        } else if (this.failedWithError) {
            throw new IOException(TLS_ERROR_MESSAGE);
        } else {
            throw new IOException("Sorry, connection has been closed, you cannot write more data");
        }
    }

    /* access modifiers changed from: protected */
    public void writeHandshakeMessage(byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int min = Math.min(i2, this.recordStream.getPlaintextLimit());
            safeWriteRecord(22, bArr, i, min);
            i += min;
            i2 -= min;
        }
    }

    public OutputStream getOutputStream() {
        return this.tlsOutputStream;
    }

    public InputStream getInputStream() {
        return this.tlsInputStream;
    }

    /* access modifiers changed from: protected */
    public void failWithError(short s, short s2, String str, Exception exc) throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (s == 2) {
                invalidateSession();
                this.failedWithError = true;
            }
            raiseAlert(s, s2, str, exc);
            this.recordStream.safeClose();
            if (s != 2) {
                return;
            }
        }
        throw new IOException(TLS_ERROR_MESSAGE);
    }

    /* access modifiers changed from: protected */
    public void invalidateSession() {
        SessionParameters sessionParameters2 = this.sessionParameters;
        if (sessionParameters2 != null) {
            sessionParameters2.clear();
            this.sessionParameters = null;
        }
        TlsSession tlsSession2 = this.tlsSession;
        if (tlsSession2 != null) {
            tlsSession2.invalidate();
            this.tlsSession = null;
        }
    }

    /* access modifiers changed from: protected */
    public void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] readFully = TlsUtils.readFully(this.expected_verify_data.length, (InputStream) byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(this.expected_verify_data, readFully)) {
            throw new TlsFatalAlert(51);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseAlert(short s, short s2, String str, Exception exc) throws IOException {
        getPeer().notifyAlertRaised(s, s2, str, exc);
        safeWriteRecord(21, new byte[]{(byte) s, (byte) s2}, 0, 2);
    }

    /* access modifiers changed from: protected */
    public void raiseWarning(short s, String str) throws IOException {
        raiseAlert(1, s, str, null);
    }

    /* access modifiers changed from: protected */
    public void sendCertificateMessage(Certificate certificate) throws IOException {
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.getLength() == 0 && !getContext().isServer()) {
            ProtocolVersion serverVersion = getContext().getServerVersion();
            if (serverVersion.isSSL()) {
                StringBuilder sb = new StringBuilder();
                sb.append(serverVersion.toString());
                sb.append(" client didn't provide credentials");
                raiseWarning(41, sb.toString());
                return;
            }
        }
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 11);
        certificate.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendChangeCipherSpecMessage() throws IOException {
        byte[] bArr = {1};
        safeWriteRecord(20, bArr, 0, bArr.length);
        this.recordStream.sentWriteCipherSpec();
    }

    /* access modifiers changed from: protected */
    public void sendFinishedMessage() throws IOException {
        byte[] createVerifyData = createVerifyData(getContext().isServer());
        HandshakeMessage handshakeMessage = new HandshakeMessage(20, createVerifyData.length);
        handshakeMessage.write(createVerifyData);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendSupplementalDataMessage(Vector vector) throws IOException {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 23);
        writeSupplementalData(handshakeMessage, vector);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public byte[] createVerifyData(boolean z) {
        AbstractTlsContext context = getContext();
        if (z) {
            return TlsUtils.calculateVerifyData(context, ExporterLabel.server_finished, getCurrentPRFHash(getContext(), this.recordStream.getHandshakeHash(), TlsUtils.SSL_SERVER));
        }
        return TlsUtils.calculateVerifyData(context, ExporterLabel.client_finished, getCurrentPRFHash(getContext(), this.recordStream.getHandshakeHash(), TlsUtils.SSL_CLIENT));
    }

    public void close() throws IOException {
        handleClose(true);
    }

    /* access modifiers changed from: protected */
    public void handleClose(boolean z) throws IOException {
        if (!this.closed) {
            if (z && !this.appDataReady) {
                raiseWarning(90, "User canceled handshake");
            }
            failWithError(1, 0, "Connection closed", null);
        }
    }

    /* access modifiers changed from: protected */
    public void flush() throws IOException {
        this.recordStream.flush();
    }

    /* access modifiers changed from: protected */
    public short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || this.resumedSession || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    protected static void assertEmpty(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert(50);
        }
    }

    protected static byte[] createRandomBlock(boolean z, RandomGenerator randomGenerator) {
        byte[] bArr = new byte[32];
        randomGenerator.nextBytes(bArr);
        if (z) {
            TlsUtils.writeGMTUnixTime(bArr, 0);
        }
        return bArr;
    }

    protected static byte[] createRenegotiationInfo(byte[] bArr) throws IOException {
        return TlsUtils.encodeOpaque8(bArr);
    }

    protected static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) throws IOException {
        byte[] generatePremasterSecret = tlsKeyExchange.generatePremasterSecret();
        try {
            tlsContext.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(tlsContext, generatePremasterSecret);
        } finally {
            if (generatePremasterSecret != null) {
                Arrays.fill(generatePremasterSecret, 0);
            }
        }
    }

    protected static byte[] getCurrentPRFHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] bArr) {
        Digest forkPRFHash = tlsHandshakeHash.forkPRFHash();
        if (bArr != null && TlsUtils.isSSL(tlsContext)) {
            forkPRFHash.update(bArr, 0, bArr.length);
        }
        byte[] bArr2 = new byte[forkPRFHash.getDigestSize()];
        forkPRFHash.doFinal(bArr2, 0);
        return bArr2;
    }

    protected static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque16);
        Hashtable hashtable = new Hashtable();
        while (byteArrayInputStream2.available() > 0) {
            if (hashtable.put(Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream2)), TlsUtils.readOpaque16(byteArrayInputStream2)) != null) {
                throw new TlsFatalAlert(47);
            }
        }
        return hashtable;
    }

    protected static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    protected static void writeExtensions(OutputStream outputStream, Hashtable hashtable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            int intValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            TlsUtils.checkUint16(intValue);
            TlsUtils.writeUint16(intValue, byteArrayOutputStream);
            TlsUtils.writeOpaque16(bArr, byteArrayOutputStream);
        }
        TlsUtils.writeOpaque16(byteArrayOutputStream.toByteArray(), outputStream);
    }

    protected static void writeSupplementalData(OutputStream outputStream, Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); i++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        if (r4 == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        if (r4 == false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        throw new org.spongycastle.crypto.tls.TlsFatalAlert(47);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int getPRFAlgorithm(org.spongycastle.crypto.tls.TlsContext r4, int r5) throws java.io.IOException {
        /*
            boolean r4 = org.spongycastle.crypto.tls.TlsUtils.isTLSv12(r4)
            r0 = 47
            r1 = 1
            switch(r5) {
                case 59: goto L_0x0038;
                case 60: goto L_0x0038;
                case 61: goto L_0x0038;
                case 62: goto L_0x0038;
                case 63: goto L_0x0038;
                case 64: goto L_0x0038;
                default: goto L_0x000a;
            }
        L_0x000a:
            switch(r5) {
                case 103: goto L_0x0038;
                case 104: goto L_0x0038;
                case 105: goto L_0x0038;
                case 106: goto L_0x0038;
                case 107: goto L_0x0038;
                default: goto L_0x000d;
            }
        L_0x000d:
            r2 = 2
            switch(r5) {
                case 156: goto L_0x0038;
                case 157: goto L_0x002f;
                case 158: goto L_0x0038;
                case 159: goto L_0x002f;
                case 160: goto L_0x0038;
                case 161: goto L_0x002f;
                case 162: goto L_0x0038;
                case 163: goto L_0x002f;
                case 164: goto L_0x0038;
                case 165: goto L_0x002f;
                default: goto L_0x0011;
            }
        L_0x0011:
            switch(r5) {
                case 168: goto L_0x0038;
                case 169: goto L_0x002f;
                case 170: goto L_0x0038;
                case 171: goto L_0x002f;
                case 172: goto L_0x0038;
                case 173: goto L_0x002f;
                default: goto L_0x0014;
            }
        L_0x0014:
            r3 = 0
            switch(r5) {
                case 175: goto L_0x002b;
                case 177: goto L_0x002b;
                case 179: goto L_0x002b;
                case 181: goto L_0x002b;
                case 183: goto L_0x002b;
                case 49208: goto L_0x002b;
                case 49211: goto L_0x002b;
                case 49301: goto L_0x002b;
                case 49303: goto L_0x002b;
                case 49305: goto L_0x002b;
                default: goto L_0x0018;
            }
        L_0x0018:
            switch(r5) {
                case 185: goto L_0x002b;
                case 186: goto L_0x0038;
                case 187: goto L_0x0038;
                case 188: goto L_0x0038;
                case 189: goto L_0x0038;
                case 190: goto L_0x0038;
                case 191: goto L_0x0038;
                case 192: goto L_0x0038;
                case 193: goto L_0x0038;
                case 194: goto L_0x0038;
                case 195: goto L_0x0038;
                case 196: goto L_0x0038;
                case 197: goto L_0x0038;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r5) {
                case 49187: goto L_0x0038;
                case 49188: goto L_0x002f;
                case 49189: goto L_0x0038;
                case 49190: goto L_0x002f;
                case 49191: goto L_0x0038;
                case 49192: goto L_0x002f;
                case 49193: goto L_0x0038;
                case 49194: goto L_0x002f;
                case 49195: goto L_0x0038;
                case 49196: goto L_0x002f;
                case 49197: goto L_0x0038;
                case 49198: goto L_0x002f;
                case 49199: goto L_0x0038;
                case 49200: goto L_0x002f;
                case 49201: goto L_0x0038;
                case 49202: goto L_0x002f;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r5) {
                case 49266: goto L_0x0038;
                case 49267: goto L_0x002f;
                case 49268: goto L_0x0038;
                case 49269: goto L_0x002f;
                case 49270: goto L_0x0038;
                case 49271: goto L_0x002f;
                case 49272: goto L_0x0038;
                case 49273: goto L_0x002f;
                case 49274: goto L_0x0038;
                case 49275: goto L_0x002f;
                case 49276: goto L_0x0038;
                case 49277: goto L_0x002f;
                case 49278: goto L_0x0038;
                case 49279: goto L_0x002f;
                case 49280: goto L_0x0038;
                case 49281: goto L_0x002f;
                case 49282: goto L_0x0038;
                case 49283: goto L_0x002f;
                case 49284: goto L_0x0038;
                case 49285: goto L_0x002f;
                case 49286: goto L_0x0038;
                case 49287: goto L_0x002f;
                case 49288: goto L_0x0038;
                case 49289: goto L_0x002f;
                case 49290: goto L_0x0038;
                case 49291: goto L_0x002f;
                case 49292: goto L_0x0038;
                case 49293: goto L_0x002f;
                case 49294: goto L_0x0038;
                case 49295: goto L_0x002f;
                case 49296: goto L_0x0038;
                case 49297: goto L_0x002f;
                case 49298: goto L_0x0038;
                case 49299: goto L_0x002f;
                default: goto L_0x0021;
            }
        L_0x0021:
            switch(r5) {
                case 49307: goto L_0x002b;
                case 49308: goto L_0x0038;
                case 49309: goto L_0x0038;
                case 49310: goto L_0x0038;
                case 49311: goto L_0x0038;
                case 49312: goto L_0x0038;
                case 49313: goto L_0x0038;
                case 49314: goto L_0x0038;
                case 49315: goto L_0x0038;
                case 49316: goto L_0x0038;
                case 49317: goto L_0x0038;
                case 49318: goto L_0x0038;
                case 49319: goto L_0x0038;
                case 49320: goto L_0x0038;
                case 49321: goto L_0x0038;
                case 49322: goto L_0x0038;
                case 49323: goto L_0x0038;
                default: goto L_0x0024;
            }
        L_0x0024:
            switch(r5) {
                case 52243: goto L_0x0038;
                case 52244: goto L_0x0038;
                case 52245: goto L_0x0038;
                default: goto L_0x0027;
            }
        L_0x0027:
            if (r4 == 0) goto L_0x002a
            return r1
        L_0x002a:
            return r3
        L_0x002b:
            if (r4 == 0) goto L_0x002e
            return r2
        L_0x002e:
            return r3
        L_0x002f:
            if (r4 == 0) goto L_0x0032
            return r2
        L_0x0032:
            org.spongycastle.crypto.tls.TlsFatalAlert r4 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r0)
            throw r4
        L_0x0038:
            if (r4 == 0) goto L_0x003b
            return r1
        L_0x003b:
            org.spongycastle.crypto.tls.TlsFatalAlert r4 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsProtocol.getPRFAlgorithm(org.spongycastle.crypto.tls.TlsContext, int):int");
    }
}
