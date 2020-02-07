package org.spongycastle.math.p025ec;

import org.spongycastle.math.p025ec.ECPoint.F2m;

/* renamed from: org.spongycastle.math.ec.WTauNafPreCompInfo */
public class WTauNafPreCompInfo implements PreCompInfo {
    protected F2m[] preComp = null;

    public F2m[] getPreComp() {
        return this.preComp;
    }

    public void setPreComp(F2m[] f2mArr) {
        this.preComp = f2mArr;
    }
}
