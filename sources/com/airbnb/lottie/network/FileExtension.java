package com.airbnb.lottie.network;

import com.airbnb.lottie.C0824L;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;

public enum FileExtension {
    JSON(ErrorLogHelper.ERROR_LOG_FILE_EXTENSION),
    ZIP(".zip");
    
    public final String extension;

    private FileExtension(String str) {
        this.extension = str;
    }

    public String tempExtension() {
        StringBuilder sb = new StringBuilder();
        sb.append(".temp");
        sb.append(this.extension);
        return sb.toString();
    }

    public String toString() {
        return this.extension;
    }

    public static FileExtension forFile(String str) {
        FileExtension[] values;
        for (FileExtension fileExtension : values()) {
            if (str.endsWith(fileExtension.extension)) {
                return fileExtension;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to find correct extension for ");
        sb.append(str);
        C0824L.warn(sb.toString());
        return JSON;
    }
}
