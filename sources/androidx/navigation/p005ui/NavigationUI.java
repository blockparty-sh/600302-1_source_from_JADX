package androidx.navigation.p005ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavController.OnDestinationChangedListener;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions.Builder;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import java.lang.ref.WeakReference;
import java.util.Set;

/* renamed from: androidx.navigation.ui.NavigationUI */
public final class NavigationUI {
    private NavigationUI() {
    }

    public static boolean onNavDestinationSelected(@NonNull MenuItem menuItem, @NonNull NavController navController) {
        Builder popExitAnim = new Builder().setLaunchSingleTop(true).setEnterAnim(C0510R.anim.nav_default_enter_anim).setExitAnim(C0510R.anim.nav_default_exit_anim).setPopEnterAnim(C0510R.anim.nav_default_pop_enter_anim).setPopExitAnim(C0510R.anim.nav_default_pop_exit_anim);
        if ((menuItem.getOrder() & 196608) == 0) {
            popExitAnim.setPopUpTo(findStartDestination(navController.getGraph()).getId(), false);
        }
        try {
            navController.navigate(menuItem.getItemId(), (Bundle) null, popExitAnim.build());
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean navigateUp(@NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        return navigateUp(navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static boolean navigateUp(@NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        DrawerLayout drawerLayout = appBarConfiguration.getDrawerLayout();
        NavDestination currentDestination = navController.getCurrentDestination();
        Set topLevelDestinations = appBarConfiguration.getTopLevelDestinations();
        if (drawerLayout != null && currentDestination != null && matchDestinations(currentDestination, topLevelDestinations)) {
            drawerLayout.openDrawer((int) GravityCompat.START);
            return true;
        } else if (navController.navigateUp()) {
            return true;
        } else {
            if (appBarConfiguration.getFallbackOnNavigateUpListener() != null) {
                return appBarConfiguration.getFallbackOnNavigateUpListener().onNavigateUp();
            }
            return false;
        }
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController) {
        setupActionBarWithNavController(appCompatActivity, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        setupActionBarWithNavController(appCompatActivity, navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new ActionBarOnDestinationChangedListener(appCompatActivity, appBarConfiguration));
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull NavController navController) {
        setupWithNavController(toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        setupWithNavController(toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull final NavController navController, @NonNull final AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new ToolbarOnDestinationChangedListener(toolbar, appBarConfiguration));
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NavigationUI.navigateUp(navController, appBarConfiguration);
            }
        });
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController) {
        setupWithNavController(collapsingToolbarLayout, toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        setupWithNavController(collapsingToolbarLayout, toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull final NavController navController, @NonNull final AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new CollapsingToolbarOnDestinationChangedListener(collapsingToolbarLayout, toolbar, appBarConfiguration));
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NavigationUI.navigateUp(navController, appBarConfiguration);
            }
        });
    }

    public static void setupWithNavController(@NonNull final NavigationView navigationView, @NonNull final NavController navController) {
        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                boolean onNavDestinationSelected = NavigationUI.onNavDestinationSelected(menuItem, navController);
                if (onNavDestinationSelected) {
                    ViewParent parent = navigationView.getParent();
                    if (parent instanceof DrawerLayout) {
                        ((DrawerLayout) parent).closeDrawer((View) navigationView);
                    } else {
                        BottomSheetBehavior findBottomSheetBehavior = NavigationUI.findBottomSheetBehavior(navigationView);
                        if (findBottomSheetBehavior != null) {
                            findBottomSheetBehavior.setState(5);
                        }
                    }
                }
                return onNavDestinationSelected;
            }
        });
        final WeakReference weakReference = new WeakReference(navigationView);
        navController.addOnDestinationChangedListener(new OnDestinationChangedListener() {
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                NavigationView navigationView = (NavigationView) weakReference.get();
                if (navigationView == null) {
                    navController.removeOnDestinationChangedListener(this);
                    return;
                }
                Menu menu = navigationView.getMenu();
                int size = menu.size();
                for (int i = 0; i < size; i++) {
                    MenuItem item = menu.getItem(i);
                    item.setChecked(NavigationUI.matchDestination(navDestination, item.getItemId()));
                }
            }
        });
    }

    static BottomSheetBehavior findBottomSheetBehavior(@NonNull View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                return findBottomSheetBehavior((View) parent);
            }
            return null;
        }
        Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (!(behavior instanceof BottomSheetBehavior)) {
            return null;
        }
        return (BottomSheetBehavior) behavior;
    }

    public static void setupWithNavController(@NonNull BottomNavigationView bottomNavigationView, @NonNull final NavController navController) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return NavigationUI.onNavDestinationSelected(menuItem, navController);
            }
        });
        final WeakReference weakReference = new WeakReference(bottomNavigationView);
        navController.addOnDestinationChangedListener(new OnDestinationChangedListener() {
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                BottomNavigationView bottomNavigationView = (BottomNavigationView) weakReference.get();
                if (bottomNavigationView == null) {
                    navController.removeOnDestinationChangedListener(this);
                    return;
                }
                Menu menu = bottomNavigationView.getMenu();
                int size = menu.size();
                for (int i = 0; i < size; i++) {
                    MenuItem item = menu.getItem(i);
                    if (NavigationUI.matchDestination(navDestination, item.getItemId())) {
                        item.setChecked(true);
                    }
                }
            }
        });
    }

    static boolean matchDestination(@NonNull NavDestination navDestination, @IdRes int i) {
        while (navDestination.getId() != i && navDestination.getParent() != null) {
            navDestination = navDestination.getParent();
        }
        return navDestination.getId() == i;
    }

    static boolean matchDestinations(@NonNull NavDestination navDestination, @NonNull Set<Integer> set) {
        while (!set.contains(Integer.valueOf(navDestination.getId()))) {
            navDestination = navDestination.getParent();
            if (navDestination == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=androidx.navigation.NavGraph, code=androidx.navigation.NavDestination, for r1v0, types: [androidx.navigation.NavDestination, androidx.navigation.NavGraph] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.navigation.NavDestination findStartDestination(@androidx.annotation.NonNull androidx.navigation.NavDestination r1) {
        /*
        L_0x0000:
            boolean r0 = r1 instanceof androidx.navigation.NavGraph
            if (r0 == 0) goto L_0x000f
            androidx.navigation.NavGraph r1 = (androidx.navigation.NavGraph) r1
            int r0 = r1.getStartDestination()
            androidx.navigation.NavDestination r1 = r1.findNode(r0)
            goto L_0x0000
        L_0x000f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p005ui.NavigationUI.findStartDestination(androidx.navigation.NavGraph):androidx.navigation.NavDestination");
    }
}
