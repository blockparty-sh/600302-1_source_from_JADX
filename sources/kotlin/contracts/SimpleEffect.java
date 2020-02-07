package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;

@ExperimentalContracts
@SinceKotlin(version = "1.3")
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§\u0004¨\u0006\u0006"}, mo37405d2 = {"Lkotlin/contracts/SimpleEffect;", "Lkotlin/contracts/Effect;", "implies", "Lkotlin/contracts/ConditionalEffect;", "booleanExpression", "", "kotlin-stdlib"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@ContractsDsl
/* compiled from: Effect.kt */
public interface SimpleEffect extends Effect {
    @ExperimentalContracts
    @NotNull
    @ContractsDsl
    ConditionalEffect implies(boolean z);
}
