/**
 * The MaxWaterContainerBruteForce class provides a method to determine the maximum area
 * of water that can be contained between two vertical lines represented by an array
 * of heights using a brute-force approach. This method is straightforward but less efficient
 * compared to optimized solutions.
 *
 * <p>
 * <b>Problem Statement:</b><br>
 * Given n non-negative integers where each represents a point at coordinate (i, ai). n vertical lines
 * are drawn such that the two endpoints of the line i are at (i, ai) and (i, 0).
 * Find two lines, which together with the x-axis forms a container, such that the container contains the most water.
 * 
 * <b>Example:</b><br>
 * <pre>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The lines at positions 1 and 8 (0-based indexing) form the container with the maximum area of 49.
 * </pre>
 * </p>
 *
 * <p>
 * <b>Approach:</b><br>
 * Utilize a brute-force approach by iterating through each possible pair of lines and calculating the area
 * they can contain. Keep track of the maximum area found during the iterations. This approach has a time
 * complexity of O(n²), making it less efficient for large datasets.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n²), where n is the number of elements in the array.<br>
 * <b>Space Complexity:</b> O(1), as it uses a constant amount of additional space.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b><br>
 * This problem is fundamental in understanding algorithmic problem-solving and is commonly
 * used in coding interviews to assess a candidate's ability to implement and analyze brute-force
 * solutions before optimizing them.
 * </p>
 */
import java.io.*;

public class MaxWaterContainerBruteForce {

    /**
     * Calculates the maximum area of water that can be contained between two lines using a brute-force approach.
     *
     * @param heights An array of integers where each element represents the height of a line.
     * @return The maximum area of water that can be contained.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize a variable `maxArea` to keep track of the maximum area found.</li>
     *     <li>Iterate through each element in the array as the first line.</li>
     *     <li>For each first line, iterate through all subsequent lines as the second line.</li>
     *     <li>For each pair of lines, calculate the area:
     *         <ul>
     *             <li>Determine the height by taking the minimum of the two line heights.</li>
     *             <li>Calculate the width as the distance between the two lines.</li>
     *             <li>Compute the current area as height multiplied by width.</li>
     *             <li>Update `maxArea` if the current area is larger than the previously recorded maximum.</li>
     *         </ul>
     *     </li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b><br>
     * For the input array [1,5,4,3], the method will evaluate the following pairs:
     * <ul>
     *     <li>(1,5) → Area = 1 * 1 = 1</li>
     *     <li>(1,4) → Area = 1 * 2 = 2</li>
     *     <li>(1,3) → Area = 1 * 3 = 3</li>
     *     <li>(5,4) → Area = 4 * 1 = 4</li>
     *     <li>(5,3) → Area = 3 * 2 = 6</li>
     *     <li>(4,3) → Area = 3 * 1 = 3</li>
     * </ul>
     * The maximum area found is 6.
     * </p>
     */
    public static int maxArea(int[] heights) {
        int maxArea = 0; // Variable to keep track of the maximum area found

        // Iterate through each pair of lines to calculate the area they can contain
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                // Determine the height by the shorter line
                int height = Math.min(heights[i], heights[j]);
                // Calculate the width between the two lines
                int width = j - i;
                // Calculate the current area
                int currentArea = height * width;
                // Update maxArea if the current area is larger
                maxArea = Math.max(maxArea, currentArea);
            }
        }

        return maxArea; // Return the maximum area found
    }

    /**
     * The main method serves as an entry point to test the maxArea method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample input arrays representing heights of lines
        int[] array1 = {1, 5, 4, 3};
        int[] array2 = {3, 1, 2, 4, 5};
        int[] array3 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] array4 = {1, 1, 1, 1};
        int[] array5 = {2, 5, 5, 2, 3, 5, 1, 2, 4};

        // Compute and print the maximum area for each array
        System.out.println("Max area for array1: " + maxArea(array1)); // Expected Output: 6
        System.out.println("Max area for array2: " + maxArea(array2)); // Expected Output: 6
        System.out.println("Max area for array3: " + maxArea(array3)); // Expected Output: 49
        System.out.println("Max area for array4: " + maxArea(array4)); // Expected Output: 1
        System.out.println("Max area for array5: " + maxArea(array5)); // Expected Output: 10
    }
}
