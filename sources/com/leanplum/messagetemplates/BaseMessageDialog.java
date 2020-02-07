package com.leanplum.messagetemplates;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.stetho.common.Utf8Charset;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.core.C2350R;
import com.leanplum.internal.Constants.Params;
import com.leanplum.utils.BitmapUtil;
import com.leanplum.utils.SizeUtil;
import com.leanplum.views.BackgroundImageView;
import com.leanplum.views.CloseButton;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import org.json.JSONObject;

public class BaseMessageDialog extends Dialog {
    protected Activity activity;
    protected RelativeLayout dialogView;
    protected HTMLOptions htmlOptions;
    /* access modifiers changed from: private */
    public boolean isClosing = false;
    /* access modifiers changed from: private */
    public boolean isHtml = false;
    private boolean isWeb = false;
    protected BaseMessageOptions options;
    protected WebInterstitialOptions webOptions;
    protected WebView webView;

    protected BaseMessageDialog(Activity activity2, boolean z, BaseMessageOptions baseMessageOptions, WebInterstitialOptions webInterstitialOptions, HTMLOptions hTMLOptions) {
        super(activity2, getTheme(activity2));
        SizeUtil.init(activity2);
        this.activity = activity2;
        this.options = baseMessageOptions;
        this.webOptions = webInterstitialOptions;
        this.htmlOptions = hTMLOptions;
        if (webInterstitialOptions != null) {
            this.isWeb = true;
        }
        if (hTMLOptions != null) {
            this.isHtml = true;
        }
        this.dialogView = new RelativeLayout(activity2);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.dialogView.setBackgroundColor(0);
        this.dialogView.setLayoutParams(layoutParams);
        RelativeLayout createContainerView = createContainerView(activity2, z);
        createContainerView.setId(C2350R.C2353id.container_view);
        this.dialogView.addView(createContainerView, createContainerView.getLayoutParams());
        if ((!this.isWeb || (webInterstitialOptions != null && webInterstitialOptions.hasDismissButton())) && !this.isHtml) {
            CloseButton createCloseButton = createCloseButton(activity2, z, createContainerView);
            this.dialogView.addView(createCloseButton, createCloseButton.getLayoutParams());
        }
        RelativeLayout relativeLayout = this.dialogView;
        setContentView(relativeLayout, relativeLayout.getLayoutParams());
        this.dialogView.setAnimation(createFadeInAnimation());
        if (!z) {
            Window window = getWindow();
            if (window != null) {
                if (!this.isHtml) {
                    window.addFlags(2);
                    if (VERSION.SDK_INT >= 14) {
                        window.setDimAmount(0.7f);
                    }
                } else {
                    window.clearFlags(2);
                    if (hTMLOptions == null || !isBannerWithTapOutsideFalse(hTMLOptions)) {
                        window.setFlags(32, 32);
                    } else {
                        window.setLayout(-1, -2);
                        window.setGravity(48);
                        WindowManager.LayoutParams attributes = window.getAttributes();
                        attributes.y = hTMLOptions.getHtmlYOffset(activity2);
                        window.setAttributes(attributes);
                        window.setFlags(40, 40);
                    }
                    if (hTMLOptions != null) {
                        if ("Bottom".equals(hTMLOptions.getHtmlAlign())) {
                            if (isBannerWithTapOutsideFalse(hTMLOptions)) {
                                window.setGravity(80);
                            } else {
                                this.dialogView.setGravity(80);
                            }
                        }
                    }
                }
            }
        }
    }

    protected static boolean isBannerWithTapOutsideFalse(HTMLOptions hTMLOptions) {
        return hTMLOptions.getActionContext().getArgs().get(Values.HTML_TEMPLATE_PREFIX).toString().toLowerCase().contains("banner") && !hTMLOptions.isHtmlTabOutsideToClose();
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            if (this.webView != null) {
                if (z) {
                    this.webView.onResume();
                } else {
                    this.webView.onPause();
                }
            }
        } catch (Throwable unused) {
        }
        super.onWindowFocusChanged(z);
    }

    private Animation createFadeInAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setDuration(350);
        return alphaAnimation;
    }

    private Animation createFadeOutAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(350);
        return alphaAnimation;
    }

    public void cancel() {
        if (!this.isClosing) {
            this.isClosing = true;
            Animation createFadeOutAnimation = createFadeOutAnimation();
            createFadeOutAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    BaseMessageDialog.super.cancel();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (BaseMessageDialog.this.isHtml && BaseMessageDialog.this.webView != null) {
                                BaseMessageDialog.this.webView.stopLoading();
                                BaseMessageDialog.this.webView.loadUrl("");
                                if (BaseMessageDialog.this.dialogView != null) {
                                    BaseMessageDialog.this.dialogView.removeAllViews();
                                }
                                BaseMessageDialog.this.webView.removeAllViews();
                                BaseMessageDialog.this.webView.destroy();
                            }
                        }
                    }, 10);
                }
            });
            this.dialogView.startAnimation(createFadeOutAnimation);
        }
    }

    private CloseButton createCloseButton(Activity activity2, boolean z, View view) {
        CloseButton closeButton = new CloseButton(activity2);
        closeButton.setId(C2350R.C2353id.close_button);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (z) {
            layoutParams.addRule(10, this.dialogView.getId());
            layoutParams.addRule(11, this.dialogView.getId());
            layoutParams.setMargins(0, SizeUtil.dp5, SizeUtil.dp5, 0);
        } else {
            layoutParams.addRule(6, view.getId());
            layoutParams.addRule(7, view.getId());
            layoutParams.setMargins(0, -SizeUtil.dp7, -SizeUtil.dp7, 0);
        }
        closeButton.setLayoutParams(layoutParams);
        closeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseMessageDialog.this.cancel();
            }
        });
        return closeButton;
    }

    private RelativeLayout createContainerView(Activity activity2, boolean z) {
        LayoutParams layoutParams;
        int i;
        int i2;
        RelativeLayout relativeLayout = new RelativeLayout(activity2);
        if (z) {
            layoutParams = new LayoutParams(-1, -1);
        } else if (this.isHtml) {
            int dpToPx = SizeUtil.dpToPx(activity2, this.htmlOptions.getHtmlHeight());
            Size htmlWidth = this.htmlOptions.getHtmlWidth();
            if (htmlWidth == null || TextUtils.isEmpty(htmlWidth.type)) {
                layoutParams = new LayoutParams(-1, dpToPx);
            } else {
                int i3 = htmlWidth.value;
                if ("%".equals(htmlWidth.type)) {
                    i2 = (SizeUtil.getDisplaySize(activity2).x * i3) / 100;
                } else {
                    i2 = SizeUtil.dpToPx(activity2, i3);
                }
                layoutParams = new LayoutParams(i2, dpToPx);
            }
            layoutParams.addRule(14, -1);
            int htmlYOffset = this.htmlOptions.getHtmlYOffset(activity2);
            if (!isBannerWithTapOutsideFalse(this.htmlOptions)) {
                if ("Bottom".equals(this.htmlOptions.getHtmlAlign())) {
                    layoutParams.bottomMargin = htmlYOffset;
                } else {
                    layoutParams.topMargin = htmlYOffset;
                }
            }
        } else {
            Point displaySize = SizeUtil.getDisplaySize(activity2);
            int dpToPx2 = SizeUtil.dpToPx(activity2, ((CenterPopupOptions) this.options).getWidth());
            int dpToPx3 = SizeUtil.dpToPx(activity2, ((CenterPopupOptions) this.options).getHeight());
            int i4 = displaySize.x - SizeUtil.dp20;
            int i5 = displaySize.y - SizeUtil.dp20;
            double d = (double) dpToPx2;
            double d2 = d / ((double) dpToPx3);
            if (dpToPx2 > i4 && ((int) (d / d2)) < i5) {
                dpToPx3 = (int) (((double) i4) / d2);
                dpToPx2 = i4;
            }
            if (dpToPx3 <= i5 || ((int) (((double) dpToPx3) * d2)) >= i4) {
                i5 = dpToPx3;
            } else {
                dpToPx2 = (int) (((double) i5) * d2);
            }
            LayoutParams layoutParams2 = new LayoutParams(dpToPx2, i5);
            layoutParams2.addRule(13, -1);
            layoutParams = layoutParams2;
        }
        relativeLayout.setLayoutParams(layoutParams);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        if (z) {
            i = 0;
        } else {
            i = SizeUtil.dp20;
        }
        shapeDrawable.setShape(createRoundRect(i));
        shapeDrawable.getPaint().setColor(0);
        if (VERSION.SDK_INT >= 16) {
            relativeLayout.setBackground(shapeDrawable);
        } else {
            relativeLayout.setBackgroundDrawable(shapeDrawable);
        }
        if (!this.isWeb && !this.isHtml) {
            ImageView createBackgroundImageView = createBackgroundImageView(activity2, z);
            relativeLayout.addView(createBackgroundImageView, createBackgroundImageView.getLayoutParams());
            RelativeLayout createTitleView = createTitleView(activity2);
            createTitleView.setId(C2350R.C2353id.title_view);
            relativeLayout.addView(createTitleView, createTitleView.getLayoutParams());
            TextView createAcceptButton = createAcceptButton(activity2);
            createAcceptButton.setId(C2350R.C2353id.accept_button);
            relativeLayout.addView(createAcceptButton, createAcceptButton.getLayoutParams());
            TextView createMessageView = createMessageView(activity2);
            ((LayoutParams) createMessageView.getLayoutParams()).addRule(3, createTitleView.getId());
            ((LayoutParams) createMessageView.getLayoutParams()).addRule(2, createAcceptButton.getId());
            relativeLayout.addView(createMessageView, createMessageView.getLayoutParams());
        } else if (this.isWeb) {
            WebView createWebView = createWebView(activity2);
            relativeLayout.addView(createWebView, createWebView.getLayoutParams());
        } else {
            this.webView = createHtml(activity2);
            WebView webView2 = this.webView;
            relativeLayout.addView(webView2, webView2.getLayoutParams());
        }
        return relativeLayout;
    }

    private Shape createRoundRect(int i) {
        float f = (float) i;
        return new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null);
    }

    private ImageView createBackgroundImageView(Context context, boolean z) {
        BackgroundImageView backgroundImageView = new BackgroundImageView(context, z);
        backgroundImageView.setScaleType(ScaleType.CENTER_CROP);
        int i = !z ? SizeUtil.dp20 : 0;
        backgroundImageView.setImageBitmap(this.options.getBackgroundImage());
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(createRoundRect(i));
        shapeDrawable.getPaint().setColor(this.options.getBackgroundColor());
        if (VERSION.SDK_INT >= 16) {
            backgroundImageView.setBackground(shapeDrawable);
        } else {
            backgroundImageView.setBackgroundDrawable(shapeDrawable);
        }
        backgroundImageView.setLayoutParams(new LayoutParams(-1, -1));
        return backgroundImageView;
    }

    private RelativeLayout createTitleView(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        TextView textView = new TextView(context);
        textView.setPadding(0, SizeUtil.dp5, 0, SizeUtil.dp5);
        textView.setGravity(17);
        textView.setText(this.options.getTitle());
        textView.setTextColor(this.options.getTitleColor());
        textView.setTextSize(2, 20.0f);
        textView.setTypeface(null, 1);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(14, -1);
        layoutParams.addRule(15, -1);
        textView.setLayoutParams(layoutParams);
        relativeLayout.addView(textView, textView.getLayoutParams());
        return relativeLayout;
    }

    private TextView createMessageView(Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(-1, -1));
        textView.setGravity(17);
        textView.setText(this.options.getMessageText());
        textView.setTextColor(this.options.getMessageColor());
        textView.setTextSize(2, 18.0f);
        return textView;
    }

    private WebView createWebView(Context context) {
        WebView webView2 = new WebView(context);
        webView2.setLayoutParams(new LayoutParams(-1, -1));
        webView2.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.contains(BaseMessageDialog.this.webOptions.getCloseUrl())) {
                    return false;
                }
                BaseMessageDialog.this.cancel();
                String[] split = str.split("\\?");
                if (split.length > 1) {
                    for (String split2 : split[1].split("&")) {
                        String[] split3 = split2.split("=");
                        if (split3.length > 1 && split3[0].equals("result")) {
                            Leanplum.track(split3[1]);
                        }
                    }
                }
                return true;
            }
        });
        webView2.loadUrl(this.webOptions.getUrl());
        return webView2;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private WebView createHtml(Context context) {
        this.dialogView.setVisibility(8);
        WebView webView2 = new WebView(context);
        webView2.setBackgroundColor(0);
        webView2.setVerticalScrollBarEnabled(false);
        webView2.setHorizontalScrollBarEnabled(false);
        webView2.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return motionEvent.getAction() == 2;
            }
        });
        webView2.canGoBack();
        webView2.setLongClickable(false);
        webView2.setHapticFeedbackEnabled(false);
        webView2.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                return true;
            }
        });
        WebSettings settings = webView2.getSettings();
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(false);
        webView2.setLayoutParams(new LayoutParams(-1, -1));
        webView2.setWebChromeClient(new WebChromeClient());
        webView2.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.contains(BaseMessageDialog.this.htmlOptions.getOpenUrl())) {
                    BaseMessageDialog.this.dialogView.setVisibility(0);
                    if (BaseMessageDialog.this.activity != null && !BaseMessageDialog.this.activity.isFinishing()) {
                        this.show();
                    }
                    return true;
                } else if (str.contains(BaseMessageDialog.this.htmlOptions.getCloseUrl())) {
                    BaseMessageDialog.this.cancel();
                    String access$200 = BaseMessageDialog.this.queryComponentsFromUrl(str, "result");
                    if (!TextUtils.isEmpty(access$200)) {
                        Leanplum.track(access$200);
                    }
                    return true;
                } else if (str.contains(BaseMessageDialog.this.htmlOptions.getTrackUrl())) {
                    String access$2002 = BaseMessageDialog.this.queryComponentsFromUrl(str, "event");
                    if (!TextUtils.isEmpty(access$2002)) {
                        Double valueOf = Double.valueOf(Double.parseDouble(BaseMessageDialog.this.queryComponentsFromUrl(str, "value")));
                        String access$2003 = BaseMessageDialog.this.queryComponentsFromUrl(str, Params.INFO);
                        Map map = null;
                        try {
                            map = ActionContext.mapFromJson(new JSONObject(BaseMessageDialog.this.queryComponentsFromUrl(str, "parameters")));
                        } catch (Exception unused) {
                        }
                        Map map2 = map;
                        if (BaseMessageDialog.this.queryComponentsFromUrl(str, "isMessageEvent").equals("true")) {
                            BaseMessageDialog.this.htmlOptions.getActionContext().trackMessageEvent(access$2002, valueOf.doubleValue(), access$2003, map2);
                        } else {
                            Leanplum.track(access$2002, valueOf.doubleValue(), access$2003, map2);
                        }
                    }
                    return true;
                } else if (!str.contains(BaseMessageDialog.this.htmlOptions.getActionUrl()) && !str.contains(BaseMessageDialog.this.htmlOptions.getTrackActionUrl())) {
                    return false;
                } else {
                    BaseMessageDialog.this.cancel();
                    String access$2004 = BaseMessageDialog.this.queryComponentsFromUrl(str, "action");
                    try {
                        access$2004 = URLDecoder.decode(access$2004, Utf8Charset.NAME);
                    } catch (UnsupportedEncodingException unused2) {
                    }
                    ActionContext actionContext = BaseMessageDialog.this.htmlOptions.getActionContext();
                    if (!TextUtils.isEmpty(access$2004) && actionContext != null) {
                        if (str.contains(BaseMessageDialog.this.htmlOptions.getActionUrl())) {
                            actionContext.runActionNamed(access$2004);
                        } else {
                            actionContext.runTrackedActionNamed(access$2004);
                        }
                    }
                    return true;
                }
            }
        });
        webView2.loadDataWithBaseURL(null, this.htmlOptions.getHtmlTemplate(), "text/html", Utf8Charset.NAME, null);
        return webView2;
    }

    /* access modifiers changed from: private */
    public String queryComponentsFromUrl(String str, String str2) {
        String[] split = str.split("\\?");
        String str3 = "";
        if (split.length > 1) {
            String str4 = str3;
            for (String split2 : split[1].split("&")) {
                String[] split3 = split2.split("=");
                if (split3.length > 1 && split3[0].equals(str2)) {
                    str4 = split3[1];
                }
            }
            str3 = str4;
        }
        try {
            return URLDecoder.decode(str3, Utf8Charset.NAME);
        } catch (Exception unused) {
            return str3;
        }
    }

    private TextView createAcceptButton(Context context) {
        TextView textView = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(12, -1);
        layoutParams.addRule(14, -1);
        layoutParams.setMargins(0, 0, 0, SizeUtil.dp5);
        textView.setPadding(SizeUtil.dp20, SizeUtil.dp5, SizeUtil.dp20, SizeUtil.dp5);
        textView.setLayoutParams(layoutParams);
        textView.setText(this.options.getAcceptButtonText());
        textView.setTextColor(this.options.getAcceptButtonTextColor());
        textView.setTypeface(null, 1);
        BitmapUtil.stateBackgroundDarkerByPercentage(textView, this.options.getAcceptButtonBackgroundColor(), 30);
        textView.setTextSize(2, 18.0f);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!BaseMessageDialog.this.isClosing) {
                    BaseMessageDialog.this.options.accept();
                    BaseMessageDialog.this.cancel();
                }
            }
        });
        return textView;
    }

    private static int getTheme(Activity activity2) {
        return (activity2.getWindow().getAttributes().flags & 1024) == 1024 ? 16973841 : 16973840;
    }
}
