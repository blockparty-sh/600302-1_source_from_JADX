package com.bitcoin.mwallet.app.components.customheader;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CustomHeaderView.kt */
final /* synthetic */ class CustomHeaderView$onActivityCreated$2 extends MutablePropertyReference0 {
    CustomHeaderView$onActivityCreated$2(CustomHeaderView customHeaderView) {
        super(customHeaderView);
    }

    public String getName() {
        return "headerText";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(CustomHeaderView.class);
    }

    public String getSignature() {
        return "getHeaderText()Ljava/lang/String;";
    }

    @Nullable
    public Object get() {
        return ((CustomHeaderView) this.receiver).getHeaderText();
    }

    public void set(@Nullable Object obj) {
        ((CustomHeaderView) this.receiver).setHeaderText((String) obj);
    }
}
