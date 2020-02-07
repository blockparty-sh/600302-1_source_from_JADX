package org.bitcoinj.protocols.channels;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.lang.Enum;
import java.util.Locale;

public class StateMachine<State extends Enum<State>> {
    private State currentState;
    private final Multimap<State, State> transitions;

    public StateMachine(State state, Multimap<State, State> multimap) {
        this.currentState = (Enum) Preconditions.checkNotNull(state);
        this.transitions = (Multimap) Preconditions.checkNotNull(multimap);
    }

    public synchronized void checkState(State state) throws IllegalStateException {
        if (state != this.currentState) {
            throw new IllegalStateException(String.format(Locale.US, "Expected state %s, but in state %s", new Object[]{state, this.currentState}));
        }
    }

    public synchronized void checkState(State... stateArr) throws IllegalStateException {
        int length = stateArr.length;
        int i = 0;
        while (i < length) {
            if (!stateArr[i].equals(this.currentState)) {
                i++;
            }
        }
        throw new IllegalStateException(String.format(Locale.US, "Expected states %s, but in state %s", new Object[]{Lists.newArrayList((E[]) stateArr), this.currentState}));
    }

    public synchronized void transition(State state) throws IllegalStateException {
        if (this.transitions.containsEntry(this.currentState, state)) {
            this.currentState = state;
        } else {
            throw new IllegalStateException(String.format(Locale.US, "Attempted invalid transition from %s to %s", new Object[]{this.currentState, state}));
        }
    }

    public synchronized State getState() {
        return this.currentState;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(getState());
        sb.append(']');
        return sb.toString();
    }
}
