package org.bitcoinj.wallet;

import java.util.List;
import org.bitcoinj.core.Transaction;

public interface RiskAnalysis {

    public interface Analyzer {
        RiskAnalysis create(C3530Wallet wallet, Transaction transaction, List<Transaction> list);
    }

    public enum Result {
        OK,
        NON_FINAL,
        NON_STANDARD
    }

    Result analyze();
}
