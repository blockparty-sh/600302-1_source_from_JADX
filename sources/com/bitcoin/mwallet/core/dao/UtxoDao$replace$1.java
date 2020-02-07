package com.bitcoin.mwallet.core.dao;

import com.bitcoin.mwallet.core.dao.UtxoDao.DefaultImpls;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH@ø\u0001\u0000"}, mo37405d2 = {"replace", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "values", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.dao.UtxoDao$DefaultImpls", mo38000f = "UtxoDao.kt", mo38001i = {0, 0, 0, 1, 1, 1}, mo38002l = {34, 35}, mo38003m = "replace", mo38004n = {"$this", "walletId", "values", "$this", "walletId", "values"}, mo38005s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: UtxoDao.kt */
final class UtxoDao$replace$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ UtxoDao this$0;

    UtxoDao$replace$1(UtxoDao utxoDao, Continuation continuation) {
        this.this$0 = utxoDao;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        UtxoDao utxoDao = this.this$0;
        return DefaultImpls.replace(null, null, null, this);
    }
}
