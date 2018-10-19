/******************************************************************************
 *  Compilation:  javac Exercise_39_BruteForceSearch
 *  Execution:    java Exercise_39_BruteForceSearch whitelist.txt < input.txt
 *  Dependencies: StdOut StdRandom
 *	
 *  Description: 1.1.38 Binary search versus brute-force search. Write a program BruteForceSearch
 *  that uses the brute-force search method given on page 48 and compare its running time
 *  on your computer with that of BinarySearch for largeW.txt and largeT.txt
 *
 *  % java Exercise_39_BruteForceSearch largeW.txt < largeT.txt
  Elapsed time for brute force search:  2826.209 seconds
  Elapsed time for binary search:         45.837 seconds
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.BinarySearch;
import java.util.Arrays;

/**
 * @author Jefoliva
 */

public class Exercise_38_BruteForceSearch {
	public static int rank(int key, int[] a) {
		for (int i = 0; i < a.length; i++)
			if (a[i] == key) return i;
		return -1;
	}

	public static void main(String[] args) {
		 In in = new In(args[0]);
         int[] whitelist = in.readAllInts();

        Arrays.sort(whitelist);
        Stopwatch timer;
        double time;
        
       // Run test with brute force search and measure time  
        timer = new Stopwatch();
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if ( rank(key, whitelist) == -1)
                StdOut.println(key);
        }
        time = timer.elapsedTime();

        StdOut.println("Elapsed time for brute force search: " + time);

        // Run test with binary search
        // timer = new Stopwatch();
        // while (!StdIn.isEmpty()) {
        //     int key = StdIn.readInt();
        //     if (BinarySearch.indexOf(whitelist, key) == -1)
        //         StdOut.println(key);
        // }
       	// time = timer.elapsedTime();
        // StdOut.println("Elapsed time for brute binary search: " + time);
	}	
}