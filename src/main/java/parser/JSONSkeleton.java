package parser;

import java.util.ArrayList;

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
