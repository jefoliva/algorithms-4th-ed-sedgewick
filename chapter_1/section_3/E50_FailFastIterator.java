/******************************************************************************
 *  Compilation:  javac E48_TwoStacksWithADeque.java
 *  Execution:    java E48_TwoStacksWithADeque
 *  Dependencies: StdOut Stack Queue
 *  
 *  Description: 1.3.50 Fail-fast iterator. Modify the iterator code in Stack to 
 *  immediately throw a java.util.ConcurrentModificationException if the client 
 *  modifies the collection (via push() or pop()) during iteration? b).
 *
 *  Example execution:
 *  % java E47_CatenableQueuesStacksOrSteques
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

/**
 * @author jefoliva
 */

public class E50_FailFastIterator {
    public static void main(String[] args)
        throws ConcurrentModificationException {
       FailFastStack<String> s = new FailFastStack<>();

       s.push("a");
       s.push("b");
       s.push("c");
       s.push("d");
       s.push("e");
       s.push("f");
       int count = 1;

        StdOut.print("Normal iteration:             ");
        for(String item : s)
            StdOut.print(item + " ");
        StdOut.println();

        /* Pop an element when count is equal to 3, which triggers
           the concurrent exception */
        StdOut.print("Pop element during iteration: ");
        for(String item : s) {
            StdOut.print(item + " ");

            if(count == 3) {
                s.pop();
            }
            count++;
       }
    } 
}

class FailFastStack<Item> implements Iterable<Item> {
    Node first;
    int n;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return n; }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow.");

        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;
        int size = n;

        public boolean hasNext() {
            if(size != n) throw new ConcurrentModificationException("Concurrent Error");

            return current != null;
        }

        public Item next() {
            if(size != n) throw new ConcurrentModificationException("Concurrent Error");
            
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}