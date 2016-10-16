package automata.generator;

import automata.entity.*;
import automata.entity.dfa.Transitions;

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

}
