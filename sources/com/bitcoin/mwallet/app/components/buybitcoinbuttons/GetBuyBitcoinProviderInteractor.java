package com.bitcoin.mwallet.app.components.buybitcoinbuttons;

import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider;
import com.bitcoin.mwallet.core.services.location.GeolocationService;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\nR\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/GetBuyBitcoinProviderInteractor;", "", "geolocationService", "Lcom/bitcoin/mwallet/core/services/location/GeolocationService;", "(Lcom/bitcoin/mwallet/core/services/location/GeolocationService;)V", "<set-?>", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/BuyBitcoinProvider;", "buyProvider", "getBuyProvider", "()Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/BuyBitcoinProvider;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetBuyBitcoinProviderInteractor.kt */
public final class GetBuyBitcoinProviderInteractor {
    @Nullable
    private BuyBitcoinProvider buyProvider;
    private final GeolocationService geolocationService;

    public GetBuyBitcoinProviderInteractor(@NotNull GeolocationService geolocationService2) {
        Intrinsics.checkParameterIsNotNull(geolocationService2, "geolocationService");
        this.geolocationService = geolocationService2;
    }

    @Nullable
    public final BuyBitcoinProvider getBuyProvider() {
        return this.buyProvider;
    }

    @Nullable
    public final Object getBuyProvider(@NotNull Continuation<? super BuyBitcoinProvider> continuation) {
        return BuyBitcoinProvider.WEBSITE;
    }
}
