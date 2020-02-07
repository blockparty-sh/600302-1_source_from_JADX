package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.address.AddressAndOriginalText;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "rawInputIsValid", "", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectAddressView.kt */
final class SelectAddressView$bindObservers$3<T> implements Observer<Boolean> {
    final /* synthetic */ SelectAddressView this$0;

    SelectAddressView$bindObservers$3(SelectAddressView selectAddressView) {
        this.this$0 = selectAddressView;
    }

    public final void onChanged(@Nullable Boolean bool) {
        View view = this.this$0.getView();
        String str = null;
        ImageView imageView = view != null ? (ImageView) view.findViewById(C1018R.C1021id.rawInputValidImageView) : null;
        View view2 = this.this$0.getView();
        Button button = view2 != null ? (Button) view2.findViewById(C1018R.C1021id.continueButton) : null;
        if (bool == null) {
            if (button != null) {
                button.setEnabled(false);
            }
            if (imageView != null) {
                imageView.setVisibility(4);
            }
        } else if (Intrinsics.areEqual((Object) bool, (Object) Boolean.valueOf(true))) {
            this.this$0.enableContinue();
            String prevAddress = this.this$0.getPrevAddress();
            AddressAndOriginalText address = ((SelectAddressPresenter) this.this$0.getPresenter()).getBitcoinUriContent().getAddress();
            if (!Intrinsics.areEqual((Object) prevAddress, address != null ? address.getOriginalText() : null)) {
                AddressAndOriginalText address2 = ((SelectAddressPresenter) this.this$0.getPresenter()).getBitcoinUriContent().getAddress();
                if ((address2 != null ? address2.getOriginalText() : null) != null) {
                    C1261Wallet checkAutoSendThreshold = ((SelectAddressPresenter) this.this$0.getPresenter()).checkAutoSendThreshold();
                    if (checkAutoSendThreshold != null) {
                        ((SelectAddressPresenter) this.this$0.getPresenter()).sendNativeCoin(checkAutoSendThreshold);
                    } else {
                        ((SelectAddressPresenter) this.this$0.getPresenter()).toSelectAmount();
                    }
                    this.this$0.viberate();
                    SelectAddressView selectAddressView = this.this$0;
                    AddressAndOriginalText address3 = ((SelectAddressPresenter) selectAddressView.getPresenter()).getBitcoinUriContent().getAddress();
                    if (address3 != null) {
                        str = address3.getOriginalText();
                    }
                    selectAddressView.setPrevAddress(str);
                    return;
                }
            }
            if ((!Intrinsics.areEqual((Object) this.this$0.getPrevUrl(), (Object) ((SelectAddressPresenter) this.this$0.getPresenter()).getBitcoinUriContent().getUrl())) && ((SelectAddressPresenter) this.this$0.getPresenter()).getBitcoinUriContent().getUrl() != null) {
                ((SelectAddressPresenter) this.this$0.getPresenter()).toSelectAmount();
                this.this$0.viberate();
                SelectAddressView selectAddressView2 = this.this$0;
                selectAddressView2.setPrevUrl(((SelectAddressPresenter) selectAddressView2.getPresenter()).getBitcoinUriContent().getUrl());
            }
        } else {
            this.this$0.disableContinue();
        }
    }
}
