/******************************************************************************
 *  Compilation:  javac E37_JosephusProblem 
 *  Execution:    java E37_JosephusProblem  7 2
 *  Dependencies: StdOut Queue
 *  
 *  Description: 1.3.37 Josephus problem. In the Josephus problem from antiquity,
 *  N people are in dire straits and agree to the following strategy to reduce the
 *  population. They arrange themselves in a circle (at positions numbered from 0 to
 *  N–1) and proceed around the circle, eliminating every Mth person until only one 
 *  person is left. Legend has it that Josephus figured out where to sit to avoid
 *  being eliminated. Write a Queue client Josephus that takes N and M from the
 *  command line and prints out the order in which people are eliminated (and thus
 *  would show Josephus where to sit in the circle).
 *  
 *  Example execution:
 *  % java E37_JosephusProblem 7 2
 *   1 3 5 0 4 2 6
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

/**
 * @author jefoliva
 */

public class E37_JosephusProblem {
    public static void main(String[] args) {
        Josephus.main(args);
    }
}

class Josephus {
    public static void main(String[] args) {
        if(args.length != 2) {
            StdOut.println("Usage: java E35 N M");
            return;
        }

        final int N = Integer.parseInt(args[0]);
        final int M = Integer.parseInt(args[1]);
        Queue<Integer> queue = new Queue<>();

        // Populate queue with N elements
        for(int i = 0 ; i < N; i++) 
            queue.enqueue(i);

        // Remove M-th element until is empty
        while(!queue.isEmpty()) {
            for(int i = 0; i < M-1; i++)
                queue.enqueue(queue.dequeue());
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
    }
}