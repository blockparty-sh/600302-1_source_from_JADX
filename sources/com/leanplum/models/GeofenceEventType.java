package com.leanplum.models;

public enum GeofenceEventType {
    ENTER_REGION("enter_region"),
    EXIT_REGION("exit_region");
    
    private final String name;

    private GeofenceEventType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
