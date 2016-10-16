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
    public void shouldCreateEquivalentDFA() throws Exception {
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

        NFA NFA = new NFA(states, alphabets, transitions, initialState, finalStates);
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

}