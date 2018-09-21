/******************************************************************************
 *  Compilation:  javac Exercise_14.java
 *  Execution:    java Exercise_14 N
 *  Dependencies: StdOUt.java
 *
 *  Description: 1.1.14 Write a static method lg() that takes an int value N 
 *  as argument and returns the largest int not larger than the base-2 
 *  logarithm of N. Do not use Math.
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

public class Exercise_14 {

	public static int lg(int N) {
		int res = 1;
		int n = 0; 		// largest int

		for( n = 0; res < N; n++ )
			res *= 2; 

		if (N < res )
			return --n;
		else
			return n;
	}

	public static void main(String[] args) {
		
		if( args.length != 1 ) {
			StdOut.println("Usage: java Exercise_14 N");
			return;
		}

		int N = Integer.parseInt(args[0]);

		StdOut.printf("lg(%d) = %d \n", N, lg(N));
		StdOut.printf("lg(%d) = %d \n", 255, lg(255));

		StdOut.printf("lg(%d) = %d \n", 128, lg(128));
		StdOut.printf("lg(%d) = %d \n", 127, lg(127));
	}
} 