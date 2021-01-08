package Chp8_Recursion_and_Dynamic_Programming;

import java.util.ArrayList;
import java.util.HashMap;

public class Q8PermutationsWithDups {
    public static ArrayList<String> getPerms(String str){
        if (str == null)
            return null;

        ArrayList<String> permutations = new ArrayList<>();
        HashMap<Character, Integer> map = buildFreqTable(str);
        getPerms(map, "", str.length(), permutations);
        return permutations;
    }

    private static HashMap<Character, Integer> buildFreqTable(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()){
            if (!map.containsKey(c)){
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        return map;
    }

    private static void getPerms(HashMap<Character, Integer> map, String prefix, int len,
                                 ArrayList<String> permutations){
        // Base case. Permutation has been completed.
        if (len == 0){
            permutations.add(prefix);
            return;
        }

        // Try remaining letters for next char, and generate remaining permutations
        for (Character c : map.keySet()){
            int count = map.get(c);
            if (count > 0){
                map.put(c, count - 1);
                getPerms(map, prefix + c, len - 1, permutations);
                map.put(c, count);
            }
        }
    }

    public static void main(String[] args) {
        String s = "aabbccc";
        ArrayList<String> result = getPerms(s);
        System.out.println("Count: " + result.size());
        for (String r : result) {
            System.out.println(r);
        }
    }
}
