package Chp8_Recursion_and_Dynamic_Programming;

import java.util.ArrayList;

public class Q7PermutationsWithoutDupsV1 {
    public static ArrayList<String> getPerms(String str){
        if (str == null)
            return null;

        ArrayList<String> permutations = new ArrayList<>();
        if (str.length() == 0){ // base case
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0);
        String remainder = str.substring(1); // remove the first char
        ArrayList<String> words = getPerms(remainder);
        for (String word : words){
            for (int j = 0; j <= word.length(); j++){
                String s = insertCharAt(word, first, j);
                permutations.add(s);
            }
        }
        return permutations;
    }

    /* Insert char c at index i in word. */
    private static String insertCharAt(String word, char c, int i){
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }

    public static void main(String[] args) {
        ArrayList<String> list = getPerms("abcde");
        System.out.println("There are " + list.size() + " permutations.");
        for (String s : list) {
            System.out.println(s);
        }
    }
}