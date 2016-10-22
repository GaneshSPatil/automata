package automata.machine;

import automata.entity.Alphabet;
import automata.entity.Alphabets;
import automata.entity.State;
import automata.entity.States;
import automata.entity.nfa.Transitions;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NFAToDFAConverterTest {

    @Test
    public void NFAWhichAcceptsOnlyEmptyString() throws Exception {
        States states = new States();
        states.add(new State("q1"));
        Alphabets alphabets = new Alphabets();
        Transitions transitions = new Transitions();
        State initialState = new State("q1");
        States finalStates = new States();
        finalStates.add(new State("q1"));

        NFA nfa = new NFA(states, alphabets, transitions, initialState, finalStates);

        DFA dfa = nfa.toDFA();

        States expectedStates = new States();
        expectedStates.add(new State(""));
        expectedStates.add(new State("q1"));
        States expectedFinalStates = new States();
        expectedFinalStates.add(new State("q1"));
        Alphabets expectedAlphabets = new Alphabets();
        automata.entity.dfa.Transitions expectedTransitions = new automata.entity.dfa.Transitions();
        State expectedInitialState = new State("q1");

        DFA expectedDFA = new DFA(expectedStates, expectedAlphabets, expectedTransitions, expectedInitialState, expectedFinalStates);

        assertThat(dfa, is(expectedDFA));
    }

    @Test
    public void NFAWhichAcceptsOnlyOnes() throws Exception {
        States states = new States();
        states.add(new State("q1"));
        states.add(new State("q2"));

        Alphabets alphabets = new Alphabets();
        alphabets.add(new Alphabet("*"));
        alphabets.add(new Alphabet("1"));

        Transitions transitions = new Transitions();
        transitions.put(new State("q1"), new HashMap<Alphabet, States>(){
            {put(new Alphabet("1"), new States(){
                {add(new State("q2"));}
            });}
        });

        State initialState = new State("q1");
        States finalStates = new States();
        finalStates.add(new State("q2"));

        NFA nfa = new NFA(states, alphabets, transitions, initialState, finalStates);

        DFA dfa = nfa.toDFA();

        States expectedStates = new States();
        expectedStates.add(new State(""));
        expectedStates.add(new State("q1"));
        expectedStates.add(new State("q2"));
        expectedStates.add(new State("q1,q2"));

        States expectedFinalStates = new States();
        expectedFinalStates.add(new State("q2"));
        expectedFinalStates.add(new State("q1,q2"));

        Alphabets expectedAlphabets = new Alphabets();
        expectedAlphabets.add(new Alphabet("1"));

        automata.entity.dfa.Transitions expectedTransitions = new automata.entity.dfa.Transitions();
        expectedTransitions.put(new State("q1"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("1"), new State("q2"));}
        });
        expectedTransitions.put(new State("q2"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("1"), new State(""));}
        });
        expectedTransitions.put(new State(""), new HashMap<Alphabet, State>(){
            {put(new Alphabet("1"), new State(""));}
        });
        expectedTransitions.put(new State("q1,q2"), new HashMap<Alphabet, State>(){
            {put(new Alphabet("1"), new State("q2"));}
        });
        State expectedInitialState = new State("q1");

        DFA expectedDFA = new DFA(expectedStates, expectedAlphabets, expectedTransitions, expectedInitialState, expectedFinalStates);

        assertThat(dfa, is(expectedDFA));
    }
}