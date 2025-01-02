/**
 * The LongestSubString class provides methods to determine the length of the longest
 * substring without repeating characters within a given string. This problem is a
 * common algorithmic challenge that tests understanding of string manipulation and
 * efficient searching techniques.
 *
 * <p>
 * <b>Problem Statement:</b><br>
 * Given a string, find the length of the longest substring without repeating characters.
 * For example:
 * <ul>
 *     <li>Input: "ilovetocodeandsolveproblemscodingismypassion" → Output: 11</li>
 *     <li>Input: "abcabcbb" → Output: 3 ("abc")</li>
 *     <li>Input: "bbbbb" → Output: 1 ("b")</li>
 *     <li>Input: "pwwkew" → Output: 3 ("wke")</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Approach:</b><br>
 * This implementation uses a brute-force approach where every possible substring is
 * examined to check if it contains all unique characters. The helper method
 * `areDistinct` checks the uniqueness of characters within a given substring.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n³), where n is the length of the string.<br>
 * <b>Space Complexity:</b> O(1), as it uses a constant amount of additional space.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b><br>
 * This problem is fundamental in understanding string manipulation and is commonly
 * used in coding interviews to assess a candidate's ability to implement and analyze
 * algorithmic solutions.
 * </p>
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
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize a boolean array `visited` of size 26 (assuming only lowercase letters).</li>
     *     <li>Iterate through the substring from index `i` to `j`.</li>
     *     <li>For each character, calculate its index by subtracting 'a' from it.</li>
     *     <li>If the character has already been visited, return {@code false}.</li>
     *     <li>Mark the character as visited.</li>
     * </ol>
     * </p>
     */
    public static Boolean areDistinct(String str, int i, int j) {
        // Assuming the string contains only lowercase English letters.
        // Initialize a boolean array to track visited characters.
        boolean[] visited = new boolean[26];

        // Iterate through the substring from index i to j.
        for (int k = i; k <= j; k++) {
            char currentChar = str.charAt(k);
            // Calculate the index (0-25) based on the character.
            int charIndex = currentChar - 'a';

            // If the character is outside the lowercase letters range, adjust accordingly.
            if (charIndex < 0 || charIndex >= 26) {
                // For simplicity, return false if character is not a lowercase letter.
                return false;
            }

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
     * This method uses a brute-force approach with a time complexity of O(n³), where n is the length
     * of the string.
     *
     * @param str The input string.
     * @return The length of the longest substring without repeating characters.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize a variable `res` to store the length of the longest substring found.</li>
     *     <li>Iterate through each character in the string as the starting point.</li>
     *     <li>For each starting point, iterate through all possible ending points.</li>
     *     <li>Check if the substring from the starting point to the ending point has all distinct characters using `areDistinct`.</li>
     *     <li>If it does, update `res` with the maximum value between `res` and the length of the current substring.</li>
     * </ol>
     * </p>
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
     * The main method serves as an entry point to test the longestUniqueSubsttr method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample input string
        String str = "ilovetocodeandsolveproblemscodingismypassion";
        System.out.println("The input string is: " + str);

        // Call the method to find the length of the longest substring without repeating characters
        int len = longestUniqueSubsttr(str);
        System.out.println("The length of the longest non-repeating character substring is: " + len);

        // Additional test cases
        String[] testStrings = {
            "abcabcbb",
            "bbbbb",
            "pwwkew",
            "",
            "a",
            "abcdefg",
            "abba",
            "tmmzuxt"
        };

        System.out.println("\nAdditional Test Cases:");
        for (String testStr : testStrings) {
            int result = longestUniqueSubsttr(testStr);
            System.out.println("Input: \"" + testStr + "\" → Longest Substring Length: " + result);
        }
    }
}
