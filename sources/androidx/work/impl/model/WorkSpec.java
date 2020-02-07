package androidx.work.impl.model;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.arch.core.util.Function;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OverwritingInputMerger;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import androidx.work.WorkRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestrictTo({Scope.LIBRARY_GROUP})
@Entity(indices = {@Index({"schedule_requested_at"})})
public class WorkSpec {
    public static final long SCHEDULE_NOT_REQUESTED_YET = -1;
    private static final String TAG = Logger.tagWithPrefix("WorkSpec");
    public static final Function<List<WorkInfoPojo>, List<WorkInfo>> WORK_INFO_MAPPER = new Function<List<WorkInfoPojo>, List<WorkInfo>>() {
        public List<WorkInfo> apply(List<WorkInfoPojo> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (WorkInfoPojo workInfo : list) {
                arrayList.add(workInfo.toWorkInfo());
            }
            return arrayList;
        }
    };
    @ColumnInfo(name = "backoff_delay_duration")
    public long backoffDelayDuration = WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS;
    @ColumnInfo(name = "backoff_policy")
    @NonNull
    public BackoffPolicy backoffPolicy = BackoffPolicy.EXPONENTIAL;
    @NonNull
    @Embedded
    public Constraints constraints = Constraints.NONE;
    @ColumnInfo(name = "flex_duration")
    public long flexDuration;
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey

    /* renamed from: id */
    public String f87id;
    @ColumnInfo(name = "initial_delay")
    public long initialDelay;
    @ColumnInfo(name = "input")
    @NonNull
    public Data input = Data.EMPTY;
    @ColumnInfo(name = "input_merger_class_name")
    public String inputMergerClassName = OverwritingInputMerger.class.getName();
    @ColumnInfo(name = "interval_duration")
    public long intervalDuration;
    @ColumnInfo(name = "minimum_retention_duration")
    public long minimumRetentionDuration;
    @ColumnInfo(name = "output")
    @NonNull
    public Data output = Data.EMPTY;
    @ColumnInfo(name = "period_start_time")
    public long periodStartTime;
    @ColumnInfo(name = "run_attempt_count")
    @IntRange(from = 0)
    public int runAttemptCount;
    @ColumnInfo(name = "schedule_requested_at")
    public long scheduleRequestedAt = -1;
    @ColumnInfo(name = "state")
    @NonNull
    public State state = State.ENQUEUED;
    @ColumnInfo(name = "worker_class_name")
    @NonNull
    public String workerClassName;

    public static class IdAndState {
        @ColumnInfo(name = "id")

        /* renamed from: id */
        public String f88id;
        @ColumnInfo(name = "state")
        public State state;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            IdAndState idAndState = (IdAndState) obj;
            if (this.state != idAndState.state) {
                return false;
            }
            return this.f88id.equals(idAndState.f88id);
        }

        public int hashCode() {
            return (this.f88id.hashCode() * 31) + this.state.hashCode();
        }
    }

    public static class WorkInfoPojo {
        @ColumnInfo(name = "id")

        /* renamed from: id */
        public String f89id;
        @ColumnInfo(name = "output")
        public Data output;
        @ColumnInfo(name = "run_attempt_count")
        public int runAttemptCount;
        @ColumnInfo(name = "state")
        public State state;
        @Relation(entity = WorkTag.class, entityColumn = "work_spec_id", parentColumn = "id", projection = {"tag"})
        public List<String> tags;

        public WorkInfo toWorkInfo() {
            WorkInfo workInfo = new WorkInfo(UUID.fromString(this.f89id), this.state, this.output, this.tags, this.runAttemptCount);
            return workInfo;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WorkInfoPojo workInfoPojo = (WorkInfoPojo) obj;
            if (this.runAttemptCount != workInfoPojo.runAttemptCount) {
                return false;
            }
            String str = this.f89id;
            if (str == null ? workInfoPojo.f89id != null : !str.equals(workInfoPojo.f89id)) {
                return false;
            }
            if (this.state != workInfoPojo.state) {
                return false;
            }
            Data data = this.output;
            if (data == null ? workInfoPojo.output != null : !data.equals(workInfoPojo.output)) {
                return false;
            }
            List<String> list = this.tags;
            if (list != null) {
                z = list.equals(workInfoPojo.tags);
            } else if (workInfoPojo.tags != null) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            String str = this.f89id;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            State state2 = this.state;
            int hashCode2 = (hashCode + (state2 != null ? state2.hashCode() : 0)) * 31;
            Data data = this.output;
            int hashCode3 = (((hashCode2 + (data != null ? data.hashCode() : 0)) * 31) + this.runAttemptCount) * 31;
            List<String> list = this.tags;
            if (list != null) {
                i = list.hashCode();
            }
            return hashCode3 + i;
        }
    }

    public WorkSpec(@NonNull String str, @NonNull String str2) {
        this.f87id = str;
        this.workerClassName = str2;
    }

    public WorkSpec(@NonNull WorkSpec workSpec) {
        this.f87id = workSpec.f87id;
        this.workerClassName = workSpec.workerClassName;
        this.state = workSpec.state;
        this.inputMergerClassName = workSpec.inputMergerClassName;
        this.input = new Data(workSpec.input);
        this.output = new Data(workSpec.output);
        this.initialDelay = workSpec.initialDelay;
        this.intervalDuration = workSpec.intervalDuration;
        this.flexDuration = workSpec.flexDuration;
        this.constraints = new Constraints(workSpec.constraints);
        this.runAttemptCount = workSpec.runAttemptCount;
        this.backoffPolicy = workSpec.backoffPolicy;
        this.backoffDelayDuration = workSpec.backoffDelayDuration;
        this.periodStartTime = workSpec.periodStartTime;
        this.minimumRetentionDuration = workSpec.minimumRetentionDuration;
        this.scheduleRequestedAt = workSpec.scheduleRequestedAt;
    }

    public void setBackoffDelayDuration(long j) {
        if (j > WorkRequest.MAX_BACKOFF_MILLIS) {
            Logger.get().warning(TAG, "Backoff delay duration exceeds maximum value", new Throwable[0]);
            j = 18000000;
        }
        if (j < WorkRequest.MIN_BACKOFF_MILLIS) {
            Logger.get().warning(TAG, "Backoff delay duration less than minimum value", new Throwable[0]);
            j = 10000;
        }
        this.backoffDelayDuration = j;
    }

    public boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public boolean isBackedOff() {
        return this.state == State.ENQUEUED && this.runAttemptCount > 0;
    }

    public void setPeriodic(long j) {
        if (j < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            Logger.get().warning(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", new Object[]{Long.valueOf(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS)}), new Throwable[0]);
            j = 900000;
        }
        setPeriodic(j, j);
    }

    public void setPeriodic(long j, long j2) {
        if (j < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            Logger.get().warning(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", new Object[]{Long.valueOf(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS)}), new Throwable[0]);
            j = 900000;
        }
        if (j2 < PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
            Logger.get().warning(TAG, String.format("Flex duration lesser than minimum allowed value; Changed to %s", new Object[]{Long.valueOf(PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS)}), new Throwable[0]);
            j2 = 300000;
        }
        if (j2 > j) {
            Logger.get().warning(TAG, String.format("Flex duration greater than interval duration; Changed to %s", new Object[]{Long.valueOf(j)}), new Throwable[0]);
            j2 = j;
        }
        this.intervalDuration = j;
        this.flexDuration = j2;
    }

    public long calculateNextRunTime() {
        long j;
        boolean z = false;
        if (isBackedOff()) {
            if (this.backoffPolicy == BackoffPolicy.LINEAR) {
                z = true;
            }
            if (z) {
                j = this.backoffDelayDuration * ((long) this.runAttemptCount);
            } else {
                j = (long) Math.scalb((float) this.backoffDelayDuration, this.runAttemptCount - 1);
            }
            return this.periodStartTime + Math.min(WorkRequest.MAX_BACKOFF_MILLIS, j);
        }
        long j2 = 0;
        if (isPeriodic()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = this.periodStartTime;
            if (j3 == 0) {
                j3 = this.initialDelay + currentTimeMillis;
            }
            if (this.flexDuration != this.intervalDuration) {
                z = true;
            }
            if (z) {
                if (this.periodStartTime == 0) {
                    j2 = this.flexDuration * -1;
                }
                return j3 + this.intervalDuration + j2;
            }
            if (this.periodStartTime != 0) {
                j2 = this.intervalDuration;
            }
            return j3 + j2;
        }
        long j4 = this.periodStartTime;
        if (j4 == 0) {
            j4 = System.currentTimeMillis();
        }
        return j4 + this.initialDelay;
    }

    public boolean hasConstraints() {
        return !Constraints.NONE.equals(this.constraints);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) obj;
        if (this.initialDelay != workSpec.initialDelay || this.intervalDuration != workSpec.intervalDuration || this.flexDuration != workSpec.flexDuration || this.runAttemptCount != workSpec.runAttemptCount || this.backoffDelayDuration != workSpec.backoffDelayDuration || this.periodStartTime != workSpec.periodStartTime || this.minimumRetentionDuration != workSpec.minimumRetentionDuration || this.scheduleRequestedAt != workSpec.scheduleRequestedAt || !this.f87id.equals(workSpec.f87id) || this.state != workSpec.state || !this.workerClassName.equals(workSpec.workerClassName)) {
            return false;
        }
        String str = this.inputMergerClassName;
        if (str == null ? workSpec.inputMergerClassName != null : !str.equals(workSpec.inputMergerClassName)) {
            return false;
        }
        if (!this.input.equals(workSpec.input) || !this.output.equals(workSpec.output) || !this.constraints.equals(workSpec.constraints)) {
            return false;
        }
        if (this.backoffPolicy != workSpec.backoffPolicy) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = ((((this.f87id.hashCode() * 31) + this.state.hashCode()) * 31) + this.workerClassName.hashCode()) * 31;
        String str = this.inputMergerClassName;
        int hashCode2 = (((((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.input.hashCode()) * 31) + this.output.hashCode()) * 31;
        long j = this.initialDelay;
        int i = (hashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.intervalDuration;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.flexDuration;
        int hashCode3 = (((((((i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31) + this.constraints.hashCode()) * 31) + this.runAttemptCount) * 31) + this.backoffPolicy.hashCode()) * 31;
        long j4 = this.backoffDelayDuration;
        int i3 = (hashCode3 + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        long j5 = this.periodStartTime;
        int i4 = (i3 + ((int) (j5 ^ (j5 >>> 32)))) * 31;
        long j6 = this.minimumRetentionDuration;
        int i5 = (i4 + ((int) (j6 ^ (j6 >>> 32)))) * 31;
        long j7 = this.scheduleRequestedAt;
        return i5 + ((int) (j7 ^ (j7 >>> 32)));
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{WorkSpec: ");
        sb.append(this.f87id);
        sb.append("}");
        return sb.toString();
    }
}
