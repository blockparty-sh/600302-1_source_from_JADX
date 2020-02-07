package com.leanplum.messagetemplates;

import android.app.Activity;
import android.content.Context;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.PostponableAction;
import com.leanplum.callbacks.VariablesChangedCallback;

public class Interstitial extends BaseMessageDialog {
    private static final String NAME = "Interstitial";

    public Interstitial(Activity activity, InterstitialOptions interstitialOptions) {
        super(activity, true, interstitialOptions, null, null);
        this.options = interstitialOptions;
    }

    public static void register(Context context) {
        Leanplum.defineAction(NAME, 3, InterstitialOptions.toArgs(context), new ActionCallback() {
            public boolean onResponse(final ActionContext actionContext) {
                Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
                    public void variablesChanged() {
                        LeanplumActivityHelper.queueActionUponActive(new PostponableAction() {
                            public void run() {
                                Activity currentActivity = LeanplumActivityHelper.getCurrentActivity();
                                if (currentActivity != null) {
                                    Interstitial interstitial = new Interstitial(currentActivity, new InterstitialOptions(actionContext));
                                    if (!currentActivity.isFinishing()) {
                                        interstitial.show();
                                    }
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
