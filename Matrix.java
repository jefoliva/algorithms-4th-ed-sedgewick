/******************************************************************************
 *  Description: 1.1.33 Matrix library. Write a library Matrix that implements the following API:
 *  public class Matrix
 *  static double     dot(double[] x, double[] y)       vector dot product
 *  static double[][] mult(double[][] a, double[][] b)  matrix-matrix product
 *  static double[][] transpose(double[][] a)           transpose
 *  static double[]   mult(double[][] a, double[] x)    matrix-vector product
 *  static double[]   mult(double[] y, double[][] a)    vector-matrix product
 *
 *  Develop a test client that reads values from standard input and tests all the methods
 ******************************************************************************/
package chapter_1.section_1;

import java.util.Arrays;

public class Matrix {
     //  vector dot product
    static double dot(double[] x, double[] y) {
        if( x.length != y.length) 
            throw new RuntimeException("Vectors should have same length");
    
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += (x[i] * y[i]);  
        return sum;
    }

    // matrix-matrix product
    static double[][] mult(double[][] a, double[][] b) {
        if((a.length != b.length) || (a[0].length != b[0].length)) 
            throw new RuntimeException("Matrices should be squared.");
        int N = a.length;

        double[][] temp = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    temp[i][j] += a[i][k]*b[k][j];
            
        return temp;
    }

   static double[][] transpose(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] temp = new double[rows][cols];

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                temp[i][j] = a[j][i];

        return temp;  
    }

   //  matrix-vector product
    static double[] mult(double[][] a, double[] x) {
        if(a[0].length != x.length) 
            throw new RuntimeException("Number of rows for matrix and lenght of vector differ.");

        double[] resultant = new double[a.length];
        // Fill resultant with zeroes
        Arrays.fill(resultant, 0.0);

        for(int i = 0; i < a.length; i++)
            for(int j = 0; j < a[0].length; j++)
                resultant[i] += a[i][j] * x[j];

        return resultant;
   }

    // vector-matrix product
    static double[]   mult(double[] y, double[][] a) {
         if(a[0].length != y.length) 
            throw new RuntimeException("Number of rows for matrix and lenght of vector differ.");

        double[] resultant = new douvble[a.length];
        // Fill resultant with zeroes
        Arrays.fill(resultant, 0.0);

        for(int i = 0; i < a.length; i++)
            for(int j = 0; j < a[0].length; j++)
                resultant[i] += a[i][j] * y[j];

        return resultant;
    }
}