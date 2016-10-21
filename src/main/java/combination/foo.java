package combination;

import automata.entity.State;
import automata.entity.States;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class foo {
    public static List<States> combinations(States set){
        List<States> combs = new ArrayList<States>();
        for (int k = 0; k <= set.size(); k++) {
            List<States> k_combs = k_combinations(set, k);
            for (States k_comb : k_combs) {
                combs.add(k_comb);
            }
        }
        return combs;
    }

    private static List<States> k_combinations(final States set, int k) {
        List<States> k_combs = new ArrayList<States>();
        if (k > set.size() || k <= 0) {
            return k_combs;
        }

        if (k == set.size()) {
            k_combs.add(set);
            return k_combs;
        }

        if (k == 1) {
            for (final State state : set) {
                k_combs.add(new States(){{add(state);}});
            }
            return k_combs;
        }
        final ArrayList<State> uniqueSet = new ArrayList<State>(set);
        for (int i = 0; i < new HashSet<State>(uniqueSet).size() - k + 1; i++) {
            final int finalI = i;
            States head = new States(){{add(uniqueSet.subList(finalI, finalI + 1).get(0));}};;
            States listOfStates = new States();
            for (State state : uniqueSet.subList(i + 1, set.size())) {
                listOfStates.add(state);
            }
            List<States> tailcombs = new ArrayList<States>(k_combinations(listOfStates, k - 1));

            for (int j = 0; j < tailcombs.size(); j++) {
                head.addAll(new ArrayList<State>(tailcombs.get(j)));
                States states = new States();
                for (State state : new ArrayList<State>(head)) {
                    states.add(state);
                }
                k_combs.add(states);
                head = new States(){{add(uniqueSet.subList(finalI, finalI + 1).get(0));}};
            }
        }
        return k_combs;
    }

    public static void main(String[] args) {
        States set = new States();
        set.add(new State("1"));
        set.add(new State("2"));
        set.add(new State("3"));
//        set.add(new State("4"));
        System.out.println(combinations(set).size());
    }
}
