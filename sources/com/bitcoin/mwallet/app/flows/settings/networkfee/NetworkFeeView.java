package com.bitcoin.mwallet.app.flows.settings.networkfee;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.PreferenceManager;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/networkfee/NetworkFeeView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/settings/networkfee/NetworkFeeBuilder;", "Lcom/bitcoin/mwallet/app/flows/settings/networkfee/NetworkFeePresenter;", "()V", "bindActions", "", "feeSelected", "fee", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NetworkFeeView.kt */
public final class NetworkFeeView extends ScreenView<NetworkFeeBuilder, NetworkFeePresenter> {
    private HashMap _$_findViewCache;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[NetworkFeeLevel.values().length];

        static {
            $EnumSwitchMapping$0[NetworkFeeLevel.NORMAL.ordinal()] = 1;
            $EnumSwitchMapping$0[NetworkFeeLevel.PRIORITY.ordinal()] = 2;
            $EnumSwitchMapping$0[NetworkFeeLevel.ECONOMY.ordinal()] = 3;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public NetworkFeeView() {
        super(C1018R.layout.fragment_screen_settings_networkfee, Reflection.getOrCreateKotlinClass(NetworkFeeBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        bindActions();
    }

    public final void feeSelected(@NotNull NetworkFeeLevel networkFeeLevel) {
        Intrinsics.checkParameterIsNotNull(networkFeeLevel, "fee");
        View view = getView();
        ImageView imageView = null;
        ImageView imageView2 = view != null ? (ImageView) view.findViewById(C1018R.C1021id.normalFeeImageView) : null;
        View view2 = getView();
        ImageView imageView3 = view2 != null ? (ImageView) view2.findViewById(C1018R.C1021id.priorityFeeImageView) : null;
        View view3 = getView();
        if (view3 != null) {
            imageView = (ImageView) view3.findViewById(C1018R.C1021id.economyFeeImageView);
        }
        if (imageView2 != null) {
            imageView2.setBackgroundResource(C1018R.C1020drawable.ic_radio_unselected);
        }
        if (imageView3 != null) {
            imageView3.setBackgroundResource(C1018R.C1020drawable.ic_radio_unselected);
        }
        if (imageView != null) {
            imageView.setBackgroundResource(C1018R.C1020drawable.ic_radio_unselected);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[networkFeeLevel.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3 && imageView != null) {
                    imageView.setBackgroundResource(C1018R.C1020drawable.ic_radio_selected);
                }
            } else if (imageView3 != null) {
                imageView3.setBackgroundResource(C1018R.C1020drawable.ic_radio_selected);
            }
        } else if (imageView2 != null) {
            imageView2.setBackgroundResource(C1018R.C1020drawable.ic_radio_selected);
        }
    }

    public final void bindActions() {
        View view = getView();
        ImageView imageView = null;
        ImageView imageView2 = view != null ? (ImageView) view.findViewById(C1018R.C1021id.normalFeeImageView) : null;
        View view2 = getView();
        ImageView imageView3 = view2 != null ? (ImageView) view2.findViewById(C1018R.C1021id.priorityFeeImageView) : null;
        View view3 = getView();
        if (view3 != null) {
            imageView = (ImageView) view3.findViewById(C1018R.C1021id.economyFeeImageView);
        }
        String string = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(SharedPreference.NETWORK_FEE.getKey(), "Normal");
        if (Intrinsics.areEqual((Object) string, (Object) NetworkFeeLevel.NORMAL.name())) {
            if (imageView2 != null) {
                imageView2.setBackgroundResource(C1018R.C1020drawable.ic_radio_selected);
            }
        } else if (Intrinsics.areEqual((Object) string, (Object) NetworkFeeLevel.PRIORITY.name())) {
            if (imageView3 != null) {
                imageView3.setBackgroundResource(C1018R.C1020drawable.ic_radio_selected);
            }
        } else if (Intrinsics.areEqual((Object) string, (Object) NetworkFeeLevel.ECONOMY.name())) {
            if (imageView != null) {
                imageView.setBackgroundResource(C1018R.C1020drawable.ic_radio_selected);
            }
        } else if (imageView2 != null) {
            imageView2.setBackgroundResource(C1018R.C1020drawable.ic_radio_selected);
        }
        View view4 = getView();
        if (view4 != null) {
            TextView textView = (TextView) view4.findViewById(C1018R.C1021id.learnMoreButton);
            if (textView != null) {
                textView.setOnClickListener(new NetworkFeeView$bindActions$1(this));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            LinearLayout linearLayout = (LinearLayout) view5.findViewById(C1018R.C1021id.priorityFeeCell);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new NetworkFeeView$bindActions$2(this));
            }
        }
        View view6 = getView();
        if (view6 != null) {
            LinearLayout linearLayout2 = (LinearLayout) view6.findViewById(C1018R.C1021id.normalFeeCell);
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new NetworkFeeView$bindActions$3(this));
            }
        }
        View view7 = getView();
        if (view7 != null) {
            LinearLayout linearLayout3 = (LinearLayout) view7.findViewById(C1018R.C1021id.economyFeeCell);
            if (linearLayout3 != null) {
                linearLayout3.setOnClickListener(new NetworkFeeView$bindActions$4(this));
            }
        }
    }
}
