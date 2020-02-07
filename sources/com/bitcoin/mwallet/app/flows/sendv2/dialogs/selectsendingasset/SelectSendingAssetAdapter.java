package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 42\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u000245B-\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020%H\u0016J\b\u0010&\u001a\u00020\u001cH\u0016J\u0010\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0014J\b\u0010*\u001a\u00020)H\u0016J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020)H\u0016J\u0018\u00102\u001a\u00020,2\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\bH\u0016R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0011R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00066"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetViewHolder;", "Landroid/widget/Filterable;", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "verifiedAssets", "", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "context", "Landroid/content/Context;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;Ljava/util/List;Landroid/content/Context;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;)V", "allList", "getAllList", "()Ljava/util/List;", "setAllList", "(Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "filteredList", "getFilteredList", "setFilteredList", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "myFilter", "Landroid/widget/Filter;", "getMyFilter", "()Landroid/widget/Filter;", "setMyFilter", "(Landroid/widget/Filter;)V", "getVerifiedAssets", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getCurrentList", "", "getFilter", "getItem", "position", "", "getItemCount", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "list", "Companion", "ValueFilter", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetAdapter.kt */
public final class SelectSendingAssetAdapter extends ListAdapter<SendableAssetModel, SelectSendingAssetViewHolder> implements Filterable {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<SendableAssetModel> DIFF_CALLBACK = new SelectSendingAssetAdapter$Companion$DIFF_CALLBACK$1();
    @NotNull
    private List<SendableAssetModel> allList = CollectionsKt.emptyList();
    @Nullable
    private final Context context;
    @NotNull
    private List<SendableAssetModel> filteredList = CollectionsKt.emptyList();
    @NotNull
    private final OnSendingAssetSelectedListener listener;
    @Nullable
    private Filter myFilter;
    @NotNull
    private final List<VerifiedToken> verifiedAssets;
    @NotNull
    private final VerifiedTokenInteractor verifiedTokenInteractor;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SelectSendingAssetAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<SendableAssetModel> getDIFF_CALLBACK() {
            return SelectSendingAssetAdapter.DIFF_CALLBACK;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0014¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetAdapter$ValueFilter;", "Landroid/widget/Filter;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetAdapter;)V", "performFiltering", "Landroid/widget/Filter$FilterResults;", "constraint", "", "publishResults", "", "results", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SelectSendingAssetAdapter.kt */
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
                    int size = SelectSendingAssetAdapter.this.getAllList().size();
                    int i = 0;
                    while (i < size) {
                        String name = ((SendableAssetModel) SelectSendingAssetAdapter.this.getAllList().get(i)).getName();
                        String str = "null cannot be cast to non-null type java.lang.String";
                        if (name != null) {
                            String upperCase = name.toUpperCase();
                            String str2 = "(this as java.lang.String).toUpperCase()";
                            Intrinsics.checkExpressionValueIsNotNull(upperCase, str2);
                            CharSequence charSequence2 = upperCase;
                            String obj = charSequence.toString();
                            if (obj != null) {
                                String upperCase2 = obj.toUpperCase();
                                Intrinsics.checkExpressionValueIsNotNull(upperCase2, str2);
                                if (!StringsKt.contains$default(charSequence2, (CharSequence) upperCase2, false, 2, (Object) null)) {
                                    String ticker = ((SendableAssetModel) SelectSendingAssetAdapter.this.getAllList().get(i)).getTicker();
                                    if (ticker != null) {
                                        String upperCase3 = ticker.toUpperCase();
                                        Intrinsics.checkExpressionValueIsNotNull(upperCase3, str2);
                                        CharSequence charSequence3 = upperCase3;
                                        String obj2 = charSequence.toString();
                                        if (obj2 != null) {
                                            String upperCase4 = obj2.toUpperCase();
                                            Intrinsics.checkExpressionValueIsNotNull(upperCase4, str2);
                                            if (!StringsKt.contains$default(charSequence3, (CharSequence) upperCase4, false, 2, (Object) null)) {
                                                i++;
                                            }
                                        } else {
                                            throw new TypeCastException(str);
                                        }
                                    } else {
                                        throw new TypeCastException(str);
                                    }
                                }
                                arrayList.add(SelectSendingAssetAdapter.this.getAllList().get(i));
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
            filterResults.count = SelectSendingAssetAdapter.this.getAllList().size();
            filterResults.values = CollectionsKt.toList(SelectSendingAssetAdapter.this.getAllList());
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(@NotNull CharSequence charSequence, @NotNull FilterResults filterResults) {
            Intrinsics.checkParameterIsNotNull(charSequence, "constraint");
            Intrinsics.checkParameterIsNotNull(filterResults, "results");
            SelectSendingAssetAdapter selectSendingAssetAdapter = SelectSendingAssetAdapter.this;
            Object obj = filterResults.values;
            if (obj != null) {
                selectSendingAssetAdapter.setFilteredList((List) obj);
                SelectSendingAssetAdapter.this.notifyDataSetChanged();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel>");
        }
    }

    @NotNull
    public final OnSendingAssetSelectedListener getListener() {
        return this.listener;
    }

    @NotNull
    public final List<VerifiedToken> getVerifiedAssets() {
        return this.verifiedAssets;
    }

    @Nullable
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        return this.verifiedTokenInteractor;
    }

    public SelectSendingAssetAdapter(@NotNull OnSendingAssetSelectedListener onSendingAssetSelectedListener, @NotNull List<VerifiedToken> list, @Nullable Context context2, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2) {
        Intrinsics.checkParameterIsNotNull(onSendingAssetSelectedListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(list, "verifiedAssets");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        super(DIFF_CALLBACK);
        this.listener = onSendingAssetSelectedListener;
        this.verifiedAssets = list;
        this.context = context2;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
    }

    @NotNull
    public final List<SendableAssetModel> getAllList() {
        return this.allList;
    }

    public final void setAllList(@NotNull List<SendableAssetModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.allList = list;
    }

    @NotNull
    public final List<SendableAssetModel> getFilteredList() {
        return this.filteredList;
    }

    public final void setFilteredList(@NotNull List<SendableAssetModel> list) {
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

    public void onBindViewHolder(@NotNull SelectSendingAssetViewHolder selectSendingAssetViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(selectSendingAssetViewHolder, "holder");
        selectSendingAssetViewHolder.bind((SendableAssetModel) this.filteredList.get(i));
    }

    public int getItemCount() {
        return this.filteredList.size();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SendableAssetModel getItem(int i) {
        return (SendableAssetModel) this.filteredList.get(i);
    }

    @NotNull
    public List<SendableAssetModel> getCurrentList() {
        return CollectionsKt.toMutableList((Collection) this.filteredList);
    }

    public void submitList(@Nullable List<SendableAssetModel> list) {
        if (list != null) {
            this.allList = list;
            this.filteredList = this.allList;
        }
        super.submitList(list);
    }

    @NotNull
    public SelectSendingAssetViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_select_sending_asset_cell, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        SelectSendingAssetViewHolder selectSendingAssetViewHolder = new SelectSendingAssetViewHolder(inflate, this.listener, this.verifiedAssets, this.context, this.verifiedTokenInteractor);
        return selectSendingAssetViewHolder;
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
