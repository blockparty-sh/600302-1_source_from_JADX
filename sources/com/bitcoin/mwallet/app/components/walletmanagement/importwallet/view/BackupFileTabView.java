package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/view/BackupFileTabView;", "Landroidx/fragment/app/Fragment;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;)V", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: BackupFileTabView.kt */
public final class BackupFileTabView extends Fragment {
    private HashMap _$_findViewCache;
    @Nullable
    private final ImportWalletPresenter presenter;

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

    public BackupFileTabView(@Nullable ImportWalletPresenter importWalletPresenter) {
        this.presenter = importWalletPresenter;
    }

    @Nullable
    public final ImportWalletPresenter getPresenter() {
        return this.presenter;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = layoutInflater.inflate(C1018R.layout.fragment_backup_file_tab_view, viewGroup, false);
        ImportWalletPresenter importWalletPresenter = this.presenter;
        if (importWalletPresenter != null) {
            LiveData backupCode = importWalletPresenter.getBackupCode();
            if (backupCode != null) {
                backupCode.observe(this, new BackupFileTabView$onCreateView$1(objectRef));
            }
        }
        View findViewById = ((View) objectRef.element).findViewById(C1018R.C1021id.passwordEditText);
        String str = "layoutView.findViewById<…t>(R.id.passwordEditText)";
        Intrinsics.checkExpressionValueIsNotNull(findViewById, str);
        ((TextView) findViewById).addTextChangedListener(new BackupFileTabView$onCreateView$$inlined$addTextChangedListener$1(this));
        View findViewById2 = ((View) objectRef.element).findViewById(C1018R.C1021id.backupCodeEditText);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "layoutView.findViewById<…(R.id.backupCodeEditText)");
        ((TextView) findViewById2).addTextChangedListener(new BackupFileTabView$onCreateView$$inlined$addTextChangedListener$2(this));
        View findViewById3 = ((View) objectRef.element).findViewById(C1018R.C1021id.passwordEditText);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, str);
        ((EditText) findViewById3).setTransformationMethod(PasswordTransformationMethod.getInstance());
        ((Button) ((View) objectRef.element).findViewById(C1018R.C1021id.importWalletByFileButton)).setOnClickListener(new BackupFileTabView$onCreateView$4(this));
        return (View) objectRef.element;
    }
}
