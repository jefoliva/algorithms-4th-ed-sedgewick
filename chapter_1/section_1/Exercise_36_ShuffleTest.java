/******************************************************************************
 *  Compilation:  javac Exercise_36_SuffleTest
 *  Execution:    java Exercise_36_SuffleTest M N
 *  Dependencies: StdOut StdRandom
 *	
 *  Description: 1.1.36 Empirical shuffle check. Run computational experiments to check that our
 *  shuffling code on page 32 works as advertised. Write a program ShuffleTest that takes
 *  command-line arguments M and N, does N shuffles of an array of size M that is initialized with 
 *  a[i] = i before each shuffle, and prints an M-by-M table such that row i
 *  gives the number of times i wound up in position j for all j. All entries in the array
 *  should be close to N/M.
 *
 *  % java Exercise_36_ShuffleTest 4 1000
 * 	 258  257  235  250
 *	 248  242  264  246
 *	 243  244  243  270
 *	 251  257  258  234
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Jefoliva
 */

public class Exercise_36_ShuffleTest {

	public static void shuffle(double[] a) {
		int N = a.length;

		for (int i = 0; i < N; i++) { 
		// Exchange a[i] with random element in a[i..N-1]
			int r = i + StdRandom.uniform(N-i);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

    public static void main(String[] args) {
    	if(args.length != 2) {
    		StdOut.println("Usage: java Exercise_36_SuffleTest M N");
    		return;
    	}

    	int M = Integer.parseInt(args[0]);
    	int N = Integer.parseInt(args[1]);

    	double[] arr = new double[M];
    	int[][] positions = new int[M][M]; 

    	// Initialize array 
    	for(int i = 0; i < arr.length; i++)
    		arr[i] = i;

    	// Run shuffles
    	for(int i = 0; i < N; i++) {
    		shuffle(arr);

    		for(int j = 0; j < arr.length; j++) 
    			positions[j][ (int) arr[j] ]++; 		
    	}

    	// Display positions
    	for (int x[] : positions) {
    		for (int y : x) 
    			StdOut.printf("%4d ", y);
    		StdOut.println();
    	}
    }
}