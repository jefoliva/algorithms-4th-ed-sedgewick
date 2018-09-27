/**************************************************************************************************
 *  Compilation:  javac Exercise_11.java
 *  Execution:    java Exercise_11
 *  Dependencies: StdOUt.java
 *
 *  Description: 1.1.11 Write a code fragment that prints the contents of a two-dimensional boolean
 *	array, using * to represent true and a space to represent false. Include row and column
 *	numbers.
 **************************************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

public class Exercise_11 {
	public static void displayBooleanMatrix(boolean[][] arr) {
		int rows = arr.length;
		int columns = arr[0].length;

		// Print column numbers
		for (int i = 0; i < columns ; i++ ) {
			if(i == 0) StdOut.print("   ");
			StdOut.printf("%2d ", i);
		}
		StdOut.println();

		for (int i = 0; i < rows; i++ ) {		
			StdOut.printf("%2d", i);		// print row numbers

			for (int j = 0; j < columns; j++ ) {		
				if ( arr[i][j] == true) StdOut.print("  *");
				else 					StdOut.print("   ");
			}
			StdOut.println();
		}
	}

	public static void main(String[] args) {
		boolean[][] booleanArr = {
			{true, false, false, false, true, false, true},
			{false, false, true, true, false, false, true},
			{true, true, true, true,  false, true, false},
			{true, false, false, false, true, false, true},
			{false, false, true, true, false, false, true}
		};

		displayBooleanMatrix(booleanArr);
	}
}