/**
 * The ReverseString class provides methods to reverse a given string.
 * It includes two different approaches:
 * 1. Using simple string concatenation.
 * 2. Using the StringBuilder class for more efficient string manipulation.
 * 
 * Reversing strings is a fundamental problem that helps in understanding
 * string manipulation, loop constructs, and the efficiency of different methods.
 */
public class ReverseString {

    /**
     * Reverses the input string using simple string concatenation.
     * This method iterates through the string from the end to the beginning
     * and appends each character to a new string.
     *
     * @param string The input string to be reversed.
     * @return A new string which is the reverse of the input string.
     */
    private String reverse(String string) {
        String temp = "";
        // Iterate from the last character to the first
        for (int i = string.length() - 1; i >= 0; i--) {
            temp += string.charAt(i);
        }
        return temp;
    }

    /**
     * Reverses the input string using the StringBuilder class.
     * This method is more efficient than simple string concatenation,
     * especially for longer strings, as it avoids the creation of multiple
     * immutable string objects during the reversal process.
     *
     * @param string The input string to be reversed.
     * @return A new string which is the reverse of the input string.
     */
    private String reverse2(String string) {
        StringBuilder temp = new StringBuilder();
        // Iterate from the last character to the first
        for (int i = string.length() - 1; i >= 0; i--) {
            temp.append(string.charAt(i));
        }
        return temp.toString();
    }

    /**
     * The main method serves as an entry point to test the reverse methods with a sample input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample input string
        String greet = "Hello World";
        ReverseString reverseString = new ReverseString();
        
        // Test reverse method using string concatenation
        System.out.println("Reversed using reverse method: " + reverseString.reverse(greet));
        
        // Test reverse method using StringBuilder
        System.out.println("Reversed using reverse2 method: " + reverseString.reverse2(greet));
    }
}
