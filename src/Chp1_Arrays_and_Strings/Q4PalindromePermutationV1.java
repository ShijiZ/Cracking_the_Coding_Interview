package Chp1_Arrays_and_Strings;

public class Q4PalindromePermutationV1 {
    public static boolean isPermutationOfPalindrome(String phrase){
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    /* Check that no more than one character has an odd count */
    private static boolean checkMaxOneOdd(int[] table){
        boolean foundOdd = false;
        for (int count : table){
            if (count % 2 == 1){
                if (foundOdd){
                    return false;
                }
            }
            foundOdd = true;
        }
        return true;
    }

    /* Map each character to a number. a->0, b->1, c->2, etc.
    * This is case insensitive. Non-letter characters map to -1.*/
    private static int getCharNumber(char c){
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z){
            return val-a;
        }
        return -1;
    }

    /* count how many times each character appears*/
    private static int[] buildCharFrequencyTable(String phrase){
        int[] table = new int[26];
        for (char c : phrase.toCharArray()){
            int x = getCharNumber(c);
            if (x != -1){
                table[x]++;
            }
        }
        return table;
    }

    public static void main(String[] args) {
        String pali = "Rats live on no evil star";
        System.out.println(isPermutationOfPalindrome(pali));
    }
}
