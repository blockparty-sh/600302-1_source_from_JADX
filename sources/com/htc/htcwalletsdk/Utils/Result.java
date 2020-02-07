package com.htc.htcwalletsdk.Utils;

public class Result {
    public int errCode;
    public String errMessage;
    public boolean success;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Result{success = ");
        sb.append(this.success);
        sb.append(", errCode = ");
        sb.append(this.errCode);
        sb.append(", errMessage = ");
        sb.append(this.errMessage);
        sb.append('}');
        return sb.toString();
    }
}
