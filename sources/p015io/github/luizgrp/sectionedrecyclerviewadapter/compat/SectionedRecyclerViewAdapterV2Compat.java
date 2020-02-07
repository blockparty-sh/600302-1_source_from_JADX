package p015io.github.luizgrp.sectionedrecyclerviewadapter.compat;

import p015io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.compat.SectionedRecyclerViewAdapterV2Compat */
public class SectionedRecyclerViewAdapterV2Compat extends SectionedRecyclerViewAdapter {
    public int getFooterPositionInAdapter(Section section) {
        return getAdapterForSection(section).getFooterPosition();
    }

    public int getFooterPositionInAdapter(String str) {
        return getAdapterForSection(str).getFooterPosition();
    }

    public int getHeaderPositionInAdapter(Section section) {
        return getAdapterForSection(section).getHeaderPosition();
    }

    public int getHeaderPositionInAdapter(String str) {
        return getAdapterForSection(str).getHeaderPosition();
    }

    public int getPositionInAdapter(Section section, int i) {
        return getAdapterForSection(section).getPositionInAdapter(i);
    }

    public int getPositionInAdapter(String str, int i) {
        return getAdapterForSection(str).getPositionInAdapter(i);
    }

    public int getSectionPosition(Section section) {
        return getAdapterForSection(section).getSectionPosition();
    }

    public int getSectionPosition(String str) {
        return getAdapterForSection(str).getSectionPosition();
    }

    public void notifyAllItemsChangedInSection(Section section) {
        getAdapterForSection(section).notifyAllItemsChanged();
    }

    public void notifyAllItemsChangedInSection(String str) {
        getAdapterForSection(str).notifyAllItemsChanged();
    }

    public void notifyAllItemsInsertedInSection(Section section) {
        getAdapterForSection(section).notifyAllItemsInserted();
    }

    public void notifyAllItemsInsertedInSection(String str) {
        getAdapterForSection(str).notifyAllItemsInserted();
    }

    public void notifyFooterChangedInSection(Section section) {
        getAdapterForSection(section).notifyFooterChanged();
    }

    public void notifyFooterChangedInSection(String str) {
        getAdapterForSection(str).notifyFooterChanged();
    }

    public void notifyFooterInsertedInSection(Section section) {
        getAdapterForSection(section).notifyFooterInserted();
    }

    public void notifyFooterInsertedInSection(String str) {
        getAdapterForSection(str).notifyFooterInserted();
    }

    public void notifyFooterRemovedFromSection(Section section) {
        getAdapterForSection(section).notifyFooterRemoved();
    }

    public void notifyFooterRemovedFromSection(String str) {
        getAdapterForSection(str).notifyFooterRemoved();
    }

    public void notifyHeaderChangedInSection(Section section) {
        getAdapterForSection(section).notifyHeaderChanged();
    }

    public void notifyHeaderChangedInSection(String str) {
        getAdapterForSection(str).notifyHeaderChanged();
    }

    public void notifyHeaderInsertedInSection(Section section) {
        getAdapterForSection(section).notifyHeaderInserted();
    }

    public void notifyHeaderInsertedInSection(String str) {
        getAdapterForSection(str).notifyHeaderInserted();
    }

    public void notifyHeaderRemovedFromSection(Section section) {
        getAdapterForSection(section).notifyHeaderRemoved();
    }

    public void notifyHeaderRemovedFromSection(String str) {
        getAdapterForSection(str).notifyHeaderRemoved();
    }

    public void notifyItemChangedInSection(Section section, int i) {
        getAdapterForSection(section).notifyItemChanged(i);
    }

    public void notifyItemChangedInSection(String str, int i) {
        getAdapterForSection(str).notifyItemChanged(i);
    }

    public void notifyItemInsertedInSection(Section section, int i) {
        getAdapterForSection(section).notifyItemInserted(i);
    }

    public void notifyItemInsertedInSection(String str, int i) {
        getAdapterForSection(str).notifyItemInserted(i);
    }

    public void notifyItemMovedInSection(Section section, int i, int i2) {
        getAdapterForSection(section).notifyItemMoved(i, i2);
    }

    public void notifyItemMovedInSection(String str, int i, int i2) {
        getAdapterForSection(str).notifyItemMoved(i, i2);
    }

    public void notifyItemRangeChangedInSection(Section section, int i, int i2) {
        getAdapterForSection(section).notifyItemRangeChanged(i, i2);
    }

    public void notifyItemRangeChangedInSection(Section section, int i, int i2, Object obj) {
        getAdapterForSection(section).notifyItemRangeChanged(i, i2, obj);
    }

    public void notifyItemRangeChangedInSection(String str, int i, int i2) {
        getAdapterForSection(str).notifyItemRangeChanged(i, i2);
    }

    public void notifyItemRangeChangedInSection(String str, int i, int i2, Object obj) {
        getAdapterForSection(str).notifyItemRangeChanged(i, i2, obj);
    }

    public void notifyItemRangeInsertedInSection(Section section, int i, int i2) {
        getAdapterForSection(section).notifyItemRangeInserted(i, i2);
    }

    public void notifyItemRangeInsertedInSection(String str, int i, int i2) {
        getAdapterForSection(str).notifyItemRangeInserted(i, i2);
    }

    public void notifyItemRangeRemovedFromSection(Section section, int i, int i2) {
        getAdapterForSection(section).notifyItemRangeRemoved(i, i2);
    }

    public void notifyItemRangeRemovedFromSection(String str, int i, int i2) {
        getAdapterForSection(str).notifyItemRangeRemoved(i, i2);
    }

    public void notifyItemRemovedFromSection(Section section, int i) {
        getAdapterForSection(section).notifyItemRemoved(i);
    }

    public void notifyItemRemovedFromSection(String str, int i) {
        getAdapterForSection(str).notifyItemRemoved(i);
    }

    public void notifyNotLoadedStateChanged(Section section, State state) {
        getAdapterForSection(section).notifyNotLoadedStateChanged(state);
    }

    public void notifyNotLoadedStateChanged(String str, State state) {
        getAdapterForSection(str).notifyNotLoadedStateChanged(state);
    }

    public void notifySectionChangedToInvisible(Section section, int i) {
        getAdapterForSection(section).notifySectionChangedToInvisible(i);
    }

    public void notifySectionChangedToInvisible(String str, int i) {
        getAdapterForSection(str).notifySectionChangedToInvisible(i);
    }

    public void notifySectionChangedToVisible(Section section) {
        getAdapterForSection(section).notifySectionChangedToVisible();
    }

    public void notifySectionChangedToVisible(String str) {
        getAdapterForSection(str).notifySectionChangedToVisible();
    }

    public void notifyStateChangedFromLoaded(Section section, int i) {
        getAdapterForSection(section).notifyStateChangedFromLoaded(i);
    }

    public void notifyStateChangedFromLoaded(String str, int i) {
        getAdapterForSection(str).notifyStateChangedFromLoaded(i);
    }

    public void notifyStateChangedToLoaded(Section section, State state) {
        getAdapterForSection(section).notifyStateChangedToLoaded(state);
    }

    public void notifyStateChangedToLoaded(String str, State state) {
        getAdapterForSection(str).notifyStateChangedToLoaded(state);
    }
}
