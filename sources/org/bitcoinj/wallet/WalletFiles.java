package org.bitcoinj.wallet;

import androidx.core.p003os.EnvironmentCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.utils.ContextPropagatingThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletFiles {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(WalletFiles.class);
    private final long delay;
    private final TimeUnit delayTimeUnit;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new ContextPropagatingThreadFactory("Wallet autosave thread", 1));
    private final File file;
    /* access modifiers changed from: private */
    public final AtomicBoolean savePending;
    private final Callable<Void> saver;
    private volatile Listener vListener;
    private final C3530Wallet wallet;

    public interface Listener {
        void onAfterAutoSave(File file);

        void onBeforeAutoSave(File file);
    }

    public WalletFiles(final C3530Wallet wallet2, File file2, long j, TimeUnit timeUnit) {
        this.executor.setKeepAliveTime(5, TimeUnit.SECONDS);
        this.executor.allowCoreThreadTimeOut(true);
        this.executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
        this.file = (File) Preconditions.checkNotNull(file2);
        this.savePending = new AtomicBoolean();
        this.delay = j;
        this.delayTimeUnit = (TimeUnit) Preconditions.checkNotNull(timeUnit);
        this.saver = new Callable<Void>() {
            public Void call() throws Exception {
                if (!WalletFiles.this.savePending.getAndSet(false)) {
                    return null;
                }
                Date lastBlockSeenTime = wallet2.getLastBlockSeenTime();
                Logger access$100 = WalletFiles.log;
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(wallet2.getLastBlockSeenHeight());
                objArr[1] = lastBlockSeenTime != null ? C3447Utils.dateTimeFormat(lastBlockSeenTime) : EnvironmentCompat.MEDIA_UNKNOWN;
                objArr[2] = wallet2.getLastBlockSeenHash();
                access$100.info("Background saving wallet; last seen block is height {}, date {}, hash {}", objArr);
                WalletFiles.this.saveNowInternal();
                return null;
            }
        };
    }

    public void setListener(@Nonnull Listener listener) {
        this.vListener = (Listener) Preconditions.checkNotNull(listener);
    }

    public void saveNow() throws IOException {
        Date lastBlockSeenTime = this.wallet.getLastBlockSeenTime();
        Logger logger = log;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.wallet.getLastBlockSeenHeight());
        objArr[1] = lastBlockSeenTime != null ? C3447Utils.dateTimeFormat(lastBlockSeenTime) : EnvironmentCompat.MEDIA_UNKNOWN;
        objArr[2] = this.wallet.getLastBlockSeenHash();
        logger.info("Saving wallet; last seen block is height {}, date {}, hash {}", objArr);
        saveNowInternal();
    }

    /* access modifiers changed from: private */
    public void saveNowInternal() throws IOException {
        Stopwatch createStarted = Stopwatch.createStarted();
        File createTempFile = File.createTempFile("wallet", null, this.file.getAbsoluteFile().getParentFile());
        Listener listener = this.vListener;
        if (listener != null) {
            listener.onBeforeAutoSave(createTempFile);
        }
        this.wallet.saveToFile(createTempFile, this.file);
        if (listener != null) {
            listener.onAfterAutoSave(this.file);
        }
        createStarted.stop();
        log.info("Save completed in {}", (Object) createStarted);
    }

    public void saveLater() {
        if (!this.savePending.getAndSet(true)) {
            this.executor.schedule(this.saver, this.delay, this.delayTimeUnit);
        }
    }

    public void shutdownAndWait() {
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
