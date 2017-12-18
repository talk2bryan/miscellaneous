import java.util.*;
// Author: Bryan Wodi<talk2kamp@gmail.com>
// Performs Next and Prev Permutation for any given String.

public class Permutation{
	public static void main(String[] args) {

		assert "abdc".equals(next_permutation("abcd"));
		assert "abcd".equals(next_permutation("dcba"));
		assert "1267348".equals(next_permutation("1264873"));
		assert "bdac".equals(next_permutation("bcda"));
		assert "123".equals(next_permutation("321"));


		assert "dcba".equals(prev_permutation("abcd"));
		assert "1264873".equals(prev_permutation("1267348"));
		assert "bcda".equals(prev_permutation("bdac"));
		assert "321".equals(prev_permutation("123"));

	}

	/*
	 * Auxillary methods *
	*/
	// Swap:
	// 	Given an array and two indexes, swap the contents.
	public static void swap(char[] arr, int a, int b){
		assert a >= 0 && a < arr.length : "Bad index";
		assert b >= 0 && b < arr.length : "Bad index";

		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	// Reverse:
	//	Given an array, start and end positions, reverse the array
	//	from start, (inclusive), to end (exclusive).
	public static void reverse(char[] arr, int start, int end){
		assert start >= 0 : "Bad start index";
		assert end >= 0 && end <= arr.length : "Bad end index";

		end = end-1;
		while(start < end ){
			char temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	// Algorithm.
	// Given a string str.
	// 1. find the rightmost index (suffix) such that:
	//		str[suffix-1] < str[suffix]
	// we have a prefixSuffix.
	// else, we cannot permute anymore. return reverse_string
	// 2. find the rightmost index j such that
	// 		str[suffix-1] < str[j]
	// 3. swap str[suffix-1], str[j]
	// 4. reverse {str[suffix], str.length()}
	public static String next_permutation(String str){
		assert str != null : "Pass valid String";
		return perform_next_permutation(str.toCharArray());
	}

	public static String perform_next_permutation(char[]arr){

		int suffix = arr.length-1;

		while(suffix>0 && arr[suffix-1] > arr[suffix]){
			suffix--;
		}
		assert suffix >= 0 : "Must be between bounds";

		if (suffix == 0) {
			reverse(arr, 0, arr.length);
			return new String(arr);
		}

		assert suffix >= 0 : "Should be greater than/equal to zero";

		int j = arr.length -1;
		while(j >= suffix && arr[suffix-1] > arr[j]){
			j--;
		}

		assert j>=0 && j>=suffix : "Making sure";
		swap(arr, suffix-1, j);

		// reverse...
		reverse(arr, suffix, arr.length);

		return new String(arr);
	}
	// Algorithm:
	// Given a string str.
	// 1. Find the rightmost index (suffix) such that: 
	//		str[suffix-1] < str[suffix]
	// If suffix == 0; did not find. return reverse_string
	// 2. Find the rightmost index j such that 
	//    str[suffix-1] > str[j]
	// 3. swap str[j], str[suffix-1]
	// 4. reverse {str[suffix, str.length]}
	public static String prev_permutation(String str){
		assert str != null : "Pass valid string";
		return perform_prev_permutation(str.toCharArray());
	}
	public static String perform_prev_permutation(char [] arr){
		int suffix = arr.length-1;

		while(suffix > 0 && arr[suffix-1] < arr[suffix]){
			suffix--;
		}
		assert suffix >= 0 : "Check your boundaries bro!";
		if (suffix == 0) {
			reverse(arr, 0, arr.length);
			return new String(arr);
		}

		int j = arr.length-1;
		while(j >= suffix && arr[suffix-1] < arr[j]){
			j--;
		}
		assert j >= 0 && j >= suffix : "boundaries bro!";

		swap(arr, suffix-1, j);
		reverse(arr, suffix, arr.length);
		return new String(arr);
	}
}