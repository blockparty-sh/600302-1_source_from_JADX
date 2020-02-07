package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Context {
    private static boolean isStrictMode;
    private static volatile Context lastConstructed;
    private static final Logger log = LoggerFactory.getLogger(Context.class);
    private static final ThreadLocal<Context> slot = new ThreadLocal<>();
    private TxConfidenceTable confidenceTable;
    private boolean ensureMinRequiredFee;
    private int eventHorizon;
    private Coin feePerKb;
    private NetworkParameters params;

    public Context(NetworkParameters networkParameters) {
        this.eventHorizon = 100;
        this.ensureMinRequiredFee = true;
        this.feePerKb = Transaction.DEFAULT_TX_FEE;
        log.info("Creating bitcoinj {} context.", (Object) VersionMessage.BITCOINJ_VERSION);
        this.confidenceTable = new TxConfidenceTable();
        this.params = networkParameters;
        lastConstructed = this;
        slot.set(this);
    }

    public Context(NetworkParameters networkParameters, int i, Coin coin, boolean z) {
        this(networkParameters);
        this.eventHorizon = i;
        this.feePerKb = coin;
        this.ensureMinRequiredFee = z;
    }

    public static Context get() {
        Context context = (Context) slot.get();
        if (context != null) {
            return context;
        }
        String str = "You should use Context.propagate() or a ContextPropagatingThreadFactory.";
        if (isStrictMode) {
            log.error("Thread is missing a bitcoinj context.");
            log.error(str);
            throw new IllegalStateException("missing context");
        } else if (lastConstructed != null) {
            slot.set(lastConstructed);
            log.error("Performing thread fixup: you are accessing bitcoinj via a thread that has not had any context set on it.");
            log.error("This error has been corrected for, but doing this makes your app less robust.");
            log.error(str);
            log.error("Please refer to the user guide for more information about this.");
            log.error("Thread name is {}.", (Object) Thread.currentThread().getName());
            return lastConstructed;
        } else {
            throw new IllegalStateException("You must construct a Context object before using bitcoinj!");
        }
    }

    public static void enableStrictMode() {
        isStrictMode = true;
    }

    public static Context getOrCreate(NetworkParameters networkParameters) {
        try {
            Context context = get();
            if (context.getParams() == networkParameters) {
                return context;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Context does not match implicit network params: ");
            sb.append(context.getParams());
            sb.append(" vs ");
            sb.append(networkParameters);
            throw new IllegalStateException(sb.toString());
        } catch (IllegalStateException unused) {
            log.warn("Implicitly creating context. This is a migration step and this message will eventually go away.");
            return new Context(networkParameters);
        }
    }

    public static void propagate(Context context) {
        slot.set(Preconditions.checkNotNull(context));
    }

    public TxConfidenceTable getConfidenceTable() {
        return this.confidenceTable;
    }

    public NetworkParameters getParams() {
        return this.params;
    }

    public int getEventHorizon() {
        return this.eventHorizon;
    }

    public Coin getFeePerKb() {
        return this.feePerKb;
    }

    public boolean isEnsureMinRequiredFee() {
        return this.ensureMinRequiredFee;
    }
}
