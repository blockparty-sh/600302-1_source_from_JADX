package org.apache.commons.collections4;

import java.util.List;

public interface ListValuedMap<K, V> extends MultiValuedMap<K, V> {

    /* renamed from: org.apache.commons.collections4.ListValuedMap$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    List<V> get(K k);

    List<V> remove(Object obj);
}
