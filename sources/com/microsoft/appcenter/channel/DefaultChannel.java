package com.microsoft.appcenter.channel;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.microsoft.appcenter.CancellationException;
import com.microsoft.appcenter.channel.Channel.GroupListener;
import com.microsoft.appcenter.channel.Channel.Listener;
import com.microsoft.appcenter.http.HttpUtils;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.AppCenterIngestion;
import com.microsoft.appcenter.ingestion.Ingestion;
import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.ingestion.models.one.PartAUtils;
import com.microsoft.appcenter.persistence.DatabasePersistence;
import com.microsoft.appcenter.persistence.Persistence;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.HandlerUtils;
import com.microsoft.appcenter.utils.IdHelper;
import com.microsoft.appcenter.utils.context.AbstractTokenContextListener;
import com.microsoft.appcenter.utils.context.AuthTokenContext;
import com.microsoft.appcenter.utils.context.AuthTokenInfo;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class DefaultChannel implements Channel {
    @VisibleForTesting
    static final int CLEAR_BATCH_SIZE = 100;
    private static final long MINIMUM_TRANSMISSION_INTERVAL = 3000;
    @VisibleForTesting
    static final String START_TIMER_PREFIX = "startTimerPrefix.";
    /* access modifiers changed from: private */
    public final Handler mAppCenterHandler;
    private String mAppSecret;
    private final Context mContext;
    private int mCurrentState;
    private Device mDevice;
    private boolean mDiscardLogs;
    private boolean mEnabled;
    private final Map<String, GroupState> mGroupStates;
    private final Ingestion mIngestion;
    private final Set<Ingestion> mIngestions;
    private final UUID mInstallId;
    private final Collection<Listener> mListeners;
    private final Persistence mPersistence;

    @VisibleForTesting
    class GroupState extends AbstractTokenContextListener {
        final long mBatchTimeInterval;
        final Ingestion mIngestion;
        final GroupListener mListener;
        final int mMaxLogsPerBatch;
        final int mMaxParallelBatches;
        final String mName;
        boolean mPaused;
        final Collection<String> mPausedTargetKeys = new HashSet();
        int mPendingLogCount;
        final Runnable mRunnable = new Runnable() {
            public void run() {
                GroupState groupState = GroupState.this;
                groupState.mScheduled = false;
                DefaultChannel.this.triggerIngestion(GroupState.this);
            }
        };
        boolean mScheduled;
        final Map<String, List<Log>> mSendingBatches = new HashMap();

        GroupState(String str, int i, long j, int i2, Ingestion ingestion, GroupListener groupListener) {
            this.mName = str;
            this.mMaxLogsPerBatch = i;
            this.mBatchTimeInterval = j;
            this.mMaxParallelBatches = i2;
            this.mIngestion = ingestion;
            this.mListener = groupListener;
        }

        public void onNewAuthToken(String str) {
            DefaultChannel.this.checkPendingLogs(this);
        }
    }

    public DefaultChannel(@NonNull Context context, String str, @NonNull LogSerializer logSerializer, @NonNull Handler handler) {
        this(context, str, buildDefaultPersistence(context, logSerializer), new AppCenterIngestion(context, logSerializer), handler);
    }

    @VisibleForTesting
    DefaultChannel(@NonNull Context context, String str, @NonNull Persistence persistence, @NonNull Ingestion ingestion, @NonNull Handler handler) {
        this.mContext = context;
        this.mAppSecret = str;
        this.mInstallId = IdHelper.getInstallId();
        this.mGroupStates = new HashMap();
        this.mListeners = new LinkedHashSet();
        this.mPersistence = persistence;
        this.mIngestion = ingestion;
        this.mIngestions = new HashSet();
        this.mIngestions.add(this.mIngestion);
        this.mAppCenterHandler = handler;
        this.mEnabled = true;
    }

    private static Persistence buildDefaultPersistence(@NonNull Context context, @NonNull LogSerializer logSerializer) {
        DatabasePersistence databasePersistence = new DatabasePersistence(context);
        databasePersistence.setLogSerializer(logSerializer);
        return databasePersistence;
    }

    public synchronized boolean setMaxStorageSize(long j) {
        return this.mPersistence.setMaxStorageSize(j);
    }

    private synchronized boolean checkStateDidNotChange(GroupState groupState, int i) {
        return i == this.mCurrentState && groupState == this.mGroupStates.get(groupState.mName);
    }

    public synchronized void setAppSecret(@NonNull String str) {
        this.mAppSecret = str;
        if (this.mEnabled) {
            for (GroupState groupState : this.mGroupStates.values()) {
                if (groupState.mIngestion == this.mIngestion) {
                    checkPendingLogs(groupState);
                }
            }
        }
    }

    public synchronized void addGroup(String str, int i, long j, int i2, Ingestion ingestion, GroupListener groupListener) {
        String str2 = str;
        synchronized (this) {
            String str3 = "AppCenter";
            StringBuilder sb = new StringBuilder();
            sb.append("addGroup(");
            sb.append(str);
            sb.append(")");
            AppCenterLog.debug(str3, sb.toString());
            Ingestion ingestion2 = ingestion == null ? this.mIngestion : ingestion;
            this.mIngestions.add(ingestion2);
            GroupState groupState = new GroupState(str, i, j, i2, ingestion2, groupListener);
            this.mGroupStates.put(str, groupState);
            groupState.mPendingLogCount = this.mPersistence.countLogs(str);
            AuthTokenContext.getInstance().addListener(groupState);
            if (!(this.mAppSecret == null && this.mIngestion == ingestion2)) {
                checkPendingLogs(groupState);
            }
            for (Listener onGroupAdded : this.mListeners) {
                onGroupAdded.onGroupAdded(str, groupListener, j);
            }
        }
    }

    public synchronized void removeGroup(String str) {
        String str2 = "AppCenter";
        StringBuilder sb = new StringBuilder();
        sb.append("removeGroup(");
        sb.append(str);
        sb.append(")");
        AppCenterLog.debug(str2, sb.toString());
        GroupState groupState = (GroupState) this.mGroupStates.remove(str);
        if (groupState != null) {
            cancelTimer(groupState);
            AuthTokenContext.getInstance().removeListener(groupState);
        }
        for (Listener onGroupRemoved : this.mListeners) {
            onGroupRemoved.onGroupRemoved(str);
        }
    }

    public synchronized void pauseGroup(String str, String str2) {
        GroupState groupState = (GroupState) this.mGroupStates.get(str);
        if (groupState != null) {
            if (str2 != null) {
                String targetKey = PartAUtils.getTargetKey(str2);
                if (groupState.mPausedTargetKeys.add(targetKey)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("pauseGroup(");
                    sb.append(str);
                    sb.append(", ");
                    sb.append(targetKey);
                    sb.append(")");
                    AppCenterLog.debug("AppCenter", sb.toString());
                }
            } else if (!groupState.mPaused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("pauseGroup(");
                sb2.append(str);
                sb2.append(")");
                AppCenterLog.debug("AppCenter", sb2.toString());
                groupState.mPaused = true;
                cancelTimer(groupState);
            }
            for (Listener onPaused : this.mListeners) {
                onPaused.onPaused(str, str2);
            }
        }
    }

    public synchronized void resumeGroup(String str, String str2) {
        GroupState groupState = (GroupState) this.mGroupStates.get(str);
        if (groupState != null) {
            if (str2 != null) {
                String targetKey = PartAUtils.getTargetKey(str2);
                if (groupState.mPausedTargetKeys.remove(targetKey)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("resumeGroup(");
                    sb.append(str);
                    sb.append(", ");
                    sb.append(targetKey);
                    sb.append(")");
                    AppCenterLog.debug("AppCenter", sb.toString());
                    groupState.mPendingLogCount = this.mPersistence.countLogs(str);
                    checkPendingLogs(groupState);
                }
            } else if (groupState.mPaused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("resumeGroup(");
                sb2.append(str);
                sb2.append(")");
                AppCenterLog.debug("AppCenter", sb2.toString());
                groupState.mPaused = false;
                checkPendingLogs(groupState);
            }
            for (Listener onResumed : this.mListeners) {
                onResumed.onResumed(str, str2);
            }
        }
    }

    public synchronized boolean isEnabled() {
        return this.mEnabled;
    }

    public synchronized void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            if (z) {
                this.mEnabled = true;
                this.mDiscardLogs = false;
                this.mCurrentState++;
                for (Ingestion reopen : this.mIngestions) {
                    reopen.reopen();
                }
                for (GroupState checkPendingLogs : this.mGroupStates.values()) {
                    checkPendingLogs(checkPendingLogs);
                }
            } else {
                suspend(true, new CancellationException());
            }
            for (Listener onGloballyEnabled : this.mListeners) {
                onGloballyEnabled.onGloballyEnabled(z);
            }
        }
    }

    public synchronized void setLogUrl(String str) {
        this.mIngestion.setLogUrl(str);
    }

    public synchronized void clear(String str) {
        if (this.mGroupStates.containsKey(str)) {
            String str2 = "AppCenter";
            StringBuilder sb = new StringBuilder();
            sb.append("clear(");
            sb.append(str);
            sb.append(")");
            AppCenterLog.debug(str2, sb.toString());
            this.mPersistence.deleteLogs(str);
            for (Listener onClear : this.mListeners) {
                onClear.onClear(str);
            }
        }
    }

    public synchronized void invalidateDeviceCache() {
        this.mDevice = null;
    }

    private void suspend(boolean z, Exception exc) {
        this.mEnabled = false;
        this.mDiscardLogs = z;
        this.mCurrentState++;
        for (GroupState groupState : this.mGroupStates.values()) {
            cancelTimer(groupState);
            Iterator it = groupState.mSendingBatches.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                it.remove();
                if (z) {
                    GroupListener groupListener = groupState.mListener;
                    if (groupListener != null) {
                        for (Log onFailure : (List) entry.getValue()) {
                            groupListener.onFailure(onFailure, exc);
                        }
                    }
                }
            }
        }
        for (Ingestion ingestion : this.mIngestions) {
            try {
                ingestion.close();
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to close ingestion: ");
                sb.append(ingestion);
                AppCenterLog.error("AppCenter", sb.toString(), e);
            }
        }
        if (z) {
            for (GroupState deleteLogsOnSuspended : this.mGroupStates.values()) {
                deleteLogsOnSuspended(deleteLogsOnSuspended);
            }
            return;
        }
        this.mPersistence.clearPendingLogState();
    }

    private void deleteLogsOnSuspended(GroupState groupState) {
        ArrayList<Log> arrayList = new ArrayList<>();
        this.mPersistence.getLogs(groupState.mName, Collections.emptyList(), 100, arrayList, null, null);
        if (arrayList.size() > 0 && groupState.mListener != null) {
            for (Log log : arrayList) {
                groupState.mListener.onBeforeSending(log);
                groupState.mListener.onFailure(log, new CancellationException());
            }
        }
        if (arrayList.size() < 100 || groupState.mListener == null) {
            this.mPersistence.deleteLogs(groupState.mName);
        } else {
            deleteLogsOnSuspended(groupState);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void cancelTimer(GroupState groupState) {
        if (groupState.mScheduled) {
            groupState.mScheduled = false;
            this.mAppCenterHandler.removeCallbacks(groupState.mRunnable);
            StringBuilder sb = new StringBuilder();
            sb.append(START_TIMER_PREFIX);
            sb.append(groupState.mName);
            SharedPreferencesManager.remove(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public synchronized void triggerIngestion(@NonNull GroupState groupState) {
        Date date;
        final String str;
        GroupState groupState2 = groupState;
        synchronized (this) {
            if (this.mEnabled) {
                int i = groupState2.mPendingLogCount;
                int min = Math.min(i, groupState2.mMaxLogsPerBatch);
                StringBuilder sb = new StringBuilder();
                sb.append("triggerIngestion(");
                sb.append(groupState2.mName);
                sb.append(") pendingLogCount=");
                sb.append(i);
                AppCenterLog.debug("AppCenter", sb.toString());
                cancelTimer(groupState);
                if (groupState2.mSendingBatches.size() == groupState2.mMaxParallelBatches) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Already sending ");
                    sb2.append(groupState2.mMaxParallelBatches);
                    sb2.append(" batches of analytics data to the server.");
                    AppCenterLog.debug("AppCenter", sb2.toString());
                    return;
                }
                AuthTokenContext instance = AuthTokenContext.getInstance();
                ListIterator listIterator = instance.getAuthTokenValidityList().listIterator();
                while (listIterator.hasNext()) {
                    AuthTokenInfo authTokenInfo = (AuthTokenInfo) listIterator.next();
                    Date date2 = null;
                    if (authTokenInfo != null) {
                        String authToken = authTokenInfo.getAuthToken();
                        Date startTime = authTokenInfo.getStartTime();
                        Date endTime = authTokenInfo.getEndTime();
                        instance.checkIfTokenNeedsToBeRefreshed(authTokenInfo);
                        date = startTime;
                        Date date3 = endTime;
                        str = authToken;
                        date2 = date3;
                    } else {
                        str = null;
                        date = null;
                    }
                    ArrayList<Log> arrayList = new ArrayList<>(min);
                    final int i2 = this.mCurrentState;
                    String logs = this.mPersistence.getLogs(groupState2.mName, groupState2.mPausedTargetKeys, min, arrayList, date, date2);
                    groupState2.mPendingLogCount -= arrayList.size();
                    if (logs != null) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("ingestLogs(");
                        sb3.append(groupState2.mName);
                        sb3.append(",");
                        sb3.append(logs);
                        sb3.append(") pendingLogCount=");
                        sb3.append(groupState2.mPendingLogCount);
                        AppCenterLog.debug("AppCenter", sb3.toString());
                        if (groupState2.mListener != null) {
                            for (Log onBeforeSending : arrayList) {
                                groupState2.mListener.onBeforeSending(onBeforeSending);
                            }
                        }
                        groupState2.mSendingBatches.put(logs, arrayList);
                        final GroupState groupState3 = groupState;
                        final ArrayList arrayList2 = arrayList;
                        final String str2 = logs;
                        C24971 r1 = new Runnable() {
                            public void run() {
                                DefaultChannel.this.sendLogs(groupState3, i2, arrayList2, str2, str);
                            }
                        };
                        HandlerUtils.runOnUiThread(r1);
                        return;
                    } else if (listIterator.previousIndex() == 0 && date2 != null && this.mPersistence.countLogs(date2) == 0) {
                        instance.removeOldestTokenIfMatching(str);
                    }
                }
                groupState2.mPendingLogCount = this.mPersistence.countLogs(groupState2.mName);
            }
        }
    }

    /* access modifiers changed from: private */
    @MainThread
    public synchronized void sendLogs(final GroupState groupState, final int i, List<Log> list, final String str, String str2) {
        if (checkStateDidNotChange(groupState, i)) {
            LogContainer logContainer = new LogContainer();
            logContainer.setLogs(list);
            groupState.mIngestion.sendAsync(str2, this.mAppSecret, this.mInstallId, logContainer, new ServiceCallback() {
                public void onCallSucceeded(String str, Map<String, String> map) {
                    DefaultChannel.this.mAppCenterHandler.post(new Runnable() {
                        public void run() {
                            DefaultChannel.this.handleSendingSuccess(groupState, str);
                        }
                    });
                }

                public void onCallFailed(final Exception exc) {
                    DefaultChannel.this.mAppCenterHandler.post(new Runnable() {
                        public void run() {
                            DefaultChannel.this.handleSendingFailure(groupState, str, exc);
                        }
                    });
                }
            });
            this.mAppCenterHandler.post(new Runnable() {
                public void run() {
                    DefaultChannel.this.checkPendingLogsAfterPost(groupState, i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void checkPendingLogsAfterPost(@NonNull GroupState groupState, int i) {
        if (checkStateDidNotChange(groupState, i)) {
            checkPendingLogs(groupState);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void handleSendingSuccess(@NonNull GroupState groupState, @NonNull String str) {
        List<Log> list = (List) groupState.mSendingBatches.remove(str);
        if (list != null) {
            this.mPersistence.deleteLogs(groupState.mName, str);
            GroupListener groupListener = groupState.mListener;
            if (groupListener != null) {
                for (Log onSuccess : list) {
                    groupListener.onSuccess(onSuccess);
                }
            }
            checkPendingLogs(groupState);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void handleSendingFailure(@NonNull GroupState groupState, @NonNull String str, @NonNull Exception exc) {
        String str2 = groupState.mName;
        List<Log> list = (List) groupState.mSendingBatches.remove(str);
        if (list != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Sending logs groupName=");
            sb.append(str2);
            sb.append(" id=");
            sb.append(str);
            sb.append(" failed");
            AppCenterLog.error("AppCenter", sb.toString(), exc);
            boolean isRecoverableError = HttpUtils.isRecoverableError(exc);
            if (isRecoverableError) {
                groupState.mPendingLogCount += list.size();
            } else {
                GroupListener groupListener = groupState.mListener;
                if (groupListener != null) {
                    for (Log onFailure : list) {
                        groupListener.onFailure(onFailure, exc);
                    }
                }
            }
            suspend(!isRecoverableError, exc);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0187, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void enqueue(@androidx.annotation.NonNull com.microsoft.appcenter.ingestion.models.Log r7, @androidx.annotation.NonNull java.lang.String r8, int r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.Map<java.lang.String, com.microsoft.appcenter.channel.DefaultChannel$GroupState> r0 = r6.mGroupStates     // Catch:{ all -> 0x01a0 }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.DefaultChannel$GroupState r0 = (com.microsoft.appcenter.channel.DefaultChannel.GroupState) r0     // Catch:{ all -> 0x01a0 }
            if (r0 != 0) goto L_0x0023
            java.lang.String r7 = "AppCenter"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a0 }
            r9.<init>()     // Catch:{ all -> 0x01a0 }
            java.lang.String r0 = "Invalid group name:"
            r9.append(r0)     // Catch:{ all -> 0x01a0 }
            r9.append(r8)     // Catch:{ all -> 0x01a0 }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.utils.AppCenterLog.error(r7, r8)     // Catch:{ all -> 0x01a0 }
            monitor-exit(r6)
            return
        L_0x0023:
            boolean r1 = r6.mDiscardLogs     // Catch:{ all -> 0x01a0 }
            if (r1 == 0) goto L_0x0043
            java.lang.String r8 = "AppCenter"
            java.lang.String r9 = "Channel is disabled, the log is discarded."
            com.microsoft.appcenter.utils.AppCenterLog.warn(r8, r9)     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.Channel$GroupListener r8 = r0.mListener     // Catch:{ all -> 0x01a0 }
            if (r8 == 0) goto L_0x0041
            com.microsoft.appcenter.channel.Channel$GroupListener r8 = r0.mListener     // Catch:{ all -> 0x01a0 }
            r8.onBeforeSending(r7)     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.Channel$GroupListener r8 = r0.mListener     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.CancellationException r9 = new com.microsoft.appcenter.CancellationException     // Catch:{ all -> 0x01a0 }
            r9.<init>()     // Catch:{ all -> 0x01a0 }
            r8.onFailure(r7, r9)     // Catch:{ all -> 0x01a0 }
        L_0x0041:
            monitor-exit(r6)
            return
        L_0x0043:
            java.util.Collection<com.microsoft.appcenter.channel.Channel$Listener> r1 = r6.mListeners     // Catch:{ all -> 0x01a0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x01a0 }
        L_0x0049:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x01a0 }
            if (r2 == 0) goto L_0x0059
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.Channel$Listener r2 = (com.microsoft.appcenter.channel.Channel.Listener) r2     // Catch:{ all -> 0x01a0 }
            r2.onPreparingLog(r7, r8)     // Catch:{ all -> 0x01a0 }
            goto L_0x0049
        L_0x0059:
            com.microsoft.appcenter.ingestion.models.Device r1 = r7.getDevice()     // Catch:{ all -> 0x01a0 }
            if (r1 != 0) goto L_0x007b
            com.microsoft.appcenter.ingestion.models.Device r1 = r6.mDevice     // Catch:{ all -> 0x01a0 }
            if (r1 != 0) goto L_0x0076
            android.content.Context r1 = r6.mContext     // Catch:{ DeviceInfoException -> 0x006c }
            com.microsoft.appcenter.ingestion.models.Device r1 = com.microsoft.appcenter.utils.DeviceInfoHelper.getDeviceInfo(r1)     // Catch:{ DeviceInfoException -> 0x006c }
            r6.mDevice = r1     // Catch:{ DeviceInfoException -> 0x006c }
            goto L_0x0076
        L_0x006c:
            r7 = move-exception
            java.lang.String r8 = "AppCenter"
            java.lang.String r9 = "Device log cannot be generated"
            com.microsoft.appcenter.utils.AppCenterLog.error(r8, r9, r7)     // Catch:{ all -> 0x01a0 }
            monitor-exit(r6)
            return
        L_0x0076:
            com.microsoft.appcenter.ingestion.models.Device r1 = r6.mDevice     // Catch:{ all -> 0x01a0 }
            r7.setDevice(r1)     // Catch:{ all -> 0x01a0 }
        L_0x007b:
            java.util.Date r1 = r7.getTimestamp()     // Catch:{ all -> 0x01a0 }
            if (r1 != 0) goto L_0x0089
            java.util.Date r1 = new java.util.Date     // Catch:{ all -> 0x01a0 }
            r1.<init>()     // Catch:{ all -> 0x01a0 }
            r7.setTimestamp(r1)     // Catch:{ all -> 0x01a0 }
        L_0x0089:
            java.util.Collection<com.microsoft.appcenter.channel.Channel$Listener> r1 = r6.mListeners     // Catch:{ all -> 0x01a0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x01a0 }
        L_0x008f:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x01a0 }
            if (r2 == 0) goto L_0x009f
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.Channel$Listener r2 = (com.microsoft.appcenter.channel.Channel.Listener) r2     // Catch:{ all -> 0x01a0 }
            r2.onPreparedLog(r7, r8, r9)     // Catch:{ all -> 0x01a0 }
            goto L_0x008f
        L_0x009f:
            java.util.Collection<com.microsoft.appcenter.channel.Channel$Listener> r1 = r6.mListeners     // Catch:{ all -> 0x01a0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x01a0 }
            r2 = 0
        L_0x00a6:
            r3 = 0
        L_0x00a7:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x01a0 }
            r5 = 1
            if (r4 == 0) goto L_0x00be
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.Channel$Listener r4 = (com.microsoft.appcenter.channel.Channel.Listener) r4     // Catch:{ all -> 0x01a0 }
            if (r3 != 0) goto L_0x00bc
            boolean r3 = r4.shouldFilter(r7)     // Catch:{ all -> 0x01a0 }
            if (r3 == 0) goto L_0x00a6
        L_0x00bc:
            r3 = 1
            goto L_0x00a7
        L_0x00be:
            if (r3 == 0) goto L_0x00e1
            java.lang.String r8 = "AppCenter"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a0 }
            r9.<init>()     // Catch:{ all -> 0x01a0 }
            java.lang.String r0 = "Log of type '"
            r9.append(r0)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = r7.getType()     // Catch:{ all -> 0x01a0 }
            r9.append(r7)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = "' was filtered out by listener(s)"
            r9.append(r7)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = r9.toString()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r8, r7)     // Catch:{ all -> 0x01a0 }
            goto L_0x0186
        L_0x00e1:
            java.lang.String r1 = r6.mAppSecret     // Catch:{ all -> 0x01a0 }
            if (r1 != 0) goto L_0x010c
            com.microsoft.appcenter.ingestion.Ingestion r1 = r0.mIngestion     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.ingestion.Ingestion r2 = r6.mIngestion     // Catch:{ all -> 0x01a0 }
            if (r1 != r2) goto L_0x010c
            java.lang.String r8 = "AppCenter"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a0 }
            r9.<init>()     // Catch:{ all -> 0x01a0 }
            java.lang.String r0 = "Log of type '"
            r9.append(r0)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = r7.getType()     // Catch:{ all -> 0x01a0 }
            r9.append(r7)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = "' was not filtered out by listener(s) but no app secret was provided. Not persisting/sending the log."
            r9.append(r7)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = r9.toString()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r8, r7)     // Catch:{ all -> 0x01a0 }
            monitor-exit(r6)
            return
        L_0x010c:
            com.microsoft.appcenter.persistence.Persistence r1 = r6.mPersistence     // Catch:{ PersistenceException -> 0x0188 }
            r1.putLog(r7, r8, r9)     // Catch:{ PersistenceException -> 0x0188 }
            java.util.Set r7 = r7.getTransmissionTargetTokens()     // Catch:{ all -> 0x01a0 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x01a0 }
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x01a0 }
            if (r8 == 0) goto L_0x012a
            java.lang.Object r7 = r7.next()     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = com.microsoft.appcenter.ingestion.models.one.PartAUtils.getTargetKey(r7)     // Catch:{ all -> 0x01a0 }
            goto L_0x012b
        L_0x012a:
            r7 = 0
        L_0x012b:
            java.util.Collection<java.lang.String> r8 = r0.mPausedTargetKeys     // Catch:{ all -> 0x01a0 }
            boolean r8 = r8.contains(r7)     // Catch:{ all -> 0x01a0 }
            if (r8 == 0) goto L_0x0150
            java.lang.String r8 = "AppCenter"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a0 }
            r9.<init>()     // Catch:{ all -> 0x01a0 }
            java.lang.String r0 = "Transmission target ikey="
            r9.append(r0)     // Catch:{ all -> 0x01a0 }
            r9.append(r7)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = " is paused."
            r9.append(r7)     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = r9.toString()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r8, r7)     // Catch:{ all -> 0x01a0 }
            monitor-exit(r6)
            return
        L_0x0150:
            int r7 = r0.mPendingLogCount     // Catch:{ all -> 0x01a0 }
            int r7 = r7 + r5
            r0.mPendingLogCount = r7     // Catch:{ all -> 0x01a0 }
            java.lang.String r7 = "AppCenter"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a0 }
            r8.<init>()     // Catch:{ all -> 0x01a0 }
            java.lang.String r9 = "enqueue("
            r8.append(r9)     // Catch:{ all -> 0x01a0 }
            java.lang.String r9 = r0.mName     // Catch:{ all -> 0x01a0 }
            r8.append(r9)     // Catch:{ all -> 0x01a0 }
            java.lang.String r9 = ") pendingLogCount="
            r8.append(r9)     // Catch:{ all -> 0x01a0 }
            int r9 = r0.mPendingLogCount     // Catch:{ all -> 0x01a0 }
            r8.append(r9)     // Catch:{ all -> 0x01a0 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r7, r8)     // Catch:{ all -> 0x01a0 }
            boolean r7 = r6.mEnabled     // Catch:{ all -> 0x01a0 }
            if (r7 == 0) goto L_0x017f
            r6.checkPendingLogs(r0)     // Catch:{ all -> 0x01a0 }
            goto L_0x0186
        L_0x017f:
            java.lang.String r7 = "AppCenter"
            java.lang.String r8 = "Channel is temporarily disabled, log was saved to disk."
            com.microsoft.appcenter.utils.AppCenterLog.debug(r7, r8)     // Catch:{ all -> 0x01a0 }
        L_0x0186:
            monitor-exit(r6)
            return
        L_0x0188:
            r8 = move-exception
            java.lang.String r9 = "AppCenter"
            java.lang.String r1 = "Error persisting log"
            com.microsoft.appcenter.utils.AppCenterLog.error(r9, r1, r8)     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.Channel$GroupListener r9 = r0.mListener     // Catch:{ all -> 0x01a0 }
            if (r9 == 0) goto L_0x019e
            com.microsoft.appcenter.channel.Channel$GroupListener r9 = r0.mListener     // Catch:{ all -> 0x01a0 }
            r9.onBeforeSending(r7)     // Catch:{ all -> 0x01a0 }
            com.microsoft.appcenter.channel.Channel$GroupListener r9 = r0.mListener     // Catch:{ all -> 0x01a0 }
            r9.onFailure(r7, r8)     // Catch:{ all -> 0x01a0 }
        L_0x019e:
            monitor-exit(r6)
            return
        L_0x01a0:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.channel.DefaultChannel.enqueue(com.microsoft.appcenter.ingestion.models.Log, java.lang.String, int):void");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        return;
     */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void checkPendingLogs(@androidx.annotation.NonNull com.microsoft.appcenter.channel.DefaultChannel.GroupState r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = "AppCenter"
            java.lang.String r1 = "checkPendingLogs(%s) pendingLogCount=%s batchTimeInterval=%s"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0054 }
            r3 = 0
            java.lang.String r4 = r8.mName     // Catch:{ all -> 0x0054 }
            r2[r3] = r4     // Catch:{ all -> 0x0054 }
            int r3 = r8.mPendingLogCount     // Catch:{ all -> 0x0054 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0054 }
            r4 = 1
            r2[r4] = r3     // Catch:{ all -> 0x0054 }
            r3 = 2
            long r5 = r8.mBatchTimeInterval     // Catch:{ all -> 0x0054 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0054 }
            r2[r3] = r5     // Catch:{ all -> 0x0054 }
            java.lang.String r1 = java.lang.String.format(r1, r2)     // Catch:{ all -> 0x0054 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r0, r1)     // Catch:{ all -> 0x0054 }
            java.lang.Long r0 = r7.resolveTriggerInterval(r8)     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0052
            boolean r1 = r8.mPaused     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0031
            goto L_0x0052
        L_0x0031:
            long r1 = r0.longValue()     // Catch:{ all -> 0x0054 }
            r5 = 0
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x003f
            r7.triggerIngestion(r8)     // Catch:{ all -> 0x0054 }
            goto L_0x0050
        L_0x003f:
            boolean r1 = r8.mScheduled     // Catch:{ all -> 0x0054 }
            if (r1 != 0) goto L_0x0050
            r8.mScheduled = r4     // Catch:{ all -> 0x0054 }
            android.os.Handler r1 = r7.mAppCenterHandler     // Catch:{ all -> 0x0054 }
            java.lang.Runnable r8 = r8.mRunnable     // Catch:{ all -> 0x0054 }
            long r2 = r0.longValue()     // Catch:{ all -> 0x0054 }
            r1.postDelayed(r8, r2)     // Catch:{ all -> 0x0054 }
        L_0x0050:
            monitor-exit(r7)
            return
        L_0x0052:
            monitor-exit(r7)
            return
        L_0x0054:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.channel.DefaultChannel.checkPendingLogs(com.microsoft.appcenter.channel.DefaultChannel$GroupState):void");
    }

    @WorkerThread
    private Long resolveTriggerInterval(@NonNull GroupState groupState) {
        if (groupState.mBatchTimeInterval > MINIMUM_TRANSMISSION_INTERVAL) {
            return resolveCustomTriggerInterval(groupState);
        }
        return resolveDefaultTriggerInterval(groupState);
    }

    @WorkerThread
    private Long resolveCustomTriggerInterval(@NonNull GroupState groupState) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        String str = START_TIMER_PREFIX;
        sb.append(str);
        sb.append(groupState.mName);
        long j = SharedPreferencesManager.getLong(sb.toString());
        String str2 = "AppCenter";
        if (groupState.mPendingLogCount <= 0) {
            if (j + groupState.mBatchTimeInterval < currentTimeMillis) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(groupState.mName);
                SharedPreferencesManager.remove(sb2.toString());
                StringBuilder sb3 = new StringBuilder();
                sb3.append("The timer for ");
                sb3.append(groupState.mName);
                sb3.append(" channel finished.");
                AppCenterLog.debug(str2, sb3.toString());
            }
            return null;
        } else if (j != 0 && j <= currentTimeMillis) {
            return Long.valueOf(Math.max(groupState.mBatchTimeInterval - (currentTimeMillis - j), 0));
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(groupState.mName);
            SharedPreferencesManager.putLong(sb4.toString(), currentTimeMillis);
            StringBuilder sb5 = new StringBuilder();
            sb5.append("The timer value for ");
            sb5.append(groupState.mName);
            sb5.append(" has been saved.");
            AppCenterLog.debug(str2, sb5.toString());
            return Long.valueOf(groupState.mBatchTimeInterval);
        }
    }

    private Long resolveDefaultTriggerInterval(@NonNull GroupState groupState) {
        if (groupState.mPendingLogCount >= groupState.mMaxLogsPerBatch) {
            return Long.valueOf(0);
        }
        return groupState.mPendingLogCount > 0 ? Long.valueOf(groupState.mBatchTimeInterval) : null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public GroupState getGroupState(String str) {
        return (GroupState) this.mGroupStates.get(str);
    }

    public synchronized void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public synchronized void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public synchronized void shutdown() {
        suspend(false, new CancellationException());
    }
}
