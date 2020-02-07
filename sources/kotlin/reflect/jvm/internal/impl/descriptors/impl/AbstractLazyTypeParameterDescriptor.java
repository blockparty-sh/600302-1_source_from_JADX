package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractLazyTypeParameterDescriptor extends AbstractTypeParameterDescriptor {
    public AbstractLazyTypeParameterDescriptor(@NotNull StorageManager storageManager, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull Name name, @NotNull Variance variance, boolean z, int i, @NotNull SourceElement sourceElement, @NotNull SupertypeLoopChecker supertypeLoopChecker) {
        super(storageManager, declarationDescriptor, Annotations.Companion.getEMPTY(), name, variance, z, i, sourceElement, supertypeLoopChecker);
    }

    public String toString() {
        Object[] objArr = new Object[3];
        String str = "";
        objArr[0] = isReified() ? "reified " : str;
        if (getVariance() != Variance.INVARIANT) {
            StringBuilder sb = new StringBuilder();
            sb.append(getVariance());
            sb.append(" ");
            str = sb.toString();
        }
        objArr[1] = str;
        objArr[2] = getName();
        return String.format("%s%s%s", objArr);
    }
}
