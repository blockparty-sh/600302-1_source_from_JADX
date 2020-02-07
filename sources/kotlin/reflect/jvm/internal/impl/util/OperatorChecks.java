package kotlin.reflect.jvm.internal.impl.util;

import com.microsoft.appcenter.http.DefaultHttpClient;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.MemberKindCheck.Member;
import kotlin.reflect.jvm.internal.impl.util.MemberKindCheck.MemberOrExtension;
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean;
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt;
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck.AtLeast;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck.Equals;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck.NoValueParameters;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck.SingleValueParameter;
import org.jetbrains.annotations.NotNull;

/* compiled from: modifierChecks.kt */
public final class OperatorChecks extends AbstractModifierChecks {
    public static final OperatorChecks INSTANCE = new OperatorChecks();
    @NotNull
    private static final List<Checks> checks;

    static {
        Name name = OperatorNameConventions.GET;
        Intrinsics.checkExpressionValueIsNotNull(name, DefaultHttpClient.METHOD_GET);
        Checks checks2 = new Checks(name, new Check[]{MemberOrExtension.INSTANCE, new AtLeast(1)}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name2 = OperatorNameConventions.SET;
        Intrinsics.checkExpressionValueIsNotNull(name2, "SET");
        Check[] checkArr = {MemberOrExtension.INSTANCE, new AtLeast(2)};
        Name name3 = OperatorNameConventions.GET_VALUE;
        Intrinsics.checkExpressionValueIsNotNull(name3, "GET_VALUE");
        Checks checks3 = new Checks(name3, new Check[]{MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new AtLeast(2), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name4 = OperatorNameConventions.SET_VALUE;
        Intrinsics.checkExpressionValueIsNotNull(name4, "SET_VALUE");
        Checks checks4 = new Checks(name4, new Check[]{MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new AtLeast(3), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name5 = OperatorNameConventions.PROVIDE_DELEGATE;
        Intrinsics.checkExpressionValueIsNotNull(name5, "PROVIDE_DELEGATE");
        Checks checks5 = new Checks(name5, new Check[]{MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new Equals(2), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name6 = OperatorNameConventions.INVOKE;
        Intrinsics.checkExpressionValueIsNotNull(name6, "INVOKE");
        Checks checks6 = new Checks(name6, new Check[]{MemberOrExtension.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name7 = OperatorNameConventions.CONTAINS;
        Intrinsics.checkExpressionValueIsNotNull(name7, "CONTAINS");
        Checks checks7 = new Checks(name7, new Check[]{MemberOrExtension.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, ReturnsBoolean.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name8 = OperatorNameConventions.ITERATOR;
        Intrinsics.checkExpressionValueIsNotNull(name8, "ITERATOR");
        Checks checks8 = new Checks(name8, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name9 = OperatorNameConventions.NEXT;
        Intrinsics.checkExpressionValueIsNotNull(name9, "NEXT");
        Checks checks9 = new Checks(name9, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name10 = OperatorNameConventions.HAS_NEXT;
        Intrinsics.checkExpressionValueIsNotNull(name10, "HAS_NEXT");
        Checks checks10 = new Checks(name10, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE, ReturnsBoolean.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name11 = OperatorNameConventions.RANGE_TO;
        Intrinsics.checkExpressionValueIsNotNull(name11, "RANGE_TO");
        Checks checks11 = new Checks(name11, new Check[]{MemberOrExtension.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Name name12 = OperatorNameConventions.EQUALS;
        Intrinsics.checkExpressionValueIsNotNull(name12, "EQUALS");
        Check[] checkArr2 = {Member.INSTANCE};
        Name name13 = OperatorNameConventions.COMPARE_TO;
        Intrinsics.checkExpressionValueIsNotNull(name13, "COMPARE_TO");
        Checks checks12 = new Checks(name13, new Check[]{MemberOrExtension.INSTANCE, ReturnsInt.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Checks checks13 = new Checks(OperatorNameConventions.BINARY_OPERATION_NAMES, new Check[]{MemberOrExtension.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Checks checks14 = new Checks(OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Collection listOf = CollectionsKt.listOf(OperatorNameConventions.INC, OperatorNameConventions.DEC);
        Check[] checkArr3 = {MemberOrExtension.INSTANCE};
        Checks checks15 = new Checks(OperatorNameConventions.ASSIGNMENT_OPERATIONS, new Check[]{MemberOrExtension.INSTANCE, ReturnsUnit.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        Checks checks16 = new Checks(OperatorNameConventions.COMPONENT_REGEX, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null);
        checks = CollectionsKt.listOf(checks2, new Checks(name2, checkArr, (Function1<? super FunctionDescriptor, String>) OperatorChecks$checks$1.INSTANCE), checks3, checks4, checks5, checks6, checks7, checks8, checks9, checks10, checks11, new Checks(name12, checkArr2, (Function1<? super FunctionDescriptor, String>) OperatorChecks$checks$2.INSTANCE), checks12, checks13, checks14, new Checks(listOf, checkArr3, (Function1<? super FunctionDescriptor, String>) OperatorChecks$checks$3.INSTANCE), checks15, checks16);
    }

    private OperatorChecks() {
    }

    @NotNull
    public List<Checks> getChecks$descriptors() {
        return checks;
    }
}
