/**
 * This class provides methods to find the peak index in a mountain array.
 * The peak index is the point where the array values increase and then decrease.
 * Methods include:
 * - Brute Force (O(N))
 * - Binary Search (O(log N))
 */
public class PeakIndexFinder {
    
    /**
     * Brute Force Approach: Linearly scan the array to find the peak.
     * Time Complexity: O(N), Space Complexity: O(1).
     * @param arr The mountain array.
     * @return The index of the peak element.
     */
    public static int peakIndexBruteForce(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1; // This should never be reached as per the problem constraints.
    }

    /**
     * Binary Search Approach: Uses binary search to efficiently find the peak index.
     * Time Complexity: O(log N), Space Complexity: O(1).
     * @param arr The mountain array.
     * @return The index of the peak element.
     */
    public static int peakIndexBinarySearch(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // Peak index
    }

    /**
     * Main method to test different approaches.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 0};
        int[] arr2 = {0, 2, 1, 0};
        int[] arr3 = {0, 10, 5, 2};
        int[] arr4 = {1, 3, 5, 7, 6, 4, 2};

        System.out.println("Brute Force Method:");
        System.out.println("Peak index in arr1: " + peakIndexBruteForce(arr1)); // Expected: 1
        System.out.println("Peak index in arr2: " + peakIndexBruteForce(arr2)); // Expected: 1
        System.out.println("Peak index in arr3: " + peakIndexBruteForce(arr3)); // Expected: 1
        System.out.println("Peak index in arr4: " + peakIndexBruteForce(arr4)); // Expected: 3

        System.out.println("\nBinary Search Method:");
        System.out.println("Peak index in arr1: " + peakIndexBinarySearch(arr1)); // Expected: 1
        System.out.println("Peak index in arr2: " + peakIndexBinarySearch(arr2)); // Expected: 1
        System.out.println("Peak index in arr3: " + peakIndexBinarySearch(arr3)); // Expected: 1
        System.out.println("Peak index in arr4: " + peakIndexBinarySearch(arr4)); // Expected: 3
    }
}
