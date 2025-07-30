/**
 * InterviewCheatSheet.java
 *
 * A student-friendly collection of clear, well-documented Java templates
 * for common coding-interview patterns. Each method has:
 *  - A plain-English description (no unexplained acronyms).
 *  - Inputs and outputs explained in detail.
 *  - When to use the pattern (recognition cues).
 *  - Step-by-step idea.
 *  - Time and space complexity described without symbolic notation.
 *  - A short worked example with expected output.
 *
 * You can compile and run this file directly:
 *   javac InterviewCheatSheet.java && java InterviewCheatSheet
 */
import java.util.*;
import java.util.function.*;

public class InterviewCheatSheet {

    // 1) Two Pointers — Remove Duplicates from a Sorted Array
    public static int removeDuplicates(int[] a) {
        int w = 0;
        for (int r = 0; r < a.length; r++) {
            if (w == 0 || a[r] != a[w - 1]) a[w++] = a[r];
        }
        return w;
    }

    // 2) Sliding Window — Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> last = new HashMap<>();
        int l = 0, ans = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (last.containsKey(c) && last.get(c) >= l) l = last.get(c) + 1;
            last.put(c, r);
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    // 3) Character Counting — Anagram Check
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[t.charAt(i) - 'a']--;
        }
        for (int x : cnt) if (x != 0) return false;
        return true;
    }

    // 4) Binary Search (Index) — First Position Not Less Than Target
    public static int lowerBound(int[] a, int target) {
        int lo = 0, hi = a.length; // [lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= target) hi = mid; else lo = mid + 1;
        }
        return lo;
    }

    // 5) Binary Search on the Answer — Minimum Eating Speed
    public static int minEatingSpeed(int[] piles, int hours) {
        int lo = 1, hi = Arrays.stream(piles).max().getAsInt();
        java.util.function.IntPredicate ok = v -> {
            long needed = 0;
            for (int p : piles) needed += (p + v - 1) / v; // ceiling
            return needed <= hours;
        };
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (ok.test(mid)) hi = mid; else lo = mid + 1;
        }
        return lo;
    }

    // 6) Intervals — Merge Overlapping Ranges
    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> out = new ArrayList<>();
        for (int[] cur : intervals) {
            if (out.isEmpty() || out.get(out.size() - 1)[1] < cur[0]) out.add(cur.clone());
            else out.get(out.size() - 1)[1] = Math.max(out.get(out.size() - 1)[1], cur[1]);
        }
        return out.toArray(new int[0][]);
    }

    // 7) Monotonic Stack — Next Greater Element (Index)
    public static int[] nextGreaterIndex(int[] a) {
        int n = a.length, res[] = new int[n]; Arrays.fill(res, -1);
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && a[i] > a[st.peek()]) res[st.pop()] = i;
            st.push(i);
        }
        return res;
    }

    // 8) Heap — Kth Largest
    public static int kthLargest(int[] a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : a) {
            pq.offer(x);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }

    // 9) Graph — Shortest Path in Unweighted Graph (BFS)
    public static int shortestUnweighted(int n, List<List<Integer>> g, int src, int dst) {
        int[] dist = new int[n]; Arrays.fill(dist, -1);
        Deque<Integer> q = new ArrayDeque<>();
        q.add(src); dist[src] = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == dst) return dist[u];
            for (int v : g.get(u)) if (dist[v] == -1) { dist[v] = dist[u] + 1; q.add(v); }
        }
        return -1;
    }

    // 10) Topological Order (Kahn)
    public static List<Integer> topologicalOrder(int n, List<List<Integer>> g) {
        int[] indeg = new int[n];
        for (int u = 0; u < n; u++) for (int v : g.get(u)) indeg[v]++;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll(); order.add(u);
            for (int v : g.get(u)) if (--indeg[v] == 0) q.add(v);
        }
        return order.size() == n ? order : List.of();
    }

    // 11) Disjoint Set (Union-Find) + Kruskal
    public static class DisjointSet {
        int[] parent, rank;
        public DisjointSet(int n) {
            parent = new int[n]; rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
        public boolean unite(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;
            if (rank[a] < rank[b]) { int t = a; a = b; b = t; }
            parent[b] = a; if (rank[a] == rank[b]) rank[a]++;
            return true;
        }
    }
    public static int minimumSpanningTreeKruskal(int n, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        DisjointSet d = new DisjointSet(n);
        int total = 0, used = 0;
        for (int[] e : edges) if (d.unite(e[0], e[1])) { total += e[2]; if (++used == n - 1) break; }
        return used == n - 1 ? total : -1;
    }

    // 12) Dijkstra — Shortest Path with Non-Negative Weights
    public static int[] dijkstra(int n, List<List<int[]>> g, int src) {
        int INF = 1_000_000_000;
        int[] dist = new int[n]; Arrays.fill(dist, INF); dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});
        boolean[] vis = new boolean[n];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); int u = cur[0], du = cur[1];
            if (vis[u]) continue; vis[u] = true;
            for (int[] e : g.get(u)) {
                int v = e[0], w = e[1];
                if (du + w < dist[v]) { dist[v] = du + w; pq.offer(new int[]{v, dist[v]}); }
            }
        }
        return dist;
    }

    // 13) Kadane — Maximum Subarray Sum
    public static int kadane(int[] a) {
        int best = a[0], cur = a[0];
        for (int i = 1; i < a.length; i++) {
            cur = Math.max(a[i], cur + a[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

    // 14) Longest Increasing Subsequence (n log n)
    public static int lis(int[] a) {
        int[] t = new int[a.length]; int len = 0;
        for (int x : a) {
            int i = Arrays.binarySearch(t, 0, len, x);
            if (i < 0) i = -i - 1;
            t[i] = x;
            if (i == len) len++;
        }
        return len;
    }

    // 15) Edit Distance (Levenshtein)
    public static int editDistance(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                dp[i][j] = (s.charAt(i - 1) == t.charAt(j - 1)) ? dp[i - 1][j - 1]
                        : 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
        return dp[n][m];
    }

    // 16) Backtracking — All Permutations
    public static List<List<Integer>> permute(int[] a) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[a.length];
        backtrackPerm(a, used, new ArrayList<>(), res);
        return res;
    }
    private static void backtrackPerm(int[] a, boolean[] used, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == a.length) { res.add(new ArrayList<>(cur)); return; }
        for (int i = 0; i < a.length; i++) {
            if (used[i]) continue;
            used[i] = true; cur.add(a[i]);
            backtrackPerm(a, used, cur, res);
            cur.remove(cur.size() - 1); used[i] = false;
        }
    }

    // Helpers
    public static List<List<Integer>> emptyAdjList(int n) {
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        return g;
    }
    public static void addEdge(List<List<Integer>> g, int u, int v) { g.get(u).add(v); }

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,2,3};
        int len = removeDuplicates(arr);
        System.out.println("Unique length: " + len + " -> " + java.util.Arrays.toString(java.util.Arrays.copyOf(arr, len)));
    }
}
