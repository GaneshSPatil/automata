package automata.machine;

import automata.entity.*;
import automata.entity.nfa.Transitions;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NFATest {
    @Test
    public void shouldAcceptAllStringsContainingEitherOnlyOnesOrZeros() throws Exception {
        NFA nfa = getOnesAndZerosNfa();

        assertTrue(nfa.canAccept("0"));
        assertTrue(nfa.canAccept("0000"));
        assertTrue(nfa.canAccept("1"));
        assertTrue(nfa.canAccept("11111"));

        assertFalse(nfa.canAccept(""));
        assertFalse(nfa.canAccept("10"));
        assertFalse(nfa.canAccept("111110"));
    }

    @Test
    public void shouldCreateAnEquivalentDFAForOnesAndZerosNfa() throws Exception {
        NFA nfa = getOnesAndZerosNfa();
        DFA equivalentDFA = nfa.toDFA();

        assertTrue(equivalentDFA.canAccept("0"));
        assertTrue(equivalentDFA.canAccept("0000"));
        assertTrue(equivalentDFA.canAccept("1"));
        assertTrue(equivalentDFA.canAccept("11111"));

        assertFalse(equivalentDFA.canAccept(""));
        assertFalse(equivalentDFA.canAccept("10"));
        assertFalse(equivalentDFA.canAccept("111110"));
    }


    @Test
    public void classRoomNFATest() throws Exception {
        NFA NFA = getClassRoomNFA();

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

    @Test
    public void shouldCreateEquivalentDFAForClassRoomNFA() throws Exception {
        NFA NFA = getClassRoomNFA();
        DFA convertedDFA = NFA.toDFA();

        assertTrue(convertedDFA.canAccept("a"));
        assertTrue(convertedDFA.canAccept("aa"));
        assertTrue(convertedDFA.canAccept("baa"));
        assertTrue(convertedDFA.canAccept("baba"));
        assertTrue(convertedDFA.canAccept("baaaaaaaaaaa"));
        assertTrue(convertedDFA.canAccept("bba"));
        assertTrue(convertedDFA.canAccept("baaba"));
        assertTrue(convertedDFA.canAccept(""));

        assertFalse(convertedDFA.canAccept("b"));
        assertFalse(convertedDFA.canAccept("bb"));
        assertFalse(convertedDFA.canAccept("bab"));
        assertFalse(convertedDFA.canAccept("aba"));
    }

    private NFA getClassRoomNFA() {
        States states = new States();
        states.add(new State("q1"));
        states.add(new State("q2"));
        states.add(new State("q3"));


        Alphabets alphabets = new Alphabets();
        alphabets.add(new Alphabet("*"));
        alphabets.add(new Alphabet("a"));
        alphabets.add(new Alphabet("b"));

        Transitions transitions = new Transitions();
        transitions.put(new State("q1"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("*"), new States(){{add(new State("q3"));}});}
            {put(new Alphabet("b"), new States(){{add(new State("q2"));}});}
        });
        transitions.put(new State("q2"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("a"), new States(){
                {add(new State("q2"));}
                {add(new State("q3"));}
            });}
            {put(new Alphabet("b"), new States(){{add(new State("q3"));}});}
        });
        transitions.put(new State("q3"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("a"), new States(){{add(new State("q1"));}});}
        });

        State initialState = new State("q1");
        States finalStates = new States();
        finalStates.add(new State("q1"));

        return new NFA(states, alphabets, transitions, initialState, finalStates);
    }

    @Test
    public void shouldAcceptAllStringsContainingEvenOnesOrZeros() throws Exception {
        NFA nfa = getEvenOnesOrZerosNFA();

        assertTrue(nfa.canAccept("11"));
        assertTrue(nfa.canAccept("00"));
        assertTrue(nfa.canAccept("1100"));
        assertTrue(nfa.canAccept("0011"));
        assertTrue(nfa.canAccept("0000"));
        assertTrue(nfa.canAccept("1111"));
        assertTrue(nfa.canAccept("001100"));
        assertTrue(nfa.canAccept("110011"));

        assertFalse(nfa.canAccept("10"));
        assertFalse(nfa.canAccept("01"));
        assertFalse(nfa.canAccept("0101"));
        assertFalse(nfa.canAccept("1010"));
    }

    @Test
    public void shouldCreateAnEquivalentDFAForEvenZerosAndOnesNFA() throws Exception {
        NFA nfa = getEvenOnesOrZerosNFA();
        DFA equivalentDFA = nfa.toDFA();

        assertTrue(equivalentDFA.canAccept("11"));
        assertTrue(equivalentDFA.canAccept("00"));
        assertTrue(equivalentDFA.canAccept("1100"));
        assertTrue(equivalentDFA.canAccept("0011"));
        assertTrue(equivalentDFA.canAccept("0000"));
        assertTrue(equivalentDFA.canAccept("1111"));
        assertTrue(equivalentDFA.canAccept("001100"));
        assertTrue(equivalentDFA.canAccept("110011"));

        assertFalse(equivalentDFA.canAccept("10"));
        assertFalse(equivalentDFA.canAccept("01"));
        assertFalse(equivalentDFA.canAccept("0101"));
        assertFalse(equivalentDFA.canAccept("1010"));
    }

    private NFA getOnesAndZerosNfa() {
        States states = new States();
        states.add(new State("q1"));
        states.add(new State("q2"));
        states.add(new State("q3"));
        states.add(new State("q4"));
        states.add(new State("q5"));

        Alphabets alphabets = new Alphabets();
        alphabets.add(new Alphabet("*"));
        alphabets.add(new Alphabet("0"));
        alphabets.add(new Alphabet("1"));

        Transitions transitions = new Transitions();

        transitions.put(new State("q1"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("*"), new States(){
                {add(new State("q2"));}
                {add(new State("q3"));}
            });}
        });

        transitions.put(new State("q2"), new HashMap<Alphabet, States>(){{put(new Alphabet("1"), new States(){{add(new State("q4"));}});}});
        transitions.put(new State("q3"), new HashMap<Alphabet, States>(){{put(new Alphabet("0"), new States(){{add(new State("q5"));}});}});
        transitions.put(new State("q4"), new HashMap<Alphabet, States>(){{put(new Alphabet("1"), new States(){{add(new State("q4"));}});}});
        transitions.put(new State("q5"), new HashMap<Alphabet, States>(){{put(new Alphabet("0"), new States(){{add(new State("q5"));}});}});

        State initialState = new State("q1");

        States finalStates = new States();
        finalStates.add(new State("q4"));
        finalStates.add(new State("q5"));

        return new NFA(states, alphabets, transitions, initialState, finalStates);
    }

    public NFA getEvenOnesOrZerosNFA() {
        States states = new States();
        states.add(new State("q1"));
        states.add(new State("q2"));
        states.add(new State("q3"));
        states.add(new State("q4"));
        states.add(new State("q5"));
        states.add(new State("q6"));

        Alphabets alphabets = new Alphabets();
        alphabets.add(new Alphabet("*"));
        alphabets.add(new Alphabet("0"));
        alphabets.add(new Alphabet("1"));

        Transitions transitions = new Transitions();
        transitions.put(new State("q1"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("*"), new States(){
                {add(new State("q2"));}
                {add(new State("q3"));}
            });}
        });
        transitions.put(new State("q2"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("1"), new States(){
                {add(new State("q4"));}
            });}
        });
        transitions.put(new State("q4"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("1"), new States(){
                {add(new State("q6"));}
            });}
        });
        transitions.put(new State("q3"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("0"), new States(){
                {add(new State("q5"));}
            });}
        });
        transitions.put(new State("q5"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("0"), new States(){
                {add(new State("q6"));}
            });}
        });
        transitions.put(new State("q6"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("*"), new States(){
                {add(new State("q1"));}
            });}
        });

        State initialState = new State("q1");

        States finalStates = new States();
        finalStates.add(new State("q6"));

        return new NFA(states, alphabets, transitions, initialState, finalStates);
    }
}