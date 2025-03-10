/**
 * This class provides methods to search for a target element in a rotated sorted array.
 * Methods include:
 * - Brute Force (O(N))
 * - Binary Search (O(log N))
 */
public class RotatedSortedArraySearch {
    
    /**
     * Brute Force Approach: Linearly search for the target in the array.
     * Time Complexity: O(N), Space Complexity: O(1).
     * @param nums The rotated sorted array.
     * @param target The target element to find.
     * @return The index of the target element, or -1 if not found.
     */
    public static int searchBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary Search Approach: Uses modified binary search to efficiently find the target.
     * Time Complexity: O(log N), Space Complexity: O(1).
     * @param nums The rotated sorted array.
     * @param target The target element to find.
     * @return The index of the target element, or -1 if not found.
     */
    public static int searchBinarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            
            // Determine which side is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * Main method to test different approaches.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        int[] nums3 = {1};
        int target3 = 0;

        System.out.println("Brute Force Method:");
        System.out.println("Index of " + target1 + ": " + searchBruteForce(nums1, target1)); // Expected: 4
        System.out.println("Index of " + target2 + ": " + searchBruteForce(nums2, target2)); // Expected: -1
        System.out.println("Index of " + target3 + ": " + searchBruteForce(nums3, target3)); // Expected: -1

        System.out.println("\nBinary Search Method:");
        System.out.println("Index of " + target1 + ": " + searchBinarySearch(nums1, target1)); // Expected: 4
        System.out.println("Index of " + target2 + ": " + searchBinarySearch(nums2, target2)); // Expected: -1
        System.out.println("Index of " + target3 + ": " + searchBinarySearch(nums3, target3)); // Expected: -1
    }
}
