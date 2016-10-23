package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class TupleSkeleton {
    private final ArrayList<String> states;
    private final ArrayList<String> alphabets;
    private final HashMap<String, HashMap> delta;
    private final String startState;
    private final ArrayList<String> finalStates;

    public TupleSkeleton(ArrayList<String> states, ArrayList<String> alphabets, HashMap<String, HashMap> delta, String startState, ArrayList<String> finalStates) {
        this.states = states;
        this.alphabets = alphabets;
        this.delta = delta;
        this.startState = startState;
        this.finalStates = finalStates;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public ArrayList<String> getAlphabets() {
        return alphabets;
    }

    public HashMap<String, HashMap> getDelta() {
        return delta;
    }

    public String getStartState() {
        return startState;
    }

    public ArrayList<String> getFinalStates() {
        return finalStates;
    }
}
