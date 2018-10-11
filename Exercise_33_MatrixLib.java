/******************************************************************************
 *  Compilation:  javac Exercise_33_MatrixLib.java
 *  Execution:    java Exercise_33_MatrixLib
 *  Dependencies: StdOut.java
 *
 *  Description: Test Client for Matrix class
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;


/**
 * @author Jefoliva
 */

public class Exercise_33_MatrixLib {
    public static void main(String[] args) {
    	StdOut.println("Let:");
        double[] x1 = {2, 1, 0};
        double[] y1 = {4, -2, -1};
	    double[][] a1 = {{12, 34, 45}, {54, 67, 93}, {82, 19, 41}};
	    double[][] b1 = {{80, 10, 68}, {44, 15, 77}, {51, 93, 64}};
        double[] z1;
	    double[][] c1;

        StdOut.println("Vector x: " + Arrays.toString(x1));
        StdOut.println("Vector y: " + Arrays.toString(y1));
        StdOut.println("Matrix a:");
        for (double row[] : a1) 
	    	StdOut.println(Arrays.toString(row));
		StdOut.println("Matrix b:");
		for (double row[] : b1) 
	    	StdOut.println(Arrays.toString(row));

        // Vector dot product
        StdOut.println("\nVECTOR DOT PRODUCT:");
        StdOut.println("x * y: " + Matrix.dot(x1, y1));

	    
	    // matrix-matrix product
	   	c1 = Matrix.mult(a1, b1);
	   	StdOut.println("\nMATRIX MATRIX PRODUCT:");

	    StdOut.println("a x b:");
	    for (double row[] : c1) 
	    	StdOut.println(Arrays.toString(row));

	    // Transpose
	    StdOut.println("\nTRANSPOSE:");
	    StdOut.println("Transpose a1:");
	    c1 = Matrix.transpose(a1);
	    for (double row[] : c1) 
	    	StdOut.println(Arrays.toString(row));

	    // matrix-vector product
	   	StdOut.println("\nMATRIX-VECTOR PRODUCT:");
	   	StdOut.println("a X x:");
	   	z1 = Matrix.mult(a1, x1);
	    StdOut.println(Arrays.toString(z1));

	    // vector-matrix product
	   	StdOut.println("\nVECTOR-MATRIX PRODUCT:");
	   	StdOut.println("y X b:");
	   	z1 = Matrix.mult(y1, b1);
	    StdOut.println(Arrays.toString(z1));
    }
}