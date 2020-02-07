package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment.WhenMappings;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaPackageFragment.kt */
final class LazyJavaPackageFragment$partToFacade$2 extends Lambda implements Function0<HashMap<JvmClassName, JvmClassName>> {
    final /* synthetic */ LazyJavaPackageFragment this$0;

    LazyJavaPackageFragment$partToFacade$2(LazyJavaPackageFragment lazyJavaPackageFragment) {
        this.this$0 = lazyJavaPackageFragment;
        super(0);
    }

    @NotNull
    public final HashMap<JvmClassName, JvmClassName> invoke() {
        HashMap<JvmClassName, JvmClassName> hashMap = new HashMap<>();
        for (Entry entry : this.this$0.getBinaryClasses$descriptors_jvm().entrySet()) {
            String str = (String) entry.getKey();
            KotlinJvmBinaryClass kotlinJvmBinaryClass = (KotlinJvmBinaryClass) entry.getValue();
            JvmClassName byInternalName = JvmClassName.byInternalName(str);
            KotlinClassHeader classHeader = kotlinJvmBinaryClass.getClassHeader();
            int i = WhenMappings.$EnumSwitchMapping$0[classHeader.getKind().ordinal()];
            String str2 = "partName";
            if (i == 1) {
                Map map = hashMap;
                Intrinsics.checkExpressionValueIsNotNull(byInternalName, str2);
                String multifileClassName = classHeader.getMultifileClassName();
                if (multifileClassName != null) {
                    JvmClassName byInternalName2 = JvmClassName.byInternalName(multifileClassName);
                    Intrinsics.checkExpressionValueIsNotNull(byInternalName2, "JvmClassName.byInternalN…: continue@kotlinClasses)");
                    map.put(byInternalName, byInternalName2);
                }
            } else if (i == 2) {
                Map map2 = hashMap;
                Intrinsics.checkExpressionValueIsNotNull(byInternalName, str2);
                map2.put(byInternalName, byInternalName);
            }
        }
        return hashMap;
    }
}
