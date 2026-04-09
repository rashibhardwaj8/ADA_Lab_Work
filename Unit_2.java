import java.util.*;

class Item {
    int weight, value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class Algorithms {

    // ------------------ 0/1 KNAPSACK ------------------
    public static int zeroOneKnapsack(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    int include = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    int exclude = dp[i - 1][w];
                    dp[i][w] = Math.max(include, exclude);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    // ------------------ FRACTIONAL KNAPSACK ------------------
    public static double fractionalKnapsack(int capacity, List<Item> items) {

        // Sort by value/weight ratio descending
        items.sort((a, b) -> Double.compare((double)b.value / b.weight, (double)a.value / a.weight));

        double maxValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                capacity -= item.weight;
                maxValue += item.value;
            } else {
                double fraction = (double) capacity / item.weight;
                maxValue += item.value * fraction;
                break;
            }
        }

        return maxValue;
    }

    // ------------------ MATRIX MULTIPLICATION ------------------
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {

        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    // ------------------ LCS ------------------
    public static int longestCommonSubsequence(String str1, String str2) {

        int len1 = str1.length();
        int len2 = str2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    // ------------------ MAIN ------------------
    public static void main(String[] args) {

        // 0/1 Knapsack
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        int capacity = 7;
        System.out.println("0/1 Knapsack Result: " +
                zeroOneKnapsack(capacity, weights, values));

        // Fractional Knapsack
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));

        System.out.println("Fractional Knapsack Result: " +
                fractionalKnapsack(50, items));

        // Matrix Multiplication
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};

        int[][] result = multiplyMatrices(A, B);
        System.out.println("Matrix Multiplication Result:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // LCS
        System.out.println("LCS Length: " +
                longestCommonSubsequence("ABCBDAB", "BDCAB"));
    }
}
