package org.bitcoinj.protocols.payments;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
import org.bitcoin.protocols.payments.Protos;
import org.bitcoin.protocols.payments.Protos.Payment;
import org.bitcoin.protocols.payments.Protos.PaymentACK;
import org.bitcoin.protocols.payments.Protos.PaymentDetails;
import org.bitcoin.protocols.payments.Protos.PaymentRequest;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.crypto.TrustStoreLoader;
import org.bitcoinj.crypto.TrustStoreLoader.DefaultTrustStoreLoader;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.protocols.payments.PaymentProtocol.Ack;
import org.bitcoinj.protocols.payments.PaymentProtocol.Output;
import org.bitcoinj.protocols.payments.PaymentProtocol.PkiVerificationData;
import org.bitcoinj.protocols.payments.PaymentProtocolException.Expired;
import org.bitcoinj.protocols.payments.PaymentProtocolException.InvalidNetwork;
import org.bitcoinj.protocols.payments.PaymentProtocolException.InvalidOutputs;
import org.bitcoinj.protocols.payments.PaymentProtocolException.InvalidPaymentRequestURL;
import org.bitcoinj.protocols.payments.PaymentProtocolException.InvalidPaymentURL;
import org.bitcoinj.protocols.payments.PaymentProtocolException.InvalidVersion;
import org.bitcoinj.uri.BitcoinURI;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.SendRequest;

public class PaymentSession {
    private static ListeningExecutorService executor = Threading.THREAD_POOL;
    private NetworkParameters params;
    private PaymentDetails paymentDetails;
    private PaymentRequest paymentRequest;
    @Nullable
    public final PkiVerificationData pkiVerificationData;
    private Coin totalValue;

    public static ListenableFuture<PaymentSession> createFromBitcoinUri(BitcoinURI bitcoinURI) throws PaymentProtocolException {
        return createFromBitcoinUri(bitcoinURI, true, null);
    }

    public static ListenableFuture<PaymentSession> createFromBitcoinUri(BitcoinURI bitcoinURI, boolean z) throws PaymentProtocolException {
        return createFromBitcoinUri(bitcoinURI, z, null);
    }

    public static ListenableFuture<PaymentSession> createFromBitcoinUri(BitcoinURI bitcoinURI, boolean z, @Nullable TrustStoreLoader trustStoreLoader) throws PaymentProtocolException {
        String paymentRequestUrl = bitcoinURI.getPaymentRequestUrl();
        if (paymentRequestUrl != null) {
            try {
                return fetchPaymentRequest(new URI(paymentRequestUrl), z, trustStoreLoader);
            } catch (URISyntaxException e) {
                throw new InvalidPaymentRequestURL((Exception) e);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("No payment request URL (r= parameter) in BitcoinURI ");
            sb.append(bitcoinURI);
            throw new InvalidPaymentRequestURL(sb.toString());
        }
    }

    public static ListenableFuture<PaymentSession> createFromUrl(String str) throws PaymentProtocolException {
        return createFromUrl(str, true, null);
    }

    public static ListenableFuture<PaymentSession> createFromUrl(String str, boolean z) throws PaymentProtocolException {
        return createFromUrl(str, z, null);
    }

    public static ListenableFuture<PaymentSession> createFromUrl(String str, boolean z, @Nullable TrustStoreLoader trustStoreLoader) throws PaymentProtocolException {
        if (str != null) {
            try {
                return fetchPaymentRequest(new URI(str), z, trustStoreLoader);
            } catch (URISyntaxException e) {
                throw new InvalidPaymentRequestURL((Exception) e);
            }
        } else {
            throw new InvalidPaymentRequestURL("null paymentRequestUrl");
        }
    }

    private static ListenableFuture<PaymentSession> fetchPaymentRequest(final URI uri, final boolean z, @Nullable final TrustStoreLoader trustStoreLoader) {
        return executor.submit((Callable<T>) new Callable<PaymentSession>() {
            public PaymentSession call() throws Exception {
                HttpURLConnection httpURLConnection = (HttpURLConnection) uri.toURL().openConnection();
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, PaymentProtocol.MIMETYPE_PAYMENTREQUEST);
                httpURLConnection.setUseCaches(false);
                return new PaymentSession(PaymentRequest.parseFrom(httpURLConnection.getInputStream()), z, trustStoreLoader);
            }
        });
    }

    public PaymentSession(PaymentRequest paymentRequest2) throws PaymentProtocolException {
        this(paymentRequest2, true, null);
    }

    public PaymentSession(PaymentRequest paymentRequest2, boolean z) throws PaymentProtocolException {
        this(paymentRequest2, z, null);
    }

    public PaymentSession(PaymentRequest paymentRequest2, boolean z, @Nullable TrustStoreLoader trustStoreLoader) throws PaymentProtocolException {
        this.totalValue = Coin.ZERO;
        if (trustStoreLoader == null) {
            trustStoreLoader = new DefaultTrustStoreLoader();
        }
        parsePaymentRequest(paymentRequest2);
        if (z) {
            try {
                this.pkiVerificationData = PaymentProtocol.verifyPaymentRequestPki(paymentRequest2, trustStoreLoader.getKeyStore());
            } catch (IOException e) {
                throw new PaymentProtocolException((Exception) e);
            } catch (KeyStoreException e2) {
                throw new PaymentProtocolException((Exception) e2);
            }
        } else {
            this.pkiVerificationData = null;
        }
    }

    public List<Output> getOutputs() {
        ArrayList arrayList = new ArrayList(this.paymentDetails.getOutputsCount());
        for (Protos.Output output : this.paymentDetails.getOutputsList()) {
            arrayList.add(new Output(output.hasAmount() ? Coin.valueOf(output.getAmount()) : null, output.getScript().toByteArray()));
        }
        return arrayList;
    }

    @Nullable
    public String getMemo() {
        if (this.paymentDetails.hasMemo()) {
            return this.paymentDetails.getMemo();
        }
        return null;
    }

    public Coin getValue() {
        return this.totalValue;
    }

    public Date getDate() {
        return new Date(this.paymentDetails.getTime() * 1000);
    }

    @Nullable
    public Date getExpires() {
        if (this.paymentDetails.hasExpires()) {
            return new Date(this.paymentDetails.getExpires() * 1000);
        }
        return null;
    }

    public boolean isExpired() {
        return this.paymentDetails.hasExpires() && System.currentTimeMillis() / 1000 > this.paymentDetails.getExpires();
    }

    @Nullable
    public String getPaymentUrl() {
        if (this.paymentDetails.hasPaymentUrl()) {
            return this.paymentDetails.getPaymentUrl();
        }
        return null;
    }

    @Nullable
    public byte[] getMerchantData() {
        if (this.paymentDetails.hasMerchantData()) {
            return this.paymentDetails.getMerchantData().toByteArray();
        }
        return null;
    }

    public SendRequest getSendRequest() {
        Transaction transaction = new Transaction(this.params);
        for (Protos.Output output : this.paymentDetails.getOutputsList()) {
            transaction.addOutput(new TransactionOutput(this.params, transaction, Coin.valueOf(output.getAmount()), output.getScript().toByteArray()));
        }
        return SendRequest.forTx(transaction).fromPaymentDetails(this.paymentDetails);
    }

    @Nullable
    public ListenableFuture<Ack> sendPayment(List<Transaction> list, @Nullable Address address, @Nullable String str) throws PaymentProtocolException, VerificationException, IOException {
        Payment payment = getPayment(list, address, str);
        if (payment == null) {
            return null;
        }
        if (!isExpired()) {
            try {
                return sendPayment(new URL(this.paymentDetails.getPaymentUrl()), payment);
            } catch (MalformedURLException e) {
                throw new InvalidPaymentURL((Exception) e);
            }
        } else {
            throw new Expired("PaymentRequest is expired");
        }
    }

    @Nullable
    public Payment getPayment(List<Transaction> list, @Nullable Address address, @Nullable String str) throws IOException, InvalidNetwork {
        if (!this.paymentDetails.hasPaymentUrl()) {
            return null;
        }
        for (Transaction params2 : list) {
            if (!params2.getParams().equals(this.params)) {
                throw new InvalidNetwork(this.params.getPaymentProtocolId());
            }
        }
        return PaymentProtocol.createPaymentMessage(list, this.totalValue, address, str, getMerchantData());
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public ListenableFuture<Ack> sendPayment(final URL url, final Payment payment) {
        return executor.submit((Callable<T>) new Callable<Ack>() {
            public Ack call() throws Exception {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", PaymentProtocol.MIMETYPE_PAYMENT);
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, PaymentProtocol.MIMETYPE_PAYMENTACK);
                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(payment.getSerializedSize()));
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                payment.writeTo(dataOutputStream);
                dataOutputStream.flush();
                dataOutputStream.close();
                return PaymentProtocol.parsePaymentAck(PaymentACK.parseFrom(httpURLConnection.getInputStream()));
            }
        });
    }

    private void parsePaymentRequest(PaymentRequest paymentRequest2) throws PaymentProtocolException {
        if (paymentRequest2 != null) {
            try {
                if (paymentRequest2.getPaymentDetailsVersion() == 1) {
                    this.paymentRequest = paymentRequest2;
                    if (paymentRequest2.hasSerializedPaymentDetails()) {
                        this.paymentDetails = PaymentDetails.newBuilder().mergeFrom(paymentRequest2.getSerializedPaymentDetails()).build();
                        if (this.paymentDetails != null) {
                            if (!this.paymentDetails.hasNetwork()) {
                                this.params = MainNetParams.get();
                            } else {
                                this.params = NetworkParameters.fromPmtProtocolID(this.paymentDetails.getNetwork());
                            }
                            if (this.params == null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Invalid network ");
                                sb.append(this.paymentDetails.getNetwork());
                                throw new InvalidNetwork(sb.toString());
                            } else if (this.paymentDetails.getOutputsCount() >= 1) {
                                for (Protos.Output output : this.paymentDetails.getOutputsList()) {
                                    if (output.hasAmount()) {
                                        this.totalValue = this.totalValue.add(Coin.valueOf(output.getAmount()));
                                    }
                                }
                                if (!this.params.hasMaxMoney()) {
                                    return;
                                }
                                if (this.totalValue.compareTo(this.params.getMaxMoney()) > 0) {
                                    throw new InvalidOutputs("The outputs are way too big.");
                                }
                            } else {
                                throw new InvalidOutputs("No outputs");
                            }
                        } else {
                            throw new PaymentProtocolException("Invalid PaymentDetails");
                        }
                    } else {
                        throw new PaymentProtocolException("No PaymentDetails");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Version 1 required. Received version ");
                    sb2.append(paymentRequest2.getPaymentDetailsVersion());
                    throw new InvalidVersion(sb2.toString());
                }
            } catch (InvalidProtocolBufferException e) {
                throw new PaymentProtocolException((Exception) e);
            }
        } else {
            throw new PaymentProtocolException("request cannot be null");
        }
    }

    @Nullable
    public PkiVerificationData verifyPki() {
        return this.pkiVerificationData;
    }

    public NetworkParameters getNetworkParameters() {
        return this.params;
    }

    public PaymentRequest getPaymentRequest() {
        return this.paymentRequest;
    }

    public PaymentDetails getPaymentDetails() {
        return this.paymentDetails;
    }
}
