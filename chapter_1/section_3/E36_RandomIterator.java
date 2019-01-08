/******************************************************************************
 *  Compilation:  javac E36_RandomIterator 
 *  Execution:    java E36_RandomIterator  
 *  Dependencies: StdOut StdRandom
 *  
 *  Description: 1.3.36 Random iterator. Write an iterator for RandomQueue<Item>
 *  from the previous exercise that returns the items in random order.
 *
 *  Example execution:
 *  % java E36_RandomIterator
 *   Q of diamonds
 *   A of spades
 *   9 of hearts
 *   2 of clubs
 *   A of clubs
 *   J of hearts
 *   A of hearts
 *   8 of hearts
 *   5 of diamonds
 *   7 of diamonds
 *   7 of spades
 *   3 of diamonds
 *   2 of hearts
 *   3 of hearts
 *   2 of spades
 *  10 of hearts
 *   A of diamonds
 *   8 of clubs
 *   4 of diamonds
 *  10 of clubs
 *   J of clubs
 *   6 of clubs
 *   5 of clubs
 *   K of spades
 *   8 of spades
 *   K of clubs
 *  10 of diamonds
 *   Q of clubs
 *   9 of spades
 *   6 of hearts
 *   3 of spades
 *   4 of spades
 *   4 of clubs
 *   7 of hearts
 *   J of diamonds
 *   6 of diamonds
 *   J of spades
 *   8 of diamonds
 *   9 of diamonds
 *  10 of spades
 *   Q of spades
 *   5 of hearts
 *   Q of hearts
 *   K of hearts
 *   7 of clubs
 *   2 of diamonds
 *   9 of clubs
 *   4 of hearts
 *   3 of clubs
 *   5 of spades
 *   K of diamonds
 *   6 of spades
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E36_RandomIterator {
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

        public ArrayIterator() {
            int n = N;

            // Shuffle array
            for (int i = 0; i < n; i++) {
                int r = i + StdRandom.uniform(n-i);     // between i and n-1
                Item temp = q[i];
                q[i] = q[r];
                q[r] = temp;
            }
        }

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

    	// Iterate bridgeDeck randomly
    	for(Card currentCard : bridgeDeck) {
            StdOut.println(currentCard);
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
 		return String.format("%2s of %-8s", value, suit);
 	}
}
