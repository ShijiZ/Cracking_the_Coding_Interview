package Chp8_Recursion_and_Dynamic_Programming;

import java.util.Arrays;

public class Q1TripleStepV2 {
    public static int countWays(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        return countWays(n, memo);
    }

    private static int countWays(int n, int[] memo){
        if (n < 0)
            return 0;
        else if (n == 0)
            return 1;
        else if (memo[n] > -1)
            return memo[n];
        else {
            memo[n] = countWays(n-1, memo) + countWays(n-2, memo) +
                    countWays(n-3, memo);
            return memo[n];
        }
    }

    public static void main(String[] args) {
        int n = 20;
        int ways = countWays(n);
        System.out.println(ways);
    }
}
