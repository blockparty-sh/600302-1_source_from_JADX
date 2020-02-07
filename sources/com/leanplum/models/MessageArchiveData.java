package com.leanplum.models;

import androidx.annotation.NonNull;
import java.util.Date;

public class MessageArchiveData {
    @NonNull
    public Date deliveryDateTime;
    @NonNull
    public String messageBody;
    @NonNull
    public String messageID;
    @NonNull
    public String recipientUserID;

    public MessageArchiveData(String str, String str2, String str3, Date date) {
        this.messageID = str;
        this.messageBody = str2;
        this.recipientUserID = str3;
        this.deliveryDateTime = date;
    }
}
