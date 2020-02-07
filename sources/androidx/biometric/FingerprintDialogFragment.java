package androidx.biometric;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import org.bitcoinj.core.PeerGroup;
import org.spongycastle.i18n.MessageBundle;

@SuppressLint({"SyntheticAccessor"})
@RestrictTo({Scope.LIBRARY})
public class FingerprintDialogFragment extends DialogFragment {
    static final int DISPLAYED_FOR_500_MS = 6;
    private static final String KEY_DIALOG_BUNDLE = "SavedBundle";
    private static final int MESSAGE_DISPLAY_TIME_MS = 2000;
    static final int MSG_DISMISS_DIALOG_AUTHENTICATED = 5;
    static final int MSG_DISMISS_DIALOG_ERROR = 3;
    static final int MSG_RESET_MESSAGE = 4;
    static final int MSG_SHOW_ERROR = 2;
    static final int MSG_SHOW_HELP = 1;
    private static final int STATE_FINGERPRINT = 1;
    private static final int STATE_FINGERPRINT_AUTHENTICATED = 3;
    private static final int STATE_FINGERPRINT_ERROR = 2;
    private static final int STATE_NONE = 0;
    private static final String TAG = "FingerprintDialogFrag";
    /* access modifiers changed from: private */
    public Bundle mBundle;
    private Context mContext;
    /* access modifiers changed from: private */
    public final OnClickListener mDeviceCredentialButtonListener = new OnClickListener() {
        public void onClick(final DialogInterface dialogInterface, int i) {
            if (i == -2) {
                int i2 = VERSION.SDK_INT;
                String str = FingerprintDialogFragment.TAG;
                if (i2 < 21) {
                    Log.e(str, "Failed to check device credential. Not supported prior to L.");
                    return;
                }
                C0179Utils.launchDeviceCredentialConfirmation(str, FingerprintDialogFragment.this.getActivity(), FingerprintDialogFragment.this.mBundle, new Runnable() {
                    public void run() {
                        FingerprintDialogFragment.this.onCancel(dialogInterface);
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mDismissInstantly = true;
    private int mErrorColor;
    private TextView mErrorText;
    private ImageView mFingerprintIcon;
    private C0167H mHandler = new C0167H();
    private int mLastState;
    @VisibleForTesting
    OnClickListener mNegativeButtonListener;
    private int mTextColor;

    /* renamed from: androidx.biometric.FingerprintDialogFragment$H */
    final class C0167H extends Handler {
        C0167H() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    FingerprintDialogFragment.this.handleShowHelp((CharSequence) message.obj);
                    return;
                case 2:
                    FingerprintDialogFragment.this.handleShowError((CharSequence) message.obj);
                    return;
                case 3:
                    FingerprintDialogFragment.this.handleDismissDialogError((CharSequence) message.obj);
                    return;
                case 4:
                    FingerprintDialogFragment.this.handleResetMessage();
                    return;
                case 5:
                    FingerprintDialogFragment.this.dismissSafely();
                    return;
                case 6:
                    Context context = FingerprintDialogFragment.this.getContext();
                    FingerprintDialogFragment.this.mDismissInstantly = context != null && C0179Utils.shouldHideFingerprintDialog(context, Build.MODEL);
                    return;
                default:
                    return;
            }
        }
    }

    private boolean shouldAnimateForTransition(int i, int i2) {
        if (i == 0 && i2 == 1) {
            return false;
        }
        if (i == 1 && i2 == 2) {
            return true;
        }
        if (i == 2 && i2 == 1) {
            return true;
        }
        if (i != 1 || i2 == 3) {
        }
        return false;
    }

    static FingerprintDialogFragment newInstance() {
        return new FingerprintDialogFragment();
    }

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        CharSequence charSequence;
        if (bundle != null && this.mBundle == null) {
            this.mBundle = bundle.getBundle(KEY_DIALOG_BUNDLE);
        }
        Builder builder = new Builder(getContext());
        builder.setTitle(this.mBundle.getCharSequence(MessageBundle.TITLE_ENTRY));
        View inflate = LayoutInflater.from(builder.getContext()).inflate(C0173R.layout.fingerprint_dialog_layout, null);
        TextView textView = (TextView) inflate.findViewById(C0173R.C0176id.fingerprint_subtitle);
        TextView textView2 = (TextView) inflate.findViewById(C0173R.C0176id.fingerprint_description);
        CharSequence charSequence2 = this.mBundle.getCharSequence("subtitle");
        if (TextUtils.isEmpty(charSequence2)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(charSequence2);
        }
        CharSequence charSequence3 = this.mBundle.getCharSequence("description");
        if (TextUtils.isEmpty(charSequence3)) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setText(charSequence3);
        }
        this.mFingerprintIcon = (ImageView) inflate.findViewById(C0173R.C0176id.fingerprint_icon);
        this.mErrorText = (TextView) inflate.findViewById(C0173R.C0176id.fingerprint_error);
        if (isDeviceCredentialAllowed()) {
            charSequence = getString(C0173R.string.confirm_device_credential_password);
        } else {
            charSequence = this.mBundle.getCharSequence("negative_text");
        }
        builder.setNegativeButton(charSequence, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (FingerprintDialogFragment.this.isDeviceCredentialAllowed()) {
                    FingerprintDialogFragment.this.mDeviceCredentialButtonListener.onClick(dialogInterface, i);
                } else if (FingerprintDialogFragment.this.mNegativeButtonListener != null) {
                    FingerprintDialogFragment.this.mNegativeButtonListener.onClick(dialogInterface, i);
                } else {
                    Log.w(FingerprintDialogFragment.TAG, "No suitable negative button listener.");
                }
            }
        });
        builder.setView(inflate);
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle(KEY_DIALOG_BUNDLE, this.mBundle);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getContext();
        if (VERSION.SDK_INT >= 26) {
            this.mErrorColor = getThemedColorFor(16844099);
        } else {
            this.mErrorColor = ContextCompat.getColor(this.mContext, C0173R.C0174color.biometric_error_color);
        }
        this.mTextColor = getThemedColorFor(16842808);
    }

    public void onResume() {
        super.onResume();
        this.mLastState = 0;
        updateFingerprintIcon(1);
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void onCancel(@NonNull DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        FingerprintHelperFragment fingerprintHelperFragment = (FingerprintHelperFragment) getFragmentManager().findFragmentByTag("FingerprintHelperFragment");
        if (fingerprintHelperFragment != null) {
            fingerprintHelperFragment.cancel(1);
        }
    }

    public void setBundle(@NonNull Bundle bundle) {
        this.mBundle = bundle;
    }

    private int getThemedColorFor(int i) {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i, typedValue, true);
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(typedValue.data, new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public CharSequence getNegativeButtonText() {
        return this.mBundle.getCharSequence("negative_text");
    }

    /* access modifiers changed from: 0000 */
    public void setNegativeButtonListener(OnClickListener onClickListener) {
        this.mNegativeButtonListener = onClickListener;
    }

    /* access modifiers changed from: 0000 */
    public Handler getHandler() {
        return this.mHandler;
    }

    /* access modifiers changed from: 0000 */
    public void dismissSafely() {
        if (getFragmentManager() == null) {
            Log.e(TAG, "Failed to dismiss fingerprint dialog fragment. Fragment manager was null.");
        } else {
            dismissAllowingStateLoss();
        }
    }

    static int getHideDialogDelay(Context context) {
        return (context == null || !C0179Utils.shouldHideFingerprintDialog(context, Build.MODEL)) ? 2000 : 0;
    }

    /* access modifiers changed from: private */
    public boolean isDeviceCredentialAllowed() {
        return this.mBundle.getBoolean("allow_device_credential");
    }

    @RequiresApi(21)
    private Drawable getAnimationForTransition(int i, int i2) {
        int i3;
        if (i == 0 && i2 == 1) {
            i3 = C0173R.C0175drawable.fingerprint_dialog_fp_to_error;
        } else if (i == 1 && i2 == 2) {
            i3 = C0173R.C0175drawable.fingerprint_dialog_fp_to_error;
        } else if (i == 2 && i2 == 1) {
            i3 = C0173R.C0175drawable.fingerprint_dialog_error_to_fp;
        } else if (i != 1 || i2 != 3) {
            return null;
        } else {
            i3 = C0173R.C0175drawable.fingerprint_dialog_error_to_fp;
        }
        return this.mContext.getDrawable(i3);
    }

    private void updateFingerprintIcon(int i) {
        if (this.mFingerprintIcon != null && VERSION.SDK_INT >= 23) {
            Drawable animationForTransition = getAnimationForTransition(this.mLastState, i);
            if (animationForTransition != null) {
                AnimatedVectorDrawable animatedVectorDrawable = animationForTransition instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) animationForTransition : null;
                this.mFingerprintIcon.setImageDrawable(animationForTransition);
                if (animatedVectorDrawable != null && shouldAnimateForTransition(this.mLastState, i)) {
                    animatedVectorDrawable.start();
                }
                this.mLastState = i;
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleShowHelp(CharSequence charSequence) {
        updateFingerprintIcon(2);
        this.mHandler.removeMessages(4);
        TextView textView = this.mErrorText;
        if (textView != null) {
            textView.setTextColor(this.mErrorColor);
            this.mErrorText.setText(charSequence);
        }
        C0167H h = this.mHandler;
        h.sendMessageDelayed(h.obtainMessage(4), PeerGroup.DEFAULT_PING_INTERVAL_MSEC);
    }

    /* access modifiers changed from: private */
    public void handleShowError(CharSequence charSequence) {
        updateFingerprintIcon(2);
        this.mHandler.removeMessages(4);
        TextView textView = this.mErrorText;
        if (textView != null) {
            textView.setTextColor(this.mErrorColor);
            this.mErrorText.setText(charSequence);
        }
        C0167H h = this.mHandler;
        h.sendMessageDelayed(h.obtainMessage(3), (long) getHideDialogDelay(this.mContext));
    }

    private void dismissAfterDelay(CharSequence charSequence) {
        TextView textView = this.mErrorText;
        if (textView != null) {
            textView.setTextColor(this.mErrorColor);
            if (charSequence != null) {
                this.mErrorText.setText(charSequence);
            } else {
                this.mErrorText.setText(C0173R.string.fingerprint_error_lockout);
            }
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                FingerprintDialogFragment.this.dismissSafely();
            }
        }, (long) getHideDialogDelay(this.mContext));
    }

    /* access modifiers changed from: private */
    public void handleDismissDialogError(CharSequence charSequence) {
        if (this.mDismissInstantly) {
            dismissSafely();
        } else {
            dismissAfterDelay(charSequence);
        }
        this.mDismissInstantly = true;
    }

    /* access modifiers changed from: private */
    public void handleResetMessage() {
        updateFingerprintIcon(1);
        TextView textView = this.mErrorText;
        if (textView != null) {
            textView.setTextColor(this.mTextColor);
            this.mErrorText.setText(this.mContext.getString(C0173R.string.fingerprint_dialog_touch_sensor));
        }
    }
}
