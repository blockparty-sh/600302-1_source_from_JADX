package com.leanplum;

public class LeanplumException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public LeanplumException(String str) {
        super(str);
    }
}
