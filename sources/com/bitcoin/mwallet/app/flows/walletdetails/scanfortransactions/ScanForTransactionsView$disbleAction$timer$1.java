package com.bitcoin.mwallet.app.flows.walletdetails.scanfortransactions;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import com.bitcoin.mwallet.C1018R;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsView$disbleAction$timer$1", "Landroid/os/CountDownTimer;", "onFinish", "", "onTick", "millisUntilFinished", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScanForTransactionsView.kt */
public final class ScanForTransactionsView$disbleAction$timer$1 extends CountDownTimer {
    final /* synthetic */ long $interval;
    final /* synthetic */ ScanForTransactionsView this$0;

    ScanForTransactionsView$disbleAction$timer$1(ScanForTransactionsView scanForTransactionsView, long j, long j2, long j3) {
        this.this$0 = scanForTransactionsView;
        this.$interval = j;
        super(j2, j3);
    }

    public void onTick(long j) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)))};
        String format = String.format("%02d:%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.buttonTextView);
            if (textView != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.this$0.getResources().getString(C1018R.string.settings_scan_for_transactions_scanning));
                sb.append(" (");
                sb.append(format);
                sb.append(')');
                textView.setText(sb.toString());
            }
        }
    }

    public void onFinish() {
        this.this$0.enableAction();
    }
}
