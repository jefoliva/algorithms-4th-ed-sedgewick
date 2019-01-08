/******************************************************************************
 *  Compilation:  javac E35_RandomQueue 
 *  Execution:    java E35_RandomQueue  
 *  Dependencies: StdOut StdRandom
 *  
 *  Description: 1.3.35 Random queue. A random queue stores a collection of 
 *  items and supports the following API:
 *  public class RandomQueue<Item>
 *      RandomQueue()           create an empty random queue
 *      boolean isEmpty()       is the queue empty?
 *      void enqueue(Item item) add an item
 *      Item dequeue()          remove and return a random item (sample without replacement)
 *      Item sample()           return a random item, but do not remove   
 *                              (sample with replacement)
 *
 *  Example execution:
 *  % java E35_RandomQueue
 *  North:
 *  3 of spades
 *  J of clubs
 *  10 of clubs
 *  4 of diamonds
 *  3 of hearts
 *  5 of clubs
 *	Q of spades
 * 	K of spades
 * 	10 of diamonds
 *	10 of hearts
 * 	A of spades
 * 	8 of diamonds
 * 	8 of hearts
 *
 *	East:
 *	6 of clubs
 *	8 of spades
 *	J of diamonds
 *	9 of hearts
 *	J of hearts
 *	6 of spades
 *	J of spades
 *	10 of spades
 *	A of clubs
 *	K of hearts
 *	Q of clubs
 *	Q of hearts
 *	9 of spades
 *	
 *	South:
 *	2 of clubs
 *	2 of spades
 *	5 of diamonds
 *	5 of spades
 *	K of clubs
 *	5 of hearts
 *	4 of spades
 *	6 of hearts
 *	7 of clubs
 *	A of diamonds
 *	4 of hearts
 *	7 of spades
 *	8 of clubs
 * 
 *	West:
 *	2 of diamonds
 *	K of diamonds
 *	9 of clubs
 *	7 of hearts
 *	Q of diamonds
 *	9 of diamonds
 *	4 of clubs
 *	6 of diamonds
 *	3 of diamonds
 *	2 of hearts
 *	7 of diamonds
 *	3 of clubs
 *	A of hearts
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E35_RandomQueue {
    public static void main(String[] args) {
        RandomQueue.main(args);
    }
}

class RandomQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N;
    private int last;

    RandomQueue() {
        q = (Item[]) new Object[2];
        last = 0;
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for(int i = 0; i < N; i++) {
            temp[i] = q[i];
        }
        q = temp;
        last  = N;
    }

    public void enqueue(Item item) {
        // double size of array if necessary and recopy to front of array
        if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        N++;
    }

    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("queue underflow");   
        // Generate random index for the element to dequeue
        int r = StdRandom.uniform(0, N);
    	
        Item randomItem = q[r];
        Item lastItem = q[last-1];
        q[r] = lastItem;
        q[last-1] = null;   		// Avoid loittering
        last--;
        N--;

        // Resize if N is less than half the queue capacity
        if(N > 0 && N == q.length/4) resize(q.length/2);
        
        return randomItem;
    }

    public Item sample() {
    	int r = StdRandom.uniform(0, N);
    	return q[r];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext()  { return i < N; }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[i];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        var bridgeDeck = new RandomQueue<Card>();
    	String[] suits = {"hearts", "spades", "clubs", "diamonds"};
        String[] cardVals = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    	String[] players = {"North", "East", "South", "West"};
    	int cardsPerPlayer = cardVals.length;

    	// Compose bridgeDeck of 52 cards
    	for(int i = 0; i < suits.length; i++) {
    		for(int j = 0; j < cardVals.length; j++) {
    			bridgeDeck.enqueue(new Card(cardVals[j], suits[i]));
    		}
    	}

    	// Deal bridge cards randomly
    	for(int i = 0; i < players.length; i++) {
    		StdOut.println(players[i] + ": ");
    		for(int j = 0; j < cardsPerPlayer; j++) {
    			StdOut.println(bridgeDeck.dequeue());
    		}
    		StdOut.println();
    	}
    }
}

class Card {
    private String value;
    private String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format("%-2s of %-8s", value, suit);
    }
}
