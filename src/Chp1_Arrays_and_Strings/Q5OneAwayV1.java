package Chp1_Arrays_and_Strings;

public class Q5OneAwayV1 {
    public static boolean oneEditAway(String first, String second){
        if (first.length() == second.length()){
            return oneEditReplace(first, second);
        }
        else if (first.length()+1 == second.length()){
            return oneEditInsert(first, second);
        }
        else if (first.length()-1 == second.length()){
            return oneEditInsert(second, first);
        }
        return false;
    }

    private static boolean oneEditReplace(String s1, String s2){
        boolean foundDifference = false;
        for (int i = 0; i < s1.length() ; i++){
            if (s1.charAt(i) != s2.charAt(i)){
                if (foundDifference)  // 2 differences found
                    return false;
            }
            foundDifference = true;
        }
        return true; // No difference found
    }

    /* Check if you can insert a character into s1 to make s2 */
    private static boolean oneEditInsert(String s1, String s2){
        int index1 = 0;
        int index2 = 0;
        while (index2 < s2.length() && index1 < s1.length()){
            if (s1.charAt(index1) != s2.charAt(index2)){
                if (index1 != index2)  // One char difference already found
                    return false;
                index2++; // One char difference found
            }
            else{
                index1++;
                index2++;
            }
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
