/**
 * This class provides a method to find the extra letter added to string t after shuffling string s.
 * Methods include:
 * - Frequency Counting (O(N) time complexity, O(1) space complexity)
 * - XOR Approach (O(N) time complexity, O(1) space complexity)
 */
import java.util.*;

public class FindTheDifference {
    
    /**
     * Finds the extra letter added to t using frequency counting.
     * Time Complexity: O(N), Space Complexity: O(1) (since the alphabet size is fixed at 26).
     * @param s The original string.
     * @param t The modified string with one extra character.
     * @return The character that was added.
     */
    public static char findTheDifferenceFrequency(String s, String t) {
        int[] charCounts = new int[26];
        
        // Count occurrences of each character in s
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }
        
        // Reduce count for each character in t
        for (char c : t.toCharArray()) {
            charCounts[c - 'a']--;
            if (charCounts[c - 'a'] < 0) {
                return c;
            }
        }
        
        return ' '; // Should never reach here
    }
    
    /**
     * Finds the extra letter added to t using XOR.
     * The XOR approach works by leveraging the properties of the XOR bitwise operation:
     * - XORing a number with itself results in 0 (a ^ a = 0).
     * - XORing a number with 0 results in the number itself (a ^ 0 = a).
     * - XOR operation is commutative and associative, meaning order does not matter.
     * 
     * Explanation:
     * - We XOR all characters in s and t together.
     * - Since all characters in s appear exactly in t except for one extra letter,
     *   all characters that appear twice will cancel out to 0.
     * - The remaining result will be the extra character.
     *
     * Time Complexity: O(N), Space Complexity: O(1).
     * @param s The original string.
     * @param t The modified string with one extra character.
     * @return The character that was added.
     */
    public static char findTheDifferenceXOR(String s, String t) {
        char result = 0;
        
        // XOR all characters in both s and t
        for (char c : s.toCharArray()) {
            result ^= c;
        }
        for (char c : t.toCharArray()) {
            result ^= c;
        }
        
        return result;
    }

    /**
     * Main method to test finding the extra letter.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String s1 = "abcd", t1 = "abcde";
        String s2 = "", t2 = "y";
        String s3 = "aeiou", t3 = "aeoiux";
        
        System.out.println("Extra letter found using Frequency Count:");
        System.out.println("Extra letter in 'abcde': " + findTheDifferenceFrequency(s1, t1)); // Expected: 'e'
        System.out.println("Extra letter in 'y': " + findTheDifferenceFrequency(s2, t2)); // Expected: 'y'
        System.out.println("Extra letter in 'aeoiux': " + findTheDifferenceFrequency(s3, t3)); // Expected: 'x'
        
        System.out.println("\nExtra letter found using XOR:");
        System.out.println("Extra letter in 'abcde': " + findTheDifferenceXOR(s1, t1)); // Expected: 'e'
        System.out.println("Extra letter in 'y': " + findTheDifferenceXOR(s2, t2)); // Expected: 'y'
        System.out.println("Extra letter in 'aeoiux': " + findTheDifferenceXOR(s3, t3)); // Expected: 'x'
    }
}
