package com.bitcoin.mwallet.core.workers;

import android.content.Context;
import androidx.work.BackoffPolicy;
import androidx.work.CoroutineWorker;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.OneTimeWorkRequest.Builder;
import androidx.work.WorkRequest;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0002\f\rB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/workers/SaveWalletWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Factory", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SaveWalletWorker.kt */
public final class SaveWalletWorker extends CoroutineWorker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DATA_KEY_LOCAL_WALLET_ID = "localWalletId";
    @NotNull
    public static final String DATA_KEY_RECOVERED_WALLET = "recoveredWallet";
    private final WalletRepository walletRepository;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\r\u001a\u0004\u0018\u00010\u000b*\u00020\u000eH\u0002J\f\u0010\u000f\u001a\u00020\u000e*\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/workers/SaveWalletWorker$Companion;", "", "()V", "DATA_KEY_LOCAL_WALLET_ID", "", "DATA_KEY_RECOVERED_WALLET", "request", "Landroidx/work/OneTimeWorkRequest;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "saveType", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository$WalletSaveType;", "uniqueWorkName", "deserializeSaveType", "", "serialize", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SaveWalletWorker.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WalletSaveType.values().length];

            static {
                $EnumSwitchMapping$0[WalletSaveType.NEW.ordinal()] = 1;
                $EnumSwitchMapping$0[WalletSaveType.FROM_MNEMONIC.ordinal()] = 2;
                $EnumSwitchMapping$0[WalletSaveType.MIGRATED.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String uniqueWorkName(@NotNull WalletId walletId) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            StringBuilder sb = new StringBuilder();
            sb.append(SaveWalletWorker.DATA_KEY_LOCAL_WALLET_ID);
            sb.append(walletId.getId());
            return sb.toString();
        }

        private final int serialize(@NotNull WalletSaveType walletSaveType) {
            int i = WhenMappings.$EnumSwitchMapping$0[walletSaveType.ordinal()];
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 3;
            }
            throw new NoWhenBranchMatchedException();
        }

        /* access modifiers changed from: private */
        public final WalletSaveType deserializeSaveType(int i) {
            if (i == 1) {
                return WalletSaveType.NEW;
            }
            if (i == 2) {
                return WalletSaveType.FROM_MNEMONIC;
            }
            if (i != 3) {
                return null;
            }
            return WalletSaveType.MIGRATED;
        }

        @NotNull
        public final OneTimeWorkRequest request(@NotNull WalletId walletId, @NotNull WalletSaveType walletSaveType) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            Intrinsics.checkParameterIsNotNull(walletSaveType, "saveType");
            Builder builder = new Builder(SaveWalletWorker.class);
            Pair[] pairArr = {TuplesKt.m309to(SaveWalletWorker.DATA_KEY_LOCAL_WALLET_ID, walletId.getId()), TuplesKt.m309to(SaveWalletWorker.DATA_KEY_RECOVERED_WALLET, Integer.valueOf(serialize(walletSaveType)))};
            Data.Builder builder2 = new Data.Builder();
            for (Pair pair : pairArr) {
                builder2.put((String) pair.getFirst(), pair.getSecond());
            }
            Data build = builder2.build();
            Intrinsics.checkExpressionValueIsNotNull(build, "dataBuilder.build()");
            WorkRequest build2 = ((Builder) ((Builder) builder.setInputData(build)).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30, TimeUnit.SECONDS)).build();
            Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequestBuilde…\n                .build()");
            return (OneTimeWorkRequest) build2;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/workers/SaveWalletWorker$Factory;", "Landroidx/work/WorkerFactory;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SaveWalletWorker.kt */
    public static final class Factory extends WorkerFactory {
        private final WalletRepository walletRepository;

        public Factory(@NotNull WalletRepository walletRepository2) {
            Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
            this.walletRepository = walletRepository2;
        }

        @Nullable
        public ListenableWorker createWorker(@NotNull Context context, @NotNull String str, @NotNull WorkerParameters workerParameters) {
            Intrinsics.checkParameterIsNotNull(context, "appContext");
            Intrinsics.checkParameterIsNotNull(str, "workerClassName");
            Intrinsics.checkParameterIsNotNull(workerParameters, "workerParameters");
            return new SaveWalletWorker(context, workerParameters, this.walletRepository);
        }
    }

    public SaveWalletWorker(@NotNull Context context, @NotNull WorkerParameters workerParameters, @NotNull WalletRepository walletRepository2) {
        Intrinsics.checkParameterIsNotNull(context, "appContext");
        Intrinsics.checkParameterIsNotNull(workerParameters, "workerParams");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        super(context, workerParameters);
        this.walletRepository = walletRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.bitcoin.mwallet.core.workers.SaveWalletWorker$doWork$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.bitcoin.mwallet.core.workers.SaveWalletWorker$doWork$1 r0 = (com.bitcoin.mwallet.core.workers.SaveWalletWorker$doWork$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.workers.SaveWalletWorker$doWork$1 r0 = new com.bitcoin.mwallet.core.workers.SaveWalletWorker$doWork$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "Result.success()"
            r4 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 != r4) goto L_0x003c
            java.lang.Object r1 = r0.L$3
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            java.lang.Object r1 = r0.L$2
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r1 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r1
            java.lang.Object r1 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.workers.SaveWalletWorker r0 = (com.bitcoin.mwallet.core.workers.SaveWalletWorker) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x009e
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.work.Data r9 = r8.getInputData()
            java.lang.String r2 = "localWalletId"
            java.lang.String r9 = r9.getString(r2)
            if (r9 == 0) goto L_0x005e
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = new com.bitcoin.mwallet.core.models.wallet.WalletId
            java.lang.String r5 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r5)
            r2.<init>(r9)
            goto L_0x005f
        L_0x005e:
            r2 = 0
        L_0x005f:
            com.bitcoin.mwallet.core.workers.SaveWalletWorker$Companion r9 = Companion
            androidx.work.Data r5 = r8.getInputData()
            r6 = 0
            java.lang.String r7 = "recoveredWallet"
            int r5 = r5.getInt(r7, r6)
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r9 = r9.deserializeSaveType(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "doWork walletId="
            r5.append(r7)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            timber.log.Timber.m426d(r5, r6)
            if (r2 == 0) goto L_0x00c0
            if (r9 != 0) goto L_0x008b
            goto L_0x00c0
        L_0x008b:
            com.bitcoin.mwallet.core.repositories.WalletRepository r5 = r8.walletRepository
            r0.L$0 = r8
            r0.L$1 = r2
            r0.L$2 = r9
            r0.L$3 = r2
            r0.label = r4
            java.lang.Object r9 = r5.saveNewWalletRemote(r2, r9, r0)
            if (r9 != r1) goto L_0x009e
            return r1
        L_0x009e:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00b6
            androidx.work.ListenableWorker$Result r9 = androidx.work.ListenableWorker.Result.success()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r3)
            goto L_0x00bf
        L_0x00b6:
            androidx.work.ListenableWorker$Result r9 = androidx.work.ListenableWorker.Result.retry()
            java.lang.String r0 = "Result.retry()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r0)
        L_0x00bf:
            return r9
        L_0x00c0:
            androidx.work.ListenableWorker$Result r9 = androidx.work.ListenableWorker.Result.success()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.workers.SaveWalletWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
