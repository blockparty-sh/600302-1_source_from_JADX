package androidx.work;

import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public final class OneTimeWorkRequest extends WorkRequest {

    public static final class Builder extends androidx.work.WorkRequest.Builder<Builder, OneTimeWorkRequest> {
        /* access modifiers changed from: 0000 */
        @NonNull
        public Builder getThis() {
            return this;
        }

        public Builder(@NonNull Class<? extends ListenableWorker> cls) {
            super(cls);
        }

        @NonNull
        public Builder setInputMerger(@NonNull Class<? extends InputMerger> cls) {
            this.mWorkSpec.inputMergerClassName = cls.getName();
            return this;
        }

        /* access modifiers changed from: 0000 */
        @NonNull
        public OneTimeWorkRequest buildInternal() {
            if (!this.mBackoffCriteriaSet || VERSION.SDK_INT < 23 || !this.mWorkSpec.constraints.requiresDeviceIdle()) {
                return new OneTimeWorkRequest(this);
            }
            throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
        }
    }

    @NonNull
    public static OneTimeWorkRequest from(@NonNull Class<? extends ListenableWorker> cls) {
        return (OneTimeWorkRequest) new Builder(cls).build();
    }

    @NonNull
    public static List<OneTimeWorkRequest> from(@NonNull List<Class<? extends ListenableWorker>> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Class builder : list) {
            arrayList.add(new Builder(builder).build());
        }
        return arrayList;
    }

    OneTimeWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }
}
