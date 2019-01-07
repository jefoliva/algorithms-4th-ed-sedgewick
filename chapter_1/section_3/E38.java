/******************************************************************************
 *  Compilation:  javac E38 
 *  Execution:    java E38  
 *  Dependencies: StdOut Queue
 *  
 *  Description: 1.3.38 Delete kth element. Implement a class that supports the 
 *  following API:
 *  public class GeneralizedQueue<Item>
 *  GeneralizedQueue()      create an empty queue
 *  boolean isEmpty()       is the queue empty?
 *  void insert(Item x)     add an item
 *  Item delete(int k)      delete and return the kth least recently inserted item
 *  
 *  First, develop an implementation that uses an array implementation, and then 
 *  develop one that uses a linked-list implementation. Note : the algorithms 
 *  and data structures that we introduce in Chapter 3 make it possible to 
 *  develop an implementation that can guarantee that both insert() and delete()
 *  take time prortional to the logarithm of the number of items in the queue
 *  
 *  Example execution:
 *  % java E38 
 *  ARRAY IMPLEMENTATION:
 *  Queue contents: 0 1 2 3 4 5 6 7 8
 *  insert(9):      0 1 2 3 4 5 6 7 8 9
 *  delete(0th)     1 2 3 4 5 6 7 8 9
 *  delete(8th)     1 2 3 4 5 6 7 8
 *  delete(4th)     1 2 3 4 6 7 8
 *
 *  LINKED LIST IMPLEMENTATION:
 *  Queue contents: 0 1 2 3 4 5 6 7 8
 *  insert(9):      0 1 2 3 4 5 6 7 8 9
 *  delete(0th)     1 2 3 4 5 6 7 8 9
 *  delete(8th)     1 2 3 4 5 6 7 8
 *  delete(4th)     1 2 3 4 6 7 8
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E38 {
    public static void main(String[] args) {
        ArrayGeneralizedQueue.main(args);
        GeneralizedQueue.main(args);
    }
}

class ArrayGeneralizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N;
    private int last;

    ArrayGeneralizedQueue() {
        q = (Item[]) new Object[2];
        N = 0;
        last = 0;
    }

    public boolean isEmpty() { return N == 0; }

    public void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for(int i = 0; i < q.length; i++)
            temp[i] = q[i];
        q = temp;
    }

    public void insert(Item item) {
        if(N == q.length) resize(q.length*2);
        q[N++] = item;
    }

    public Item delete(int k) {
        if      (isEmpty())         throw new NoSuchElementException("Queue underflow");
        else if (k < 0 || k >= N)   throw new NoSuchElementException("Invalid index");

        Item item = q[k];
        q[k] = null;        // Avoid loittering

        // Move array to left unless it is the last element
        if(k != N-1)
            moveToLeft(k);
        N--;
        if(N <= q.length/4) resize(q.length/2);
        return item;
    }

    public void moveToLeft(int k) {
        for(int i = k; i < N; i++) {
            q[i] = q[i+1];
        }
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        int i = 0;

        public boolean hasNext() { return i < N; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            return q[i++];
        }
    }

    public static void main(String[] args) {
        var gqueue = new ArrayGeneralizedQueue<Integer>();

        for(int i = 0; i < 9; i++)
            gqueue.insert(i);

        StdOut.println("ARRAY IMPLEMENTATION:");
        StdOut.print("Queue contents: ");
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("insert(9):      ");
        gqueue.insert(9);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("delete(0th)     ");
        gqueue.delete(0);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("delete(8th)     ");
        gqueue.delete(8);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("delete(4th)     ");
        gqueue.delete(4);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();
    }
}

class GeneralizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public int size() { return N; }
    public boolean isEmpty() { return first == null; }

    public void insert(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item delete(int k) {
        if      (isEmpty())         throw new NoSuchElementException("Queue underflow");
        else if (k < 0 || k >= N)   throw new NoSuchElementException("Invalid index");

        Node current = first;
        Item item;

        // If it is the 1st element
        if(k == 0) {
            item = first.item;
            first = first.next;
        } else {
            for(int i = 0; i != k-1; i++) 
                current = current.next; 

            item = current.next.item;
            current.next = current.next.next;
            // If it is the last element in the list update the last reference
            if(current.next == null) last = current;
        }
        N--;
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
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        var gqueue = new GeneralizedQueue<Integer>();

        for(int i = 0; i < 9; i++)
            gqueue.insert(i);

        StdOut.println("\nLINKED LIST IMPLEMENTATION:");
        StdOut.print("Queue contents: ");
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("insert(9):      ");
        gqueue.insert(9);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("delete(0th)     ");
        gqueue.delete(0);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("delete(8th)     ");
        gqueue.delete(8);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("delete(4th)     ");
        gqueue.delete(4);
        for(int item : gqueue) 
            StdOut.print(item + " ");
        StdOut.println();
    }
} 