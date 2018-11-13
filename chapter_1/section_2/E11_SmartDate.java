/******************************************************************************
 *  Compilation:  javac E11_SmartDate 
 *  Execution:    java E11_SmartDate  
 *  Dependencies: StdOut
 *	
 *  Description: 1.2.11 Develop an implementation SmartDate of our Date API that raises an exception
 *  if the date is not legal.
 *
 *  Example exec: 
 *  % java E11_SmartDate 
 * 	Date 1: 7/11/2018
 *  Date 2: Exception: Invalid date
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */
public class E11_SmartDate {
	public static void main(String[] args) {
		SmartDate.main(args);		
	}
}

class SmartDate {
	private final int day;
    private final int month;
    private final int year;
    private final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public SmartDate(int day, int month, int year) throws InvalidDateException {
        if(!isValid(day, month, year)) throw new InvalidDateException();
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Create custom exception
    private class InvalidDateException extends Exception {
        @Override
        public String toString() {
            return "Exception: Invalid date";
        }
    }

    public int day() { return day; }
    public int month() { return month; }
    public int year() { return year; }

    private boolean isLeapYear(int y) {
        if(y % 400 == 0) return true;
        if(y % 100 == 0) return false;
        return y % 4 == 0;
    }

    private boolean isValid(int d, int m, int y) {
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
    public boolean equals(Object x) {
        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;
        SmartDate that = (SmartDate) x;
        if(this.day() != that.day()) return false;
        if(this.month() != that.month()) return false;
        if(this.year() != that.year()) return false;
        return true;
    }

    public static void main(String[] args) {
        try {
            var date1 = new SmartDate(7, 11, 2018);
            StdOut.println("Date 1: " + date1);

            // Invalid date
            var date2 = new SmartDate(7, 13, 2018);
            StdOut.println(date1);
        } catch(InvalidDateException exc) {
            StdOut.println("Date 2: " + exc);
        }
    }	
}

