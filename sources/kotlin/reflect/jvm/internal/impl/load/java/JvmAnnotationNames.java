package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;

public final class JvmAnnotationNames {
    public static final Name DEFAULT_ANNOTATION_MEMBER_NAME = Name.identifier("value");
    public static final FqName DEFAULT_NULL_FQ_NAME = new FqName("kotlin.annotations.jvm.internal.DefaultNull");
    public static final FqName DEFAULT_VALUE_FQ_NAME = new FqName("kotlin.annotations.jvm.internal.DefaultValue");
    public static final FqName ENHANCED_MUTABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedMutability");
    public static final FqName ENHANCED_NULLABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedNullability");
    public static final FqName JETBRAINS_MUTABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Mutable");
    public static final FqName JETBRAINS_NOT_NULL_ANNOTATION = new FqName("org.jetbrains.annotations.NotNull");
    public static final FqName JETBRAINS_NULLABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Nullable");
    public static final FqName JETBRAINS_READONLY_ANNOTATION = new FqName("org.jetbrains.annotations.ReadOnly");
    public static final String METADATA_DESC;
    public static final FqName METADATA_FQ_NAME = new FqName("kotlin.Metadata");
    public static final FqName MUTABLE_ANNOTATION = new FqName("kotlin.annotations.jvm.Mutable");
    public static final FqName PARAMETER_NAME_FQ_NAME = new FqName("kotlin.annotations.jvm.internal.ParameterName");
    public static final FqName PURELY_IMPLEMENTS_ANNOTATION = new FqName("kotlin.jvm.PurelyImplements");
    public static final FqName READONLY_ANNOTATION = new FqName("kotlin.annotations.jvm.ReadOnly");

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("L");
        sb.append(JvmClassName.byFqNameWithoutInnerClasses(METADATA_FQ_NAME).getInternalName());
        sb.append(";");
        METADATA_DESC = sb.toString();
    }
}
