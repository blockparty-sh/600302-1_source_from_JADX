package kotlin.reflect.jvm.internal.impl.renderer;

import java.lang.reflect.Field;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy.SOURCE_CODE_QUALIFIED;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler.DEFAULT;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DescriptorRendererOptionsImpl.kt */
public final class DescriptorRendererOptionsImpl implements DescriptorRendererOptions {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "classifierNamePolicy", "getClassifierNamePolicy()Lorg/jetbrains/kotlin/renderer/ClassifierNamePolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "withDefinedIn", "getWithDefinedIn()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "withSourceFileForTopLevel", "getWithSourceFileForTopLevel()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "modifiers", "getModifiers()Ljava/util/Set;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "startFromName", "getStartFromName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "startFromDeclarationKeyword", "getStartFromDeclarationKeyword()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "debugMode", "getDebugMode()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "classWithPrimaryConstructor", "getClassWithPrimaryConstructor()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "verbose", "getVerbose()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "unitReturnType", "getUnitReturnType()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "withoutReturnType", "getWithoutReturnType()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "enhancedTypes", "getEnhancedTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "normalizedVisibilities", "getNormalizedVisibilities()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "renderDefaultVisibility", "getRenderDefaultVisibility()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "uninferredTypeParameterAsName", "getUninferredTypeParameterAsName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "includePropertyConstant", "getIncludePropertyConstant()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "withoutTypeParameters", "getWithoutTypeParameters()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "withoutSuperTypes", "getWithoutSuperTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "typeNormalizer", "getTypeNormalizer()Lkotlin/jvm/functions/Function1;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "defaultParameterValueRenderer", "getDefaultParameterValueRenderer()Lkotlin/jvm/functions/Function1;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "secondaryConstructorsAsPrimary", "getSecondaryConstructorsAsPrimary()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "overrideRenderingPolicy", "getOverrideRenderingPolicy()Lorg/jetbrains/kotlin/renderer/OverrideRenderingPolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "valueParametersHandler", "getValueParametersHandler()Lorg/jetbrains/kotlin/renderer/DescriptorRenderer$ValueParametersHandler;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "textFormat", "getTextFormat()Lorg/jetbrains/kotlin/renderer/RenderingFormat;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "parameterNameRenderingPolicy", "getParameterNameRenderingPolicy()Lorg/jetbrains/kotlin/renderer/ParameterNameRenderingPolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "receiverAfterName", "getReceiverAfterName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "renderCompanionObjectName", "getRenderCompanionObjectName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "renderAccessors", "getRenderAccessors()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "renderDefaultAnnotationArguments", "getRenderDefaultAnnotationArguments()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "eachAnnotationOnNewLine", "getEachAnnotationOnNewLine()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "excludedAnnotationClasses", "getExcludedAnnotationClasses()Ljava/util/Set;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "excludedTypeAnnotationClasses", "getExcludedTypeAnnotationClasses()Ljava/util/Set;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "annotationFilter", "getAnnotationFilter()Lkotlin/jvm/functions/Function1;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "annotationArgumentsRenderingPolicy", "getAnnotationArgumentsRenderingPolicy()Lorg/jetbrains/kotlin/renderer/AnnotationArgumentsRenderingPolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "alwaysRenderModifiers", "getAlwaysRenderModifiers()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "renderConstructorKeyword", "getRenderConstructorKeyword()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "renderUnabbreviatedType", "getRenderUnabbreviatedType()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "includeAdditionalModifiers", "getIncludeAdditionalModifiers()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "parameterNamesInFunctionalTypes", "getParameterNamesInFunctionalTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "renderFunctionContracts", "getRenderFunctionContracts()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "presentableUnresolvedTypes", "getPresentableUnresolvedTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class), "boldOnlyForNamesInHtml", "getBoldOnlyForNamesInHtml()Z"))};
    @NotNull
    private final ReadWriteProperty alwaysRenderModifiers$delegate;
    @NotNull
    private final ReadWriteProperty annotationArgumentsRenderingPolicy$delegate;
    @Nullable
    private final ReadWriteProperty annotationFilter$delegate;
    @NotNull
    private final ReadWriteProperty boldOnlyForNamesInHtml$delegate;
    @NotNull
    private final ReadWriteProperty classWithPrimaryConstructor$delegate;
    @NotNull
    private final ReadWriteProperty classifierNamePolicy$delegate = property(SOURCE_CODE_QUALIFIED.INSTANCE);
    @NotNull
    private final ReadWriteProperty debugMode$delegate;
    @Nullable
    private final ReadWriteProperty defaultParameterValueRenderer$delegate;
    @NotNull
    private final ReadWriteProperty eachAnnotationOnNewLine$delegate;
    @NotNull
    private final ReadWriteProperty enhancedTypes$delegate;
    @NotNull
    private final ReadWriteProperty excludedAnnotationClasses$delegate;
    @NotNull
    private final ReadWriteProperty excludedTypeAnnotationClasses$delegate;
    @NotNull
    private final ReadWriteProperty includeAdditionalModifiers$delegate;
    @NotNull
    private final ReadWriteProperty includePropertyConstant$delegate;
    private boolean isLocked;
    @NotNull
    private final ReadWriteProperty modifiers$delegate;
    @NotNull
    private final ReadWriteProperty normalizedVisibilities$delegate;
    @NotNull
    private final ReadWriteProperty overrideRenderingPolicy$delegate;
    @NotNull
    private final ReadWriteProperty parameterNameRenderingPolicy$delegate;
    @NotNull
    private final ReadWriteProperty parameterNamesInFunctionalTypes$delegate;
    @NotNull
    private final ReadWriteProperty presentableUnresolvedTypes$delegate;
    @NotNull
    private final ReadWriteProperty receiverAfterName$delegate;
    @NotNull
    private final ReadWriteProperty renderAccessors$delegate;
    @NotNull
    private final ReadWriteProperty renderCompanionObjectName$delegate;
    @NotNull
    private final ReadWriteProperty renderConstructorKeyword$delegate;
    @NotNull
    private final ReadWriteProperty renderDefaultAnnotationArguments$delegate;
    @NotNull
    private final ReadWriteProperty renderDefaultVisibility$delegate;
    @NotNull
    private final ReadWriteProperty renderFunctionContracts$delegate;
    @NotNull
    private final ReadWriteProperty renderUnabbreviatedType$delegate;
    @NotNull
    private final ReadWriteProperty secondaryConstructorsAsPrimary$delegate;
    @NotNull
    private final ReadWriteProperty startFromDeclarationKeyword$delegate;
    @NotNull
    private final ReadWriteProperty startFromName$delegate;
    @NotNull
    private final ReadWriteProperty textFormat$delegate;
    @NotNull
    private final ReadWriteProperty typeNormalizer$delegate;
    @NotNull
    private final ReadWriteProperty uninferredTypeParameterAsName$delegate;
    @NotNull
    private final ReadWriteProperty unitReturnType$delegate;
    @NotNull
    private final ReadWriteProperty valueParametersHandler$delegate;
    @NotNull
    private final ReadWriteProperty verbose$delegate;
    @NotNull
    private final ReadWriteProperty withDefinedIn$delegate;
    @NotNull
    private final ReadWriteProperty withSourceFileForTopLevel$delegate;
    @NotNull
    private final ReadWriteProperty withoutReturnType$delegate;
    @NotNull
    private final ReadWriteProperty withoutSuperTypes$delegate;
    @NotNull
    private final ReadWriteProperty withoutTypeParameters$delegate;

    public boolean getAlwaysRenderModifiers() {
        return ((Boolean) this.alwaysRenderModifiers$delegate.getValue(this, $$delegatedProperties[34])).booleanValue();
    }

    @NotNull
    public AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy() {
        return (AnnotationArgumentsRenderingPolicy) this.annotationArgumentsRenderingPolicy$delegate.getValue(this, $$delegatedProperties[33]);
    }

    @Nullable
    public Function1<AnnotationDescriptor, Boolean> getAnnotationFilter() {
        return (Function1) this.annotationFilter$delegate.getValue(this, $$delegatedProperties[32]);
    }

    public boolean getBoldOnlyForNamesInHtml() {
        return ((Boolean) this.boldOnlyForNamesInHtml$delegate.getValue(this, $$delegatedProperties[41])).booleanValue();
    }

    public boolean getClassWithPrimaryConstructor() {
        return ((Boolean) this.classWithPrimaryConstructor$delegate.getValue(this, $$delegatedProperties[7])).booleanValue();
    }

    @NotNull
    public ClassifierNamePolicy getClassifierNamePolicy() {
        return (ClassifierNamePolicy) this.classifierNamePolicy$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public boolean getDebugMode() {
        return ((Boolean) this.debugMode$delegate.getValue(this, $$delegatedProperties[6])).booleanValue();
    }

    @Nullable
    public Function1<ValueParameterDescriptor, String> getDefaultParameterValueRenderer() {
        return (Function1) this.defaultParameterValueRenderer$delegate.getValue(this, $$delegatedProperties[19]);
    }

    public boolean getEachAnnotationOnNewLine() {
        return ((Boolean) this.eachAnnotationOnNewLine$delegate.getValue(this, $$delegatedProperties[29])).booleanValue();
    }

    public boolean getEnhancedTypes() {
        return ((Boolean) this.enhancedTypes$delegate.getValue(this, $$delegatedProperties[11])).booleanValue();
    }

    @NotNull
    public Set<FqName> getExcludedAnnotationClasses() {
        return (Set) this.excludedAnnotationClasses$delegate.getValue(this, $$delegatedProperties[30]);
    }

    @NotNull
    public Set<FqName> getExcludedTypeAnnotationClasses() {
        return (Set) this.excludedTypeAnnotationClasses$delegate.getValue(this, $$delegatedProperties[31]);
    }

    public boolean getIncludeAdditionalModifiers() {
        return ((Boolean) this.includeAdditionalModifiers$delegate.getValue(this, $$delegatedProperties[37])).booleanValue();
    }

    public boolean getIncludePropertyConstant() {
        return ((Boolean) this.includePropertyConstant$delegate.getValue(this, $$delegatedProperties[15])).booleanValue();
    }

    @NotNull
    public Set<DescriptorRendererModifier> getModifiers() {
        return (Set) this.modifiers$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public boolean getNormalizedVisibilities() {
        return ((Boolean) this.normalizedVisibilities$delegate.getValue(this, $$delegatedProperties[12])).booleanValue();
    }

    @NotNull
    public OverrideRenderingPolicy getOverrideRenderingPolicy() {
        return (OverrideRenderingPolicy) this.overrideRenderingPolicy$delegate.getValue(this, $$delegatedProperties[21]);
    }

    @NotNull
    public ParameterNameRenderingPolicy getParameterNameRenderingPolicy() {
        return (ParameterNameRenderingPolicy) this.parameterNameRenderingPolicy$delegate.getValue(this, $$delegatedProperties[24]);
    }

    public boolean getParameterNamesInFunctionalTypes() {
        return ((Boolean) this.parameterNamesInFunctionalTypes$delegate.getValue(this, $$delegatedProperties[38])).booleanValue();
    }

    public boolean getPresentableUnresolvedTypes() {
        return ((Boolean) this.presentableUnresolvedTypes$delegate.getValue(this, $$delegatedProperties[40])).booleanValue();
    }

    public boolean getReceiverAfterName() {
        return ((Boolean) this.receiverAfterName$delegate.getValue(this, $$delegatedProperties[25])).booleanValue();
    }

    public boolean getRenderAccessors() {
        return ((Boolean) this.renderAccessors$delegate.getValue(this, $$delegatedProperties[27])).booleanValue();
    }

    public boolean getRenderCompanionObjectName() {
        return ((Boolean) this.renderCompanionObjectName$delegate.getValue(this, $$delegatedProperties[26])).booleanValue();
    }

    public boolean getRenderConstructorKeyword() {
        return ((Boolean) this.renderConstructorKeyword$delegate.getValue(this, $$delegatedProperties[35])).booleanValue();
    }

    public boolean getRenderDefaultAnnotationArguments() {
        return ((Boolean) this.renderDefaultAnnotationArguments$delegate.getValue(this, $$delegatedProperties[28])).booleanValue();
    }

    public boolean getRenderDefaultVisibility() {
        return ((Boolean) this.renderDefaultVisibility$delegate.getValue(this, $$delegatedProperties[13])).booleanValue();
    }

    public boolean getRenderUnabbreviatedType() {
        return ((Boolean) this.renderUnabbreviatedType$delegate.getValue(this, $$delegatedProperties[36])).booleanValue();
    }

    public boolean getSecondaryConstructorsAsPrimary() {
        return ((Boolean) this.secondaryConstructorsAsPrimary$delegate.getValue(this, $$delegatedProperties[20])).booleanValue();
    }

    public boolean getStartFromDeclarationKeyword() {
        return ((Boolean) this.startFromDeclarationKeyword$delegate.getValue(this, $$delegatedProperties[5])).booleanValue();
    }

    public boolean getStartFromName() {
        return ((Boolean) this.startFromName$delegate.getValue(this, $$delegatedProperties[4])).booleanValue();
    }

    @NotNull
    public RenderingFormat getTextFormat() {
        return (RenderingFormat) this.textFormat$delegate.getValue(this, $$delegatedProperties[23]);
    }

    @NotNull
    public Function1<KotlinType, KotlinType> getTypeNormalizer() {
        return (Function1) this.typeNormalizer$delegate.getValue(this, $$delegatedProperties[18]);
    }

    public boolean getUninferredTypeParameterAsName() {
        return ((Boolean) this.uninferredTypeParameterAsName$delegate.getValue(this, $$delegatedProperties[14])).booleanValue();
    }

    public boolean getUnitReturnType() {
        return ((Boolean) this.unitReturnType$delegate.getValue(this, $$delegatedProperties[9])).booleanValue();
    }

    @NotNull
    public ValueParametersHandler getValueParametersHandler() {
        return (ValueParametersHandler) this.valueParametersHandler$delegate.getValue(this, $$delegatedProperties[22]);
    }

    public boolean getVerbose() {
        return ((Boolean) this.verbose$delegate.getValue(this, $$delegatedProperties[8])).booleanValue();
    }

    public boolean getWithDefinedIn() {
        return ((Boolean) this.withDefinedIn$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public boolean getWithSourceFileForTopLevel() {
        return ((Boolean) this.withSourceFileForTopLevel$delegate.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    public boolean getWithoutReturnType() {
        return ((Boolean) this.withoutReturnType$delegate.getValue(this, $$delegatedProperties[10])).booleanValue();
    }

    public boolean getWithoutSuperTypes() {
        return ((Boolean) this.withoutSuperTypes$delegate.getValue(this, $$delegatedProperties[17])).booleanValue();
    }

    public boolean getWithoutTypeParameters() {
        return ((Boolean) this.withoutTypeParameters$delegate.getValue(this, $$delegatedProperties[16])).booleanValue();
    }

    public void setAnnotationArgumentsRenderingPolicy(@NotNull AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy) {
        Intrinsics.checkParameterIsNotNull(annotationArgumentsRenderingPolicy, "<set-?>");
        this.annotationArgumentsRenderingPolicy$delegate.setValue(this, $$delegatedProperties[33], annotationArgumentsRenderingPolicy);
    }

    public void setClassifierNamePolicy(@NotNull ClassifierNamePolicy classifierNamePolicy) {
        Intrinsics.checkParameterIsNotNull(classifierNamePolicy, "<set-?>");
        this.classifierNamePolicy$delegate.setValue(this, $$delegatedProperties[0], classifierNamePolicy);
    }

    public void setDebugMode(boolean z) {
        this.debugMode$delegate.setValue(this, $$delegatedProperties[6], Boolean.valueOf(z));
    }

    public void setExcludedTypeAnnotationClasses(@NotNull Set<FqName> set) {
        Intrinsics.checkParameterIsNotNull(set, "<set-?>");
        this.excludedTypeAnnotationClasses$delegate.setValue(this, $$delegatedProperties[31], set);
    }

    public void setModifiers(@NotNull Set<? extends DescriptorRendererModifier> set) {
        Intrinsics.checkParameterIsNotNull(set, "<set-?>");
        this.modifiers$delegate.setValue(this, $$delegatedProperties[3], set);
    }

    public void setParameterNameRenderingPolicy(@NotNull ParameterNameRenderingPolicy parameterNameRenderingPolicy) {
        Intrinsics.checkParameterIsNotNull(parameterNameRenderingPolicy, "<set-?>");
        this.parameterNameRenderingPolicy$delegate.setValue(this, $$delegatedProperties[24], parameterNameRenderingPolicy);
    }

    public void setReceiverAfterName(boolean z) {
        this.receiverAfterName$delegate.setValue(this, $$delegatedProperties[25], Boolean.valueOf(z));
    }

    public void setRenderCompanionObjectName(boolean z) {
        this.renderCompanionObjectName$delegate.setValue(this, $$delegatedProperties[26], Boolean.valueOf(z));
    }

    public void setStartFromName(boolean z) {
        this.startFromName$delegate.setValue(this, $$delegatedProperties[4], Boolean.valueOf(z));
    }

    public void setTextFormat(@NotNull RenderingFormat renderingFormat) {
        Intrinsics.checkParameterIsNotNull(renderingFormat, "<set-?>");
        this.textFormat$delegate.setValue(this, $$delegatedProperties[23], renderingFormat);
    }

    public void setVerbose(boolean z) {
        this.verbose$delegate.setValue(this, $$delegatedProperties[8], Boolean.valueOf(z));
    }

    public void setWithDefinedIn(boolean z) {
        this.withDefinedIn$delegate.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    public void setWithoutSuperTypes(boolean z) {
        this.withoutSuperTypes$delegate.setValue(this, $$delegatedProperties[17], Boolean.valueOf(z));
    }

    public void setWithoutTypeParameters(boolean z) {
        this.withoutTypeParameters$delegate.setValue(this, $$delegatedProperties[16], Boolean.valueOf(z));
    }

    public DescriptorRendererOptionsImpl() {
        Boolean valueOf = Boolean.valueOf(true);
        this.withDefinedIn$delegate = property(valueOf);
        this.withSourceFileForTopLevel$delegate = property(valueOf);
        this.modifiers$delegate = property(DescriptorRendererModifier.DEFAULTS);
        Boolean valueOf2 = Boolean.valueOf(false);
        this.startFromName$delegate = property(valueOf2);
        this.startFromDeclarationKeyword$delegate = property(valueOf2);
        this.debugMode$delegate = property(valueOf2);
        this.classWithPrimaryConstructor$delegate = property(valueOf2);
        this.verbose$delegate = property(valueOf2);
        this.unitReturnType$delegate = property(valueOf);
        this.withoutReturnType$delegate = property(valueOf2);
        this.enhancedTypes$delegate = property(valueOf2);
        this.normalizedVisibilities$delegate = property(valueOf2);
        this.renderDefaultVisibility$delegate = property(valueOf);
        this.uninferredTypeParameterAsName$delegate = property(valueOf2);
        this.includePropertyConstant$delegate = property(valueOf2);
        this.withoutTypeParameters$delegate = property(valueOf2);
        this.withoutSuperTypes$delegate = property(valueOf2);
        this.typeNormalizer$delegate = property(DescriptorRendererOptionsImpl$typeNormalizer$2.INSTANCE);
        this.defaultParameterValueRenderer$delegate = property(DescriptorRendererOptionsImpl$defaultParameterValueRenderer$2.INSTANCE);
        this.secondaryConstructorsAsPrimary$delegate = property(valueOf);
        this.overrideRenderingPolicy$delegate = property(OverrideRenderingPolicy.RENDER_OPEN);
        this.valueParametersHandler$delegate = property(DEFAULT.INSTANCE);
        this.textFormat$delegate = property(RenderingFormat.PLAIN);
        this.parameterNameRenderingPolicy$delegate = property(ParameterNameRenderingPolicy.ALL);
        this.receiverAfterName$delegate = property(valueOf2);
        this.renderCompanionObjectName$delegate = property(valueOf2);
        this.renderAccessors$delegate = property(valueOf2);
        this.renderDefaultAnnotationArguments$delegate = property(valueOf2);
        this.eachAnnotationOnNewLine$delegate = property(valueOf2);
        this.excludedAnnotationClasses$delegate = property(SetsKt.emptySet());
        this.excludedTypeAnnotationClasses$delegate = property(ExcludedTypeAnnotations.INSTANCE.getInternalAnnotationsForResolve());
        this.annotationFilter$delegate = property(null);
        this.annotationArgumentsRenderingPolicy$delegate = property(AnnotationArgumentsRenderingPolicy.NO_ARGUMENTS);
        this.alwaysRenderModifiers$delegate = property(valueOf2);
        this.renderConstructorKeyword$delegate = property(valueOf);
        this.renderUnabbreviatedType$delegate = property(valueOf);
        this.includeAdditionalModifiers$delegate = property(valueOf);
        this.parameterNamesInFunctionalTypes$delegate = property(valueOf);
        this.renderFunctionContracts$delegate = property(valueOf2);
        this.presentableUnresolvedTypes$delegate = property(valueOf2);
        this.boldOnlyForNamesInHtml$delegate = property(valueOf2);
    }

    public boolean getIncludeAnnotationArguments() {
        return DefaultImpls.getIncludeAnnotationArguments(this);
    }

    public boolean getIncludeEmptyAnnotationArguments() {
        return DefaultImpls.getIncludeEmptyAnnotationArguments(this);
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final void lock() {
        boolean z = !this.isLocked;
        if (!_Assertions.ENABLED || z) {
            this.isLocked = true;
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    @NotNull
    public final DescriptorRendererOptionsImpl copy() {
        Field[] declaredFields;
        DescriptorRendererOptionsImpl descriptorRendererOptionsImpl = new DescriptorRendererOptionsImpl();
        for (Field field : getClass().getDeclaredFields()) {
            Intrinsics.checkExpressionValueIsNotNull(field, "field");
            if ((field.getModifiers() & 8) == 0) {
                field.setAccessible(true);
                Object obj = field.get(this);
                if (!(obj instanceof ObservableProperty)) {
                    obj = null;
                }
                ObservableProperty observableProperty = (ObservableProperty) obj;
                if (observableProperty != null) {
                    String name = field.getName();
                    String str = "field.name";
                    Intrinsics.checkExpressionValueIsNotNull(name, str);
                    boolean startsWith$default = true ^ StringsKt.startsWith$default(name, "is", false, 2, null);
                    if (!_Assertions.ENABLED || startsWith$default) {
                        KDeclarationContainer orCreateKotlinClass = Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class);
                        String name2 = field.getName();
                        StringBuilder sb = new StringBuilder();
                        sb.append("get");
                        String name3 = field.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name3, str);
                        sb.append(StringsKt.capitalize(name3));
                        field.set(descriptorRendererOptionsImpl, descriptorRendererOptionsImpl.property(observableProperty.getValue(this, new PropertyReference1Impl(orCreateKotlinClass, name2, sb.toString()))));
                    } else {
                        throw new AssertionError("Fields named is* are not supported here yet");
                    }
                } else {
                    continue;
                }
            }
        }
        return descriptorRendererOptionsImpl;
    }

    private final <T> ReadWriteProperty<DescriptorRendererOptionsImpl, T> property(T t) {
        Delegates delegates = Delegates.INSTANCE;
        return new DescriptorRendererOptionsImpl$property$$inlined$vetoable$1<>(t, t, this);
    }
}
