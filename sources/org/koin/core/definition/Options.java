package org.koin.core.definition;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\b¨\u0006\u0014"}, mo37405d2 = {"Lorg/koin/core/definition/Options;", "", "isCreatedAtStart", "", "override", "(ZZ)V", "()Z", "setCreatedAtStart", "(Z)V", "getOverride", "setOverride", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Options.kt */
public final class Options {
    private boolean isCreatedAtStart;
    private boolean override;

    public Options() {
        this(false, false, 3, null);
    }

    @NotNull
    public static /* synthetic */ Options copy$default(Options options, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = options.isCreatedAtStart;
        }
        if ((i & 2) != 0) {
            z2 = options.override;
        }
        return options.copy(z, z2);
    }

    public final boolean component1() {
        return this.isCreatedAtStart;
    }

    public final boolean component2() {
        return this.override;
    }

    @NotNull
    public final Options copy(boolean z, boolean z2) {
        return new Options(z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Options) {
                Options options = (Options) obj;
                if (this.isCreatedAtStart == options.isCreatedAtStart) {
                    if (this.override == options.override) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.isCreatedAtStart;
        int i = 1;
        if (z) {
            z = true;
        }
        int i2 = (z ? 1 : 0) * true;
        boolean z2 = this.override;
        if (!z2) {
            i = z2;
        }
        return i2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Options(isCreatedAtStart=");
        sb.append(this.isCreatedAtStart);
        sb.append(", override=");
        sb.append(this.override);
        sb.append(")");
        return sb.toString();
    }

    public Options(boolean z, boolean z2) {
        this.isCreatedAtStart = z;
        this.override = z2;
    }

    public /* synthetic */ Options(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        this(z, z2);
    }

    public final boolean getOverride() {
        return this.override;
    }

    public final boolean isCreatedAtStart() {
        return this.isCreatedAtStart;
    }

    public final void setCreatedAtStart(boolean z) {
        this.isCreatedAtStart = z;
    }

    public final void setOverride(boolean z) {
        this.override = z;
    }
}
