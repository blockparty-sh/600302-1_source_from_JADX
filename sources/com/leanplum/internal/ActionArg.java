package com.leanplum.internal;

import android.text.TextUtils;
import com.leanplum.Leanplum;
import com.leanplum.internal.Constants.Kinds;
import java.io.InputStream;

public class ActionArg<T> {
    private T defaultValue;
    private boolean isAsset;
    private String kind;
    private String name;

    private ActionArg() {
    }

    private static <T> ActionArg<T> argNamed(String str, T t, String str2) {
        ActionArg<T> actionArg = new ActionArg<>();
        actionArg.name = str;
        actionArg.kind = str2;
        actionArg.defaultValue = t;
        Leanplum.countAggregator().incrementCount("arg_named");
        return actionArg;
    }

    public static <T> ActionArg<T> argNamed(String str, T t) {
        return argNamed(str, t, VarCache.kindFromValue(t));
    }

    public static ActionArg<Integer> colorArgNamed(String str, int i) {
        return argNamed(str, Integer.valueOf(i), Kinds.COLOR);
    }

    public static ActionArg<String> fileArgNamed(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        ActionArg<String> argNamed = argNamed(str, str2, "file");
        T t = argNamed.defaultValue;
        VarCache.registerFile((String) t, (String) t, argNamed.defaultStream(), false, null, 0);
        return argNamed;
    }

    public static ActionArg<String> assetArgNamed(String str, String str2) {
        ActionArg<String> argNamed = argNamed(str, str2, "file");
        argNamed.isAsset = true;
        T t = argNamed.defaultValue;
        VarCache.registerFile((String) t, (String) t, argNamed.defaultStream(), false, null, 0);
        return argNamed;
    }

    public static ActionArg<String> actionArgNamed(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        return argNamed(str, str2, "action");
    }

    public String name() {
        return this.name;
    }

    public String kind() {
        return this.kind;
    }

    public T defaultValue() {
        return this.defaultValue;
    }

    public InputStream defaultStream() {
        if (!this.kind.equals("file")) {
            return null;
        }
        Boolean valueOf = Boolean.valueOf(this.isAsset);
        Boolean valueOf2 = Boolean.valueOf(this.isAsset);
        T t = this.defaultValue;
        return FileManager.stream(false, valueOf, valueOf2, (String) t, (String) t, null);
    }
}
