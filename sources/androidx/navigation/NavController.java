package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavOptions.Builder;
import androidx.navigation.Navigator.Extras;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class NavController {
    private static final String KEY_BACK_STACK_ARGS = "android-support-nav:controller:backStackArgs";
    private static final String KEY_BACK_STACK_IDS = "android-support-nav:controller:backStackIds";
    private static final String KEY_BACK_STACK_UUIDS = "android-support-nav:controller:backStackUUIDs";
    static final String KEY_DEEP_LINK_EXTRAS = "android-support-nav:controller:deepLinkExtras";
    static final String KEY_DEEP_LINK_HANDLED = "android-support-nav:controller:deepLinkHandled";
    static final String KEY_DEEP_LINK_IDS = "android-support-nav:controller:deepLinkIds";
    @NonNull
    public static final String KEY_DEEP_LINK_INTENT = "android-support-nav:controller:deepLinkIntent";
    private static final String KEY_NAVIGATOR_STATE = "android-support-nav:controller:navigatorState";
    private static final String KEY_NAVIGATOR_STATE_NAMES = "android-support-nav:controller:navigatorState:names";
    private static final String TAG = "NavController";
    private Activity mActivity;
    private final Deque<NavBackStackEntry> mBackStack = new ArrayDeque();
    private Parcelable[] mBackStackArgsToRestore;
    private int[] mBackStackIdsToRestore;
    private String[] mBackStackUUIDsToRestore;
    private final Context mContext;
    private boolean mDeepLinkHandled;
    private boolean mEnableOnBackPressedCallback = true;
    private NavGraph mGraph;
    private NavInflater mInflater;
    private LifecycleOwner mLifecycleOwner;
    private final NavigatorProvider mNavigatorProvider = new NavigatorProvider();
    private Bundle mNavigatorStateToRestore;
    private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) {
        public void handleOnBackPressed() {
            NavController.this.popBackStack();
        }
    };
    private final CopyOnWriteArrayList<OnDestinationChangedListener> mOnDestinationChangedListeners = new CopyOnWriteArrayList<>();
    private NavControllerViewModel mViewModel;

    public interface OnDestinationChangedListener {
        void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle);
    }

    public NavController(@NonNull Context context) {
        this.mContext = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            } else if (context instanceof Activity) {
                this.mActivity = (Activity) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        NavigatorProvider navigatorProvider = this.mNavigatorProvider;
        navigatorProvider.addNavigator(new NavGraphNavigator(navigatorProvider));
        this.mNavigatorProvider.addNavigator(new ActivityNavigator(this.mContext));
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    @NonNull
    public NavigatorProvider getNavigatorProvider() {
        return this.mNavigatorProvider;
    }

    public void addOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
        if (!this.mBackStack.isEmpty()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.mBackStack.peekLast();
            onDestinationChangedListener.onDestinationChanged(this, navBackStackEntry.getDestination(), navBackStackEntry.getArguments());
        }
        this.mOnDestinationChangedListeners.add(onDestinationChangedListener);
    }

    public void removeOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
        this.mOnDestinationChangedListeners.remove(onDestinationChangedListener);
    }

    public boolean popBackStack() {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        return popBackStack(getCurrentDestination().getId(), true);
    }

    public boolean popBackStack(@IdRes int i, boolean z) {
        return popBackStackInternal(i, z) && dispatchOnDestinationChanged();
    }

    /* access modifiers changed from: 0000 */
    public boolean popBackStackInternal(@IdRes int i, boolean z) {
        boolean z2;
        boolean z3 = false;
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator descendingIterator = this.mBackStack.descendingIterator();
        while (true) {
            if (!descendingIterator.hasNext()) {
                z2 = false;
                break;
            }
            NavDestination destination = ((NavBackStackEntry) descendingIterator.next()).getDestination();
            Navigator navigator = this.mNavigatorProvider.getNavigator(destination.getNavigatorName());
            if (z || destination.getId() != i) {
                arrayList.add(navigator);
            }
            if (destination.getId() == i) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            String displayName = NavDestination.getDisplayName(this.mContext, i);
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring popBackStack to destination ");
            sb.append(displayName);
            sb.append(" as it was not found on the current back stack");
            Log.i(TAG, sb.toString());
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext() && ((Navigator) it.next()).popBackStack()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.mBackStack.removeLast();
            NavControllerViewModel navControllerViewModel = this.mViewModel;
            if (navControllerViewModel != null) {
                navControllerViewModel.clear(navBackStackEntry.mId);
            }
            z3 = true;
        }
        updateOnBackPressedCallbackEnabled();
        return z3;
    }

    public boolean navigateUp() {
        if (getDestinationCountOnBackStack() != 1) {
            return popBackStack();
        }
        NavDestination currentDestination = getCurrentDestination();
        int id = currentDestination.getId();
        for (NavGraph parent = currentDestination.getParent(); parent != null; parent = parent.getParent()) {
            if (parent.getStartDestination() != id) {
                new NavDeepLinkBuilder(this).setDestination(parent.getId()).createTaskStackBuilder().startActivities();
                Activity activity = this.mActivity;
                if (activity != null) {
                    activity.finish();
                }
                return true;
            }
            id = parent.getId();
        }
        return false;
    }

    private int getDestinationCountOnBackStack() {
        int i = 0;
        for (NavBackStackEntry destination : this.mBackStack) {
            if (!(destination.getDestination() instanceof NavGraph)) {
                i++;
            }
        }
        return i;
    }

    private boolean dispatchOnDestinationChanged() {
        while (!this.mBackStack.isEmpty() && (((NavBackStackEntry) this.mBackStack.peekLast()).getDestination() instanceof NavGraph)) {
            if (!popBackStackInternal(((NavBackStackEntry) this.mBackStack.peekLast()).getDestination().getId(), true)) {
                break;
            }
        }
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.mBackStack.peekLast();
        Iterator it = this.mOnDestinationChangedListeners.iterator();
        while (it.hasNext()) {
            ((OnDestinationChangedListener) it.next()).onDestinationChanged(this, navBackStackEntry.getDestination(), navBackStackEntry.getArguments());
        }
        return true;
    }

    @NonNull
    public NavInflater getNavInflater() {
        if (this.mInflater == null) {
            this.mInflater = new NavInflater(this.mContext, this.mNavigatorProvider);
        }
        return this.mInflater;
    }

    @CallSuper
    public void setGraph(@NavigationRes int i) {
        setGraph(i, (Bundle) null);
    }

    @CallSuper
    public void setGraph(@NavigationRes int i, @Nullable Bundle bundle) {
        setGraph(getNavInflater().inflate(i), bundle);
    }

    @CallSuper
    public void setGraph(@NonNull NavGraph navGraph) {
        setGraph(navGraph, (Bundle) null);
    }

    @CallSuper
    public void setGraph(@NonNull NavGraph navGraph, @Nullable Bundle bundle) {
        NavGraph navGraph2 = this.mGraph;
        if (navGraph2 != null) {
            popBackStackInternal(navGraph2.getId(), true);
        }
        this.mGraph = navGraph;
        onGraphCreated(bundle);
    }

    private void onGraphCreated(@Nullable Bundle bundle) {
        Bundle bundle2 = this.mNavigatorStateToRestore;
        if (bundle2 != null) {
            ArrayList stringArrayList = bundle2.getStringArrayList(KEY_NAVIGATOR_STATE_NAMES);
            if (stringArrayList != null) {
                Iterator it = stringArrayList.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    Navigator navigator = this.mNavigatorProvider.getNavigator(str);
                    Bundle bundle3 = this.mNavigatorStateToRestore.getBundle(str);
                    if (bundle3 != null) {
                        navigator.onRestoreState(bundle3);
                    }
                }
            }
        }
        boolean z = false;
        if (this.mBackStackUUIDsToRestore != null) {
            int i = 0;
            while (true) {
                String[] strArr = this.mBackStackUUIDsToRestore;
                if (i >= strArr.length) {
                    updateOnBackPressedCallbackEnabled();
                    this.mBackStackUUIDsToRestore = null;
                    this.mBackStackIdsToRestore = null;
                    this.mBackStackArgsToRestore = null;
                    break;
                }
                UUID fromString = UUID.fromString(strArr[i]);
                int i2 = this.mBackStackIdsToRestore[i];
                Bundle bundle4 = (Bundle) this.mBackStackArgsToRestore[i];
                NavDestination findDestination = findDestination(i2);
                if (findDestination != null) {
                    if (bundle4 != null) {
                        bundle4.setClassLoader(this.mContext.getClassLoader());
                    }
                    this.mBackStack.add(new NavBackStackEntry(fromString, findDestination, bundle4, this.mViewModel));
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("unknown destination during restore: ");
                    sb.append(this.mContext.getResources().getResourceName(i2));
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
        if (this.mGraph != null && this.mBackStack.isEmpty()) {
            if (!this.mDeepLinkHandled) {
                Activity activity = this.mActivity;
                if (activity != null && handleDeepLink(activity.getIntent())) {
                    z = true;
                }
            }
            if (!z) {
                navigate((NavDestination) this.mGraph, bundle, (NavOptions) null, (Extras) null);
            }
        }
    }

    public boolean handleDeepLink(@Nullable Intent intent) {
        NavGraph navGraph;
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        int[] intArray = extras != null ? extras.getIntArray(KEY_DEEP_LINK_IDS) : null;
        Bundle bundle = new Bundle();
        Bundle bundle2 = extras != null ? extras.getBundle(KEY_DEEP_LINK_EXTRAS) : null;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if ((intArray == null || intArray.length == 0) && intent.getData() != null) {
            DeepLinkMatch matchDeepLink = this.mGraph.matchDeepLink(intent.getData());
            if (matchDeepLink != null) {
                intArray = matchDeepLink.getDestination().buildDeepLinkIds();
                bundle.putAll(matchDeepLink.getMatchingArgs());
            }
        }
        if (intArray == null || intArray.length == 0) {
            return false;
        }
        String findInvalidDestinationDisplayNameInDeepLink = findInvalidDestinationDisplayNameInDeepLink(intArray);
        if (findInvalidDestinationDisplayNameInDeepLink != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find destination ");
            sb.append(findInvalidDestinationDisplayNameInDeepLink);
            sb.append(" in the navigation graph, ignoring the deep link from ");
            sb.append(intent);
            Log.i(TAG, sb.toString());
            return false;
        }
        bundle.putParcelable(KEY_DEEP_LINK_INTENT, intent);
        int flags = intent.getFlags();
        int i = 268435456 & flags;
        if (i == 0 || (flags & 32768) != 0) {
            String str = "unknown destination during deep link: ";
            if (i != 0) {
                if (!this.mBackStack.isEmpty()) {
                    popBackStackInternal(this.mGraph.getId(), true);
                }
                int i2 = 0;
                while (i2 < intArray.length) {
                    int i3 = i2 + 1;
                    int i4 = intArray[i2];
                    NavDestination findDestination = findDestination(i4);
                    if (findDestination != null) {
                        navigate(findDestination, bundle, new Builder().setEnterAnim(0).setExitAnim(0).build(), (Extras) null);
                        i2 = i3;
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(NavDestination.getDisplayName(this.mContext, i4));
                        throw new IllegalStateException(sb2.toString());
                    }
                }
                return true;
            }
            NavGraph navGraph2 = this.mGraph;
            int i5 = 0;
            while (i5 < intArray.length) {
                int i6 = intArray[i5];
                NavDestination findNode = i5 == 0 ? this.mGraph : navGraph2.findNode(i6);
                if (findNode != null) {
                    if (i5 != intArray.length - 1) {
                        while (true) {
                            navGraph = (NavGraph) findNode;
                            if (!(navGraph.findNode(navGraph.getStartDestination()) instanceof NavGraph)) {
                                break;
                            }
                            findNode = navGraph.findNode(navGraph.getStartDestination());
                        }
                        navGraph2 = navGraph;
                    } else {
                        navigate(findNode, findNode.addInDefaultArgs(bundle), new Builder().setPopUpTo(this.mGraph.getId(), true).setEnterAnim(0).setExitAnim(0).build(), (Extras) null);
                    }
                    i5++;
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append(NavDestination.getDisplayName(this.mContext, i6));
                    throw new IllegalStateException(sb3.toString());
                }
            }
            this.mDeepLinkHandled = true;
            return true;
        }
        intent.addFlags(32768);
        TaskStackBuilder.create(this.mContext).addNextIntentWithParentStack(intent).startActivities();
        Activity activity = this.mActivity;
        if (activity != null) {
            activity.finish();
            this.mActivity.overridePendingTransition(0, 0);
        }
        return true;
    }

    @Nullable
    private String findInvalidDestinationDisplayNameInDeepLink(@NonNull int[] iArr) {
        NavGraph navGraph;
        NavGraph navGraph2 = this.mGraph;
        int i = 0;
        while (i < iArr.length) {
            int i2 = iArr[i];
            NavDestination findNode = i == 0 ? this.mGraph : navGraph2.findNode(i2);
            if (findNode == null) {
                return NavDestination.getDisplayName(this.mContext, i2);
            }
            if (i != iArr.length - 1) {
                while (true) {
                    navGraph = (NavGraph) findNode;
                    if (!(navGraph.findNode(navGraph.getStartDestination()) instanceof NavGraph)) {
                        break;
                    }
                    findNode = navGraph.findNode(navGraph.getStartDestination());
                }
                navGraph2 = navGraph;
            }
            i++;
        }
        return null;
    }

    @NonNull
    public NavGraph getGraph() {
        NavGraph navGraph = this.mGraph;
        if (navGraph != null) {
            return navGraph;
        }
        throw new IllegalStateException("You must call setGraph() before calling getGraph()");
    }

    @Nullable
    public NavDestination getCurrentDestination() {
        if (this.mBackStack.isEmpty()) {
            return null;
        }
        return ((NavBackStackEntry) this.mBackStack.getLast()).getDestination();
    }

    /* access modifiers changed from: 0000 */
    public NavDestination findDestination(@IdRes int i) {
        NavDestination navDestination;
        NavGraph navGraph;
        NavGraph navGraph2 = this.mGraph;
        if (navGraph2 == null) {
            return null;
        }
        if (navGraph2.getId() == i) {
            return this.mGraph;
        }
        if (this.mBackStack.isEmpty()) {
            navDestination = this.mGraph;
        } else {
            navDestination = ((NavBackStackEntry) this.mBackStack.getLast()).getDestination();
        }
        if (navDestination instanceof NavGraph) {
            navGraph = (NavGraph) navDestination;
        } else {
            navGraph = navDestination.getParent();
        }
        return navGraph.findNode(i);
    }

    public void navigate(@IdRes int i) {
        navigate(i, (Bundle) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle) {
        navigate(i, bundle, (NavOptions) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle, @Nullable NavOptions navOptions) {
        navigate(i, bundle, navOptions, (Extras) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Extras extras) {
        NavDestination navDestination;
        int i2;
        String str;
        if (this.mBackStack.isEmpty()) {
            navDestination = this.mGraph;
        } else {
            navDestination = ((NavBackStackEntry) this.mBackStack.getLast()).getDestination();
        }
        if (navDestination != null) {
            NavAction action = navDestination.getAction(i);
            Bundle bundle2 = null;
            if (action != null) {
                if (navOptions == null) {
                    navOptions = action.getNavOptions();
                }
                i2 = action.getDestinationId();
                Bundle defaultArguments = action.getDefaultArguments();
                if (defaultArguments != null) {
                    bundle2 = new Bundle();
                    bundle2.putAll(defaultArguments);
                }
            } else {
                i2 = i;
            }
            if (bundle != null) {
                if (bundle2 == null) {
                    bundle2 = new Bundle();
                }
                bundle2.putAll(bundle);
            }
            if (i2 == 0 && navOptions != null && navOptions.getPopUpTo() != -1) {
                popBackStack(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
            } else if (i2 != 0) {
                NavDestination findDestination = findDestination(i2);
                if (findDestination == null) {
                    String displayName = NavDestination.getDisplayName(this.mContext, i2);
                    StringBuilder sb = new StringBuilder();
                    sb.append("navigation destination ");
                    sb.append(displayName);
                    if (action != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(" referenced from action ");
                        sb2.append(NavDestination.getDisplayName(this.mContext, i));
                        str = sb2.toString();
                    } else {
                        str = "";
                    }
                    sb.append(str);
                    sb.append(" is unknown to this NavController");
                    throw new IllegalArgumentException(sb.toString());
                }
                navigate(findDestination, bundle2, navOptions, extras);
            } else {
                throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
            }
        } else {
            throw new IllegalStateException("no current navigation node");
        }
    }

    public void navigate(@NonNull Uri uri) {
        navigate(uri, (NavOptions) null);
    }

    public void navigate(@NonNull Uri uri, @Nullable NavOptions navOptions) {
        navigate(uri, navOptions, (Extras) null);
    }

    public void navigate(@NonNull Uri uri, @Nullable NavOptions navOptions, @Nullable Extras extras) {
        DeepLinkMatch matchDeepLink = this.mGraph.matchDeepLink(uri);
        if (matchDeepLink != null) {
            navigate(matchDeepLink.getDestination(), matchDeepLink.getMatchingArgs(), navOptions, extras);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("navigation destination with deepLink ");
        sb.append(uri);
        sb.append(" is unknown to this NavController");
        throw new IllegalArgumentException(sb.toString());
    }

    private void navigate(@NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Extras extras) {
        boolean popBackStackInternal = (navOptions == null || navOptions.getPopUpTo() == -1) ? false : popBackStackInternal(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
        Navigator navigator = this.mNavigatorProvider.getNavigator(navDestination.getNavigatorName());
        Bundle addInDefaultArgs = navDestination.addInDefaultArgs(bundle);
        NavDestination navigate = navigator.navigate(navDestination, addInDefaultArgs, navOptions, extras);
        if (navigate != null) {
            if (!(navigate instanceof FloatingWindow)) {
                while (!this.mBackStack.isEmpty() && (((NavBackStackEntry) this.mBackStack.peekLast()).getDestination() instanceof FloatingWindow)) {
                    if (!popBackStackInternal(((NavBackStackEntry) this.mBackStack.peekLast()).getDestination().getId(), true)) {
                        break;
                    }
                }
            }
            if (this.mBackStack.isEmpty()) {
                this.mBackStack.add(new NavBackStackEntry(this.mGraph, addInDefaultArgs, this.mViewModel));
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            NavDestination navDestination2 = navigate;
            while (navDestination2 != null && findDestination(navDestination2.getId()) == null) {
                navDestination2 = navDestination2.getParent();
                if (navDestination2 != null) {
                    arrayDeque.addFirst(new NavBackStackEntry(navDestination2, addInDefaultArgs, this.mViewModel));
                }
            }
            this.mBackStack.addAll(arrayDeque);
            this.mBackStack.add(new NavBackStackEntry(navigate, navigate.addInDefaultArgs(addInDefaultArgs), this.mViewModel));
        }
        updateOnBackPressedCallbackEnabled();
        if (popBackStackInternal || navigate != null) {
            dispatchOnDestinationChanged();
        }
    }

    public void navigate(@NonNull NavDirections navDirections) {
        navigate(navDirections.getActionId(), navDirections.getArguments());
    }

    public void navigate(@NonNull NavDirections navDirections, @Nullable NavOptions navOptions) {
        navigate(navDirections.getActionId(), navDirections.getArguments(), navOptions);
    }

    public void navigate(@NonNull NavDirections navDirections, @NonNull Extras extras) {
        navigate(navDirections.getActionId(), navDirections.getArguments(), (NavOptions) null, extras);
    }

    @NonNull
    public NavDeepLinkBuilder createDeepLink() {
        return new NavDeepLinkBuilder(this);
    }

    @CallSuper
    @Nullable
    public Bundle saveState() {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        Bundle bundle2 = new Bundle();
        for (Entry entry : this.mNavigatorProvider.getNavigators().entrySet()) {
            String str = (String) entry.getKey();
            Bundle onSaveState = ((Navigator) entry.getValue()).onSaveState();
            if (onSaveState != null) {
                arrayList.add(str);
                bundle2.putBundle(str, onSaveState);
            }
        }
        if (!arrayList.isEmpty()) {
            bundle = new Bundle();
            bundle2.putStringArrayList(KEY_NAVIGATOR_STATE_NAMES, arrayList);
            bundle.putBundle(KEY_NAVIGATOR_STATE, bundle2);
        } else {
            bundle = null;
        }
        if (!this.mBackStack.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String[] strArr = new String[this.mBackStack.size()];
            int[] iArr = new int[this.mBackStack.size()];
            Parcelable[] parcelableArr = new Parcelable[this.mBackStack.size()];
            int i = 0;
            for (NavBackStackEntry navBackStackEntry : this.mBackStack) {
                strArr[i] = navBackStackEntry.mId.toString();
                iArr[i] = navBackStackEntry.getDestination().getId();
                int i2 = i + 1;
                parcelableArr[i] = navBackStackEntry.getArguments();
                i = i2;
            }
            bundle.putStringArray(KEY_BACK_STACK_UUIDS, strArr);
            bundle.putIntArray(KEY_BACK_STACK_IDS, iArr);
            bundle.putParcelableArray(KEY_BACK_STACK_ARGS, parcelableArr);
        }
        if (this.mDeepLinkHandled) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean(KEY_DEEP_LINK_HANDLED, this.mDeepLinkHandled);
        }
        return bundle;
    }

    @CallSuper
    public void restoreState(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(this.mContext.getClassLoader());
            this.mNavigatorStateToRestore = bundle.getBundle(KEY_NAVIGATOR_STATE);
            this.mBackStackUUIDsToRestore = bundle.getStringArray(KEY_BACK_STACK_UUIDS);
            this.mBackStackIdsToRestore = bundle.getIntArray(KEY_BACK_STACK_IDS);
            this.mBackStackArgsToRestore = bundle.getParcelableArray(KEY_BACK_STACK_ARGS);
            this.mDeepLinkHandled = bundle.getBoolean(KEY_DEEP_LINK_HANDLED);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setLifecycleOwner(@NonNull LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
    }

    /* access modifiers changed from: 0000 */
    public void setOnBackPressedDispatcher(@NonNull OnBackPressedDispatcher onBackPressedDispatcher) {
        if (this.mLifecycleOwner != null) {
            this.mOnBackPressedCallback.remove();
            onBackPressedDispatcher.addCallback(this.mLifecycleOwner, this.mOnBackPressedCallback);
            return;
        }
        throw new IllegalStateException("You must call setLifecycleOwner() before calling setOnBackPressedDispatcher()");
    }

    /* access modifiers changed from: 0000 */
    public void enableOnBackPressed(boolean z) {
        this.mEnableOnBackPressedCallback = z;
        updateOnBackPressedCallbackEnabled();
    }

    private void updateOnBackPressedCallbackEnabled() {
        OnBackPressedCallback onBackPressedCallback = this.mOnBackPressedCallback;
        boolean z = true;
        if (!this.mEnableOnBackPressedCallback || getDestinationCountOnBackStack() <= 1) {
            z = false;
        }
        onBackPressedCallback.setEnabled(z);
    }

    /* access modifiers changed from: 0000 */
    public void setViewModelStore(@NonNull ViewModelStore viewModelStore) {
        this.mViewModel = NavControllerViewModel.getInstance(viewModelStore);
        for (NavBackStackEntry navControllerViewModel : this.mBackStack) {
            navControllerViewModel.setNavControllerViewModel(this.mViewModel);
        }
    }

    @NonNull
    @Deprecated
    public ViewModelStore getViewModelStore(@IdRes int i) {
        return getViewModelStoreOwner(i).getViewModelStore();
    }

    @NonNull
    public ViewModelStoreOwner getViewModelStoreOwner(@IdRes int i) {
        if (this.mViewModel != null) {
            NavBackStackEntry navBackStackEntry = null;
            Iterator descendingIterator = this.mBackStack.descendingIterator();
            while (true) {
                if (!descendingIterator.hasNext()) {
                    break;
                }
                NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) descendingIterator.next();
                NavDestination destination = navBackStackEntry2.getDestination();
                if ((destination instanceof NavGraph) && destination.getId() == i) {
                    navBackStackEntry = navBackStackEntry2;
                    break;
                }
            }
            if (navBackStackEntry != null) {
                return navBackStackEntry;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No NavGraph with ID ");
            sb.append(i);
            sb.append(" is on the NavController's back stack");
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalStateException("You must call setViewModelStore() before calling getViewModelStoreOwner().");
    }
}
