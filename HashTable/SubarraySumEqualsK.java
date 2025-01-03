import java.util.HashMap;
import java.util.Map;

/**
 * The {@code SubarraySumEqualsK} class provides a method to find the total number of continuous
 * subarrays within a given array of integers that sum up to a target value {@code k}.
 *
 * <p>This implementation utilizes a HashMap to store cumulative sums and their frequencies,
 * enabling an efficient solution with linear time complexity.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * int[] nums = {1, 1, 1};
 * int k = 2;
 * int result = SubarraySumEqualsK.subarraySum(nums, k);
 * System.out.println(result); // Output: 2
 * }</pre>
 *
 * @author
 */
public class SubarraySumEqualsK {

    /**
     * Calculates the total number of continuous subarrays within the input array {@code nums}
     * that sum up to the target value {@code k}.
     *
     * <p>The method employs a HashMap to store cumulative sums and their frequencies.
     * As it iterates through the array, it checks if there exists a previous cumulative sum
     * such that the difference between the current cumulative sum and this previous sum equals {@code k}.
     * If such a sum exists, it indicates the presence of a subarray that sums to {@code k}.</p>
     *
     * <p>This approach ensures that each element is processed only once, resulting in an
     * efficient solution with a time complexity of O(n), where n is the length of {@code nums}.</p>
     *
     * @param nums The input array of integers.
     * @param k    The target sum for the subarrays.
     * @return The total number of continuous subarrays that sum up to {@code k}.
     */
    public static int subarraySum(int[] nums, int k) {
        // HashMap to store the frequency of cumulative sums encountered.
        // Key: Cumulative sum up to the current index.
        // Value: Number of times this cumulative sum has occurred.
        Map<Integer, Integer> map = new HashMap<>();

        // Initialize the HashMap with a cumulative sum of 0 occurring once.
        // This accounts for subarrays that start from index 0.
        map.put(0, 1);

        int sum = 0;  // Variable to store the cumulative sum.
        int ans = 0;  // Variable to count the number of valid subarrays.

        // Iterate through the array to compute cumulative sums and count valid subarrays.
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];  // Update the cumulative sum with the current element.

            // Check if (sum - k) exists in the HashMap.
            // If it does, there exists a subarray ending at index j with sum equal to k.
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);  // Increment the count by the number of times (sum - k) has occurred.
            }

            // Update the frequency of the current cumulative sum in the HashMap.
            // If the sum is already present, increment its frequency by 1.
            // Otherwise, add it to the HashMap with a frequency of 1.
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        // After traversing the array, 'ans' holds the total number of valid subarrays.
        return ans;
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code subarraySum} method with various input scenarios.
     *
     * <p>It defines a series of test cases, invokes the {@code subarraySum} method on each, and prints the results to the console.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        // Define an array of test input scenarios with corresponding target sums and expected outputs.
        TestCase[] testCases = {
            new TestCase(new int[]{1, 1, 1}, 2, 2),
            new TestCase(new int[]{1, 2, 3}, 3, 2),
            new TestCase(new int[]{-1, -1, 1}, 0, 1),
            new TestCase(new int[]{1, -1, 0}, 0, 3),
            new TestCase(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7, 4),
            new TestCase(new int[]{1}, 0, 0),
            new TestCase(new int[]{0, 0, 0, 0}, 0, 10),
            new TestCase(new int[]{1, 2, 1, 2, 1}, 3, 4),
            new TestCase(new int[]{1000, -1000, 1000}, 1000, 2),
            new TestCase(new int[]{1, -1, 1, -1, 1, -1}, 0, 3)
        };

        // Iterate through each test case, call the subarraySum method, and display the results.
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int result = subarraySum(tc.nums, tc.k);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: nums = " + arrayToString(tc.nums) + ", k = " + tc.k);
            System.out.println("Expected Output: " + tc.expectedOutput);
            System.out.println("Actual Output: " + result);
            System.out.println(result == tc.expectedOutput ? "✅ Passed" : "❌ Failed");
            System.out.println("---------------------------");
        }
    }

    /**
     * A helper method to convert an integer array to its string representation.
     *
     * @param nums The input array of integers.
     * @return A string representing the array in the format [num1, num2, ...].
     */
    private static String arrayToString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<nums.length; i++) {
            sb.append(nums[i]);
            if(i != nums.length -1) {
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
        int[] nums;           // The input array of integers.
        int k;                // The target sum.
        int expectedOutput;   // The expected number of subarrays that sum to k.

        /**
         * Constructs a new TestCase instance.
         *
         * @param nums           The input array of integers.
         * @param k              The target sum for subarrays.
         * @param expectedOutput The expected number of valid subarrays.
         */
        TestCase(int[] nums, int k, int expectedOutput) {
            this.nums = nums;
            this.k = k;
            this.expectedOutput = expectedOutput;
        }
    }
}
