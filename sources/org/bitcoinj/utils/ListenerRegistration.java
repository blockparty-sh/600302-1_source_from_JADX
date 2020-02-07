package org.bitcoinj.utils;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class ListenerRegistration<T> {
    public final Executor executor;
    public final T listener;

    public ListenerRegistration(T t, Executor executor2) {
        this.listener = Preconditions.checkNotNull(t);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    public static <T> boolean removeFromList(T t, List<? extends ListenerRegistration<T>> list) {
        ListenerRegistration listenerRegistration;
        Preconditions.checkNotNull(t);
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                listenerRegistration = null;
                break;
            }
            listenerRegistration = (ListenerRegistration) it.next();
            if (listenerRegistration.listener == t) {
                break;
            }
        }
        return listenerRegistration != null && list.remove(listenerRegistration);
    }
}
