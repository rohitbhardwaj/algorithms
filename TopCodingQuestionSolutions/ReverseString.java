//Reverse String Problem
// Write a function that reverses a string. The input string is given as an array of characters s.
// You must do this by modifying the input array in-place with O(1) extra memory.
//example ['h', 'e', 'l', 'l', 'o'] --> ['o', 'l', 'l', 'e', 'h']


public class ReverseString { 
  public void reverseString(char[] s) {
   int left = 0;
   int end = s.length-1;
   while (left < end) {
      //swap
      char temp = s[left];
      s[left] = s[end];
      s[end] = temp;
      
      left ++;
      end --;
   }
    }

  public static void main(String args[]) {

      ReverseString ob = new ReverseString();
      char arr[] = {'h', 'e', 'l', 'l', 'o'};

      ob.reverseString(arr);

      for (char i : arr) {
        System.out.print("" + i + ' ');
      }
      System.out.println("");
  }
  
}