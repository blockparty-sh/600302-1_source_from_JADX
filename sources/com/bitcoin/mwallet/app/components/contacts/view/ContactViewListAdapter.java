package com.bitcoin.mwallet.app.components.contacts.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.views.ContactListViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000  2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001f !B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\bH\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bH\u0016J\u0014\u0010\u001d\u001a\u00020\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eR\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\""}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/view/ContactViewListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "clickedListener", "Lcom/bitcoin/mwallet/app/components/contacts/view/ContactViewListAdapter$ContactClickedListener;", "(Lcom/bitcoin/mwallet/app/components/contacts/view/ContactViewListAdapter$ContactClickedListener;)V", "CONTACT_ADD", "", "getCONTACT_ADD", "()I", "CONTACT_DATA", "getCONTACT_DATA", "contactList", "", "getContactList", "()Ljava/util/List;", "setContactList", "(Ljava/util/List;)V", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newContactList", "AddContactViewHolder", "Companion", "ContactClickedListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactViewListAdapter.kt */
public final class ContactViewListAdapter extends ListAdapter<Contact, ViewHolder> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<Contact> DIFF_CALLBACK = new ContactViewListAdapter$Companion$DIFF_CALLBACK$1();
    private final int CONTACT_ADD;
    private final int CONTACT_DATA = 1;
    /* access modifiers changed from: private */
    public final ContactClickedListener clickedListener;
    @NotNull
    private List<Contact> contactList = CollectionsKt.emptyList();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/view/ContactViewListAdapter$AddContactViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "addContactText", "Landroid/widget/TextView;", "getView", "()Landroid/view/View;", "bind", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ContactViewListAdapter.kt */
    private static final class AddContactViewHolder extends ViewHolder {
        private final TextView addContactText;
        @NotNull
        private final View view;

        public AddContactViewHolder(@NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            super(view2);
            this.view = view2;
            View findViewById = this.view.findViewById(C1018R.C1021id.addContactText);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.addContactText)");
            this.addContactText = (TextView) findViewById;
        }

        @NotNull
        public final View getView() {
            return this.view;
        }

        public final void bind() {
            this.addContactText.setText(C1018R.string.addcontacts_title);
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/view/ContactViewListAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ContactViewListAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<Contact> getDIFF_CALLBACK() {
            return ContactViewListAdapter.DIFF_CALLBACK;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/view/ContactViewListAdapter$ContactClickedListener;", "", "onAddContactClicked", "", "onContactClicked", "item", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ContactViewListAdapter.kt */
    public interface ContactClickedListener {
        void onAddContactClicked();

        void onContactClicked(@NotNull Contact contact);
    }

    public ContactViewListAdapter(@NotNull ContactClickedListener contactClickedListener) {
        Intrinsics.checkParameterIsNotNull(contactClickedListener, "clickedListener");
        super(DIFF_CALLBACK);
        this.clickedListener = contactClickedListener;
    }

    public final int getCONTACT_ADD() {
        return this.CONTACT_ADD;
    }

    public final int getCONTACT_DATA() {
        return this.CONTACT_DATA;
    }

    @NotNull
    public final List<Contact> getContactList() {
        return this.contactList;
    }

    public final void setContactList(@NotNull List<Contact> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.contactList = list;
    }

    public final void updateData(@NotNull List<Contact> list) {
        Intrinsics.checkParameterIsNotNull(list, "newContactList");
        this.contactList = list;
        notifyDataSetChanged();
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        if (i == this.CONTACT_ADD) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.component_add_contact_item, viewGroup, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…tact_item, parent, false)");
            inflate.setOnClickListener(new ContactViewListAdapter$onCreateViewHolder$1(this));
            return new AddContactViewHolder(inflate);
        }
        View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.component_contact_list_item, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, "LayoutInflater.from(pare…list_item, parent, false)");
        inflate2.setOnClickListener(new ContactViewListAdapter$onCreateViewHolder$2(this));
        return new ContactListViewHolder(inflate2);
    }

    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        if (viewHolder instanceof AddContactViewHolder) {
            ((AddContactViewHolder) viewHolder).bind();
        }
        if (viewHolder instanceof ContactListViewHolder) {
            Contact contact = (Contact) this.contactList.get(i - 1);
            ContactListViewHolder contactListViewHolder = (ContactListViewHolder) viewHolder;
            contactListViewHolder.bind(contact);
            contactListViewHolder.getView().setOnClickListener(new ContactViewListAdapter$onBindViewHolder$1(this, contact));
        }
    }

    public int getItemCount() {
        return this.contactList.size() + 1;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return this.CONTACT_ADD;
        }
        return this.CONTACT_DATA;
    }
}
