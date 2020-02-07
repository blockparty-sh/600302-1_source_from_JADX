package com.bitcoin.mwallet.core.services.notification;

import com.bitcoin.mwallet.core.utils.SignedStub.SignedRequest;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\fJ%\u0010\r\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "", "registerRegion", "", "signedRequest", "", "Lcom/bitcoin/mwallet/core/utils/SignedStub$SignedRequest;", "languageCode", "", "(Ljava/util/Set;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribe", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unSubscribe", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NotificationService.kt */
public interface NotificationService {
    @Nullable
    Object registerRegion(@NotNull Set<SignedRequest> set, @NotNull String str, @NotNull Continuation<? super Boolean> continuation);

    @Nullable
    Object subscribe(@NotNull Map<SignedRequest, String> map, @NotNull Continuation<? super Boolean> continuation);

    @Nullable
    Object unSubscribe(@NotNull Map<SignedRequest, String> map, @NotNull Continuation<? super Boolean> continuation);
}
