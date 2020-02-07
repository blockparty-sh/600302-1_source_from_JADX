package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig.Builder;
import androidx.recyclerview.widget.AsyncListDiffer.ListListener;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

public abstract class ListAdapter<T, VH extends ViewHolder> extends Adapter<VH> {
    final AsyncListDiffer<T> mDiffer;
    private final ListListener<T> mListener = new ListListener<T>() {
        public void onCurrentListChanged(@NonNull List<T> list, @NonNull List<T> list2) {
            ListAdapter.this.onCurrentListChanged(list, list2);
        }
    };

    public void onCurrentListChanged(@NonNull List<T> list, @NonNull List<T> list2) {
    }

    protected ListAdapter(@NonNull ItemCallback<T> itemCallback) {
        this.mDiffer = new AsyncListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), new Builder(itemCallback).build());
        this.mDiffer.addListListener(this.mListener);
    }

    protected ListAdapter(@NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mDiffer = new AsyncListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.mDiffer.addListListener(this.mListener);
    }

    public void submitList(@Nullable List<T> list) {
        this.mDiffer.submitList(list);
    }

    public void submitList(@Nullable List<T> list, @Nullable Runnable runnable) {
        this.mDiffer.submitList(list, runnable);
    }

    /* access modifiers changed from: protected */
    public T getItem(int i) {
        return this.mDiffer.getCurrentList().get(i);
    }

    public int getItemCount() {
        return this.mDiffer.getCurrentList().size();
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mDiffer.getCurrentList();
    }
}
