/******************************************************************************
 *  Compilation:  javac Exercise_22.java
 *  Execution:    java Exercise_22 key < input.txt
 *  Example exec: java Exercise_22 982455 < 8Kints.txt
 *  Dependencies: StdOUt.java StdIn.java
 *
 *  Description: 1.1.22 Write a version of BinarySearch that uses the recursive
 *  rank() given on page 25 and traces the method calls. Each time the recursive 
 *  method is called, print the argument values lo and hi, indented by the depth 
 *  of the recursion. Hint: Add an argument to the recursive method that keeps
 *  track of the depth.
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Arrays;

/**
 * @author Jefoliva
 */

public class Exercise_22 {

	// This method keeps track of the depth inside rank method
	public static void traceCalls(int lo, int hi, int mid, int a[], int depth) {
		
		StdOut.print(depth + ".");
		for( int i = 0; i < depth; i++)
			StdOut.print(" ");

		StdOut.printf("lo = %d  hi = %d  mid[%d] = %d \n", lo, hi, mid, a[mid]);
	}

	public static int rank(int key, int[] a)
	{ return rank(key, a, 0, a.length - 1, 1); }
	
	public static int rank(int key, int[] a, int lo, int hi, int depth) { 	
		// Index of key in a[], if present, is not smaller than lo
		// and not larger than hi.
		if (lo > hi) return -1;

		int mid = lo + (hi - lo) / 2;
		
		// Print trace
		traceCalls(lo, hi, mid, a, depth);

		if 		(key < a[mid]) 	return rank(key, a, lo, mid - 1, depth+1);
		else if (key > a[mid]) 	return rank(key, a, mid + 1, hi, depth+1);
		else 					return mid;
	}

	public static void main(String[] args) {
		int[] a = StdIn.readAllInts();
		Arrays.sort(a);

		int key = Integer.parseInt(args[0]);
		rank(key, a);
	}
} 