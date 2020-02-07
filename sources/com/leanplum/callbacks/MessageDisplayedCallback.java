package com.leanplum.callbacks;

import com.leanplum.models.MessageArchiveData;

public abstract class MessageDisplayedCallback implements Runnable {
    private MessageArchiveData messageArchiveData;

    public abstract void messageDisplayed(MessageArchiveData messageArchiveData2);

    public void setMessageArchiveData(MessageArchiveData messageArchiveData2) {
        this.messageArchiveData = messageArchiveData2;
    }

    public void run() {
        messageDisplayed(this.messageArchiveData);
    }
}
