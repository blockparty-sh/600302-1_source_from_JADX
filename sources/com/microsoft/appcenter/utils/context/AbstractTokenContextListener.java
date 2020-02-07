package com.microsoft.appcenter.utils.context;

import com.microsoft.appcenter.utils.context.AuthTokenContext.Listener;

public abstract class AbstractTokenContextListener implements Listener {
    public void onNewAuthToken(String str) {
    }

    public void onNewUser(String str) {
    }

    public void onTokenRequiresRefresh(String str) {
    }
}
