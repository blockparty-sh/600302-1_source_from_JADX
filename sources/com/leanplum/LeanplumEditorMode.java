package com.leanplum;

@Deprecated
public enum LeanplumEditorMode {
    LP_EDITOR_MODE_INTERFACE(0),
    LP_EDITOR_MODE_EVENT(1);
    
    private final int value;

    private LeanplumEditorMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
