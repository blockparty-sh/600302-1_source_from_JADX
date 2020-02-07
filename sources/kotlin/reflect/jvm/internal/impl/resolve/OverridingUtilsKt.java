package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;

/* compiled from: overridingUtils.kt */
public final class OverridingUtilsKt {
    @NotNull
    public static final <H> Collection<H> selectMostSpecificInEachOverridableGroup(@NotNull Collection<? extends H> collection, @NotNull Function1<? super H, ? extends CallableDescriptor> function1) {
        Intrinsics.checkParameterIsNotNull(collection, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "descriptorByHandle");
        if (collection.size() <= 1) {
            return collection;
        }
        LinkedList linkedList = new LinkedList(collection);
        SmartSet create = SmartSet.Companion.create();
        while (true) {
            Collection collection2 = linkedList;
            if (!(!collection2.isEmpty())) {
                return create;
            }
            Object first = CollectionsKt.first((List<? extends T>) linkedList);
            SmartSet create2 = SmartSet.Companion.create();
            Collection extractMembersOverridableInBothWays = OverridingUtil.extractMembersOverridableInBothWays(first, collection2, function1, new C3121x410e6287(create2));
            String str = "overridableGroup";
            if (extractMembersOverridableInBothWays.size() != 1 || !create2.isEmpty()) {
                Object selectMostSpecificMember = OverridingUtil.selectMostSpecificMember(extractMembersOverridableInBothWays, function1);
                Intrinsics.checkExpressionValueIsNotNull(selectMostSpecificMember, "mostSpecific");
                CallableDescriptor callableDescriptor = (CallableDescriptor) function1.invoke(selectMostSpecificMember);
                Intrinsics.checkExpressionValueIsNotNull(extractMembersOverridableInBothWays, str);
                for (Object next : extractMembersOverridableInBothWays) {
                    Intrinsics.checkExpressionValueIsNotNull(next, "it");
                    if (!OverridingUtil.isMoreSpecific(callableDescriptor, (CallableDescriptor) function1.invoke(next))) {
                        create2.add(next);
                    }
                }
                Collection collection3 = create2;
                if (!collection3.isEmpty()) {
                    create.addAll(collection3);
                }
                create.add(selectMostSpecificMember);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(extractMembersOverridableInBothWays, str);
                Object single = CollectionsKt.single((Iterable<? extends T>) extractMembersOverridableInBothWays);
                Intrinsics.checkExpressionValueIsNotNull(single, "overridableGroup.single()");
                create.add(single);
            }
        }
    }

    public static final <D extends CallableDescriptor> void retainMostSpecificInEachOverridableGroup(@NotNull Collection<D> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$receiver");
        Collection selectMostSpecificInEachOverridableGroup = selectMostSpecificInEachOverridableGroup(collection, C3120x48e71be5.INSTANCE);
        if (collection.size() != selectMostSpecificInEachOverridableGroup.size()) {
            collection.retainAll(selectMostSpecificInEachOverridableGroup);
        }
    }
}
