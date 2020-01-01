import java.util.ArrayList;

public class Q7PermutationsWithoutDupsV3 {
    public static ArrayList<String> getPerms(String str){
        if (str == null)
            return null;

        ArrayList<String> permutations = new ArrayList<>();
        getPerms("", str, permutations);
        return permutations;
    }

    private static void getPerms(String prefix, String str, ArrayList<String> permutations){
        if (str.length() == 0){ // base case
            permutations.add(prefix);
            return;
        }

        int len = str.length();
        for (int i=0; i < len; i++){
            // Remove char i and find permutations of remaining chars.
            String before = str.substring(0, i);
            String after = str.substring(i + 1, len);
            char c = str.charAt(i);
            getPerms(prefix + c, before + after, permutations);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = getPerms("abcde");
        System.out.println("There are " + list.size() + " permutations.");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
