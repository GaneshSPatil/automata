package automata.generator;

import automata.entity.*;
import automata.machine.DFA;
import automata.machine.Machine;

import java.util.ArrayList;
import java.util.HashMap;

public class DFAGenrator implements MachineGenerator {
    public DFAGenrator() {
    }

    public Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap<String, String>> delta, String startState, ArrayList<String> finalStates) {
        States allStates = new States();
        for (String state : states) {
            allStates.add(new State(state));
        }

        Alphabets allAlphabets = new Alphabets();
        for (String alphabet : alphabets) {
            allAlphabets.add(new Alphabet(alphabet));
        }

        Transitions transitions = new Transitions();
        for (String state : delta.keySet()) {
            HashMap<String, String> values = delta.get(state);
            for (String alphabet : values.keySet()) {
                HashMap<Alphabet, State> transitInfo = new HashMap<Alphabet, State>();
                transitInfo.put(new Alphabet(alphabet), new State(values.get(alphabet)));
                transitions.put(new State(state), transitInfo);
            }
        }

        States allFinalStates = new States();
        for (String state : finalStates) {
            allFinalStates.add(new State(state));
        }

        State initialState = new State(startState);
        return new DFA(allStates, allAlphabets, transitions, initialState, allFinalStates);
    }
}
