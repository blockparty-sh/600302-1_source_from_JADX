package com.leanplum.callbacks;

import com.leanplum.Var;

public abstract class VariableCallback<T> implements Runnable {
    private Var<T> variable;

    public abstract void handle(Var<T> var);

    public void setVariable(Var<T> var) {
        this.variable = var;
    }

    public void run() {
        handle(this.variable);
    }
}
