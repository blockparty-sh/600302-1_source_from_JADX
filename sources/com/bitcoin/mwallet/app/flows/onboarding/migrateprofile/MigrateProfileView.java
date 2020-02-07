package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus.Migration;
import com.bitcoin.mwallet.app.viper.PresenterWithCloseOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0010\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileBuilder;", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfilePresenter;", "()V", "migrateThreshold", "", "getMigrateThreshold", "()I", "setMigrateThreshold", "(I)V", "openPopupIn", "", "getOpenPopupIn", "()J", "timer", "com/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileView$timer$1", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileView$timer$1;", "bindDataObservers", "", "controlAnimation", "status", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileView.kt */
public final class MigrateProfileView extends ScreenView<MigrateProfileBuilder, MigrateProfilePresenter> {
    private HashMap _$_findViewCache;
    private int migrateThreshold;
    private final long openPopupIn = 5000;
    private final MigrateProfileView$timer$1 timer;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Migration.values().length];

        static {
            $EnumSwitchMapping$0[Migration.MIGRATING.ordinal()] = 1;
            $EnumSwitchMapping$0[Migration.DONE.ordinal()] = 2;
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

    public MigrateProfileView() {
        super(C1018R.layout.fragment_screen_onboarding_migration, Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class));
        MigrateProfileView$timer$1 migrateProfileView$timer$1 = new MigrateProfileView$timer$1(this, this.openPopupIn, 1000);
        this.timer = migrateProfileView$timer$1;
    }

    public final int getMigrateThreshold() {
        return this.migrateThreshold;
    }

    public final void setMigrateThreshold(int i) {
        this.migrateThreshold = i;
    }

    public final long getOpenPopupIn() {
        return this.openPopupIn;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ((MigrateProfilePresenter) getPresenter()).beginMigration();
        setUpCloseOnBackHandler((PresenterWithCloseOnBackHandler) getPresenter());
        bindDataObservers();
    }

    private final void bindDataObservers() {
        View view = getView();
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(C1018R.C1021id.migrateImage);
            if (imageView != null) {
                imageView.setOnClickListener(new MigrateProfileView$bindDataObservers$1(this));
            }
        }
        ((MigrateProfilePresenter) getPresenter()).getMigrationStatus().observe(getViewLifecycleOwner(), new MigrateProfileView$bindDataObservers$2(this));
        ((MigrateProfilePresenter) getPresenter()).getMigrationComplete().observe(getViewLifecycleOwner(), new MigrateProfileView$bindDataObservers$3(this));
    }

    /* access modifiers changed from: private */
    public final void controlAnimation(MigrationStatus migrationStatus) {
        int i = WhenMappings.$EnumSwitchMapping$0[migrationStatus.getStatus().ordinal()];
        String str = "loadingAnimation";
        if (i == 1) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C1018R.C1021id.loadingAnimation);
            Intrinsics.checkExpressionValueIsNotNull(lottieAnimationView, str);
            lottieAnimationView.setVisibility(0);
            this.timer.start();
        } else if (i == 2) {
            LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C1018R.C1021id.loadingAnimation);
            Intrinsics.checkExpressionValueIsNotNull(lottieAnimationView2, str);
            lottieAnimationView2.setVisibility(8);
            this.timer.cancel();
        }
    }
}
