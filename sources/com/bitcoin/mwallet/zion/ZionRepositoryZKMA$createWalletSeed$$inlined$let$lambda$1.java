package com.bitcoin.mwallet.zion;

import com.bitcoin.mwallet.core.dao.ZionNameDao;
import com.bitcoin.mwallet.core.entity.ZionNameEntity;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/zion/ZionError;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$createWalletSeed$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1 extends SuspendLambda implements Function1<Continuation<? super ZionError>, Object> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ ZionWalletName $walletName$inlined;
    final /* synthetic */ ZionId $zionId$inlined;
    int I$0;
    Object L$0;
    int label;
    final /* synthetic */ ZionRepositoryZKMA this$0;

    ZionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionRepositoryZKMA zionRepositoryZKMA, Continuation continuation2, ZionId zionId, ZionWalletName zionWalletName) {
        this.$it = htcWalletSdkManager;
        this.this$0 = zionRepositoryZKMA;
        this.$continuation$inlined = continuation2;
        this.$zionId$inlined = zionId;
        this.$walletName$inlined = zionWalletName;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ZionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1 zionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1 = new ZionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1(this.$it, continuation, this.this$0, this.$continuation$inlined, this.$zionId$inlined, this.$walletName$inlined);
        return zionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1;
    }

    public final Object invoke(Object obj) {
        return ((ZionRepositoryZKMA$createWalletSeed$$inlined$let$lambda$1) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ZionError zionError;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int createSeed = this.$it.createSeed(this.$zionId$inlined.getId());
            zionError = ZionError.Companion.fromResult(createSeed);
            StringBuilder sb = new StringBuilder();
            sb.append("createWalletSeed zionWalletName=");
            sb.append(this.$walletName$inlined);
            sb.append(" result=");
            sb.append(createSeed);
            sb.append(" error=");
            sb.append(zionError);
            Timber.m426d(sb.toString(), new Object[0]);
            if (zionError == null) {
                ZionNameDao access$getZionNameDao$p = this.this$0.zionNameDao;
                ZionNameEntity[] zionNameEntityArr = {new ZionNameEntity(this.$walletName$inlined.getIndex(), this.$zionId$inlined)};
                this.I$0 = createSeed;
                this.L$0 = zionError;
                this.label = 1;
                if (access$getZionNameDao$p.upsert(zionNameEntityArr, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ZionError zionError2 = (ZionError) this.L$0;
            int i2 = this.I$0;
            ResultKt.throwOnFailure(obj);
            zionError = zionError2;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return zionError;
    }
}
