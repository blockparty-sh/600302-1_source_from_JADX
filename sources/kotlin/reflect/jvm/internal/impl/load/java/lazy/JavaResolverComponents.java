package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: context.kt */
public final class JavaResolverComponents {
    @NotNull
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    @NotNull
    private final DeserializedDescriptorResolver deserializedDescriptorResolver;
    @NotNull
    private final ErrorReporter errorReporter;
    @NotNull
    private final JavaClassFinder finder;
    @NotNull
    private final JavaClassesTracker javaClassesTracker;
    @NotNull
    private final JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator;
    @NotNull
    private final JavaResolverCache javaResolverCache;
    @NotNull
    private final KotlinClassFinder kotlinClassFinder;
    @NotNull
    private final LookupTracker lookupTracker;
    @NotNull
    private final ModuleDescriptor module;
    @NotNull
    private final ModuleClassResolver moduleClassResolver;
    @NotNull
    private final PackagePartProvider packagePartProvider;
    @NotNull
    private final ReflectionTypes reflectionTypes;
    @NotNull
    private final SamConversionResolver samConversionResolver;
    @NotNull
    private final JavaResolverSettings settings;
    @NotNull
    private final SignatureEnhancement signatureEnhancement;
    @NotNull
    private final SignaturePropagator signaturePropagator;
    @NotNull
    private final JavaSourceElementFactory sourceElementFactory;
    @NotNull
    private final StorageManager storageManager;
    @NotNull
    private final SupertypeLoopChecker supertypeLoopChecker;

    public JavaResolverComponents(@NotNull StorageManager storageManager2, @NotNull JavaClassFinder javaClassFinder, @NotNull KotlinClassFinder kotlinClassFinder2, @NotNull DeserializedDescriptorResolver deserializedDescriptorResolver2, @NotNull SignaturePropagator signaturePropagator2, @NotNull ErrorReporter errorReporter2, @NotNull JavaResolverCache javaResolverCache2, @NotNull JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator2, @NotNull SamConversionResolver samConversionResolver2, @NotNull JavaSourceElementFactory javaSourceElementFactory, @NotNull ModuleClassResolver moduleClassResolver2, @NotNull PackagePartProvider packagePartProvider2, @NotNull SupertypeLoopChecker supertypeLoopChecker2, @NotNull LookupTracker lookupTracker2, @NotNull ModuleDescriptor moduleDescriptor, @NotNull ReflectionTypes reflectionTypes2, @NotNull AnnotationTypeQualifierResolver annotationTypeQualifierResolver2, @NotNull SignatureEnhancement signatureEnhancement2, @NotNull JavaClassesTracker javaClassesTracker2, @NotNull JavaResolverSettings javaResolverSettings) {
        StorageManager storageManager3 = storageManager2;
        JavaClassFinder javaClassFinder2 = javaClassFinder;
        KotlinClassFinder kotlinClassFinder3 = kotlinClassFinder2;
        DeserializedDescriptorResolver deserializedDescriptorResolver3 = deserializedDescriptorResolver2;
        SignaturePropagator signaturePropagator3 = signaturePropagator2;
        ErrorReporter errorReporter3 = errorReporter2;
        JavaResolverCache javaResolverCache3 = javaResolverCache2;
        JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator3 = javaPropertyInitializerEvaluator2;
        SamConversionResolver samConversionResolver3 = samConversionResolver2;
        JavaSourceElementFactory javaSourceElementFactory2 = javaSourceElementFactory;
        ModuleClassResolver moduleClassResolver3 = moduleClassResolver2;
        PackagePartProvider packagePartProvider3 = packagePartProvider2;
        SupertypeLoopChecker supertypeLoopChecker3 = supertypeLoopChecker2;
        LookupTracker lookupTracker3 = lookupTracker2;
        ModuleDescriptor moduleDescriptor2 = moduleDescriptor;
        ReflectionTypes reflectionTypes3 = reflectionTypes2;
        Intrinsics.checkParameterIsNotNull(storageManager3, "storageManager");
        Intrinsics.checkParameterIsNotNull(javaClassFinder2, "finder");
        Intrinsics.checkParameterIsNotNull(kotlinClassFinder3, "kotlinClassFinder");
        Intrinsics.checkParameterIsNotNull(deserializedDescriptorResolver3, "deserializedDescriptorResolver");
        Intrinsics.checkParameterIsNotNull(signaturePropagator3, "signaturePropagator");
        Intrinsics.checkParameterIsNotNull(errorReporter3, "errorReporter");
        Intrinsics.checkParameterIsNotNull(javaResolverCache3, "javaResolverCache");
        Intrinsics.checkParameterIsNotNull(javaPropertyInitializerEvaluator3, "javaPropertyInitializerEvaluator");
        Intrinsics.checkParameterIsNotNull(samConversionResolver3, "samConversionResolver");
        Intrinsics.checkParameterIsNotNull(javaSourceElementFactory2, "sourceElementFactory");
        Intrinsics.checkParameterIsNotNull(moduleClassResolver3, "moduleClassResolver");
        Intrinsics.checkParameterIsNotNull(packagePartProvider3, "packagePartProvider");
        Intrinsics.checkParameterIsNotNull(supertypeLoopChecker3, "supertypeLoopChecker");
        Intrinsics.checkParameterIsNotNull(lookupTracker3, "lookupTracker");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor2, "module");
        Intrinsics.checkParameterIsNotNull(reflectionTypes2, "reflectionTypes");
        Intrinsics.checkParameterIsNotNull(annotationTypeQualifierResolver2, "annotationTypeQualifierResolver");
        Intrinsics.checkParameterIsNotNull(signatureEnhancement2, "signatureEnhancement");
        Intrinsics.checkParameterIsNotNull(javaClassesTracker2, "javaClassesTracker");
        Intrinsics.checkParameterIsNotNull(javaResolverSettings, "settings");
        ReflectionTypes reflectionTypes4 = reflectionTypes2;
        this.storageManager = storageManager3;
        this.finder = javaClassFinder2;
        this.kotlinClassFinder = kotlinClassFinder3;
        this.deserializedDescriptorResolver = deserializedDescriptorResolver3;
        this.signaturePropagator = signaturePropagator3;
        this.errorReporter = errorReporter3;
        this.javaResolverCache = javaResolverCache3;
        this.javaPropertyInitializerEvaluator = javaPropertyInitializerEvaluator3;
        this.samConversionResolver = samConversionResolver3;
        this.sourceElementFactory = javaSourceElementFactory2;
        this.moduleClassResolver = moduleClassResolver3;
        this.packagePartProvider = packagePartProvider3;
        this.supertypeLoopChecker = supertypeLoopChecker3;
        this.lookupTracker = lookupTracker3;
        this.module = moduleDescriptor;
        this.reflectionTypes = reflectionTypes4;
        SignatureEnhancement signatureEnhancement3 = signatureEnhancement2;
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver2;
        this.signatureEnhancement = signatureEnhancement3;
        JavaResolverSettings javaResolverSettings2 = javaResolverSettings;
        this.javaClassesTracker = javaClassesTracker2;
        this.settings = javaResolverSettings2;
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @NotNull
    public final JavaClassFinder getFinder() {
        return this.finder;
    }

    @NotNull
    public final KotlinClassFinder getKotlinClassFinder() {
        return this.kotlinClassFinder;
    }

    @NotNull
    public final DeserializedDescriptorResolver getDeserializedDescriptorResolver() {
        return this.deserializedDescriptorResolver;
    }

    @NotNull
    public final SignaturePropagator getSignaturePropagator() {
        return this.signaturePropagator;
    }

    @NotNull
    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    @NotNull
    public final JavaResolverCache getJavaResolverCache() {
        return this.javaResolverCache;
    }

    @NotNull
    public final JavaPropertyInitializerEvaluator getJavaPropertyInitializerEvaluator() {
        return this.javaPropertyInitializerEvaluator;
    }

    @NotNull
    public final JavaSourceElementFactory getSourceElementFactory() {
        return this.sourceElementFactory;
    }

    @NotNull
    public final ModuleClassResolver getModuleClassResolver() {
        return this.moduleClassResolver;
    }

    @NotNull
    public final PackagePartProvider getPackagePartProvider() {
        return this.packagePartProvider;
    }

    @NotNull
    public final SupertypeLoopChecker getSupertypeLoopChecker() {
        return this.supertypeLoopChecker;
    }

    @NotNull
    public final LookupTracker getLookupTracker() {
        return this.lookupTracker;
    }

    @NotNull
    public final ModuleDescriptor getModule() {
        return this.module;
    }

    @NotNull
    public final ReflectionTypes getReflectionTypes() {
        return this.reflectionTypes;
    }

    @NotNull
    public final AnnotationTypeQualifierResolver getAnnotationTypeQualifierResolver() {
        return this.annotationTypeQualifierResolver;
    }

    @NotNull
    public final SignatureEnhancement getSignatureEnhancement() {
        return this.signatureEnhancement;
    }

    @NotNull
    public final JavaClassesTracker getJavaClassesTracker() {
        return this.javaClassesTracker;
    }

    @NotNull
    public final JavaResolverSettings getSettings() {
        return this.settings;
    }

    @NotNull
    public final JavaResolverComponents replace(@NotNull JavaResolverCache javaResolverCache2) {
        JavaResolverCache javaResolverCache3 = javaResolverCache2;
        Intrinsics.checkParameterIsNotNull(javaResolverCache2, "javaResolverCache");
        JavaResolverComponents javaResolverComponents = new JavaResolverComponents(this.storageManager, this.finder, this.kotlinClassFinder, this.deserializedDescriptorResolver, this.signaturePropagator, this.errorReporter, javaResolverCache3, this.javaPropertyInitializerEvaluator, this.samConversionResolver, this.sourceElementFactory, this.moduleClassResolver, this.packagePartProvider, this.supertypeLoopChecker, this.lookupTracker, this.module, this.reflectionTypes, this.annotationTypeQualifierResolver, this.signatureEnhancement, this.javaClassesTracker, this.settings);
        return javaResolverComponents;
    }
}
