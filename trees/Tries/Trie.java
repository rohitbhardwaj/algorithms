import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * The {@code Trie} class implements a Trie (Prefix Tree) data structure.
 *
 * <p>A Trie is an efficient information retrieval data structure used to store and search
 * strings, especially useful for tasks like autocomplete and spell checking. Each node in
 * the Trie represents a character, and edges represent the sequence of characters forming words.</p>
 *
 * <p>Functionalities include:</p>
 * <ul>
 *     <li><strong>Insert:</strong> Add a word to the Trie.</li>
 *     <li><strong>Search (`findWord`):</strong> Check if a word exists in the Trie.</li>
 *     <li><strong>Delete:</strong> Remove a word from the Trie.</li>
 * </ul>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * Trie trie = new Trie();
 * trie.insert("heating");
 * trie.insert("heat");
 * System.out.println(trie.findWord("heat")); // Output: true
 * System.out.println(trie.delete("heat"));   // Output: true
 * System.out.println(trie.findWord("heat")); // Output: false
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong></p>
 * <ul>
 *     <li><strong>Insert:</strong> O(m), where m is the length of the word.</li>
 *     <li><strong>Search:</strong> O(m), where m is the length of the word.</li>
 *     <li><strong>Delete:</strong> O(m), where m is the length of the word.</li>
 * </ul>
 *
 * <p><strong>Space Complexity:</strong> O(n * m), where n is the number of words inserted
 * and m is the average length of the words. This accounts for the storage of each character
 * in the Trie.</p>
 *
 * @author 
 */
public class Trie {
    /**
     * The {@code Node} class represents a single node within the Trie.
     *
     * <p>Each node contains:
     * <ul>
     *     <li>A HashMap mapping characters to their corresponding child nodes.</li>
     *     <li>A boolean flag indicating whether the node marks the end of a valid word.</li>
     * </ul>
     * </p>
     */
    private static class Node {
        // Children nodes mapped by character
        Map<Character, Node> children;
        
        // Flag to indicate end of a word
        boolean isWord;

        /**
         * Constructs a new {@code Node} with an empty children map and {@code isWord} set to {@code false}.
         */
        Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    // Root node of the Trie
    private final Node root;

    /**
     * Constructs an empty Trie.
     */
    public Trie() {
        root = new Node();
    }

    ////////////////////////////////////////
    //              insert                //
    ////////////////////////////////////////

    /**
     * Inserts a word into the Trie.
     *
     * <p>This method traverses the Trie character by character, creating new nodes as
     * necessary. After inserting all characters, it marks the last node as representing
     * the end of a valid word.</p>
     *
     * @param word The word to be inserted into the Trie.
     */
    public void insert(String word) {
        Node current = root;
        /*
           For each character in the word, check if that character exists
           in the current node's children. If not, add the character as a key
           in the map and create a new node as its value. Continue this process
           until all characters are traversed.
        */
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new Node());
            current = current.children.get(ch);
        }
        current.isWord = true;
    }

    ////////////////////////////////////////
    //              findWord              //
    ////////////////////////////////////////

    /**
     * Searches for a word in the Trie.
     *
     * <p>This method traverses the Trie based on the characters of the word.
     * If it successfully reaches the end of the word and the corresponding node
     * is marked as a valid word, it returns {@code true}. Otherwise, it returns {@code false}.</p>
     *
     * @param word The word to search for in the Trie.
     * @return {@code true} if the word exists in the Trie; {@code false} otherwise.
     */
    public boolean findWord(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                return false;
            }
        }
        return current.isWord;
    }

    ////////////////////////////////////////
    //               delete               //
    ////////////////////////////////////////

    /**
     * Deletes a word from the Trie.
     *
     * <p>This method removes a word from the Trie by traversing the Trie to locate the word.
     * If the word exists, it removes nodes that are no longer part of other words.
     * It handles cases where the word is a prefix of another word or shares a prefix
     * with other words.</p>
     *
     * @param word The word to be deleted from the Trie.
     * @return {@code true} if the word was successfully deleted; {@code false} if the word does not exist in the Trie.
     */
    public boolean delete(String word) {
        // Stack to keep track of nodes visited along the path
        Stack<Node> stack = new Stack<>();
        Node current = root;

        // Traverse the Trie and push nodes onto the stack
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false; // Word does not exist
            }
            current = current.children.get(ch);
            stack.push(current);
        }

        if (!current.isWord) {
            return false; // Word does not exist as a complete word
        }

        // Unmark the end of the word
        current.isWord = false;

        // Remove unnecessary nodes
        for (int i = word.length() - 1; i >= 0; i--) {
            Node node = stack.pop();
            if (node.isWord || !node.children.isEmpty()) {
                break; // Stop if the node is part of another word or has other children
            }
            if (!stack.isEmpty()) {
                char ch = word.charAt(i);
                stack.peek().children.remove(ch);
            } else {
                // If stack is empty, we're at the root
                root.children.remove(word.charAt(i));
            }
        }

        return true;
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code Trie} class.
     *
     * <p>It demonstrates the insertion, search, and deletion of words in the Trie.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        // Insert words into the Trie
        trie.insert("heating");
        trie.insert("heat");
        trie.insert("hello");
        trie.insert("helium");
        
        // Display the children of 'h' -> 'e' node
        System.out.println("Children of 'he': " + trie.root.children.get('h').children.get('e').children.keySet());
        // Expected Output: [a, l]
        
        // Search for existing and non-existing words
        System.out.println("Find 'heat': " + trie.findWordStatic(trie, "heat")); // Output: true
        System.out.println("Find 'he': " + trie.findWordStatic(trie, "he"));     // Output: false
        System.out.println("Find 'helium': " + trie.findWordStatic(trie, "helium")); // Output: true
        System.out.println("Find 'heating': " + trie.findWordStatic(trie, "heating")); // Output: true
        
        // Delete a word and verify
        System.out.println("Delete 'heat': " + trie.delete("heat")); // Output: true
        System.out.println("Find 'heat' after deletion: " + trie.findWordStatic(trie, "heat")); // Output: false
        System.out.println("Children of 'he': " + trie.root.children.get('h').children.get('e').children.keySet());
        // Expected Output: [a, l] (no change, as 'heat' shares prefix with 'heating')
        
        // Attempt to delete a non-existing word
        System.out.println("Delete 'he': " + trie.delete("he")); // Output: false
    }

    /**
     * Static helper method to access the non-static findWord method from the static main method.
     *
     * @param trie The Trie instance.
     * @param word The word to search for.
     * @return {@code true} if the word exists in the Trie; {@code false} otherwise.
     */
    private static boolean findWordStatic(Trie trie, String word) {
        return trie.findWord(word);
    }
}
