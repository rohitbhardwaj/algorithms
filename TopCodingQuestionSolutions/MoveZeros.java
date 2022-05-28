//Given an integer array nums, move all 0's to the end of it
// while maintaining the relative order of the non-zero elements.
//example : nums = [0, 1, 0, 3, 12] --> [1, 3, 12, 0, 0]

import java.util.*;
public class MoveZeros {
    public static void moveZeroes(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++){
            if(nums[i] != 0){
                nums[count++] = nums[i];
            }
        }
        for (int j = count; j < n; j++){
            nums[j] = 0;
        }
    }

    public static void main(String args[]) {
        int arr[] = {0, 1, 0, 3, 12};
        
        moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}