/**************************************************************************************************
 *  Compilation:  javac Exercise_13.java
 *  Execution:    java Exercise_13
 *  Dependencies: StdOUt.java
 *
 *  Description: 1.1.13 Write a code fragment to print the transposition (rows and columns changed)
 *  of a two-dimensional array with M rows and N columns.
 **************************************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

public class Exercise_13 {
	public static int[][] transpose(int[][] arr) {
		int rows = arr.length;
		int columns = arr[0].length;

		int[][] tempArr = new int[columns][rows];

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				tempArr[j][i] = arr[i][j]; 
			}
		}

		return tempArr;
	}

	public static void displayMatrix(int[][] arr) {
		for(int i[] : arr) {
			for(int j : i) 
				StdOut.printf("%4d", j);
			StdOut.println();
		}
	}

	public static void main(String[] args) {
		int[][] intMatrix = {
			{23, 43, 22, 12, 98, 34, 53, 21},
			{54, 34, 32, 11, 10, 59, 88, 13},
			{99, 58, 80, 15, 22, 44, 51, 75},
			{84, 24, 12, 21, 10, 99, 68, 13}
		};

		StdOut.println("Original Matrix:");
		displayMatrix(intMatrix);

		intMatrix = transpose(intMatrix);

		StdOut.println("\nTransposed Matrix:");
		displayMatrix(intMatrix);
	}
}