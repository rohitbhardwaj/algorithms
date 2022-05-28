//Single Number
//Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
// You must implement a solution with a linear runtime complexity and use only constant extra space.
//example: [4, 1, 2, 1, 2] --> 4

public class SingleNumber {
    public static int singleNumber(int[] nums) {
          int res = 0;
          for (int num : nums){
              res ^= num;
          }
          return res;
      }
  
    public static void main(String args[]) {
      int arr[] = {4, 1, 2, 2, 1};
  
      System.out.println(singleNumber(arr));
      
    }
    
    
  }
  