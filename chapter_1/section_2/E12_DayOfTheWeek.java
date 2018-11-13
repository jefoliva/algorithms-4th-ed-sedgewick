/******************************************************************************
 *  Compilation:  javac E12_DayOfTheWeek 
 *  Execution:    java E12_DayOfTheWeek  
 *  Dependencies: StdOut
 *	
 *  Description: 1.2.12 Add a method dayOfTheWeek() to SmartDate that returns a String value
 *  Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, or Sunday, giving the appropriate 
 *  day of the week for the date. You may assume that the date is in the 21st century.
 *
 *  Example execution: 
 *  % java E12_DayOfTheWeek 
 * 	Date 1: 7/11/2018
 *  Day of the week: Wednesday
 *
 *  today's date: 8/11/2018
 *  Day of the week: Thursday
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;
import java.util.Calendar;
/**
 * @author Jefoliva
 */
public class E12_DayOfTheWeek {
	public static void main(String[] args) {
		SmartDate.main(args);		
	}
}

class SmartDate {
	private final int day;
    private final int month;
    private final int year;
    private final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final String[] DAYNAMES = {"Saturday","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};


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
            SmartDate date1 = new SmartDate(7, 11, 2018);
            StdOut.println("Date 1: " + date1);
            StdOut.println("Day of the week: " + date1.dayOfTheWeek());

            // Get today's date
            Calendar cal = Calendar.getInstance();
			int d = cal.get(Calendar.DAY_OF_MONTH);
			int m = cal.get(Calendar.MONTH);
			int y = cal.get(Calendar.YEAR);
			StdOut.println();

			SmartDate date2 = new SmartDate(d, m+1, y);
			StdOut.println("today's date: " + date2);
            StdOut.println("Day of the week: " + date2.dayOfTheWeek());
        } catch(InvalidDateException exc) {
            StdOut.println(exc);
        }
    }	
}