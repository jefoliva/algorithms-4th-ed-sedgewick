/******************************************************************************
 *  Compilation:  javac E34_RandomBag 
 *  Execution:    java E34_RandomBag  
 *  Dependencies: StdOut StdRandom
 *  
 *  Description: 1.3.34 Random bag. A random bag stores a collection of items 
 *  and supports the following API: 
 *  public class RandomBag<Item> implements Iterable<Item>
 *      RandomBag()          create an empty random bag
 *      boolean isEmpty()    is the bag empty?
 *      int size()           number of items in the bag
 *      void add(Item item)  add an item
 * 
 *  Write a class RandomBag that implements this API. Note that this API is the 
 *  same as for Bag, except for the adjective random, which indicates that the
 *  iteration should provide the items in random order (all N ! permutations 
 *  equally likely, for each iterator). Hint : Put the items in an array and
 *  randomize their order in the iterator’s constructor.
 *
 *  Example execution:
 *  % java E34_RandomBag
 *  Original LIFO order inside bag:
 *  10 9 8 7 6 5 4 3 2 1 0
 * 
 *  Using random iterator (1st try):
 *  7 5 2 9 6 4 10 0 1 3 8
 * 
 *  Using random iterator (2nd try):
 *  4 1 10 6 8 3 0 2 5 7 9
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E34_RandomBag {
    public static void main(String[] args) {
        RandomBag.main(args);
    }
}

class  RandomBag<Item> implements Iterable<Item> {
    private int N;
    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    } 

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Item a[] = randomizeItems();
        int current;

        public boolean hasNext() { return current < a.length; }
        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() { 
            return a[current++];
        }

        /**
         * This method rearrange the elements of an array ramdomly.
         */
        private Item[] randomizeItems() {
            Item a[] = storeItemsInArray();
            int n = a.length;
            for (int i = 0; i < n; i++) {
                int r = i + StdRandom.uniform(n-i);     // between i and n-1
                Item temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
            return a;
        }

        /**`
         * This method puts oll the elements of the bag into an array
         * @return items[] 
         */
        private Item[] storeItemsInArray() {
            Item a[] = (Item[]) new Object[size()];

            Node current = first;
            for(int i = 0; current != null; i++) {
                a[i] = current.item;
                current = current.next;
            }
            return a;
        }
    }
    
    public static void main(String[] args) {
        int[] items = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        var bag = new RandomBag<Integer>();

        for(int i = 0; i < items.length; i++)
            bag.add(i);

        StdOut.println("Original LIFO order inside bag:");
        for(int i = 0; i < items.length; i++)
            StdOut.print(items[i] + " ");
        StdOut.println();

        StdOut.println("\nUsing random iterator (1st try):");
        for(int i : bag)
            StdOut.print(i + " ");
        StdOut.println();

        StdOut.println("\nUsing random iterator (2nd try):");
        for(int i : bag)
            StdOut.print(i + " ");
        StdOut.println();
    }
}