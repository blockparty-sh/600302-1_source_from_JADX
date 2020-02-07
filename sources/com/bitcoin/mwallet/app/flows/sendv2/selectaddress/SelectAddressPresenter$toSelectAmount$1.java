package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectAddressPresenter.kt */
final /* synthetic */ class SelectAddressPresenter$toSelectAmount$1 extends MutablePropertyReference0 {
    SelectAddressPresenter$toSelectAmount$1(SelectAddressPresenter selectAddressPresenter) {
        super(selectAddressPresenter);
    }

    public String getName() {
        return "bitcoinUriContent";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SelectAddressPresenter.class);
    }

    public String getSignature() {
        return "getBitcoinUriContent()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;";
    }

    @Nullable
    public Object get() {
        return ((SelectAddressPresenter) this.receiver).getBitcoinUriContent();
    }

    public void set(@Nullable Object obj) {
        ((SelectAddressPresenter) this.receiver).setBitcoinUriContent((BitcoinUriContent) obj);
    }
}
