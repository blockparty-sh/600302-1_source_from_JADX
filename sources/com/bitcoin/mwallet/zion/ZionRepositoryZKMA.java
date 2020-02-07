package com.bitcoin.mwallet.zion;

import android.app.Application;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.mwallet.core.dao.ZionNameDao;
import com.bitcoin.mwallet.core.entity.ZionNameEntity;
import com.bitcoin.mwallet.core.models.Coin;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import com.squareup.moshi.JsonAdapter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 G2\u00020\u0001:\u0001GB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J#\u0010\u0019\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u000bH\u0002J\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H@ø\u0001\u0000¢\u0006\u0002\u0010\"J\u0019\u0010#\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001b\u0010$\u001a\u0004\u0018\u00010\u001b2\u0006\u0010%\u001a\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010'J'\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010+\u001a\u00020,H@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0011\u0010.\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010/J\u0019\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u000202H@ø\u0001\u0000¢\u0006\u0002\u00103J\u001f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0)2\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00160)2\u0006\u00106\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u00107J#\u00108\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ#\u00109\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020&0;0:H@ø\u0001\u0000¢\u0006\u0002\u0010/J\u001b\u0010<\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J/\u0010=\u001a\b\u0012\u0004\u0012\u00020>0)2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010+\u001a\u00020,2\u0006\u0010?\u001a\u00020@H@ø\u0001\u0000¢\u0006\u0002\u0010AJ/\u0010B\u001a\b\u0012\u0004\u0012\u00020>0)2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010+\u001a\u00020,2\u0006\u0010C\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010DJ\u0013\u0010E\u001a\u0004\u0018\u00010\bH@ø\u0001\u0000¢\u0006\u0002\u0010/J!\u0010F\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006H"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionRepositoryZKMA;", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "application", "Landroid/app/Application;", "zionNameDao", "Lcom/bitcoin/mwallet/core/dao/ZionNameDao;", "(Landroid/app/Application;Lcom/bitcoin/mwallet/core/dao/ZionNameDao;)V", "manager", "Lcom/htc/htcwalletsdk/Export/HtcWalletSdkManager;", "triedToInit", "Ljava/util/concurrent/atomic/AtomicReference;", "", "zionTxJsonAdapter", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/bitcoin/mwallet/zion/ZionTransaction;", "getZionTxJsonAdapter", "()Lcom/squareup/moshi/JsonAdapter;", "zionTxJsonAdapter$delegate", "Lkotlin/Lazy;", "clearWalletSeed", "Lcom/bitcoin/mwallet/zion/ZionError;", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmPin", "createWalletSeed", "walletName", "Lcom/bitcoin/mwallet/zion/ZionWalletName;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/zion/ZionWalletName;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deviceSupported", "freeWalletNameByIndex", "", "walletNameIndex", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "freeWalletNameByZionId", "getNextAvailableWalletName", "prefix", "Lcom/bitcoin/mwallet/zion/ZionNamePrefix;", "(Lcom/bitcoin/mwallet/zion/ZionNamePrefix;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletXPub", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "Lcom/bitcoin/mwallet/zion/ZionXPub;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasZion", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertZionName", "zionName", "Lcom/bitcoin/mwallet/core/entity/ZionNameEntity;", "(Lcom/bitcoin/mwallet/core/entity/ZionNameEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSeedExists", "registerWallet", "zionWalletName", "(Lcom/bitcoin/mwallet/zion/ZionWalletName;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreWalletSeed", "scanAndRestoreZionWallets", "", "Lkotlin/Pair;", "showWalletSeed", "signMessage", "", "message", "", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signTransaction", "zionTransasction", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/mwallet/zion/ZionTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryInitManager", "unregisterWallet", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
public final class ZionRepositoryZKMA implements ZionRepository {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ZionRepositoryZKMA.class), "zionTxJsonAdapter", "getZionTxJsonAdapter()Lcom/squareup/moshi/JsonAdapter;"))};
    public static final Companion Companion = new Companion(null);
    private static final Map<Coin, DerivationPath> coinPaths;
    /* access modifiers changed from: private */
    public final Application application;
    private HtcWalletSdkManager manager;
    private final AtomicReference<Boolean> triedToInit = new AtomicReference<>(Boolean.valueOf(false));
    /* access modifiers changed from: private */
    public final ZionNameDao zionNameDao;
    private final Lazy zionTxJsonAdapter$delegate;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.zion.ZionRepositoryZKMA$1", mo38000f = "ZionRepositoryZKMA.kt", mo38001i = {}, mo38002l = {98}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.zion.ZionRepositoryZKMA$1 */
    /* compiled from: ZionRepositoryZKMA.kt */
    static final class C12811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f455p$;
        final /* synthetic */ ZionRepositoryZKMA this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C12811 r0 = new C12811(this.this$0, continuation);
            r0.f455p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C12811) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f455p$;
                ZionRepositoryZKMA zionRepositoryZKMA = this.this$0;
                this.label = 1;
                if (zionRepositoryZKMA.hasZion(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J?\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ/\u0010\u000f\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0005H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionRepositoryZKMA$Companion;", "", "()V", "coinPaths", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/bitcoink/DerivationPath;", "trySuspendZionCall", "T", "errorMessage", "", "func", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryZionCall", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toZionCoinType", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ZionRepositoryZKMA.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

            static {
                $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
                $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ <T> java.lang.Object tryZionCall(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends T> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r8) {
            /*
                r5 = this;
                boolean r0 = r8 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$tryZionCall$1
                if (r0 == 0) goto L_0x0014
                r0 = r8
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$tryZionCall$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$tryZionCall$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L_0x0019
            L_0x0014:
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$tryZionCall$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$tryZionCall$1
                r0.<init>(r5, r8)
            L_0x0019:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L_0x0040
                if (r2 != r4) goto L_0x0038
                java.lang.Object r6 = r0.L$2
                kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
                java.lang.Object r6 = r0.L$1
                java.lang.String r6 = (java.lang.String) r6
                java.lang.Object r7 = r0.L$0
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r7 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA.Companion) r7
                kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ RuntimeException -> 0x005f }
            L_0x0036:
                r3 = r8
                goto L_0x0068
            L_0x0038:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0040:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ RuntimeException -> 0x005f }
                kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8     // Catch:{ RuntimeException -> 0x005f }
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$tryZionCall$2 r2 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$tryZionCall$2     // Catch:{ RuntimeException -> 0x005f }
                r2.<init>(r7, r3)     // Catch:{ RuntimeException -> 0x005f }
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ RuntimeException -> 0x005f }
                r0.L$0 = r5     // Catch:{ RuntimeException -> 0x005f }
                r0.L$1 = r6     // Catch:{ RuntimeException -> 0x005f }
                r0.L$2 = r7     // Catch:{ RuntimeException -> 0x005f }
                r0.label = r4     // Catch:{ RuntimeException -> 0x005f }
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)     // Catch:{ RuntimeException -> 0x005f }
                if (r8 != r1) goto L_0x0036
                return r1
            L_0x005f:
                r7 = move-exception
                java.lang.Throwable r7 = (java.lang.Throwable) r7
                r8 = 0
                java.lang.Object[] r8 = new java.lang.Object[r8]
                timber.log.Timber.m431e(r7, r6, r8)
            L_0x0068:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.Companion.tryZionCall(java.lang.String, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ <T> java.lang.Object trySuspendZionCall(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r8) {
            /*
                r5 = this;
                boolean r0 = r8 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$1
                if (r0 == 0) goto L_0x0014
                r0 = r8
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L_0x0019
            L_0x0014:
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$1
                r0.<init>(r5, r8)
            L_0x0019:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L_0x0040
                if (r2 != r4) goto L_0x0038
                java.lang.Object r6 = r0.L$2
                kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
                java.lang.Object r6 = r0.L$1
                java.lang.String r6 = (java.lang.String) r6
                java.lang.Object r7 = r0.L$0
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r7 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA.Companion) r7
                kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ RuntimeException -> 0x005f }
            L_0x0036:
                r3 = r8
                goto L_0x0068
            L_0x0038:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0040:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ RuntimeException -> 0x005f }
                kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8     // Catch:{ RuntimeException -> 0x005f }
                com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$2 r2 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$2     // Catch:{ RuntimeException -> 0x005f }
                r2.<init>(r7, r3)     // Catch:{ RuntimeException -> 0x005f }
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ RuntimeException -> 0x005f }
                r0.L$0 = r5     // Catch:{ RuntimeException -> 0x005f }
                r0.L$1 = r6     // Catch:{ RuntimeException -> 0x005f }
                r0.L$2 = r7     // Catch:{ RuntimeException -> 0x005f }
                r0.label = r4     // Catch:{ RuntimeException -> 0x005f }
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)     // Catch:{ RuntimeException -> 0x005f }
                if (r8 != r1) goto L_0x0036
                return r1
            L_0x005f:
                r7 = move-exception
                java.lang.Throwable r7 = (java.lang.Throwable) r7
                r8 = 0
                java.lang.Object[] r8 = new java.lang.Object[r8]
                timber.log.Timber.m431e(r7, r6, r8)
            L_0x0068:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.Companion.trySuspendZionCall(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* access modifiers changed from: private */
        public final int toZionCoinType(@NotNull Coin coin) {
            int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
            if (i == 1) {
                return 145;
            }
            if (i == 2) {
                return 0;
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* access modifiers changed from: private */
    public final JsonAdapter<ZionTransaction> getZionTxJsonAdapter() {
        Lazy lazy = this.zionTxJsonAdapter$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (JsonAdapter) lazy.getValue();
    }

    public ZionRepositoryZKMA(@NotNull Application application2, @NotNull ZionNameDao zionNameDao2) {
        Intrinsics.checkParameterIsNotNull(application2, "application");
        Intrinsics.checkParameterIsNotNull(zionNameDao2, "zionNameDao");
        this.application = application2;
        this.zionNameDao = zionNameDao2;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C12811(this, null), 3, null);
        this.zionTxJsonAdapter$delegate = LazyKt.lazy(ZionRepositoryZKMA$zionTxJsonAdapter$2.INSTANCE);
    }

    @Nullable
    public Object freeWalletNameByIndex(int i, @NotNull Continuation<? super Unit> continuation) {
        return this.zionNameDao.deleteByIndex(i, continuation);
    }

    @Nullable
    public Object freeWalletNameByZionId(@NotNull ZionId zionId, @NotNull Continuation<? super Unit> continuation) {
        return this.zionNameDao.deleteByZionId(zionId, continuation);
    }

    @Nullable
    public Object insertZionName(@NotNull ZionNameEntity zionNameEntity, @NotNull Continuation<? super Unit> continuation) {
        return this.zionNameDao.insertZionId(new ZionNameEntity[]{zionNameEntity}, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getNextAvailableWalletName(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionNamePrefix r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionWalletName> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getNextAvailableWalletName$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getNextAvailableWalletName$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getNextAvailableWalletName$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getNextAvailableWalletName$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getNextAvailableWalletName$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.zion.ZionNamePrefix r5 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r5
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004c
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.core.dao.ZionNameDao r6 = r4.zionNameDao
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.getNextAvailableSuffix(r0)
            if (r6 != r1) goto L_0x004c
            return r1
        L_0x004c:
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 == 0) goto L_0x005c
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            com.bitcoin.mwallet.zion.ZionWalletName r0 = new com.bitcoin.mwallet.zion.ZionWalletName
            r0.<init>(r5, r6)
            goto L_0x005d
        L_0x005c:
            r0 = 0
        L_0x005d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.getNextAvailableWalletName(com.bitcoin.mwallet.zion.ZionNamePrefix, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x01d0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x02ca  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0306 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0322  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0329  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object scanAndRestoreZionWallets(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends kotlin.Pair<com.bitcoin.mwallet.zion.ZionId, ? extends com.bitcoin.mwallet.zion.ZionNamePrefix>>> r29) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            boolean r2 = r1 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$scanAndRestoreZionWallets$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$scanAndRestoreZionWallets$1 r2 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$scanAndRestoreZionWallets$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$scanAndRestoreZionWallets$1 r2 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$scanAndRestoreZionWallets$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 2
            r10 = 1
            if (r4 == 0) goto L_0x0138
            if (r4 == r10) goto L_0x012e
            if (r4 == r8) goto L_0x00ed
            if (r4 == r7) goto L_0x0095
            if (r4 == r6) goto L_0x0055
            if (r4 != r5) goto L_0x004d
            long r3 = r2.J$0
            java.lang.Object r5 = r2.L$3
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r2.L$2
            java.util.Set r6 = (java.util.Set) r6
            java.lang.Object r6 = r2.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r2 = r2.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r2 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0351
        L_0x004d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0055:
            boolean r4 = r2.Z$0
            java.lang.Object r4 = r2.L$9
            com.bitcoin.mwallet.zion.ZionId r4 = (com.bitcoin.mwallet.zion.ZionId) r4
            java.lang.Object r4 = r2.L$8
            com.bitcoin.mwallet.zion.ZionWalletName r4 = (com.bitcoin.mwallet.zion.ZionWalletName) r4
            int r4 = r2.I$3
            int r11 = r2.I$2
            java.lang.Object r12 = r2.L$7
            com.bitcoin.mwallet.zion.ZionNamePrefix r12 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r12
            java.lang.Object r13 = r2.L$6
            com.bitcoin.mwallet.zion.ZionNamePrefix r13 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r13
            int r14 = r2.I$1
            int r15 = r2.I$0
            java.lang.Object r5 = r2.L$5
            com.bitcoin.mwallet.zion.ZionNamePrefix[] r5 = (com.bitcoin.mwallet.zion.ZionNamePrefix[]) r5
            java.lang.Object r6 = r2.L$4
            com.bitcoin.mwallet.zion.ZionNamePrefix[] r6 = (com.bitcoin.mwallet.zion.ZionNamePrefix[]) r6
            long r7 = r2.J$0
            java.lang.Object r9 = r2.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r2.L$2
            java.util.Set r10 = (java.util.Set) r10
            r17 = r4
            java.lang.Object r4 = r2.L$1
            java.util.List r4 = (java.util.List) r4
            r18 = r4
            java.lang.Object r4 = r2.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r4 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r6
            r6 = r11
            r0 = 4
            goto L_0x02d5
        L_0x0095:
            java.lang.Object r4 = r2.L$9
            com.bitcoin.mwallet.zion.ZionId r4 = (com.bitcoin.mwallet.zion.ZionId) r4
            java.lang.Object r5 = r2.L$8
            com.bitcoin.mwallet.zion.ZionWalletName r5 = (com.bitcoin.mwallet.zion.ZionWalletName) r5
            int r6 = r2.I$3
            int r7 = r2.I$2
            java.lang.Object r8 = r2.L$7
            com.bitcoin.mwallet.zion.ZionNamePrefix r8 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r8
            java.lang.Object r9 = r2.L$6
            com.bitcoin.mwallet.zion.ZionNamePrefix r9 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r9
            int r10 = r2.I$1
            int r11 = r2.I$0
            java.lang.Object r12 = r2.L$5
            com.bitcoin.mwallet.zion.ZionNamePrefix[] r12 = (com.bitcoin.mwallet.zion.ZionNamePrefix[]) r12
            java.lang.Object r13 = r2.L$4
            com.bitcoin.mwallet.zion.ZionNamePrefix[] r13 = (com.bitcoin.mwallet.zion.ZionNamePrefix[]) r13
            long r14 = r2.J$0
            r17 = r4
            java.lang.Object r4 = r2.L$3
            java.util.List r4 = (java.util.List) r4
            r18 = r4
            java.lang.Object r4 = r2.L$2
            java.util.Set r4 = (java.util.Set) r4
            r19 = r4
            java.lang.Object r4 = r2.L$1
            java.util.List r4 = (java.util.List) r4
            r20 = r4
            java.lang.Object r4 = r2.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r4 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r0 = r1
            r24 = r3
            r23 = r6
            r6 = r13
            r1 = r17
            r3 = r20
            r13 = r7
            r17 = r9
            r9 = r18
            r25 = r12
            r12 = r8
            r7 = r14
            r14 = r10
            r15 = r11
            r11 = r25
            r10 = r19
            goto L_0x0230
        L_0x00ed:
            java.lang.Object r4 = r2.L$8
            com.bitcoin.mwallet.zion.ZionWalletName r4 = (com.bitcoin.mwallet.zion.ZionWalletName) r4
            int r5 = r2.I$3
            int r6 = r2.I$2
            java.lang.Object r7 = r2.L$7
            com.bitcoin.mwallet.zion.ZionNamePrefix r7 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r7
            java.lang.Object r8 = r2.L$6
            com.bitcoin.mwallet.zion.ZionNamePrefix r8 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r8
            int r9 = r2.I$1
            int r10 = r2.I$0
            java.lang.Object r11 = r2.L$5
            com.bitcoin.mwallet.zion.ZionNamePrefix[] r11 = (com.bitcoin.mwallet.zion.ZionNamePrefix[]) r11
            java.lang.Object r12 = r2.L$4
            com.bitcoin.mwallet.zion.ZionNamePrefix[] r12 = (com.bitcoin.mwallet.zion.ZionNamePrefix[]) r12
            long r13 = r2.J$0
            java.lang.Object r15 = r2.L$3
            java.util.List r15 = (java.util.List) r15
            r17 = r4
            java.lang.Object r4 = r2.L$2
            java.util.Set r4 = (java.util.Set) r4
            r18 = r4
            java.lang.Object r4 = r2.L$1
            java.util.List r4 = (java.util.List) r4
            r19 = r4
            java.lang.Object r4 = r2.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r4 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r22 = r3
            r21 = r17
            r3 = r18
            r0 = r19
            goto L_0x01da
        L_0x012e:
            java.lang.Object r4 = r2.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r4 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r5 = r4
            r4 = 1
            goto L_0x014a
        L_0x0138:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.core.dao.ZionNameDao r1 = r0.zionNameDao
            r2.L$0 = r0
            r4 = 1
            r2.label = r4
            java.lang.Object r1 = r1.selectAll(r2)
            if (r1 != r3) goto L_0x0149
            return r3
        L_0x0149:
            r5 = r0
        L_0x014a:
            java.util.List r1 = (java.util.List) r1
            r6 = r1
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            r6 = r6 ^ r4
            if (r6 == 0) goto L_0x0163
            r4 = 0
            java.lang.Object[] r1 = new java.lang.Object[r4]
            java.lang.String r2 = "Attempt to scan for zion wallets with a non empty database"
            timber.log.Timber.m438w(r2, r1)
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            return r1
        L_0x0163:
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.Set r4 = (java.util.Set) r4
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.List r6 = (java.util.List) r6
            long r7 = java.lang.System.currentTimeMillis()
            com.bitcoin.mwallet.zion.ZionNamePrefix[] r9 = com.bitcoin.mwallet.zion.ZionNamePrefix.values()
            int r10 = r9.length
            r11 = r7
            r7 = r2
            r8 = r3
            r3 = r9
            r2 = r1
            r1 = 0
        L_0x0180:
            if (r1 >= r10) goto L_0x0322
            r13 = r9[r1]
            com.bitcoin.mwallet.core.dao.ZionNameDao r14 = r5.zionNameDao
            int r14 = r14.getMaxNames()
            if (r14 < 0) goto L_0x031b
            r15 = r6
            r17 = r8
            r8 = r13
            r6 = 0
            r25 = r9
            r9 = r1
            r1 = r7
            r7 = r8
            r26 = r11
            r12 = r3
            r3 = r4
            r4 = r5
            r11 = r25
            r5 = r14
            r13 = r26
        L_0x01a0:
            com.bitcoin.mwallet.zion.ZionWalletName r0 = new com.bitcoin.mwallet.zion.ZionWalletName
            r0.<init>(r7, r6)
            r1.L$0 = r4
            r1.L$1 = r2
            r1.L$2 = r3
            r1.L$3 = r15
            r1.J$0 = r13
            r1.L$4 = r12
            r1.L$5 = r11
            r1.I$0 = r10
            r1.I$1 = r9
            r1.L$6 = r8
            r1.L$7 = r7
            r1.I$2 = r6
            r1.I$3 = r5
            r1.L$8 = r0
            r18 = r3
            r3 = 2
            r1.label = r3
            java.lang.Object r3 = r4.registerWallet(r0, r1)
            r19 = r0
            r0 = r17
            if (r3 != r0) goto L_0x01d1
            return r0
        L_0x01d1:
            r22 = r0
            r0 = r2
            r21 = r19
            r2 = r1
            r1 = r3
            r3 = r18
        L_0x01da:
            com.bitcoin.mwallet.zion.ZionResponse r1 = (com.bitcoin.mwallet.zion.ZionResponse) r1
            java.lang.Object r1 = r1.getResult()
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            if (r1 == 0) goto L_0x02ec
            r2.L$0 = r4
            r2.L$1 = r0
            r2.L$2 = r3
            r2.L$3 = r15
            r2.J$0 = r13
            r2.L$4 = r12
            r2.L$5 = r11
            r2.I$0 = r10
            r2.I$1 = r9
            r2.L$6 = r8
            r2.L$7 = r7
            r2.I$2 = r6
            r2.I$3 = r5
            r17 = r0
            r0 = r21
            r2.L$8 = r0
            r2.L$9 = r1
            r18 = r0
            r0 = 3
            r2.label = r0
            java.lang.Object r0 = r4.isSeedExists(r1, r2)
            r19 = r1
            r1 = r22
            if (r0 != r1) goto L_0x0216
            return r1
        L_0x0216:
            r24 = r1
            r23 = r5
            r5 = r18
            r1 = r19
            r25 = r10
            r10 = r3
            r3 = r17
            r17 = r8
            r26 = r13
            r13 = r6
            r14 = r9
            r6 = r12
            r9 = r15
            r12 = r7
            r15 = r25
            r7 = r26
        L_0x0230:
            com.bitcoin.mwallet.zion.ZionResponse r0 = (com.bitcoin.mwallet.zion.ZionResponse) r0
            java.lang.Object r0 = r0.getResult()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            r18 = r14
            r16 = 1
            java.lang.Boolean r14 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r16)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r14)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r19 = r15
            java.lang.String r15 = "Scanning Zion walletName="
            r14.append(r15)
            r14.append(r5)
            java.lang.String r15 = " seedExists="
            r14.append(r15)
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            r20 = r5
            r15 = 0
            java.lang.Object[] r5 = new java.lang.Object[r15]
            timber.log.Timber.m426d(r14, r5)
            if (r0 == 0) goto L_0x0290
            kotlin.Pair r0 = new kotlin.Pair
            r0.<init>(r1, r12)
            r9.add(r0)
            com.bitcoin.mwallet.core.entity.ZionNameEntity r0 = new com.bitcoin.mwallet.core.entity.ZionNameEntity
            r0.<init>(r13, r1)
            r10.add(r0)
            r1 = r2
            r2 = r3
            r15 = r9
            r3 = r10
            r9 = r18
            r10 = r19
            r5 = r23
            r25 = r12
            r12 = r6
            r6 = r13
            r13 = r7
            r7 = r25
            r8 = r17
            r17 = r24
            goto L_0x02f7
        L_0x0290:
            r2.L$0 = r4
            r2.L$1 = r3
            r2.L$2 = r10
            r2.L$3 = r9
            r2.J$0 = r7
            r2.L$4 = r6
            r2.L$5 = r11
            r5 = r19
            r2.I$0 = r5
            r14 = r18
            r2.I$1 = r14
            r15 = r17
            r2.L$6 = r15
            r2.L$7 = r12
            r2.I$2 = r13
            r17 = r3
            r3 = r23
            r2.I$3 = r3
            r18 = r3
            r3 = r20
            r2.L$8 = r3
            r2.L$9 = r1
            r2.Z$0 = r0
            r0 = 4
            r2.label = r0
            java.lang.Object r1 = r4.unregisterWallet(r1, r3, r2)
            r3 = r24
            if (r1 != r3) goto L_0x02ca
            return r3
        L_0x02ca:
            r1 = r6
            r6 = r13
            r13 = r15
            r15 = r5
            r5 = r11
            r25 = r18
            r18 = r17
            r17 = r25
        L_0x02d5:
            r11 = r5
            r5 = r17
            r17 = r3
            r3 = r10
            r10 = r15
            r15 = r9
            r9 = r14
            r25 = r12
            r12 = r1
            r1 = r2
            r2 = r18
            r26 = r7
            r7 = r25
            r8 = r13
            r13 = r26
            goto L_0x02f7
        L_0x02ec:
            r17 = r0
            r1 = r22
            r25 = r17
            r17 = r1
            r1 = r2
            r2 = r25
        L_0x02f7:
            int r0 = r15.size()
            r18 = r1
            com.bitcoin.mwallet.core.dao.ZionNameDao r1 = r4.zionNameDao
            int r1 = r1.getMaxNames()
            if (r0 < r1) goto L_0x0306
            goto L_0x030e
        L_0x0306:
            if (r6 == r5) goto L_0x030e
            int r6 = r6 + 1
            r1 = r18
            goto L_0x01a0
        L_0x030e:
            r5 = r4
            r1 = r9
            r9 = r11
            r6 = r15
            r8 = r17
            r7 = r18
            r0 = 1
            r4 = r3
            r3 = r12
            r11 = r13
            goto L_0x031d
        L_0x031b:
            r0 = r3
            r0 = 1
        L_0x031d:
            int r1 = r1 + r0
            r0 = r28
            goto L_0x0180
        L_0x0322:
            com.bitcoin.mwallet.core.dao.ZionNameDao r0 = r5.zionNameDao
            r1 = r4
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x037c
            r3 = 0
            com.bitcoin.mwallet.core.entity.ZionNameEntity[] r9 = new com.bitcoin.mwallet.core.entity.ZionNameEntity[r3]
            java.lang.Object[] r1 = r1.toArray(r9)
            if (r1 == 0) goto L_0x0374
            com.bitcoin.mwallet.core.entity.ZionNameEntity[] r1 = (com.bitcoin.mwallet.core.entity.ZionNameEntity[]) r1
            int r3 = r1.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)
            com.bitcoin.mwallet.core.entity.ZionNameEntity[] r1 = (com.bitcoin.mwallet.core.entity.ZionNameEntity[]) r1
            r7.L$0 = r5
            r7.L$1 = r2
            r7.L$2 = r4
            r7.L$3 = r6
            r7.J$0 = r11
            r2 = 5
            r7.label = r2
            java.lang.Object r0 = r0.upsert(r1, r7)
            if (r0 != r8) goto L_0x034f
            return r8
        L_0x034f:
            r5 = r6
            r3 = r11
        L_0x0351:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Zion wallet name scan ms="
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            long r1 = r1 - r3
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            timber.log.Timber.m426d(r0, r1)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r0 = kotlin.collections.CollectionsKt.toList(r5)
            return r0
        L_0x0374:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Array<T>"
            r0.<init>(r1)
            throw r0
        L_0x037c:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type java.util.Collection<T>"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.scanAndRestoreZionWallets(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0096  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object hasZion(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r6 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$hasZion$1     // Catch:{ all -> 0x009d }
            if (r0 == 0) goto L_0x0015
            r0 = r6
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$hasZion$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$hasZion$1) r0     // Catch:{ all -> 0x009d }
            int r1 = r0.label     // Catch:{ all -> 0x009d }
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0015
            int r6 = r0.label     // Catch:{ all -> 0x009d }
            int r6 = r6 - r2
            r0.label = r6     // Catch:{ all -> 0x009d }
            goto L_0x001a
        L_0x0015:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$hasZion$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$hasZion$1     // Catch:{ all -> 0x009d }
            r0.<init>(r5, r6)     // Catch:{ all -> 0x009d }
        L_0x001a:
            java.lang.Object r6 = r0.result     // Catch:{ all -> 0x009d }
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch:{ all -> 0x009d }
            int r2 = r0.label     // Catch:{ all -> 0x009d }
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r4) goto L_0x0034
            java.lang.Object r1 = r0.L$1     // Catch:{ all -> 0x009d }
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r1 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r1     // Catch:{ all -> 0x009d }
            java.lang.Object r0 = r0.L$0     // Catch:{ all -> 0x009d }
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r0     // Catch:{ all -> 0x009d }
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x009d }
            goto L_0x008c
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x009d }
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)     // Catch:{ all -> 0x009d }
            throw r6     // Catch:{ all -> 0x009d }
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x009d }
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = r5.manager     // Catch:{ all -> 0x009d }
            if (r6 == 0) goto L_0x0049
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch:{ all -> 0x009d }
            monitor-exit(r5)
            return r6
        L_0x0049:
            java.util.concurrent.atomic.AtomicReference<java.lang.Boolean> r6 = r5.triedToInit     // Catch:{ all -> 0x009d }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x009d }
            java.lang.String r2 = "triedToInit.get()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r2)     // Catch:{ all -> 0x009d }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x009d }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x009d }
            if (r6 == 0) goto L_0x0062
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ all -> 0x009d }
            monitor-exit(r5)
            return r6
        L_0x0062:
            boolean r6 = r5.deviceSupported()     // Catch:{ all -> 0x009d }
            if (r6 != 0) goto L_0x006e
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ all -> 0x009d }
            monitor-exit(r5)
            return r6
        L_0x006e:
            java.util.concurrent.atomic.AtomicReference<java.lang.Boolean> r6 = r5.triedToInit     // Catch:{ all -> 0x009d }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x009d }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x009d }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x009d }
            if (r6 != 0) goto L_0x0091
            r0.L$0 = r5     // Catch:{ all -> 0x009d }
            r0.L$1 = r5     // Catch:{ all -> 0x009d }
            r0.label = r4     // Catch:{ all -> 0x009d }
            java.lang.Object r6 = r5.tryInitManager(r0)     // Catch:{ all -> 0x009d }
            if (r6 != r1) goto L_0x008a
            monitor-exit(r5)
            return r1
        L_0x008a:
            r0 = r5
            r1 = r0
        L_0x008c:
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r6     // Catch:{ all -> 0x009d }
            r1.manager = r6     // Catch:{ all -> 0x009d }
            goto L_0x0092
        L_0x0091:
            r0 = r5
        L_0x0092:
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = r0.manager     // Catch:{ all -> 0x009d }
            if (r6 == 0) goto L_0x0097
            r3 = 1
        L_0x0097:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ all -> 0x009d }
            monitor-exit(r5)
            return r6
        L_0x009d:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.hasZion(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean deviceSupported() {
        FeatureInfo featureInfo;
        PackageManager packageManager = this.application.getPackageManager();
        Intrinsics.checkExpressionValueIsNotNull(packageManager, "application.packageManager");
        FeatureInfo[] systemAvailableFeatures = packageManager.getSystemAvailableFeatures();
        Intrinsics.checkExpressionValueIsNotNull(systemAvailableFeatures, "application.packageManager.systemAvailableFeatures");
        int length = systemAvailableFeatures.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                featureInfo = null;
                break;
            }
            featureInfo = systemAvailableFeatures[i];
            if (Intrinsics.areEqual((Object) featureInfo.name, (Object) "com.htc.hardware.wallet")) {
                break;
            }
            i++;
        }
        if (featureInfo != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object tryInitManager(@NotNull Continuation<? super HtcWalletSdkManager> continuation) {
        return Companion.tryZionCall("init", new ZionRepositoryZKMA$tryInitManager$2(this), continuation);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0072 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object isSeedExists(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionResponse<java.lang.Boolean>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$isSeedExists$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$isSeedExists$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$isSeedExists$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$isSeedExists$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$isSeedExists$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r7 = r0.L$2
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r7 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r7
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.zion.ZionId r7 = (com.bitcoin.mwallet.zion.ZionId) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r7 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x006e
        L_0x0036:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r8)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r8 = r6.manager
            if (r8 == 0) goto L_0x0073
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r2 = Companion
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "isSeedExists zionId="
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$isSeedExists$$inlined$let$lambda$1 r5 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$isSeedExists$$inlined$let$lambda$1
            r5.<init>(r8, r0, r7)
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r8 = r2.tryZionCall(r4, r5, r0)
            if (r8 != r1) goto L_0x006e
            return r1
        L_0x006e:
            com.bitcoin.mwallet.zion.ZionResponse r8 = (com.bitcoin.mwallet.zion.ZionResponse) r8
            if (r8 == 0) goto L_0x0073
            return r8
        L_0x0073:
            com.bitcoin.mwallet.zion.ZionResponse r7 = new com.bitcoin.mwallet.zion.ZionResponse
            r8 = 0
            com.bitcoin.mwallet.zion.ZionError r0 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            r7.<init>(r8, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.isSeedExists(com.bitcoin.mwallet.zion.ZionId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object showWalletSeed(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionError> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$showWalletSeed$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$showWalletSeed$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$showWalletSeed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$showWalletSeed$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$showWalletSeed$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r7 = r0.L$2
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r7 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r7
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.zion.ZionId r7 = (com.bitcoin.mwallet.zion.ZionId) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r7 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0073
        L_0x0036:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r8)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r8 = r6.manager
            if (r8 != 0) goto L_0x0048
            com.bitcoin.mwallet.zion.ZionError r7 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            return r7
        L_0x0048:
            if (r8 == 0) goto L_0x0076
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r2 = Companion
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "showWalletSeed zionId="
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$showWalletSeed$$inlined$let$lambda$1 r5 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$showWalletSeed$$inlined$let$lambda$1
            r5.<init>(r8, r0, r7)
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r8 = r2.tryZionCall(r4, r5, r0)
            if (r8 != r1) goto L_0x0073
            return r1
        L_0x0073:
            java.lang.Void r8 = (java.lang.Void) r8
            goto L_0x0077
        L_0x0076:
            r8 = 0
        L_0x0077:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.showWalletSeed(com.bitcoin.mwallet.zion.ZionId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ee A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getWalletXPub(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r21, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionResponse<com.bitcoin.mwallet.zion.ZionXPub>> r23) {
        /*
            r20 = this;
            r0 = r20
            r10 = r22
            r1 = r23
            boolean r2 = r1 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getWalletXPub$1
            if (r2 == 0) goto L_0x001a
            r2 = r1
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getWalletXPub$1 r2 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getWalletXPub$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x001a
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001f
        L_0x001a:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getWalletXPub$1 r2 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getWalletXPub$1
            r2.<init>(r0, r1)
        L_0x001f:
            r11 = r2
            java.lang.Object r1 = r11.result
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            r13 = 0
            r14 = 1
            if (r2 == 0) goto L_0x0055
            if (r2 != r14) goto L_0x004d
            java.lang.Object r2 = r11.L$4
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r2 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r2
            int r2 = r11.I$2
            int r2 = r11.I$1
            int r2 = r11.I$0
            java.lang.Object r2 = r11.L$3
            com.bitcoin.bitcoink.DerivationPath r2 = (com.bitcoin.bitcoink.DerivationPath) r2
            java.lang.Object r2 = r11.L$2
            com.bitcoin.mwallet.core.models.Coin r2 = (com.bitcoin.mwallet.core.models.Coin) r2
            java.lang.Object r2 = r11.L$1
            com.bitcoin.mwallet.zion.ZionId r2 = (com.bitcoin.mwallet.zion.ZionId) r2
            java.lang.Object r2 = r11.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r2 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00ea
        L_0x004d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0055:
            kotlin.ResultKt.throwOnFailure(r1)
            java.util.Map<com.bitcoin.mwallet.core.models.Coin, com.bitcoin.bitcoink.DerivationPath> r1 = coinPaths
            java.lang.Object r1 = r1.get(r10)
            r15 = r1
            com.bitcoin.bitcoink.DerivationPath r15 = (com.bitcoin.bitcoink.DerivationPath) r15
            if (r15 == 0) goto L_0x00f8
            java.util.List r1 = r15.getPath()
            int r1 = r1.size()
            r2 = 3
            if (r1 == r2) goto L_0x0076
            com.bitcoin.mwallet.zion.ZionResponse r1 = new com.bitcoin.mwallet.zion.ZionResponse
            com.bitcoin.mwallet.zion.ZionError r2 = com.bitcoin.mwallet.zion.ZionError.BAD_ARGUMENTS
            r1.<init>(r13, r2)
            return r1
        L_0x0076:
            java.util.List r1 = r15.getPath()
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            com.bitcoin.bitcoink.DerivationPath$Path r1 = (com.bitcoin.bitcoink.DerivationPath.Path) r1
            int r9 = r1.getPath()
            java.util.List r1 = r15.getPath()
            java.lang.Object r1 = r1.get(r14)
            com.bitcoin.bitcoink.DerivationPath$Path r1 = (com.bitcoin.bitcoink.DerivationPath.Path) r1
            int r8 = r1.getPath()
            java.util.List r1 = r15.getPath()
            r2 = 2
            java.lang.Object r1 = r1.get(r2)
            com.bitcoin.bitcoink.DerivationPath$Path r1 = (com.bitcoin.bitcoink.DerivationPath.Path) r1
            int r7 = r1.getPath()
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = r0.manager
            if (r6 == 0) goto L_0x00ef
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r5 = Companion
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getWalletXPub$$inlined$let$lambda$1 r16 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$getWalletXPub$$inlined$let$lambda$1
            r1 = r16
            r2 = r6
            r3 = r11
            r4 = r21
            r13 = r5
            r5 = r9
            r14 = r6
            r6 = r8
            r17 = r7
            r18 = r12
            r12 = r8
            r8 = r22
            r19 = r13
            r13 = r9
            r9 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r1 = r16
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            r11.L$0 = r0
            r2 = r21
            r11.L$1 = r2
            r11.L$2 = r10
            r11.L$3 = r15
            r11.I$0 = r13
            r11.I$1 = r12
            r2 = r17
            r11.I$2 = r2
            r11.L$4 = r14
            r2 = 1
            r11.label = r2
            java.lang.String r2 = "getWalletXPub"
            r3 = r19
            java.lang.Object r1 = r3.tryZionCall(r2, r1, r11)
            r2 = r18
            if (r1 != r2) goto L_0x00ea
            return r2
        L_0x00ea:
            com.bitcoin.mwallet.zion.ZionResponse r1 = (com.bitcoin.mwallet.zion.ZionResponse) r1
            if (r1 == 0) goto L_0x00ef
            return r1
        L_0x00ef:
            com.bitcoin.mwallet.zion.ZionResponse r1 = new com.bitcoin.mwallet.zion.ZionResponse
            com.bitcoin.mwallet.zion.ZionError r2 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            r3 = 0
            r1.<init>(r3, r2)
            return r1
        L_0x00f8:
            r3 = r13
            com.bitcoin.mwallet.zion.ZionResponse r1 = new com.bitcoin.mwallet.zion.ZionResponse
            com.bitcoin.mwallet.zion.ZionError r2 = com.bitcoin.mwallet.zion.ZionError.BAD_ARGUMENTS
            r1.<init>(r3, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.getWalletXPub(com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.core.models.Coin, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object createWalletSeed(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r17, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionWalletName r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionError> r19) {
        /*
            r16 = this;
            r7 = r16
            r8 = r17
            r0 = r19
            boolean r1 = r0 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$createWalletSeed$1
            if (r1 == 0) goto L_0x001a
            r1 = r0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$createWalletSeed$1 r1 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$createWalletSeed$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x001a
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001f
        L_0x001a:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$createWalletSeed$1 r1 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$createWalletSeed$1
            r1.<init>(r7, r0)
        L_0x001f:
            r9 = r1
            java.lang.Object r0 = r9.result
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r11 = 1
            if (r1 == 0) goto L_0x0049
            if (r1 != r11) goto L_0x0041
            java.lang.Object r1 = r9.L$3
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r1 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r1
            java.lang.Object r1 = r9.L$2
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r9.L$1
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r9.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r1 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x008c
        L_0x0041:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r0)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r12 = r7.manager
            if (r12 != 0) goto L_0x0053
            com.bitcoin.mwallet.zion.ZionError r0 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            return r0
        L_0x0053:
            if (r12 == 0) goto L_0x008f
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r13 = Companion
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "createWalletSeed zionId="
            r0.append(r1)
            r0.append(r8)
            java.lang.String r14 = r0.toString()
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1 r15 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1
            r2 = 0
            r0 = r15
            r1 = r12
            r3 = r16
            r4 = r9
            r5 = r17
            r6 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6)
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15
            r9.L$0 = r7
            r9.L$1 = r8
            r0 = r18
            r9.L$2 = r0
            r9.L$3 = r12
            r9.label = r11
            java.lang.Object r0 = r13.trySuspendZionCall(r14, r15, r9)
            if (r0 != r10) goto L_0x008c
            return r10
        L_0x008c:
            com.bitcoin.mwallet.zion.ZionError r0 = (com.bitcoin.mwallet.zion.ZionError) r0
            goto L_0x0090
        L_0x008f:
            r0 = 0
        L_0x0090:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.createWalletSeed(com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.zion.ZionWalletName, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object restoreWalletSeed(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r13, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionWalletName r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionError> r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$restoreWalletSeed$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$restoreWalletSeed$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$restoreWalletSeed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$restoreWalletSeed$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$restoreWalletSeed$1
            r0.<init>(r12, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r9 = 1
            if (r1 == 0) goto L_0x0042
            if (r1 != r9) goto L_0x003a
            java.lang.Object r13 = r0.L$3
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r13 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r13
            java.lang.Object r13 = r0.L$2
            com.bitcoin.mwallet.zion.ZionWalletName r13 = (com.bitcoin.mwallet.zion.ZionWalletName) r13
            java.lang.Object r13 = r0.L$1
            com.bitcoin.mwallet.zion.ZionId r13 = (com.bitcoin.mwallet.zion.ZionId) r13
            java.lang.Object r13 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r13 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r13
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0071
        L_0x003a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r15)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r15 = r12.manager
            if (r15 != 0) goto L_0x004c
            com.bitcoin.mwallet.zion.ZionError r13 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            return r13
        L_0x004c:
            if (r15 == 0) goto L_0x0074
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r10 = Companion
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$restoreWalletSeed$$inlined$let$lambda$1 r11 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$restoreWalletSeed$$inlined$let$lambda$1
            r3 = 0
            r1 = r11
            r2 = r15
            r4 = r12
            r5 = r0
            r6 = r13
            r7 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            r0.L$0 = r12
            r0.L$1 = r13
            r0.L$2 = r14
            r0.L$3 = r15
            r0.label = r9
            java.lang.String r13 = "restoreWalletSeed"
            java.lang.Object r15 = r10.trySuspendZionCall(r13, r11, r0)
            if (r15 != r8) goto L_0x0071
            return r8
        L_0x0071:
            com.bitcoin.mwallet.zion.ZionError r15 = (com.bitcoin.mwallet.zion.ZionError) r15
            goto L_0x0075
        L_0x0074:
            r15 = 0
        L_0x0075:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.restoreWalletSeed(com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.zion.ZionWalletName, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object clearWalletSeed(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionError> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$clearWalletSeed$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$clearWalletSeed$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$clearWalletSeed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$clearWalletSeed$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$clearWalletSeed$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r6 = r0.L$2
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r6
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.zion.ZionId r6 = (com.bitcoin.mwallet.zion.ZionId) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r6 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0064
        L_0x0036:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r7)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r7 = r5.manager
            if (r7 != 0) goto L_0x0048
            com.bitcoin.mwallet.zion.ZionError r6 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            return r6
        L_0x0048:
            if (r7 == 0) goto L_0x0067
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r2 = Companion
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$clearWalletSeed$$inlined$let$lambda$1 r4 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$clearWalletSeed$$inlined$let$lambda$1
            r4.<init>(r7, r0, r6)
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.String r6 = "clearWalletSeed"
            java.lang.Object r7 = r2.tryZionCall(r6, r4, r0)
            if (r7 != r1) goto L_0x0064
            return r1
        L_0x0064:
            com.bitcoin.mwallet.zion.ZionError r7 = (com.bitcoin.mwallet.zion.ZionError) r7
            goto L_0x0068
        L_0x0067:
            r7 = 0
        L_0x0068:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.clearWalletSeed(com.bitcoin.mwallet.zion.ZionId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object confirmPin(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionError> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$confirmPin$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$confirmPin$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$confirmPin$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$confirmPin$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$confirmPin$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r6 = r0.L$2
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r6
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.zion.ZionId r6 = (com.bitcoin.mwallet.zion.ZionId) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r6 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0064
        L_0x0036:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r7)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r7 = r5.manager
            if (r7 != 0) goto L_0x0048
            com.bitcoin.mwallet.zion.ZionError r6 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            return r6
        L_0x0048:
            if (r7 == 0) goto L_0x0067
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r2 = Companion
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$confirmPin$$inlined$let$lambda$1 r4 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$confirmPin$$inlined$let$lambda$1
            r4.<init>(r7, r0, r6)
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.String r6 = "confirmPin"
            java.lang.Object r7 = r2.tryZionCall(r6, r4, r0)
            if (r7 != r1) goto L_0x0064
            return r1
        L_0x0064:
            com.bitcoin.mwallet.zion.ZionError r7 = (com.bitcoin.mwallet.zion.ZionError) r7
            goto L_0x0068
        L_0x0067:
            r7 = 0
        L_0x0068:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.confirmPin(com.bitcoin.mwallet.zion.ZionId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object registerWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionWalletName r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionResponse<com.bitcoin.mwallet.zion.ZionId>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$registerWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$registerWallet$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$registerWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$registerWallet$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$registerWallet$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r6 = r0.L$2
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r6
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.zion.ZionWalletName r6 = (com.bitcoin.mwallet.zion.ZionWalletName) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r6 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005f
        L_0x0036:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r7)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r7 = r5.manager
            if (r7 == 0) goto L_0x0064
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r2 = Companion
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$registerWallet$$inlined$let$lambda$1 r4 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$registerWallet$$inlined$let$lambda$1
            r4.<init>(r7, r0, r6)
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.String r6 = "registerWallet"
            java.lang.Object r7 = r2.tryZionCall(r6, r4, r0)
            if (r7 != r1) goto L_0x005f
            return r1
        L_0x005f:
            com.bitcoin.mwallet.zion.ZionResponse r7 = (com.bitcoin.mwallet.zion.ZionResponse) r7
            if (r7 == 0) goto L_0x0064
            return r7
        L_0x0064:
            com.bitcoin.mwallet.zion.ZionResponse r6 = new com.bitcoin.mwallet.zion.ZionResponse
            r7 = 0
            com.bitcoin.mwallet.zion.ZionError r0 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            r6.<init>(r7, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.registerWallet(com.bitcoin.mwallet.zion.ZionWalletName, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object unregisterWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r6, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionWalletName r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$unregisterWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$unregisterWallet$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$unregisterWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$unregisterWallet$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$unregisterWallet$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            java.lang.Object r6 = r0.L$3
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r6 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r6
            java.lang.Object r6 = r0.L$2
            com.bitcoin.mwallet.zion.ZionWalletName r6 = (com.bitcoin.mwallet.zion.ZionWalletName) r6
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.zion.ZionId r6 = (com.bitcoin.mwallet.zion.ZionId) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r6 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0065
        L_0x003a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r8)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r8 = r5.manager
            if (r8 == 0) goto L_0x0067
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r2 = Companion
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$unregisterWallet$$inlined$let$lambda$1 r4 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$unregisterWallet$$inlined$let$lambda$1
            r4.<init>(r8, r0, r7, r6)
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r8
            r0.label = r3
            java.lang.String r6 = "unregisterWallet"
            java.lang.Object r8 = r2.tryZionCall(r6, r4, r0)
            if (r8 != r1) goto L_0x0065
            return r1
        L_0x0065:
            kotlin.Unit r8 = (kotlin.Unit) r8
        L_0x0067:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.unregisterWallet(com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.zion.ZionWalletName, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x009f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object signTransaction(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r18, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r19, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionTransaction r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionResponse<byte[]>> r21) {
        /*
            r17 = this;
            r7 = r17
            r8 = r18
            r9 = r20
            r0 = r21
            boolean r1 = r0 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signTransaction$1
            if (r1 == 0) goto L_0x001c
            r1 = r0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signTransaction$1 r1 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signTransaction$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x001c
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x0021
        L_0x001c:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signTransaction$1 r1 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signTransaction$1
            r1.<init>(r7, r0)
        L_0x0021:
            r10 = r1
            java.lang.Object r0 = r10.result
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r12 = 1
            if (r1 == 0) goto L_0x004f
            if (r1 != r12) goto L_0x0047
            java.lang.Object r1 = r10.L$4
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r1 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r1
            java.lang.Object r1 = r10.L$3
            com.bitcoin.mwallet.zion.ZionTransaction r1 = (com.bitcoin.mwallet.zion.ZionTransaction) r1
            java.lang.Object r1 = r10.L$2
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r10.L$1
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r10.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r1 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x009b
        L_0x0047:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r0)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r13 = r7.manager
            if (r13 == 0) goto L_0x00a0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r14 = Companion
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "signTransaction zionId="
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = " zionTransaction="
            r0.append(r1)
            r0.append(r9)
            java.lang.String r15 = r0.toString()
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signTransaction$$inlined$let$lambda$1 r16 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signTransaction$$inlined$let$lambda$1
            r0 = r16
            r1 = r13
            r2 = r17
            r3 = r10
            r4 = r18
            r5 = r20
            r6 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0 = r16
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r10.L$0 = r7
            r10.L$1 = r8
            r1 = r19
            r10.L$2 = r1
            r10.L$3 = r9
            r10.L$4 = r13
            r10.label = r12
            java.lang.Object r0 = r14.tryZionCall(r15, r0, r10)
            if (r0 != r11) goto L_0x009b
            return r11
        L_0x009b:
            com.bitcoin.mwallet.zion.ZionResponse r0 = (com.bitcoin.mwallet.zion.ZionResponse) r0
            if (r0 == 0) goto L_0x00a0
            return r0
        L_0x00a0:
            com.bitcoin.mwallet.zion.ZionResponse r0 = new com.bitcoin.mwallet.zion.ZionResponse
            r1 = 0
            com.bitcoin.mwallet.zion.ZionError r2 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            r0.<init>(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.signTransaction(com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.core.models.Coin, com.bitcoin.mwallet.zion.ZionTransaction, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0086 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object signMessage(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r7, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r8, @org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionResponse<byte[]>> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signMessage$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signMessage$1 r0 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signMessage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signMessage$1 r0 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signMessage$1
            r0.<init>(r6, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            java.lang.Object r7 = r0.L$4
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r7 = (com.htc.htcwalletsdk.Export.HtcWalletSdkManager) r7
            java.lang.Object r7 = r0.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$2
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.zion.ZionId r7 = (com.bitcoin.mwallet.zion.ZionId) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA r7 = (com.bitcoin.mwallet.zion.ZionRepositoryZKMA) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0082
        L_0x003e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r10)
            com.htc.htcwalletsdk.Export.HtcWalletSdkManager r10 = r6.manager
            if (r10 == 0) goto L_0x0087
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion r2 = Companion
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "signingMessage zionId="
            r4.append(r5)
            r4.append(r7)
            java.lang.String r5 = " message="
            r4.append(r5)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signMessage$$inlined$let$lambda$1 r5 = new com.bitcoin.mwallet.zion.ZionRepositoryZKMA$signMessage$$inlined$let$lambda$1
            r5.<init>(r10, r0, r7, r9)
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.L$4 = r10
            r0.label = r3
            java.lang.Object r10 = r2.tryZionCall(r4, r5, r0)
            if (r10 != r1) goto L_0x0082
            return r1
        L_0x0082:
            com.bitcoin.mwallet.zion.ZionResponse r10 = (com.bitcoin.mwallet.zion.ZionResponse) r10
            if (r10 == 0) goto L_0x0087
            return r10
        L_0x0087:
            com.bitcoin.mwallet.zion.ZionResponse r7 = new com.bitcoin.mwallet.zion.ZionResponse
            r8 = 0
            com.bitcoin.mwallet.zion.ZionError r9 = com.bitcoin.mwallet.zion.ZionError.NO_MANAGER
            r7.<init>(r8, r9)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionRepositoryZKMA.signMessage(com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.core.models.Coin, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static {
        String str = "m/44'/0'/0'";
        coinPaths = MapsKt.mapOf(new Pair(Coin.BCH, DerivationPath.Companion.parse(str)), new Pair(Coin.BTC, DerivationPath.Companion.parse(str)));
    }
}
