package combination;

import automata.entity.State;
import automata.entity.States;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class CombinationGenerator {
    public static List<States> getCombinationsOf(States set){
        List<States> allCombinations = new ArrayList<States>();
        for (int i = 0; i <= set.size(); i++) {
            List<States> kSizedCombinations = getSizedCombinations(set, i);
            for (States combination : kSizedCombinations) {
                allCombinations.add(combination);
            }
        }
        return allCombinations;
    }

    private static List<States> getSizedCombinations(final States allStates, int length) {
        List<States> kSizedCombs = new ArrayList<States>();
        if (length > allStates.size() || length <= 0) {
            return kSizedCombs;
        }

        if (length == allStates.size()) {
            kSizedCombs.add(allStates);
            return kSizedCombs;
        }

        if (length == 1) {
            for (final State state : allStates) {
                kSizedCombs.add(new States(){{add(state);}});
            }
            return kSizedCombs;
        }
        final ArrayList<State> uniqueSetOfStates = new ArrayList<State>(allStates);
        for (int i = 0; i < new ArrayList<State>(uniqueSetOfStates).size() - length + 1; i++) {
            final int finalI = i;
            States head = new States(){
                {add(uniqueSetOfStates.subList(finalI, finalI + 1).get(0));}
            };
            States listOfStates = new States();
            for (State state : uniqueSetOfStates.subList(i + 1, allStates.size())) {
                listOfStates.add(state);
            }
            List<States> tailCombs = new ArrayList<States>(getSizedCombinations(listOfStates, length - 1));

            for (int j = 0; j < tailCombs.size(); j++) {
                head.addAll(new ArrayList<State>(tailCombs.get(j)));
                States states = new States();
                for (State state : new ArrayList<State>(head)) {
                    states.add(state);
                }
                kSizedCombs.add(states);
                head = new States(){{add(uniqueSetOfStates.subList(finalI, finalI + 1).get(0));}};
            }
        }
        return kSizedCombs;
    }
}
