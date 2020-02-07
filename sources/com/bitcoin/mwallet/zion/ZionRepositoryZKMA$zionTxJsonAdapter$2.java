package com.bitcoin.mwallet.zion;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi.Builder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/bitcoin/mwallet/zion/ZionTransaction;", "kotlin.jvm.PlatformType", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$zionTxJsonAdapter$2 extends Lambda implements Function0<JsonAdapter<ZionTransaction>> {
    public static final ZionRepositoryZKMA$zionTxJsonAdapter$2 INSTANCE = new ZionRepositoryZKMA$zionTxJsonAdapter$2();

    ZionRepositoryZKMA$zionTxJsonAdapter$2() {
        super(0);
    }

    public final JsonAdapter<ZionTransaction> invoke() {
        return new Builder().build().adapter(ZionTransaction.class);
    }
}
