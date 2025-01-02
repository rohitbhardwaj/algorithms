/**
 * The SumExistInArrayHashSet class provides methods to determine whether a given array
 * contains two elements whose sum equals a specified target value using a HashSet for
 * efficient lookup. This approach offers a time-efficient solution with a linear time
 * complexity, making it a popular choice for solving the Two Sum problem in coding interviews.
 *
 * The class includes:
 * 1. A method `printPairs` that identifies and prints all pairs in the array that add up to the target sum.
 * 2. A `main` method to demonstrate and test the functionality with sample inputs.
 */
import java.util.HashSet;

public class SumExistInArrayHashSet {

    /**
     * Prints all pairs of integers in the array that add up to the specified target sum.
     *
     * @param arr The input array of integers.
     * @param sum The target sum value to be found.
     *
     * <p>
     * <b>Algorithm Explanation:</b>
     * <ol>
     *     <li>Initialize an empty HashSet to store the elements as they are iterated.</li>
     *     <li>Iterate through each element in the array:</li>
     *         <ul>
     *             <li>Calculate the complement of the current element by subtracting it from the target sum.</li>
     *             <li>Check if the complement exists in the HashSet:</li>
     *                 <ul>
     *                     <li>If it does, print the pair (current element, complement).</li>
     *                     <li>If not, add the current element to the HashSet for future reference.</li>
     *                 </ul>
     *         </ul>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Time Complexity:</b> O(n), where n is the number of elements in the array.
     * This is because each element is processed exactly once.
     * <b>Space Complexity:</b> O(n), due to the additional space used by the HashSet.
     * </p>
     */
    static void printPairs(int arr[], int sum) {
        // Initialize an empty HashSet to store elements as we iterate
        HashSet<Integer> s = new HashSet<Integer>();

        // Iterate through each element in the array
        for (int i = 0; i < arr.length; ++i) {
            // Calculate the complement of the current element
            int temp = sum - arr[i];

            // Check if the complement exists in the HashSet
            if (s.contains(temp)) {
                System.out.println("Pair with given sum " + sum + " is (" + arr[i] + ", " + temp + ")");
            }

            // Add the current element to the HashSet for future reference
            s.add(arr[i]);
        }
    }

    /**
     * The main method serves as an entry point to test the printPairs method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample input array
        int A[] = {1, 4, 45, 6, 10, 8};
        int targetSum = 16;

        // Function calling to print all pairs that add up to targetSum
        printPairs(A, targetSum);

        // Additional test cases
        System.out.println("\nAdditional Test Cases:");

        int B[] = {2, 7, 11, 15};
        int targetSum2 = 9;
        printPairs(B, targetSum2);

        int C[] = {3, 2, 4};
        int targetSum3 = 6;
        printPairs(C, targetSum3);

        int D[] = {3, 3};
        int targetSum4 = 6;
        printPairs(D, targetSum4);

        int E[] = {1, 1, 1, 1};
        int targetSum5 = 2;
        printPairs(E, targetSum5);

        int F[] = {5, -1, 7, 2, -3, 4};
        int targetSum6 = 4;
        printPairs(F, targetSum6);
    }
}
