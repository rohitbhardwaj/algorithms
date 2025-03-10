/**
 * Given a number X and an array arr[] of length N, this program finds the minimum number of operations required to make X non-positive.
 * In each operation:
 * - Select any number Y from the array and reduce X by Y.
 * - Then make Y = Y/2 (taking the floor value if Y is odd).
 * - If it is not possible to make X non-positive, return -1.
 *
 * Example:
 * Input: N = 3, arr[] = {3, 4, 12}, X = 25
 * Output: 4
 */
import java.util.*;

public class MinimizeSubtractionArray { 
    /**
     * Function to find the minimum number of operations required to make X non-positive.
     * @param N The number of elements in the array.
     * @param X The target number to be reduced.
     * @param nums The array of numbers.
     * @return The minimum number of operations required, or -1 if not possible.
     */
    public static int minimumOperations(int N, int X, int nums[]) {
        // Initialize answer as zero
        int ans = 0;

        // Create a Max-Heap using PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Insert all numbers into the priority queue
        for (int num : nums) {
            pq.add(num);
        }

        // Execute operations while numbers are available and X is still positive
        while (!pq.isEmpty() && X > 0) {
            if (pq.peek() == 0) break;

            // Increment the operation count
            ans++;

            // Get and remove the largest number
            int num = pq.poll();

            // Reduce X by num and halve num
            X -= num;
            num /= 2;

            // Reinsert num into the heap if it's still positive
            if (num > 0) pq.add(num);
        }

        // If X is still positive, return -1 (not possible)
        return (X > 0) ? -1 : ans;
    }

    /**
     * Main method to test different inputs.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[][] testCases = {
            {3, 25, 3, 4, 12},  // Expected output: 4
            {4, 10, 8, 2, 3, 1}, // Expected output: 2
            {3, 30, 5, 10, 15},  // Expected output: 3
            {3, 100, 1, 1, 1}    // Expected output: -1 (not possible)
        };

        for (int[] testCase : testCases) {
            int N = testCase[0];
            int X = testCase[1];
            int[] nums = Arrays.copyOfRange(testCase, 2, testCase.length);
            
            System.out.println("Minimum operations for X=" + X + " with nums=" + Arrays.toString(nums) + " -> " + minimumOperations(N, X, nums));
        }
    }
}
