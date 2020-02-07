package androidx.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.navigation.common.C0481R;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NavGraph extends NavDestination implements Iterable<NavDestination> {
    final SparseArrayCompat<NavDestination> mNodes = new SparseArrayCompat<>();
    private int mStartDestId;
    private String mStartDestIdName;

    public NavGraph(@NonNull Navigator<? extends NavGraph> navigator) {
        super(navigator);
    }

    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super.onInflate(context, attributeSet);
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0481R.styleable.NavGraphNavigator);
        setStartDestination(obtainAttributes.getResourceId(C0481R.styleable.NavGraphNavigator_startDestination, 0));
        this.mStartDestIdName = getDisplayName(context, this.mStartDestId);
        obtainAttributes.recycle();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public DeepLinkMatch matchDeepLink(@NonNull Uri uri) {
        DeepLinkMatch matchDeepLink = super.matchDeepLink(uri);
        Iterator it = iterator();
        while (it.hasNext()) {
            DeepLinkMatch matchDeepLink2 = ((NavDestination) it.next()).matchDeepLink(uri);
            if (matchDeepLink2 != null && (matchDeepLink == null || matchDeepLink2.compareTo(matchDeepLink) > 0)) {
                matchDeepLink = matchDeepLink2;
            }
        }
        return matchDeepLink;
    }

    public final void addDestination(@NonNull NavDestination navDestination) {
        if (navDestination.getId() != 0) {
            NavDestination navDestination2 = (NavDestination) this.mNodes.get(navDestination.getId());
            if (navDestination2 != navDestination) {
                if (navDestination.getParent() == null) {
                    if (navDestination2 != null) {
                        navDestination2.setParent(null);
                    }
                    navDestination.setParent(this);
                    this.mNodes.put(navDestination.getId(), navDestination);
                    return;
                }
                throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
            }
            return;
        }
        throw new IllegalArgumentException("Destinations must have an id. Call setId() or include an android:id in your navigation XML.");
    }

    public final void addDestinations(@NonNull Collection<NavDestination> collection) {
        for (NavDestination navDestination : collection) {
            if (navDestination != null) {
                addDestination(navDestination);
            }
        }
    }

    public final void addDestinations(@NonNull NavDestination... navDestinationArr) {
        for (NavDestination navDestination : navDestinationArr) {
            if (navDestination != null) {
                addDestination(navDestination);
            }
        }
    }

    @Nullable
    public final NavDestination findNode(@IdRes int i) {
        return findNode(i, true);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final NavDestination findNode(@IdRes int i, boolean z) {
        NavDestination navDestination = (NavDestination) this.mNodes.get(i);
        if (navDestination != null) {
            return navDestination;
        }
        if (!z || getParent() == null) {
            return null;
        }
        return getParent().findNode(i);
    }

    @NonNull
    public final Iterator<NavDestination> iterator() {
        return new Iterator<NavDestination>() {
            private int mIndex = -1;
            private boolean mWentToNext = false;

            public boolean hasNext() {
                return this.mIndex + 1 < NavGraph.this.mNodes.size();
            }

            public NavDestination next() {
                if (hasNext()) {
                    this.mWentToNext = true;
                    SparseArrayCompat<NavDestination> sparseArrayCompat = NavGraph.this.mNodes;
                    int i = this.mIndex + 1;
                    this.mIndex = i;
                    return (NavDestination) sparseArrayCompat.valueAt(i);
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                if (this.mWentToNext) {
                    ((NavDestination) NavGraph.this.mNodes.valueAt(this.mIndex)).setParent(null);
                    NavGraph.this.mNodes.removeAt(this.mIndex);
                    this.mIndex--;
                    this.mWentToNext = false;
                    return;
                }
                throw new IllegalStateException("You must call next() before you can remove an element");
            }
        };
    }

    public final void addAll(@NonNull NavGraph navGraph) {
        Iterator it = navGraph.iterator();
        while (it.hasNext()) {
            NavDestination navDestination = (NavDestination) it.next();
            it.remove();
            addDestination(navDestination);
        }
    }

    public final void remove(@NonNull NavDestination navDestination) {
        int indexOfKey = this.mNodes.indexOfKey(navDestination.getId());
        if (indexOfKey >= 0) {
            ((NavDestination) this.mNodes.valueAt(indexOfKey)).setParent(null);
            this.mNodes.removeAt(indexOfKey);
        }
    }

    public final void clear() {
        Iterator it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public String getDisplayName() {
        return getId() != 0 ? super.getDisplayName() : "the root navigation";
    }

    @IdRes
    public final int getStartDestination() {
        return this.mStartDestId;
    }

    public final void setStartDestination(@IdRes int i) {
        this.mStartDestId = i;
        this.mStartDestIdName = null;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public String getStartDestDisplayName() {
        if (this.mStartDestIdName == null) {
            this.mStartDestIdName = Integer.toString(this.mStartDestId);
        }
        return this.mStartDestIdName;
    }
}
