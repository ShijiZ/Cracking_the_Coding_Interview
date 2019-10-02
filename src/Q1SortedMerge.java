import CtCILibrary.AssortedMethods;

public class Q1SortedMerge {
    public static void merge(int[] a, int[] b, int lastA, int lastB){
        int indexA = lastA - 1; // Index of the last element in array a
        int indexB = lastB - 1; // Index of the last element in array b
        int indexMerged = lastB + lastA - 1; // end of merged array

        /* Merge a and b, starting from the last element in each */
        while (indexB >= 0){
            if (indexA >= 0 && a[indexA] > b[indexB]){
                a[indexMerged] = a[indexA];
                indexA--;
            }else{
                a[indexMerged] = b[indexB];
                indexB--;
            }
            indexMerged--; // move indices
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
        int[] b = {1, 4, 6, 6, 7, 7};
        merge(a, b, 8, 6);
        System.out.println(AssortedMethods.arrayToString(a));
    }
}
