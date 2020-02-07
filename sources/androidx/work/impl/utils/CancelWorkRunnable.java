package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.WorkerThread;
import androidx.work.Operation;
import androidx.work.Operation.State.FAILURE;
import androidx.work.WorkInfo.State;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpecDao;
import java.util.LinkedList;
import java.util.UUID;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class CancelWorkRunnable implements Runnable {
    private final OperationImpl mOperation = new OperationImpl();

    /* access modifiers changed from: 0000 */
    public abstract void runInternal();

    public Operation getOperation() {
        return this.mOperation;
    }

    public void run() {
        try {
            runInternal();
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.setState(new FAILURE(th));
        }
    }

    /* access modifiers changed from: 0000 */
    public void cancel(WorkManagerImpl workManagerImpl, String str) {
        iterativelyCancelWorkAndDependents(workManagerImpl.getWorkDatabase(), str);
        workManagerImpl.getProcessor().stopAndCancelWork(str);
        for (Scheduler cancel : workManagerImpl.getSchedulers()) {
            cancel.cancel(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public void reschedulePendingWorkers(WorkManagerImpl workManagerImpl) {
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private void iterativelyCancelWorkAndDependents(WorkDatabase workDatabase, String str) {
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        DependencyDao dependencyDao = workDatabase.dependencyDao();
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            State state = workSpecDao.getState(str2);
            if (!(state == State.SUCCEEDED || state == State.FAILED)) {
                workSpecDao.setState(State.CANCELLED, str2);
            }
            linkedList.addAll(dependencyDao.getDependentWorkIds(str2));
        }
    }

    public static CancelWorkRunnable forId(@NonNull final UUID uuid, @NonNull final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: 0000 */
            @WorkerThread
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    cancel(workManagerImpl, uuid.toString());
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    reschedulePendingWorkers(workManagerImpl);
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forTag(@NonNull final String str, @NonNull final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: 0000 */
            @WorkerThread
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String cancel : workDatabase.workSpecDao().getUnfinishedWorkWithTag(str)) {
                        cancel(workManagerImpl, cancel);
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    reschedulePendingWorkers(workManagerImpl);
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forName(@NonNull final String str, @NonNull final WorkManagerImpl workManagerImpl, final boolean z) {
        return new CancelWorkRunnable() {
            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: 0000 */
            @WorkerThread
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String cancel : workDatabase.workSpecDao().getUnfinishedWorkWithName(str)) {
                        cancel(workManagerImpl, cancel);
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    if (z) {
                        reschedulePendingWorkers(workManagerImpl);
                    }
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forAll(@NonNull final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* access modifiers changed from: 0000 */
            @WorkerThread
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String cancel : workDatabase.workSpecDao().getAllUnfinishedWork()) {
                        cancel(workManagerImpl, cancel);
                    }
                    workDatabase.setTransactionSuccessful();
                    new Preferences(workManagerImpl.getApplicationContext()).setLastCancelAllTimeMillis(System.currentTimeMillis());
                } finally {
                    workDatabase.endTransaction();
                }
            }
        };
    }
}
