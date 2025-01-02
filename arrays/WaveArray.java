/**
 * The WaveArray class provides a method to sort an array in a wave-like pattern.
 * In a wave-like array, elements are arranged such that `a[0] >= a[1] <= a[2] >= a[3] <= a[4]...`
 * This implementation uses the Comparing Neighbors approach to achieve the desired wave form.
 *
 * <p>
 * <b>Problem Statement:</b><br>
 * Given an array of integers, rearrange the elements into a wave-like array. A wave-like array is defined
 * as an array where elements are arranged in an alternating fashion such that:
 * <ul>
 *     <li>Elements at even indices are greater than or equal to their adjacent odd elements.</li>
 *     <li>Elements at odd indices are less than or equal to their adjacent even elements.</li>
 * </ul>
 *
 * <b>Example:</b><br>
 * <pre>
 * Input:  [3, 6, 5, 10, 7, 20]
 * Output: [6, 3, 10, 5, 20, 7]
 * Explanation: 
 * - 6 >= 3
 * - 3 <= 10
 * - 10 >= 5
 * - 5 <= 20
 * - 20 >= 7
 * </pre>
 * </p>
 *
 * <p>
 * <b>Approach:</b><br>
 * Traverse all even positioned elements of the input array and perform the following:
 * <ol>
 *     <li>If the current element is smaller than the previous odd element, swap them.</li>
 *     <li>If the current element is smaller than the next odd element, swap them.</li>
 *     <li>Move to the next even index by incrementing the index by 2.</li>
 * </ol>
 * This ensures that the elements at even indices are greater than or equal to their adjacent odd elements,
 * achieving the wave-like pattern.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n), where n is the number of elements in the array.<br>
 * <b>Space Complexity:</b> O(1), as the rearrangement is done in-place without using extra space.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b><br>
 * - Data visualization where wave-like patterns are required.<br>
 * - Signal processing applications.<br>
 * - Any scenario requiring alternating high and low values for optimal arrangement.
 * </p>
 */
import java.util.Arrays;

public class WaveArray {

    /**
     * Sorts the input array into a wave-like pattern where elements are arranged in an alternating
     * high-low pattern. Specifically, elements at even indices are greater than or equal to their
     * adjacent odd elements, and elements at odd indices are less than or equal to their adjacent
     * even elements.
     *
     * @param arr The input array of integers to be rearranged into wave form.
     * @param n   The number of elements in the array.
     * @return The rearranged array in wave-like form.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize an index `i` to 0 to start from the first even index.</li>
     *     <li>Traverse the array in steps of 2 (i.e., process even indices).</li>
     *     <li>For each even index `i`:
     *         <ul>
     *             <li>If `i > 0` and `arr[i] < arr[i - 1]`, swap `arr[i]` and `arr[i - 1]`.</li>
     *             <li>If `i < n - 1` and `arr[i] < arr[i + 1]`, swap `arr[i]` and `arr[i + 1]`.</li>
     *         </ul>
     *     </li>
     *     <li>Increment `i` by 2 to move to the next even index.</li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b><br>
     * <pre>
     * Input:  [3, 6, 5, 10, 7, 20]
     * Output: [6, 3, 10, 5, 20, 7]
     * </pre>
     * </p>
     */
    public static int[] waveArray(int[] arr, int n) {
        int i = 0;
        while (i < n) {
            // If current even index element is smaller than previous element, swap them
            if (i > 0 && arr[i - 1] > arr[i]) {
                swap(arr, i - 1, i);
            }

            // If current even index element is smaller than next element, swap them
            if (i < n - 1 && arr[i] < arr[i + 1]) {
                swap(arr, i, i + 1);
            }

            // Move to the next even index
            i += 2;
        }
        return arr;
    }

    /**
     * Swaps two elements in the array at the specified indices.
     *
     * @param arr The array containing the elements to be swapped.
     * @param i   The index of the first element.
     * @param j   The index of the second element.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * The main method serves as an entry point to test the waveArray method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample input arrays
        int[] array1 = {3, 6, 5, 10, 7, 20};
        int[] array2 = {10, 90, 49, 2, 1, 5, 23};

        // Apply the waveArray method to each sample array
        int[] wave1 = waveArray(array1, array1.length);
        int[] wave2 = waveArray(array2, array2.length);

        // Display the results
        System.out.println("Original Array1: " + Arrays.toString(array1));
        System.out.println("Wave Array1:     " + Arrays.toString(wave1));

        System.out.println("\nOriginal Array2: " + Arrays.toString(array2));
        System.out.println("Wave Array2:     " + Arrays.toString(wave2));

        // Additional Test Cases
        System.out.println("\nAdditional Test Cases:");

        // Test Case 3
        int[] array3 = {1, 2, 3, 4, 5, 6, 7};
        int[] wave3 = waveArray(array3, array3.length);
        System.out.println("Original Array3: " + Arrays.toString(array3));
        System.out.println("Wave Array3:     " + Arrays.toString(wave3));

        // Test Case 4
        int[] array4 = {9, 6, 8, 3, 7};
        int[] wave4 = waveArray(array4, array4.length);
        System.out.println("\nOriginal Array4: " + Arrays.toString(array4));
        System.out.println("Wave Array4:     " + Arrays.toString(wave4));

        // Test Case 5
        int[] array5 = {1};
        int[] wave5 = waveArray(array5, array5.length);
        System.out.println("\nOriginal Array5: " + Arrays.toString(array5));
        System.out.println("Wave Array5:     " + Arrays.toString(wave5));

        // Test Case 6
        int[] array6 = {};
        int[] wave6 = waveArray(array6, array6.length);
        System.out.println("\nOriginal Array6: " + Arrays.toString(array6));
        System.out.println("Wave Array6:     " + Arrays.toString(wave6));
    }
}
