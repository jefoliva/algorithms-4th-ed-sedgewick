/******************************************************************************
 *  Compilation:  javac E17_RobustRational 
 *  Execution:    java E17_RobustRational  
 *  Dependencies: StdOut 
 *	
 *  Description: 1.2.17 Robust implementation of rational numbers. Use assertions to 
 *  develop an implementation of Rational (see Exercise 1.2.16) that is immune to overflow. 
 *
 *  Example execution: (assertions must be enabled using -ea flag) 
 *  % java -ea E17_RobustRational
 *  Let:
 *  r1 = 9223372036854775807/1
 *  r2 = 4/3
 *
 *  r1 plus r2:    java.lang.AssertionError: Addition overflow error.
 *  r1 minus r2:   java.lang.AssertionError: Substraction overflow error.
 *  r1 times r2:   java.lang.AssertionError: Product overflow error.
 *  r1 divides r2: java.lang.AssertionError: Division overflow error.
 ******************************************************************************/
package chapter_1.section_2;
import edu.princeton.cs.algs4.StdOut;
import java.math.BigInteger;
/**
 * @author Jefoliva
 */

public class E17_RobustRational {
	public static void main(String[] args) {
		Rational.main(args);	
	}
}

class Rational {
	private final long numerator;
	private final long denominator;
	private final String PLUS_OVERFLOW_ERROR = "Addition overflow error.";
	private final String MINUS_OVERFLOW_ERROR = "Substraction overflow error.";
	private final String TIMES_OVERFLOW_ERROR = "Product overflow error.";
	private final String DIVIDES_OVERFLOW_ERROR = "Division overflow error.";

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
		assert !isOverflow(this.numerator(), (lcd / this.denominator()), "times") : PLUS_OVERFLOW_ERROR;
		assert !isOverflow(b.numerator(), (lcd / b.denominator()), "times") : PLUS_OVERFLOW_ERROR;

		long numA = this.numerator() * (lcd / this.denominator());
		long numB = b.numerator() * (lcd / b.denominator());

		assert !isOverflow(numA, numB, "plus") : PLUS_OVERFLOW_ERROR;
		return new Rational(numA + numB, lcd);
	}

	/**
	 * Substraction of rationals: find a common denominator, then multiply 
	 * each numerator by the number we have multiply the denominator.
	 * finally substract numerators and keep common denominator
	 */
	public Rational minus(Rational b) {
		long lcd = lcm(this.denominator(), b.denominator());
		assert !isOverflow(this.numerator(), (lcd / this.denominator()), "times") : MINUS_OVERFLOW_ERROR;
		assert !isOverflow(b.numerator(), (lcd / b.denominator()), "times") : MINUS_OVERFLOW_ERROR;

		long numA = this.numerator() * (lcd / this.denominator());
		long numB = b.numerator() * (lcd / b.denominator());
		
		assert !isOverflow(numA, numB, "minus") : MINUS_OVERFLOW_ERROR;
		return new Rational(numA - numB, lcd);
	}

	/**
	 * product of rationals: multiply numerator with numerator and
	 * denominator with denominator
	 */
	public Rational times(Rational b) {
		assert !isOverflow(this.numerator(), b.numerator(), "times") : TIMES_OVERFLOW_ERROR;
		assert !isOverflow(this.denominator(), b.denominator(), "times") : TIMES_OVERFLOW_ERROR;
		
		long numerator = this.numerator() * b.numerator();
		long denominator = this.denominator() * b.denominator();
		return new Rational(numerator, denominator);
	}

	/**
	 * division of rationals: cross product of numerators with denominators 
	 */
	public Rational divides(Rational b) {
		assert !isOverflow(this.numerator(), b.denominator(), "times") : DIVIDES_OVERFLOW_ERROR;
		assert !isOverflow(this.denominator(), b.numerator(), "times") : DIVIDES_OVERFLOW_ERROR;
		
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

	/**
	 * Checks for overflow according to the operations specified
	 * in the third parameter.
	 */
	private boolean isOverflow(long a, long b, String operation) {
		BigInteger bigC = null;

		switch(operation) {
			case "plus":
				bigC = BigInteger.valueOf(a).add(BigInteger.valueOf(b));
				break;
			case "minus":
				bigC = BigInteger.valueOf(a).subtract(BigInteger.valueOf(b));
				break;
			case "times":
				bigC = BigInteger.valueOf(a).multiply(BigInteger.valueOf(b));
				break;
		}
		if(bigC.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0)
		  return true;
		else if(bigC.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0)
		  return true;

		return false;
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
     */
	public static void main(String[] args) {
		Rational r1 = new Rational(Long.MAX_VALUE, 1);
		Rational r2 = new Rational(4, 3);

		StdOut.println("Let:");
		StdOut.println("r1 = " + r1);
		StdOut.println("r2 = " + r2);
		
		/**
         * Catching assetions is very unusual but otherwise
         * the program would stop when the first assetion
         * evaluates to false.
		 */
		StdOut.print("\nr1 plus r2:    ");
		try {StdOut.println(r1.plus(r2));}
		catch(AssertionError e) {StdOut.println(e);}
		
		StdOut.print("r1 minus r2:   ");
		try {StdOut.println(r1.minus(r2));}
		catch(AssertionError e) {StdOut.println(e);}
		
		StdOut.print("r1 times r2:   ");
		try {StdOut.println(r1.times(r2));}
		catch(AssertionError e) {StdOut.println(e);}
		
		StdOut.print("r1 divides r2: ");
		try {StdOut.println(r1.divides(r2));}
		catch(AssertionError e) {StdOut.println(e);}
	}
}