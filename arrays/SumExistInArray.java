/**
 * The SumExistInArray class provides a method to determine whether a given array
 * contains two elements whose sum is equal to a specified target value. This problem
 * is fundamental in algorithm design and is commonly encountered in coding interviews.
 *
 * The class includes:
 * 1. A method `hasArrayTwoCandidates` that implements a two-pointer approach after sorting the array.
 * 2. A `main` method to demonstrate and test the functionality with sample inputs.
 */
import java.util.*;

public class SumExistInArray {

    /**
     * Checks if the array contains two elements whose sum equals the specified target value.
     *
     * @param A        The input array of integers.
     * @param arr_size The number of elements in the array.
     * @param sum      The target sum value to be found.
     * @return {@code true} if there exist two elements in the array that add up to the target sum; {@code false} otherwise.
     *
     * <p>
     * <b>Algorithm Explanation:</b>
     * <ol>
     *     <li>Sort the array in ascending order. This enables the use of the two-pointer technique.</li>
     *     <li>Initialize two pointers: one at the beginning (left) and one at the end (right) of the array.</li>
     *     <li>While the left pointer is less than the right pointer:
     *         <ul>
     *             <li>If the sum of the elements at the left and right pointers equals the target sum, return {@code true}.</li>
     *             <li>If the sum is less than the target, increment the left pointer to increase the sum.</li>
     *             <li>If the sum is greater than the target, decrement the right pointer to decrease the sum.</li>
     *         </ul>
     *     </li>
     *     <li>If no such pair is found after the loop, return {@code false}.</li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Time Complexity:</b> O(n log n) due to the sorting step, where n is the number of elements in the array.
     * <b>Space Complexity:</b> O(1) additional space, assuming the sorting is done in place.
     * </p>
     */
    static boolean hasArrayTwoCandidates(int A[], int arr_size, int sum) {
        int left, right;

        // Step 1: Sort the elements in ascending order
        Arrays.sort(A);

        // Step 2: Initialize two pointers
        left = 0;
        right = arr_size - 1;

        // Step 3: Traverse the array with two pointers
        while (left < right) {
            int currentSum = A[left] + A[right];

            if (currentSum == sum) {
                // Found the pair
                return true;
            } else if (currentSum < sum) {
                // Need a larger sum, move the left pointer to the right
                left++;
            } else {
                // Need a smaller sum, move the right pointer to the left
                right--;
            }
        }

        // No pair found
        return false;
    }

    /**
     * The main method serves as an entry point to test the hasArrayTwoCandidates method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String args[]) {
        // Sample input array
        int A[] = {1, 4, 45, 6, 10, -8};
        int arr_size = A.length;
        int targetSum = 15;

        // Function calling to check if any two elements sum up to targetSum
        if (hasArrayTwoCandidates(A, arr_size, targetSum)) {
            System.out.println("Array has two elements with the given sum " + targetSum + ".");
        } else {
            System.out.println("Array doesn't have two elements with the given sum " + targetSum + ".");
        }

        // Additional test cases
        int B[] = {2, 7, 11, 15};
        int targetSum2 = 9;
        if (hasArrayTwoCandidates(B, B.length, targetSum2)) {
            System.out.println("Array has two elements with the given sum " + targetSum2 + ".");
        } else {
            System.out.println("Array doesn't have two elements with the given sum " + targetSum2 + ".");
        }

        int C[] = {3, 2, 4};
        int targetSum3 = 6;
        if (hasArrayTwoCandidates(C, C.length, targetSum3)) {
            System.out.println("Array has two elements with the given sum " + targetSum3 + ".");
        } else {
            System.out.println("Array doesn't have two elements with the given sum " + targetSum3 + ".");
        }

        int D[] = {3, 3};
        int targetSum4 = 6;
        if (hasArrayTwoCandidates(D, D.length, targetSum4)) {
            System.out.println("Array has two elements with the given sum " + targetSum4 + ".");
        } else {
            System.out.println("Array doesn't have two elements with the given sum " + targetSum4 + ".");
        }
    }
}
