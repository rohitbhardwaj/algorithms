/**
 * This class provides multiple methods to determine if two arrays contain any common items.
 * Methods include:
 * - Brute force (O(N*M))
 * - Using a HashSet (O(N+M) time complexity)
 * - Using sorting and binary search (O(N log N + M log N))
 */
import java.util.HashSet;
import java.util.Arrays;

public class CommonItemFinder {
    
    /**
     * Brute Force Approach (O(N*M)): Compare every element of array1 with every element of array2.
     * @param array1 First array.
     * @param array2 Second array.
     * @return true if there is a common item, false otherwise.
     */
    public static boolean hasCommonItemBruteForce(char[] array1, char[] array2) {
        for (char c1 : array1) {
            for (char c2 : array2) {
                if (c1 == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * HashSet Approach (O(N+M)): Store all elements of array1 in a HashSet and check for presence in array2.
     * @param array1 First array.
     * @param array2 Second array.
     * @return true if there is a common item, false otherwise.
     */
    public static boolean hasCommonItemUsingSet(char[] array1, char[] array2) {
        HashSet<Character> set = new HashSet<>();
        for (char c : array1) {
            set.add(c);
        }
        for (char c : array2) {
            if (set.contains(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sorting & Binary Search Approach (O(N log N + M log N)): Sort the first array, then binary search for each element in array2.
     * @param array1 First array.
     * @param array2 Second array.
     * @return true if there is a common item, false otherwise.
     */
    public static boolean hasCommonItemBinarySearch(char[] array1, char[] array2) {
        Arrays.sort(array1);
        for (char c : array2) {
            if (Arrays.binarySearch(array1, c) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Main method to test the different approaches.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        char[] array1 = {'a', 'b', 'c', 'd', 'e'};
        char[] array2 = {'f', 'g', 'c'};
        char[] array3 = {'x', 'y', 'w', 'z'};
        char[] array4 = {'m', 'n', 'k'};

        System.out.println("Brute Force Method:");
        System.out.println(hasCommonItemBruteForce(array1, array2)); // Expected: true
        System.out.println(hasCommonItemBruteForce(array3, array4)); // Expected: false

        System.out.println("\nUsing HashSet:");
        System.out.println(hasCommonItemUsingSet(array1, array2)); // Expected: true
        System.out.println(hasCommonItemUsingSet(array3, array4)); // Expected: false

        System.out.println("\nUsing Sorting and Binary Search:");
        System.out.println(hasCommonItemBinarySearch(array1, array2)); // Expected: true
        System.out.println(hasCommonItemBinarySearch(array3, array4)); // Expected: false
    }
}
