package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.Hex;
import com.bitcoin.bitcoink.util.ByteUtils;
import com.bitcoin.mwallet.core.utils.MeasureKt;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Lcom/htc/htcwalletsdk/Export/HtcWalletSdkManager;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$tryInitManager$2 extends Lambda implements Function0<HtcWalletSdkManager> {
    final /* synthetic */ ZionRepositoryZKMA this$0;

    ZionRepositoryZKMA$tryInitManager$2(ZionRepositoryZKMA zionRepositoryZKMA) {
        this.this$0 = zionRepositoryZKMA;
        super(0);
    }

    @Nullable
    public final HtcWalletSdkManager invoke() {
        return (HtcWalletSdkManager) MeasureKt.logDuration("Zion manager initialization", new Function0<HtcWalletSdkManager>(this) {
            final /* synthetic */ ZionRepositoryZKMA$tryInitManager$2 this$0;

            {
                this.this$0 = r1;
            }

            @Nullable
            public final HtcWalletSdkManager invoke() {
                try {
                    HtcWalletSdkManager instance = HtcWalletSdkManager.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(instance, "HtcWalletSdkManager.getInstance()");
                    int init = instance.init(this.this$0.this$0.application.getApplicationContext());
                    StringBuilder sb = new StringBuilder();
                    sb.append("Init code ");
                    sb.append(init);
                    Timber.m426d(sb.toString(), new Object[0]);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Module version ");
                    sb2.append(instance.getModuleVersion());
                    Timber.m426d(sb2.toString(), new Object[0]);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("API version ");
                    sb3.append(instance.getApiVersion());
                    Timber.m426d(sb3.toString(), new Object[0]);
                    if (init != 0) {
                        return null;
                    }
                    String apiVersion = instance.getApiVersion();
                    Intrinsics.checkExpressionValueIsNotNull(apiVersion, "manager.apiVersion");
                    if (ByteUtils.INSTANCE.toInt(Hex.Companion.decode(StringsKt.padStart((String) CollectionsKt.last(StringsKt.split$default((CharSequence) apiVersion, new String[]{"."}, false, 0, 6, (Object) null)), 8, '0'))) >= 16842827) {
                        return instance;
                    }
                    return null;
                } catch (RuntimeException e) {
                    Timber.m430e(e);
                    return null;
                }
            }
        });
    }
}
