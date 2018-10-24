/******************************************************************************
 *  Compilation:  javac E02.java
 *  Execution:    java E02 N
 *  Dependencies: StdOUt.java StdIn.java StdPoint2D.java
 *
 *  Description: 1.2.2 Write an Interval1D client that takes an int value N as command-line 
 *  argument, reads N intervals (each defined by a pair of double values) from standard input,
 *  and prints all pairs that intersect
 *  
 * % java E02 5
 *   Intervals pairs that intersect
 *   [0.081, 0.911] and [0.324, 0.727]
 *   [0.081, 0.911] and   [0.75, 0.75]
 *   [0.081, 0.911] and [0.288, 0.288]
 *   [0.081, 0.911] and  [0.216, 0.68]
 *   [0.324, 0.727] and  [0.216, 0.68]
 *   [0.288, 0.288] and  [0.216, 0.68]
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Interval1D;

/**
 * @author Jefoliva
 */
public class E02 {
	public static void main(String[] args) {
        if(args.length != 1) {
            StdOut.println("Usage: java Exercise_01 N");
            return;
        }

        int N = Integer.parseInt(args[0]);
        var intervals = new Interval1D[N];

        // Generate intervals
        for(int i = 0; i < N; i++) {
            double x0 = StdRandom.uniform();
            double x1 = StdRandom.uniform();

            // Set precision to 4 decimals
            x0 = (double) Math.round(x0 * 1000d) / 1000d;
            x1 = (double) Math.round(x1 * 1000d) / 1000d;
            
            if(x0 > x1) {
                double temp = x1;
                x0 = x1;
                x1 = temp;

            }
            intervals[i] = new Interval1D(x0, x1);
        }

        
        StdOut.println("Intervals pairs that intersect");
        for(int i = 0; i < N-1; i++) 
            for(int j = i+1; j < N; j++)
                if (intervals[i].intersects(intervals[j]))
                    StdOut.printf("%-14s and %14s\n", intervals[i], intervals[j]);
	}
}