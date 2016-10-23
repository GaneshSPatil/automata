package automata.Integration;

import automata.generator.DFAGenrator;
import automata.machine.Machine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFAIntegrationTest {
    @Test
    public void shouldCreateADFAWhichRecognizesAllStringsWhichContainsOnlyZeros() throws Exception {
        ArrayList<String> states = new ArrayList<String>();
        states.add("q1");
        states.add("q2");
        states.add("q3");

        ArrayList<String> alphabets = new ArrayList<String>();
        alphabets.add("0");
        alphabets.add("1");

        HashMap<String, HashMap> delta = new HashMap<String, HashMap>();
        delta.put("q1", new HashMap<String, String>(){
            {put("1", "q3");}
            {put("0", "q2");}
        });
        delta.put("q2", new HashMap<String, String>(){
            {put("1", "q3");}
            {put("0", "q2");}
        });
        delta.put("q3", new HashMap<String, String>(){
            {put("1", "q3");}
            {put("0", "q3");}
        });

        String startState = "q1";

        ArrayList<String> finalStates = new ArrayList<String>();
        finalStates.add("q2");

        Machine dfa = new DFAGenrator().generate(states, alphabets, delta, startState, finalStates);

        assertTrue(dfa.canAccept("0"));
        assertTrue(dfa.canAccept("00"));
        assertTrue(dfa.canAccept("00000000"));

        assertFalse(dfa.canAccept(""));
        assertFalse(dfa.canAccept("1"));
        assertFalse(dfa.canAccept("1111111"));
        assertFalse(dfa.canAccept("000010"));
        assertFalse(dfa.canAccept("010101010"));
    }
}
