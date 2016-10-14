package automata.entity;

import java.util.HashSet;

public class States extends HashSet<State>{

    public boolean containsAnyOf(States currentStates) {
        for (State currentState : currentStates) {
            if(this.contains(currentState)){
                return true;
            }
        }
        return false;
    }
}
