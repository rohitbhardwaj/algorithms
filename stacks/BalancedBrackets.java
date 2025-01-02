/**
 * The BalancedBrackets class provides a method to check if the brackets in a given
 * expression are balanced. Balanced brackets mean that each opening bracket has a corresponding
 * closing bracket in the correct order and type. This problem is fundamental in understanding
 * stack operations and is commonly encountered in coding interviews.
 *
 * The class includes:
 * 1. A method `areBracketsBalanced` that uses a stack-based approach to verify bracket balance.
 * 2. A `main` method to demonstrate and test the functionality with sample inputs.
 */
import java.util.*;

public class BalancedBrackets {

    /**
     * Checks if the brackets in the given expression are balanced.
     *
     * @param expr The input string containing brackets.
     * @return {@code true} if the brackets are balanced; {@code false} otherwise.
     *
     * <p>
     * <b>Algorithm Explanation:</b>
     * <ol>
     *     <li>Initialize an empty stack to keep track of opening brackets.</li>
     *     <li>Traverse each character in the expression:
     *         <ul>
     *             <li>If the character is an opening bracket ('(', '[', '{'), push it onto the stack.</li>
     *             <li>If the character is a closing bracket (')', ']', '}'):
     *                 <ul>
     *                     <li>Check if the stack is empty. If it is, return {@code false} as there's no matching opening bracket.</li>
     *                     <li>Pop the top element from the stack and check if it matches the type of the closing bracket.</li>
     *                     <li>If it doesn't match, return {@code false}.</li>
     *                 </ul>
     *             </li>
     *         </ul>
     *     </li>
     * </ol>
     * After traversing the entire expression, check if the stack is empty. If it is, all brackets were balanced.
     * Otherwise, return {@code false}.
     * </p>
     *
     * <p>
     * <b>Time Complexity:</b> O(n), where n is the length of the expression.
     * <b>Space Complexity:</b> O(n), due to the use of the stack.
     * </p>
     */
    static boolean areBracketsBalanced(String expr) {
        // Using ArrayDeque is faster and more efficient than using the Stack class
        Deque<Character> stack = new ArrayDeque<Character>();

        // Traverse each character in the expression
        for (int i = 0; i < expr.length(); i++) {
            char currentChar = expr.charAt(i);

            // If the current character is an opening bracket, push it onto the stack
            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                stack.push(currentChar);
                continue;
            }

            // If the current character is a closing bracket, check for balance
            if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
                // If stack is empty, there's no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }

                // Pop the top element from the stack
                char lastBracket = stack.pop();

                // Check if the popped bracket matches the type of the closing bracket
                if (!isMatchingPair(lastBracket, currentChar)) {
                    return false;
                }
            }
        }

        // After processing all characters, check if any unmatched brackets remain
        return stack.isEmpty();
    }

    /**
     * Helper method to check if the pair of brackets match.
     *
     * @param open  The opening bracket.
     * @param close The closing bracket.
     * @return {@code true} if the brackets match; {@code false} otherwise.
     */
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }

    /**
     * The main method serves as an entry point to test the areBracketsBalanced method with sample inputs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Sample input expressions
        String expr1 = "([{}}])";
        String expr2 = "({[]})";
        String expr3 = "((()";
        String expr4 = "";

        // Function calls to check if the brackets are balanced
        System.out.println("Expression: " + expr1);
        System.out.println("Balanced: " + (areBracketsBalanced(expr1) ? "Yes" : "No") + "\n");

        System.out.println("Expression: " + expr2);
        System.out.println("Balanced: " + (areBracketsBalanced(expr2) ? "Yes" : "No") + "\n");

        System.out.println("Expression: " + expr3);
        System.out.println("Balanced: " + (areBracketsBalanced(expr3) ? "Yes" : "No") + "\n");

        System.out.println("Expression: \"" + expr4 + "\" (Empty String)");
        System.out.println("Balanced: " + (areBracketsBalanced(expr4) ? "Yes" : "No") + "\n");

        // Additional test cases
        String expr5 = "{[()]}";
        String expr6 = "{[(])}";
        String expr7 = "{{[[(())]]}}";

        System.out.println("Expression: " + expr5);
        System.out.println("Balanced: " + (areBracketsBalanced(expr5) ? "Yes" : "No") + "\n");

        System.out.println("Expression: " + expr6);
        System.out.println("Balanced: " + (areBracketsBalanced(expr6) ? "Yes" : "No") + "\n");

        System.out.println("Expression: " + expr7);
        System.out.println("Balanced: " + (areBracketsBalanced(expr7) ? "Yes" : "No") + "\n");
    }
}
