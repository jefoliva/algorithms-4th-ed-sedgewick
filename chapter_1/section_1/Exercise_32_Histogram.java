/******************************************************************************
 *  Compilation:  javac Exercise_32_Histogram.java
 *  Execution:    java Exercise_32_Histogram < doubleValues.txt N l r
 *  Dependencies: StdOUt StdIn StdDraw StdStats
 *  
 *  Sample exec: java Exercise_32_Histogram 9 10.0 90.0  < doubleValues.txt

 *  Description: 1.1.32 Histogram. Suppose that the standard input stream is a sequence of double
 *  values. Write a program that takes an integer N and two double values l and r from the
 *  command line and uses StdDraw to plot a histogram of the count of the numbers in the
 *  standard input stream that fall in each of the N intervals defined by dividing (l , r) into
 *  N equal-sized intervals.
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author Jefoliva
 */

class Exercise_32_Histogram {

   // Find to which interval the key belongs, return the index
    public static int  findInterval(double key, double[] a, double interval) {
      // If key is not between the interval range, return -1
     if(key < a[0] || key > (a[a.length-1] + interval))
         return -1;

      int lo = 0;
      int hi = a.length-1;
      int mid;

      while(lo <= hi) {
         mid = lo + (hi - lo) / 2;
         if      (key % a[mid] < interval) return mid;
         else if (key < a[mid]) hi = mid - 1;
         else if (key > a[mid]) lo = mid + 1;
      }

      return -1;
   }

   public static void main(String[] args) {
      if(args.length != 3) {
           StdOut.println("Usage: Exercise_32_Histogram N l r < doubleValues.txt");
           return;
      }

       int      N = Integer.parseInt(args[0]);
       double   l = Double.parseDouble(args[1]);
       double   r = Double.parseDouble(args[2]);
       double   intervalLength = (r - l) / N;  
       double[] intervals = new double[N];
       int[]    freq = new int[N];

      // Assigns the N  equal-sized intervals to array
      for(int i = 0; i < N; i++) 
         intervals[i] = l + intervalLength * i;      

      while(!StdIn.isEmpty()) {
         double key = StdIn.readDouble();
         int    index = findInterval(key, intervals, intervalLength);

         if(index != -1)
            freq[index]++;
      }

      // Find the max frequency
      int maxFreq =  StdStats.max(freq);

      StdDraw.setCanvasSize(600, 700);
      StdDraw.setXscale(l- Math.floor(intervalLength), r+1);
      StdDraw.setYscale(0, maxFreq+6);

      // Display inteval labels
      for (int i = 0; i < N; i++) 
        StdDraw.text(intervals[i]-intervalLength/2, maxFreq+3, (Math.round(intervals[i] * 100d) / 100d) + "");
      
      // Display bars
      for(int i = 0; i < N; i++) 
         StdDraw.filledRectangle(intervals[i], 0, intervalLength/2.2, freq[i]);      
   }
}