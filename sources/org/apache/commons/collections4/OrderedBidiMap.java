package org.apache.commons.collections4;

public interface OrderedBidiMap<K, V> extends BidiMap<K, V>, OrderedMap<K, V> {

    /* renamed from: org.apache.commons.collections4.OrderedBidiMap$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    OrderedBidiMap<V, K> inverseBidiMap();
}
