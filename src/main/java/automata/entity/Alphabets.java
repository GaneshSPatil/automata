package automata.entity;

import automata.machine.NFA;

import java.util.ArrayList;

public class Alphabets extends ArrayList<Alphabet> {
    public static Alphabets fromString(String inputString) {
        Alphabets alphabets = new Alphabets();
        char[] chars = inputString.toCharArray();
        for (char input : chars) {
            alphabets.add(new Alphabet(input));
        }
        return alphabets;
    }

    public Alphabets getAllAlphabetsForDFA(){
        Alphabets alphabets = new Alphabets();
        for (Alphabet alphabet : this) {
            if(!alphabet.equals(NFA.EPHSILON)){
                alphabets.add(alphabet);
            }
        }
        return alphabets;
    };
}
