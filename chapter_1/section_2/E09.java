/******************************************************************************
 *  Compilation:  javac E09.java
 *  Execution:    java E09 whitelist.txt < input.txt
 *  Dependencies: StdOut Counter StdIn In
 *
 *  Description: 1.2.9 Instrument BinarySearch (page 47) to use a Counter to count the total number
 *  of keys examined during all searches and then print the total after all searches are complete. 
 *  Hint : Create a Counter in main() and pass it as an argument to rank()
 *  
 *  Example execution: (Using data files provided by the book)
 *  % java E09 tinyW.txt < tinyT.txt
 *	50
 *  99
 *  13
 *  18 #lookups
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

/**
 * @author Jefoliva
 */
public class E09 {

	public static int rank(int[] a, int key, Counter c) {
		int lo = 0;
		int hi = a.length - 1;

		c.increment();
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if  	(key < a[mid]) hi = mid-1;
			else if (key > a[mid]) lo = mid+1;
			else return mid;	// key found 
		}
		return -1; 	// not found
	}

    public static void main(String[] args) {
    	In in = new In(args[0]);
    	int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);

        Counter counter = new Counter("#lookups");

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(whitelist, key, counter) == -1)
                StdOut.println(key);
        }
        StdOut.println(counter);
    }
}