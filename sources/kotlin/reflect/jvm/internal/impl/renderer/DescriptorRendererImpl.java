package kotlin.reflect.jvm.internal.impl.renderer;

import com.microsoft.appcenter.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PossiblyInnerType;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.AbbreviatedType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils.UninferredParameterTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnresolvedType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.WrappedType;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DescriptorRendererImpl.kt */
public final class DescriptorRendererImpl extends DescriptorRenderer implements DescriptorRendererOptions {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererImpl.class), "functionTypeAnnotationsRenderer", "getFunctionTypeAnnotationsRenderer()Lorg/jetbrains/kotlin/renderer/DescriptorRendererImpl;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererImpl.class), "functionTypeParameterTypesRenderer", "getFunctionTypeParameterTypesRenderer()Lorg/jetbrains/kotlin/renderer/DescriptorRenderer;"))};
    private final Lazy functionTypeAnnotationsRenderer$delegate;
    private final Lazy functionTypeParameterTypesRenderer$delegate;
    @NotNull
    private final DescriptorRendererOptionsImpl options;

    /* compiled from: DescriptorRendererImpl.kt */
    private final class RenderDeclarationDescriptorVisitor implements DeclarationDescriptorVisitor<Unit, StringBuilder> {
        public RenderDeclarationDescriptorVisitor() {
        }

        public /* bridge */ /* synthetic */ Object visitClassDescriptor(ClassDescriptor classDescriptor, Object obj) {
            visitClassDescriptor(classDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor, Object obj) {
            visitConstructorDescriptor(constructorDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitFunctionDescriptor(FunctionDescriptor functionDescriptor, Object obj) {
            visitFunctionDescriptor(functionDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitModuleDeclaration(ModuleDescriptor moduleDescriptor, Object obj) {
            visitModuleDeclaration(moduleDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor, Object obj) {
            visitPackageFragmentDescriptor(packageFragmentDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor, Object obj) {
            visitPackageViewDescriptor(packageViewDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitPropertyDescriptor(PropertyDescriptor propertyDescriptor, Object obj) {
            visitPropertyDescriptor(propertyDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor, Object obj) {
            visitPropertyGetterDescriptor(propertyGetterDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor, Object obj) {
            visitPropertySetterDescriptor(propertySetterDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor, Object obj) {
            visitReceiverParameterDescriptor(receiverParameterDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor, Object obj) {
            visitTypeAliasDescriptor(typeAliasDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor, Object obj) {
            visitTypeParameterDescriptor(typeParameterDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public /* bridge */ /* synthetic */ Object visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor, Object obj) {
            visitValueParameterDescriptor(valueParameterDescriptor, (StringBuilder) obj);
            return Unit.INSTANCE;
        }

        public void visitValueParameterDescriptor(@NotNull ValueParameterDescriptor valueParameterDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(valueParameterDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderValueParameter(valueParameterDescriptor, true, sb, true);
        }

        public void visitPropertyDescriptor(@NotNull PropertyDescriptor propertyDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(propertyDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderProperty(propertyDescriptor, sb);
        }

        public void visitPropertyGetterDescriptor(@NotNull PropertyGetterDescriptor propertyGetterDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(propertyGetterDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            if (DescriptorRendererImpl.this.getRenderAccessors()) {
                DescriptorRendererImpl.this.renderAccessorModifiers(propertyGetterDescriptor, sb);
                sb.append("getter for ");
                DescriptorRendererImpl descriptorRendererImpl = DescriptorRendererImpl.this;
                PropertyDescriptor correspondingProperty = propertyGetterDescriptor.getCorrespondingProperty();
                Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "descriptor.correspondingProperty");
                descriptorRendererImpl.renderProperty(correspondingProperty, sb);
                return;
            }
            visitFunctionDescriptor((FunctionDescriptor) propertyGetterDescriptor, sb);
        }

        public void visitPropertySetterDescriptor(@NotNull PropertySetterDescriptor propertySetterDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(propertySetterDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            if (DescriptorRendererImpl.this.getRenderAccessors()) {
                DescriptorRendererImpl.this.renderAccessorModifiers(propertySetterDescriptor, sb);
                sb.append("setter for ");
                DescriptorRendererImpl descriptorRendererImpl = DescriptorRendererImpl.this;
                PropertyDescriptor correspondingProperty = propertySetterDescriptor.getCorrespondingProperty();
                Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "descriptor.correspondingProperty");
                descriptorRendererImpl.renderProperty(correspondingProperty, sb);
                return;
            }
            visitFunctionDescriptor((FunctionDescriptor) propertySetterDescriptor, sb);
        }

        public void visitFunctionDescriptor(@NotNull FunctionDescriptor functionDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(functionDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderFunction(functionDescriptor, sb);
        }

        public void visitReceiverParameterDescriptor(@NotNull ReceiverParameterDescriptor receiverParameterDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(receiverParameterDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            sb.append(receiverParameterDescriptor.getName());
        }

        public void visitConstructorDescriptor(@NotNull ConstructorDescriptor constructorDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(constructorDescriptor, "constructorDescriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderConstructor(constructorDescriptor, sb);
        }

        public void visitTypeParameterDescriptor(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderTypeParameter(typeParameterDescriptor, sb, true);
        }

        public void visitPackageFragmentDescriptor(@NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderPackageFragment(packageFragmentDescriptor, sb);
        }

        public void visitPackageViewDescriptor(@NotNull PackageViewDescriptor packageViewDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(packageViewDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderPackageView(packageViewDescriptor, sb);
        }

        public void visitModuleDeclaration(@NotNull ModuleDescriptor moduleDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(moduleDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderName(moduleDescriptor, sb, true);
        }

        public void visitClassDescriptor(@NotNull ClassDescriptor classDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderClass(classDescriptor, sb);
        }

        public void visitTypeAliasDescriptor(@NotNull TypeAliasDescriptor typeAliasDescriptor, @NotNull StringBuilder sb) {
            Intrinsics.checkParameterIsNotNull(typeAliasDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(sb, "builder");
            DescriptorRendererImpl.this.renderTypeAlias(typeAliasDescriptor, sb);
        }
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RenderingFormat.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[RenderingFormat.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[RenderingFormat.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$3 = new int[RenderingFormat.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$4 = new int[ParameterNameRenderingPolicy.values().length];

        static {
            $EnumSwitchMapping$0[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$0[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$1[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$1[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$2[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$2[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$3[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$3[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$4[ParameterNameRenderingPolicy.ALL.ordinal()] = 1;
            $EnumSwitchMapping$4[ParameterNameRenderingPolicy.ONLY_NON_SYNTHESIZED.ordinal()] = 2;
            $EnumSwitchMapping$4[ParameterNameRenderingPolicy.NONE.ordinal()] = 3;
        }
    }

    private final DescriptorRendererImpl getFunctionTypeAnnotationsRenderer() {
        Lazy lazy = this.functionTypeAnnotationsRenderer$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (DescriptorRendererImpl) lazy.getValue();
    }

    private final DescriptorRenderer getFunctionTypeParameterTypesRenderer() {
        Lazy lazy = this.functionTypeParameterTypesRenderer$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (DescriptorRenderer) lazy.getValue();
    }

    public boolean getAlwaysRenderModifiers() {
        return this.options.getAlwaysRenderModifiers();
    }

    @NotNull
    public AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy() {
        return this.options.getAnnotationArgumentsRenderingPolicy();
    }

    @Nullable
    public Function1<AnnotationDescriptor, Boolean> getAnnotationFilter() {
        return this.options.getAnnotationFilter();
    }

    public boolean getBoldOnlyForNamesInHtml() {
        return this.options.getBoldOnlyForNamesInHtml();
    }

    public boolean getClassWithPrimaryConstructor() {
        return this.options.getClassWithPrimaryConstructor();
    }

    @NotNull
    public ClassifierNamePolicy getClassifierNamePolicy() {
        return this.options.getClassifierNamePolicy();
    }

    public boolean getDebugMode() {
        return this.options.getDebugMode();
    }

    @Nullable
    public Function1<ValueParameterDescriptor, String> getDefaultParameterValueRenderer() {
        return this.options.getDefaultParameterValueRenderer();
    }

    public boolean getEachAnnotationOnNewLine() {
        return this.options.getEachAnnotationOnNewLine();
    }

    public boolean getEnhancedTypes() {
        return this.options.getEnhancedTypes();
    }

    @NotNull
    public Set<FqName> getExcludedAnnotationClasses() {
        return this.options.getExcludedAnnotationClasses();
    }

    @NotNull
    public Set<FqName> getExcludedTypeAnnotationClasses() {
        return this.options.getExcludedTypeAnnotationClasses();
    }

    public boolean getIncludeAdditionalModifiers() {
        return this.options.getIncludeAdditionalModifiers();
    }

    public boolean getIncludeAnnotationArguments() {
        return this.options.getIncludeAnnotationArguments();
    }

    public boolean getIncludeEmptyAnnotationArguments() {
        return this.options.getIncludeEmptyAnnotationArguments();
    }

    public boolean getIncludePropertyConstant() {
        return this.options.getIncludePropertyConstant();
    }

    @NotNull
    public Set<DescriptorRendererModifier> getModifiers() {
        return this.options.getModifiers();
    }

    public boolean getNormalizedVisibilities() {
        return this.options.getNormalizedVisibilities();
    }

    @NotNull
    public OverrideRenderingPolicy getOverrideRenderingPolicy() {
        return this.options.getOverrideRenderingPolicy();
    }

    @NotNull
    public ParameterNameRenderingPolicy getParameterNameRenderingPolicy() {
        return this.options.getParameterNameRenderingPolicy();
    }

    public boolean getParameterNamesInFunctionalTypes() {
        return this.options.getParameterNamesInFunctionalTypes();
    }

    public boolean getPresentableUnresolvedTypes() {
        return this.options.getPresentableUnresolvedTypes();
    }

    public boolean getReceiverAfterName() {
        return this.options.getReceiverAfterName();
    }

    public boolean getRenderAccessors() {
        return this.options.getRenderAccessors();
    }

    public boolean getRenderCompanionObjectName() {
        return this.options.getRenderCompanionObjectName();
    }

    public boolean getRenderConstructorKeyword() {
        return this.options.getRenderConstructorKeyword();
    }

    public boolean getRenderDefaultAnnotationArguments() {
        return this.options.getRenderDefaultAnnotationArguments();
    }

    public boolean getRenderDefaultVisibility() {
        return this.options.getRenderDefaultVisibility();
    }

    public boolean getRenderUnabbreviatedType() {
        return this.options.getRenderUnabbreviatedType();
    }

    public boolean getSecondaryConstructorsAsPrimary() {
        return this.options.getSecondaryConstructorsAsPrimary();
    }

    public boolean getStartFromDeclarationKeyword() {
        return this.options.getStartFromDeclarationKeyword();
    }

    public boolean getStartFromName() {
        return this.options.getStartFromName();
    }

    @NotNull
    public RenderingFormat getTextFormat() {
        return this.options.getTextFormat();
    }

    @NotNull
    public Function1<KotlinType, KotlinType> getTypeNormalizer() {
        return this.options.getTypeNormalizer();
    }

    public boolean getUninferredTypeParameterAsName() {
        return this.options.getUninferredTypeParameterAsName();
    }

    public boolean getUnitReturnType() {
        return this.options.getUnitReturnType();
    }

    @NotNull
    public ValueParametersHandler getValueParametersHandler() {
        return this.options.getValueParametersHandler();
    }

    public boolean getVerbose() {
        return this.options.getVerbose();
    }

    public boolean getWithDefinedIn() {
        return this.options.getWithDefinedIn();
    }

    public boolean getWithSourceFileForTopLevel() {
        return this.options.getWithSourceFileForTopLevel();
    }

    public boolean getWithoutReturnType() {
        return this.options.getWithoutReturnType();
    }

    public boolean getWithoutSuperTypes() {
        return this.options.getWithoutSuperTypes();
    }

    public boolean getWithoutTypeParameters() {
        return this.options.getWithoutTypeParameters();
    }

    public void setAnnotationArgumentsRenderingPolicy(@NotNull AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy) {
        Intrinsics.checkParameterIsNotNull(annotationArgumentsRenderingPolicy, "<set-?>");
        this.options.setAnnotationArgumentsRenderingPolicy(annotationArgumentsRenderingPolicy);
    }

    public void setClassifierNamePolicy(@NotNull ClassifierNamePolicy classifierNamePolicy) {
        Intrinsics.checkParameterIsNotNull(classifierNamePolicy, "<set-?>");
        this.options.setClassifierNamePolicy(classifierNamePolicy);
    }

    public void setDebugMode(boolean z) {
        this.options.setDebugMode(z);
    }

    public void setExcludedTypeAnnotationClasses(@NotNull Set<FqName> set) {
        Intrinsics.checkParameterIsNotNull(set, "<set-?>");
        this.options.setExcludedTypeAnnotationClasses(set);
    }

    public void setModifiers(@NotNull Set<? extends DescriptorRendererModifier> set) {
        Intrinsics.checkParameterIsNotNull(set, "<set-?>");
        this.options.setModifiers(set);
    }

    public void setParameterNameRenderingPolicy(@NotNull ParameterNameRenderingPolicy parameterNameRenderingPolicy) {
        Intrinsics.checkParameterIsNotNull(parameterNameRenderingPolicy, "<set-?>");
        this.options.setParameterNameRenderingPolicy(parameterNameRenderingPolicy);
    }

    public void setReceiverAfterName(boolean z) {
        this.options.setReceiverAfterName(z);
    }

    public void setRenderCompanionObjectName(boolean z) {
        this.options.setRenderCompanionObjectName(z);
    }

    public void setStartFromName(boolean z) {
        this.options.setStartFromName(z);
    }

    public void setTextFormat(@NotNull RenderingFormat renderingFormat) {
        Intrinsics.checkParameterIsNotNull(renderingFormat, "<set-?>");
        this.options.setTextFormat(renderingFormat);
    }

    public void setVerbose(boolean z) {
        this.options.setVerbose(z);
    }

    public void setWithDefinedIn(boolean z) {
        this.options.setWithDefinedIn(z);
    }

    public void setWithoutSuperTypes(boolean z) {
        this.options.setWithoutSuperTypes(z);
    }

    public void setWithoutTypeParameters(boolean z) {
        this.options.setWithoutTypeParameters(z);
    }

    @NotNull
    public final DescriptorRendererOptionsImpl getOptions() {
        return this.options;
    }

    public DescriptorRendererImpl(@NotNull DescriptorRendererOptionsImpl descriptorRendererOptionsImpl) {
        Intrinsics.checkParameterIsNotNull(descriptorRendererOptionsImpl, "options");
        this.options = descriptorRendererOptionsImpl;
        boolean isLocked = this.options.isLocked();
        if (!_Assertions.ENABLED || isLocked) {
            this.functionTypeAnnotationsRenderer$delegate = LazyKt.lazy(new DescriptorRendererImpl$functionTypeAnnotationsRenderer$2(this));
            this.functionTypeParameterTypesRenderer$delegate = LazyKt.lazy(new DescriptorRendererImpl$functionTypeParameterTypesRenderer$2(this));
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    private final String renderKeyword(String str) {
        int i = WhenMappings.$EnumSwitchMapping$0[getTextFormat().ordinal()];
        if (i == 1) {
            return str;
        }
        if (i == 2) {
            if (!getBoldOnlyForNamesInHtml()) {
                StringBuilder sb = new StringBuilder();
                sb.append("<b>");
                sb.append(str);
                sb.append("</b>");
                str = sb.toString();
            }
            return str;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String renderError(String str) {
        int i = WhenMappings.$EnumSwitchMapping$1[getTextFormat().ordinal()];
        if (i == 1) {
            return str;
        }
        if (i == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("<font color=red><b>");
            sb.append(str);
            sb.append("</b></font>");
            return sb.toString();
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String escape(String str) {
        return getTextFormat().escape(str);
    }

    /* renamed from: lt */
    private final String m323lt() {
        return escape("<");
    }

    /* renamed from: gt */
    private final String m322gt() {
        return escape(">");
    }

    private final String arrow() {
        int i = WhenMappings.$EnumSwitchMapping$2[getTextFormat().ordinal()];
        if (i == 1) {
            return escape("->");
        }
        if (i == 2) {
            return "&rarr;";
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public String renderMessage(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        int i = WhenMappings.$EnumSwitchMapping$3[getTextFormat().ordinal()];
        if (i == 1) {
            return str;
        }
        if (i == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("<i>");
            sb.append(str);
            sb.append("</i>");
            return sb.toString();
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public String renderName(@NotNull Name name, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String escape = escape(RenderingUtilsKt.render(name));
        if (!getBoldOnlyForNamesInHtml() || getTextFormat() != RenderingFormat.HTML || !z) {
            return escape;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<b>");
        sb.append(escape);
        sb.append("</b>");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public final void renderName(DeclarationDescriptor declarationDescriptor, StringBuilder sb, boolean z) {
        Name name = declarationDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.name");
        sb.append(renderName(name, z));
    }

    private final void renderCompanionObjectName(DeclarationDescriptor declarationDescriptor, StringBuilder sb) {
        if (getRenderCompanionObjectName()) {
            if (getStartFromName()) {
                sb.append("companion object");
            }
            renderSpaceIfNeeded(sb);
            DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
            if (containingDeclaration != null) {
                sb.append("of ");
                Name name = containingDeclaration.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "containingDeclaration.name");
                sb.append(renderName(name, false));
            }
        }
        if (getVerbose() || (!Intrinsics.areEqual((Object) declarationDescriptor.getName(), (Object) SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT))) {
            if (!getStartFromName()) {
                renderSpaceIfNeeded(sb);
            }
            Name name2 = declarationDescriptor.getName();
            Intrinsics.checkExpressionValueIsNotNull(name2, "descriptor.name");
            sb.append(renderName(name2, true));
        }
    }

    @NotNull
    public String renderFqName(@NotNull FqNameUnsafe fqNameUnsafe) {
        Intrinsics.checkParameterIsNotNull(fqNameUnsafe, "fqName");
        List pathSegments = fqNameUnsafe.pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "fqName.pathSegments()");
        return renderFqName(pathSegments);
    }

    private final String renderFqName(List<Name> list) {
        return escape(RenderingUtilsKt.renderFqName(list));
    }

    @NotNull
    public String renderClassifierName(@NotNull ClassifierDescriptor classifierDescriptor) {
        Intrinsics.checkParameterIsNotNull(classifierDescriptor, "klass");
        if (ErrorUtils.isError(classifierDescriptor)) {
            return classifierDescriptor.getTypeConstructor().toString();
        }
        return getClassifierNamePolicy().renderClassifier(classifierDescriptor, this);
    }

    @NotNull
    public String renderType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        StringBuilder sb = new StringBuilder();
        renderNormalizedType(sb, (KotlinType) getTypeNormalizer().invoke(kotlinType));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private final void renderNormalizedType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof AbbreviatedType)) {
            unwrap = null;
        }
        AbbreviatedType abbreviatedType = (AbbreviatedType) unwrap;
        if (abbreviatedType != null) {
            renderNormalizedTypeAsIs(sb, abbreviatedType.getAbbreviation());
            if (getRenderUnabbreviatedType()) {
                renderAbbreviatedTypeExpansion(sb, abbreviatedType);
            }
            return;
        }
        renderNormalizedTypeAsIs(sb, kotlinType);
    }

    private final void renderAbbreviatedTypeExpansion(@NotNull StringBuilder sb, AbbreviatedType abbreviatedType) {
        if (getTextFormat() == RenderingFormat.HTML) {
            sb.append("<font color=\"808080\"><i>");
        }
        sb.append(" /* = ");
        renderNormalizedTypeAsIs(sb, abbreviatedType.getExpandedType());
        sb.append(" */");
        if (getTextFormat() == RenderingFormat.HTML) {
            sb.append("</i></font>");
        }
    }

    private final void renderNormalizedTypeAsIs(@NotNull StringBuilder sb, KotlinType kotlinType) {
        if (!(kotlinType instanceof WrappedType) || !getDebugMode() || ((WrappedType) kotlinType).isComputed()) {
            UnwrappedType unwrap = kotlinType.unwrap();
            if (unwrap instanceof FlexibleType) {
                sb.append(((FlexibleType) unwrap).render(this, this));
            } else if (unwrap instanceof SimpleType) {
                renderSimpleType(sb, (SimpleType) unwrap);
            }
            return;
        }
        sb.append("<Not computed yet>");
    }

    private final void renderSimpleType(@NotNull StringBuilder sb, SimpleType simpleType) {
        String str = "???";
        if (!Intrinsics.areEqual((Object) simpleType, (Object) TypeUtils.CANT_INFER_FUNCTION_PARAM_TYPE)) {
            KotlinType kotlinType = simpleType;
            if (!TypeUtils.isDontCarePlaceholder(kotlinType)) {
                if (ErrorUtils.isUninferredParameter(kotlinType)) {
                    if (getUninferredTypeParameterAsName()) {
                        TypeConstructor constructor = simpleType.getConstructor();
                        if (constructor != null) {
                            TypeParameterDescriptor typeParameterDescriptor = ((UninferredParameterTypeConstructor) constructor).getTypeParameterDescriptor();
                            Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "(type.constructor as Uni…).typeParameterDescriptor");
                            String name = typeParameterDescriptor.getName().toString();
                            Intrinsics.checkExpressionValueIsNotNull(name, "(type.constructor as Uni…escriptor.name.toString()");
                            sb.append(renderError(name));
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.ErrorUtils.UninferredParameterTypeConstructor");
                        }
                    } else {
                        sb.append(str);
                    }
                    return;
                } else if (KotlinTypeKt.isError(kotlinType)) {
                    renderDefaultType(sb, kotlinType);
                    return;
                } else {
                    if (shouldRenderAsPrettyFunctionType(kotlinType)) {
                        renderFunctionType(sb, kotlinType);
                    } else {
                        renderDefaultType(sb, kotlinType);
                    }
                    return;
                }
            }
        }
        sb.append(str);
    }

    private final boolean shouldRenderAsPrettyFunctionType(KotlinType kotlinType) {
        boolean z;
        if (!FunctionTypesKt.isBuiltinFunctionalType(kotlinType)) {
            return false;
        }
        Iterable arguments = kotlinType.getArguments();
        if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
            Iterator it = arguments.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((TypeProjection) it.next()).isStarProjection()) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = true;
        if (z) {
            return true;
        }
        return false;
    }

    @NotNull
    public String renderFlexibleType(@NotNull String str, @NotNull String str2, @NotNull KotlinBuiltIns kotlinBuiltIns) {
        String str3 = str;
        String str4 = str2;
        Intrinsics.checkParameterIsNotNull(str3, "lowerRendered");
        Intrinsics.checkParameterIsNotNull(str4, "upperRendered");
        Intrinsics.checkParameterIsNotNull(kotlinBuiltIns, "builtIns");
        if (!differsOnlyInNullability(str, str2)) {
            ClassifierNamePolicy classifierNamePolicy = getClassifierNamePolicy();
            ClassDescriptor collection = kotlinBuiltIns.getCollection();
            Intrinsics.checkExpressionValueIsNotNull(collection, "builtIns.collection");
            DescriptorRenderer descriptorRenderer = this;
            String substringBefore$default = StringsKt.substringBefore$default(classifierNamePolicy.renderClassifier(collection, descriptorRenderer), "Collection", (String) null, 2, (Object) null);
            String str5 = "Mutable";
            StringBuilder sb = new StringBuilder();
            sb.append(substringBefore$default);
            sb.append(str5);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(substringBefore$default);
            sb3.append('(');
            sb3.append(str5);
            sb3.append(')');
            String replacePrefixes = replacePrefixes(str, sb2, str2, substringBefore$default, sb3.toString());
            if (replacePrefixes != null) {
                return replacePrefixes;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append(substringBefore$default);
            sb4.append("MutableMap.MutableEntry");
            String sb5 = sb4.toString();
            StringBuilder sb6 = new StringBuilder();
            sb6.append(substringBefore$default);
            sb6.append("Map.Entry");
            String sb7 = sb6.toString();
            StringBuilder sb8 = new StringBuilder();
            sb8.append(substringBefore$default);
            sb8.append("(Mutable)Map.(Mutable)Entry");
            String replacePrefixes2 = replacePrefixes(str, sb5, str2, sb7, sb8.toString());
            if (replacePrefixes2 != null) {
                return replacePrefixes2;
            }
            ClassifierNamePolicy classifierNamePolicy2 = getClassifierNamePolicy();
            ClassDescriptor array = kotlinBuiltIns.getArray();
            Intrinsics.checkExpressionValueIsNotNull(array, "builtIns.array");
            String substringBefore$default2 = StringsKt.substringBefore$default(classifierNamePolicy2.renderClassifier(array, descriptorRenderer), "Array", (String) null, 2, (Object) null);
            StringBuilder sb9 = new StringBuilder();
            sb9.append(substringBefore$default2);
            sb9.append(escape("Array<"));
            String sb10 = sb9.toString();
            StringBuilder sb11 = new StringBuilder();
            sb11.append(substringBefore$default2);
            sb11.append(escape("Array<out "));
            String sb12 = sb11.toString();
            StringBuilder sb13 = new StringBuilder();
            sb13.append(substringBefore$default2);
            sb13.append(escape("Array<(out) "));
            String replacePrefixes3 = replacePrefixes(str, sb10, str2, sb12, sb13.toString());
            if (replacePrefixes3 != null) {
                return replacePrefixes3;
            }
            StringBuilder sb14 = new StringBuilder();
            sb14.append('(');
            sb14.append(str3);
            sb14.append("..");
            sb14.append(str4);
            sb14.append(')');
            return sb14.toString();
        } else if (StringsKt.startsWith$default(str4, "(", false, 2, null)) {
            StringBuilder sb15 = new StringBuilder();
            sb15.append('(');
            sb15.append(str3);
            sb15.append(")!");
            return sb15.toString();
        } else {
            StringBuilder sb16 = new StringBuilder();
            sb16.append(str3);
            sb16.append("!");
            return sb16.toString();
        }
    }

    @NotNull
    public String renderTypeArguments(@NotNull List<? extends TypeProjection> list) {
        Intrinsics.checkParameterIsNotNull(list, "typeArguments");
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(m323lt());
        appendTypeProjections(sb, list);
        sb.append(m322gt());
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private final void renderDefaultType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        renderAnnotations(sb, kotlinType);
        if (KotlinTypeKt.isError(kotlinType)) {
            if (!(kotlinType instanceof UnresolvedType) || !getPresentableUnresolvedTypes()) {
                sb.append(kotlinType.getConstructor().toString());
            } else {
                sb.append(((UnresolvedType) kotlinType).getPresentableName());
            }
            sb.append(renderTypeArguments(kotlinType.getArguments()));
        } else {
            renderTypeConstructorAndArguments$default(this, sb, kotlinType, null, 2, null);
        }
        if (kotlinType.isMarkedNullable()) {
            sb.append("?");
        }
        if (SpecialTypesKt.isDefinitelyNotNullType(kotlinType)) {
            sb.append("!!");
        }
    }

    static /* bridge */ /* synthetic */ void renderTypeConstructorAndArguments$default(DescriptorRendererImpl descriptorRendererImpl, StringBuilder sb, KotlinType kotlinType, TypeConstructor typeConstructor, int i, Object obj) {
        if ((i & 2) != 0) {
            typeConstructor = kotlinType.getConstructor();
        }
        descriptorRendererImpl.renderTypeConstructorAndArguments(sb, kotlinType, typeConstructor);
    }

    private final void renderTypeConstructorAndArguments(@NotNull StringBuilder sb, KotlinType kotlinType, TypeConstructor typeConstructor) {
        PossiblyInnerType buildPossiblyInnerType = TypeParameterUtilsKt.buildPossiblyInnerType(kotlinType);
        if (buildPossiblyInnerType == null) {
            sb.append(renderTypeConstructor(typeConstructor));
            sb.append(renderTypeArguments(kotlinType.getArguments()));
            return;
        }
        renderPossiblyInnerType(sb, buildPossiblyInnerType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0023, code lost:
        if (r3 != null) goto L_0x003a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void renderPossiblyInnerType(@org.jetbrains.annotations.NotNull java.lang.StringBuilder r3, kotlin.reflect.jvm.internal.impl.descriptors.PossiblyInnerType r4) {
        /*
            r2 = this;
            kotlin.reflect.jvm.internal.impl.descriptors.PossiblyInnerType r0 = r4.getOuterType()
            if (r0 == 0) goto L_0x0026
            r2.renderPossiblyInnerType(r3, r0)
            r0 = 46
            r3.append(r0)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters r0 = r4.getClassifierDescriptor()
            kotlin.reflect.jvm.internal.impl.name.Name r0 = r0.getName()
            java.lang.String r1 = "possiblyInnerType.classifierDescriptor.name"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r1 = 0
            java.lang.String r0 = r2.renderName(r0, r1)
            r3.append(r0)
            if (r3 == 0) goto L_0x0026
            goto L_0x003a
        L_0x0026:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters r0 = r4.getClassifierDescriptor()
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = r0.getTypeConstructor()
            java.lang.String r1 = "possiblyInnerType.classi…escriptor.typeConstructor"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r0 = r2.renderTypeConstructor(r0)
            r3.append(r0)
        L_0x003a:
            java.util.List r4 = r4.getArguments()
            java.lang.String r4 = r2.renderTypeArguments(r4)
            r3.append(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderPossiblyInnerType(java.lang.StringBuilder, kotlin.reflect.jvm.internal.impl.descriptors.PossiblyInnerType):void");
    }

    @NotNull
    public String renderTypeConstructor(@NotNull TypeConstructor typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "typeConstructor");
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if ((declarationDescriptor instanceof TypeParameterDescriptor) || (declarationDescriptor instanceof ClassDescriptor) || (declarationDescriptor instanceof TypeAliasDescriptor)) {
            return renderClassifierName(declarationDescriptor);
        }
        if (declarationDescriptor == null) {
            return typeConstructor.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected classifier: ");
        sb.append(declarationDescriptor.getClass());
        throw new IllegalStateException(sb.toString().toString());
    }

    @NotNull
    public String renderTypeProjection(@NotNull TypeProjection typeProjection) {
        Intrinsics.checkParameterIsNotNull(typeProjection, "typeProjection");
        StringBuilder sb = new StringBuilder();
        appendTypeProjections(sb, CollectionsKt.listOf(typeProjection));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private final void appendTypeProjections(@NotNull StringBuilder sb, List<? extends TypeProjection> list) {
        CollectionsKt.joinTo$default(list, sb, ", ", null, null, 0, null, new DescriptorRendererImpl$appendTypeProjections$1(this), 60, null);
    }

    private final void renderFunctionType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        Name name;
        int length = sb.length();
        getFunctionTypeAnnotationsRenderer().renderAnnotations(sb, kotlinType);
        boolean z = true;
        boolean z2 = sb.length() != length;
        boolean isSuspendFunctionType = FunctionTypesKt.isSuspendFunctionType(kotlinType);
        boolean isMarkedNullable = kotlinType.isMarkedNullable();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        boolean z3 = isMarkedNullable || (z2 && receiverTypeFromFunctionType != null);
        String str = "(";
        if (z3) {
            if (isSuspendFunctionType) {
                sb.insert(length, '(');
            } else {
                if (z2) {
                    CharSequence charSequence = sb;
                    boolean z4 = StringsKt.last(charSequence) == ' ';
                    if (_Assertions.ENABLED && !z4) {
                        throw new AssertionError("Assertion failed");
                    } else if (sb.charAt(StringsKt.getLastIndex(charSequence) - 1) != ')') {
                        sb.insert(StringsKt.getLastIndex(charSequence), "()");
                    }
                }
                sb.append(str);
            }
        }
        renderModifier(sb, isSuspendFunctionType, "suspend");
        String str2 = ")";
        if (receiverTypeFromFunctionType != null) {
            if ((!shouldRenderAsPrettyFunctionType(receiverTypeFromFunctionType) || receiverTypeFromFunctionType.isMarkedNullable()) && !hasModifiersOrAnnotations(receiverTypeFromFunctionType)) {
                z = false;
            }
            if (z) {
                sb.append(str);
            }
            renderNormalizedType(sb, receiverTypeFromFunctionType);
            if (z) {
                sb.append(str2);
            }
            sb.append(".");
        }
        sb.append(str);
        int i = 0;
        for (TypeProjection typeProjection : FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType)) {
            if (i > 0) {
                sb.append(", ");
            }
            if (getParameterNamesInFunctionalTypes()) {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "typeProjection.type");
                name = FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type);
            } else {
                name = null;
            }
            if (name != null) {
                sb.append(renderName(name, false));
                sb.append(": ");
            }
            sb.append(getFunctionTypeParameterTypesRenderer().renderTypeProjection(typeProjection));
            i++;
        }
        sb.append(") ");
        sb.append(arrow());
        sb.append(" ");
        renderNormalizedType(sb, FunctionTypesKt.getReturnTypeFromFunctionType(kotlinType));
        if (z3) {
            sb.append(str2);
        }
        if (isMarkedNullable) {
            sb.append("?");
        }
    }

    private final boolean hasModifiersOrAnnotations(@NotNull KotlinType kotlinType) {
        return FunctionTypesKt.isSuspendFunctionType(kotlinType) || !kotlinType.getAnnotations().isEmpty();
    }

    private final void appendDefinedIn(@NotNull StringBuilder sb, DeclarationDescriptor declarationDescriptor) {
        if (!(declarationDescriptor instanceof PackageFragmentDescriptor) && !(declarationDescriptor instanceof PackageViewDescriptor)) {
            if (declarationDescriptor instanceof ModuleDescriptor) {
                sb.append(" is a module");
                return;
            }
            DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
            if (containingDeclaration != null && !(containingDeclaration instanceof ModuleDescriptor)) {
                String str = " ";
                sb.append(str);
                sb.append(renderMessage("defined in"));
                sb.append(str);
                FqNameUnsafe fqName = DescriptorUtils.getFqName(containingDeclaration);
                Intrinsics.checkExpressionValueIsNotNull(fqName, "fqName");
                sb.append(fqName.isRoot() ? "root package" : renderFqName(fqName));
                if (getWithSourceFileForTopLevel() && (containingDeclaration instanceof PackageFragmentDescriptor) && (declarationDescriptor instanceof DeclarationDescriptorWithSource)) {
                    SourceElement source = ((DeclarationDescriptorWithSource) declarationDescriptor).getSource();
                    Intrinsics.checkExpressionValueIsNotNull(source, "descriptor.source");
                    SourceFile containingFile = source.getContainingFile();
                    Intrinsics.checkExpressionValueIsNotNull(containingFile, "descriptor.source.containingFile");
                    String name = containingFile.getName();
                    if (name != null) {
                        sb.append(str);
                        sb.append(renderMessage("in file"));
                        sb.append(str);
                        sb.append(name);
                    }
                }
            }
        }
    }

    private final void renderAnnotations(@NotNull StringBuilder sb, Annotated annotated) {
        if (getModifiers().contains(DescriptorRendererModifier.ANNOTATIONS)) {
            Set excludedTypeAnnotationClasses = annotated instanceof KotlinType ? getExcludedTypeAnnotationClasses() : getExcludedAnnotationClasses();
            Function1 annotationFilter = getAnnotationFilter();
            for (AnnotationWithTarget annotationWithTarget : annotated.getAnnotations().getAllAnnotations()) {
                AnnotationDescriptor component1 = annotationWithTarget.component1();
                AnnotationUseSiteTarget component2 = annotationWithTarget.component2();
                if (!CollectionsKt.contains(excludedTypeAnnotationClasses, component1.getFqName()) && (annotationFilter == null || ((Boolean) annotationFilter.invoke(component1)).booleanValue())) {
                    sb.append(renderAnnotation(component1, component2));
                    if (getEachAnnotationOnNewLine()) {
                        StringsKt.appendln(sb);
                    } else {
                        sb.append(" ");
                    }
                }
            }
        }
    }

    @NotNull
    public String renderAnnotation(@NotNull AnnotationDescriptor annotationDescriptor, @Nullable AnnotationUseSiteTarget annotationUseSiteTarget) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotation");
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        if (annotationUseSiteTarget != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(annotationUseSiteTarget.getRenderName());
            sb2.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(sb2.toString());
        }
        KotlinType type = annotationDescriptor.getType();
        sb.append(renderType(type));
        if (getIncludeAnnotationArguments()) {
            List renderAndSortAnnotationArguments = renderAndSortAnnotationArguments(annotationDescriptor);
            if (getIncludeEmptyAnnotationArguments() || (!renderAndSortAnnotationArguments.isEmpty())) {
                CollectionsKt.joinTo$default(renderAndSortAnnotationArguments, sb, ", ", "(", ")", 0, null, null, 112, null);
            }
        }
        if (getVerbose() && (KotlinTypeKt.isError(type) || (type.getConstructor().getDeclarationDescriptor() instanceof MockClassDescriptor))) {
            sb.append(" /* annotation class not found */");
        }
        String sb3 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb3, "StringBuilder().apply(builderAction).toString()");
        return sb3;
    }

    private final List<String> renderAndSortAnnotationArguments(AnnotationDescriptor annotationDescriptor) {
        Map allValueArguments = annotationDescriptor.getAllValueArguments();
        List list = null;
        ClassDescriptor annotationClass = getRenderDefaultAnnotationArguments() ? DescriptorUtilsKt.getAnnotationClass(annotationDescriptor) : null;
        if (annotationClass != null) {
            ClassConstructorDescriptor unsubstitutedPrimaryConstructor = annotationClass.getUnsubstitutedPrimaryConstructor();
            if (unsubstitutedPrimaryConstructor != null) {
                List valueParameters = unsubstitutedPrimaryConstructor.getValueParameters();
                if (valueParameters != null) {
                    Iterable iterable = valueParameters;
                    Collection arrayList = new ArrayList();
                    for (Object next : iterable) {
                        if (((ValueParameterDescriptor) next).declaresDefaultValue()) {
                            arrayList.add(next);
                        }
                    }
                    Iterable<ValueParameterDescriptor> iterable2 = (List) arrayList;
                    Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                    for (ValueParameterDescriptor valueParameterDescriptor : iterable2) {
                        Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it");
                        arrayList2.add(valueParameterDescriptor.getName());
                    }
                    list = (List) arrayList2;
                }
            }
        }
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Iterable iterable3 = list;
        Collection arrayList3 = new ArrayList();
        for (Object next2 : iterable3) {
            if (!allValueArguments.containsKey((Name) next2)) {
                arrayList3.add(next2);
            }
        }
        Iterable<Name> iterable4 = (List) arrayList3;
        Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
        for (Name name : iterable4) {
            StringBuilder sb = new StringBuilder();
            sb.append(name.asString());
            sb.append(" = ...");
            arrayList4.add(sb.toString());
        }
        List list2 = (List) arrayList4;
        Iterable<Entry> entrySet = allValueArguments.entrySet();
        Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entrySet, 10));
        for (Entry entry : entrySet) {
            Name name2 = (Name) entry.getKey();
            ConstantValue constantValue = (ConstantValue) entry.getValue();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(name2.asString());
            sb2.append(" = ");
            sb2.append(!list.contains(name2) ? renderConstant(constantValue) : "...");
            arrayList5.add(sb2.toString());
        }
        return CollectionsKt.sorted(CollectionsKt.plus((Collection<? extends T>) list2, (Iterable<? extends T>) (List) arrayList5));
    }

    /* access modifiers changed from: private */
    public final String renderConstant(ConstantValue<?> constantValue) {
        if (constantValue instanceof ArrayValue) {
            return CollectionsKt.joinToString$default((Iterable) ((ArrayValue) constantValue).getValue(), ", ", "{", "}", 0, null, new DescriptorRendererImpl$renderConstant$1(this), 24, null);
        }
        if (constantValue instanceof AnnotationValue) {
            return StringsKt.removePrefix(DescriptorRenderer.renderAnnotation$default(this, (AnnotationDescriptor) ((AnnotationValue) constantValue).getValue(), null, 2, null), (CharSequence) "@");
        }
        if (!(constantValue instanceof KClassValue)) {
            return constantValue.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(renderType(((KClassValue) constantValue).getValue()));
        sb.append("::class");
        return sb.toString();
    }

    private final void renderVisibility(Visibility visibility, StringBuilder sb) {
        if (getModifiers().contains(DescriptorRendererModifier.VISIBILITY)) {
            if (getNormalizedVisibilities()) {
                visibility = visibility.normalize();
            }
            if (getRenderDefaultVisibility() || !Intrinsics.areEqual((Object) visibility, (Object) Visibilities.DEFAULT_VISIBILITY)) {
                sb.append(renderKeyword(visibility.getDisplayName()));
                sb.append(" ");
            }
        }
    }

    private final void renderModality(Modality modality, StringBuilder sb) {
        boolean contains = getModifiers().contains(DescriptorRendererModifier.MODALITY);
        String name = modality.name();
        if (name != null) {
            String lowerCase = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            renderModifier(sb, contains, lowerCase);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void renderModalityForCallable(CallableMemberDescriptor callableMemberDescriptor, StringBuilder sb) {
        if ((!DescriptorUtils.isTopLevelDeclaration(callableMemberDescriptor) || callableMemberDescriptor.getModality() != Modality.FINAL) && !(getOverrideRenderingPolicy() == OverrideRenderingPolicy.RENDER_OVERRIDE && callableMemberDescriptor.getModality() == Modality.OPEN && overridesSomething(callableMemberDescriptor))) {
            Modality modality = callableMemberDescriptor.getModality();
            Intrinsics.checkExpressionValueIsNotNull(modality, "callable.modality");
            renderModality(modality, sb);
        }
    }

    private final void renderOverride(CallableMemberDescriptor callableMemberDescriptor, StringBuilder sb) {
        if (getModifiers().contains(DescriptorRendererModifier.OVERRIDE) && overridesSomething(callableMemberDescriptor) && getOverrideRenderingPolicy() != OverrideRenderingPolicy.RENDER_OPEN) {
            renderModifier(sb, true, "override");
            if (getVerbose()) {
                sb.append("/*");
                sb.append(callableMemberDescriptor.getOverriddenDescriptors().size());
                sb.append("*/ ");
            }
        }
    }

    private final void renderMemberKind(CallableMemberDescriptor callableMemberDescriptor, StringBuilder sb) {
        if (getModifiers().contains(DescriptorRendererModifier.MEMBER_KIND) && getVerbose() && callableMemberDescriptor.getKind() != Kind.DECLARATION) {
            sb.append("/*");
            String name = callableMemberDescriptor.getKind().name();
            if (name != null) {
                String lowerCase = name.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                sb.append(lowerCase);
                sb.append("*/ ");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
    }

    private final void renderModifier(StringBuilder sb, boolean z, String str) {
        if (z) {
            sb.append(renderKeyword(str));
            sb.append(" ");
        }
    }

    private final void renderMemberModifiers(MemberDescriptor memberDescriptor, StringBuilder sb) {
        renderModifier(sb, memberDescriptor.isExternal(), "external");
        boolean z = true;
        renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.EXPECT) && memberDescriptor.isExpect(), "expect");
        if (!getModifiers().contains(DescriptorRendererModifier.ACTUAL) || !memberDescriptor.isActual()) {
            z = false;
        }
        renderModifier(sb, z, "actual");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void renderAdditionalModifiers(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r7, java.lang.StringBuilder r8) {
        /*
            r6 = this;
            boolean r0 = r7.isOperator()
            java.lang.String r1 = "it"
            java.lang.String r2 = "functionDescriptor.overriddenDescriptors"
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L_0x0048
            java.util.Collection r0 = r7.getOverriddenDescriptors()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r5 = r0 instanceof java.util.Collection
            if (r5 == 0) goto L_0x0024
            r5 = r0
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0024
        L_0x0022:
            r0 = 1
            goto L_0x003e
        L_0x0024:
            java.util.Iterator r0 = r0.iterator()
        L_0x0028:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0022
            java.lang.Object r5 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r5
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r1)
            boolean r5 = r5.isOperator()
            if (r5 == 0) goto L_0x0028
            r0 = 0
        L_0x003e:
            if (r0 != 0) goto L_0x0046
            boolean r0 = r6.getAlwaysRenderModifiers()
            if (r0 == 0) goto L_0x0048
        L_0x0046:
            r0 = 1
            goto L_0x0049
        L_0x0048:
            r0 = 0
        L_0x0049:
            boolean r5 = r7.isInfix()
            if (r5 == 0) goto L_0x008a
            java.util.Collection r5 = r7.getOverriddenDescriptors()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r2)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r2 = r5 instanceof java.util.Collection
            if (r2 == 0) goto L_0x0067
            r2 = r5
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0067
        L_0x0065:
            r1 = 1
            goto L_0x0081
        L_0x0067:
            java.util.Iterator r2 = r5.iterator()
        L_0x006b:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0065
            java.lang.Object r5 = r2.next()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r5
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r1)
            boolean r5 = r5.isInfix()
            if (r5 == 0) goto L_0x006b
            r1 = 0
        L_0x0081:
            if (r1 != 0) goto L_0x0089
            boolean r1 = r6.getAlwaysRenderModifiers()
            if (r1 == 0) goto L_0x008a
        L_0x0089:
            r3 = 1
        L_0x008a:
            boolean r1 = r7.isTailrec()
            java.lang.String r2 = "tailrec"
            r6.renderModifier(r8, r1, r2)
            r6.renderSuspendModifier(r7, r8)
            boolean r7 = r7.isInline()
            java.lang.String r1 = "inline"
            r6.renderModifier(r8, r7, r1)
            java.lang.String r7 = "infix"
            r6.renderModifier(r8, r3, r7)
            java.lang.String r7 = "operator"
            r6.renderModifier(r8, r0, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderAdditionalModifiers(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, java.lang.StringBuilder):void");
    }

    private final void renderSuspendModifier(FunctionDescriptor functionDescriptor, StringBuilder sb) {
        renderModifier(sb, functionDescriptor.isSuspend(), "suspend");
    }

    @NotNull
    public String render(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "declarationDescriptor");
        StringBuilder sb = new StringBuilder();
        declarationDescriptor.accept(new RenderDeclarationDescriptorVisitor(), sb);
        if (getWithDefinedIn()) {
            appendDefinedIn(sb, declarationDescriptor);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* access modifiers changed from: private */
    public final void renderTypeParameter(TypeParameterDescriptor typeParameterDescriptor, StringBuilder sb, boolean z) {
        if (z) {
            sb.append(m323lt());
        }
        if (getVerbose()) {
            sb.append("/*");
            sb.append(typeParameterDescriptor.getIndex());
            sb.append("*/ ");
        }
        renderModifier(sb, typeParameterDescriptor.isReified(), "reified");
        String label = typeParameterDescriptor.getVariance().getLabel();
        boolean z2 = true;
        renderModifier(sb, label.length() > 0, label);
        renderAnnotations(sb, typeParameterDescriptor);
        renderName(typeParameterDescriptor, sb, z);
        int size = typeParameterDescriptor.getUpperBounds().size();
        String str = "upperBound";
        String str2 = " : ";
        if ((size > 1 && !z) || size == 1) {
            KotlinType kotlinType = (KotlinType) typeParameterDescriptor.getUpperBounds().iterator().next();
            if (!KotlinBuiltIns.isDefaultBound(kotlinType)) {
                sb.append(str2);
                Intrinsics.checkExpressionValueIsNotNull(kotlinType, str);
                sb.append(renderType(kotlinType));
            }
        } else if (z) {
            for (KotlinType kotlinType2 : typeParameterDescriptor.getUpperBounds()) {
                if (!KotlinBuiltIns.isDefaultBound(kotlinType2)) {
                    if (z2) {
                        sb.append(str2);
                    } else {
                        sb.append(" & ");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(kotlinType2, str);
                    sb.append(renderType(kotlinType2));
                    z2 = false;
                }
            }
        }
        if (z) {
            sb.append(m322gt());
        }
    }

    private final void renderTypeParameters(List<? extends TypeParameterDescriptor> list, StringBuilder sb, boolean z) {
        if (!getWithoutTypeParameters() && !list.isEmpty()) {
            sb.append(m323lt());
            renderTypeParameterList(sb, list);
            sb.append(m322gt());
            if (z) {
                sb.append(" ");
            }
        }
    }

    private final void renderTypeParameterList(StringBuilder sb, List<? extends TypeParameterDescriptor> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            renderTypeParameter((TypeParameterDescriptor) it.next(), sb, false);
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
    }

    /* access modifiers changed from: private */
    public final void renderFunction(FunctionDescriptor functionDescriptor, StringBuilder sb) {
        String str = "function.typeParameters";
        if (!getStartFromName()) {
            if (!getStartFromDeclarationKeyword()) {
                renderAnnotations(sb, functionDescriptor);
                Visibility visibility = functionDescriptor.getVisibility();
                Intrinsics.checkExpressionValueIsNotNull(visibility, "function.visibility");
                renderVisibility(visibility, sb);
                CallableMemberDescriptor callableMemberDescriptor = functionDescriptor;
                renderModalityForCallable(callableMemberDescriptor, sb);
                if (getIncludeAdditionalModifiers()) {
                    renderMemberModifiers(functionDescriptor, sb);
                }
                renderOverride(callableMemberDescriptor, sb);
                if (getIncludeAdditionalModifiers()) {
                    renderAdditionalModifiers(functionDescriptor, sb);
                } else {
                    renderSuspendModifier(functionDescriptor, sb);
                }
                renderMemberKind(callableMemberDescriptor, sb);
                if (getVerbose()) {
                    if (functionDescriptor.isHiddenToOvercomeSignatureClash()) {
                        sb.append("/*isHiddenToOvercomeSignatureClash*/ ");
                    }
                    if (functionDescriptor.isHiddenForResolutionEverywhereBesideSupercalls()) {
                        sb.append("/*isHiddenForResolutionEverywhereBesideSupercalls*/ ");
                    }
                }
            }
            sb.append(renderKeyword("fun"));
            sb.append(" ");
            List typeParameters = functionDescriptor.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, str);
            renderTypeParameters(typeParameters, sb, true);
            renderReceiver(functionDescriptor, sb);
        }
        renderName(functionDescriptor, sb, true);
        List valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "function.valueParameters");
        renderValueParameters(valueParameters, functionDescriptor.hasSynthesizedParameterNames(), sb);
        renderReceiverAfterName(functionDescriptor, sb);
        KotlinType returnType = functionDescriptor.getReturnType();
        if (!getWithoutReturnType() && (getUnitReturnType() || returnType == null || !KotlinBuiltIns.isUnit(returnType))) {
            sb.append(": ");
            sb.append(returnType == null ? "[NULL]" : renderType(returnType));
        }
        List typeParameters2 = functionDescriptor.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters2, str);
        renderWhereSuffix(typeParameters2, sb);
    }

    private final void renderReceiverAfterName(CallableDescriptor callableDescriptor, StringBuilder sb) {
        if (getReceiverAfterName()) {
            ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
            if (extensionReceiverParameter != null) {
                sb.append(" on ");
                KotlinType type = extensionReceiverParameter.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "receiver.type");
                sb.append(renderType(type));
            }
        }
    }

    private final void renderReceiver(CallableDescriptor callableDescriptor, StringBuilder sb) {
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        if (extensionReceiverParameter != null) {
            KotlinType type = extensionReceiverParameter.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "type");
            String renderType = renderType(type);
            if (shouldRenderAsPrettyFunctionType(type) && !TypeUtils.isNullableType(type)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append('(');
                sb2.append(renderType);
                sb2.append(')');
                renderType = sb2.toString();
            }
            sb.append(renderType);
            sb.append(".");
        }
    }

    /* access modifiers changed from: private */
    public final void renderConstructor(ConstructorDescriptor constructorDescriptor, StringBuilder sb) {
        renderAnnotations(sb, constructorDescriptor);
        Visibility visibility = constructorDescriptor.getVisibility();
        Intrinsics.checkExpressionValueIsNotNull(visibility, "constructor.visibility");
        renderVisibility(visibility, sb);
        renderMemberKind(constructorDescriptor, sb);
        if (getRenderConstructorKeyword()) {
            sb.append(renderKeyword("constructor"));
        }
        String str = "constructor.typeParameters";
        if (getSecondaryConstructorsAsPrimary()) {
            ClassifierDescriptorWithTypeParameters containingDeclaration = constructorDescriptor.getContainingDeclaration();
            if (getRenderConstructorKeyword()) {
                sb.append(" ");
            }
            Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "classDescriptor");
            renderName(containingDeclaration, sb, true);
            List typeParameters = constructorDescriptor.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, str);
            renderTypeParameters(typeParameters, sb, false);
        }
        List valueParameters = constructorDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "constructor.valueParameters");
        renderValueParameters(valueParameters, constructorDescriptor.hasSynthesizedParameterNames(), sb);
        if (getSecondaryConstructorsAsPrimary()) {
            List typeParameters2 = constructorDescriptor.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters2, str);
            renderWhereSuffix(typeParameters2, sb);
        }
    }

    private final void renderWhereSuffix(List<? extends TypeParameterDescriptor> list, StringBuilder sb) {
        if (!getWithoutTypeParameters()) {
            ArrayList arrayList = new ArrayList(0);
            for (TypeParameterDescriptor typeParameterDescriptor : list) {
                List upperBounds = typeParameterDescriptor.getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds, "typeParameter.upperBounds");
                for (KotlinType kotlinType : CollectionsKt.drop(upperBounds, 1)) {
                    Collection collection = arrayList;
                    StringBuilder sb2 = new StringBuilder();
                    Name name = typeParameterDescriptor.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "typeParameter.name");
                    sb2.append(renderName(name, false));
                    sb2.append(" : ");
                    Intrinsics.checkExpressionValueIsNotNull(kotlinType, "it");
                    sb2.append(renderType(kotlinType));
                    collection.add(sb2.toString());
                }
                Collection collection2 = arrayList;
            }
            if (!arrayList.isEmpty()) {
                String str = " ";
                sb.append(str);
                sb.append(renderKeyword("where"));
                sb.append(str);
                CollectionsKt.joinTo$default(arrayList, sb, ", ", null, null, 0, null, null, 124, null);
            }
        }
    }

    private final void renderValueParameters(Collection<? extends ValueParameterDescriptor> collection, boolean z, StringBuilder sb) {
        boolean shouldRenderParameterNames = shouldRenderParameterNames(z);
        int size = collection.size();
        getValueParametersHandler().appendBeforeValueParameters(size, sb);
        int i = 0;
        for (ValueParameterDescriptor valueParameterDescriptor : collection) {
            getValueParametersHandler().appendBeforeValueParameter(valueParameterDescriptor, i, size, sb);
            renderValueParameter(valueParameterDescriptor, shouldRenderParameterNames, sb, false);
            getValueParametersHandler().appendAfterValueParameter(valueParameterDescriptor, i, size, sb);
            i++;
        }
        getValueParametersHandler().appendAfterValueParameters(size, sb);
    }

    private final boolean shouldRenderParameterNames(boolean z) {
        int i = WhenMappings.$EnumSwitchMapping$4[getParameterNameRenderingPolicy().ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return !z;
        }
        if (i == 3) {
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void renderValueParameter(kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r3, boolean r4, java.lang.StringBuilder r5, boolean r6) {
        /*
            r2 = this;
            if (r6 == 0) goto L_0x0010
            java.lang.String r0 = "value-parameter"
            java.lang.String r0 = r2.renderKeyword(r0)
            r5.append(r0)
            java.lang.String r0 = " "
            r5.append(r0)
        L_0x0010:
            boolean r0 = r2.getVerbose()
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = "/*"
            r5.append(r0)
            int r0 = r3.getIndex()
            r5.append(r0)
            java.lang.String r0 = "*/ "
            r5.append(r0)
        L_0x0027:
            r0 = r3
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated r0 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated) r0
            r2.renderAnnotations(r5, r0)
            boolean r0 = r3.isCrossinline()
            java.lang.String r1 = "crossinline"
            r2.renderModifier(r5, r0, r1)
            boolean r0 = r3.isNoinline()
            java.lang.String r1 = "noinline"
            r2.renderModifier(r5, r0, r1)
            r0 = r3
            kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor) r0
            r2.renderVariable(r0, r4, r5, r6)
            kotlin.jvm.functions.Function1 r4 = r2.getDefaultParameterValueRenderer()
            if (r4 == 0) goto L_0x005e
            boolean r4 = r2.getDebugMode()
            if (r4 == 0) goto L_0x0056
            boolean r4 = r3.declaresDefaultValue()
            goto L_0x005a
        L_0x0056:
            boolean r4 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue(r3)
        L_0x005a:
            if (r4 == 0) goto L_0x005e
            r4 = 1
            goto L_0x005f
        L_0x005e:
            r4 = 0
        L_0x005f:
            if (r4 == 0) goto L_0x0084
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = " = "
            r4.append(r6)
            kotlin.jvm.functions.Function1 r6 = r2.getDefaultParameterValueRenderer()
            if (r6 != 0) goto L_0x0074
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0074:
            java.lang.Object r3 = r6.invoke(r3)
            java.lang.String r3 = (java.lang.String) r3
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r5.append(r3)
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderValueParameter(kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor, boolean, java.lang.StringBuilder, boolean):void");
    }

    private final void renderValVarPrefix(VariableDescriptor variableDescriptor, StringBuilder sb) {
        if (!(variableDescriptor instanceof ValueParameterDescriptor)) {
            sb.append(renderKeyword(variableDescriptor.isVar() ? "var" : "val"));
            sb.append(" ");
        }
    }

    private final void renderVariable(VariableDescriptor variableDescriptor, boolean z, StringBuilder sb, boolean z2) {
        KotlinType kotlinType;
        KotlinType type = variableDescriptor.getType();
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) (!(variableDescriptor instanceof ValueParameterDescriptor) ? null : variableDescriptor);
        KotlinType varargElementType = valueParameterDescriptor != null ? valueParameterDescriptor.getVarargElementType() : null;
        String str = "realType";
        if (varargElementType != null) {
            kotlinType = varargElementType;
        } else {
            Intrinsics.checkExpressionValueIsNotNull(type, str);
            kotlinType = type;
        }
        renderModifier(sb, varargElementType != null, "vararg");
        if (z2 && !getStartFromName()) {
            renderValVarPrefix(variableDescriptor, sb);
        }
        if (z) {
            renderName(variableDescriptor, sb, z2);
            sb.append(": ");
        }
        sb.append(renderType(kotlinType));
        renderInitializer(variableDescriptor, sb);
        if (getVerbose() && varargElementType != null) {
            sb.append(" /*");
            Intrinsics.checkExpressionValueIsNotNull(type, str);
            sb.append(renderType(type));
            sb.append("*/");
        }
    }

    /* access modifiers changed from: private */
    public final void renderProperty(PropertyDescriptor propertyDescriptor, StringBuilder sb) {
        String str = "property.typeParameters";
        if (!getStartFromName()) {
            if (!getStartFromDeclarationKeyword()) {
                renderAnnotations(sb, propertyDescriptor);
                Visibility visibility = propertyDescriptor.getVisibility();
                Intrinsics.checkExpressionValueIsNotNull(visibility, "property.visibility");
                renderVisibility(visibility, sb);
                renderModifier(sb, propertyDescriptor.isConst(), "const");
                renderMemberModifiers(propertyDescriptor, sb);
                CallableMemberDescriptor callableMemberDescriptor = propertyDescriptor;
                renderModalityForCallable(callableMemberDescriptor, sb);
                renderOverride(callableMemberDescriptor, sb);
                renderModifier(sb, propertyDescriptor.isLateInit(), "lateinit");
                renderMemberKind(callableMemberDescriptor, sb);
            }
            renderValVarPrefix(propertyDescriptor, sb);
            List typeParameters = propertyDescriptor.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, str);
            renderTypeParameters(typeParameters, sb, true);
            renderReceiver(propertyDescriptor, sb);
        }
        renderName(propertyDescriptor, sb, true);
        sb.append(": ");
        KotlinType type = propertyDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "property.type");
        sb.append(renderType(type));
        renderReceiverAfterName(propertyDescriptor, sb);
        renderInitializer(propertyDescriptor, sb);
        List typeParameters2 = propertyDescriptor.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters2, str);
        renderWhereSuffix(typeParameters2, sb);
    }

    private final void renderInitializer(VariableDescriptor variableDescriptor, StringBuilder sb) {
        if (getIncludePropertyConstant()) {
            ConstantValue compileTimeInitializer = variableDescriptor.getCompileTimeInitializer();
            if (compileTimeInitializer != null) {
                sb.append(" = ");
                Intrinsics.checkExpressionValueIsNotNull(compileTimeInitializer, "constant");
                sb.append(escape(renderConstant(compileTimeInitializer)));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void renderTypeAlias(TypeAliasDescriptor typeAliasDescriptor, StringBuilder sb) {
        renderAnnotations(sb, typeAliasDescriptor);
        Visibility visibility = typeAliasDescriptor.getVisibility();
        Intrinsics.checkExpressionValueIsNotNull(visibility, "typeAlias.visibility");
        renderVisibility(visibility, sb);
        renderMemberModifiers(typeAliasDescriptor, sb);
        sb.append(renderKeyword("typealias"));
        sb.append(" ");
        renderName(typeAliasDescriptor, sb, true);
        List declaredTypeParameters = typeAliasDescriptor.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "typeAlias.declaredTypeParameters");
        renderTypeParameters(declaredTypeParameters, sb, false);
        renderCapturedTypeParametersIfRequired(typeAliasDescriptor, sb);
        sb.append(" = ");
        sb.append(renderType(typeAliasDescriptor.getUnderlyingType()));
    }

    private final void renderCapturedTypeParametersIfRequired(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, StringBuilder sb) {
        List declaredTypeParameters = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        TypeConstructor typeConstructor = classifierDescriptorWithTypeParameters.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "classifier.typeConstructor");
        List parameters = typeConstructor.getParameters();
        if (getVerbose() && classifierDescriptorWithTypeParameters.isInner() && parameters.size() > declaredTypeParameters.size()) {
            sb.append(" /*captured type parameters: ");
            renderTypeParameterList(sb, parameters.subList(declaredTypeParameters.size(), parameters.size()));
            sb.append("*/");
        }
    }

    /* access modifiers changed from: private */
    public final void renderClass(ClassDescriptor classDescriptor, StringBuilder sb) {
        boolean z = classDescriptor.getKind() == ClassKind.ENUM_ENTRY;
        String str = "klass.kind";
        if (!getStartFromName()) {
            renderAnnotations(sb, classDescriptor);
            if (!z) {
                Visibility visibility = classDescriptor.getVisibility();
                Intrinsics.checkExpressionValueIsNotNull(visibility, "klass.visibility");
                renderVisibility(visibility, sb);
            }
            if (!(classDescriptor.getKind() == ClassKind.INTERFACE && classDescriptor.getModality() == Modality.ABSTRACT)) {
                ClassKind kind = classDescriptor.getKind();
                Intrinsics.checkExpressionValueIsNotNull(kind, str);
                if (!kind.isSingleton() || classDescriptor.getModality() != Modality.FINAL) {
                    Modality modality = classDescriptor.getModality();
                    Intrinsics.checkExpressionValueIsNotNull(modality, "klass.modality");
                    renderModality(modality, sb);
                }
            }
            renderMemberModifiers(classDescriptor, sb);
            renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.INNER) && classDescriptor.isInner(), "inner");
            renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.DATA) && classDescriptor.isData(), "data");
            renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.INLINE) && classDescriptor.isInline(), "inline");
            renderClassKindPrefix(classDescriptor, sb);
        }
        DeclarationDescriptor declarationDescriptor = classDescriptor;
        if (!DescriptorUtils.isCompanionObject(declarationDescriptor)) {
            if (!getStartFromName()) {
                renderSpaceIfNeeded(sb);
            }
            renderName(declarationDescriptor, sb, true);
        } else {
            renderCompanionObjectName(declarationDescriptor, sb);
        }
        if (!z) {
            List declaredTypeParameters = classDescriptor.getDeclaredTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "typeParameters");
            renderTypeParameters(declaredTypeParameters, sb, false);
            renderCapturedTypeParametersIfRequired(classDescriptor, sb);
            ClassKind kind2 = classDescriptor.getKind();
            Intrinsics.checkExpressionValueIsNotNull(kind2, str);
            if (!kind2.isSingleton() && getClassWithPrimaryConstructor()) {
                ClassConstructorDescriptor unsubstitutedPrimaryConstructor = classDescriptor.getUnsubstitutedPrimaryConstructor();
                if (unsubstitutedPrimaryConstructor != null) {
                    sb.append(" ");
                    renderAnnotations(sb, unsubstitutedPrimaryConstructor);
                    Visibility visibility2 = unsubstitutedPrimaryConstructor.getVisibility();
                    Intrinsics.checkExpressionValueIsNotNull(visibility2, "primaryConstructor.visibility");
                    renderVisibility(visibility2, sb);
                    sb.append(renderKeyword("constructor"));
                    List valueParameters = unsubstitutedPrimaryConstructor.getValueParameters();
                    Intrinsics.checkExpressionValueIsNotNull(valueParameters, "primaryConstructor.valueParameters");
                    renderValueParameters(valueParameters, unsubstitutedPrimaryConstructor.hasSynthesizedParameterNames(), sb);
                }
            }
            renderSuperTypes(classDescriptor, sb);
            renderWhereSuffix(declaredTypeParameters, sb);
        }
    }

    private final void renderSuperTypes(ClassDescriptor classDescriptor, StringBuilder sb) {
        if (!getWithoutSuperTypes() && !KotlinBuiltIns.isNothing(classDescriptor.getDefaultType())) {
            TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "klass.typeConstructor");
            Collection supertypes = typeConstructor.getSupertypes();
            if (!supertypes.isEmpty() && (supertypes.size() != 1 || !KotlinBuiltIns.isAnyOrNullableAny((KotlinType) supertypes.iterator().next()))) {
                renderSpaceIfNeeded(sb);
                sb.append(": ");
                Intrinsics.checkExpressionValueIsNotNull(supertypes, "supertypes");
                CollectionsKt.joinTo$default(supertypes, sb, ", ", null, null, 0, null, new DescriptorRendererImpl$renderSuperTypes$1(this), 60, null);
            }
        }
    }

    private final void renderClassKindPrefix(ClassDescriptor classDescriptor, StringBuilder sb) {
        sb.append(renderKeyword(DescriptorRenderer.Companion.getClassifierKindPrefix(classDescriptor)));
    }

    /* access modifiers changed from: private */
    public final void renderPackageView(PackageViewDescriptor packageViewDescriptor, StringBuilder sb) {
        renderPackageHeader(packageViewDescriptor.getFqName(), "package", sb);
        if (getDebugMode()) {
            sb.append(" in context of ");
            renderName(packageViewDescriptor.getModule(), sb, false);
        }
    }

    /* access modifiers changed from: private */
    public final void renderPackageFragment(PackageFragmentDescriptor packageFragmentDescriptor, StringBuilder sb) {
        renderPackageHeader(packageFragmentDescriptor.getFqName(), "package-fragment", sb);
        if (getDebugMode()) {
            sb.append(" in ");
            renderName(packageFragmentDescriptor.getContainingDeclaration(), sb, false);
        }
    }

    private final void renderPackageHeader(FqName fqName, String str, StringBuilder sb) {
        sb.append(renderKeyword(str));
        FqNameUnsafe unsafe = fqName.toUnsafe();
        Intrinsics.checkExpressionValueIsNotNull(unsafe, "fqName.toUnsafe()");
        String renderFqName = renderFqName(unsafe);
        if (renderFqName.length() > 0) {
            sb.append(" ");
            sb.append(renderFqName);
        }
    }

    /* access modifiers changed from: private */
    public final void renderAccessorModifiers(PropertyAccessorDescriptor propertyAccessorDescriptor, StringBuilder sb) {
        renderMemberModifiers(propertyAccessorDescriptor, sb);
    }

    private final void renderSpaceIfNeeded(StringBuilder sb) {
        int length = sb.length();
        if (length == 0 || sb.charAt(length - 1) != ' ') {
            sb.append(' ');
        }
    }

    private final String replacePrefixes(String str, String str2, String str3, String str4, String str5) {
        if (StringsKt.startsWith$default(str, str2, false, 2, null) && StringsKt.startsWith$default(str3, str4, false, 2, null)) {
            int length = str2.length();
            String str6 = "null cannot be cast to non-null type java.lang.String";
            if (str != null) {
                String substring = str.substring(length);
                String str7 = "(this as java.lang.String).substring(startIndex)";
                Intrinsics.checkExpressionValueIsNotNull(substring, str7);
                int length2 = str4.length();
                if (str3 != null) {
                    String substring2 = str3.substring(length2);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, str7);
                    StringBuilder sb = new StringBuilder();
                    sb.append(str5);
                    sb.append(substring);
                    String sb2 = sb.toString();
                    if (Intrinsics.areEqual((Object) substring, (Object) substring2)) {
                        return sb2;
                    }
                    if (differsOnlyInNullability(substring, substring2)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(sb2);
                        sb3.append("!");
                        return sb3.toString();
                    }
                } else {
                    throw new TypeCastException(str6);
                }
            } else {
                throw new TypeCastException(str6);
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0032, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0.toString(), (java.lang.Object) r8) == false) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean differsOnlyInNullability(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r1 = "?"
            java.lang.String r2 = ""
            r3 = 0
            r4 = 4
            r5 = 0
            r0 = r8
            java.lang.String r0 = kotlin.text.StringsKt.replace$default(r0, r1, r2, r3, r4, r5)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            r1 = 0
            if (r0 != 0) goto L_0x0050
            r0 = 2
            r2 = 0
            java.lang.String r3 = "?"
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r8, r3, r1, r0, r2)
            if (r0 == 0) goto L_0x0034
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            r2 = 63
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r8)
            if (r0 != 0) goto L_0x0050
        L_0x0034:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r2 = 40
            r0.append(r2)
            r0.append(r7)
            java.lang.String r7 = ")?"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
            if (r7 == 0) goto L_0x0051
        L_0x0050:
            r1 = 1
        L_0x0051:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.differsOnlyInNullability(java.lang.String, java.lang.String):boolean");
    }

    private final boolean overridesSomething(CallableMemberDescriptor callableMemberDescriptor) {
        return !callableMemberDescriptor.getOverriddenDescriptors().isEmpty();
    }
}
