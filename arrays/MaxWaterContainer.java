/**
 * The MaxWaterContainer class provides a method to determine the maximum area
 * of water that can be contained between two vertical lines represented by an array
 * of heights. This is a common algorithmic problem often encountered in coding interviews.
 */
import java.util.*;

public class MaxWaterContainer {

    /**
     * Calculates the maximum area of water that can be contained between two lines.
     *
     * @param heights An array of integers where each element represents the height of a line.
     * @param length  The number of elements in the heights array.
     * @return The maximum area of water that can be contained.
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
        int[] array1 = {1, 5, 4, 3};
        int[] array2 = {3, 1, 2, 4, 5};

        // Calculate the length of each array
        int len1 = array1.length;
        int len2 = array2.length;

        // Compute and print the maximum area for each array
        System.out.println("Max area for array1: " + maxArea(array1, len1));
        System.out.println("Max area for array2: " + maxArea(array2, len2));
    }
}
