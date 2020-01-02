import java.util.ArrayList;

public class Q9ParensV2 {
    private static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index){
        if (leftRem < 0 || rightRem < leftRem)
            return; // invalid state

        if (leftRem == 0 && rightRem == 0){ // Out of left and right parentheses
            list.add(String.copyValueOf(str));
            // copyValueOf() function copies the content of a character array into the string
        } else {
            str[index] = '('; // Add left and recurse
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')'; // Add right and recurse
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

    public static ArrayList<String> generateParens(int count){
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<>();
        addParen(list, count, count, str, 0);
        return list;
    }

    public static void main(String[] args) {
        ArrayList<String> list = generateParens(4);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }
}

