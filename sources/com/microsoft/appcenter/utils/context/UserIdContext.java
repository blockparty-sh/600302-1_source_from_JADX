package com.microsoft.appcenter.utils.context;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserIdContext {
    private static final String CUSTOM_PREFIX = "c";
    @VisibleForTesting
    public static final int USER_ID_APP_CENTER_MAX_LENGTH = 256;
    private static UserIdContext sInstance;
    private final Set<Listener> mListeners = Collections.newSetFromMap(new ConcurrentHashMap());
    private String mUserId;

    public interface Listener {
        void onNewUserId(String str);
    }

    public static synchronized UserIdContext getInstance() {
        UserIdContext userIdContext;
        synchronized (UserIdContext.class) {
            if (sInstance == null) {
                sInstance = new UserIdContext();
            }
            userIdContext = sInstance;
        }
        return userIdContext;
    }

    @VisibleForTesting
    public static synchronized void unsetInstance() {
        synchronized (UserIdContext.class) {
            sInstance = null;
        }
    }

    public static boolean checkUserIdValidForOneCollector(String str) {
        if (str == null) {
            return true;
        }
        String str2 = "userId must not be empty.";
        String str3 = "AppCenter";
        if (str.isEmpty()) {
            AppCenterLog.error(str3, str2);
            return false;
        }
        String str4 = Constants.COMMON_SCHEMA_PREFIX_SEPARATOR;
        int indexOf = str.indexOf(str4);
        if (indexOf >= 0) {
            String substring = str.substring(0, indexOf);
            String str5 = CUSTOM_PREFIX;
            if (!substring.equals(str5)) {
                AppCenterLog.error(str3, String.format("userId prefix must be '%s%s', '%s%s' is not supported.", new Object[]{str5, str4, substring, str4}));
                return false;
            } else if (indexOf == str.length() - 1) {
                AppCenterLog.error(str3, str2);
                return false;
            }
        }
        return true;
    }

    public static boolean checkUserIdValidForAppCenter(String str) {
        if (str == null || str.length() <= 256) {
            return true;
        }
        AppCenterLog.error("AppCenter", "userId is limited to 256 characters.");
        return false;
    }

    public static String getPrefixedUserId(String str) {
        if (str == null || str.contains(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("c:");
        sb.append(str);
        return sb.toString();
    }

    public void addListener(@NonNull Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(@NonNull Listener listener) {
        this.mListeners.remove(listener);
    }

    public synchronized String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String str) {
        if (updateUserId(str)) {
            for (Listener onNewUserId : this.mListeners) {
                onNewUserId.onNewUserId(this.mUserId);
            }
        }
    }

    private synchronized boolean updateUserId(String str) {
        if (TextUtils.equals(this.mUserId, str)) {
            return false;
        }
        this.mUserId = str;
        return true;
    }
}
