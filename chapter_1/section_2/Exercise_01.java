/******************************************************************************
 *  Compilation:  javac Exercise_01.java
 *  Execution:    java Exercise_01 N
 *  Dependencies: StdOUt.java StdIn.java StdPoint2D.java
 *
 *  Description: Write a Point2D client that takes an integer value N from the 
 *  command line, generates N random points in the unit square, and computes the 
 *  distance separating the closest pair of points.
 *  
 * % java Exercise_01 50
 * Closest Points are: (38.93, 92.59) and (39.47, 91.76)
 * Distance: 0.988 
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Point2D;
/**
 * @author Jefoliva
 */
public class Exercise_01 {
	public static void main(String[] args) {
        if(args.length != 1) {
            StdOut.println("Usage: java Exercise_01 N");
            return;
        }

        int N = Integer.parseInt(args[0]);
        double maxscale = 100;
        // Set canvas
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, maxscale);
        StdDraw.setYscale(0, maxscale);
        StdDraw.setPenRadius(0.008);
        StdDraw.enableDoubleBuffering();

        // Generate array of random points and plot them
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform(0.0, maxscale);
            double y = StdRandom.uniform(0.0, maxscale);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        // Max possible distance between points
        double closestDist = Math.sqrt(maxscale*2 + maxscale*2);
        Point2D[] closestPair = new Point2D[2];

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Point2D pointA = new Point2D(points[i].x(), points[i].y());
                Point2D pointB = new Point2D(points[j].x(), points[j].y());
                
                if(pointA.equals(pointB))
                    break;

                double currentPointsDist = points[i].distanceTo(points[j]);
                
                if(currentPointsDist < closestDist) {
                    closestDist = currentPointsDist;
                    closestPair[0] = pointA;
                    closestPair[1] = pointB;
                }
            }
        }

        // Draw line to closes point
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        closestPair[0].drawTo(closestPair[1]);
        StdDraw.show();

        // SHOW RESUTS IN CONSOLE
        System.out.println("Closest Points are: " + closestPair[0] + " and " + closestPair[1]);
        System.out.println("Distance: " + closestDist );
	}
}