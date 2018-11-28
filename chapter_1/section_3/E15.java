/******************************************************************************
 *  Compilation:  javac E15 
 *  Execution:    java E15  
 *  Dependencies: StdOut StdIn Queue
 *	
 *  Description: 1.3.15 Write a Queue client that takes a command-line argument k and prints the kth
 *  from the last string found on standard input (assuming that standard input has k or
 *  more strings)
 *
 *  Example execution: 
 *  % java E15 3
 * It was the best of times
 * [ctrl-z]
 * kth: best
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Queue;

/**
 * @author Jefoliva
 */

public class E15 {
  public static void main(String[] args) {
    if(args.length != 1)  {
       StdOut.println("Usage: java E15 N");
       return;
    }

    int N = Integer.parseInt(args[0]);
    Queue<String> queue = new Queue<>();

    while(!StdIn.isEmpty()) {
      String str = StdIn.readString();
      queue.enqueue(str);
    }

    if(N > queue.size()) {
      StdOut.println("Error: input should have at least N strings");
      return;
    }

    int length = queue.size()-N;
    for(int i = 0; i < length; i++)
      queue.dequeue();  

    StdOut.println("kth: " + queue.dequeue());
  }
}