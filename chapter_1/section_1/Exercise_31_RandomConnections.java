/******************************************************************************
 *  Compilation:  javac Exercise_31_RandomConnections.java
 *  Execution:    java Exercise_31_RandomConnections N p
 *  Dependencies: StdOUt.java StdDraw StdRandom
 *
 *  Description: 1.1.31 Random connections. Write a program that takes as command-line arguments
 * an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size
 * .05 on the circumference of a circle, and then, with probability p for each pair of points,
 * draws a gray line connecting them.
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Jefoliva
 */

class Exercise_31_RandomConnections {
    public static void main(String[] args) {

       if(args.length != 2) {
            StdOut.println("Usage: Exercise_31_RandomConnections N p");
            return;
       }

       int N = Integer.parseInt(args[0]);
       double p = Double.parseDouble(args[1]);

       if(p < 0 || p > 1) {
            StdOut.println("Usage: p should be given a value between 0 and 1");
            return;
       }

       int size = 800;
       StdDraw.setCanvasSize(size, size);
       StdDraw.setXscale(-size/2, size/2);
       StdDraw.setYscale(-size/2, size/2);
       StdDraw.setPenRadius(0.05);
       double radious = size / 2.2;
       // 2 Pi radians divided by N, it gives you the equal space for each pair of points.
       double delta = 2 * Math.PI / N;
       double[] pointsY = new double[N];
       double[] pointsX = new double[N];

        // Store X and Y coordinates an plot them
        for (int i = 0; i < N; i++) {   
            pointsX[i] = radious * Math.sin(i * delta);
            pointsY[i] = radious * Math.cos(i * delta);

            StdDraw.point(pointsX[i], pointsY[i]);
        }

        // With probability p for each pair of points plot a line connecting them
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for(int i = 0; i < N-1; i++) {
            if(StdRandom.bernoulli(p))
                StdDraw.line(pointsX[i], pointsY[i], pointsX[i+1], pointsY[i+1]);
        }

        // with probability p draw a line for first and last point
         if(StdRandom.bernoulli(p))
            StdDraw.line(pointsX[N-1], pointsY[N-1], pointsX[0], pointsY[0]);
   }
}