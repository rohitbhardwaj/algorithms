/**
 * This class provides methods to compare two strings after processing backspace characters ('#').
 * Methods include:
 * - Using a Stack (O(N) time, O(N) space)
 * - Using Two Pointers (O(N) time, O(1) space, optimized approach)
 */
import java.util.Stack;

public class BackspaceStringCompare {
    
    /**
     * Stack-based Approach: Process each string using a stack.
     * Time Complexity: O(N), Space Complexity: O(N).
     * @param str The input string containing characters and '#' backspaces.
     * @return The final processed string after applying backspaces.
     */
    public static String processStringUsingStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        return String.valueOf(stack);
    }

    /**
     * Two-Pointer Approach: Compare characters from the end while skipping backspaces.
     * Time Complexity: O(N), Space Complexity: O(1) (optimal solution).
     * @param s First input string.
     * @param t Second input string.
     * @return true if both strings are equal after processing, false otherwise.
     */
    public static boolean backspaceCompareTwoPointers(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skipS = 0, skipT = 0;
        
        while (i >= 0 || j >= 0) {
            // Process backspaces in s
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            
            // Process backspaces in t
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            
            // Compare characters
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                return false;
            }
            
            // If one string is exhausted but the other isn't
            if ((i >= 0) != (j >= 0)) {
                return false;
            }
            
            i--;
            j--;
        }
        
        return true;
    }

    /**
     * Main method to test different approaches.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String s1 = "ab#c", t1 = "ad#c";
        String s2 = "ab##", t2 = "c#d#";
        String s3 = "a#c", t3 = "b";
        String s4 = "xywrrmp", t4 = "xywrrmu#p";

        System.out.println("Using Stack Method:");
        System.out.println(processStringUsingStack(s1).equals(processStringUsingStack(t1))); // Expected: true
        System.out.println(processStringUsingStack(s2).equals(processStringUsingStack(t2))); // Expected: true
        System.out.println(processStringUsingStack(s3).equals(processStringUsingStack(t3))); // Expected: false
        System.out.println(processStringUsingStack(s4).equals(processStringUsingStack(t4))); // Expected: true

        System.out.println("\nUsing Two Pointers Method:");
        System.out.println(backspaceCompareTwoPointers(s1, t1)); // Expected: true
        System.out.println(backspaceCompareTwoPointers(s2, t2)); // Expected: true
        System.out.println(backspaceCompareTwoPointers(s3, t3)); // Expected: false
        System.out.println(backspaceCompareTwoPointers(s4, t4)); // Expected: true
    }
}
