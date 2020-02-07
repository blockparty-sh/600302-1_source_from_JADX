package com.bitcoin.mwallet.app.components.warningdialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u001d"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/warningdialog/WarningView;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "title", "", "descripton", "actionUrl", "", "(IILjava/lang/String;)V", "getActionUrl", "()Ljava/lang/String;", "getDescripton", "()I", "getTitle", "customizeText", "Landroid/text/SpannableStringBuilder;", "addressString", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "", "view", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WarningView.kt */
public final class WarningView extends AppCompatDialogFragment {
    private HashMap _$_findViewCache;
    @Nullable
    private final String actionUrl;
    private final int descripton;
    private final int title;

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
    public final String getActionUrl() {
        return this.actionUrl;
    }

    public final int getDescripton() {
        return this.descripton;
    }

    public final int getTitle() {
        return this.title;
    }

    public WarningView(int i, int i2, @Nullable String str) {
        this.title = i;
        this.descripton = i2;
        this.actionUrl = str;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(1);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            Window window = dialog2.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(0));
            }
        }
        setStyle(2, 16973829);
        super.onViewCreated(view, bundle);
    }

    @NotNull
    public final SpannableStringBuilder customizeText(@NotNull String str) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str2, "addressString");
        CharSequence charSequence = str2;
        int indexOf = StringsKt.indexOf(charSequence, "[b]", 0, true);
        int indexOf2 = StringsKt.indexOf(charSequence, "[/b]", 0, true);
        if (indexOf < 0 && indexOf2 < 0) {
            return new SpannableStringBuilder(charSequence);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(StringsKt.replace$default(StringsKt.replace$default(str, "[b]", "", false, 4, (Object) null), "[/b]", "", false, 4, (Object) null));
        spannableStringBuilder.setSpan(new StyleSpan(1), indexOf, indexOf2 - 3, 18);
        return spannableStringBuilder;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x003a  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull android.view.LayoutInflater r4, @org.jetbrains.annotations.Nullable android.view.ViewGroup r5, @org.jetbrains.annotations.Nullable android.os.Bundle r6) {
        /*
            r3 = this;
            java.lang.String r6 = "inflater"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r6)
            r6 = 2131558537(0x7f0d0089, float:1.8742393E38)
            android.view.View r4 = r4.inflate(r6, r5)
            r5 = 2131362678(0x7f0a0376, float:1.8345143E38)
            android.view.View r5 = r4.findViewById(r5)
            java.lang.String r6 = "layoutView.findViewById<TextView>(R.id.titleText)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            android.widget.TextView r5 = (android.widget.TextView) r5
            android.content.Context r6 = r3.getContext()
            r0 = 0
            if (r6 == 0) goto L_0x002e
            android.content.res.Resources r6 = r6.getResources()
            if (r6 == 0) goto L_0x002e
            int r1 = r3.title
            java.lang.String r6 = r6.getString(r1)
            goto L_0x002f
        L_0x002e:
            r6 = r0
        L_0x002f:
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
            android.content.Context r5 = r3.getContext()
            if (r5 == 0) goto L_0x0047
            android.content.res.Resources r5 = r5.getResources()
            if (r5 == 0) goto L_0x0047
            int r6 = r3.descripton
            java.lang.String r5 = r5.getString(r6)
            goto L_0x0048
        L_0x0047:
            r5 = r0
        L_0x0048:
            if (r5 == 0) goto L_0x0078
            r5 = 2131362190(0x7f0a018e, float:1.8344154E38)
            android.view.View r5 = r4.findViewById(r5)
            java.lang.String r6 = "layoutView.findViewById<TextView>(R.id.infoText)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            android.widget.TextView r5 = (android.widget.TextView) r5
            android.content.Context r6 = r3.getContext()
            if (r6 == 0) goto L_0x006a
            android.content.res.Resources r6 = r6.getResources()
            if (r6 == 0) goto L_0x006a
            int r0 = r3.descripton
            java.lang.String r0 = r6.getString(r0)
        L_0x006a:
            if (r0 != 0) goto L_0x006f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x006f:
            android.text.SpannableStringBuilder r6 = r3.customizeText(r0)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
        L_0x0078:
            r5 = 2131361955(0x7f0a00a3, float:1.8343677E38)
            android.view.View r6 = r4.findViewById(r5)
            android.widget.TextView r6 = (android.widget.TextView) r6
            if (r6 == 0) goto L_0x008d
            com.bitcoin.mwallet.app.components.warningdialog.WarningView$onCreateView$1 r0 = new com.bitcoin.mwallet.app.components.warningdialog.WarningView$onCreateView$1
            r0.<init>(r3)
            android.view.View$OnClickListener r0 = (android.view.View.OnClickListener) r0
            r6.setOnClickListener(r0)
        L_0x008d:
            java.lang.String r6 = r3.actionUrl
            r0 = 0
            r1 = 2131361840(0x7f0a0030, float:1.8343444E38)
            if (r6 == 0) goto L_0x00c9
            android.view.View r6 = r4.findViewById(r1)
            android.widget.TextView r6 = (android.widget.TextView) r6
            if (r6 == 0) goto L_0x00a7
            com.bitcoin.mwallet.app.components.warningdialog.WarningView$onCreateView$2 r2 = new com.bitcoin.mwallet.app.components.warningdialog.WarningView$onCreateView$2
            r2.<init>(r3)
            android.view.View$OnClickListener r2 = (android.view.View.OnClickListener) r2
            r6.setOnClickListener(r2)
        L_0x00a7:
            android.view.View r6 = r4.findViewById(r1)
            android.widget.TextView r6 = (android.widget.TextView) r6
            if (r6 == 0) goto L_0x00b2
            r6.setVisibility(r0)
        L_0x00b2:
            android.view.View r5 = r4.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x00ec
            android.content.res.Resources r6 = r3.getResources()
            r1 = 2131099809(0x7f0600a1, float:1.7811982E38)
            int r6 = r6.getColor(r1)
            r5.setTextColor(r6)
            goto L_0x00ec
        L_0x00c9:
            android.view.View r6 = r4.findViewById(r1)
            android.widget.TextView r6 = (android.widget.TextView) r6
            if (r6 == 0) goto L_0x00d6
            r1 = 8
            r6.setVisibility(r1)
        L_0x00d6:
            android.view.View r5 = r4.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x00ec
            android.content.res.Resources r6 = r3.getResources()
            r1 = 2131099693(0x7f06002d, float:1.7811746E38)
            int r6 = r6.getColor(r1)
            r5.setTextColor(r6)
        L_0x00ec:
            r4.setBackgroundColor(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.warningdialog.WarningView.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(null);
            }
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            Window window2 = dialog2.getWindow();
            if (window2 != null) {
                window2.setBackgroundDrawableResource(17170445);
            }
        }
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }
}
