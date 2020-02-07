package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ImageView;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 +2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0002+,B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0016J\u0010\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0014J\b\u0010!\u001a\u00020 H\u0016J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020 H\u0016J\u0018\u0010)\u001a\u00020#2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tH\u0016R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0007R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006-"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Ljava/util/Currency;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyViewHolder;", "Landroid/widget/Filterable;", "onCurrencySelectedListener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;)V", "allList", "", "getAllList", "()Ljava/util/List;", "setAllList", "(Ljava/util/List;)V", "filteredList", "getFilteredList", "setFilteredList", "listener", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "setListener", "myFilter", "Landroid/widget/Filter;", "getMyFilter", "()Landroid/widget/Filter;", "setMyFilter", "(Landroid/widget/Filter;)V", "getCurrentList", "", "getFilter", "getItem", "position", "", "getItemCount", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "list", "Companion", "ValueFilter", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectCurrencyAdapter.kt */
public final class SelectCurrencyAdapter extends ListAdapter<Currency, SelectCurrencyViewHolder> implements Filterable {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<Currency> DIFF_CALLBACK = new SelectCurrencyAdapter$Companion$DIFF_CALLBACK$1();
    @NotNull
    private List<Currency> allList = CollectionsKt.emptyList();
    @NotNull
    private List<Currency> filteredList = CollectionsKt.emptyList();
    @NotNull
    private OnCurrencySelectedListener listener;
    @Nullable
    private Filter myFilter;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Ljava/util/Currency;", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SelectCurrencyAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<Currency> getDIFF_CALLBACK() {
            return SelectCurrencyAdapter.DIFF_CALLBACK;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0014¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyAdapter$ValueFilter;", "Landroid/widget/Filter;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyAdapter;)V", "performFiltering", "Landroid/widget/Filter$FilterResults;", "constraint", "", "publishResults", "", "results", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SelectCurrencyAdapter.kt */
    private final class ValueFilter extends Filter {
        public ValueFilter() {
        }

        /* access modifiers changed from: protected */
        @NotNull
        public FilterResults performFiltering(@Nullable CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence != null) {
                if (charSequence.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    int size = SelectCurrencyAdapter.this.getAllList().size();
                    int i = 0;
                    while (i < size) {
                        String currencyCode = ((Currency) SelectCurrencyAdapter.this.getAllList().get(i)).getCurrencyCode();
                        Intrinsics.checkExpressionValueIsNotNull(currencyCode, "allList[i].currencyCode");
                        String str = "null cannot be cast to non-null type java.lang.String";
                        if (currencyCode != null) {
                            String upperCase = currencyCode.toUpperCase();
                            String str2 = "(this as java.lang.String).toUpperCase()";
                            Intrinsics.checkExpressionValueIsNotNull(upperCase, str2);
                            CharSequence charSequence2 = upperCase;
                            String obj = charSequence.toString();
                            if (obj != null) {
                                String upperCase2 = obj.toUpperCase();
                                Intrinsics.checkExpressionValueIsNotNull(upperCase2, str2);
                                if (!StringsKt.contains$default(charSequence2, (CharSequence) upperCase2, false, 2, (Object) null)) {
                                    String displayName = ((Currency) SelectCurrencyAdapter.this.getAllList().get(i)).getDisplayName();
                                    Intrinsics.checkExpressionValueIsNotNull(displayName, "allList[i].displayName");
                                    if (displayName != null) {
                                        String upperCase3 = displayName.toUpperCase();
                                        Intrinsics.checkExpressionValueIsNotNull(upperCase3, str2);
                                        CharSequence charSequence3 = upperCase3;
                                        String obj2 = charSequence.toString();
                                        if (obj2 != null) {
                                            String upperCase4 = obj2.toUpperCase();
                                            Intrinsics.checkExpressionValueIsNotNull(upperCase4, str2);
                                            if (!StringsKt.contains$default(charSequence3, (CharSequence) upperCase4, false, 2, (Object) null)) {
                                                String symbol = ((Currency) SelectCurrencyAdapter.this.getAllList().get(i)).getSymbol();
                                                Intrinsics.checkExpressionValueIsNotNull(symbol, "allList[i].symbol");
                                                if (symbol != null) {
                                                    String upperCase5 = symbol.toUpperCase();
                                                    Intrinsics.checkExpressionValueIsNotNull(upperCase5, str2);
                                                    CharSequence charSequence4 = upperCase5;
                                                    String obj3 = charSequence.toString();
                                                    if (obj3 != null) {
                                                        String upperCase6 = obj3.toUpperCase();
                                                        Intrinsics.checkExpressionValueIsNotNull(upperCase6, str2);
                                                        if (!StringsKt.contains$default(charSequence4, (CharSequence) upperCase6, false, 2, (Object) null)) {
                                                            i++;
                                                        }
                                                    } else {
                                                        throw new TypeCastException(str);
                                                    }
                                                } else {
                                                    throw new TypeCastException(str);
                                                }
                                            }
                                        } else {
                                            throw new TypeCastException(str);
                                        }
                                    } else {
                                        throw new TypeCastException(str);
                                    }
                                }
                                arrayList.add(SelectCurrencyAdapter.this.getAllList().get(i));
                                i++;
                            } else {
                                throw new TypeCastException(str);
                            }
                        } else {
                            throw new TypeCastException(str);
                        }
                    }
                    filterResults.count = arrayList.size();
                    filterResults.values = CollectionsKt.toList(arrayList);
                    return filterResults;
                }
            }
            filterResults.count = SelectCurrencyAdapter.this.getAllList().size();
            filterResults.values = CollectionsKt.toList(SelectCurrencyAdapter.this.getAllList());
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(@NotNull CharSequence charSequence, @NotNull FilterResults filterResults) {
            Intrinsics.checkParameterIsNotNull(charSequence, "constraint");
            Intrinsics.checkParameterIsNotNull(filterResults, "results");
            SelectCurrencyAdapter selectCurrencyAdapter = SelectCurrencyAdapter.this;
            Object obj = filterResults.values;
            if (obj != null) {
                selectCurrencyAdapter.setFilteredList((List) obj);
                SelectCurrencyAdapter.this.notifyDataSetChanged();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<java.util.Currency>");
        }
    }

    public SelectCurrencyAdapter(@NotNull OnCurrencySelectedListener onCurrencySelectedListener) {
        Intrinsics.checkParameterIsNotNull(onCurrencySelectedListener, "onCurrencySelectedListener");
        super(DIFF_CALLBACK);
        this.listener = onCurrencySelectedListener;
    }

    @NotNull
    public final OnCurrencySelectedListener getListener() {
        return this.listener;
    }

    public final void setListener(@NotNull OnCurrencySelectedListener onCurrencySelectedListener) {
        Intrinsics.checkParameterIsNotNull(onCurrencySelectedListener, "<set-?>");
        this.listener = onCurrencySelectedListener;
    }

    @NotNull
    public final List<Currency> getAllList() {
        return this.allList;
    }

    public final void setAllList(@NotNull List<Currency> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.allList = list;
    }

    @NotNull
    public final List<Currency> getFilteredList() {
        return this.filteredList;
    }

    public final void setFilteredList(@NotNull List<Currency> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.filteredList = list;
    }

    @Nullable
    public final Filter getMyFilter() {
        return this.myFilter;
    }

    public final void setMyFilter(@Nullable Filter filter) {
        this.myFilter = filter;
    }

    public void onBindViewHolder(@NotNull SelectCurrencyViewHolder selectCurrencyViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(selectCurrencyViewHolder, "holder");
        selectCurrencyViewHolder.bind((Currency) this.filteredList.get(i));
    }

    public int getItemCount() {
        return this.filteredList.size();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Currency getItem(int i) {
        return (Currency) this.filteredList.get(i);
    }

    @NotNull
    public List<Currency> getCurrentList() {
        return CollectionsKt.toMutableList((Collection) this.filteredList);
    }

    public void submitList(@Nullable List<Currency> list) {
        if (list != null) {
            this.allList = list;
            this.filteredList = this.allList;
        }
        super.submitList(list);
    }

    @NotNull
    public SelectCurrencyViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_select_currency_cell, viewGroup, false);
        View findViewById = inflate.findViewById(C1018R.C1021id.currencySelectedImageView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<ImageV…urrencySelectedImageView)");
        ((ImageView) findViewById).setVisibility(8);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        return new SelectCurrencyViewHolder(inflate, this.listener);
    }

    @NotNull
    public Filter getFilter() {
        if (this.myFilter == null) {
            this.myFilter = new ValueFilter();
        }
        Filter filter = this.myFilter;
        if (filter == null) {
            Intrinsics.throwNpe();
        }
        return filter;
    }
}
