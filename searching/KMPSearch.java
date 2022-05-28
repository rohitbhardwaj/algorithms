// Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function 
//search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[]. 
//You may assume that n > m.

//example:
//Input:  txt[] = "THIS IS A TEST TEXT"
// pat[] = "TEST"
// Output: Pattern found at index 10


//KMP Search:
//We start comparison of pat[j] with j = 0 with characters of current window of text.
// We keep matching characters txt[i] and pat[j] and keep incrementing i and j 
// while pat[j] and txt[i] keep matching.
// When we see a mismatch:
// - We know that characters pat[0..j-1] match with txt[i-j…i-1] 
// (Note that j starts with 0 and increment it only when there is a match).
// - We also know (from above definition) that lps[j-1] is count of characters 
// of pat[0…j-1] that are both proper prefix and suffix.
// - From above two points, we can conclude that we do not need to match 
// these lps[j-1] characters with txt[i-j…i-1] because we know that these 
// characters will anyway match. Let us consider above example to understand 
// this.

public class KMPSearch {
    public void search(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
  
        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]
  
        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);
  
        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern "
                                   + "at index " + (i - j));
                j = lps[j - 1];
            }
  
            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }
  
    public static void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0
  
        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
  
                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
  
    // Driver program to test above function
    public static void main(String args[])
    {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        new KMPSearch().search(pat, txt);
    }
}