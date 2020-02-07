package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

@RestrictTo({Scope.LIBRARY_GROUP})
public class LiveDataUtils {
    public static <In, Out> LiveData<Out> dedupedMappedLiveDataFor(@NonNull LiveData<In> liveData, @NonNull final Function<In, Out> function, @NonNull final TaskExecutor taskExecutor) {
        final Object obj = new Object();
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<In>() {
            Out mCurrentOutput = null;

            public void onChanged(@Nullable final In in) {
                taskExecutor.executeOnBackgroundThread(new Runnable() {
                    public void run() {
                        synchronized (obj) {
                            Out apply = function.apply(in);
                            if (C08071.this.mCurrentOutput == null && apply != null) {
                                C08071.this.mCurrentOutput = apply;
                                mediatorLiveData.postValue(apply);
                            } else if (C08071.this.mCurrentOutput != null && !C08071.this.mCurrentOutput.equals(apply)) {
                                C08071.this.mCurrentOutput = apply;
                                mediatorLiveData.postValue(apply);
                            }
                        }
                    }
                });
            }
        });
        return mediatorLiveData;
    }

    private LiveDataUtils() {
    }
}
