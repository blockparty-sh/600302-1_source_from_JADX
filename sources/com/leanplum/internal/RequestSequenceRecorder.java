package com.leanplum.internal;

public interface RequestSequenceRecorder {
    void afterRead();

    void afterWrite();

    void beforeRead();

    void beforeWrite();
}
