package Chp8_Recursion_and_Dynamic_Programming;

import java.util.ArrayList;

public class Q9ParensV2 {
    public static ArrayList<String> generateParens(int count){
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<>();
        addParen(list, count, count, str, 0);
        return list;
    }

    private static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index){
        if (leftRem < 0 || rightRem < leftRem)
            return; // invalid state

        if (leftRem == 0 && rightRem == 0){ // Out of left and right parentheses
            list.add(String.copyValueOf(str));
            // copyValueOf() function copies the content of a character array into the string
        } else {
            str[index] = '('; // Add left paren and recurse, will return if leftRem < 0 triggered
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')'; // Add right pren and recurse, will return if rightRem < leftRem triggered
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = generateParens(4);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }
}

