package automata.entity;

public class Alphabet {
    private char alphabet;

    public Alphabet(char alphabet) {
        this.alphabet = alphabet;
    }

    public Alphabet(String alphabet) {
        this(alphabet.charAt(0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alphabet)) return false;

        Alphabet alphabet1 = (Alphabet) o;

        return alphabet == alphabet1.alphabet;

    }

    @Override
    public int hashCode() {
        return (int) alphabet;
    }
}
