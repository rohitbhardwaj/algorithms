import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code LongestIncreasingSubsequence} class provides functionality to determine the length of
 * the longest strictly increasing subsequence within an integer array.
 *
 * <p>The class implements two primary methods:
 * <ul>
 *     <li><strong>Dynamic Programming (DP) Approach:</strong> Utilizes a DP array to compute the LIS.</li>
 *     <li><strong>Binary Search Approach:</strong> Employs a tails array with binary search to optimize performance.</li>
 * </ul>
 * </p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * LongestIncreasingSubsequence solver = new LongestIncreasingSubsequence();
 * int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
 * System.out.println("DP Approach: " + solver.lengthOfLISDP(nums1)); // Output: 4
 * System.out.println("Binary Search Approach: " + solver.lengthOfLISBinarySearch(nums1)); // Output: 4
 *
 * int[] nums2 = {0, 1, 0, 3, 2, 3};
 * System.out.println("DP Approach: " + solver.lengthOfLISDP(nums2)); // Output: 4
 * System.out.println("Binary Search Approach: " + solver.lengthOfLISBinarySearch(nums2)); // Output: 4
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong></p>
 * <ul>
 *     <li><strong>DP Approach:</strong> O(n²)</li>
 *     <li><strong>Binary Search Approach:</strong> O(n log n)</li>
 * </ul>
 *
 * <p><strong>Space Complexity:</strong> O(n) for both approaches.</p>
 *
 * @author 
 */
public class LongestIncreasingSubsequence {
    
    /**
     * Finds the length of the longest increasing subsequence using the Dynamic Programming approach.
     *
     * <p>This method initializes a DP array where each element represents the length of the longest
     * increasing subsequence ending with the corresponding element in the input array. It iteratively
     * updates the DP array based on previous computations.</p>
     *
     * @param nums The input array of integers.
     * @return The length of the longest strictly increasing subsequence.
     */
    public int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        // dp[i] will hold the length of the LIS ending at index i
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each element is an LIS of length 1 by itself
        
        // Iterate through the array
        for (int i = 1; i < n; i++) {
            // Check all previous elements for possible extensions
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // Update dp[i] if a longer subsequence is found
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        // Find the maximum value in dp array
        int maxLIS = 1;
        for (int length : dp) {
            if (length > maxLIS) {
                maxLIS = length;
            }
        }
        
        return maxLIS;
    }
    
    /**
     * Finds the length of the longest increasing subsequence using the Binary Search approach.
     *
     * <p>This method maintains a tails array where each element represents the smallest possible tail
     * of all increasing subsequences with a specific length. It employs binary search to efficiently
     * update the tails array, ensuring optimal performance.</p>
     *
     * @param nums The input array of integers.
     * @return The length of the longest strictly increasing subsequence.
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            if (tails.isEmpty() || num > tails.get(tails.size() - 1)) {
                tails.add(num);
            } else {
                // Binary search to find the first element in tails >= num
                int left = 0;
                int right = tails.size() - 1;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (tails.get(mid) < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                tails.set(left, num); // Replace the found element with num
            }
        }
        
        return tails.size();
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code LongestIncreasingSubsequence} class.
     *
     * <p>It demonstrates the usage of both the Dynamic Programming and Binary Search approaches
     * with sample inputs, including the provided examples and additional test cases.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        LongestIncreasingSubsequence solver = new LongestIncreasingSubsequence();
        
        // Example 1:
        // Input: [10, 9, 2, 5, 3, 7, 101, 18]
        // Output: 4
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Example 1 Output (DP Approach): " + solver.lengthOfLISDP(nums1)); // Output: 4
        System.out.println("Example 1 Output (Binary Search Approach): " + solver.lengthOfLISBinarySearch(nums1)); // Output: 4
        
        // Example 2:
        // Input: [0, 1, 0, 3, 2, 3]
        // Output: 4
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Example 2 Output (DP Approach): " + solver.lengthOfLISDP(nums2)); // Output: 4
        System.out.println("Example 2 Output (Binary Search Approach): " + solver.lengthOfLISBinarySearch(nums2)); // Output: 4
        
        // Example 3:
        // Input: [7, 7, 7, 7, 7, 7, 7]
        // Output: 1
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Example 3 Output (DP Approach): " + solver.lengthOfLISDP(nums3)); // Output: 1
        System.out.println("Example 3 Output (Binary Search Approach): " + solver.lengthOfLISBinarySearch(nums3)); // Output: 1
        
        // Additional Example 4:
        // Input: [1, 3, 6, 7, 9, 4, 10, 5, 6]
        // Output: 6
        int[] nums4 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println("Example 4 Output (DP Approach): " + solver.lengthOfLISDP(nums4)); // Output: 6
        System.out.println("Example 4 Output (Binary Search Approach): " + solver.lengthOfLISBinarySearch(nums4)); // Output: 6
        
        // Edge Case Example 5:
        // Input: []
        // Output: 0
        int[] nums5 = {};
        System.out.println("Example 5 Output (DP Approach): " + solver.lengthOfLISDP(nums5)); // Output: 0
        System.out.println("Example 5 Output (Binary Search Approach): " + solver.lengthOfLISBinarySearch(nums5)); // Output: 0
        
        // Edge Case Example 6:
        // Input: [1]
        // Output: 1
        int[] nums6 = {1};
        System.out.println("Example 6 Output (DP Approach): " + solver.lengthOfLISDP(nums6)); // Output: 1
        System.out.println("Example 6 Output (Binary Search Approach): " + solver.lengthOfLISBinarySearch(nums6)); // Output: 1
    }
}
