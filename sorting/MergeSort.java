/**
 * The MergeSort class provides methods to perform the merge sort algorithm on a list of integers.
 * Merge sort is a divide-and-conquer algorithm that divides the input list into smaller sublists,
 * sorts them, and then merges the sorted sublists to produce the final sorted list.
 *
 * <p>
 * <b>Algorithm Steps:</b>
 * <ol>
 *     <li>Divide the unsorted list into n sublists, each containing one element (a list of one element is considered sorted).</li>
 *     <li>Repeatedly merge sublists to produce new sorted sublists until there is only one sublist remaining. This will be the sorted list.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n log n), where n is the number of elements in the list.
 * <b>Space Complexity:</b> O(n), due to the additional space required for temporary lists during the merge process.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b>
 * Merge sort is widely used in scenarios where stable sorting and guaranteed O(n log n) performance are required,
 * such as in external sorting (sorting large data sets that do not fit into memory).
 * </p>
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    /**
     * Recursively sorts the input list using the merge sort algorithm.
     *
     * @param array The list of integers to be sorted.
     * @return A new list containing the sorted elements.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Check if the list has only one element. If so, it is already sorted, and the method returns it.</li>
     *     <li>Divide the list into two halves: left and right.</li>
     *     <li>Recursively call mergeSort on the left and right halves to sort them.</li>
     *     <li>Merge the two sorted halves using the merge method.</li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b>
     * <pre>
     * {@code
     * List<Integer> sorted = mergeSort(Arrays.asList(99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0));
     * // sorted = [0, 1, 2, 4, 5, 6, 44, 63, 87, 99, 283]
     * }
     * </pre>
     * </p>
     */
    public List<Integer> mergeSort(List<Integer> array) {
        // Base case: a list of zero or one elements is already sorted
        if (array.size() <= 1) {
            return array;
        }

        // Divide the list into two halves
        List<Integer> left = new ArrayList<>(array.subList(0, array.size() / 2));
        List<Integer> right = new ArrayList<>(array.subList(array.size() / 2, array.size()));

        // Recursively sort both halves
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted halves and return
        return merge(left, right);
    }

    /**
     * Merges two sorted lists into a single sorted list.
     *
     * @param left  The first sorted list.
     * @param right The second sorted list.
     * @return A new list containing all elements from left and right, sorted.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize an empty list to store the merged result.</li>
     *     <li>Use two pointers to traverse the left and right lists.</li>
     *     <li>Compare the current elements of both lists:
     *         <ul>
     *             <li>If the element in the left list is smaller, add it to the result and move the left pointer.</li>
     *             <li>If the element in the right list is smaller or equal, add it to the result and move the right pointer.</li>
     *         </ul>
     *     </li>
     *     <li>After one list is exhausted, append the remaining elements from the other list.</li>
     *     <li>Return the merged sorted list.</li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b>
     * <pre>
     * {@code
     * List<Integer> left = Arrays.asList(1, 3, 5);
     * List<Integer> right = Arrays.asList(2, 4, 6);
     * List<Integer> merged = merge(left, right);
     * // merged = [1, 2, 3, 4, 5, 6]
     * }
     * </pre>
     * </p>
     */
    public List<Integer> merge(List<Integer> left, List<Integer> right) {
        ArrayList<Integer> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        // Traverse both lists and append the smaller element to the result
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) < right.get(rightIndex)) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        // Append any remaining elements from the left list
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }

        // Append any remaining elements from the right list
        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }

        return result;
    }

    /**
     * The main method serves as the entry point to test the mergeSort method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        MergeSort sort = new MergeSort();

        // Define sample input arrays
        List<Integer> numbers1 = Arrays.asList(99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0);
        List<Integer> numbers2 = Arrays.asList(38, 27, 43, 3, 9, 82, 10);
        List<Integer> numbers3 = Arrays.asList(12, 11, 13, 5, 6, 7);
        List<Integer> numbers4 = Arrays.asList(1); // Single element
        List<Integer> numbers5 = new ArrayList<>(); // Empty list

        // Perform merge sort on the sample inputs
        System.out.println("Original List 1: " + numbers1);
        System.out.println("Sorted List 1: " + sort.mergeSort(numbers1));
        System.out.println();

        System.out.println("Original List 2: " + numbers2);
        System.out.println("Sorted List 2: " + sort.mergeSort(numbers2));
        System.out.println();

        System.out.println("Original List 3: " + numbers3);
        System.out.println("Sorted List 3: " + sort.mergeSort(numbers3));
        System.out.println();

        System.out.println("Original List 4: " + numbers4);
        System.out.println("Sorted List 4: " + sort.mergeSort(numbers4));
        System.out.println();

        System.out.println("Original List 5: " + numbers5);
        System.out.println("Sorted List 5: " + sort.mergeSort(numbers5));
    }
}
