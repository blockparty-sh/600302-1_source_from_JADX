package com.bitcoin.mwallet.app.flows.receive.receive;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.ReceiveActivity;
import com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView.WhenMappings;
import com.bitcoin.mwallet.core.entity.WalletType;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "type", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindDataObservers$5<T> implements Observer<WalletType> {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindDataObservers$5(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final void onChanged(WalletType walletType) {
        if (walletType != null) {
            int i = WhenMappings.$EnumSwitchMapping$2[walletType.ordinal()];
            String str = "null cannot be cast to non-null type com.bitcoin.mwallet.ReceiveActivity";
            if (i == 1) {
                View view = this.this$0.getView();
                if (view != null) {
                    RadioButton radioButton = (RadioButton) view.findViewById(C1018R.C1021id.bitcoinCashFilterButton);
                    if (radioButton != null) {
                        radioButton.setChecked(true);
                    }
                }
                View view2 = this.this$0.getView();
                if (view2 != null) {
                    ImageView imageView = (ImageView) view2.findViewById(C1018R.C1021id.qrLogoImageView);
                    if (imageView != null) {
                        FragmentActivity activity = this.this$0.getActivity();
                        if (activity != null) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(((ReceiveActivity) activity).getApplicationContext(), C1018R.C1020drawable.ic_qr_bch));
                        } else {
                            throw new TypeCastException(str);
                        }
                    }
                }
            } else if (i == 2) {
                View view3 = this.this$0.getView();
                if (view3 != null) {
                    RadioButton radioButton2 = (RadioButton) view3.findViewById(C1018R.C1021id.bitcoinFilterButton);
                    if (radioButton2 != null) {
                        radioButton2.setChecked(true);
                    }
                }
                View view4 = this.this$0.getView();
                if (view4 != null) {
                    ImageView imageView2 = (ImageView) view4.findViewById(C1018R.C1021id.qrLogoImageView);
                    if (imageView2 != null) {
                        FragmentActivity activity2 = this.this$0.getActivity();
                        if (activity2 != null) {
                            imageView2.setImageDrawable(ContextCompat.getDrawable(((ReceiveActivity) activity2).getApplicationContext(), C1018R.C1020drawable.ic_qr_btc));
                        } else {
                            throw new TypeCastException(str);
                        }
                    }
                }
            } else if (i == 3) {
                View view5 = this.this$0.getView();
                if (view5 != null) {
                    RadioButton radioButton3 = (RadioButton) view5.findViewById(C1018R.C1021id.slpFilterButton);
                    if (radioButton3 != null) {
                        radioButton3.setChecked(true);
                    }
                }
                View view6 = this.this$0.getView();
                if (view6 != null) {
                    ImageView imageView3 = (ImageView) view6.findViewById(C1018R.C1021id.qrLogoImageView);
                    if (imageView3 != null) {
                        FragmentActivity activity3 = this.this$0.getActivity();
                        if (activity3 != null) {
                            imageView3.setImageDrawable(ContextCompat.getDrawable(((ReceiveActivity) activity3).getApplicationContext(), C1018R.C1020drawable.ic_qr_slp));
                        } else {
                            throw new TypeCastException(str);
                        }
                    }
                }
            }
        }
        if (walletType == WalletType.SLP) {
            View view7 = this.this$0.getView();
            if (view7 != null) {
                TextView textView = (TextView) view7.findViewById(C1018R.C1021id.editAmountButton);
                if (textView != null) {
                    textView.setVisibility(8);
                }
            }
        } else {
            View view8 = this.this$0.getView();
            if (view8 != null) {
                TextView textView2 = (TextView) view8.findViewById(C1018R.C1021id.editAmountButton);
                if (textView2 != null) {
                    textView2.setVisibility(0);
                }
            }
        }
        if (walletType == WalletType.BCH) {
            View view9 = this.this$0.getView();
            if (view9 != null) {
                TextView textView3 = (TextView) view9.findViewById(C1018R.C1021id.legacyAddrTextView);
                if (textView3 != null) {
                    textView3.setVisibility(0);
                    return;
                }
                return;
            }
            return;
        }
        View view10 = this.this$0.getView();
        if (view10 != null) {
            TextView textView4 = (TextView) view10.findViewById(C1018R.C1021id.legacyAddrTextView);
            if (textView4 != null) {
                textView4.setVisibility(4);
            }
        }
    }
}
