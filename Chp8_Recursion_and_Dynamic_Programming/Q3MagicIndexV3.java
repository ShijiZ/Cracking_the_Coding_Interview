import CtCILibrary.AssortedMethods;

import java.util.Arrays;

public class Q3MagicIndexV3 {
    public static int magicFast(int[] array){
        return magicFast(array, 0, array.length - 1);
    }

    private static int magicFast(int[] array, int start, int end){
        if (end < start){
            return -1;
        }

        int midIndex = (start + end) / 2;
        int midValue = array[midIndex];
        if (midValue == midIndex){
            return midIndex;
        }

        /* Search left */
        int leftIndex = Math.min(midIndex - 1, midValue);
        int left = magicFast(array, start, leftIndex);
        if (left >= 0)
            return left;

        /* Search right */
        int rightIndex = Math.max(midIndex + 1, midValue);
        int right = magicFast(array, rightIndex, end);

        return right;
    }

    /* Creates an array that is sorted */
    public static int[] getSortedArray(int size) {
        int[] array = AssortedMethods.randomArray(size, -1 * size, size);
        Arrays.sort(array);
        return array;
    }

    public static void main(String[] args){
        int size = AssortedMethods.randomIntInRange(5, 20);
        int[] array = getSortedArray(size);
        int magicIndex = magicFast(array);

        int[] indexArray = new int[size];
        for (int i=0; i<size; i++){
            indexArray[i] = i;
        }

        System.out.println("Indices: " + AssortedMethods.arrayToString(indexArray));
        System.out.println("Values: " + AssortedMethods.arrayToString(array));
        System.out.println("The magic index is: " + magicIndex);
    }
}
