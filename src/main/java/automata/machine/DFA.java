package automata.machine;

import automata.entity.*;
import automata.entity.dfa.Transitions;

public class DFA implements Machine {
    private final States states;
    private final Alphabets alphabets;
    private final Transitions transitions;
    private final State initialState;
    private final States finalStates;

    public DFA(States states, Alphabets alphabets, Transitions transitions, State initialState, States finalStates) {
        this.states = states;
        this.alphabets = alphabets;
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public boolean canAccept(String inputString) {
        State currentState = initialState;
        Alphabets alphabets = Alphabets.fromString(inputString);
        for (Alphabet alphabet : alphabets) {
             currentState = transitions.transit(currentState, alphabet);
        }
        return finalStates.contains(currentState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DFA)) return false;

        DFA dfa = (DFA) o;

        if (states != null ? !states.equals(dfa.states) : dfa.states != null) return false;
        if (alphabets != null ? !alphabets.equals(dfa.alphabets) : dfa.alphabets != null) return false;
        if (transitions != null ? !transitions.equals(dfa.transitions) : dfa.transitions != null) return false;
        if (initialState != null ? !initialState.equals(dfa.initialState) : dfa.initialState != null) return false;
        return finalStates != null ? finalStates.equals(dfa.finalStates) : dfa.finalStates == null;

    }

    @Override
    public int hashCode() {
        int result = states != null ? states.hashCode() : 0;
        result = 31 * result + (alphabets != null ? alphabets.hashCode() : 0);
        result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
        result = 31 * result + (initialState != null ? initialState.hashCode() : 0);
        result = 31 * result + (finalStates != null ? finalStates.hashCode() : 0);
        return result;
    }
}
