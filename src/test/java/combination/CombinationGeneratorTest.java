package combination;

import automata.entity.State;
import automata.entity.States;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CombinationGeneratorTest {

    @Test
    public void shouldGenerateCombinationsForCollectionOfStatesWithZeroStates() throws Exception {
        States states = new States();
        List<States> combinations = CombinationGenerator.getCombinationsOf(states);

        assertThat(combinations.size(), is(0));
    }

    @Test
    public void shouldGenerateCombinationsForCollectionOfStatesWithOneState() throws Exception {
        State q1 = new State("q1");

        States states = new States();
        states.add(q1);

        List<States> combinations = CombinationGenerator.getCombinationsOf(states);

        States firstElement = new States();
        firstElement.add(q1);

        assertThat(combinations.size(), is(1));
        assertThat(combinations.get(0), is(firstElement));
    }

    @Test
    public void shouldGenerateCombinationsForCollectionOfStatesWithTwoStates() throws Exception {
        State q1 = new State("q1");
        State q2 = new State("q2");

        States states = new States();
        states.add(q1);
        states.add(q2);

        List<States> combinations = CombinationGenerator.getCombinationsOf(states);

        States firstElement = new States();
        firstElement.add(q1);

        States secondElement = new States();
        secondElement.add(q2);

        States thirdElement = new States();
        thirdElement.add(q1);
        thirdElement.add(q2);

        assertThat(combinations.size(), is(3));
        assertThat(combinations.get(0), is(firstElement));
        assertThat(combinations.get(1), is(secondElement));
        assertThat(combinations.get(2), is(thirdElement));
    }

    @Test
    public void shouldGenerateCombinationsForCollectionOfStatesWithThreeStates() throws Exception {
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");

        States states = new States();
        states.add(q1);
        states.add(q2);
        states.add(q3);

        List<States> combinations = CombinationGenerator.getCombinationsOf(states);

        States firstElement = new States();
        firstElement.add(q1);

        States secondElement = new States();
        secondElement.add(q2);

        States thirdElement = new States();
        thirdElement.add(q3);

        States fourthElement = new States();
        fourthElement.add(q1);
        fourthElement.add(q2);

        States fifthElement = new States();
        fifthElement.add(q1);
        fifthElement.add(q3);

        States sixthElement = new States();
        sixthElement.add(q2);
        sixthElement.add(q3);

        States seventhElement = new States();
        seventhElement.add(q1);
        seventhElement.add(q2);
        seventhElement.add(q3);

        assertThat(combinations.size(), is(7));
        assertThat(combinations.get(0), is(firstElement));
        assertThat(combinations.get(1), is(secondElement));
        assertThat(combinations.get(2), is(thirdElement));
        assertThat(combinations.get(3), is(fourthElement));
        assertThat(combinations.get(4), is(fifthElement));
        assertThat(combinations.get(5), is(sixthElement));
        assertThat(combinations.get(6), is(seventhElement));
    }
}