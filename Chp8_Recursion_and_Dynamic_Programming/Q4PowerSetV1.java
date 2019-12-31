import java.util.ArrayList;

public class Q4PowerSetV1 {
    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set){
        return getSubsets(set, 0);
    }

    private static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index){
        ArrayList<ArrayList<Integer>> allsubsets;
        if (set.size() == index){ // Base case - add empty set
            allsubsets = new ArrayList<>();
            allsubsets.add(new ArrayList<Integer>()); // Empty set
        } else {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<>();
            for (ArrayList<Integer> subset : allsubsets){
                ArrayList<Integer> newsubset = new ArrayList<>();
                newsubset.addAll(subset);
                newsubset.add(item);
                moresubsets.add(newsubset);
            }
            allsubsets.addAll(moresubsets);
        }
        return allsubsets;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        ArrayList<ArrayList<Integer>> subsets = getSubsets(list);
        System.out.println(subsets.toString());
    }
}
