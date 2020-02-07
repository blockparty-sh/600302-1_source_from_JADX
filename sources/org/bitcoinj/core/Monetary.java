package org.bitcoinj.core;

import java.io.Serializable;

public interface Monetary extends Serializable {
    long getValue();

    int signum();

    int smallestUnitExponent();
}
