package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class JvmBuiltInsSettings$getFunctions$2 extends Lambda implements Function1<MemberScope, Collection<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ Name $name;

    JvmBuiltInsSettings$getFunctions$2(Name name) {
        this.$name = name;
        super(1);
    }

    @NotNull
    public final Collection<SimpleFunctionDescriptor> invoke(@NotNull MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(memberScope, "it");
        return memberScope.getContributedFunctions(this.$name, NoLookupLocation.FROM_BUILTINS);
    }
}
