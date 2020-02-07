package com.bitcoin.mwallet.app.components.contactcreate.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.contactcreate.builder.AddContactBuilder;
import com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter;
import com.bitcoin.mwallet.core.views.FullScreenRoundedBottomSheetDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\nH\u0002J\u001a\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactcreate/view/AddContactView;", "Lcom/bitcoin/mwallet/core/views/FullScreenRoundedBottomSheetDialogFragment;", "()V", "presenter", "Lcom/bitcoin/mwallet/app/components/contactcreate/presenter/AddContactPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/contactcreate/presenter/AddContactPresenter;", "setPresenter", "(Lcom/bitcoin/mwallet/app/components/contactcreate/presenter/AddContactPresenter;)V", "bindButtons", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDone", "onViewCreated", "view", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddContactView.kt */
public final class AddContactView extends FullScreenRoundedBottomSheetDialogFragment {
    private HashMap _$_findViewCache;
    @Nullable
    private AddContactPresenter presenter;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public final AddContactPresenter getPresenter() {
        return this.presenter;
    }

    public final void setPresenter(@Nullable AddContactPresenter addContactPresenter) {
        this.presenter = addContactPresenter;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(AddContactBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntactBuilder::class.java)");
        this.presenter = ((AddContactBuilder) viewModel).getPresenter();
        View inflate = getLayoutInflater().inflate(C1018R.layout.fragment_component_add_contact, null);
        TextView textView = (TextView) inflate.findViewById(C1018R.C1021id.addButton);
        if (textView != null) {
            textView.setOnClickListener(new AddContactView$onCreateView$1(this));
        }
        AddContactPresenter addContactPresenter = this.presenter;
        String str = "";
        if (addContactPresenter != null) {
            addContactPresenter.setName(str);
        }
        AddContactPresenter addContactPresenter2 = this.presenter;
        if (addContactPresenter2 != null) {
            addContactPresenter2.setRawInput(str, null);
        }
        AddContactPresenter addContactPresenter3 = this.presenter;
        if (addContactPresenter3 != null) {
            MutableLiveData availableSave = addContactPresenter3.getAvailableSave();
            if (availableSave != null) {
                availableSave.observe(getViewLifecycleOwner(), new AddContactView$onCreateView$2(this));
            }
        }
        AddContactPresenter addContactPresenter4 = this.presenter;
        if (addContactPresenter4 != null) {
            MutableLiveData daoResult = addContactPresenter4.getDaoResult();
            if (daoResult != null) {
                daoResult.observe(this, new AddContactView$onCreateView$3(this));
            }
        }
        AddContactPresenter addContactPresenter5 = this.presenter;
        if (addContactPresenter5 != null) {
            MutableLiveData pleaseSelectAsset = addContactPresenter5.getPleaseSelectAsset();
            if (pleaseSelectAsset != null) {
                pleaseSelectAsset.observe(getViewLifecycleOwner(), new AddContactView$onCreateView$4(this));
            }
        }
        return inflate;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        EditText editText = (EditText) view.findViewById(C1018R.C1021id.nameEditText);
        Intrinsics.checkExpressionValueIsNotNull(editText, "name");
        editText.addTextChangedListener(new AddContactView$onViewCreated$$inlined$doAfterTextChanged$1(this));
        editText.requestFocus();
        EditText editText2 = (EditText) view.findViewById(C1018R.C1021id.addressEditText);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "address");
        editText2.addTextChangedListener(new AddContactView$onViewCreated$$inlined$doAfterTextChanged$2(this));
        editText2.setOnEditorActionListener(new AddContactView$onViewCreated$3(this));
        ImageView imageView = (ImageView) view.findViewById(C1018R.C1021id.rawInputValidImageView);
        if (imageView != null) {
            imageView.setOnClickListener(new AddContactView$onViewCreated$4(editText2));
        }
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setSoftInputMode(16);
            }
        }
        bindButtons();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onDone() {
        /*
            r4 = this;
            com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter r0 = r4.presenter
            r1 = 0
            if (r0 == 0) goto L_0x0012
            androidx.lifecycle.MutableLiveData r0 = r0.getAddress()
            if (r0 == 0) goto L_0x0012
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0013
        L_0x0012:
            r0 = r1
        L_0x0013:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0022
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r0 = 0
            goto L_0x0023
        L_0x0022:
            r0 = 1
        L_0x0023:
            if (r0 == 0) goto L_0x0026
            return
        L_0x0026:
            com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter r0 = r4.presenter
            if (r0 == 0) goto L_0x0037
            androidx.lifecycle.MutableLiveData r0 = r0.getName()
            if (r0 == 0) goto L_0x0037
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0038
        L_0x0037:
            r0 = r1
        L_0x0038:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0042
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0043
        L_0x0042:
            r2 = 1
        L_0x0043:
            if (r2 == 0) goto L_0x0046
            return
        L_0x0046:
            com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter r0 = r4.presenter
            if (r0 == 0) goto L_0x0057
            androidx.lifecycle.MutableLiveData r0 = r0.getSelectedWalletType()
            if (r0 == 0) goto L_0x0057
            java.lang.Object r0 = r0.getValue()
            r1 = r0
            com.bitcoin.mwallet.core.entity.WalletType r1 = (com.bitcoin.mwallet.core.entity.WalletType) r1
        L_0x0057:
            if (r1 != 0) goto L_0x005a
            return
        L_0x005a:
            com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter r0 = r4.presenter
            if (r0 == 0) goto L_0x0061
            r0.onCreateContact()
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.contactcreate.view.AddContactView.onDone():void");
    }

    public final void bindButtons() {
        View view = getView();
        RadioButton radioButton = null;
        EditText editText = view != null ? (EditText) view.findViewById(C1018R.C1021id.addressEditText) : null;
        View view2 = getView();
        RadioButton radioButton2 = view2 != null ? (RadioButton) view2.findViewById(C1018R.C1021id.bitcoinCashFilterButton) : null;
        View view3 = getView();
        RadioButton radioButton3 = view3 != null ? (RadioButton) view3.findViewById(C1018R.C1021id.bitcoinFilterButton) : null;
        View view4 = getView();
        if (view4 != null) {
            radioButton = (RadioButton) view4.findViewById(C1018R.C1021id.slpFilterButton);
        }
        if (radioButton2 != null) {
            radioButton2.setOnClickListener(new AddContactView$bindButtons$1(this, editText));
        }
        if (radioButton3 != null) {
            radioButton3.setOnClickListener(new AddContactView$bindButtons$2(this, editText));
        }
        if (radioButton != null) {
            radioButton.setOnClickListener(new AddContactView$bindButtons$3(this, editText));
        }
    }
}
