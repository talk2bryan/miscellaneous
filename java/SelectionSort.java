//selectionSort.java
import java.util.*;

public class SelectionSort
{
	public static void main(String [] parms)
	{
		System.out.println("before sorting");
		int array[] = {6,3,3,8,6,13,3,45,7,9,0,7,5,3,22,4,5,0};
		System.out.println(Arrays.toString(array));
		selectionSort(array);
		System.out.println("after sorting");
		System.out.println(Arrays.toString(array));
	}
	public static void selectionSort(int []arr)
	{
		int min_index,temp; 
		for (int i = 0; i < arr.length ;i++ ) {
			min_index = i;
			for (int j = arr.length-1;j > i ;j-- ) {
				if (arr[j] < arr[min_index]) {
					min_index = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[min_index];
			arr[min_index] = temp;
		}
	}
}