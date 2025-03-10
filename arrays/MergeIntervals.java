/**
 * This class provides methods to merge overlapping intervals.
 * Methods include:
 * - Sorting & Merging Approach (O(N log N) time complexity)
 */
import java.util.Arrays;
import java.util.LinkedList;

public class MergeIntervals {
    
    /**
     * Merges overlapping intervals and returns a list of non-overlapping intervals.
     * Time Complexity: O(N log N) (due to sorting), Space Complexity: O(N) (for output list).
     * @param intervals The array of intervals where each interval is represented as [start, end].
     * @return The merged intervals as a 2D array.
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        LinkedList<int[]> merged = new LinkedList<>();
        
        for (int[] interval : intervals) {
            // If merged list is empty or there is no overlap, add new interval
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Merge overlapping intervals
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * Main method to test merging intervals.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals2 = {{1,4},{4,5}};
        int[][] intervals3 = {{6,8},{1,9},{2,4},{4,7}};
        
        System.out.println("Merged Intervals:");
        System.out.println(Arrays.deepToString(mergeIntervals(intervals1))); // Expected: [[1,6],[8,10],[15,18]]
        System.out.println(Arrays.deepToString(mergeIntervals(intervals2))); // Expected: [[1,5]]
        System.out.println(Arrays.deepToString(mergeIntervals(intervals3))); // Expected: [[1,9]]
    }
}
