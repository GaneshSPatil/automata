package automata.entity.dfa;

import automata.entity.Alphabet;
import automata.entity.State;

import java.util.HashMap;

public class Transitions extends HashMap<State, HashMap<Alphabet, State>>{
    public State transit(State currentState, Alphabet alphabet) {
        return this.get(currentState).get(alphabet);
    }
}
