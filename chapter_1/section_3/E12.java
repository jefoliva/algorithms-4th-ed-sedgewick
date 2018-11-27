/******************************************************************************
 *  Compilation:  javac E12 
 *  Execution:    java E12 
 *  Dependencies: StdOut StdIn
 *	
 *  Description: 1.3.12 Write an iterable Stack client that has a static method copy() that takes a stack
 *  of strings as argument and returns a copy of the stack. Note : This ability is a prime
 *  example of the value of having an iterator, because it allows development of such functionality
 *  without changing the basic API.
 *
 *  Example execution: 
 *  % java E12
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
import java.util.NoSuchElementException;

/**
 * @author Jefoliva
 */

public class E12 {
  public static Stack<String> copy(Stack<String> s) {
    Stack<String> temp = new Stack<>();
    Stack<String> copy = new Stack<>();

    // Temp stores each element of s in reverse order
    for(String item : s)
      temp.push(item);  

    // Reversing the order of temp gives a exact copy of s
    for(String item : temp)
      copy.push(item);

    return copy;
  }

  public static void main(String[] args) {
    Stack<String> st1 = new Stack<>();
    st1.push("a");
    st1.push("b");
    st1.push("c");

    Stack<String> copy = copy(st1);

    StdOut.println("Contents of st1: ");
     for(String item : st1)
      StdOut.print(item + " ");
    StdOut.println();

    StdOut.println("Contents of copy: ");
    for(String item : copy)
      StdOut.print(item + " ");
    StdOut.println();
  }
}