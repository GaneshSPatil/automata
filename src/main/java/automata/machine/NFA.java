package automata.machine;

import automata.entity.*;
import automata.entity.dfa.Transitions;

import java.util.Collection;
import java.util.HashMap;

public class NFA implements Machine {
    public static final Alphabet EPHSILON = new Alphabet("*");
    private final States states;
    private final Alphabets alphabets;
    private final automata.entity.nfa.Transitions transitions;
    private final State initialState;
    private final States finalStates;

    public NFA(States states, Alphabets alphabets, automata.entity.nfa.Transitions transitions, State initialState, States finalStates) {
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
            currentStates = getEphsilonStates(currentStates);
            States bufferedStates = new States();
            for (State state : currentStates) {
                bufferedStates.addAll(transitions.transit(state, alphabet));
            }
            currentStates = bufferedStates;
        }
        return finalStates.containsAnyOf(currentStates);
    }

    private States getEphsilonStates(States currentStates) {
        States states = new States();
        for (State state : currentStates) {
            if (hasElpsilonTransition(state)) {
                states.addAll(transitions.transit(state, EPHSILON));
            }
            states.add(state);
        }

        if (currentStates.containsAll(states) && states.containsAll(currentStates)) {
            return currentStates;
        } else {
            return getEphsilonStates(states);
        }
    }

    private boolean hasElpsilonTransition(State state) {
        return transitions.hasEphsilonTransition(state);
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

    public DFA toDFA() {
        HashMap<States, State> allCombinationsOfStates = getAllCombinations(states);
        States DFAStates = new States();
        DFAStates.addAll(allCombinationsOfStates.values());
//        Alphabets DFAAlphabets = this.alphabets.getAllAlphabetsForDFA();
        Alphabets DFAAlphabets = new Alphabets(){
            { add(new Alphabet("a"));}
            { add(new Alphabet("b"));}
        };
        Transitions DFATransitions = new Transitions();
        for (States combination : allCombinationsOfStates.keySet()) {
            HashMap<Alphabet, State> values = new HashMap<Alphabet, State>();
            for (Alphabet alphabet : DFAAlphabets) {
                States transitedStates = new States();
                for (State state : combination) {
                    transitedStates.addAll(getEphsilonStates(transitions.transit(state, alphabet)));
                }
                values.put(alphabet, allCombinationsOfStates.get(transitedStates));
            }
            DFATransitions.put(allCombinationsOfStates.get(combination), values);
        }
        State DFAInitialState = allCombinationsOfStates.get(getEphsilonStates(new States(){{ add(initialState);}}));
        States DFAFinalStates = getFinalStates(allCombinationsOfStates, this.finalStates);
        return new DFA(DFAStates, DFAAlphabets, DFATransitions, DFAInitialState, DFAFinalStates);
    }

    private States getFinalStates(HashMap<States, State> allCombinations, States finalStates) {
        States states = new States();
        for (State state : finalStates) {
            for (States combination : allCombinations.keySet()) {
                if(combination.contains(state)){
                    states.add(allCombinations.get(combination));
                }
            }
        }
        return states;
    }

    private HashMap<States, State> getAllCombinations(States states) {
        HashMap<States, State> combinations = new HashMap<States, State>();

        combinations.put(new States(){{ add(new State("q1")); }}, new State("q1"));
        combinations.put(new States(){{ add(new State("q2")); }}, new State("q2"));
        combinations.put(new States(){{ add(new State("q3")); }}, new State("q3"));

        combinations.put(new States(){
            { add(new State("q2")); }
            { add(new State("q3")); }
        }, new State("q2,q3"));

        combinations.put(new States(){
            { add(new State("q1")); }
            { add(new State("q3")); }
        }, new State("q1,q3"));

        combinations.put(new States(){
            { add(new State("q1")); }
            { add(new State("q2")); }
        }, new State("q1,q2"));

        combinations.put(new States(){
            { add(new State("q1")); }
            { add(new State("q2")); }
            { add(new State("q3")); }
        }, new State("q1,q2,q3"));

        combinations.put(new States(), new State(""));

        return combinations;
    }
}
