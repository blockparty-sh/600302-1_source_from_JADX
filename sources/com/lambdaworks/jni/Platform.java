package com.lambdaworks.jni;

import java.util.regex.Pattern;

public class Platform {
    public final Arch arch;

    /* renamed from: os */
    public final C2313OS f641os;

    public enum Arch {
        x86("x86|i386"),
        x86_64("x86_64|amd64");
        
        Pattern pattern;

        private Arch(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("\\A");
            sb.append(str);
            sb.append("\\Z");
            this.pattern = Pattern.compile(sb.toString(), 2);
        }
    }

    /* renamed from: com.lambdaworks.jni.Platform$OS */
    public enum C2313OS {
        darwin("darwin|mac os x"),
        freebsd(r3),
        linux(r4);
        
        Pattern pattern;

        private C2313OS(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("\\A");
            sb.append(str);
            sb.append("\\Z");
            this.pattern = Pattern.compile(sb.toString(), 2);
        }
    }

    private Platform(Arch arch2, C2313OS os) {
        this.arch = arch2;
        this.f641os = os;
    }

    public static Platform detect() throws UnsupportedPlatformException {
        Arch[] values;
        C2313OS[] values2;
        String property = System.getProperty("os.arch");
        String property2 = System.getProperty("os.name");
        for (Arch arch2 : Arch.values()) {
            if (arch2.pattern.matcher(property).matches()) {
                for (C2313OS os : C2313OS.values()) {
                    if (os.pattern.matcher(property2).matches()) {
                        return new Platform(arch2, os);
                    }
                }
                continue;
            }
        }
        throw new UnsupportedPlatformException(String.format("Unsupported platform %s %s", new Object[]{property, property2}));
    }
}
