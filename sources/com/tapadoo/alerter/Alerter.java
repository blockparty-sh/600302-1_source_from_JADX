package com.tapadoo.alerter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.AnimRes;
import androidx.annotation.C0045Px;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.i18n.MessageBundle;
import org.spongycastle.i18n.TextBundle;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 Z2\u00020\u0001:\u0001ZB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0013J\u0006\u0010\u0019\u001a\u00020\u0000J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0013J\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u0006\u0010\u001d\u001a\u00020\u0000J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00002\b\b\u0001\u0010#\u001a\u00020\rJ\u0010\u0010$\u001a\u00020\u00002\b\b\u0001\u0010%\u001a\u00020\rJ\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(J\u0010\u0010)\u001a\u00020\u00002\b\b\u0001\u0010*\u001a\u00020\rJ\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\rJ\u000e\u00100\u001a\u00020\u00002\u0006\u00101\u001a\u00020\u0013J\u000e\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u000204J\u0010\u00105\u001a\u00020\u00002\b\b\u0001\u00106\u001a\u00020\rJ\u0010\u00107\u001a\u00020\u00002\b\b\u0001\u00106\u001a\u00020\rJ\u000e\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020:J\u000e\u00108\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(J\u0010\u00108\u001a\u00020\u00002\b\b\u0001\u0010;\u001a\u00020\rJ\u000e\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>J\u0010\u0010<\u001a\u00020\u00002\b\b\u0001\u0010?\u001a\u00020\rJ\u0018\u0010<\u001a\u00020\u00002\b\b\u0001\u0010?\u001a\u00020\r2\u0006\u0010@\u001a\u00020AJ\u0010\u0010B\u001a\u00020\u00002\b\b\u0001\u0010C\u001a\u00020\rJ\u0010\u0010D\u001a\u00020\u00002\b\b\u0001\u0010C\u001a\u00020\rJ\u000e\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020\u000fJ\u000e\u0010G\u001a\u00020\u00002\u0006\u0010H\u001a\u00020IJ\u000e\u0010J\u001a\u00020\u00002\u0006\u0010H\u001a\u00020KJ\u0010\u0010L\u001a\u00020\u00002\b\b\u0001\u0010?\u001a\u00020\rJ\u0010\u0010M\u001a\u00020\u00002\b\b\u0001\u0010?\u001a\u00020\rJ\u000e\u0010N\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010N\u001a\u00020\u00002\b\b\u0001\u0010O\u001a\u00020\rJ\u0010\u0010P\u001a\u00020\u00002\b\b\u0001\u0010Q\u001a\u00020\rJ\u000e\u0010R\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010T\u001a\u00020\u000bJ\u0010\u0010S\u001a\u00020\u00002\b\b\u0001\u0010U\u001a\u00020\rJ\u0010\u0010V\u001a\u00020\u00002\b\b\u0001\u0010Q\u001a\u00020\rJ\u000e\u0010W\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-J\b\u0010X\u001a\u0004\u0018\u00010\bJ\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010Y\u001a\u00020\u0013R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006["}, mo37405d2 = {"Lcom/tapadoo/alerter/Alerter;", "", "()V", "activityDecorView", "Landroid/view/ViewGroup;", "getActivityDecorView", "()Landroid/view/ViewGroup;", "alert", "Lcom/tapadoo/alerter/Alert;", "addButton", "text", "", "style", "", "onClick", "Landroid/view/View$OnClickListener;", "disableOutsideTouch", "enableIconPulse", "pulse", "", "enableInfiniteDuration", "infiniteDuration", "enableProgress", "enable", "enableSound", "enableSwipeToDismiss", "enableVibration", "getLayoutContainer", "Landroid/view/View;", "hideIcon", "setActivity", "", "activity", "Landroid/app/Activity;", "setBackgroundColorInt", "colorInt", "setBackgroundColorRes", "colorResId", "setBackgroundDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setBackgroundResource", "drawableResId", "setButtonTypeface", "typeface", "Landroid/graphics/Typeface;", "setContentGravity", "gravity", "setDismissable", "dismissable", "setDuration", "milliseconds", "", "setEnterAnimation", "animation", "setExitAnimation", "setIcon", "bitmap", "Landroid/graphics/Bitmap;", "iconId", "setIconColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "color", "mode", "Landroid/graphics/PorterDuff$Mode;", "setIconPixelSize", "size", "setIconSize", "setOnClickListener", "onClickListener", "setOnHideListener", "listener", "Lcom/tapadoo/alerter/OnHideAlertListener;", "setOnShowListener", "Lcom/tapadoo/alerter/OnShowAlertListener;", "setProgressColorInt", "setProgressColorRes", "setText", "textId", "setTextAppearance", "textAppearance", "setTextTypeface", "setTitle", "title", "titleId", "setTitleAppearance", "setTitleTypeface", "show", "showIcon", "Companion", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Alerter.kt */
public final class Alerter {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static WeakReference<Activity> activityWeakReference;
    /* access modifiers changed from: private */
    public Alert alert;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0007J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u0007J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u000bH\u0007R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u00078FX\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\u0006\u0010\t¨\u0006\u0016"}, mo37405d2 = {"Lcom/tapadoo/alerter/Alerter$Companion;", "", "()V", "activityWeakReference", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "isShowing", "", "isShowing$annotations", "()Z", "clearCurrent", "", "activity", "create", "Lcom/tapadoo/alerter/Alerter;", "layoutId", "", "getRemoveViewRunnable", "Ljava/lang/Runnable;", "childView", "Lcom/tapadoo/alerter/Alert;", "hide", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Alerter.kt */
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void isShowing$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Alerter create(@Nullable Activity activity) {
            return create(activity, C2645R.layout.alerter_alert_default_layout);
        }

        @JvmStatic
        @NotNull
        public final Alerter create(@Nullable Activity activity, @LayoutRes int i) {
            if (activity != null) {
                Alerter alerter = new Alerter(null);
                Alerter.Companion.clearCurrent(activity);
                alerter.setActivity(activity);
                Alert alert = new Alert(activity, i, null, 0, 12, null);
                alerter.alert = alert;
                return alerter;
            }
            throw new IllegalArgumentException("Activity cannot be null!");
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0018  */
        /* JADX WARNING: Removed duplicated region for block: B:29:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0013  */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void clearCurrent(@org.jetbrains.annotations.Nullable android.app.Activity r7) {
            /*
                r6 = this;
                r0 = 0
                if (r7 == 0) goto L_0x000e
                android.view.Window r7 = r7.getWindow()
                if (r7 == 0) goto L_0x000e
                android.view.View r7 = r7.getDecorView()
                goto L_0x000f
            L_0x000e:
                r7 = r0
            L_0x000f:
                boolean r1 = r7 instanceof android.view.ViewGroup
                if (r1 != 0) goto L_0x0014
                r7 = r0
            L_0x0014:
                android.view.ViewGroup r7 = (android.view.ViewGroup) r7
                if (r7 == 0) goto L_0x005b
                r1 = 0
                int r2 = r7.getChildCount()
                if (r2 < 0) goto L_0x005b
            L_0x001f:
                android.view.View r3 = r7.getChildAt(r1)
                boolean r3 = r3 instanceof com.tapadoo.alerter.Alert
                if (r3 == 0) goto L_0x0038
                android.view.View r3 = r7.getChildAt(r1)
                if (r3 == 0) goto L_0x0030
                com.tapadoo.alerter.Alert r3 = (com.tapadoo.alerter.Alert) r3
                goto L_0x0039
            L_0x0030:
                kotlin.TypeCastException r7 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type com.tapadoo.alerter.Alert"
                r7.<init>(r0)
                throw r7
            L_0x0038:
                r3 = r0
            L_0x0039:
                if (r3 == 0) goto L_0x0056
                android.os.IBinder r4 = r3.getWindowToken()
                if (r4 == 0) goto L_0x0056
                r4 = r3
                android.view.View r4 = (android.view.View) r4
                androidx.core.view.ViewPropertyAnimatorCompat r4 = androidx.core.view.ViewCompat.animate(r4)
                r5 = 0
                androidx.core.view.ViewPropertyAnimatorCompat r4 = r4.alpha(r5)
                com.tapadoo.alerter.Alerter$Companion r5 = com.tapadoo.alerter.Alerter.Companion
                java.lang.Runnable r3 = r5.getRemoveViewRunnable(r3)
                r4.withEndAction(r3)
            L_0x0056:
                if (r1 == r2) goto L_0x005b
                int r1 = r1 + 1
                goto L_0x001f
            L_0x005b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapadoo.alerter.Alerter.Companion.clearCurrent(android.app.Activity):void");
        }

        @JvmStatic
        public final void hide() {
            WeakReference access$getActivityWeakReference$cp = Alerter.activityWeakReference;
            if (access$getActivityWeakReference$cp != null) {
                Activity activity = (Activity) access$getActivityWeakReference$cp.get();
                if (activity != null) {
                    Alerter.Companion.clearCurrent(activity);
                }
            }
        }

        public final boolean isShowing() {
            WeakReference access$getActivityWeakReference$cp = Alerter.activityWeakReference;
            if (access$getActivityWeakReference$cp == null) {
                return false;
            }
            Activity activity = (Activity) access$getActivityWeakReference$cp.get();
            if (activity == null || activity.findViewById(C2645R.C2648id.llAlertBackground) == null) {
                return false;
            }
            return true;
        }

        private final Runnable getRemoveViewRunnable(Alert alert) {
            return new Alerter$Companion$getRemoveViewRunnable$1(alert);
        }
    }

    @JvmStatic
    public static final void clearCurrent(@Nullable Activity activity) {
        Companion.clearCurrent(activity);
    }

    @JvmStatic
    @NotNull
    public static final Alerter create(@Nullable Activity activity) {
        return Companion.create(activity);
    }

    @JvmStatic
    @NotNull
    public static final Alerter create(@Nullable Activity activity, @LayoutRes int i) {
        return Companion.create(activity, i);
    }

    @JvmStatic
    public static final void hide() {
        Companion.hide();
    }

    public static final boolean isShowing() {
        return Companion.isShowing();
    }

    private Alerter() {
    }

    public /* synthetic */ Alerter(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* access modifiers changed from: private */
    public final ViewGroup getActivityDecorView() {
        ViewGroup viewGroup = null;
        WeakReference<Activity> weakReference = activityWeakReference;
        if (weakReference == null) {
            return viewGroup;
        }
        Activity activity = (Activity) weakReference.get();
        if (activity == null) {
            return viewGroup;
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "it");
        Window window = activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "it.window");
        View decorView = window.getDecorView();
        if (decorView != null) {
            return (ViewGroup) decorView;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    @Nullable
    public final Alert show() {
        WeakReference<Activity> weakReference = activityWeakReference;
        if (weakReference != null) {
            Activity activity = (Activity) weakReference.get();
            if (activity != null) {
                activity.runOnUiThread(new Alerter$show$$inlined$let$lambda$1(this));
            }
        }
        return this.alert;
    }

    @NotNull
    public final Alerter setTitle(@StringRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setTitle(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setTitle(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, MessageBundle.TITLE_ENTRY);
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setTitle(charSequence);
        }
        return this;
    }

    @NotNull
    public final Alerter setTitleTypeface(@NotNull Typeface typeface) {
        Intrinsics.checkParameterIsNotNull(typeface, "typeface");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setTitleTypeface(typeface);
        }
        return this;
    }

    @NotNull
    public final Alerter setTitleAppearance(@StyleRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setTitleAppearance(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setContentGravity(int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setContentGravity(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setText(@StringRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setText(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setText(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, TextBundle.TEXT_ENTRY);
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setText(charSequence);
        }
        return this;
    }

    @NotNull
    public final Alerter setTextTypeface(@NotNull Typeface typeface) {
        Intrinsics.checkParameterIsNotNull(typeface, "typeface");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setTextTypeface(typeface);
        }
        return this;
    }

    @NotNull
    public final Alerter setTextAppearance(@StyleRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setTextAppearance(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setBackgroundColorInt(@ColorInt int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setAlertBackgroundColor(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setBackgroundColorRes(@ColorRes int i) {
        WeakReference<Activity> weakReference = activityWeakReference;
        if (weakReference != null) {
            Activity activity = (Activity) weakReference.get();
            if (activity != null) {
                Alert alert2 = this.alert;
                if (alert2 != null) {
                    alert2.setAlertBackgroundColor(ContextCompat.getColor(activity, i));
                }
            }
        }
        return this;
    }

    @NotNull
    public final Alerter setBackgroundDrawable(@NotNull Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setAlertBackgroundDrawable(drawable);
        }
        return this;
    }

    @NotNull
    public final Alerter setBackgroundResource(@DrawableRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setAlertBackgroundResource(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setIcon(@DrawableRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIcon(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setIcon(@NotNull Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIcon(bitmap);
        }
        return this;
    }

    @NotNull
    public final Alerter setIcon(@NotNull Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIcon(drawable);
        }
        return this;
    }

    @NotNull
    public final Alerter setIconSize(@DimenRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIconSize(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setIconPixelSize(@C0045Px int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIconPixelSize(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setIconColorFilter(@ColorInt int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIconColorFilter(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setIconColorFilter(@NotNull ColorFilter colorFilter) {
        Intrinsics.checkParameterIsNotNull(colorFilter, "colorFilter");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIconColorFilter(colorFilter);
        }
        return this;
    }

    @NotNull
    public final Alerter setIconColorFilter(@ColorInt int i, @NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setIconColorFilter(i, mode);
        }
        return this;
    }

    @NotNull
    public final Alerter hideIcon() {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.showIcon(false);
        }
        return this;
    }

    @NotNull
    public final Alerter setOnClickListener(@NotNull OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(onClickListener, "onClickListener");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setOnClickListener(onClickListener);
        }
        return this;
    }

    @NotNull
    public final Alerter setDuration(long j) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setDuration$alerter_release(j);
        }
        return this;
    }

    @NotNull
    public final Alerter enableIconPulse(boolean z) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.pulseIcon(z);
        }
        return this;
    }

    @NotNull
    public final Alerter showIcon(boolean z) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.showIcon(z);
        }
        return this;
    }

    @NotNull
    public final Alerter enableInfiniteDuration(boolean z) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setEnableInfiniteDuration(z);
        }
        return this;
    }

    @NotNull
    public final Alerter setOnShowListener(@NotNull OnShowAlertListener onShowAlertListener) {
        Intrinsics.checkParameterIsNotNull(onShowAlertListener, CastExtraArgs.LISTENER);
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setOnShowListener(onShowAlertListener);
        }
        return this;
    }

    @NotNull
    public final Alerter setOnHideListener(@NotNull OnHideAlertListener onHideAlertListener) {
        Intrinsics.checkParameterIsNotNull(onHideAlertListener, CastExtraArgs.LISTENER);
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setOnHideListener$alerter_release(onHideAlertListener);
        }
        return this;
    }

    @NotNull
    public final Alerter enableSwipeToDismiss() {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.enableSwipeToDismiss();
        }
        return this;
    }

    @NotNull
    public final Alerter enableVibration(boolean z) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setVibrationEnabled(z);
        }
        return this;
    }

    @NotNull
    public final Alerter enableSound(boolean z) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setSoundEnabled(z);
        }
        return this;
    }

    @NotNull
    public final Alerter disableOutsideTouch() {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.disableOutsideTouch();
        }
        return this;
    }

    @NotNull
    public final Alerter enableProgress(boolean z) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setEnableProgress(z);
        }
        return this;
    }

    @NotNull
    public final Alerter setProgressColorRes(@ColorRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setProgressColorRes(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setProgressColorInt(@ColorInt int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setProgressColorInt(i);
        }
        return this;
    }

    @NotNull
    public final Alerter setDismissable(boolean z) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setDismissible(z);
        }
        return this;
    }

    @NotNull
    public final Alerter setEnterAnimation(@AnimRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(alert2 != null ? alert2.getContext() : null, i);
            Intrinsics.checkExpressionValueIsNotNull(loadAnimation, "AnimationUtils.loadAnima…lert?.context, animation)");
            alert2.setEnterAnimation$alerter_release(loadAnimation);
        }
        return this;
    }

    @NotNull
    public final Alerter setExitAnimation(@AnimRes int i) {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(alert2 != null ? alert2.getContext() : null, i);
            Intrinsics.checkExpressionValueIsNotNull(loadAnimation, "AnimationUtils.loadAnima…lert?.context, animation)");
            alert2.setExitAnimation$alerter_release(loadAnimation);
        }
        return this;
    }

    public static /* synthetic */ Alerter addButton$default(Alerter alerter, CharSequence charSequence, int i, OnClickListener onClickListener, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = C2645R.C2650style.AlertButton;
        }
        return alerter.addButton(charSequence, i, onClickListener);
    }

    @NotNull
    public final Alerter addButton(@NotNull CharSequence charSequence, @StyleRes int i, @NotNull OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(charSequence, TextBundle.TEXT_ENTRY);
        Intrinsics.checkParameterIsNotNull(onClickListener, "onClick");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.addButton(charSequence, i, onClickListener);
        }
        return this;
    }

    @NotNull
    public final Alerter setButtonTypeface(@NotNull Typeface typeface) {
        Intrinsics.checkParameterIsNotNull(typeface, "typeface");
        Alert alert2 = this.alert;
        if (alert2 != null) {
            alert2.setButtonTypeFace(typeface);
        }
        return this;
    }

    @Nullable
    public final View getLayoutContainer() {
        Alert alert2 = this.alert;
        if (alert2 != null) {
            return alert2.getLayoutContainer();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final void setActivity(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
    }
}
