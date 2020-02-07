package com.bitcoin.mwallet.app.viper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u001d\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u001c\u0010\u000b\u001a\u00028\u0000X.¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u00028\u0001X.¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/viper/ComponentView;", "BUILDER", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "PRESENTER", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "Landroidx/fragment/app/Fragment;", "fragment", "", "builderClass", "Lkotlin/reflect/KClass;", "(ILkotlin/reflect/KClass;)V", "builder", "getBuilder", "()Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "setBuilder", "(Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;)V", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "getFragment", "()I", "presenter", "getPresenter", "()Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "setPresenter", "(Lcom/bitcoin/mwallet/app/viper/PresenterBase;)V", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ComponentView.kt */
public abstract class ComponentView<BUILDER extends ComponentBuilderBase, PRESENTER extends PresenterBase> extends Fragment {
    private HashMap _$_findViewCache;
    @NotNull
    protected BUILDER builder;
    private final KClass<BUILDER> builderClass;
    private final int fragment;
    @NotNull
    protected PRESENTER presenter;

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

    public ComponentView(@LayoutRes int i, @NotNull KClass<BUILDER> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "builderClass");
        this.fragment = i;
        this.builderClass = kClass;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final PRESENTER getPresenter() {
        PRESENTER presenter2 = this.presenter;
        if (presenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return presenter2;
    }

    /* access modifiers changed from: protected */
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
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(this.fragment, viewGroup, false);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        BUILDER builder2 = ViewModelProviders.m16of((Fragment) this).get(JvmClassMappingKt.getJavaClass(this.builderClass));
        Intrinsics.checkExpressionValueIsNotNull(builder2, "ViewModelProviders.of(this).get(builderClass.java)");
        this.builder = (ComponentBuilderBase) builder2;
        BUILDER builder3 = this.builder;
        if (builder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        PRESENTER presenter2 = builder3.getPresenter();
        if (presenter2 != null) {
            this.presenter = presenter2;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type PRESENTER");
    }
}
