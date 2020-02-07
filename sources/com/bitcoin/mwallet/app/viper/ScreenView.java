package com.bitcoin.mwallet.app.viper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.LayoutRes;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.FragmentKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.utils.MeasureKt;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 3*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0010\b\u0001\u0010\u0003*\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\u00020\u0006:\u00013B\u001d\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0002\u0010\u000bJ&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u001a\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u001d\u0010+\u001a\u00020)\"\b\b\u0002\u0010,*\u00020-2\u0006\u0010.\u001a\u0002H,¢\u0006\u0002\u0010/J\u001d\u00100\u001a\u00020)\"\b\b\u0002\u0010,*\u0002012\u0006\u0010.\u001a\u0002H,¢\u0006\u0002\u00102R\u001c\u0010\f\u001a\u00028\u0000X.¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00028\u0001X.¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u00064"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/viper/ScreenView;", "BUILDER", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "PRESENTER", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "Landroidx/fragment/app/Fragment;", "fragment", "", "builderClass", "Lkotlin/reflect/KClass;", "(ILkotlin/reflect/KClass;)V", "builder", "getBuilder", "()Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "setBuilder", "(Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;)V", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "getFragment", "()I", "presenter", "getPresenter", "()Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "setPresenter", "(Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;)V", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "savedStateViewModelFactory", "Landroidx/lifecycle/SavedStateVMFactory;", "getSavedStateViewModelFactory", "()Landroidx/lifecycle/SavedStateVMFactory;", "setSavedStateViewModelFactory", "(Landroidx/lifecycle/SavedStateVMFactory;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setUpCloseOnBackHandler", "P", "Lcom/bitcoin/mwallet/app/viper/PresenterWithCloseOnBackHandler;", "backHandlerPresenter", "(Lcom/bitcoin/mwallet/app/viper/PresenterWithCloseOnBackHandler;)V", "setUpFinishOnBackHandler", "Lcom/bitcoin/mwallet/app/viper/PresenterWithFinishOnBackHandler;", "(Lcom/bitcoin/mwallet/app/viper/PresenterWithFinishOnBackHandler;)V", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScreenView.kt */
public abstract class ScreenView<BUILDER extends ScreenBuilderBase, PRESENTER extends ScreenPresenter<? extends RouterBase>> extends Fragment {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;
    @NotNull
    protected BUILDER builder;
    /* access modifiers changed from: private */
    public final KClass<BUILDER> builderClass;
    private final int fragment;
    @NotNull
    public PRESENTER presenter;
    @Nullable
    private SavedStateVMFactory savedStateViewModelFactory;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0004\u0018\u0001H\u0004\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\b¢\u0006\u0002\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/viper/ScreenView$Companion;", "", "()V", "findChildIn", "T", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ScreenView.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final <T> T findChildIn(Fragment fragment) {
            String str;
            T t;
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "fragment.childFragmentManager");
            List fragments = childFragmentManager.getFragments();
            Intrinsics.checkExpressionValueIsNotNull(fragments, "fragment.childFragmentManager.fragments");
            Iterator it = fragments.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                str = ExifInterface.GPS_DIRECTION_TRUE;
                if (!hasNext) {
                    t = null;
                    break;
                }
                t = it.next();
                Fragment fragment2 = (Fragment) t;
                Intrinsics.reifiedOperationMarker(3, str);
                if (fragment2 instanceof Object) {
                    break;
                }
            }
            Intrinsics.reifiedOperationMarker(2, str);
            return (Object) t;
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

    /* access modifiers changed from: protected */
    public final int getFragment() {
        return this.fragment;
    }

    public ScreenView(@LayoutRes int i, @NotNull KClass<BUILDER> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "builderClass");
        this.fragment = i;
        this.builderClass = kClass;
    }

    @NotNull
    public final PRESENTER getPresenter() {
        PRESENTER presenter2 = this.presenter;
        if (presenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return presenter2;
    }

    public final void setPresenter(@NotNull PRESENTER presenter2) {
        Intrinsics.checkParameterIsNotNull(presenter2, "<set-?>");
        this.presenter = presenter2;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final BUILDER getBuilder() {
        BUILDER builder2 = this.builder;
        if (builder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        return builder2;
    }

    /* access modifiers changed from: protected */
    public final void setBuilder(@NotNull BUILDER builder2) {
        Intrinsics.checkParameterIsNotNull(builder2, "<set-?>");
        this.builder = builder2;
    }

    @Nullable
    public final SavedStateVMFactory getSavedStateViewModelFactory() {
        return this.savedStateViewModelFactory;
    }

    public final void setSavedStateViewModelFactory(@Nullable SavedStateVMFactory savedStateVMFactory) {
        this.savedStateViewModelFactory = savedStateVMFactory;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(this.fragment, viewGroup, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        ViewModelProvider viewModelProvider;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        SavedStateVMFactory savedStateVMFactory = this.savedStateViewModelFactory;
        if (savedStateVMFactory != null) {
            viewModelProvider = new ViewModelProvider((ViewModelStoreOwner) this, (Factory) savedStateVMFactory);
        } else {
            viewModelProvider = ViewModelProviders.m16of((Fragment) this);
            Intrinsics.checkExpressionValueIsNotNull(viewModelProvider, "ViewModelProviders.of(this)");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ViewModelProvider.get(");
        sb.append(this.builderClass.getSimpleName());
        sb.append('}');
        Object logDuration = MeasureKt.logDuration(sb.toString(), new ScreenView$onViewCreated$builder$1(this, viewModelProvider));
        Intrinsics.checkExpressionValueIsNotNull(logDuration, "logDuration(\"ViewModelPr…lderClass.java)\n        }");
        ScreenBuilderBase screenBuilderBase = (ScreenBuilderBase) logDuration;
        screenBuilderBase.setNavController(FragmentKt.findNavController(this));
        PRESENTER presenter2 = screenBuilderBase.getPresenter();
        if (presenter2 != null) {
            this.presenter = (ScreenPresenter) presenter2;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type PRESENTER");
    }

    public final <P extends PresenterWithCloseOnBackHandler> void setUpCloseOnBackHandler(@NotNull P p) {
        Intrinsics.checkParameterIsNotNull(p, "backHandlerPresenter");
        LifecycleOwner lifecycleOwner = this;
        p.getBackHandler().getShouldClose().observe(lifecycleOwner, new ScreenView$setUpCloseOnBackHandler$1(this));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            OnBackPressedDispatcher onBackPressedDispatcher = activity.getOnBackPressedDispatcher();
            if (onBackPressedDispatcher != null) {
                onBackPressedDispatcher.addCallback(lifecycleOwner, p.getBackHandler());
            }
        }
    }

    public final <P extends PresenterWithFinishOnBackHandler> void setUpFinishOnBackHandler(@NotNull P p) {
        Intrinsics.checkParameterIsNotNull(p, "backHandlerPresenter");
        LifecycleOwner lifecycleOwner = this;
        p.getBackHandler().getShouldFinish().observe(lifecycleOwner, new ScreenView$setUpFinishOnBackHandler$1(this));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            OnBackPressedDispatcher onBackPressedDispatcher = activity.getOnBackPressedDispatcher();
            if (onBackPressedDispatcher != null) {
                onBackPressedDispatcher.addCallback(lifecycleOwner, p.getBackHandler());
            }
        }
    }
}
