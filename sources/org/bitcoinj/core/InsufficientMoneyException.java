package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

public class InsufficientMoneyException extends Exception {
    @Nullable
    public final Coin missing;

    protected InsufficientMoneyException() {
        this.missing = null;
    }

    public InsufficientMoneyException(Coin coin) {
        StringBuilder sb = new StringBuilder();
        sb.append("Insufficient money,  missing ");
        sb.append(coin.toFriendlyString());
        this(coin, sb.toString());
    }

    public InsufficientMoneyException(Coin coin, String str) {
        super(str);
        this.missing = (Coin) Preconditions.checkNotNull(coin);
    }
}
