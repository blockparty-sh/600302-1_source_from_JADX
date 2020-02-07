package com.bitcoin.mwallet.app.flows.receive.receive;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.ReceiveActivity;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.Coin;
import com.google.android.gms.common.util.GmsVersion;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010B\u001a\u00020CH\u0002J\u0006\u0010D\u001a\u00020CJ\b\u0010E\u001a\u00020CH\u0002J\u0006\u0010F\u001a\u00020CJ\u0006\u0010G\u001a\u00020CJ\b\u0010H\u001a\u00020CH\u0002J\u001a\u0010I\u001a\u00020C2\b\u0010J\u001a\u0004\u0018\u00010\u00072\u0006\u0010K\u001a\u00020LH\u0016J\u0012\u0010M\u001a\u00020C2\b\u0010N\u001a\u0004\u0018\u00010OH\u0016J\b\u0010P\u001a\u00020CH\u0016J\b\u0010Q\u001a\u00020CH\u0016J\u0012\u0010R\u001a\u00020C2\b\u0010S\u001a\u0004\u0018\u00010TH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR\u001e\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010*\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0017\"\u0004\b-\u0010\u0019R\u001a\u0010.\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u000b\"\u0004\b0\u0010\rR\u001a\u00101\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u000b\"\u0004\b3\u0010\rR\u001a\u00104\u001a\u000205X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u000b\"\u0004\b<\u0010\rR\u001a\u0010=\u001a\u000205X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u00107\"\u0004\b?\u00109R\u000e\u0010@\u001a\u00020AX.¢\u0006\u0002\n\u0000¨\u0006U"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveBuilder;", "Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceivePresenter;", "Landroid/hardware/SensorEventListener;", "()V", "accelerometer", "Landroid/hardware/Sensor;", "bottomSheetSlideDown", "Landroid/animation/ObjectAnimator;", "getBottomSheetSlideDown", "()Landroid/animation/ObjectAnimator;", "setBottomSheetSlideDown", "(Landroid/animation/ObjectAnimator;)V", "bottomSheetSlideUp", "getBottomSheetSlideUp", "setBottomSheetSlideUp", "centerQrCard", "getCenterQrCard", "setCenterQrCard", "flipPhoneAnimations", "Landroid/animation/AnimatorSet;", "getFlipPhoneAnimations", "()Landroid/animation/AnimatorSet;", "setFlipPhoneAnimations", "(Landroid/animation/AnimatorSet;)V", "geomagnetic", "", "getGeomagnetic", "()[F", "setGeomagnetic", "([F)V", "gravity", "getGravity", "setGravity", "lastPitchValue", "", "getLastPitchValue", "()Ljava/lang/Double;", "setLastPitchValue", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "magnetometer", "returnPhoneAnimations", "getReturnPhoneAnimations", "setReturnPhoneAnimations", "returnQrCard", "getReturnQrCard", "setReturnQrCard", "reverseRotate", "getReverseRotate", "setReverseRotate", "reverseRotationInProgress", "", "getReverseRotationInProgress", "()Z", "setReverseRotationInProgress", "(Z)V", "rotate", "getRotate", "setRotate", "rotationInProgress", "getRotationInProgress", "setRotationInProgress", "sensorManager", "Landroid/hardware/SensorManager;", "bindActions", "", "bindAnimations", "bindDataObservers", "bindMotionSensors", "bindRadioButtons", "initPreferredId", "onAccuracyChanged", "p0", "p1", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
public final class ReceiveView extends ScreenView<ReceiveBuilder, ReceivePresenter> implements SensorEventListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public Sensor accelerometer;
    @NotNull
    public ObjectAnimator bottomSheetSlideDown;
    @NotNull
    public ObjectAnimator bottomSheetSlideUp;
    @NotNull
    public ObjectAnimator centerQrCard;
    @NotNull
    public AnimatorSet flipPhoneAnimations;
    @Nullable
    private float[] geomagnetic;
    @Nullable
    private float[] gravity;
    @Nullable
    private Double lastPitchValue;
    /* access modifiers changed from: private */
    public Sensor magnetometer;
    @NotNull
    public AnimatorSet returnPhoneAnimations;
    @NotNull
    public ObjectAnimator returnQrCard;
    @NotNull
    public ObjectAnimator reverseRotate;
    private boolean reverseRotationInProgress;
    @NotNull
    public ObjectAnimator rotate;
    private boolean rotationInProgress;
    private SensorManager sensorManager;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[WalletType.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$1[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$1[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$2[WalletType.BCH.ordinal()] = 1;
            $EnumSwitchMapping$2[WalletType.BTC.ordinal()] = 2;
            $EnumSwitchMapping$2[WalletType.SLP.ordinal()] = 3;
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

    public void onAccuracyChanged(@Nullable Sensor sensor, int i) {
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ Sensor access$getAccelerometer$p(ReceiveView receiveView) {
        Sensor sensor = receiveView.accelerometer;
        if (sensor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accelerometer");
        }
        return sensor;
    }

    public static final /* synthetic */ Sensor access$getMagnetometer$p(ReceiveView receiveView) {
        Sensor sensor = receiveView.magnetometer;
        if (sensor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("magnetometer");
        }
        return sensor;
    }

    public ReceiveView() {
        super(C1018R.layout.fragment_screen_receive_receive, Reflection.getOrCreateKotlinClass(ReceiveBuilder.class));
    }

    @Nullable
    public final float[] getGravity() {
        return this.gravity;
    }

    public final void setGravity(@Nullable float[] fArr) {
        this.gravity = fArr;
    }

    @Nullable
    public final float[] getGeomagnetic() {
        return this.geomagnetic;
    }

    public final void setGeomagnetic(@Nullable float[] fArr) {
        this.geomagnetic = fArr;
    }

    public final boolean getRotationInProgress() {
        return this.rotationInProgress;
    }

    public final void setRotationInProgress(boolean z) {
        this.rotationInProgress = z;
    }

    public final boolean getReverseRotationInProgress() {
        return this.reverseRotationInProgress;
    }

    public final void setReverseRotationInProgress(boolean z) {
        this.reverseRotationInProgress = z;
    }

    @Nullable
    public final Double getLastPitchValue() {
        return this.lastPitchValue;
    }

    public final void setLastPitchValue(@Nullable Double d) {
        this.lastPitchValue = d;
    }

    @NotNull
    public final AnimatorSet getFlipPhoneAnimations() {
        AnimatorSet animatorSet = this.flipPhoneAnimations;
        if (animatorSet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("flipPhoneAnimations");
        }
        return animatorSet;
    }

    public final void setFlipPhoneAnimations(@NotNull AnimatorSet animatorSet) {
        Intrinsics.checkParameterIsNotNull(animatorSet, "<set-?>");
        this.flipPhoneAnimations = animatorSet;
    }

    @NotNull
    public final AnimatorSet getReturnPhoneAnimations() {
        AnimatorSet animatorSet = this.returnPhoneAnimations;
        if (animatorSet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("returnPhoneAnimations");
        }
        return animatorSet;
    }

    public final void setReturnPhoneAnimations(@NotNull AnimatorSet animatorSet) {
        Intrinsics.checkParameterIsNotNull(animatorSet, "<set-?>");
        this.returnPhoneAnimations = animatorSet;
    }

    @NotNull
    public final ObjectAnimator getRotate() {
        ObjectAnimator objectAnimator = this.rotate;
        if (objectAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate");
        }
        return objectAnimator;
    }

    public final void setRotate(@NotNull ObjectAnimator objectAnimator) {
        Intrinsics.checkParameterIsNotNull(objectAnimator, "<set-?>");
        this.rotate = objectAnimator;
    }

    @NotNull
    public final ObjectAnimator getBottomSheetSlideDown() {
        ObjectAnimator objectAnimator = this.bottomSheetSlideDown;
        if (objectAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheetSlideDown");
        }
        return objectAnimator;
    }

    public final void setBottomSheetSlideDown(@NotNull ObjectAnimator objectAnimator) {
        Intrinsics.checkParameterIsNotNull(objectAnimator, "<set-?>");
        this.bottomSheetSlideDown = objectAnimator;
    }

    @NotNull
    public final ObjectAnimator getReverseRotate() {
        ObjectAnimator objectAnimator = this.reverseRotate;
        if (objectAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reverseRotate");
        }
        return objectAnimator;
    }

    public final void setReverseRotate(@NotNull ObjectAnimator objectAnimator) {
        Intrinsics.checkParameterIsNotNull(objectAnimator, "<set-?>");
        this.reverseRotate = objectAnimator;
    }

    @NotNull
    public final ObjectAnimator getBottomSheetSlideUp() {
        ObjectAnimator objectAnimator = this.bottomSheetSlideUp;
        if (objectAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheetSlideUp");
        }
        return objectAnimator;
    }

    public final void setBottomSheetSlideUp(@NotNull ObjectAnimator objectAnimator) {
        Intrinsics.checkParameterIsNotNull(objectAnimator, "<set-?>");
        this.bottomSheetSlideUp = objectAnimator;
    }

    @NotNull
    public final ObjectAnimator getCenterQrCard() {
        ObjectAnimator objectAnimator = this.centerQrCard;
        if (objectAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerQrCard");
        }
        return objectAnimator;
    }

    public final void setCenterQrCard(@NotNull ObjectAnimator objectAnimator) {
        Intrinsics.checkParameterIsNotNull(objectAnimator, "<set-?>");
        this.centerQrCard = objectAnimator;
    }

    @NotNull
    public final ObjectAnimator getReturnQrCard() {
        ObjectAnimator objectAnimator = this.returnQrCard;
        if (objectAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("returnQrCard");
        }
        return objectAnimator;
    }

    public final void setReturnQrCard(@NotNull ObjectAnimator objectAnimator) {
        Intrinsics.checkParameterIsNotNull(objectAnimator, "<set-?>");
        this.returnQrCard = objectAnimator;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ((ReceivePresenter) getPresenter()).initialize();
        bindActions();
        bindRadioButtons();
        bindDataObservers();
        bindAnimations();
        bindMotionSensors();
        if (!((ReceivePresenter) getPresenter()).getInitializedPreferredId()) {
            initPreferredId();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void initPreferredId() {
        /*
            r7 = this;
            androidx.fragment.app.FragmentActivity r0 = r7.getActivity()
            r1 = 0
            if (r0 == 0) goto L_0x0014
            android.content.Intent r0 = r0.getIntent()
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "wallet_id"
            java.io.Serializable r0 = r0.getSerializableExtra(r2)
            goto L_0x0015
        L_0x0014:
            r0 = r1
        L_0x0015:
            boolean r2 = r0 instanceof com.bitcoin.mwallet.core.models.wallet.WalletId
            if (r2 != 0) goto L_0x001a
            r0 = r1
        L_0x001a:
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r0
            if (r0 == 0) goto L_0x00de
            com.bitcoin.mwallet.app.viper.ScreenPresenter r1 = r7.getPresenter()
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r1 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r1
            com.bitcoin.mwallet.core.models.Coin r1 = r1.getWalletCoin(r0)
            if (r1 != 0) goto L_0x002c
            goto L_0x00d5
        L_0x002c:
            int[] r2 = com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r2[r1]
            r2 = 2131361932(0x7f0a008c, float:1.834363E38)
            r3 = 2131362412(0x7f0a026c, float:1.8344604E38)
            r4 = 1
            r5 = 8
            if (r1 == r4) goto L_0x00a1
            r6 = 2
            if (r1 == r6) goto L_0x0044
            goto L_0x00d5
        L_0x0044:
            android.view.View r1 = r7.getView()
            if (r1 == 0) goto L_0x0058
            android.view.View r1 = r1.findViewById(r3)
            android.widget.TextView r1 = (android.widget.TextView) r1
            if (r1 == 0) goto L_0x0058
            r3 = 2131951920(0x7f130130, float:1.9540268E38)
            r1.setText(r3)
        L_0x0058:
            com.bitcoin.mwallet.app.viper.ScreenPresenter r1 = r7.getPresenter()
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r1 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r1
            androidx.lifecycle.MutableLiveData r1 = r1.getCurrentWalletType()
            com.bitcoin.mwallet.core.entity.WalletType r3 = com.bitcoin.mwallet.core.entity.WalletType.BTC
            r1.postValue(r3)
            android.view.View r1 = r7.getView()
            if (r1 == 0) goto L_0x007b
            r3 = 2131361929(0x7f0a0089, float:1.8343624E38)
            android.view.View r1 = r1.findViewById(r3)
            android.widget.RadioButton r1 = (android.widget.RadioButton) r1
            if (r1 == 0) goto L_0x007b
            r1.setVisibility(r5)
        L_0x007b:
            android.view.View r1 = r7.getView()
            if (r1 == 0) goto L_0x008f
            r3 = 2131362562(0x7f0a0302, float:1.8344908E38)
            android.view.View r1 = r1.findViewById(r3)
            android.widget.RadioButton r1 = (android.widget.RadioButton) r1
            if (r1 == 0) goto L_0x008f
            r1.setVisibility(r5)
        L_0x008f:
            android.view.View r1 = r7.getView()
            if (r1 == 0) goto L_0x00d5
            android.view.View r1 = r1.findViewById(r2)
            android.widget.RadioButton r1 = (android.widget.RadioButton) r1
            if (r1 == 0) goto L_0x00d5
            r1.setChecked(r4)
            goto L_0x00d5
        L_0x00a1:
            android.view.View r1 = r7.getView()
            if (r1 == 0) goto L_0x00b5
            android.view.View r1 = r1.findViewById(r3)
            android.widget.TextView r1 = (android.widget.TextView) r1
            if (r1 == 0) goto L_0x00b5
            r3 = 2131951919(0x7f13012f, float:1.9540266E38)
            r1.setText(r3)
        L_0x00b5:
            com.bitcoin.mwallet.app.viper.ScreenPresenter r1 = r7.getPresenter()
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r1 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r1
            androidx.lifecycle.MutableLiveData r1 = r1.getCurrentWalletType()
            com.bitcoin.mwallet.core.entity.WalletType r3 = com.bitcoin.mwallet.core.entity.WalletType.BCH
            r1.postValue(r3)
            android.view.View r1 = r7.getView()
            if (r1 == 0) goto L_0x00d5
            android.view.View r1 = r1.findViewById(r2)
            android.widget.RadioButton r1 = (android.widget.RadioButton) r1
            if (r1 == 0) goto L_0x00d5
            r1.setVisibility(r5)
        L_0x00d5:
            com.bitcoin.mwallet.app.viper.ScreenPresenter r1 = r7.getPresenter()
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r1 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r1
            r1.setPreferredId(r0)
        L_0x00de:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView.initPreferredId():void");
    }

    public final void bindAnimations() {
        View view = getView();
        LinearLayout linearLayout = null;
        ConstraintLayout constraintLayout = view != null ? (ConstraintLayout) view.findViewById(C1018R.C1021id.qrCodeDisplay) : null;
        View view2 = getView();
        LinearLayout linearLayout2 = view2 != null ? (LinearLayout) view2.findViewById(C1018R.C1021id.receiveLayoutBottomFrame) : null;
        View view3 = getView();
        if (view3 != null) {
            linearLayout = (LinearLayout) view3.findViewById(C1018R.C1021id.topBarContainer);
        }
        String str = "rotation";
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(constraintLayout, str, new float[]{0.0f, 180.0f});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat, "ObjectAnimator.ofFloat(q…r , \"rotation\", 0f, 180f)");
        this.rotate = ofFloat;
        ObjectAnimator objectAnimator = this.rotate;
        String str2 = "rotate";
        if (objectAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator objectAnimator2 = this.rotate;
        if (objectAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        objectAnimator2.setDuration(200);
        String str3 = "alpha";
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(linearLayout, str3, new float[]{0.0f, 1.0f});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat2, "fadeIn");
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat2.setDuration(200);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(linearLayout, str3, new float[]{1.0f, 0.0f});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat3, "fadeOut");
        ofFloat3.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat3.setDuration(200);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(constraintLayout, str, new float[]{180.0f, 0.0f});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat4, "ObjectAnimator.ofFloat(q…r , \"rotation\", 180f, 0f)");
        this.reverseRotate = ofFloat4;
        ObjectAnimator objectAnimator3 = this.reverseRotate;
        String str4 = "reverseRotate";
        if (objectAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str4);
        }
        objectAnimator3.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator objectAnimator4 = this.reverseRotate;
        if (objectAnimator4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str4);
        }
        objectAnimator4.setDuration(200);
        View view4 = getView();
        if (view4 != null) {
            ReceiveView$bindAnimations$1 receiveView$bindAnimations$1 = new ReceiveView$bindAnimations$1(this, constraintLayout, linearLayout2, ofFloat3, ofFloat2);
            view4.post(receiveView$bindAnimations$1);
        }
    }

    private final void bindActions() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.receiveAddress);
            if (textView != null) {
                textView.setOnClickListener(new ReceiveView$bindActions$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.legacyAddrTextView);
            if (textView2 != null) {
                textView2.setOnClickListener(new ReceiveView$bindActions$2(this));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            ImageView imageView = (ImageView) view3.findViewById(C1018R.C1021id.shareImageView);
            if (imageView != null) {
                imageView.setOnClickListener(new ReceiveView$bindActions$3(this));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            ImageView imageView2 = (ImageView) view4.findViewById(C1018R.C1021id.copyAddressImageView);
            if (imageView2 != null) {
                imageView2.setOnClickListener(new ReceiveView$bindActions$4(this));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            ImageView imageView3 = (ImageView) view5.findViewById(C1018R.C1021id.qrCodeImage);
            if (imageView3 != null) {
                imageView3.setOnClickListener(new ReceiveView$bindActions$5(this));
            }
        }
        View view6 = getView();
        if (view6 != null) {
            ImageView imageView4 = (ImageView) view6.findViewById(C1018R.C1021id.generateNewReceiveAddressButton);
            if (imageView4 != null) {
                imageView4.setOnClickListener(new ReceiveView$bindActions$6(this));
            }
        }
        View view7 = getView();
        if (view7 != null) {
            TextView textView3 = (TextView) view7.findViewById(C1018R.C1021id.editAmountButton);
            if (textView3 != null) {
                textView3.setOnClickListener(new ReceiveView$bindActions$7(this));
            }
        }
        View view8 = getView();
        if (view8 != null) {
            ImageView imageView5 = (ImageView) view8.findViewById(C1018R.C1021id.closeButton);
            if (imageView5 != null) {
                imageView5.setOnClickListener(new ReceiveView$bindActions$8(this));
            }
        }
        View view9 = getView();
        if (view9 != null) {
            LinearLayout linearLayout = (LinearLayout) view9.findViewById(C1018R.C1021id.targetWallet);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new ReceiveView$bindActions$9(this));
            }
        }
    }

    public final void bindRadioButtons() {
        View view = getView();
        RadioButton radioButton = null;
        RadioButton radioButton2 = view != null ? (RadioButton) view.findViewById(C1018R.C1021id.bitcoinCashFilterButton) : null;
        View view2 = getView();
        RadioButton radioButton3 = view2 != null ? (RadioButton) view2.findViewById(C1018R.C1021id.bitcoinFilterButton) : null;
        View view3 = getView();
        if (view3 != null) {
            radioButton = (RadioButton) view3.findViewById(C1018R.C1021id.slpFilterButton);
        }
        if (radioButton2 != null) {
            radioButton2.setChecked(true);
        }
        if (radioButton2 != null) {
            radioButton2.setOnCheckedChangeListener(new ReceiveView$bindRadioButtons$1(this));
        }
        if (radioButton3 != null) {
            radioButton3.setOnCheckedChangeListener(new ReceiveView$bindRadioButtons$2(this));
        }
        if (radioButton != null) {
            radioButton.setOnCheckedChangeListener(new ReceiveView$bindRadioButtons$3(this));
        }
        if (radioButton2 != null) {
            radioButton2.setOnTouchListener(new ReceiveView$bindRadioButtons$4(this));
        }
        if (radioButton3 != null) {
            radioButton3.setOnTouchListener(new ReceiveView$bindRadioButtons$5(this));
        }
        if (radioButton != null) {
            radioButton.setOnTouchListener(new ReceiveView$bindRadioButtons$6(this));
        }
    }

    private final void bindDataObservers() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((ReceiveActivity) activity).getGlobalReceiveModel().getAmount().observe(getViewLifecycleOwner(), new ReceiveView$bindDataObservers$1(this));
            ((ReceivePresenter) getPresenter()).getAvailableWallets().observe(getViewLifecycleOwner(), new ReceiveView$bindDataObservers$2(this));
            ((ReceivePresenter) getPresenter()).getQrCode().observe(getViewLifecycleOwner(), new ReceiveView$bindDataObservers$3(this));
            ((ReceivePresenter) getPresenter()).getSelectedWalletId().observe(getViewLifecycleOwner(), new ReceiveView$bindDataObservers$4(this));
            ((ReceivePresenter) getPresenter()).getCurrentWalletType().observe(getViewLifecycleOwner(), new ReceiveView$bindDataObservers$5(this));
            ((ReceivePresenter) getPresenter()).getDisplayedAddress().observe(getViewLifecycleOwner(), new ReceiveView$bindDataObservers$6(this));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ReceiveActivity");
    }

    public final void bindMotionSensors() {
        FragmentActivity activity = getActivity();
        Object systemService = activity != null ? activity.getSystemService("sensor") : null;
        if (systemService != null) {
            this.sensorManager = (SensorManager) systemService;
            SensorManager sensorManager2 = this.sensorManager;
            String str = "sensorManager";
            if (sensorManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str);
            }
            Sensor defaultSensor = sensorManager2.getDefaultSensor(1);
            if (defaultSensor != null) {
                this.accelerometer = defaultSensor;
            }
            SensorManager sensorManager3 = this.sensorManager;
            if (sensorManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str);
            }
            Sensor defaultSensor2 = sensorManager3.getDefaultSensor(2);
            if (defaultSensor2 != null) {
                this.magnetometer = defaultSensor2;
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.SensorManager");
    }

    public void onSensorChanged(@Nullable SensorEvent sensorEvent) {
        if (!(sensorEvent == null || sensorEvent.values == null)) {
            Sensor sensor = sensorEvent.sensor;
            Intrinsics.checkExpressionValueIsNotNull(sensor, "event.sensor");
            int type = sensor.getType();
            if (type == 1) {
                this.gravity = sensorEvent.values;
            } else if (type == 2) {
                this.geomagnetic = sensorEvent.values;
            }
            float[] fArr = new float[9];
            float[] fArr2 = this.gravity;
            if (fArr2 != null) {
                float[] fArr3 = this.geomagnetic;
                if (fArr3 != null) {
                    ConstraintLayout constraintLayout = null;
                    if (SensorManager.getRotationMatrix(fArr, null, fArr2, fArr3)) {
                        float[] fArr4 = new float[9];
                        SensorManager.getOrientation(fArr, fArr4);
                        double degrees = Math.toDegrees((double) fArr4[1]);
                        View view = getView();
                        if (view != null) {
                            constraintLayout = (ConstraintLayout) view.findViewById(C1018R.C1021id.qrCodeDisplay);
                        }
                        Double d = this.lastPitchValue;
                        if (d == null) {
                            this.lastPitchValue = Double.valueOf(degrees);
                            return;
                        }
                        if (d == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Math.abs(degrees - d.doubleValue()) >= 0.3d) {
                            this.lastPitchValue = Double.valueOf(degrees);
                            if (degrees >= ((double) 10) && constraintLayout != null && constraintLayout.getRotation() == 0.0f) {
                                AnimatorSet animatorSet = this.flipPhoneAnimations;
                                if (animatorSet == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("flipPhoneAnimations");
                                }
                                animatorSet.start();
                                this.rotationInProgress = true;
                            } else if (degrees <= ((double) 0) && constraintLayout != null && constraintLayout.getRotation() == 180.0f) {
                                AnimatorSet animatorSet2 = this.returnPhoneAnimations;
                                if (animatorSet2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("returnPhoneAnimations");
                                }
                                animatorSet2.start();
                                this.reverseRotationInProgress = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void onPause() {
        super.onPause();
        SensorManager sensorManager2 = this.sensorManager;
        if (sensorManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sensorManager");
        }
        sensorManager2.unregisterListener(this);
    }

    public void onResume() {
        super.onResume();
        ReceiveView receiveView = this;
        if (receiveView.accelerometer != null && receiveView.magnetometer != null) {
            SensorManager sensorManager2 = this.sensorManager;
            String str = "sensorManager";
            if (sensorManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str);
            }
            SensorEventListener sensorEventListener = this;
            Sensor sensor = this.accelerometer;
            if (sensor == null) {
                Intrinsics.throwUninitializedPropertyAccessException("accelerometer");
            }
            sensorManager2.registerListener(sensorEventListener, sensor, GmsVersion.VERSION_MANCHEGO);
            SensorManager sensorManager3 = this.sensorManager;
            if (sensorManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str);
            }
            Sensor sensor2 = this.magnetometer;
            if (sensor2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("magnetometer");
            }
            sensorManager3.registerListener(sensorEventListener, sensor2, GmsVersion.VERSION_MANCHEGO);
        }
    }
}
