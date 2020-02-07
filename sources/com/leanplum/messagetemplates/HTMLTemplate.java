package com.leanplum.messagetemplates;

import android.app.Activity;
import android.graphics.Point;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.PostponableAction;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.internal.Log;
import com.leanplum.utils.SizeUtil;

public class HTMLTemplate extends BaseMessageDialog {
    private static final String NAME = "HTML";

    public HTMLTemplate(Activity activity, HTMLOptions hTMLOptions) {
        super(activity, hTMLOptions.isFullScreen(), null, null, hTMLOptions);
        this.htmlOptions = hTMLOptions;
    }

    public boolean dispatchTouchEvent(@NonNull MotionEvent motionEvent) {
        int i;
        int i2;
        if (!this.htmlOptions.isFullScreen()) {
            if (isBannerWithTapOutsideFalse(this.htmlOptions)) {
                return super.dispatchTouchEvent(motionEvent);
            }
            Point displaySize = SizeUtil.getDisplaySize(this.activity);
            int width = this.webView.getWidth();
            int i3 = (displaySize.x - width) / 2;
            int i4 = (displaySize.x + width) / 2;
            int dpToPx = SizeUtil.dpToPx(Leanplum.getContext(), this.htmlOptions.getHtmlHeight());
            int statusBarHeight = SizeUtil.getStatusBarHeight(Leanplum.getContext());
            int htmlYOffset = this.htmlOptions.getHtmlYOffset(this.activity);
            if ("Bottom".equals(this.htmlOptions.getHtmlAlign())) {
                i = ((displaySize.y - dpToPx) - statusBarHeight) - htmlYOffset;
                i2 = (displaySize.y - htmlYOffset) - statusBarHeight;
            } else {
                i = htmlYOffset + statusBarHeight;
                i2 = dpToPx + statusBarHeight + htmlYOffset;
            }
            if (motionEvent.getY() < ((float) i) || motionEvent.getY() > ((float) i2) || motionEvent.getX() < ((float) i3) || motionEvent.getX() > ((float) i4)) {
                if (this.htmlOptions.isHtmlTabOutsideToClose()) {
                    cancel();
                }
                this.activity.dispatchTouchEvent(motionEvent);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public static void register() {
        Leanplum.defineAction(NAME, 3, HTMLOptions.toArgs(), new ActionCallback() {
            public boolean onResponse(final ActionContext actionContext) {
                Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
                    public void variablesChanged() {
                        LeanplumActivityHelper.queueActionUponActive(new PostponableAction() {
                            public void run() {
                                try {
                                    HTMLOptions hTMLOptions = new HTMLOptions(actionContext);
                                    if (hTMLOptions.getHtmlTemplate() != null) {
                                        Activity currentActivity = LeanplumActivityHelper.getCurrentActivity();
                                        if (currentActivity != null && !currentActivity.isFinishing()) {
                                            new HTMLTemplate(currentActivity, hTMLOptions);
                                        }
                                    }
                                } catch (Throwable th) {
                                    Log.m280e("Leanplum", "Fail on show HTML In-App message.", th);
                                }
                            }
                        });
                    }
                });
                return true;
            }
        });
    }
}
