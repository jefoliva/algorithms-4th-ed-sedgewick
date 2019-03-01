/******************************************************************************
 *  Compilation:  javac E33_Deque
 *  Execution:    java E33_Deque 
 *  Dependencies: StdOut
 *  
 *  Description: Deque implementation
 *
 *  Example execution:
 *  % java Deque
 *  deque contents:   b d e f g
 *  pushLeft(a):      a b d e f g
 *  pushRight(h):     a b d e f g h
 *  popLeft():        b d e f g h
 *  popRight():       b d e f g
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    int N;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    public Node first() { return first; }
    public Node last() { return last; }

    public void pushLeft(Item item) {
        if(isEmpty()) {
            first = new Node();
            first.item = item;
            first.next = null;
            first.prev = null;
            last = first;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            oldfirst.prev = first;  
        }
        N++;
    }   

    public void pushRight(Item item) {
        if(isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = null;
            first = last;
        } else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            oldlast.next = last;
        }
        N++;
    }

    public Item popLeft() {
        if(isEmpty()) throw new NoSuchElementException("List Underflow");
        Item item;
        if(size() == 1) {
            item = first.item;
            first = null;
            last = null;
        } else {
            item = first.item;
            first = first.next;
            first.prev = null;
        }
        N--;
        return item;
    }

    public Item popRight() {
        if(isEmpty()) throw new NoSuchElementException("List Underflow");
        Item item;
        if(size() == 1) {
            item = last.item;
            last = null;
            first = last;
        } else {
            item = last.item;
            last = last.prev;
            last.next = null;
        }
        N--;
        return item;
    }

    public String toString() {
        String out = "";
        for(Item item : this) 
            out += item + " ";
        return out;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;
        private int size = N;

        public boolean hasNext() { return current != null; }
        public void remove() {}
        
        public Item next() { 
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        var deque = new Deque<String>();
        deque.pushRight("b");
        deque.pushRight("d");
        deque.pushRight("e");
        deque.pushRight("f");
        deque.pushRight("g");

        StdOut.print("deque contents:   ");
        for(String item : deque) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("pushLeft(a):      ");
        deque.pushLeft("a");
        for(String item : deque) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("pushRight(h):     ");
        deque.pushRight("h");
        for(String item : deque) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("popLeft():        ");
        deque.popLeft();
        for(String item : deque) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("popRight():       ");
        deque.popRight();
        for(String item : deque) 
            StdOut.print(item + " ");
        StdOut.println();
    }
}