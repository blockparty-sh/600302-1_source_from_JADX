package com.leanplum.callbacks;

import com.leanplum.Leanplum;

public abstract class RegisterDeviceCallback implements Runnable {
    private EmailCallback callback;

    public static abstract class EmailCallback implements Runnable {
        private String email;

        public abstract void onResponse(String str);

        public void setResponseHandler(String str) {
            this.email = str;
        }

        public void run() {
            onResponse(this.email);
        }
    }

    public abstract void onResponse(EmailCallback emailCallback);

    public void setResponseHandler(EmailCallback emailCallback) {
        this.callback = emailCallback;
        Leanplum.countAggregator().incrementCount("init_with_callback");
    }

    public void run() {
        onResponse(this.callback);
    }
}
