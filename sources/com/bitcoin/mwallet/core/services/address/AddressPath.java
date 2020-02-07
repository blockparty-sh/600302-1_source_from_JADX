package com.bitcoin.mwallet.core.services.address;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/address/AddressPath;", "", "x", "", "y", "(II)V", "getX", "()I", "getY", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressPath.kt */
public final class AddressPath {

    /* renamed from: x */
    private final int f431x;

    /* renamed from: y */
    private final int f432y;

    @NotNull
    public static /* synthetic */ AddressPath copy$default(AddressPath addressPath, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = addressPath.f431x;
        }
        if ((i3 & 2) != 0) {
            i2 = addressPath.f432y;
        }
        return addressPath.copy(i, i2);
    }

    public final int component1() {
        return this.f431x;
    }

    public final int component2() {
        return this.f432y;
    }

    @NotNull
    public final AddressPath copy(int i, int i2) {
        return new AddressPath(i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof AddressPath) {
                AddressPath addressPath = (AddressPath) obj;
                if (this.f431x == addressPath.f431x) {
                    if (this.f432y == addressPath.f432y) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f431x * 31) + this.f432y;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AddressPath(x=");
        sb.append(this.f431x);
        sb.append(", y=");
        sb.append(this.f432y);
        sb.append(")");
        return sb.toString();
    }

    public AddressPath(int i, int i2) {
        this.f431x = i;
        this.f432y = i2;
    }

    public final int getX() {
        return this.f431x;
    }

    public final int getY() {
        return this.f432y;
    }
}
