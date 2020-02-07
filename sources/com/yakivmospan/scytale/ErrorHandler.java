package com.yakivmospan.scytale;

import android.util.Log;

class ErrorHandler {
    private ErrorListener mErrorListener;

    ErrorHandler() {
    }

    public void setErrorListener(ErrorListener errorListener) {
        this.mErrorListener = errorListener;
    }

    /* access modifiers changed from: protected */
    public void onException(Exception exc) {
        Log.e(C2652Utils.TAG, exc.toString());
        ErrorListener errorListener = this.mErrorListener;
        if (errorListener != null) {
            errorListener.onError(exc);
        }
    }
}
