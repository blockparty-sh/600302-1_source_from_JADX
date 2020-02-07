package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.NetworkFeeDao;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFee;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFees;
import com.bitcoin.mwallet.core.services.networkfee.NetworkFeeResponse;
import com.bitcoin.mwallet.core.services.networkfee.NetworkFeeService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;", "", "networkFeeDao", "Lcom/bitcoin/mwallet/core/dao/NetworkFeeDao;", "networkFeeService", "Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeService;", "(Lcom/bitcoin/mwallet/core/dao/NetworkFeeDao;Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeService;)V", "getNetworkFee", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFees;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NetworkFeeRepository.kt */
public final class NetworkFeeRepository {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final Map<NetworkFeeLevel, BigDecimal> bch = MapsKt.mapOf(new Pair(NetworkFeeLevel.PRIORITY, BigDecimal.ONE), new Pair(NetworkFeeLevel.NORMAL, BigDecimal.ONE), new Pair(NetworkFeeLevel.ECONOMY, BigDecimal.ONE));
    /* access modifiers changed from: private */
    public static final Map<NetworkFeeLevel, BigDecimal> btc = MapsKt.mapOf(new Pair(NetworkFeeLevel.PRIORITY, new BigDecimal(50)), new Pair(NetworkFeeLevel.NORMAL, new BigDecimal(25)), new Pair(NetworkFeeLevel.ECONOMY, new BigDecimal(5)));
    /* access modifiers changed from: private */
    public final NetworkFeeDao networkFeeDao;
    /* access modifiers changed from: private */
    public final NetworkFeeService networkFeeService;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository$Companion;", "", "()V", "bch", "", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "Ljava/math/BigDecimal;", "btc", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: NetworkFeeRepository.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    public NetworkFeeRepository(@NotNull NetworkFeeDao networkFeeDao2, @NotNull NetworkFeeService networkFeeService2) {
        Intrinsics.checkParameterIsNotNull(networkFeeDao2, "networkFeeDao");
        Intrinsics.checkParameterIsNotNull(networkFeeService2, "networkFeeService");
        this.networkFeeDao = networkFeeDao2;
        this.networkFeeService = networkFeeService2;
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        Intrinsics.checkExpressionValueIsNotNull(newSingleThreadScheduledExecutor, "Executors.newSingleThreadScheduledExecutor()");
        newSingleThreadScheduledExecutor.scheduleWithFixedDelay(new Runnable(this) {
            final /* synthetic */ NetworkFeeRepository this$0;

            @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
            @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.NetworkFeeRepository$1$1", mo38000f = "NetworkFeeRepository.kt", mo38001i = {1, 1}, mo38002l = {29, 71}, mo38003m = "invokeSuspend", mo38004n = {"getNetworkFeeResponse", "fees"}, mo38005s = {"L$0", "L$1"})
            /* renamed from: com.bitcoin.mwallet.core.repositories.NetworkFeeRepository$1$1 */
            /* compiled from: NetworkFeeRepository.kt */
            static final class C12671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                Object L$1;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f386p$;
                final /* synthetic */ C12661 this$0;

                {
                    this.this$0 = r1;
                }

                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    Intrinsics.checkParameterIsNotNull(continuation, "completion");
                    C12671 r0 = new C12671(this.this$0, continuation);
                    r0.f386p$ = (CoroutineScope) obj;
                    return r0;
                }

                public final Object invoke(Object obj, Object obj2) {
                    return ((C12671) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f386p$;
                        NetworkFeeService access$getNetworkFeeService$p = this.this$0.this$0.networkFeeService;
                        Set of = SetsKt.setOf(Coin.BTC, Coin.BCH);
                        this.label = 1;
                        obj = access$getNetworkFeeService$p.getNetworkFee(of, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    } else if (i == 2) {
                        List list = (List) this.L$1;
                        NetworkFeeResponse networkFeeResponse = (NetworkFeeResponse) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    NetworkFeeResponse networkFeeResponse2 = (NetworkFeeResponse) obj;
                    List fees = networkFeeResponse2.getFees();
                    if (fees != null) {
                        NetworkFeeDao access$getNetworkFeeDao$p = this.this$0.this$0.networkFeeDao;
                        Object[] array = fees.toArray(new NetworkFee[0]);
                        if (array != null) {
                            NetworkFee[] networkFeeArr = (NetworkFee[]) array;
                            NetworkFee[] networkFeeArr2 = (NetworkFee[]) Arrays.copyOf(networkFeeArr, networkFeeArr.length);
                            this.L$0 = networkFeeResponse2;
                            this.L$1 = fees;
                            this.label = 2;
                            if (access$getNetworkFeeDao$p.upsert(networkFeeArr2, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            {
                this.this$0 = r1;
            }

            public final void run() {
                BuildersKt__BuildersKt.runBlocking$default(null, new C12671(this, null), 1, null);
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    @Nullable
    public final Object getNetworkFee(@NotNull Coin coin, @NotNull Continuation<? super NetworkFees> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new NetworkFeeRepository$getNetworkFee$2(this, coin, null), continuation);
    }
}
