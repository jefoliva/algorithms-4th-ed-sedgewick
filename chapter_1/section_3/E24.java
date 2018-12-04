/******************************************************************************
 *  Compilation:  javac E24 
 *  Execution:    java E24  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.24 Write a method removeAfter() that takes a linked-list 
 *  Node as argument and removes the node following the given one (and does nothing
 *  if the argument or the next field in the argument node is null)
 *
 *  Example execution: 
 *  % java E24
 *  myList:             A B C D E
 *  removeAfter(nodeB): A B D E
 *  removeAfter(nodeE): A B D E  [nothing happens]
 *  removeAfter(null):  A B D E  [nothing happens]
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E24 {
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
        List<String> myList = new List<>();

        // Manually create the nodes for illustration purposes
        List<String>.Node nodeA = myList.createNode("A");
        List<String>.Node nodeB = myList.createNode("B");
        List<String>.Node nodeC = myList.createNode("C");
        List<String>.Node nodeD = myList.createNode("D");
        List<String>.Node nodeE = myList.createNode("E");

        // Add nodes
        myList.add(nodeA);
        myList.add(nodeB);
        myList.add(nodeC);
        myList.add(nodeD);
        myList.add(nodeE);

        StdOut.print("myList:             ");
        for(String item : myList)
            StdOut.print(item + " ");
        StdOut.println();

        // Remove node after nodeB, namely nodeC
        myList.removeAfter(nodeB);
        StdOut.print("removeAfter(nodeB): ");
        for(String item : myList)
            StdOut.print(item + " ");
        StdOut.println();

        // Invoke method using last node, nothing happens
        myList.removeAfter(nodeE);
        StdOut.print("removeAfter(nodeE): ");
        for(String item : myList)
            StdOut.print(item + " ");
        StdOut.println(" [nothing happens]");

        // Invoke method using null, nothing happens
        myList.removeAfter(null);
        StdOut.print("removeAfter(null):  ");
        for(String item : myList)
            StdOut.print(item + " ");
        StdOut.println(" [nothing happens]");
    }
}