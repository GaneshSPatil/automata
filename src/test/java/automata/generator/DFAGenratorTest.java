package automata.generator;

import automata.entity.*;
import automata.machine.DFA;
import automata.machine.Machine;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DFAGenratorTest {
    @Test
    public void shouldGenerateMac() throws Exception {
        ArrayList<String> states = new ArrayList<String>();
        states.add("q1");
        states.add("q2");
        ArrayList<String> alphabets = new ArrayList<String>();
        alphabets.add("0");
        HashMap<String, HashMap<String, String>> delta = new HashMap<String, HashMap<String, String>>();
        HashMap<String, String> transition = new HashMap<String, String>();
        transition.put("0", "q2");
        delta.put("q1", transition);
        String startState = "q1";
        ArrayList<String> finalStates = new ArrayList<String>();
        finalStates.add("q2");
        Machine dfa = new DFAGenrator().generate(states, alphabets, delta, startState, finalStates);

        States allStates = new States();
        final State q1 = new State("q1");
        final State q2 = new State("q2");
        allStates.add(q1);
        allStates.add(q2);

        State initialState = q1;

        States allFinalStates = new States();
        allFinalStates.add(q2);

        Alphabets allAlphabets = new Alphabets();
        final Alphabet zero = new Alphabet('0');
        allAlphabets.add(zero);

        Transitions transitions = new Transitions();
        transitions.put(q1, new HashMap<Alphabet, State>(){{put(zero, q2);}});
        Machine expectedDFA = new DFA(allStates, allAlphabets, transitions, initialState, allFinalStates);

        assertThat(dfa, is(expectedDFA));
    }
}