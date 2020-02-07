package org.spongycastle.math.field;

import java.math.BigInteger;

class PrimeField implements FiniteField {
    protected final BigInteger characteristic;

    public int getDimension() {
        return 1;
    }

    PrimeField(BigInteger bigInteger) {
        this.characteristic = bigInteger;
    }

    public BigInteger getCharacteristic() {
        return this.characteristic;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrimeField)) {
            return false;
        }
        return this.characteristic.equals(((PrimeField) obj).characteristic);
    }

    public int hashCode() {
        return this.characteristic.hashCode();
    }
}
