/**
 * This class provides a method to group anagrams from a list of strings.
 * An anagram is formed by rearranging the letters of a word to produce another word.
 * Method used:
 * - Sorting-Based Hashing (O(N * K log K))
 */
import java.util.*;

public class GroupAnagrams {
    
    /**
     * Groups anagrams together from the given list of strings.
     * Time Complexity: O(N * K log K) (due to sorting each string),
     * Space Complexity: O(N) (for storing the grouped anagrams).
     * @param strs The array of strings.
     * @return A list of grouped anagrams.
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedKey = new String(charArray);
            
            map.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }

    /**
     * Main method to test grouping anagrams.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};
        
        System.out.println("Grouped Anagrams:");
        System.out.println(groupAnagrams(strs1)); // Expected: [["bat"], ["nat", "tan"], ["eat", "tea", "ate"]]
        System.out.println(groupAnagrams(strs2)); // Expected: [[""]]
        System.out.println(groupAnagrams(strs3)); // Expected: [["a"]]
    }
}
