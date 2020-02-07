package androidx.navigation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.navigation.NavDestination.ClassType;
import androidx.navigation.Navigator.Name;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

@Name("activity")
public class ActivityNavigator extends Navigator<Destination> {
    private static final String EXTRA_NAV_CURRENT = "android-support-navigation:ActivityNavigator:current";
    private static final String EXTRA_NAV_SOURCE = "android-support-navigation:ActivityNavigator:source";
    private static final String EXTRA_POP_ENTER_ANIM = "android-support-navigation:ActivityNavigator:popEnterAnim";
    private static final String EXTRA_POP_EXIT_ANIM = "android-support-navigation:ActivityNavigator:popExitAnim";
    private Context mContext;
    private Activity mHostActivity;

    @ClassType(Activity.class)
    public static class Destination extends NavDestination {
        private String mDataPattern;
        private Intent mIntent;

        /* access modifiers changed from: 0000 */
        public boolean supportsActions() {
            return false;
        }

        public Destination(@NonNull NavigatorProvider navigatorProvider) {
            this(navigatorProvider.getNavigator(ActivityNavigator.class));
        }

        public Destination(@NonNull Navigator<? extends Destination> navigator) {
            super(navigator);
        }

        @CallSuper
        public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
            super.onInflate(context, attributeSet);
            TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0476R.styleable.ActivityNavigator);
            String string = obtainAttributes.getString(C0476R.styleable.ActivityNavigator_targetPackage);
            if (string != null) {
                string = string.replace("${applicationId}", context.getPackageName());
            }
            setTargetPackage(string);
            String string2 = obtainAttributes.getString(C0476R.styleable.ActivityNavigator_android_name);
            if (string2 != null) {
                if (string2.charAt(0) == '.') {
                    StringBuilder sb = new StringBuilder();
                    sb.append(context.getPackageName());
                    sb.append(string2);
                    string2 = sb.toString();
                }
                setComponentName(new ComponentName(context, string2));
            }
            setAction(obtainAttributes.getString(C0476R.styleable.ActivityNavigator_action));
            String string3 = obtainAttributes.getString(C0476R.styleable.ActivityNavigator_data);
            if (string3 != null) {
                setData(Uri.parse(string3));
            }
            setDataPattern(obtainAttributes.getString(C0476R.styleable.ActivityNavigator_dataPattern));
            obtainAttributes.recycle();
        }

        @NonNull
        public final Destination setIntent(@Nullable Intent intent) {
            this.mIntent = intent;
            return this;
        }

        @Nullable
        public final Intent getIntent() {
            return this.mIntent;
        }

        @NonNull
        public final Destination setTargetPackage(@Nullable String str) {
            if (this.mIntent == null) {
                this.mIntent = new Intent();
            }
            this.mIntent.setPackage(str);
            return this;
        }

        @Nullable
        public final String getTargetPackage() {
            Intent intent = this.mIntent;
            if (intent == null) {
                return null;
            }
            return intent.getPackage();
        }

        @NonNull
        public final Destination setComponentName(@Nullable ComponentName componentName) {
            if (this.mIntent == null) {
                this.mIntent = new Intent();
            }
            this.mIntent.setComponent(componentName);
            return this;
        }

        @Nullable
        public final ComponentName getComponent() {
            Intent intent = this.mIntent;
            if (intent == null) {
                return null;
            }
            return intent.getComponent();
        }

        @NonNull
        public final Destination setAction(@Nullable String str) {
            if (this.mIntent == null) {
                this.mIntent = new Intent();
            }
            this.mIntent.setAction(str);
            return this;
        }

        @Nullable
        public final String getAction() {
            Intent intent = this.mIntent;
            if (intent == null) {
                return null;
            }
            return intent.getAction();
        }

        @NonNull
        public final Destination setData(@Nullable Uri uri) {
            if (this.mIntent == null) {
                this.mIntent = new Intent();
            }
            this.mIntent.setData(uri);
            return this;
        }

        @Nullable
        public final Uri getData() {
            Intent intent = this.mIntent;
            if (intent == null) {
                return null;
            }
            return intent.getData();
        }

        @NonNull
        public final Destination setDataPattern(@Nullable String str) {
            this.mDataPattern = str;
            return this;
        }

        @Nullable
        public final String getDataPattern() {
            return this.mDataPattern;
        }
    }

    public static final class Extras implements androidx.navigation.Navigator.Extras {
        private final ActivityOptionsCompat mActivityOptions;
        private final int mFlags;

        public static final class Builder {
            private ActivityOptionsCompat mActivityOptions;
            private int mFlags;

            @NonNull
            public Builder addFlags(int i) {
                this.mFlags = i | this.mFlags;
                return this;
            }

            @NonNull
            public Builder setActivityOptions(@NonNull ActivityOptionsCompat activityOptionsCompat) {
                this.mActivityOptions = activityOptionsCompat;
                return this;
            }

            @NonNull
            public Extras build() {
                return new Extras(this.mFlags, this.mActivityOptions);
            }
        }

        Extras(int i, @Nullable ActivityOptionsCompat activityOptionsCompat) {
            this.mFlags = i;
            this.mActivityOptions = activityOptionsCompat;
        }

        public int getFlags() {
            return this.mFlags;
        }

        @Nullable
        public ActivityOptionsCompat getActivityOptions() {
            return this.mActivityOptions;
        }
    }

    public ActivityNavigator(@NonNull Context context) {
        this.mContext = context;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                this.mHostActivity = (Activity) context;
                return;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
    }

    public static void applyPopAnimationsToPendingTransition(@NonNull Activity activity) {
        Intent intent = activity.getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra(EXTRA_POP_ENTER_ANIM, -1);
            int intExtra2 = intent.getIntExtra(EXTRA_POP_EXIT_ANIM, -1);
            if (!(intExtra == -1 && intExtra2 == -1)) {
                if (intExtra == -1) {
                    intExtra = 0;
                }
                if (intExtra2 == -1) {
                    intExtra2 = 0;
                }
                activity.overridePendingTransition(intExtra, intExtra2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final Context getContext() {
        return this.mContext;
    }

    @NonNull
    public Destination createDestination() {
        return new Destination((Navigator<? extends Destination>) this);
    }

    public boolean popBackStack() {
        Activity activity = this.mHostActivity;
        if (activity == null) {
            return false;
        }
        activity.finish();
        return true;
    }

    @Nullable
    public NavDestination navigate(@NonNull Destination destination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable androidx.navigation.Navigator.Extras extras) {
        if (destination.getIntent() != null) {
            Intent intent = new Intent(destination.getIntent());
            if (bundle != null) {
                intent.putExtras(bundle);
                String dataPattern = destination.getDataPattern();
                if (!TextUtils.isEmpty(dataPattern)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(dataPattern);
                    while (matcher.find()) {
                        String group = matcher.group(1);
                        if (bundle.containsKey(group)) {
                            matcher.appendReplacement(stringBuffer, "");
                            stringBuffer.append(Uri.encode(bundle.get(group).toString()));
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Could not find ");
                            sb.append(group);
                            sb.append(" in ");
                            sb.append(bundle);
                            sb.append(" to fill data pattern ");
                            sb.append(dataPattern);
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    matcher.appendTail(stringBuffer);
                    intent.setData(Uri.parse(stringBuffer.toString()));
                }
            }
            boolean z = extras instanceof Extras;
            if (z) {
                intent.addFlags(((Extras) extras).getFlags());
            }
            if (!(this.mContext instanceof Activity)) {
                intent.addFlags(268435456);
            }
            if (navOptions != null && navOptions.shouldLaunchSingleTop()) {
                intent.addFlags(PKIFailureInfo.duplicateCertReq);
            }
            Activity activity = this.mHostActivity;
            String str = EXTRA_NAV_CURRENT;
            if (activity != null) {
                Intent intent2 = activity.getIntent();
                if (intent2 != null) {
                    int intExtra = intent2.getIntExtra(str, 0);
                    if (intExtra != 0) {
                        intent.putExtra(EXTRA_NAV_SOURCE, intExtra);
                    }
                }
            }
            intent.putExtra(str, destination.getId());
            if (navOptions != null) {
                intent.putExtra(EXTRA_POP_ENTER_ANIM, navOptions.getPopEnterAnim());
                intent.putExtra(EXTRA_POP_EXIT_ANIM, navOptions.getPopExitAnim());
            }
            if (z) {
                ActivityOptionsCompat activityOptions = ((Extras) extras).getActivityOptions();
                if (activityOptions != null) {
                    ActivityCompat.startActivity(this.mContext, intent, activityOptions.toBundle());
                } else {
                    this.mContext.startActivity(intent);
                }
            } else {
                this.mContext.startActivity(intent);
            }
            if (!(navOptions == null || this.mHostActivity == null)) {
                int enterAnim = navOptions.getEnterAnim();
                int exitAnim = navOptions.getExitAnim();
                if (!(enterAnim == -1 && exitAnim == -1)) {
                    if (enterAnim == -1) {
                        enterAnim = 0;
                    }
                    if (exitAnim == -1) {
                        exitAnim = 0;
                    }
                    this.mHostActivity.overridePendingTransition(enterAnim, exitAnim);
                }
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Destination ");
        sb2.append(destination.getId());
        sb2.append(" does not have an Intent set.");
        throw new IllegalStateException(sb2.toString());
    }
}
