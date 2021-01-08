package Chp8_Recursion_and_Dynamic_Programming;

public class Q11CoinsV2 {
    public static int makeChange(int n) {
        int[] coins = {25, 10, 5, 1};
        int[][] map = new int[n + 1][coins.length]; // precomputed vals
        int ways = makeChange(n, coins, 0, map);

        // show map[][]
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + ",");
            }
            System.out.println();
        }

        return ways;
    }

    // calculate ways to represent amount using coins[index] as denomination,
    // ie, ONLY use coins less than or equals coins[index]
    private static int makeChange(int amount, int[] coins, int index, int[][] map) {
        if (map[amount][index] > 0) {
            return map[amount][index];
        }

        // use only penny, only 1 way
        if (index >= coins.length - 1) {
            map[amount][index] = 1;
            return 1;
        }

        int ways = 0;
        int denom = coins[index];
        int useDenom = 0;
        // if useDenom * denom == amount, still need to recurse, will return 1
        while (useDenom * denom <= amount){
            int amountRemaining = amount - useDenom * denom;
            ways += makeChange(amountRemaining, coins, index + 1, map);
            useDenom++;
        }
        map[amount][index] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int ways = makeChange(25);
        System.out.println(ways);
    }
}

