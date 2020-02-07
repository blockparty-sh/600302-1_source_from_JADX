package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.prng.ThreadedSeedGenerator;
import org.spongycastle.util.Arrays;

public class TlsClientProtocol extends TlsProtocol {
    protected TlsAuthentication authentication;
    protected CertificateRequest certificateRequest;
    protected CertificateStatus certificateStatus;
    protected TlsKeyExchange keyExchange;
    protected byte[] selectedSessionID;
    protected TlsClient tlsClient;
    protected TlsClientContextImpl tlsClientContext;

    private static SecureRandom createSecureRandom() {
        ThreadedSeedGenerator threadedSeedGenerator = new ThreadedSeedGenerator();
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(threadedSeedGenerator.generateSeed(20, true));
        return secureRandom;
    }

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream) {
        this(inputStream, outputStream, createSecureRandom());
    }

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
        this.tlsClient = null;
        this.tlsClientContext = null;
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public void connect(TlsClient tlsClient2) throws IOException {
        if (tlsClient2 == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        } else if (this.tlsClient == null) {
            this.tlsClient = tlsClient2;
            this.securityParameters = new SecurityParameters();
            this.securityParameters.entity = 1;
            this.tlsClientContext = new TlsClientContextImpl(this.secureRandom, this.securityParameters);
            this.securityParameters.clientRandom = createRandomBlock(tlsClient2.shouldUseGMTUnixTime(), this.tlsClientContext.getNonceRandomGenerator());
            this.tlsClient.init(this.tlsClientContext);
            this.recordStream.init(this.tlsClientContext);
            TlsSession sessionToResume = tlsClient2.getSessionToResume();
            if (sessionToResume != null) {
                SessionParameters exportSessionParameters = sessionToResume.exportSessionParameters();
                if (exportSessionParameters != null) {
                    this.tlsSession = sessionToResume;
                    this.sessionParameters = exportSessionParameters;
                }
            }
            sendClientHelloMessage();
            this.connection_state = 1;
            completeHandshake();
        } else {
            throw new IllegalStateException("'connect' can only be called once");
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    /* access modifiers changed from: protected */
    public AbstractTlsContext getContext() {
        return this.tlsClientContext;
    }

    /* access modifiers changed from: protected */
    public TlsPeer getPeer() {
        return this.tlsClient;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005f, code lost:
        r11.keyExchange.skipServerCredentials();
        r11.authentication = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0066, code lost:
        r11.keyExchange.skipServerKeyExchange();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        assertEmpty(r0);
        r11.connection_state = 8;
        r11.recordStream.getHandshakeHash().sealHashAlgorithms();
        r12 = r11.tlsClient.getClientSupplementalData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007f, code lost:
        if (r12 == null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
        sendSupplementalDataMessage(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0084, code lost:
        r11.connection_state = 9;
        r12 = r11.certificateRequest;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008a, code lost:
        if (r12 != null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008c, code lost:
        r11.keyExchange.skipClientCredentials();
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        r12 = r11.authentication.getClientCredentials(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0099, code lost:
        if (r12 != null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009b, code lost:
        r11.keyExchange.skipClientCredentials();
        sendCertificateMessage(org.spongycastle.crypto.tls.Certificate.EMPTY_CHAIN);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a6, code lost:
        r11.keyExchange.processClientCredentials(r12);
        sendCertificateMessage(r12.getCertificate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b2, code lost:
        r11.connection_state = 10;
        sendClientKeyExchangeMessage();
        r11.connection_state = 11;
        establishMasterSecret(getContext(), r11.keyExchange);
        r11.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
        r13 = r11.recordStream.prepareToFinish();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00df, code lost:
        if (r12 == null) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e3, code lost:
        if ((r12 instanceof org.spongycastle.crypto.tls.TlsSignerCredentials) == false) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e5, code lost:
        r12 = (org.spongycastle.crypto.tls.TlsSignerCredentials) r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ef, code lost:
        if (org.spongycastle.crypto.tls.TlsUtils.isTLSv12(getContext()) == false) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f1, code lost:
        r8 = r12.getSignatureAndHashAlgorithm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f5, code lost:
        if (r8 == null) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f7, code lost:
        r13 = r13.getFinalHash(r8.getHash());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0107, code lost:
        throw new org.spongycastle.crypto.tls.TlsFatalAlert(80);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0108, code lost:
        r13 = getCurrentPRFHash(getContext(), r13, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0110, code lost:
        sendCertificateVerifyMessage(new org.spongycastle.crypto.tls.DigitallySigned(r8, r12.generateCertificateSignature(r13)));
        r11.connection_state = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0120, code lost:
        sendChangeCipherSpecMessage();
        sendFinishedMessage();
        r11.connection_state = 13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleHandshakeMessage(short r12, byte[] r13) throws java.io.IOException {
        /*
            r11 = this;
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r13)
            boolean r13 = r11.resumedSession
            r1 = 15
            r2 = 20
            r3 = 16
            r4 = 13
            r5 = 2
            r6 = 10
            if (r13 == 0) goto L_0x002d
            if (r12 != r2) goto L_0x0027
            short r12 = r11.connection_state
            if (r12 != r5) goto L_0x0027
            r11.processFinishedMessage(r0)
            r11.connection_state = r1
            r11.sendFinishedMessage()
            r11.connection_state = r4
            r11.connection_state = r3
            return
        L_0x0027:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x002d:
            r13 = 40
            if (r12 == 0) goto L_0x02c5
            r7 = 8
            r8 = 0
            if (r12 == r5) goto L_0x024b
            r9 = 14
            r10 = 4
            if (r12 == r10) goto L_0x022d
            if (r12 == r2) goto L_0x020d
            r1 = 22
            r2 = 5
            if (r12 == r1) goto L_0x01ec
            r1 = 23
            if (r12 == r1) goto L_0x01d9
            r1 = 6
            r3 = 3
            switch(r12) {
                case 11: goto L_0x0197;
                case 12: goto L_0x0170;
                case 13: goto L_0x012a;
                case 14: goto L_0x0051;
                default: goto L_0x004b;
            }
        L_0x004b:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x0051:
            short r12 = r11.connection_state
            switch(r12) {
                case 2: goto L_0x005c;
                case 3: goto L_0x005f;
                case 4: goto L_0x0066;
                case 5: goto L_0x0066;
                case 6: goto L_0x006b;
                case 7: goto L_0x006b;
                default: goto L_0x0056;
            }
        L_0x0056:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r13)
            throw r12
        L_0x005c:
            r11.handleSupplementalData(r8)
        L_0x005f:
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            r12.skipServerCredentials()
            r11.authentication = r8
        L_0x0066:
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            r12.skipServerKeyExchange()
        L_0x006b:
            assertEmpty(r0)
            r11.connection_state = r7
            org.spongycastle.crypto.tls.RecordStream r12 = r11.recordStream
            org.spongycastle.crypto.tls.TlsHandshakeHash r12 = r12.getHandshakeHash()
            r12.sealHashAlgorithms()
            org.spongycastle.crypto.tls.TlsClient r12 = r11.tlsClient
            java.util.Vector r12 = r12.getClientSupplementalData()
            if (r12 == 0) goto L_0x0084
            r11.sendSupplementalDataMessage(r12)
        L_0x0084:
            r12 = 9
            r11.connection_state = r12
            org.spongycastle.crypto.tls.CertificateRequest r12 = r11.certificateRequest
            if (r12 != 0) goto L_0x0093
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            r12.skipClientCredentials()
            r12 = r8
            goto L_0x00b2
        L_0x0093:
            org.spongycastle.crypto.tls.TlsAuthentication r13 = r11.authentication
            org.spongycastle.crypto.tls.TlsCredentials r12 = r13.getClientCredentials(r12)
            if (r12 != 0) goto L_0x00a6
            org.spongycastle.crypto.tls.TlsKeyExchange r13 = r11.keyExchange
            r13.skipClientCredentials()
            org.spongycastle.crypto.tls.Certificate r13 = org.spongycastle.crypto.tls.Certificate.EMPTY_CHAIN
            r11.sendCertificateMessage(r13)
            goto L_0x00b2
        L_0x00a6:
            org.spongycastle.crypto.tls.TlsKeyExchange r13 = r11.keyExchange
            r13.processClientCredentials(r12)
            org.spongycastle.crypto.tls.Certificate r13 = r12.getCertificate()
            r11.sendCertificateMessage(r13)
        L_0x00b2:
            r11.connection_state = r6
            r11.sendClientKeyExchangeMessage()
            r13 = 11
            r11.connection_state = r13
            org.spongycastle.crypto.tls.AbstractTlsContext r13 = r11.getContext()
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r11.keyExchange
            establishMasterSecret(r13, r0)
            org.spongycastle.crypto.tls.RecordStream r13 = r11.recordStream
            org.spongycastle.crypto.tls.TlsPeer r0 = r11.getPeer()
            org.spongycastle.crypto.tls.TlsCompression r0 = r0.getCompression()
            org.spongycastle.crypto.tls.TlsPeer r1 = r11.getPeer()
            org.spongycastle.crypto.tls.TlsCipher r1 = r1.getCipher()
            r13.setPendingConnectionState(r0, r1)
            org.spongycastle.crypto.tls.RecordStream r13 = r11.recordStream
            org.spongycastle.crypto.tls.TlsHandshakeHash r13 = r13.prepareToFinish()
            if (r12 == 0) goto L_0x0120
            boolean r0 = r12 instanceof org.spongycastle.crypto.tls.TlsSignerCredentials
            if (r0 == 0) goto L_0x0120
            org.spongycastle.crypto.tls.TlsSignerCredentials r12 = (org.spongycastle.crypto.tls.TlsSignerCredentials) r12
            org.spongycastle.crypto.tls.AbstractTlsContext r0 = r11.getContext()
            boolean r0 = org.spongycastle.crypto.tls.TlsUtils.isTLSv12(r0)
            if (r0 == 0) goto L_0x0108
            org.spongycastle.crypto.tls.SignatureAndHashAlgorithm r8 = r12.getSignatureAndHashAlgorithm()
            if (r8 == 0) goto L_0x0100
            short r0 = r8.getHash()
            byte[] r13 = r13.getFinalHash(r0)
            goto L_0x0110
        L_0x0100:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r13 = 80
            r12.<init>(r13)
            throw r12
        L_0x0108:
            org.spongycastle.crypto.tls.AbstractTlsContext r0 = r11.getContext()
            byte[] r13 = getCurrentPRFHash(r0, r13, r8)
        L_0x0110:
            byte[] r12 = r12.generateCertificateSignature(r13)
            org.spongycastle.crypto.tls.DigitallySigned r13 = new org.spongycastle.crypto.tls.DigitallySigned
            r13.<init>(r8, r12)
            r11.sendCertificateVerifyMessage(r13)
            r12 = 12
            r11.connection_state = r12
        L_0x0120:
            r11.sendChangeCipherSpecMessage()
            r11.sendFinishedMessage()
            r11.connection_state = r4
            goto L_0x02e4
        L_0x012a:
            short r12 = r11.connection_state
            if (r12 == r10) goto L_0x0139
            if (r12 == r2) goto L_0x0139
            if (r12 != r1) goto L_0x0133
            goto L_0x013e
        L_0x0133:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x0139:
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            r12.skipServerKeyExchange()
        L_0x013e:
            org.spongycastle.crypto.tls.TlsAuthentication r12 = r11.authentication
            if (r12 == 0) goto L_0x016a
            org.spongycastle.crypto.tls.AbstractTlsContext r12 = r11.getContext()
            org.spongycastle.crypto.tls.CertificateRequest r12 = org.spongycastle.crypto.tls.CertificateRequest.parse(r12, r0)
            r11.certificateRequest = r12
            assertEmpty(r0)
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            org.spongycastle.crypto.tls.CertificateRequest r13 = r11.certificateRequest
            r12.validateCertificateRequest(r13)
            org.spongycastle.crypto.tls.RecordStream r12 = r11.recordStream
            org.spongycastle.crypto.tls.TlsHandshakeHash r12 = r12.getHandshakeHash()
            org.spongycastle.crypto.tls.CertificateRequest r13 = r11.certificateRequest
            java.util.Vector r13 = r13.getSupportedSignatureAlgorithms()
            org.spongycastle.crypto.tls.TlsUtils.trackHashAlgorithms(r12, r13)
            r12 = 7
            r11.connection_state = r12
            goto L_0x02e4
        L_0x016a:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r13)
            throw r12
        L_0x0170:
            short r12 = r11.connection_state
            if (r12 == r5) goto L_0x0181
            if (r12 == r3) goto L_0x0184
            if (r12 == r10) goto L_0x018b
            if (r12 != r2) goto L_0x017b
            goto L_0x018b
        L_0x017b:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x0181:
            r11.handleSupplementalData(r8)
        L_0x0184:
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            r12.skipServerCredentials()
            r11.authentication = r8
        L_0x018b:
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            r12.processServerKeyExchange(r0)
            assertEmpty(r0)
            r11.connection_state = r1
            goto L_0x02e4
        L_0x0197:
            short r12 = r11.connection_state
            if (r12 == r5) goto L_0x01a4
            if (r12 != r3) goto L_0x019e
            goto L_0x01a7
        L_0x019e:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x01a4:
            r11.handleSupplementalData(r8)
        L_0x01a7:
            org.spongycastle.crypto.tls.Certificate r12 = org.spongycastle.crypto.tls.Certificate.parse(r0)
            r11.peerCertificate = r12
            assertEmpty(r0)
            org.spongycastle.crypto.tls.Certificate r12 = r11.peerCertificate
            if (r12 == 0) goto L_0x01bc
            org.spongycastle.crypto.tls.Certificate r12 = r11.peerCertificate
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x01bf
        L_0x01bc:
            r12 = 0
            r11.allowCertificateStatus = r12
        L_0x01bf:
            org.spongycastle.crypto.tls.TlsKeyExchange r12 = r11.keyExchange
            org.spongycastle.crypto.tls.Certificate r13 = r11.peerCertificate
            r12.processServerCertificate(r13)
            org.spongycastle.crypto.tls.TlsClient r12 = r11.tlsClient
            org.spongycastle.crypto.tls.TlsAuthentication r12 = r12.getAuthentication()
            r11.authentication = r12
            org.spongycastle.crypto.tls.TlsAuthentication r12 = r11.authentication
            org.spongycastle.crypto.tls.Certificate r13 = r11.peerCertificate
            r12.notifyServerCertificate(r13)
            r11.connection_state = r10
            goto L_0x02e4
        L_0x01d9:
            short r12 = r11.connection_state
            if (r12 != r5) goto L_0x01e6
            java.util.Vector r12 = readSupplementalDataMessage(r0)
            r11.handleSupplementalData(r12)
            goto L_0x02e4
        L_0x01e6:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x01ec:
            short r12 = r11.connection_state
            if (r12 != r10) goto L_0x0207
            boolean r12 = r11.allowCertificateStatus
            if (r12 == 0) goto L_0x0201
            org.spongycastle.crypto.tls.CertificateStatus r12 = org.spongycastle.crypto.tls.CertificateStatus.parse(r0)
            r11.certificateStatus = r12
            assertEmpty(r0)
            r11.connection_state = r2
            goto L_0x02e4
        L_0x0201:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x0207:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x020d:
            short r12 = r11.connection_state
            if (r12 == r4) goto L_0x021a
            if (r12 != r9) goto L_0x0214
            goto L_0x021e
        L_0x0214:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x021a:
            boolean r12 = r11.expectSessionTicket
            if (r12 != 0) goto L_0x0227
        L_0x021e:
            r11.processFinishedMessage(r0)
            r11.connection_state = r1
            r11.connection_state = r3
            goto L_0x02e4
        L_0x0227:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x022d:
            short r12 = r11.connection_state
            if (r12 != r4) goto L_0x0245
            boolean r12 = r11.expectSessionTicket
            if (r12 == 0) goto L_0x023f
            r11.invalidateSession()
            r11.receiveNewSessionTicketMessage(r0)
            r11.connection_state = r9
            goto L_0x02c5
        L_0x023f:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x0245:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x024b:
            short r12 = r11.connection_state
            r13 = 1
            if (r12 != r13) goto L_0x02bf
            r11.receiveServerHelloMessage(r0)
            r11.connection_state = r5
            org.spongycastle.crypto.tls.SecurityParameters r12 = r11.securityParameters
            short r12 = r12.maxFragmentLength
            if (r12 < 0) goto L_0x0267
            org.spongycastle.crypto.tls.SecurityParameters r12 = r11.securityParameters
            short r12 = r12.maxFragmentLength
            int r12 = r12 + r7
            int r12 = r13 << r12
            org.spongycastle.crypto.tls.RecordStream r13 = r11.recordStream
            r13.setPlaintextLimit(r12)
        L_0x0267:
            org.spongycastle.crypto.tls.SecurityParameters r12 = r11.securityParameters
            org.spongycastle.crypto.tls.AbstractTlsContext r13 = r11.getContext()
            org.spongycastle.crypto.tls.SecurityParameters r0 = r11.securityParameters
            int r0 = r0.getCipherSuite()
            int r13 = getPRFAlgorithm(r13, r0)
            r12.prfAlgorithm = r13
            org.spongycastle.crypto.tls.SecurityParameters r12 = r11.securityParameters
            r13 = 12
            r12.verifyDataLength = r13
            org.spongycastle.crypto.tls.RecordStream r12 = r11.recordStream
            r12.notifyHelloComplete()
            boolean r12 = r11.resumedSession
            if (r12 == 0) goto L_0x02af
            org.spongycastle.crypto.tls.SecurityParameters r12 = r11.securityParameters
            org.spongycastle.crypto.tls.SessionParameters r13 = r11.sessionParameters
            byte[] r13 = r13.getMasterSecret()
            byte[] r13 = org.spongycastle.util.Arrays.clone(r13)
            r12.masterSecret = r13
            org.spongycastle.crypto.tls.RecordStream r12 = r11.recordStream
            org.spongycastle.crypto.tls.TlsPeer r13 = r11.getPeer()
            org.spongycastle.crypto.tls.TlsCompression r13 = r13.getCompression()
            org.spongycastle.crypto.tls.TlsPeer r0 = r11.getPeer()
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.getCipher()
            r12.setPendingConnectionState(r13, r0)
            r11.sendChangeCipherSpecMessage()
            goto L_0x02e4
        L_0x02af:
            r11.invalidateSession()
            byte[] r12 = r11.selectedSessionID
            int r13 = r12.length
            if (r13 <= 0) goto L_0x02e4
            org.spongycastle.crypto.tls.TlsSessionImpl r13 = new org.spongycastle.crypto.tls.TlsSessionImpl
            r13.<init>(r12, r8)
            r11.tlsSession = r13
            goto L_0x02e4
        L_0x02bf:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r6)
            throw r12
        L_0x02c5:
            assertEmpty(r0)
            short r12 = r11.connection_state
            if (r12 != r3) goto L_0x02e4
            org.spongycastle.crypto.tls.AbstractTlsContext r12 = r11.getContext()
            boolean r12 = org.spongycastle.crypto.tls.TlsUtils.isSSL(r12)
            if (r12 != 0) goto L_0x02de
            r12 = 100
            java.lang.String r13 = "Renegotiation not supported"
            r11.raiseWarning(r12, r13)
            goto L_0x02e4
        L_0x02de:
            org.spongycastle.crypto.tls.TlsFatalAlert r12 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r12.<init>(r13)
            throw r12
        L_0x02e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsClientProtocol.handleHandshakeMessage(short, byte[]):void");
    }

    /* access modifiers changed from: protected */
    public void handleSupplementalData(Vector vector) throws IOException {
        this.tlsClient.processServerSupplementalData(vector);
        this.connection_state = 3;
        this.keyExchange = this.tlsClient.getKeyExchange();
        this.keyExchange.init(getContext());
    }

    /* access modifiers changed from: protected */
    public void receiveNewSessionTicketMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        this.tlsClient.notifyNewSessionTicket(parse);
    }

    /* access modifiers changed from: protected */
    public void receiveServerHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        if (readVersion.isDTLS()) {
            throw new TlsFatalAlert(47);
        } else if (!readVersion.equals(this.recordStream.getReadVersion())) {
            throw new TlsFatalAlert(47);
        } else if (readVersion.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            this.recordStream.setWriteVersion(readVersion);
            getContext().setServerVersion(readVersion);
            this.tlsClient.notifyServerVersion(readVersion);
            this.securityParameters.serverRandom = TlsUtils.readFully(32, (InputStream) byteArrayInputStream);
            this.selectedSessionID = TlsUtils.readOpaque8(byteArrayInputStream);
            byte[] bArr = this.selectedSessionID;
            if (bArr.length <= 32) {
                this.tlsClient.notifySessionID(bArr);
                boolean z = false;
                this.resumedSession = this.selectedSessionID.length > 0 && this.tlsSession != null && Arrays.areEqual(this.selectedSessionID, this.tlsSession.getSessionID());
                int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
                if (!Arrays.contains(this.offeredCipherSuites, readUint16) || readUint16 == 0 || readUint16 == 255 || !TlsUtils.isValidCipherSuiteForVersion(readUint16, readVersion)) {
                    throw new TlsFatalAlert(47);
                }
                this.tlsClient.notifySelectedCipherSuite(readUint16);
                short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
                if (Arrays.contains(this.offeredCompressionMethods, readUint8)) {
                    this.tlsClient.notifySelectedCompressionMethod(readUint8);
                    this.serverExtensions = readExtensions(byteArrayInputStream);
                    if (this.serverExtensions != null) {
                        Enumeration keys = this.serverExtensions.keys();
                        while (keys.hasMoreElements()) {
                            Integer num = (Integer) keys.nextElement();
                            if (!num.equals(EXT_RenegotiationInfo)) {
                                boolean z2 = this.resumedSession;
                                if (TlsUtils.getExtensionData(this.clientExtensions, num) == null) {
                                    throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                                }
                            }
                        }
                    }
                    byte[] extensionData = TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo);
                    if (extensionData != null) {
                        this.secure_renegotiation = true;
                        if (!Arrays.constantTimeAreEqual(extensionData, createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                            throw new TlsFatalAlert(40);
                        }
                    }
                    this.tlsClient.notifySecureRenegotiation(this.secure_renegotiation);
                    Hashtable hashtable = this.clientExtensions;
                    Hashtable hashtable2 = this.serverExtensions;
                    if (this.resumedSession) {
                        if (readUint16 == this.sessionParameters.getCipherSuite() && readUint8 == this.sessionParameters.getCompressionAlgorithm()) {
                            hashtable = null;
                            hashtable2 = this.sessionParameters.readServerExtensions();
                        } else {
                            throw new TlsFatalAlert(47);
                        }
                    }
                    this.securityParameters.cipherSuite = readUint16;
                    this.securityParameters.compressionAlgorithm = readUint8;
                    if (hashtable2 != null) {
                        boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable2);
                        if (!hasEncryptThenMACExtension || TlsUtils.isBlockCipherSuite(readUint16)) {
                            this.securityParameters.encryptThenMAC = hasEncryptThenMACExtension;
                            this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(hashtable, hashtable2, 47);
                            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable2);
                            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsExtensionsUtils.EXT_status_request, 47);
                            if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsProtocol.EXT_SessionTicket, 47)) {
                                z = true;
                            }
                            this.expectSessionTicket = z;
                        } else {
                            throw new TlsFatalAlert(47);
                        }
                    }
                    if (hashtable != null) {
                        this.tlsClient.processServerExtensions(hashtable2);
                        return;
                    }
                    return;
                }
                throw new TlsFatalAlert(47);
            }
            throw new TlsFatalAlert(47);
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    /* access modifiers changed from: protected */
    public void sendCertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 15);
        digitallySigned.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendClientHelloMessage() throws IOException {
        this.recordStream.setWriteVersion(this.tlsClient.getClientHelloRecordLayerVersion());
        ProtocolVersion clientVersion = this.tlsClient.getClientVersion();
        if (!clientVersion.isDTLS()) {
            getContext().setClientVersion(clientVersion);
            byte[] bArr = TlsUtils.EMPTY_BYTES;
            if (this.tlsSession != null) {
                bArr = this.tlsSession.getSessionID();
                if (bArr == null || bArr.length > 32) {
                    bArr = TlsUtils.EMPTY_BYTES;
                }
            }
            this.offeredCipherSuites = this.tlsClient.getCipherSuites();
            this.offeredCompressionMethods = this.tlsClient.getCompressionMethods();
            if (bArr.length > 0 && this.sessionParameters != null && (!Arrays.contains(this.offeredCipherSuites, this.sessionParameters.getCipherSuite()) || !Arrays.contains(this.offeredCompressionMethods, this.sessionParameters.getCompressionAlgorithm()))) {
                bArr = TlsUtils.EMPTY_BYTES;
            }
            this.clientExtensions = this.tlsClient.getClientExtensions();
            HandshakeMessage handshakeMessage = new HandshakeMessage(this, 1);
            TlsUtils.writeVersion(clientVersion, handshakeMessage);
            handshakeMessage.write(this.securityParameters.getClientRandom());
            TlsUtils.writeOpaque8(bArr, handshakeMessage);
            boolean z = !Arrays.contains(this.offeredCipherSuites, 255);
            if ((TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo) == null) && z) {
                this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 255);
            }
            TlsUtils.writeUint16ArrayWithUint16Length(this.offeredCipherSuites, handshakeMessage);
            TlsUtils.writeUint8ArrayWithUint8Length(this.offeredCompressionMethods, handshakeMessage);
            if (this.clientExtensions != null) {
                writeExtensions(handshakeMessage, this.clientExtensions);
            }
            handshakeMessage.writeToRecordStream();
            return;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void sendClientKeyExchangeMessage() throws IOException {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 16);
        this.keyExchange.generateClientKeyExchange(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }
}
