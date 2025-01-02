/**
 * The MaxWaterContainer class provides a method to determine the maximum area
 * of water that can be contained between two vertical lines represented by an array
 * of heights. This is a common algorithmic problem often encountered in coding interviews.
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
 * Utilize the Two-Pointer technique to efficiently compute the maximum area. Initialize two pointers,
 * one at the beginning and one at the end of the array. Calculate the area formed between the two lines
 * pointed by the pointers. Move the pointer pointing to the shorter line inward, as moving the longer line
 * cannot possibly result in a larger area. Repeat the process until the pointers meet.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n), where n is the number of elements in the array.<br>
 * <b>Space Complexity:</b> O(1), as it uses a constant amount of additional space.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b><br>
 * This problem is fundamental in understanding the Two-Pointer technique and is commonly
 * used in coding interviews to assess a candidate's ability to implement efficient algorithms.
 * </p>
 */
import java.util.*;

public class MaxWaterContainer {

    /**
     * Calculates the maximum area of water that can be contained between two lines.
     *
     * @param heights An array of integers where each element represents the height of a line.
     * @param length  The number of elements in the heights array.
     * @return The maximum area of water that can be contained.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize two pointers, `left` at the start (0) and `right` at the end (length - 1) of the array.</li>
     *     <li>Initialize `maxArea` to 0 to keep track of the maximum area found.</li>
     *     <li>Loop while `left` is less than `right`:
     *         <ol type="a">
     *             <li>Determine the height of the container by taking the minimum of `heights[left]` and `heights[right]`.</li>
     *             <li>Calculate the width between the two pointers as `right - left`.</li>
     *             <li>Compute the current area as `height * width`.</li>
     *             <li>Update `maxArea` if the current area is larger than the previously recorded `maxArea`.</li>
     *             <li>Move the pointer pointing to the shorter line inward by one step:
     *                 <ul>
     *                     <li>If `heights[left] < heights[right]`, increment `left`.</li>
     *                     <li>Otherwise, decrement `right`.</li>
     *                 </ul>
     *             </li>
     *         </ol>
     *     </li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b><br>
     * <pre>
     * Input: heights = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * Explanation: The lines at positions 1 and 8 form the container with the maximum area of 49.
     * </pre>
     * </p>
     */
    public static int maxArea(int[] heights, int length) {
        int left = 0;                   // Initialize the left pointer at the start of the array
        int right = length - 1;         // Initialize the right pointer at the end of the array
        int maxArea = 0;                // Variable to keep track of the maximum area found

        // Iterate until the two pointers meet
        while (left < right) {
            // Determine the height of the container by the shorter line
            int height = Math.min(heights[left], heights[right]);
            // Calculate the width between the two pointers
            int width = right - left;
            // Calculate the current area
            int currentArea = height * width;
            // Update maxArea if the current area is larger
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer that points to the shorter line inward
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
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
        int[] array1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] array2 = {3, 1, 2, 4, 5};
        int[] array3 = {10, 25, 32, 45, 55, 68};
        int[] array4 = {1, 5, 4, 3};
        int[] array5 = {3, 1, 2, 1, 5};
        int[] array6 = {1, 2, 1, 3, 5};
        int[] array7 = {2, 5, 5, 2, 3, 5, 1, 2, 4}; 

        // Calculate the length of each array
        int len1 = array1.length;
        int len2 = array2.length;
        int len3 = array3.length;
        int len4 = array4.length;
        int len5 = array5.length;
        int len6 = array6.length;
        int len7 = array7.length;

        // Compute and print the maximum area for each array
        System.out.println("Max area for array1: " + maxArea(array1, len1)); // Expected: 49
        System.out.println("Max area for array2: " + maxArea(array2, len2)); // Expected: 8
        System.out.println("Max area for array3: " + maxArea(array3, len3)); // Expected: 150
        System.out.println("Max area for array4: " + maxArea(array4, len4)); // Expected: 6
        System.out.println("Max area for array5: " + maxArea(array5, len5)); // Expected: 6
        System.out.println("Max area for array6: " + maxArea(array6, len6)); // Expected: 6
        System.out.println("Max area for array7: " + maxArea(array7, len7)); // Expected: 8
    }
}
