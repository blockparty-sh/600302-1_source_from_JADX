package p015io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.annotation.Nullable;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;

/* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionNotifier */
interface SectionNotifier {
    void notifyAllItemsChanged();

    void notifyAllItemsChanged(@Nullable Object obj);

    void notifyAllItemsInserted();

    void notifyFooterChanged();

    void notifyFooterChanged(@Nullable Object obj);

    void notifyFooterInserted();

    void notifyFooterRemoved();

    void notifyHeaderChanged();

    void notifyHeaderChanged(@Nullable Object obj);

    void notifyHeaderInserted();

    void notifyHeaderRemoved();

    void notifyItemChanged(int i);

    void notifyItemChanged(int i, @Nullable Object obj);

    void notifyItemInserted(int i);

    void notifyItemMoved(int i, int i2);

    void notifyItemRangeChanged(int i, int i2);

    void notifyItemRangeChanged(int i, int i2, @Nullable Object obj);

    void notifyItemRangeInserted(int i, int i2);

    void notifyItemRangeRemoved(int i, int i2);

    void notifyItemRemoved(int i);

    void notifyNotLoadedStateChanged(State state);

    void notifySectionChangedToInvisible(int i);

    void notifySectionChangedToVisible();

    void notifyStateChangedFromLoaded(int i);

    void notifyStateChangedToLoaded(State state);
}
