/******************************************************************************
 *  Compilation:  javac E18_VarianceForAccumulator 
 *  Execution:    java E18_VarianceForAccumulator  
 *  Dependencies: StdOut StdRandom
 *	
 *  Description: 1.2.19 Parsing. Develop the parse constructors for your Date 
 *  and Transaction implementations of Exercise 1.2.13 that take a single String 
 *  argument to specify the initialization values, using the formats given in the table below.
 *  Date          integers separated by slashes:  5/22/1939
 *  Transaction   customer, date, and amount, separated by whitespace:  Turing 5/22/1939 11.99
 *
 *  Example execution: 
 *  % java E19_Parsing
 * 
 ******************************************************************************/
package chapter_1.section_2;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

/**
 * @author Jefoliva
 */

public class E19_Parsing {
	public static void main(String[] args) {
		Transaction.main(args);
	}
}

// Create custom exception
class InvalidDateException extends Exception {
	@Override
	public String toString() {
		return "Exception: Invalid date";
	}
}

class Date {
	private final int day;
    private final int month;
    private final int year;
    private final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final String[] DAYNAMES = {"Saturday","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    public Date(String date) throws InvalidDateException {
    	String[] fields = date.split("/");
    	month = Integer.parseInt(fields[0]);
    	day   = Integer.parseInt(fields[1]);
    	year  = Integer.parseInt(fields[2]);

    	if(!isValid(month, day, year)) throw new InvalidDateException();
    }

    public Date(int day, int month, int year) throws InvalidDateException {
        if(!isValid(month, day, year)) throw new InvalidDateException();
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int day() { return day; }
    public int month() { return month; }
    public int year() { return year; }

    /**
     * Compute day of the week using Zeller's Congruence algorithm
     * @return day of the week as a String
     */
    public String dayOfTheWeek() {
    	int m = month();
    	int d = day();
    	int y = year();
    	// This algorithm needs to treat january and february as 13 and 14 respectively
    	if (m == 1) m = 13;
    	if (m == 2) m = 14;

    	final int J = y / 100;
    	final int K = y % 100;
    	int h = (d + ((13*(m+1))/5) + K + (K/4) + (J/4) + (5*J))  % 7;
    	return DAYNAMES[h];
    }

    private boolean isLeapYear(int y) {
        if(y % 400 == 0) return true;
        if(y % 100 == 0) return false;
        return y % 4 == 0;
    }

    private boolean isValid(int m, int d, int y) {
        if(m < 1 || m > 12)      return false;
        if(d < 1 || d > DAYS[m]) return false;
        if(m == 2 && d == 29 && !isLeapYear(year)) return false;
        return true;
    }

    @Override 
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Date that = (Date) other;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
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

	public Transaction(String transaction) throws InvalidDateException {
		String[] fields = transaction.trim().split(" ");
		if(fields.length != 3) throw new RuntimeException("Invalid input.");
		
		who = fields[0];
		when = new Date(fields[1]);
		amount = Double.parseDouble(fields[2]);
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
		try { 
			var t1 = new Transaction("John 11/15/2018 1000.0");
			var t2 = new Transaction("Maria 11/1/2018 500.0");
			var t3 = new Transaction("John 11/15/2018 1000.0");

	    	StdOut.println("t1: " + t1);
	    	StdOut.println("t2: " + t2);
	    	StdOut.println("t3: " + t3);
	    	StdOut.println("is t1 equals t2: " + t1.equals(t2));
	    	StdOut.println("is t1 equals t3: " + t1.equals(t3));
		} catch(InvalidDateException e) {
			StdOut.println(e);
		}
	}
}