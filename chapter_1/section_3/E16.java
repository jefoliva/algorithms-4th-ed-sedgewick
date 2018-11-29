/******************************************************************************
 *  Compilation:  javac E16 
 *  Execution:    java E16  
 *  Dependencies: StdOut StdIn Date Queue
 *	
 *  Description: 1.3.16 UsingreadInts()on page 126 as a model, write a static methodreadDates()for
 *  Datethat reads dates from standard input in the format specified in the table on page 119
 *  and returns an array containing them
 *
 *  Example execution: 
 *  % java E16
 *  11/22/2018 11/23/2018 11/24/2018 11/25/2018 11/26/2018
 *  [ctrl-z]
 *  Results:
 *  11/22/2018 11/23/2018 11/24/2018 11/25/2018 11/26/2018
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Queue;

/**
 * @author Jefoliva
 */

public class E16 {
  public static Date[] readDates() {
    Queue<Date> q = new Queue<>();
    
    while(!StdIn.isEmpty()) {
      String item = StdIn.readString();
      q.enqueue(new Date(item));
    }

    Date[] d = new Date[q.size()];
    int N = d.length;

    for(int i = 0; i < N; i++)
      d[i] = q.dequeue();

    return d; 
  }

  public static void main(String[] args) {
    Date[] dates = readDates();

    StdOut.println("Results:");
    for(Date d : dates)
      StdOut.print(d + " ");
    StdOut.println();
  }
}