package automata.machine;

import automata.entity.Alphabet;
import automata.entity.Alphabets;
import automata.entity.State;
import automata.entity.States;
import automata.entity.dfa.Transitions;

import java.util.HashMap;
import java.util.List;

import static combination.CombinationGenerator.getCombinationsOf;

public class NFAToDFAConverter {
    public static DFA convert(NFA nfa, States states, final State initialState, States finalStates, Alphabets DFAAlphabets, automata.entity.nfa.Transitions transitions) {
        HashMap<States, State> allCombinationsOfStates = getAllCombinations(states);
        States DFAStates = new States();
        DFAStates.addAll(allCombinationsOfStates.values());
        Transitions DFATransitions = getTransitions(nfa, DFAAlphabets, transitions, allCombinationsOfStates);
        State DFAInitialState = allCombinationsOfStates.get(nfa.getEphsilonStates(new States() {{add(initialState);}}));
        States DFAFinalStates = getFinalStates(allCombinationsOfStates, finalStates);
        DFAStates = filterUslessStates(DFAStates, DFATransitions, DFAInitialState);
        DFAFinalStates = filterUslessFinalStates(DFAStates, DFAFinalStates);
        DFATransitions = filterTransitions(DFATransitions, DFAStates);
        return new DFA(DFAStates, DFAAlphabets, DFATransitions, DFAInitialState, DFAFinalStates);
    }

    private static States filterUslessFinalStates(States states, States finalStates) {
        States DFAFinalStates = new States();
        for (State state : finalStates) {
            if(states.contains(state)){
                DFAFinalStates.add(state);
            }
        }
        return DFAFinalStates;
    }

    private static Transitions filterTransitions(Transitions dfaTransitions, States DFAStates) {
        Transitions transitions = new Transitions();
        for (State state : dfaTransitions.keySet()) {
            if (DFAStates.contains(state)) {
                transitions.put(state, dfaTransitions.get(state));
            }
        }
        return transitions;
    }

    private static States filterUslessStates(States dfaStates, Transitions transitions, State initialState) {
        HashMap<State, Integer> countMap = getIncomingTransitionCountMap(dfaStates, transitions);
        States states = new States();
        for (State state : dfaStates) {
            if (countMap.get(state) != null) {
                states.add(state);
            } else if (initialState.equals(state)) {
                states.add(state);
            }
        }
        return states;
    }

    public static HashMap<State, Integer> getIncomingTransitionCountMap(States dfaStates, Transitions transitions) {
        HashMap<State, Integer> countMap = new HashMap<State, Integer>();
        if(transitions.size() == 0)
            return countMap;
        for (State state : dfaStates) {
            HashMap<Alphabet, State> transitionMap = transitions.get(state);
            for (State transitedState : transitionMap.values()) {
                if(transitedState.equals(state)){
                    continue;
                }
                Integer count = countMap.get(transitedState);
                if (count == null) {
                    countMap.put(transitedState, 1);
                } else {
                    countMap.put(transitedState, count++);
                }
            }
        }
        return countMap;
    }

    private static Transitions getTransitions(NFA nfa, Alphabets DFAAlphabets, automata.entity.nfa.Transitions transitions, HashMap<States, State> allCombinationsOfStates) {
        Transitions DFATransitions = new Transitions();
        if (transitions.size() == 0) {
            return DFATransitions;
        }
        for (States combination : allCombinationsOfStates.keySet()) {
            HashMap<Alphabet, State> values = new HashMap<Alphabet, State>();
            for (Alphabet alphabet : DFAAlphabets) {
                States transitedStates = new States();
                for (State state : combination) {
                    transitedStates.addAll(nfa.getEphsilonStates(transitions.transit(state, alphabet)));
                }
                values.put(alphabet, allCombinationsOfStates.get(transitedStates));
            }
            DFATransitions.put(allCombinationsOfStates.get(combination), values);
        }
        return DFATransitions;
    }


    private static States getFinalStates(HashMap<States, State> allCombinations, States finalStates) {
        States states = new States();
        for (States combination : allCombinations.keySet()) {
            if (combination.containsAnyOf(finalStates)) {
                states.add(allCombinations.get(combination));
            }
        }
        return states;
    }

    private static HashMap<States, State> getAllCombinations(States states) {
        List<States> allCombinations = getCombinationsOf(states);
        HashMap<States, State> combinations = new HashMap<States, State>();

        combinations.put(new States(), new State(""));
        for (States combination : allCombinations) {
            String newName = combination.getName();
            combinations.put(combination, new State(newName));
        }

        return combinations;
    }
}
