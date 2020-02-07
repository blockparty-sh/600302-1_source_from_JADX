package org.spongycastle.math.p025ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.ECMultiplier */
public interface ECMultiplier {
    ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger);
}
