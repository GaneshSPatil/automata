package automata.generator;

import automata.entity.Alphabet;
import automata.entity.Alphabets;
import automata.entity.State;
import automata.entity.States;
import automata.machine.DFA;
import automata.machine.Machine;
import automata.machine.NFA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NFAToDFAGeneratorTest {
    @Test
    public void shouldCreateDFAFromNFA_NFAToDFAConverter() throws Exception {
        ArrayList<String> states = new ArrayList<String>();
        states.add("q1");
        states.add("q2");
        states.add("q3");

        ArrayList<String> alphabets = new ArrayList<String>();
        alphabets.add("*");
        alphabets.add("a");
        alphabets.add("b");

        HashMap<String, HashMap> delta = new HashMap<String, HashMap>();
        delta.put("q1", new HashMap<String, ArrayList<String>>(){
            {put("*", new ArrayList<String>(){{ add("q3");}});}
            {put("b", new ArrayList<String>(){{ add("q2");}});}
        });
        delta.put("q2", new HashMap<String, ArrayList<String>>(){
            {put("a", new ArrayList<String>(){
                { add("q2");}
                { add("q3");}
            });}
            {put("b", new ArrayList<String>(){{ add("q3");}});}
        });
        delta.put("q3", new HashMap<String, ArrayList<String>>(){
            {put("a", new ArrayList<String>(){{ add("q1");}});}
        });

        String startState = "q1";

        ArrayList<String> finalStates = new ArrayList<String>();
        finalStates.add("q1");

        Machine classroomNFAtoDFAExample = ((NFA)new NFAGenerator().generate(states, alphabets, delta, startState, finalStates)).toDFA();

        States expectedStates = new States();
        expectedStates.add(new State(""));
        expectedStates.add(new State("q2"));
        expectedStates.add(new State("q3"));
        expectedStates.add(new State("q1,q2,q3"));
        expectedStates.add(new State("q1,q3"));
        expectedStates.add(new State("q2,q3"));

        States expectedFinalStates = new States();
        expectedFinalStates.add(new State("q1,q2,q3"));
        expectedFinalStates.add(new State("q1,q3"));

        Alphabets expectedAlphabets = new Alphabets();
        expectedAlphabets.add(new Alphabet("a"));
        expectedAlphabets.add(new Alphabet("b"));

        automata.entity.dfa.Transitions expectedTransitions = new automata.entity.dfa.Transitions();
        expectedTransitions.put(new State("q2"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("b"), new State("q3"));}
            {put(new Alphabet("a"), new State("q2,q3"));}
        });
        expectedTransitions.put(new State("q3"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("b"), new State(""));}
            {put(new Alphabet("a"), new State("q1,q3"));}
        });
        expectedTransitions.put(new State(""), new HashMap<Alphabet, State>(){
            {put(new Alphabet("b"), new State(""));}
            {put(new Alphabet("a"), new State(""));}
        });
        expectedTransitions.put(new State("q1,q3"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("b"), new State("q2"));}
            {put(new Alphabet("a"), new State("q1,q3"));}
        });
        expectedTransitions.put(new State("q2,q3"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("b"), new State("q3"));}
            {put(new Alphabet("a"), new State("q1,q2,q3"));}
        });
        expectedTransitions.put(new State("q1,q2,q3"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("b"), new State("q2,q3"));}
            {put(new Alphabet("a"), new State("q1,q2,q3"));}
        });

        State expectedInitialState = new State("q1,q3");

        Machine expectedDFA = new DFA(expectedStates, expectedAlphabets, expectedTransitions, expectedInitialState, expectedFinalStates);

        assertThat(classroomNFAtoDFAExample, is(expectedDFA));
    }
}