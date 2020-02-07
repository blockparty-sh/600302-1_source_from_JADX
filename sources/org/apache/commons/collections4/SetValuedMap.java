package org.apache.commons.collections4;

import java.util.Set;

public interface SetValuedMap<K, V> extends MultiValuedMap<K, V> {

    /* renamed from: org.apache.commons.collections4.SetValuedMap$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    Set<V> get(K k);

    Set<V> remove(Object obj);
}
