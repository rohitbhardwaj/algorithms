import java.util.*;

/**
 * The {@code WordLadder} class provides methods to determine the length of the shortest
 * transformation sequence from a start word to an end word using a given dictionary.
 *
 * <p>It utilizes Breadth-First Search (BFS) to explore all possible one-letter transformations
 * efficiently.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * WordLadder solver = new WordLadder();
 * String beginWord = "hit";
 * String endWord = "cog";
 * List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
 * int length = solver.ladderLength(beginWord, endWord, wordList);
 * System.out.println(length); // Output: 5
 * }</pre>
 *
 * @author 
 */
public class WordLadder {
    
    /**
     * Finds the length of the shortest transformation sequence from beginWord to endWord
     * using the provided wordList.
     *
     * @param beginWord The word to start the transformation.
     * @param endWord   The word to end the transformation.
     * @param wordList  The list of allowed intermediate words.
     * @return The number of words in the shortest transformation sequence, or 0 if no sequence exists.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert the word list to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If the endWord is not in the wordList, no possible transformation
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        // Initialize BFS
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        
        // To keep track of visited words
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        int level = 1; // Number of transformations
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Iterate through the current level
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // If the current word is the endWord, return the level
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                // Generate all possible one-letter transformations
                List<String> neighbors = getNeighbors(currentWord, wordSet);
                
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            
            // Increment the level after exploring all nodes at the current level
            level++;
        }
        
        // If endWord is not reachable
        return 0;
    }
    
    /**
     * Generates all possible one-letter transformations of the given word that exist in the wordSet.
     *
     * @param word     The current word.
     * @param wordSet  The set of allowed words.
     * @return A list of valid neighboring words.
     */
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        
        // Iterate through each character position
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            
            // Try all possible lowercase letters
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) {
                    continue; // Skip the same character
                }
                
                chars[i] = c;
                String newWord = String.valueOf(chars);
                
                // If the new word is in the wordSet, it's a valid neighbor
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            
            // Restore the original character for the next iteration
            chars[i] = originalChar;
        }
        
        return neighbors;
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code WordLadder} class.
     *
     * <p>It demonstrates the usage of the ladderLength method with sample inputs.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        WordLadder solver = new WordLadder();
        
        // Example 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");
        int length1 = solver.ladderLength(beginWord1, endWord1, wordList1);
        System.out.println("Example 1 Output: " + length1); // Expected Output: 5
        
        // Example 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        int length2 = solver.ladderLength(beginWord2, endWord2, wordList2);
        System.out.println("Example 2 Output: " + length2); // Expected Output: 0
        
        // Additional Example 3
        String beginWord3 = "a";
        String endWord3 = "c";
        List<String> wordList3 = Arrays.asList("a","b","c");
        int length3 = solver.ladderLength(beginWord3, endWord3, wordList3);
        System.out.println("Example 3 Output: " + length3); // Expected Output: 2 ("a" -> "c")
    }
}
