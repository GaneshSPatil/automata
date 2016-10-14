package automata.entity.nfa;

import automata.entity.Alphabet;
import automata.entity.State;
import automata.entity.States;

import java.util.HashMap;

public class Transitions extends HashMap<State, HashMap<Alphabet, States>>{
    public States transit(State currentState, Alphabet alphabet) {
        States transitedStates = this.get(currentState).get(alphabet);
        return transitedStates == null ? new States() : transitedStates;
    }
}
