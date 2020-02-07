package com.bitcoin.mwallet.app.android;

import androidx.annotation.StringRes;
import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0017J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003Jj\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0006HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/android/ActionBarTraits;", "", "isShown", "", "logoIsShown", "titleResId", "", "showEditContactMenu", "showAddContactMenu", "showSubmitContactMenu", "showSettingsMenu", "showWalletSettingsMenu", "showShopMenu", "(ZZLjava/lang/Integer;ZZZZZZ)V", "()Z", "getLogoIsShown", "getShowAddContactMenu", "getShowEditContactMenu", "getShowSettingsMenu", "getShowShopMenu", "getShowSubmitContactMenu", "getShowWalletSettingsMenu", "getTitleResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ZZLjava/lang/Integer;ZZZZZZ)Lcom/bitcoin/mwallet/app/android/ActionBarTraits;", "equals", "other", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ActionBarTraits.kt */
public final class ActionBarTraits {
    private final boolean isShown;
    private final boolean logoIsShown;
    private final boolean showAddContactMenu;
    private final boolean showEditContactMenu;
    private final boolean showSettingsMenu;
    private final boolean showShopMenu;
    private final boolean showSubmitContactMenu;
    private final boolean showWalletSettingsMenu;
    @Nullable
    private final Integer titleResId;

    public ActionBarTraits() {
        this(false, false, null, false, false, false, false, false, false, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @NotNull
    public static /* synthetic */ ActionBarTraits copy$default(ActionBarTraits actionBarTraits, boolean z, boolean z2, Integer num, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i, Object obj) {
        ActionBarTraits actionBarTraits2 = actionBarTraits;
        int i2 = i;
        return actionBarTraits.copy((i2 & 1) != 0 ? actionBarTraits2.isShown : z, (i2 & 2) != 0 ? actionBarTraits2.logoIsShown : z2, (i2 & 4) != 0 ? actionBarTraits2.titleResId : num, (i2 & 8) != 0 ? actionBarTraits2.showEditContactMenu : z3, (i2 & 16) != 0 ? actionBarTraits2.showAddContactMenu : z4, (i2 & 32) != 0 ? actionBarTraits2.showSubmitContactMenu : z5, (i2 & 64) != 0 ? actionBarTraits2.showSettingsMenu : z6, (i2 & 128) != 0 ? actionBarTraits2.showWalletSettingsMenu : z7, (i2 & 256) != 0 ? actionBarTraits2.showShopMenu : z8);
    }

    public final boolean component1() {
        return this.isShown;
    }

    public final boolean component2() {
        return this.logoIsShown;
    }

    @Nullable
    public final Integer component3() {
        return this.titleResId;
    }

    public final boolean component4() {
        return this.showEditContactMenu;
    }

    public final boolean component5() {
        return this.showAddContactMenu;
    }

    public final boolean component6() {
        return this.showSubmitContactMenu;
    }

    public final boolean component7() {
        return this.showSettingsMenu;
    }

    public final boolean component8() {
        return this.showWalletSettingsMenu;
    }

    public final boolean component9() {
        return this.showShopMenu;
    }

    @NotNull
    public final ActionBarTraits copy(boolean z, boolean z2, @Nullable @StringRes Integer num, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        ActionBarTraits actionBarTraits = new ActionBarTraits(z, z2, num, z3, z4, z5, z6, z7, z8);
        return actionBarTraits;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ActionBarTraits) {
                ActionBarTraits actionBarTraits = (ActionBarTraits) obj;
                if (this.isShown == actionBarTraits.isShown) {
                    if ((this.logoIsShown == actionBarTraits.logoIsShown) && Intrinsics.areEqual((Object) this.titleResId, (Object) actionBarTraits.titleResId)) {
                        if (this.showEditContactMenu == actionBarTraits.showEditContactMenu) {
                            if (this.showAddContactMenu == actionBarTraits.showAddContactMenu) {
                                if (this.showSubmitContactMenu == actionBarTraits.showSubmitContactMenu) {
                                    if (this.showSettingsMenu == actionBarTraits.showSettingsMenu) {
                                        if (this.showWalletSettingsMenu == actionBarTraits.showWalletSettingsMenu) {
                                            if (this.showShopMenu == actionBarTraits.showShopMenu) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.isShown;
        int i = 1;
        if (z) {
            z = true;
        }
        int i2 = (z ? 1 : 0) * true;
        boolean z2 = this.logoIsShown;
        if (z2) {
            z2 = true;
        }
        int i3 = (i2 + (z2 ? 1 : 0)) * 31;
        Integer num = this.titleResId;
        int hashCode = (i3 + (num != null ? num.hashCode() : 0)) * 31;
        boolean z3 = this.showEditContactMenu;
        if (z3) {
            z3 = true;
        }
        int i4 = (hashCode + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.showAddContactMenu;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.showSubmitContactMenu;
        if (z5) {
            z5 = true;
        }
        int i6 = (i5 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.showSettingsMenu;
        if (z6) {
            z6 = true;
        }
        int i7 = (i6 + (z6 ? 1 : 0)) * 31;
        boolean z7 = this.showWalletSettingsMenu;
        if (z7) {
            z7 = true;
        }
        int i8 = (i7 + (z7 ? 1 : 0)) * 31;
        boolean z8 = this.showShopMenu;
        if (!z8) {
            i = z8;
        }
        return i8 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActionBarTraits(isShown=");
        sb.append(this.isShown);
        sb.append(", logoIsShown=");
        sb.append(this.logoIsShown);
        sb.append(", titleResId=");
        sb.append(this.titleResId);
        sb.append(", showEditContactMenu=");
        sb.append(this.showEditContactMenu);
        sb.append(", showAddContactMenu=");
        sb.append(this.showAddContactMenu);
        sb.append(", showSubmitContactMenu=");
        sb.append(this.showSubmitContactMenu);
        sb.append(", showSettingsMenu=");
        sb.append(this.showSettingsMenu);
        sb.append(", showWalletSettingsMenu=");
        sb.append(this.showWalletSettingsMenu);
        sb.append(", showShopMenu=");
        sb.append(this.showShopMenu);
        sb.append(")");
        return sb.toString();
    }

    public ActionBarTraits(boolean z, boolean z2, @Nullable @StringRes Integer num, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        this.isShown = z;
        this.logoIsShown = z2;
        this.titleResId = num;
        this.showEditContactMenu = z3;
        this.showAddContactMenu = z4;
        this.showSubmitContactMenu = z5;
        this.showSettingsMenu = z6;
        this.showWalletSettingsMenu = z7;
        this.showShopMenu = z8;
    }

    public final boolean isShown() {
        return this.isShown;
    }

    public final boolean getLogoIsShown() {
        return this.logoIsShown;
    }

    public /* synthetic */ ActionBarTraits(boolean z, boolean z2, Integer num, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = i;
        boolean z9 = true;
        boolean z10 = (i2 & 1) != 0 ? true : z;
        if ((i2 & 2) == 0) {
            z9 = z2;
        }
        Integer num2 = (i2 & 4) != 0 ? null : num;
        boolean z11 = false;
        boolean z12 = (i2 & 8) != 0 ? false : z3;
        boolean z13 = (i2 & 16) != 0 ? false : z4;
        boolean z14 = (i2 & 32) != 0 ? false : z5;
        boolean z15 = (i2 & 64) != 0 ? false : z6;
        boolean z16 = (i2 & 128) != 0 ? false : z7;
        if ((i2 & 256) == 0) {
            z11 = z8;
        }
        this(z10, z9, num2, z12, z13, z14, z15, z16, z11);
    }

    @Nullable
    public final Integer getTitleResId() {
        return this.titleResId;
    }

    public final boolean getShowEditContactMenu() {
        return this.showEditContactMenu;
    }

    public final boolean getShowAddContactMenu() {
        return this.showAddContactMenu;
    }

    public final boolean getShowSubmitContactMenu() {
        return this.showSubmitContactMenu;
    }

    public final boolean getShowSettingsMenu() {
        return this.showSettingsMenu;
    }

    public final boolean getShowWalletSettingsMenu() {
        return this.showWalletSettingsMenu;
    }

    public final boolean getShowShopMenu() {
        return this.showShopMenu;
    }
}
