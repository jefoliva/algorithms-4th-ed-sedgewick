/******************************************************************************
 *  Compilation:  javac E33 
 *  Execution:    java E33  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.33 Deque. A double-ended queue or deque (pronounced "deck") 
 *  is like a stack or a queue but supports adding and removing items at both ends.
 *  A deque stores a collection of items and supports the following API:
 *  public class Deque<Item> implements Iterable<Item>
 *  Deque()                   create an empty deque
 *  boolean isEmpty()         is the deque empty?
 *  int size()                number of items in the deque
 *  void pushLeft(Item item)  add an item to the left end
 *  void pushRight(Item item) add an item to the right end
 *  Item popLeft()            remove an item from the left end
 *  Item popRight()           remove an item from the right end
 *
 *  Example execution:
 *  % java E33 
 *  Doubly-linked list deque:
 *  deque contents:   b d e f g
 *  pushLeft(a):      a b d e f g
 *  pushRight(h):     a b d e f g h
 *  popLeft():        b d e f g h
 *  popRight():       b d e f g
 *
 *  ResizingArrayDeque:
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

public class E33 {
    public static void main(String[] args) {
        StdOut.println("Doubly-linked list deque:");
        Deque.main(args);

        StdOut.println("\nResizingArrayDeque:");
        ResizingArrayDeque.main(args);
    }
}

class Deque<Item> implements Iterable<Item> {
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

class ResizingArrayDeque<Item> implements Iterable<Item> {
    private int N;
    private int first;
    private int last;

    private Item a[] = (Item[]) new Object[1];

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    private void resize(int max) {
     Item temp[] = (Item[]) new Object[max];
     for(int i = 0; i < a.length; i++)
         temp[i] = a[i];
     a = temp;
    }

    private boolean isFull() { return N == a.length; }

    public void pushLeft(Item item) {
        if(isFull())
            resize(a.length*2);

        if(first != 0) {
            a[first--] = item;
        } else {
            Item temp[] = (Item[]) new Object[a.length];
            temp[0] = item;
            for(int i = 1; i < a.length; i++)
                temp[i] = a[i-1];
            a = temp;
            last++;
        }
        N++;
    }

    public void pushRight(Item item) {
        if(isFull())
            resize(a.length*2);

        a[last++] = item;
        N++;
    }

    public Item popLeft() {
        if(isEmpty()) throw new NoSuchElementException("deque underflow.");
        Item item;

        if(size() == 1) {
            item = a[first];
            a[first] = null;
        } else {
            item = a[first];
            a[first] = null;
            first++;
        }
        N--;
        return item;
    }

    public Item popRight() {
        if(isEmpty()) throw new NoSuchElementException("deque underflow.");
        
        Item item = a[--last];
        a[last] = null;
        N--; 
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayTraverse();
    }

    private class ArrayTraverse implements Iterator<Item> {
        int size = N;
        int current = first;

        public boolean hasNext() { return size != 0; }
        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            Item item = a[current++];
            size--;
            return item;
        }
    }

    public void printFirstAndLast() {
        StdOut.println("first: " +  first);
        StdOut.println("last: " +  last);
        StdOut.println("a.length:" + a.length);
    }

    public static void main(String[] args) {
        var deque = new ResizingArrayDeque<String>();
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