/******************************************************************************
 *  Compilation:  javac E16_Rational 
 *  Execution:    java E16_Rational  
 *  Dependencies: StdOut 
 *	
 *  Description: 1.2.16 Rational numbers. Implement an immutable data type Rational for rational
 *  numbers that supports addition, subtraction, multiplication, and division.
 *
 *  Example execution: 
 *  % java E16_Rational
 *  Let:
 *  r1 = 9/16
 *  r2 = 3/32
 *  r1 plus r2:    21/32
 *  r1 minus r2:   15/32
 *  r1 times r2:   27/512
 *  r1 divides r2: 6/1
 *  r1 equals r2:  false
 ******************************************************************************/
// package ch01.section_2;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;
/**
 * @author Jefoliva
 */

public class E04 {
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
    private Node current;

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
  	boolean isBalanced = true;

  	while(!StdIn.isEmpty()) {
  		String item = StdIn.readString();

  		if(item.equals("(") || item.equals("{") || item.equals("["))
  			s.push(item);
  		else if(item.equals(")")) {
  			String str = s.pop();

  			if(!str.equals("(")) {
  				isBalanced = false;
  				break;
  			}
  		}
  		else if(item.equals("}")) {
  			String str = s.pop();

  			if(!str.equals("{")) {
  				isBalanced = false;
  				break;
  			}
  		}
  		else if(item.equals("]")) {
  			String str = s.pop();

  			if(!str.equals("[")) {
  				isBalanced = false;
  				break;
  			}
  		}
  	}

  	StdOut.println(isBalanced ? "Is balanced" : "Is not balanced");
  }
}