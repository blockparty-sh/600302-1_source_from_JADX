package org.bitcoinj.protocols.payments;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import com.yakivmospan.scytale.Options;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateParsingException;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.bitcoin.protocols.payments.Protos.Payment;
import org.bitcoin.protocols.payments.Protos.PaymentACK;
import org.bitcoin.protocols.payments.Protos.PaymentDetails;
import org.bitcoin.protocols.payments.Protos.PaymentRequest;
import org.bitcoin.protocols.payments.Protos.PaymentRequest.Builder;
import org.bitcoin.protocols.payments.Protos.X509Certificates;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.crypto.X509Utils;
import org.bitcoinj.protocols.payments.PaymentProtocolException.PkiVerificationException;
import org.bitcoinj.script.ScriptBuilder;

public class PaymentProtocol {
    public static final String MIMETYPE_PAYMENT = "application/bitcoincash-payment";
    public static final String MIMETYPE_PAYMENTACK = "application/bitcoincash-paymentack";
    public static final String MIMETYPE_PAYMENTREQUEST = "application/bitcoincash-paymentrequest";

    public static class Ack {
        @Nullable
        private final String memo;

        Ack(@Nullable String str) {
            this.memo = str;
        }

        @Nullable
        public String getMemo() {
            return this.memo;
        }
    }

    public static class Output implements Serializable {
        @Nullable
        public final Coin amount;
        public final byte[] scriptData;

        public Output(@Nullable Coin coin, byte[] bArr) {
            this.amount = coin;
            this.scriptData = bArr;
        }
    }

    public static class PkiVerificationData {
        public final String displayName;
        public final PublicKey merchantSigningKey;
        public final TrustAnchor rootAuthority;
        public final String rootAuthorityName;

        private PkiVerificationData(@Nullable String str, PublicKey publicKey, TrustAnchor trustAnchor) throws PkiVerificationException {
            try {
                this.displayName = str;
                this.merchantSigningKey = publicKey;
                this.rootAuthority = trustAnchor;
                this.rootAuthorityName = X509Utils.getDisplayNameFromCertificate(trustAnchor.getTrustedCert(), true);
            } catch (CertificateParsingException e) {
                throw new PkiVerificationException((Exception) e);
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("displayName", (Object) this.displayName).add("rootAuthorityName", (Object) this.rootAuthorityName).add("merchantSigningKey", (Object) this.merchantSigningKey).add("rootAuthority", (Object) this.rootAuthority).toString();
        }
    }

    public static Builder createPaymentRequest(NetworkParameters networkParameters, @Nullable Coin coin, Address address, @Nullable String str, @Nullable String str2, @Nullable byte[] bArr) {
        return createPaymentRequest(networkParameters, ImmutableList.m127of(createPayToAddressOutput(coin, address)), str, str2, bArr);
    }

    public static Builder createPaymentRequest(NetworkParameters networkParameters, List<org.bitcoin.protocols.payments.Protos.Output> list, @Nullable String str, @Nullable String str2, @Nullable byte[] bArr) {
        PaymentDetails.Builder newBuilder = PaymentDetails.newBuilder();
        newBuilder.setNetwork(networkParameters.getPaymentProtocolId());
        for (org.bitcoin.protocols.payments.Protos.Output addOutputs : list) {
            newBuilder.addOutputs(addOutputs);
        }
        if (str != null) {
            newBuilder.setMemo(str);
        }
        if (str2 != null) {
            newBuilder.setPaymentUrl(str2);
        }
        if (bArr != null) {
            newBuilder.setMerchantData(ByteString.copyFrom(bArr));
        }
        newBuilder.setTime(C3447Utils.currentTimeSeconds());
        Builder newBuilder2 = PaymentRequest.newBuilder();
        newBuilder2.setSerializedPaymentDetails(newBuilder.build().toByteString());
        return newBuilder2;
    }

    public static PaymentSession parsePaymentRequest(PaymentRequest paymentRequest) throws PaymentProtocolException {
        return new PaymentSession(paymentRequest, false, null);
    }

    public static void signPaymentRequest(Builder builder, X509Certificate[] x509CertificateArr, PrivateKey privateKey) {
        try {
            X509Certificates.Builder newBuilder = X509Certificates.newBuilder();
            for (X509Certificate encoded : x509CertificateArr) {
                newBuilder.addCertificate(ByteString.copyFrom(encoded.getEncoded()));
            }
            builder.setPkiType("x509+sha256");
            builder.setPkiData(newBuilder.build().toByteString());
            builder.setSignature(ByteString.EMPTY);
            PaymentRequest build = builder.build();
            if (Options.ALGORITHM_RSA.equalsIgnoreCase(privateKey.getAlgorithm())) {
                Signature instance = Signature.getInstance("SHA256withRSA");
                instance.initSign(privateKey);
                instance.update(build.toByteArray());
                builder.setSignature(ByteString.copyFrom(instance.sign()));
                return;
            }
            throw new IllegalStateException(privateKey.getAlgorithm());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: used method not loaded: org.bitcoinj.protocols.payments.PaymentProtocolException.PkiVerificationException.<init>(java.lang.Exception):null, types can be incorrect */
    /* JADX INFO: used method not loaded: org.bitcoinj.protocols.payments.PaymentProtocolException.InvalidPkiData.<init>(java.lang.Exception):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00eb, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00f1, code lost:
        throw new java.lang.RuntimeException(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f2, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f8, code lost:
        throw new org.bitcoinj.protocols.payments.PaymentProtocolException.PkiVerificationException((java.lang.Exception) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f9, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ff, code lost:
        throw new org.bitcoinj.protocols.payments.PaymentProtocolException.PkiVerificationException((java.lang.Exception) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0100, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0101, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0108, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010e, code lost:
        throw new java.lang.RuntimeException(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x010f, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0115, code lost:
        throw new java.lang.RuntimeException(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0116, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011c, code lost:
        throw new org.bitcoinj.protocols.payments.PaymentProtocolException.PkiVerificationException((java.lang.Exception) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0123, code lost:
        throw new org.bitcoinj.protocols.payments.PaymentProtocolException.InvalidPkiData((java.lang.Exception) r7);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00eb A[ExcHandler: KeyStoreException (r7v9 'e' java.security.KeyStoreException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f2 A[ExcHandler: SignatureException (r7v8 'e' java.security.SignatureException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f9 A[ExcHandler: InvalidKeyException (r7v7 'e' java.security.InvalidKeyException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0108 A[ExcHandler: InvalidAlgorithmParameterException (r7v4 'e' java.security.InvalidAlgorithmParameterException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010f A[ExcHandler: NoSuchAlgorithmException (r7v3 'e' java.security.NoSuchAlgorithmException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0116 A[ExcHandler: CertificateException (r7v2 'e' java.security.cert.CertificateException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011d A[ExcHandler: InvalidProtocolBufferException (r7v1 'e' com.google.protobuf.InvalidProtocolBufferException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.bitcoinj.protocols.payments.PaymentProtocol.PkiVerificationData verifyPaymentRequestPki(org.bitcoin.protocols.payments.Protos.PaymentRequest r7, java.security.KeyStore r8) throws org.bitcoinj.protocols.payments.PaymentProtocolException {
        /*
            r0 = 0
            java.lang.String r1 = r7.getPkiType()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.String r2 = "none"
            boolean r2 = r2.equals(r1)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            if (r2 == 0) goto L_0x000e
            return r0
        L_0x000e:
            java.lang.String r2 = "x509+sha256"
            boolean r2 = r2.equals(r1)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            if (r2 == 0) goto L_0x0019
            java.lang.String r1 = "SHA256withRSA"
            goto L_0x0023
        L_0x0019:
            java.lang.String r2 = "x509+sha1"
            boolean r2 = r2.equals(r1)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            if (r2 == 0) goto L_0x00d4
            java.lang.String r1 = "SHA1withRSA"
        L_0x0023:
            com.google.protobuf.ByteString r2 = r7.getPkiData()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            org.bitcoin.protocols.payments.Protos$X509Certificates r2 = org.bitcoin.protocols.payments.Protos.X509Certificates.parseFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            int r3 = r2.getCertificateCount()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            if (r3 == 0) goto L_0x00cc
            java.lang.String r3 = "X.509"
            java.security.cert.CertificateFactory r3 = java.security.cert.CertificateFactory.getInstance(r3)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.util.ArrayList r4 = com.google.common.collect.Lists.newArrayList()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.util.List r2 = r2.getCertificateList()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
        L_0x0043:
            boolean r5 = r2.hasNext()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            if (r5 == 0) goto L_0x005d
            java.lang.Object r5 = r2.next()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            com.google.protobuf.ByteString r5 = (com.google.protobuf.ByteString) r5     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.io.InputStream r5 = r5.newInput()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.cert.Certificate r5 = r3.generateCertificate(r5)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r4.add(r5)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            goto L_0x0043
        L_0x005d:
            java.security.cert.CertPath r2 = r3.generateCertPath(r4)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.cert.PKIXParameters r3 = new java.security.cert.PKIXParameters     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r3.<init>(r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r8 = 0
            r3.setRevocationEnabled(r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.String r5 = "PKIX"
            java.security.cert.CertPathValidator r5 = java.security.cert.CertPathValidator.getInstance(r5)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.cert.CertPathValidatorResult r2 = r5.validate(r2, r3)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.cert.PKIXCertPathValidatorResult r2 = (java.security.cert.PKIXCertPathValidatorResult) r2     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.PublicKey r3 = r2.getPublicKey()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.Signature r1 = java.security.Signature.getInstance(r1)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r1.initVerify(r3)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            org.bitcoin.protocols.payments.Protos$PaymentRequest$Builder r5 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            com.google.protobuf.ByteString r6 = com.google.protobuf.ByteString.EMPTY     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r5.setSignature(r6)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            org.bitcoin.protocols.payments.Protos$PaymentRequest r5 = r5.build()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            byte[] r5 = r5.toByteArray()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r1.update(r5)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            com.google.protobuf.ByteString r7 = r7.getSignature()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            byte[] r7 = r7.toByteArray()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            boolean r7 = r1.verify(r7)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            if (r7 == 0) goto L_0x00c2
            java.lang.Object r7 = r4.get(r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.cert.X509Certificate r7 = (java.security.cert.X509Certificate) r7     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r8 = 1
            java.lang.String r7 = org.bitcoinj.crypto.X509Utils.getDisplayNameFromCertificate(r7, r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            if (r7 == 0) goto L_0x00ba
            org.bitcoinj.protocols.payments.PaymentProtocol$PkiVerificationData r8 = new org.bitcoinj.protocols.payments.PaymentProtocol$PkiVerificationData     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.security.cert.TrustAnchor r1 = r2.getTrustAnchor()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r8.<init>(r7, r3, r1)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            return r8
        L_0x00ba:
            org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException r7 = new org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.String r8 = "Could not extract name from certificate"
            r7.<init>(r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            throw r7     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
        L_0x00c2:
            org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException r7 = new org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.String r8 = "Invalid signature, this payment request is not valid."
            r7.<init>(r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            throw r7     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x00ca, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
        L_0x00ca:
            r7 = move-exception
            goto L_0x0102
        L_0x00cc:
            org.bitcoinj.protocols.payments.PaymentProtocolException$InvalidPkiData r7 = new org.bitcoinj.protocols.payments.PaymentProtocolException$InvalidPkiData     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.String r8 = "No certificates provided in message: server config error"
            r7.<init>(r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            throw r7     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
        L_0x00d4:
            org.bitcoinj.protocols.payments.PaymentProtocolException$InvalidPkiType r7 = new org.bitcoinj.protocols.payments.PaymentProtocolException$InvalidPkiType     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.String r2 = "Unsupported PKI type: "
            r8.append(r2)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r8.append(r1)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            java.lang.String r8 = r8.toString()     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            r7.<init>(r8)     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
            throw r7     // Catch:{ InvalidProtocolBufferException -> 0x011d, CertificateException -> 0x0116, NoSuchAlgorithmException -> 0x010f, InvalidAlgorithmParameterException -> 0x0108, CertPathValidatorException -> 0x0100, InvalidKeyException -> 0x00f9, SignatureException -> 0x00f2, KeyStoreException -> 0x00eb }
        L_0x00eb:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            r8.<init>(r7)
            throw r8
        L_0x00f2:
            r7 = move-exception
            org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException r8 = new org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException
            r8.<init>(r7)
            throw r8
        L_0x00f9:
            r7 = move-exception
            org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException r8 = new org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException
            r8.<init>(r7)
            throw r8
        L_0x0100:
            r7 = move-exception
            r4 = r0
        L_0x0102:
            org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException r8 = new org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException
            r8.<init>(r7, r4)
            throw r8
        L_0x0108:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            r8.<init>(r7)
            throw r8
        L_0x010f:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            r8.<init>(r7)
            throw r8
        L_0x0116:
            r7 = move-exception
            org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException r8 = new org.bitcoinj.protocols.payments.PaymentProtocolException$PkiVerificationException
            r8.<init>(r7)
            throw r8
        L_0x011d:
            r7 = move-exception
            org.bitcoinj.protocols.payments.PaymentProtocolException$InvalidPkiData r8 = new org.bitcoinj.protocols.payments.PaymentProtocolException$InvalidPkiData
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.payments.PaymentProtocol.verifyPaymentRequestPki(org.bitcoin.protocols.payments.Protos$PaymentRequest, java.security.KeyStore):org.bitcoinj.protocols.payments.PaymentProtocol$PkiVerificationData");
    }

    public static Payment createPaymentMessage(List<Transaction> list, @Nullable Coin coin, @Nullable Address address, @Nullable String str, @Nullable byte[] bArr) {
        if (address == null) {
            return createPaymentMessage(list, null, str, bArr);
        }
        if (coin != null) {
            return createPaymentMessage(list, ImmutableList.m127of(createPayToAddressOutput(coin, address)), str, bArr);
        }
        throw new IllegalArgumentException("Specify refund amount if refund address is specified.");
    }

    public static Payment createPaymentMessage(List<Transaction> list, @Nullable List<org.bitcoin.protocols.payments.Protos.Output> list2, @Nullable String str, @Nullable byte[] bArr) {
        Payment.Builder newBuilder = Payment.newBuilder();
        for (Transaction transaction : list) {
            transaction.verify();
            newBuilder.addTransactions(ByteString.copyFrom(transaction.unsafeBitcoinSerialize()));
        }
        if (list2 != null) {
            for (org.bitcoin.protocols.payments.Protos.Output addRefundTo : list2) {
                newBuilder.addRefundTo(addRefundTo);
            }
        }
        if (str != null) {
            newBuilder.setMemo(str);
        }
        if (bArr != null) {
            newBuilder.setMerchantData(ByteString.copyFrom(bArr));
        }
        return newBuilder.build();
    }

    public static List<Transaction> parseTransactionsFromPaymentMessage(NetworkParameters networkParameters, Payment payment) {
        ArrayList arrayList = new ArrayList(payment.getTransactionsCount());
        for (ByteString byteArray : payment.getTransactionsList()) {
            arrayList.add(networkParameters.getDefaultSerializer().makeTransaction(byteArray.toByteArray()));
        }
        return arrayList;
    }

    public static PaymentACK createPaymentAck(Payment payment, @Nullable String str) {
        PaymentACK.Builder newBuilder = PaymentACK.newBuilder();
        newBuilder.setPayment(payment);
        if (str != null) {
            newBuilder.setMemo(str);
        }
        return newBuilder.build();
    }

    public static Ack parsePaymentAck(PaymentACK paymentACK) {
        return new Ack(paymentACK.hasMemo() ? paymentACK.getMemo() : null);
    }

    public static org.bitcoin.protocols.payments.Protos.Output createPayToAddressOutput(@Nullable Coin coin, Address address) {
        org.bitcoin.protocols.payments.Protos.Output.Builder newBuilder = org.bitcoin.protocols.payments.Protos.Output.newBuilder();
        if (coin != null) {
            NetworkParameters parameters = address.getParameters();
            if (!parameters.hasMaxMoney() || coin.compareTo(parameters.getMaxMoney()) <= 0) {
                newBuilder.setAmount(coin.value);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Amount too big: ");
                sb.append(coin);
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            newBuilder.setAmount(0);
        }
        newBuilder.setScript(ByteString.copyFrom(ScriptBuilder.createOutputScript(address).getProgram()));
        return newBuilder.build();
    }
}
