package automata.generator;

import automata.entity.*;
import automata.machine.Machine;
import automata.machine.NFA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NFAGeneratorTest {
    @Test
    public void shouldCreateAnNFA() throws Exception {
        ArrayList<String> states = new ArrayList<String>();
        states.add("q1");
        states.add("q2");
        states.add("q3");
        states.add("q4");
        states.add("q5");

        ArrayList<String> alphabets = new ArrayList<String>();
        alphabets.add("*");
        alphabets.add("0");
        alphabets.add("1");

        HashMap<String, HashMap<String, String>> delta = new HashMap<String, HashMap<String, String>>();

        String startState = "q1";

        ArrayList<String> finalStates = new ArrayList<String>();
        finalStates.add("q4");
        finalStates.add("q5");

        Machine nfa = new NFAGenerator().generate(states, alphabets, delta, startState, finalStates);

        States allStates = new States();
        allStates.add(new State("q1"));
        allStates.add(new State("q2"));
        allStates.add(new State("q3"));
        allStates.add(new State("q4"));
        allStates.add(new State("q5"));

        Alphabets allAlphabets = new Alphabets();
        allAlphabets.add(new Alphabet("*"));
        allAlphabets.add(new Alphabet("0"));
        allAlphabets.add(new Alphabet("1"));

        Transitions transitions = new Transitions();

        State initialState = new State("q1");

        States allFinalStates = new States();
        allFinalStates.add(new State("q4"));
        allFinalStates.add(new State("q5"));

        Machine expectedNFA = new NFA(allStates, allAlphabets, transitions, initialState, allFinalStates);

        assertThat(nfa, is(expectedNFA));
    }


}