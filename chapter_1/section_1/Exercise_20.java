/******************************************************************************
 *  Compilation:  javac Exercise_20.java
 *  Execution:    java Exercise_20 N
 *  Dependencies: StdOUt.java
 *
 *  Description: 1.1.20 Write a recursive static method that computes 
 *  the value of ln (N !)
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

public class Exercise_20 {

	// Compute N! 
	public static long recFactorial(int N) {
		if (N <= 1) {
			return 1;
		} else {
			return N * recFactorial(N-1);
		}
	}

	// Calcule lg(N!)
	public static double lnFactorial(int N) {
		return Math.log(recFactorial(N));
	}


	public static void main(String[] args) {
		if (args.length != 1) {
			StdOut.println("Usage: java Exercise_20 N");
			return;
		}

		int N = Integer.parseInt(args[0]);

		if (N > 39) {
			StdOut.println("This implementation cannot compute ln(N) for N bigger than 39.");
			return;
		}

		StdOut.println( lnFactorial(N) );
	}
} 