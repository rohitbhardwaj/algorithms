/**
 * The LongestSubString class provides methods to determine the length of the longest
 * substring without repeating characters within a given string. This problem is a
 * common algorithmic challenge that tests understanding of string manipulation and
 * efficient searching techniques.
 */
import java.io.*;
import java.util.*;

public class LongestSubString {

    /**
     * Checks if all characters in the substring of the given string from index i to j are distinct.
     *
     * @param str The input string.
     * @param i   The starting index of the substring.
     * @param j   The ending index of the substring.
     * @return {@code true} if all characters are distinct; {@code false} otherwise.
     */
    public static Boolean areDistinct(String str, int i, int j) {
        // Assuming the string contains only lowercase English letters.
        // Initialize a boolean array to track visited characters.
        boolean[] visited = new boolean[26];

        // Iterate through the substring from index i to j.
        for (int k = i; k <= j; k++) {
            int charIndex = str.charAt(k) - 'a'; // Map character to index 0-25.
            if (visited[charIndex]) {
                // If the character is already visited, substring is not unique.
                return false;
            }
            // Mark the character as visited.
            visited[charIndex] = true;
        }
        // All characters in the substring are distinct.
        return true;
    }

    /**
     * Finds the length of the longest substring without repeating characters in the given string.
     * This method uses a brute-force approach with a time complexity of O(n^3), where n is the length
     * of the string.
     *
     * @param str The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int longestUniqueSubsttr(String str) {
        int n = str.length();
        int res = 0; // To store the length of the longest substring found.

        // Iterate through each character in the string as the starting point.
        for (int i = 0; i < n; i++) {
            // For each starting point, iterate through all possible ending points.
            for (int j = i; j < n; j++) {
                // Check if the substring from index i to j has all distinct characters.
                if (areDistinct(str, i, j)) {
                    // Update the result if a longer unique substring is found.
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res; // Return the maximum length found.
    }

    /**
     * The main method serves as an entry point to test the longestUniqueSubsttr method with a sample input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String str = "ilovetocodeandsolveproblemscodingismypassion";
        System.out.println("The input string is: " + str);

        int len = longestUniqueSubsttr(str);

        System.out.println("The length of the longest non-repeating character substring is: " + len);
    }
}
