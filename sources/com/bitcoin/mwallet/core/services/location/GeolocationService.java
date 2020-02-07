package com.bitcoin.mwallet.core.services.location;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/location/GeolocationService;", "", "getBuyBitcoinProvider", "Lcom/bitcoin/mwallet/core/services/location/GeolocationResponse;", "ipAddress", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GeolocationService.kt */
public interface GeolocationService {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: GeolocationService.kt */
    public static final class DefaultImpls {
        @Nullable
        public static /* synthetic */ Object getBuyBitcoinProvider$default(GeolocationService geolocationService, String str, Continuation continuation, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = null;
                }
                return geolocationService.getBuyBitcoinProvider(str, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBuyBitcoinProvider");
        }
    }

    @Nullable
    Object getBuyBitcoinProvider(@Nullable String str, @NotNull Continuation<? super GeolocationResponse> continuation);
}
