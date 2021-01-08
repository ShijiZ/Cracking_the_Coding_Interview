package Chp8_Recursion_and_Dynamic_Programming;

import CtCILibrary.AssortedMethods;

import java.util.Arrays;

public class Q3MagicIndexV1 {
    public static int magicSlow(int[] array){
        for (int i=0; i < array.length; i++){
            if (array[i] == i)
                return i;
        }
        return -1;
    }

    /* Creates an array that is distinct and sorted */
    public static int[] getDistinctSortedArray(int size) {
        int[] array = AssortedMethods.randomArray(size, -1 * size, size);
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i-1]) {
                array[i]++;
            } else if (array[i] < array[i - 1]) {
                array[i] = array[i-1] + 1;
            }
        }
        return array;
    }

    public static void main(String[] args){
        int size = AssortedMethods.randomIntInRange(5, 20);
        int[] array = getDistinctSortedArray(size);
        int magicIndex = magicSlow(array);

        int[] indexArray = new int[size];
        for (int i=0; i<size; i++){
            indexArray[i] = i;
        }

        System.out.println("Indices: " + AssortedMethods.arrayToString(indexArray));
        System.out.println("Values: " + AssortedMethods.arrayToString(array));
        System.out.println("The magic index is: " + magicIndex);
    }
}
