import java.util.*;

public class PositiveNegativePairs {
	public static void main(String[] args) {
		int [] input = new int []{1, -3, 2, 3, 6, -1};
		int [] input2 = new int [] {4, 8, 9, -4, 1, -1, -8, -9};
		print(input);
		print(input2);

	}

	public static void print(int []arr){
		int len = arr.length;

		Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>(len);
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (Integer i : arr) {
			if(table.containsValue(Math.abs(i)))
				list.add(Math.abs(i));
			else 
				table.put(Math.abs(i), Math.abs(i));
		}

		Collections.sort(list);

		for (Integer i : list) {

			System.out.print("-"+i + " " + i + " ");
		}
		System.out.println();
	}
}