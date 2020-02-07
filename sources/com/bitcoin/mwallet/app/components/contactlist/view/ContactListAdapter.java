package com.bitcoin.mwallet.app.components.contactlist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.contact.Contact;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 /2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0003/01B\u0005¢\u0006\u0002\u0010\u0005J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0012H\u0016J\u0010\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0014J\b\u0010#\u001a\u00020\"H\u0016J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\"H\u0016J\u000e\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020\rJ\u0018\u0010-\u001a\u00020%2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007H\u0016R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u00062"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter$ViewHolder;", "Landroid/widget/Filterable;", "()V", "allList", "", "getAllList", "()Ljava/util/List;", "setAllList", "(Ljava/util/List;)V", "clickedListener", "Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactClickedListener;", "filteredList", "getFilteredList", "setFilteredList", "myFilter", "Landroid/widget/Filter;", "getMyFilter", "()Landroid/widget/Filter;", "setMyFilter", "(Landroid/widget/Filter;)V", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getWalletType", "()Lcom/bitcoin/mwallet/core/entity/WalletType;", "setWalletType", "(Lcom/bitcoin/mwallet/core/entity/WalletType;)V", "getCurrentList", "", "getFilter", "getItem", "position", "", "getItemCount", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnClickListener", "newListener", "submitList", "list", "Companion", "ValueFilter", "ViewHolder", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactListAdapter.kt */
public final class ContactListAdapter extends ListAdapter<Contact, ViewHolder> implements Filterable {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<Contact> DIFF_CALLBACK = new ContactListAdapter$Companion$DIFF_CALLBACK$1();
    @NotNull
    private List<Contact> allList = CollectionsKt.emptyList();
    /* access modifiers changed from: private */
    public ContactClickedListener clickedListener;
    @NotNull
    private List<Contact> filteredList = CollectionsKt.emptyList();
    @Nullable
    private Filter myFilter;
    @NotNull
    private WalletType walletType = WalletType.BCH;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ContactListAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<Contact> getDIFF_CALLBACK() {
            return ContactListAdapter.DIFF_CALLBACK;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0014¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter$ValueFilter;", "Landroid/widget/Filter;", "(Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter;)V", "performFiltering", "Landroid/widget/Filter$FilterResults;", "constraint", "", "publishResults", "", "results", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ContactListAdapter.kt */
    private final class ValueFilter extends Filter {
        public ValueFilter() {
        }

        /* access modifiers changed from: protected */
        @NotNull
        public FilterResults performFiltering(@Nullable CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            ArrayList arrayList = new ArrayList();
            int size = ContactListAdapter.this.getAllList().size();
            int i = 0;
            while (true) {
                String str = "null cannot be cast to non-null type java.lang.String";
                String str2 = "(this as java.lang.String).toUpperCase()";
                if (i < size) {
                    String walletType = ((Contact) ContactListAdapter.this.getAllList().get(i)).getWalletType();
                    if (walletType != null) {
                        String upperCase = walletType.toUpperCase();
                        Intrinsics.checkExpressionValueIsNotNull(upperCase, str2);
                        CharSequence charSequence2 = upperCase;
                        String name = ContactListAdapter.this.getWalletType().name();
                        if (name != null) {
                            String upperCase2 = name.toUpperCase();
                            Intrinsics.checkExpressionValueIsNotNull(upperCase2, str2);
                            if (StringsKt.contains$default(charSequence2, (CharSequence) upperCase2, false, 2, (Object) null)) {
                                arrayList.add(ContactListAdapter.this.getAllList().get(i));
                            }
                            i++;
                        } else {
                            throw new TypeCastException(str);
                        }
                    } else {
                        throw new TypeCastException(str);
                    }
                } else {
                    if (charSequence != null) {
                        if (charSequence.length() > 0) {
                            ArrayList arrayList2 = new ArrayList();
                            int size2 = arrayList.size();
                            int i2 = 0;
                            while (i2 < size2) {
                                String name2 = ((Contact) arrayList.get(i2)).getName();
                                if (name2 != null) {
                                    String upperCase3 = name2.toUpperCase();
                                    Intrinsics.checkExpressionValueIsNotNull(upperCase3, str2);
                                    CharSequence charSequence3 = upperCase3;
                                    String obj = charSequence.toString();
                                    if (obj != null) {
                                        String upperCase4 = obj.toUpperCase();
                                        Intrinsics.checkExpressionValueIsNotNull(upperCase4, str2);
                                        if (StringsKt.contains$default(charSequence3, (CharSequence) upperCase4, false, 2, (Object) null)) {
                                            arrayList2.add(arrayList.get(i2));
                                        }
                                        i2++;
                                    } else {
                                        throw new TypeCastException(str);
                                    }
                                } else {
                                    throw new TypeCastException(str);
                                }
                            }
                            filterResults.count = arrayList2.size();
                            filterResults.values = CollectionsKt.toList(arrayList2);
                            return filterResults;
                        }
                    }
                    filterResults.count = arrayList.size();
                    filterResults.values = CollectionsKt.toList(arrayList);
                    return filterResults;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void publishResults(@NotNull CharSequence charSequence, @NotNull FilterResults filterResults) {
            Intrinsics.checkParameterIsNotNull(charSequence, "constraint");
            Intrinsics.checkParameterIsNotNull(filterResults, "results");
            ContactListAdapter contactListAdapter = ContactListAdapter.this;
            Object obj = filterResults.values;
            if (obj != null) {
                contactListAdapter.setFilteredList((List) obj);
                ContactListAdapter.this.notifyDataSetChanged();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.bitcoin.mwallet.core.models.contact.Contact>");
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/view/ContactListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemsView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "contact", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ContactListAdapter.kt */
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        public ViewHolder(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemsView");
            super(view);
        }

        public final void bind(@NotNull Contact contact) {
            Intrinsics.checkParameterIsNotNull(contact, "contact");
            View view = this.itemView;
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.contactName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "contactName");
            textView.setText(contact.getName());
            TextView textView2 = (TextView) view.findViewById(C1018R.C1021id.contactAddress);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "contactAddress");
            textView2.setText(contact.getAddress());
        }
    }

    public ContactListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NotNull
    public final List<Contact> getAllList() {
        return this.allList;
    }

    public final void setAllList(@NotNull List<Contact> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.allList = list;
    }

    @NotNull
    public final List<Contact> getFilteredList() {
        return this.filteredList;
    }

    public final void setFilteredList(@NotNull List<Contact> list) {
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

    @NotNull
    public final WalletType getWalletType() {
        return this.walletType;
    }

    public final void setWalletType(@NotNull WalletType walletType2) {
        Intrinsics.checkParameterIsNotNull(walletType2, "<set-?>");
        this.walletType = walletType2;
    }

    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        Contact contact = (Contact) this.filteredList.get(i);
        viewHolder.bind(contact);
        viewHolder.itemView.setOnClickListener(new ContactListAdapter$onBindViewHolder$1(this, contact));
    }

    public int getItemCount() {
        return this.filteredList.size();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Contact getItem(int i) {
        return (Contact) this.filteredList.get(i);
    }

    @NotNull
    public List<Contact> getCurrentList() {
        return CollectionsKt.toMutableList((Collection) this.filteredList);
    }

    public void submitList(@Nullable List<Contact> list) {
        if (list != null) {
            this.allList = list;
            Iterable iterable = this.allList;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (Intrinsics.areEqual((Object) ((Contact) next).getCoin().name(), (Object) this.walletType.name())) {
                    arrayList.add(next);
                }
            }
            this.filteredList = (List) arrayList;
        }
        super.submitList(list);
    }

    public final void setOnClickListener(@NotNull ContactClickedListener contactClickedListener) {
        Intrinsics.checkParameterIsNotNull(contactClickedListener, "newListener");
        this.clickedListener = contactClickedListener;
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.component_contact_row_item, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…_row_item, parent, false)");
        inflate.setOnClickListener(new ContactListAdapter$onCreateViewHolder$1(this));
        return new ViewHolder(inflate);
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
