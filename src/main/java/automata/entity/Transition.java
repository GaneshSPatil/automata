package automata.entity;

public class Transition {
    private final State current;
    private final Alphabet alphabet;
    private final State transitedState;

    public Transition(State current, Alphabet alphabet, State transitedState) {
        this.current = current;
        this.alphabet = alphabet;
        this.transitedState = transitedState;
    }

    public Transition(String current, String alphabet, String transited) {
        this(new State(current), new Alphabet(alphabet), new State(transited));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transition)) return false;

        Transition that = (Transition) o;

        if (current != null ? !current.equals(that.current) : that.current != null) return false;
        if (alphabet != null ? !alphabet.equals(that.alphabet) : that.alphabet != null) return false;
        return transitedState != null ? transitedState.equals(that.transitedState) : that.transitedState == null;
    }

    @Override
    public int hashCode() {
        int result = current != null ? current.hashCode() : 0;
        result = 31 * result + (alphabet != null ? alphabet.hashCode() : 0);
        result = 31 * result + (transitedState != null ? transitedState.hashCode() : 0);
        return result;
    }
}
