/******************************************************************************
 *  Compilation:  javac E03.java
 *  Execution:    java E03 N min max
 *  Dependencies: StdOut StdRandom StdDraw StdPoint2D Point2D Interval1D Inteval2D
 *
 *  Description: 1.2.3 Write an Interval2D client that takes command-line arguments N, 
 *  min, and max and generates N random 2D intervals whose width and height are uniformly distributed
 *  between min and max in the unit square. Draw them on StdDraw and print the number
 *  of pairs of intervals that intersect and the number of intervals that are contained in one
 *  another.
 *  
 *  Example execution:
 *  % java E03 10 0 500
 *  Intersected: 19
 *  Contained:   2
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;

/**
* @author Jefoliva
*/
public class E03 {
    public static void main(String[] args) {
        // Make sure there are three arguments in args
        if(args.length != 3) {
            StdOut.println("Usage: java E03 N min max");
            return;
        }

        int N   = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);                
        int max = Integer.parseInt(args[2]);

        StdDraw.setCanvasSize(700, 700);
        StdDraw.setXscale(min*1.1, max*1.1);
        StdDraw.setYscale(min*1.1, max*1.1);

        var intervals   = new Interval2D[N];
        /* In order to know if an interval is contained within another
        * It is not necessary to test if the four points of the small box
        * are inside the larger box, but only test for two diagonal points */
        var leftBottom = new Point2D[N];
        var rightTop   = new Point2D[N];

        // Generate random 2D intervals and store diagonal points
        for(int i = 0; i < N; i++) {
            int xmin = StdRandom.uniform(min, max);
            int xmax = StdRandom.uniform(min, max);
            int ymin = StdRandom.uniform(min, max);
            int ymax = StdRandom.uniform(min, max);
            
            if(xmin > xmax) {
                int temp = xmin;
                xmin = xmax;
                xmax = temp;
            }

            if(ymin > ymax) {
                int temp = ymin;
                ymin = ymax;
                ymax = temp;
            }

            Interval1D xInterval = new Interval1D(xmin, xmax); 
            Interval1D yInterval = new Interval1D(ymin, ymax);

            leftBottom[i] = new Point2D(xmin, ymin);
            rightTop[i]   = new Point2D(xmax, ymax);
            intervals[i]  = new Interval2D(xInterval, yInterval);
            intervals[i].draw(); 
        }

        int intersected = 0;
        int contained   = 0;
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(intervals[i].intersects(intervals[j]))
                    intersected++;

                if(intervals[i].contains(leftBottom[j]) && 
                   intervals[i].contains(  rightTop[j])    )
                    contained++;
            }
        }
        StdOut.println("Intersected: " + intersected);
        StdOut.println("Contained:   " + contained);
    }
}