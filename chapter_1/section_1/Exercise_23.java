/******************************************************************************
 *  Compilation:  javac Exercise_23.java
 *  Execution:    java Exercise_23 whitelist.txt < input.txt
 *  Example exec: java Exercise_23 tinyT.txt < tinyW.txt
 *  Dependencies: StdOUt.java StdIn.java StdIn.java BinarySearch.java
 *
 *  Description: 1.1.23 Add to the BinarySearch test client the ability to 
 *  respond to a second argument: + to print numbers from standard input that
 *  are not in the whitelist, - to print numbers that are in the whitelist.
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BinarySearch;
import java.util.Arrays;

/**
 * @author Jefoliva
 */

public class Exercise_23 {
	public static void main(String[] args) {

		// Check that there are two arguments
		if (args.length != 2) {
			System.out.println("Usage: Exercise_23 whitelist.txt  +/-  < 8Kints.txt");
			return;
		}

		In in = new In(args[0]);
		int[] whitelist = in.readAllInts();
		Arrays.sort(whitelist);

		// option: + / -
		String option = args[1];

		if( option.equals("+") ) 
		{	// option +: Show numbers not present in whitelist
			while(!StdIn.isEmpty()) {
				int key = StdIn.readInt();

				if(BinarySearch.indexOf(whitelist, key) == -1)
					StdOut.println(key);
			}
		} else if ( option.equals("-") ) 
		{	// Option -: Show number that ARE in whitelist
			while(!StdIn.isEmpty()) {
				int key = StdIn.readInt();

				if(BinarySearch.indexOf(whitelist, key) != -1)
					StdOut.println(key);
			}
		}
	}
} 