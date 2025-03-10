/**
 * This class provides multiple methods to find the intersection of two arrays.
 * Methods include:
 * - Brute force (O(N*M))
 * - Using a HashSet (O(N+M) time complexity)
 * - Using sorting and two-pointer technique (O(N log N + M log M))
 */
import java.util.HashSet;
import java.util.Arrays;

public class ArrayIntersectionFinder {
    
    /**
     * Brute Force Approach (O(N*M)): Compare every element of nums1 with every element of nums2.
     * @param nums1 First array.
     * @param nums2 Second array.
     * @return Array containing the unique intersection elements.
     */
    public static int[] findIntersectionBruteForce(int[] nums1, int[] nums2) {
        HashSet<Integer> resultSet = new HashSet<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 == num2) {
                    resultSet.add(num1);
                }
            }
        }
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * HashSet Approach (O(N+M)): Store all elements of nums1 in a HashSet and check for presence in nums2.
     * @param nums1 First array.
     * @param nums2 Second array.
     * @return Array containing the unique intersection elements.
     */
    public static int[] findIntersectionUsingSet(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> resultSet = new HashSet<>();
        
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Sorting & Two-Pointer Approach (O(N log N + M log M)): Sort both arrays and use two pointers to find the intersection.
     * @param nums1 First array.
     * @param nums2 Second array.
     * @return Array containing the unique intersection elements.
     */
    public static int[] findIntersectionTwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        HashSet<Integer> resultSet = new HashSet<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resultSet.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Main method to test different approaches.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};

        System.out.println("Brute Force Method:");
        System.out.println(Arrays.toString(findIntersectionBruteForce(nums1, nums2))); // Expected: [2]
        System.out.println(Arrays.toString(findIntersectionBruteForce(nums3, nums4))); // Expected: [9, 4]

        System.out.println("\nUsing HashSet:");
        System.out.println(Arrays.toString(findIntersectionUsingSet(nums1, nums2))); // Expected: [2]
        System.out.println(Arrays.toString(findIntersectionUsingSet(nums3, nums4))); // Expected: [9, 4]

        System.out.println("\nUsing Sorting and Two Pointers:");
        System.out.println(Arrays.toString(findIntersectionTwoPointers(nums1, nums2))); // Expected: [2]
        System.out.println(Arrays.toString(findIntersectionTwoPointers(nums3, nums4))); // Expected: [9, 4]
    }
}
