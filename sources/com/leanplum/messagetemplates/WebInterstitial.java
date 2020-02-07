package com.leanplum.messagetemplates;

import android.app.Activity;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.PostponableAction;

public class WebInterstitial extends BaseMessageDialog {
    private static final String NAME = "Web Interstitial";

    public WebInterstitial(Activity activity, WebInterstitialOptions webInterstitialOptions) {
        super(activity, true, null, webInterstitialOptions, null);
        this.webOptions = webInterstitialOptions;
    }

    public static void register() {
        Leanplum.defineAction(NAME, 3, WebInterstitialOptions.toArgs(), new ActionCallback() {
            public boolean onResponse(final ActionContext actionContext) {
                LeanplumActivityHelper.queueActionUponActive(new PostponableAction() {
                    public void run() {
                        Activity currentActivity = LeanplumActivityHelper.getCurrentActivity();
                        if (currentActivity != null) {
                            WebInterstitial webInterstitial = new WebInterstitial(currentActivity, new WebInterstitialOptions(actionContext));
                            if (!currentActivity.isFinishing()) {
                                webInterstitial.show();
                            }
                        }
                    }
                });
                return true;
            }
        });
    }
}
