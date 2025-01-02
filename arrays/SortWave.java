/**
 * The SortWave class provides methods to sort an array of integers into a wave-like
 * pattern. In a wave-sorted array, elements are arranged such that every even-indexed
 * element is greater than or equal to its adjacent elements, and every odd-indexed
 * element is less than or equal to its adjacent elements.
 *
 * Example:
 * Input: [10, 90, 49, 2, 1, 5, 23]
 * Wave Sorted Output: [10, 49, 2, 90, 1, 23, 5]
 *
 * Wave pattern: arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] ...
 *
 * This class demonstrates a naive approach to achieving wave sort by first sorting the
 * array and then swapping adjacent elements.
 */
import java.util.*;

public class SortWave {

    /**
     * Swaps two elements in an integer array.
     *
     * @param arr The array containing elements to be swapped.
     * @param a   The index of the first element to swap.
     * @param b   The index of the second element to swap.
     */
    void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * Sorts the input array in a wave-like fashion. The method first sorts the array in
     * ascending order and then swaps every pair of adjacent elements to achieve the wave pattern.
     *
     * The resulting array will satisfy:
     * arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] ...
     *
     * @param arr The input array to be sorted in wave form.
     * @param n   The number of elements in the array.
     */
    void sortInWave(int arr[], int n) {
        // Sort the input array in ascending order
        Arrays.sort(arr);

        // Swap adjacent elements to achieve the wave pattern
        for (int i = 0; i < n - 1; i += 2) {
            swap(arr, i, i + 1);
        }
    }

    /**
     * The main method serves as an entry point to test the sortInWave method with a sample input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String args[]) {
        SortWave sorter = new SortWave();
        // Sample input array
        int arr[] = {10, 90, 49, 2, 1, 5, 23};
        int n = arr.length;

        // Perform wave sort
        sorter.sortInWave(arr, n);

        // Display the wave-sorted array
        System.out.print("Wave Sorted Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
