package org.apache.commons.collections4;

import java.util.Set;

public interface BidiMap<K, V> extends IterableMap<K, V> {

    /* renamed from: org.apache.commons.collections4.BidiMap$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    K getKey(Object obj);

    BidiMap<V, K> inverseBidiMap();

    V put(K k, V v);

    K removeValue(Object obj);

    Set<V> values();
}
