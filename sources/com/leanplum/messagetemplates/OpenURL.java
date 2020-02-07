package com.leanplum.messagetemplates;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.PostponableAction;
import java.util.List;

class OpenURL {
    private static final String NAME = "Open URL";

    OpenURL() {
    }

    public static void register() {
        Leanplum.defineAction(NAME, 2, new ActionArgs().with("URL", "http://www.example.com"), new ActionCallback() {
            public boolean onResponse(ActionContext actionContext) {
                String stringNamed = actionContext.stringNamed("URL");
                final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringNamed));
                if (!(Leanplum.getContext() instanceof Activity)) {
                    intent.addFlags(268435456);
                }
                try {
                    if (Leanplum.getContext() == null) {
                        return false;
                    }
                    LeanplumActivityHelper.queueActionUponActive(new PostponableAction() {
                        public void run() {
                            Context context = Leanplum.getContext();
                            if (context != null) {
                                List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
                                if (!(queryIntentActivities == null || queryIntentActivities.size() == 0)) {
                                    for (ResolveInfo resolveInfo : queryIntentActivities) {
                                        if (!(resolveInfo == null || resolveInfo.activityInfo == null || resolveInfo.activityInfo.name == null || !resolveInfo.activityInfo.name.contains(context.getPackageName()))) {
                                            intent.setPackage(resolveInfo.activityInfo.packageName);
                                        }
                                    }
                                    try {
                                        context.startActivity(intent);
                                    } catch (ActivityNotFoundException e) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Activity you are trying to start doesn't exist or isn't exported in manifest: ");
                                        sb.append(e);
                                        Log.e("Leanplum", sb.toString());
                                    }
                                }
                            }
                        }
                    });
                    return true;
                } catch (ActivityNotFoundException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to handle URL ");
                    sb.append(stringNamed);
                    Log.e("Leanplum", sb.toString());
                    return false;
                }
            }
        });
    }
}
