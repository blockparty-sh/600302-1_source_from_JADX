package com.leanplum.messagetemplates;

import android.app.Activity;
import android.content.Context;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.PostponableAction;
import com.leanplum.callbacks.VariablesChangedCallback;

public class CenterPopup extends BaseMessageDialog {
    private static final String NAME = "Center Popup";

    public CenterPopup(Activity activity, CenterPopupOptions centerPopupOptions) {
        super(activity, false, centerPopupOptions, null, null);
        this.options = centerPopupOptions;
    }

    public static void register(Context context) {
        Leanplum.defineAction(NAME, 3, CenterPopupOptions.toArgs(context), new ActionCallback() {
            public boolean onResponse(final ActionContext actionContext) {
                Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
                    public void variablesChanged() {
                        LeanplumActivityHelper.queueActionUponActive(new PostponableAction() {
                            public void run() {
                                Activity currentActivity = LeanplumActivityHelper.getCurrentActivity();
                                if (currentActivity != null) {
                                    CenterPopup centerPopup = new CenterPopup(currentActivity, new CenterPopupOptions(actionContext));
                                    if (!currentActivity.isFinishing()) {
                                        centerPopup.show();
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
