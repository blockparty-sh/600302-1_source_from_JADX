package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.ScriptChunk;
import org.bitcoinj.wallet.RiskAnalysis.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRiskAnalysis implements RiskAnalysis {
    public static Analyzer FACTORY = new Analyzer();
    public static final Coin MIN_ANALYSIS_NONDUST_OUTPUT = Transaction.MIN_NONDUST_OUTPUT;
    private static final Logger log = LoggerFactory.getLogger(DefaultRiskAnalysis.class);
    protected boolean analyzed;
    protected final List<Transaction> dependencies;
    protected Transaction nonFinal;
    private Transaction nonStandard;

    /* renamed from: tx */
    protected final Transaction f821tx;
    @Nullable
    protected final C3530Wallet wallet;

    public static class Analyzer implements org.bitcoinj.wallet.RiskAnalysis.Analyzer {
        public DefaultRiskAnalysis create(C3530Wallet wallet, Transaction transaction, List<Transaction> list) {
            return new DefaultRiskAnalysis(wallet, transaction, list);
        }
    }

    public enum RuleViolation {
        NONE,
        VERSION,
        DUST,
        SHORTEST_POSSIBLE_PUSHDATA,
        NONEMPTY_STACK,
        SIGNATURE_CANONICAL_ENCODING,
        SIGNATURE_MISSING_FORKID
    }

    private DefaultRiskAnalysis(C3530Wallet wallet2, Transaction transaction, List<Transaction> list) {
        this.f821tx = transaction;
        this.dependencies = list;
        this.wallet = wallet2;
    }

    public Result analyze() {
        Preconditions.checkState(!this.analyzed);
        this.analyzed = true;
        Result analyzeIsFinal = analyzeIsFinal();
        if (analyzeIsFinal == null || analyzeIsFinal == Result.OK) {
            return analyzeIsStandard();
        }
        return analyzeIsFinal;
    }

    @Nullable
    private Result analyzeIsFinal() {
        if (this.f821tx.getConfidence().getSource() == Source.SELF) {
            return Result.OK;
        }
        if (this.f821tx.isOptInFullRBF()) {
            this.nonFinal = this.f821tx;
            return Result.NON_FINAL;
        }
        C3530Wallet wallet2 = this.wallet;
        if (wallet2 == null) {
            return null;
        }
        int lastBlockSeenHeight = wallet2.getLastBlockSeenHeight();
        long lastBlockSeenTimeSecs = this.wallet.getLastBlockSeenTimeSecs();
        int i = lastBlockSeenHeight + 1;
        if (!this.f821tx.isFinal(i, lastBlockSeenTimeSecs)) {
            this.nonFinal = this.f821tx;
            return Result.NON_FINAL;
        }
        for (Transaction transaction : this.dependencies) {
            if (!transaction.isFinal(i, lastBlockSeenTimeSecs)) {
                this.nonFinal = transaction;
                return Result.NON_FINAL;
            }
        }
        return Result.OK;
    }

    public static RuleViolation isStandard(Transaction transaction) {
        if (transaction.getVersion() > 2 || transaction.getVersion() < 1) {
            log.warn("TX considered non-standard due to unknown version number {}", (Object) Long.valueOf(transaction.getVersion()));
            return RuleViolation.VERSION;
        }
        List outputs = transaction.getOutputs();
        for (int i = 0; i < outputs.size(); i++) {
            RuleViolation isOutputStandard = isOutputStandard((TransactionOutput) outputs.get(i));
            if (isOutputStandard != RuleViolation.NONE) {
                log.warn("TX considered non-standard due to output {} violating rule {}", (Object) Integer.valueOf(i), (Object) isOutputStandard);
                return isOutputStandard;
            }
        }
        List inputs = transaction.getInputs();
        for (int i2 = 0; i2 < inputs.size(); i2++) {
            RuleViolation isInputStandard = isInputStandard((TransactionInput) inputs.get(i2));
            if (isInputStandard != RuleViolation.NONE) {
                log.warn("TX considered non-standard due to input {} violating rule {}", (Object) Integer.valueOf(i2), (Object) isInputStandard);
                return isInputStandard;
            }
        }
        return RuleViolation.NONE;
    }

    public static RuleViolation isOutputStandard(TransactionOutput transactionOutput) {
        if (transactionOutput.getValue().compareTo(MIN_ANALYSIS_NONDUST_OUTPUT) < 0) {
            return RuleViolation.DUST;
        }
        for (ScriptChunk scriptChunk : transactionOutput.getScriptPubKey().getChunks()) {
            if (scriptChunk.isPushData() && !scriptChunk.isShortestPossiblePushData()) {
                return RuleViolation.SHORTEST_POSSIBLE_PUSHDATA;
            }
        }
        return RuleViolation.NONE;
    }

    public static RuleViolation isInputStandard(TransactionInput transactionInput) {
        ECDSASignature eCDSASignature;
        for (ScriptChunk scriptChunk : transactionInput.getScriptSig().getChunks()) {
            if (scriptChunk.data != null && !scriptChunk.isShortestPossiblePushData()) {
                return RuleViolation.SHORTEST_POSSIBLE_PUSHDATA;
            }
            if (scriptChunk.isPushData()) {
                try {
                    eCDSASignature = ECDSASignature.decodeFromDER(scriptChunk.data);
                } catch (RuntimeException unused) {
                    eCDSASignature = null;
                }
                if (eCDSASignature == null) {
                    continue;
                } else if (!TransactionSignature.isEncodingCanonical(scriptChunk.data)) {
                    return RuleViolation.SIGNATURE_CANONICAL_ENCODING;
                } else {
                    if (!eCDSASignature.isCanonical()) {
                        return RuleViolation.SIGNATURE_CANONICAL_ENCODING;
                    }
                }
            }
        }
        return RuleViolation.NONE;
    }

    public static RuleViolation isInputSignedWithForkId(TransactionInput transactionInput, boolean z) {
        ECDSASignature eCDSASignature;
        for (ScriptChunk scriptChunk : transactionInput.getScriptSig().getChunks()) {
            if (scriptChunk.data != null && !scriptChunk.isShortestPossiblePushData()) {
                return RuleViolation.SHORTEST_POSSIBLE_PUSHDATA;
            }
            if (scriptChunk.isPushData()) {
                try {
                    eCDSASignature = ECDSASignature.decodeFromDER(scriptChunk.data);
                } catch (RuntimeException unused) {
                    eCDSASignature = null;
                }
                if (eCDSASignature != null && z && !TransactionSignature.hasForkId(scriptChunk.data)) {
                    return RuleViolation.SIGNATURE_MISSING_FORKID;
                }
            }
        }
        return RuleViolation.NONE;
    }

    private Result analyzeIsStandard() {
        C3530Wallet wallet2 = this.wallet;
        if (wallet2 != null && !wallet2.getNetworkParameters().getId().equals(NetworkParameters.ID_MAINNET)) {
            return Result.OK;
        }
        if (isStandard(this.f821tx) != RuleViolation.NONE) {
            this.nonStandard = this.f821tx;
            return Result.NON_STANDARD;
        }
        long lastBlockSeenTimeSecs = this.wallet.getLastBlockSeenTimeSecs();
        List inputs = this.f821tx.getInputs();
        for (int i = 0; i < inputs.size(); i++) {
            RuleViolation isInputSignedWithForkId = isInputSignedWithForkId((TransactionInput) inputs.get(i), lastBlockSeenTimeSecs > 1501590000);
            if (isInputSignedWithForkId != RuleViolation.NONE) {
                log.warn("TX considered non-standard due to input {} violating rule {}", (Object) Integer.valueOf(i), (Object) isInputSignedWithForkId);
                return Result.NON_STANDARD;
            }
        }
        for (Transaction transaction : this.dependencies) {
            if (isStandard(transaction) != RuleViolation.NONE) {
                this.nonStandard = transaction;
                return Result.NON_STANDARD;
            }
        }
        return Result.OK;
    }

    @Nullable
    public Transaction getNonStandard() {
        return this.nonStandard;
    }

    @Nullable
    public Transaction getNonFinal() {
        return this.nonFinal;
    }

    public String toString() {
        if (!this.analyzed) {
            StringBuilder sb = new StringBuilder();
            sb.append("Pending risk analysis for ");
            sb.append(this.f821tx.getHashAsString());
            return sb.toString();
        } else if (this.nonFinal != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Risky due to non-finality of ");
            sb2.append(this.nonFinal.getHashAsString());
            return sb2.toString();
        } else if (this.nonStandard == null) {
            return "Non-risky";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Risky due to non-standard tx ");
            sb3.append(this.nonStandard.getHashAsString());
            return sb3.toString();
        }
    }
}
