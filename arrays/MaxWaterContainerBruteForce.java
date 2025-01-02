/**
 * The MaxWaterContainerBruteForce class provides a method to determine the maximum area
 * of water that can be contained between two vertical lines represented by an array
 * of heights using a brute-force approach. This method is straightforward but less efficient
 * compared to optimized solutions.
 */
import java.io.*;

public class MaxWaterContainerBruteForce {

    /**
     * Calculates the maximum area of water that can be contained between two lines using a brute-force approach.
     *
     * @param heights An array of integers where each element represents the height of a line.
     * @return The maximum area of water that can be contained.
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

        // Compute and print the maximum area for each array
        System.out.println("Max area for array1: " + maxArea(array1));
        System.out.println("Max area for array2: " + maxArea(array2));
    }
}