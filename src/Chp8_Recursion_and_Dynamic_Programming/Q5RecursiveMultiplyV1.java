package Chp8_Recursion_and_Dynamic_Programming;

public class Q5RecursiveMultiplyV1 {
    public static int minProduct(int a, int b){
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return minProductHelper(smaller, bigger);
    }

    private static int minProductHelper(int smaller, int bigger){
        if (smaller == 0){ // 0 x bigger = 0
            return 0;
        } else if (smaller == 1){ // 1 x bigger = 1
            return bigger;
        }

        /* Compute half. If uneven, compute other half. If even, double it. */
        int s = smaller >> 1; // Divide by 2
        int side1 = minProductHelper(s, bigger);
        int side2;
        if (smaller % 2 == 1){
            side2 = minProductHelper(smaller - s, bigger);
        }
        else{ side2 = side1; }

        return side1 + side2;
    }

    public static void main(String[] args) {
        int a = 13493;
        int b = 22323;
        int product = a * b;
        int minProduct = minProduct(a, b);
        if (product == minProduct) {
            System.out.println("Success: " + a + " * " + b + " = " + product);
        } else {
            System.out.println("Failure: " + a + " * " + b + " = " + product + " instead of " + minProduct);
        }
    }
}
