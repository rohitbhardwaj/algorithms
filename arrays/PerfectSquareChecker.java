/**
 * The PerfectSquareChecker class provides a method to determine whether a given integer
 * is a perfect square. A perfect square is an integer that is the square of another integer
 * (e.g., 16 is a perfect square because it is 4 squared).
 *
 * <p>
 * <b>Problem Statement:</b><br>
 * Given a non-negative integer `n`, write a function that returns `true` if `n` is a perfect square
 * else `false`. You are **not** allowed to use any built-in library function such as `sqrt`.
 * The solution should have a time complexity of O(log n).
 * </p>
 *
 * <p>
 * <b>Example:</b>
 * <ul>
 *     <li>Input: `n = 16`<br>Output: `true`<br>Explanation: 4 × 4 = 16</li>
 *     <li>Input: `n = 14`<br>Output: `false`<br>Explanation: There is no integer that multiplies by itself to give 14</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Approach:</b><br>
 * Utilize the Binary Search algorithm to efficiently determine if `n` is a perfect square.
 * The idea is to search for an integer `x` such that `x * x = n`. Start with a search range from
 * 1 to `n`. At each step, calculate the midpoint `mid` of the current range and compute `mid * mid`.
 * If `mid * mid` equals `n`, return `true`. If it's less than `n`, adjust the search range to the
 * right half; otherwise, adjust it to the left half. Continue this process until the range is exhausted.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(log n), where `n` is the input integer.<br>
 * <b>Space Complexity:</b> O(1), as it uses a constant amount of additional space.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b><br>
 * - Validating user inputs in applications that require numerical precision.<br>
 * - Optimizing mathematical computations where square roots are involved.<br>
 * - Fundamental understanding of binary search applications beyond sorted arrays.
 * </p>
 */
public class PerfectSquareChecker {

    /**
     * Determines whether a given integer is a perfect square using the Binary Search algorithm.
     *
     * @param n The non-negative integer to check.
     * @return {@code true} if {@code n} is a perfect square; {@code false} otherwise.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Handle edge cases:
     *         <ul>
     *             <li>If {@code n} is 0 or 1, it is a perfect square.</li>
     *         </ul>
     *     </li>
     *     <li>Initialize two pointers:
     *         <ul>
     *             <li>{@code left} at 1.</li>
     *             <li>{@code right} at {@code n}.</li>
     *         </ul>
     *     </li>
     *     <li>Perform Binary Search:
     *         <ul>
     *             <li>While {@code left} is less than or equal to {@code right}:</li>
     *                 <ol>
     *                     <li>Calculate {@code mid} as the average of {@code left} and {@code right}.</li>
     *                     <li>Compute {@code square} as {@code mid * mid}.</li>
     *                     <li>If {@code square} equals {@code n}, return {@code true}.</li>
     *                     <li>If {@code square} is less than {@code n}, move the {@code left} pointer to {@code mid + 1}.</li>
     *                     <li>If {@code square} is greater than {@code n}, move the {@code right} pointer to {@code mid - 1}.</li>
     *                 </ol>
     *         </ul>
     *     </li>
     *     <li>If no such {@code mid} is found, return {@code false}.</li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b><br>
     * <pre>
     * {@code
     * int number = 16;
     * boolean result = isPerfectSquare(number);
     * // Output: true
     * }
     * </pre>
     * </p>
     */
    public static boolean isPerfectSquare(long n) {
        if (n < 1) {
            return false; // Negative numbers and zero are not perfect squares
        }
        if (n == 1) {
            return true; // 1 is a perfect square
        }

        long left = 1;
        long right = n;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;

            if (square == n) {
                return true; // Found the perfect square
            } else if (square < n) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return false; // No perfect square found
    }

    /**
     * The main method serves as an entry point to test the isPerfectSquare method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample inputs to test the isPerfectSquare method
        long[] testNumbers = {16, 25, 14, 100, 0, 1, 2147483647, 2147395600L};

        System.out.println("Perfect Square Checker Using Binary Search:");
        for (long number : testNumbers) {
            boolean result = isPerfectSquare(number);
            System.out.println("Is " + number + " a perfect square? " + result);
        }
    }
}
