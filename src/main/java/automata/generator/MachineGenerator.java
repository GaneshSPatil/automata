package automata.generator;

import automata.machine.Machine;

import java.util.ArrayList;
import java.util.HashMap;

public interface MachineGenerator {
    Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap<String, String>> delta, String startState, ArrayList<String> finalStates);
}
