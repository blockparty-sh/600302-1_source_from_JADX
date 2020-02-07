package com.lambdaworks.jni;

import androidx.core.app.NotificationCompat;

public class LibraryLoaders {
    public static LibraryLoader loader() {
        String property = System.getProperty("com.lambdaworks.jni.loader");
        if (property == null) {
            return System.getProperty("java.vm.specification.name").startsWith("Java") ? new JarLibraryLoader() : new SysLibraryLoader();
        } else if (property.equals(NotificationCompat.CATEGORY_SYSTEM)) {
            return new SysLibraryLoader();
        } else {
            if (property.equals("nil")) {
                return new NilLibraryLoader();
            }
            if (property.equals("jar")) {
                return new JarLibraryLoader();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal value for com.lambdaworks.jni.loader: ");
            sb.append(property);
            throw new IllegalStateException(sb.toString());
        }
    }
}
