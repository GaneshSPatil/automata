package combination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class foo {
    public static List<List<String>> combinations(List<String> set){
        List<List<String>> combs = new ArrayList<List<String>>();
        for (int k = 0; k <= set.size(); k++) {
            List<List<String>> k_combs = k_combinations(set, k);
            for (List<String> k_comb : k_combs) {
                combs.add(k_comb);
            }
        }
        return combs;
    }

    private static List<List<String>> k_combinations(final List<String> set, int k) {
        List<List<String>> k_combs = new ArrayList<List<String>>();
        if (k > set.size() || k <= 0) {
            return k_combs;
        }

        if (k == set.size()) {
            k_combs.add(set);
            return k_combs;
        }

        if (k == 1) {
            for (int i = 0; i < set.size(); i++) {
                final int finalI = i;
                k_combs.add(new ArrayList<String>(){{
                    int index = finalI;
                    add(set.get(index));}});
            }
            return k_combs;
        }

        for (int i = 0; i < new HashSet<String>(set).size() - k + 1; i++) {
            List<String> head = new ArrayList<String>(set.subList(i, i + 1));
            List<List<String>> tailcombs = new ArrayList<List<String>>(k_combinations(set.subList(i + 1, set.size()), k - 1));
            for (int j = 0; j < tailcombs.size(); j++) {
                head.addAll(new ArrayList<String>(tailcombs.get(j)));
                k_combs.add(new ArrayList<String>(head));
                head = new ArrayList<String>(set.subList(i, i + 1));
            }
        }
        return k_combs;
    }

    public static void main(String[] args) {
        ArrayList<String> set = new ArrayList<String>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        System.out.println(combinations(set));
    }
}
