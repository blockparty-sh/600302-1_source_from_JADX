package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.WalletAddressInfoDao;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.address.AddressPurpose;
import com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler;
import com.bitcoin.mwallet.core.services.address.AddressPath;
import com.bitcoin.mwallet.core.services.address.AddressService;
import com.bitcoin.mwallet.core.services.address.NewMaxAddressPathResponse;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$requestMoreAddressesAsync$1", mo38000f = "WalletRepository.kt", mo38001i = {1, 1, 1}, mo38002l = {439, 450}, mo38003m = "invokeSuspend", mo38004n = {"response", "newAddressPath", "pathYNew"}, mo38005s = {"L$0", "L$1", "L$2"})
/* compiled from: WalletRepository.kt */
final class WalletRepository$AddressHandler$requestMoreAddressesAsync$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CopayerId $copayerId;
    final /* synthetic */ PathY $pathYCurrent;
    final /* synthetic */ AddressPurpose $purpose;
    final /* synthetic */ SigningKey $signingKey;
    final /* synthetic */ WalletId $walletId;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f400p$;
    final /* synthetic */ AddressHandler this$0;

    WalletRepository$AddressHandler$requestMoreAddressesAsync$1(AddressHandler addressHandler, WalletId walletId, CopayerId copayerId, SigningKey signingKey, AddressPurpose addressPurpose, PathY pathY, Continuation continuation) {
        this.this$0 = addressHandler;
        this.$walletId = walletId;
        this.$copayerId = copayerId;
        this.$signingKey = signingKey;
        this.$purpose = addressPurpose;
        this.$pathYCurrent = pathY;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$AddressHandler$requestMoreAddressesAsync$1 walletRepository$AddressHandler$requestMoreAddressesAsync$1 = new WalletRepository$AddressHandler$requestMoreAddressesAsync$1(this.this$0, this.$walletId, this.$copayerId, this.$signingKey, this.$purpose, this.$pathYCurrent, continuation);
        walletRepository$AddressHandler$requestMoreAddressesAsync$1.f400p$ = (CoroutineScope) obj;
        return walletRepository$AddressHandler$requestMoreAddressesAsync$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$AddressHandler$requestMoreAddressesAsync$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f400p$;
            AddressService access$getAddressService$p = WalletRepository.this.addressService;
            WalletId walletId = this.$walletId;
            CopayerId copayerId = this.$copayerId;
            SigningKey signingKey = this.$signingKey;
            AddressPath addressPath = new AddressPath(this.$purpose.getPathX(), this.$pathYCurrent.getMax());
            this.label = 1;
            obj = access$getAddressService$p.getNewMaxAddressPath(walletId, copayerId, signingKey, addressPath, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            PathY pathY = (PathY) this.L$2;
            AddressPath addressPath2 = (AddressPath) this.L$1;
            NewMaxAddressPathResponse newMaxAddressPathResponse = (NewMaxAddressPathResponse) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        NewMaxAddressPathResponse newMaxAddressPathResponse2 = (NewMaxAddressPathResponse) obj;
        AddressPath newMaxPath = newMaxAddressPathResponse2.getNewMaxPath();
        if (newMaxPath != null) {
            PathY pathY2 = this.$pathYCurrent;
            PathY copy = pathY2.copy(pathY2.getMax() + 1, newMaxPath.getY());
            StringBuilder sb = new StringBuilder();
            sb.append("Updating address Y path from ");
            sb.append(this.$pathYCurrent);
            sb.append(" to ");
            sb.append(copy);
            Timber.m426d(sb.toString(), new Object[0]);
            WalletAddressInfoDao access$getWalletAddressInfoDao$p = WalletRepository.this.walletAddressInfoDao;
            WalletId walletId2 = this.$walletId;
            int x = newMaxPath.getX();
            int max = this.$pathYCurrent.getMax() + 1;
            int max2 = copy.getMax();
            this.L$0 = newMaxAddressPathResponse2;
            this.L$1 = newMaxPath;
            this.L$2 = copy;
            this.label = 2;
            if (access$getWalletAddressInfoDao$p.updatePathY(walletId2, x, max, max2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            Timber.m429e(newMaxAddressPathResponse2.toString(), new Object[0]);
        }
        return Unit.INSTANCE;
    }
}
