package com.htc.htcwalletsdk.Utils;

public class PropertiesWrapper {
    public static String getString(String str, String str2) {
        return System.getProperty(str, str2);
    }

    public static int getInt(String str, String str2) {
        return Integer.parseInt(System.getProperty(str, str2));
    }

    public static boolean getBoolean(String str, String str2) {
        return Boolean.parseBoolean(System.getProperty(str, str2));
    }

    public static String setString(String str, String str2) {
        return System.setProperty(str, str2);
    }

    public static int setInt(String str, int i) {
        return Integer.parseInt(System.setProperty(str, Integer.toString(i)));
    }

    public static boolean setBoolean(String str, boolean z) {
        return Boolean.parseBoolean(System.getProperty(str, Boolean.toString(z)));
    }
}
