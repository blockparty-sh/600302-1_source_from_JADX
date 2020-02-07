package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.tls.SessionParameters.Builder;
import org.spongycastle.util.Arrays;

public class DTLSClientProtocol extends DTLSProtocol {

    protected static class ClientHandshakeState {
        boolean allowCertificateStatus = false;
        TlsAuthentication authentication = null;
        CertificateRequest certificateRequest = null;
        CertificateStatus certificateStatus = null;
        TlsClient client = null;
        TlsClientContextImpl clientContext = null;
        TlsCredentials clientCredentials = null;
        Hashtable clientExtensions = null;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        short maxFragmentLength = -1;
        int[] offeredCipherSuites = null;
        short[] offeredCompressionMethods = null;
        boolean secure_renegotiation = false;
        int selectedCipherSuite = -1;
        short selectedCompressionMethod = -1;
        byte[] selectedSessionID = null;
        SessionParameters sessionParameters = null;
        Builder sessionParametersBuilder = null;
        TlsSession tlsSession = null;

        protected ClientHandshakeState() {
        }
    }

    public DTLSClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public DTLSTransport connect(TlsClient tlsClient, DatagramTransport datagramTransport) throws IOException {
        if (tlsClient == null) {
            throw new IllegalArgumentException("'client' cannot be null");
        } else if (datagramTransport != null) {
            SecurityParameters securityParameters = new SecurityParameters();
            securityParameters.entity = 1;
            ClientHandshakeState clientHandshakeState = new ClientHandshakeState();
            clientHandshakeState.client = tlsClient;
            clientHandshakeState.clientContext = new TlsClientContextImpl(this.secureRandom, securityParameters);
            securityParameters.clientRandom = TlsProtocol.createRandomBlock(tlsClient.shouldUseGMTUnixTime(), clientHandshakeState.clientContext.getNonceRandomGenerator());
            tlsClient.init(clientHandshakeState.clientContext);
            DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(datagramTransport, clientHandshakeState.clientContext, tlsClient, 22);
            TlsSession sessionToResume = clientHandshakeState.client.getSessionToResume();
            if (sessionToResume != null) {
                SessionParameters exportSessionParameters = sessionToResume.exportSessionParameters();
                if (exportSessionParameters != null) {
                    clientHandshakeState.tlsSession = sessionToResume;
                    clientHandshakeState.sessionParameters = exportSessionParameters;
                }
            }
            try {
                return clientHandshake(clientHandshakeState, dTLSRecordLayer);
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
    public DTLSTransport clientHandshake(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer) throws IOException {
        Message message;
        Certificate certificate;
        SignatureAndHashAlgorithm signatureAndHashAlgorithm;
        byte[] bArr;
        ClientHandshakeState clientHandshakeState2 = clientHandshakeState;
        DTLSRecordLayer dTLSRecordLayer2 = dTLSRecordLayer;
        SecurityParameters securityParameters = clientHandshakeState2.clientContext.getSecurityParameters();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(clientHandshakeState2.clientContext, dTLSRecordLayer2);
        byte[] generateClientHello = generateClientHello(clientHandshakeState2, clientHandshakeState2.client);
        boolean z = true;
        dTLSReliableHandshake.sendMessage(1, generateClientHello);
        Message receiveMessage = dTLSReliableHandshake.receiveMessage();
        while (receiveMessage.getType() == 3) {
            if (dTLSRecordLayer.resetDiscoveredPeerVersion().isEqualOrEarlierVersionOf(clientHandshakeState2.clientContext.getClientVersion())) {
                byte[] patchClientHelloWithCookie = patchClientHelloWithCookie(generateClientHello, processHelloVerifyRequest(clientHandshakeState2, receiveMessage.getBody()));
                dTLSReliableHandshake.resetHandshakeMessagesDigest();
                dTLSReliableHandshake.sendMessage(1, patchClientHelloWithCookie);
                receiveMessage = dTLSReliableHandshake.receiveMessage();
            } else {
                throw new TlsFatalAlert(47);
            }
        }
        if (receiveMessage.getType() == 2) {
            reportServerVersion(clientHandshakeState2, dTLSRecordLayer.getDiscoveredPeerVersion());
            processServerHello(clientHandshakeState2, receiveMessage.getBody());
            if (clientHandshakeState2.maxFragmentLength >= 0) {
                dTLSRecordLayer2.setPlaintextLimit(1 << (clientHandshakeState2.maxFragmentLength + 8));
            }
            securityParameters.cipherSuite = clientHandshakeState2.selectedCipherSuite;
            securityParameters.compressionAlgorithm = clientHandshakeState2.selectedCompressionMethod;
            securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(clientHandshakeState2.clientContext, clientHandshakeState2.selectedCipherSuite);
            securityParameters.verifyDataLength = 12;
            dTLSReliableHandshake.notifyHelloComplete();
            if (clientHandshakeState2.selectedSessionID.length <= 0 || clientHandshakeState2.tlsSession == null || !Arrays.areEqual(clientHandshakeState2.selectedSessionID, clientHandshakeState2.tlsSession.getSessionID())) {
                z = false;
            }
            String str = ExporterLabel.client_finished;
            String str2 = ExporterLabel.server_finished;
            if (!z) {
                invalidateSession(clientHandshakeState);
                if (clientHandshakeState2.selectedSessionID.length > 0) {
                    clientHandshakeState2.tlsSession = new TlsSessionImpl(clientHandshakeState2.selectedSessionID, null);
                }
                Message receiveMessage2 = dTLSReliableHandshake.receiveMessage();
                if (receiveMessage2.getType() == 23) {
                    processServerSupplementalData(clientHandshakeState2, receiveMessage2.getBody());
                    receiveMessage2 = dTLSReliableHandshake.receiveMessage();
                } else {
                    clientHandshakeState2.client.processServerSupplementalData(null);
                }
                clientHandshakeState2.keyExchange = clientHandshakeState2.client.getKeyExchange();
                clientHandshakeState2.keyExchange.init(clientHandshakeState2.clientContext);
                if (receiveMessage2.getType() == 11) {
                    certificate = processServerCertificate(clientHandshakeState2, receiveMessage2.getBody());
                    message = dTLSReliableHandshake.receiveMessage();
                } else {
                    clientHandshakeState2.keyExchange.skipServerCredentials();
                    message = receiveMessage2;
                    certificate = null;
                }
                if (certificate == null || certificate.isEmpty()) {
                    clientHandshakeState2.allowCertificateStatus = false;
                }
                if (message.getType() == 22) {
                    processCertificateStatus(clientHandshakeState2, message.getBody());
                    message = dTLSReliableHandshake.receiveMessage();
                }
                if (message.getType() == 12) {
                    processServerKeyExchange(clientHandshakeState2, message.getBody());
                    message = dTLSReliableHandshake.receiveMessage();
                } else {
                    clientHandshakeState2.keyExchange.skipServerKeyExchange();
                }
                if (message.getType() == 13) {
                    processCertificateRequest(clientHandshakeState2, message.getBody());
                    TlsUtils.trackHashAlgorithms(dTLSReliableHandshake.getHandshakeHash(), clientHandshakeState2.certificateRequest.getSupportedSignatureAlgorithms());
                    message = dTLSReliableHandshake.receiveMessage();
                }
                if (message.getType() != 14) {
                    throw new TlsFatalAlert(10);
                } else if (message.getBody().length == 0) {
                    dTLSReliableHandshake.getHandshakeHash().sealHashAlgorithms();
                    Vector clientSupplementalData = clientHandshakeState2.client.getClientSupplementalData();
                    if (clientSupplementalData != null) {
                        dTLSReliableHandshake.sendMessage(23, generateSupplementalData(clientSupplementalData));
                    }
                    if (clientHandshakeState2.certificateRequest != null) {
                        clientHandshakeState2.clientCredentials = clientHandshakeState2.authentication.getClientCredentials(clientHandshakeState2.certificateRequest);
                        Certificate certificate2 = clientHandshakeState2.clientCredentials != null ? clientHandshakeState2.clientCredentials.getCertificate() : null;
                        if (certificate2 == null) {
                            certificate2 = Certificate.EMPTY_CHAIN;
                        }
                        dTLSReliableHandshake.sendMessage(11, generateCertificate(certificate2));
                    }
                    if (clientHandshakeState2.clientCredentials != null) {
                        clientHandshakeState2.keyExchange.processClientCredentials(clientHandshakeState2.clientCredentials);
                    } else {
                        clientHandshakeState2.keyExchange.skipClientCredentials();
                    }
                    dTLSReliableHandshake.sendMessage(16, generateClientKeyExchange(clientHandshakeState));
                    TlsProtocol.establishMasterSecret(clientHandshakeState2.clientContext, clientHandshakeState2.keyExchange);
                    dTLSRecordLayer2.initPendingEpoch(clientHandshakeState2.client.getCipher());
                    TlsHandshakeHash prepareToFinish = dTLSReliableHandshake.prepareToFinish();
                    if (clientHandshakeState2.clientCredentials != null && (clientHandshakeState2.clientCredentials instanceof TlsSignerCredentials)) {
                        TlsSignerCredentials tlsSignerCredentials = (TlsSignerCredentials) clientHandshakeState2.clientCredentials;
                        if (TlsUtils.isTLSv12(clientHandshakeState2.clientContext)) {
                            signatureAndHashAlgorithm = tlsSignerCredentials.getSignatureAndHashAlgorithm();
                            if (signatureAndHashAlgorithm != null) {
                                bArr = prepareToFinish.getFinalHash(signatureAndHashAlgorithm.getHash());
                            } else {
                                throw new TlsFatalAlert(80);
                            }
                        } else {
                            bArr = TlsProtocol.getCurrentPRFHash(clientHandshakeState2.clientContext, prepareToFinish, null);
                            signatureAndHashAlgorithm = null;
                        }
                        dTLSReliableHandshake.sendMessage(15, generateCertificateVerify(clientHandshakeState2, new DigitallySigned(signatureAndHashAlgorithm, tlsSignerCredentials.generateCertificateSignature(bArr))));
                    }
                    dTLSReliableHandshake.sendMessage(20, TlsUtils.calculateVerifyData(clientHandshakeState2.clientContext, str, TlsProtocol.getCurrentPRFHash(clientHandshakeState2.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                    if (clientHandshakeState2.expectSessionTicket) {
                        Message receiveMessage3 = dTLSReliableHandshake.receiveMessage();
                        if (receiveMessage3.getType() == 4) {
                            processNewSessionTicket(clientHandshakeState2, receiveMessage3.getBody());
                        } else {
                            throw new TlsFatalAlert(10);
                        }
                    }
                    processFinished(dTLSReliableHandshake.receiveMessageBody(20), TlsUtils.calculateVerifyData(clientHandshakeState2.clientContext, str2, TlsProtocol.getCurrentPRFHash(clientHandshakeState2.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                    dTLSReliableHandshake.finish();
                    if (clientHandshakeState2.tlsSession != null) {
                        clientHandshakeState2.sessionParameters = new Builder().setCipherSuite(securityParameters.cipherSuite).setCompressionAlgorithm(securityParameters.compressionAlgorithm).setMasterSecret(securityParameters.masterSecret).setPeerCertificate(certificate).build();
                        clientHandshakeState2.tlsSession = TlsUtils.importSession(clientHandshakeState2.tlsSession.getSessionID(), clientHandshakeState2.sessionParameters);
                        clientHandshakeState2.clientContext.setResumableSession(clientHandshakeState2.tlsSession);
                    }
                    clientHandshakeState2.client.notifyHandshakeComplete();
                    return new DTLSTransport(dTLSRecordLayer2);
                } else {
                    throw new TlsFatalAlert(50);
                }
            } else if (securityParameters.getCipherSuite() == clientHandshakeState2.sessionParameters.getCipherSuite() && securityParameters.getCompressionAlgorithm() == clientHandshakeState2.sessionParameters.getCompressionAlgorithm()) {
                securityParameters.masterSecret = Arrays.clone(clientHandshakeState2.sessionParameters.getMasterSecret());
                dTLSRecordLayer2.initPendingEpoch(clientHandshakeState2.client.getCipher());
                processFinished(dTLSReliableHandshake.receiveMessageBody(20), TlsUtils.calculateVerifyData(clientHandshakeState2.clientContext, str2, TlsProtocol.getCurrentPRFHash(clientHandshakeState2.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                dTLSReliableHandshake.sendMessage(20, TlsUtils.calculateVerifyData(clientHandshakeState2.clientContext, str, TlsProtocol.getCurrentPRFHash(clientHandshakeState2.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                dTLSReliableHandshake.finish();
                clientHandshakeState2.clientContext.setResumableSession(clientHandshakeState2.tlsSession);
                clientHandshakeState2.client.notifyHandshakeComplete();
                return new DTLSTransport(dTLSRecordLayer2);
            } else {
                throw new TlsFatalAlert(47);
            }
        } else {
            throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateVerify(ClientHandshakeState clientHandshakeState, DigitallySigned digitallySigned) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        digitallySigned.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateClientHello(ClientHandshakeState clientHandshakeState, TlsClient tlsClient) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtocolVersion clientVersion = tlsClient.getClientVersion();
        if (clientVersion.isDTLS()) {
            clientHandshakeState.clientContext.setClientVersion(clientVersion);
            TlsUtils.writeVersion(clientVersion, byteArrayOutputStream);
            byteArrayOutputStream.write(clientHandshakeState.clientContext.getSecurityParameters().getClientRandom());
            byte[] bArr = TlsUtils.EMPTY_BYTES;
            if (clientHandshakeState.tlsSession != null) {
                bArr = clientHandshakeState.tlsSession.getSessionID();
                if (bArr == null || bArr.length > 32) {
                    bArr = TlsUtils.EMPTY_BYTES;
                }
            }
            TlsUtils.writeOpaque8(bArr, byteArrayOutputStream);
            TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
            clientHandshakeState.offeredCipherSuites = tlsClient.getCipherSuites();
            clientHandshakeState.clientExtensions = tlsClient.getClientExtensions();
            boolean z = !Arrays.contains(clientHandshakeState.offeredCipherSuites, 255);
            if ((TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) && z) {
                clientHandshakeState.offeredCipherSuites = Arrays.append(clientHandshakeState.offeredCipherSuites, 255);
            }
            TlsUtils.writeUint16ArrayWithUint16Length(clientHandshakeState.offeredCipherSuites, byteArrayOutputStream);
            clientHandshakeState.offeredCompressionMethods = new short[]{0};
            TlsUtils.writeUint8ArrayWithUint8Length(clientHandshakeState.offeredCompressionMethods, byteArrayOutputStream);
            if (clientHandshakeState.clientExtensions != null) {
                TlsProtocol.writeExtensions(byteArrayOutputStream, clientHandshakeState.clientExtensions);
            }
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public byte[] generateClientKeyExchange(ClientHandshakeState clientHandshakeState) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        clientHandshakeState.keyExchange.generateClientKeyExchange(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public void invalidateSession(ClientHandshakeState clientHandshakeState) {
        if (clientHandshakeState.sessionParameters != null) {
            clientHandshakeState.sessionParameters.clear();
            clientHandshakeState.sessionParameters = null;
        }
        if (clientHandshakeState.tlsSession != null) {
            clientHandshakeState.tlsSession.invalidate();
            clientHandshakeState.tlsSession = null;
        }
    }

    /* access modifiers changed from: protected */
    public void processCertificateRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        if (clientHandshakeState.authentication != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            clientHandshakeState.certificateRequest = CertificateRequest.parse(clientHandshakeState.clientContext, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            clientHandshakeState.keyExchange.validateCertificateRequest(clientHandshakeState.certificateRequest);
            return;
        }
        throw new TlsFatalAlert(40);
    }

    /* access modifiers changed from: protected */
    public void processCertificateStatus(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        if (clientHandshakeState.allowCertificateStatus) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            clientHandshakeState.certificateStatus = CertificateStatus.parse(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return;
        }
        throw new TlsFatalAlert(10);
    }

    /* access modifiers changed from: protected */
    public byte[] processHelloVerifyRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!readVersion.isEqualOrEarlierVersionOf(clientHandshakeState.clientContext.getClientVersion())) {
            throw new TlsFatalAlert(47);
        } else if (ProtocolVersion.DTLSv12.isEqualOrEarlierVersionOf(readVersion) || readOpaque8.length <= 32) {
            return readOpaque8;
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    /* access modifiers changed from: protected */
    public void processNewSessionTicket(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.client.notifyNewSessionTicket(parse);
    }

    /* access modifiers changed from: protected */
    public Certificate processServerCertificate(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate parse = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.keyExchange.processServerCertificate(parse);
        clientHandshakeState.authentication = clientHandshakeState.client.getAuthentication();
        clientHandshakeState.authentication.notifyServerCertificate(parse);
        return parse;
    }

    /* access modifiers changed from: protected */
    public void processServerHello(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        SecurityParameters securityParameters = clientHandshakeState.clientContext.getSecurityParameters();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        reportServerVersion(clientHandshakeState, readVersion);
        securityParameters.serverRandom = TlsUtils.readFully(32, (InputStream) byteArrayInputStream);
        clientHandshakeState.selectedSessionID = TlsUtils.readOpaque8(byteArrayInputStream);
        if (clientHandshakeState.selectedSessionID.length <= 32) {
            clientHandshakeState.client.notifySessionID(clientHandshakeState.selectedSessionID);
            clientHandshakeState.selectedCipherSuite = TlsUtils.readUint16(byteArrayInputStream);
            if (!Arrays.contains(clientHandshakeState.offeredCipherSuites, clientHandshakeState.selectedCipherSuite) || clientHandshakeState.selectedCipherSuite == 0 || clientHandshakeState.selectedCipherSuite == 255 || !TlsUtils.isValidCipherSuiteForVersion(clientHandshakeState.selectedCipherSuite, readVersion)) {
                throw new TlsFatalAlert(47);
            }
            validateSelectedCipherSuite(clientHandshakeState.selectedCipherSuite, 47);
            clientHandshakeState.client.notifySelectedCipherSuite(clientHandshakeState.selectedCipherSuite);
            clientHandshakeState.selectedCompressionMethod = TlsUtils.readUint8(byteArrayInputStream);
            if (Arrays.contains(clientHandshakeState.offeredCompressionMethods, clientHandshakeState.selectedCompressionMethod)) {
                clientHandshakeState.client.notifySelectedCompressionMethod(clientHandshakeState.selectedCompressionMethod);
                Hashtable readExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
                if (readExtensions != null) {
                    Enumeration keys = readExtensions.keys();
                    while (keys.hasMoreElements()) {
                        Integer num = (Integer) keys.nextElement();
                        if (!num.equals(TlsProtocol.EXT_RenegotiationInfo) && TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, num) == null) {
                            throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                        }
                    }
                    byte[] bArr2 = (byte[]) readExtensions.get(TlsProtocol.EXT_RenegotiationInfo);
                    if (bArr2 != null) {
                        clientHandshakeState.secure_renegotiation = true;
                        if (!Arrays.constantTimeAreEqual(bArr2, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                            throw new TlsFatalAlert(40);
                        }
                    }
                    boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(readExtensions);
                    if (!hasEncryptThenMACExtension || TlsUtils.isBlockCipherSuite(clientHandshakeState.selectedCipherSuite)) {
                        securityParameters.encryptThenMAC = hasEncryptThenMACExtension;
                        clientHandshakeState.maxFragmentLength = evaluateMaxFragmentLengthExtension(clientHandshakeState.clientExtensions, readExtensions, 47);
                        securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(readExtensions);
                        clientHandshakeState.allowCertificateStatus = TlsUtils.hasExpectedEmptyExtensionData(readExtensions, TlsExtensionsUtils.EXT_status_request, 47);
                        clientHandshakeState.expectSessionTicket = TlsUtils.hasExpectedEmptyExtensionData(readExtensions, TlsProtocol.EXT_SessionTicket, 47);
                    } else {
                        throw new TlsFatalAlert(47);
                    }
                }
                clientHandshakeState.client.notifySecureRenegotiation(clientHandshakeState.secure_renegotiation);
                if (clientHandshakeState.clientExtensions != null) {
                    clientHandshakeState.client.processServerExtensions(readExtensions);
                    return;
                }
                return;
            }
            throw new TlsFatalAlert(47);
        }
        throw new TlsFatalAlert(47);
    }

    /* access modifiers changed from: protected */
    public void processServerKeyExchange(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.keyExchange.processServerKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    /* access modifiers changed from: protected */
    public void processServerSupplementalData(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        clientHandshakeState.client.processServerSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    /* access modifiers changed from: protected */
    public void reportServerVersion(ClientHandshakeState clientHandshakeState, ProtocolVersion protocolVersion) throws IOException {
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.clientContext;
        ProtocolVersion serverVersion = tlsClientContextImpl.getServerVersion();
        if (serverVersion == null) {
            tlsClientContextImpl.setServerVersion(protocolVersion);
            clientHandshakeState.client.notifyServerVersion(protocolVersion);
        } else if (!serverVersion.equals(protocolVersion)) {
            throw new TlsFatalAlert(47);
        }
    }

    protected static byte[] patchClientHelloWithCookie(byte[] bArr, byte[] bArr2) throws IOException {
        int readUint8 = 35 + TlsUtils.readUint8(bArr, 34);
        int i = readUint8 + 1;
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, readUint8);
        TlsUtils.checkUint8(bArr2.length);
        TlsUtils.writeUint8(bArr2.length, bArr3, readUint8);
        System.arraycopy(bArr2, 0, bArr3, i, bArr2.length);
        System.arraycopy(bArr, i, bArr3, bArr2.length + i, bArr.length - i);
        return bArr3;
    }
}
