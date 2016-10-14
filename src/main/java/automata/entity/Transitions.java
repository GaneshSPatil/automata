package automata.entity;

import java.util.HashMap;

public class Transitions extends HashMap<State, HashMap<Alphabet, State>>{
    public State transit(State currentState, Alphabet alphabet) {
        return this.get(currentState).get(alphabet);
    }
}
