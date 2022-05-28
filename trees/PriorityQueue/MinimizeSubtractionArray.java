// Given a number X, and an array arr[] of length N containing the N numbers. 
// The task is to find the minimum number of operations required to make X 
// non-positive. In one operation:
// - Select any one number Y from the array and reduce X by Y. 
// - Then make Y = Y/2 (take floor value if Y is odd).
// - If it is not possible to make X non-positive, return -1.

//example:
// Input: N = 3, arr[] = {3, 4, 12}, X = 25
// Output:  4

import java.util.*;

public class MinimizeSubtractionArray {
    // Function to find minimum operations
  public static int minimumOperations(int N, int X,
  int nums[])
{

// Initialize answer as zero
int ans = 0;

// Create Max-Heap using
// Priority-queue
PriorityQueue<Integer> pq = new PriorityQueue<>(
Collections.reverseOrder());

// Put all nums in the priority queue
for (int i = 0; i < N; i++)
pq.add(nums[i]);

// Execute the operation on num with
// max value until nums are left
// and X is positive
while (!pq.isEmpty() && X > 0) {
if (pq.peek() == 0)
break;

// Increment the answer everytime
ans++;

// num with maximum value
int num = pq.peek();

// Removing the num
pq.poll();

// Reduce X's value and num's
// value as per the operation
X -= num;
num /= 2;

// If the num's value is positive
// insert back in the
// priority queue
if (num > 0)
pq.add(num);
}

// If X's value is positive then it
// is impossible to make X
// non-positive
if (X > 0)
return -1;

// Otherwise return the number of
// operations performed
return ans;
}

// Driver Code
public static void main(String[] args)
{
int N = 3;
int nums[] = { 3, 4, 12 };
int X = 25;

// Function call
System.out.print(minimumOperations(N, X, nums));
}
}