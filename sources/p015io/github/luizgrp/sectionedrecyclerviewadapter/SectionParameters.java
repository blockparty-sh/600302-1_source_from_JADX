package p015io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.annotation.LayoutRes;

/* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters */
public final class SectionParameters {
    @LayoutRes
    public final transient Integer emptyResourceId;
    public final transient boolean emptyViewWillBeProvided;
    @LayoutRes
    public final transient Integer failedResourceId;
    public final transient boolean failedViewWillBeProvided;
    @LayoutRes
    public final transient Integer footerResourceId;
    public final transient boolean footerViewWillBeProvided;
    @LayoutRes
    public final transient Integer headerResourceId;
    public final transient boolean headerViewWillBeProvided;
    @LayoutRes
    public final transient Integer itemResourceId;
    public final transient boolean itemViewWillBeProvided;
    @LayoutRes
    public final transient Integer loadingResourceId;
    public final transient boolean loadingViewWillBeProvided;

    /* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters$Builder */
    public static class Builder {
        /* access modifiers changed from: private */
        @LayoutRes
        public transient Integer emptyResourceId;
        /* access modifiers changed from: private */
        public transient boolean emptyViewWillBeProvided;
        /* access modifiers changed from: private */
        @LayoutRes
        public transient Integer failedResourceId;
        /* access modifiers changed from: private */
        public transient boolean failedViewWillBeProvided;
        /* access modifiers changed from: private */
        @LayoutRes
        public transient Integer footerResourceId;
        /* access modifiers changed from: private */
        public transient boolean footerViewWillBeProvided;
        /* access modifiers changed from: private */
        @LayoutRes
        public transient Integer headerResourceId;
        /* access modifiers changed from: private */
        public transient boolean headerViewWillBeProvided;
        /* access modifiers changed from: private */
        @LayoutRes
        public transient Integer itemResourceId;
        /* access modifiers changed from: private */
        public transient boolean itemViewWillBeProvided;
        /* access modifiers changed from: private */
        @LayoutRes
        public transient Integer loadingResourceId;
        /* access modifiers changed from: private */
        public transient boolean loadingViewWillBeProvided;

        private Builder() {
        }

        public Builder itemResourceId(@LayoutRes int i) {
            this.itemResourceId = Integer.valueOf(i);
            return this;
        }

        public Builder itemViewWillBeProvided() {
            this.itemViewWillBeProvided = true;
            return this;
        }

        public Builder headerResourceId(@LayoutRes int i) {
            this.headerResourceId = Integer.valueOf(i);
            return this;
        }

        public Builder headerViewWillBeProvided() {
            this.headerViewWillBeProvided = true;
            return this;
        }

        public Builder footerResourceId(@LayoutRes int i) {
            this.footerResourceId = Integer.valueOf(i);
            return this;
        }

        public Builder footerViewWillBeProvided() {
            this.footerViewWillBeProvided = true;
            return this;
        }

        public Builder loadingResourceId(@LayoutRes int i) {
            this.loadingResourceId = Integer.valueOf(i);
            return this;
        }

        public Builder loadingViewWillBeProvided() {
            this.loadingViewWillBeProvided = true;
            return this;
        }

        public Builder failedResourceId(@LayoutRes int i) {
            this.failedResourceId = Integer.valueOf(i);
            return this;
        }

        public Builder failedViewWillBeProvided() {
            this.failedViewWillBeProvided = true;
            return this;
        }

        public Builder emptyResourceId(@LayoutRes int i) {
            this.emptyResourceId = Integer.valueOf(i);
            return this;
        }

        public Builder emptyViewWillBeProvided() {
            this.emptyViewWillBeProvided = true;
            return this;
        }

        public SectionParameters build() {
            return new SectionParameters(this);
        }
    }

    private SectionParameters(Builder builder) {
        this.itemResourceId = builder.itemResourceId;
        this.headerResourceId = builder.headerResourceId;
        this.footerResourceId = builder.footerResourceId;
        this.loadingResourceId = builder.loadingResourceId;
        this.failedResourceId = builder.failedResourceId;
        this.emptyResourceId = builder.emptyResourceId;
        this.itemViewWillBeProvided = builder.itemViewWillBeProvided;
        this.headerViewWillBeProvided = builder.headerViewWillBeProvided;
        this.footerViewWillBeProvided = builder.footerViewWillBeProvided;
        this.loadingViewWillBeProvided = builder.loadingViewWillBeProvided;
        this.failedViewWillBeProvided = builder.failedViewWillBeProvided;
        this.emptyViewWillBeProvided = builder.emptyViewWillBeProvided;
        if (this.itemResourceId != null && this.itemViewWillBeProvided) {
            throw new IllegalArgumentException("itemResourceId and itemViewWillBeProvided cannot both be set");
        } else if (this.itemResourceId == null && !this.itemViewWillBeProvided) {
            throw new IllegalArgumentException("Either itemResourceId or itemViewWillBeProvided must be set");
        } else if (this.headerResourceId != null && this.headerViewWillBeProvided) {
            throw new IllegalArgumentException("headerResourceId and headerViewWillBeProvided cannot both be set");
        } else if (this.footerResourceId != null && this.footerViewWillBeProvided) {
            throw new IllegalArgumentException("footerResourceId and footerViewWillBeProvided cannot both be set");
        } else if (this.loadingResourceId != null && this.loadingViewWillBeProvided) {
            throw new IllegalArgumentException("loadingResourceId and loadingViewWillBeProvided cannot both be set");
        } else if (this.failedResourceId != null && this.failedViewWillBeProvided) {
            throw new IllegalArgumentException("failedResourceId and failedViewWillBeProvided cannot both be set");
        } else if (this.emptyResourceId != null && this.emptyViewWillBeProvided) {
            throw new IllegalArgumentException("emptyResourceId and emptyViewWillBeProvided cannot both be set");
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
