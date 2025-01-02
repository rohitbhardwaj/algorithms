/**
 * The SearchInRotatedSortedArray class provides a method to determine the index of a target
 * element in a rotated sorted array of distinct integers. A rotated sorted array is an array
 * that has been shifted to the right or left by a certain pivot unknown to the user.
 * 
 * <p>
 * <b>Problem Statement:</b><br>
 * There is an integer array `nums` sorted in ascending order (with distinct values).
 * Prior to being passed to your function, `nums` is possibly rotated at an unknown pivot index `k`
 * (1 <= k < nums.length) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (0-indexed).
 * 
 * <b>Examples:</b>
 * <ul>
 *     <li><b>Example 1:</b>
 *         <ul>
 *             <li>Input: nums = [4,5,6,7,0,1,2], target = 0</li>
 *             <li>Output: 4</li>
 *             <li>Explanation: The target 0 is located at index 4.</li>
 *         </ul>
 *     </li>
 *     <li><b>Example 2:</b>
 *         <ul>
 *             <li>Input: nums = [4,5,6,7,0,1,2], target = 3</li>
 *             <li>Output: -1</li>
 *             <li>Explanation: The target 3 is not present in the array.</li>
 *         </ul>
 *     </li>
 *     <li><b>Example 3:</b>
 *         <ul>
 *             <li>Input: nums = [1], target = 0</li>
 *             <li>Output: -1</li>
 *             <li>Explanation: The target 0 is not present in the array.</li>
 *         </ul>
 *     </li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Approach:</b><br>
 * Utilize the Binary Search algorithm with modifications to account for the rotation pivot.
 * The key idea is to determine which side of the array (left or right of the midpoint) is properly sorted,
 * and then decide whether to search in that sorted side or the unsorted side based on the target's value.
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
 * - Efficiently searching in rotated sorted arrays, which are common in real-world applications like
 *   circular queues, stock price analysis, and calendar systems.<br>
 * - Fundamental understanding of binary search variations and algorithmic problem-solving.
 * </p>
 */
public class SearchInRotatedSortedArray {
    
    /**
     * Searches for a target value in a rotated sorted array and returns its index.
     * If the target is not found, returns -1.
     *
     * @param nums   The rotated sorted array of distinct integers.
     * @param target The integer value to search for.
     * @return The index of the target if found; otherwise, -1.
     */
    public static int search(int[] nums, int target) {
        int left = 0;                 // Initialize the left pointer.
        int right = nums.length - 1;  // Initialize the right pointer.
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevents potential overflow.
            
            if (nums[mid] == target) {
                return mid; // Target found at index mid.
            }
            
            // Determine which side is properly sorted.
            if (nums[left] <= nums[mid]) { // Left side is sorted.
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Target is in the left sorted side.
                } else {
                    left = mid + 1; // Target is in the right unsorted side.
                }
            } else { // Right side is sorted.
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Target is in the right sorted side.
                } else {
                    right = mid - 1; // Target is in the left unsorted side.
                }
            }
        }
        
        return -1; // Target not found in the array.
    }
    
    /**
     * The main method serves as an entry point to test the search method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample input arrays and targets.
        int[][] testArrays = {
            {4,5,6,7,0,1,2},
            {4,5,6,7,0,1,2},
            {1},
            {1, 3}
        };
        
        int[] targets = {0, 3, 0, 3};
        
        // Expected outputs for reference.
        int[] expectedOutputs = {4, -1, -1, 1};
        
        // Iterate through each test case and display the results.
        for (int i = 0; i < testArrays.length; i++) {
            int[] nums = testArrays[i];
            int target = targets[i];
            int expected = expectedOutputs[i];
            int result = search(nums, target);
            
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input Array: " + arrayToString(nums));
            System.out.println("Target: " + target);
            System.out.println("Expected Output: " + expected);
            System.out.println("Actual Output: " + result);
            System.out.println(result == expected ? "✅ Passed" : "❌ Failed");
            System.out.println("----------------------------------------------------");
        }
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
        // Remove the trailing comma and space.
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
}
