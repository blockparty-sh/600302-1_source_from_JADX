package kotlin.reflect.jvm.internal.structure;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassObjectAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/structure/ReflectJavaClassObjectAnnotationArgument;", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaAnnotationArgument;", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaClassObjectAnnotationArgument;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "klass", "Ljava/lang/Class;", "(Lorg/jetbrains/kotlin/name/Name;Ljava/lang/Class;)V", "getReferencedType", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaType;", "descriptors.runtime"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: ReflectJavaAnnotationArguments.kt */
public final class ReflectJavaClassObjectAnnotationArgument extends ReflectJavaAnnotationArgument implements JavaClassObjectAnnotationArgument {
    private final Class<?> klass;

    public ReflectJavaClassObjectAnnotationArgument(@Nullable Name name, @NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "klass");
        super(name);
        this.klass = cls;
    }

    @NotNull
    public JavaType getReferencedType() {
        return ReflectJavaType.Factory.create(this.klass);
    }
}
