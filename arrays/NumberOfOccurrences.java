/**
 * The NumberOfOccurrences class provides methods to determine the number of times
 * a target element appears in a sorted array of integers. This implementation utilizes
 * the Binary Search algorithm to achieve an efficient O(log n) time complexity.
 * 
 * <p>
 * <b>Problem Statement:</b><br>
 * Given a sorted array of n elements, possibly with duplicates, find the number of occurrences
 * of a target element.
 * 
 * <b>Examples:</b>
 * <ul>
 *     <li><b>Example 1:</b>
 *         <ul>
 *             <li>Input: arr = [4, 4, 8, 8, 8, 15, 16, 23, 23, 42], target = 8</li>
 *             <li>Output: 3</li>
 *         </ul>
 *     </li>
 *     <li><b>Example 2:</b>
 *         <ul>
 *             <li>Input: arr = [3, 5, 5, 5, 5, 7, 8, 8], target = 6</li>
 *             <li>Output: 0</li>
 *         </ul>
 *     </li>
 *     <li><b>Example 3:</b>
 *         <ul>
 *             <li>Input: arr = [3, 5, 5, 5, 5, 7, 8, 8], target = 5</li>
 *             <li>Output: 4</li>
 *         </ul>
 *     </li>
 * </ul>
 * </p>
 * 
 * <p>
 * <b>Approach:</b><br>
 * Utilize the Binary Search algorithm to efficiently locate the first and last occurrences
 * of the target element in the sorted array. The number of occurrences is then determined
 * by the difference between the indices of the last and first occurrences, plus one.
 * This approach ensures an O(log n) time complexity.
 * </p>
 * 
 * <p>
 * <b>Time Complexity:</b> O(log n), where n is the number of elements in the array.<br>
 * <b>Space Complexity:</b> O(1), as it uses a constant amount of additional space.
 * </p>
 * 
 * <p>
 * <b>Use Cases:</b><br>
 * - Efficiently counting duplicates in large datasets.<br>
 * - Optimizing search operations in databases and information retrieval systems.<br>
 * - Fundamental understanding of Binary Search applications beyond simple searches.
 * </p>
 */
public class NumberOfOccurrences {
    
    /**
     * Finds the number of times a target element appears in a sorted array using Binary Search.
     *
     * @param arr    A sorted array of integers (may contain duplicates).
     * @param target The integer value to count within the array.
     * @return The number of occurrences of the target in the array.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Perform a binary search to find the first occurrence of the target.</li>
     *     <li>Perform a binary search to find the last occurrence of the target.</li>
     *     <li>If the target is not found, return 0.</li>
     *     <li>Otherwise, calculate the number of occurrences as (lastIndex - firstIndex + 1).</li>
     * </ol>
     * </p>
     */
    public static int countOccurrences(int[] arr, int target) {
        int first = findFirstOccurrence(arr, target);
        if (first == -1) {
            // Target not found in the array
            return 0;
        }
        int last = findLastOccurrence(arr, target);
        return last - first + 1;
    }

    /**
     * Finds the first occurrence index of the target in the sorted array using Binary Search.
     *
     * @param arr    A sorted array of integers.
     * @param target The integer value to find.
     * @return The index of the first occurrence of the target, or -1 if not found.
     */
    private static int findFirstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int firstOccurrence = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                firstOccurrence = mid;
                // Continue searching in the left half to find the first occurrence
                right = mid - 1;
            } else if (arr[mid] < target) {
                // Search in the right half
                left = mid + 1;
            } else {
                // Search in the left half
                right = mid - 1;
            }
        }
        
        return firstOccurrence;
    }

    /**
     * Finds the last occurrence index of the target in the sorted array using Binary Search.
     *
     * @param arr    A sorted array of integers.
     * @param target The integer value to find.
     * @return The index of the last occurrence of the target, or -1 if not found.
     */
    private static int findLastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int lastOccurrence = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                lastOccurrence = mid;
                // Continue searching in the right half to find the last occurrence
                left = mid + 1;
            } else if (arr[mid] < target) {
                // Search in the right half
                left = mid + 1;
            } else {
                // Search in the left half
                right = mid - 1;
            }
        }
        
        return lastOccurrence;
    }

    /**
     * The main method serves as an entry point to test the countOccurrences method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define sample input arrays and targets
        int[] arr1 = {4, 4, 8, 8, 8, 15, 16, 23, 23, 42};
        int target1 = 8;

        int[] arr2 = {3, 5, 5, 5, 5, 7, 8, 8};
        int target2 = 6;

        int[] arr3 = {3, 5, 5, 5, 5, 7, 8, 8};
        int target3 = 5;

        // Compute and print the number of occurrences for each target
        System.out.println("Example 1:");
        System.out.println("Input Array: " + arrayToString(arr1));
        System.out.println("Target: " + target1);
        System.out.println("Number of Occurrences: " + countOccurrences(arr1, target1));
        System.out.println();

        System.out.println("Example 2:");
        System.out.println("Input Array: " + arrayToString(arr2));
        System.out.println("Target: " + target2);
        System.out.println("Number of Occurrences: " + countOccurrences(arr2, target2));
        System.out.println();

        System.out.println("Example 3:");
        System.out.println("Input Array: " + arrayToString(arr3));
        System.out.println("Target: " + target3);
        System.out.println("Number of Occurrences: " + countOccurrences(arr3, target3));
        System.out.println();

        // Additional test cases
        int[] arr4 = {};
        int target4 = 1;

        int[] arr5 = {1};
        int target5 = 1;

        int[] arr6 = {1, 2, 3, 4, 5};
        int target6 = 3;

        System.out.println("Additional Test Cases:");
        System.out.println("Input Array: " + arrayToString(arr4));
        System.out.println("Target: " + target4);
        System.out.println("Number of Occurrences: " + countOccurrences(arr4, target4));
        System.out.println();

        System.out.println("Input Array: " + arrayToString(arr5));
        System.out.println("Target: " + target5);
        System.out.println("Number of Occurrences: " + countOccurrences(arr5, target5));
        System.out.println();

        System.out.println("Input Array: " + arrayToString(arr6));
        System.out.println("Target: " + target6);
        System.out.println("Number of Occurrences: " + countOccurrences(arr6, target6));
        System.out.println();
    }

    /**
     * Helper method to convert an integer array to a string representation.
     *
     * @param arr The input array of integers.
     * @return A string representation of the array.
     */
    private static String arrayToString(int[] arr) {
        if (arr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int num : arr) {
            sb.append(num).append(", ");
        }
        // Remove the trailing comma and space
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
}
