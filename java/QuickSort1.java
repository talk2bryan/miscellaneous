//QusickSort1.java
public class QuickSort1
{
	public static void main(String[] args) {
		int max_size = 10000000;
		ArrayIn arr = new ArrayIn(max_size);

		for (int i=0;i<max_size ;++i ) {
			int n = (int) (java.lang.Math.random() * 99);
			arr.insert(n);
		}

		// arr.display();
		double start = System.currentTimeMillis();
		// System.out.println(start);
		arr.quicksort();
		System.out.println("Time: "+ (System.currentTimeMillis() - start));
		// arr.display();
	}
}//class QusickSort1

class ArrayIn
{
	private int num_items;
	private int arr[];

	public ArrayIn(int n)
	{
		arr = new int[n];
		num_items = 0;
	}
	public void insert(int n)
	{
		arr[num_items++] = n;
	}
	public void quicksort()
	{
		quicksort(0,num_items-1);
	}
	public void display()
	{
		System.out.print("A= ");
		for (int i=0;i<num_items ;i++ ) {
			System.out.print(arr[i]+ " ");
		}
		System.out.println();
	}
	private void quicksort(int left, int right)
	{
		if (right-left <=0) {
			return;
		}
		else{
			int pivot = arr[right];
			int partition = partition_it(left,right,pivot);
			quicksort(left,partition-1);
			quicksort(partition+1,right);
		}
	}
	private int partition_it(int left, int right, int pivot)
	{
		int leftPtr = left-1;
		int rightPtr = right;

		while(true)
		{
			//while(leftPtr< right)
			while(leftPtr < right && arr[++leftPtr] < pivot)
				;
			while(rightPtr>0 && arr[--rightPtr] > pivot)
				;
			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr,rightPtr);
		}
		swap(leftPtr,right);
		return leftPtr;
	}
	private void swap(int a, int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}