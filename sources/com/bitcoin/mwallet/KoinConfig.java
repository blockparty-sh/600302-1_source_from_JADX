package com.bitcoin.mwallet;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.koin.core.module.Module;
import org.koin.dsl.ModuleKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/KoinConfig;", "", "()V", "analyticsModule", "Lorg/koin/core/module/Module;", "getAnalyticsModule", "()Lorg/koin/core/module/Module;", "coreInteractors", "getCoreInteractors", "databaseModule", "getDatabaseModule", "globalModule", "getGlobalModule", "grpcModule", "getGrpcModule", "repositoryModule", "getRepositoryModule", "workManagerModule", "getWorkManagerModule", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: KoinConfig.kt */
public final class KoinConfig {
    public static final KoinConfig INSTANCE = new KoinConfig();
    @NotNull
    private static final Module analyticsModule = ModuleKt.module$default(false, false, KoinConfig$analyticsModule$1.INSTANCE, 3, null);
    @NotNull
    private static final Module coreInteractors = ModuleKt.module$default(false, false, KoinConfig$coreInteractors$1.INSTANCE, 3, null);
    @NotNull
    private static final Module databaseModule = ModuleKt.module$default(false, false, KoinConfig$databaseModule$1.INSTANCE, 3, null);
    @NotNull
    private static final Module globalModule = ModuleKt.module$default(false, false, KoinConfig$globalModule$1.INSTANCE, 3, null);
    @NotNull
    private static final Module grpcModule = ModuleKt.module$default(false, false, KoinConfig$grpcModule$1.INSTANCE, 3, null);
    @NotNull
    private static final Module repositoryModule = ModuleKt.module$default(false, false, KoinConfig$repositoryModule$1.INSTANCE, 3, null);
    @NotNull
    private static final Module workManagerModule = ModuleKt.module$default(false, false, KoinConfig$workManagerModule$1.INSTANCE, 3, null);

    private KoinConfig() {
    }

    @NotNull
    public final Module getDatabaseModule() {
        return databaseModule;
    }

    @NotNull
    public final Module getRepositoryModule() {
        return repositoryModule;
    }

    @NotNull
    public final Module getGrpcModule() {
        return grpcModule;
    }

    @NotNull
    public final Module getAnalyticsModule() {
        return analyticsModule;
    }

    @NotNull
    public final Module getGlobalModule() {
        return globalModule;
    }

    @NotNull
    public final Module getCoreInteractors() {
        return coreInteractors;
    }

    @NotNull
    public final Module getWorkManagerModule() {
        return workManagerModule;
    }
}
