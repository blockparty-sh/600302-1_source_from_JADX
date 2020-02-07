package com.bitcoin.mwallet.app.flows.receive.receive;

import android.hardware.Sensor;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final /* synthetic */ class ReceiveView$onResume$2 extends MutablePropertyReference0 {
    ReceiveView$onResume$2(ReceiveView receiveView) {
        super(receiveView);
    }

    public String getName() {
        return "magnetometer";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ReceiveView.class);
    }

    public String getSignature() {
        return "getMagnetometer()Landroid/hardware/Sensor;";
    }

    @Nullable
    public Object get() {
        return ReceiveView.access$getMagnetometer$p((ReceiveView) this.receiver);
    }

    public void set(@Nullable Object obj) {
        ((ReceiveView) this.receiver).magnetometer = (Sensor) obj;
    }
}
