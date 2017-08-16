public class BinarySearch{
  static int n = 10;
  static int arr[] = new int[n];
  
  public static void main(String [] parms){
    for (int i = 0; i < n; ++i)
    { arr[i] = 10*i; /* 0 10 20 30 40 50 60 70 80 90 */ }
    // extra tests....
    assert(0 <= binary_search(10) && binary_search(10) <n);
    assert( binary_search(5) == -1 ) : "Should evaluate to -1";
  }
  
  public static int binary_search(int t)
  {
    int result = -1;
    int lower, upper, mid, oldsize, size;
    boolean found = false;
    
    lower = 0;
    upper = n-1;
    size = n+1;
    
    while( lower <= upper && !found )
    {
      // Loop invariant...
      // size will always shrink - we know this to be true
      oldsize = size;
      size = upper-lower +1;
      
      assert size < oldsize : "Range must be smaller";
      // invariant ends here...
      
      mid = (lower+upper)/2;
      if ( arr[mid] < t ) { lower = mid+1; }
      else if ( arr[mid] == t )
      {// found, testing for correctness...
        assert(0 <= mid && mid < n && arr[mid] == t);
        result = mid;
        found = true;
      }
      else { upper = mid-1; }
    }
    return result;
  } 
}