/******************************************************************************
 *  Compilation:  javac E42_CopyStack 
 *  Execution:    java E42_CopyStack  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.41 Copy a queue. Create a new constructor so that
 *  Queue<Item> r = new Queue<Item>(q);
 *  makes r a reference to a new and independent copy of the queue q. You should be 
 *  able to push and pop from either q or r without influencing the other. Hint : 
 *  Delete all of the elements from q and add these elements to both q and r
 *
 *  Example execution:
 *  % java E42_CopyStack 
 *  s contents:   h g f e d c b a
 *  t contents:   h g f e d c b a
 *  s.pop():      g f e d c b a
 *  s.pop():      f e d c b a
 *  t.pop():      g f e d c b a
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E42_CopyStack {
	public static void main(String[] args) {
		Stack.main(args);
	}
}

class Stack<Item> implements Iterable<Item> {
	private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public Stack() {}

    /**
     *  This constructor makes the current Stack instance an
     *  independent copy of 'q'
     */
    public Stack(Stack<Item> s) {
        Stack<Item> temp = new Stack<Item>();
        for(Item item : s)
            temp.push(item);

        for(Item item : temp)
            push(item);
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
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
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

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    public static void main(String[] args) {
        var s = new Stack<String>();

        for(int i = 0; i < 8; i++)
            s.push(String.valueOf((char) ('a'+i)));

        var t = new Stack<String>(s);

        StdOut.print("s contents:   ");
        for(String item : s) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("t contents:   ");
        for(String item : t) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("s.pop():      ");
        s.pop();
        for(String item : s) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("s.pop():      ");
        s.pop();
        for(String item : s) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("t.pop():      ");
        t.pop();
        for(String item : t) 
            StdOut.print(item + " ");
        StdOut.println();
    }
}