/**
 * The FirstRecurring class provides methods to identify the first recurring character
 * in an array of integers. A recurring character is one that appears more than once
 * in the array. The "first" recurring character is the one whose second occurrence
 * appears before the second occurrences of any other characters.
 *
 * <p>
 * <b>Problem Statement:</b>
 * Given an array of integers, find the first element that appears more than once.
 * If no such element exists, return {@code null}.
 *
 * <b>Examples:</b>
 * <ul>
 *     <li>Input: [2, 5, 1, 2, 3, 5, 1, 2, 4] → Output: 2</li>
 *     <li>Input: [2, 1, 1, 2, 3, 5, 1, 2, 4] → Output: 1</li>
 *     <li>Input: [2, 3, 4, 5] → Output: null</li>
 *     <li>Input: [2, 5, 5, 2, 3, 5, 1, 2, 4] → Output: 5</li>
 * </ul>
 *
 * <p>
 * <b>Approach:</b>
 * Utilize a {@link HashSet} to keep track of the elements that have been encountered.
 * Iterate through the array, and for each element, check if it already exists in the
 * HashSet:
 * <ul>
 *     <li>If it does, return that element as the first recurring character.</li>
 *     <li>If it does not, add the element to the HashSet and continue.</li>
 * </ul>
 *
 * <p>
 * <b>Time Complexity:</b> O(n), where n is the number of elements in the array.
 * <b>Space Complexity:</b> O(n), due to the additional space used by the HashSet.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b>
 * This problem is commonly used in coding interviews to assess a candidate's ability
 * to implement efficient algorithms using appropriate data structures.
 * </p>
 */
import java.util.HashSet;

public class FirstRecurring {

    /**
     * Finds the first recurring character in the given array of integers.
     *
     * @param array The input array of integers.
     * @return The first recurring integer if any; otherwise, {@code null}.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize an empty {@link HashSet} to keep track of seen elements.</li>
     *     <li>Iterate through each element in the array:</li>
     *         <ul>
     *             <li>If the current element is already in the HashSet, return it as the first recurring character.</li>
     *             <li>If not, add the current element to the HashSet.</li>
     *         </ul>
     *     <li>If no recurring character is found after traversing the array, return {@code null}.</li>
     * </ol>
     * </p>
     */
    private Integer firstRecurringCharacter(int[] array) {
        // Initialize a HashSet to store elements as we iterate through the array
        HashSet<Integer> seenElements = new HashSet<>();

        // Iterate through each element in the array
        for (int element : array) {
            // Check if the current element is already in the HashSet
            if (seenElements.contains(element)) {
                // If it is, we've found the first recurring character
                return element;
            } else {
                // If not, add the element to the HashSet for future reference
                seenElements.add(element);
            }
        }

        // If no recurring character is found, return null
        return null;
    }

    /**
     * The main method serves as the entry point to test the firstRecurringCharacter method
     * with various sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Instantiate the FirstRecurring class
        FirstRecurring obj = new FirstRecurring();

        // Define various test cases
        int[][] testArrays = {
            {2, 5, 1, 2, 3, 5, 1, 2, 4},
            {2, 1, 1, 2, 3, 5, 1, 2, 4},
            {2, 3, 4, 5},
            {2, 5, 5, 2, 3, 5, 1, 2, 4},
            {}, // Empty array
            {1}, // Single element
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1},
            {3, 3, 3, 3},
            {2, 5, 5, 2, 3, 5, 1, 2, 4}
        };

        // Define the expected outputs for reference
        String[] expectedOutputs = {
            "2",
            "1",
            "null",
            "5",
            "null",
            "null",
            "null",
            "1",
            "3",
            "5"
        };

        // Iterate through each test case and display the results
        for (int i = 0; i < testArrays.length; i++) {
            int[] testArray = testArrays[i];
            Integer result = obj.firstRecurringCharacter(testArray);

            // Convert the result to string, handling the null case
            String resultStr = (result != null) ? result.toString() : "null";

            // Display the test case and the result
            System.out.println("Test Case " + (i + 1) + ": " + arrayToString(testArray));
            System.out.println("Expected Output: " + expectedOutputs[i]);
            System.out.println("Actual Output: " + resultStr);
            System.out.println(resultStr.equals(expectedOutputs[i]) ? "✅ Passed" : "❌ Failed");
            System.out.println("----------------------------------------------------");
        }
    }

    /**
     * Helper method to convert an integer array to a string representation.
     *
     * @param array The input array of integers.
     * @return A string representation of the array.
     */
    private static String arrayToString(int[] array) {
        if (array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int num : array) {
            sb.append(num).append(", ");
        }
        // Remove the trailing comma and space
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
}
