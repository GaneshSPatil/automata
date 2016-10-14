package automata.generator;

import automata.entity.Alphabet;
import automata.entity.Alphabets;
import automata.entity.State;
import automata.entity.States;
import automata.entity.dfa.Transitions;
import automata.machine.DFA;
import automata.machine.Machine;

import java.util.ArrayList;
import java.util.HashMap;

public class DFAGenrator extends GenerationHelper implements MachineGenerator {
    public DFAGenrator() {
    }

    public Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap> delta, String startState, ArrayList<String> finalStates) {
        States allStates = getStates(states);
        Alphabets allAlphabets = getAlphabets(alphabets);
        Transitions transitions = getTransitions(delta);
        States allFinalStates = getStates(finalStates);
        State initialState = new State(startState);

        return new DFA(allStates, allAlphabets, transitions, initialState, allFinalStates);
    }

    Transitions getTransitions(HashMap<String, HashMap> delta) {
        Transitions transitions = new Transitions();
        for (String state : delta.keySet()) {
            HashMap<String, String> values = delta.get(state);
            HashMap<Alphabet, State> transitInfo = new HashMap<Alphabet, State>();
            for (String alphabet : values.keySet()) {
                transitInfo.put(new Alphabet(alphabet), new State(values.get(alphabet)));
            }
            transitions.put(new State(state), transitInfo);
        }
        return transitions;
    }
}
