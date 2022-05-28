//Given a text txt[0..n-1] and a pattern pat[0..m-1] where n is the length of 
//the text and m is the length of the pattern, write a function 
//search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[]. 
//You may assume that n > m. 

//example:
// Input:  txt[] = "THIS IS A TEST TEXT"
// pat[] = "TEST"
// Output: Pattern found at index 10

//Bad Character Heuristic
// The idea of bad character heuristic is simple. The character of the text 
//which doesn’t match with the current character of the pattern is called 
//the Bad Character. Upon mismatch, we shift the pattern until – 
// 1) The mismatch becomes a match
// 2) Pattern P moves past the mismatched character.

// Good Suffix Heuristic
// Let t be substring of text T which is matched with substring of pattern P. 
// Now we shift pattern until :
// 1) Another occurrence of t in P matched with t in T.
// 2) A prefix of P, which matches with suffix of t
// 3) P moves past t


public class BoyerMooreSearch {
    static int NO_OF_CHARS = 256;
      
    //A utility function to get maximum of two integers
     static int max (int a, int b) { return (a > b)? a: b; }
 
     //The preprocessing function for Boyer Moore's
     //bad character heuristic
     public static void badCharHeuristic( char []str, int size,int badchar[])
     {
 
      // Initialize all occurrences as -1
      for (int i = 0; i < NO_OF_CHARS; i++)
           badchar[i] = -1;
 
      // Fill the actual value of last occurrence
      // of a character (indices of table are ascii and values are index of occurrence)
      for (int i = 0; i < size; i++)
           badchar[(int) str[i]] = i;
     }
 
     /* A pattern searching function that uses Bad
     Character Heuristic of Boyer Moore Algorithm */
     static void search( char txt[],  char pat[])
     {
      int m = pat.length;
      int n = txt.length;
 
      int badchar[] = new int[NO_OF_CHARS];
 
      /* Fill the bad character array by calling
         the preprocessing function badCharHeuristic()
         for given pattern */
      badCharHeuristic(pat, m, badchar);
 
      int s = 0;  // s is shift of the pattern with
                  // respect to text
       //there are n-m+1 potential alignments
      while(s <= (n - m))
      {
          int j = m-1;
 
          /* Keep reducing index j of pattern while
             characters of pattern and text are
             matching at this shift s */
          while(j >= 0 && pat[j] == txt[s+j])
              j--;
 
          /* If the pattern is present at current
             shift, then index j will become -1 after
             the above loop */
          if (j < 0)
          {
              System.out.println("Patterns occur at shift = " + s);
 
              /* Shift the pattern so that the next
                 character in text aligns with the last
                 occurrence of it in pattern.
                 The condition s+m < n is necessary for
                 the case when pattern occurs at the end
                 of text */
              //txt[s+m] is character after the pattern in text
              s += (s+m < n)? m-badchar[txt[s+m]] : 1;
 
          }
 
          else
              /* Shift the pattern so that the bad character
                 in text aligns with the last occurrence of
                 it in pattern. The max function is used to
                 make sure that we get a positive shift.
                 We may get a negative shift if the last
                 occurrence  of bad character in pattern
                 is on the right side of the current
                 character. */
              s += max(1, j - badchar[txt[s+j]]);
      }
     }



    // preprocessing for strong good suffix rule
public static void preprocess_strong_suffix(int []shift, int []bpos,
                                      char []pat, int m)
{
    // m is the length of pattern 
    int i = m, j = m + 1;
    bpos[i] = j;
  
    while(i > 0)
    {
        /*if character at position i-1 is not 
        equivalent to character at j-1, then 
        continue searching to right of the
        pattern for border */
        while(j <= m && pat[i - 1] != pat[j - 1])
        {
            /* the character preceding the occurrence of t 
            in pattern P is different than the mismatching 
            character in P, we stop skipping the occurrences 
            and shift the pattern from i to j */
            if (shift[j] == 0)
                shift[j] = j - i;
  
            //Update the position of next border 
            j = bpos[j];
        }
        /* p[i-1] matched with p[j-1], border is found.
        store the beginning position of border */
        i--; j--;
        bpos[i] = j; 
    }
}
  
//Preprocessing for case 2
public static void preprocess_case2(int []shift, int []bpos,
                              char []pat, int m)
{
    int i, j;
    j = bpos[0];
    for(i = 0; i <= m; i++)
    {
        /* set the border position of the first character 
        of the pattern to all indices in array shift
        having shift[i] = 0 */
        if(shift[i] == 0)
            shift[i] = j;
  
        /* suffix becomes shorter than bpos[0], 
        use the position of next widest border
        as value of j */
        if (i == j)
            j = bpos[j];
    }
}
  
/*Search for a pattern in given text using
Boyer Moore algorithm with Good suffix rule */
public static void search1(char []text, char []pat)
{
    // s is shift of the pattern 
    // with respect to text
    int s = 0, j;
    int m = pat.length;
    int n = text.length;
  
    int []bpos = new int[m + 1];
    int []shift = new int[m + 1];
  
    //initialize all occurrence of shift to 0
    for(int i = 0; i < m + 1; i++) 
        shift[i] = 0;
  
    //do preprocessing
    preprocess_strong_suffix(shift, bpos, pat, m);
    preprocess_case2(shift, bpos, pat, m);
  
    while(s <= n - m)
    {
        j = m - 1;
  
        /* Keep reducing index j of pattern while 
        characters of pattern and text are matching 
        at this shift s*/
        while(j >= 0 && pat[j] == text[s+j])
            j--;
  
        /* If the pattern is present at the current shift, 
        then index j will become -1 after the above loop */
        if (j < 0)
        {
            System.out.printf("pattern occurs at shift = %d\n", s);
            s += shift[0];
        }
        else
          
            /*pat[i] != pat[s+j] so shift the pattern
            shift[j+1] times */
            s += shift[j + 1];
    }
  
}
 
     /* Driver program to test above function */
    public static void main(String []args) {
         
         char txt[] = "ABAAABCD".toCharArray();
         char pat[] = "ABC".toCharArray();
         search(txt, pat);

        txt = "ABAAAABAACD".toCharArray();
        pat = "ABA".toCharArray();
        search1(txt, pat);
    }
}