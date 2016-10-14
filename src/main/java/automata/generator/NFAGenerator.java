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
        for (String state : delta.keySet()) {
            HashMap<String, String> values = delta.get(state);
            for (String alphabet : values.keySet()) {

                HashMap<Alphabet, States> transitInfo = new HashMap<Alphabet, States>();
                States transitedStates = new States();
                transitedStates.add(new State(values.get(alphabet)));
                transitInfo.put(new Alphabet(alphabet), transitedStates);
                transitions.put(new State(state), transitInfo);
            }
        }
        return transitions;
    }
}
