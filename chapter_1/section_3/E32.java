/******************************************************************************
 *  Compilation:  javac E32 
 *  Execution:    java E32  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.32 Steque. A stack-ended queue or steque is a data type 
 *  that supports push, pop, and enqueue. Articulate an API for this ADT. Develop
 *  a linked-list-based implementation.
 *
 *  Example execution:
 *  % java E32 
 *  List contents: d c b
 *  enqueue(a):    d c b a
 *  push(e):       e d c b a
 * 
 *  Print first node
 *  first.item: e
 *  first.next: chapter_1.section_3.Steque$Node@2133c8f8
 *
 *  Print last node
 *  last.item: a
 *  last.next: null
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E32 {
    public static void main(String[] args) {
       Steque.main(args);
    }
}

class Steque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst == null) last = first;
        N++;
    }

    public Item pop() {
        if(isEmpty()) { throw new NoSuchElementException("Steque underflow"); }
        Item item = first.item;
        first = first.next;
        if(first == null) last = first;
        N--;
        return item;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        N++;
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

    public void printFirst() {
        StdOut.println("\nPrint first node");
        StdOut.println("first.item: " + (first != null ? first.item : "null object"));
        StdOut.println("first.next: " + (first != null ? first.next : "null object"));
    }

    public void printLast() {
        StdOut.println("\nPrint last node");
        StdOut.println("last.item: " + (last != null ? last.item : "null object"));
        StdOut.println("last.next: " + (last != null ? last.next : "null object"));
    }

    public static void main(String[] args) {
        Steque<String> steque = new Steque<>();
        steque.push("b");
        steque.push("c");
        steque.push("d");

        StdOut.print("List contents: ");
        for(String item : steque) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("enqueue(a):    ");
        steque.enqueue("a");
        for(String item : steque) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("push(e):       ");
        steque.push("e");
        for(String item : steque) 
            StdOut.print(item + " ");
        StdOut.println();

        steque.printFirst();
        steque.printLast();
    }
}