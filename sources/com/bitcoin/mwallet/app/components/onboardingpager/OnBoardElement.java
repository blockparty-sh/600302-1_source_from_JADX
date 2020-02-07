package com.bitcoin.mwallet.app.components.onboardingpager;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardElement;", "", "imageResource", "", "descriptionResource", "(II)V", "getDescriptionResource", "()I", "getImageResource", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OnBoardingPagerPresenter.kt */
public final class OnBoardElement {
    private final int descriptionResource;
    private final int imageResource;

    @NotNull
    public static /* synthetic */ OnBoardElement copy$default(OnBoardElement onBoardElement, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = onBoardElement.imageResource;
        }
        if ((i3 & 2) != 0) {
            i2 = onBoardElement.descriptionResource;
        }
        return onBoardElement.copy(i, i2);
    }

    public final int component1() {
        return this.imageResource;
    }

    public final int component2() {
        return this.descriptionResource;
    }

    @NotNull
    public final OnBoardElement copy(int i, int i2) {
        return new OnBoardElement(i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof OnBoardElement) {
                OnBoardElement onBoardElement = (OnBoardElement) obj;
                if (this.imageResource == onBoardElement.imageResource) {
                    if (this.descriptionResource == onBoardElement.descriptionResource) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.imageResource * 31) + this.descriptionResource;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OnBoardElement(imageResource=");
        sb.append(this.imageResource);
        sb.append(", descriptionResource=");
        sb.append(this.descriptionResource);
        sb.append(")");
        return sb.toString();
    }

    public OnBoardElement(int i, int i2) {
        this.imageResource = i;
        this.descriptionResource = i2;
    }

    public final int getDescriptionResource() {
        return this.descriptionResource;
    }

    public final int getImageResource() {
        return this.imageResource;
    }
}
