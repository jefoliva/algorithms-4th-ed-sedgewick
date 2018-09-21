/******************************************************************************
 *  Compilation:  javac Exercise_9.java
 *  Execution:    java Exercise_9 N
 *  Dependencies: StdOUt.java
 *
 *  Description: 1.1.9 Write a code fragment that puts the binary representation of a positive integer N
	into a String s.
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

public class Exercise_9 {
	public static void main(String[] args) {
		
		// Check that there is one argument, return otherwise
		if (args.length != 1) {
			StdOut.println("Usage: java Exercise_9 N");
			return;
		}

		int N = Integer.parseInt(args[0]);
		String s = "";

		for(int n = N; n > 0; n /= 2)  
			s =  (n % 2) + s;

		// if s is empty it means that N was 0
		StdOut.println(s.equals("") ? 0 : s);
	}
}