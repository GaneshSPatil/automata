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

    public String getName() {
        String name = new String();
        for (State state : this) {
            name += state.getName() + ",";
        }
        return name.substring(0, name.length() - 1);
    }
}
