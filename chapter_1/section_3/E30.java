/******************************************************************************
 *  Compilation:  javac E30 
 *  Execution:    java E30  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.30 Write a function that takes the first Node in a linked 
 *  list as argument and (destructively) reverses the list, returning the first 
 *  Node in the result.
 *
 *  Example execution:
 *  % java E30 
 *  List contents:           a b c d
 *  list.reverse(firstNode): d c b a
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E30 {
    public static void main(String[] args) {
       List.main(args);
    }
}

class List<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    public class Node {
        Item item;
        Node next;
    }
    
    private Node createNode(Item item) {
        Node node = new Node();
        node.item = item;
        return node;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    public Node first() { return first; }

    public void add(Node node) {
        Node oldlast = last;
        last = node;
        last.next = null;
        if(isEmpty()) first = last;
        else          oldlast.next = last;
        N++;
    }

    public void add(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else          oldlast.next = last;
        N++;
    }

    /*
     * Delete the kth item in the list, if exist.
     * This impelementation is zero-based index.
     */
    public void delete(int k) {
        // if kth element not exists
        if(k > size()-1) throw new NoSuchElementException("Kth is greater than list's size");
        
        // Delete first element in the list
        if(k == 0) {
            first = first.next;
            N--;
            return;
        }

        Node head = first;
        for(int i = 0; i < k; i++, head = head.next ) {
            if(i == k-1) {
                head.next = head.next.next;
                N--;
                break;
            }
        }       
    }

    public void removeAfter(Node node) {
        // argument equals to null or next node is null, do nothing
        if(node == null || node.next == null) {
            return;
        }

        /* If next field in next node is null, it means next node is the
         * last node in the list. Being so, just update next field of
         * current node to null 
         */
        if(node.next.next == null) 
            node.next = null;
        else
            node.next = node.next.next;

        N--;
    }

    public void insertAfter(Node a, Node b) {
        // If either argument is null, do nothing
        if(a == null || b == null)
            return;

        b.next = a.next;
        a.next = b;
    }

    public void remove(Item key) {
        if(isEmpty()) throw new NoSuchElementException("List is empty");
        Node head = first;

        // if first element in list equals to key remove
        if(head.item.equals(key)) {
            first = first.next;
            N--;
        }

        while(head.next != null) {
            if(head.next.item.equals(key)) {
                head.next = head.next.next;
                N--;    
                continue;
            }
            head = head.next;
        }
    }

    public Node reverse(Node x) {
        Node first = x;
        Node reverse = null;
        while(first != null) {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        this.first = reverse;
        return reverse;
    }

    public Node recReverse(Node first) {
        if (first == null) return null;
        if (first.next == null) return first;

        Node second = first.next;
        Node rest = reverse(second);
        second.next = first;
        first.next = null;
        return rest;
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
        List<String> list = new List<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        StdOut.print("List contents:           ");
        for(String item : list)
            StdOut.print(item + " ");
        StdOut.println();

        List<String>.Node first = list.first();
        first = list.reverse(first);

        StdOut.print("list.reverse(firstNode): ");
        while(first != null) {
            StdOut.print(first.item + " ");
            first = first.next;
        }
        StdOut.println();        
    }
}