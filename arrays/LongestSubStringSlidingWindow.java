/**
 * The LongestSubStringSlidingWindow class provides a method to determine the length
 * of the longest substring without repeating characters within a given string.
 * This implementation utilizes the Sliding Window technique for efficient computation.
 * This problem is a common algorithmic challenge that tests understanding of
 * string manipulation and optimized searching techniques.
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
 * Utilize the Sliding Window technique to maintain a window of characters without duplicates.
 * Iterate through the string, expanding the window by adding characters until a duplicate is found.
 * Upon encountering a duplicate, shrink the window from the left to exclude the first occurrence of the duplicated character.
 * Keep track of the maximum window size encountered during the process.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n), where n is the length of the string.<br>
 * <b>Space Complexity:</b> O(min(m, n)), where m is the size of the character set.
 * </p>
 *
 * <p>
 * <b>Use Cases:</b><br>
 * This problem is fundamental in understanding string manipulation and efficient searching
 * techniques. It is commonly used in coding interviews to assess a candidate's ability to
 * implement optimized algorithms.
 * </p>
 */
public class LongestSubStringSlidingWindow {

    /**
     * Finds the length of the longest substring without repeating characters in the given string
     * using the Sliding Window technique.
     *
     * @param str The input string.
     * @return The length of the longest substring without repeating characters.
     *
     * <p>
     * <b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize an empty string `window` to represent the current substring without duplicates.</li>
     *     <li>Initialize `maxLength` to keep track of the maximum length found.</li>
     *     <li>Handle edge cases:
     *         <ul>
     *             <li>If the input string is empty, return 0.</li>
     *             <li>If the string has only one character, return 1.</li>
     *         </ul>
     *     </li>
     *     <li>Iterate through each character in the string:
     *         <ul>
     *             <li>If the character is already in the `window`, adjust the `window` by removing all characters up to and including the first occurrence of the duplicated character.</li>
     *             <li>Add the current character to the `window`.</li>
     *             <li>Update `maxLength` if the current `window` is larger than the previously recorded maximum.</li>
     *         </ul>
     *     </li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example:</b><br>
     * For the input string "ilovetocodeandsolveproblemscodingismypassion":
     * <ul>
     *     <li>The method will return 11, corresponding to the substring "andsolvepro".</li>
     * </ul>
     * </p>
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
