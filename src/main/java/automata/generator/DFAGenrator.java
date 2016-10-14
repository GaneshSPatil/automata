package automata.generator;

import automata.entity.*;
import automata.machine.DFA;
import automata.machine.Machine;

import java.util.ArrayList;
import java.util.HashMap;

public class DFAGenrator extends GenerationHelper implements MachineGenerator {
    public DFAGenrator() {
    }

    public Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap<String, String>> delta, String startState, ArrayList<String> finalStates) {
        States allStates = getStates(states);
        Alphabets allAlphabets = getAlphabets(alphabets);
        Transitions transitions = getTransitions(delta);
        States allFinalStates = getStates(finalStates);
        State initialState = new State(startState);

        return new DFA(allStates, allAlphabets, transitions, initialState, allFinalStates);
    }
}
