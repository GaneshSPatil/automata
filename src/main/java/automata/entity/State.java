package automata.entity;

public class State {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;

        State state = (State) o;

        return name != null ? name.equals(state.name) : state.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public State(String name) {
        this.name = name;
    }
}
