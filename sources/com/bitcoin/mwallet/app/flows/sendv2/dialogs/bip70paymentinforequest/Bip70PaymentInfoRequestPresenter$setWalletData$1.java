package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$setWalletData$1", mo38000f = "Bip70PaymentInfoRequestPresenter.kt", mo38001i = {}, mo38002l = {208}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
final class Bip70PaymentInfoRequestPresenter$setWalletData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SendWalletData>, Object> {
    final /* synthetic */ C1261Wallet $wallet;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f275p$;
    final /* synthetic */ Bip70PaymentInfoRequestPresenter this$0;

    Bip70PaymentInfoRequestPresenter$setWalletData$1(Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter, C1261Wallet wallet, Continuation continuation) {
        this.this$0 = bip70PaymentInfoRequestPresenter;
        this.$wallet = wallet;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Bip70PaymentInfoRequestPresenter$setWalletData$1 bip70PaymentInfoRequestPresenter$setWalletData$1 = new Bip70PaymentInfoRequestPresenter$setWalletData$1(this.this$0, this.$wallet, continuation);
        bip70PaymentInfoRequestPresenter$setWalletData$1.f275p$ = (CoroutineScope) obj;
        return bip70PaymentInfoRequestPresenter$setWalletData$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Bip70PaymentInfoRequestPresenter$setWalletData$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        WalletId pri_key;
        WalletId id;
        Network network;
        DerivationPath path;
        Object walletUtxos;
        Coin coin;
        boolean z;
        String str;
        CredentialId credentialId;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f275p$;
            pri_key = this.$wallet.getPri_key();
            id = this.$wallet.getId();
            network = this.$wallet.getNetwork();
            path = this.$wallet.getPath();
            CredentialId credentialId2 = this.$wallet.getCredentialId();
            String name = this.$wallet.getName();
            Coin coin2 = this.$wallet.getCoin();
            boolean isMultiSig = this.$wallet.isMultiSig();
            UtxoRepository access$getUtxoService$p = this.this$0.utxoService;
            Object value = this.this$0.getSelectedWalletId().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "selectedWalletId.value!!");
            WalletId walletId = (WalletId) value;
            this.L$0 = pri_key;
            this.L$1 = id;
            this.L$2 = network;
            this.L$3 = path;
            this.L$4 = credentialId2;
            this.L$5 = name;
            this.L$6 = coin2;
            this.Z$0 = isMultiSig;
            this.label = 1;
            walletUtxos = access$getUtxoService$p.getWalletUtxos(walletId, this);
            if (walletUtxos == coroutine_suspended) {
                return coroutine_suspended;
            }
            coin = coin2;
            z = isMultiSig;
            str = name;
            credentialId = credentialId2;
        } else if (i == 1) {
            boolean z2 = this.Z$0;
            Coin coin3 = (Coin) this.L$6;
            String str2 = (String) this.L$5;
            CredentialId credentialId3 = (CredentialId) this.L$4;
            path = (DerivationPath) this.L$3;
            network = (Network) this.L$2;
            id = (WalletId) this.L$1;
            pri_key = (WalletId) this.L$0;
            ResultKt.throwOnFailure(obj);
            z = z2;
            coin = coin3;
            str = str2;
            credentialId = credentialId3;
            walletUtxos = obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        SendWalletData sendWalletData = new SendWalletData(pri_key, id, network, path, credentialId, str, coin, z, (WalletUtxos) walletUtxos, null);
        return sendWalletData;
    }
}
