package automata.generator;

import automata.entity.*;
import automata.machine.DFA;
import automata.machine.Machine;

import java.util.ArrayList;
import java.util.HashMap;

public class GenerationHelper {

    States getStates(ArrayList<String> states) {
        States allStates = new States();
        for (String state : states) {
            allStates.add(new State(state));
        }
        return allStates;
    }

    Alphabets getAlphabets(ArrayList<String> alphabets) {
        Alphabets allAlphabets = new Alphabets();
        for (String alphabet : alphabets) {
            allAlphabets.add(new Alphabet(alphabet));
        }
        return allAlphabets;
    }

    Transitions getTransitions(HashMap<String, HashMap<String, String>> delta) {
        Transitions transitions = new Transitions();
        for (String state : delta.keySet()) {
            HashMap<String, String> values = delta.get(state);
            for (String alphabet : values.keySet()) {
                HashMap<Alphabet, State> transitInfo = new HashMap<Alphabet, State>();
                transitInfo.put(new Alphabet(alphabet), new State(values.get(alphabet)));
                transitions.put(new State(state), transitInfo);
            }
        }
        return transitions;
    }
}
