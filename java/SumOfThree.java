import java.util.Arrays;

public class SumOfThree{
 static int arr[] = {2, 34, 1, 9, 6, 10, 8, 4, 13, 3, 7, 18};
 static int n = arr.length;
 public static void main(String[] parms) {
  sum_of_three(-1);
 }

 public static boolean sum_of_three(int t){
  int lower_bound, upper_bound;
  boolean result = false;

  Arrays.sort(arr); // sort array

  assert(n >= 3) : "Array must contain least 3 elements to find a sum.";

 search:
  // begin from index 0, then find the other two
  for (int i = 0; i<n-2 ; ++i) {
   // scan from left and right of the remaining contents
   // of the array, and move towards each other.
   lower_bound = i+1;
   upper_bound = n-1; 

   while (lower_bound < upper_bound) {
    if (arr[i] + arr[lower_bound] + arr[upper_bound] == t) {
     // found, testing for correctness...
     assert( (0 <= i) && (i<=lower_bound) && (lower_bound < upper_bound)
      && (upper_bound < n-1) &&
       (arr[i] + arr[lower_bound] + arr[upper_bound] == t) );
     System.out.println("Values: "+ arr[i] +", "
      +arr[lower_bound] +", "+ arr[upper_bound]);
     result = true;
     break search;
    }
    else if (arr[i] + arr[lower_bound] + arr[upper_bound] < t) {
     lower_bound++;
    }
    else { upper_bound--; } // arr[i]+arr[lower_bound]+arr[upper_bound] > t
   }  // while
  }  // search:
  return result;
 }  // sum_of_three()
}  // SumOfThree