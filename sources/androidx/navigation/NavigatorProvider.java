package androidx.navigation;

import android.annotation.SuppressLint;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigator.Name;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"TypeParameterUnusedInFormals"})
public class NavigatorProvider {
    private static final HashMap<Class<?>, String> sAnnotationNames = new HashMap<>();
    private final HashMap<String, Navigator<? extends NavDestination>> mNavigators = new HashMap<>();

    private static boolean validateName(String str) {
        return str != null && !str.isEmpty();
    }

    @NonNull
    static String getNameForNavigator(@NonNull Class<? extends Navigator> cls) {
        String str = (String) sAnnotationNames.get(cls);
        if (str == null) {
            Name name = (Name) cls.getAnnotation(Name.class);
            str = name != null ? name.value() : null;
            if (validateName(str)) {
                sAnnotationNames.put(cls, str);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("No @Navigator.Name annotation found for ");
                sb.append(cls.getSimpleName());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return str;
    }

    @NonNull
    public final <T extends Navigator<?>> T getNavigator(@NonNull Class<T> cls) {
        return getNavigator(getNameForNavigator(cls));
    }

    @CallSuper
    @NonNull
    public <T extends Navigator<?>> T getNavigator(@NonNull String str) {
        if (validateName(str)) {
            T t = (Navigator) this.mNavigators.get(str);
            if (t != null) {
                return t;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find Navigator with name \"");
            sb.append(str);
            sb.append("\". You must call NavController.addNavigator() for each navigation type.");
            throw new IllegalStateException(sb.toString());
        }
        throw new IllegalArgumentException("navigator name cannot be an empty string");
    }

    @Nullable
    public final Navigator<? extends NavDestination> addNavigator(@NonNull Navigator<? extends NavDestination> navigator) {
        return addNavigator(getNameForNavigator(navigator.getClass()), navigator);
    }

    @CallSuper
    @Nullable
    public Navigator<? extends NavDestination> addNavigator(@NonNull String str, @NonNull Navigator<? extends NavDestination> navigator) {
        if (validateName(str)) {
            return (Navigator) this.mNavigators.put(str, navigator);
        }
        throw new IllegalArgumentException("navigator name cannot be an empty string");
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Navigator<? extends NavDestination>> getNavigators() {
        return this.mNavigators;
    }
}
