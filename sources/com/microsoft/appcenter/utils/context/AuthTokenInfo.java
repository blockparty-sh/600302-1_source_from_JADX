package com.microsoft.appcenter.utils.context;

import java.util.Calendar;
import java.util.Date;

public class AuthTokenInfo {
    private static final int EXPIRATION_OFFSET_TO_REFRESH_SEC = 600;
    private final String mAuthToken;
    private final Date mEndTime;
    private final Date mStartTime;

    public AuthTokenInfo() {
        this(null, null, null);
    }

    public AuthTokenInfo(String str, Date date, Date date2) {
        this.mAuthToken = str;
        this.mStartTime = date;
        this.mEndTime = date2;
    }

    /* access modifiers changed from: 0000 */
    public boolean isAboutToExpire() {
        if (this.mEndTime == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(13, 600);
        return instance.getTime().after(this.mEndTime);
    }

    public String getAuthToken() {
        return this.mAuthToken;
    }

    public Date getStartTime() {
        return this.mStartTime;
    }

    public Date getEndTime() {
        return this.mEndTime;
    }
}
