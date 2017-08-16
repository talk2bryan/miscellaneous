//insertionSort.java
import java.util.*;

public class InsertionSort{
	public static void main(String[] args) {
		System.out.println("before sorting");
		int array[] = {77,99,44,55,22,88,11,00,66,33};
		System.out.println(Arrays.toString(array));
		insertionSort(array);
		System.out.println("after sorting");
		System.out.println(Arrays.toString(array));
	}
	public static void insertionSort(int arr[]){
		int temp,j;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];
			j = i;
			while(j >0 && arr[j-1] > temp)
			{
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = temp;
		}
	}
}