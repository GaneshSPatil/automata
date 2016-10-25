package automata.generator;

import automata.machine.Machine;
import automata.machine.NFA;

import java.util.ArrayList;
import java.util.HashMap;

public class NFAToDFAGenerator implements MachineGenerator {
    public Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap> delta, String startState, ArrayList<String> finalStates) {
        NFA nfa = (NFA) new NFAGenerator().generate(states, alphabets, delta, startState, finalStates);
        return nfa.toDFA();
    }
}
