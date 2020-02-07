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

public class Alert {
    private static final String NAME = "Alert";

    public static void register(Context context) {
        Leanplum.defineAction(NAME, 3, new ActionArgs().with(Keys.TITLE, MessageTemplates.getApplicationName(context)).with("Message", "Alert message goes here.").with("Dismiss text", "OK").withAction("Dismiss action", null), new ActionCallback() {
            public boolean onResponse(final ActionContext actionContext) {
                LeanplumActivityHelper.queueActionUponActive(new PostponableAction() {
                    public void run() {
                        Activity currentActivity = LeanplumActivityHelper.getCurrentActivity();
                        if (currentActivity != null) {
                            Builder builder = new Builder(currentActivity);
                            builder.setTitle(actionContext.stringNamed(Keys.TITLE)).setMessage(actionContext.stringNamed("Message")).setCancelable(false).setPositiveButton(actionContext.stringNamed("Dismiss text"), new OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    actionContext.runActionNamed("Dismiss action");
                                }
                            });
                            AlertDialog create = builder.create();
                            if (!currentActivity.isFinishing()) {
                                create.show();
                            }
                        }
                    }
                });
                return true;
            }
        });
    }
}
