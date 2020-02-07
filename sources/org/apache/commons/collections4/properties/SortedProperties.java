package org.apache.commons.collections4.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.collections4.iterators.IteratorEnumeration;

public class SortedProperties extends Properties {
    private static final long serialVersionUID = 1;

    public synchronized Enumeration<Object> keys() {
        ArrayList arrayList;
        Set<Object> keySet = keySet();
        arrayList = new ArrayList(keySet.size());
        for (Object obj : keySet) {
            arrayList.add(obj.toString());
        }
        Collections.sort(arrayList);
        return new IteratorEnumeration(arrayList.iterator());
    }
}
