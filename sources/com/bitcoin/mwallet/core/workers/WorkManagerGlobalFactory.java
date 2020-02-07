package com.bitcoin.mwallet.core.workers;

import android.app.Application;
import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Configuration.Builder;
import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import com.bitcoin.mwallet.core.workers.SaveWalletWorker.Factory;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/workers/WorkManagerGlobalFactory;", "Landroidx/work/WorkerFactory;", "application", "Landroid/app/Application;", "saveWalletWorkerFactory", "Lcom/bitcoin/mwallet/core/workers/SaveWalletWorker$Factory;", "(Landroid/app/Application;Lcom/bitcoin/mwallet/core/workers/SaveWalletWorker$Factory;)V", "factories", "", "", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "workerParameters", "Landroidx/work/WorkerParameters;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WorkManagerGlobalFactory.kt */
public final class WorkManagerGlobalFactory extends WorkerFactory {
    private final Map<String, WorkerFactory> factories;

    public WorkManagerGlobalFactory(@NotNull Application application, @NotNull Factory factory) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(factory, "saveWalletWorkerFactory");
        Configuration build = new Builder().setWorkerFactory(this).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Configuration.Builder()\n…his)\n            .build()");
        WorkManager.initialize(application.getApplicationContext(), build);
        Timber.m426d("Initialized WorkManager", new Object[0]);
        this.factories = MapsKt.mapOf(new Pair(SaveWalletWorker.class.getName(), factory));
    }

    @Nullable
    public ListenableWorker createWorker(@NotNull Context context, @NotNull String str, @NotNull WorkerParameters workerParameters) {
        Intrinsics.checkParameterIsNotNull(context, "appContext");
        Intrinsics.checkParameterIsNotNull(str, "workerClassName");
        Intrinsics.checkParameterIsNotNull(workerParameters, "workerParameters");
        WorkerFactory workerFactory = (WorkerFactory) this.factories.get(str);
        if (workerFactory == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("No WorkerFactory for className=");
            sb.append(str);
            Timber.m426d(sb.toString(), new Object[0]);
        }
        if (workerFactory != null) {
            return workerFactory.createWorker(context, str, workerParameters);
        }
        return null;
    }
}
