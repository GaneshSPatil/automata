package automata.entity.nfa;

import automata.entity.Alphabet;
import automata.entity.State;
import automata.entity.States;
import automata.machine.NFA;

import java.util.HashMap;

public class Transitions extends HashMap<State, HashMap<Alphabet, States>>{
    public States transit(State currentState, Alphabet alphabet) {
        States transitedStates = this.get(currentState) == null ? null : this.get(currentState).get(alphabet);
        return transitedStates == null? new States() : transitedStates;
    }

    public boolean hasEphsilonTransition(State state) {
        return this.get(state) == null ? false : this.get(state).keySet().contains(NFA.EPHSILON);
    }
}
