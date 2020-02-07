package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1$1$invokeSuspend$$inlined$sortedBy$1 */
/* compiled from: Comparisons.kt */
public final class C1111x627b516f<T> implements Comparator<T> {
    public final int compare(T t, T t2) {
        return ComparisonsKt.compareValues(((SLPAssetRow) t).getToken().getName(), ((SLPAssetRow) t2).getToken().getName());
    }
}
