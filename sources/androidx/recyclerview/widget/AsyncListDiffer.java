package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig.Builder;
import androidx.recyclerview.widget.DiffUtil.Callback;
import androidx.recyclerview.widget.DiffUtil.DiffResult;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

public class AsyncListDiffer<T> {
    private static final Executor sMainThreadExecutor = new MainThreadExecutor();
    final AsyncDifferConfig<T> mConfig;
    @Nullable
    private List<T> mList;
    private final List<ListListener<T>> mListeners;
    Executor mMainThreadExecutor;
    int mMaxScheduledGeneration;
    @NonNull
    private List<T> mReadOnlyList;
    private final ListUpdateCallback mUpdateCallback;

    public interface ListListener<T> {
        void onCurrentListChanged(@NonNull List<T> list, @NonNull List<T> list2);
    }

    private static class MainThreadExecutor implements Executor {
        final Handler mHandler = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        public void execute(@NonNull Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public AsyncListDiffer(@NonNull Adapter adapter, @NonNull ItemCallback<T> itemCallback) {
        this((ListUpdateCallback) new AdapterListUpdateCallback(adapter), new Builder(itemCallback).build());
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mListeners = new CopyOnWriteArrayList();
        this.mReadOnlyList = Collections.emptyList();
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = asyncDifferConfig;
        if (asyncDifferConfig.getMainThreadExecutor() != null) {
            this.mMainThreadExecutor = asyncDifferConfig.getMainThreadExecutor();
        } else {
            this.mMainThreadExecutor = sMainThreadExecutor;
        }
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mReadOnlyList;
    }

    public void submitList(@Nullable List<T> list) {
        submitList(list, null);
    }

    public void submitList(@Nullable List<T> list, @Nullable Runnable runnable) {
        final int i = this.mMaxScheduledGeneration + 1;
        this.mMaxScheduledGeneration = i;
        final List<T> list2 = this.mList;
        if (list == list2) {
            if (runnable != null) {
                runnable.run();
            }
            return;
        }
        List<T> list3 = this.mReadOnlyList;
        if (list == null) {
            int size = list2.size();
            this.mList = null;
            this.mReadOnlyList = Collections.emptyList();
            this.mUpdateCallback.onRemoved(0, size);
            onCurrentListChanged(list3, runnable);
        } else if (list2 == null) {
            this.mList = list;
            this.mReadOnlyList = Collections.unmodifiableList(list);
            this.mUpdateCallback.onInserted(0, list.size());
            onCurrentListChanged(list3, runnable);
        } else {
            Executor backgroundThreadExecutor = this.mConfig.getBackgroundThreadExecutor();
            final List<T> list4 = list;
            final Runnable runnable2 = runnable;
            C05631 r1 = new Runnable() {
                public void run() {
                    final DiffResult calculateDiff = DiffUtil.calculateDiff(new Callback() {
                        public int getOldListSize() {
                            return list2.size();
                        }

                        public int getNewListSize() {
                            return list4.size();
                        }

                        public boolean areItemsTheSame(int i, int i2) {
                            Object obj = list2.get(i);
                            Object obj2 = list4.get(i2);
                            if (obj != null && obj2 != null) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(obj, obj2);
                            }
                            return obj == null && obj2 == null;
                        }

                        public boolean areContentsTheSame(int i, int i2) {
                            Object obj = list2.get(i);
                            Object obj2 = list4.get(i2);
                            if (obj != null && obj2 != null) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(obj, obj2);
                            }
                            if (obj == null && obj2 == null) {
                                return true;
                            }
                            throw new AssertionError();
                        }

                        @Nullable
                        public Object getChangePayload(int i, int i2) {
                            Object obj = list2.get(i);
                            Object obj2 = list4.get(i2);
                            if (obj != null && obj2 != null) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(obj, obj2);
                            }
                            throw new AssertionError();
                        }
                    });
                    AsyncListDiffer.this.mMainThreadExecutor.execute(new Runnable() {
                        public void run() {
                            if (AsyncListDiffer.this.mMaxScheduledGeneration == i) {
                                AsyncListDiffer.this.latchList(list4, calculateDiff, runnable2);
                            }
                        }
                    });
                }
            };
            backgroundThreadExecutor.execute(r1);
        }
    }

    /* access modifiers changed from: 0000 */
    public void latchList(@NonNull List<T> list, @NonNull DiffResult diffResult, @Nullable Runnable runnable) {
        List<T> list2 = this.mReadOnlyList;
        this.mList = list;
        this.mReadOnlyList = Collections.unmodifiableList(list);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
        onCurrentListChanged(list2, runnable);
    }

    private void onCurrentListChanged(@NonNull List<T> list, @Nullable Runnable runnable) {
        for (ListListener onCurrentListChanged : this.mListeners) {
            onCurrentListChanged.onCurrentListChanged(list, this.mReadOnlyList);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public void addListListener(@NonNull ListListener<T> listListener) {
        this.mListeners.add(listListener);
    }

    public void removeListListener(@NonNull ListListener<T> listListener) {
        this.mListeners.remove(listListener);
    }
}
