package com.lambdaworks.jni;

public class SysLibraryLoader implements LibraryLoader {
    public boolean load(String str, boolean z) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
