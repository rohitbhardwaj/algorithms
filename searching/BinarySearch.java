/**
 * The BinarySearch class provides a method to perform a binary search on a sorted array of integers.
 * Binary search is an efficient algorithm for finding an item from a sorted list of items.
 * It works by repeatedly dividing the search interval in half.
 *
 * <p>
 * <b>Algorithm Steps:</b>
 * <ol>
 *     <li>Initialize two pointers, `first` and `last`, to the start and end of the array respectively.</li>
 *     <li>Calculate the middle index `mid` of the current search interval.</li>
 *     <li>Compare the element at `mid` with the target key:
 *         <ul>
 *             <li>If the element is equal to the key, return the index.</li>
 *             <li>If the element is less than the key, adjust the `first` pointer to `mid + 1` to search in the right half.</li>
 *             <li>If the element is greater than the key, adjust the `last` pointer to `mid - 1` to search in the left half.</li>
 *         </ul>
 *     </li>
 *     <li>Repeat the process until the element is found or the search interval is empty.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(log n), where n is the number of elements in the array.
 * <b>Space Complexity:</b> O(1), as it uses a constant amount of additional space.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b>
 * Binary search is widely used in various applications such as:
 * <ul>
 *     <li>Searching in databases.</li>
 *     <li>Implementing efficient search operations in large datasets.</li>
 *     <li>Algorithm design problems requiring efficient lookup operations.</li>
 * </ul>
 * </p>
 */
public class BinarySearch {

    /**
     * Performs a binary search on a sorted array to find the index of a given key.
     *
     * @param arr The sorted array of integers where the search is performed.
     * @param key The target integer value to search for.
     * @return A string indicating the result of the search:
     *         - If found, returns "Element Found At Index X".
     *         - If not found, returns "Element Not Found".
     *
     * <p>
     * <b>Precondition:</b> The array `arr` must be sorted in ascending order.
     * </p>
     *
     * <p>
     * <b>Example:</b>
     * <pre>
     * {@code
     * int[] array = {10, 25, 32, 45, 55, 68};
     * String result = binarySearch(array, 55);
     * // Output: "Element Found At Index 4"
     * }
     * </pre>
     * </p>
     */
    public String binarySearch(int[] arr, int key) {
        // Initialize the starting and ending indices
        int first = 0;
        int last = arr.length - 1;

        // Continue the search while the search space is valid
        while (first <= last) {
            // Calculate the middle index to prevent integer overflow
            int mid = first + (last - first) / 2;

            // Debug statement to trace the current search interval
            System.out.println("Searching in range [" + first + ", " + last + "], mid=" + mid + ", arr[mid]=" + arr[mid]);

            // Check if the key is present at mid
            if (arr[mid] == key) {
                return "Element Found At Index " + mid;
            }

            // If the key is greater, ignore the left half
            if (arr[mid] < key) {
                first = mid + 1;
            }
            // If the key is smaller, ignore the right half
            else {
                last = mid - 1;
            }
        }

        // If the key was not found in the array
        return "Element Not Found";
    }

    /**
     * The main method serves as the entry point to test the binarySearch method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();

        // Test Case 1: Key exists in the array
        int[] array1 = {10, 25, 32, 45, 55, 68};
        String result1 = bs.binarySearch(array1, 55);
        System.out.println("\nTest Case 1:");
        System.out.println("Array: " + arrayToString(array1));
        System.out.println("Searching for key 55: " + result1);

        // Test Case 2: Key does not exist in the array
        int[] array2 = {10, 25, 32, 45, 55, 68};
        String result2 = bs.binarySearch(array2, 100);
        System.out.println("\nTest Case 2:");
        System.out.println("Array: " + arrayToString(array2));
        System.out.println("Searching for key 100: " + result2);

        // Test Case 3: First element is the key
        int[] array3 = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        String result3 = bs.binarySearch(array3, 2);
        System.out.println("\nTest Case 3:");
        System.out.println("Array: " + arrayToString(array3));
        System.out.println("Searching for key 2: " + result3);

        // Test Case 4: Last element is the key
        int[] array4 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        String result4 = bs.binarySearch(array4, 19);
        System.out.println("\nTest Case 4:");
        System.out.println("Array: " + arrayToString(array4));
        System.out.println("Searching for key 19: " + result4);

        // Test Case 5: Empty array
        int[] array5 = {};
        String result5 = bs.binarySearch(array5, 10);
        System.out.println("\nTest Case 5:");
        System.out.println("Array: " + arrayToString(array5));
        System.out.println("Searching for key 10: " + result5);

        // Test Case 6: Single-element array (key present)
        int[] array6 = {7};
        String result6 = bs.binarySearch(array6, 7);
        System.out.println("\nTest Case 6:");
        System.out.println("Array: " + arrayToString(array6));
        System.out.println("Searching for key 7: " + result6);

        // Test Case 7: Single-element array (key absent)
        int[] array7 = {7};
        String result7 = bs.binarySearch(array7, 10);
        System.out.println("\nTest Case 7:");
        System.out.println("Array: " + arrayToString(array7));
        System.out.println("Searching for key 10: " + result7);

        // Test Case 8: Array with duplicate elements
        int[] array8 = {1, 3, 5, 5, 7, 9, 9, 11};
        String result8 = bs.binarySearch(array8, 5);
        System.out.println("\nTest Case 8:");
        System.out.println("Array: " + arrayToString(array8));
        System.out.println("Searching for key 5: " + result8);

        String result9 = bs.binarySearch(array8, 9);
        System.out.println("Searching for key 9: " + result9);

        // Test Case 9: Array with negative elements
        int[] array9 = {-10, -5, 0, 5, 10, 15, 20};
        String result9a = bs.binarySearch(array9, -5);
        System.out.println("\nTest Case 9:");
        System.out.println("Array: " + arrayToString(array9));
        System.out.println("Searching for key -5: " + result9a);

        String result9b = bs.binarySearch(array9, 15);
        System.out.println("Searching for key 15: " + result9b);
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
