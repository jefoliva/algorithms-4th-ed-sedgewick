/******************************************************************************
 *  Compilation:  javac Exercise_15.java
 *  Execution:    java Exercise_15
 *  Dependencies: StdOUt.java
 *
 *  Description: 1.1.15 Write a static method histogram() that takes an array
 *  a[] of int values and an integer M as arguments and returns an array of length
 *  M whose ith entry is the number of times the integer i appeared in the argument 
 *  array. If the values in a[] are all between 0 and M–1, the sum of the values in
 *  the returned array should be equal to a.length.
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */



public class Exercise_15 {

	public static int[] histogram(int[] a, int M) {
		int[] arr = new int[M];

		for(int i = 0; i < arr.length; i++)
			arr[i] = 0;

		for(int i = 0; i < arr.length; i++) {
			if (a[i] < arr.length)
				arr[a[i]]++;
		}

		return arr;
	}


	public static void main(String[] args) {
		int[] arrOne = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5};
		int[] arrTwo = {1, 2, 2, 3, 3, 3, 4, 4};
		int[] res;

		res = histogram(arrOne, 8);
		for (int i = 0; i < res.length; i++) 
			StdOut.printf("[%d] = %d\n", i, res[i]);
	}
} 