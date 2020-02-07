package kotlin.reflect.jvm.internal.impl.name;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FqNamesUtil.kt */
public final class FqNamesUtilKt {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[State.values().length];

        static {
            $EnumSwitchMapping$0[State.BEGINNING.ordinal()] = 1;
            $EnumSwitchMapping$0[State.AFTER_DOT.ordinal()] = 2;
            $EnumSwitchMapping$0[State.MIDDLE.ordinal()] = 3;
        }
    }

    public static final boolean isSubpackageOf(@NotNull FqName fqName, @NotNull FqName fqName2) {
        Intrinsics.checkParameterIsNotNull(fqName, "$receiver");
        Intrinsics.checkParameterIsNotNull(fqName2, "packageName");
        if (Intrinsics.areEqual((Object) fqName, (Object) fqName2) || fqName2.isRoot()) {
            return true;
        }
        String asString = fqName.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "this.asString()");
        String asString2 = fqName2.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "packageName.asString()");
        return isSubpackageOf(asString, asString2);
    }

    private static final boolean isSubpackageOf(String str, String str2) {
        return StringsKt.startsWith$default(str, str2, false, 2, null) && str.charAt(str2.length()) == '.';
    }

    @NotNull
    public static final FqName tail(@NotNull FqName fqName, @NotNull FqName fqName2) {
        Intrinsics.checkParameterIsNotNull(fqName, "$receiver");
        Intrinsics.checkParameterIsNotNull(fqName2, "prefix");
        if (!isSubpackageOf(fqName, fqName2) || fqName2.isRoot()) {
            return fqName;
        }
        if (Intrinsics.areEqual((Object) fqName, (Object) fqName2)) {
            FqName fqName3 = FqName.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(fqName3, "FqName.ROOT");
            return fqName3;
        }
        String asString = fqName.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "asString()");
        int length = fqName2.asString().length() + 1;
        if (asString != null) {
            String substring = asString.substring(length);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return new FqName(substring);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static final boolean isValidJavaFqName(@Nullable String str) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        State state = State.BEGINNING;
        int length = str.length();
        State state2 = state;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[state2.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (!Character.isJavaIdentifierPart(charAt)) {
                    return false;
                }
                state2 = State.MIDDLE;
            } else if (i2 != 3) {
                continue;
            } else if (charAt == '.') {
                state2 = State.AFTER_DOT;
            } else if (!Character.isJavaIdentifierPart(charAt)) {
                return false;
            }
        }
        if (state2 != State.AFTER_DOT) {
            z = true;
        }
        return z;
    }
}
