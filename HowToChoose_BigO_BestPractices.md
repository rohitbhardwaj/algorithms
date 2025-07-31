# How to Choose the Right Approach, Big‑O Cheat Sheet, and Best Practices (Java‑Friendly, Markdown)

This guide helps you quickly decide **which algorithmic pattern to use**, recall **time/space costs**, and apply **best practices** during interviews. It pairs with `InterviewCheatSheet.java` templates.

---

## 1) How to Choose the Right Approach

### A. Fast triage questions
1. **What is the input shape?** (array, string, intervals, grid/graph, tree)
2. **What is the output?** (boolean, index, count, min/max, path, ordering, top‑k)
3. **Are there special properties?** (sorted, small alphabet, duplicates allowed, non‑negative weights)
4. **Constraints?** (n up to? time limit? memory limit?)
5. **Is there a monotone “feasible?” check for an answer value?** (use binary search on the answer)
6. **Is the graph unweighted or weighted (non‑negative / negative)?**
7. **Do I need all solutions or just one best solution?** (backtracking vs greedy/DP)

### B. Cue → Pattern mapping (quick table)

| Problem cue | Typical pattern(s) | Why |
|---|---|---|
| Longest/shortest **subarray/substring** with condition | Sliding window (+ HashMap/Set) | Grow/shrink a window while maintaining a property |
| **Two sorted** arrays/strings, merge‑like tasks | Two pointers | Linear pass without extra sort |
| **Duplicates**, frequency, anagram | Counting with array or HashMap | Track counts quickly |
| **Sorted** list + boundary (first ≥ x, last ≤ x) | Binary search (index) | Boundaries in sorted data |
| Min/max **value** with monotone feasibility | Binary search on the **answer** | Shrink search space of values |
| Overlapping **intervals**, rooms, calendar | Sort + sweep / merge / min‑heap | Order by start/end to combine or count overlaps |
| **Next greater/smaller** to the right/left | Monotonic stack | Track candidates in order |
| **Top‑k**, median, frequent | Heap (PriorityQueue), selection | Keep small structure of best items |
| **Reachability/levels** in graph | BFS | Each edge costs one step |
| **All nodes once**, components, cycle (undirected) | DFS or Union‑Find | Explore or group nodes |
| **Course order / dependencies** | Topological sort (Kahn or DFS) | Respect prerequisites |
| Shortest path, **non‑negative** weights | Dijkstra | Greedy expansion by current shortest |
| **Minimum spanning tree** | Kruskal (Union‑Find) / Prim | Connect with smallest total cost |
| **Best of a contiguous block** | Kadane (max subarray) | Local recurrence ending at i |
| Increasing subsequence length | LIS (binary search table) | Maintain minimal tails |
| “Fewest edits” between strings | Edit distance DP | Classic 2D dynamic programming |
| Generate all arrangements/choices | Backtracking with pruning | Try/undo structure |

### C. Picking under constraints
- **n ≤ 10^5:** prefer O(n) or O(n log n). Avoid O(n^2) unless constants are tiny and constraints smaller.
- **Alphabet is small (e.g., 26 letters):** use fixed‑size arrays over maps for speed.
- **Graph edges ≫ nodes:** adjacency lists beat adjacency matrices.
- **Weights negative?** Use Bellman–Ford (or SPFA cautiously), not Dijkstra.

---

## 2) Big‑O Cheat Sheet (time and space)

### A. Common data structures (Java)

| Structure | Operation | Cost (average) | Notes |
|---|---|---|---|
| `ArrayList` | get / set | constant | append amortized constant |
|  | add/remove at end | amortized constant | middle insert/remove is linear |
| `LinkedList` | add/remove at ends | constant | random access is linear |
| `HashMap` / `HashSet` | put/get/contains/remove | constant avg | worst‑case linear; good hash needed |
| `TreeMap` / `TreeSet` | put/get/contains/remove | log n | ordered keys |
| `PriorityQueue` | offer/poll | log n | peek is constant |
| `ArrayDeque` | addFirst/addLast/pollFirst/pollLast | amortized constant | prefer over legacy `Stack` |
| `Arrays.sort(int[])` | sort | n log n | dual‑pivot Quicksort |
| `Collections.sort(List<T>)` | sort | n log n | Timsort |

### B. Pattern/algorithm costs

| Pattern | Time | Space | Notes |
|---|---|---|---|
| Two pointers | linear | constant | One pass over array(s) |
| Sliding window | linear | up to unique elements in window | Use `HashMap`/array for counts |
| Binary search (index) | log n | constant | Needs sorted data |
| Binary search on answer | log(range) × check | constant | Check is usually linear |
| Merge intervals | n log n | result size | Sort by start |
| Monotonic stack | linear | linear | Each index pushed/popped ≤ 1 |
| Heap top‑k | n log k | k | Keep k best only |
| BFS/DFS | nodes + edges | nodes | Graph traversal |
| Topological sort (Kahn) | nodes + edges | nodes | Cycle if processed < nodes |
| Union‑Find (with optimizations) | ~constant per op | nodes | Used by Kruskal |
| Kruskal MST | edges log edges | nodes | Sort edges + Union‑Find |
| Dijkstra (with heap) | edges log nodes | nodes | Non‑negative weights only |
| Kadane | linear | constant | Max subarray sum |
| LIS (binary search) | n log n | n | Only length unless you track parents |
| Edit distance DP | n × m | n × m | Can reduce to two rows |
| Backtracking (permutations) | n! | n | Exponential by nature |

### C. Space quick reference
- **Avoid extra arrays** when pattern allows (two pointers, sliding window).  
- **Use `long`** for sums/products to avoid overflow.  
- **Graph:** adjacency list uses memory proportional to nodes + edges.

---

## 3) Best Practices (interview‑ready)

### A. Reasoning and invariants
- **State your invariant** before coding (e.g., “window has at most K distinct characters”). Keep it true every loop.
- For **binary search**, choose one interval style and stick to it: `[low, high)` is reliable; compute `mid = low + (high - low)/2`.
- For **sliding window**, when you shrink from the left, **remove zero‑count keys** from the map to keep the window’s condition accurate.
- For **graphs**, mark nodes **when you enqueue/push** (not after popping) to avoid duplicates.

### B. Edge cases to build first
- Empty / size‑1 inputs.
- All equal values; strictly increasing/decreasing arrays.
- All negative numbers (Kadane), duplicates everywhere (LIS), disconnected graphs.
- Intervals that just touch (e.g., `[1,4]` and `[4,5]`): decide overlap policy clearly.

### C. Correctness tricks
- **Proof of termination** for binary search: show `high` moves left or `low` moves right each step.
- **Union‑Find**: return `false` if `find(a) == find(b)`; otherwise unite.
- **Topological sort**: if processed count `< n`, a cycle exists.
- **Dijkstra**: skip if you pop a node whose recorded distance is already smaller.

### D. Performance tips (Java)
- Prefer `ArrayDeque` for stacks/queues. Avoid `Stack` (synchronized, legacy).
- For frequency of lowercase letters, use `int[26]` rather than `HashMap<Character,Integer>`.
- Reuse `StringBuilder` for string accumulation inside loops.
- For custom objects in `HashMap/HashSet`, implement `equals` and `hashCode` carefully.

### E. Binary search mini‑guide
- **Index‑based** (on sorted arrays): boundaries like first ≥ x, last ≤ x, first true of a predicate.  
- **Answer‑based**: search a numeric answer; write `boolean feasible(mid)` that is **monotone** (false…false, true…true).

### F. Sliding window recipes
- **Longest with at most K distinct:** expand right, while `map.size() > K` shrink left.  
- **No repeats (unique):** store last seen index; when you hit a repeat inside the window, jump left to `last[c] + 1`.

### G. Graph checklist
- Build **adjacency lists**, not matrices, for large sparse graphs.  
- **Unweighted shortest path** → BFS.  
- **Weighted, non‑negative** → Dijkstra.  
- **Negative edges** → Bellman–Ford.  
- **Dependency order** → Topological sort.

### H. Dynamic programming scaffolding
1. **State**: what subproblem does `dp[i]` or `dp[i][j]` mean?  
2. **Transition**: how to compute from smaller states?  
3. **Base case**: what are the initial values?  
4. **Order**: which indices first (row/column, increasing/decreasing)?  
5. **Answer**: where is it in the table (last cell, max over row, etc.)?

### I. Backtracking hygiene
- **Choose → explore → undo**.  
- Prune early (e.g., stop on invalid partial solutions).  
- Avoid duplicates by **sorting and skipping** equal neighbors when appropriate.

### J. Testing habit (fast but effective)
- Write **3 cases**: typical, edge/minimal, and stressy.  
- For graphs, test both **reachable** and **unreachable** cases.  
- For intervals, test **overlap**, **touching**, and **disjoint** cases.

---

## 4) Constraint‑to‑Strategy Examples

- **“Find the first index ≥ target”** → sorted input, so use **binary search (index)**.  
- **“Longest substring without repeating characters”** → **sliding window** with a map of last seen positions.  
- **“Merge meetings times”** → sort by start and **merge intervals**.  
- **“Kth largest”** with `n=10^5`, `k=100` → **min‑heap** size `k` (faster than sorting all).  
- **“Ship packages within D days”** → **binary search on answer** (capacity), feasibility is one pass.  
- **“Shortest route with tolls (non‑negative)”** → **Dijkstra**.  
- **“Connect cities with least cost”** → **Kruskal** with **Union‑Find**.

---

## 5) Quick “Which DP?” Map

| Wording | DP style |
|---|---|
| “minimum edits/operations” between strings | 2D table, edit distance |
| “ways to make a sum” / “min coins” | 1D or 2D coin change |
| “longest increasing/bitonic” | LIS (n log n) or 2D DP |
| “grid paths with obstacles” | 2D grid DP (row/col transitions) |
| “knapsack capacity/value” | 1D rolling or 2D classic knapsack |

---

## 6) Big‑O Reading of Constraints (rules of thumb)

- `n ≤ 10^3`: nested loops (quadratic) often OK.  
- `n ≤ 10^5`: aim for linear or `n log n`.  
- `n ≥ 10^6`: tight linear only, careful with constants and memory.  
- Graph with `nodes ≤ 10^5` and `edges ≤ 2×10^5`: use adjacency lists; avoid recursion depth issues (convert DFS to stack if needed).

---

## 7) Pointers to Templates

The following Java methods exist in `InterviewCheatSheet.java`:
- Arrays/Strings: `removeDuplicates`, `lengthOfLongestSubstring`, `isAnagram`, `lowerBound`, `minEatingSpeed`
- Intervals/Stacks/Heap: `mergeIntervals`, `nextGreaterIndex`, `kthLargest`
- Graphs: `shortestUnweighted`, `topologicalOrder`, `minimumSpanningTreeKruskal`, `dijkstra`
- DP/Greedy/Backtracking: `kadane`, `lis`, `editDistance`, `permute`

> Tip: Practice by **rewriting one template from memory** each day, then solving one small problem using it.

---

*Last updated:* 2025-07-30
