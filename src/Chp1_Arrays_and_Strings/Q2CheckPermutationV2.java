package Chp1_Arrays_and_Strings;

public class Q2CheckPermutationV2 {
    public static boolean permutation(String s, String t){
        if (s.length() != t.length())
            return false;

        int[] letters = new int[128];

        char[] s_array = s.toCharArray();
        for (char c : s_array)
            letters[c]++;

        for (int i=0; i<t.length(); i++){
            int c = t.charAt(i);
            letters[c]--;
            if (letters[c] < 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean anagram = permutation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + anagram);
        }
    }
}