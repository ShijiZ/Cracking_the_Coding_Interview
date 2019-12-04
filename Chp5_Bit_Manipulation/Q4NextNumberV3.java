public class Q4NextNumberV3 {
    public static int getNext(int n){
        /* Compute c0 and c1 */
        int c = n;
        int c0 = 0;
        int c1 = 0;
        while (((c & 1) == 0) && (c != 0)){
            c0++;
            c >>= 1;
        }

        while ((c & 1) == 1){
            c1++;
            c >>= 1;
        }

        /* Error: if n == 11 .. 1100 ... 00, then there is no bigger number with the same
         * number of 1s. */
        if (c0 + c1 == 31 || c0 + c1 == 0)
            return -1;

        return n + (1<<c0) + (1<<(c1-1)) - 1;
    }

    public static int getPrev(int n){
        /* Compute c0 and c1 */
        int c = n;
        int c0 = 0;
        int c1 = 0;
        while ((c & 1) == 1){
            c1++;
            c >>= 1;
        }

        if (c == 0)
            return -1;

        while (((c & 1) == 0) && (c != 0)){
            c0++;
            c >>= 1;
        }

        return n - (1<<c1) - (1<<(c0 - 1)) + 1;
    }

    public static void main(String[] args) {
        int i = 13948;
        int p = getPrev(i);
        int n = getNext(i);
        System.out.println(i + ": " + Integer.toBinaryString(i));
        System.out.println(p + ": " + Integer.toBinaryString(p));
        System.out.println(n + ": " + Integer.toBinaryString(n));
    }
}
