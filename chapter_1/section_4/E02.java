/******************************************************************************
 *  Compilation:  javac E02
 *  Execution:    java E02 
 *  Dependencies: StringIndexOutOfBoundsException
 *  
 *  Description: Modify ThreeSum to work properly even when the int values are 
 *  so large that adding two of them might cause overflow.
 *
 *  Example execution:
 *  java E02 1Kints.ext
 *  70
 ******************************************************************************/
package chapter_1.section_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.math.BigInteger;


/**
 * @author jefoliva
 */

public class E02 {
    public static int count(int[] a)
    { // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    BigInteger sum = new BigInteger("0");
                    sum = sum.add(BigInteger.valueOf(a[i]));
                    sum = sum.add(BigInteger.valueOf(a[j]));
                    sum = sum.add(BigInteger.valueOf(a[k]));

                    if(sum.intValue() == 0)
                        cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
         int[] a = new In(args[0]).readAllInts();
         StdOut.println(count(a));
    }
}