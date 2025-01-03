/**
 * The {@code FirstUniqueCharacter} class provides a method to find the index of the first non-repeating character
 * in a given string. If no such character exists, the method returns -1.
 *
 * <p>This implementation assumes that the input string consists solely of lowercase English letters ('a' to 'z').
 * It employs an efficient two-pass approach using a fixed-size frequency array to achieve optimal time and space
 * complexity.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * String input = "loveleetcode";
 * int index = FirstUniqueCharacter.firstUniqChar(input);
 * System.out.println(index); // Output: 2
 * }</pre>
 *
 * @author
 */
public class FirstUniqueCharacter {

    /**
     * Finds the index of the first non-repeating (unique) character in the input string.
     *
     * <p>The method performs the following steps:</p>
     * <ol>
     *     <li>Validates the input string to ensure it is not null or empty.</li>
     *     <li>Initializes a frequency array to count occurrences of each character.</li>
     *     <li>Traverses the string once to populate the frequency array.</li>
     *     <li>Traverses the string a second time to identify the first character with a frequency of one.</li>
     *     <li>Returns the index of the first unique character if found; otherwise, returns -1.</li>
     * </ol>
     *
     * @param s The input string consisting of lowercase English letters ('a' to 'z').
     * @return The index of the first non-repeating character, or -1 if no such character exists.
     */
    public static int firstUniqChar(String s) {
        // Edge Case Handling:
        // If the input string is null or empty, there are no characters to evaluate, so return -1.
        if (s == null || s.length() == 0) {
            return -1;
        }

        // Frequency Array Initialization:
        // Create an integer array of size 26 to represent each lowercase English letter.
        // Each index corresponds to a letter: 0 -> 'a', 1 -> 'b', ..., 25 -> 'z'.
        int[] freq = new int[26];

        // First Pass - Frequency Counting:
        // Traverse the string character by character to count the occurrences of each character.
        // This pass populates the frequency array.
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);          // Extract the current character from the string.
            int charIndex = currentChar - 'a';       // Calculate the array index for the character.
            freq[charIndex]++;                        // Increment the frequency count for this character.
        }

        // Second Pass - Identifying the First Unique Character:
        // Traverse the string again to find the first character with a frequency of exactly one.
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);          // Extract the current character.
            int charIndex = currentChar - 'a';       // Calculate the array index for the character.
            if (freq[charIndex] == 1) {              // Check if this character is unique.
                return i;                             // If unique, return its index immediately.
            }
        }

        // If no unique character is found after traversing the entire string, return -1.
        return -1;
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code firstUniqChar} method with various input scenarios.
     *
     * <p>It defines a series of test cases, invokes the {@code firstUniqChar} method on each, and prints the results to the console.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        // Define an array of test input strings covering diverse scenarios.
        String[] testInputs = {
            "leetcode",      // Expected Output: 0 ('l' is unique)
            "loveleetcode",  // Expected Output: 2 ('v' is unique)
            "aabb",          // Expected Output: -1 (No unique characters)
            "abcabcde",      // Expected Output: 6 ('d' is unique)
            "xxyz",          // Expected Output: 2 ('y' is unique)
            "a",             // Expected Output: 0 ('a' is the only character)
            "aaabcccdeeef",  // Expected Output: 7 ('d' is unique)
            "abcdabcd",      // Expected Output: -1 (All characters repeat)
            "aabccbd",       // Expected Output: 6 ('d' is unique)
            "z"              // Expected Output: 0 ('z' is the only character)
        };

        // Iterate through each test input to evaluate the method's correctness.
        for (String input : testInputs) {
            // Invoke the firstUniqChar method and store the result.
            int index = firstUniqChar(input);

            // Display the input string and the corresponding output.
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Output: " + index);
            System.out.println("---------------------------");
        }
    }
}
