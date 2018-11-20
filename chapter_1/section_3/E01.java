/******************************************************************************
 *  Compilation:  javac E01.java
 *  Execution:    java E01
 *  Dependencies: StdOUt.java StdIn.java
 *
 *  Description: Add a method isFull() to FixedCapacityStackOfStrings.
 *  
 * % java E01 
 * Closest Points are: (38.93, 92.59) and (39.47, 91.76)
 * Distance: 0.988 
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author jefoliva
 */

public class E01 {
  public static void main(String[] args) {
    FixedCapacityStackOfStrings.main(args);
  }
}

class FixedCapacityStackOfStrings {
  private String[] a;
  private int N;

  public FixedCapacityStackOfStrings(int cap) 
  { a = new String[cap]; }

  public boolean isEmpty() { return N == 0; }
  public boolean isFull() { return N == a.length; }
  public int size() { return N; }

  public void push(String item)
  { a[N++] = item; }

  public String pop() 
  { return a[--N]; }

  public static void main(String[] args) {
    FixedCapacityStackOfStrings s;
    s = new FixedCapacityStackOfStrings(100);

    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-"))
      s.push(item);
      else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
    }

    StdOut.println("(" + s.size() + " left on stack)");
  }
}