/******************************************************************************
 *  Compilation:  javac Exercise_28_RemoveDuplicates.java
 *  Execution:    java Exercise_28_RemoveDuplicates
 *  Dependencies: StdOUt.java StdIn.java In.java
 *	Usage:  java Exercise_28_RemoveDuplicates tinyW.txt < tinyT.txt
 *
 *  Description: Remove duplicates. Modify the test client in BinarySearch to
 *  remove any duplicate keys in the whitelist after the sort.
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;


/**
 * @author Jefoliva
 */

public class Exercise_28_RemoveDuplicates {

	public static int rank(int key, int[] a, int lo, int hi)
	{ 	
		if (lo > hi) return -1;

		int mid = lo + (hi - lo) / 2;

		if 		(key < a[mid]) return rank(key, a, lo, mid - 1);
		else if (key > a[mid]) return rank(key, a, mid + 1, hi);
		else return mid;
	}

	public static int indexOf(int[] a, int key) {
		return rank(key, a, 0, a.length-1);
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
     	int[] whitelist = in.readAllInts();
		Arrays.sort(whitelist);

		int N = 0;		// Keep track of how many non-repeated numbers there are
		int[] tempWhitelist = new int[whitelist.length];


		// Save non-repeated numbers is tempWhiteList
		for(int i = 0; i < whitelist.length; i++) {
			int key = whitelist[i];

			if( rank(key, tempWhitelist, 0, N) == -1 ) {
				tempWhitelist[N++] = key; 
			}
		}

		// Create a new whitelist with length N (of non-repeated numbers)
		whitelist = new int[N];    
		for(int i = 0; i < N; i++)
			whitelist[i] = tempWhitelist[i];


		while(!StdIn.isEmpty()) {
			int key = StdIn.readInt();

			if( indexOf(whitelist, key) == -1) 
				StdOut.println(key);
		}
	}
}