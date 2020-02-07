package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.NetworkFeeDao;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFee;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFees;
import com.bitcoin.mwallet.core.repositories.NetworkFeeRepository.WhenMappings;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFees;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.NetworkFeeRepository$getNetworkFee$2", mo38000f = "NetworkFeeRepository.kt", mo38001i = {}, mo38002l = {41}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: NetworkFeeRepository.kt */
final class NetworkFeeRepository$getNetworkFee$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NetworkFees>, Object> {
    final /* synthetic */ Coin $coin;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f387p$;
    final /* synthetic */ NetworkFeeRepository this$0;

    NetworkFeeRepository$getNetworkFee$2(NetworkFeeRepository networkFeeRepository, Coin coin, Continuation continuation) {
        this.this$0 = networkFeeRepository;
        this.$coin = coin;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        NetworkFeeRepository$getNetworkFee$2 networkFeeRepository$getNetworkFee$2 = new NetworkFeeRepository$getNetworkFee$2(this.this$0, this.$coin, continuation);
        networkFeeRepository$getNetworkFee$2.f387p$ = (CoroutineScope) obj;
        return networkFeeRepository$getNetworkFee$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((NetworkFeeRepository$getNetworkFee$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        NetworkFees networkFees;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f387p$;
            NetworkFeeDao access$getNetworkFeeDao$p = this.this$0.networkFeeDao;
            Coin coin = this.$coin;
            this.label = 1;
            obj = access$getNetworkFeeDao$p.selectNetworkFees(coin, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        List list = (List) obj;
        if (list.size() != NetworkFeeLevel.values().length) {
            StringBuilder sb = new StringBuilder();
            sb.append("Fallback to default fees coin=");
            sb.append(this.$coin);
            Timber.m426d(sb.toString(), new Object[0]);
            int i2 = WhenMappings.$EnumSwitchMapping$0[this.$coin.ordinal()];
            if (i2 == 1) {
                networkFees = new NetworkFees(this.$coin, NetworkFeeRepository.bch);
            } else if (i2 == 2) {
                networkFees = new NetworkFees(this.$coin, NetworkFeeRepository.btc);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            return networkFees;
        }
        Iterable iterable = list;
        Map linkedHashMap = new LinkedHashMap();
        for (Object next : iterable) {
            NetworkFeeLevel feeLevel = ((NetworkFee) next).getFeeLevel();
            Object obj2 = linkedHashMap.get(feeLevel);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(feeLevel, obj2);
            }
            ((List) obj2).add(next);
        }
        Map linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Entry entry : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry.getKey(), ((NetworkFee) ((List) entry.getValue()).get(0)).getSatoshisPerByte());
        }
        return new NetworkFees(this.$coin, linkedHashMap2);
    }
}
