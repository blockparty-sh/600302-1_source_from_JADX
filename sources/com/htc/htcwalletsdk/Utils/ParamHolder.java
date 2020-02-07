package com.htc.htcwalletsdk.Utils;

import android.os.Bundle;

public class ParamHolder {
    Bundle mIn = new Bundle();
    Bundle mOut = new Bundle();

    public void setIn(Bundle bundle) {
        this.mIn = bundle;
    }

    public Bundle getIn() {
        return this.mIn;
    }

    public void setOut(Bundle bundle) {
        this.mOut = bundle;
    }

    public Bundle getOut() {
        return this.mOut;
    }
}
