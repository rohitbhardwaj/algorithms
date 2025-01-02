/**
 * The MergeSortedArrays class provides a method to merge two sorted integer arrays
 * into a single sorted array. This is a fundamental algorithmic problem that
 * tests understanding of array manipulation and efficient merging techniques.
 */
import java.util.Arrays;

public class MergeSortedArrays {

    /**
     * Merges two sorted arrays into a single sorted array.
     *
     * @param arr1 The first sorted array of integers.
     * @param arr2 The second sorted array of integers.
     * @return A new sorted array containing all elements from arr1 and arr2.
     */
    private int[] mergeArrays(int[] arr1, int[] arr2) {
        // Initialize index counters for arr1, arr2, and the merged array
        int i = 0, j = 0, k = 0;
        // Calculate the total length of the merged array
        int mergedArrayLength = arr1.length + arr2.length;

        // Create the merged array to hold all elements from arr1 and arr2
        int[] mergedArray = new int[mergedArrayLength];

        /**
         * Iterate through both arrays, comparing the current elements and adding
         * the smaller one to the merged array. Increment the respective index counters.
         */
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                mergedArray[k] = arr1[i];
                i++; // Move to the next element in arr1
            } else {
                mergedArray[k] = arr2[j];
                j++; // Move to the next element in arr2
            }
            k++; // Move to the next position in mergedArray
        }

        /*
         * After one of the arrays is exhausted, append the remaining elements
         * from the other array to the merged array.
         */

        // Add any remaining elements from arr1
        while (i < arr1.length) {
            mergedArray[k] = arr1[i];
            i++;
            k++;
        }

        // Add any remaining elements from arr2
        while (j < arr2.length) {
            mergedArray[k] = arr2[j];
            j++;
            k++;
        }

        return mergedArray; // Return the fully merged and sorted array
    }

    /**
     * The main method serves as an entry point to test the mergeArrays method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        MergeSortedArrays arrays = new MergeSortedArrays();
        // Sample input arrays
        int[] arr1 = {0, 3, 4, 31};
        int[] arr2 = {4, 6, 30};

        // Merge the two arrays
        int[] mergedArray = arrays.mergeArrays(arr1, arr2);
        // Display the merged array
        System.out.println("Merged Array: " + Arrays.toString(mergedArray));
    }
}
