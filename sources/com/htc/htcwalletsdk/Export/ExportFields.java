package com.htc.htcwalletsdk.Export;

public class ExportFields {
    public static boolean bShowErrorDialog = true;
    public static boolean bTZ_support = false;
    public static int minServiceVer = 5;
    public static int minTzApiVer = 16842808;
    String[][][] History;
    private final String TAG = "ExportFields";

    public ExportFields() {
        String str = "0x0004";
        String[][] strArr = {new String[]{"1.2.0"}, new String[]{str}, new String[]{"0x01010005"}};
        String[][] strArr2 = {new String[]{"1.2.1"}, new String[]{str}, new String[]{"0x01010006"}};
        String str2 = "0x0005";
        String str3 = "0x01010038";
        this.History = new String[][][]{new String[][]{new String[]{"1.0.1"}, new String[]{"0x0001"}, new String[]{"0x01000000"}}, new String[][]{new String[]{"1.1.0"}, new String[]{"0x0003"}, new String[]{"0x01010001"}}, strArr, strArr2, new String[][]{new String[]{"1.2.3"}, new String[]{str2}, new String[]{"0x0101000e"}}, new String[][]{new String[]{"1.2.5"}, new String[]{str2}, new String[]{"0x01010011"}}, new String[][]{new String[]{"1.2.7"}, new String[]{str2}, new String[]{"0x01010017"}}, new String[][]{new String[]{"2.0.1"}, new String[]{str2}, new String[]{"0x01010031"}}, new String[][]{new String[]{"2.0.2"}, new String[]{str2}, new String[]{str3}}, new String[][]{new String[]{"2.2.2"}, new String[]{str2}, new String[]{str3}}};
    }
}
