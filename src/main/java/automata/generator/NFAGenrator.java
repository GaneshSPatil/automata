package automata.generator;

import automata.machine.Machine;
import automata.machine.NFA;

import java.util.ArrayList;
import java.util.HashMap;

public class NFAGenrator implements MachineGenerator {
    public Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap<String, String>> delta, String startState, ArrayList<String> finalStates) {
        return new NFA();
    }
}
