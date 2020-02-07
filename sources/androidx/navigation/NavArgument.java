package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class NavArgument {
    @Nullable
    private final Object mDefaultValue;
    private final boolean mDefaultValuePresent;
    private final boolean mIsNullable;
    @NonNull
    private final NavType mType;

    public static final class Builder {
        @Nullable
        private Object mDefaultValue;
        private boolean mDefaultValuePresent = false;
        private boolean mIsNullable = false;
        @Nullable
        private NavType<?> mType;

        @NonNull
        public Builder setType(@NonNull NavType<?> navType) {
            this.mType = navType;
            return this;
        }

        @NonNull
        public Builder setIsNullable(boolean z) {
            this.mIsNullable = z;
            return this;
        }

        @NonNull
        public Builder setDefaultValue(@Nullable Object obj) {
            this.mDefaultValue = obj;
            this.mDefaultValuePresent = true;
            return this;
        }

        @NonNull
        public NavArgument build() {
            if (this.mType == null) {
                this.mType = NavType.inferFromValueType(this.mDefaultValue);
            }
            return new NavArgument(this.mType, this.mIsNullable, this.mDefaultValue, this.mDefaultValuePresent);
        }
    }

    NavArgument(@NonNull NavType<?> navType, boolean z, @Nullable Object obj, boolean z2) {
        if (!navType.isNullableAllowed() && z) {
            StringBuilder sb = new StringBuilder();
            sb.append(navType.getName());
            sb.append(" does not allow nullable values");
            throw new IllegalArgumentException(sb.toString());
        } else if (z || !z2 || obj != null) {
            this.mType = navType;
            this.mIsNullable = z;
            this.mDefaultValue = obj;
            this.mDefaultValuePresent = z2;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Argument with type ");
            sb2.append(navType.getName());
            sb2.append(" has null value but is not nullable.");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public boolean isDefaultValuePresent() {
        return this.mDefaultValuePresent;
    }

    @NonNull
    public NavType<?> getType() {
        return this.mType;
    }

    public boolean isNullable() {
        return this.mIsNullable;
    }

    @Nullable
    public Object getDefaultValue() {
        return this.mDefaultValue;
    }

    /* access modifiers changed from: 0000 */
    public void putDefaultValue(@NonNull String str, @NonNull Bundle bundle) {
        if (this.mDefaultValuePresent) {
            this.mType.put(bundle, str, this.mDefaultValue);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean verify(@NonNull String str, @NonNull Bundle bundle) {
        if (!this.mIsNullable && bundle.containsKey(str) && bundle.get(str) == null) {
            return false;
        }
        try {
            this.mType.get(bundle, str);
            return true;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NavArgument navArgument = (NavArgument) obj;
        if (this.mIsNullable != navArgument.mIsNullable || this.mDefaultValuePresent != navArgument.mDefaultValuePresent || !this.mType.equals(navArgument.mType)) {
            return false;
        }
        Object obj2 = this.mDefaultValue;
        if (obj2 != null) {
            z = obj2.equals(navArgument.mDefaultValue);
        } else if (navArgument.mDefaultValue != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = ((((this.mType.hashCode() * 31) + (this.mIsNullable ? 1 : 0)) * 31) + (this.mDefaultValuePresent ? 1 : 0)) * 31;
        Object obj = this.mDefaultValue;
        return hashCode + (obj != null ? obj.hashCode() : 0);
    }
}
