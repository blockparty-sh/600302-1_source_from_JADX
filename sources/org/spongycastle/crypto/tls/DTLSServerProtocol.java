package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;

public class DTLSServerProtocol extends DTLSProtocol {
    protected boolean verifyRequests = true;

    protected static class ServerHandshakeState {
        boolean allowCertificateStatus = false;
        CertificateRequest certificateRequest = null;
        Certificate clientCertificate = null;
        short clientCertificateType = -1;
        Hashtable clientExtensions;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        short maxFragmentLength = -1;
        int[] offeredCipherSuites;
        short[] offeredCompressionMethods;
        boolean secure_renegotiation = false;
        int selectedCipherSuite = -1;
        short selectedCompressionMethod = -1;
        TlsServer server = null;
        TlsServerContextImpl serverContext = null;
        TlsCredentials serverCredentials = null;
        Hashtable serverExtensions = null;

        protected ServerHandshakeState() {
        }
    }

    public DTLSServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public boolean getVerifyRequests() {
        return this.verifyRequests;
    }

    public void setVerifyRequests(boolean z) {
        this.verifyRequests = z;
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport) throws IOException {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'server' cannot be null");
        } else if (datagramTransport != null) {
            SecurityParameters securityParameters = new SecurityParameters();
            securityParameters.entity = 0;
            ServerHandshakeState serverHandshakeState = new ServerHandshakeState();
            serverHandshakeState.server = tlsServer;
            serverHandshakeState.serverContext = new TlsServerContextImpl(this.secureRandom, securityParameters);
            securityParameters.serverRandom = TlsProtocol.createRandomBlock(tlsServer.shouldUseGMTUnixTime(), serverHandshakeState.serverContext.getNonceRandomGenerator());
            tlsServer.init(serverHandshakeState.serverContext);
            DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(datagramTransport, serverHandshakeState.serverContext, tlsServer, 22);
            try {
                return serverHandshake(serverHandshakeState, dTLSRecordLayer);
            } catch (TlsFatalAlert e) {
                dTLSRecordLayer.fail(e.getAlertDescription());
                throw e;
            } catch (IOException e2) {
                dTLSRecordLayer.fail(80);
                throw e2;
            } catch (RuntimeException unused) {
                dTLSRecordLayer.fail(80);
                throw new TlsFatalAlert(80);
            }
        } else {
            throw new IllegalArgumentException("'transport' cannot be null");
        }
    }

    /* access modifiers changed from: protected */
    public DTLSTransport serverHandshake(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer) throws IOException {
        Certificate certificate;
        SecurityParameters securityParameters = serverHandshakeState.serverContext.getSecurityParameters();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(serverHandshakeState.serverContext, dTLSRecordLayer);
        Message receiveMessage = dTLSReliableHandshake.receiveMessage();
        serverHandshakeState.serverContext.setClientVersion(dTLSRecordLayer.getDiscoveredPeerVersion());
        if (receiveMessage.getType() == 1) {
            processClientHello(serverHandshakeState, receiveMessage.getBody());
            byte[] generateServerHello = generateServerHello(serverHandshakeState);
            if (serverHandshakeState.maxFragmentLength >= 0) {
                dTLSRecordLayer.setPlaintextLimit(1 << (serverHandshakeState.maxFragmentLength + 8));
            }
            securityParameters.cipherSuite = serverHandshakeState.selectedCipherSuite;
            securityParameters.compressionAlgorithm = serverHandshakeState.selectedCompressionMethod;
            securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(serverHandshakeState.serverContext, serverHandshakeState.selectedCipherSuite);
            securityParameters.verifyDataLength = 12;
            dTLSReliableHandshake.sendMessage(2, generateServerHello);
            dTLSReliableHandshake.notifyHelloComplete();
            Vector serverSupplementalData = serverHandshakeState.server.getServerSupplementalData();
            if (serverSupplementalData != null) {
                dTLSReliableHandshake.sendMessage(23, generateSupplementalData(serverSupplementalData));
            }
            serverHandshakeState.keyExchange = serverHandshakeState.server.getKeyExchange();
            serverHandshakeState.keyExchange.init(serverHandshakeState.serverContext);
            serverHandshakeState.serverCredentials = serverHandshakeState.server.getCredentials();
            if (serverHandshakeState.serverCredentials == null) {
                serverHandshakeState.keyExchange.skipServerCredentials();
                certificate = null;
            } else {
                serverHandshakeState.keyExchange.processServerCredentials(serverHandshakeState.serverCredentials);
                certificate = serverHandshakeState.serverCredentials.getCertificate();
                dTLSReliableHandshake.sendMessage(11, generateCertificate(certificate));
            }
            if (certificate == null || certificate.isEmpty()) {
                serverHandshakeState.allowCertificateStatus = false;
            }
            if (serverHandshakeState.allowCertificateStatus) {
                CertificateStatus certificateStatus = serverHandshakeState.server.getCertificateStatus();
                if (certificateStatus != null) {
                    dTLSReliableHandshake.sendMessage(22, generateCertificateStatus(serverHandshakeState, certificateStatus));
                }
            }
            byte[] generateServerKeyExchange = serverHandshakeState.keyExchange.generateServerKeyExchange();
            if (generateServerKeyExchange != null) {
                dTLSReliableHandshake.sendMessage(12, generateServerKeyExchange);
            }
            if (serverHandshakeState.serverCredentials != null) {
                serverHandshakeState.certificateRequest = serverHandshakeState.server.getCertificateRequest();
                if (serverHandshakeState.certificateRequest != null) {
                    serverHandshakeState.keyExchange.validateCertificateRequest(serverHandshakeState.certificateRequest);
                    dTLSReliableHandshake.sendMessage(13, generateCertificateRequest(serverHandshakeState, serverHandshakeState.certificateRequest));
                    TlsUtils.trackHashAlgorithms(dTLSReliableHandshake.getHandshakeHash(), serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms());
                }
            }
            dTLSReliableHandshake.sendMessage(14, TlsUtils.EMPTY_BYTES);
            dTLSReliableHandshake.getHandshakeHash().sealHashAlgorithms();
            Message receiveMessage2 = dTLSReliableHandshake.receiveMessage();
            if (receiveMessage2.getType() == 23) {
                processClientSupplementalData(serverHandshakeState, receiveMessage2.getBody());
                receiveMessage2 = dTLSReliableHandshake.receiveMessage();
            } else {
                serverHandshakeState.server.processClientSupplementalData(null);
            }
            if (serverHandshakeState.certificateRequest == null) {
                serverHandshakeState.keyExchange.skipClientCredentials();
            } else if (receiveMessage2.getType() == 11) {
                processClientCertificate(serverHandshakeState, receiveMessage2.getBody());
                receiveMessage2 = dTLSReliableHandshake.receiveMessage();
            } else if (!TlsUtils.isTLSv12(serverHandshakeState.serverContext)) {
                notifyClientCertificate(serverHandshakeState, Certificate.EMPTY_CHAIN);
            } else {
                throw new TlsFatalAlert(10);
            }
            if (receiveMessage2.getType() == 16) {
                processClientKeyExchange(serverHandshakeState, receiveMessage2.getBody());
                TlsProtocol.establishMasterSecret(serverHandshakeState.serverContext, serverHandshakeState.keyExchange);
                dTLSRecordLayer.initPendingEpoch(serverHandshakeState.server.getCipher());
                TlsHandshakeHash prepareToFinish = dTLSReliableHandshake.prepareToFinish();
                if (expectCertificateVerifyMessage(serverHandshakeState)) {
                    processCertificateVerify(serverHandshakeState, dTLSReliableHandshake.receiveMessageBody(15), prepareToFinish);
                }
                processFinished(dTLSReliableHandshake.receiveMessageBody(20), TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                if (serverHandshakeState.expectSessionTicket) {
                    dTLSReliableHandshake.sendMessage(4, generateNewSessionTicket(serverHandshakeState, serverHandshakeState.server.getNewSessionTicket()));
                }
                dTLSReliableHandshake.sendMessage(20, TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                dTLSReliableHandshake.finish();
                serverHandshakeState.server.notifyHandshakeComplete();
                return new DTLSTransport(dTLSRecordLayer);
            }
            throw new TlsFatalAlert(10);
        }
        throw new TlsFatalAlert(10);
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateRequest(ServerHandshakeState serverHandshakeState, CertificateRequest certificateRequest) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateRequest.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateStatus(ServerHandshakeState serverHandshakeState, CertificateStatus certificateStatus) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateStatus.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateNewSessionTicket(ServerHandshakeState serverHandshakeState, NewSessionTicket newSessionTicket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        newSessionTicket.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateServerHello(ServerHandshakeState serverHandshakeState) throws IOException {
        SecurityParameters securityParameters = serverHandshakeState.serverContext.getSecurityParameters();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtocolVersion serverVersion = serverHandshakeState.server.getServerVersion();
        if (serverVersion.isEqualOrEarlierVersionOf(serverHandshakeState.serverContext.getClientVersion())) {
            serverHandshakeState.serverContext.setServerVersion(serverVersion);
            TlsUtils.writeVersion(serverHandshakeState.serverContext.getServerVersion(), byteArrayOutputStream);
            byteArrayOutputStream.write(securityParameters.getServerRandom());
            TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
            serverHandshakeState.selectedCipherSuite = serverHandshakeState.server.getSelectedCipherSuite();
            if (!Arrays.contains(serverHandshakeState.offeredCipherSuites, serverHandshakeState.selectedCipherSuite) || serverHandshakeState.selectedCipherSuite == 0 || serverHandshakeState.selectedCipherSuite == 255 || !TlsUtils.isValidCipherSuiteForVersion(serverHandshakeState.selectedCipherSuite, serverVersion)) {
                throw new TlsFatalAlert(80);
            }
            validateSelectedCipherSuite(serverHandshakeState.selectedCipherSuite, 80);
            serverHandshakeState.selectedCompressionMethod = serverHandshakeState.server.getSelectedCompressionMethod();
            if (Arrays.contains(serverHandshakeState.offeredCompressionMethods, serverHandshakeState.selectedCompressionMethod)) {
                TlsUtils.writeUint16(serverHandshakeState.selectedCipherSuite, byteArrayOutputStream);
                TlsUtils.writeUint8(serverHandshakeState.selectedCompressionMethod, (OutputStream) byteArrayOutputStream);
                serverHandshakeState.serverExtensions = serverHandshakeState.server.getServerExtensions();
                if (serverHandshakeState.secure_renegotiation) {
                    if (TlsUtils.getExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) {
                        serverHandshakeState.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.serverExtensions);
                        serverHandshakeState.serverExtensions.put(TlsProtocol.EXT_RenegotiationInfo, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
                    }
                }
                if (serverHandshakeState.serverExtensions != null) {
                    securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(serverHandshakeState.serverExtensions);
                    serverHandshakeState.maxFragmentLength = evaluateMaxFragmentLengthExtension(serverHandshakeState.clientExtensions, serverHandshakeState.serverExtensions, 80);
                    securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(serverHandshakeState.serverExtensions);
                    serverHandshakeState.allowCertificateStatus = TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsExtensionsUtils.EXT_status_request, 80);
                    serverHandshakeState.expectSessionTicket = TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_SessionTicket, 80);
                    TlsProtocol.writeExtensions(byteArrayOutputStream, serverHandshakeState.serverExtensions);
                }
                return byteArrayOutputStream.toByteArray();
            }
            throw new TlsFatalAlert(80);
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void notifyClientCertificate(ServerHandshakeState serverHandshakeState, Certificate certificate) throws IOException {
        if (serverHandshakeState.certificateRequest == null) {
            throw new IllegalStateException();
        } else if (serverHandshakeState.clientCertificate == null) {
            serverHandshakeState.clientCertificate = certificate;
            if (certificate.isEmpty()) {
                serverHandshakeState.keyExchange.skipClientCredentials();
            } else {
                serverHandshakeState.clientCertificateType = TlsUtils.getClientCertificateType(certificate, serverHandshakeState.serverCredentials.getCertificate());
                serverHandshakeState.keyExchange.processClientCertificate(certificate);
            }
            serverHandshakeState.server.notifyClientCertificate(certificate);
        } else {
            throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public void processClientCertificate(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate parse = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(serverHandshakeState, parse);
    }

    /* access modifiers changed from: protected */
    public void processCertificateVerify(ServerHandshakeState serverHandshakeState, byte[] bArr, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        byte[] bArr2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        DigitallySigned parse = DigitallySigned.parse(serverHandshakeState.serverContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        boolean z = false;
        try {
            if (TlsUtils.isTLSv12(serverHandshakeState.serverContext)) {
                bArr2 = tlsHandshakeHash.getFinalHash(parse.getAlgorithm().getHash());
            } else {
                bArr2 = TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, tlsHandshakeHash, null);
            }
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(serverHandshakeState.clientCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner createTlsSigner = TlsUtils.createTlsSigner(serverHandshakeState.clientCertificateType);
            createTlsSigner.init(serverHandshakeState.serverContext);
            z = createTlsSigner.verifyRawSignature(parse.getAlgorithm(), parse.getSignature(), createKey, bArr2);
        } catch (Exception unused) {
        }
        if (!z) {
            throw new TlsFatalAlert(51);
        }
    }

    /* access modifiers changed from: protected */
    public void processClientHello(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        if (readVersion.isDTLS()) {
            byte[] readFully = TlsUtils.readFully(32, (InputStream) byteArrayInputStream);
            if (TlsUtils.readOpaque8(byteArrayInputStream).length <= 32) {
                TlsUtils.readOpaque8(byteArrayInputStream);
                int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
                if (readUint16 < 2 || (readUint16 & 1) != 0) {
                    throw new TlsFatalAlert(50);
                }
                serverHandshakeState.offeredCipherSuites = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
                short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
                if (readUint8 >= 1) {
                    serverHandshakeState.offeredCompressionMethods = TlsUtils.readUint8Array(readUint8, byteArrayInputStream);
                    serverHandshakeState.clientExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
                    serverHandshakeState.serverContext.setClientVersion(readVersion);
                    serverHandshakeState.server.notifyClientVersion(readVersion);
                    serverHandshakeState.serverContext.getSecurityParameters().clientRandom = readFully;
                    serverHandshakeState.server.notifyOfferedCipherSuites(serverHandshakeState.offeredCipherSuites);
                    serverHandshakeState.server.notifyOfferedCompressionMethods(serverHandshakeState.offeredCompressionMethods);
                    if (Arrays.contains(serverHandshakeState.offeredCipherSuites, 255)) {
                        serverHandshakeState.secure_renegotiation = true;
                    }
                    byte[] extensionData = TlsUtils.getExtensionData(serverHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
                    if (extensionData != null) {
                        serverHandshakeState.secure_renegotiation = true;
                        if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                            throw new TlsFatalAlert(40);
                        }
                    }
                    serverHandshakeState.server.notifySecureRenegotiation(serverHandshakeState.secure_renegotiation);
                    if (serverHandshakeState.clientExtensions != null) {
                        serverHandshakeState.server.processClientExtensions(serverHandshakeState.clientExtensions);
                        return;
                    }
                    return;
                }
                throw new TlsFatalAlert(47);
            }
            throw new TlsFatalAlert(47);
        }
        throw new TlsFatalAlert(47);
    }

    /* access modifiers changed from: protected */
    public void processClientKeyExchange(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        serverHandshakeState.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    /* access modifiers changed from: protected */
    public void processClientSupplementalData(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        serverHandshakeState.server.processClientSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    /* access modifiers changed from: protected */
    public boolean expectCertificateVerifyMessage(ServerHandshakeState serverHandshakeState) {
        return serverHandshakeState.clientCertificateType >= 0 && TlsUtils.hasSigningCapability(serverHandshakeState.clientCertificateType);
    }
}
