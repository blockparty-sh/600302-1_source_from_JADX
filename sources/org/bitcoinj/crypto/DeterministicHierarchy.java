package org.bitcoinj.crypto;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DeterministicHierarchy {
    public static final int BIP32_STANDARDISATION_TIME_SECS = 1369267200;
    private final Map<ImmutableList<ChildNumber>, DeterministicKey> keys = Maps.newHashMap();
    private final Map<ImmutableList<ChildNumber>, ChildNumber> lastChildNumbers = Maps.newHashMap();
    private final ImmutableList<ChildNumber> rootPath;

    public DeterministicHierarchy(DeterministicKey deterministicKey) {
        putKey(deterministicKey);
        this.rootPath = deterministicKey.getPath();
    }

    public final void putKey(DeterministicKey deterministicKey) {
        ImmutableList path = deterministicKey.getPath();
        DeterministicKey parent = deterministicKey.getParent();
        if (parent != null) {
            this.lastChildNumbers.put(parent.getPath(), deterministicKey.getChildNumber());
        }
        this.keys.put(path, deterministicKey);
    }

    public DeterministicKey get(List<ChildNumber> list, boolean z, boolean z2) {
        ImmutableList immutableList;
        if (z) {
            immutableList = ImmutableList.builder().addAll((Iterable) this.rootPath).addAll((Iterable) list).build();
        } else {
            immutableList = ImmutableList.copyOf((Collection<? extends E>) list);
        }
        if (!this.keys.containsKey(immutableList)) {
            if (!z2) {
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = z ? "relative" : "absolute";
                objArr[1] = HDUtils.formatPath(list);
                throw new IllegalArgumentException(String.format(locale, "No key found for %s path %s.", objArr));
            }
            Preconditions.checkArgument(immutableList.size() > 0, "Can't derive the master key: nothing to derive from.");
            putKey(HDKeyDerivation.deriveChildKey(get(immutableList.subList(0, immutableList.size() - 1), false, true), (ChildNumber) immutableList.get(immutableList.size() - 1)));
        }
        return (DeterministicKey) this.keys.get(immutableList);
    }

    public DeterministicKey deriveNextChild(ImmutableList<ChildNumber> immutableList, boolean z, boolean z2, boolean z3) {
        DeterministicKey deterministicKey = get(immutableList, z, z2);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i < 100) {
                try {
                    return deriveChild(deterministicKey, getNextChildNumberToDerive(deterministicKey.getPath(), z3));
                } catch (HDDerivationException unused) {
                    i = i2;
                }
            } else {
                throw new HDDerivationException("Maximum number of child derivation attempts reached, this is probably an indication of a bug.");
            }
        }
    }

    private ChildNumber getNextChildNumberToDerive(ImmutableList<ChildNumber> immutableList, boolean z) {
        ChildNumber childNumber = (ChildNumber) this.lastChildNumbers.get(immutableList);
        ChildNumber childNumber2 = new ChildNumber(childNumber != null ? childNumber.num() + 1 : 0, z);
        this.lastChildNumbers.put(immutableList, childNumber2);
        return childNumber2;
    }

    public int getNumChildren(ImmutableList<ChildNumber> immutableList) {
        ChildNumber childNumber = (ChildNumber) this.lastChildNumbers.get(immutableList);
        if (childNumber == null) {
            return 0;
        }
        return childNumber.num() + 1;
    }

    public DeterministicKey deriveChild(List<ChildNumber> list, boolean z, boolean z2, ChildNumber childNumber) {
        return deriveChild(get(list, z, z2), childNumber);
    }

    private DeterministicKey deriveChild(DeterministicKey deterministicKey, ChildNumber childNumber) {
        DeterministicKey deriveChildKey = HDKeyDerivation.deriveChildKey(deterministicKey, childNumber);
        putKey(deriveChildKey);
        return deriveChildKey;
    }

    public DeterministicKey getRootKey() {
        return get(this.rootPath, false, false);
    }
}
