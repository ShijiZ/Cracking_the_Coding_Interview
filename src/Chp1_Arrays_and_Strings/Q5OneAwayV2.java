package Chp1_Arrays_and_Strings;

public class Q5OneAwayV2 {
    public static boolean oneEditAway(String first, String second){
        /* Length checks */
        if (Math.abs(first.length() - second.length()) > 1 )
            return false;

        /* get shorter and longer strings */
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while (index2 < s2.length() && index1 < s1.length()){
            if (s1.charAt(index1) != s2.charAt(index2)){
                if (foundDifference)  // 2 differences found
                    return false;
                foundDifference = true;

                if (s1.length() == s2.length()) // On replace, move shorter pointer
                    index1++;
            }
            else{
                index1++;  // If matching, move shorter pointer
            }
            index2++; // Always move pointer for longer string
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "pse";
        String b = "pale";
        boolean isOneEdit = oneEditAway(a, b);
        System.out.println(a + ", " + b + ": " + isOneEdit);
    }
}
