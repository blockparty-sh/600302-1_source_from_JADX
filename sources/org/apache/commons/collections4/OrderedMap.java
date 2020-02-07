package org.apache.commons.collections4;

public interface OrderedMap<K, V> extends IterableMap<K, V> {

    /* renamed from: org.apache.commons.collections4.OrderedMap$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    K firstKey();

    K lastKey();

    OrderedMapIterator<K, V> mapIterator();

    K nextKey(K k);

    K previousKey(K k);
}
