package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength;

public class ReferenceMap<K, V> extends AbstractReferenceMap<K, V> implements Serializable {
    private static final long serialVersionUID = 1555089888138299607L;

    public ReferenceMap() {
        super(ReferenceStrength.HARD, ReferenceStrength.SOFT, 16, 0.75f, false);
    }

    public ReferenceMap(ReferenceStrength referenceStrength, ReferenceStrength referenceStrength2) {
        super(referenceStrength, referenceStrength2, 16, 0.75f, false);
    }

    public ReferenceMap(ReferenceStrength referenceStrength, ReferenceStrength referenceStrength2, boolean z) {
        super(referenceStrength, referenceStrength2, 16, 0.75f, z);
    }

    public ReferenceMap(ReferenceStrength referenceStrength, ReferenceStrength referenceStrength2, int i, float f) {
        super(referenceStrength, referenceStrength2, i, f, false);
    }

    public ReferenceMap(ReferenceStrength referenceStrength, ReferenceStrength referenceStrength2, int i, float f, boolean z) {
        super(referenceStrength, referenceStrength2, i, f, z);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        doReadObject(objectInputStream);
    }
}
