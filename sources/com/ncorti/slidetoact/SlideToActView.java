package com.ncorti.slidetoact;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewOutlineProvider;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\n\u0001\u0001\u0001\u0001\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010s\u001a\u00020\u00132\u0006\u0010t\u001a\u00020\"2\u0006\u0010u\u001a\u00020\"H\u0002J\u0006\u0010v\u001a\u00020wJ\u0010\u0010x\u001a\u00020w2\u0006\u0010y\u001a\u00020\u0007H\u0002J\b\u0010z\u001a\u00020wH\u0002J\u0006\u0010{\u001a\u00020\u0013J\u0012\u0010|\u001a\u00020w2\b\u0010}\u001a\u0004\u0018\u00010~H\u0014J\u001a\u0010\u001a\u00020w2\u0007\u0010\u0001\u001a\u00020\u00072\u0007\u0010\u0001\u001a\u00020\u0007H\u0014J-\u0010\u0001\u001a\u00020w2\u0007\u0010\u0001\u001a\u00020\u00072\u0007\u0010\u0001\u001a\u00020\u00072\u0007\u0010\u0001\u001a\u00020\u00072\u0007\u0010\u0001\u001a\u00020\u0007H\u0014J\u0015\u0010\u0001\u001a\u00020\u00132\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0016J+\u0010\u0001\u001a\u00020*2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\u00072\r\u0010\u0001\u001a\b0\u0001R\u00030\u0001H\u0002J\t\u0010\u0001\u001a\u00020\u0013H\u0016J\u0007\u0010\u0001\u001a\u00020wJ\t\u0010\u0001\u001a\u00020wH\u0002J\t\u0010\u0001\u001a\u00020wH\u0002J\t\u0010\u0001\u001a\u00020wH\u0002J\t\u0010\u0001\u001a\u00020wH\u0002R$\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R$\u0010\u0019\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\u001b\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u000e\u0010\u001d\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010-\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@BX\u000e¢\u0006\b\n\u0000\"\u0004\b.\u0010\u000eR\u000e\u0010/\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\"XD¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000205X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u000207X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010=\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@BX\u000e¢\u0006\b\n\u0000\"\u0004\b>\u0010\u000eR\u000e\u0010?\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u000205X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010B\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@BX\u000e¢\u0006\b\n\u0000\"\u0004\bC\u0010\u000eR\u000e\u0010D\u001a\u00020EX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010I\u001a\u0004\u0018\u00010JX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001c\u0010O\u001a\u0004\u0018\u00010PX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001c\u0010U\u001a\u0004\u0018\u00010VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001c\u0010[\u001a\u0004\u0018\u00010\\X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R$\u0010a\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\f\"\u0004\bc\u0010\u000eR$\u0010e\u001a\u00020d2\u0006\u0010\t\u001a\u00020d@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR$\u0010j\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\f\"\u0004\bl\u0010\u000eR$\u0010m\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\f\"\u0004\bo\u0010\u000eR$\u0010p\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\f\"\u0004\br\u0010\u000e¨\u0006\u0001"}, mo37405d2 = {"Lcom/ncorti/slidetoact/SlideToActView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "iconColor", "getIconColor", "()I", "setIconColor", "(I)V", "innerColor", "getInnerColor", "setInnerColor", "isAnimateCompletion", "", "()Z", "setAnimateCompletion", "(Z)V", "isLocked", "setLocked", "isReversed", "setReversed", "isRotateIcon", "setRotateIcon", "mActualAreaMargin", "mActualAreaWidth", "mAreaHeight", "mAreaWidth", "mArrowAngle", "", "mArrowMargin", "mBorderRadius", "mDesiredSliderHeight", "mDesiredSliderHeightDp", "mDesiredSliderWidth", "mDesiredSliderWidthDp", "mDrawableArrow", "Landroidx/vectordrawable/graphics/drawable/VectorDrawableCompat;", "mDrawableTick", "Landroid/graphics/drawable/Drawable;", "mEffectivePosition", "setMEffectivePosition", "mFlagDrawTick", "mFlagMoving", "mGraceValue", "mIcon", "mIconMargin", "mInnerPaint", "Landroid/graphics/Paint;", "mInnerRect", "Landroid/graphics/RectF;", "mIsCompleted", "mLastX", "mOriginAreaMargin", "mOuterPaint", "mOuterRect", "mPosition", "setMPosition", "mPositionPerc", "mPositionPercInv", "mTextPaint", "mTextSize", "setMTextSize", "mTextView", "Landroid/widget/TextView;", "mTextXPosition", "mTextYPosition", "mTickMargin", "onSlideCompleteListener", "Lcom/ncorti/slidetoact/SlideToActView$OnSlideCompleteListener;", "getOnSlideCompleteListener", "()Lcom/ncorti/slidetoact/SlideToActView$OnSlideCompleteListener;", "setOnSlideCompleteListener", "(Lcom/ncorti/slidetoact/SlideToActView$OnSlideCompleteListener;)V", "onSlideResetListener", "Lcom/ncorti/slidetoact/SlideToActView$OnSlideResetListener;", "getOnSlideResetListener", "()Lcom/ncorti/slidetoact/SlideToActView$OnSlideResetListener;", "setOnSlideResetListener", "(Lcom/ncorti/slidetoact/SlideToActView$OnSlideResetListener;)V", "onSlideToActAnimationEventListener", "Lcom/ncorti/slidetoact/SlideToActView$OnSlideToActAnimationEventListener;", "getOnSlideToActAnimationEventListener", "()Lcom/ncorti/slidetoact/SlideToActView$OnSlideToActAnimationEventListener;", "setOnSlideToActAnimationEventListener", "(Lcom/ncorti/slidetoact/SlideToActView$OnSlideToActAnimationEventListener;)V", "onSlideUserFailedListener", "Lcom/ncorti/slidetoact/SlideToActView$OnSlideUserFailedListener;", "getOnSlideUserFailedListener", "()Lcom/ncorti/slidetoact/SlideToActView$OnSlideUserFailedListener;", "setOnSlideUserFailedListener", "(Lcom/ncorti/slidetoact/SlideToActView$OnSlideUserFailedListener;)V", "outerColor", "getOuterColor", "setOuterColor", "", "text", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "textAppearance", "getTextAppearance", "setTextAppearance", "textColor", "getTextColor", "setTextColor", "typeFace", "getTypeFace", "setTypeFace", "checkInsideButton", "x", "y", "completeSlider", "", "increasePosition", "inc", "invalidateArea", "isCompleted", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "parseVectorDrawableCompat", "res", "Landroid/content/res/Resources;", "resId", "theme", "Landroid/content/res/Resources$Theme;", "performClick", "resetSlider", "startAnimationComplete", "startAnimationReset", "startTickAnimation", "stopTickAnimation", "OnSlideCompleteListener", "OnSlideResetListener", "OnSlideToActAnimationEventListener", "OnSlideUserFailedListener", "SlideToActOutlineProvider", "slidetoact_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: SlideToActView.kt */
public final class SlideToActView extends View {
    private int iconColor;
    private int innerColor;
    private boolean isAnimateCompletion;
    private boolean isLocked;
    private boolean isReversed;
    private boolean isRotateIcon;
    /* access modifiers changed from: private */
    public int mActualAreaMargin;
    /* access modifiers changed from: private */
    public int mActualAreaWidth;
    /* access modifiers changed from: private */
    public int mAreaHeight;
    /* access modifiers changed from: private */
    public int mAreaWidth;
    private float mArrowAngle;
    /* access modifiers changed from: private */
    public int mArrowMargin;
    /* access modifiers changed from: private */
    public int mBorderRadius;
    private int mDesiredSliderHeight;
    private float mDesiredSliderHeightDp;
    private int mDesiredSliderWidth;
    private float mDesiredSliderWidthDp;
    private final VectorDrawableCompat mDrawableArrow;
    /* access modifiers changed from: private */
    public final Drawable mDrawableTick;
    private int mEffectivePosition;
    /* access modifiers changed from: private */
    public boolean mFlagDrawTick;
    private boolean mFlagMoving;
    private final float mGraceValue;
    private int mIcon;
    /* access modifiers changed from: private */
    public final int mIconMargin;
    private final Paint mInnerPaint;
    private RectF mInnerRect;
    /* access modifiers changed from: private */
    public boolean mIsCompleted;
    private float mLastX;
    private final int mOriginAreaMargin;
    private final Paint mOuterPaint;
    private RectF mOuterRect;
    /* access modifiers changed from: private */
    public int mPosition;
    /* access modifiers changed from: private */
    public float mPositionPerc;
    private float mPositionPercInv;
    private Paint mTextPaint;
    private int mTextSize;
    private TextView mTextView;
    private float mTextXPosition;
    private float mTextYPosition;
    /* access modifiers changed from: private */
    public int mTickMargin;
    @Nullable
    private OnSlideCompleteListener onSlideCompleteListener;
    @Nullable
    private OnSlideResetListener onSlideResetListener;
    @Nullable
    private OnSlideToActAnimationEventListener onSlideToActAnimationEventListener;
    @Nullable
    private OnSlideUserFailedListener onSlideUserFailedListener;
    private int outerColor;
    @NotNull
    private CharSequence text;
    private int textAppearance;
    private int textColor;
    private int typeFace;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, mo37405d2 = {"Lcom/ncorti/slidetoact/SlideToActView$OnSlideCompleteListener;", "", "onSlideComplete", "", "view", "Lcom/ncorti/slidetoact/SlideToActView;", "slidetoact_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: SlideToActView.kt */
    public interface OnSlideCompleteListener {
        void onSlideComplete(@NotNull SlideToActView slideToActView);
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, mo37405d2 = {"Lcom/ncorti/slidetoact/SlideToActView$OnSlideResetListener;", "", "onSlideReset", "", "view", "Lcom/ncorti/slidetoact/SlideToActView;", "slidetoact_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: SlideToActView.kt */
    public interface OnSlideResetListener {
        void onSlideReset(@NotNull SlideToActView slideToActView);
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, mo37405d2 = {"Lcom/ncorti/slidetoact/SlideToActView$OnSlideToActAnimationEventListener;", "", "onSlideCompleteAnimationEnded", "", "view", "Lcom/ncorti/slidetoact/SlideToActView;", "onSlideCompleteAnimationStarted", "threshold", "", "onSlideResetAnimationEnded", "onSlideResetAnimationStarted", "slidetoact_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: SlideToActView.kt */
    public interface OnSlideToActAnimationEventListener {
        void onSlideCompleteAnimationEnded(@NotNull SlideToActView slideToActView);

        void onSlideCompleteAnimationStarted(@NotNull SlideToActView slideToActView, float f);

        void onSlideResetAnimationEnded(@NotNull SlideToActView slideToActView);

        void onSlideResetAnimationStarted(@NotNull SlideToActView slideToActView);
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, mo37405d2 = {"Lcom/ncorti/slidetoact/SlideToActView$OnSlideUserFailedListener;", "", "onSlideFailed", "", "view", "Lcom/ncorti/slidetoact/SlideToActView;", "isOutside", "", "slidetoact_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: SlideToActView.kt */
    public interface OnSlideUserFailedListener {
        void onSlideFailed(@NotNull SlideToActView slideToActView, boolean z);
    }

    @RequiresApi(21)
    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, mo37405d2 = {"Lcom/ncorti/slidetoact/SlideToActView$SlideToActOutlineProvider;", "Landroid/view/ViewOutlineProvider;", "(Lcom/ncorti/slidetoact/SlideToActView;)V", "getOutline", "", "view", "Landroid/view/View;", "outline", "Landroid/graphics/Outline;", "slidetoact_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: SlideToActView.kt */
    private final class SlideToActOutlineProvider extends ViewOutlineProvider {
        public SlideToActOutlineProvider() {
        }

        public void getOutline(@Nullable View view, @Nullable Outline outline) {
            if (view != null && outline != null) {
                outline.setRoundRect(SlideToActView.this.mActualAreaWidth, 0, SlideToActView.this.mAreaWidth - SlideToActView.this.mActualAreaWidth, SlideToActView.this.mAreaHeight, (float) SlideToActView.this.mBorderRadius);
            }
        }
    }

    @JvmOverloads
    public SlideToActView(@NotNull Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public SlideToActView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    @JvmOverloads
    public /* synthetic */ SlideToActView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = C2537R.attr.slideToActViewStyle;
        }
        this(context, attributeSet, i);
    }

    /* JADX INFO: finally extract failed */
    @JvmOverloads
    public SlideToActView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        Drawable drawable;
        String str = "resources";
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i);
        this.mDesiredSliderHeightDp = 72.0f;
        this.mDesiredSliderWidthDp = 280.0f;
        this.mBorderRadius = -1;
        this.text = "";
        this.mTextYPosition = -1.0f;
        this.mTextXPosition = -1.0f;
        this.mPositionPercInv = 1.0f;
        this.mIcon = C2537R.C2539drawable.ic_arrow;
        this.mOuterPaint = new Paint(1);
        this.mInnerPaint = new Paint(1);
        this.mTextPaint = new Paint(1);
        this.mGraceValue = 0.8f;
        this.isRotateIcon = true;
        this.isAnimateCompletion = true;
        this.mTextView = new TextView(context);
        TextPaint paint = this.mTextView.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "mTextView.paint");
        this.mTextPaint = paint;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2537R.styleable.SlideToActView, i, C2537R.C2541style.SlideToActView);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.theme.obtainStyl…, R.style.SlideToActView)");
        try {
            float f = this.mDesiredSliderHeightDp;
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, str);
            this.mDesiredSliderHeight = (int) TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
            float f2 = this.mDesiredSliderWidthDp;
            Resources resources2 = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources2, str);
            this.mDesiredSliderWidth = (int) TypedValue.applyDimension(1, f2, resources2.getDisplayMetrics());
            this.mDesiredSliderHeight = obtainStyledAttributes.getDimensionPixelSize(C2537R.styleable.SlideToActView_slider_height, this.mDesiredSliderHeight);
            this.mBorderRadius = obtainStyledAttributes.getDimensionPixelSize(C2537R.styleable.SlideToActView_border_radius, -1);
            int color = ContextCompat.getColor(getContext(), C2537R.C2538color.defaultAccent);
            int color2 = ContextCompat.getColor(getContext(), C2537R.C2538color.white);
            int color3 = obtainStyledAttributes.getColor(C2537R.styleable.SlideToActView_outer_color, color);
            int color4 = obtainStyledAttributes.getColor(C2537R.styleable.SlideToActView_inner_color, color2);
            if (obtainStyledAttributes.hasValue(C2537R.styleable.SlideToActView_text_color)) {
                color2 = obtainStyledAttributes.getColor(C2537R.styleable.SlideToActView_text_color, color2);
            } else if (obtainStyledAttributes.hasValue(C2537R.styleable.SlideToActView_inner_color)) {
                color2 = color4;
            }
            String string = obtainStyledAttributes.getString(C2537R.styleable.SlideToActView_text);
            Intrinsics.checkExpressionValueIsNotNull(string, "layoutAttrs.getString(R.…able.SlideToActView_text)");
            setText(string);
            setTypeFace(obtainStyledAttributes.getInt(C2537R.styleable.SlideToActView_text_style, 0));
            setMTextSize(obtainStyledAttributes.getDimensionPixelSize(C2537R.styleable.SlideToActView_text_size, getResources().getDimensionPixelSize(C2537R.dimen.default_text_size)));
            setTextColor(color2);
            setTextAppearance(obtainStyledAttributes.getResourceId(C2537R.styleable.SlideToActView_text_appearance, 0));
            this.isLocked = obtainStyledAttributes.getBoolean(C2537R.styleable.SlideToActView_slider_locked, false);
            setReversed(obtainStyledAttributes.getBoolean(C2537R.styleable.SlideToActView_slider_reversed, false));
            this.isRotateIcon = obtainStyledAttributes.getBoolean(C2537R.styleable.SlideToActView_rotate_icon, true);
            this.isAnimateCompletion = obtainStyledAttributes.getBoolean(C2537R.styleable.SlideToActView_animate_completion, true);
            this.mOriginAreaMargin = obtainStyledAttributes.getDimensionPixelSize(C2537R.styleable.SlideToActView_area_margin, getResources().getDimensionPixelSize(C2537R.dimen.default_area_margin));
            this.mActualAreaMargin = this.mOriginAreaMargin;
            this.mIcon = obtainStyledAttributes.getResourceId(C2537R.styleable.SlideToActView_slider_icon, C2537R.C2539drawable.ic_arrow);
            if (obtainStyledAttributes.hasValue(C2537R.styleable.SlideToActView_slider_icon_color)) {
                color = obtainStyledAttributes.getColor(C2537R.styleable.SlideToActView_slider_icon_color, color);
            } else if (obtainStyledAttributes.hasValue(C2537R.styleable.SlideToActView_outer_color)) {
                color = color3;
            }
            obtainStyledAttributes.recycle();
            int i2 = this.mActualAreaMargin;
            int i3 = this.mEffectivePosition;
            float f3 = (float) (i2 + i3);
            float f4 = (float) i2;
            int i4 = this.mAreaHeight;
            this.mInnerRect = new RectF(f3, f4, ((float) (i3 + i4)) - ((float) i2), ((float) i4) - ((float) i2));
            int i5 = this.mActualAreaWidth;
            this.mOuterRect = new RectF((float) i5, 0.0f, ((float) this.mAreaWidth) - ((float) i5), (float) this.mAreaHeight);
            Resources resources3 = context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources3, "context.resources");
            int i6 = this.mIcon;
            Theme theme = context.getTheme();
            Intrinsics.checkExpressionValueIsNotNull(theme, "context.theme");
            this.mDrawableArrow = parseVectorDrawableCompat(resources3, i6, theme);
            if (VERSION.SDK_INT >= 21) {
                Drawable drawable2 = context.getResources().getDrawable(C2537R.C2539drawable.animated_ic_check, context.getTheme());
                if (drawable2 != null) {
                    drawable = (AnimatedVectorDrawable) drawable2;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
                }
            } else {
                AnimatedVectorDrawableCompat create = AnimatedVectorDrawableCompat.create(context, C2537R.C2539drawable.animated_ic_check);
                if (create == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(create, "AnimatedVectorDrawableCo…able.animated_ic_check)!!");
                drawable = create;
            }
            this.mDrawableTick = drawable;
            this.mTextPaint.setTextAlign(Align.CENTER);
            setOuterColor(color3);
            setInnerColor(color4);
            setIconColor(color);
            this.mIconMargin = context.getResources().getDimensionPixelSize(C2537R.dimen.default_icon_margin);
            int i7 = this.mIconMargin;
            this.mArrowMargin = i7;
            this.mTickMargin = i7;
            if (VERSION.SDK_INT >= 21) {
                setOutlineProvider(new SlideToActOutlineProvider());
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @NotNull
    public final CharSequence getText() {
        return this.text;
    }

    public final void setText(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "value");
        this.text = charSequence;
        this.mTextView.setText(charSequence);
        this.mTextPaint.set(this.mTextView.getPaint());
        invalidate();
    }

    public final int getTypeFace() {
        return this.typeFace;
    }

    public final void setTypeFace(int i) {
        this.typeFace = i;
        this.mTextView.setTypeface(Typeface.create("sans-serif-light", i));
        this.mTextPaint.set(this.mTextView.getPaint());
        invalidate();
    }

    public final int getTextAppearance() {
        return this.textAppearance;
    }

    public final void setTextAppearance(int i) {
        this.textAppearance = i;
        if (i != 0) {
            TextViewCompat.setTextAppearance(this.mTextView, i);
            this.mTextPaint.set(this.mTextView.getPaint());
            this.mTextPaint.setColor(this.mTextView.getCurrentTextColor());
        }
    }

    public final int getOuterColor() {
        return this.outerColor;
    }

    public final void setOuterColor(int i) {
        this.outerColor = i;
        this.mOuterPaint.setColor(i);
        invalidate();
    }

    public final int getInnerColor() {
        return this.innerColor;
    }

    public final void setInnerColor(int i) {
        this.innerColor = i;
        this.mInnerPaint.setColor(i);
        invalidate();
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(int i) {
        this.textColor = i;
        this.mTextView.setTextColor(i);
        this.mTextPaint.setColor(this.textColor);
        invalidate();
    }

    public final int getIconColor() {
        return this.iconColor;
    }

    public final void setIconColor(int i) {
        this.iconColor = i;
        this.mDrawableArrow.setTint(i);
        invalidate();
    }

    /* access modifiers changed from: private */
    public final void setMPosition(int i) {
        this.mPosition = i;
        int i2 = this.mAreaWidth;
        int i3 = this.mAreaHeight;
        if (i2 - i3 == 0) {
            this.mPositionPerc = 0.0f;
            this.mPositionPercInv = 1.0f;
            return;
        }
        float f = (float) i;
        this.mPositionPerc = f / ((float) (i2 - i3));
        this.mPositionPercInv = ((float) 1) - (f / ((float) (i2 - i3)));
        setMEffectivePosition(this.mPosition);
    }

    private final void setMEffectivePosition(int i) {
        if (this.isReversed) {
            i = (this.mAreaWidth - this.mAreaHeight) - i;
        }
        this.mEffectivePosition = i;
    }

    private final void setMTextSize(int i) {
        this.mTextSize = i;
        this.mTextView.setTextSize(0, (float) this.mTextSize);
        this.mTextPaint.set(this.mTextView.getPaint());
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final void setLocked(boolean z) {
        this.isLocked = z;
    }

    public final boolean isReversed() {
        return this.isReversed;
    }

    public final void setReversed(boolean z) {
        this.isReversed = z;
        setMPosition(this.mPosition);
        invalidate();
    }

    public final boolean isRotateIcon() {
        return this.isRotateIcon;
    }

    public final void setRotateIcon(boolean z) {
        this.isRotateIcon = z;
    }

    public final boolean isAnimateCompletion() {
        return this.isAnimateCompletion;
    }

    public final void setAnimateCompletion(boolean z) {
        this.isAnimateCompletion = z;
    }

    @Nullable
    public final OnSlideToActAnimationEventListener getOnSlideToActAnimationEventListener() {
        return this.onSlideToActAnimationEventListener;
    }

    public final void setOnSlideToActAnimationEventListener(@Nullable OnSlideToActAnimationEventListener onSlideToActAnimationEventListener2) {
        this.onSlideToActAnimationEventListener = onSlideToActAnimationEventListener2;
    }

    @Nullable
    public final OnSlideCompleteListener getOnSlideCompleteListener() {
        return this.onSlideCompleteListener;
    }

    public final void setOnSlideCompleteListener(@Nullable OnSlideCompleteListener onSlideCompleteListener2) {
        this.onSlideCompleteListener = onSlideCompleteListener2;
    }

    @Nullable
    public final OnSlideResetListener getOnSlideResetListener() {
        return this.onSlideResetListener;
    }

    public final void setOnSlideResetListener(@Nullable OnSlideResetListener onSlideResetListener2) {
        this.onSlideResetListener = onSlideResetListener2;
    }

    @Nullable
    public final OnSlideUserFailedListener getOnSlideUserFailedListener() {
        return this.onSlideUserFailedListener;
    }

    public final void setOnSlideUserFailedListener(@Nullable OnSlideUserFailedListener onSlideUserFailedListener2) {
        this.onSlideUserFailedListener = onSlideUserFailedListener2;
    }

    private final VectorDrawableCompat parseVectorDrawableCompat(Resources resources, int i, Theme theme) {
        XmlResourceParser xml = resources.getXml(i);
        XmlPullParser xmlPullParser = xml;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        int next = xml.next();
        while (next != 2 && next != 1) {
            next = xml.next();
        }
        if (next == 2) {
            VectorDrawableCompat createFromXmlInner = VectorDrawableCompat.createFromXmlInner(resources, xmlPullParser, asAttributeSet, theme);
            Intrinsics.checkExpressionValueIsNotNull(createFromXmlInner, "VectorDrawableCompat.cre…es, parser, attrs, theme)");
            return createFromXmlInner;
        }
        throw new XmlPullParserException("No start tag found");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(this.mDesiredSliderWidth, size);
        } else if (mode == 0) {
            size = this.mDesiredSliderWidth;
        } else if (mode != 1073741824) {
            size = this.mDesiredSliderWidth;
        }
        setMeasuredDimension(size, this.mDesiredSliderHeight);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mAreaWidth = i;
        this.mAreaHeight = i2;
        if (this.mBorderRadius == -1) {
            this.mBorderRadius = i2 / 2;
        }
        float f = (float) 2;
        this.mTextXPosition = ((float) this.mAreaWidth) / f;
        this.mTextYPosition = (((float) this.mAreaHeight) / f) - ((this.mTextPaint.descent() + this.mTextPaint.ascent()) / f);
        setMPosition(0);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0041, code lost:
        if (r0 != null) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDraw(@org.jetbrains.annotations.Nullable android.graphics.Canvas r9) {
        /*
            r8 = this;
            super.onDraw(r9)
            if (r9 != 0) goto L_0x0006
            return
        L_0x0006:
            android.graphics.RectF r0 = r8.mOuterRect
            int r1 = r8.mActualAreaWidth
            float r2 = (float) r1
            r3 = 0
            int r4 = r8.mAreaWidth
            float r4 = (float) r4
            float r1 = (float) r1
            float r4 = r4 - r1
            int r1 = r8.mAreaHeight
            float r1 = (float) r1
            r0.set(r2, r3, r4, r1)
            android.graphics.RectF r0 = r8.mOuterRect
            int r1 = r8.mBorderRadius
            float r2 = (float) r1
            float r1 = (float) r1
            android.graphics.Paint r3 = r8.mOuterPaint
            r9.drawRoundRect(r0, r2, r1, r3)
            android.graphics.Paint r0 = r8.mTextPaint
            r1 = 255(0xff, float:3.57E-43)
            float r1 = (float) r1
            float r2 = r8.mPositionPercInv
            float r1 = r1 * r2
            int r1 = (int) r1
            r0.setAlpha(r1)
            android.widget.TextView r0 = r8.mTextView
            android.text.method.TransformationMethod r0 = r0.getTransformationMethod()
            if (r0 == 0) goto L_0x0044
            java.lang.CharSequence r1 = r8.text
            android.widget.TextView r2 = r8.mTextView
            android.view.View r2 = (android.view.View) r2
            java.lang.CharSequence r0 = r0.getTransformation(r1, r2)
            if (r0 == 0) goto L_0x0044
            goto L_0x0046
        L_0x0044:
            java.lang.CharSequence r0 = r8.text
        L_0x0046:
            r2 = r0
            r3 = 0
            int r4 = r2.length()
            float r5 = r8.mTextXPosition
            float r6 = r8.mTextYPosition
            android.graphics.Paint r7 = r8.mTextPaint
            r1 = r9
            r1.drawText(r2, r3, r4, r5, r6, r7)
            int r0 = r8.mAreaHeight
            int r1 = r8.mActualAreaMargin
            int r2 = r1 * 2
            int r2 = r0 - r2
            float r2 = (float) r2
            float r3 = (float) r0
            float r2 = r2 / r3
            android.graphics.RectF r3 = r8.mInnerRect
            int r4 = r8.mEffectivePosition
            int r5 = r1 + r4
            float r5 = (float) r5
            float r6 = (float) r1
            int r4 = r4 + r0
            float r4 = (float) r4
            float r7 = (float) r1
            float r4 = r4 - r7
            float r0 = (float) r0
            float r1 = (float) r1
            float r0 = r0 - r1
            r3.set(r5, r6, r4, r0)
            android.graphics.RectF r0 = r8.mInnerRect
            int r1 = r8.mBorderRadius
            float r3 = (float) r1
            float r3 = r3 * r2
            float r1 = (float) r1
            float r1 = r1 * r2
            android.graphics.Paint r2 = r8.mInnerPaint
            r9.drawRoundRect(r0, r3, r1, r2)
            r9.save()
            boolean r0 = r8.isReversed
            if (r0 == 0) goto L_0x009a
            r0 = 1127481344(0x43340000, float:180.0)
            android.graphics.RectF r1 = r8.mInnerRect
            float r1 = r1.centerX()
            android.graphics.RectF r2 = r8.mInnerRect
            float r2 = r2.centerY()
            r9.rotate(r0, r1, r2)
        L_0x009a:
            boolean r0 = r8.isRotateIcon
            if (r0 == 0) goto L_0x00c2
            r0 = 180(0xb4, float:2.52E-43)
            float r0 = (float) r0
            float r1 = r8.mPositionPerc
            float r0 = r0 * r1
            boolean r1 = r8.isReversed
            if (r1 == 0) goto L_0x00ab
            r1 = 1
            goto L_0x00ac
        L_0x00ab:
            r1 = -1
        L_0x00ac:
            float r1 = (float) r1
            float r0 = r0 * r1
            r8.mArrowAngle = r0
            float r0 = r8.mArrowAngle
            android.graphics.RectF r1 = r8.mInnerRect
            float r1 = r1.centerX()
            android.graphics.RectF r2 = r8.mInnerRect
            float r2 = r2.centerY()
            r9.rotate(r0, r1, r2)
        L_0x00c2:
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r0 = r8.mDrawableArrow
            android.graphics.RectF r1 = r8.mInnerRect
            float r1 = r1.left
            int r1 = (int) r1
            int r2 = r8.mArrowMargin
            int r1 = r1 + r2
            android.graphics.RectF r2 = r8.mInnerRect
            float r2 = r2.top
            int r2 = (int) r2
            int r3 = r8.mArrowMargin
            int r2 = r2 + r3
            android.graphics.RectF r3 = r8.mInnerRect
            float r3 = r3.right
            int r3 = (int) r3
            int r4 = r8.mArrowMargin
            int r3 = r3 - r4
            android.graphics.RectF r4 = r8.mInnerRect
            float r4 = r4.bottom
            int r4 = (int) r4
            int r5 = r8.mArrowMargin
            int r4 = r4 - r5
            r0.setBounds(r1, r2, r3, r4)
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r0 = r8.mDrawableArrow
            android.graphics.Rect r0 = r0.getBounds()
            int r0 = r0.left
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r1 = r8.mDrawableArrow
            android.graphics.Rect r1 = r1.getBounds()
            int r1 = r1.right
            if (r0 > r1) goto L_0x0110
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r0 = r8.mDrawableArrow
            android.graphics.Rect r0 = r0.getBounds()
            int r0 = r0.top
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r1 = r8.mDrawableArrow
            android.graphics.Rect r1 = r1.getBounds()
            int r1 = r1.bottom
            if (r0 > r1) goto L_0x0110
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r0 = r8.mDrawableArrow
            r0.draw(r9)
        L_0x0110:
            r9.restore()
            android.graphics.drawable.Drawable r0 = r8.mDrawableTick
            int r1 = r8.mActualAreaWidth
            int r2 = r8.mTickMargin
            int r3 = r1 + r2
            int r4 = r8.mAreaWidth
            int r4 = r4 - r2
            int r4 = r4 - r1
            int r1 = r8.mAreaHeight
            int r1 = r1 - r2
            r0.setBounds(r3, r2, r4, r1)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x0133
            android.graphics.drawable.Drawable r0 = r8.mDrawableTick
            int r1 = r8.innerColor
            r0.setTint(r1)
            goto L_0x013e
        L_0x0133:
            android.graphics.drawable.Drawable r0 = r8.mDrawableTick
            if (r0 == 0) goto L_0x0148
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r0 = (androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat) r0
            int r1 = r8.innerColor
            r0.setTint(r1)
        L_0x013e:
            boolean r0 = r8.mFlagDrawTick
            if (r0 == 0) goto L_0x0147
            android.graphics.drawable.Drawable r0 = r8.mDrawableTick
            r0.draw(r9)
        L_0x0147:
            return
        L_0x0148:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.support.graphics.drawable.AnimatedVectorDrawableCompat"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ncorti.slidetoact.SlideToActView.onDraw(android.graphics.Canvas):void");
    }

    public boolean performClick() {
        return super.performClick();
    }

    public boolean onTouchEvent(@Nullable MotionEvent motionEvent) {
        if (motionEvent == null || !isEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (checkInsideButton(motionEvent.getX(), motionEvent.getY())) {
                this.mFlagMoving = true;
                this.mLastX = motionEvent.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                OnSlideUserFailedListener onSlideUserFailedListener2 = this.onSlideUserFailedListener;
                if (onSlideUserFailedListener2 != null) {
                    onSlideUserFailedListener2.onSlideFailed(this, true);
                }
            }
            performClick();
        } else if (action == 1) {
            getParent().requestDisallowInterceptTouchEvent(false);
            if ((this.mPosition > 0 && this.isLocked) || (this.mPosition > 0 && this.mPositionPerc < this.mGraceValue)) {
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mPosition, 0});
                Intrinsics.checkExpressionValueIsNotNull(ofInt, "positionAnimator");
                ofInt.setDuration(300);
                ofInt.addUpdateListener(new SlideToActView$onTouchEvent$1(this));
                ofInt.start();
            } else if (this.mPosition > 0 && this.mPositionPerc >= this.mGraceValue) {
                setEnabled(false);
                startAnimationComplete();
            } else if (this.mFlagMoving && this.mPosition == 0) {
                OnSlideUserFailedListener onSlideUserFailedListener3 = this.onSlideUserFailedListener;
                if (onSlideUserFailedListener3 != null) {
                    onSlideUserFailedListener3.onSlideFailed(this, false);
                }
            }
            this.mFlagMoving = false;
        } else if (action == 2 && this.mFlagMoving) {
            float x = motionEvent.getX() - this.mLastX;
            this.mLastX = motionEvent.getX();
            increasePosition((int) x);
            invalidateArea();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final void invalidateArea() {
        invalidate((int) this.mOuterRect.left, (int) this.mOuterRect.top, (int) this.mOuterRect.right, (int) this.mOuterRect.bottom);
    }

    private final boolean checkInsideButton(float f, float f2) {
        if (((float) 0) >= f2) {
            return false;
        }
        int i = this.mAreaHeight;
        if (f2 >= ((float) i)) {
            return false;
        }
        int i2 = this.mEffectivePosition;
        return ((float) i2) < f && f < ((float) (i + i2));
    }

    private final void increasePosition(int i) {
        setMPosition(this.isReversed ? this.mPosition - i : this.mPosition + i);
        if (this.mPosition < 0) {
            setMPosition(0);
        }
        int i2 = this.mPosition;
        int i3 = this.mAreaWidth;
        int i4 = this.mAreaHeight;
        if (i2 > i3 - i4) {
            setMPosition(i3 - i4);
        }
    }

    private final void startAnimationComplete() {
        ValueAnimator valueAnimator;
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mPosition, this.mAreaWidth - this.mAreaHeight});
        ofInt.addUpdateListener(new SlideToActView$startAnimationComplete$1(this));
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{this.mActualAreaMargin, ((int) (this.mInnerRect.width() / ((float) 2))) + this.mActualAreaMargin});
        ofInt2.addUpdateListener(new SlideToActView$startAnimationComplete$2(this));
        Intrinsics.checkExpressionValueIsNotNull(ofInt2, "marginAnimator");
        ofInt2.setInterpolator(new AnticipateOvershootInterpolator(2.0f));
        ValueAnimator ofInt3 = ValueAnimator.ofInt(new int[]{0, (this.mAreaWidth - this.mAreaHeight) / 2});
        ofInt3.addUpdateListener(new SlideToActView$startAnimationComplete$3(this));
        if (VERSION.SDK_INT <= 24) {
            valueAnimator = ValueAnimator.ofInt(new int[]{0, 255});
            Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "ValueAnimator.ofInt(0, 255)");
            valueAnimator.addUpdateListener(new SlideToActView$startAnimationComplete$4(this));
        } else {
            valueAnimator = ValueAnimator.ofInt(new int[]{0});
            Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "ValueAnimator.ofInt(0)");
            valueAnimator.addUpdateListener(new SlideToActView$startAnimationComplete$5(this));
        }
        List arrayList = new ArrayList();
        if (this.mPosition < this.mAreaWidth - this.mAreaHeight) {
            Intrinsics.checkExpressionValueIsNotNull(ofInt, "finalPositionAnimator");
            arrayList.add(ofInt);
        }
        if (this.isAnimateCompletion) {
            arrayList.add(ofInt2);
            Intrinsics.checkExpressionValueIsNotNull(ofInt3, "areaAnimator");
            arrayList.add(ofInt3);
            arrayList.add(valueAnimator);
        }
        Object[] array = arrayList.toArray(new Animator[0]);
        if (array != null) {
            Animator[] animatorArr = (Animator[]) array;
            animatorSet.playSequentially((Animator[]) Arrays.copyOf(animatorArr, animatorArr.length));
            animatorSet.setDuration(300);
            animatorSet.addListener(new SlideToActView$startAnimationComplete$6(this));
            animatorSet.start();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final void completeSlider() {
        if (!this.mIsCompleted) {
            startAnimationComplete();
        }
    }

    public final void resetSlider() {
        if (this.mIsCompleted) {
            startAnimationReset();
        }
    }

    public final boolean isCompleted() {
        return this.mIsCompleted;
    }

    private final void startAnimationReset() {
        this.mIsCompleted = false;
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mTickMargin, this.mAreaWidth / 2});
        ofInt.addUpdateListener(new SlideToActView$startAnimationReset$1(this));
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{this.mActualAreaWidth, 0});
        ofInt2.addUpdateListener(new SlideToActView$startAnimationReset$2(this));
        ValueAnimator ofInt3 = ValueAnimator.ofInt(new int[]{this.mPosition, 0});
        ofInt3.addUpdateListener(new SlideToActView$startAnimationReset$3(this));
        ValueAnimator ofInt4 = ValueAnimator.ofInt(new int[]{this.mActualAreaMargin, this.mOriginAreaMargin});
        ofInt4.addUpdateListener(new SlideToActView$startAnimationReset$4(this));
        Intrinsics.checkExpressionValueIsNotNull(ofInt4, "marginAnimator");
        ofInt4.setInterpolator(new AnticipateOvershootInterpolator(2.0f));
        ValueAnimator ofInt5 = ValueAnimator.ofInt(new int[]{this.mArrowMargin, this.mIconMargin});
        ofInt5.addUpdateListener(new SlideToActView$startAnimationReset$5(this));
        ofInt4.setInterpolator(new OvershootInterpolator(2.0f));
        if (this.isAnimateCompletion) {
            animatorSet.playSequentially(new Animator[]{ofInt, ofInt2, ofInt3, ofInt4, ofInt5});
        } else {
            animatorSet.playSequentially(new Animator[]{ofInt3});
        }
        animatorSet.setDuration(300);
        animatorSet.addListener(new SlideToActView$startAnimationReset$6(this));
        animatorSet.start();
    }

    /* access modifiers changed from: private */
    public final void startTickAnimation() {
        if (VERSION.SDK_INT >= 21) {
            Drawable drawable = this.mDrawableTick;
            if (drawable != null) {
                ((AnimatedVectorDrawable) drawable).start();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
        }
        Drawable drawable2 = this.mDrawableTick;
        if (drawable2 != null) {
            ((AnimatedVectorDrawableCompat) drawable2).start();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.graphics.drawable.AnimatedVectorDrawableCompat");
    }

    /* access modifiers changed from: private */
    public final void stopTickAnimation() {
        if (VERSION.SDK_INT >= 21) {
            Drawable drawable = this.mDrawableTick;
            if (drawable != null) {
                ((AnimatedVectorDrawable) drawable).stop();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
        }
        Drawable drawable2 = this.mDrawableTick;
        if (drawable2 != null) {
            ((AnimatedVectorDrawableCompat) drawable2).stop();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.graphics.drawable.AnimatedVectorDrawableCompat");
    }
}
