package org.bitcoinj.wallet;

import com.google.common.annotations.VisibleForTesting;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.TransactionOutput;

public class DefaultCoinSelector implements CoinSelector {
    public CoinSelection select(Coin coin, List<TransactionOutput> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        if (!coin.equals(NetworkParameters.MAX_MONEY)) {
            sortOutputs(arrayList2);
        }
        long j = 0;
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            TransactionOutput transactionOutput = (TransactionOutput) it.next();
            if (j >= coin.value) {
                break;
            } else if (shouldSelect(transactionOutput.getParentTransaction())) {
                arrayList.add(transactionOutput);
                j += transactionOutput.getValue().value;
            }
        }
        return new CoinSelection(Coin.valueOf(j), arrayList);
    }

    @VisibleForTesting
    static void sortOutputs(ArrayList<TransactionOutput> arrayList) {
        Collections.sort(arrayList, new Comparator<TransactionOutput>() {
            public int compare(TransactionOutput transactionOutput, TransactionOutput transactionOutput2) {
                int parentTransactionDepthInBlocks = transactionOutput.getParentTransactionDepthInBlocks();
                int parentTransactionDepthInBlocks2 = transactionOutput2.getParentTransactionDepthInBlocks();
                Coin value = transactionOutput.getValue();
                Coin value2 = transactionOutput2.getValue();
                int compareTo = BigInteger.valueOf(value2.value).multiply(BigInteger.valueOf((long) parentTransactionDepthInBlocks2)).compareTo(BigInteger.valueOf(value.value).multiply(BigInteger.valueOf((long) parentTransactionDepthInBlocks)));
                if (compareTo != 0) {
                    return compareTo;
                }
                int compareTo2 = value2.compareTo(value);
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                return transactionOutput.getParentTransactionHash().toBigInteger().compareTo(transactionOutput2.getParentTransactionHash().toBigInteger());
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean shouldSelect(Transaction transaction) {
        if (transaction != null) {
            return isSelectable(transaction);
        }
        return true;
    }

    public static boolean isSelectable(Transaction transaction) {
        TransactionConfidence confidence = transaction.getConfidence();
        ConfidenceType confidenceType = confidence.getConfidenceType();
        if (confidenceType.equals(ConfidenceType.BUILDING)) {
            return true;
        }
        if (!confidenceType.equals(ConfidenceType.PENDING) || !confidence.getSource().equals(Source.SELF) || (confidence.numBroadcastPeers() <= 1 && !transaction.getParams().getId().equals(NetworkParameters.ID_REGTEST))) {
            return false;
        }
        return true;
    }
}
