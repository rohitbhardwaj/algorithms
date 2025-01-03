import java.util.Arrays;

/**
 * The {@code KnapsackProblem} class provides functionality to solve the 0/1 Knapsack Problem.
 *
 * <p>The 0/1 Knapsack Problem is a classic optimization problem where the goal is to select a subset
 * of items with given values and weights to maximize the total value without exceeding a specified
 * weight limit. Each item can be either included (1) or excluded (0) from the knapsack.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * KnapsackProblem knapsack = new KnapsackProblem();
 * int maxWeight = 10;
 * int[] values = {300, 200, 400, 500};
 * int[] weights = {2, 1, 5, 3};
 * int maxValue = knapsack.knapsack(maxWeight, values, weights);
 * System.out.println("Maximum value achievable: $" + maxValue); // Output: $1200
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong> O(nW), where n is the number of items and W is the maximum weight.</p>
 * <p><strong>Space Complexity:</strong> O(W), where W is the maximum weight.</p>
 *
 * Time and Space Complexity Analysis
    Time Complexity
    Initialization:
    Creating and initializing the DP array takes O(W) time, where W is the maximum weight.
    Nested Loops:
    The outer loop iterates through each of the n items, resulting in O(n) iterations.
    The inner loop iterates through weight limits from W down to the current item's weight. In the worst case, this results in O(W) iterations per item.
    Total Time Complexity: O(nW)
    Where n is the number of items and W is the maximum weight limit.
    Space Complexity
    DP Array:
    The DP array dp of size W + 1 requires O(W) space.
    Variables:
    A few integer variables are used, which take O(1) space.
    Total Space Complexity: O(W)
    Where W is the maximum weight limit.
    Note: This optimized approach reduces space complexity from O(nW) (using a 2D array) to O(W) by using a 1D DP array.
 * 
 * @author 
 */
public class KnapsackProblem {
    
    /**
     * Solves the 0/1 Knapsack Problem using Dynamic Programming with space optimization.
     *
     * <p>Given the maximum weight limit of the knapsack, an array of item values, and an array of
     * corresponding item weights, this method computes the maximum total value achievable without
     * exceeding the weight limit. Each item can be taken at most once.</p>
     *
     * @param maxWeight The maximum weight limit of the knapsack.
     * @param values    An array where values[i] represents the value of the i-th item.
     * @param weights   An array where weights[i] represents the weight of the i-th item.
     * @return The maximum total value achievable within the weight limit.
     */
    public int knapsack(int maxWeight, int[] values, int[] weights) {
        // Number of items
        int n = values.length;
        
        // Initialize DP array: dp[w] will hold the maximum value for weight w
        int[] dp = new int[maxWeight + 1];
        Arrays.fill(dp, 0);
        
        // Iterate through each item
        for (int i = 0; i < n; i++) {
            // Iterate through weights from maxWeight down to weights[i]
            for (int w = maxWeight; w >= weights[i]; w--) {
                // Update dp[w] if including the current item yields a better value
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        // The last element of dp array contains the maximum value achievable
        return dp[maxWeight];
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code KnapsackProblem} class.
     *
     * <p>It demonstrates the usage of the knapsack method with sample inputs, including the
     * manual example provided.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        KnapsackProblem knapsack = new KnapsackProblem();
        
        // Example 1:
        // Items:
        // 1. Microscope: $300, 2 kg
        // 2. Globe: $200, 1 kg
        // 3. Cup: $400, 5 kg
        // 4. Crown: $500, 3 kg
        // Knapsack Capacity: 10 kg
        int maxWeight1 = 10;
        int[] values1 = {300, 200, 400, 500};
        int[] weights1 = {2, 1, 5, 3};
        int maxValue1 = knapsack.knapsack(maxWeight1, values1, weights1);
        System.out.println("Example 1 Output: $" + maxValue1); // Expected Output: $1200
        
        // Example 2:
        // Items:
        // 1. Item1: $60, 10 kg
        // 2. Item2: $100, 20 kg
        // 3. Item3: $120, 30 kg
        // Knapsack Capacity: 50 kg
        int maxWeight2 = 50;
        int[] values2 = {60, 100, 120};
        int[] weights2 = {10, 20, 30};
        int maxValue2 = knapsack.knapsack(maxWeight2, values2, weights2);
        System.out.println("Example 2 Output: $" + maxValue2); // Expected Output: $220 (Items 2 and 3)
        
        // Example 3:
        // Items:
        // 1. Laptop: $1500, 4 kg
        // 2. Headphones: $300, 1 kg
        // 3. Book: $200, 2 kg
        // 4. Pen: $50, 0.5 kg (assuming weights are integers, represent 1 kg)
        // Knapsack Capacity: 5 kg
        int maxWeight3 = 5;
        int[] values3 = {1500, 300, 200, 50};
        int[] weights3 = {4, 1, 2, 1}; // Pen weight adjusted to 1 kg
        int maxValue3 = knapsack.knapsack(maxWeight3, values3, weights3);
        System.out.println("Example 3 Output: $" + maxValue3); // Expected Output: $1800 (Laptop + Headphones)
    }
}
