package automata.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Alphabets extends ArrayList<Alphabet> {
    public static Alphabets fromString(String inputString) {
        Alphabets alphabets = new Alphabets();
        char[] chars = inputString.toCharArray();
        for (char input : chars) {
            alphabets.add(new Alphabet(input));
        }
        return alphabets;
    }
}
