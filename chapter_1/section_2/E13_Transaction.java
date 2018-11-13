/******************************************************************************
 *  Compilation:  javac E13_Transaction 
 *  Execution:    java E13_Transaction  
 *  Dependencies: StdOut Date StdRandom
 *	
 *  Description: 1.2.13 Using our implementation of Date as a model (page 91), 
 *  develop an implementation of Transaction
 *
 *  Example execution: 
 *  % java E13_Transaction
 *   who: John Doe, when: 6/18/2018, amount: 9050.7
 *   who: John Doe, when: 7/10/2018, amount: 4727.64
 *   who: John Doe, when: 6/9/2018, amount: 7322.4
 ******************************************************************************/
package chapter_1.section_2;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;

/**
 * @author Jefoliva
 */

public class E13_Transaction {
	public static void main(String[] args) {
		Transaction.main(args);		
	}
}

class Transaction {
	private String who;
	private Date when;
	private double amount;

	public Transaction(String who, Date when, double amount) {
		this.who = who;
		this.when = when;
		this.amount = amount;
	}

	public String who() { return who; }
	public Date when() { return when; }
	public double amount() { return amount;}

	@Override
	public String toString() {
		return "who: " + who + ", when: " + when + ", amount: " + amount;
	}

	public static void main(String[] args) {
    	int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		var transactionList = new ArrayList<Transaction>();

		for(int i = 0; i < 10; i++) {
			int m = StdRandom.uniform(1, 13);
			int d = StdRandom.uniform(1, DAYS[m]);
			double amount = StdRandom.uniform(100.0, 10000.0);
			       amount = Math.round(amount * 100d) / 100d;  // Set precision to two decimals
			transactionList.add(new Transaction("John Doe", new Date(m, d, 2018), amount));
		}

		for(Transaction tempT : transactionList) 
			StdOut.println(tempT);
	}
}