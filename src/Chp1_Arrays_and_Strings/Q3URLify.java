package Chp1_Arrays_and_Strings;

public class Q3URLify {
    public static void replaceSpaces(char[] str, int truelength){
        int SpaceCount = 0;
        for (int i=0; i<truelength; i++)
            if (str[i] == ' ')
                SpaceCount++;

        int index = truelength + SpaceCount*2;
        if (truelength < str.length) str[truelength] = '\0'; // End array
        for (int i = truelength-1; i>=0; i--){
            if (str[i] == ' '){
                str[index-1] = '0';
                str[index-2] = '2';
                str[index-3] = '%';
                index -= 3;
            }
            else{
                str[index-1] = str[i];
                index--;
            }
        }
    }

    public static void main(String[] args) {
        String str = "Mr John Smith    ";
        int trueLength = 13;
        char[] arr = str.toCharArray();
        replaceSpaces(arr, trueLength);
        for (int i=0; i < arr.length; i++)
            System.out.print(arr[i]);
    }
}
