package automata.entity;

import java.util.LinkedHashSet;

public class States extends LinkedHashSet<State> {

    public boolean containsAnyOf(States currentStates) {
        for (State currentState : currentStates) {
            if (this.contains(currentState)) {
                return true;
            }
        }
        return false;
    }
}
