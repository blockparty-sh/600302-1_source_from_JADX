package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.FloatingWindow;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDestination.ClassType;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.Navigator.Extras;
import androidx.navigation.Navigator.Name;
import androidx.navigation.NavigatorProvider;

@Name("dialog")
public final class DialogFragmentNavigator extends Navigator<Destination> {
    private static final String DIALOG_TAG = "androidx-nav-fragment:navigator:dialog:";
    private static final String KEY_DIALOG_COUNT = "androidx-nav-dialogfragment:navigator:count";
    private static final String TAG = "DialogFragmentNavigator";
    private final Context mContext;
    private int mDialogCount = 0;
    private final FragmentManager mFragmentManager;
    private LifecycleEventObserver mObserver = new LifecycleEventObserver() {
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Event event) {
            if (event == Event.ON_STOP) {
                DialogFragment dialogFragment = (DialogFragment) lifecycleOwner;
                if (!dialogFragment.requireDialog().isShowing()) {
                    NavHostFragment.findNavController(dialogFragment).popBackStack();
                }
            }
        }
    };

    @ClassType(DialogFragment.class)
    public static class Destination extends NavDestination implements FloatingWindow {
        private String mClassName;

        public Destination(@NonNull NavigatorProvider navigatorProvider) {
            this(navigatorProvider.getNavigator(DialogFragmentNavigator.class));
        }

        public Destination(@NonNull Navigator<? extends Destination> navigator) {
            super(navigator);
        }

        @CallSuper
        public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
            super.onInflate(context, attributeSet);
            TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0487R.styleable.DialogFragmentNavigator);
            String string = obtainAttributes.getString(C0487R.styleable.DialogFragmentNavigator_android_name);
            if (string != null) {
                setClassName(string);
            }
            obtainAttributes.recycle();
        }

        @NonNull
        public final Destination setClassName(@NonNull String str) {
            this.mClassName = str;
            return this;
        }

        @NonNull
        public final String getClassName() {
            String str = this.mClassName;
            if (str != null) {
                return str;
            }
            throw new IllegalStateException("DialogFragment class was not set");
        }
    }

    public DialogFragmentNavigator(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    public boolean popBackStack() {
        if (this.mDialogCount == 0) {
            return false;
        }
        if (this.mFragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        }
        FragmentManager fragmentManager = this.mFragmentManager;
        StringBuilder sb = new StringBuilder();
        sb.append(DIALOG_TAG);
        int i = this.mDialogCount - 1;
        this.mDialogCount = i;
        sb.append(i);
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(sb.toString());
        if (findFragmentByTag != null) {
            findFragmentByTag.getLifecycle().removeObserver(this.mObserver);
            ((DialogFragment) findFragmentByTag).dismiss();
        }
        return true;
    }

    @NonNull
    public Destination createDestination() {
        return new Destination((Navigator<? extends Destination>) this);
    }

    @Nullable
    public NavDestination navigate(@NonNull Destination destination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Extras extras) {
        if (this.mFragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String className = destination.getClassName();
        if (className.charAt(0) == '.') {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mContext.getPackageName());
            sb.append(className);
            className = sb.toString();
        }
        Fragment instantiate = this.mFragmentManager.getFragmentFactory().instantiate(this.mContext.getClassLoader(), className);
        if (DialogFragment.class.isAssignableFrom(instantiate.getClass())) {
            DialogFragment dialogFragment = (DialogFragment) instantiate;
            dialogFragment.setArguments(bundle);
            dialogFragment.getLifecycle().addObserver(this.mObserver);
            FragmentManager fragmentManager = this.mFragmentManager;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(DIALOG_TAG);
            int i = this.mDialogCount;
            this.mDialogCount = i + 1;
            sb2.append(i);
            dialogFragment.show(fragmentManager, sb2.toString());
            return destination;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Dialog destination ");
        sb3.append(destination.getClassName());
        sb3.append(" is not an instance of DialogFragment");
        throw new IllegalArgumentException(sb3.toString());
    }

    @Nullable
    public Bundle onSaveState() {
        if (this.mDialogCount == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DIALOG_COUNT, this.mDialogCount);
        return bundle;
    }

    public void onRestoreState(@Nullable Bundle bundle) {
        if (bundle != null) {
            int i = 0;
            this.mDialogCount = bundle.getInt(KEY_DIALOG_COUNT, 0);
            while (i < this.mDialogCount) {
                FragmentManager fragmentManager = this.mFragmentManager;
                StringBuilder sb = new StringBuilder();
                sb.append(DIALOG_TAG);
                sb.append(i);
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(sb.toString());
                if (dialogFragment != null) {
                    dialogFragment.getLifecycle().addObserver(this.mObserver);
                    i++;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("DialogFragment ");
                    sb2.append(i);
                    sb2.append(" doesn't exist in the FragmentManager");
                    throw new IllegalStateException(sb2.toString());
                }
            }
        }
    }
}
