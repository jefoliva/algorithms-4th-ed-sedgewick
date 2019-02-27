package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class Steque<Item> implements Iterable<Item> {
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

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
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
    }
}