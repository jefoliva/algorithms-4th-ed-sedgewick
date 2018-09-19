/***********************************************************************
 *  Compilation:  javac Exercise_3.java
 *  Execution:    java Exercise_3 n1 n2 n3
 *  Dependencies: StdOut.java
 *
 *  Write a program that takes three integer command-line arguments
 *  and prints equal if all three are equal, and not equal otherwise.
************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
* @author jefoliva
*/

public class Exercise_3 {
	public static void main(String[] args) {

		// Check if user entered three arguments
		if ( args.length != 3) {
			StdOut.println("Usage: java Exercise_3 n1 n2 n3");
			return;
		}

		// Cast arguments from String to integer
		try {
			int intOne = Integer.parseInt(args[0]);
			int intTwo = Integer.parseInt(args[1]);
			int intThree = Integer.parseInt(args[2]);

			StdOut.
			if (intOne == intTwo && intTwo == intThree)
				StdOut.println("Equal");
			else
				StdOut.println("Not equal");

		} catch(NumberFormatException exc) {
			StdOut.println("Fail to cast one of the arguments.");
		}
	}
}