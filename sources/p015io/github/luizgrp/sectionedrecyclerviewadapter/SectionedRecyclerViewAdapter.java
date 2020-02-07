package p015io.github.luizgrp.sectionedrecyclerviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.apache.commons.collections4.map.ListOrderedMap;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;

/* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter */
public class SectionedRecyclerViewAdapter extends Adapter<ViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 5;
    public static final int VIEW_TYPE_FAILED = 4;
    public static final int VIEW_TYPE_FOOTER = 1;
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM_LOADED = 2;
    public static final int VIEW_TYPE_LOADING = 3;
    private static final int VIEW_TYPE_QTY = 6;
    private final transient Map<Section, SectionAdapter> sectionAdapters = new HashMap();
    private final transient Map<String, Integer> sectionViewTypeNumbers = new LinkedHashMap();
    private final transient ListOrderedMap<String, Section> sections = new ListOrderedMap<>();
    private transient int viewTypeCount;

    /* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter$1 */
    static /* synthetic */ class C26671 {

        /* renamed from: $SwitchMap$io$github$luizgrp$sectionedrecyclerviewadapter$Section$State */
        static final /* synthetic */ int[] f676x814321f1 = new int[State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State[] r0 = p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f676x814321f1 = r0
                int[] r0 = f676x814321f1     // Catch:{ NoSuchFieldError -> 0x0014 }
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.LOADING     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f676x814321f1     // Catch:{ NoSuchFieldError -> 0x001f }
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.LOADED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f676x814321f1     // Catch:{ NoSuchFieldError -> 0x002a }
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.FAILED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f676x814321f1     // Catch:{ NoSuchFieldError -> 0x0035 }
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p015io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.EMPTY     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p015io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter.C26671.<clinit>():void");
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = null;
        for (Entry entry : this.sectionViewTypeNumbers.entrySet()) {
            if (i >= ((Integer) entry.getValue()).intValue() && i < ((Integer) entry.getValue()).intValue() + 6) {
                Section section = (Section) this.sections.get(entry.getKey());
                int intValue = i - ((Integer) entry.getValue()).intValue();
                if (intValue == 0) {
                    viewHolder = getHeaderViewHolder(viewGroup, section);
                } else if (intValue == 1) {
                    viewHolder = getFooterViewHolder(viewGroup, section);
                } else if (intValue == 2) {
                    viewHolder = getItemViewHolder(viewGroup, section);
                } else if (intValue == 3) {
                    viewHolder = getLoadingViewHolder(viewGroup, section);
                } else if (intValue == 4) {
                    viewHolder = getFailedViewHolder(viewGroup, section);
                } else if (intValue == 5) {
                    viewHolder = getEmptyViewHolder(viewGroup, section);
                } else {
                    throw new IllegalArgumentException("Invalid viewType");
                }
            }
        }
        return viewHolder;
    }

    private ViewHolder getItemViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isItemViewWillBeProvided()) {
            view = section.getItemView(viewGroup);
            if (view == null) {
                throw new NullPointerException("Section.getItemView() returned null");
            }
        } else {
            Integer itemResourceId = section.getItemResourceId();
            if (itemResourceId != null) {
                view = inflate(itemResourceId.intValue(), viewGroup);
            } else {
                throw new NullPointerException("Missing 'item' resource id");
            }
        }
        return section.getItemViewHolder(view);
    }

    private ViewHolder getHeaderViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isHeaderViewWillBeProvided()) {
            view = section.getHeaderView(viewGroup);
            if (view == null) {
                throw new NullPointerException("Section.getHeaderView() returned null");
            }
        } else {
            Integer headerResourceId = section.getHeaderResourceId();
            if (headerResourceId != null) {
                view = inflate(headerResourceId.intValue(), viewGroup);
            } else {
                throw new NullPointerException("Missing 'header' resource id");
            }
        }
        return section.getHeaderViewHolder(view);
    }

    private ViewHolder getFooterViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isFooterViewWillBeProvided()) {
            view = section.getFooterView(viewGroup);
            if (view == null) {
                throw new NullPointerException("Section.getFooterView() returned null");
            }
        } else {
            Integer footerResourceId = section.getFooterResourceId();
            if (footerResourceId != null) {
                view = inflate(footerResourceId.intValue(), viewGroup);
            } else {
                throw new NullPointerException("Missing 'footer' resource id");
            }
        }
        return section.getFooterViewHolder(view);
    }

    private ViewHolder getLoadingViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isLoadingViewWillBeProvided()) {
            view = section.getLoadingView(viewGroup);
            if (view == null) {
                throw new NullPointerException("Section.getLoadingView() returned null");
            }
        } else {
            Integer loadingResourceId = section.getLoadingResourceId();
            if (loadingResourceId != null) {
                view = inflate(loadingResourceId.intValue(), viewGroup);
            } else {
                throw new NullPointerException("Missing 'loading' resource id");
            }
        }
        return section.getLoadingViewHolder(view);
    }

    private ViewHolder getFailedViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isFailedViewWillBeProvided()) {
            view = section.getFailedView(viewGroup);
            if (view == null) {
                throw new NullPointerException("Section.getFailedView() returned null");
            }
        } else {
            Integer failedResourceId = section.getFailedResourceId();
            if (failedResourceId != null) {
                view = inflate(failedResourceId.intValue(), viewGroup);
            } else {
                throw new NullPointerException("Missing 'failed' resource id");
            }
        }
        return section.getFailedViewHolder(view);
    }

    private ViewHolder getEmptyViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isEmptyViewWillBeProvided()) {
            view = section.getEmptyView(viewGroup);
            if (view == null) {
                throw new NullPointerException("Section.getEmptyView() returned null");
            }
        } else {
            Integer emptyResourceId = section.getEmptyResourceId();
            if (emptyResourceId != null) {
                view = inflate(emptyResourceId.intValue(), viewGroup);
            } else {
                throw new NullPointerException("Missing 'empty' resource id");
            }
        }
        return section.getEmptyViewHolder(view);
    }

    public String addSection(Section section) {
        String generateSectionTag = generateSectionTag();
        addSection(generateSectionTag, section);
        return generateSectionTag;
    }

    public void addSection(String str, Section section) {
        addSection(this.sections.size(), str, section);
    }

    public void addSection(int i, String str, Section section) {
        this.sections.put(i, str, section);
        addSectionViewTypeNumbers(str);
        this.sectionAdapters.put(section, new SectionAdapter(this, section));
    }

    public String addSection(int i, Section section) {
        String generateSectionTag = generateSectionTag();
        addSection(i, generateSectionTag, section);
        return generateSectionTag;
    }

    private String generateSectionTag() {
        return UUID.randomUUID().toString();
    }

    private void addSectionViewTypeNumbers(String str) {
        this.sectionViewTypeNumbers.put(str, Integer.valueOf(this.viewTypeCount));
        this.viewTypeCount += 6;
    }

    public Section getSection(String str) {
        return (Section) this.sections.get(str);
    }

    public void removeSection(Section section) {
        String str = null;
        for (Entry entry : this.sections.entrySet()) {
            if (entry.getValue() == section) {
                str = (String) entry.getKey();
            }
        }
        if (str != null) {
            removeSection(str);
        }
    }

    public void removeSection(String str) {
        Section section = (Section) this.sections.remove((Object) str);
        this.sectionViewTypeNumbers.remove(str);
        this.sectionAdapters.remove(section);
    }

    public void removeAllSections() {
        this.sections.clear();
        this.sectionViewTypeNumbers.clear();
        this.sectionAdapters.clear();
        this.viewTypeCount = 0;
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        internalOnBindViewHolder(viewHolder, i, null);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, i, list);
        } else {
            internalOnBindViewHolder(viewHolder, i, list);
        }
    }

    private void internalOnBindViewHolder(@NonNull ViewHolder viewHolder, int i, List<Object> list) {
        int i2 = 0;
        for (Entry value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i >= i2) {
                    int i3 = (i2 + sectionItemsTotal) - 1;
                    if (i <= i3) {
                        if (section.hasHeader() && i == i2) {
                            if (list == null) {
                                getSectionForPosition(i).onBindHeaderViewHolder(viewHolder);
                            } else {
                                getSectionForPosition(i).onBindHeaderViewHolder(viewHolder, list);
                            }
                            return;
                        } else if (!section.hasFooter() || i != i3) {
                            onBindContentViewHolder(getSectionForPosition(i), viewHolder, i, list);
                            return;
                        } else {
                            if (list == null) {
                                getSectionForPosition(i).onBindFooterViewHolder(viewHolder);
                            } else {
                                getSectionForPosition(i).onBindFooterViewHolder(viewHolder, list);
                            }
                            return;
                        }
                    }
                }
                i2 += sectionItemsTotal;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    private void onBindContentViewHolder(@NonNull Section section, @NonNull ViewHolder viewHolder, int i, List<Object> list) {
        int i2 = C26671.f676x814321f1[section.getState().ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        throw new IllegalStateException("Invalid state");
                    } else if (list == null) {
                        section.onBindEmptyViewHolder(viewHolder);
                    } else {
                        section.onBindEmptyViewHolder(viewHolder, list);
                    }
                } else if (list == null) {
                    section.onBindFailedViewHolder(viewHolder);
                } else {
                    section.onBindFailedViewHolder(viewHolder, list);
                }
            } else if (list == null) {
                section.onBindItemViewHolder(viewHolder, getPositionInSection(i));
            } else {
                section.onBindItemViewHolder(viewHolder, getPositionInSection(i), list);
            }
        } else if (list == null) {
            section.onBindLoadingViewHolder(viewHolder);
        } else {
            section.onBindLoadingViewHolder(viewHolder, list);
        }
    }

    public int getItemCount() {
        int i = 0;
        for (Entry value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                i += section.getSectionItemsTotal();
            }
        }
        return i;
    }

    public int getItemViewType(int i) {
        int i2 = 0;
        for (Entry entry : this.sections.entrySet()) {
            Section section = (Section) entry.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i >= i2) {
                    int i3 = (i2 + sectionItemsTotal) - 1;
                    if (i <= i3) {
                        int intValue = ((Integer) this.sectionViewTypeNumbers.get(entry.getKey())).intValue();
                        if (section.hasHeader() && i == i2) {
                            return intValue;
                        }
                        if (section.hasFooter() && i == i3) {
                            return intValue + 1;
                        }
                        int i4 = C26671.f676x814321f1[section.getState().ordinal()];
                        if (i4 == 1) {
                            return intValue + 3;
                        }
                        if (i4 == 2) {
                            return intValue + 2;
                        }
                        if (i4 == 3) {
                            return intValue + 4;
                        }
                        if (i4 == 4) {
                            return intValue + 5;
                        }
                        throw new IllegalStateException("Invalid state");
                    }
                }
                i2 += sectionItemsTotal;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionItemViewTypeForAdapterViewType(int i) {
        return i % 6;
    }

    public int getSectionItemViewType(int i) {
        return getSectionItemViewTypeForAdapterViewType(getItemViewType(i));
    }

    public Section getSectionForPosition(int i) {
        int i2 = 0;
        for (Entry value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i >= i2 && i <= (i2 + sectionItemsTotal) - 1) {
                    return section;
                }
                i2 += sectionItemsTotal;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getPositionInSection(int i) {
        int i2 = 0;
        for (Entry value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i >= i2 && i <= (i2 + sectionItemsTotal) - 1) {
                    return (i - i2) - (section.hasHeader() ? 1 : 0);
                }
                i2 += sectionItemsTotal;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    /* access modifiers changed from: 0000 */
    public ListOrderedMap<String, Section> getSections() {
        return this.sections;
    }

    @NonNull
    public Map<String, Section> getCopyOfSectionsMap() {
        return ListOrderedMap.listOrderedMap(this.sections);
    }

    public int getSectionCount() {
        return this.sections.size();
    }

    public Section getSection(int i) {
        return (Section) this.sections.getValue(i);
    }

    public int getSectionIndex(Section section) {
        int i = 0;
        for (Entry value : this.sections.entrySet()) {
            if (value.getValue() == section) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public SectionAdapter getAdapterForSection(String str) {
        return getAdapterForSection(getValidSectionOrThrowException(str));
    }

    public SectionAdapter getAdapterForSection(Section section) {
        SectionAdapter sectionAdapter = (SectionAdapter) this.sectionAdapters.get(section);
        if (sectionAdapter != null) {
            return sectionAdapter;
        }
        throw new IllegalArgumentException("Invalid section");
    }

    @VisibleForTesting
    public View inflate(@LayoutRes int i, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
    }

    @NonNull
    private Section getValidSectionOrThrowException(String str) {
        Section section = getSection(str);
        if (section != null) {
            return section;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid tag: ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }
}
