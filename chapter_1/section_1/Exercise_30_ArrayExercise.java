/******************************************************************************
 *  Compilation:  javac Exercise_30.java
 *  Execution:    java Exercise_30
 *  Dependencies: StdOUt.java
 *
 *  Description: 1.1.30 Array exercise. Write a code fragment that creates an N-by-N boolean array
 *  a[][] such that a[i][j] is true if i and j are relatively prime (have no common factors),
 *  and false otherwise.
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

class Exercise_30_ArrayExercise {
    // Euclid algorithm
    private static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    // If gcd(i, j) == 0 or 1, it means that both are relatively prime, false otherwise
    public static boolean isRelativelyPrime(int i, int j) {
       return gcd(i, j) == 1 || gcd(i, j) == 0 ? true : false;
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            StdOut.println("Usage: Exercise_30_ArrayExercise N");
            return;
        }

        int N = Integer.parseInt(args[0]);
        boolean[][] booleanArr = new boolean[N][N];

        // Test condition
        for(int i = 0; i < N; i++)  
            for(int j = 0; j < N; j++)
                booleanArr[i][j] = isRelativelyPrime(i, j);
        
        // Display matrix
        for (boolean[] x : booleanArr) {
            for (boolean y : x) 
                System.out.printf("%5s ", y);
            System.out.println();
        }
    }
}