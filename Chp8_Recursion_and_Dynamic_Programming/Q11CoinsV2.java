public class Q11CoinsV2 {
    public static int makeChange(int n) {
        int[] denoms = {25, 10, 5, 1};
        int[][] map = new int[n + 1][denoms.length]; // precomputed vals
        return makeChange(n, denoms, 0, map);
    }

    private static int makeChange(int amount, int[] denoms, int index, int[][] map) {
        if (map[amount][index] > 0) {
            return map[amount][index];
        }

        if (index >= denoms.length - 1) // last denom
            return 1;
        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, denoms, index + 1, map);
        }
        map[amount][index] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int ways = makeChange(197);
        System.out.println(ways);
    }
}

