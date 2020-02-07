package com.microsoft.appcenter.crashes;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.ingestion.models.Exception;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.DeviceInfoHelper.DeviceInfoException;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.storage.FileManager;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WrapperSdkExceptionManager {
    private static final String DATA_FILE_EXTENSION = ".dat";
    @VisibleForTesting
    static final Map<String, String> sWrapperExceptionDataContainer = new HashMap();

    @VisibleForTesting
    WrapperSdkExceptionManager() {
    }

    public static UUID saveWrapperException(Thread thread, Throwable th, Exception exception, String str) {
        String str2 = Crashes.LOG_TAG;
        try {
            UUID saveUncaughtException = Crashes.getInstance().saveUncaughtException(thread, th, exception);
            if (!(saveUncaughtException == null || str == null)) {
                sWrapperExceptionDataContainer.put(saveUncaughtException.toString(), str);
                File file = getFile(saveUncaughtException);
                FileManager.write(file, str);
                StringBuilder sb = new StringBuilder();
                sb.append("Saved raw wrapper exception data into ");
                sb.append(file);
                AppCenterLog.debug(str2, sb.toString());
            }
            return saveUncaughtException;
        } catch (Exception e) {
            AppCenterLog.error(str2, "Failed to save wrapper exception data to file", e);
            return null;
        }
    }

    public static void deleteWrapperExceptionData(UUID uuid) {
        String str = Crashes.LOG_TAG;
        if (uuid == null) {
            AppCenterLog.error(str, "Failed to delete wrapper exception data: null errorId");
            return;
        }
        File file = getFile(uuid);
        if (file.exists()) {
            if (loadWrapperExceptionData(uuid) == null) {
                AppCenterLog.error(str, "Failed to load wrapper exception data.");
            }
            FileManager.delete(file);
        }
    }

    public static String loadWrapperExceptionData(UUID uuid) {
        String str = null;
        if (uuid == null) {
            AppCenterLog.error(Crashes.LOG_TAG, "Failed to load wrapper exception data: null errorId");
            return null;
        }
        String str2 = (String) sWrapperExceptionDataContainer.get(uuid.toString());
        if (str2 != null) {
            return str2;
        }
        File file = getFile(uuid);
        if (file.exists()) {
            str = FileManager.read(file);
            if (str != null) {
                sWrapperExceptionDataContainer.put(uuid.toString(), str);
            }
        }
        return str;
    }

    private static File getFile(@NonNull UUID uuid) {
        File errorStorageDirectory = ErrorLogHelper.getErrorStorageDirectory();
        StringBuilder sb = new StringBuilder();
        sb.append(uuid.toString());
        sb.append(DATA_FILE_EXTENSION);
        return new File(errorStorageDirectory, sb.toString());
    }

    public static String trackException(Exception exception, Map<String, String> map, Iterable<ErrorAttachmentLog> iterable) {
        return Crashes.getInstance().queueException(exception, map, iterable).toString();
    }

    public static void setAutomaticProcessing(boolean z) {
        Crashes.getInstance().setAutomaticProcessing(z);
    }

    public static AppCenterFuture<Collection<ErrorReport>> getUnprocessedErrorReports() {
        return Crashes.getInstance().getUnprocessedErrorReports();
    }

    public static AppCenterFuture<Boolean> sendCrashReportsOrAwaitUserConfirmation(Collection<String> collection) {
        return Crashes.getInstance().sendCrashReportsOrAwaitUserConfirmation(collection);
    }

    public static ErrorReport buildHandledErrorReport(Context context, String str) {
        ErrorReport errorReport = new ErrorReport();
        errorReport.setId(str);
        errorReport.setAppErrorTime(new Date());
        errorReport.setAppStartTime(new Date(Crashes.getInstance().getInitializeTimestamp()));
        try {
            errorReport.setDevice(Crashes.getInstance().getDeviceInfo(context));
        } catch (DeviceInfoException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Handled error report cannot get device info, errorReportId=");
            sb.append(str);
            AppCenterLog.warn(Crashes.LOG_TAG, sb.toString());
        }
        return errorReport;
    }

    public static void sendErrorAttachments(String str, Iterable<ErrorAttachmentLog> iterable) {
        Crashes.getInstance().sendErrorAttachments(str, iterable);
    }
}
