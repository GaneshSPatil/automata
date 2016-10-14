package automata.machine;

import automata.entity.*;
import automata.entity.nfa.Transitions;
import automata.machine.Machine;

public class NFA implements Machine {
    private final States states;
    private final Alphabets alphabets;
    private final Transitions transitions;
    private final State initialState;
    private final States finalStates;

    public NFA(States states, Alphabets alphabets, Transitions transitions, State initialState, States finalStates) {
        this.states = states;
        this.alphabets = alphabets;
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public boolean canAccept(String inputString) {
        States currentStates = new States();
        currentStates.add(initialState);

        Alphabets alphabets = Alphabets.fromString(inputString);

        for (Alphabet alphabet : alphabets) {
            States bufferedStates = new States();
            for (State state : currentStates) {
                bufferedStates.addAll(transitions.transit(state, alphabet));
            }
            currentStates = bufferedStates;
        }
        return finalStates.containsAny(currentStates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NFA)) return false;

        NFA nfa = (NFA) o;

        if (states != null ? !states.equals(nfa.states) : nfa.states != null) return false;
        if (alphabets != null ? !alphabets.equals(nfa.alphabets) : nfa.alphabets != null) return false;
        if (transitions != null ? !transitions.equals(nfa.transitions) : nfa.transitions != null) return false;
        if (initialState != null ? !initialState.equals(nfa.initialState) : nfa.initialState != null) return false;
        return finalStates != null ? finalStates.equals(nfa.finalStates) : nfa.finalStates == null;

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
