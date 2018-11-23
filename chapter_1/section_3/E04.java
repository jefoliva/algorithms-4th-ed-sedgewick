/******************************************************************************
 *  Compilation:  javac E04 
 *  Execution:    java E04  
 *  Dependencies: StdOut StdIn
 *	
 *  Description: 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
 *  and uses a stack to determine whether its parentheses are properly balanced. For example, your program 
 *  should print true for [()]{}{[()()]()} and false for [(])
 *
 *  Example execution: 
 *  % java E04
 *  [()]{}{[()()]()}
 *  true
 *  [(])
 *  false
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;

/**
 * @author Jefoliva
 */

public class E04 {
  public static boolean isMatchingPair(char a, char b) {
    if      (a == '(' && b == ')') return true;
    else if (a == '{' && b == '}') return true;
    else if (a == '[' && b == ']') return true;
    
    return false;
  }

	public static void main(String[] args) {
    Stack<Character> s = new Stack<Character>();
    boolean isBalanced = true;

    if(StdIn.isEmpty()) {
      StdOut.println("Empty input. Try a valid expression");
      return;
    }

    while(!StdIn.isEmpty()) {
      char item = StdIn.readChar();

      if(item == '(' || item == '{' || item == '[')
        s.push(item);
      else if(item == ')' || item == '}' || item == ']') {
        // If it finds an end parenthesis without a matching pair
        // it is not balanced
        if(s.isEmpty()) {
          isBalanced = false;
          break;
        }

        // if popped item is not matching pair with current item
        // break loop
        if(!isMatchingPair(s.pop(), item)) {
          isBalanced = false;
          break;
        }     
      } 
    }

    // If stack is empty and isBalance evaluates to true, expression
    // is balanced. If not empty it means there is a starting parenthesis 
    // without a matching pair.
    StdOut.print("is balanced: ");
    StdOut.println(s.isEmpty() && isBalanced ? true : false );
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
    private Node current = first;

    public boolean hasNext() { return current != null; }
    public Item next() {
      Item item = current.item;
      current = current.next;
      return item;
    }
    public void remove() { throw new UnsupportedOperationException(); }
  }

  
}