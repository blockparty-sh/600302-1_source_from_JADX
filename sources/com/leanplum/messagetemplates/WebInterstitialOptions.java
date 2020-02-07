package com.leanplum.messagetemplates;

import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;

public class WebInterstitialOptions {
    private String closeUrl;
    private boolean hasDismissButton;
    private String url;

    protected WebInterstitialOptions(ActionContext actionContext) {
        setUrl(actionContext.stringNamed("URL"));
        setHasDismissButton(actionContext.booleanNamed("Has dismiss button"));
        setCloseUrl(actionContext.stringNamed("Close URL"));
    }

    public String getUrl() {
        return this.url;
    }

    private void setUrl(String str) {
        this.url = str;
    }

    public boolean hasDismissButton() {
        return this.hasDismissButton;
    }

    private void setHasDismissButton(boolean z) {
        this.hasDismissButton = z;
    }

    public String getCloseUrl() {
        return this.closeUrl;
    }

    private void setCloseUrl(String str) {
        this.closeUrl = str;
    }

    public static ActionArgs toArgs() {
        return new ActionArgs().with("URL", "http://www.example.com").with("Close URL", "http://leanplum/close").with("Has dismiss button", Boolean.valueOf(true));
    }
}
