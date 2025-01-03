import java.util.HashMap;
import java.util.Map;

/**
 * The {@code MajorityElement} class provides functionality to find the majority element
 * in an array. A majority element is an element that appears more than half the size
 * of the array.
 *
 * <p>Two primary methods are implemented:
 * <ul>
 *     <li><strong>HashMap Counting:</strong> Counts occurrences using a hash map.</li>
 *     <li><strong>Boyer-Moore Voting Algorithm:</strong> Optimizes space by identifying a potential candidate.</li>
 * </ul>
 * </p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * MajorityElement solver = new MajorityElement();
 * int[] array1 = {1, 1, 1, 1, 2, 3, 5};
 * System.out.println(solver.findMajorityHashMap(array1)); // Output: 1
 * System.out.println(solver.findMajorityBoyerMoore(array1)); // Output: 1
 *
 * int[] array2 = {1, 2, 3};
 * System.out.println(solver.findMajorityHashMap(array2)); // Output: -1
 * System.out.println(solver.findMajorityBoyerMoore(array2)); // Output: -1
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong></p>
 * <ul>
 *     <li><strong>HashMap Counting:</strong> O(n)</li>
 *     <li><strong>Boyer-Moore Voting:</strong> O(n)</li>
 * </ul>
 *
 * <p><strong>Space Complexity:</strong></p>
 * <ul>
 *     <li><strong>HashMap Counting:</strong> O(n)</li>
 *     <li><strong>Boyer-Moore Voting:</strong> O(1)</li>
 * </ul>
 *Time and Space Complexity Analysis
    1. HashMap Counting (findMajorityHashMap)
    Time Complexity: O(n)
    Explanation: Traverses the array once, performing constant-time hash map operations (put and get) for each element.
    Space Complexity: O(n)
    Explanation: In the worst case, where all elements are unique, the hash map stores n key-value pairs.
    2. Boyer-Moore Voting Algorithm (findMajorityBoyerMoore)
    Time Complexity: O(n)
    Explanation: Traverses the array twice:
    First pass to identify the candidate.
    Second pass to verify the candidate.
    Space Complexity: O(1)
    Explanation: Uses only a few integer variables regardless of input size.
    Summary:

    Both methods operate in linear time relative to the size of the input array.
    The Boyer-Moore algorithm is more space-efficient, requiring constant extra space, making it preferable for large datasets.
 * 
 * @author 
 */
public class MajorityElement {
    
    /**
     * Finds the majority element in the array using HashMap counting.
     *
     * <p>This method traverses the array, counts the occurrences of each element using a hash map,
     * and identifies the element that appears more than n/2 times.</p>
     *
     * @param nums The input array of integers.
     * @return The majority element if it exists; otherwise, -1.
     */
    public int findMajorityHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int majorityThreshold = nums.length / 2;
        /* Traverse the array, counting the occurrences of each element using a hash map.
            If any element's count exceeds n/2, return that element immediately.
            If no such element is found after traversal, return -1. */
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if (countMap.get(num) > majorityThreshold) {
                return num;
            }
        }
        
        return -1;
    }
    
    /**
     * Finds the majority element in the array using the Boyer-Moore Voting Algorithm.
     *
     * <p>This method identifies a potential candidate by maintaining a count that
     * increases or decreases based on the current element. After identifying the candidate,
     * it verifies whether it truly is the majority element.</p>
     *
     * @param nums The input array of integers.
     * @return The majority element if it exists; otherwise, -1.
     */
    public int findMajorityBoyerMoore(int[] nums) {
        int candidate = -1;
        int count = 0;
        
        // Phase 1: Find a potential candidate
        /*Identify a potential candidate by maintaining a count. 
        Increment count when the current element matches the candidate; 
        decrement otherwise. 
        Reset the candidate when count reaches zero. */
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            }
            else if (num == candidate) {
                count++;
            }
            else {
                count--;
            }
        }
        
        // Phase 2: Verify the candidate
        /*Verify the candidate by counting its actual occurrences in the array. 
        If it exceeds n/2, return it; otherwise, return -1. */
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        
        if (count > nums.length / 2) {
            return candidate;
        }
        else {
            return -1;
        }
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code MajorityElement} class.
     *
     * <p>It demonstrates the usage of both methods to find the majority element using sample inputs.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        MajorityElement solver = new MajorityElement();
        
        // Example 1:
        // Input: {1, 1, 1, 1, 2, 3, 5}
        // Output: 1
        int[] array1 = {1, 1, 1, 1, 2, 3, 5};
        System.out.println("Example 1 Output (HashMap): " + solver.findMajorityHashMap(array1)); // Output: 1
        System.out.println("Example 1 Output (Boyer-Moore): " + solver.findMajorityBoyerMoore(array1)); // Output: 1
        
        // Example 2:
        // Input: {1, 2, 3}
        // Output: -1
        int[] array2 = {1, 2, 3};
        System.out.println("Example 2 Output (HashMap): " + solver.findMajorityHashMap(array2)); // Output: -1
        System.out.println("Example 2 Output (Boyer-Moore): " + solver.findMajorityBoyerMoore(array2)); // Output: -1
        
        // Additional Example 3:
        // Input: {2, 2, 1, 1, 1, 2, 2}
        // Output: 2
        int[] array3 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Example 3 Output (HashMap): " + solver.findMajorityHashMap(array3)); // Output: 2
        System.out.println("Example 3 Output (Boyer-Moore): " + solver.findMajorityBoyerMoore(array3)); // Output: 2
        
        // Edge Case Example 4:
        // Input: {}
        // Output: -1
        int[] array4 = {};
        System.out.println("Example 4 Output (HashMap): " + solver.findMajorityHashMap(array4)); // Output: -1
        System.out.println("Example 4 Output (Boyer-Moore): " + solver.findMajorityBoyerMoore(array4)); // Output: -1
    }
}

