public class Q5RecursiveMultiplyV2 {
    public static int minProduct(int a, int b){
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;

        int memo[] = new int[smaller + 1];
        return minProductHelper(smaller, bigger, memo);
    }

    private static int minProductHelper(int smaller, int bigger, int[] memo){
        if (smaller == 0){ // 0 x bigger = 0
            return 0;
        } else if (smaller == 1){ // 1 x bigger = 1
            return bigger;
        } else if (memo[smaller] > 0){
            return memo[smaller];
        }

        /* Compute half. If uneven, compute other half. If even, double it. */
        int s = smaller >> 1; // Divide by 2
        int side1 = minProduct(s, bigger);
        int side2 = side1;
        if (smaller % 2 == 1){
            side2 = minProductHelper(smaller - s, bigger, memo);
        }

        memo[smaller] = side1 + side2;
        return memo[smaller];
    }

    public static void main(String[] args) {
        int a = 13494;
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
