package org.bitcoinj.wallet;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import org.bitcoin.protocols.payments.Protos.PaymentDetails;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.Purpose;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.utils.ExchangeRate;
import org.bitcoinj.wallet.C3530Wallet.MissingSigsMode;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.spongycastle.crypto.params.KeyParameter;

public class SendRequest {
    public KeyParameter aesKey = null;
    public Address changeAddress = null;
    public CoinSelector coinSelector = null;
    boolean completed;
    public boolean emptyWallet = false;
    public boolean ensureMinRequiredFee = Context.get().isEnsureMinRequiredFee();
    public ExchangeRate exchangeRate = null;
    public Coin feePerKb = Context.get().getFeePerKb();
    public String memo = null;
    public MissingSigsMode missingSigsMode = MissingSigsMode.THROW;
    public boolean shuffleOutputs = true;
    public boolean signInputs = true;

    /* renamed from: tx */
    public Transaction f829tx;
    private boolean useForkId = false;

    private SendRequest() {
    }

    /* renamed from: to */
    public static SendRequest m345to(Address address, Coin coin) {
        SendRequest sendRequest = new SendRequest();
        NetworkParameters parameters = address.getParameters();
        Preconditions.checkNotNull(parameters, "Address is for an unknown network");
        sendRequest.f829tx = new Transaction(parameters);
        sendRequest.f829tx.addOutput(coin, address);
        return sendRequest;
    }

    /* renamed from: to */
    public static SendRequest m346to(NetworkParameters networkParameters, ECKey eCKey, Coin coin) {
        SendRequest sendRequest = new SendRequest();
        sendRequest.f829tx = new Transaction(networkParameters);
        sendRequest.f829tx.addOutput(coin, eCKey);
        return sendRequest;
    }

    public static SendRequest forTx(Transaction transaction) {
        SendRequest sendRequest = new SendRequest();
        sendRequest.f829tx = transaction;
        return sendRequest;
    }

    public static SendRequest emptyWallet(Address address) {
        SendRequest sendRequest = new SendRequest();
        NetworkParameters parameters = address.getParameters();
        Preconditions.checkNotNull(parameters, "Address is for an unknown network");
        sendRequest.f829tx = new Transaction(parameters);
        sendRequest.f829tx.addOutput(Coin.ZERO, address);
        sendRequest.emptyWallet = true;
        return sendRequest;
    }

    public static SendRequest childPaysForParent(C3530Wallet wallet, Transaction transaction, Coin coin) {
        TransactionOutput transactionOutput;
        Iterator it = transaction.getOutputs().iterator();
        while (true) {
            if (!it.hasNext()) {
                transactionOutput = null;
                break;
            }
            transactionOutput = (TransactionOutput) it.next();
            if (transactionOutput.isMine(wallet) && transactionOutput.isAvailableForSpending() && transactionOutput.getValue().isGreaterThan(coin)) {
                break;
            }
        }
        Preconditions.checkNotNull(transactionOutput, "Can't find adequately sized output that spends to us");
        Transaction transaction2 = new Transaction(transaction.getParams());
        transaction2.addInput(transactionOutput);
        transaction2.addOutput(transactionOutput.getValue().subtract(coin), wallet.freshAddress(KeyPurpose.CHANGE));
        transaction2.setPurpose(Purpose.RAISE_FEE);
        SendRequest forTx = forTx(transaction2);
        forTx.completed = true;
        return forTx;
    }

    public static SendRequest toCLTVPaymentChannel(NetworkParameters networkParameters, Date date, ECKey eCKey, ECKey eCKey2, Coin coin) {
        long time = date.getTime() / 1000;
        Preconditions.checkArgument(time >= 500000000, "Release time was too small");
        return toCLTVPaymentChannel(networkParameters, BigInteger.valueOf(time), eCKey, eCKey2, coin);
    }

    public static SendRequest toCLTVPaymentChannel(NetworkParameters networkParameters, int i, ECKey eCKey, ECKey eCKey2, Coin coin) {
        Preconditions.checkArgument(i >= 0 && i < 500000000, "Block number was too large");
        return toCLTVPaymentChannel(networkParameters, BigInteger.valueOf((long) i), eCKey, eCKey2, coin);
    }

    public static SendRequest toCLTVPaymentChannel(NetworkParameters networkParameters, BigInteger bigInteger, ECKey eCKey, ECKey eCKey2, Coin coin) {
        SendRequest sendRequest = new SendRequest();
        Script createCLTVPaymentChannelOutput = ScriptBuilder.createCLTVPaymentChannelOutput(bigInteger, eCKey, eCKey2);
        sendRequest.f829tx = new Transaction(networkParameters);
        sendRequest.f829tx.addOutput(coin, createCLTVPaymentChannelOutput);
        return sendRequest;
    }

    public SendRequest fromPaymentDetails(PaymentDetails paymentDetails) {
        if (paymentDetails.hasMemo()) {
            this.memo = paymentDetails.getMemo();
        }
        return this;
    }

    public String toString() {
        ToStringHelper omitNullValues = MoreObjects.toStringHelper((Object) this).omitNullValues();
        omitNullValues.add("emptyWallet", this.emptyWallet);
        omitNullValues.add("changeAddress", (Object) this.changeAddress);
        omitNullValues.add("feePerKb", (Object) this.feePerKb);
        omitNullValues.add("ensureMinRequiredFee", this.ensureMinRequiredFee);
        omitNullValues.add("signInputs", this.signInputs);
        omitNullValues.add("aesKey", (Object) this.aesKey != null ? "set" : null);
        omitNullValues.add("coinSelector", (Object) this.coinSelector);
        omitNullValues.add("shuffleOutputs", this.shuffleOutputs);
        return omitNullValues.toString();
    }

    public void setUseForkId(boolean z) {
        this.useForkId = z;
        Transaction transaction = this.f829tx;
        if (transaction != null) {
            transaction.setVersion(2);
        }
    }

    public boolean getUseForkId() {
        return this.useForkId;
    }
}
