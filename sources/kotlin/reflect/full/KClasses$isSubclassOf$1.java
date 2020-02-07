package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty1;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KClasses.kt */
final class KClasses$isSubclassOf$1 extends PropertyReference1 {
    public static final KProperty1 INSTANCE = new KClasses$isSubclassOf$1();

    KClasses$isSubclassOf$1() {
    }

    public String getName() {
        return "superclasses";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinPackage(KClasses.class, "kotlin-reflect-api");
    }

    public String getSignature() {
        return "getSuperclasses(Lkotlin/reflect/KClass;)Ljava/util/List;";
    }

    @Nullable
    public Object get(@Nullable Object obj) {
        return KClasses.getSuperclasses((KClass) obj);
    }
}
