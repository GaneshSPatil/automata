package automata.generator;

import automata.entity.Alphabets;
import automata.entity.State;
import automata.entity.States;
import automata.entity.Transitions;
import automata.machine.Machine;
import automata.machine.NFA;

import java.util.ArrayList;
import java.util.HashMap;

public class NFAGenerator extends GenerationHelper implements MachineGenerator {
    public NFAGenerator() {
    }

    public Machine generate(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap<String, String>> delta, String startState, ArrayList<String> finalStates) {
        States allStates = getStates(states);
        Alphabets allAlphabets = getAlphabets(alphabets);
        Transitions transitions = getTransitions(delta);
        States allFinalStates = getStates(finalStates);
        State initialState = new State(startState);

        return new NFA(allStates, allAlphabets, transitions, initialState, allFinalStates);
    }
}
