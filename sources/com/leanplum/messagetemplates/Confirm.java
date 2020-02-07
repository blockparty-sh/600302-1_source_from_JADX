package com.leanplum.messagetemplates;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.PostponableAction;
import com.leanplum.internal.Constants.Keys;

class Confirm {
    private static final String NAME = "Confirm";

    Confirm() {
    }

    public static void register(Context context) {
        Leanplum.defineAction(NAME, 3, new ActionArgs().with(Keys.TITLE, MessageTemplates.getApplicationName(context)).with("Message", "Confirmation message goes here.").with("Accept text", "Yes").with("Cancel text", "No").withAction("Accept action", null).withAction("Cancel action", null), new ActionCallback() {
            public boolean onResponse(final ActionContext actionContext) {
                LeanplumActivityHelper.queueActionUponActive(new PostponableAction() {
                    public void run() {
                        Activity currentActivity = LeanplumActivityHelper.getCurrentActivity();
                        Builder builder = new Builder(currentActivity);
                        builder.setTitle(actionContext.stringNamed(Keys.TITLE)).setMessage(actionContext.stringNamed("Message")).setCancelable(false).setPositiveButton(actionContext.stringNamed("Accept text"), new OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                actionContext.runTrackedActionNamed("Accept action");
                            }
                        }).setNegativeButton(actionContext.stringNamed("Cancel text"), new OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                actionContext.runActionNamed("Cancel action");
                            }
                        });
                        AlertDialog create = builder.create();
                        if (currentActivity != null && !currentActivity.isFinishing()) {
                            create.show();
                        }
                    }
                });
                return true;
            }
        });
    }
}
