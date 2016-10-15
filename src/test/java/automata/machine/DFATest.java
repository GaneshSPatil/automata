package automata.machine;

import automata.entity.*;
import automata.entity.dfa.Transitions;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DFATest {

    @Test
    public void shouldAcceptAllStringsWhichEndsWithZero() throws Exception {
        States states = new States();
        states.add(new State("q1"));
        states.add(new State("q2"));

        Alphabets alphabets = new Alphabets();
        alphabets.add(new Alphabet("1"));
        alphabets.add(new Alphabet("0"));

        Transitions transitions = new Transitions();
        transitions.put(new State("q1"), new HashMap<Alphabet, State>(){
            { put(new Alphabet("0"), new State("q2"));}
            { put(new Alphabet("1"), new State("q1"));}
        });
        transitions.put(new State("q2"), new HashMap<Alphabet, State>(){
            { put(new Alphabet("0"), new State("q2"));}
            { put(new Alphabet("1"), new State("q1"));}
        });

        State initialState = new State("q1");

        States finalStates = new States();
        finalStates.add(new State("q2"));

        DFA dfa = new DFA(states, alphabets, transitions, initialState, finalStates);

        assertTrue(dfa.canAccept("0"));
        assertTrue(dfa.canAccept("00"));
        assertTrue(dfa.canAccept("10"));
        assertTrue(dfa.canAccept("1010"));

        assertFalse(dfa.canAccept("1"));
        assertFalse(dfa.canAccept("01"));
        assertFalse(dfa.canAccept("0000001"));
    }
}