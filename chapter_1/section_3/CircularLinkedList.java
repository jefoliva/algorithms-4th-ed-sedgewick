package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    Node last;
    int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return last == null; }
    public int size() { return N; }

    public void add(Item item) {
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

    public Item remove() {
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
        var list = new CircularLinkedList<String>();
        list.add("My");
        list.add("name");
        list.add("is");
        list.add("Mud.");
        list.add("My");
        list.add("name");
        list.add("is");
        list.add("Mud.");
        list.add("My");
        list.add("name");
        list.add("is");
        list.add("Mud.");

        StdOut.print("List contents: ");
        for(String item : list)
            StdOut.print(item + " ");
        StdOut.println();
    }
}