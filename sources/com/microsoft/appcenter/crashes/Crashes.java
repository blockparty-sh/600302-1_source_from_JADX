package com.microsoft.appcenter.crashes;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.microsoft.appcenter.AbstractAppCenterService;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.channel.Channel.GroupListener;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.ingestion.models.Exception;
import com.microsoft.appcenter.crashes.ingestion.models.HandledErrorLog;
import com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog;
import com.microsoft.appcenter.crashes.ingestion.models.json.ErrorAttachmentLogFactory;
import com.microsoft.appcenter.crashes.ingestion.models.json.HandledErrorLogFactory;
import com.microsoft.appcenter.crashes.ingestion.models.json.ManagedErrorLogFactory;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.crashes.model.NativeException;
import com.microsoft.appcenter.crashes.model.TestCrashException;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.DefaultLogSerializer;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.DeviceInfoHelper;
import com.microsoft.appcenter.utils.DeviceInfoHelper.DeviceInfoException;
import com.microsoft.appcenter.utils.HandlerUtils;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.async.DefaultAppCenterFuture;
import com.microsoft.appcenter.utils.context.SessionContext;
import com.microsoft.appcenter.utils.context.SessionContext.SessionInfo;
import com.microsoft.appcenter.utils.context.UserIdContext;
import com.microsoft.appcenter.utils.storage.FileManager;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONException;

public class Crashes extends AbstractAppCenterService {
    public static final int ALWAYS_SEND = 2;
    private static final CrashesListener DEFAULT_ERROR_REPORTING_LISTENER = new DefaultCrashesListener();
    public static final int DONT_SEND = 1;
    @VisibleForTesting
    static final String ERROR_GROUP = "groupErrors";
    public static final String LOG_TAG = "AppCenterCrashes";
    private static final int MAX_ATTACHMENT_PER_CRASH = 2;
    @VisibleForTesting
    public static final String PREF_KEY_ALWAYS_SEND = "com.microsoft.appcenter.crashes.always.send";
    @VisibleForTesting
    static final String PREF_KEY_MEMORY_RUNNING_LEVEL = "com.microsoft.appcenter.crashes.memory";
    public static final int SEND = 0;
    private static final String SERVICE_NAME = "Crashes";
    @SuppressLint({"StaticFieldLeak"})
    private static Crashes sInstance = null;
    /* access modifiers changed from: private */
    public boolean mAutomaticProcessing = true;
    private Context mContext;
    /* access modifiers changed from: private */
    public CrashesListener mCrashesListener;
    private Device mDevice;
    private final Map<UUID, ErrorLogReport> mErrorReportCache;
    private final Map<String, LogFactory> mFactories = new HashMap();
    /* access modifiers changed from: private */
    public boolean mHasReceivedMemoryWarningInLastSession;
    private long mInitializeTimestamp;
    /* access modifiers changed from: private */
    public ErrorReport mLastSessionErrorReport;
    private LogSerializer mLogSerializer;
    private ComponentCallbacks2 mMemoryWarningListener;
    private boolean mSavedUncaughtException;
    private UncaughtExceptionHandler mUncaughtExceptionHandler;
    /* access modifiers changed from: private */
    public final Map<UUID, ErrorLogReport> mUnprocessedErrorReports;

    private interface CallbackProcessor {
        void onCallBack(ErrorReport errorReport);

        boolean shouldDeleteThrowable();
    }

    private static class DefaultCrashesListener extends AbstractCrashesListener {
        private DefaultCrashesListener() {
        }
    }

    private static class ErrorLogReport {
        /* access modifiers changed from: private */
        public final ManagedErrorLog log;
        /* access modifiers changed from: private */
        public final ErrorReport report;

        private ErrorLogReport(ManagedErrorLog managedErrorLog, ErrorReport errorReport) {
            this.log = managedErrorLog;
            this.report = errorReport;
        }
    }

    private interface ExceptionModelBuilder {
        Exception buildExceptionModel();
    }

    private static boolean isMemoryRunningLevelWasReceived(int i) {
        return i == 5 || i == 10 || i == 15 || i == 80;
    }

    /* access modifiers changed from: protected */
    public String getGroupName() {
        return ERROR_GROUP;
    }

    /* access modifiers changed from: protected */
    public String getLoggerTag() {
        return LOG_TAG;
    }

    public String getServiceName() {
        return SERVICE_NAME;
    }

    /* access modifiers changed from: protected */
    public int getTriggerCount() {
        return 1;
    }

    private Crashes() {
        Map<String, LogFactory> map = this.mFactories;
        ManagedErrorLogFactory instance = ManagedErrorLogFactory.getInstance();
        String str = ManagedErrorLog.TYPE;
        map.put(str, instance);
        this.mFactories.put(HandledErrorLog.TYPE, HandledErrorLogFactory.getInstance());
        Map<String, LogFactory> map2 = this.mFactories;
        ErrorAttachmentLogFactory instance2 = ErrorAttachmentLogFactory.getInstance();
        String str2 = ErrorAttachmentLog.TYPE;
        map2.put(str2, instance2);
        this.mLogSerializer = new DefaultLogSerializer();
        this.mLogSerializer.addLogFactory(str, ManagedErrorLogFactory.getInstance());
        this.mLogSerializer.addLogFactory(str2, ErrorAttachmentLogFactory.getInstance());
        this.mCrashesListener = DEFAULT_ERROR_REPORTING_LISTENER;
        this.mUnprocessedErrorReports = new LinkedHashMap();
        this.mErrorReportCache = new LinkedHashMap();
    }

    @NonNull
    public static synchronized Crashes getInstance() {
        Crashes crashes;
        synchronized (Crashes.class) {
            if (sInstance == null) {
                sInstance = new Crashes();
            }
            crashes = sInstance;
        }
        return crashes;
    }

    @VisibleForTesting
    static synchronized void unsetInstance() {
        synchronized (Crashes.class) {
            sInstance = null;
        }
    }

    public static AppCenterFuture<Boolean> isEnabled() {
        return getInstance().isInstanceEnabledAsync();
    }

    public static AppCenterFuture<Void> setEnabled(boolean z) {
        return getInstance().setInstanceEnabledAsync(z);
    }

    public static void trackError(Throwable th) {
        trackError(th, null, null);
    }

    public static void trackError(Throwable th, Map<String, String> map, Iterable<ErrorAttachmentLog> iterable) {
        getInstance().queueException(th, map, iterable);
    }

    public static void generateTestCrash() {
        if (!Constants.APPLICATION_DEBUGGABLE) {
            AppCenterLog.warn(LOG_TAG, "The application is not debuggable so SDK won't generate test crash");
            return;
        }
        throw new TestCrashException();
    }

    public static void setListener(CrashesListener crashesListener) {
        getInstance().setInstanceListener(crashesListener);
    }

    public static AppCenterFuture<String> getMinidumpDirectory() {
        return getInstance().getNewMinidumpDirectoryAsync();
    }

    public static void notifyUserConfirmation(int i) {
        getInstance().handleUserConfirmation(i);
    }

    public static AppCenterFuture<Boolean> hasCrashedInLastSession() {
        return getInstance().hasInstanceCrashedInLastSession();
    }

    public static AppCenterFuture<ErrorReport> getLastSessionCrashReport() {
        return getInstance().getInstanceLastSessionCrashReport();
    }

    public static AppCenterFuture<Boolean> hasReceivedMemoryWarningInLastSession() {
        return getInstance().hasInstanceReceivedMemoryWarningInLastSession();
    }

    private synchronized AppCenterFuture<String> getNewMinidumpDirectoryAsync() {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        postAsyncGetter(new Runnable() {
            public void run() {
                defaultAppCenterFuture.complete(ErrorLogHelper.getNewMinidumpDirectory().getAbsolutePath());
            }
        }, defaultAppCenterFuture, null);
        return defaultAppCenterFuture;
    }

    private synchronized AppCenterFuture<Boolean> hasInstanceCrashedInLastSession() {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        postAsyncGetter(new Runnable() {
            public void run() {
                defaultAppCenterFuture.complete(Boolean.valueOf(Crashes.this.mLastSessionErrorReport != null));
            }
        }, defaultAppCenterFuture, Boolean.valueOf(false));
        return defaultAppCenterFuture;
    }

    private synchronized AppCenterFuture<Boolean> hasInstanceReceivedMemoryWarningInLastSession() {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        postAsyncGetter(new Runnable() {
            public void run() {
                defaultAppCenterFuture.complete(Boolean.valueOf(Crashes.this.mHasReceivedMemoryWarningInLastSession));
            }
        }, defaultAppCenterFuture, Boolean.valueOf(false));
        return defaultAppCenterFuture;
    }

    private synchronized AppCenterFuture<ErrorReport> getInstanceLastSessionCrashReport() {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        postAsyncGetter(new Runnable() {
            public void run() {
                defaultAppCenterFuture.complete(Crashes.this.mLastSessionErrorReport);
            }
        }, defaultAppCenterFuture, null);
        return defaultAppCenterFuture;
    }

    /* access modifiers changed from: protected */
    public synchronized void applyEnabledState(boolean z) {
        initialize();
        if (z) {
            this.mMemoryWarningListener = new ComponentCallbacks2() {
                public void onConfigurationChanged(@NonNull Configuration configuration) {
                }

                public void onTrimMemory(int i) {
                    Crashes.saveMemoryRunningLevel(i);
                }

                public void onLowMemory() {
                    Crashes.saveMemoryRunningLevel(80);
                }
            };
            this.mContext.registerComponentCallbacks(this.mMemoryWarningListener);
        } else {
            File[] listFiles = ErrorLogHelper.getErrorStorageDirectory().listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    String str = LOG_TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Deleting file ");
                    sb.append(file);
                    AppCenterLog.debug(str, sb.toString());
                    if (!file.delete()) {
                        String str2 = LOG_TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Failed to delete file ");
                        sb2.append(file);
                        AppCenterLog.warn(str2, sb2.toString());
                    }
                }
            }
            AppCenterLog.info(LOG_TAG, "Deleted crashes local files");
            this.mErrorReportCache.clear();
            this.mLastSessionErrorReport = null;
            this.mContext.unregisterComponentCallbacks(this.mMemoryWarningListener);
            this.mMemoryWarningListener = null;
            SharedPreferencesManager.remove(PREF_KEY_MEMORY_RUNNING_LEVEL);
        }
    }

    public synchronized void onStarted(@NonNull Context context, @NonNull Channel channel, String str, String str2, boolean z) {
        this.mContext = context;
        super.onStarted(context, channel, str, str2, z);
        if (isInstanceEnabled()) {
            processPendingErrors();
        }
    }

    public Map<String, LogFactory> getLogFactories() {
        return this.mFactories;
    }

    /* access modifiers changed from: protected */
    public GroupListener getChannelListener() {
        return new GroupListener() {
            private void processCallback(final Log log, final CallbackProcessor callbackProcessor) {
                Crashes.this.post(new Runnable() {
                    public void run() {
                        Log log = log;
                        boolean z = log instanceof ManagedErrorLog;
                        String str = Crashes.LOG_TAG;
                        if (z) {
                            ManagedErrorLog managedErrorLog = (ManagedErrorLog) log;
                            final ErrorReport buildErrorReport = Crashes.this.buildErrorReport(managedErrorLog);
                            UUID id = managedErrorLog.getId();
                            if (buildErrorReport != null) {
                                if (callbackProcessor.shouldDeleteThrowable()) {
                                    Crashes.this.removeStoredThrowable(id);
                                }
                                HandlerUtils.runOnUiThread(new Runnable() {
                                    public void run() {
                                        callbackProcessor.onCallBack(buildErrorReport);
                                    }
                                });
                                return;
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("Cannot find crash report for the error log: ");
                            sb.append(id);
                            AppCenterLog.warn(str, sb.toString());
                        } else if (!(log instanceof ErrorAttachmentLog) && !(log instanceof HandledErrorLog)) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("A different type of log comes to crashes: ");
                            sb2.append(log.getClass().getName());
                            AppCenterLog.warn(str, sb2.toString());
                        }
                    }
                });
            }

            public void onBeforeSending(Log log) {
                processCallback(log, new CallbackProcessor() {
                    public boolean shouldDeleteThrowable() {
                        return false;
                    }

                    public void onCallBack(ErrorReport errorReport) {
                        Crashes.this.mCrashesListener.onBeforeSending(errorReport);
                    }
                });
            }

            public void onSuccess(Log log) {
                processCallback(log, new CallbackProcessor() {
                    public boolean shouldDeleteThrowable() {
                        return true;
                    }

                    public void onCallBack(ErrorReport errorReport) {
                        Crashes.this.mCrashesListener.onSendingSucceeded(errorReport);
                    }
                });
            }

            public void onFailure(Log log, final Exception exc) {
                processCallback(log, new CallbackProcessor() {
                    public boolean shouldDeleteThrowable() {
                        return true;
                    }

                    public void onCallBack(ErrorReport errorReport) {
                        Crashes.this.mCrashesListener.onSendingFailed(errorReport, exc);
                    }
                });
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public synchronized Device getDeviceInfo(Context context) throws DeviceInfoException {
        if (this.mDevice == null) {
            this.mDevice = DeviceInfoHelper.getDeviceInfo(context);
        }
        return this.mDevice;
    }

    /* access modifiers changed from: 0000 */
    public synchronized long getInitializeTimestamp() {
        return this.mInitializeTimestamp;
    }

    private synchronized void queueException(@NonNull final Throwable th, Map<String, String> map, Iterable<ErrorAttachmentLog> iterable) {
        queueException((ExceptionModelBuilder) new ExceptionModelBuilder() {
            public Exception buildExceptionModel() {
                return ErrorLogHelper.getModelExceptionFromThrowable(th);
            }
        }, map, iterable);
    }

    /* access modifiers changed from: 0000 */
    public synchronized UUID queueException(@NonNull final Exception exception, Map<String, String> map, Iterable<ErrorAttachmentLog> iterable) {
        return queueException((ExceptionModelBuilder) new ExceptionModelBuilder() {
            public Exception buildExceptionModel() {
                return exception;
            }
        }, map, iterable);
    }

    private synchronized UUID queueException(@NonNull ExceptionModelBuilder exceptionModelBuilder, Map<String, String> map, Iterable<ErrorAttachmentLog> iterable) {
        UUID randomUUID;
        final String userId = UserIdContext.getInstance().getUserId();
        randomUUID = UUID.randomUUID();
        final Map validateProperties = ErrorLogHelper.validateProperties(map, "HandledError");
        final UUID uuid = randomUUID;
        final ExceptionModelBuilder exceptionModelBuilder2 = exceptionModelBuilder;
        final Iterable<ErrorAttachmentLog> iterable2 = iterable;
        C25219 r1 = new Runnable() {
            public void run() {
                HandledErrorLog handledErrorLog = new HandledErrorLog();
                handledErrorLog.setId(uuid);
                handledErrorLog.setUserId(userId);
                handledErrorLog.setException(exceptionModelBuilder2.buildExceptionModel());
                handledErrorLog.setProperties(validateProperties);
                Crashes.this.mChannel.enqueue(handledErrorLog, Crashes.ERROR_GROUP, 1);
                Crashes.this.sendErrorAttachment(uuid, iterable2);
            }
        };
        post(r1);
        return randomUUID;
    }

    private void initialize() {
        boolean isInstanceEnabled = isInstanceEnabled();
        this.mInitializeTimestamp = isInstanceEnabled ? System.currentTimeMillis() : -1;
        if (!isInstanceEnabled) {
            UncaughtExceptionHandler uncaughtExceptionHandler = this.mUncaughtExceptionHandler;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.unregister();
                this.mUncaughtExceptionHandler = null;
                return;
            }
            return;
        }
        this.mUncaughtExceptionHandler = new UncaughtExceptionHandler();
        this.mUncaughtExceptionHandler.register();
        processMinidumpFiles();
    }

    private void processMinidumpFiles() {
        File[] newMinidumpFiles = ErrorLogHelper.getNewMinidumpFiles();
        int length = newMinidumpFiles.length;
        int i = 0;
        while (true) {
            String str = LOG_TAG;
            if (i < length) {
                File file = newMinidumpFiles[i];
                StringBuilder sb = new StringBuilder();
                sb.append("Process pending minidump file: ");
                sb.append(file);
                AppCenterLog.debug(str, sb.toString());
                long lastModified = file.lastModified();
                File file2 = new File(ErrorLogHelper.getPendingMinidumpDirectory(), file.getName());
                Exception exception = new Exception();
                exception.setType("minidump");
                String str2 = Constants.WRAPPER_SDK_NAME_NDK;
                exception.setWrapperSdkName(str2);
                exception.setMinidumpFilePath(file2.getPath());
                ManagedErrorLog managedErrorLog = new ManagedErrorLog();
                managedErrorLog.setException(exception);
                managedErrorLog.setTimestamp(new Date(lastModified));
                managedErrorLog.setFatal(Boolean.valueOf(true));
                managedErrorLog.setId(UUID.randomUUID());
                SessionInfo sessionAt = SessionContext.getInstance().getSessionAt(lastModified);
                if (sessionAt == null || sessionAt.getAppLaunchTimestamp() > lastModified) {
                    managedErrorLog.setAppLaunchTimestamp(managedErrorLog.getTimestamp());
                } else {
                    managedErrorLog.setAppLaunchTimestamp(new Date(sessionAt.getAppLaunchTimestamp()));
                }
                managedErrorLog.setProcessId(Integer.valueOf(0));
                managedErrorLog.setProcessName("");
                managedErrorLog.setUserId(UserIdContext.getInstance().getUserId());
                try {
                    managedErrorLog.setDevice(getDeviceInfo(this.mContext));
                    managedErrorLog.getDevice().setWrapperSdkName(str2);
                    saveErrorLogFiles(new NativeException(), managedErrorLog);
                    if (file.renameTo(file2)) {
                        i++;
                    } else {
                        throw new IOException("Failed to move file");
                    }
                } catch (Exception e) {
                    file.delete();
                    removeAllStoredErrorLogFiles(managedErrorLog.getId());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Failed to process new minidump file: ");
                    sb2.append(file);
                    AppCenterLog.error(str, sb2.toString(), e);
                }
            } else {
                File lastErrorLogFile = ErrorLogHelper.getLastErrorLogFile();
                while (lastErrorLogFile != null && lastErrorLogFile.length() == 0) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Deleting empty error file: ");
                    sb3.append(lastErrorLogFile);
                    AppCenterLog.warn(str, sb3.toString());
                    lastErrorLogFile.delete();
                    lastErrorLogFile = ErrorLogHelper.getLastErrorLogFile();
                }
                if (lastErrorLogFile != null) {
                    AppCenterLog.debug(str, "Processing crash report for the last session.");
                    String read = FileManager.read(lastErrorLogFile);
                    if (read == null) {
                        AppCenterLog.error(str, "Error reading last session error log.");
                        return;
                    }
                    try {
                        this.mLastSessionErrorReport = buildErrorReport((ManagedErrorLog) this.mLogSerializer.deserializeLog(read, null));
                        AppCenterLog.debug(str, "Processed crash report for the last session.");
                        return;
                    } catch (JSONException e2) {
                        AppCenterLog.error(str, "Error parsing last session error log.", e2);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private void processPendingErrors() {
        String str;
        File[] storedErrorLogFiles = ErrorLogHelper.getStoredErrorLogFiles();
        int length = storedErrorLogFiles.length;
        int i = 0;
        while (true) {
            str = LOG_TAG;
            if (i >= length) {
                break;
            }
            File file = storedErrorLogFiles[i];
            StringBuilder sb = new StringBuilder();
            sb.append("Process pending error file: ");
            sb.append(file);
            AppCenterLog.debug(str, sb.toString());
            String read = FileManager.read(file);
            if (read != null) {
                try {
                    ManagedErrorLog managedErrorLog = (ManagedErrorLog) this.mLogSerializer.deserializeLog(read, null);
                    UUID id = managedErrorLog.getId();
                    ErrorReport buildErrorReport = buildErrorReport(managedErrorLog);
                    if (buildErrorReport == null) {
                        removeAllStoredErrorLogFiles(id);
                    } else {
                        if (this.mAutomaticProcessing) {
                            if (!this.mCrashesListener.shouldProcess(buildErrorReport)) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("CrashesListener.shouldProcess returned false, clean up and ignore log: ");
                                sb2.append(id.toString());
                                AppCenterLog.debug(str, sb2.toString());
                                removeAllStoredErrorLogFiles(id);
                            }
                        }
                        if (!this.mAutomaticProcessing) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("CrashesListener.shouldProcess returned true, continue processing log: ");
                            sb3.append(id.toString());
                            AppCenterLog.debug(str, sb3.toString());
                        }
                        this.mUnprocessedErrorReports.put(id, this.mErrorReportCache.get(id));
                    }
                } catch (JSONException e) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Error parsing error log. Deleting invalid file: ");
                    sb4.append(file);
                    AppCenterLog.error(str, sb4.toString(), e);
                    file.delete();
                }
            }
            i++;
        }
        String str2 = PREF_KEY_MEMORY_RUNNING_LEVEL;
        this.mHasReceivedMemoryWarningInLastSession = isMemoryRunningLevelWasReceived(SharedPreferencesManager.getInt(str2, -1));
        if (this.mHasReceivedMemoryWarningInLastSession) {
            AppCenterLog.debug(str, "The application received a low memory warning in the last session.");
        }
        SharedPreferencesManager.remove(str2);
        if (this.mAutomaticProcessing) {
            sendCrashReportsOrAwaitUserConfirmation();
        }
    }

    /* access modifiers changed from: private */
    public boolean sendCrashReportsOrAwaitUserConfirmation() {
        final boolean z = SharedPreferencesManager.getBoolean(PREF_KEY_ALWAYS_SEND, false);
        HandlerUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (Crashes.this.mUnprocessedErrorReports.size() > 0) {
                    boolean z = z;
                    String str = Crashes.LOG_TAG;
                    if (z) {
                        AppCenterLog.debug(str, "The flag for user confirmation is set to ALWAYS_SEND, will send logs.");
                        Crashes.this.handleUserConfirmation(0);
                    } else if (!Crashes.this.mAutomaticProcessing) {
                        AppCenterLog.debug(str, "Automatic processing disabled, will wait for explicit user confirmation.");
                    } else if (!Crashes.this.mCrashesListener.shouldAwaitUserConfirmation()) {
                        AppCenterLog.debug(str, "CrashesListener.shouldAwaitUserConfirmation returned false, will send logs.");
                        Crashes.this.handleUserConfirmation(0);
                    } else {
                        AppCenterLog.debug(str, "CrashesListener.shouldAwaitUserConfirmation returned true, wait sending logs.");
                    }
                }
            }
        });
        return z;
    }

    /* access modifiers changed from: private */
    public void removeAllStoredErrorLogFiles(UUID uuid) {
        ErrorLogHelper.removeStoredErrorLogFile(uuid);
        removeStoredThrowable(uuid);
    }

    /* access modifiers changed from: private */
    public void removeStoredThrowable(UUID uuid) {
        this.mErrorReportCache.remove(uuid);
        WrapperSdkExceptionManager.deleteWrapperExceptionData(uuid);
        ErrorLogHelper.removeStoredThrowableFile(uuid);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.mUncaughtExceptionHandler;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.mUncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    @Nullable
    public ErrorReport buildErrorReport(ManagedErrorLog managedErrorLog) {
        UUID id = managedErrorLog.getId();
        if (this.mErrorReportCache.containsKey(id)) {
            ErrorReport access$1200 = ((ErrorLogReport) this.mErrorReportCache.get(id)).report;
            access$1200.setDevice(managedErrorLog.getDevice());
            return access$1200;
        }
        File storedThrowableFile = ErrorLogHelper.getStoredThrowableFile(id);
        if (storedThrowableFile == null) {
            return null;
        }
        ErrorReport errorReportFromErrorLog = ErrorLogHelper.getErrorReportFromErrorLog(managedErrorLog, storedThrowableFile.length() > 0 ? FileManager.read(storedThrowableFile) : null);
        this.mErrorReportCache.put(id, new ErrorLogReport(managedErrorLog, errorReportFromErrorLog));
        return errorReportFromErrorLog;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public CrashesListener getInstanceListener() {
        return this.mCrashesListener;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void setInstanceListener(CrashesListener crashesListener) {
        if (crashesListener == null) {
            crashesListener = DEFAULT_ERROR_REPORTING_LISTENER;
        }
        this.mCrashesListener = crashesListener;
    }

    /* access modifiers changed from: private */
    @VisibleForTesting
    public synchronized void handleUserConfirmation(final int i) {
        post(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:25:0x00b4  */
            /* JADX WARNING: Removed duplicated region for block: B:28:0x00d0  */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x00eb A[SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r9 = this;
                    int r0 = r2
                    r1 = 1
                    if (r0 != r1) goto L_0x0028
                    com.microsoft.appcenter.crashes.Crashes r0 = com.microsoft.appcenter.crashes.Crashes.this
                    java.util.Map r0 = r0.mUnprocessedErrorReports
                    java.util.Set r0 = r0.keySet()
                    java.util.Iterator r0 = r0.iterator()
                L_0x0013:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto L_0x00f9
                    java.lang.Object r1 = r0.next()
                    java.util.UUID r1 = (java.util.UUID) r1
                    r0.remove()
                    com.microsoft.appcenter.crashes.Crashes r2 = com.microsoft.appcenter.crashes.Crashes.this
                    r2.removeAllStoredErrorLogFiles(r1)
                    goto L_0x0013
                L_0x0028:
                    r2 = 2
                    if (r0 != r2) goto L_0x0030
                    java.lang.String r0 = "com.microsoft.appcenter.crashes.always.send"
                    com.microsoft.appcenter.utils.storage.SharedPreferencesManager.putBoolean(r0, r1)
                L_0x0030:
                    com.microsoft.appcenter.crashes.Crashes r0 = com.microsoft.appcenter.crashes.Crashes.this
                    java.util.Map r0 = r0.mUnprocessedErrorReports
                    java.util.Set r0 = r0.entrySet()
                    java.util.Iterator r0 = r0.iterator()
                L_0x003e:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto L_0x00f9
                    java.lang.Object r1 = r0.next()
                    java.util.Map$Entry r1 = (java.util.Map.Entry) r1
                    java.lang.Object r3 = r1.getValue()
                    com.microsoft.appcenter.crashes.Crashes$ErrorLogReport r3 = (com.microsoft.appcenter.crashes.Crashes.ErrorLogReport) r3
                    com.microsoft.appcenter.crashes.model.ErrorReport r4 = r3.report
                    com.microsoft.appcenter.ingestion.models.Device r4 = r4.getDevice()
                    r5 = 0
                    if (r4 == 0) goto L_0x00a2
                    com.microsoft.appcenter.crashes.model.ErrorReport r4 = r3.report
                    com.microsoft.appcenter.ingestion.models.Device r4 = r4.getDevice()
                    java.lang.String r4 = r4.getWrapperSdkName()
                    java.lang.String r6 = "appcenter.ndk"
                    boolean r4 = r6.equals(r4)
                    if (r4 == 0) goto L_0x00a2
                    com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog r4 = r3.log
                    com.microsoft.appcenter.crashes.ingestion.models.Exception r4 = r4.getException()
                    java.lang.String r6 = r4.getMinidumpFilePath()
                    r4.setMinidumpFilePath(r5)
                    if (r6 != 0) goto L_0x0087
                    java.lang.String r6 = r4.getStackTrace()
                    r4.setStackTrace(r5)
                L_0x0087:
                    if (r6 == 0) goto L_0x009b
                    java.io.File r5 = new java.io.File
                    r5.<init>(r6)
                    byte[] r4 = com.microsoft.appcenter.utils.storage.FileManager.readBytes(r5)
                    java.lang.String r6 = "minidump.dmp"
                    java.lang.String r7 = "application/octet-stream"
                    com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog r4 = com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog.attachmentWithBinary(r4, r6, r7)
                    goto L_0x00a3
                L_0x009b:
                    java.lang.String r4 = "AppCenterCrashes"
                    java.lang.String r6 = "NativeException found without minidump."
                    com.microsoft.appcenter.utils.AppCenterLog.warn(r4, r6)
                L_0x00a2:
                    r4 = r5
                L_0x00a3:
                    com.microsoft.appcenter.crashes.Crashes r6 = com.microsoft.appcenter.crashes.Crashes.this
                    com.microsoft.appcenter.channel.Channel r6 = r6.mChannel
                    com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog r7 = r3.log
                    java.lang.String r8 = "groupErrors"
                    r6.enqueue(r7, r8, r2)
                    if (r4 == 0) goto L_0x00c8
                    com.microsoft.appcenter.crashes.Crashes r6 = com.microsoft.appcenter.crashes.Crashes.this
                    com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog r7 = r3.log
                    java.util.UUID r7 = r7.getId()
                    java.util.Set r4 = java.util.Collections.singleton(r4)
                    r6.sendErrorAttachment(r7, r4)
                    r5.delete()
                L_0x00c8:
                    com.microsoft.appcenter.crashes.Crashes r4 = com.microsoft.appcenter.crashes.Crashes.this
                    boolean r4 = r4.mAutomaticProcessing
                    if (r4 == 0) goto L_0x00eb
                    com.microsoft.appcenter.crashes.Crashes r4 = com.microsoft.appcenter.crashes.Crashes.this
                    com.microsoft.appcenter.crashes.CrashesListener r4 = r4.mCrashesListener
                    com.microsoft.appcenter.crashes.model.ErrorReport r5 = r3.report
                    java.lang.Iterable r4 = r4.getErrorAttachments(r5)
                    com.microsoft.appcenter.crashes.Crashes r5 = com.microsoft.appcenter.crashes.Crashes.this
                    com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog r3 = r3.log
                    java.util.UUID r3 = r3.getId()
                    r5.sendErrorAttachment(r3, r4)
                L_0x00eb:
                    r0.remove()
                    java.lang.Object r1 = r1.getKey()
                    java.util.UUID r1 = (java.util.UUID) r1
                    com.microsoft.appcenter.crashes.utils.ErrorLogHelper.removeStoredErrorLogFile(r1)
                    goto L_0x003e
                L_0x00f9:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.crashes.Crashes.C250511.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void sendErrorAttachment(UUID uuid, Iterable<ErrorAttachmentLog> iterable) {
        String str = LOG_TAG;
        if (iterable == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error report: ");
            sb.append(uuid.toString());
            sb.append(" does not have any attachment.");
            AppCenterLog.debug(str, sb.toString());
            return;
        }
        int i = 0;
        for (ErrorAttachmentLog errorAttachmentLog : iterable) {
            if (errorAttachmentLog != null) {
                errorAttachmentLog.setId(UUID.randomUUID());
                errorAttachmentLog.setErrorId(uuid);
                if (errorAttachmentLog.isValid()) {
                    i++;
                    this.mChannel.enqueue(errorAttachmentLog, ERROR_GROUP, 1);
                } else {
                    AppCenterLog.error(str, "Not all required fields are present in ErrorAttachmentLog.");
                }
            } else {
                AppCenterLog.warn(str, "Skipping null ErrorAttachmentLog.");
            }
        }
        if (i > 2) {
            AppCenterLog.warn(str, "A limit of 2 attachments per error report might be enforced by server.");
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void setLogSerializer(LogSerializer logSerializer) {
        this.mLogSerializer = logSerializer;
    }

    /* access modifiers changed from: 0000 */
    public void saveUncaughtException(Thread thread, Throwable th) {
        String str = LOG_TAG;
        try {
            saveUncaughtException(thread, th, ErrorLogHelper.getModelExceptionFromThrowable(th));
        } catch (JSONException e) {
            AppCenterLog.error(str, "Error serializing error log to JSON", e);
        } catch (IOException e2) {
            AppCenterLog.error(str, "Error writing error log to file", e2);
        }
    }

    /* access modifiers changed from: 0000 */
    public UUID saveUncaughtException(Thread thread, Throwable th, Exception exception) throws JSONException, IOException {
        if (!((Boolean) isEnabled().get()).booleanValue() || this.mSavedUncaughtException) {
            return null;
        }
        this.mSavedUncaughtException = true;
        return saveErrorLogFiles(th, ErrorLogHelper.createErrorLog(this.mContext, thread, exception, Thread.getAllStackTraces(), this.mInitializeTimestamp, true));
    }

    @NonNull
    private UUID saveErrorLogFiles(Throwable th, ManagedErrorLog managedErrorLog) throws JSONException, IOException {
        File errorStorageDirectory = ErrorLogHelper.getErrorStorageDirectory();
        UUID id = managedErrorLog.getId();
        String uuid = id.toString();
        String str = LOG_TAG;
        AppCenterLog.debug(str, "Saving uncaught exception.");
        StringBuilder sb = new StringBuilder();
        sb.append(uuid);
        sb.append(ErrorLogHelper.ERROR_LOG_FILE_EXTENSION);
        File file = new File(errorStorageDirectory, sb.toString());
        FileManager.write(file, this.mLogSerializer.serializeLog(managedErrorLog));
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Saved JSON content for ingestion into ");
        sb2.append(file);
        AppCenterLog.debug(str, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(uuid);
        sb3.append(ErrorLogHelper.THROWABLE_FILE_EXTENSION);
        File file2 = new File(errorStorageDirectory, sb3.toString());
        if (th != null) {
            try {
                String stackTraceString = android.util.Log.getStackTraceString(th);
                FileManager.write(file2, stackTraceString);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Saved stack trace as is for client side inspection in ");
                sb4.append(file2);
                sb4.append(" stack trace:");
                sb4.append(stackTraceString);
                AppCenterLog.debug(str, sb4.toString());
            } catch (StackOverflowError e) {
                AppCenterLog.error(str, "Failed to store stack trace.", e);
                th = null;
                file2.delete();
            }
        }
        if (th == null) {
            if (file2.createNewFile()) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Saved empty Throwable file in ");
                sb5.append(file2);
                AppCenterLog.debug(str, sb5.toString());
            } else {
                throw new IOException(file2.getName());
            }
        }
        return id;
    }

    /* access modifiers changed from: 0000 */
    public void setAutomaticProcessing(boolean z) {
        this.mAutomaticProcessing = z;
    }

    /* access modifiers changed from: 0000 */
    public AppCenterFuture<Collection<ErrorReport>> getUnprocessedErrorReports() {
        final DefaultAppCenterFuture defaultAppCenterFuture = new DefaultAppCenterFuture();
        postAsyncGetter(new Runnable() {
            public void run() {
                ArrayList arrayList = new ArrayList(Crashes.this.mUnprocessedErrorReports.size());
                for (ErrorLogReport access$1200 : Crashes.this.mUnprocessedErrorReports.values()) {
                    arrayList.add(access$1200.report);
                }
                defaultAppCenterFuture.complete(arrayList);
            }
        }, defaultAppCenterFuture, Collections.emptyList());
        return defaultAppCenterFuture;
    }

    /* access modifiers changed from: 0000 */
    public AppCenterFuture<Boolean> sendCrashReportsOrAwaitUserConfirmation(final Collection<String> collection) {
        final DefaultAppCenterFuture defaultAppCenterFuture = new DefaultAppCenterFuture();
        postAsyncGetter(new Runnable() {
            public void run() {
                Iterator it = Crashes.this.mUnprocessedErrorReports.entrySet().iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    UUID uuid = (UUID) entry.getKey();
                    String id = ((ErrorLogReport) entry.getValue()).report.getId();
                    Collection collection = collection;
                    String str = Crashes.LOG_TAG;
                    if (collection == null || !collection.contains(id)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("CrashesListener.shouldProcess returned false, clean up and ignore log: ");
                        sb.append(id);
                        AppCenterLog.debug(str, sb.toString());
                        Crashes.this.removeAllStoredErrorLogFiles(uuid);
                        it.remove();
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("CrashesListener.shouldProcess returned true, continue processing log: ");
                        sb2.append(id);
                        AppCenterLog.debug(str, sb2.toString());
                    }
                }
                defaultAppCenterFuture.complete(Boolean.valueOf(Crashes.this.sendCrashReportsOrAwaitUserConfirmation()));
            }
        }, defaultAppCenterFuture, Boolean.valueOf(false));
        return defaultAppCenterFuture;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void sendErrorAttachments(final String str, final Iterable<ErrorAttachmentLog> iterable) {
        post(new Runnable() {
            public void run() {
                try {
                    Crashes.this.sendErrorAttachment(UUID.fromString(str), iterable);
                } catch (RuntimeException unused) {
                    AppCenterLog.error(Crashes.LOG_TAG, "Error report identifier has an invalid format for sending attachments.");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public static void saveMemoryRunningLevel(int i) {
        SharedPreferencesManager.putInt(PREF_KEY_MEMORY_RUNNING_LEVEL, i);
        AppCenterLog.debug(LOG_TAG, String.format("The memory running level (%s) was saved.", new Object[]{Integer.valueOf(i)}));
    }
}
