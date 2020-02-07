package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: FunctionClassScope.kt */
public final class FunctionClassScope extends GivenFunctionsMemberScope {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Kind.values().length];

        static {
            $EnumSwitchMapping$0[Kind.Function.ordinal()] = 1;
            $EnumSwitchMapping$0[Kind.SuspendFunction.ordinal()] = 2;
        }
    }

    public FunctionClassScope(@NotNull StorageManager storageManager, @NotNull FunctionClassDescriptor functionClassDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(functionClassDescriptor, "containingClass");
        super(storageManager, functionClassDescriptor);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<FunctionDescriptor> computeDeclaredFunctions() {
        ClassDescriptor containingClass = getContainingClass();
        if (containingClass != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[((FunctionClassDescriptor) containingClass).getFunctionKind().ordinal()];
            if (i == 1) {
                return CollectionsKt.listOf(FunctionInvokeDescriptor.Factory.create((FunctionClassDescriptor) getContainingClass(), false));
            }
            if (i != 2) {
                return CollectionsKt.emptyList();
            }
            return CollectionsKt.listOf(FunctionInvokeDescriptor.Factory.create((FunctionClassDescriptor) getContainingClass(), true));
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.builtins.functions.FunctionClassDescriptor");
    }
}