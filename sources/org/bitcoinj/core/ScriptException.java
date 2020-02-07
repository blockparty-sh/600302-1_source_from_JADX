package org.bitcoinj.core;

public class ScriptException extends VerificationException {
    public ScriptException(String str) {
        super(str);
    }

    public ScriptException(String str, Exception exc) {
        super(str, exc);
    }
}
