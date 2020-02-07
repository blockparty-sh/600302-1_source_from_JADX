package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import com.bitcoin.mwallet.core.models.slp.Slp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "<anonymous parameter 0>", "", "rawTokens", "invoke", "(Ljava/lang/Boolean;Ljava/util/List;)Ljava/util/List;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetsPresenter.kt */
final class SLPAssetsPresenter$getTokens$1 extends Lambda implements Function2<Boolean, List<? extends Slp>, List<? extends Slp>> {
    public static final SLPAssetsPresenter$getTokens$1 INSTANCE = new SLPAssetsPresenter$getTokens$1();

    SLPAssetsPresenter$getTokens$1() {
        super(2);
    }

    @Nullable
    public final List<Slp> invoke(@Nullable Boolean bool, @Nullable List<Slp> list) {
        if (list == null) {
            return null;
        }
        return list;
    }
}
