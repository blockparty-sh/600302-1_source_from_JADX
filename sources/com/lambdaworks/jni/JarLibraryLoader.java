package com.lambdaworks.jni;

import com.lambdaworks.jni.Platform.C2313OS;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLibraryLoader implements LibraryLoader {
    private final CodeSource codeSource;
    private final String libraryPath;

    /* renamed from: com.lambdaworks.jni.JarLibraryLoader$1 */
    static /* synthetic */ class C23121 {
        static final /* synthetic */ int[] $SwitchMap$com$lambdaworks$jni$Platform$OS = new int[C2313OS.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.lambdaworks.jni.Platform$OS[] r0 = com.lambdaworks.jni.Platform.C2313OS.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$lambdaworks$jni$Platform$OS = r0
                int[] r0 = $SwitchMap$com$lambdaworks$jni$Platform$OS     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.lambdaworks.jni.Platform$OS r1 = com.lambdaworks.jni.Platform.C2313OS.darwin     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$lambdaworks$jni$Platform$OS     // Catch:{ NoSuchFieldError -> 0x001f }
                com.lambdaworks.jni.Platform$OS r1 = com.lambdaworks.jni.Platform.C2313OS.linux     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$lambdaworks$jni$Platform$OS     // Catch:{ NoSuchFieldError -> 0x002a }
                com.lambdaworks.jni.Platform$OS r1 = com.lambdaworks.jni.Platform.C2313OS.freebsd     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lambdaworks.jni.JarLibraryLoader.C23121.<clinit>():void");
        }
    }

    public JarLibraryLoader() {
        this(JarLibraryLoader.class.getProtectionDomain().getCodeSource(), "lib");
    }

    public JarLibraryLoader(CodeSource codeSource2, String str) {
        this.codeSource = codeSource2;
        this.libraryPath = str;
    }

    public boolean load(String str, boolean z) {
        JarFile jarFile;
        boolean z2;
        try {
            Platform detect = Platform.detect();
            jarFile = new JarFile(this.codeSource.getLocation().getPath(), z);
            Iterator it = libCandidates(detect, str).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                JarEntry jarEntry = jarFile.getJarEntry((String) it.next());
                if (jarEntry != null) {
                    File extract = extract(str, jarFile.getInputStream(jarEntry));
                    System.load(extract.getAbsolutePath());
                    extract.delete();
                    z2 = true;
                    break;
                }
            }
            jarFile.close();
            return z2;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static File extract(String str, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        File createTempFile = File.createTempFile(str, "lib");
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    return createTempFile;
                }
            } catch (IOException e) {
                createTempFile.delete();
                throw e;
            } catch (Throwable th) {
                fileOutputStream.close();
                inputStream.close();
                throw th;
            }
        }
    }

    private List<String> libCandidates(Platform platform, String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append(this.libraryPath);
        String str2 = "/";
        sb.append(str2);
        sb.append(platform.arch);
        sb.append(str2);
        sb.append(platform.f641os);
        sb.append(str2);
        sb.append("lib");
        sb.append(str);
        int i = C23121.$SwitchMap$com$lambdaworks$jni$Platform$OS[platform.f641os.ordinal()];
        if (i == 1) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(sb);
            sb2.append(".dylib");
            arrayList.add(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb);
            sb3.append(".jnilib");
            arrayList.add(sb3.toString());
        } else if (i == 2 || i == 3) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb);
            sb4.append(".so");
            arrayList.add(sb4.toString());
        }
        return arrayList;
    }
}
