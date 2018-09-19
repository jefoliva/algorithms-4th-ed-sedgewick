/***********************************************************************
 *  Compilation:  javac Exercise_1.java
 *  Execution:    java Exercise_1
 *  Dependencies: StdOut.java
 *
 *  Give the value of each of the following expressions:
 * 		a. ( 0 + 15 ) / 2
 * 		b. 2.0e-6 * 100000000.1
 * 		c. true && false || true && true
 ***********************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
* @author jefoliva
*/

public class Exercise_1 {
	public static void main(String[] args) {

		StdOut.println(( 0 + 15 ) / 2);	
		// Answer: 7

		StdOut.println( 2.0e-6 * 100000000.1);
		// Answer: 200.0000002	

		StdOut.println(true && false || true && true );	
		// Answer: true
	}
}