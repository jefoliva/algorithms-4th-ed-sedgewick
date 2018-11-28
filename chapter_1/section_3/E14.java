/******************************************************************************
 *  Compilation:  javac E14 
 *  Execution:    java E14  
 *  Dependencies: StdOut StdIn
 *	
 *  Description: 1.3.10 Write a filter InfixToPostfix that converts an arithmetic  
 *  expression from infix to postfix
 *
 *  Example execution: 
 *  % java E14
 *  FixedArrayQueueOfStrings (initial size: 3)
 *  Stack overflow.
 *  a b c
 *  ResizingArrayQueueOfStrings (initial size: 3)
 *  a b c d
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stack;

/**
 * @author Jefoliva
 */

public class E14 {
  public static void main(String[] args) {
    var q1 = new FixedArrayQueueOfStrings(3);
    var q2 = new ResizingArrayQueueOfStrings(3);
    
    // Fixed queue
    StdOut.println("FixedArrayQueueOfStrings (initial size: 3)");
    q1.enqueue("a");
    q1.enqueue("b");
    q1.enqueue("c");
    StdOut.println("Try to add other element...");
    q1.enqueue("d");  // stack overflow

    while(!q1.isEmpty())
      StdOut.print(q1.dequeue() + " ");
    StdOut.println();

    StdOut.println();

    // Resize Queue
    StdOut.println("\nResizingArrayQueueOfStrings (initial size: 3)");
    q2.enqueue("a");
    q2.enqueue("b");
    q2.enqueue("c");
    q2.enqueue("d");  // resizes

    while(!q2.isEmpty())
      StdOut.print(q2.dequeue() + " ");
    StdOut.println();
  }
}


class FixedArrayQueueOfStrings {    
  private String q[];
  private int N, last, first; 
     
  public FixedArrayQueueOfStrings(int size) {    
    q = new String[size+1];  
    last = first = 0;    
  } 

  public boolean isEmpty() { return N == 0; }
  public int size() { return  N; }
  
  public void enqueue(String item) {    
    /* Queue is full if either last is one less than 
       first, or if last is at the end of the array 
       and first is at the beginning. */ 
    if(last+1==first | ((last==q.length-1) & (first==0))) {
      StdOut.println("Stack overflow.");
      return;  
    }
          
    q[last++] = item;  
    N++;  
    if(last==q.length) last = 0; // loop back 

  }    
    
  public String dequeue() {    
    if(first == last) 
      return "Stack undeflow"; 

    String item = q[first++];
    if(first==q.length) first = 0; // loop back 
    N--;
    return item;    
  }    
}

class ResizingArrayQueueOfStrings {    
  private String q[];  
  private int N;  
  private int first;  
  private int last;  
      
  public ResizingArrayQueueOfStrings(int size) {    
    q = new String[size]; // allocate memory for queue    
    last = first = 0;    
  }

  public boolean isEmpty() { return N == 0; }
  public int size() { return  N; }
       
  public void enqueue(String item) {    
    if(last==q.length)  
      resize(q.length * 2);
    
     N++;
     q[last++] = item;    
  }    

  public String dequeue() {    
    if(first == last) 
      throw new RuntimeException("Stack undeflow");  
    
    N--;
    return q[first++];    
  }

  private void resize(int max) {
      String t[] = new String[max]; 
 
      for(int i=0; i < q.length; i++) 
        t[i] = q[i]; 
 
      q = t; 
  }
}