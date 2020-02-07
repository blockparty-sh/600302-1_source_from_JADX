package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.script.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyTimeCoinSelector implements CoinSelector {
    public static final int MAX_SIMULTANEOUS_INPUTS = 600;
    private static final Logger log = LoggerFactory.getLogger(KeyTimeCoinSelector.class);
    private final boolean ignorePending;
    private final long unixTimeSeconds;
    private final C3530Wallet wallet;

    public KeyTimeCoinSelector(C3530Wallet wallet2, long j, boolean z) {
        this.unixTimeSeconds = j;
        this.wallet = wallet2;
        this.ignorePending = z;
    }

    public CoinSelection select(Coin coin, List<TransactionOutput> list) {
        ECKey eCKey;
        try {
            LinkedList newLinkedList = Lists.newLinkedList();
            Coin coin2 = Coin.ZERO;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TransactionOutput transactionOutput = (TransactionOutput) it.next();
                if (!this.ignorePending || isConfirmed(transactionOutput)) {
                    Script scriptPubKey = transactionOutput.getScriptPubKey();
                    if (scriptPubKey.isSentToRawPubKey()) {
                        eCKey = this.wallet.findKeyFromPubKey(scriptPubKey.getPubKey());
                    } else if (scriptPubKey.isSentToAddress()) {
                        eCKey = this.wallet.findKeyFromPubHash(scriptPubKey.getPubKeyHash());
                    } else {
                        log.info("Skipping tx output {} because it's not of simple form.", (Object) transactionOutput);
                    }
                    Preconditions.checkNotNull(eCKey, "Coin selector given output as candidate for which we lack the key");
                    if (eCKey.getCreationTimeSeconds() < this.unixTimeSeconds) {
                        coin2 = coin2.add(transactionOutput.getValue());
                        newLinkedList.push(transactionOutput);
                        if (newLinkedList.size() >= 600) {
                            log.warn("Reached {} inputs, going further would yield a tx that is too large, stopping here.", (Object) Integer.valueOf(newLinkedList.size()));
                            break;
                        }
                    }
                }
            }
            return new CoinSelection(coin2, newLinkedList);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isConfirmed(TransactionOutput transactionOutput) {
        return transactionOutput.getParentTransaction().getConfidence().getConfidenceType().equals(ConfidenceType.BUILDING);
    }
}
