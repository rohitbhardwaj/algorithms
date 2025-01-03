/**
 * The {@code ClimbingStairs} class provides functionality to determine the number of
 * distinct ways to climb to the top of a staircase with a given number of steps.
 *
 * <p>The problem is modeled using Dynamic Programming (DP) to efficiently calculate
 * the total number of ways without redundant computations.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * ClimbingStairs solver = new ClimbingStairs();
 * int n1 = 2;
 * System.out.println(solver.climbStairs(n1)); // Output: 2
 *
 * int n2 = 3;
 * System.out.println(solver.climbStairs(n2)); // Output: 3
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong> O(n)</p>
 * <p><strong>Space Complexity:</strong> O(1)</p>
 *
 * Time and Space Complexity Analysis
    Time Complexity
    Overall Time Complexity: O(n)

    Explanation:
    The method processes each step from 2 to n exactly once.
    Each iteration involves constant-time operations: addition and variable assignments.
    Therefore, the time taken grows linearly with the number of steps n.
    Space Complexity
    Overall Space Complexity: O(1)

    Explanation:
    The method uses a fixed number of variables (prev1, prev2, current), regardless of the input size n.
    No additional space is used that scales with n.
    Thus, space usage remains constant.
 * @author 
 */
public class ClimbingStairs {
    
    /**
     * Calculates the number of distinct ways to climb to the top of a staircase.
     *
     * <p>Each time you can either climb 1 or 2 steps. The method uses a space-optimized
     * Dynamic Programming approach to compute the total number of distinct ways.</p>
     *
     * @param n The total number of steps to reach the top.
     * @return The number of distinct ways to climb to the top.
     */
    public int climbStairs(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // Initialize variables to store the number of ways to reach the previous two steps
        int prev1 = 1; // Ways to reach step 1
        int prev2 = 1; // Ways to reach step 0
        
        // Iterate from step 2 to step n
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2; // Current step can be reached by taking 1 step from prev1 or 2 steps from prev2
            prev2 = prev1; // Update prev2 to prev1 for the next iteration
            prev1 = current; // Update prev1 to current for the next iteration
        }
        
        // The variable prev1 now holds the number of ways to reach step n
        return prev1;
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code ClimbingStairs} class.
     *
     * <p>It demonstrates the usage of the climbStairs method with sample inputs.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        ClimbingStairs solver = new ClimbingStairs();
        
        // Example 1:
        // Input: n = 2
        // Output: 2
        int n1 = 2;
        System.out.println("Example 1 Output: " + solver.climbStairs(n1)); // Expected Output: 2
        
        // Example 2:
        // Input: n = 3
        // Output: 3
        int n2 = 3;
        System.out.println("Example 2 Output: " + solver.climbStairs(n2)); // Expected Output: 3
        
        // Additional Example 3:
        // Input: n = 4
        // Output: 5
        int n3 = 4;
        System.out.println("Example 3 Output: " + solver.climbStairs(n3)); // Expected Output: 5
        
        // Additional Example 4:
        // Input: n = 5
        // Output: 8
        int n4 = 5;
        System.out.println("Example 4 Output: " + solver.climbStairs(n4)); // Expected Output: 8
        
        // Edge Case Example 5:
        // Input: n = 0
        // Output: 0
        int n5 = 0;
        System.out.println("Example 5 Output: " + solver.climbStairs(n5)); // Expected Output: 0
        
        // Edge Case Example 6:
        // Input: n = 1
        // Output: 1
        int n6 = 1;
        System.out.println("Example 6 Output: " + solver.climbStairs(n6)); // Expected Output: 1
    }
}
