package parser;

import java.util.ArrayList;
import java.util.HashMap;
//{"name":"odd number of zeroes",
// "type":"dfa",
// "tuple":{"states":["q1","q2"],"alphabets":["1","0"],"delta":{"q1":{"0":"q2","1":"q1"},"q2":{"0":"q1","1":"q2"}},"start-state":"q1","final-states":["q2"]},
// "pass-cases":["0","000","00000","10","101010","010101"],
// "fail-cases":["00","0000","1001","1010","001100"]}

public class JSONSkeleton {
    private final String name;
    private final String type;
    private final TupleSkeleton tuple;
    private final ArrayList<String> pass;
    private ArrayList<String> fail;

    public JSONSkeleton(String name, String type, TupleSkeleton tuple, ArrayList<String> pass, ArrayList<String> fail) {
        this.name = name;
        this.type = type;
        this.tuple = tuple;
        this.pass = pass;
        this.fail = fail;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public TupleSkeleton getTuple() {
        return tuple;
    }

    public ArrayList<String> getPassingStrings() {
        return pass;
    }

    public ArrayList<String> getFailingStrings() {
        return fail;
    }
}
