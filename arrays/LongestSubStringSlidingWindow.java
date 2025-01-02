/**
 * The LongestSubStringSlidingWindow class provides a method to determine the length
 * of the longest substring without repeating characters within a given string.
 * This implementation utilizes the Sliding Window technique for efficient computation.
 * This problem is a common algorithmic challenge that tests understanding of
 * string manipulation and optimized searching techniques.
 */
import java.io.*;

public class LongestSubStringSlidingWindow {

    /**
     * Finds the length of the longest substring without repeating characters in the given string
     * using the Sliding Window technique.
     *
     * @param str The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int longestUniqueSubsttr(String str) {
        String window = ""; // Current window substring
        int maxLength = 0;  // Maximum length of substring found

        // Handle edge cases
        if (str.isEmpty()) {
            return 0; // No characters in the string
        } else if (str.length() == 1) {
            return 1; // Single character string
        }

        // Iterate through each character in the string
        for (char c : str.toCharArray()) {
            String currentChar = String.valueOf(c);

            // If the current character is already in the window, adjust the window
            if (window.contains(currentChar)) {
                // Remove all characters up to and including the first occurrence of currentChar
                window = window.substring(window.indexOf(currentChar) + 1);
            }

            // Add the current character to the window
            window += currentChar;

            // Update maxLength if the current window is larger
            maxLength = Math.max(window.length(), maxLength);
        }

        return maxLength; // Return the length of the longest unique substring found
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
