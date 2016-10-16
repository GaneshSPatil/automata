package automata.generator;

import automata.entity.Alphabet;
import automata.entity.Alphabets;
import automata.entity.State;
import automata.entity.States;
import automata.entity.nfa.Transitions;
import automata.machine.Machine;
import automata.machine.NFA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

        HashMap<String, HashMap> delta = new HashMap<String, HashMap>();
        delta.put("q1", new HashMap<String, HashSet<String>>(){{
            put("*", new HashSet<String>(){{
                add("q2");
                add("q3");
            }});
        }});
        delta.put("q2", new HashMap<String, HashSet<String>>(){{put("0", new HashSet<String>(){{add("q4");}});}});
        delta.put("q3", new HashMap<String, HashSet<String>>(){{put("1", new HashSet<String>(){{add("q5");}});}});
        delta.put("q4", new HashMap<String, HashSet<String>>(){{put("0", new HashSet<String>(){{add("q4");}});}});
        delta.put("q5", new HashMap<String, HashSet<String>>(){{put("1", new HashSet<String>(){{add("q5");}});}});

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

        transitions.put(new State("q1"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("*"), new States(){
                {add(new State("q2"));}
                {add(new State("q3"));}
            });}
        });

        transitions.put(new State("q2"), new HashMap<Alphabet, States>(){{put(new Alphabet("0"), new States(){{add(new State("q4"));}});}});
        transitions.put(new State("q3"), new HashMap<Alphabet, States>(){{put(new Alphabet("1"), new States(){{add(new State("q5"));}});}});
        transitions.put(new State("q4"), new HashMap<Alphabet, States>(){{put(new Alphabet("0"), new States(){{add(new State("q4"));}});}});
        transitions.put(new State("q5"), new HashMap<Alphabet, States>(){{put(new Alphabet("1"), new States(){{add(new State("q5"));}});}});

        State initialState = new State("q1");

        States allFinalStates = new States();
        allFinalStates.add(new State("q4"));
        allFinalStates.add(new State("q5"));

        Machine expectedNFA = new NFA(allStates, allAlphabets, transitions, initialState, allFinalStates);

        assertThat(nfa, is(expectedNFA));
    }


}