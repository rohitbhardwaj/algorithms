/**
 * This class provides methods to determine if a given number is a perfect square.
 * Methods include:
 * - Brute Force (O(sqrt(N)))
 * - Binary Search (O(log N))
 * - Newton's Method (O(log N))
 */
public class PerfectSquareChecker { 
    
    /**
     * Brute Force Approach: Iterate from 1 to sqrt(n) and check if any number squared equals n.
     * Time Complexity: O(sqrt(N)), Space Complexity: O(1).
     * @param n The input number.
     * @return true if n is a perfect square, false otherwise.
     */
    public static boolean isPerfectSquareBruteForce(int n) {
        if (n < 0) return false;
        for (int i = 0; i * i <= n; i++) {
            if (i * i == n) return true;
        }
        return false;
    }

    /**
     * Binary Search Approach: Perform binary search between 1 and n to find if a square exists.
     * Time Complexity: O(log N), Space Complexity: O(1).
     * @param n The input number.
     * @return true if n is a perfect square, false otherwise.
     */
    public static boolean isPerfectSquareBinarySearch(int n) {
        if (n < 0) return false;
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            if (square == n) return true;
            else if (square < n) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    /**
     * Newton's Method: Uses Newton's iterative approximation method to check for perfect squares.
     * Time Complexity: O(log N), Space Complexity: O(1).
     * @param n The input number.
     * @return true if n is a perfect square, false otherwise.
     */
    public static boolean isPerfectSquareNewtonsMethod(int n) {
        if (n < 0) return false;
        long x = n;
        while (x * x > n) {
            x = (x + n / x) / 2;
        }
        return x * x == n;
    }

    /**
     * Main method to test different approaches.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] testCases = {16, 25, 49, 50, 100, 101};
        
        System.out.println("Brute Force Method:");
        for (int n : testCases) {
            System.out.println("Is " + n + " a perfect square? " + isPerfectSquareBruteForce(n));
        }
        
        System.out.println("\nBinary Search Method:");
        for (int n : testCases) {
            System.out.println("Is " + n + " a perfect square? " + isPerfectSquareBinarySearch(n));
        }
        
        System.out.println("\nNewton's Method:");
        for (int n : testCases) {
            System.out.println("Is " + n + " a perfect square? " + isPerfectSquareNewtonsMethod(n));
        }
    }
}
