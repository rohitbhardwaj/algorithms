/**
 * The MissingNumber class provides methods to determine the missing number
 * in a sequence of integers. Given an array containing n distinct numbers taken from
 * the range 1 to n+1, the method identifies the missing number in linear time
 * and constant space. This problem is a common algorithmic challenge often
 * encountered in coding interviews.
 *
 * <p>
 * <b>Problem Statement:</b><br>
 * Given an array of n distinct integers where each integer is in the range [1, n+1],
 * find the one number that is missing from the array.
 *
 * <b>Example:</b>
 * <pre>
 * Input: [1, 2, 4, 5, 6]
 * Output: 3
 * Explanation: The number 3 is missing from the array.
 *
 * Input: [2, 1, 4, 5, 6]
 * Output: 3
 * Explanation: The number 3 is missing from the array.
 *
 * Input: [1, 2, 3, 4, 5]
 * Output: 6
 * Explanation: The number 6 is missing from the array.
 * </pre>
 * </p>
 *
 * <p>
 * <b>Approach:</b><br>
 * The solution leverages the mathematical formula for the sum of the first n natural numbers:
 * Sum = n*(n+1)/2. By calculating the expected sum for the range [1, n+1] and subtracting
 * the actual sum of the array elements, the missing number can be determined efficiently.
 * This approach ensures a time complexity of O(n) and a space complexity of O(1).
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n), where n is the number of elements in the array.<br>
 * <b>Space Complexity:</b> O(1), as it uses a constant amount of additional space.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b><br>
 * This problem is fundamental in understanding array manipulation and algorithm optimization.
 * It is commonly used in coding interviews to assess a candidate's ability to implement
 * efficient solutions using mathematical concepts.
 * </p>
 */
import java.util.Arrays;

public class MissingNumber {

    /**
     * Finds the missing number in the array using the mathematical sum formula.
     *
     * @param nums An array of distinct integers taken from the range [1, n+1], where n is the length of the array.
     * @return The missing integer from the array.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Determine the length of the array, denoted as n.</li>
     *     <li>Calculate the expected sum of numbers from 1 to n+1 using the formula: Sum = (n+1)*(n+2)/2.</li>
     *     <li>Iterate through the array and subtract each element from the expected sum.</li>
     *     <li>The remaining value after the iteration is the missing number.</li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b><br>
     * For the input array [1, 2, 4, 5, 6]:
     * <ul>
     *     <li>n = 5</li>
     *     <li>Expected Sum = (5+1)*(5+2)/2 = 6*7/2 = 21</li>
     *     <li>Actual Sum = 1 + 2 + 4 + 5 + 6 = 18</li>
     *     <li>Missing Number = 21 - 18 = 3</li>
     * </ul>
     * </p>
     */
    public static int findMissingNumber(int[] nums) {
        int n = nums.length;
        // Calculate the expected sum of numbers from 1 to n+1
        int expectedSum = ((n + 1) * (n + 2)) / 2;

        // Subtract each number in the array from the expected sum
        for (int num : nums) {
            expectedSum -= num;
        }

        // The remaining value is the missing number
        return expectedSum;
    }

    /**
     * The main method serves as an entry point to test the findMissingNumber method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define various test cases
        int[][] testCases = {
            {1, 2, 4, 5, 6},             // Missing number: 3
            {2, 1, 4, 5, 6},             // Missing number: 3
            {1, 2, 3, 4, 5},             // Missing number: 6
            {2, 3, 4, 5, 6, 7, 8, 9},    // Missing number: 1
            {1},                          // Missing number: 2
            {},                           // Missing number: 1
            {1, 3},                        // Missing number: 2
            {2, 1},                        // Missing number: 3
            {1, 2, 3, 5},                  // Missing number: 4
            {1, 2, 3, 4, 5, 7, 8, 9, 10}  // Missing number: 6
        };

        // Iterate through each test case and display the result
        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = testCases[i];
            int missingNumber = findMissingNumber(testCase);
            System.out.println("Test Case " + (i + 1) + ": " + Arrays.toString(testCase));
            System.out.println("Missing Number: " + missingNumber + "\n");
        }
    }
}
