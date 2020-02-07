package com.microsoft.appcenter.ingestion.models;

import java.util.List;

public class LogContainer {
    private List<Log> logs;

    public List<Log> getLogs() {
        return this.logs;
    }

    public void setLogs(List<Log> list) {
        this.logs = list;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LogContainer logContainer = (LogContainer) obj;
        List<Log> list = this.logs;
        if (list != null) {
            z = list.equals(logContainer.logs);
        } else if (logContainer.logs != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        List<Log> list = this.logs;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }
}
