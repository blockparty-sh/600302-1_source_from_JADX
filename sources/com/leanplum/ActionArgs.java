package com.leanplum;

import com.leanplum.internal.ActionArg;
import com.leanplum.internal.Log;
import java.util.ArrayList;
import java.util.List;

public class ActionArgs {
    private List<ActionArg<?>> args = new ArrayList();

    public <T> ActionArgs with(String str, T t) {
        if (str == null) {
            Log.m280e("with - Invalid name parameter provided.");
            return this;
        }
        this.args.add(ActionArg.argNamed(str, t));
        return this;
    }

    public ActionArgs withColor(String str, int i) {
        if (str == null) {
            Log.m280e("withColor - Invalid name parameter provided.");
            return this;
        }
        this.args.add(ActionArg.colorArgNamed(str, i));
        return this;
    }

    public ActionArgs withFile(String str, String str2) {
        if (str == null) {
            Log.m280e("withFile - Invalid name parameter provided.");
            return this;
        }
        this.args.add(ActionArg.fileArgNamed(str, str2));
        return this;
    }

    public ActionArgs withAsset(String str, String str2) {
        if (str == null) {
            Log.m280e("withAsset - Invalid name parameter provided.");
            return this;
        }
        this.args.add(ActionArg.assetArgNamed(str, str2));
        return this;
    }

    public ActionArgs withAction(String str, String str2) {
        if (str == null) {
            Log.m280e("withAction - Invalid name parameter provided.");
            return this;
        }
        this.args.add(ActionArg.actionArgNamed(str, str2));
        return this;
    }

    /* access modifiers changed from: 0000 */
    public List<ActionArg<?>> getValue() {
        return this.args;
    }
}
