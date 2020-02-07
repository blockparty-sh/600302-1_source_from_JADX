package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FunctionClassDescriptor.kt */
public final class FunctionClassDescriptor extends AbstractClassDescriptor {
    private final int arity;
    /* access modifiers changed from: private */
    public final PackageFragmentDescriptor containingDeclaration;
    @NotNull
    private final Kind functionKind;
    private final FunctionClassScope memberScope = new FunctionClassScope(this.storageManager, this);
    /* access modifiers changed from: private */
    public final List<TypeParameterDescriptor> parameters;
    /* access modifiers changed from: private */
    public final StorageManager storageManager;
    private final FunctionTypeConstructor typeConstructor = new FunctionTypeConstructor();

    /* compiled from: FunctionClassDescriptor.kt */
    private final class FunctionTypeConstructor extends AbstractClassTypeConstructor {

        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Kind.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Kind.values().length];

            static {
                $EnumSwitchMapping$0[Kind.SuspendFunction.ordinal()] = 1;
                $EnumSwitchMapping$0[Kind.KSuspendFunction.ordinal()] = 2;
                $EnumSwitchMapping$1[Kind.KFunction.ordinal()] = 1;
                $EnumSwitchMapping$1[Kind.KSuspendFunction.ordinal()] = 2;
            }
        }

        public boolean isDenotable() {
            return true;
        }

        public FunctionTypeConstructor() {
            super(FunctionClassDescriptor.this.storageManager);
        }

        /* access modifiers changed from: protected */
        @NotNull
        public Collection<KotlinType> computeSupertypes() {
            Kind kind;
            ArrayList arrayList = new ArrayList(2);
            C2939x86380a31 functionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1 = new C2939x86380a31(this, arrayList);
            int i = WhenMappings.$EnumSwitchMapping$0[FunctionClassDescriptor.this.getFunctionKind().ordinal()];
            if (i == 1) {
                PackageFragmentDescriptor access$getContainingDeclaration$p = FunctionClassDescriptor.this.containingDeclaration;
                Name identifier = Name.identifier("Function");
                Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"Function\")");
                functionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1.invoke(access$getContainingDeclaration$p, identifier);
            } else if (i != 2) {
                PackageFragmentDescriptor access$getContainingDeclaration$p2 = FunctionClassDescriptor.this.containingDeclaration;
                Name identifier2 = Name.identifier(FunctionClassDescriptor.this.getFunctionKind().getClassNamePrefix());
                Intrinsics.checkExpressionValueIsNotNull(identifier2, "Name.identifier(functionKind.classNamePrefix)");
                functionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1.invoke(access$getContainingDeclaration$p2, identifier2);
            } else {
                PackageFragmentDescriptor access$getContainingDeclaration$p3 = FunctionClassDescriptor.this.containingDeclaration;
                Name identifier3 = Name.identifier("KFunction");
                Intrinsics.checkExpressionValueIsNotNull(identifier3, "Name.identifier(\"KFunction\")");
                functionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1.invoke(access$getContainingDeclaration$p3, identifier3);
            }
            int i2 = WhenMappings.$EnumSwitchMapping$1[FunctionClassDescriptor.this.getFunctionKind().ordinal()];
            if (i2 == 1) {
                kind = Kind.Function;
            } else if (i2 != 2) {
                kind = null;
            } else {
                kind = Kind.SuspendFunction;
            }
            if (kind != null) {
                ModuleDescriptor containingDeclaration = FunctionClassDescriptor.this.containingDeclaration.getContainingDeclaration();
                FqName fqName = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME;
                Intrinsics.checkExpressionValueIsNotNull(fqName, "BUILT_INS_PACKAGE_FQ_NAME");
                Iterable fragments = containingDeclaration.getPackage(fqName).getFragments();
                Collection arrayList2 = new ArrayList();
                for (Object next : fragments) {
                    if (next instanceof BuiltInsPackageFragment) {
                        arrayList2.add(next);
                    }
                }
                PackageFragmentDescriptor packageFragmentDescriptor = (BuiltInsPackageFragment) CollectionsKt.first((List) arrayList2);
                Name numberedClassName = kind.numberedClassName(FunctionClassDescriptor.this.getArity());
                Intrinsics.checkExpressionValueIsNotNull(numberedClassName, "numberedSupertypeKind.numberedClassName(arity)");
                functionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1.invoke(packageFragmentDescriptor, numberedClassName);
            }
            return CollectionsKt.toList(arrayList);
        }

        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return FunctionClassDescriptor.this.parameters;
        }

        @NotNull
        public FunctionClassDescriptor getDeclarationDescriptor() {
            return FunctionClassDescriptor.this;
        }

        @NotNull
        public String toString() {
            return getDeclarationDescriptor().toString();
        }

        /* access modifiers changed from: protected */
        @NotNull
        public SupertypeLoopChecker getSupertypeLoopChecker() {
            return EMPTY.INSTANCE;
        }
    }

    /* compiled from: FunctionClassDescriptor.kt */
    public enum Kind {
        Function(r2, r4),
        SuspendFunction(r2, r3),
        KFunction(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), r3),
        KSuspendFunction(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), r3);
        
        public static final Companion Companion = null;
        @NotNull
        private final String classNamePrefix;
        @NotNull
        private final FqName packageFqName;

        /* compiled from: FunctionClassDescriptor.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Nullable
            public final Kind byClassNamePrefix(@NotNull FqName fqName, @NotNull String str) {
                Kind[] values;
                Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
                Intrinsics.checkParameterIsNotNull(str, "className");
                for (Kind kind : Kind.values()) {
                    if (Intrinsics.areEqual((Object) kind.getPackageFqName(), (Object) fqName) && StringsKt.startsWith$default(str, kind.getClassNamePrefix(), false, 2, null)) {
                        return kind;
                    }
                }
                return null;
            }
        }

        protected Kind(FqName fqName, String str) {
            Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
            Intrinsics.checkParameterIsNotNull(str, "classNamePrefix");
            this.packageFqName = fqName;
            this.classNamePrefix = str;
        }

        @NotNull
        public final String getClassNamePrefix() {
            return this.classNamePrefix;
        }

        @NotNull
        public final FqName getPackageFqName() {
            return this.packageFqName;
        }

        static {
            Companion = new Companion(null);
        }

        @NotNull
        public final Name numberedClassName(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.classNamePrefix);
            sb.append(i);
            return Name.identifier(sb.toString());
        }
    }

    @Nullable
    public Void getCompanionObjectDescriptor() {
        return null;
    }

    @Nullable
    public Void getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    public boolean isActual() {
        return false;
    }

    public boolean isCompanionObject() {
        return false;
    }

    public boolean isData() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isExternal() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isInner() {
        return false;
    }

    @NotNull
    public final Kind getFunctionKind() {
        return this.functionKind;
    }

    public final int getArity() {
        return this.arity;
    }

    public FunctionClassDescriptor(@NotNull StorageManager storageManager2, @NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull Kind kind, int i) {
        Intrinsics.checkParameterIsNotNull(storageManager2, "storageManager");
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(kind, "functionKind");
        super(storageManager2, kind.numberedClassName(i));
        this.storageManager = storageManager2;
        this.containingDeclaration = packageFragmentDescriptor;
        this.functionKind = kind;
        this.arity = i;
        final ArrayList arrayList = new ArrayList();
        C29381 r6 = new Function2<Variance, String, Unit>(this) {
            final /* synthetic */ FunctionClassDescriptor this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Variance) obj, (String) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull Variance variance, @NotNull String str) {
                Intrinsics.checkParameterIsNotNull(variance, "variance");
                Intrinsics.checkParameterIsNotNull(str, "name");
                arrayList.add(TypeParameterDescriptorImpl.createWithDefaultBound(this.this$0, Annotations.Companion.getEMPTY(), false, variance, Name.identifier(str), arrayList.size()));
            }
        };
        Iterable intRange = new IntRange(1, this.arity);
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator it = intRange.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            Variance variance = Variance.IN_VARIANCE;
            StringBuilder sb = new StringBuilder();
            sb.append('P');
            sb.append(nextInt);
            r6.invoke(variance, sb.toString());
            arrayList2.add(Unit.INSTANCE);
        }
        List list = (List) arrayList2;
        r6.invoke(Variance.OUT_VARIANCE, "R");
        this.parameters = CollectionsKt.toList(arrayList);
    }

    @NotNull
    public PackageFragmentDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @NotNull
    public Empty getStaticScope() {
        return Empty.INSTANCE;
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @NotNull
    public FunctionClassScope getUnsubstitutedMemberScope() {
        return this.memberScope;
    }

    @NotNull
    public List<ClassConstructorDescriptor> getConstructors() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public ClassKind getKind() {
        return ClassKind.INTERFACE;
    }

    @NotNull
    public Modality getModality() {
        return Modality.ABSTRACT;
    }

    @NotNull
    public Visibility getVisibility() {
        return Visibilities.PUBLIC;
    }

    @NotNull
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @NotNull
    public SourceElement getSource() {
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        return sourceElement;
    }

    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.parameters;
    }

    @NotNull
    public String toString() {
        return getName().asString();
    }
}
