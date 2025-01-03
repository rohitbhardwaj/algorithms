import java.util.*;

/**
 * The {@code WordLadderOptimized} class provides an optimized method to determine the length
 * of the shortest transformation sequence from a start word to an end word using a given dictionary.
 *
 * <p>It utilizes Bidirectional Breadth-First Search (BFS) to explore from both the start and end simultaneously,
 * reducing the search space and improving efficiency.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * WordLadderOptimized solver = new WordLadderOptimized();
 * String beginWord = "hit";
 * String endWord = "cog";
 * List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
 * int length = solver.ladderLength(beginWord, endWord, wordList);
 * System.out.println(length); // Output: 5
 * }</pre>
 *
 * @author 
 */
public class WordLadderOptimized {
    
    /**
     * Finds the length of the shortest transformation sequence from beginWord to endWord
     * using the provided wordList, optimized with Bidirectional BFS.
     *
     * @param beginWord The word to start the transformation.
     * @param endWord   The word to end the transformation.
     * @param wordList  The list of allowed intermediate words.
     * @return The number of words in the shortest transformation sequence, or 0 if no sequence exists.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert the word list to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        
        // Early termination if endWord is not present
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        // Initialize two sets for Bidirectional BFS
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        // To keep track of visited words
        Set<String> visited = new HashSet<>();
        
        int level = 1; // Number of transformations
        
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller set for efficiency
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            
            Set<String> tempSet = new HashSet<>();
            
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                
                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];
                    
                    // Try all possible lowercase letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;
                        }
                        
                        chars[i] = c;
                        String newWord = String.valueOf(chars);
                        
                        // If the new word is in the endSet, a connection is found
                        if (endSet.contains(newWord)) {
                            return level + 1;
                        }
                        
                        // If the new word is valid and not visited
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            tempSet.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    
                    // Restore the original character
                    chars[i] = originalChar;
                }
            }
            
            // Move to the next level
            beginSet = tempSet;
            level++;
        }
        
        // If no connection is found
        return 0;
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code WordLadderOptimized} class.
     *
     * <p>It demonstrates the usage of the ladderLength method with sample inputs.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        WordLadderOptimized solver = new WordLadderOptimized();
        
        // Example 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");
        int length1 = solver.ladderLength(beginWord1, endWord1, wordList1);
        System.out.println("Example 1 Output (Optimized): " + length1); // Expected Output: 5
        
        // Example 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        int length2 = solver.ladderLength(beginWord2, endWord2, wordList2);
        System.out.println("Example 2 Output (Optimized): " + length2); // Expected Output: 0
        
        // Additional Example 3
        String beginWord3 = "a";
        String endWord3 = "c";
        List<String> wordList3 = Arrays.asList("a","b","c");
        int length3 = solver.ladderLength(beginWord3, endWord3, wordList3);
        System.out.println("Example 3 Output (Optimized): " + length3); // Expected Output: 2 ("a" -> "c")
    }
}
