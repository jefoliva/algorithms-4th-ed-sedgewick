/******************************************************************************
 *  Compilation:  javac Exercise_29_EqualKeys.java
 *  Execution:    java Exercise_29_EqualKeys
 *  Dependencies: StdOUt.java StdIn.java In.java
 *	Usage:  java Exercise_29_EqualKeys whitelist.txt < keys.txt
 *
 *  Description: Equal keys. Add to BinarySearch a static method rank() that takes a key and
 *	a sorted array of int values (some of which may be equal) as arguments and returns the
 *	number of elements that are smaller than the key and a similar method count() that
 *	returns the number of elements equal to the key. Note : If i and j are the values returned
 *	by rank(key, a) and count(key, a) respectively, then a[i..i+j-1] are the values in
 *	the array that are equal to key.
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;


/**
 * @author Jefoliva
 */

public class Exercise_29_EqualKeys {

	public static int indexOf(int key, int[] a, int lo, int hi) { 	
		if (lo > hi) return -1;

		int mid = lo + (hi - lo) / 2;

		if 		(key < a[mid]) return indexOf(key, a, lo, mid - 1);
		else if (key > a[mid]) return indexOf(key, a, mid + 1, hi);
		else return mid;
	}

	// This method finds and returns the first ocurrence of key
	public static int rank(int key, int[] a) {
		int first = a.length;

		// Find first ocurrence of key
		while( indexOf(key, a, 0, first-1) != -1)
			first = indexOf(key, a, 0, first-1);

		return first;
	}

	/**
	* This method finds the first and last ocurrence of key
	* and returns the count (last - first + 1)
	*/
	public static int count(int key, int[] a) {
		int first = rank(key, a);
		int last = first;

		// Find last ocurrence of key
		while( indexOf(key, a, last+1, a.length-1) != -1) 
			last = indexOf(key, a, last+1, a.length-1);

		// If key not found return -1
		if(first == -1) return -1;
		else 			return  last - first + 1;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] whitelist = in.readAllInts();
		Arrays.sort(whitelist);

		while( !StdIn.isEmpty() ) {
			// read key from input
			int key = StdIn.readInt();
			int smaller = rank(key, whitelist);
			int equal = count(key, whitelist);

			StdOut.printf("Key = %2d, Smaller: %2d,  Equal = %d\n", key, smaller, equal);
		}
	}
}