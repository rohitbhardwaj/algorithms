import java.util.HashMap;
import java.util.Map;

/**
 * The {@code LongestSubarraySumK} class provides a method to find the length of the longest contiguous
 * subarray within a given array of integers that sums up to a target value {@code k}.
 *
 * <p>This implementation utilizes a HashMap to store cumulative sums and their earliest indices,
 * enabling an efficient solution with linear time complexity.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * int[] arr = {10, 5, 2, 7, 1, 9};
 * int k = 15;
 * int result = LongestSubarraySumK.findLongestSubarraySumK(arr, k);
 * System.out.println(result); // Output: 4
 * }</pre>
 *
 * <p>The above example identifies the subarray {5, 2, 7, 1} as the longest subarray summing to 15.</p>
 *
 * @author
 */
public class LongestSubarraySumK {

    /**
     * Finds the length of the longest contiguous subarray within the input array {@code arr}
     * that sums exactly to the target value {@code k}.
     *
     * <p>The method employs a HashMap to store cumulative sums and their earliest indices.
     * As it iterates through the array, it checks if there exists a previous cumulative sum
     * such that the difference between the current cumulative sum and this previous sum equals {@code k}.
     * If such a sum exists, it indicates the presence of a subarray that sums to {@code k},
     * and the length of this subarray is potentially the longest found so far.</p>
     *
     * <p>This approach ensures that each element is processed only once, resulting in an
     * efficient solution with a time complexity of O(n), where n is the length of {@code arr}.</p>
     *
     * @param arr The input array of integers.
     * @param k   The target sum for the subarrays.
     * @return The length of the longest contiguous subarray that sums to {@code k}.
     *         Returns 0 if no such subarray exists.
     */
    public static int findLongestSubarraySumK(int[] arr, int k) {
        // HashMap to store the earliest index at which each cumulative sum occurs.
        // Key: Cumulative sum up to the current index.
        // Value: Earliest index at which this cumulative sum has been seen.
        Map<Integer, Integer> map = new HashMap<>();

        // Initialize the HashMap with a cumulative sum of 0 occurring at index -1.
        // This accounts for subarrays that start from index 0.
        map.put(0, -1);

        int sum = 0;       // Variable to store the cumulative sum.
        int maxLength = 0; // Variable to track the maximum length of valid subarrays.

        // Iterate through the array to compute cumulative sums and identify valid subarrays.
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // Update the cumulative sum with the current element.

            // Check if (sum - k) exists in the HashMap.
            // If it does, a subarray ending at index 'i' with sum 'k' exists.
            if (map.containsKey(sum - k)) {
                // Calculate the length of this subarray.
                int currentLength = i - map.get(sum - k);
                // Update maxLength if this subarray is longer than previously found subarrays.
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
            }

            // If the current cumulative sum is not already in the HashMap, add it.
            // We store only the first occurrence to maximize the potential subarray length.
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        // After traversing the array, 'maxLength' holds the length of the longest valid subarray.
        return maxLength;
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code findLongestSubarraySumK} method
     * with various input scenarios.
     *
     * <p>It defines a series of test cases, invokes the {@code findLongestSubarraySumK} method on each,
     * and prints the results to the console.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        // Define an array of test input scenarios with corresponding target sums and expected outputs.
        TestCase[] testCases = {
            new TestCase(new int[]{10, 5, 2, 7, 1, 9}, 15, 4),
            new TestCase(new int[]{-5, 8, -14, 2, 4, 12}, -5, 5),
            new TestCase(new int[]{1, 2, 3, 4, 5}, 9, 3),
            new TestCase(new int[]{1, -1, 5, -2, 3}, 3, 4),
            new TestCase(new int[]{-2, -1, 2, 1}, 0, 4),
            new TestCase(new int[]{1, 2, 3}, 6, 3),
            new TestCase(new int[]{1, 2, 3}, 7, 0),
            new TestCase(new int[]{0, 0, 0, 0}, 0, 4),
            new TestCase(new int[]{1}, 1, 1),
            new TestCase(new int[]{1}, 0, 0)
        };

        // Iterate through each test case, call the findLongestSubarraySumK method, and display the results.
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int result = findLongestSubarraySumK(tc.arr, tc.k);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: arr = " + arrayToString(tc.arr) + ", k = " + tc.k);
            System.out.println("Expected Output: " + tc.expectedOutput);
            System.out.println("Actual Output: " + result);
            System.out.println(result == tc.expectedOutput ? "✅ Passed" : "❌ Failed");
            System.out.println("---------------------------");
        }
    }

    /**
     * A helper method to convert an integer array to its string representation.
     *
     * @param arr The input array of integers.
     * @return A string representing the array in the format [num1, num2, ...].
     */
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<arr.length; i++) {
            sb.append(arr[i]);
            if(i != arr.length -1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * A nested static class to represent a test case with input and expected output.
     */
    static class TestCase {
        int[] arr;           // The input array of integers.
        int k;               // The target sum.
        int expectedOutput;  // The expected length of the longest subarray that sums to k.

        /**
         * Constructs a new TestCase instance.
         *
         * @param arr            The input array of integers.
         * @param k              The target sum for subarrays.
         * @param expectedOutput The expected length of the longest valid subarray.
         */
        TestCase(int[] arr, int k, int expectedOutput) {
            this.arr = arr;
            this.k = k;
            this.expectedOutput = expectedOutput;
        }
    }
}
