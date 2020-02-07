package androidx.core.view;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.annotation.C0045Px;
import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a2\u0010\u0019\u001a\u00020\u001a*\u00020\u00032#\b\u0004\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u001a0\u001cH\b\u001a2\u0010 \u001a\u00020\u001a*\u00020\u00032#\b\u0004\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u001a0\u001cH\b\u001a2\u0010!\u001a\u00020\"*\u00020\u00032#\b\u0004\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u001a0\u001cH\b\u001a\u0014\u0010#\u001a\u00020$*\u00020\u00032\b\b\u0002\u0010%\u001a\u00020&\u001a%\u0010'\u001a\u00020(*\u00020\u00032\u0006\u0010)\u001a\u00020*2\u000e\b\u0004\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0+H\b\u001a%\u0010,\u001a\u00020(*\u00020\u00032\u0006\u0010)\u001a\u00020*2\u000e\b\u0004\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0+H\b\u001a\u0017\u0010-\u001a\u00020\u001a*\u00020\u00032\b\b\u0001\u0010.\u001a\u00020\fH\b\u001a7\u0010/\u001a\u00020\u001a\"\n\b\u0000\u00100\u0018\u0001*\u000201*\u00020\u00032\u0017\u00102\u001a\u0013\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u00020\u001a0\u001c¢\u0006\u0002\b3H\b¢\u0006\u0002\b4\u001a&\u0010/\u001a\u00020\u001a*\u00020\u00032\u0017\u00102\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u001a0\u001c¢\u0006\u0002\b3H\b\u001a5\u00105\u001a\u00020\u001a*\u00020\u00032\b\b\u0003\u00106\u001a\u00020\f2\b\b\u0003\u00107\u001a\u00020\f2\b\b\u0003\u00108\u001a\u00020\f2\b\b\u0003\u00109\u001a\u00020\fH\b\u001a5\u0010:\u001a\u00020\u001a*\u00020\u00032\b\b\u0003\u0010;\u001a\u00020\f2\b\b\u0003\u00107\u001a\u00020\f2\b\b\u0003\u0010<\u001a\u00020\f2\b\b\u0003\u00109\u001a\u00020\fH\b\"*\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018Æ\u0002@Æ\u0002X\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006\"*\u0010\u0007\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018Æ\u0002@Æ\u0002X\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\u0004\"\u0004\b\b\u0010\u0006\"*\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018Æ\u0002@Æ\u0002X\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0004\"\u0004\b\n\u0010\u0006\"\u0016\u0010\u000b\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u0016\u0010\u000f\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e\"\u0016\u0010\u0011\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000e\"\u0016\u0010\u0013\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000e\"\u0016\u0010\u0015\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000e\"\u0016\u0010\u0017\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000e¨\u0006="}, mo37405d2 = {"value", "", "isGone", "Landroid/view/View;", "(Landroid/view/View;)Z", "setGone", "(Landroid/view/View;Z)V", "isInvisible", "setInvisible", "isVisible", "setVisible", "marginBottom", "", "getMarginBottom", "(Landroid/view/View;)I", "marginEnd", "getMarginEnd", "marginLeft", "getMarginLeft", "marginRight", "getMarginRight", "marginStart", "getMarginStart", "marginTop", "getMarginTop", "doOnLayout", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "doOnNextLayout", "doOnPreDraw", "Landroidx/core/view/OneShotPreDrawListener;", "drawToBitmap", "Landroid/graphics/Bitmap;", "config", "Landroid/graphics/Bitmap$Config;", "postDelayed", "Ljava/lang/Runnable;", "delayInMillis", "", "Lkotlin/Function0;", "postOnAnimationDelayed", "setPadding", "size", "updateLayoutParams", "T", "Landroid/view/ViewGroup$LayoutParams;", "block", "Lkotlin/ExtensionFunctionType;", "updateLayoutParamsTyped", "updatePadding", "left", "top", "right", "bottom", "updatePaddingRelative", "start", "end", "core-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: View.kt */
public final class ViewKt {
    public static final void doOnNextLayout(@NotNull View view, @NotNull Function1<? super View, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(view, "$this$doOnNextLayout");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        view.addOnLayoutChangeListener(new ViewKt$doOnNextLayout$1(function1));
    }

    public static final void doOnLayout(@NotNull View view, @NotNull Function1<? super View, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(view, "$this$doOnLayout");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        if (!ViewCompat.isLaidOut(view) || view.isLayoutRequested()) {
            view.addOnLayoutChangeListener(new ViewKt$doOnLayout$$inlined$doOnNextLayout$1(function1));
        } else {
            function1.invoke(view);
        }
    }

    @NotNull
    public static final OneShotPreDrawListener doOnPreDraw(@NotNull View view, @NotNull Function1<? super View, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(view, "$this$doOnPreDraw");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        OneShotPreDrawListener add = OneShotPreDrawListener.add(view, new ViewKt$doOnPreDraw$1(view, function1));
        Intrinsics.checkExpressionValueIsNotNull(add, "OneShotPreDrawListener.add(this) { action(this) }");
        return add;
    }

    @RequiresApi(17)
    public static /* synthetic */ void updatePaddingRelative$default(View view, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = view.getPaddingStart();
        }
        if ((i5 & 2) != 0) {
            i2 = view.getPaddingTop();
        }
        if ((i5 & 4) != 0) {
            i3 = view.getPaddingEnd();
        }
        if ((i5 & 8) != 0) {
            i4 = view.getPaddingBottom();
        }
        Intrinsics.checkParameterIsNotNull(view, "$this$updatePaddingRelative");
        view.setPaddingRelative(i, i2, i3, i4);
    }

    @RequiresApi(17)
    public static final void updatePaddingRelative(@NotNull View view, @C0045Px int i, @C0045Px int i2, @C0045Px int i3, @C0045Px int i4) {
        Intrinsics.checkParameterIsNotNull(view, "$this$updatePaddingRelative");
        view.setPaddingRelative(i, i2, i3, i4);
    }

    public static /* synthetic */ void updatePadding$default(View view, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = view.getPaddingLeft();
        }
        if ((i5 & 2) != 0) {
            i2 = view.getPaddingTop();
        }
        if ((i5 & 4) != 0) {
            i3 = view.getPaddingRight();
        }
        if ((i5 & 8) != 0) {
            i4 = view.getPaddingBottom();
        }
        Intrinsics.checkParameterIsNotNull(view, "$this$updatePadding");
        view.setPadding(i, i2, i3, i4);
    }

    public static final void updatePadding(@NotNull View view, @C0045Px int i, @C0045Px int i2, @C0045Px int i3, @C0045Px int i4) {
        Intrinsics.checkParameterIsNotNull(view, "$this$updatePadding");
        view.setPadding(i, i2, i3, i4);
    }

    public static final void setPadding(@NotNull View view, @C0045Px int i) {
        Intrinsics.checkParameterIsNotNull(view, "$this$setPadding");
        view.setPadding(i, i, i, i);
    }

    @NotNull
    public static final Runnable postDelayed(@NotNull View view, long j, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(view, "$this$postDelayed");
        Intrinsics.checkParameterIsNotNull(function0, "action");
        Runnable viewKt$postDelayed$runnable$1 = new ViewKt$postDelayed$runnable$1(function0);
        view.postDelayed(viewKt$postDelayed$runnable$1, j);
        return viewKt$postDelayed$runnable$1;
    }

    @RequiresApi(16)
    @NotNull
    public static final Runnable postOnAnimationDelayed(@NotNull View view, long j, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(view, "$this$postOnAnimationDelayed");
        Intrinsics.checkParameterIsNotNull(function0, "action");
        Runnable viewKt$postOnAnimationDelayed$runnable$1 = new ViewKt$postOnAnimationDelayed$runnable$1(function0);
        view.postOnAnimationDelayed(viewKt$postOnAnimationDelayed$runnable$1, j);
        return viewKt$postOnAnimationDelayed$runnable$1;
    }

    @NotNull
    public static /* synthetic */ Bitmap drawToBitmap$default(View view, Config config, int i, Object obj) {
        if ((i & 1) != 0) {
            config = Config.ARGB_8888;
        }
        return drawToBitmap(view, config);
    }

    @NotNull
    public static final Bitmap drawToBitmap(@NotNull View view, @NotNull Config config) {
        Intrinsics.checkParameterIsNotNull(view, "$this$drawToBitmap");
        Intrinsics.checkParameterIsNotNull(config, "config");
        if (ViewCompat.isLaidOut(view)) {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), config);
            Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(width, height, config)");
            Canvas canvas = new Canvas(createBitmap);
            canvas.translate(-((float) view.getScrollX()), -((float) view.getScrollY()));
            view.draw(canvas);
            return createBitmap;
        }
        throw new IllegalStateException("View needs to be laid out before calling drawToBitmap()");
    }

    public static final boolean isVisible(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isVisible");
        return view.getVisibility() == 0;
    }

    public static final void setVisible(@NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isVisible");
        view.setVisibility(z ? 0 : 8);
    }

    public static final boolean isInvisible(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isInvisible");
        return view.getVisibility() == 4;
    }

    public static final void setInvisible(@NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isInvisible");
        view.setVisibility(z ? 4 : 0);
    }

    public static final boolean isGone(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isGone");
        return view.getVisibility() == 8;
    }

    public static final void setGone(@NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isGone");
        view.setVisibility(z ? 8 : 0);
    }

    @JvmName(name = "updateLayoutParamsTyped")
    private static final <T extends LayoutParams> void updateLayoutParamsTyped(@NotNull View view, Function1<? super T, Unit> function1) {
        LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        LayoutParams layoutParams2 = layoutParams;
        function1.invoke(layoutParams2);
        view.setLayoutParams(layoutParams2);
    }

    public static final int getMarginLeft(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$marginLeft");
        LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof MarginLayoutParams)) {
            layoutParams = null;
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.leftMargin;
        }
        return 0;
    }

    public static final int getMarginTop(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$marginTop");
        LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof MarginLayoutParams)) {
            layoutParams = null;
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.topMargin;
        }
        return 0;
    }

    public static final int getMarginRight(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$marginRight");
        LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof MarginLayoutParams)) {
            layoutParams = null;
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.rightMargin;
        }
        return 0;
    }

    public static final int getMarginBottom(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$marginBottom");
        LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof MarginLayoutParams)) {
            layoutParams = null;
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.bottomMargin;
        }
        return 0;
    }

    public static final int getMarginStart(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$marginStart");
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            return MarginLayoutParamsCompat.getMarginStart((MarginLayoutParams) layoutParams);
        }
        return 0;
    }

    public static final int getMarginEnd(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$marginEnd");
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            return MarginLayoutParamsCompat.getMarginEnd((MarginLayoutParams) layoutParams);
        }
        return 0;
    }

    public static final void updateLayoutParams(@NotNull View view, @NotNull Function1<? super LayoutParams, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(view, "$this$updateLayoutParams");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            function1.invoke(layoutParams);
            view.setLayoutParams(layoutParams);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
    }
}
