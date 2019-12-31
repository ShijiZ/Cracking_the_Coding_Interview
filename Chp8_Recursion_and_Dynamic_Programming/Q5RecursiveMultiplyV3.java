public class Q5RecursiveMultiplyV3 {
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
        int halfProd = minProduct(s, bigger);

        if (smaller % 2 == 1){
            return halfProd + halfProd + bigger;
        } else {
            return halfProd + halfProd;
        }


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
