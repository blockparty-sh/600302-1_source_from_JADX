package androidx.navigation;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.navigation.common.C0481R;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class NavDestination {
    private static final HashMap<String, Class<?>> sClasses = new HashMap<>();
    private SparseArrayCompat<NavAction> mActions;
    private HashMap<String, NavArgument> mArguments;
    private ArrayList<NavDeepLink> mDeepLinks;
    private int mId;
    private String mIdName;
    private CharSequence mLabel;
    private final String mNavigatorName;
    private NavGraph mParent;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    public @interface ClassType {
        Class<?> value();
    }

    static class DeepLinkMatch implements Comparable<DeepLinkMatch> {
        @NonNull
        private final NavDestination mDestination;
        private final boolean mIsExactDeepLink;
        @NonNull
        private final Bundle mMatchingArgs;

        DeepLinkMatch(@NonNull NavDestination navDestination, @NonNull Bundle bundle, boolean z) {
            this.mDestination = navDestination;
            this.mMatchingArgs = bundle;
            this.mIsExactDeepLink = z;
        }

        /* access modifiers changed from: 0000 */
        @NonNull
        public NavDestination getDestination() {
            return this.mDestination;
        }

        /* access modifiers changed from: 0000 */
        @NonNull
        public Bundle getMatchingArgs() {
            return this.mMatchingArgs;
        }

        public int compareTo(DeepLinkMatch deepLinkMatch) {
            if (this.mIsExactDeepLink && !deepLinkMatch.mIsExactDeepLink) {
                return 1;
            }
            if (this.mIsExactDeepLink || !deepLinkMatch.mIsExactDeepLink) {
                return this.mMatchingArgs.size() - deepLinkMatch.mMatchingArgs.size();
            }
            return -1;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean supportsActions() {
        return true;
    }

    @NonNull
    protected static <C> Class<? extends C> parseClassFromName(@NonNull Context context, @NonNull String str, @NonNull Class<? extends C> cls) {
        if (str.charAt(0) == '.') {
            StringBuilder sb = new StringBuilder();
            sb.append(context.getPackageName());
            sb.append(str);
            str = sb.toString();
        }
        Class<? extends C> cls2 = (Class) sClasses.get(str);
        if (cls2 == null) {
            try {
                cls2 = Class.forName(str, true, context.getClassLoader());
                sClasses.put(str, cls2);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        if (cls.isAssignableFrom(cls2)) {
            return cls2;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(" must be a subclass of ");
        sb2.append(cls);
        throw new IllegalArgumentException(sb2.toString());
    }

    @NonNull
    static String getDisplayName(@NonNull Context context, int i) {
        try {
            return context.getResources().getResourceName(i);
        } catch (NotFoundException unused) {
            return Integer.toString(i);
        }
    }

    @NonNull
    public final Map<String, NavArgument> getArguments() {
        HashMap<String, NavArgument> hashMap = this.mArguments;
        if (hashMap == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public NavDestination(@NonNull Navigator<? extends NavDestination> navigator) {
        this(NavigatorProvider.getNameForNavigator(navigator.getClass()));
    }

    public NavDestination(@NonNull String str) {
        this.mNavigatorName = str;
    }

    @CallSuper
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0481R.styleable.Navigator);
        setId(obtainAttributes.getResourceId(C0481R.styleable.Navigator_android_id, 0));
        this.mIdName = getDisplayName(context, this.mId);
        setLabel(obtainAttributes.getText(C0481R.styleable.Navigator_android_label));
        obtainAttributes.recycle();
    }

    /* access modifiers changed from: 0000 */
    public final void setParent(NavGraph navGraph) {
        this.mParent = navGraph;
    }

    @Nullable
    public final NavGraph getParent() {
        return this.mParent;
    }

    @IdRes
    public final int getId() {
        return this.mId;
    }

    public final void setId(@IdRes int i) {
        this.mId = i;
        this.mIdName = null;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public String getDisplayName() {
        if (this.mIdName == null) {
            this.mIdName = Integer.toString(this.mId);
        }
        return this.mIdName;
    }

    public final void setLabel(@Nullable CharSequence charSequence) {
        this.mLabel = charSequence;
    }

    @Nullable
    public final CharSequence getLabel() {
        return this.mLabel;
    }

    @NonNull
    public final String getNavigatorName() {
        return this.mNavigatorName;
    }

    public boolean hasDeepLink(@NonNull Uri uri) {
        return matchDeepLink(uri) != null;
    }

    public final void addDeepLink(@NonNull String str) {
        if (this.mDeepLinks == null) {
            this.mDeepLinks = new ArrayList<>();
        }
        this.mDeepLinks.add(new NavDeepLink(str));
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public DeepLinkMatch matchDeepLink(@NonNull Uri uri) {
        ArrayList<NavDeepLink> arrayList = this.mDeepLinks;
        DeepLinkMatch deepLinkMatch = null;
        if (arrayList == null) {
            return null;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NavDeepLink navDeepLink = (NavDeepLink) it.next();
            Bundle matchingArguments = navDeepLink.getMatchingArguments(uri, getArguments());
            if (matchingArguments != null) {
                DeepLinkMatch deepLinkMatch2 = new DeepLinkMatch(this, matchingArguments, navDeepLink.isExactDeepLink());
                if (deepLinkMatch == null || deepLinkMatch2.compareTo(deepLinkMatch) > 0) {
                    deepLinkMatch = deepLinkMatch2;
                }
            }
        }
        return deepLinkMatch;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public int[] buildDeepLinkIds() {
        ArrayDeque arrayDeque = new ArrayDeque();
        NavDestination navDestination = this;
        while (true) {
            NavGraph parent = navDestination.getParent();
            if (parent == 0 || parent.getStartDestination() != navDestination.getId()) {
                arrayDeque.addFirst(navDestination);
            }
            if (parent == 0) {
                break;
            }
            navDestination = parent;
        }
        int[] iArr = new int[arrayDeque.size()];
        int i = 0;
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            iArr[i] = ((NavDestination) it.next()).getId();
            i = i2;
        }
        return iArr;
    }

    @Nullable
    public final NavAction getAction(@IdRes int i) {
        SparseArrayCompat<NavAction> sparseArrayCompat = this.mActions;
        NavAction navAction = sparseArrayCompat == null ? null : (NavAction) sparseArrayCompat.get(i);
        if (navAction != null) {
            return navAction;
        }
        if (getParent() != null) {
            return getParent().getAction(i);
        }
        return null;
    }

    public final void putAction(@IdRes int i, @IdRes int i2) {
        putAction(i, new NavAction(i2));
    }

    public final void putAction(@IdRes int i, @NonNull NavAction navAction) {
        if (!supportsActions()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot add action ");
            sb.append(i);
            sb.append(" to ");
            sb.append(this);
            sb.append(" as it does not support actions, indicating that it is a terminal destination in your navigation graph and will never trigger actions.");
            throw new UnsupportedOperationException(sb.toString());
        } else if (i != 0) {
            if (this.mActions == null) {
                this.mActions = new SparseArrayCompat<>();
            }
            this.mActions.put(i, navAction);
        } else {
            throw new IllegalArgumentException("Cannot have an action with actionId 0");
        }
    }

    public final void removeAction(@IdRes int i) {
        SparseArrayCompat<NavAction> sparseArrayCompat = this.mActions;
        if (sparseArrayCompat != null) {
            sparseArrayCompat.delete(i);
        }
    }

    public final void addArgument(@NonNull String str, @NonNull NavArgument navArgument) {
        if (this.mArguments == null) {
            this.mArguments = new HashMap<>();
        }
        this.mArguments.put(str, navArgument);
    }

    public final void removeArgument(@NonNull String str) {
        HashMap<String, NavArgument> hashMap = this.mArguments;
        if (hashMap != null) {
            hashMap.remove(str);
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Bundle addInDefaultArgs(@Nullable Bundle bundle) {
        if (bundle == null) {
            HashMap<String, NavArgument> hashMap = this.mArguments;
            if (hashMap == null || hashMap.isEmpty()) {
                return null;
            }
        }
        Bundle bundle2 = new Bundle();
        HashMap<String, NavArgument> hashMap2 = this.mArguments;
        if (hashMap2 != null) {
            for (Entry entry : hashMap2.entrySet()) {
                ((NavArgument) entry.getValue()).putDefaultValue((String) entry.getKey(), bundle2);
            }
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            HashMap<String, NavArgument> hashMap3 = this.mArguments;
            if (hashMap3 != null) {
                for (Entry entry2 : hashMap3.entrySet()) {
                    if (!((NavArgument) entry2.getValue()).verify((String) entry2.getKey(), bundle)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Wrong argument type for '");
                        sb.append((String) entry2.getKey());
                        sb.append("' in argument bundle. ");
                        sb.append(((NavArgument) entry2.getValue()).getType().getName());
                        sb.append(" expected.");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            }
        }
        return bundle2;
    }
}
