package p014go;

import p014go.Seq.Proxy;

/* renamed from: go.Universe */
public abstract class Universe {

    /* renamed from: go.Universe$proxyerror */
    private static final class proxyerror extends Exception implements Proxy, C2657error {
        private final int refnum;

        public native String error();

        public final int incRefnum() {
            Seq.incGoRef(this.refnum, this);
            return this.refnum;
        }

        proxyerror(int i) {
            this.refnum = i;
            Seq.trackGoRef(i, this);
        }

        public String getMessage() {
            return error();
        }
    }

    private static native void _init();

    public static void touch() {
    }

    static {
        Seq.touch();
        _init();
    }

    private Universe() {
    }
}
