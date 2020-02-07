package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0002\u0010\u000eJ\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0010J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R&\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006+"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "discoverContentInteractor", "Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetRouter;)V", "assets", "", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "getAssets", "()Ljava/util/List;", "setAssets", "(Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "queriedAssets", "Landroidx/lifecycle/MutableLiveData;", "getQueriedAssets", "()Landroidx/lifecycle/MutableLiveData;", "setQueriedAssets", "(Landroidx/lifecycle/MutableLiveData;)V", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getAsset", "bitcoinUriContent", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "preferredWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getVerifiedAssets", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "setQuery", "", "query", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetPresenter.kt */
public final class SelectSendingAssetPresenter extends ScreenPresenter<SelectSendingAssetRouter> {
    @Nullable
    private List<SendableAssetModel> assets;
    @NotNull
    private final Context context;
    /* access modifiers changed from: private */
    public final DiscoverContentInteractor discoverContentInteractor;
    @NotNull
    private MutableLiveData<List<SendableAssetModel>> queriedAssets = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final SlpRepository slpRepository;
    @NotNull
    private final VerifiedTokenInteractor verifiedTokenInteractor;

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        return this.verifiedTokenInteractor;
    }

    public SelectSendingAssetPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull SlpRepository slpRepository2, @NotNull DiscoverContentInteractor discoverContentInteractor2, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2, @NotNull SelectSendingAssetRouter selectSendingAssetRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(discoverContentInteractor2, "discoverContentInteractor");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        Intrinsics.checkParameterIsNotNull(selectSendingAssetRouter, "router");
        super(context2, selectSendingAssetRouter);
        this.context = context2;
        this.slpRepository = slpRepository2;
        this.discoverContentInteractor = discoverContentInteractor2;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
    }

    @Nullable
    public final List<SendableAssetModel> getAssets() {
        return this.assets;
    }

    public final void setAssets(@Nullable List<SendableAssetModel> list) {
        this.assets = list;
    }

    @NotNull
    public final MutableLiveData<List<SendableAssetModel>> getQueriedAssets() {
        return this.queriedAssets;
    }

    public final void setQueriedAssets(@NotNull MutableLiveData<List<SendableAssetModel>> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.queriedAssets = mutableLiveData;
    }

    @NotNull
    public final List<VerifiedToken> getVerifiedAssets() {
        return (List) BuildersKt__BuildersKt.runBlocking$default(null, new SelectSendingAssetPresenter$getVerifiedAssets$1(this, null), 1, null);
    }

    @NotNull
    public final List<SendableAssetModel> getAsset(@NotNull BitcoinUriContent bitcoinUriContent, @Nullable WalletId walletId) {
        List list;
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "bitcoinUriContent");
        if (bitcoinUriContent.isSlp()) {
            if (walletId != null) {
                Iterable iterable = (Iterable) BuildersKt__BuildersKt.runBlocking$default(null, new SelectSendingAssetPresenter$getAsset$1(this, walletId, null), 1, null);
                Collection arrayList = new ArrayList();
                for (Object next : iterable) {
                    if (((BigDecimal) BuildersKt__BuildersKt.runBlocking$default(null, new SelectSendingAssetPresenter$getAsset$$inlined$filter$lambda$1((Slp) next, null, this, walletId), 1, null)).compareTo(BigDecimal.ZERO) > 0) {
                        arrayList.add(next);
                    }
                }
                list = (List) arrayList;
            } else {
                Iterable iterable2 = (Iterable) BuildersKt__BuildersKt.runBlocking$default(null, new SelectSendingAssetPresenter$getAsset$3(this, null), 1, null);
                Collection arrayList2 = new ArrayList();
                for (Object next2 : iterable2) {
                    if (((BigDecimal) BuildersKt__BuildersKt.runBlocking$default(null, new SelectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2((Slp) next2, null, this), 1, null)).compareTo(BigDecimal.ZERO) > 0) {
                        arrayList2.add(next2);
                    }
                }
                list = (List) arrayList2;
            }
            Iterable<Slp> iterable3 = list;
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
            for (Slp slp : iterable3) {
                arrayList3.add(new SendableAssetModel(slp.getName(), slp.getTicker(), Coin.BCH, slp.getTokenId()));
            }
            return (List) arrayList3;
        }
        Coin[] values = Coin.values();
        Collection arrayList4 = new ArrayList(values.length);
        for (Coin coin : values) {
            arrayList4.add(new SendableAssetModel(coin.name(), coin.getTicker(), coin, null));
        }
        return (List) arrayList4;
    }

    /* JADX INFO: used method not loaded: kotlin.text.StringsKt__StringsKt.contains$default(java.lang.CharSequence, java.lang.CharSequence, boolean, int, java.lang.Object):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006c, code lost:
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, r8, false, 2, (java.lang.Object) null) != false) goto L_0x0075;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setQuery(@org.jetbrains.annotations.NotNull java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = "query"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.lang.String r12 = r12.toLowerCase()
            java.lang.String r0 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r0)
            java.lang.String r1 = ""
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r1)
            if (r1 == 0) goto L_0x001e
            java.util.List<com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel> r12 = r11.assets
            androidx.lifecycle.MutableLiveData<java.util.List<com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel>> r0 = r11.queriedAssets
            r0.setValue(r12)
            goto L_0x008a
        L_0x001e:
            java.util.List<com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel> r1 = r11.assets
            r2 = 0
            if (r1 == 0) goto L_0x0085
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r1 = r1.iterator()
        L_0x0030:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0082
            java.lang.Object r4 = r1.next()
            r5 = r4
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel r5 = (com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel) r5
            java.lang.String r6 = r5.getName()
            java.lang.String r7 = "null cannot be cast to non-null type java.lang.String"
            if (r6 == 0) goto L_0x007c
            java.lang.String r6 = r6.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r8 = r12
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9 = 2
            r10 = 0
            boolean r6 = kotlin.text.StringsKt.contains$default(r6, r8, r10, r9, r2)
            if (r6 != 0) goto L_0x0075
            java.lang.String r5 = r5.getTicker()
            if (r5 == 0) goto L_0x006f
            java.lang.String r5 = r5.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            boolean r5 = kotlin.text.StringsKt.contains$default(r5, r8, r10, r9, r2)
            if (r5 == 0) goto L_0x0076
            goto L_0x0075
        L_0x006f:
            kotlin.TypeCastException r12 = new kotlin.TypeCastException
            r12.<init>(r7)
            throw r12
        L_0x0075:
            r10 = 1
        L_0x0076:
            if (r10 == 0) goto L_0x0030
            r3.add(r4)
            goto L_0x0030
        L_0x007c:
            kotlin.TypeCastException r12 = new kotlin.TypeCastException
            r12.<init>(r7)
            throw r12
        L_0x0082:
            r2 = r3
            java.util.List r2 = (java.util.List) r2
        L_0x0085:
            androidx.lifecycle.MutableLiveData<java.util.List<com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel>> r12 = r11.queriedAssets
            r12.setValue(r2)
        L_0x008a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SelectSendingAssetPresenter.setQuery(java.lang.String):void");
    }
}
