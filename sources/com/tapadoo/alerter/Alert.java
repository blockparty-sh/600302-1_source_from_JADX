package com.tapadoo.alerter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.C0045Px;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.tapadoo.alerter.SwipeDismissTouchListener.DismissCallbacks;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.i18n.MessageBundle;
import org.spongycastle.i18n.TextBundle;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u0000 \u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u0001B/\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ \u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\b\b\u0001\u0010H\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u0002J\b\u0010J\u001a\u00020#H\u0016J\u0006\u0010K\u001a\u00020EJ\u0006\u0010L\u001a\u00020EJ\b\u0010M\u001a\u00020EH\u0002J\u0006\u0010/\u001a\u00020#J\u0010\u0010N\u001a\u00020E2\u0006\u0010O\u001a\u00020'H\u0016J\u0010\u0010P\u001a\u00020E2\u0006\u0010O\u001a\u00020'H\u0016J\u0010\u0010Q\u001a\u00020E2\u0006\u0010O\u001a\u00020'H\u0016J\b\u0010R\u001a\u00020EH\u0014J\u0010\u0010I\u001a\u00020E2\u0006\u0010S\u001a\u000201H\u0016J\b\u0010T\u001a\u00020EH\u0014J\u0010\u0010U\u001a\u00020E2\u0006\u0010V\u001a\u000201H\u0016J\u0018\u0010W\u001a\u00020E2\u0006\u0010X\u001a\u00020\b2\u0006\u0010Y\u001a\u00020\bH\u0014J\u0018\u0010Z\u001a\u00020E2\u0006\u0010V\u001a\u0002012\u0006\u0010[\u001a\u00020#H\u0016J\u0010\u0010\\\u001a\u00020#2\u0006\u0010]\u001a\u00020^H\u0016J\u000e\u0010_\u001a\u00020E2\u0006\u0010`\u001a\u00020#J\r\u0010a\u001a\u00020EH\u0000¢\u0006\u0002\bbJ\u0010\u0010c\u001a\u00020E2\b\b\u0001\u0010d\u001a\u00020\bJ\u000e\u0010e\u001a\u00020E2\u0006\u0010f\u001a\u00020gJ\u0010\u0010h\u001a\u00020E2\b\b\u0001\u0010i\u001a\u00020\bJ\u000e\u0010j\u001a\u00020E2\u0006\u0010k\u001a\u00020#J\u000e\u0010l\u001a\u00020E2\u0006\u0010$\u001a\u00020#J\u000e\u0010m\u001a\u00020E2\u0006\u0010%\u001a\u00020#J\u000e\u0010n\u001a\u00020E2\u0006\u0010o\u001a\u00020pJ\u000e\u0010n\u001a\u00020E2\u0006\u0010f\u001a\u00020gJ\u0010\u0010n\u001a\u00020E2\b\b\u0001\u0010q\u001a\u00020\bJ\u000e\u0010r\u001a\u00020E2\u0006\u0010s\u001a\u00020tJ\u0010\u0010r\u001a\u00020E2\b\b\u0001\u0010d\u001a\u00020\bJ\u0018\u0010r\u001a\u00020E2\b\b\u0001\u0010d\u001a\u00020\b2\u0006\u0010u\u001a\u00020vJ\u0010\u0010w\u001a\u00020E2\b\b\u0001\u0010x\u001a\u00020\bJ\u0010\u0010y\u001a\u00020E2\b\b\u0001\u0010x\u001a\u00020\bJ\u0012\u0010z\u001a\u00020E2\b\u0010{\u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010|\u001a\u00020E2\u0006\u0010{\u001a\u00020>J\u0010\u0010}\u001a\u00020E2\b\b\u0001\u0010d\u001a\u00020\bJ\u0010\u0010~\u001a\u00020E2\b\b\u0001\u0010d\u001a\u00020\bJ\u000e\u0010\u001a\u00020E2\u0006\u0010B\u001a\u00020#J\u000f\u0010\u0001\u001a\u00020E2\u0006\u0010F\u001a\u00020GJ\u0012\u0010\u0001\u001a\u00020E2\t\b\u0001\u0010\u0001\u001a\u00020\bJ\u0012\u0010\u0001\u001a\u00020E2\t\b\u0001\u0010\u0001\u001a\u00020\bJ\u0010\u0010\u0001\u001a\u00020E2\u0007\u0010\u0001\u001a\u00020\u000eJ\u0010\u0010\u0001\u001a\u00020E2\u0007\u0010\u0001\u001a\u00020GJ\u0012\u0010\u0001\u001a\u00020E2\t\b\u0001\u0010\u0001\u001a\u00020\bJ\u0012\u0010\u0001\u001a\u00020E2\t\b\u0001\u0010\u0001\u001a\u00020\bJ\u0010\u0010\u0001\u001a\u00020E2\u0007\u0010\u0001\u001a\u00020\u000eJ\u000f\u0010\u0001\u001a\u00020E2\u0006\u0010C\u001a\u00020#J\u0012\u0010\u0001\u001a\u00020E2\u0007\u0010\u0001\u001a\u00020\bH\u0016J\u000e\u0010A\u001a\u00020E2\u0006\u0010A\u001a\u00020#J\t\u0010\u0001\u001a\u00020EH\u0003R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016X\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R\u000e\u0010/\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u001d\u00100\u001a\u0004\u0018\u0001018FX\u0002¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b2\u00103R\u000e\u00106\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u001c\u00107\u001a\u0004\u0018\u000108X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0001"}, mo37405d2 = {"Lcom/tapadoo/alerter/Alert;", "Landroid/widget/FrameLayout;", "Landroid/view/View$OnClickListener;", "Landroid/view/animation/Animation$AnimationListener;", "Lcom/tapadoo/alerter/SwipeDismissTouchListener$DismissCallbacks;", "context", "Landroid/content/Context;", "layoutId", "", "attrs", "Landroid/util/AttributeSet;", "defStyle", "(Landroid/content/Context;ILandroid/util/AttributeSet;I)V", "buttonTypeFace", "Landroid/graphics/Typeface;", "getButtonTypeFace", "()Landroid/graphics/Typeface;", "setButtonTypeFace", "(Landroid/graphics/Typeface;)V", "buttons", "Ljava/util/ArrayList;", "Landroid/widget/Button;", "Lkotlin/collections/ArrayList;", "contentGravity", "getContentGravity", "()I", "setContentGravity", "(I)V", "duration", "", "getDuration$alerter_release", "()J", "setDuration$alerter_release", "(J)V", "enableIconPulse", "", "enableInfiniteDuration", "enableProgress", "enterAnimation", "Landroid/view/animation/Animation;", "getEnterAnimation$alerter_release", "()Landroid/view/animation/Animation;", "setEnterAnimation$alerter_release", "(Landroid/view/animation/Animation;)V", "exitAnimation", "getExitAnimation$alerter_release", "setExitAnimation$alerter_release", "isDismissable", "layoutContainer", "Landroid/view/View;", "getLayoutContainer", "()Landroid/view/View;", "layoutContainer$delegate", "Lkotlin/Lazy;", "marginSet", "onHideListener", "Lcom/tapadoo/alerter/OnHideAlertListener;", "getOnHideListener$alerter_release", "()Lcom/tapadoo/alerter/OnHideAlertListener;", "setOnHideListener$alerter_release", "(Lcom/tapadoo/alerter/OnHideAlertListener;)V", "onShowListener", "Lcom/tapadoo/alerter/OnShowAlertListener;", "runningAnimation", "Ljava/lang/Runnable;", "showIcon", "soundEnabled", "vibrationEnabled", "addButton", "", "text", "", "style", "onClick", "canDismiss", "disableOutsideTouch", "enableSwipeToDismiss", "hide", "onAnimationEnd", "animation", "onAnimationRepeat", "onAnimationStart", "onAttachedToWindow", "v", "onDetachedFromWindow", "onDismiss", "view", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouch", "touch", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "pulseIcon", "shouldPulse", "removeFromParent", "removeFromParent$alerter_release", "setAlertBackgroundColor", "color", "setAlertBackgroundDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setAlertBackgroundResource", "resource", "setDismissible", "dismissible", "setEnableInfiniteDuration", "setEnableProgress", "setIcon", "bitmap", "Landroid/graphics/Bitmap;", "iconId", "setIconColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "mode", "Landroid/graphics/PorterDuff$Mode;", "setIconPixelSize", "size", "setIconSize", "setOnClickListener", "listener", "setOnShowListener", "setProgressColorInt", "setProgressColorRes", "setSoundEnabled", "setText", "textId", "setTextAppearance", "textAppearance", "setTextTypeface", "typeface", "setTitle", "title", "titleId", "setTitleAppearance", "setTitleTypeface", "setVibrationEnabled", "setVisibility", "visibility", "startHideAnimation", "Companion", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Alert.kt */
public final class Alert extends FrameLayout implements OnClickListener, AnimationListener, DismissCallbacks {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Alert.class), "layoutContainer", "getLayoutContainer()Landroid/view/View;"))};
    private static final int CLEAN_UP_DELAY_MILLIS = 100;
    public static final Companion Companion = new Companion(null);
    private static final long DISPLAY_TIME_IN_SECONDS = DISPLAY_TIME_IN_SECONDS;
    private static final int MUL = -16777216;
    private HashMap _$_findViewCache;
    @Nullable
    private Typeface buttonTypeFace;
    private ArrayList<Button> buttons;
    private long duration;
    private boolean enableIconPulse;
    private boolean enableInfiniteDuration;
    private boolean enableProgress;
    @NotNull
    private Animation enterAnimation;
    @NotNull
    private Animation exitAnimation;
    private boolean isDismissable;
    @Nullable
    private final Lazy layoutContainer$delegate;
    private boolean marginSet;
    @Nullable
    private OnHideAlertListener onHideListener;
    private OnShowAlertListener onShowListener;
    private Runnable runningAnimation;
    private boolean showIcon;
    private boolean soundEnabled;
    private boolean vibrationEnabled;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo37405d2 = {"Lcom/tapadoo/alerter/Alert$Companion;", "", "()V", "CLEAN_UP_DELAY_MILLIS", "", "DISPLAY_TIME_IN_SECONDS", "", "MUL", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Alert.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmOverloads
    public Alert(@NotNull Context context, @LayoutRes int i) {
        this(context, i, null, 0, 12, null);
    }

    @JvmOverloads
    public Alert(@NotNull Context context, @LayoutRes int i, @Nullable AttributeSet attributeSet) {
        this(context, i, attributeSet, 0, 8, null);
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
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Nullable
    public final View getLayoutContainer() {
        Lazy lazy = this.layoutContainer$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (View) lazy.getValue();
    }

    public void onAnimationRepeat(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
    }

    public /* synthetic */ Alert(Context context, int i, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i3 & 4) != 0) {
            attributeSet = null;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        this(context, i, attributeSet, i2);
    }

    @JvmOverloads
    public Alert(@NotNull Context context, @LayoutRes int i, @Nullable AttributeSet attributeSet, int i2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i2);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, C2645R.anim.alerter_slide_in_from_top);
        Intrinsics.checkExpressionValueIsNotNull(loadAnimation, "AnimationUtils.loadAnima…lerter_slide_in_from_top)");
        this.enterAnimation = loadAnimation;
        Animation loadAnimation2 = AnimationUtils.loadAnimation(context, C2645R.anim.alerter_slide_out_to_top);
        Intrinsics.checkExpressionValueIsNotNull(loadAnimation2, "AnimationUtils.loadAnima…alerter_slide_out_to_top)");
        this.exitAnimation = loadAnimation2;
        this.duration = DISPLAY_TIME_IN_SECONDS;
        this.showIcon = true;
        this.enableIconPulse = true;
        this.isDismissable = true;
        this.buttons = new ArrayList<>();
        this.vibrationEnabled = true;
        this.layoutContainer$delegate = LazyKt.lazy(new Alert$layoutContainer$2(this));
        FrameLayout.inflate(context, C2645R.layout.alerter_alert_view, this);
        ViewStub viewStub = (ViewStub) findViewById(C2645R.C2648id.vAlertContentContainer);
        Intrinsics.checkExpressionValueIsNotNull(viewStub, "vAlertContentContainer");
        viewStub.setLayoutResource(i);
        ((ViewStub) findViewById(C2645R.C2648id.vAlertContentContainer)).inflate();
        setHapticFeedbackEnabled(true);
        ViewCompat.setTranslationZ(this, (float) Integer.MAX_VALUE);
        ((LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground)).setOnClickListener(this);
    }

    @Nullable
    public final OnHideAlertListener getOnHideListener$alerter_release() {
        return this.onHideListener;
    }

    public final void setOnHideListener$alerter_release(@Nullable OnHideAlertListener onHideAlertListener) {
        this.onHideListener = onHideAlertListener;
    }

    @NotNull
    public final Animation getEnterAnimation$alerter_release() {
        return this.enterAnimation;
    }

    public final void setEnterAnimation$alerter_release(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "<set-?>");
        this.enterAnimation = animation;
    }

    @NotNull
    public final Animation getExitAnimation$alerter_release() {
        return this.exitAnimation;
    }

    public final void setExitAnimation$alerter_release(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "<set-?>");
        this.exitAnimation = animation;
    }

    public final long getDuration$alerter_release() {
        return this.duration;
    }

    public final void setDuration$alerter_release(long j) {
        this.duration = j;
    }

    @Nullable
    public final Typeface getButtonTypeFace() {
        return this.buttonTypeFace;
    }

    public final void setButtonTypeFace(@Nullable Typeface typeface) {
        this.buttonTypeFace = typeface;
    }

    public final int getContentGravity() {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground);
        LayoutParams layoutParams = linearLayout != null ? linearLayout.getLayoutParams() : null;
        if (layoutParams != null) {
            return ((FrameLayout.LayoutParams) layoutParams).gravity;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
    }

    public final void setContentGravity(int i) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvTitle);
        LayoutParams layoutParams = appCompatTextView != null ? appCompatTextView.getLayoutParams() : null;
        if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
            layoutParams = null;
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        if (layoutParams2 != null) {
            layoutParams2.gravity = i;
        }
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvTitle);
        if (appCompatTextView2 != null) {
            appCompatTextView2.setLayoutParams(layoutParams2);
        }
        AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
        LayoutParams layoutParams3 = appCompatTextView3 != null ? appCompatTextView3.getLayoutParams() : null;
        if (!(layoutParams3 instanceof LinearLayout.LayoutParams)) {
            layoutParams3 = null;
        }
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
        if (layoutParams4 != null) {
            layoutParams4.gravity = i;
        }
        AppCompatTextView appCompatTextView4 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
        if (appCompatTextView4 != null) {
            appCompatTextView4.setLayoutParams(layoutParams4);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.enterAnimation.setAnimationListener(this);
        setAnimation(this.enterAnimation);
        for (Button button : this.buttons) {
            Typeface typeface = this.buttonTypeFace;
            if (typeface != null) {
                button.setTypeface(typeface);
            }
            ((LinearLayout) _$_findCachedViewById(C2645R.C2648id.llButtonContainer)).addView(button);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (!this.marginSet) {
            this.marginSet = true;
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                marginLayoutParams.topMargin = context.getResources().getDimensionPixelSize(C2645R.dimen.alerter_alert_negative_margin_top);
                if (VERSION.SDK_INT >= 28) {
                    Context context2 = getContext();
                    DisplayCutout displayCutout = null;
                    if (!(context2 instanceof Activity)) {
                        context2 = null;
                    }
                    Activity activity = (Activity) context2;
                    if (activity != null) {
                        Window window = activity.getWindow();
                        if (window != null) {
                            View decorView = window.getDecorView();
                            if (decorView != null) {
                                WindowInsets rootWindowInsets = decorView.getRootWindowInsets();
                                if (rootWindowInsets != null) {
                                    displayCutout = rootWindowInsets.getDisplayCutout();
                                }
                            }
                        }
                    }
                    LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground);
                    linearLayout.setPadding(linearLayout.getPaddingLeft(), linearLayout.getPaddingTop() + ((displayCutout != null ? displayCutout.getSafeInsetTop() : 0) / 2), linearLayout.getPaddingRight(), linearLayout.getPaddingBottom());
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.enterAnimation.setAnimationListener(null);
    }

    public boolean onTouchEvent(@NotNull MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(motionEvent, "event");
        super.performClick();
        return super.onTouchEvent(motionEvent);
    }

    public void onClick(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        if (this.isDismissable) {
            hide();
        }
    }

    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        ((LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground)).setOnClickListener(onClickListener);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
            childAt.setVisibility(i);
        }
    }

    public void onAnimationStart(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        if (!isInEditMode()) {
            setVisibility(0);
            if (this.vibrationEnabled) {
                performHapticFeedback(1);
            }
            if (this.soundEnabled) {
                try {
                    RingtoneManager.getRingtone(getContext(), RingtoneManager.getDefaultUri(2)).play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.enableProgress) {
                AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
                if (appCompatImageView != null) {
                    appCompatImageView.setVisibility(4);
                }
                ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(C2645R.C2648id.pbProgress);
                if (progressBar != null) {
                    progressBar.setVisibility(0);
                }
            } else if (this.showIcon) {
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
                if (appCompatImageView2 != null) {
                    appCompatImageView2.setVisibility(0);
                }
                if (this.enableIconPulse) {
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
                    if (appCompatImageView3 != null) {
                        appCompatImageView3.startAnimation(AnimationUtils.loadAnimation(getContext(), C2645R.anim.alerter_pulse));
                    }
                }
            } else {
                FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(C2645R.C2648id.flIconContainer);
                if (frameLayout != null) {
                    frameLayout.setVisibility(8);
                }
            }
        }
    }

    public void onAnimationEnd(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        OnShowAlertListener onShowAlertListener = this.onShowListener;
        if (onShowAlertListener != null) {
            onShowAlertListener.onShow();
        }
        startHideAnimation();
    }

    @TargetApi(11)
    private final void startHideAnimation() {
        if (!this.enableInfiniteDuration) {
            this.runningAnimation = new Alert$startHideAnimation$1(this);
            postDelayed(this.runningAnimation, this.duration);
        }
    }

    /* access modifiers changed from: private */
    public final void hide() {
        try {
            this.exitAnimation.setAnimationListener(new Alert$hide$1(this));
            startAnimation(this.exitAnimation);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), Log.getStackTraceString(e));
        }
    }

    public final void removeFromParent$alerter_release() {
        clearAnimation();
        setVisibility(8);
        postDelayed(new Alert$removeFromParent$1(this), (long) 100);
    }

    public final void setAlertBackgroundColor(@ColorInt int i) {
        ((LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground)).setBackgroundColor(i);
    }

    public final void setAlertBackgroundResource(@DrawableRes int i) {
        ((LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground)).setBackgroundResource(i);
    }

    public final void setAlertBackgroundDrawable(@NotNull Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        if (VERSION.SDK_INT >= 16) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llAlertBackground");
            linearLayout.setBackground(drawable);
            return;
        }
        ((LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground)).setBackgroundDrawable(drawable);
    }

    public final void setTitle(@StringRes int i) {
        String string = getContext().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(titleId)");
        setTitle((CharSequence) string);
    }

    public final void setText(@StringRes int i) {
        String string = getContext().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(textId)");
        setText((CharSequence) string);
    }

    public final void disableOutsideTouch() {
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(C2645R.C2648id.flClickShield);
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "flClickShield");
        frameLayout.setClickable(true);
    }

    public final void setTitle(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, MessageBundle.TITLE_ENTRY);
        if (!TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvTitle);
            if (appCompatTextView != null) {
                appCompatTextView.setVisibility(0);
            }
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvTitle);
            if (appCompatTextView2 != null) {
                appCompatTextView2.setText(charSequence);
            }
        }
    }

    public final void setTitleAppearance(@StyleRes int i) {
        if (VERSION.SDK_INT >= 23) {
            AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvTitle);
            if (appCompatTextView != null) {
                appCompatTextView.setTextAppearance(i);
                return;
            }
            return;
        }
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvTitle);
        if (appCompatTextView2 != null) {
            AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
            appCompatTextView2.setTextAppearance(appCompatTextView3 != null ? appCompatTextView3.getContext() : null, i);
        }
    }

    public final void setTitleTypeface(@NotNull Typeface typeface) {
        Intrinsics.checkParameterIsNotNull(typeface, "typeface");
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvTitle);
        if (appCompatTextView != null) {
            appCompatTextView.setTypeface(typeface);
        }
    }

    public final void setTextTypeface(@NotNull Typeface typeface) {
        Intrinsics.checkParameterIsNotNull(typeface, "typeface");
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
        if (appCompatTextView != null) {
            appCompatTextView.setTypeface(typeface);
        }
    }

    public final void setText(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, TextBundle.TEXT_ENTRY);
        if (!TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
            if (appCompatTextView != null) {
                appCompatTextView.setVisibility(0);
            }
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
            if (appCompatTextView2 != null) {
                appCompatTextView2.setText(charSequence);
            }
        }
    }

    public final void setTextAppearance(@StyleRes int i) {
        if (VERSION.SDK_INT >= 23) {
            AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
            if (appCompatTextView != null) {
                appCompatTextView.setTextAppearance(i);
                return;
            }
            return;
        }
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
        if (appCompatTextView2 != null) {
            AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(C2645R.C2648id.tvText);
            appCompatTextView2.setTextAppearance(appCompatTextView3 != null ? appCompatTextView3.getContext() : null, i);
        }
    }

    public final void setIcon(@DrawableRes int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        if (appCompatImageView != null) {
            appCompatImageView.setImageDrawable(AppCompatResources.getDrawable(getContext(), i));
        }
    }

    public final void setIconColorFilter(@ColorInt int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        if (appCompatImageView != null) {
            appCompatImageView.setColorFilter(i);
        }
    }

    public final void setIconColorFilter(@NotNull ColorFilter colorFilter) {
        Intrinsics.checkParameterIsNotNull(colorFilter, "colorFilter");
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        if (appCompatImageView != null) {
            appCompatImageView.setColorFilter(colorFilter);
        }
    }

    public final void setIconColorFilter(@ColorInt int i, @NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        if (appCompatImageView != null) {
            appCompatImageView.setColorFilter(i, mode);
        }
    }

    public final void setIcon(@NotNull Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        if (appCompatImageView != null) {
            appCompatImageView.setImageBitmap(bitmap);
        }
    }

    public final void setIcon(@NotNull Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        if (appCompatImageView != null) {
            appCompatImageView.setImageDrawable(drawable);
        }
    }

    public final void setIconSize(@DimenRes int i) {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        setIconPixelSize(context.getResources().getDimensionPixelSize(i));
    }

    public final void setIconPixelSize(@C0045Px int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        String str = "ivIcon";
        Intrinsics.checkExpressionValueIsNotNull(appCompatImageView, str);
        AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(C2645R.C2648id.ivIcon);
        Intrinsics.checkExpressionValueIsNotNull(appCompatImageView2, str);
        LayoutParams layoutParams = appCompatImageView2.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        setMinimumWidth(i);
        setMinimumHeight(i);
        appCompatImageView.setLayoutParams(layoutParams);
    }

    public final void showIcon(boolean z) {
        this.showIcon = z;
    }

    public final void setDismissible(boolean z) {
        this.isDismissable = z;
    }

    public final boolean isDismissable() {
        return this.isDismissable;
    }

    public final void enableSwipeToDismiss() {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "it");
        linearLayout.setOnTouchListener(new SwipeDismissTouchListener(linearLayout, new Alert$enableSwipeToDismiss$$inlined$let$lambda$1(this)));
    }

    public final void pulseIcon(boolean z) {
        this.enableIconPulse = z;
    }

    public final void setEnableInfiniteDuration(boolean z) {
        this.enableInfiniteDuration = z;
    }

    public final void setEnableProgress(boolean z) {
        this.enableProgress = z;
    }

    public final void setProgressColorRes(@ColorRes int i) {
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(C2645R.C2648id.pbProgress);
        if (progressBar != null) {
            Drawable progressDrawable = progressBar.getProgressDrawable();
            if (progressDrawable != null) {
                progressDrawable.setColorFilter(new LightingColorFilter(-16777216, ContextCompat.getColor(getContext(), i)));
            }
        }
    }

    public final void setProgressColorInt(@ColorInt int i) {
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(C2645R.C2648id.pbProgress);
        if (progressBar != null) {
            Drawable progressDrawable = progressBar.getProgressDrawable();
            if (progressDrawable != null) {
                progressDrawable.setColorFilter(new LightingColorFilter(-16777216, i));
            }
        }
    }

    public final void setOnShowListener(@NotNull OnShowAlertListener onShowAlertListener) {
        Intrinsics.checkParameterIsNotNull(onShowAlertListener, CastExtraArgs.LISTENER);
        this.onShowListener = onShowAlertListener;
    }

    public final void setVibrationEnabled(boolean z) {
        this.vibrationEnabled = z;
    }

    public final void setSoundEnabled(boolean z) {
        this.soundEnabled = z;
    }

    public final void addButton(@NotNull CharSequence charSequence, @StyleRes int i, @NotNull OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(charSequence, TextBundle.TEXT_ENTRY);
        Intrinsics.checkParameterIsNotNull(onClickListener, "onClick");
        Button button = new Button(new ContextThemeWrapper(getContext(), i), null, i);
        button.setText(charSequence);
        button.setOnClickListener(onClickListener);
        this.buttons.add(button);
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground);
        if (linearLayout != null) {
            linearLayout.setPadding(linearLayout.getPaddingLeft(), linearLayout.getPaddingTop(), linearLayout.getPaddingRight(), linearLayout.getPaddingBottom() / 2);
        }
    }

    public boolean canDismiss() {
        return this.isDismissable;
    }

    public void onDismiss(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(C2645R.C2648id.flClickShield);
        if (frameLayout != null) {
            frameLayout.removeView((LinearLayout) _$_findCachedViewById(C2645R.C2648id.llAlertBackground));
        }
    }

    public void onTouch(@NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (z) {
            removeCallbacks(this.runningAnimation);
        } else {
            startHideAnimation();
        }
    }
}
