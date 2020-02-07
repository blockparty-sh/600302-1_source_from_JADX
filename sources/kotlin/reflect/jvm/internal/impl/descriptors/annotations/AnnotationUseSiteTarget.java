package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationUseSiteTarget.kt */
public enum AnnotationUseSiteTarget {
    FIELD(null, 1, null),
    FILE(null, 1, null),
    PROPERTY(null, 1, null),
    PROPERTY_GETTER("get"),
    PROPERTY_SETTER("set"),
    RECEIVER(null, 1, null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");
    
    public static final Companion Companion = null;
    @NotNull
    private final String renderName;

    /* compiled from: AnnotationUseSiteTarget.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final AnnotationUseSiteTarget getAssociatedUseSiteTarget(@NotNull DeclarationDescriptor declarationDescriptor) {
            Intrinsics.checkParameterIsNotNull(declarationDescriptor, "descriptor");
            if (declarationDescriptor instanceof PropertyDescriptor) {
                return AnnotationUseSiteTarget.PROPERTY;
            }
            if (declarationDescriptor instanceof ValueParameterDescriptor) {
                return AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER;
            }
            if (declarationDescriptor instanceof PropertyGetterDescriptor) {
                return AnnotationUseSiteTarget.PROPERTY_GETTER;
            }
            if (declarationDescriptor instanceof PropertySetterDescriptor) {
                return AnnotationUseSiteTarget.PROPERTY_SETTER;
            }
            return null;
        }
    }

    protected AnnotationUseSiteTarget(String str) {
        if (str == null) {
            String name = name();
            if (name != null) {
                str = name.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        this.renderName = str;
    }

    static {
        Companion = new Companion(null);
    }

    @NotNull
    public final String getRenderName() {
        return this.renderName;
    }
}
