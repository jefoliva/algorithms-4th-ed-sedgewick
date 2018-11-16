/******************************************************************************
 *  Compilation:  javac E16_Rational 
 *  Execution:    java E16_Rational  
 *  Dependencies: StdOut 
 *	
 *  Description: 1.2.16 Rational numbers. Implement an immutable data type Rational for rational
 *  numbers that supports addition, subtraction, multiplication, and division.
 *
 *  Example execution: 
 *  % java E16_Rational
 *  Let:
 *  r1 = 9/16
 *  r2 = 3/32
 *  r1 plus r2:    21/32
 *  r1 minus r2:   15/32
 *  r1 times r2:   27/512
 *  r1 divides r2: 6/1
 *  r1 equals r2:  false
 ******************************************************************************/
package chapter_1.section_2;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Jefoliva
 */

public class E16_Rational {
	public static void main(String[] args) {
		Rational.main(args);	
	}
}

class Rational {
	private final long numerator;
	private final long denominator;

	/**
	 * Initializes a Rational and, if possible, simplify
	 * using euclid's algorithm for computing gcd
	 * @param numerator the numerator
	 * @param denominator the denominator
	 */
	public Rational(long numerator, long denominator) {
		long gcd = gcd(numerator, denominator);
		if(gcd != 1) {
			numerator = numerator / gcd;
			denominator = denominator / gcd; 
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public long numerator() { return numerator; }

	public long denominator() { return denominator; }

	/**
	 * Sum of rationals: find a common denominator, then multiply 
	 * each numerator by the number we have multiply the denominator.
	 * finally add numerators and keep common denominator
	 */
	public Rational plus(Rational b) {
		long lcd = lcm(this.denominator(), b.denominator());
		long numA = this.numerator() * (lcd / this.denominator());
		long numB = b.numerator() * (lcd / b.denominator());
		return new Rational(numA + numB, lcd);
	}

	/**
	 * Substraction of rationals: find a common denominator, then multiply 
	 * each numerator by the number we have multiply the denominator.
	 * finally substract numerators and keep common denominator
	 */
	public Rational minus(Rational b) {
		long lcd = lcm(this.denominator(), b.denominator());
		long numA = this.numerator() * (lcd / this.denominator());
		long numB = b.numerator() * (lcd / b.denominator());
		return new Rational(numA - numB, lcd);
	}

	/**
	 * product of rationals: multiply numerator with numerator and
	 * denominator with denominator
	 */
	public Rational times(Rational b) {
		long numerator = this.numerator() * b.numerator();
		long denominator = this.denominator() * b.denominator();
		return new Rational(numerator, denominator);
	}

	/**
	 * division of rationals: cross product of numerators with denominators 
	 */
	public Rational divides(Rational b) {
		long numerator = this.numerator() * b.denominator();
		long denominator = this.denominator() * b.numerator();
		return new Rational(numerator, denominator);
	}

	/**
	 * Greatest common divisor (gcd) using Euclid´s algorithm
	 * @return gcd
	 */
	private static long gcd(long p, long q) {
		if (q == 0) return p;
		long r = p % q;
		return gcd(q, r);
	}

	/**
	 * Least common multiple (lcm) used to find least common denominator
	 * @return lcm
	 */
	private static long lcm(long p, long q) {
		return (p * q) / gcd(p, q); 
	}

	@Override
	public boolean equals(Object x) {
		if(this == x) return true;
		if(x == null) return false;
		if(this.getClass() != x.getClass()) return false;

		Rational that = (Rational) x;
		if(this.numerator() != that.numerator()) return false;
		if(this.denominator() != that.denominator()) return false;
		return true;
	} 

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	/**
     * Unit tests the {@code Rational} data type.
     * @param args the command-line arguments
     */
	public static void main(String[] args) {
		Rational r1 = new Rational(9, 16);
		Rational r2 = new Rational(3, 32);

		StdOut.println("Let:");
		StdOut.println("r1 = " + r1);
		StdOut.println("r2 = " + r2);

		StdOut.print("r1 plus r2:    ");
		StdOut.println(r1.plus(r2));

		StdOut.print("r1 minus r2:   ");
		StdOut.println(r1.minus(r2));

		StdOut.print("r1 times r2:   ");
		StdOut.println(r1.times(r2));

		StdOut.print("r1 divides r2: ");
		StdOut.println(r1.divides(r2));

		StdOut.print("r1 equals r2:  ");
		StdOut.println(r1.equals(r2));
	}
}