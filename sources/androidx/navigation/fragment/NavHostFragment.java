package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator.Destination;

public class NavHostFragment extends Fragment implements NavHost {
    private static final String KEY_DEFAULT_NAV_HOST = "android-support-nav:fragment:defaultHost";
    private static final String KEY_GRAPH_ID = "android-support-nav:fragment:graphId";
    private static final String KEY_NAV_CONTROLLER_STATE = "android-support-nav:fragment:navControllerState";
    private static final String KEY_START_DESTINATION_ARGS = "android-support-nav:fragment:startDestinationArgs";
    private boolean mDefaultNavHost;
    private int mGraphId;
    private Boolean mIsPrimaryBeforeOnCreate = null;
    private NavHostController mNavController;

    @NonNull
    public static NavController findNavController(@NonNull Fragment fragment) {
        for (Fragment fragment2 = fragment; fragment2 != null; fragment2 = fragment2.getParentFragment()) {
            if (fragment2 instanceof NavHostFragment) {
                return ((NavHostFragment) fragment2).getNavController();
            }
            Fragment primaryNavigationFragment = fragment2.requireFragmentManager().getPrimaryNavigationFragment();
            if (primaryNavigationFragment instanceof NavHostFragment) {
                return ((NavHostFragment) primaryNavigationFragment).getNavController();
            }
        }
        View view = fragment.getView();
        if (view != null) {
            return Navigation.findNavController(view);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Fragment ");
        sb.append(fragment);
        sb.append(" does not have a NavController set");
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    public static NavHostFragment create(@NavigationRes int i) {
        return create(i, null);
    }

    @NonNull
    public static NavHostFragment create(@NavigationRes int i, @Nullable Bundle bundle) {
        Bundle bundle2;
        if (i != 0) {
            bundle2 = new Bundle();
            bundle2.putInt(KEY_GRAPH_ID, i);
        } else {
            bundle2 = null;
        }
        if (bundle != null) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putBundle(KEY_START_DESTINATION_ARGS, bundle);
        }
        NavHostFragment navHostFragment = new NavHostFragment();
        if (bundle2 != null) {
            navHostFragment.setArguments(bundle2);
        }
        return navHostFragment;
    }

    @NonNull
    public final NavController getNavController() {
        NavHostController navHostController = this.mNavController;
        if (navHostController != null) {
            return navHostController;
        }
        throw new IllegalStateException("NavController is not available before onCreate()");
    }

    @CallSuper
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (this.mDefaultNavHost) {
            requireFragmentManager().beginTransaction().setPrimaryNavigationFragment(this).commit();
        }
    }

    @CallSuper
    public void onCreate(@Nullable Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        this.mNavController = new NavHostController(requireContext());
        this.mNavController.setLifecycleOwner(this);
        this.mNavController.setOnBackPressedDispatcher(requireActivity().getOnBackPressedDispatcher());
        NavHostController navHostController = this.mNavController;
        Boolean bool = this.mIsPrimaryBeforeOnCreate;
        int i = 0;
        navHostController.enableOnBackPressed(bool != null && bool.booleanValue());
        Bundle bundle3 = null;
        this.mIsPrimaryBeforeOnCreate = null;
        this.mNavController.setViewModelStore(getViewModelStore());
        onCreateNavController(this.mNavController);
        if (bundle != null) {
            bundle2 = bundle.getBundle(KEY_NAV_CONTROLLER_STATE);
            if (bundle.getBoolean(KEY_DEFAULT_NAV_HOST, false)) {
                this.mDefaultNavHost = true;
                requireFragmentManager().beginTransaction().setPrimaryNavigationFragment(this).commit();
            }
        } else {
            bundle2 = null;
        }
        if (bundle2 != null) {
            this.mNavController.restoreState(bundle2);
        }
        int i2 = this.mGraphId;
        if (i2 != 0) {
            this.mNavController.setGraph(i2);
            return;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            i = arguments.getInt(KEY_GRAPH_ID);
        }
        if (arguments != null) {
            bundle3 = arguments.getBundle(KEY_START_DESTINATION_ARGS);
        }
        if (i != 0) {
            this.mNavController.setGraph(i, bundle3);
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onCreateNavController(@NonNull NavController navController) {
        navController.getNavigatorProvider().addNavigator(new DialogFragmentNavigator(requireContext(), getChildFragmentManager()));
        navController.getNavigatorProvider().addNavigator(createFragmentNavigator());
    }

    @CallSuper
    public void onPrimaryNavigationFragmentChanged(boolean z) {
        NavHostController navHostController = this.mNavController;
        if (navHostController != null) {
            navHostController.enableOnBackPressed(z);
        } else {
            this.mIsPrimaryBeforeOnCreate = Boolean.valueOf(z);
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Deprecated
    public Navigator<? extends Destination> createFragmentNavigator() {
        return new FragmentNavigator(requireContext(), getChildFragmentManager(), getId());
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        frameLayout.setId(getId());
        return frameLayout;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (view instanceof ViewGroup) {
            if (view.getParent() != null) {
                view = (View) view.getParent();
            }
            Navigation.setViewNavController(view, this.mNavController);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("created host view ");
        sb.append(view);
        sb.append(" is not a ViewGroup");
        throw new IllegalStateException(sb.toString());
    }

    @CallSuper
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0487R.styleable.NavHost);
        int resourceId = obtainStyledAttributes.getResourceId(C0487R.styleable.NavHost_navGraph, 0);
        if (resourceId != 0) {
            this.mGraphId = resourceId;
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, C0487R.styleable.NavHostFragment);
        if (obtainStyledAttributes2.getBoolean(C0487R.styleable.NavHostFragment_defaultNavHost, false)) {
            this.mDefaultNavHost = true;
        }
        obtainStyledAttributes2.recycle();
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Bundle saveState = this.mNavController.saveState();
        if (saveState != null) {
            bundle.putBundle(KEY_NAV_CONTROLLER_STATE, saveState);
        }
        if (this.mDefaultNavHost) {
            bundle.putBoolean(KEY_DEFAULT_NAV_HOST, true);
        }
    }
}
