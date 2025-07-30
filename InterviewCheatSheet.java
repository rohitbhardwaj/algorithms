/**
 * InterviewCheatSheet.java
 *
 * Now includes test methods for each algorithm. Each test method runs 3 cases
 * and reports PASS/FAIL with clear expected vs actual outputs.
 *
 * Compile & run:
 *   javac InterviewCheatSheet.java && java InterviewCheatSheet
 */
import java.util.*;
import java.util.function.*;

public class InterviewCheatSheet {

    // ==============================
    // Algorithms (same as before)
    // ==============================

    public static int removeDuplicates(int[] a) {
        int w = 0;
        for (int r = 0; r < a.length; r++) {
            if (w == 0 || a[r] != a[w - 1]) a[w++] = a[r];
        }
        return w;
    }

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

    public static int lowerBound(int[] a, int target) {
        int lo = 0, hi = a.length; // [lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= target) hi = mid; else lo = mid + 1;
        }
        return lo;
    }

    public static int minEatingSpeed(int[] piles, int hours) {
        int lo = 1, hi = Arrays.stream(piles).max().getAsInt();
        IntPredicate ok = v -> {
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

    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> out = new ArrayList<>();
        for (int[] cur : intervals) {
            if (out.isEmpty() || out.get(out.size() - 1)[1] < cur[0]) out.add(cur.clone());
            else out.get(out.size() - 1)[1] = Math.max(out.get(out.size() - 1)[1], cur[1]);
        }
        return out.toArray(new int[0][]);
    }

    public static int[] nextGreaterIndex(int[] a) {
        int n = a.length, res[] = new int[n]; Arrays.fill(res, -1);
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && a[i] > a[st.peek()]) res[st.pop()] = i;
            st.push(i);
        }
        return res;
    }

    public static int kthLargest(int[] a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : a) {
            pq.offer(x);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }

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

    public static int kadane(int[] a) {
        int best = a[0], cur = a[0];
        for (int i = 1; i < a.length; i++) {
            cur = Math.max(a[i], cur + a[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

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

    // Helpers to build graphs
    public static List<List<Integer>> emptyAdjList(int n) {
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        return g;
    }
    public static void addEdge(List<List<Integer>> g, int u, int v) { g.get(u).add(v); }

    // ==============================
    // Simple test helpers
    // ==============================
    private static void assertEquals(String name, Object exp, Object act) {
        if (Objects.equals(exp, act)) {
            System.out.println("PASS - " + name + " => " + exp);
        } else {
            System.out.println("FAIL - " + name + " expected: " + exp + " but got: " + act);
        }
    }
    private static void assertArrayEquals(String name, int[] exp, int[] act) {
        if (Arrays.equals(exp, act)) {
            System.out.println("PASS - " + name + " => " + Arrays.toString(act));
        } else {
            System.out.println("FAIL - " + name + " expected: " + Arrays.toString(exp) + " but got: " + Arrays.toString(act));
        }
    }
    private static void assert2DArrayEquals(String name, int[][] exp, int[][] act) {
        if (Arrays.deepEquals(toObj(exp), toObj(act))) {
            System.out.println("PASS - " + name + " => " + Arrays.deepToString(act));
        } else {
            System.out.println("FAIL - " + name + " expected: " + Arrays.deepToString(exp) + " but got: " + Arrays.deepToString(act));
        }
    }
    private static Integer[][] toObj(int[][] a) {
        Integer[][] r = new Integer[a.length][];
        for (int i = 0; i < a.length; i++) {
            r[i] = Arrays.stream(a[i]).boxed().toArray(Integer[]::new);
        }
        return r;
    }

    // ==============================
    // Tests for each algorithm (3 cases each)
    // ==============================

    private static void testRemoveDuplicates() {
        System.out.println("\n== removeDuplicates ==");
        int[] a1 = {1,1,2,2,2,3}; int len1 = removeDuplicates(a1);
        assertEquals("case1 length", 3, len1); assertArrayEquals("case1 array", new int[]{1,2,3}, Arrays.copyOf(a1, len1));

        int[] a2 = {1,1,1,1}; int len2 = removeDuplicates(a2);
        assertEquals("case2 length", 1, len2); assertArrayEquals("case2 array", new int[]{1}, Arrays.copyOf(a2, len2));

        int[] a3 = {1,2,3,4}; int len3 = removeDuplicates(a3);
        assertEquals("case3 length", 4, len3); assertArrayEquals("case3 array", new int[]{1,2,3,4}, Arrays.copyOf(a3, len3));
    }

    private static void testLengthOfLongestSubstring() {
        System.out.println("\n== lengthOfLongestSubstring ==");
        assertEquals("case1", 3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals("case2", 1, lengthOfLongestSubstring("bbbb"));
        assertEquals("case3", 3, lengthOfLongestSubstring("pwwkew")); // "wke"
    }

    private static void testIsAnagram() {
        System.out.println("\n== isAnagram ==");
        assertEquals("case1", true, isAnagram("anagram","nagaram"));
        assertEquals("case2", false, isAnagram("rat","car"));
        assertEquals("case3", true, isAnagram("aabbcc","abcabc"));
    }

    private static void testLowerBound() {
        System.out.println("\n== lowerBound ==");
        int[] a = {1,3,3,5};
        assertEquals("case1", 1, lowerBound(a,3));
        assertEquals("case2", 3, lowerBound(a,4));
        assertEquals("case3", 4, lowerBound(a,6));
    }

    private static void testMinEatingSpeed() {
        System.out.println("\n== minEatingSpeed ==");
        assertEquals("case1", 4, minEatingSpeed(new int[]{3,6,7,11}, 8));
        assertEquals("case2", 30, minEatingSpeed(new int[]{30,11,23,4,20}, 5));
        assertEquals("case3", 23, minEatingSpeed(new int[]{30,11,23,4,20}, 6));
    }

    private static void testMergeIntervals() {
        System.out.println("\n== mergeIntervals ==");
        int[][] in1 = {{1,3},{2,6},{8,10}};
        int[][] ex1 = {{1,6},{8,10}};
        assert2DArrayEquals("case1", ex1, mergeIntervals(in1));

        int[][] in2 = {{1,4},{4,5}};
        int[][] ex2 = {{1,5}};
        assert2DArrayEquals("case2", ex2, mergeIntervals(in2));

        int[][] in3 = {{1,2},{3,4}};
        int[][] ex3 = {{1,2},{3,4}};
        assert2DArrayEquals("case3", ex3, mergeIntervals(in3));
    }

    private static void testNextGreaterIndex() {
        System.out.println("\n== nextGreaterIndex ==");
        assertArrayEquals("case1", new int[]{2,2,-1}, nextGreaterIndex(new int[]{2,1,3}));
        assertArrayEquals("case2", new int[]{1,3,3,-1}, nextGreaterIndex(new int[]{1,5,2,6}));
        assertArrayEquals("case3", new int[]{-1,-1,-1}, nextGreaterIndex(new int[]{3,2,1}));
    }

    private static void testKthLargest() {
        System.out.println("\n== kthLargest ==");
        assertEquals("case1", 5, kthLargest(new int[]{3,2,1,5,6,4}, 2));
        assertEquals("case2", 4, kthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        assertEquals("case3", 1, kthLargest(new int[]{1}, 1));
    }

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
        addEdge(g3,0,1);
        assertEquals("case3", -1, shortestUnweighted(3, g3, 2, 0));
    }

    private static void testTopologicalOrder() {
        System.out.println("\n== topologicalOrder (Kahn) ==");
        // Graph with unique order [0,1,2,3]
        List<List<Integer>> g1 = emptyAdjList(4);
        addEdge(g1,0,1); addEdge(g1,1,2); addEdge(g1,2,3);
        assertEquals("case1", Arrays.asList(0,1,2,3), topologicalOrder(4, g1));

        // Another unique order [0,2,3,1,4]
        List<List<Integer>> g2 = emptyAdjList(5);
        addEdge(g2,0,2); addEdge(g2,2,3); addEdge(g2,3,1); addEdge(g2,1,4);
        assertEquals("case2", Arrays.asList(0,2,3,1,4), topologicalOrder(5, g2));

        // DAG with one component chain [0,1] and isolated node 2 -> we make order unique by 2->0
        List<List<Integer>> g3 = emptyAdjList(3);
        addEdge(g3,2,0); addEdge(g3,0,1);
        assertEquals("case3", Arrays.asList(2,0,1), topologicalOrder(3, g3));
    }

    private static void testMinimumSpanningTreeKruskal() {
        System.out.println("\n== minimumSpanningTreeKruskal ==");
        int[][] edges1 = {{0,1,1},{1,2,2},{2,3,3},{0,3,10}};
        assertEquals("case1", 6, minimumSpanningTreeKruskal(4, edges1));

        int[][] edges2 = {{0,1,4},{0,2,3},{1,2,1},{1,3,2},{2,3,4},{3,4,2},{4,5,6}};
        assertEquals("case2", 14, minimumSpanningTreeKruskal(6, edges2));

        // Disconnected graph -> -1
        int[][] edges3 = {{0,1,1},{2,3,1}};
        assertEquals("case3", -1, minimumSpanningTreeKruskal(4, edges3));
    }

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

    private static void testKadane() {
        System.out.println("\n== kadane ==");
        assertEquals("case1", 6, kadane(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        assertEquals("case2", 1, kadane(new int[]{-2,1}));
        assertEquals("case3", -1, kadane(new int[]{-3,-1,-2}));
    }

    private static void testLIS() {
        System.out.println("\n== lis ==");
        assertEquals("case1", 4, lis(new int[]{10,9,2,5,3,7,101,18}));
        assertEquals("case2", 1, lis(new int[]{5,5,5}));
        assertEquals("case3", 3, lis(new int[]{1,2,4,3}));
    }

    private static void testEditDistance() {
        System.out.println("\n== editDistance ==");
        assertEquals("case1", 3, editDistance("horse","ros"));
        assertEquals("case2", 1, editDistance("ab","a"));
        assertEquals("case3", 0, editDistance("same","same"));
    }

    private static void testPermute() {
        System.out.println("\n== permute ==");
        List<List<Integer>> r1 = permute(new int[]{1,2,3});
        assertEquals("case1 count", 6, r1.size());
        // Check presence of a couple permutations
        assertEquals("case1 contains [1,2,3]", true, r1.contains(Arrays.asList(1,2,3)));
        assertEquals("case1 contains [2,1,3]", true, r1.contains(Arrays.asList(2,1,3)));

        List<List<Integer>> r2 = permute(new int[]{1,2});
        assertEquals("case2 count", 2, r2.size());
        assertEquals("case2 contains [2,1]", true, r2.contains(Arrays.asList(2,1)));

        List<List<Integer>> r3 = permute(new int[]{7});
        assertEquals("case3 count", 1, r3.size());
        assertEquals("case3 contains [7]", true, r3.contains(Arrays.asList(7)));
    }

    // ==============================
    // MAIN: run all tests
    // ==============================
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
