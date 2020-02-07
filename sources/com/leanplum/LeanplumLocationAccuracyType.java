package com.leanplum;

public enum LeanplumLocationAccuracyType {
    IP(0),
    CELL(1),
    GPS(2);
    
    private int value;

    private LeanplumLocationAccuracyType(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }
}
