package com.microsoft.appcenter.utils;

import androidx.annotation.NonNull;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.util.UUID;

public class IdHelper {
    @NonNull
    public static UUID getInstallId() {
        String str = PrefStorageConstants.KEY_INSTALL_ID;
        try {
            return UUID.fromString(SharedPreferencesManager.getString(str, ""));
        } catch (Exception unused) {
            AppCenterLog.warn("AppCenter", "Unable to get installID from Shared Preferences");
            UUID randomUUID = UUID.randomUUID();
            SharedPreferencesManager.putString(str, randomUUID.toString());
            return randomUUID;
        }
    }
}
