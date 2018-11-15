/******************************************************************************
 *  Compilation:  javac E14_Transaction 
 *  Execution:    java E14_Transaction  
 *  Dependencies: StdOut Date StdRandom
 *	
 *  Description: Using our implementation of equals() in Date as a model (page 103), develop
 *  implementations of equals() for Transaction.
 *
 *  Example execution: 
 *  % java E14_Transaction
 *   t1: who: John Doe, when: 11/9/2018, amount: 1000.0
 *   t2: who: Maria Doe, when: 11/1/2018, amount: 500.0
 *   t3: who: John Doe, when: 11/9/2018, amount: 1000.0
 *   is t1 equals t2: false
 *   is t1 equals t3: true
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;

/**
 * @author Jefoliva
 */

public class E14_Transaction {
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
	public boolean equals(Object x) {
		if(this == x) return true;
		if(x == null) return false;
		if(this.getClass() != x.getClass()) return false;
		
		Transaction that = (Transaction) x;
		if(!this.who.equals(that.who)) return false;
		if(!this.when.equals(that.when)) return false;
		if(this.amount() != that.amount()) return false;
		return true;
	}

	@Override
	public String toString() {
		return "who: " + who + ", when: " + when + ", amount: " + amount;
	}

	public static void main(String[] args) {
    	var t1 = new Transaction("John Doe", new Date(11, 9, 2018), 1000.0);
    	var t2 = new Transaction("Maria Doe", new Date(11, 1, 2018), 500.0);
    	var t3 = new Transaction("John Doe", new Date(11, 9, 2018), 1000.0);
	
    	StdOut.println("t1: " + t1);
    	StdOut.println("t2: " + t2);
    	StdOut.println("t3: " + t3);
    	StdOut.println("is t1 equals t2: " + t1.equals(t2));
    	StdOut.println("is t1 equals t3: " + t1.equals(t3));
	}
}