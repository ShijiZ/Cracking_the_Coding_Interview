package Chp8_Recursion_and_Dynamic_Programming;

public class Q11CoinsV1 {
    public static int makeChange(int n){
        int[] coins = {25, 10, 5, 1};
        return makeChange(n, coins, 0);
    }

    // calculate ways to represent amount using coins[index] as denomination,
    // ie, ONLY use coins less than or equals coins[index]
    private static int makeChange(int amount, int[] coins, int index){
        // use only penny, only 1 way
        if (index >= coins.length - 1)
            return 1;

        int ways = 0;
        int denom = coins[index];
        int useDenom = 0;
        // if useDenom * denom == amount, still need to recurse, will return 1
        while (useDenom * denom <= amount) {
            int amountRemaining = amount - useDenom * denom;
            ways += makeChange(amountRemaining, coins, index + 1);
            useDenom++;
        }
        return ways;
    }

    public static void main(String[] args) {
        int ways = makeChange(197);
        System.out.println(ways);
    }
}
