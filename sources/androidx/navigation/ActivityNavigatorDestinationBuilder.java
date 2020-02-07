package androidx.navigation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.IdRes;
import androidx.navigation.ActivityNavigator.Destination;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010#\u001a\u00020\u0002H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0010\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR\u001c\u0010 \u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010\r¨\u0006$"}, mo37405d2 = {"Landroidx/navigation/ActivityNavigatorDestinationBuilder;", "Landroidx/navigation/NavDestinationBuilder;", "Landroidx/navigation/ActivityNavigator$Destination;", "navigator", "Landroidx/navigation/ActivityNavigator;", "id", "", "(Landroidx/navigation/ActivityNavigator;I)V", "action", "", "getAction", "()Ljava/lang/String;", "setAction", "(Ljava/lang/String;)V", "activityClass", "Lkotlin/reflect/KClass;", "Landroid/app/Activity;", "getActivityClass", "()Lkotlin/reflect/KClass;", "setActivityClass", "(Lkotlin/reflect/KClass;)V", "context", "Landroid/content/Context;", "data", "Landroid/net/Uri;", "getData", "()Landroid/net/Uri;", "setData", "(Landroid/net/Uri;)V", "dataPattern", "getDataPattern", "setDataPattern", "targetPackage", "getTargetPackage", "setTargetPackage", "build", "navigation-runtime-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@NavDestinationDsl
/* compiled from: ActivityNavigatorDestinationBuilder.kt */
public final class ActivityNavigatorDestinationBuilder extends NavDestinationBuilder<Destination> {
    @Nullable
    private String action;
    @Nullable
    private KClass<? extends Activity> activityClass;
    private final Context context;
    @Nullable
    private Uri data;
    @Nullable
    private String dataPattern;
    @Nullable
    private String targetPackage;

    public ActivityNavigatorDestinationBuilder(@NotNull ActivityNavigator activityNavigator, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(activityNavigator, "navigator");
        super(activityNavigator, i);
        Context context2 = activityNavigator.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "navigator.context");
        this.context = context2;
    }

    @Nullable
    public final String getTargetPackage() {
        return this.targetPackage;
    }

    public final void setTargetPackage(@Nullable String str) {
        this.targetPackage = str;
    }

    @Nullable
    public final KClass<? extends Activity> getActivityClass() {
        return this.activityClass;
    }

    public final void setActivityClass(@Nullable KClass<? extends Activity> kClass) {
        this.activityClass = kClass;
    }

    @Nullable
    public final String getAction() {
        return this.action;
    }

    public final void setAction(@Nullable String str) {
        this.action = str;
    }

    @Nullable
    public final Uri getData() {
        return this.data;
    }

    public final void setData(@Nullable Uri uri) {
        this.data = uri;
    }

    @Nullable
    public final String getDataPattern() {
        return this.dataPattern;
    }

    public final void setDataPattern(@Nullable String str) {
        this.dataPattern = str;
    }

    @NotNull
    public Destination build() {
        Destination destination = (Destination) super.build();
        destination.setTargetPackage(this.targetPackage);
        KClass<? extends Activity> kClass = this.activityClass;
        if (kClass != null) {
            destination.setComponentName(new ComponentName(this.context, JvmClassMappingKt.getJavaClass(kClass)));
        }
        destination.setAction(this.action);
        destination.setData(this.data);
        destination.setDataPattern(this.dataPattern);
        return destination;
    }
}
