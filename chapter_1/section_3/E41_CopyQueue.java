/******************************************************************************
 *  Compilation:  javac E41_CopyQueue 
 *  Execution:    java E41_CopyQueue  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.41 Copy a queue. Create a new constructor so that
 *  Queue<Item> r = new Queue<Item>(q);
 *  makes r a reference to a new and independent copy of the queue q. You should be 
 *  able to push and pop from either q or r without influencing the other. Hint : 
 *  Delete all of the elements from q and add these elements to both q and r
 *
 *  Example execution:
 *  % java E41_CopyQueue 
 *  q contents:  0 1 2 3 4
 *  r contents:  0 1 2 3 4
 *  q.dequeue(): 1 2 3 4
 *  q.dequeue(): 2 3 4
 *  r.dequeue(): 1 2 3 4
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E41_CopyQueue {
	public static void main(String[] args) {
		Queue.main(args);
	}
}

class Queue<Item> implements Iterable<Item> {
	private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public Queue() {}

    /**
     *  This constructor makes the current Queue instance an
     *  independent copy of 'q'
     */
    public Queue(Queue<Item> q) {
        for(Item item : q)
            enqueue(item);
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else          oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty()) last = null;  // avoid loittering
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item  item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        var q = new Queue<Integer>();

        // Populate queue
        for(int i = 0; i < 5; i++)
            q.enqueue(i);

        // Independent copy of queue q
        var r = new Queue<Integer>(q);

        StdOut.print("q contents:  ");
        for(int i : q) 
            StdOut.print(i + " ");
        StdOut.println();

        StdOut.print("r contents:  ");
        for(int i : r) 
            StdOut.print(i + " ");
        StdOut.println();

        StdOut.print("q.dequeue(): ");
        q.dequeue();
        for(int i : q) 
            StdOut.print(i + " ");
        StdOut.println();

        StdOut.print("q.dequeue(): ");
        q.dequeue();
        for(int i : q)  
            StdOut.print(i + " ");
        StdOut.println();

        StdOut.print("r.dequeue(): ");
        r.dequeue();
        for(int i : r) 
            StdOut.print(i + " ");
        StdOut.println();
    }
}