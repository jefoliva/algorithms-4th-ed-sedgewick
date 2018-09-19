/***********************************************************************
 *  Compilation:  javac Exercise_5.java
 *  Execution:    java Exercise_5
 *  Dependencies: StdOut.java
 *
 *  Write a code fragment that prints true if the double variables x and y 
 *	are bothc strictly between 0 and 1 and false otherwise
 ***********************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
* @author jefoliva
*/

public class Exercise_5 {
	public static void main(String[] args) {

		isBetweenZeroAndOne(0.002, 0.003);
		isBetweenZeroAndOne(0.4, 0.8);
		isBetweenZeroAndOne(0.001, 1.001);
		isBetweenZeroAndOne(1.001, 0.001);
		isBetweenZeroAndOne(1.0, 0.0);
		
	}


	public static void isBetweenZeroAndOne(double x, double y) {
		boolean condition = (x > 0 && x < 1) && (y > 0 && y < 1);
		
		if ( condition )
			StdOut.println("result: " + condition);
		else
			StdOut.println("result: " + condition); 
	}
}