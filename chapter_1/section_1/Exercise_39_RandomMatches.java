/******************************************************************************
 *  Compilation:  javac Exercise_39_RandomMatches
 *  Execution:    java Exercise_39_RandomMatches T
 *  Dependencies: StdOut StdRandom
 *	
 *  Description: 1.1.39 Random matches. Write a BinarySearch client that takes an int value T as
 *  command-line argument and runs T trials of the following experiment for N = 10^3, 10^4,
 *  10^5, and 10^6: generate two arrays of N randomly generated positive six-digit int values,
 *  and find the number of values that appear in both arrays. Print a table giving the average
 *  value of this quantity over the T trials for each value of N
 *
 * % java Exercise_39_RandomMatches 4
 * Trial        N^3        N^4        N^5      N^6
 *  1            1        127      10553     670645
 *  2            3        114      10481     670843
 *  3            0        125      10556     670574
 *  4            0        112      10633     671158
 * Average     1.00     119.50   10555.75    670805.00
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

/**
 * @author Jefoliva
 */

public class Exercise_39_RandomMatches {
    public static int indexOf(int key, int[] a) {
        int lo = 0;
        int hi = a.length-1;

        while(lo <= hi) {
            int mid = lo + (hi-lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else    return mid;
        }
        return -1;
    }

    /**
    * @param N the size of both arrays
    * @return the number of equals values in both arrays
    */
    public static int runExperiment(int N) {
        int[] a = genRandomArray(N);
        int[] b = genRandomArray(N);
        int length = a.length;
        int count = 0;

        for(int i = 0; i < length; i++) {
        // increment count if value is present in both arrays
            if(indexOf(a[i], b) != -1)
                count++;  
        }
        return count;
    }

    // This method generates an array of size N
    // and N ranomly generated int values of 6 digits
    public static int[] genRandomArray(int N) {
        int[] arr = new int[N];
        int length = arr.length;
        // min and max generate only values of 6 digits
        int min = 100000;
        int max = 1000000;
        for(int i = 0; i < length; i++) 
            arr[i] = StdRandom.uniform(min, max);

        Arrays.sort(arr);
        // Return sorted array
        return arr;
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        int N3 = 1000;
        int N4 = 10000;
        int N5 = 100000;
        int N6 = 1000000;
        double[] sum = new double[4];
        double[] avg = new double[4];

        StdOut.printf("%5s   %8s   %8s   %8s   %8s \n", "Trial", "N^3", "N^4", "N^5", "N^6");
        for(int i = 0; i < T; i++) {
            int countN3 = runExperiment(N3);
            int countN4 = runExperiment(N4);
            int countN5 = runExperiment(N5);
            int countN6 = runExperiment(N6);
            // Sum results of each trial
            sum[0] += countN3;
            sum[1] += countN4;
            sum[2] += countN5;
            sum[3] += countN6;
            // Display results of each trial
            StdOut.printf("%3d     %8d   %8d", i+1, countN3, countN4);
            StdOut.printf("   %8d   %8d\n", countN5, countN6);
        }
        // Compute averages
        for(int i = 0; i < sum.length; i++)
            avg[i] = sum[i] / T;

        StdOut.printf("Average %8.2f   %8.2f   %8.2f     %8.2f", avg[0], avg[1], avg[2], avg[3]);
    }
}