package com.htc.htcwalletsdk.Act;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import com.htc.htcwalletsdk.C2271R;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Utils.ZKMALog;

public class UITemplateAct extends BaseResultCallbackAct {
    private static final String TAG = "WalletSecure_UITemplateAct";
    int activityResultValue = RESULT.E_TEEKM_FAILURE;
    public ImageButton mBackButton;
    Callback mCallback;
    private TextView mDescription;
    private FrameLayout mDynamicContent;
    /* access modifiers changed from: private */
    public ImageView mImageView;
    private FrameLayout mKeyboardView;
    /* access modifiers changed from: private */
    public LinearLayout mLinearLayout;
    private ProgressBar mProgBar1;
    private ProgressBar mProgBar2;
    private ProgressBar mProgBar3;
    private RelativeLayout mRelativeLayout;
    /* access modifiers changed from: private */
    public ScrollView mScrollView;
    private TextView mSubtitle;
    private TextView mTitle;
    /* access modifiers changed from: private */
    public Button nextBtn;

    public interface Callback {
        void onBackButtonPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2271R.layout.activity_ui_template);
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setSoftInputMode(3);
        if (VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ContextCompat.getColor(this, 17170445));
        } else if (VERSION.SDK_INT >= 19) {
            window.setFlags(67108864, 67108864);
        }
        window.getDecorView().setSystemUiVisibility(1280);
        this.mRelativeLayout = (RelativeLayout) findViewById(C2271R.C2274id.main_layout);
        this.mScrollView = (ScrollView) findViewById(C2271R.C2274id.scroll_view);
        this.mTitle = (TextView) findViewById(C2271R.C2274id.title);
        this.mDescription = (TextView) findViewById(C2271R.C2274id.description);
        this.mSubtitle = (TextView) findViewById(C2271R.C2274id.subtitle);
        this.mProgBar1 = (ProgressBar) findViewById(C2271R.C2274id.progressBar1);
        this.mProgBar2 = (ProgressBar) findViewById(C2271R.C2274id.progressBar2);
        this.mProgBar3 = (ProgressBar) findViewById(C2271R.C2274id.progressBar3);
        this.mBackButton = (ImageButton) findViewById(C2271R.C2274id.backButton);
        this.mDynamicContent = (FrameLayout) findViewById(C2271R.C2274id.dynamic_content);
        this.mKeyboardView = (FrameLayout) findViewById(C2271R.C2274id.keyboard);
        unlimitTextline();
        this.mBackButton.setOnClickListener(new OnClickListener() {
            @SuppressLint({"LongLogTag"})
            public void onClick(View view) {
                String str = UITemplateAct.TAG;
                ZKMALog.m272d(str, "onClick++");
                UITemplateAct.this.onBackPressed();
                ZKMALog.m272d(str, "onClick--");
            }
        });
    }

    @SuppressLint({"LongLogTag"})
    public void finish(int i) {
        ZKMALog.m272d(TAG, "finish()");
        this.activityResultValue = i;
        finish();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"LongLogTag"})
    public void onStop() {
        ZKMALog.m272d(TAG, "onStop()");
        super.onStop();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"LongLogTag"})
    public void onDestroy() {
        ZKMALog.m272d(TAG, "onDestroy()");
        if (sResultCallback != null) {
            if (this.activityResultValue == 0) {
                sResultCallback.makeSuccess();
                sResultCallback = null;
            } else {
                sResultCallback.makeFailure(this.activityResultValue);
                sResultCallback = null;
            }
        }
        super.onDestroy();
    }

    private void unlimitTextline() {
        this.mTitle.setMaxLines(Integer.MAX_VALUE);
        this.mTitle.setMinLines(0);
        this.mDescription.setMaxLines(Integer.MAX_VALUE);
        this.mDescription.setMinLines(0);
    }

    public void setViewTitle(String str) {
        this.mTitle.setText(str);
    }

    public void setViewTitle(int i) {
        this.mTitle.setText(i);
    }

    public void setViewDescription(int i) {
        this.mDescription.setText(i);
    }

    public void setViewDescriptionVisible() {
        this.mDescription.setVisibility(0);
    }

    public void setViewDescriptionInvisible() {
        this.mDescription.setVisibility(4);
    }

    public void setViewDescriptionCenter() {
        this.mDescription.setGravity(17);
    }

    public void setViewDescriptionStart() {
        this.mDescription.setGravity(GravityCompat.START);
    }

    public void setViewSubtitleVisible() {
        this.mSubtitle.setVisibility(0);
    }

    public void setViewSubtitle(int i) {
        this.mSubtitle.setText(i);
    }

    public void setFrameLayout(View view) {
        this.mDynamicContent.removeAllViews();
        this.mDynamicContent.addView(view);
    }

    public void setKeyboardView(View view) {
        ((LayoutParams) this.mScrollView.getLayoutParams()).addRule(2, C2271R.C2274id.keyboard);
        this.mKeyboardView.removeAllViews();
        this.mKeyboardView.addView(view);
    }

    public void removeKeyboardView() {
        this.mKeyboardView.removeAllViews();
    }

    public boolean checkKeyboardView() {
        return this.mKeyboardView.getChildCount() == 1;
    }

    public void setProgressvalue(int i, int i2, int i3) {
        this.mProgBar1.setProgress(i);
        this.mProgBar2.setProgress(i2);
        this.mProgBar3.setProgress(i3);
    }

    public void setProgressVisible() {
        this.mProgBar1.setVisibility(0);
        this.mProgBar2.setVisibility(0);
        this.mProgBar3.setVisibility(0);
    }

    public void setProgressInvisible() {
        this.mProgBar1.setVisibility(4);
        this.mProgBar2.setVisibility(4);
        this.mProgBar3.setVisibility(4);
    }

    public void setBackButtonInvisible() {
        this.mBackButton.setVisibility(4);
    }

    private float convertDpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public void setNavigationBarInvisible() {
        getWindow().getDecorView().setSystemUiVisibility(5378);
    }

    public void setFrameLayoutBelowSubtitle() {
        ((LayoutParams) this.mDynamicContent.getLayoutParams()).addRule(3, C2271R.C2274id.subtitle);
    }

    public void fullScrollBottom() {
        this.mScrollView.post(new Runnable() {
            public void run() {
                UITemplateAct.this.mScrollView.scrollTo(0, UITemplateAct.this.mScrollView.getBottom());
            }
        });
    }

    public void setBackButtonOnClickListener(Callback callback) {
        this.mCallback = callback;
        this.mBackButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = UITemplateAct.TAG;
                ZKMALog.m272d(str, "onClick++");
                UITemplateAct.this.mCallback.onBackButtonPressed();
                ZKMALog.m272d(str, "onClick--");
            }
        });
    }

    public void avoidTruncatedRKI() {
        this.mRelativeLayout.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                UITemplateAct uITemplateAct = UITemplateAct.this;
                uITemplateAct.mImageView = (ImageView) uITemplateAct.findViewById(C2271R.C2274id.recoveryKey_image);
                UITemplateAct uITemplateAct2 = UITemplateAct.this;
                uITemplateAct2.nextBtn = (Button) uITemplateAct2.findViewById(C2271R.C2274id.next_button);
                try {
                    int dimensionPixelOffset = UITemplateAct.this.getResources().getDimensionPixelOffset(C2271R.dimen.common_margin_m1);
                    if (UITemplateAct.this.nextBtn.getTop() - UITemplateAct.this.mImageView.getBottom() < dimensionPixelOffset) {
                        LayoutParams layoutParams = (LayoutParams) UITemplateAct.this.nextBtn.getLayoutParams();
                        layoutParams.removeRule(12);
                        layoutParams.addRule(3, C2271R.C2274id.recoveryKey_image);
                        layoutParams.setMargins(layoutParams.leftMargin, dimensionPixelOffset, layoutParams.rightMargin, layoutParams.bottomMargin);
                        UITemplateAct.this.nextBtn.setLayoutParams(layoutParams);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void avoidTruncatedWRK() {
        this.mRelativeLayout.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                UITemplateAct uITemplateAct = UITemplateAct.this;
                uITemplateAct.mLinearLayout = (LinearLayout) uITemplateAct.findViewById(C2271R.C2274id.LinearLayoutRoot);
                UITemplateAct uITemplateAct2 = UITemplateAct.this;
                uITemplateAct2.nextBtn = (Button) uITemplateAct2.findViewById(C2271R.C2274id.next_button);
                try {
                    int dimensionPixelOffset = UITemplateAct.this.getResources().getDimensionPixelOffset(C2271R.dimen.common_margin_m1);
                    if (UITemplateAct.this.nextBtn.getTop() - UITemplateAct.this.mLinearLayout.getBottom() < dimensionPixelOffset) {
                        LayoutParams layoutParams = (LayoutParams) UITemplateAct.this.nextBtn.getLayoutParams();
                        layoutParams.removeRule(12);
                        layoutParams.addRule(3, C2271R.C2274id.LinearLayoutRoot);
                        layoutParams.setMargins(layoutParams.leftMargin, dimensionPixelOffset, layoutParams.rightMargin, layoutParams.bottomMargin);
                        UITemplateAct.this.nextBtn.setLayoutParams(layoutParams);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
