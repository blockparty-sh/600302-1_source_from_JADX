package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.dao.MerchantDao;
import com.bitcoin.mwallet.core.models.merchant.Merchant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/MerchantRepository;", "", "merchantDao", "Lcom/bitcoin/mwallet/core/dao/MerchantDao;", "(Lcom/bitcoin/mwallet/core/dao/MerchantDao;)V", "getMerchantInfo", "Lcom/bitcoin/mwallet/core/models/merchant/Merchant;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "(Lcom/bitcoin/bitcoink/tx/TxId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MerchantRepository.kt */
public final class MerchantRepository {
    /* access modifiers changed from: private */
    public final MerchantDao merchantDao;

    public MerchantRepository(@NotNull MerchantDao merchantDao2) {
        Intrinsics.checkParameterIsNotNull(merchantDao2, "merchantDao");
        this.merchantDao = merchantDao2;
    }

    @Nullable
    public final Object getMerchantInfo(@NotNull TxId txId, @NotNull Continuation<? super Merchant> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MerchantRepository$getMerchantInfo$2(this, txId, null), continuation);
    }
}
