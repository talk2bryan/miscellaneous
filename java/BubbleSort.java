//bubbleSort.java
import java.util.*;

public class BubbleSort
{
	public static void main(String[] args) {
		System.out.println("before sorting");
		int array[] = {6,3,3,8,6,13,3,45,7,9,0,7,5,3,22,4,5,0};
		System.out.println(Arrays.toString(array));
		bubbleSort(array);
		System.out.println("after sorting");
		System.out.println(Arrays.toString(array));
	}
	public static void bubbleSort(int []arr){
		int iter = arr.length-1;

		while(iter > 0){
			for (int i = 0;i < iter ;i++ ) {
				if (arr[i+1] < arr[i]) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			}
			iter--;
		}
		
	}
}