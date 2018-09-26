/******************************************************************************
 *  Compilation:  javac Exercise_24_Euclid.java
 *  Execution:    java Exercise_24_Euclid N1 N2
 *  Dependencies: StdOUt.java
 *
 *  Description: Extend the code given on page 4 to develop a program Euclid 
 *  that takes two integers from the command line and computes their greatest 
 *  common divisor, printing out the two arguments for each call on the recursive
 *  method. Use your program to compute the greatest common divisor of 
 *  1111112 and 1234567.
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

public class Exercise_24_Euclid {
	
	public static int gcd(int p, int q) {
		traceArguments(p, q);
		if (q == 0) return p;

		int r = p % q;
		return gcd(q, r);
	}

	public static void traceArguments(int p, int q) {
		StdOut.printf("gcd( %d, %d)\n", p, q);
	}

	public static void main(String[] args) {
		// Check that there are two arguments
		if(args.length != 2) {
			StdOut.println("Usage: Exercise_24_Euclid N1 N2");
			return;
		}

		int p = Integer.parseInt(args[0]);
		int q = Integer.parseInt(args[1]);
		
		StdOut.println("Result: " + gcd(p, q) );
		StdOut.println();
		StdOut.println("Result: " + gcd(1111112, 1234567) );
	}
}