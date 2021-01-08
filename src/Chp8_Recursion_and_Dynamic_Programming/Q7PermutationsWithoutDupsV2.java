package Chp8_Recursion_and_Dynamic_Programming;

import java.util.ArrayList;

public class Q7PermutationsWithoutDupsV2 {
    public static ArrayList<String> getPerms(String str){
        if (str == null)
            return null;

        ArrayList<String> permutations = new ArrayList<>();
        int len = str.length();
        if (len == 0){ // base case
            permutations.add("");
            return permutations;
        }

        for (int i=0; i < len; i++){
            // Remove char i and find permutations of remaining chars.
            String before = str.substring(0, i);
            String after = str.substring(i + 1, len);
            ArrayList<String> partials = getPerms(before + after);

            /* Prepend char i to each permutation */
            for (String s : partials){
                permutations.add(str.charAt(i) + s);
            }
        }

        return permutations;
    }

    public static void main(String[] args) {
        ArrayList<String> list = getPerms("abcde");
        System.out.println("There are " + list.size() + " permutations.");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
