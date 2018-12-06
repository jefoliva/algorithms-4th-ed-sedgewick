/******************************************************************************
 *  Compilation:  javac E29 
 *  Execution:    java E29  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.29 Write a Queue implementation that uses a circular linked
 *  list, which is the same as a linked list except that no links are null and 
 *  the value of last.next is first whenever the list is not empty. Keep only one
 *  Node instance variable (last).
 *
 *  Example execution:
 *  % java E29 
 *  queue contents: My name is Mud.
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E29 {
    public static void main(String[] args) {
       Queue.main(args);
    }
}

class Queue<Item> implements Iterable<Item> {
    Node last;
    int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return last == null; }
    public int size() { return N; }

    public void enqueue(Item item) {
        if(isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = last;
            N++;
            return;
        } 

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = oldlast.next;
        oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Queue Underflow");

        if(size() == 1) {
            Item item = last.item;
            last = null;
            return item;
        }

        Item item = last.next.item;
        last.next = last.next.next;
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = last.next;
        int size = N;

        public boolean hasNext() { return size != 0; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item  item = current.item;
            current = current.next;
            size--;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        q.enqueue("My");
        q.enqueue("name");
        q.enqueue("is");
        q.enqueue("Mud.");

        StdOut.print("queue contents: ");
        for(String item : q)
            StdOut.print(item + " ");
        StdOut.println();
    }
}