package automata.generator;

import automata.entity.Alphabet;
import automata.entity.Alphabets;
import automata.entity.State;
import automata.entity.States;
import automata.entity.nfa.Transitions;
import automata.machine.Machine;
import automata.machine.NFA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class NFAGenerator extends GenerationHelper implements MachineGenerator {
    public NFAGenerator() {
    }

    public Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap> delta, String startState, ArrayList<String> finalStates) {
        States allStates = getStates(states);
        Alphabets allAlphabets = getAlphabets(alphabets);
        Transitions transitions = getTransitions(delta);
        States allFinalStates = getStates(finalStates);
        State initialState = new State(startState);

        return new NFA(allStates, allAlphabets, transitions, initialState, allFinalStates);
    }

    private Transitions getTransitions(HashMap<String, HashMap> delta) {
        Transitions transitions = new Transitions();
        ArrayList<String> unprocessedTransitions = new ArrayList<String>();
        for (String state : delta.keySet()) {
            HashMap<String, HashSet<String>> values = delta.get(state);
            for (String alphabet : values.keySet()) {
                if(alphabet == "*"){
                    unprocessedTransitions.add(state);
                    continue;
                }
                HashMap<Alphabet, States> transitInfo = new HashMap<Alphabet, States>();
                States transitedStates = new States();
                for (String s : values.get(alphabet)) {
                    transitedStates.add(new State(s));
                }
                transitInfo.put(new Alphabet(alphabet), transitedStates);
                transitions.put(new State(state), transitInfo);
            }
        }

        for (String state : unprocessedTransitions) {
            HashSet<String> transitedStates = (HashSet<String>) delta.get(state).get("*");
            HashMap<Alphabet, States> values = new HashMap<Alphabet, States>();
            for (String transitedState : transitedStates) {
                 values.putAll(transitions.get(new State(transitedState)));
            }
            transitions.put(new State(state), values);
        }

        return transitions;
    }
}
