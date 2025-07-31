/**
 * InterviewCheatSheet.java
 *
 * A student-friendly library of interview algorithms with:
 *  1) Clear JavaDoc on every method describing the real interview problem.
 *  2) Inline comments on most lines to explain what each statement does.
 *  3) Self-contained test methods (each has JavaDoc) that run 3 cases and print PASS/FAIL.
 *
 * How to compile and run:
 *   javac InterviewCheatSheet.java && java InterviewCheatSheet
 *
 * NOTE: The goal is readability and learning, not micro-optimizations.
 */
import java.util.*;
import java.util.function.IntPredicate;

public class InterviewCheatSheet {

    // ---------------------------------------------------------------------
    // Utility: tiny assert helpers for readable PASS/FAIL output
    // ---------------------------------------------------------------------

    /**
     * Prints PASS if the two objects are equal, else prints a human-friendly FAIL.
     * @param name name of the test case
     * @param expected what we want
     * @param actual what we got
     */
    private static void assertEquals(String name, Object expected, Object actual) {
        if (Objects.equals(expected, actual)) {
            System.out.println("PASS - " + name + " => " + expected);
        } else {
            System.out.println("FAIL - " + name + " expected: " + expected + " but got: " + actual);
        }
    }

    /**
     * Prints PASS/FAIL for integer array equality.
     */
    private static void assertArrayEquals(String name, int[] expected, int[] actual) {
        if (Arrays.equals(expected, actual)) {
            System.out.println("PASS - " + name + " => " + Arrays.toString(actual));
        } else {
            System.out.println("FAIL - " + name + " expected: " + Arrays.toString(expected) + " but got: " + Arrays.toString(actual));
        }
    }

    /**
     * Prints PASS/FAIL for 2D integer arrays (e.g., interval lists).
     */
    private static void assert2DArrayEquals(String name, int[][] expected, int[][] actual) {
        if (Arrays.deepEquals(toObj(expected), toObj(actual))) {
            System.out.println("PASS - " + name + " => " + Arrays.deepToString(actual));
        } else {
            System.out.println("FAIL - " + name + " expected: " + Arrays.deepToString(expected) + " but got: " + Arrays.deepToString(actual));
        }
    }

    // Helper to box int[][] into Integer[][] so Arrays.deepEquals behaves as intended for comparisons in logs.
    private static Integer[][] toObj(int[][] a) {
        Integer[][] r = new Integer[a.length][];
        for (int i = 0; i < a.length; i++) {
            r[i] = Arrays.stream(a[i]).boxed().toArray(Integer[]::new);
        }
        return r;
    }

    // ---------------------------------------------------------------------
    // 1) TWO POINTERS — REMOVE DUPLICATES FROM A SORTED ARRAY
    // ---------------------------------------------------------------------

    /**
     * Problem: Given a sorted array of integers, remove duplicates in-place so that
     * each unique value appears only once at the front of the array. Return the count
     * of unique values. This mirrors a common interview problem where the input is
     * already sorted, so duplicates are consecutive.
     *
     * Inputs:
     *  - a: sorted integer array (non-decreasing order).
     *
     * Output:
     *  - the number of unique values placed at the start of the same array.
     *
     * Approach (Two Pointers):
     *  - Maintain two indices:
     *      read r: moves forward through every element.
     *      write w: next position to place a new unique element.
     *  - When the current element differs from the previous unique value, copy it to a[w] and advance w.
     *
     * Complexity:
     *  - Time: visits each element once (linear).
     *  - Space: constant extra memory (in-place).
     */
    public static int removeDuplicates(int[] a) {
        int w = 0;                                  // write index: where next unique value goes
        for (int r = 0; r < a.length; r++) {        // r scans the array from left to right
            if (w == 0 || a[r] != a[w - 1]) {       // if first element or different from last unique
                a[w++] = a[r];                      // copy current value to the write position, then move write forward
            }
        }
        return w;                                    // number of unique values written
    }

    // ---------------------------------------------------------------------
    // 2) SLIDING WINDOW — LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
    // ---------------------------------------------------------------------

    /**
     * Problem: Given a string, find the length of the longest substring that has all
     * unique (non-repeating) characters.
     *
     * Inputs:
     *  - s: any string.
     *
     * Output:
     *  - length of the longest substring without repeated characters.
     *
     * Approach (Sliding Window with last-seen map):
     *  - Move a right pointer r across the string.
     *  - Track the last index where each character appeared.
     *  - If current character appeared inside the window [l..r-1], jump l to lastIndex+1.
     *  - Keep the best length.
     *
     * Complexity:
     *  - Time: linear over characters (each char enters and leaves window at most once).
     *  - Space: up to number of distinct characters seen.
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> last = new HashMap<>(); // remembers last index of each character
        int l = 0,                                      // left boundary of current window
            ans = 0;                                    // best length found
        for (int r = 0; r < s.length(); r++) {          // expand window by moving right boundary
            char c = s.charAt(r);                       // current character
            if (last.containsKey(c) && last.get(c) >= l) {
                l = last.get(c) + 1;                    // jump left boundary past the previous occurrence
            }
            last.put(c, r);                              // update last seen index of c
            ans = Math.max(ans, r - l + 1);             // update best length
        }
        return ans;
    }

    // ---------------------------------------------------------------------
    // 3) CHARACTER COUNTING — ANAGRAM CHECK
    // ---------------------------------------------------------------------

    /**
     * Problem: Are two lowercase strings anagrams of each other? That is, do they
     * contain the exact same letters with the exact same counts (order may differ)?
     *
     * Inputs:
     *  - s, t: two lowercase strings (assumes 'a'..'z').
     *
     * Output:
     *  - true if s and t are anagrams; false otherwise.
     *
     * Approach (Counting array):
     *  - Use a frequency array of size 26. Count for s, subtract for t.
     *  - If all counts end at zero, they match.
     *
     * Complexity:
     *  - Time: linear in the number of characters.
     *  - Space: fixed (26).
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;      // quick reject by length
        int[] cnt = new int[26];                         // frequency counts for letters 'a'..'z'
        for (int i = 0; i < s.length(); i++) {           // walk both strings once
            cnt[s.charAt(i) - 'a']++;                    // increment for s
            cnt[t.charAt(i) - 'a']--;                    // decrement for t
        }
        for (int x : cnt) if (x != 0) return false;      // any mismatch means not an anagram
        return true;                                     // all zero => anagram
    }

    // ---------------------------------------------------------------------
    // 4) BINARY SEARCH (INDEX) — FIRST POSITION NOT LESS THAN TARGET
    // ---------------------------------------------------------------------

    /**
     * Problem: Given a sorted array and a target value, return the first index i
     * such that a[i] >= target. If every value is less than target, return a.length.
     *
     * Inputs:
     *  - a: sorted integer array.
     *  - target: value to compare against.
     *
     * Output:
     *  - index of first value >= target, or a.length if none.
     *
     * Approach (Binary Search on indices with half-open interval [lo, hi)):
     *  - Maintain [lo, hi) that always contains the answer.
     *  - If a[mid] >= target, answer is in [lo, mid]; else it's in (mid, hi).
     *
     * Complexity:
     *  - Time: logarithmic in array length (halves the space each iteration).
     *  - Space: constant.
     */
    public static int lowerBound(int[] a, int target) {
        int lo = 0, hi = a.length;                       // search space is [lo, hi)
        while (lo < hi) {                                
            int mid = lo + (hi - lo) / 2;                // avoid overflow and find middle
            if (a[mid] >= target) hi = mid;              // keep left half including mid
            else lo = mid + 1;                           // keep right half after mid
        }
        return lo;                                       // smallest i with a[i] >= target, or a.length
    }

    // ---------------------------------------------------------------------
    // 5) BINARY SEARCH ON ANSWER — MINIMUM EATING SPEED
    // ---------------------------------------------------------------------

    /**
     * Problem: Given banana piles and hours, find the smallest eating speed (bananas/hour)
     * so that all bananas are eaten within the given hours. Classic "search the answer" task.
     *
     * Inputs:
     *  - piles: array of pile sizes (positive integers).
     *  - hours: total hours allowed (positive integer).
     *
     * Output:
     *  - minimum integer speed that finishes in time.
     *
     * Approach (Binary search on speed):
     *  - The slower bound is 1 banana/hour; the upper bound is the largest pile.
     *  - For a guessed speed v, compute total hours needed using ceiling(pile/v).
     *  - If we can finish within the limit, try slower (left half); otherwise try faster (right half).
     *
     * Complexity:
     *  - Time: logarithmic number of guesses; each guess scans piles once.
     *  - Space: constant.
     */
    public static int minEatingSpeed(int[] piles, int hours) {
        int lo = 1,                                               // minimum possible speed
            hi = Arrays.stream(piles).max().getAsInt();           // maximum needed speed (largest pile)
        IntPredicate ok = v -> {                                  // feasibility check: can we finish at speed v?
            long needed = 0;                                      // hours needed at speed v
            for (int p : piles) {                                 // for each pile
                needed += (p + v - 1) / v;                        // ceiling division without floating point
            }
            return needed <= hours;                               // true if within hours
        };
        while (lo < hi) {                                         // standard binary search on answer
            int mid = lo + (hi - lo) / 2;                         // candidate speed
            if (ok.test(mid)) hi = mid;                           // feasible => try slower (left)
            else lo = mid + 1;                                    // not feasible => try faster (right)
        }
        return lo;                                                // smallest feasible speed
    }

    // ---------------------------------------------------------------------
    // 6) INTERVALS — MERGE OVERLAPPING RANGES
    // ---------------------------------------------------------------------

    /**
     * Problem: Given a list of closed intervals [start, end] (start <= end),
     * merge all overlapping intervals and return the result.
     *
     * Inputs:
     *  - intervals: array of [start, end] pairs.
     *
     * Output:
     *  - merged list of intervals (no overlaps), still [start, end].
     *
     * Approach:
     *  - Sort intervals by start time.
     *  - Walk the list; if current overlaps the last merged, extend the end.
     *    Otherwise, start a new merged interval.
     *
     * Complexity:
     *  - Time: sorting dominates; single pass merge is linear.
     *  - Space: proportional to number of merged intervals.
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // sort by start time
        List<int[]> out = new ArrayList<>();                        // output list of merged intervals
        for (int[] cur : intervals) {                               // scan input intervals
            if (out.isEmpty() || out.get(out.size() - 1)[1] < cur[0]) {
                out.add(cur.clone());                               // no overlap → start new merged interval
            } else {
                out.get(out.size() - 1)[1] =                        // overlap → extend the end
                        Math.max(out.get(out.size() - 1)[1], cur[1]);
            }
        }
        return out.toArray(new int[0][]);                           // convert list to array
    }

    // ---------------------------------------------------------------------
    // 7) MONOTONIC STACK — NEXT GREATER ELEMENT (INDEX)
    // ---------------------------------------------------------------------

    /**
     * Problem: For each position i in an array, find the index of the next element to
     * the right that is strictly greater than a[i]. If none exists, output -1 for i.
     *
     * Inputs:
     *  - a: integer array.
     *
     * Output:
     *  - int[] res where res[i] is the index of next greater element, or -1.
     *
     * Approach (Monotonic decreasing stack of indices):
     *  - Maintain a stack of indices whose values are in decreasing order.
     *  - When we see a new value that is larger than the top, it is the "next greater"
     *    for the index on top. Pop and fill answers repeatedly.
     *
     * Complexity:
     *  - Time: each index is pushed and popped at most once (linear).
     *  - Space: up to linear due to the stack in worst-case.
     */
    public static int[] nextGreaterIndex(int[] a) {
        int n = a.length;                                  // number of elements
        int[] res = new int[n];                            // result array
        Arrays.fill(res, -1);                              // default to -1 (no next greater)
        Deque<Integer> st = new ArrayDeque<>();            // stack will store indices, not values
        for (int i = 0; i < n; i++) {                      // scan from left to right
            while (!st.isEmpty() && a[i] > a[st.peek()]) { // while current is greater than stack top value
                res[st.pop()] = i;                         // current index i is the next greater for popped index
            }
            st.push(i);                                    // push current index; stack remains decreasing by value
        }
        return res;                                        // any remaining indices have res = -1
    }

    // ---------------------------------------------------------------------
    // 8) HEAP (PRIORITY QUEUE) — KTH LARGEST ELEMENT
    // ---------------------------------------------------------------------

    /**
     * Problem: Find the k-th largest element in an unsorted array without sorting the whole array.
     *
     * Inputs:
     *  - a: integer array.
     *  - k: rank to find (1 means the largest element).
     *
     * Output:
     *  - the k-th largest integer.
     *
     * Approach (Min-heap of size k):
     *  - Maintain a small min-heap that stores the k largest values seen so far.
     *  - If the heap grows beyond k, remove the smallest; the root is the k-th largest.
     *
     * Complexity:
     *  - Time: each insert/pop is logarithmic in k, done for each element.
     *  - Space: stores k elements.
     */
    public static int kthLargest(int[] a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min-heap
        for (int x : a) {                                  // process every number
            pq.offer(x);                                   // push into heap
            if (pq.size() > k) pq.poll();                  // keep only k largest values
        }
        return pq.peek();                                  // smallest among the k largest = k-th largest overall
    }

    // ---------------------------------------------------------------------
    // 9) GRAPH — SHORTEST PATH IN UNWEIGHTED GRAPH (BREADTH-FIRST SEARCH)
    // ---------------------------------------------------------------------

    /**
     * Problem: In a directed, unweighted graph, compute the smallest number of edges
     * from a source node to a destination node. If unreachable, return -1.
     *
     * Inputs:
     *  - n: number of nodes (0..n-1)
     *  - g: adjacency list where g[u] lists neighbors v with an edge u->v
     *  - src: source node id
     *  - dst: destination node id
     *
     * Output:
     *  - the minimum number of edges on any path from src to dst, or -1 if none exists.
     *
     * Approach (Breadth-First Search):
     *  - BFS explores the graph level by level, where level equals distance in edges.
     *  - When we first pop dst from the queue, we have found the shortest path length.
     *
     * Complexity:
     *  - Time: processes each node and edge at most once.
     *  - Space: queue and distance arrays scale with the number of nodes.
     */
    public static int shortestUnweighted(int n, List<List<Integer>> g, int src, int dst) {
        int[] dist = new int[n];                           // distance array
        Arrays.fill(dist, -1);                             // -1 means unvisited
        Deque<Integer> q = new ArrayDeque<>();             // queue for BFS
        q.add(src);                                        // start from src
        dist[src] = 0;                                     // distance to src is 0
        while (!q.isEmpty()) {                             // while there are nodes to explore
            int u = q.poll();                              // pop from queue
            if (u == dst) return dist[u];                  // found destination -> shortest distance
            for (int v : g.get(u)) {                       // explore neighbors
                if (dist[v] == -1) {                       // if neighbor not visited
                    dist[v] = dist[u] + 1;                 // set its distance as one more step
                    q.add(v);                              // and enqueue for future exploration
                }
            }
        }
        return -1;                                         // unreachable
    }

    // ---------------------------------------------------------------------
    // 10) TOPOLOGICAL ORDER — KAHN'S ALGORITHM
    // ---------------------------------------------------------------------

    /**
     * Problem: Given a directed acyclic graph (DAG) of prerequisites (u -> v means u must
     * come before v), produce a valid order of all nodes. If there is a cycle, return empty.
     *
     * Inputs:
     *  - n: number of nodes (0..n-1)
     *  - g: adjacency list; edge u->v means "u before v"
     *
     * Output:
     *  - list of nodes in an order that respects prerequisites, or empty list if a cycle exists.
     *
     * Approach (Kahn's Algorithm using indegree counts):
     *  - indegree[v] = number of prerequisites for v.
     *  - Start with all nodes of indegree 0 in a queue.
     *  - Repeatedly remove a node u, append to order, and reduce indegree of its neighbors.
     *  - If we processed fewer than n nodes, there is a cycle.
     *
     * Complexity:
     *  - Time: proportional to nodes + edges.
     *  - Space: for indegree array and the queue.
     */
    public static List<Integer> topologicalOrder(int n, List<List<Integer>> g) {
        int[] indeg = new int[n];                          // indegree of each node
        for (int u = 0; u < n; u++)                        // compute indegrees by scanning edges
            for (int v : g.get(u)) indeg[v]++;
        Deque<Integer> q = new ArrayDeque<>();             // queue of nodes with indegree 0
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();           // result list
        while (!q.isEmpty()) {                             // process nodes with no remaining prerequisites
            int u = q.poll();                              // take one
            order.add(u);                                  // add to final order
            for (int v : g.get(u)) {                       // for each node depending on u
                if (--indeg[v] == 0) q.add(v);             // once all its prerequisites are done, push it
            }
        }
        return order.size() == n ? order : List.of();      // if not all nodes processed => cycle -> return empty
    }

    // ---------------------------------------------------------------------
    // 11) DISJOINT SET (UNION-FIND) — WITH KRUSKAL'S MINIMUM SPANNING TREE
    // ---------------------------------------------------------------------

    /**
     * Data structure: Disjoint Set (Union-Find)
     * Problem it solves: Maintain a collection of disjoint groups of elements, supporting:
     *  - find(x): which group is x in?
     *  - unite(a,b): merge groups containing a and b; returns false if already same group.
     *
     * Implementation details:
     *  - Path compression (flatten trees on find) and union by rank (attach smaller tree under bigger)
     *    make operations almost constant-time in practice.
     */
    public static class DisjointSet {
        int[] parent;                                     // parent[i] is parent of i; root has parent[i] == i
        int[] rank;                                       // rank approximates tree height (or size surrogate)

        /** Create n singleton sets labeled 0..n-1. */
        public DisjointSet(int n) {
            parent = new int[n];                          // allocate parent array
            rank = new int[n];                            // allocate rank array (starts at 0)
            for (int i = 0; i < n; i++) parent[i] = i;    // each element is initially a root
        }

        /** Find the representative (root) of x’s set with path compression. */
        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x])); // compress path on the way back
        }

        /**
         * Merge sets containing a and b.
         * @return false if a and b were already in the same set; true if a merge happened.
         */
        public boolean unite(int a, int b) {
            a = find(a);                                  // get root of a
            b = find(b);                                  // get root of b
            if (a == b) return false;                     // already same set → nothing to do
            if (rank[a] < rank[b]) { int t = a; a = b; b = t; } // ensure a has >= rank
            parent[b] = a;                                // attach b under a
            if (rank[a] == rank[b]) rank[a]++;            // increase rank if both equal
            return true;                                  // merged
        }
    }

    /**
     * Problem: Minimum Spanning Tree (MST) total weight in an undirected weighted graph.
     * If the graph is disconnected (cannot connect all nodes), return -1.
     *
     * Inputs:
     *  - n: number of nodes (0..n-1)
     *  - edges: array of {u, v, w} meaning an undirected edge u--v with weight w
     *
     * Output:
     *  - total weight of a minimum spanning tree, or -1 if no spanning tree exists.
     *
     * Approach (Kruskal + Union-Find):
     *  - Sort edges by weight ascending.
     *  - Scan edges; if u and v are in different sets, unite them and add weight.
     *  - Stop after taking n-1 edges, otherwise disconnected.
     */
    public static int minimumSpanningTreeKruskal(int n, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2])); // sort edges by weight
        DisjointSet d = new DisjointSet(n);                     // create DSU for n nodes
        int total = 0,                                          // total MST weight so far
            used = 0;                                           // number of edges chosen
        for (int[] e : edges) {                                 // examine edges lightest-first
            if (d.unite(e[0], e[1])) {                          // if endpoints were separate, we can connect them
                total += e[2];                                  // add edge weight
                if (++used == n - 1) break;                     // once n-1 edges chosen, MST complete
            }
        }
        return used == n - 1 ? total : -1;                      // disconnected if we chose fewer than n-1 edges
    }

    // ---------------------------------------------------------------------
    // 12) DIJKSTRA — SHORTEST PATH WITH NON-NEGATIVE EDGE WEIGHTS
    // ---------------------------------------------------------------------

    /**
     * Problem: Given a directed graph with non-negative edge weights, compute the
     * shortest path distance from a source to every node.
     *
     * Inputs:
     *  - n: number of nodes (0..n-1)
     *  - g: adjacency list where g[u] contains pairs {v, w} for edges u->v with weight w (w >= 0)
     *  - src: source node
     *
     * Output:
     *  - int[] dist where dist[v] is the minimum distance from src to v; a large value means unreachable.
     *
     * Approach (Dijkstra with a min-heap / priority queue):
     *  - Keep best-known distances; repeatedly expand the node with the smallest tentative distance.
     *  - For each neighbor, relax the distance if we found a shorter path.
     *
     * Complexity:
     *  - Time: each edge can cause a decrease; priority queue handles minimum extraction efficiently.
     *  - Space: arrays for distances and visited flags.
     */
    public static int[] dijkstra(int n, List<List<int[]>> g, int src) {
        final int INF = 1_000_000_000;                     // sentinel for "unreachable"
        int[] dist = new int[n];                           // best known distances
        Arrays.fill(dist, INF);                            // initialize as unreachable
        dist[src] = 0;                                     // distance to source is 0
        PriorityQueue<int[]> pq =                         // min-heap by distance
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});                       // start from source with distance 0
        boolean[] vis = new boolean[n];                    // visited marker (finalized distances)
        while (!pq.isEmpty()) {                            // process nodes in order of current distance
            int[] cur = pq.poll();                         // take the node with smallest tentative dist
            int u = cur[0], du = cur[1];                   // u = node id, du = distance we popped with
            if (vis[u]) continue;                          // skip if we've already finalized u
            vis[u] = true;                                 // mark u finalized
            for (int[] e : g.get(u)) {                     // relax edges out of u
                int v = e[0], w = e[1];                    // v = neighbor, w = weight
                if (du + w < dist[v]) {                    // found a shorter path to v
                    dist[v] = du + w;                      // update best distance
                    pq.offer(new int[]{v, dist[v]});       // push updated distance to priority queue
                }
            }
        }
        return dist;                                       // final distances array
    }

    // ---------------------------------------------------------------------
    // 13) KADANE — MAXIMUM SUBARRAY SUM
    // ---------------------------------------------------------------------

    /**
     * Problem: Given an integer array (may have negatives), find the maximum possible
     * sum of any non-empty contiguous subarray.
     *
     * Inputs:
     *  - a: integer array (size >= 1).
     *
     * Output:
     *  - best (integer) sum among all contiguous subarrays.
     *
     * Approach (Kadane's idea):
     *  - curr = max(current element, curr + current element)  // best sum ending at this index
     *  - best = max(best, curr)                               // overall best so far
     *
     * Complexity:
     *  - Time: linear.
     *  - Space: constant.
     */
    public static int kadane(int[] a) {
        int best = a[0];                                   // best sum seen so far
        int curr = a[0];                                   // best sum ending at current position
        for (int i = 1; i < a.length; i++) {               // scan from second element
            curr = Math.max(a[i], curr + a[i]);            // either start new subarray here or extend previous
            best = Math.max(best, curr);                   // update global best
        }
        return best;                                       // answer
    }

    // ---------------------------------------------------------------------
    // 14) LONGEST INCREASING SUBSEQUENCE (N LOG N) — LENGTH ONLY
    // ---------------------------------------------------------------------

    /**
     * Problem: Return the length of the longest strictly increasing subsequence (not
     * necessarily contiguous) in the array.
     *
     * Inputs:
     *  - a: integer array.
     *
     * Output:
     *  - length (integer) of the longest increasing subsequence.
     *
     * Approach (Patience sorting idea with binary search):
     *  - t[len] stores the smallest tail value of any increasing subsequence of length len+1.
     *  - For each x, find the first position i where t[i] >= x and set t[i] = x.
     *  - The number of filled positions in t equals the LIS length.
     *
     * Complexity:
     *  - Time: each element uses a binary search → logarithmic per element.
     *  - Space: up to n for table t.
     */
    public static int lis(int[] a) {
        int[] t = new int[a.length];                       // tails table
        int len = 0;                                       // current LIS length
        for (int x : a) {                                  // process each element
            int i = Arrays.binarySearch(t, 0, len, x);     // find insertion point for x
            if (i < 0) i = -i - 1;                         // if not found, binarySearch returns -(pos+1)
            t[i] = x;                                      // set the smallest tail for length i+1
            if (i == len) len++;                           // if extended the longest, increase length
        }
        return len;                                        // return LIS length
    }

    // ---------------------------------------------------------------------
    // 15) EDIT DISTANCE (LEVENSHTEIN) — MINIMUM EDITS
    // ---------------------------------------------------------------------

    /**
     * Problem: Given two strings s and t, compute the minimum number of single-character
     * edits (insert, delete, replace) needed to transform s into t.
     *
     * Inputs:
     *  - s: source string
     *  - t: target string
     *
     * Output:
     *  - the fewest number of edits to change s into t.
     *
     * Approach (Dynamic Programming):
     *  - dp[i][j] = answer for s[0..i) vs t[0..j).
     *  - If last characters equal → dp[i][j] = dp[i-1][j-1].
     *  - Else dp[i][j] = 1 + min(replace, delete, insert).
     *
     * Complexity:
     *  - Time: proportional to (length of s) × (length of t).
     *  - Space: same as table size (can be optimized to two rows if needed).
     */
    public static int editDistance(String s, String t) {
        int n = s.length(), m = t.length();                // lengths of s and t
        int[][] dp = new int[n + 1][m + 1];                // DP table
        for (int i = 0; i <= n; i++) dp[i][0] = i;         // i deletions to convert s[0..i) to ""
        for (int j = 0; j <= m; j++) dp[0][j] = j;         // j insertions to convert "" to t[0..j)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {  // last characters equal
                    dp[i][j] = dp[i - 1][j - 1];           // carry over
                } else {                                   // choose best of replace, delete, insert
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[n][m];                                    // final answer
    }

    // ---------------------------------------------------------------------
    // 16) BACKTRACKING — ALL PERMUTATIONS
    // ---------------------------------------------------------------------

    /**
     * Problem: Return all permutations (all orderings) of the input array.
     * Warning: number of permutations grows very quickly (n!); use small n.
     *
     * Inputs:
     *  - a: integer array
     *
     * Output:
     *  - list of permutations, where each permutation is a list of integers.
     *
     * Approach (Backtracking = choose → explore → undo):
     *  - Use a boolean[] to track which indices are already used.
     *  - Build a current list, add an unused element, recurse, then remove it (undo).
     */
    public static List<List<Integer>> permute(int[] a) {
        List<List<Integer>> res = new ArrayList<>();       // will hold all permutations
        boolean[] used = new boolean[a.length];            // marks which indices are in current permutation
        backtrackPerm(a, used, new ArrayList<>(), res);    // start recursion with empty current list
        return res;
    }

    // Helper for permutations: the recursive function that builds permutations.
    private static void backtrackPerm(int[] a, boolean[] used, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == a.length) {                      // if current permutation uses all elements
            res.add(new ArrayList<>(cur));                 // add a copy to results
            return;                                        // and backtrack
        }
        for (int i = 0; i < a.length; i++) {               // try each index i
            if (used[i]) continue;                         // skip if already used
            used[i] = true;                                // choose a[i]
            cur.add(a[i]);                                 // put a[i] into current permutation
            backtrackPerm(a, used, cur, res);              // explore deeper
            cur.remove(cur.size() - 1);                    // undo: remove last
            used[i] = false;                               // undo: mark i as unused
        }
    }

    // ---------------------------------------------------------------------
    // Helpers to build small graphs in tests
    // ---------------------------------------------------------------------

    /**
     * Create an empty adjacency list with n nodes (0..n-1).
     */
    public static List<List<Integer>> emptyAdjList(int n) {
        List<List<Integer>> g = new ArrayList<>(n);        // allocate outer list
        for (int i = 0; i < n; i++) g.add(new ArrayList<>()); // each node gets its own neighbor list
        return g;
    }

    /**
     * Add a directed edge u -> v to adjacency list g.
     */
    public static void addEdge(List<List<Integer>> g, int u, int v) {
        g.get(u).add(v);                                   // append v as neighbor of u
    }

    // ---------------------------------------------------------------------
    // TESTS — each test method has JavaDoc and runs 3 cases
    // ---------------------------------------------------------------------

    /** Tests removeDuplicates with three scenarios: mixed duplicates, all same, all unique. */
    private static void testRemoveDuplicates() {
        System.out.println("\n== removeDuplicates ==");
        int[] a1 = {1,1,2,2,2,3};
        int len1 = removeDuplicates(a1);                   // expect 3 with [1,2,3]
        assertEquals("case1 length", 3, len1);
        assertArrayEquals("case1 array", new int[]{1,2,3}, Arrays.copyOf(a1, len1));

        int[] a2 = {1,1,1,1};
        int len2 = removeDuplicates(a2);                   // expect 1 with [1]
        assertEquals("case2 length", 1, len2);
        assertArrayEquals("case2 array", new int[]{1}, Arrays.copyOf(a2, len2));

        int[] a3 = {1,2,3,4};
        int len3 = removeDuplicates(a3);                   // expect 4 unchanged
        assertEquals("case3 length", 4, len3);
        assertArrayEquals("case3 array", new int[]{1,2,3,4}, Arrays.copyOf(a3, len3));
    }

    /** Tests lengthOfLongestSubstring: typical, all same char, and mixed with repeats. */
    private static void testLengthOfLongestSubstring() {
        System.out.println("\n== lengthOfLongestSubstring ==");
        assertEquals("case1", 3, lengthOfLongestSubstring("abcabcbb")); // "abc"
        assertEquals("case2", 1, lengthOfLongestSubstring("bbbb"));     // "b"
        assertEquals("case3", 3, lengthOfLongestSubstring("pwwkew"));   // "wke"
    }

    /** Tests isAnagram: positive case, negative case, and counting case with repeats. */
    private static void testIsAnagram() {
        System.out.println("\n== isAnagram ==");
        assertEquals("case1", true, isAnagram("anagram","nagaram"));
        assertEquals("case2", false, isAnagram("rat","car"));
        assertEquals("case3", true, isAnagram("aabbcc","abcabc"));
    }

    /** Tests lowerBound with present target, gap target, and target beyond array end. */
    private static void testLowerBound() {
        System.out.println("\n== lowerBound ==");
        int[] a = {1,3,3,5};
        assertEquals("case1", 1, lowerBound(a,3));
        assertEquals("case2", 3, lowerBound(a,4));
        assertEquals("case3", 4, lowerBound(a,6));
    }

    /** Tests minEatingSpeed with standard LeetCode-like examples. */
    private static void testMinEatingSpeed() {
        System.out.println("\n== minEatingSpeed ==");
        assertEquals("case1", 4, minEatingSpeed(new int[]{3,6,7,11}, 8));
        assertEquals("case2", 30, minEatingSpeed(new int[]{30,11,23,4,20}, 5));
        assertEquals("case3", 23, minEatingSpeed(new int[]{30,11,23,4,20}, 6));
    }

    /** Tests mergeIntervals: overlapping, touching, and disjoint examples. */
    private static void testMergeIntervals() {
        System.out.println("\n== mergeIntervals ==");
        int[][] in1 = {{1,3},{2,6},{8,10}};  int[][] ex1 = {{1,6},{8,10}};
        assert2DArrayEquals("case1", ex1, mergeIntervals(in1));

        int[][] in2 = {{1,4},{4,5}};         int[][] ex2 = {{1,5}};       // touching => merged
        assert2DArrayEquals("case2", ex2, mergeIntervals(in2));

        int[][] in3 = {{1,2},{3,4}};         int[][] ex3 = {{1,2},{3,4}}; // no overlap
        assert2DArrayEquals("case3", ex3, mergeIntervals(in3));
    }

    /** Tests nextGreaterIndex: case with later greater, multiple jumps, and no greater. */
    private static void testNextGreaterIndex() {
        System.out.println("\n== nextGreaterIndex ==");
        assertArrayEquals("case1", new int[]{2,2,-1}, nextGreaterIndex(new int[]{2,1,3}));
        assertArrayEquals("case2", new int[]{1,3,3,-1}, nextGreaterIndex(new int[]{1,5,2,6}));
        assertArrayEquals("case3", new int[]{-1,-1,-1}, nextGreaterIndex(new int[]{3,2,1}));
    }

    /** Tests kthLargest with small arrays including duplicates and single-element case. */
    private static void testKthLargest() {
        System.out.println("\n== kthLargest ==");
        assertEquals("case1", 5, kthLargest(new int[]{3,2,1,5,6,4}, 2));
        assertEquals("case2", 4, kthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        assertEquals("case3", 1, kthLargest(new int[]{1}, 1));
    }

    /** Tests shortestUnweighted (BFS) with reachable, chain, and unreachable cases. */
    private static void testShortestUnweighted() {
        System.out.println("\n== shortestUnweighted (BFS) ==");
        int n = 4;
        List<List<Integer>> g1 = emptyAdjList(n);
        addEdge(g1,0,1); addEdge(g1,0,2); addEdge(g1,1,3); addEdge(g1,2,3);
        assertEquals("case1", 2, shortestUnweighted(n, g1, 0, 3));

        List<List<Integer>> g2 = emptyAdjList(3);
        addEdge(g2,0,1); addEdge(g2,1,2);
        assertEquals("case2", 2, shortestUnweighted(3, g2, 0, 2));

        List<List<Integer>> g3 = emptyAdjList(3);
        addEdge(g3,0,1); // 2 is isolated
        assertEquals("case3", -1, shortestUnweighted(3, g3, 2, 0));
    }

    /** Tests topologicalOrder with chain, custom order, and small DAG. */
    private static void testTopologicalOrder() {
        System.out.println("\n== topologicalOrder (Kahn) ==");
        // Chain graph: 0->1->2->3
        List<List<Integer>> g1 = emptyAdjList(4);
        addEdge(g1,0,1); addEdge(g1,1,2); addEdge(g1,2,3);
        assertEquals("case1", Arrays.asList(0,1,2,3), topologicalOrder(4, g1));

        // A DAG with a specific order: 0->2->3->1->4
        List<List<Integer>> g2 = emptyAdjList(5);
        addEdge(g2,0,2); addEdge(g2,2,3); addEdge(g2,3,1); addEdge(g2,1,4);
        assertEquals("case2", Arrays.asList(0,2,3,1,4), topologicalOrder(5, g2));

        // Small DAG: 2->0->1
        List<List<Integer>> g3 = emptyAdjList(3);
        addEdge(g3,2,0); addEdge(g3,0,1);
        assertEquals("case3", Arrays.asList(2,0,1), topologicalOrder(3, g3));
    }

    /** Tests minimumSpanningTreeKruskal with normal, bigger, and disconnected graphs. */
    private static void testMinimumSpanningTreeKruskal() {
        System.out.println("\n== minimumSpanningTreeKruskal ==");
        int[][] edges1 = {{0,1,1},{1,2,2},{2,3,3},{0,3,10}};
        assertEquals("case1", 6, minimumSpanningTreeKruskal(4, edges1));

        int[][] edges2 = {{0,1,4},{0,2,3},{1,2,1},{1,3,2},{2,3,4},{3,4,2},{4,5,6}};
        assertEquals("case2", 14, minimumSpanningTreeKruskal(6, edges2));

        int[][] edges3 = {{0,1,1},{2,3,1}}; // disconnected components
        assertEquals("case3", -1, minimumSpanningTreeKruskal(4, edges3));
    }

    /** Tests dijkstra with simple path, longer chain, and an unreachable node. */
    private static void testDijkstra() {
        System.out.println("\n== dijkstra ==");
        // Graph 1
        List<List<int[]>> g1 = new ArrayList<>();
        for (int i=0;i<3;i++) g1.add(new ArrayList<>());
        g1.get(0).add(new int[]{1,2}); g1.get(0).add(new int[]{2,5});
        g1.get(1).add(new int[]{2,1});
        assertArrayEquals("case1", new int[]{0,2,3}, dijkstra(3, g1, 0));

        // Graph 2
        List<List<int[]>> g2 = new ArrayList<>();
        for (int i=0;i<4;i++) g2.add(new ArrayList<>());
        g2.get(0).add(new int[]{1,1}); g2.get(1).add(new int[]{2,1}); g2.get(2).add(new int[]{3,1});
        assertArrayEquals("case2", new int[]{0,1,2,3}, dijkstra(4, g2, 0));

        // Graph 3 with unreachable node
        List<List<int[]>> g3 = new ArrayList<>();
        for (int i=0;i<3;i++) g3.add(new ArrayList<>());
        g3.get(0).add(new int[]{1,10});
        int INF = 1_000_000_000;
        int[] out = dijkstra(3, g3, 0);
        boolean ok = out[0]==0 && out[1]==10 && out[2]==INF;
        assertEquals("case3", true, ok);
    }

    /** Tests kadane with mixed, small positive, and all negative arrays. */
    private static void testKadane() {
        System.out.println("\n== kadane ==");
        assertEquals("case1", 6, kadane(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        assertEquals("case2", 1, kadane(new int[]{-2,1}));
        assertEquals("case3", -1, kadane(new int[]{-3,-1,-2}));
    }

    /** Tests lis (length only) with standard, all equal, and small variant arrays. */
    private static void testLIS() {
        System.out.println("\n== lis ==");
        assertEquals("case1", 4, lis(new int[]{10,9,2,5,3,7,101,18}));
        assertEquals("case2", 1, lis(new int[]{5,5,5}));
        assertEquals("case3", 3, lis(new int[]{1,2,4,3}));
    }

    /** Tests editDistance with standard example, one deletion, and identical strings. */
    private static void testEditDistance() {
        System.out.println("\n== editDistance ==");
        assertEquals("case1", 3, editDistance("horse","ros"));
        assertEquals("case2", 1, editDistance("ab","a"));
        assertEquals("case3", 0, editDistance("same","same"));
    }

    /** Tests permute: length check and membership checks for small arrays. */
    private static void testPermute() {
        System.out.println("\n== permute ==");
        List<List<Integer>> r1 = permute(new int[]{1,2,3});
        assertEquals("case1 count", 6, r1.size());
        assertEquals("case1 contains [1,2,3]", true, r1.contains(Arrays.asList(1,2,3)));
        assertEquals("case1 contains [2,1,3]", true, r1.contains(Arrays.asList(2,1,3)));

        List<List<Integer>> r2 = permute(new int[]{1,2});
        assertEquals("case2 count", 2, r2.size());
        assertEquals("case2 contains [2,1]", true, r2.contains(Arrays.asList(2,1)));

        List<List<Integer>> r3 = permute(new int[]{7});
        assertEquals("case3 count", 1, r3.size());
        assertEquals("case3 contains [7]", true, r3.contains(Arrays.asList(7)));
    }

    // ---------------------------------------------------------------------
    // MAIN — runs every test method
    // ---------------------------------------------------------------------

    /**
     * Runs all test suites. Each suite prints three test cases.
     * Visual inspection is enough during practice; add JUnit later if desired.
     */
    public static void main(String[] args) {
        testRemoveDuplicates();
        testLengthOfLongestSubstring();
        testIsAnagram();
        testLowerBound();
        testMinEatingSpeed();
        testMergeIntervals();
        testNextGreaterIndex();
        testKthLargest();
        testShortestUnweighted();
        testTopologicalOrder();
        testMinimumSpanningTreeKruskal();
        testDijkstra();
        testKadane();
        testLIS();
        testEditDistance();
        testPermute();
        System.out.println("\nAll tests executed.");
    }
}
