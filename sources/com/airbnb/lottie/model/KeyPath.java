package com.airbnb.lottie.model;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Marker;

public class KeyPath {
    private final List<String> keys;
    @Nullable
    private KeyPathElement resolvedElement;

    public KeyPath(String... strArr) {
        this.keys = Arrays.asList(strArr);
    }

    private KeyPath(KeyPath keyPath) {
        this.keys = new ArrayList(keyPath.keys);
        this.resolvedElement = keyPath.resolvedElement;
    }

    @CheckResult
    @RestrictTo({Scope.LIBRARY})
    public KeyPath addKey(String str) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.keys.add(str);
        return keyPath;
    }

    @RestrictTo({Scope.LIBRARY})
    public KeyPath resolve(KeyPathElement keyPathElement) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.resolvedElement = keyPathElement;
        return keyPath;
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY})
    public KeyPathElement getResolvedElement() {
        return this.resolvedElement;
    }

    @RestrictTo({Scope.LIBRARY})
    public boolean matches(String str, int i) {
        if (isContainer(str)) {
            return true;
        }
        if (i >= this.keys.size()) {
            return false;
        }
        if (((String) this.keys.get(i)).equals(str) || ((String) this.keys.get(i)).equals("**") || ((String) this.keys.get(i)).equals(Marker.ANY_MARKER)) {
            return true;
        }
        return false;
    }

    @RestrictTo({Scope.LIBRARY})
    public int incrementDepthBy(String str, int i) {
        if (isContainer(str)) {
            return 0;
        }
        if (!((String) this.keys.get(i)).equals("**")) {
            return 1;
        }
        if (i != this.keys.size() - 1 && ((String) this.keys.get(i + 1)).equals(str)) {
            return 2;
        }
        return 0;
    }

    @RestrictTo({Scope.LIBRARY})
    public boolean fullyResolvesTo(String str, int i) {
        boolean z = false;
        if (i >= this.keys.size()) {
            return false;
        }
        boolean z2 = i == this.keys.size() - 1;
        String str2 = (String) this.keys.get(i);
        if (!str2.equals("**")) {
            boolean z3 = str2.equals(str) || str2.equals(Marker.ANY_MARKER);
            if ((z2 || (i == this.keys.size() - 2 && endsWithGlobstar())) && z3) {
                z = true;
            }
            return z;
        }
        if (!z2 && ((String) this.keys.get(i + 1)).equals(str)) {
            if (i == this.keys.size() - 2 || (i == this.keys.size() - 3 && endsWithGlobstar())) {
                z = true;
            }
            return z;
        } else if (z2) {
            return true;
        } else {
            int i2 = i + 1;
            if (i2 < this.keys.size() - 1) {
                return false;
            }
            return ((String) this.keys.get(i2)).equals(str);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public boolean propagateToChildren(String str, int i) {
        boolean z = true;
        if ("__container".equals(str)) {
            return true;
        }
        if (i >= this.keys.size() - 1 && !((String) this.keys.get(i)).equals("**")) {
            z = false;
        }
        return z;
    }

    private boolean isContainer(String str) {
        return "__container".equals(str);
    }

    private boolean endsWithGlobstar() {
        List<String> list = this.keys;
        return ((String) list.get(list.size() - 1)).equals("**");
    }

    public String keysToString() {
        return this.keys.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.keys);
        sb.append(",resolved=");
        sb.append(this.resolvedElement != null);
        sb.append('}');
        return sb.toString();
    }
}
