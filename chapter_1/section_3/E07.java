/******************************************************************************
 *  Compilation:  javac E07 
 *  Execution:    java E07  
 *  Dependencies: StdOut
 *	
 *  Description: 1.3.7 Add a method peek() to Stack that returns the most recently 
 *  inserted item on the stack (without popping it)
 *
 *  Example execution: 
 *  % java E07
 *  Stack content: d c b a
 *  peek: d
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;
/**
 * @author Jefoliva
 */

public class E07 {
	public static void main(String[] args) {
		Stack.main(args);
	}
}

class Stack<Item> implements Iterable<Item> {
	Node first;
	int N;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() { return first == null; }
	public int size() { return N; }
  public Item peek() { return first.item; }

	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Item pop() {
		if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	public Iterator<Item> iterator() {
    	return new ListIterator();
  	}

  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() { return current != null; }
    public Item next() {
      Item item = current.item;
      current = current.next;
      return item;
    }
    public void remove() { throw new UnsupportedOperationException(); }
  }



  public static void main(String[] args) {
  	Stack<String> s = new Stack<String>();
  	
    s.push("a");
    s.push("b");
    s.push("c");
    s.push("d");

    StdOut.print("Stack content: ");
    for(String str : s)
      StdOut.print(str + " ");
    StdOut.println();

    StdOut.println("peek: " + s.peek());
  }
}