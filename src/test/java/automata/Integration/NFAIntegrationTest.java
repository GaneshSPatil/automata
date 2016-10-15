package automata.Integration;

import automata.generator.NFAGenerator;
import automata.machine.Machine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NFAIntegrationTest {
    @Test
    public void shouldCreateAnNFAWhichRecognizesSpecifiedPatterns() throws Exception {
        ArrayList<String> states = new ArrayList<String>();
        states.add("q1");
        states.add("q2");
        states.add("q3");

        ArrayList<String> alphabets = new ArrayList<String>();
        alphabets.add("*");
        alphabets.add("a");
        alphabets.add("b");

        HashMap<String, HashMap> delta = new HashMap<String, HashMap>();
        delta.put("q1", new HashMap<String, HashSet<String>>(){
            {put("*", new HashSet<String>(){{ add("q3");}});}
            {put("b", new HashSet<String>(){{ add("q2");}});}
        });
        delta.put("q2", new HashMap<String, HashSet<String>>(){
            {put("a", new HashSet<String>(){
                { add("q2");}
                { add("q3");}
            });}
            {put("b", new HashSet<String>(){{ add("q3");}});}
        });
        delta.put("q3", new HashMap<String, HashSet<String>>(){
            {put("a", new HashSet<String>(){{ add("q1");}});}
        });

        String startState = "q1";

        ArrayList<String> finalStates = new ArrayList<String>();
        finalStates.add("q1");

        Machine NFA = new NFAGenerator().generate(states, alphabets, delta, startState, finalStates);

        assertTrue(NFA.canAccept("a"));
        assertTrue(NFA.canAccept("aa"));
        assertTrue(NFA.canAccept("baa"));
        assertTrue(NFA.canAccept("baba"));
        assertTrue(NFA.canAccept("baaaaaaaaaaa"));
        assertTrue(NFA.canAccept("bba"));
        assertTrue(NFA.canAccept("baaba"));
        assertTrue(NFA.canAccept(""));

        assertFalse(NFA.canAccept("b"));
        assertFalse(NFA.canAccept("bb"));
        assertFalse(NFA.canAccept("bab"));
        assertFalse(NFA.canAccept("aba"));
    }
}
